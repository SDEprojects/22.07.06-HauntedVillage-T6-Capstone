package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.event.*;
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
    JPopupMenu popupMenu;



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
        locationOrder++;
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

    private void createObject(String locationName, JPanel panel) {

        objectLabel = new JLabel();
        objectLabel.setBounds(450, 230, 300, 300);
        objectLabel.addMouseListener(new ObjectListener());
        ImageIcon objectImage;

        popupMenu = new JPopupMenu();
        JMenuItem[] menuItems = new JMenuItem[3];
       menuItems[0] = new JMenuItem("speak");
       menuItems[0].addActionListener(new menuListener());
       menuItems[0].setActionCommand("speak");
       popupMenu.add(menuItems[0]);

       menuItems[1] = new JMenuItem("attack");
        menuItems[1].addActionListener(new menuListener());
        menuItems[1].setActionCommand("attack");
       popupMenu.add(menuItems[1]);

       menuItems[2] = new JMenuItem("");
        popupMenu.add(menuItems[2]);
        menuItems[1].addActionListener(new menuListener());
        menuItems[1].setActionCommand("");


        switch(locationName){
            case "home":
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/npc_kids.png"));
                objectLabel.setText("home");
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "center courtyard":
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/villagers.png"));
                objectLabel.setText("villagers");
                objectLabel.setBounds(500, 230, 300, 300);
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "northern square":
                objectLabel.setBounds(450, 280, 300, 300);
                objectLabel.setText("children");
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
                objectLabel.setText("werewolf");
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
                objectLabel.setText("keep");
                objectLabel.setBounds(560, 230, 300, 300);
                objectImage = new ImageIcon(getClass().getClassLoader().getResource("npc_images/keep.png"));
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "church":
                objectLabel.setText("pastor");
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

    private class menuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            System.out.println(command);
            JMenuItem text = (JMenuItem) e.getSource();

            JPopupMenu clickedPopMenu = (JPopupMenu) text.getParent();
            JLabel objectClicked = (JLabel) clickedPopMenu.getInvoker();

            switch (command){
                case "speak":
                    baseController.speak(objectClicked.getText());
                    break;
                case "attack":
                    objectClicked.setVisible(false);

            }
        }
    }

    private class ObjectListener implements MouseListener{


        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel objectClicked = (JLabel) e.getSource();
//            if(objectClicked.getText().equals("villagers")){
//                popupMenu.getSubElements()[1].getComponent().setVisible(false);
//            }
            popupMenu.show(objectClicked, e.getX(), e.getY());

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

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
        createBackground("Background_images/home.jpg", "home");
        createBackground("Background_images/center_courtyard.jpg", "center courtyard");
        createBackground("Background_images/northern_square.jpg", "northern square");
        createBackground("Background_images/southern_square.jpg", "southern square");
        createBackground("Background_images/farm3.jpg", "farm");
        createBackground("Background_images/townHall.jpg", "town hall");
        createBackground("Background_images/tavern.jpg", "tavern");
        createBackground("Background_images/church2.jpg", "church");
        createBackground("Background_images/well.jpg", "well");
        createBackground("Background_images/woods.jpg", "woods");
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