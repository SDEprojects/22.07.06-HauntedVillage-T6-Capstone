package com.game.hauntedVillage.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Location {
    //    public static String currentRoom = "home";
    private String current = "home";
    private String north;
    private String south;
    private String west;
    private String east;
    private ArrayList<String> items;
    private String description;
    private String direction;
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
            String locationData = file.dataReader("ReadingFile/location.txt");
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

    public void moving(String direction, Location room) {
        if (room.getEast().equals("east")) {
            current = room.getEast();
        } else if (room.getSouth().equals("south")) {
            current = room.getSouth();
        } else if (room.getNorth().equals("north")) {
            current = room.getNorth();
        } else if (room.getWest().equals("west")) {
            current = room.getWest();
        } else {
            current = current;
        }
    }

    public List<String> directionList(String roomName) {
        Location currentArea = getLocationByName(roomName);
        System.out.println(currentArea.getCurrent());
        System.out.println(currentArea.getNorth());
        List<String> validDirection = new LinkedList<>();
        if (!currentArea.getNorth().equals("no exit")) {
            validDirection.add("north");
        }
        if (!currentArea.getSouth().equals("no exit")) {
            validDirection.add("south");
        }
        if (!currentArea.getWest().equals("no exit")) {
            validDirection.add("west");
        }
        if (!currentArea.getEast().equals("no exit")) {
            validDirection.add("east");
        }
        return validDirection;
    }

    public void movingDirection(String direction) {
//        room = getLocationByName(room.getCurrent());
//        moving(direction, room);
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
