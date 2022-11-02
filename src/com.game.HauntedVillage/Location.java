package com.game.HauntedVillage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Location {
    static String currentRoom = "Home";
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

    Location() {
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

    List<Location> dataReader() {
        try {
            String locationData = file.dataReader("location.txt");
            locations = objectMapper.readValue(locationData, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return locations;
    }

    Location getLocationByName(String name) {
        Location room = null;
        List<Location> Locations = dataReader();
        for (Location location : Locations) {
            if (location.getCurrent().equals(name)) {
                return room = location;
            }
        }
        return room;
    }

    void moving(String direction, List<Location> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            if (currentRoom.equals(rooms.get(i).getCurrent())) {
                if (direction.equals("north")) {
                    if (!rooms.get(i).getNorth().equals("no exit")) {
                        currentRoom = rooms.get(i).getNorth();
                        break;
                    } else {
                        System.out.println( "Wrong way!" );
                    }
                } else if (direction.equals("south")) {
                    if (!rooms.get(i).getSouth().equals("no exit")) {
                        currentRoom = rooms.get(i).getSouth();
                        break;
                    } else {
                        System.out.println("Wrong way!" );
                    }
                } else if (direction.equals("east")) {
                    if (!rooms.get(i).getEast().equals("no exit")) {
                        currentRoom = rooms.get(i).getEast();
                        break;
                    } else {
                        System.out.println("Wrong way!" );
                    }
                } else if (direction.equals("west")) {
                    if (!rooms.get(i).getWest().equals("no exit")) {
                        currentRoom = rooms.get(i).getWest();
                        break;
                    } else {
                        System.out.println("Wrong way!" );
                    }
                } else {
                    System.out.println("Please enter a valid entry" );
                    currentRoom = currentRoom;
                }
            }
        }
        System.out.println(getLocationByName(Location.currentRoom).getDescription());
    }

    public static String getCurrentRoom() {
        return currentRoom;
    }

    public static void setCurrentRoom(String currentRoom) {
        Location.currentRoom = currentRoom;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Room: current Room=" + getCurrent() + ", north=" + getNorth() + ", south=" + getSouth() + ", west=" + getWest()
                + ", east=" + getEast() + ", items=" + getItems() + ", description=" + getDescription();
    }

    public static void main(String[] args) {
        Location abc=new Location();
        System.out.println(abc.dataReader().get(0).getItems().get(0));
    }
}
