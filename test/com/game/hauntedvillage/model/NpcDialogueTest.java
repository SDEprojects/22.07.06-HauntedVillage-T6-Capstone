package com.game.hauntedvillage.model;


import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class NpcDialogueTest {


    @Test
    public void speakTest(){
        NpcDialogue npc = new NpcDialogue();
        System.out.println(npc.speak("children"));
        assertEquals("I've never seen that cat before",npc.speak("children") );


    }

    @Test
    public void npcNameListTest(){
        NpcDialogue npc = new NpcDialogue();
        String [] names = new String[] {"children","villagers", "werewolf", "farmer", "clerk",
                "keep","pastor", "cat","demon"};
        List<String> nameList = npc.npcNameList();
        assertArrayEquals(names, nameList.toArray());

    }

}