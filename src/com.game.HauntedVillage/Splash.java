package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.ObjectMapper;

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
        try {
            // instantiate mapper obect
            ObjectMapper mapper = new ObjectMapper();

            // convert array to list of items
            List<Splash> splash = List.of(mapper.readValue(Paths.get("resources/splash.json").toFile(), Splash.class));

            // print
            System.out.println(splash.get(0).getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ACCESSOR METHODS
    public String getTitle() {
        return title;
    }
}