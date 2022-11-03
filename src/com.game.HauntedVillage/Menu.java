package com.game.HauntedVillage;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    void startNewGame() {
        System.out.print("\n Enter 'start' to start new game or 'quit game' to exit: \n");
        String input = scanner.nextLine().trim();
        if (input.equalsIgnoreCase("start")) {
//            Sound.runMusic();  //build separate function file inmplement to GUI
        } else if (input.equalsIgnoreCase("quit")) {
//            quit();
        } else {
            System.out.println("Invalid input.");
            startNewGame();
        }
    }

    public static void quit() {
        System.out.println("Thanks for playing");
        System.exit(0);
    }

}