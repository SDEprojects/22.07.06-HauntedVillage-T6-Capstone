package com.game.hauntedVillage.controller;

import com.game.hauntedVillage.model.Engine;
import com.game.hauntedVillage.viewer.MainFrame;

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
    public void itemListPanel(){
        theView.getCurrentItem();
    }

    public void displayText() {
        theView.updateText(theModel.location().getCurrentRoom().getDescription());
    }

    public void speak(String npcName) {
        theView.updateText(theModel.npc().speak(npcName));
    }

    public void displayItemPanelInTheMap() {

    }


//
//    public GameManager(GUI theView, Engine theModel){
//        this.theModel=theModel;
//        this.theView=theView;
//    }
//
//
//    public void move(String direction){
//        //invoke the necessary methods in the model to update the location
//        //invoke methods in the view to update itself.
//
//        // the GUI is going to need its reference to engine to get the data it needs to display
//    }

}

//methods in the game manager that teh gui can invoke
