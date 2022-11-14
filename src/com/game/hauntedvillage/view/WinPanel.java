package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;
import com.game.hauntedvillage.utility.FontStyle;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class WinPanel extends JPanel{

    private final GameManager baseController;

    public WinPanel(GameManager baseController) {
        this.baseController = baseController;
        JLabel imageLabel = new JLabel("You Won");
        imageLabel.setBounds(0, 0, 900, 900);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
        imageLabel.setVerticalTextPosition(JLabel.TOP);
        imageLabel.setFont(new Font("Libian TC", Font.BOLD, 56));
        imageLabel.setForeground(Color.red);
        ImageIcon backGroundImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("BackgroundImages/home.jpg")));
        imageLabel.setIcon(backGroundImage);
        add(imageLabel);
        setBounds(50, 50, 900, 900);
        setBackground(Color.black);
        setLayout(null);
    }
}