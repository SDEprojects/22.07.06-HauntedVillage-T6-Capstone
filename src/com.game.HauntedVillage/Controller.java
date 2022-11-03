package com.game.HauntedVillage;

import com.apps.util.Console;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Controller {
    private final static Controller playGame = new Controller();
    private final Engine engine = new Engine();
    private final Splash splashScreen = new Splash();
    private Menu menu = new Menu();
    private final IntroStroy introStory = new IntroStroy();

    // CONSTRUCTOR
    private Controller() {
        splashScreen.splashScreen(); //not necessary to build classLoad for this
        menu.startNewGame();
        introStory.presentInfo();
//        Console.pause(10000);
        Console.clear();
        engine.gameLoop();
    }
    // BUSINESS METHODS
    public static Controller getPlayGame() {
        return playGame;
    }

}