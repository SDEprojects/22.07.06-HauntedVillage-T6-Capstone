package com.game.hauntedvillage.model;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class LocationTest {
    Location location=new Location();

    @Test
    public void buildingDirectionListWith_validInput(){
        List<String> expectList=new LinkedList<>();
        expectList.add("east");
        List<String> actualList=location.directionList( "home");
        assertEquals(expectList.get(0),actualList.get(0));
        assertEquals(expectList.size(),actualList.size());
    }
    @Test
    public void buildingDirectionListWith_validInputWithMultiAnswer(){
        List<String> expectList=new LinkedList<>();
        expectList.add("north");
        expectList.add("east");
        expectList.add("south");
        List<String> actualList=location.directionList( "southern square");
        assertEquals(expectList.get(0),actualList.get(0));
        assertEquals(expectList.size(),actualList.size());
    }
    @Test
    public void buildingDirectionListWith_InvalidInput(){
        List<String> expectList=new LinkedList<>();
        List<String> actualList=location.directionList( "");
        assertEquals(expectList.size(),actualList.size());
    }
}