package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

class Splash {

    // ATTRIBUTES
    private String title;

    // CONSTRUCTORS
    public Splash() {

    }

    // BUSSINESS METHODS
    void splashScreen() {
        InputStream logo = FileReading.getFileFromResourceAsStreamFortxt("splash.txt");
        FileReading.printInputStream(logo);
    }

    // ACCESSOR METHODS
    public String getTitle() {
        return title;
    }
}