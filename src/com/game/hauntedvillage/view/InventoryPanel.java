package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

class InventoryPanel extends JPanel {
    private final GameManager baseController;
    private boolean combine = false;

    InventoryPanel(GameManager baseController) {
        this.baseController = baseController;
        setBounds(680, 628, 270, 240);
        setBackground(Color.BLACK);
        Border abc = BorderFactory.createDashedBorder(Color.ORANGE, 10, 25);
        TitledBorder titleBorder = new TitledBorder(abc, "Inventory", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        titleBorder.setTitleColor(Color.RED);
        titleBorder.setTitleFont(new Font("Libian SC", Font.PLAIN, 24));
        setBorder(titleBorder);
    }

    private JButton ImageCreate(String picName, int xPosition, int yPosition) {
        JButton itemIcon = new JButton();
        itemIcon.setLayout(null);
        itemIcon.setBounds(xPosition, yPosition, xPosition, yPosition);
        ImageIcon bgImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(picName)));
        itemIcon.setIcon(bgImage);
        itemIcon.addActionListener(new ItemActionListener());
        add(itemIcon);
        return itemIcon;
    }

    void createItemInInventory() {
        removeAll();
        repaint();
        ArrayList<String> itemList = baseController.getEngine().getPlayer().getInventory();
        for (int i = 0; i < itemList.size(); i++) {
            if (i < 4) {
                itemImage(itemList.get(i), i, 0);
            } else if (i < 7) {
                itemImage(itemList.get(i), i - 4, 50);
            } else {
                itemImage(itemList.get(i), i - 7, 50);
            }
        }
        updateUI();
    }

    private void createPopupMenu(String itemName) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem inspect = new JMenuItem("inspect");
        inspect.addActionListener(new InspectListener());
        inspect.setActionCommand(itemName);
        popupMenu.add(inspect);

        JMenuItem use = new JMenuItem("use");
        use.addActionListener(new UseListener());
        use.setActionCommand(itemName);
        popupMenu.add(use);
        popupMenu.show(this, 100, 100);
    }

    private void itemImage(String itemName, int xPosition, int yPosition) {
        switch (itemName) {
            case "matches":
                JButton matches = ImageCreate("Icons/matches.png", xPosition, yPosition);
                matches.setActionCommand("matches");
                add(matches);
                break;
            case "crucifix":
                JButton crucifix = ImageCreate("Icons/cross.png", xPosition, yPosition);
                crucifix.setActionCommand("crucifix");
                add(crucifix);
                break;
            case "knife":
                JButton knife = ImageCreate("Icons/knife.png", xPosition, yPosition);
                knife.setActionCommand("knife");
                add(knife);
                break;
            case "shovel":
                JButton shovel = ImageCreate("Icons/shovel.png", xPosition, yPosition);
                shovel.setActionCommand("shovel");
                add(shovel);
                break;
            case "musket":
                JButton musket = ImageCreate("Icons/musket.png", xPosition, yPosition);
                musket.setActionCommand("musket");
                add(musket);
                break;
            case "silver bullet":
                JButton bullet = ImageCreate("Icons/silverBullet.png", xPosition, yPosition);
                bullet.setActionCommand("silver bullet");
                add(bullet);
                break;
            case "feed":
                JButton food = ImageCreate("Icons/food.png", xPosition, yPosition);
                food.setActionCommand("feed");
                add(food);
                break;
            case "triangular amulet":
                JButton amulet = ImageCreate("Icons/amulet.png", xPosition, yPosition);
                amulet.setActionCommand("triangular amulet");
                add(amulet);
                break;
            case "blue stone":
                JButton stone = ImageCreate("Icons/stone.png", xPosition, yPosition);
                stone.setActionCommand("blue stone");
                add(stone);
                break;
            default:
//                throw new IllegalArgumentException();
        }

    }

    private class ItemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            createPopupMenu(e.getActionCommand());
        }
    }

    private class InspectListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            baseController.displayItemInformation(e.getActionCommand());
        }
    }

    private class UseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = e.getActionCommand();
            String location = baseController.getEngine().getLocation().getCurrent();
            switch (itemName) {
                case "matches":
                    if (location.equals("church")) {
                        String message = "You light the Candle by using your matches, now you can use crucifix to pray.";
                        combine = true;
                        information(message, "Message");
                        baseController.getEngine().getPlayer().dropItem(itemName);
                        createItemInInventory();
                    } else if (location.equals("church")) {
                        String message = "You must find some item to defeat the pastor in order to light the candle";
                        information(message, "Warning");
                    } else {
                        String message = "You can not use " + itemName + " at here!!";
                        information(message, "Warning");
                    }
                    break;
                case "crucifix":
                    if (location.equals("church") && combine) {
                        baseController.getEngine().getPlayer().addItemToinventory("triangular amulet");
                        createItemInInventory();
                        String message = "After you pray to the God, you receive a holy triangular amulet";
                        information(message, "Congratulation");
                        baseController.getEngine().getPlayer().dropItem(itemName);
                        createItemInInventory();
                        combine = false;
                    } else {
                        String message = "Nothing happen after you use " + itemName + "! Maybe you can try different area or combine with other items";
                        information(message, "Warning");
                    }
                    break;
                case "triangular amulet":
                    if (location.equals("well")) {
                        baseController.getEngine().getPlayer().addItemToinventory("blue stone");
                        String message = "After you put the amulet to the stone, you receive a mysterious blue stone";
                        baseController.getEngine().getPlayer().dropItem(itemName);
                        createItemInInventory();
                        information(message, "Congratulation");
                    } else {
                        String message = "Nothing happen after you use " + itemName + "!";
                        information(message, "Warning");
                    }
                    break;
                case "blue stone":
                    if (location.equals("woods")) {
                        baseController.getEngine().getPlayer().dropItem(itemName);
                        createItemInInventory();
                    } else {
                        String message = "Nothing happen after you use " + itemName + "!";
                        information(message, "Warning");
                    }
                    break;
                case "feed":
                    if (location.equals("town hall")) {
                        baseController.getEngine().getPlayer().addItemToinventory("silver bullet");
                        String message = "the crows fly away from the ammo box because the food , you successfully" +
                                "open the ammo box and get a silver bullet and some regular ammo!";
                        information(message, "Congratulation");
                        baseController.getEngine().getPlayer().dropItem(itemName);
                        createItemInInventory();
                    } else {
                        String message = "Nothing happen after you use " + itemName + "!";
                        information(message, "Warning");
                    }
                    break;
                case "musket":
                    if (location.equals("church")) {
                        int decision = JOptionPane.showConfirmDialog(null, "Are you sure to use musket " +
                                "to kill \"pastor\"?", "Warning", JOptionPane.OK_CANCEL_OPTION);
                        if (decision == JOptionPane.OK_OPTION) {
                            baseController.getEngine().getPlayer().dropItem(itemName);
                            String message = "You killed the pastor, now you can enter to the church";
                            information(message, "Warning");
                            createItemInInventory();
                        }
                    } else {
                        String message = "Nothing happen after you use " + itemName + "!";
                        information(message, "Warning");
                    }
                    break;
                case "silver bullet":
                    if (location.equals("farm")) {
                        baseController.getEngine().getPlayer().dropItem(itemName);
                        String message = "You gave farmer the silver bullet, farmer loaded the silver bullet and killed the " +
                                "werewolf. Then he gave you his musket for defeating the evil power";
                        information(message, "Congratulation");
                        baseController.getEngine().getPlayer().addItemToinventory("musket");
                        createItemInInventory();
                    } else {
                        String message = "Nothing happen after you use " + itemName + "!";
                        information(message, "Warning");
                    }
                default:
            }
        }

        private void information(String message, String title) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
        }
    }

}