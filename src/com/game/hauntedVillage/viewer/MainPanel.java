package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;
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
    private JTextArea textArea=new JTextArea();


    public MainPanel(GameManager baseController,TextPanel textPanel) {
        this.baseController = baseController;
        this.textPanel=textPanel;
        currentLocation = baseController.getEngine().location().getCurrent();
        generateScene();
        changeScreen = new ScreenChanger(getBgPanel());
    }

    // Building the completed mainPanel with arrow
    private void backGroundPanel(String fileName, String locationName) {
        // function to build the background image for the current location map
        createBackground(fileName);
        // function for building existing direction to go
        createArrow(locationName);
        // function to create the object in this map (such as NPC or item)
//        createObject();
        // add panel with label and moving on next map
        bgPanel.get(locationOrder).add(bgLabel.get(locationOrder));
        locationOrder++;
    }

    // function to build the background image for the current location map
    private void createBackground(String picName) {
        JPanel backGround = new JPanel();
        backGround.setBounds(50, 50, 900, 500);
        backGround.setLayout(null);
        backGround.setBackground(null);
        bgPanel.add(backGround);
        add(bgPanel.get(locationOrder));

        JLabel backGroundLable = new JLabel();
        backGroundLable.setBounds(0, 0, 900, 500);
        backGroundLable.setLayout(null);
        bgLabel.add(backGroundLable);

        ImageIcon bgImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(picName)));
        bgLabel.get(locationOrder).setIcon(bgImage);
        bgPanel.get(locationOrder).add(bgLabel.get(locationOrder));
    }

    private void createArrow(String roomName) {
        ImageIcon arrow0 = loadingImageIcon("Images_clickTriggers/north.png");
        ImageIcon arrow1 = loadingImageIcon("Images_clickTriggers/south.png");
        ImageIcon arrow2 = loadingImageIcon("Images_clickTriggers/west.png");
        ImageIcon arrow3 = loadingImageIcon("Images_clickTriggers/east.png");

        List<String> directionList = baseController.getEngine().location().directionList(roomName);
        for (int i = 0; i < directionList.size(); i++) {
            if (directionList.contains("north")) {
                arrowBtn(0, 350, 0, arrow0, "north");
                bgPanel.get(locationOrder).add(arrow[0]);
            }
            if (directionList.contains("south")) {
                arrowBtn(1, 350, 350, arrow1, "south");
                bgPanel.get(locationOrder).add(arrow[1]);
            }
            if (directionList.contains("west")) {
                arrowBtn(2, 0, 200, arrow2, "west");
                bgPanel.get(locationOrder).add(arrow[2]);
            }
            if (directionList.contains("east")) {
                arrowBtn(3, 780, 200, arrow3, "east");
                bgPanel.get(locationOrder).add(arrow[3]);
            }
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
        arrow[arrowDirection].addActionListener(new arrowListener());
        arrow[arrowDirection].setActionCommand(direction);
    }

    public ArrayList<JPanel> generateScene() {
        backGroundPanel("Background_images/home.jpg", "home");
        backGroundPanel("Background_images/center_courtyard.jpg", "center courtyard");
        backGroundPanel("Background_images/northern_square.jpg", "northern square");
        backGroundPanel("Background_images/southern_square.jpg", "southern square");
        backGroundPanel("Background_images/farm.jpg", "farm");
        backGroundPanel("Background_images/townhall.jpg", "town hall");
        backGroundPanel("Background_images/tavern.jpg", "tavern");
        backGroundPanel("Background_images/church.jpg", "church");
        backGroundPanel("Background_images/well.jpg", "well");
        backGroundPanel("Background_images/woods.jpg", "woods");
        return bgPanel;
    }

    public ArrayList<JPanel> getBgPanel() {
        return bgPanel;
    }

    class arrowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("east")) {
                changeScreen.currentRoom(baseController.getEngine().location().getCurrentRoom().getEast());
                baseController.getEngine().currentRoom(command);
//                System.out.println(baseController.getEngine().location().getCurrentRoom().getDescription());
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

        }
    }
}