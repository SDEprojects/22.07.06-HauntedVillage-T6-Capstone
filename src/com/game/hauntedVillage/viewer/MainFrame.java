package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel backgroundPanel;

    public MainFrame(GameManager baseController){
        backgroundPanel=new MainPanel(baseController);
        setupFrame();
    }
    private void setupFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000,1000);
        getContentPane().setBackground(Color.black);
        for(JPanel backGround:backgroundPanel.getBgPanel()) {
            add(backGround);
        }
        this.setVisible(true);
    }
}
