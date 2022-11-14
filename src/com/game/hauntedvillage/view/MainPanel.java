package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

class MainPanel extends JPanel {
    private final GameManager baseController;
    private final ScreenChanger changeScreen;
    private final ArrayList<JPanel> bgPanel = new ArrayList<>();
    private final JButton[] arrow = new JButton[4];
    private final InventoryPanel inventoryPanel;
    private JPopupMenu popupMenu;

    MainPanel(GameManager baseController, TextPanel textPanel, ItemDisplayPanel itemListPanel, InventoryPanel inventoryPanel) {
        this.baseController = baseController;
        this.inventoryPanel = inventoryPanel;
        generateScene();
        setLayout(null);
        setBounds(50, 70, 900, 500);
        changeScreen = new ScreenChanger(getBgPanel(), itemListPanel.getItemPanel());
        changeScreen.currentRoom("home");
        createPopupMenu("home");
    }

    // function to build the background image for the current location map
    private void createBackground(String picName, String locationName) {
        JPanel backGround = new JPanel();
        backGround.setLayout(null);
        backGround.setBounds(0, 0, 900, 500);
        backGround.setBackground(null);
        bgPanel.add(backGround);
        add(backGround);

        JLabel backGroundLable = new JLabel();
        backGroundLable.setLayout(null);
        backGroundLable.setBounds(0, 0, 900, 500);

        ImageIcon locationBoard = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("BackgroundImages/woodPostLocation.png")));
        JLabel location = new JLabel();
        location.setText(locationName.toUpperCase());
        location.setBounds(0, 400, 200, 100);
        location.setHorizontalTextPosition(JLabel.CENTER);
        location.setVerticalTextPosition(JLabel.CENTER);
        location.setLayout(new FlowLayout());
        location.setFont(new Font("Hanzipen", Font.BOLD, 14));
        location.setForeground(Color.yellow);
        location.setIcon(locationBoard);
        backGroundLable.add(location);

        ImageIcon bgImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(picName)));
        backGroundLable.setIcon(bgImage);
        backGroundLable.addMouseListener(new MapListener());
        backGroundLable.repaint();
        createObject(locationName, backGround);
        createArrow(locationName, backGround);
        backGround.add(backGroundLable);
        backGround.repaint();
    }

    private void createArrow(String roomName, JPanel panel) {
        ImageIcon arrow0 = loadingImageIcon("ImagesClickTriggers/north.png");
        ImageIcon arrow1 = loadingImageIcon("ImagesClickTriggers/south.png");
        ImageIcon arrow2 = loadingImageIcon("ImagesClickTriggers/west.png");
        ImageIcon arrow3 = loadingImageIcon("ImagesClickTriggers/east.png");

        List<String> directionList = baseController.getEngine().getLocation().directionList(roomName);
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

    private JPopupMenu createPopupMenu(String locationName) {
        popupMenu = new JPopupMenu();
        List<String> actions = baseController.getEngine().getLocation().allAreaActionList(locationName);

        ArrayList<JMenuItem> menuItems = new ArrayList<>();
        for (int i = 0; i < actions.size(); i++) {
            menuItems.add(new JMenuItem(actions.get(i)));
            menuItems.get(i).addActionListener(new MenuListener());
            menuItems.get(i).setActionCommand(actions.get(i));
            popupMenu.add(menuItems.get(i));
        }
        return popupMenu;
    }

    private void createObject(String locationName, JPanel panel) {
        JLabel objectLabel = new JLabel();
        objectLabel.addMouseListener(new ObjectListener());
        objectLabel.setBounds(450, 230, 300, 300);

        ImageIcon objectImage;

        switch (locationName) {
            case "home":
                objectImage = loadingImageIcon("NPCImages/home.png");
                objectLabel.setBounds(226, 100, 400, 360);
                objectLabel.setText("home");
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "center courtyard":
                objectImage = loadingImageIcon("NPCImages/villagers.png");
                objectLabel.setText("villagers");
                objectLabel.setBounds(480, 270, 280, 230);
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "northern square":
                objectLabel.setBounds(450, 300, 300, 300);
                objectLabel.setText("children");
                objectImage = loadingImageIcon("NPCImages/kids.png");
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "southern square":
            case "well":
                break;
            case "farm":
                objectLabel.setBounds(450, 230, 300, 300);
                objectLabel.setText("werewolf");
                objectImage = loadingImageIcon("NPCImages/werewolf.png");
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "town hall":
                objectImage = loadingImageIcon("NPCImages/clerk.png");
                objectLabel.setText("clerk");
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "tavern":
                objectLabel.setText("keep");
                objectLabel.setBounds(560, 300, 300, 450);
                objectImage = loadingImageIcon("NPCImages/keep.png");
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "church":
                objectLabel.setText("pastor");
                objectImage = loadingImageIcon("NPCImages/pastor.png");
                objectLabel.setIcon(objectImage);
                panel.add(objectLabel);
                break;
            case "woods":
                objectLabel.setText("demon");
                objectImage = loadingImageIcon("NPCImages/demon.png");
                objectLabel.setIcon(objectImage);
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

    ArrayList<JPanel> generateScene() {
        createBackground("BackgroundImages/home.jpg", "home");
        createBackground("BackgroundImages/center_courtyard.jpg", "center courtyard");
        createBackground("BackgroundImages/northern_square.jpg", "northern square");
        createBackground("BackgroundImages/southern_square.jpg", "southern square");
        createBackground("BackgroundImages/farm3.jpg", "farm");
        createBackground("BackgroundImages/townHall.jpg", "town hall");
        createBackground("BackgroundImages/tavern.jpg", "tavern");
        createBackground("BackgroundImages/church.jpg", "church");
        createBackground("BackgroundImages/well.jpg", "well");
        createBackground("BackgroundImages/woods.jpg", "woods");
        return bgPanel;
    }

    public ArrayList<JPanel> getBgPanel() {
        return bgPanel;
    }

    private class MapListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            baseController.displayText();
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

    private class ArrowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            baseController.itemPanelControllerOff();
            switch (command) {
                case "east":
                    changeScreen.currentRoom(baseController.getEngine().getLocation().getCurrentRoom().getEast());
                    baseController.getEngine().getCurrentRoom(command);
                    break;
                case "north":
                    changeScreen.currentRoom(baseController.getEngine().getLocation().getCurrentRoom().getNorth());
                    baseController.getEngine().getCurrentRoom(command);
                    break;
                case "west":
                    changeScreen.currentRoom(baseController.getEngine().getLocation().getCurrentRoom().getWest());
                    baseController.getEngine().getCurrentRoom(command);
                    break;
                case "south":
                    changeScreen.currentRoom(baseController.getEngine().getLocation().getCurrentRoom().getSouth());
                    baseController.getEngine().getCurrentRoom(command);
                    break;
                default:
                    System.out.println("you need more arrows");
                    break;
            }
            baseController.displayText();
            popupMenu = createPopupMenu(baseController.getEngine().getLocation().getCurrent());
        }
    }

    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            JMenuItem text = (JMenuItem) e.getSource();

            JPopupMenu clickedPopMenu = (JPopupMenu) text.getParent();
            JLabel objectClicked = (JLabel) clickedPopMenu.getInvoker();

            switch (command) {
                case "speak":
                    baseController.speak(objectClicked.getText());
                    break;
                case "wake farmer":
                    baseController.speak("farmer");
                    break;
                case "attack":
                    if (objectClicked.getText().equals("werewolf")) {
                        if (baseController.getEngine().getPlayer().getInventory().contains("silver bullet")) {
                            String confirm = "Do you want to give a silver bullet to the farmer?";
                            int decision = JOptionPane.showConfirmDialog(null, confirm, "Confirm Action", JOptionPane.OK_CANCEL_OPTION);
                            if (decision == JOptionPane.OK_OPTION) {
                                String message = "You gave farmer the silver bullet, farmer loaded the silver bullet " +
                                        "\nand killed the werewolf. Then he gave you his musket for defeating the evil power";
                                baseController.getEngine().getPlayer().dropItem("silver bullet");
                                baseController.getEngine().getPlayer().addItemToinventory("musket");
                                inventoryPanel.createItemInInventory();
                                baseController.displayAttackMessage(message);
                                objectClicked.setVisible(false);
                            } else {
                                String message = "Farmer:\"I need find the silver bullet!!!!\"";
                                baseController.displayAttackMessage(message);
                            }
                        } else {
                            String confirm = "Do you believe you are strong enough to kill werewolf? (you may need some items) ";
                            int decision = JOptionPane.showConfirmDialog(null, confirm, "Confirm Action", JOptionPane.OK_CANCEL_OPTION);
                            if (decision == JOptionPane.OK_OPTION) {

                            }
                        }
                    } else if (objectClicked.getText().equals("pastor")) {
                        if (baseController.getEngine().getPlayer().getInventory().contains("musket")) {
                            String confirm = "Do you want to use musket to kill the evil pastor?";
                            int decision = JOptionPane.showConfirmDialog(null, confirm, "Confirm Action", JOptionPane.OK_CANCEL_OPTION);
                            if (decision == JOptionPane.OK_OPTION) {
                                String message = "You kill the evil pastor, and enter to the church, you see the dead candle" +
                                        "in front the holy statue, you may want to light the candle to pray for blessing";
                                baseController.displayAttackMessage(message);
                                baseController.getEngine().getPlayer().dropItem("musket");
                                inventoryPanel.createItemInInventory();
                                baseController.displayAttackMessage(message);
                                objectClicked.setVisible(false);
                            } else {
                                String message = "\"That pastor is strong, I may need some powerful weapon!!!!\"";
                                baseController.displayAttackMessage(message);
                            }
                        } else {
                            String confirm = "Do you believe you are strong enough to kill pastor? (you may need some items) ";
                            int decision = JOptionPane.showConfirmDialog(null, confirm, "Confirm Action", JOptionPane.OK_CANCEL_OPTION);
                            if (decision == JOptionPane.OK_OPTION) {

                            }
                        }
                        if (objectClicked.getText().equals("werewolf")) {
                            if (baseController.getEngine().getPlayer().getInventory().contains("silver bullet")) {
                                String confirm = "Do you want to give a silver bullet to the farmer?";
                                int decision = JOptionPane.showConfirmDialog(null, confirm, "Confirm Action", JOptionPane.OK_CANCEL_OPTION);
                                if (decision == JOptionPane.OK_OPTION) {
                                    String message = "You gave farmer the silver bullet, farmer loaded the silver bullet " +
                                            "\nand killed the werewolf. Then he gave you his musket for defeating the evil power";
                                    baseController.getEngine().getPlayer().dropItem("silver bullet");
                                    baseController.getEngine().getPlayer().addItemToinventory("musket");
                                    inventoryPanel.createItemInInventory();
                                    baseController.displayAttackMessage(message);
                                    objectClicked.setVisible(false);
                                } else {
                                    String message = "Farmer:\"I need find the silver bullet!!!!\"";
                                    baseController.displayAttackMessage(message);
                                }
                            } else {
                                String confirm = "Do you believe you are strong enough to kill werewolf? (you may need some items) ";
                                int decision = JOptionPane.showConfirmDialog(null, confirm, "Confirm Action", JOptionPane.OK_CANCEL_OPTION);
                                if (decision == JOptionPane.OK_OPTION) {

                                }
                            }
                        }
                    } else if (objectClicked.getText().equals("demo")) {
                        if (baseController.getEngine().getPlayer().getInventory().contains("blue stone")) {
                            String confirm = "In your bag, the blue stone start to shine, Do you want to use?";
                            int decision = JOptionPane.showConfirmDialog(null, confirm, "Confirm Action", JOptionPane.OK_CANCEL_OPTION);
                            if (decision == JOptionPane.OK_OPTION) {
                                String message = "The demo is killed by the stone " +
                                        "\nand killed the werewolf. Then he gave you his musket for defeating the evil power";
                                baseController.getEngine().getPlayer().dropItem("silver bullet");
                                inventoryPanel.createItemInInventory();
                                baseController.displayAttackMessage(message);
                                objectClicked.setVisible(false);
                            } else {
                                String message = "\"Can not make!!!!\"";
                                baseController.displayAttackMessage(message);
                            }
                        } else {
                            String confirm = "Do you believe you are strong enough to kill demo? (you may need some items) ";
                            int decision = JOptionPane.showConfirmDialog(null, confirm, "Confirm Action", JOptionPane.OK_CANCEL_OPTION);
                            if (decision == JOptionPane.OK_OPTION) {

                            }
                        }
                    }
                    break;
                case "search":
                    baseController.itemPanelControllerOn();
                    changeScreen.currentList(baseController.getEngine().getLocation().getCurrent());
                    break;
            }
        }
    }

    private class ObjectListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel objectClicked = (JLabel) e.getSource();
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
}