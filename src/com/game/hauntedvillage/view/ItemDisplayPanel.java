package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

class ItemDisplayPanel extends JPanel {

    private final GameManager baseController;
    private final ArrayList<JPanel> itemPanel = new ArrayList<>();
    private final InventoryPanel inventoryPanel;

    //generates main Item display panel
    ItemDisplayPanel(GameManager baseController, InventoryPanel inventoryPanel) {
        this.baseController = baseController;
        this.inventoryPanel = inventoryPanel;
        setLayout(null);
        setBounds(50, 582, 900, 40);
        setBackground(null);
        generateScene();
    }

    private JButton ImageCreate(String picName, int xPosition) {
        JButton itemIcon = new JButton();
        itemIcon.setLayout(null);
        itemIcon.setBounds(xPosition, 0, 40, 40);
        ImageIcon bgImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(picName)));
        itemIcon.setIcon(bgImage);
        itemIcon.addActionListener(new ItemPickListener());
        return itemIcon;
    }

    // building the
    private void createBackground(String locationName) {
        JPanel backGround = new JPanel();
        backGround.setLayout(null);
        backGround.setBounds(0, 0, 900, 40);
        backGround.setBackground(null);
        itemPanel.add(backGround);
        add(backGround);

        switch (locationName) {
            case "home":
                JButton matches = ImageCreate("Icons/matches.png", 0);
                matches.setActionCommand("matches");
                backGround.add(matches);

                JButton crucifix = ImageCreate("Icons/cross.png", 50);
                crucifix.setActionCommand("crucifix");
                backGround.add(crucifix);

                JButton knife = ImageCreate("Icons/knife.png", 100);
                knife.setActionCommand("knife");
                backGround.add(knife);
                break;
            case "tavern":
                JButton food = ImageCreate("Icons/food.png", 0);
                food.setActionCommand("feed");
                backGround.add(food);
                break;
            default:
//                throw new IllegalArgumentException();
        }
    }

    private ArrayList<JPanel> generateScene() {
        createBackground("home");
        createBackground("tavern");
        return itemPanel;
    }

    ArrayList<JPanel> getItemPanel() {
        return itemPanel;
    }

    private class ItemPickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton text = (JButton) e.getSource();
            text.setVisible(false);
            String itemName = e.getActionCommand();
            baseController.getEngine().getPlayer().addItemToinventory(itemName);
            inventoryPanel.createItemInInventory();
        }
    }
}
