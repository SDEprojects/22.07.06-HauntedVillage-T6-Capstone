package com.game.HauntedVillage;

import com.apps.util.Console;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.File;
import java.io.IOException;

class Engine {

    public Engine() {
    }

    public void execute() {
        Console.clear();
        splash();
        Console.clear();
        presentInfo();
        Console.clear();
    }

    private void splash() {
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

            Console.pause(3000);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}