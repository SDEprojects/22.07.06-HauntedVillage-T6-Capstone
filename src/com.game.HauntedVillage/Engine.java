package com.game.HauntedVillage;

import com.apps.util.Console;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class Engine {

    // initialize scanner. takes system input
    Scanner scanner = new Scanner(System.in);
    private String userInput;
    private ArrayList<String> verbNoun = new ArrayList<>(List.of("verb", "noun"));


    public Engine() {
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

    public void gameLoop(){
        boolean endGame = false;
      Player player = new Player();

        while(endGame == false){
            player.playerCurrentInfo();
            player.prompt();
            userPromptInput();
            if ("go".equals(getVerbNoun().get(0))){
                String newLocation = Map.moveFinder(player.getLocation(), getVerbNoun().get(1));
                if (!Objects.equals(newLocation, "")) {
                    player.setLocation(newLocation);
                }
            }
            Console.clear();

            if(Player.end() == true){
                endGame = true;
            }
        }
    }


    private void userPromptInput() {
        boolean validInput = false;
        while (!validInput) {
            userInput = scanner.nextLine().trim().toLowerCase();
            TextParser parser= new TextParser();
            ArrayList<String> result =  parser.textParser(userInput);

            if (!"verb".equals(result.get(0))){
                if(!"noun".equals(result.get(1))) {
                    validInput = true;
                    EventHandler.eventHandler(userInput);
                    setVerbNoun(result);
                }
                else {
                    System.out.println("Invalid Input: Enter as Prompted (verb and noun)");
                }
            }
        }
    }

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
}