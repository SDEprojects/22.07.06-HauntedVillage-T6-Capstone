package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private MainPanel backgroundPanel;
    private TextPanel textPanel;

    public MainFrame(GameManager baseController){
        backgroundPanel=new MainPanel(baseController);
        textPanel=new TextPanel(baseController);
        setupFrame();
    }
    private void setupFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000,1000);
        getContentPane().setBackground(Color.black);

        //add background panel to the main frame
        for(JPanel backGround:backgroundPanel.getBgPanel()) {
            add(backGround);
        }
        //add textPanel on the main frame
        add(textPanel);
        this.setVisible(true);
    }
}