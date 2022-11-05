package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.Engine;
import com.game.hauntedVillage.model.Command;
import com.game.hauntedVillage.model.ScreenChanger;
import com.game.hauntedVillage.viewer.GUI;

public class GameManager {

    private Command command = new Command(this);
    private ScreenChanger screenChanger = new ScreenChanger(this);
    private GUI gui = new GUI(this);


    public GameManager(){


    }

    public void move(String direction){
        //invoke the necessary methods in the model to update the location
        //invoke methods in the view to update itself.
        gui.update();
        // the GUI is going to need its reference to engine to get the data it needs to display
    }

    public GUI getGui() {
        return gui;
    }

    public Command getCommand() {
        return command;
    }

    public ScreenChanger getScreenChanger() {
        return screenChanger;
    }
}

//methods in the game manager that teh gui can invoke
