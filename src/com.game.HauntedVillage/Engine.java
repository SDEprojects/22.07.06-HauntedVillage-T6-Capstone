package com.game.HauntedVillage;

import com.apps.util.Console;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Engine {

    // initialize scanner. takes system input
    Scanner scanner = new Scanner(System.in);
    private String userInput;
    private ArrayList<String> verbNoun = new ArrayList<>(List.of("verb", "noun"));
    private String npcResponse;


    public Engine() {
    }

    public void execute() {
        Console.clear();
        splashScreen();
        Menu.startNewGame();
        Console.clear();
        presentInfo();
        Console.clear();
        gameLoop();
    }

    //game loop
    public void gameLoop(){
        boolean endGame = false;
      Player player = new Player();

        //game continues if player is alive
        while(endGame == false){

            //returns player information at top of screen
            player.playerCurrentInfo();

            //returns location description and player prompt
            player.prompt();

            //takes user input, specific to players location
            userPromptInput(player.getLocation());

            //go command, player moves to given direction
            if ("go".equals(getVerbNoun().get(0))) {
                //finds new location given cardinal direction
                String newLocation = Map.moveFinder(player.getLocation(), getVerbNoun().get(1));
                //if new location is not blank the location is updated
                if (!Objects.equals(newLocation, "")) {
                    player.setLocation(newLocation);
                }
            }

            //search command, player looks for items
            if ("search".equals(getVerbNoun().get(0))) {
                //found items retrieves locations item list
                System.out.println("You found " + foundItems(player.getLocation(), player.getInventory()));
                System.out.println("Take an item to add to your inventory");
                userPromptInput(player.getLocation());
                //take command, player adds item to inventory
                for (String item:foundItems(player.getLocation(), player.getInventory())) {
                    if (item.equals(getVerbNoun().get(1))){
                        Sound.runFX();
                        player.addInventory(getVerbNoun().get(1));
                    }
                }
            }
            //speak command, player speaks to NPCs
            if ("speak".equals(getVerbNoun().get(0))) {
                String character = getVerbNoun().get(1);
                if (NPC.npcLocation(player.getLocation(), character)){
                    System.out.println(NPC.npcConversation(character));
                    Console.pause(10000);
                }
            }

            //clears console before update
            Console.clear();

            //if player is dead, end game
            if(Player.end() == true){
                endGame = true;
            }
        }
    }

    //returns location specific items
    private ArrayList<String> foundItems(String location, ArrayList<String> inventory) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<String> itemsList = new ArrayList<>(0);

        try{
            JsonNode rootArray = mapper.readTree(new File("22.07.06-HauntedVillage/resources/location.json"));

            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing
                    for (JsonNode node : nameNode){
                        // Get node names
                        JsonNode itemsNode = nameNode.path("items");
                        if(itemsNode.equals(node)){
                            for (JsonNode item: itemsNode){
                                itemsList.add(item.asText());
                            }
                        }
                    }
                }
            }
            itemsList.removeIf(inventory::contains);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemsList;
    }

    //checks if action is allowed for given location
    private boolean actionChecker(String location, String inputAction) {
        boolean result = false;
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode rootArray = mapper.readTree(new File("22.07.06-HauntedVillage/resources/location.json"));
            //Always-allowed actions are hard coded
            ArrayList<String> actionsList = new ArrayList<>(List.of("help","quit","look"));
            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode){
                        // Get node names
                        JsonNode actionsNode = nameNode.path("actions");

                        if(actionsNode.equals(node)){
                            for (JsonNode item: actionsNode){
                                actionsList.add(item.asText());
                            }
                        }
                    }
                }
            }
            for (String action: actionsList) {
                if (inputAction.equals(action)){
                    result = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //user input processor, sets verbNoun attribute array
    private void userPromptInput(String location) {
        boolean validInput = false;
        while (!validInput) {
            userInput = scanner.nextLine().trim().toLowerCase();
            TextParser parser= new TextParser();
            //verb-noun pair array using text parser
            ArrayList<String> result =  parser.textParser(userInput);

            //checks verbs and nouns for validity
            if (!"verb".equals(result.get(0))){
                if(!"noun".equals(result.get(1))) {
                    if (actionChecker(location, result.get(0))) {
                        validInput = true;
                        //sends to event handler if a global command
                        EventHandler.eventHandler(userInput);
                        setVerbNoun(result);
                    }
                }
                else {
                    System.out.println("Invalid Input: Enter as Prompted (verb and noun)");
                }
            }
        }
    }

    //prints game background information before game
    private void presentInfo() {

        try (JsonParser jParser = new JsonFactory()
                .createParser(new File("22.07.06-HauntedVillage/resources/info.json"))) {

            // loop until token equal to "}"
            while (jParser.nextToken() != JsonToken.END_OBJECT) {

                String fieldname = jParser.getCurrentName();

                if ("story".equals(fieldname)) {
                    jParser.nextToken();
                    System.out.println(jParser.getText());
                }
            }

            Console.pause(13000);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void splashScreen() {
        try {
            // instantiate mapper obect
            ObjectMapper mapper = new ObjectMapper();

            // convert array to list of items
            List<Splash> splash = List.of(mapper.readValue(Paths.get("22.07.06-HauntedVillage/resources/splash.json").toFile(), Splash.class));

            // print
            System.out.println(splash.get(0).getTitle());

            Console.pause(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> getVerbNoun() {
        return verbNoun;
    }

    public void setVerbNoun(ArrayList<String> verbNoun) {
        this.verbNoun = verbNoun;
    }

    public String getNpcResponse() {
        return npcResponse;
    }

    public void setNpcResponse(String npcResponse) {
        this.npcResponse = npcResponse;
    }
}