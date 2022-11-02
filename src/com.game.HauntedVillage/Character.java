package com.game.HauntedVillage;

import com.apps.util.Console;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

class Character {
    private String description;
    private int healthLevel = 10;
    private List<String> inventory = new ArrayList<>();
    private int attackPoint;
    private String location;
    private ObjectMapper objectMapper = new ObjectMapper();
    private FileReading file = new FileReading();

    Character() {
        super();
    }

    Character(String description,String location,int healthLevel) {
        this.description = description;
        this.location=location;
        this.healthLevel=healthLevel;
    }

    //business methods
    void addItem(String roomName, String itemName, ItemList items, List<Location> rooms) {
        int roomNumber = -1;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCurrent().equals(roomName) && rooms.get(i).getItems().get(i).equals(itemName)) {
                roomNumber = i;
            }
        }
        if (roomNumber == -1) {
            System.out.println("There is no " + itemName + " in this area");
        } else {
            inventory.add(itemName);
            for(int i=0;i<rooms.get(roomNumber).getItems().size();i++){
                if(itemName.equals(rooms.get(roomNumber).getItems().get(i))){
                    rooms.get(roomNumber).getItems().remove(i);
                    items.setLocation("inventory");
                    System.out.println("You get " + itemName + " in your bag!");
                }
            }
        }
    }

    void dropItem(String itemName, Character player, ItemList items, List<Location> rooms) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            if (player.getInventory().get(i).equals(itemName)) {
                inventory.remove(itemName);
                System.out.println("\nYou drop " + itemName + " from your bag!");
                items.setLocation(Location.currentRoom);
            } else {
                System.out.println("\nThere is no " + itemName + " to drop from your bag");
            }
        }
    }

    void checkInventory() {
        getInventory();
        if (getInventory().size() > 0) {
            System.out.println("You have following items: "+ getInventory() + ".");
        } else {
            System.out.println("You have nothing in your bag");
        }
    }


    public String getDescription() {
        return description;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getInventory() {
        return inventory;
    }
}
