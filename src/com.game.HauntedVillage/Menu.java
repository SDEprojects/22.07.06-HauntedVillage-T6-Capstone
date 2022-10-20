package com.game.HauntedVillage;

import java.util.Scanner;

public class Menu {


    public static void startNewGame() {
        System.out.println("Would you like to start a new game, type start to start a new game. ");
        Scanner scanner = new Scanner(System.in);
        String playerChoice = scanner.nextLine().trim().toLowerCase();
        if (playerChoice.equals("start")) {
            System.out.println("You chose to start a new game, booting up....");
        }
    }

}