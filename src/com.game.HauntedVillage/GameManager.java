package com.game.HauntedVillage;

public class GameManager {

    private Engine engine = new Engine();
    private GUI gui = new GUI(this, engine);

    public GameManager(){
        gui.setMovementCallback(this::move);

    }

    public void move(String direction){
        //invoke the necessary methods in the model to update the location
        //invoke methods in the view to update itself.
        gui.update();
        // the GUI is going to need its reference to engine to get the data it needs to display
    }
//    public static void main(String[] args) {
//        new GameManager();
//    }
}

//methods in the game manager that teh gui can invoke
