package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;
//import com.game.hauntedVillage.model.Sound;
import com.game.hauntedVillage.model.SoundHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class TopPanel extends JPanel {

    private GameManager baseController;
    private final ArrayList<JLabel> heart = new ArrayList<>();
    SoundHandler soundHandler = new SoundHandler();
    //Sound sound = new Sound();

    //generates top panel
    public TopPanel(GameManager baseController) {
        this.baseController = baseController;
        setLayout(null);
        setBounds(50, 50, 900, 50);
        setBackground(null);
        setOpaque(true);
        createHeartIcons();
        createMapIcons();
        createSound();
        createHelp();
    }

    //create icons
    public void createHeartIcons() {
        int hp = baseController.getEngine().getPlayer().getHp();
        for (int i = 0; i < hp; i++) {
            JLabel heartLabel = new JLabel();
            heartLabel.setBounds(0 + (44 * i), 0, 44, 30);
            ImageIcon heart = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/heart.png")));
            heartLabel.setIcon(heart);
            add(heartLabel);
        }
    }

    public void createMapIcons() {
        JLabel mapLabel = new JLabel();
        mapLabel.setBounds(750, 0, 44, 30);
        mapLabel.addMouseListener(new Maplistener());
        ImageIcon map = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/heart.png")));
        mapLabel.setIcon(map);
        add(mapLabel);
    }

    public void createSound() {
        //Label/icon for music
        JLabel soundLabel = new JLabel();
        soundLabel.setBounds(800, 0, 44, 30);
        ImageIcon soundIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/musicNote.png")));
        soundLabel.setIcon(soundIcon);
        add(soundLabel);

        //Creates menu
        JPopupMenu popUp = new JPopupMenu();
        popUp.setLayout(new GridLayout(1, 3));

        //Volume Down
        JMenuItem item1 = new JMenuItem();
        JButton volumeDownBtn = new JButton();
        ImageIcon volDownIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/volumeMinus.png")));
        volumeDownBtn.setIcon(volDownIcon);
        add(volumeDownBtn);
        volumeDownBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundHandler.volumeDown();
            }
        });
        item1.add(volumeDownBtn);
        popUp.add(item1);

        //Toggle Mute
        JMenuItem item2 = new JMenuItem();
        JButton muteBtn = new JButton();
        ImageIcon muteIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/muteIcon.png")));
        muteBtn.setIcon(muteIcon);
        add(muteBtn);
        muteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundHandler.toggleMute();
            }
        });
        item2.add(muteBtn);
        popUp.add(item2);

        //Volume Up
        JMenuItem item3 = new JMenuItem();
        JButton volumeUpBtn = new JButton();
        ImageIcon volUpIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/volumePlus.png")));
        volumeUpBtn.setIcon(volUpIcon);
        add(volumeUpBtn);
        volumeUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundHandler.volumeUp();
            }
        });
        item3.add(volumeUpBtn);
        popUp.add(item3);

        soundLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                popUp.show(soundLabel, e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        popUp.pack();
        setVisible(true);
        URL soundUrl = getClass().getResource("/Sound/music.wav");
        soundHandler.playMusic(soundUrl);
    }

    public void createHelp() {
        JLabel helpIcon = new JLabel();
        helpIcon.setBounds(850, 0, 44, 30);
        ImageIcon map = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/heart.png")));
        helpIcon.setIcon(map);
        add(helpIcon);
    }

    // Mouse/Action listener
    private class Maplistener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            MapFrame map = new MapFrame(baseController);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
