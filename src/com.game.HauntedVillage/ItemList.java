package com.game.HauntedVillage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class ItemList {

    private String name;
    private String description;
    private String location;
    private String Description;
    private String use_location;
    private String health_reduction;
    private String boss_kill;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<ItemList> listItem;
    private List<String> itemNameList = new ArrayList<>();
    private FileReading file = new FileReading();

    public ItemList(){
        super();
    }

// business methods

    // This function loads all the items into a list
    public List<ItemList> dataReader(){
        try {
            String itemData = file.dataReader("items.txt");
            listItem = objectMapper.readValue(itemData, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listItem;
    }

 //This function retrieves an item by name

    public ItemList getItemByName(String name){
        List<ItemList> listOfItems = dataReader();
        ItemList item = null;
        for(int i = 0; i< listOfItems.size(); i++){
            if(listOfItems.get(i).getName().equals(name)){
                item = listItem.get(i);
            }
        }
        return item;
    }

    // This function return just the names of all the items
    List<String> itemNameList() {
        List<ItemList> listItem = dataReader();
        for (int i = 0; i < listItem.size(); i++) {
            itemNameList.add(listItem.get(i).getName());
        }
        return itemNameList;
    }

    // This function gives the description of the item
    String looking(String name, ItemList item) {
        String description;
        ItemList object = item.getItemByName(name);
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

    public String getBoss_kill() {
        return boss_kill;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Item: name=" + getName() + ", location=" + getLocation()
                + ", description=" + getDescription() + ", use_location="+ getUse_location()
                + ", health_reduction=" + getHealth_reduction()
                + ", boss_kill=" + getBoss_kill();
    }

    public static void main(String[] args) {
        ItemList items = new ItemList();
        System.out.println("Testing dataReader() function");
        System.out.println(items.dataReader());
        System.out.println("Testing getItemByName() function");
        System.out.println(items.getItemByName("matches"));
        System.out.println("Testing ItemNameList() function");
        System.out.println(items.itemNameList());
    }

}