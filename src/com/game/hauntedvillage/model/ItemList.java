package com.game.hauntedvillage.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.hauntedvillage.utility.FileReading;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemList {
    private String name;
    private String description;
    private String location;
    private String Description;
    private String use_location;
    private String health_reduction;
    private String boss_kill;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<ItemList> listItem;
    private final List<String> itemNameList = new ArrayList<>();
    private final FileReading file = new FileReading();

    public ItemList() {
        super();
    }

// business methods

    // This function loads all the items into a list
    private List<ItemList> dataReader() {
        try {
            String itemData = file.dataReader("ReadingFile/items.txt");
            listItem = objectMapper.readValue(itemData, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listItem;
    }

    //This function retrieves an item by name

    ItemList getItemByName(String name) {
        List<ItemList> listOfItems = dataReader();
        ItemList item = null;
        for (int i = 0; i < listOfItems.size(); i++) {
            if (listOfItems.get(i).getName().equals(name)) {
                item = listItem.get(i);
            }
        }
        return item;
    }

    // This function return just the names of all the items
    public List<String> itemNameList() {
        List<ItemList> listItem = dataReader();
        for (int i = 0; i < listItem.size(); i++) {
            itemNameList.add(listItem.get(i).getName());
        }
        return itemNameList;
    }

    // This function gives the description of the item
    public String looking(String name) {
        String description;
        ItemList object = getItemByName(name);
        description = object.getDescription();
        return description;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUse_location() {
        return use_location;
    }

    public String getHealth_reduction() {
        return health_reduction;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBoss_kill() {
        return boss_kill;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Item: name=" + getName() + ", location=" + getLocation()
                + ", description=" + getDescription() + ", use_location=" + getUse_location()
                + ", health_reduction=" + getHealth_reduction()
                + ", boss_kill=" + getBoss_kill();
    }
}