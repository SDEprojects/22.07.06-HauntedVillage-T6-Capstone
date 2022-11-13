package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

class SplashScreen extends JPanel {

    private final GameManager baseController;

    public SplashScreen(GameManager baseController) {
        this.baseController = baseController;
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 900, 900);
        ImageIcon backGroundImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("BackgroundImages/splashscreenBackground.png")));
        imageLabel.setIcon(backGroundImage);
        this.add(startGameButton());
        this.add(quitGameButton());
        this.add(imageLabel);
        this.setBounds(50, 50, 900, 900);
        this.setBackground(Color.black);
        this.setLayout(null);
    }


    private JButton startGameButton() {
        JButton startGame = new JButton("Start Game");
        startGame.setBounds(240, 500, 150, 50);
        startGame.setFont(new Font("Comic sans", Font.PLAIN, 20));
        startGame.addActionListener(new StartGameListener());
        return startGame;
    }

    private JButton quitGameButton() {
        JButton quitGame = new JButton("Quit Game");
        quitGame.setBounds(470, 500, 150, 50);
        quitGame.setFont(new Font("Comic sans", Font.PLAIN, 20));
        quitGame.addActionListener(new QuitGameListener());
        return quitGame;
    }

    private class StartGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            baseController.startGame();
        }
    }

    private class QuitGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}