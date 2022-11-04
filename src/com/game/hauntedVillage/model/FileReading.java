package com.game.hauntedVillage.model;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

class FileReading {
    private JSONParser parser = new JSONParser();

    static InputStream getFileFromResourceAsStreamFortxt(String fileName) {
        ClassLoader classLoader = FileReading.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("Can not read the file! " + fileName);
        } else {
            return inputStream;
        }
    }

    static void printInputStream(InputStream content) {
        try (InputStreamReader streamReader = new InputStreamReader(content, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String dataReader(String filename) {
        String data = "";
        try {
            InputStream fileData = FileReading.getFileFromResourceAsStreamFortxt(filename);
            String result = new BufferedReader(new InputStreamReader(fileData))
                    .lines().collect(Collectors.joining("\n"));
            Object obj = parser.parse(result);
            data = obj.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }
}
