package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class WinPanel extends JPanel{

    private final GameManager baseController;

    public WinPanel(GameManager baseController) {
        this.baseController = baseController;
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 900, 900);
        ImageIcon backGroundImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("BackgroundImages/splashscreenBackground.png")));
        imageLabel.setIcon(backGroundImage);
        this.add(imageLabel);
        this.setBounds(50, 50, 900, 900);
        this.setBackground(Color.black);
        this.setLayout(null);
    }
}