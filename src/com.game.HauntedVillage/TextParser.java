package com.game.HauntedVillage;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TextParser {



    ArrayList<String> textParser(String input){

        ArrayList<String> result =  new ArrayList<>(List.of("verb", "noun"));
        // split string by no space
        String[] strSplit = input.split(" ");

        // Now convert string into ArrayList
        ArrayList<String> words = new ArrayList<>(Arrays.asList(strSplit));

        //for (String word: words) {
            try (JsonParser jParser = new JsonFactory()
                    .createParser(new File("22.07.06-HauntedVillage/resources/textParse.json"))) {

                // loop until token equal to "}"
                while (jParser.nextToken() != JsonToken.END_OBJECT) {

                    String fieldname = jParser.getCurrentName();

                    if ("verb".equals(fieldname)) {

                        if (jParser.nextToken() == JsonToken.START_ARRAY) {
                            // messages is array, loop until token equal to "]"
                            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                                String elementName = jParser.getText();
                                for (String word: words) {
                                    if (word.equals(elementName)) {
                                        result.set(0, word);
                                    }
                                }
                            }
                        }
                    }

                    if ("noun".equals(fieldname)) {

                        if (jParser.nextToken() == JsonToken.START_ARRAY) {
                            // messages is array, loop until token equal to "]"
                            while (jParser.nextToken() != JsonToken.END_ARRAY) {
                                String elementName = jParser.getText();
                                for (String word: words) {
                                    if (word.equals(elementName)) {
                                        result.set(1, word);
                                    }
                                }
                            }
                        }
                    }

                }

            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        //}
        return result;
    }

}