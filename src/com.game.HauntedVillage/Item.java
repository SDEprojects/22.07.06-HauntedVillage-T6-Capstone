package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Item {

    private String description;

    public static void checkForItem(String item){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode rootArray = objectMapper.readTree(new File("22.07.06-HauntedVillage/resources/items.json"));

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

}