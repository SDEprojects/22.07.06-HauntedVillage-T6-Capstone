package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel backgroundPanel;
    private TextPanel textPanel;
    private TopPanel topPanel;
    private SplashScreen splashScreen;
    private InventoryPanel inventoryPanel;
    private boolean isVisible;

    public MainFrame(GameManager baseController){
        backgroundPanel=new MainPanel(baseController,textPanel);
        textPanel=new TextPanel(baseController);
        splashScreen=new SplashScreen(baseController);
        topPanel = new TopPanel(baseController);
        inventoryPanel = new InventoryPanel(baseController);
        setupFrame();
    }


    public void showGamePanel(){
        splashScreen.setVisible(false);
        backgroundPanel.setVisible(true);
    }

    public void updateText(String text){
        System.out.println(text);
        textPanel.setText(text);
    }

    private void setupFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000, 1000);
        getContentPane().setBackground(Color.black);
        add(splashScreen);
        add(backgroundPanel);
        backgroundPanel.setVisible(false);
        //add textPanel on the main frame
        add(textPanel);
        add(inventoryPanel);
        add(topPanel);
        textPanel.setVisible(true);
        this.setVisible(true);
    }


}
