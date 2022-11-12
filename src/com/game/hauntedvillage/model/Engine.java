package com.game.hauntedvillage.model;


import java.util.List;

public class Engine {
    private final Location location = new Location();
    private final ItemList gameItems = new ItemList();
    private final Character player = new Character();
    private NpcDialogue npc = new NpcDialogue();
    private List<Location> rooms = location.dataReader();


    // CONSTRUCTOR
    public Engine() {
    }

    public Character getPlayer() {
        return player;
    }

    public Location location(){
        return location;
    }
    public void currentRoom(String direction){
        location.moving(direction,rooms);
    }

    public ItemList gameItems(){
        return gameItems;
    }

    public NpcDialogue npc(){
        return npc;
    }


//
//
//
//
//
//
//    void playerInforPrompt() {
//        System.out.println(movement.getLocationByName(Location.currentRoom).getDescription());
//        for (int i = 0; i < rooms.size(); i++) {
//            if (rooms.get(i).getCurrent().equals(Location.getCurrentRoom())) {
//                items = rooms.get(i).getItems();
//            }
//        }
//        if (!displayItem) {
//            if (items.size() > 0) {
//                System.out.println("\n\nAfter the search, there are some items you may take with you :" + items);
//            } else {
//                System.out.println("\n\nThere is nothing at this room.");
//            }
//        }
//    }




}