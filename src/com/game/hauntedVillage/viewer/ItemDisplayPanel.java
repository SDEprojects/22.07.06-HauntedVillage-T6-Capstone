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
        setBackground(null);
        generateScene();
    }

    private JLabel ImageCreate(String picName,int xPosition){
        JLabel backGroundLable = new JLabel();
        backGroundLable.setLayout(null);
        backGroundLable.setBounds(xPosition, 0, 40, 40);
        ImageIcon bgImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(picName)));
        backGroundLable.setIcon(bgImage);
        bgLabel.add(backGroundLable);
        return backGroundLable;
    }

    private void createBackground(String picName,String locationName) {
        JPanel backGround = new JPanel();
        backGround.setLayout(null);
        backGround.setBounds(0, 0, 900, 40);
        backGround.setBackground(null);
        bgPanel.add(backGround);
        add(backGround);
        List<String> areaNameList = baseController.getEngine().location().allAreaNameList();
        itemListName=baseController.getEngine().location().getItems();

        switch(locationName){
            case "home":
                JLabel matches= ImageCreate(picName,0);
                backGround.add(matches);
                JLabel crucifix= ImageCreate(picName,50);
                backGround.add(crucifix);
                JLabel knife= ImageCreate(picName,100);
                backGround.add(knife);
                System.out.println("1");
                break;
            case "farm":
                JLabel shovel= ImageCreate(picName,0);
                backGround.add(shovel);
                JLabel musket= ImageCreate(picName,50);
                backGround.add(musket);
                break;
            case "town hall":
                JLabel bullet= ImageCreate(picName,0);
                backGround.add(bullet);
                break;
            case "tavern":
                JLabel food= ImageCreate(picName,0);
                backGround.add(food);
                break;
            case "church":
                JLabel amulet= ImageCreate(picName,0);
                backGround.add(amulet);
                break;
            case "well":
                JLabel stone= ImageCreate(picName,0);
                backGround.add(stone);
                break;
            default:
                System.out.println("works");
        }
    }
    public ArrayList<JPanel> generateScene() {
        createBackground("Icons/heart.png", "home");
        createBackground("Icons/heart.png", "center courtyard");
        createBackground("Icons/heart.png", "northern square");
        createBackground("Icons/cross.png", "southern square");
        createBackground("Icons/heart.png", "farm");
        createBackground("Icons/heart.png", "town hall");
        createBackground("Icons/heart.png", "tavern");
        createBackground("Icons/heart.png", "church");
        createBackground("Icons/heart.png", "well");
        createBackground("Icons/heart.png", "woods");
        return bgPanel;
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
    public ArrayList<JPanel> getBgPanel() {
        return bgPanel;
    }
}
