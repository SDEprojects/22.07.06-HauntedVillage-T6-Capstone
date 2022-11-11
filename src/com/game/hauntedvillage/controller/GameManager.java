package com.game.hauntedvillage.controller;

import com.game.hauntedvillage.model.Engine;
import com.game.hauntedvillage.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;

public class GameManager {
    private Engine theModel;
    private MainFrame theView;

    public GameManager() {
        theModel = new Engine();
    }

    public Engine getEngine() {
        return theModel;
    }

    public void start() {
        theView = new MainFrame(this);
    }

    public void startGame() {
        theView.showGamePanel();
    }

    public void itemPanelControllerOn() {
        theView.itemPanelOn();
    }

    public void itemPanelControllerOff() {
        theView.itemPanelOff();
    }

    public void setItemPanelController() {
    }

    public void displayText() {
        theView.updateText(theModel.location().getCurrentRoom().getDescription());
    }

    public void speak(String npcName) {
        theView.updateText(theModel.npc().speak(npcName));
    }
}
