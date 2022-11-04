package com.game.hauntedVillage.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NpcDialogue {

    private String name;
    private String location;
    private ArrayList<String> conversations = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<NpcDialogue> listNpc;
    private List<String> npcNameList = new ArrayList<>();
    private FileReading file = new FileReading();

    public NpcDialogue(){
        super();
    }

    //Business Class data reader
    List<NpcDialogue> dataReader(){
        try {
            String npcData = file.dataReader("npc.txt");
            listNpc = objectMapper.readValue(npcData, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listNpc;
    }

    //This function returns a npc using the name
    NpcDialogue getNpcByName(String name){
        List<NpcDialogue> listOfNpcs = dataReader();
        NpcDialogue npc = null;
        for(int i = 0; i< listOfNpcs.size(); i++){
            if(listOfNpcs.get(i).getName().equals(name)){
                npc = listOfNpcs.get(i);
            }
        }
        return npc;
    }
    // This function returns the name of all the npcs
    public List<String> npcNameList() {
        List<NpcDialogue> listNpc = dataReader();
        for (int i = 0; i < listNpc.size(); i++) {
            npcNameList.add(listNpc.get(i).getName());
        }
        return npcNameList;
    }

    // This function implements the speak feature
     public String speak(String name){
         String dialogue;

        // getting the npc by name
        NpcDialogue npc = getNpcByName(name);

        // getting all the conversations for that npc
         ArrayList<String> npcConversations = npc.getConversations();

         //generating a random number based on the size of the array of dialogues
         int randIndex = (int)(Math.random() * npcConversations.size());

         dialogue = npcConversations.get(randIndex) ;

         return dialogue;
     }


    //getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
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

    public static void main(String[] args) {
        NpcDialogue npcs = new NpcDialogue();
        System.out.println("Testing dataReader() function");
        System.out.println(npcs.dataReader());
        System.out.println("Testing getNpcByName() function");
        System.out.println(npcs.getNpcByName("children"));
        System.out.println("Testing NpcNameList() function");
        System.out.println(npcs.npcNameList());
        System.out.println("Testing dialogue() function");
        System.out.println(npcs.speak("children"));
    }


}