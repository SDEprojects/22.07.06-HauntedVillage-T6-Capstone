package com.game.hauntedVillage.viewer;


import javax.swing.*;
import java.util.ArrayList;

class ScreenChanger {
    private ArrayList<JPanel> getBgPanel;
    public ScreenChanger(ArrayList<JPanel> getBgPanel){
        this.getBgPanel = getBgPanel;
    }
    private void mainScreen(int mapNum){
        getBgPanel.forEach(panel -> panel.setVisible(false));
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