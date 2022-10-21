package com.game.HauntedVillage;

import com.apps.util.Console;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Controller {
    // FIELDS
    //private final Scanner scanner = new Scanner(System.in);

    // CONSTRUCTOR
    public Controller() {
    }

    // BUSINESS METHODS
    public void playGame() {
        Engine engine = new Engine();

        engine.execute();

    }



}