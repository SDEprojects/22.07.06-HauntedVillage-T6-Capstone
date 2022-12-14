package com.game.hauntedvillage.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.game.hauntedvillage.utility.FileReading;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NpcDialogue {

    private String name;
    private String location;
    private final ArrayList<String> conversations = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<NpcDialogue> listNpc;
    private ArrayList<String> npcNameList = new ArrayList<>();
    private final FileReading file = new FileReading();

    //Business Class data reader
    private List<NpcDialogue> dataReader() {
        try {
            String npcData = file.dataReader("ReadingFile/npc.txt");
            listNpc = objectMapper.readValue(npcData, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listNpc;
    }

    //This function returns a npc using the name
    private NpcDialogue getNpcByName(String name) {
        List<NpcDialogue> listOfNpcs = dataReader();
        NpcDialogue npc = null;
        for (int i = 0; i < listOfNpcs.size(); i++) {
            if (listOfNpcs.get(i).getName().equals(name)) {
                npc = listOfNpcs.get(i);
            }
        }
        return npc;
    }

    // This function returns the name of all the npcs
    public ArrayList<String> npcNameList() {
        List<NpcDialogue> listNpc = dataReader();
        for (int i = 0; i < listNpc.size(); i++) {
            npcNameList.add(listNpc.get(i).getName());
        }
        return npcNameList;
    }

    // This function implements the speak feature
    public String speak(String name) {
        String dialogue;

        // getting the npc by name
        NpcDialogue npc = getNpcByName(name);

        // getting all the conversations for that npc
        ArrayList<String> npcConversations = npc.getConversations();

        //generating a random number based on the size of the array of dialogues
        int randIndex = (int) (Math.random() * npcConversations.size());

        dialogue = npcConversations.get(randIndex);

        return dialogue;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public ArrayList<String> getNameList(){
        if(npcNameList.size()==0){
            npcNameList=npcNameList();
        }
        return npcNameList;
    }
    public void removeNPC(String name){
        npcNameList.remove(name);
    }

    public ArrayList<String> getConversations() {
        return conversations;
    }

    //to string
    @Override
    public String toString() {
        return "Item: name=" + getName() + ", location=" + getLocation()
                + ", conversations=" + getConversations();
    }
}