package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class MainPanel extends JPanel {
    private final GameManager baseController;
    private final ScreenChanger changeScreen;
    private final ArrayList<JPanel> bgPanel = new ArrayList<>();
    private final ArrayList<JLabel> bgLabel = new ArrayList<>();
    private int locationOrder = 0;
    private String currentLocation;
    private final JButton[] arrow = new JButton[4];
    private TextPanel textPanel;
    private JLabel objectLabel;



    public MainPanel(GameManager baseController,TextPanel textPanel) {
        this.baseController = baseController;
        this.textPanel=textPanel;
        generateScene();
        currentLocation = baseController.getEngine().location().getCurrent();
        setLayout(null);
        setBounds(50,120,900,500);
        changeScreen = new ScreenChanger(getBgPanel());
        changeScreen.currentRoom("home");

    }

    // Building the completed mainPanel with arrow
    private void backGroundPanel(String fileName, String locationName) {
        // function to build the background image for the current location map
        createBackground(fileName,locationName);
        // function for building existing direction to go
        // function to create the object in this map (such as NPC or item)
//        createObject();
        // add panel with label and moving on next map
//        bgPanel.get(locationOrder).add(bgLabel.get(locationOrder));
        locationOrder++;
    }

    // function to build the background image for the current location map
    private void createBackground(String picName,String locationName) {
        JPanel backGround = new JPanel();
        backGround.setLayout(null);
        backGround.setBounds(0, 0, 900, 500);
        backGround.setBackground(null);
        bgPanel.add(backGround);
        add(backGround);

        JLabel backGroundLable = new JLabel();
        backGroundLable.setLayout(null);
        backGroundLable.setBounds(0, 0, 900, 500);
        bgLabel.add(backGroundLable);



        ImageIcon bgImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(picName)));
        backGroundLable.setIcon(bgImage);
        backGroundLable.repaint();
        createObject(locationName, backGround);
        createArrow(locationName,backGround);
        backGround.add(backGroundLable);
        backGround.repaint();

    }

    private void createArrow(String roomName,JPanel panel) {

        ImageIcon arrow0 = loadingImageIcon("Images_clickTriggers/north.png");
        ImageIcon arrow1 = loadingImageIcon("Images_clickTriggers/south.png");
        ImageIcon arrow2 = loadingImageIcon("Images_clickTriggers/west.png");
        ImageIcon arrow3 = loadingImageIcon("Images_clickTriggers/east.png");

        List<String> directionList = baseController.getEngine().location().directionList(roomName);
        for (int i = 0; i < directionList.size(); i++) {
            if (directionList.contains("north")) {
                arrowBtn(0, 350, 0, arrow0, "north");
                panel.add(arrow[0]);
            }
            if (directionList.contains("south")) {
                arrowBtn(1, 350, 350, arrow1, "south");
                panel.add(arrow[1]);
            }
            if (directionList.contains("west")) {
                arrowBtn(2, 0, 200, arrow2, "west");
                panel.add(arrow[2]);
            }
            if (directionList.contains("east")) {
                arrowBtn(3, 750, 200, arrow3, "east");
                panel.add(arrow[3]);
            }
        }
    }

    private void createObject(String locationName, JPanel panel){
        objectLabel = new JLabel();
        objectLabel.setBounds(450, 230, 300, 300);
        int num = 2;
        ImageIcon objectImage;
        switch(locationName){
            case "home":
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/npc_kids.png"));
                objectLabel.setIcon(objectImage);
                if(num != 2) {
                    panel.add(objectLabel);
                }
                break;
            case "center courtyard":
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/villagers.png"));
                objectLabel.setBounds(500, 230, 300, 300);
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "northern square":
                objectLabel.setBounds(450, 280, 300, 300);
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/kids.png"));
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "southern square":
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/npc_kids.png"));
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "farm":
                objectLabel.setBounds(450, 230, 300, 300);
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/werewolf.png"));
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "town hall":
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/werewolf.png"));
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "tavern":
                objectLabel.setBounds(560, 230, 300, 300);
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/keep.png"));
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "church":
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/pastor.png"));
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "well":
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/npc_kids.png"));
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "woods":
//                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/npc_kids.png"));
//                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
        }
    }


    private ImageIcon loadingImageIcon(String file) {
        return new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(file)));
    }

    private void arrowBtn(int arrowDirection, int xPosition, int yPosition, ImageIcon arrowIcon, String direction) {
        arrow[arrowDirection] = new JButton();
        arrow[arrowDirection].setBackground(null);
        arrow[arrowDirection].setBorderPainted(false);
        arrow[arrowDirection].setBorder(null);
        arrow[arrowDirection].setIcon(arrowIcon);
        arrow[arrowDirection].setBounds(xPosition, yPosition, 100, 100);
        arrow[arrowDirection].addActionListener(new ArrowListener());
        arrow[arrowDirection].setActionCommand(direction);
    }

    public ArrayList<JPanel> generateScene() {
        backGroundPanel("Background_images/home.jpg", "home");
        backGroundPanel("Background_images/center_courtyard.jpg", "center courtyard");
        backGroundPanel("Background_images/northern_square.jpg", "northern square");
        backGroundPanel("Background_images/southern_square.jpg", "southern square");
        backGroundPanel("Background_images/farm3.jpg", "farm");
        backGroundPanel("Background_images/townHall.jpg", "town hall");
        backGroundPanel("Background_images/tavern.jpg", "tavern");
        backGroundPanel("Background_images/church2.jpg", "church");
        backGroundPanel("Background_images/well.jpg", "well");
        backGroundPanel("Background_images/woods.jpg", "woods");
        return bgPanel;
    }


    public ArrayList<JPanel> getBgPanel() {
        return bgPanel;
    }


    private class ArrowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("east")) {
                changeScreen.currentRoom(baseController.getEngine().location().getCurrentRoom().getEast());
                baseController.getEngine().currentRoom(command);
//                textPanel.setText(baseController.getEngine().location().getCurrentRoom().getDescription());
            } else if (command.equals("north")) {
                changeScreen.currentRoom(baseController.getEngine().location().getCurrentRoom().getNorth());
                baseController.getEngine().currentRoom(command);
            } else if (command.equals("west")) {
                changeScreen.currentRoom(baseController.getEngine().location().getCurrentRoom().getWest());
                baseController.getEngine().currentRoom(command);
            } else if (command.equals("south")) {
                changeScreen.currentRoom(baseController.getEngine().location().getCurrentRoom().getSouth());
                baseController.getEngine().currentRoom(command);
            } else {
                System.out.println("you need more arrows");
            }
            baseController.displayText();

        }
    }
}