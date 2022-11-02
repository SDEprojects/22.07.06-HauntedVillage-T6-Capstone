package com.game.HauntedVillage;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TextParser {

    private ArrayList<String> synonymList;

    public TextParser() {
    }


    public ArrayList<String> textParser(String input){

        generateSynonymList();

        ArrayList<String> result =  new ArrayList<>(List.of("verb", "noun"));
        // split string by no space
        String[] strSplit = input.split(" ");

        // Now convert string into ArrayList
        ArrayList<String> words = new ArrayList<>(Arrays.asList(strSplit));
        ArrayList<String> synonyms = getSynonymList();

        //for (String word: words) {
            try (JsonParser jParser = new JsonFactory()
                    .createParser(new File("resources/textParse.json"))) {

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
                                        if (synonyms.contains(word)){
                                            result.set(0, synonym(word));
                                        }
                                        else {
                                            result.set(0, word);
                                        }
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
                                        if (synonyms.contains(word)){
                                            result.set(1, synonym(word));
                                        }
                                        else {
                                            result.set(1, word);
                                        }
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

    private String synonym(String word) {
        String newWord = "";
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode rootArray = mapper.readTree(new File("resources/synonym.json"));

            for (JsonNode root : rootArray) {
                JsonNode name = rootArray.path(word);

                if (name.equals(root)) {
                    newWord = root.asText();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return newWord;
    }

    public void generateSynonymList() {

        ArrayList<String> synonyms = new ArrayList<>(0);
        ObjectMapper mapper = new ObjectMapper();

        try{
            JsonNode rootArray = mapper.readTree(new File("resources/synonym.json"));

            Iterator<String> iterator = rootArray.fieldNames();
            iterator.forEachRemaining(synonyms::add);

            setSynonymList(synonyms);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getSynonymList() {
        return synonymList;
    }

    public void setSynonymList(ArrayList<String> synonymList) {
        this.synonymList = synonymList;
    }



}