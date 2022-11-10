package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class TopPanel extends JPanel {

    private GameManager baseController;
    private final ArrayList<JLabel> heart = new ArrayList<>();

    //generates top panel
    public TopPanel(GameManager baseController) {
        this.baseController = baseController;
        setLayout(null);
        setBounds(50,50,900,50);
        setBackground(null);
        setOpaque(true);
        createHeartIcons();
        createMapIcons();
        createSound();
        createHelp();
    }

    //create icons
    public void createHeartIcons() {
        int hp=baseController.getEngine().getPlayer().getHp();
        for(int i=0;i<hp;i++){
            JLabel heartLabel = new JLabel();
            heartLabel.setBounds(0+(44*i),0, 44, 30);
            ImageIcon heart = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/heart.png")));
            heartLabel.setIcon(heart);
            add(heartLabel);
        }
    }

    public void createMapIcons(){
        JLabel mapLabel = new JLabel();
        mapLabel.setBounds(750,0, 44, 30);
        ImageIcon map = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/heart.png")));
        mapLabel.setIcon(map);
        add(mapLabel);
    }

    public void createSound(){
        JLabel soundIcon = new JLabel();
        soundIcon.setBounds(800,0, 44, 30);
        ImageIcon map = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/heart.png")));
        soundIcon.setIcon(map);
        add(soundIcon);
    }
    public void createHelp(){
        JLabel helpIcon = new JLabel();
        helpIcon.setBounds(850,0, 44, 30);
        ImageIcon map = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/heart.png")));
        helpIcon.setIcon(map);
        add(helpIcon);
    }
}
