package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemDisplayPanel extends JPanel {

    private GameManager baseController;
    private final ArrayList<JPanel> bgPanel = new ArrayList<>();
    private final ArrayList<JLabel> bgLabel = new ArrayList<>();
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

    private ImageIcon loadingImageIcon(String file) {
        return new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(file)));
    }

    private void createBackground(String picName,String locationName) {
        JPanel backGround = new JPanel();
        backGround.setLayout(null);
        backGround.setBounds(50, 640, 900, 40);
        backGround.setBackground(null);
        bgPanel.add(backGround);
        add(backGround);

        JLabel backGroundLable = new JLabel();
        backGroundLable.setLayout(null);
        backGroundLable.setBounds(0, 0, 900, 40);
        bgLabel.add(backGroundLable);

        List<String> areaNameList = baseController.getEngine().location().allAreaNameList();
        itemListName=baseController.getEngine().location().getItems();


        ImageIcon bgImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(picName)));
        backGroundLable.setIcon(bgImage);
        backGround.add(backGroundLable);
    }
    private void createAllItemIcon(){
        ImageIcon Item1 = loadingImageIcon("Icons/cross.png");
        ImageIcon Item2 = loadingImageIcon("Icons/heart.png");
        ImageIcon Item3 = loadingImageIcon("Icons/knife.png");
        ImageIcon Item4 = loadingImageIcon("Icons/cross.png");
        ImageIcon Item5 = loadingImageIcon("Icons/cross.png");
        ImageIcon Item6 = loadingImageIcon("Icons/cross.png");
        ImageIcon Item7 = loadingImageIcon("Icons/cross.png");
        ImageIcon Item8 = loadingImageIcon("Icons/cross.png");
        ImageIcon Item9 = loadingImageIcon("Icons/cross.png");
        ImageIcon Item10 = loadingImageIcon("Icons/cross.png");

        List<String> directionList = baseController.getEngine().location().allAreaNameList();
        itemListName=baseController.getEngine().location().getItems();
//        for (int i=0;i<directionList.size();i++){
//
//        }
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
    }
    private void createMapIcons(String fileName, int xPostion) {
        JLabel itemLabel = new JLabel();
        itemLabel.setBounds(xPostion, 0, 44, 30);
        ImageIcon map = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)));
        itemLabel.setIcon(map);
        add(itemLabel);
    }
}
