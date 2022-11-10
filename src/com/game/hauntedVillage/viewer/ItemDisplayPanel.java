package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class ItemDisplayPanel extends JPanel {

    private GameManager baseController;
    private ArrayList<JLabel> itemListInMap;
    private ArrayList<String> itemListName;

    //generates main Item display panel
    public ItemDisplayPanel(GameManager baseController) {
        this.baseController = baseController;
        setLayout(null);
        setBounds(50, 640, 900, 40);
        setBackground(Color.white);
        setOpaque(true);
    }
    // build the individual item panel for each map
    public void singleItemDisplayPanel() {
        setLayout(null);
        setBounds(50, 640, 900, 40);
        setBackground(Color.white);
        setOpaque(true);
    }

    private void itemList(){
        itemListName=baseController.getEngine().location().getItems();
        for(int i=0;i<itemListName.size();i++){

        }
    }
    private void createMapIcons(String fileName, int xPostion) {
        JLabel itemLabel = new JLabel();
        itemLabel.setBounds(xPostion, 0, 44, 30);
        ImageIcon map = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
        itemLabel.setIcon(map);
        add(itemLabel);
    }
}
