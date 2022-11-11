package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemDisplayPanel extends JPanel {

    private GameManager baseController;
    private final ArrayList<JPanel> itemPanel = new ArrayList<>();
    private final ArrayList<JLabel> itemLabel = new ArrayList<>();
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

    private JLabel ImageCreate(String picName, int xPosition) {
        JLabel backGroundLable = new JLabel();
        backGroundLable.setLayout(null);
        backGroundLable.setBounds(xPosition, 0, 40, 40);
        ImageIcon bgImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(picName)));
        backGroundLable.setIcon(bgImage);
        itemLabel.add(backGroundLable);
        return backGroundLable;
    }

    private void createBackground(String picName, String locationName) {
        JPanel backGround = new JPanel();
        backGround.setLayout(null);
        backGround.setBounds(0, 0, 900, 40);
        backGround.setBackground(null);
        itemPanel.add(backGround);
        add(backGround);
        itemListName = baseController.getEngine().location().getItems();
        switch (locationName) {
            case "home":
                JLabel matches = ImageCreate(picName, 0);
                backGround.add(matches);
                JLabel crucifix = ImageCreate(picName, 50);
                backGround.add(crucifix);
                JLabel knife = ImageCreate(picName, 100);
                backGround.add(knife);
                break;
            case "farm":
                JLabel shovel = ImageCreate(picName, 0);
                backGround.add(shovel);
                JLabel musket = ImageCreate(picName, 50);
                backGround.add(musket);
                break;
            case "town hall":
                JLabel bullet = ImageCreate(picName, 0);
                backGround.add(bullet);
                break;
            case "tavern":
                JLabel food = ImageCreate(picName, 0);
                backGround.add(food);
                break;
            case "church":
                JLabel amulet = ImageCreate(picName, 0);
                backGround.add(amulet);
                break;
            case "well":
                JLabel stone = ImageCreate(picName, 0);
                backGround.add(stone);
                break;
            default:
//                throw new IllegalArgumentException();
        }
    }

    public ArrayList<JPanel> generateScene() {
        createBackground("Icons/heart.png", "home");
        createBackground("Icons/heart.png", "farm");
        createBackground("Icons/heart.png", "town hall");
        createBackground("Icons/heart.png", "tavern");
        createBackground("Icons/heart.png", "church");
        createBackground("Icons/heart.png", "well");
        return itemPanel;
    }

    public ArrayList<JPanel> getItemPanel() {
        return itemPanel;
    }
}
