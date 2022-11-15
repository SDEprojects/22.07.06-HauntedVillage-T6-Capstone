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

    public Location getLocation() {
        return location;
    }

    public void getCurrentRoom(String direction) {
        location.moving(direction, rooms);
    }

    public ItemList getGameItems() {
        return gameItems;
    }

    public NpcDialogue getNpc() {
        return npc;
    }
}