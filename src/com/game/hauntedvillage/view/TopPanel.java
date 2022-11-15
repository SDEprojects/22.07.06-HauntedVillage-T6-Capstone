package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;
import com.game.hauntedvillage.utility.SoundHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Objects;

class TopPanel extends JPanel {

    private final GameManager baseController;
    SoundHandler soundHandler = new SoundHandler();
    //Sound sound = new Sound();

    //generates top panel
    public TopPanel(GameManager baseController) {
        this.baseController = baseController;
        setLayout(null);
        setBounds(50, 10, 900, 50);
        setBackground(null);
        setOpaque(true);
        createHeartIcons();
        createMapIcons();
        createSound();
        createHelp();
    }

    //create icons
    private void createHeartIcons() {
        int hp = baseController.getEngine().getPlayer().getHp();
        for (int i = 0; i < hp; i++) {
            JLabel heartLabel = new JLabel();
            heartLabel.setBounds((44 * i), 0, 44, 30);
            ImageIcon heart = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/heart.png")));
            heartLabel.setIcon(heart);
            add(heartLabel);
        }
    }

    private void createMapIcons() {
        JLabel mapLabel = new JLabel();
        mapLabel.setBounds(750, 0, 44, 30);
        mapLabel.addMouseListener(new MapListener());
        ImageIcon map = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/map.png")));
        mapLabel.setIcon(map);
        add(mapLabel);
    }

    private void createHelp() {
        JLabel helpIcon = new JLabel();
        helpIcon.setBounds(850, 0, 44, 30);
        ImageIcon map = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/help.png")));
        helpIcon.addMouseListener(new HelpListener());
        helpIcon.setIcon(map);
        add(helpIcon);
    }

    private void createSound() {
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
        volumeDownBtn.addActionListener(e -> soundHandler.volumeDown());
        item1.add(volumeDownBtn);
        popUp.add(item1);

        //Toggle Mute
        JMenuItem item2 = new JMenuItem();
        JButton muteBtn = new JButton();
        ImageIcon muteIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/muteIcon.png")));
        muteBtn.setIcon(muteIcon);
        add(muteBtn);
        muteBtn.addActionListener(e -> soundHandler.toggleMute());
        item2.add(muteBtn);
        popUp.add(item2);

        //Volume Up
        JMenuItem item3 = new JMenuItem();
        JButton volumeUpBtn = new JButton();
        ImageIcon volUpIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/volumePlus.png")));
        volumeUpBtn.setIcon(volUpIcon);
        add(volumeUpBtn);
        volumeUpBtn.addActionListener(e -> soundHandler.volumeUp());
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

    // Mouse/Action listener
    private class MapListener implements MouseListener {

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

    private class HelpListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            HelpFrame help = new HelpFrame(baseController);
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
