package com.game.HauntedVillage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class Map {


    public static String moveFinder(String location, String directionInput) {

        String result = "";

        try{

            ObjectMapper mapper = new ObjectMapper();

            JsonNode rootArray = mapper.readTree(new File("resources/location.json"));

            for (JsonNode root : rootArray) {

                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode){
                        // Get Direction
                        JsonNode directionsNode = nameNode.path("directions");

                        if(directionsNode.equals(node)){
                            for (JsonNode direction: directionsNode) {
                                JsonNode inputNode = node.path(directionInput);

                                if (inputNode.equals(direction)) {
                                    result = node.path(directionInput).asText();
                                }
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