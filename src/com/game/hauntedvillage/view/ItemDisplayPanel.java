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
            case "farm":
                JButton shovel = ImageCreate("Icons/shovel.png", 0);
                shovel.setActionCommand("shovel");
                backGround.add(shovel);

                JButton musket = ImageCreate("Icons/musket.png", 50);
                musket.setActionCommand("musket");
                backGround.add(musket);
                break;
            case "town hall":
                JButton bullet = ImageCreate("Icons/silverBullet.png", 0);
                bullet.setActionCommand("silver bullet");
                backGround.add(bullet);
                break;
            case "tavern":
                JButton food = ImageCreate("Icons/food.png", 0);
                food.setActionCommand("feed");
                backGround.add(food);
                break;
            case "church":
                JButton amulet = ImageCreate("Icons/amulet.png", 0);
                amulet.setActionCommand("triangular amulet");
                backGround.add(amulet);
                break;
            case "well":
                JButton stone = ImageCreate("Icons/stone.png", 0);
                stone.setActionCommand("blue stone");
                backGround.add(stone);
                break;
            default:
//                throw new IllegalArgumentException();
        }
    }

    private ArrayList<JPanel> generateScene() {
        createBackground("home");
        createBackground("farm");
        createBackground("town hall");
        createBackground("tavern");
        createBackground("church");
        createBackground("well");
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
