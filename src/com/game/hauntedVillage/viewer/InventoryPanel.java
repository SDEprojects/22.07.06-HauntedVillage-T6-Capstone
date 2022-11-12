package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

class InventoryPanel extends JPanel {
    private final GameManager baseController;
    private JPanel inventoryPanel;
    private ArrayList<String> itemList;
    private int itemCount;

    public InventoryPanel(GameManager baseController){
        this.baseController = baseController;
        inventoryPanel = new JPanel();
        setBounds(680, 700, 270, 200);
        setBackground(Color.white);
        Border innerBorder=BorderFactory.createTitledBorder("Inventory");
        Border outerBorder=BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
    }
    private JButton ImageCreate(String picName, int yPosition) {
        JButton itemIcon = new JButton();
        itemIcon.setLayout(null);
        itemIcon.setBounds(0, yPosition, 40, 40);
        ImageIcon bgImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(picName)));
        itemIcon.setIcon(bgImage);
//        itemIcon.addActionListener(new ItemActionListener());
        return itemIcon;
    }
    private void itemList(String itemName){



    }
    private void buildItemIcon(){

    }

}