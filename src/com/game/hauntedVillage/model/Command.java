package com.game.hauntedVillage.model;

import com.apps.util.Console;
import com.game.hauntedVillage.controller.GameManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Command {
    private GameManager game;
    public Command(){
    }
    ParseCommand check = new ParseCommand();

    public String commandFilter(String[] command) {
        if (command.length == 3) {
            return command[1] + " " + command[2];
        } else if (command.length == 2) {
            return command[1];
        } else {
            return null;
        }
    }

    public void executeCommand(String verb, String noun, ItemList gameItems, List<Location> rooms, Character player) {
        verb = check.verifyAction(verb);
        if (verb.equals("look")) {
            System.out.println(gameItems.looking(noun, gameItems));
        } else if (verb.equals("get")) {
            player.addItem(Location.currentRoom, noun, gameItems, rooms);
        } else if (verb.equals("drop")) {
            player.dropItem(noun, player, gameItems, rooms);
        } else if (verb.equals("check") && noun.equals("bag")) {
            player.checkInventory();
        } else {
            System.out.println("There is no " + noun + " in this room");
        }
    }

    public void gameCommand(String command) {
        if (command.equals("help")) {
            help();
        } else if (command.equals("map")) {
            MapImage.display(Location.getCurrentRoom());
            backFunction();
        } else if (command.equals("search")) {
//            Engine.displayItem = false;
        } else {
            System.out.println("invalid input");
        }
    }

    void help() {
        Console.clear();
        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("help.txt");
        FileReading.printInputStream(is);
        backFunction();
    }

    void backFunction(){
        System.out.println("Type \"back\" to exit the menu");
        boolean condition = false;
        while (!condition) {
            Scanner scanner = new Scanner(System.in);
            String playerChoice = scanner.nextLine().trim().toLowerCase();
            if (playerChoice.equals("back")) {
                condition = true;
            }
        }
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String command = e.getActionCommand();
//        System.out.println(command);
//
//
//        if(command.equals("look")){
//            game.getGui().getMessageArea().setText("hello");
//        }else if(command.equals("church")){
//            game.getScreenChanger().showScreen2();
//        }else if(command.equals("northern_square")){
//            game.getScreenChanger().showScreen3();
//        }else if(command.equals("home")){
//            game.getScreenChanger().showScreen1();
//        }else{
//            System.out.println("you need more arrows");
//        }
//
//    }
}
