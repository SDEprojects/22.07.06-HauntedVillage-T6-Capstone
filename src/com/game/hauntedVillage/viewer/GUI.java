package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;
import com.game.hauntedVillage.model.IntroStroy;
import com.game.hauntedVillage.model.NpcDialogue;
import com.game.hauntedVillage.model.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class GUI {
//    GameManager game;
//    Engine engine;
//    private Consumer<String> movementCallback;//Whenever the user clicks a button to move, gui code could invoke movementCallback.accept(direction)
//    JFrame window;
//    JTextArea messageArea;
//    JPanel [] bgPanel = new JPanel[12];
//    JLabel [] bgLabel = new JLabel[12];
//    IntroStroy intro = new IntroStroy();
//    NpcDialogue npc = new NpcDialogue();
//
//    public GUI( GameManager game){
//        this.game = game;
//
//
//      createGameWindow();
//      generateScene();
//
//
//
//      window.setVisible(true);
//    }
//
//    public void createGameWindow(){
//        // initialize window
//        window = new JFrame();
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setLayout(null);
//        window.setSize(1000, 800);
//        window.getContentPane().setBackground(Color.black);
//
//        // initialize message area
//        messageArea = new JTextArea();
//        messageArea.setBounds(50,600, 900,150);
//        messageArea.setMargin(new Insets(20,15,10,15));
//        messageArea.setBackground(Color.WHITE);
//        messageArea.setFont(new Font("Comic sans", Font.PLAIN, 20));
//        messageArea.setLineWrap(true);
//        messageArea.setEditable(false);
//        messageArea.setWrapStyleWord(true);
//
//        window.add(messageArea);
//    }
//
//    public void createBackground(int bgNum, String picName){
//        bgPanel[bgNum] = new JPanel();
//        bgPanel[bgNum].setBounds(50,50, 900, 500);
//        bgPanel[bgNum].setLayout(null);
//        bgPanel[bgNum].setBackground(null);
//        window.add(bgPanel[bgNum]);
//
//        bgLabel[bgNum] = new JLabel();
//        bgLabel[bgNum].setBounds(0,0, 900, 500);
//        bgLabel[bgNum].setLayout(null);
//
//        ImageIcon bgImage = new ImageIcon(getClass().getClassLoader().getResource(picName));
//        bgLabel[bgNum].setIcon(bgImage);
//
//
//    }
//    public void createArrow(int bgNum, String location, int arrowNum, int posX, int posY, String direction ){
//     ImageIcon arrow1 = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/east.png"));
//        ImageIcon arrow2 = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/north.png"));
//        ImageIcon arrow3 = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/south.png"));
//        ImageIcon arrow4 = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/west.png"));
//     JButton[] arrow = new JButton[4];
//     arrow[arrowNum] = new JButton();
//     arrow[arrowNum].setBackground(null);
//     if(direction.equals("east")){
//         arrow[arrowNum].setIcon(arrow1);
//         arrow[arrowNum].setBounds(posX, posY, 200,200 );
//     } else if(direction.equals("north")){
//         arrow[arrowNum].setIcon(arrow2);
//         arrow[arrowNum].setBounds(posX, posY, 200,200 );
//     }else if(direction.equals("south")) {
//         arrow[arrowNum].setIcon(arrow3);
//         arrow[arrowNum].setBounds(posX, posY, 200,200 );
//     }else{
//         arrow[arrowNum].setIcon(arrow4);
//         arrow[arrowNum].setBounds(posX, posY, 200,200 );
//     }
//
//     arrow[arrowNum].addActionListener(game.getCommand());
//     arrow[arrowNum].setContentAreaFilled(false);
//     arrow[arrowNum].setFocusPainted(false);
//     arrow[arrowNum].setBorderPainted(false);
//     arrow[arrowNum].setActionCommand(location);
//     bgPanel[bgNum].add(arrow[arrowNum]);
//
//
//    }
//
//    public void createObject(){
//        JLabel objectLabel = new JLabel();
//        objectLabel.setBounds(230, 100, 500, 300);
//
//        JPopupMenu popupMenu = new JPopupMenu();
//        JMenuItem[] menuItems = new JMenuItem[3];
//        menuItems[0] = new JMenuItem("look");
//        menuItems[0].addActionListener(game.getCommand());
//        menuItems[0].setActionCommand("look");
//        popupMenu.add(menuItems[0]);
//
//        menuItems[1] = new JMenuItem("search");
//        popupMenu.add(menuItems[1]);
//
//        menuItems[2] = new JMenuItem("rest");
//        popupMenu.add(menuItems[2]);
//
//        ImageIcon cottage = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/home_clickTest.png"));
//        objectLabel.setIcon(cottage);
//        bgPanel[1].add(objectLabel);
//        //bgPanel[1].add(bgLabel[1]);
//
//        objectLabel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                //super.mouseClicked(e);
//                messageArea.setText(intro.getIntro());
//                popupMenu.show(objectLabel, e.getX(), e.getY());
//
//
//            }
//        });
//    }
//
//    public void generateScene(){
//        createBackground(1, "Background_images/home.jpg");
//        createArrow(1, "church", 1, 750, 150, "east");
//        createObject();
//        bgPanel[1].add(bgLabel[1]);
//
//        createBackground(2, "Background_images/center_courtyard.jpg");
//        createArrow(2, "northern_square", 1, 350, -40, "north");
//        createArrow(2, "home", 2, -40, 150, "west" );
//        createArrow(2, "tavern", 1, 750, 150, "east");
//        createArrow(2, "southern_square", 1, 350, 350, "south");
//        bgPanel[2].add(bgLabel[2]);
//
//        createBackground(3, "Background_images/northern_square.jpg");
//        createArrow(3, "church", 1, 50, 50, "north");
//        bgPanel[3].add(bgLabel[3]);
//    }
//
//    public void update(){
//        // use the reference to engine to display the updated state
//    }
//
//    public void setMovementCallback(Consumer<String> movementCallback) {
//        this.movementCallback = movementCallback;
//    }
//
//
//    public JTextArea getMessageArea() {
//        return messageArea;
//    }
//
//    public void setMessageArea(JTextArea messageArea) {
//        this.messageArea = messageArea;
//    }
//
//    public JPanel[] getBgPanel() {
//        return bgPanel;
//    }
}