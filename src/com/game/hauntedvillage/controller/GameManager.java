package com.game.hauntedvillage.controller;

import com.game.hauntedvillage.model.Engine;
import com.game.hauntedvillage.view.MainFrame;

public class GameManager {
    private Engine theModel;
    private MainFrame theView;

    public GameManager(){
        theModel=new Engine();
        theView=new MainFrame(this);
    }

    public Engine getEngine(){
        return theModel;
    }

    public void startGame(){
        theView.showGamePanel();
    }

    public void itemPanelControllerOn() {
        theView.itemPanelOn();
    }

    public void itemPanelControllerOff() {
        theView.itemPanelOff();
    }

    public void displayText(){
         theView.updateText(theModel.getLocation().getCurrentRoom().getDescription());
    }

    public void displayItemInformation(String itemName){
        theView.updateText(theModel.getGameItems().looking(itemName));
    }
    public void displayAttackMessage(String message){
        theView.updateText(message);
    }

    public void speak(String npcName){
        theView.updateText(theModel.getNpc().speak(npcName));
    }

    public void endGame(boolean gameStatus){
    theView.endGamePanels(gameStatus);
    }
}

