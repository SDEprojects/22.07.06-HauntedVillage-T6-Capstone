package com.game.HauntedVillage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ParseCommand {
    final List<String> getCommand = new ArrayList<>(Arrays.asList("get", "pick", "grab", "take"));
    final List<String> checkCommand = new ArrayList<>(Arrays.asList("check", "inventory", "open"));
    final List<String> lookCommand = new ArrayList<>(Arrays.asList("look", "show", "inspect"));
    final List<String> useCommand = new ArrayList<>(Arrays.asList("use", "utilize"));
    final List<String> dropCommand = new ArrayList<>(Arrays.asList("drop", "throw", "abandon"));
    final List<String> movingCommand = new ArrayList<>(Arrays.asList("go", "moving", "move", "walk", "run", "turn"));
    final List<String> attackCommand = new ArrayList<>(Arrays.asList("attack", "fight"));
    //final List<String> searchCommand = new ArrayList<>(Arrays.asList("search"));
    final List<String> speakCommand = new ArrayList<>(Arrays.asList("speak", "talk"));
    final List<String> direction = new ArrayList<>(Arrays.asList("south", "north", "east", "west"));
    final List<String> musicControl = new ArrayList<>(Arrays.asList("play", "stop", "reset", "lower", "raise"));

    private final List<List> listCommand = new ArrayList<>(Arrays.asList(getCommand, checkCommand,
            lookCommand, useCommand, dropCommand, attackCommand, speakCommand));

    List command() {
        List<String> allCommand = new ArrayList<>();
        for (List eachCommand : listCommand) {
            allCommand.addAll(eachCommand);
        }
        return allCommand;
    }

    String verifyAction(String commandParse) {
        if (getCommand.contains(commandParse)) {
            return "get";
        } else if (checkCommand.contains(commandParse)) {
            return "check";
        } else if (lookCommand.contains(commandParse)) {
            return "look";
        } else if (useCommand.contains(commandParse)) {
            return "use";
        } else if (dropCommand.contains(commandParse)) {
            return "drop";
        } else if (attackCommand.contains(commandParse)) {
            return "attack";

        } else if (speakCommand.contains(commandParse)) {
            return "speak";
        }
        return null;
    }
}
