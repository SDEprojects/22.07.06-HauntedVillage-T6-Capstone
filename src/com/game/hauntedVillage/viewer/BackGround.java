package com.game.hauntedVillage.viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

class BackGround extends JPanel {
//    private final ArrayList<JPanel> bgPanel=new ArrayList<>();
//    private final ArrayList<JLabel> bgLabel=new ArrayList<>();
    JPanel [] bgPanel = new JPanel[12];
    JLabel [] bgLabel = new JLabel[12];
    GameManager game;

//    BackGround(GameManager game){
//        this.game=game;
//    }
//
//    public void createBackground(int bgNum, String picName){
//        bgPanel[bgNum] = new JPanel();
//        bgPanel[bgNum].setBounds(50,50, 900, 500);
//        bgPanel[bgNum].setLayout(null);
//        bgPanel[bgNum].setBackground(null);
//
//        bgLabel[bgNum] = new JLabel();
//        bgLabel[bgNum].setBounds(0,0, 900, 500);
//        bgLabel[bgNum].setLayout(null);
//
//        ImageIcon bgImage = new ImageIcon(getClass().getClassLoader().getResource(picName));
//        bgLabel[bgNum].setIcon(bgImage);
//    }
//
//    public void createArrow(int bgNum, String direction, int arrowNum, int posX, int posY){
//        ImageIcon arrow1 = new ImageIcon(getClass().getClassLoader().getResource("cottage.png"));
//        JButton[] arrow = new JButton[4];
//        arrow[arrowNum] = new JButton();
//        arrow[arrowNum].setBounds(posX, posY, 200,200 );
//        arrow[arrowNum].setBackground(null);
//        arrow[arrowNum].setIcon(arrow1);
//        arrow[arrowNum].addActionListener(game.getCommand());
//        arrow[arrowNum].setActionCommand(direction);
//        bgPanel[bgNum].add(arrow[arrowNum]);
//    }
//    public void generateScene(){
//        createBackground(1, "Background_images/home.jpg");
//        createArrow(1, "church", 1, 50, 50);
//        createArrow(1, "northern_square", 2, 450, 50 );
//        bgPanel[1].add(bgLabel[1]);
//
//        createBackground(2, "Background_images/church2.jpg");
//        createArrow(2, "northern_square", 1, 50, 50);
//        createArrow(2, "home", 2, 450, 50 );
//        bgPanel[2].add(bgLabel[2]);
//
//        createBackground(3, "Background_images/northern_square.jpg");
//        createArrow(3, "church", 1, 50, 50);
//        bgPanel[3].add(bgLabel[3]);
//    }

//    void createBackGroundArray(String backGroundImage){
//        JPanel backGround=new JPanel();
//        backGround.setBounds(50,50, 900, 500);
//        backGround.setLayout(null);
//        backGround.setBackground(null);
//        backGround.setBackground(Color.white);
//        bgPanel.add(backGround);
//        add(bgPanel.get(graphNumber));
//
//        JLabel backGroundLable=new JLabel();
//        backGroundLable.setBounds(0,0, 900, 500);
//        backGroundLable.setLayout(null);
//        bgLabel.add(backGroundLable);
//        ImageIcon bgImage = new ImageIcon(getClass().getClassLoader().getResource(backGroundImage));
//        bgLabel.get(graphNumber).setIcon(bgImage);
//        bgPanel.get(graphNumber).add(bgLabel.get(graphNumber));
//    }
//
//    void createArrow(String direction, int arrowNum, int posX, int posY){
//        ImageIcon arrow1 = new ImageIcon(getClass().getClassLoader().getResource("cottage.png"));
//        JButton[] arrow = new JButton[4];
//        arrow[arrowNum] = new JButton();
//        arrow[arrowNum].setBounds(posX, posY, 200,200 );
//        arrow[arrowNum].setBackground(null);
//        arrow[arrowNum].setIcon(arrow1);
//        arrow[arrowNum].addActionListener(game.getCommand());
//        arrow[arrowNum].setActionCommand(direction);
//        bgPanel.get(graphNumber).add(arrow[arrowNum]);
//    }
//
//    ArrayList<JPanel> generateScene(){
//        createBackGroundArray( "Background_images/home.jpg");
//        createArrow("church", 1, 50, 50);
//        createArrow("northern_square", 2, 450, 50 );
////        createObject();
//        bgPanel.get(graphNumber).add(bgLabel.get(graphNumber));
//        graphNumber++;
//
//        createBackGroundArray( "Background_images/church2.jpg");
//        createArrow("northern_square", 1, 50, 50);
//        createArrow("home", 2, 450, 50 );
//        bgPanel.get(graphNumber).add(bgLabel.get(graphNumber));
//        graphNumber++;
//
//        createBackGroundArray("Background_images/northern_square.jpg");
//        createArrow("church", 1, 50, 50);
//        bgPanel.get(graphNumber).add(bgLabel.get(graphNumber));
//        graphNumber++;
//        return bgPanel;
//    }
//
//    public void createObject(){
//        JLabel objectLabel = new JLabel();
//        objectLabel.setBounds(400, 280, 200, 200);
//
//        JPopupMenu popupMenu = new JPopupMenu();
//        JMenuItem[] menuItems = new JMenuItem[3];
//        menuItems[0] = new JMenuItem("look");
////        menuItems[0].addActionListener(game.getCommand());
//        menuItems[0].setActionCommand("look");
//        popupMenu.add(menuItems[0]);
//
//        menuItems[1] = new JMenuItem("search");
//        popupMenu.add(menuItems[1]);
//
//        menuItems[2] = new JMenuItem("rest");
//        popupMenu.add(menuItems[2]);
//
//        ImageIcon cottage = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/home_clickEvent.png"));
//        objectLabel.setIcon(cottage);
//        bgPanel.get(0).add(objectLabel);
//        //bgPanel[1].add(bgLabel[1]);
//
//        objectLabel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                //super.mouseClicked(e);
////                messageArea.setText(intro.getIntro());
//                popupMenu.show(objectLabel, e.getX(), e.getY());
//            }
//        });
//    }
//public JPanel[] getBgPanel() {
//    return bgPanel;
//}
}
