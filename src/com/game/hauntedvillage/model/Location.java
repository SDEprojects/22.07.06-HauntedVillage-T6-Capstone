package com.game.hauntedvillage.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.hauntedvillage.utility.FileReading;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Location {
    private String current = "home";
    private String north;
    private String south;
    private String west;
    private String east;
    private ArrayList<String> items;
    private String description;
    private String direction;
    private ArrayList<String> action;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Location> locations;
    private List<String> locationNameList=new ArrayList<>();
    private ArrayList<String> actionList=new ArrayList<>();
    private final FileReading file = new FileReading();

    public ArrayList<String> allAreaActionList(String locationName){
        List<Location> Locations = dataReader();
        for (int i = 0; i < Locations.size(); i++) {
            if(locationName.equals(Locations.get(i).getCurrent()))
            actionList = Locations.get(i).getAction();
        }
        return actionList;
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

    private Location getLocationByName(String name) {
        Location room = null;
        List<Location> Locations = dataReader();
        for (Location location : Locations) {
            if (location.getCurrent().equals(name)) {
                room = location;
                break;
            }
        }
        return room;
    }

    public void moving(String direction, List<Location> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            if (current.equals(rooms.get(i).getCurrent())) {
                if (direction.equals("north")&&!rooms.get(i).getNorth().equals("no exit")) {
                    current = rooms.get(i).getNorth();
                    break;
                }
                else if (direction.equals("south")&&!rooms.get(i).getSouth().equals("no exit")) {
                    current = rooms.get(i).getSouth();
                    break;
                }
                else if (direction.equals("east")&&!rooms.get(i).getEast().equals("no exit")) {
                    current = rooms.get(i).getEast();
                    break;
                }
                else if (direction.equals("west")&&!rooms.get(i).getWest().equals("no exit")) {
                    current = rooms.get(i).getWest();
                    break;
                }else{
                    System.out.println("Invalid the direction");
                }
            }
        }
    }
    public Location getCurrentRoom(){
        Location currentRoom = getLocationByName(current);
        return currentRoom;
    }

    public List<String> directionList(String roomName) {
        List<String> validDirection = new LinkedList<>();
        Location currentArea = getLocationByName(roomName);

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

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getAction() {
        return action;
    }

    public void setAction(ArrayList<String> action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Room: current Room=" + getCurrent() + ", north=" + getNorth() + ", south=" + getSouth() + ", west=" + getWest()
                + ", east=" + getEast() + ", items=" + getItems() + ", description=" + getDescription() + ", action=" + getAction();
    }
}
