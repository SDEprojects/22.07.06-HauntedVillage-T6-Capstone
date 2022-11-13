package com.game.hauntedvillage.model;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ItemListTest {

    @Test
    public void itemNameListTest() {
        ItemList items = new ItemList();
        String[] inventory = new String[]{"matches", "crucifix", "knife", "shovel",
                "musket", "silver bullet", "birds food", "triangular amulet",
                "blue stone", "water", "bag", "gift",};
        List<String> itemLists = items.itemNameList();
        assertArrayEquals(inventory, itemLists.toArray());
    }

    @Test
    public void itemDescriptionTest(){
        ItemList itemList = new ItemList();
        ItemList object = itemList.getItemByName("crucifix");
        String itemDescription = "crucifix";
        assertEquals(itemDescription, object);
    }

}