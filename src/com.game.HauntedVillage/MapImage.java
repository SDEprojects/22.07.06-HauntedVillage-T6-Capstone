package com.game.HauntedVillage;

import java.util.Scanner;

public class MapImage {

    public static void display() {
        System.out.println("                   ==========================");
        System.out.println("       N           ==        Woods         ==");
        System.out.println("       |           ==========================");
        System.out.println("  W----|----E      ==        Well          ==");
        System.out.println("       |           ========================================");
        System.out.println("       S           ==   Northern Square    ==    Church  ==");
        System.out.println("===========================================================");
        System.out.println("==  Home           ==    Center Courtyard  ==   Tavern   ==");
        System.out.println("===========================================================");
        System.out.println("                   ==    Southern Square   ==  Townhall  ==");
        System.out.println("                   ========================================");
        System.out.println("                   ==        Farm          ==");
        System.out.println("                   ==========================");
    }

    public static void map(){
        display();

        System.out.println("\nEnter back to exit");
        boolean condition = false;
        while (!condition) {
            Scanner scanner = new Scanner(System.in);
            String playerChoice = scanner.nextLine().trim().toLowerCase();
            if (playerChoice.equals("back")) {
                condition = true;
            }
        }
    }
}
