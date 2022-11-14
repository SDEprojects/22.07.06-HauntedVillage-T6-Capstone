package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class HelpFrame extends JFrame {

    private final GameManager baseController;

    public HelpFrame(GameManager baseController) {
        this.baseController = baseController;
        setTitle("Guidance");
        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 540);
        setLayout(null);
        createBackground();
        setVisible(true);
    }

    private void createBackground() {
        JPanel backGround = new JPanel();
        backGround.setLayout(null);
        backGround.setBounds(0, 0, 600, 540);
        backGround.setBackground(null);
        JLabel backGroundLable = new JLabel();
        backGroundLable.setLayout(null);
        backGroundLable.setBounds(0, 0, 600, 540);
        ImageIcon bgImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Icons/helpFrame.png")));
        backGroundLable.setIcon(bgImage);
        backGround.add(backGroundLable);
        add(backGround);
    }
}