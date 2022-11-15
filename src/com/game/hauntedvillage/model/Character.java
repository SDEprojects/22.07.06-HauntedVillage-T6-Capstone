package com.game.hauntedvillage.model;

import java.util.*;

public class Character {
    private final ArrayList<String> inventory = new ArrayList<>();
    private int hp = 5;

    public Character() {
        super();
    }

    public void addItemToinventory(String itemName) {
        inventory.add(itemName);
    }

    public void dropItem(String itemName) {
        inventory.remove(itemName);
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public int getHp() {
        return hp;
    }
}
