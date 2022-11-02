package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Item {

    private String description;

    public static void checkForItem(String item){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootArray = objectMapper.readTree(new File("resources/items.json"));

            for (JsonNode root : rootArray) {
                JsonNode nameNode = root.path(item);

                if (!nameNode.isMissingNode()) {
                    for (JsonNode node : nameNode){
                        JsonNode descNode = nameNode.path("description");

                        if (descNode.equals(node)) {
                            System.out.println("\n\n");
                            System.out.println(descNode.asText());
                        }
                    }
                }
            }
            System.out.println("Enter back to exit");
            boolean condition = false;
            while (!condition) {
                Scanner scanner = new Scanner(System.in);
                String playerChoice = scanner.nextLine().trim().toLowerCase();
                if (playerChoice.equals("back")) {
                    condition = true;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //returns stationary interactive items
    public static ArrayList stationaryItems(String interactionItem) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        ArrayList<String> stationaryItemElements = new ArrayList<>();

        try{
            JsonNode rootArray = mapper.readTree(new File("resources/stationaryItems.json"));
            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(interactionItem);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing
                    for (JsonNode node : nameNode){
                        stationaryItemElements.add(node.asText());
                    }
                    result.add(stationaryItemElements);
                } else {
                    System.out.println("You can not interact with this item.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkStationaryItemLocation(String location, String interactionItem) {
        boolean result = false;
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode rootArray = mapper.readTree(new File("resources/stationaryItems.json"));

            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(interactionItem);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode){
                        // Get node names
                        JsonNode actionsNode = nameNode.path("location");

                        if(actionsNode.equals(node)){
                            String stationaryItemLocation = node.asText();
                            if (location.equals(stationaryItemLocation)){
                                result = true;
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}