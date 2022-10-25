package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Player {
    private String location = "Home";

    public Player() {
    }



    public void prompt() {
        ObjectMapper mapper = new ObjectMapper();

        try{

            JsonNode rootArray = mapper.readTree(new File("22.07.06-HauntedVillage/resources/location.json"));

            for (JsonNode root : rootArray) {

                String location = getLocation();
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode){
                        // Get Description
                        JsonNode descriptionNode = nameNode.path("description");

                        if(descriptionNode.equals(node)){
                            System.out.println("\n\n");
                            System.out.println(node.asText());
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
        ObjectMapper mapper = new ObjectMapper();

        try{

            JsonNode rootArray = mapper.readTree(new File("22.07.06-HauntedVillage/resources/location.json"));

            for (JsonNode root : rootArray) {

                String location = getLocation();
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode){
                        // Get node names
                        JsonNode locationNode = nameNode.path("name");
                        JsonNode itemsNode = nameNode.path("items");
                        JsonNode directionsNode = nameNode.path("directions");

                        if(locationNode.equals(node)){
                            System.out.println("Location: " + node.asText());
                        }

                        if(itemsNode.equals(node)){
                            ArrayList<String> itemsList = new ArrayList<>(0);
                            for (JsonNode item: itemsNode){
                                itemsList.add(item.asText());
                            }
                            System.out.println("Items: " + itemsList);
                        }

                        if(directionsNode.equals(node)){
                            ArrayList<String> directionList = new ArrayList<>(0);
                            for (JsonNode direction: directionsNode){
                                JsonNode northNode = node.path("north");
                                JsonNode eastNode = node.path("east");
                                JsonNode southNode = node.path("south");
                                JsonNode westNode = node.path("west");

                                if (northNode.equals(direction)){
                                    directionList.add("north");
                                }
                                if (eastNode.equals(direction)){
                                    directionList.add("east");
                                }
                                if (southNode.equals(direction)){
                                    directionList.add("south");
                                }
                                if (westNode.equals(direction)){
                                    directionList.add("west");
                                }
                            }
                            System.out.println("Directions: " + directionList);
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