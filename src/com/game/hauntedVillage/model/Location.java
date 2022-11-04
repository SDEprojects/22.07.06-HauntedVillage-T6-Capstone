package com.game.hauntedVillage.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Location {
    public static String currentRoom = "home";
    private String current;
    private String north;
    private String south;
    private String west;
    private String east;
    private ArrayList<String> items;
    private String description;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<Location> locations;
    private List<String> locationNameList = new ArrayList<>();
    private FileReading file = new FileReading();

    public Location() {
        super();
    }

    Location(String current, String north, String south, String west, String east, ArrayList<String> items, String description) {
        this.current = current;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
        this.items = items;
        this.description = description;
    }

    public List<Location> dataReader() {
        try {
            String locationData = file.dataReader("location.txt");
            locations = objectMapper.readValue(locationData, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locations;
    }

    public Location getLocationByName(String name) {
        Location room = null;
        List<Location> Locations = dataReader();
        for (Location location : Locations) {
            if (location.getCurrent().equals(name)) {
                return room = location;
            }
        }
        return room;
    }

    public void moving(String direction, List<Location> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            if (currentRoom.equals(rooms.get(i).getCurrent())) {
                if (direction.equals("north")) {
                    if (!rooms.get(i).getNorth().equals("no exit")) {
                        currentRoom = rooms.get(i).getNorth();
                        break;
                    } else {
                        System.out.println("Wrong way!");
                    }
                } else if (direction.equals("south")) {
                    if (!rooms.get(i).getSouth().equals("no exit")) {
                        currentRoom = rooms.get(i).getSouth();
                        break;
                    } else {
                        System.out.println("Wrong way!");
                    }
                } else if (direction.equals("east")) {
                    if (!rooms.get(i).getEast().equals("no exit")) {
                        currentRoom = rooms.get(i).getEast();
                        break;
                    } else {
                        System.out.println("Wrong way!");
                    }
                } else if (direction.equals("west")) {
                    if (!rooms.get(i).getWest().equals("no exit")) {
                        currentRoom = rooms.get(i).getWest();
                        break;
                    } else {
                        System.out.println("Wrong way!");
                    }
                } else {
                    System.out.println("Please enter a valid entry");
                    currentRoom = currentRoom;
                }
            }
        }
    }

    public static String getCurrentRoom() {
        return currentRoom;
    }

    public String getCurrent() {
        return current;
    }

    public String getNorth() {
        return north;
    }

    public String getSouth() {
        return south;
    }

    public String getWest() {
        return west;
    }


    public String getEast() {
        return east;
    }


    public ArrayList<String> getItems() {
        return items;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "Room: current Room=" + getCurrent() + ", north=" + getNorth() + ", south=" + getSouth() + ", west=" + getWest()
                + ", east=" + getEast() + ", items=" + getItems() + ", description=" + getDescription();
    }
}