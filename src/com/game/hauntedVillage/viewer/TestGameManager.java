package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.model.Command;
import com.game.hauntedVillage.model.ScreenChanger;

public class TestGameManager {
    private TestGUI gui=new TestGUI(this);
    private Command command = new Command(this);
    private ScreenChanger screenChanger = new ScreenChanger(this);
    public TestGameManager(){


    }

    public void move(String direction){
        //invoke the necessary methods in the model to update the location
        //invoke methods in the view to update itself.
//        gui.update();
        // the GUI is going to need its reference to engine to get the data it needs to display
    }

    public TestGUI getGui() {
        return gui;
    }

    public Command getCommand() {
        return command;
    }

    public ScreenChanger getScreenChanger() {
        return screenChanger;
    }
}
