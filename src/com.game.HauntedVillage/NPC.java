package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class NPC {

    public static String npcConversation(String character) {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode rootArray = mapper.readTree(new File("resources/npc.json"));
            ArrayList<String> convoList = new ArrayList<>(0);
            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(character);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode){
                        // Get node names
                        JsonNode conversationsNode = nameNode.path("conversations");

                        if(conversationsNode.equals(node)){
                            for (JsonNode item: conversationsNode){
                                convoList.add(item.asText());
                            }
                        }
                    }
                }
            }
            //Generate random int value from 0 to 2
            int num = (int)(Math.random()*(3));
            //random conversation
            result = convoList.get(num);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean npcLocation(String location, String character) {
        boolean result = false;
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode rootArray = mapper.readTree(new File("resources/npc.json"));

            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(character);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode){
                        // Get node names
                        JsonNode actionsNode = nameNode.path("location");

                        if(actionsNode.equals(node)){
                            String characterLocation = node.asText();
                            if (location.equals(characterLocation)){
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
