package com.game.HauntedVillage;

import com.apps.util.Console;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

class Command {
    ParseCommand check = new ParseCommand();
//    MusicPlayer music = new MusicPlayer();
//    ParseCommand musicCheck=new ParseCommand();

    String commandFilter(String[] command) {
        if (command.length == 3) {
            return command[1] + " " + command[2];
        } else if (command.length == 2) {
            return command[1];
        } else {
            return null;
        }
    }

    void executeCommand(String verb, String noun, ItemList gameItems, List<Location> rooms,Character player) {
        verb = check.verifyAction(verb);
        if (verb.equals("look")) {
            System.out.println(gameItems.looking(noun, gameItems));
        }
        else if (verb.equals("get")) {
            player.addItem(Location.currentRoom, noun, gameItems, rooms);
        } else if (verb.equals("drop")) {
            player.dropItem(noun, player, gameItems, rooms);
        } else if (verb.equals("check") && noun.equals("bag")) {
            player.checkInventory();
        }
        else {
            System.out.println("There is no " + noun + " in this room");
        }
    }

    void gameCommand(String command) {
        if (command.equals("help")) {
            help();
        } else if (command.equals("map")) {
            map();
        }
//        else if(musicCheck.musicControl.contains(command)){
//            music.runMusic(command);
//        }
        else {
            System.out.println("invalid input");
        }
    }

//    void fightCommand(String verb, String noun, Character playerAbility, Character player,
//                      Character monster, Character ghoul, Character king, List<Location> rooms, LinkedList<String> monsterList) {
//        if (noun.equals("monster") && Location.currentRoom.equals(monster.getLocation())) {
//            playerAbility.attack(player, monster, monsterList,playerAbility);
//        } else if (noun.equals("ghoul") && Location.currentRoom.equals(ghoul.getLocation())) {
//            playerAbility.attack(player, ghoul, monsterList,playerAbility);
//        } else if (noun.equals("king") && Location.currentRoom.equals(king.getLocation())) {
//            playerAbility.attack(player, king, monsterList,playerAbility);
//        } else {
//            System.out.println("Invalid input");
//        }
//    }

    void help() {
        Console.clear();
//        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("help.txt");
    }

    void map() {
        Console.clear();
//        InputStream is = FileReading.getFileFromResourceAsStreamFortxt("map.txt");
    }
}
