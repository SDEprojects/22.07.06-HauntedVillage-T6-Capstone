package com.game.hauntedVillage.model;

import java.io.InputStream;

public class Splash {

    // ATTRIBUTES
    private String title;

    // CONSTRUCTORS
    public Splash() {

    }

    // BUSSINESS METHODS
    public void splashScreen() {
        InputStream logo = FileReading.getFileFromResourceAsStreamFortxt("splash.txt");
        FileReading.printInputStream(logo);
    }

    // ACCESSOR METHODS
    public String getTitle() {
        return title;
    }
}