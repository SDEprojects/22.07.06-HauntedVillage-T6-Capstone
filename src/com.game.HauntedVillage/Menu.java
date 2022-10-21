package com.game.HauntedVillage;

import java.util.Scanner;

public class Menu {


    public static void startNewGame() {
        System.out.println("Would you like to start a new game, type start to start a new game. ");
        boolean condition = false;
        while (!condition) {
            Scanner scanner = new Scanner(System.in);
            String playerChoice = scanner.nextLine().trim().toLowerCase();
            if (playerChoice.equals("start")) {
                condition = true;
            }
        }
    }

    public static void help(){
        System.out.println("----------------------- Help Menu -----------------------\n");
        System.out.println("                                     enter (back) to exit\n\n");

        System.out.println("To move, when prompted enter: \n     go north \n     go east \n     go south" +
                "\n     go west\n");
        System.out.println("To use an item, when prompted enter: \n     use \"item name\"\n");
        System.out.println("To add an item to your inventory, when prompted enter: \n     take \"item name\"\n");
        System.out.println("Enter \"quit\" at any time to end the game");

        boolean condition = false;
        while (!condition) {
            Scanner scanner = new Scanner(System.in);
            String playerChoice = scanner.nextLine().trim().toLowerCase();
            if (playerChoice.equals("back")) {
                condition = true;
            }
        }
    }

    public static void quit() {
        System.out.println("Thanks for playing");
        System.exit(0);
    }

}