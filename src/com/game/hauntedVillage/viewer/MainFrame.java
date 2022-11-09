package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel backgroundPanel;
    private TextPanel textPanel;
    private SplashScreen splashScreen;
    boolean startGame=false;
    public MainFrame(GameManager baseController){
        backgroundPanel=new MainPanel(baseController,textPanel);
        textPanel=new TextPanel(baseController);
        splashScreen=new SplashScreen(baseController);
        setupFrame();
    }

    public void showGamePanel(){
        splashScreen.setVisible(false);
        backgroundPanel.setVisible(true);
    }

    private void setupFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000,1000);
        getContentPane().setBackground(Color.black);
        add(splashScreen);
        add(backgroundPanel);
        backgroundPanel.setVisible(false);
        startGame=splashScreen.getGameStatus();
        //add textPanel on the main frame
        add(textPanel);
        textPanel.setVisible(true);
        this.setVisible(true);
    }
}
