package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.util.ArrayList;

class ScreenChanger {
    private ArrayList<JPanel> getBgPanel;
    public ScreenChanger(ArrayList<JPanel> getBgPanel){
        this.getBgPanel = getBgPanel;
    }
    private void mainScreen(int mapNum){
        getBgPanel.get(0).setVisible(false);
        getBgPanel.get(1).setVisible(false);
        getBgPanel.get(2).setVisible(false);
        getBgPanel.get(3).setVisible(false);
        getBgPanel.get(4).setVisible(false);
        getBgPanel.get(5).setVisible(false);
        getBgPanel.get(6).setVisible(false);
        getBgPanel.get(7).setVisible(false);
        getBgPanel.get(8).setVisible(false);
        getBgPanel.get(9).setVisible(false);
        getBgPanel.get(mapNum).setVisible(true);
    }
    public void currentRoom(String roomName){
        switch(roomName){
            case "home":
                mainScreen(0);
                break;
            case "center courtyard":
                mainScreen(1);
                break;
            case "northern square":
                mainScreen(2);
                break;
            case "southern square":
                mainScreen(3);
                break;
            case "farm":
                mainScreen(4);
                break;
            case "town hall":
                mainScreen(5);
                break;
            case "tavern":
                mainScreen(6);
                break;
            case "church":
                mainScreen(7);
                break;
            case "well":
                mainScreen(8);
                break;
            case "woods":
                mainScreen(9);
                break;
        }
    }
}