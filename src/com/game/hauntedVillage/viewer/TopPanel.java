package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;
//import com.game.hauntedVillage.model.Sound;
import com.game.hauntedVillage.model.SoundHandler;

import javax.swing.*;
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
        // TODO: Implement buttons/ action Listeners on here from Sound Class

        JButton muteButton = new JButton();
        muteButton.setBounds(800, 0, 44, 30);
        ImageIcon soundIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/sound.png")));
        muteButton.setIcon(soundIcon);

        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                soundHandler.toggleMute();
            }
        });
        add(muteButton);
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
