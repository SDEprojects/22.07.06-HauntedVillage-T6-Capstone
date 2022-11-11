package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class InventoryPanel extends JPanel {
    private final GameManager baseController;
    private JPanel inventoryPanel;

    public InventoryPanel(GameManager baseController){
        this.baseController = baseController;

        inventoryPanel = new JPanel();
        setBounds(680, 700, 270, 200);
        setBackground(Color.white);
        Border innerBorder=BorderFactory.createTitledBorder("Inventory");
        Border outerBorder=BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));


    }
}