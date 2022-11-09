package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JPanel{

    private JPanel splashscreenPanel;
    private GameManager baseController;
    private JButton startGame;
    private JButton quitGame;
    private boolean gameStatus;


    public SplashScreen(GameManager baseController){
        this.baseController = baseController;
        splashscreenPanel = new JPanel();
        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(0,0, 900,900);
        ImageIcon backGroundImage = new ImageIcon(getClass().getClassLoader().getResource("Background_images/splashscreenBackground.png"));

        imageLabel.setIcon(backGroundImage);

        this.add(startGameButton());
        this.add(quitGameButton());
        this.add(imageLabel);
        this.setBounds(50, 50, 900, 900);
        this.setBackground(Color.black);
        this.setLayout(null);


    }


    JButton startGameButton(){
        startGame = new JButton("Start Game");
        startGame.setBounds(240,500, 150, 50);
        startGame.setFont(new Font("Comic sans", Font.PLAIN, 20));
        startGame.addActionListener(new StartGameListener());

        return startGame;
    }

    JButton quitGameButton(){
        quitGame = new JButton("Quit Game");
        quitGame.setBounds(470,500, 150, 50);
        quitGame.setFont(new Font("Comic sans", Font.PLAIN, 20));
        return  quitGame;
    }

    //getter and setters
    public boolean getGameStatus() {
        return gameStatus;
    }

    private void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    class StartGameListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            setGameStatus(true);
            setVisible(false);
            System.out.println(getGameStatus());
        }
    }

}