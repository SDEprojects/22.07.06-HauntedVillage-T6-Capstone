package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.util.ArrayList;

class ScreenChanger {
    private ArrayList<JPanel> getBgPanel;
    public ScreenChanger(ArrayList<JPanel> getBgPanel){
        this.getBgPanel = getBgPanel;
    }
//
    public void showScreen1(){
        getBgPanel.get(0).setVisible(true);
        getBgPanel.get(1).setVisible(false);
        getBgPanel.get(2).setVisible(false);
    }
    public void showScreen2(){
        getBgPanel.get(0).setVisible(false);
        getBgPanel.get(1).setVisible(true);
        getBgPanel.get(2).setVisible(false);
    }
    public void showScreen3(){
        getBgPanel.get(0).setVisible(false);
        getBgPanel.get(1).setVisible(false);
        getBgPanel.get(2).setVisible(true);
    }
}