package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

class MapFrame extends JFrame{

    private GameManager baseController;
    private FontStyle font = new FontStyle();

    public MapFrame(GameManager baseController){
        this.baseController = baseController;
        generateRooms();
        setTitle("Map");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,540);
        setLayout(null);
        setVisible(true);
    }
    private JPanel createRoomLabel (int xPos, int yPos, String locationName){
        JPanel roomPanel = new JPanel();
        roomPanel.setBounds(xPos,yPos, 199, 74);
        roomPanel.setBackground(Color.gray);
        Border border = BorderFactory.createLineBorder(Color.black, 3);
        roomPanel.setBorder(border);
        roomPanel.setOpaque(true);

        JLabel roomLabel = new JLabel(locationName, SwingConstants.CENTER);
        roomLabel.setBounds(25,20, 150, 30);
        roomPanel.add(roomLabel);
        roomLabel.setForeground(Color.decode("#7E2811"));
        roomLabel.setFont(font.getfont(Font.BOLD, 18, "ReadingFile/Blood.ttf"));

        if(locationName.equals(baseController.getEngine().location().getCurrent())){
            roomPanel.setBackground(Color.gray);
            roomLabel.setForeground(Color.green);
        }
        roomPanel.setLayout(null);
        return  roomPanel;
    }

    private void createMap(String locationName){

        switch (locationName){
            case "home":
                JPanel homePanel = createRoomLabel(0,265, locationName);
                add(homePanel);
                break;
            case "farm":
                JPanel farmPanel = createRoomLabel(200,415, locationName);
                add(farmPanel);
                break;
            case "southern square":
                JPanel southernPanel = createRoomLabel(200,340, locationName);
                add(southernPanel);
                break;
            case "center courtyard":
                JPanel centerPanel = createRoomLabel(200,265, locationName);
                add(centerPanel);
                break;
            case "northern square":
                JPanel northernPanel = createRoomLabel(200,190, locationName);
                add(northernPanel);
                break;
            case "well":
                JPanel wellPanel = createRoomLabel(200,115, locationName);
                add(wellPanel);
                break;
            case "woods":
                JPanel woodsPanel = createRoomLabel(200,40, locationName);
                add(woodsPanel);
                break;
            case "tavern":
                JPanel tavernPanel = createRoomLabel(400,265, locationName);
                add(tavernPanel);
                break;
            case "church":
                JPanel churchPanel = createRoomLabel(400,190, locationName);
                add(churchPanel);
                break;
            case "town hall":
                JPanel townHallPanel = createRoomLabel(400,340, locationName);
                add(townHallPanel);
                break;

            default:
                System.out.println("works");

        }


    }

    public void generateRooms(){

        createMap("home");
        createMap("farm");
        createMap("center courtyard");
        createMap("southern square");
        createMap("northern square");
        createMap("well");
        createMap("woods");
        createMap("church");
        createMap("tavern");
        createMap("town hall");
    }


}