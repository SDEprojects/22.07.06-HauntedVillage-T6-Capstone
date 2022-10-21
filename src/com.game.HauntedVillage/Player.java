package com.game.HauntedVillage;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean end() {
        boolean endCondition = false;
        return endCondition;
    }

    void playerCurrentInfo(){
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
                        if ("name".equals(elementName)) {
                            // current token is "description",
                            // move to next, which is "description"'s value
                            jParser.nextToken();
                            System.out.println("Location: "+ jParser.getText());
                        }
                        if ("items".equals(elementName)) {
                            //jParser.nextToken(); // current token is "[", move next
                            if (jParser.nextToken() == JsonToken.START_ARRAY) {
                                ArrayList<String> itemList = new ArrayList<>(0);
                                // messages is array, loop until token equal to "]"
                                while (jParser.nextToken() != JsonToken.END_ARRAY) {
                                    itemList.add(jParser.getText());
                                }
                                System.out.println("Items: " + itemList);
                            }
                        }
                        if ("directions".equals(elementName)) {
                            ArrayList<String> directionList = new ArrayList<>(0);

                            while (jParser.nextToken() != JsonToken.END_OBJECT) {
                                String direction = jParser.getCurrentName();
                                if ("north".equals(direction)){
                                    directionList.add("north");
                                }
                                if ("east".equals(direction)){
                                    directionList.add(direction);
                                }
                                if ("south".equals(direction)){
                                    directionList.add("south");
                                }
                                if ("west".equals(direction)){
                                    directionList.add("west");
                                }
                            }
                            List<String> directionListReduced = directionList.stream()
                                    .distinct()
                                    .collect(Collectors.toList());
                            System.out.println("Directions: " + directionListReduced);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}