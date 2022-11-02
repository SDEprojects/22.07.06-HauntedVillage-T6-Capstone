package com.game.HauntedVillage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MapImage {


    private static List<List<String>> mainMap = new ArrayList<>(List.of(
            Arrays.asList("                   ","==========================","                "),
            Arrays.asList("         N         ","==        Woods         ==","                "),
            Arrays.asList("         |         ","==========================","                "),
            Arrays.asList("    W----|----E    ","==        Well          ==","                "),
            Arrays.asList("         |         ","==========================","================"),
            Arrays.asList("         S         ","==    Northern Square   ==","    Church    =="),
            Arrays.asList("===================","==========================","================"),
            Arrays.asList("==     Home        ","==    Center Courtyard  ==","    Tavern    =="),
            Arrays.asList("===================","==========================","================"),
            Arrays.asList("                   ","==    Southern Square   ==","    Town hall =="),
            Arrays.asList("                   ","==========================","================"),
            Arrays.asList("                   ","==        Farm          ==","                "),
            Arrays.asList("                   ","==========================","                ")
    ));


    public static void display(String location) {

        List<List<String>> localMap = getMap();

        if (location.equals("Home")){
            String spot = "==  *  Home        ";
            localMap.get(7).set(0, spot);
        }
        if (location.equals("Woods")){
            String spot = "==  *     Woods         ==";
            localMap.get(1).set(1, spot);
        }
        if (location.equals("Well")){
            String spot = "==  *     Well          ==";
            localMap.get(3).set(1, spot);
        }
        if (location.equals("Northern Square")){
            String spot = "==  * Northern Square   ==";
            localMap.get(5).set(1, spot);
        }
        if (location.equals("Center Courtyard")){
            String spot = "==  * Center Courtyard  ==";
            localMap.get(7).set(1, spot);
        }
        if (location.equals("Southern Square")){
            String spot = "==  * Southern Square   ==";
            localMap.get(9).set(1, spot);
        }
        if (location.equals("Farm")){
            String spot = "==  *     Farm          ==";
            localMap.get(11).set(1, spot);
        }
        if (location.equals("Church")){
            String spot = "  * Church    ==";
            localMap.get(5).set(2, spot);
        }
        if (location.equals("Tavern")){
            String spot = "  * Tavern    ==";
            localMap.get(7).set(2, spot);
        }
        if (location.equals("Town hall")){
            String spot = "  * Town hall ==";
            localMap.get(9).set(2, spot);
        }

        for (List<String> list : localMap) {
            System.out.println(list.toString()
                    .replace("[", "")
                    .replaceAll("]", "")
                    .replaceAll(",", ""));
        }

        localMap.get(7).set(0, "==     Home        ");
        localMap.get(1).set(1, "==        Woods         ==");
        localMap.get(3).set(1, "==        Well          ==");
        localMap.get(5).set(1, "==    Northern Square   ==");
        localMap.get(7).set(1, "==   Center Courtyard  ==");
        localMap.get(9).set(1, "==    Southern Square   ==");
        localMap.get(11).set(1, "==        Farm          ==");
        localMap.get(5).set(2, "    Church    ==");
        localMap.get(7).set(2, "    Tavern    ==");
        localMap.get(9).set(2, "    Town hall ==");


    }

    public static void map(String location){
        display(location);

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

    public static List<List<String>> getMap() {
        return mainMap;
    }
}
