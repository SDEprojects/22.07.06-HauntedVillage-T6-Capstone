package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Player implements Serializable {
    private String location = "Home";
    private ArrayList<String> inventory = new ArrayList<>(0);
    private int healthLevel = 10;

    public Player(String location, ArrayList<String> inventory, int healthLevel) {
        this.location = location;
        this.inventory = inventory;
        this.healthLevel = healthLevel;
    }

    public Player() {
    }

    private static void healthModifier(String item) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootArray = objectMapper.readTree(new File("resources/items.json"));

            for (JsonNode root : rootArray) {
                JsonNode nameNode = root.path(item);

                if (!nameNode.isMissingNode()) {
                    for (JsonNode node : nameNode) {
                        JsonNode healthReductionNode = nameNode.path("health_reduction");
                        int healthReduction = healthReductionNode.asInt();
                        if (healthReductionNode.equals(node)) {
                            System.out.println("\n\n");
                            System.out.println(healthReduction);

                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void prompt() {
        ObjectMapper mapper = new ObjectMapper();

        try {

            JsonNode rootArray = mapper.readTree(new File("resources/location.json"));

            for (JsonNode root : rootArray) {

                String location = getLocation();
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode) {
                        // Get Description
                        JsonNode descriptionNode = nameNode.path("description");

                        if (descriptionNode.equals(node)) {
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

    void playerCurrentInfo() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootArray = mapper.readTree(new File("resources/location.json"));

            for (JsonNode root : rootArray) {

                String location = getLocation();
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode) {
                        // Get node names
                        JsonNode locationNode = nameNode.path("name");
                        JsonNode itemsNode = nameNode.path("items");
                        JsonNode directionsNode = nameNode.path("directions");

                        //location
                        if (locationNode.equals(node)) {
                            System.out.println("Location: " + node.asText());
                        }

                        //direction
                        if (directionsNode.equals(node)) {
                            ArrayList<String> directionList = new ArrayList<>(0);
                            for (JsonNode direction : directionsNode) {
                                JsonNode northNode = node.path("north");
                                JsonNode eastNode = node.path("east");
                                JsonNode southNode = node.path("south");
                                JsonNode westNode = node.path("west");

                                if (northNode.equals(direction)) {
                                    directionList.add("north");
                                }
                                if (eastNode.equals(direction)) {
                                    directionList.add("east");
                                }
                                if (southNode.equals(direction)) {
                                    directionList.add("south");
                                }
                                if (westNode.equals(direction)) {
                                    directionList.add("west");
                                }
                            }
                            System.out.println("Directions: " + directionList);
                        }
                    }
                }
            }
            //Inventory
            System.out.println("Inventory: " + getInventory());

            //Health bar
            ArrayList<String> healthIconList = new ArrayList<>(0);
            for (int i = 0; i < getHealthLevel(); i++) {
                healthIconList.add("♥");
            }
            String healthBar = healthIconList.toString().replaceAll("[\\[\\]]", "").replaceAll(",", "");
            System.out.println("Health: " + healthBar);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //remove an item from player's inventory
    public void removeItem(String item){
        ArrayList<String> itemToBeRemoved = getInventory();
        itemToBeRemoved.remove(item);
        setInventory(itemToBeRemoved);
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void addInventory(String item) {
        inventory.add(item);
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }
}