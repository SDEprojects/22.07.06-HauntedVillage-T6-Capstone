package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemDisplayPanel extends JPanel {

    private GameManager baseController;
    private final ArrayList<JPanel> itemPanel = new ArrayList<>();

    //generates main Item display panel
    public ItemDisplayPanel(GameManager baseController) {
        this.baseController = baseController;
        setLayout(null);
        setBounds(50, 640, 900, 40);
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
                JButton matches = ImageCreate("Icons/knife.png", 0);
                matches.setActionCommand("matches");
                backGround.add(matches);

                JButton crucifix = ImageCreate("Icons/heart.png", 50);
                crucifix.setActionCommand("crucifix");
                backGround.add(crucifix);

                JButton knife = ImageCreate("Icons/cross.png", 100);
                knife.setActionCommand("knife");
                backGround.add(knife);
                break;
            case "farm":
                JButton shovel = ImageCreate("Icons/heart.png", 0);
                shovel.setActionCommand("shovel");
                backGround.add(shovel);

                JButton musket = ImageCreate("Icons/heart.png", 50);
                musket.setActionCommand("musket");
                backGround.add(musket);
                break;
            case "town hall":
                JButton bullet = ImageCreate("Icons/heart.png", 0);
                bullet.setActionCommand("silver bullet");
                backGround.add(bullet);
                break;
            case "tavern":
                JButton food = ImageCreate("Icons/heart.png", 0);
                food.setActionCommand("feed");
                backGround.add(food);
                break;
            case "church":
                JButton amulet = ImageCreate("Icons/heart.png", 0);
                amulet.setActionCommand("triangular amulet");
                backGround.add(amulet);
                break;
            case "well":
                JButton stone = ImageCreate("Icons/heart.png", 0);
                stone.setActionCommand("blue stone");
                backGround.add(stone);
                break;
            default:
//                throw new IllegalArgumentException();
        }
    }

    public ArrayList<JPanel> generateScene() {
        createBackground( "home");
        createBackground("farm");
        createBackground( "town hall");
        createBackground("tavern");
        createBackground("church");
        createBackground( "well");
        return itemPanel;
    }

    public ArrayList<JPanel> getItemPanel() {
        return itemPanel;
    }

    private class ItemPickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton text = (JButton ) e.getSource();
            text.setVisible(false);
            String itemName=e.getActionCommand();
            baseController.getEngine().getPlayer().addItemToinventory(itemName);
        }
    }
}
