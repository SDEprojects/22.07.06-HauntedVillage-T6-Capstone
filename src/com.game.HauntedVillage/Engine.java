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

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

class Engine {

    // initialize scanner. takes system input
    Scanner scanner = new Scanner(System.in);
    private String userInput;
    private ArrayList<String> verbNoun = new ArrayList<>(List.of("verb", "noun"));
    private String npcResponse;
    static Player player = new Player();
    private boolean wellActivation = false;
    private boolean endGame = false;

    public Engine() {
    }

    public static void restoreGame() {
        ArrayList<ArrayList<String>> playerInfoList = new ArrayList<>();
        playerInfoList = RestorePlayer.restorePlayer();
        String location = playerInfoList.get(0).get(0);
        ArrayList<String> inventory = playerInfoList.get(1);
        int healthLevel = parseInt(playerInfoList.get(2).get(0));
        player.setLocation(location);
        player.setInventory(inventory);
        player.setHealthLevel(healthLevel);
    }

    public static void saveGame() {
        SavePlayer.savePlayer(player.getLocation(), player.getInventory(), player.getHealthLevel());
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
    public void gameLoop() {

        //game continues if player is alive
        while (endGame == false) {

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
                ArrayList<String> items = foundItems(player.getLocation(), player.getInventory());
                if (items.size() > 0) {
                    System.out.println("You found " + items);
                    System.out.println("Take an item to add to your inventory");
                    userPromptInput(player.getLocation());
                    //take command, player adds item to inventory
                    for (String item : foundItems(player.getLocation(), player.getInventory())) {
                        if (item.equals(getVerbNoun().get(1))) {
                            Sound.runFX();
                            player.addInventory(getVerbNoun().get(1));
                        }
                    }
                }
                if("Well".equals(player.getLocation())){
                    System.out.println("There is a triangular indentation in the stone.");
                    if(player.getInventory().contains("amulet")){
                        setWellActivation(true);
                        System.out.println("You insert the triangular amulet. The ground rumbles and a grown comes from within the well.");
                    }
                    else {
                        System.out.println("Something must fit here.");
                    }
                    Console.pause(8000);
                }
            }

            //speak command, player speaks to NPCs
            if ("speak".equals(getVerbNoun().get(0))) {
                String character = getVerbNoun().get(1);
                if (NPC.npcLocation(player.getLocation(), character)) {
                    System.out.println(NPC.npcConversation(character));
                    Console.pause(10000);
                }
            }

            //light command, player lights candle for amulet
            if ("light".equals(getVerbNoun().get(0))) {
                System.out.println("The illumination reveals a triangular amulet, this may come in handy.  (amulet added to inventory)");
                player.addInventory("amulet");
                Console.pause(5000);
            }


            // use command, used to interact with static location items (ex. well)
            if ("use".equals(getVerbNoun().get(0))) {
                String interactionItem = getVerbNoun().get(1);
                if (Item.checkStationaryItemLocation(player.getLocation(), interactionItem)) {
                    if(amuletWellCondition(interactionItem)){
                        System.out.println("You retrieve a dark blue stone");
                        player.addInventory("stone");
                    }
                    else {

                        ArrayList<ArrayList<String>> result;
                        result = Item.stationaryItems(interactionItem);
                        String useDesc = result.get(0).get(2);
                        System.out.println(useDesc);

                        // if item heals
                        int healthDelta = parseInt(result.get(0).get(4));
                        if ((player.getHealthLevel() + healthDelta > 10)) {
                            player.setHealthLevel(10);
                        } else {
                            player.setHealthLevel(player.getHealthLevel() + healthDelta);
                        }
                    }
                    Console.pause(5000);
                }
            }

            //use drop command, player can drop inventory.
            if ("drop".equals(getVerbNoun().get(0))) {
                String interactionItem = getVerbNoun().get(1);
                if (player.getInventory().contains(interactionItem)) {
                    player.removeItem(interactionItem);
                }else{
                    System.out.println(interactionItem + " is not in your inventory. ");
                    Console.pause(3000);
                }
            }

            //fight command.
            if ("fight".equals(getVerbNoun().get(0))) {
                String weapon = getVerbNoun().get(1);
                if (player.getInventory().contains(weapon)) {
                   if ("Woods".equals(player.getLocation())){
                       if ("stone".equals(weapon)){
                           finalBattle();
                       }
                       else{
                           //NPC.demonDamage();
                           System.out.println("This weapon isn’t doing anything");
                           Console.pause(3000);
                       }
                   }
                }else{
                    System.out.println(weapon + " is not in your inventory. ");
                    Console.pause(3000);
                }
            }


            //clears console before update
            Console.clear();

            //if player is dead, end game
            if (Player.end() == true) {
                endGame = true;
            }
        }
    }

    private void finalBattle() {
        System.out.println("You through the blue stone at the beast. \nIt locks into space in the flame and radiates in bright blue light! \n\n“No!!!”, he roars");
        Console.pause(8000);
        Console.clear();
        System.out.println("The demon is destroyed in a burst of white light. \n\nYou can finally rest now that the curse has lifted.");
        Console.pause(8000);
        setEndGame(true);

    }

    private boolean amuletWellCondition(String item) {
        boolean condition = false;
        if("well".equals(item)){
            if(player.getInventory().contains("amulet")){
                if (wellActivation) {
                    if (!player.getInventory().contains("stone")) {
                        condition = true;
                    }
                }
            }
        }
        return condition;
    }


    //returns location specific items
    private ArrayList<String> foundItems(String location, ArrayList<String> inventory) {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<String> itemsList = new ArrayList<>(0);

        try {
            JsonNode rootArray = mapper.readTree(new File("22.07.06-HauntedVillage/resources/location.json"));

            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing
                    for (JsonNode node : nameNode) {
                        // Get node names
                        JsonNode itemsNode = nameNode.path("items");
                        if (itemsNode.equals(node)) {
                            for (JsonNode item : itemsNode) {
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

        try {
            JsonNode rootArray = mapper.readTree(new File("22.07.06-HauntedVillage/resources/location.json"));
            //Always-allowed actions are hard coded
            ArrayList<String> actionsList = new ArrayList<>(List.of("help", "quit", "look", "restore", "save","drop", "map"));
            for (JsonNode root : rootArray) {
                // Get Name
                JsonNode nameNode = root.path(location);

                if (!nameNode.isMissingNode()) {  // if "name" node is not missing

                    for (JsonNode node : nameNode) {
                        // Get node names
                        JsonNode actionsNode = nameNode.path("actions");

                        if (actionsNode.equals(node)) {
                            for (JsonNode item : actionsNode) {
                                actionsList.add(item.asText());
                            }
                        }
                    }
                }
            }
            for (String action : actionsList) {
                if (inputAction.equals(action)) {
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
            TextParser parser = new TextParser();
            //verb-noun pair array using text parser
            ArrayList<String> result = parser.textParser(userInput);

            //checks verbs and nouns for validity
            if (!"verb".equals(result.get(0))) {
                if (actionChecker(location, result.get(0))) {
                    validInput = true;
                    //sends to event handler if a global command
                    EventHandler.eventHandler(userInput);
                    setVerbNoun(result);
                } else {
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

    public boolean isWellActivation() {
        return wellActivation;
    }

    public void setWellActivation(boolean wellActivation) {
        this.wellActivation = wellActivation;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }
}
