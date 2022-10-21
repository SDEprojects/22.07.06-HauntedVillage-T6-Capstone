package com.game.HauntedVillage;

import com.apps.util.Console;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.File;
import java.io.IOException;

public class Player {
    private String location = "home";

    public Player() {
    }

    public void prompt() {
        try (JsonParser jParser = new JsonFactory()
                .createParser(new File("22.07.06-HauntedVillage/resources/location.json"))) {

            // loop until token equal to "}"
            while (jParser.nextToken() != JsonToken.END_OBJECT) {

                String fieldName = jParser.getCurrentName();

                String location = getLocation();

                if (location.equals(fieldName)) {
                    // loop until token equal to "}"
                    while (jParser.nextToken() != JsonToken.END_OBJECT) {
                        String elementName = jParser.getCurrentName();
                        if ("description".equals(elementName)) {
                            // current token is "description",
                            // move to next, which is "description"'s value
                            jParser.nextToken();
                            System.out.println(jParser.getText());
                        }
                    }
                }
            }

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean end() {
        boolean endCondition = false;
        return endCondition;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}