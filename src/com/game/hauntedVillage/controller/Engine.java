package com.game.hauntedVillage.controller;

import com.apps.util.Console;
import com.game.hauntedVillage.model.*;
import com.game.hauntedVillage.model.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Engine {
    private final static Engine playGame = new Engine();
    private final Splash splashScreen = new Splash();
    private Menu menu = new Menu();
    private final IntroStroy introStory = new IntroStroy();

    //------------------------------------------------------
    private Scanner scanner = new Scanner(System.in);
    private String userInput;
    private String delimiter = "[ \t,.:;?!\"']+";
    private String objectName;
    private final ParseCommand commandPares = new ParseCommand();
    private final List<String> verbs = commandPares.command();
    private final List<String> verbForMoving = commandPares.movingCommand;
    private final List<String> direction = commandPares.direction;
    private final Command InputCommand = new Command();
    private final Location movement = new Location();
    private final List<Location> rooms = movement.dataReader();
    private final ItemList gameItems = new ItemList();
    private final Character player = new Character();
    private boolean endGame = false;
    public static boolean displayItem = true;
    private ArrayList<String> items = new ArrayList<>();
    private NpcDialogue npc = new NpcDialogue();


    // CONSTRUCTOR
    public Engine() {
        splashScreen.splashScreen(); //not necessary to build classLoad for this
        menu.startNewGame();
        Console.clear();
        introStory.presentInfo();
//        Console.pause(10000);
        Console.clear();
        gameLoop();
    }



    //-----------------------------------------------------
    //game loop
    void gameLoop() {

        //game continues if player is alive
        while (!endGame) {
            //returns player information at top of screen and location description and player prompt
            Console.clear();
            playerInforPrompt();
            System.out.println("\nWhat is your next command:");
            userInput = scanner.nextLine().trim().toLowerCase();
            String[] commandInput = userInput.toLowerCase().split(delimiter);
            objectName = InputCommand.commandFilter(commandInput);
            boolean testCommand = commandInput.length > 1 && verbs.contains(commandInput[0]);
            if (commandInput.length == 1 && commandInput[0].equals("quit")) {
                endGame = true;
                Menu.quit();
            } else if (commandInput.length == 1) {
                InputCommand.gameCommand(commandInput[0]);
            } else if (testCommand && (gameItems.itemNameList().contains(objectName))) {
                InputCommand.executeCommand(commandInput[0], objectName, gameItems, rooms, player);
                Console.pause(2000);
            }else if(testCommand && (npc.npcNameList().contains(objectName))){
                System.out.println(npc.speak(objectName));
                Console.pause(7000);
            } else if (commandInput.length == 2 && verbForMoving.contains(commandInput[0]) && direction.contains((commandInput[1]))) {
                Console.clear();
                movement.moving(commandInput[1], rooms);
                Engine.displayItem = true;
            } else {
                System.out.println("Invalid input. Please enter the 'verb' + 'name'. Type help for checking the command");
            }
        }
    }

    void playerInforPrompt() {
        System.out.println(movement.getLocationByName(Location.currentRoom).getDescription());
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCurrent().equals(Location.getCurrentRoom())) {
                items = rooms.get(i).getItems();
            }
        }
        if (!displayItem) {
            if (items.size() > 0) {
                System.out.println("\n\nAfter the search, there are some items you may take with you :" + items);
            } else {
                System.out.println("\n\nThere is nothing at this room.");
            }
        }
    }


    // BUSINESS METHODS
    public static Engine getPlayGame() {
        return playGame;
    }

}