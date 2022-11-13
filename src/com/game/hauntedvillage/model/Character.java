package com.game.hauntedvillage.model;

import java.util.*;

public class Character {
    private String description;
    private final ArrayList<String> inventory = new ArrayList<>();
    private String location;
    private int hp = 5;
    private int maxHP = 5;

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

    public void setHp(int hp) {
        this.hp = hp;
    }
}
