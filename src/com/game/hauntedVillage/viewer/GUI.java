package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.model.IntroStroy;
import com.game.hauntedVillage.model.NpcDialogue;
import com.game.hauntedVillage.controller.Engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

public class GUI {
    GameManager game;
    Engine engine;
    private Consumer<String> movementCallback;//Whenever the user clicks a button to move, gui code could invoke movementCallback.accept(direction)
    JFrame window;
    JTextArea messageArea;
    JPanel [] bgPanel = new JPanel[12];
    JLabel [] bgLabel = new JLabel[12];
    IntroStroy intro = new IntroStroy();
    NpcDialogue npc = new NpcDialogue();

    public GUI( GameManager game){
        this.game = game;


      createGameWindow();

      generateScene();



      window.setVisible(true);
    }

    public void createGameWindow(){
        // initialize window
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setSize(1000, 800);
        window.getContentPane().setBackground(Color.black);

        // initialize message area
        messageArea = new JTextArea();
        messageArea.setBounds(50,600, 900,150);
        messageArea.setMargin(new Insets(20,15,10,15));
        messageArea.setBackground(Color.WHITE);
        messageArea.setFont(new Font("Comic sans", Font.PLAIN, 20));
        messageArea.setLineWrap(true);
        messageArea.setEditable(false);
        messageArea.setWrapStyleWord(true);

        window.add(messageArea);
    }

    public void createBackground(int bgNum, String picName){
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(50,50, 900, 500);
        bgPanel[bgNum].setLayout(null);
        bgPanel[bgNum].setBackground(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0,0, 900, 500);
        bgLabel[bgNum].setLayout(null);

        ImageIcon bgImage = new ImageIcon(getClass().getClassLoader().getResource(picName));
        bgLabel[bgNum].setIcon(bgImage);


    }
    public void createArrow(int bgNum, String direction, int arrowNum, int posX, int posY){
     ImageIcon arrow1 = new ImageIcon(getClass().getClassLoader().getResource("cottage.png"));
     JButton[] arrow = new JButton[4];
     arrow[arrowNum] = new JButton();
     arrow[arrowNum].setBounds(posX, posY, 200,200 );
     arrow[arrowNum].setBackground(null);
     arrow[arrowNum].setIcon(arrow1);
     arrow[arrowNum].addActionListener(game.getCommand());
     arrow[arrowNum].setActionCommand(direction);
     bgPanel[bgNum].add(arrow[arrowNum]);


    }

    public void createObject(){
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(400, 280, 200, 200);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem[] menuItems = new JMenuItem[3];
        menuItems[0] = new JMenuItem("look");
        menuItems[0].addActionListener(game.getCommand());
        menuItems[0].setActionCommand("look");
        popupMenu.add(menuItems[0]);

        menuItems[1] = new JMenuItem("search");
        popupMenu.add(menuItems[1]);

        menuItems[2] = new JMenuItem("rest");
        popupMenu.add(menuItems[2]);

        ImageIcon cottage = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/home_clickEvent.png"));
        objectLabel.setIcon(cottage);
        bgPanel[1].add(objectLabel);
        //bgPanel[1].add(bgLabel[1]);

        objectLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                messageArea.setText(intro.getIntro());
                popupMenu.show(objectLabel, e.getX(), e.getY());


            }
        });
    }

    public void generateScene(){
        createBackground(1, "Background_images/home.jpg");
        createArrow(1, "church", 1, 50, 50);
        createArrow(1, "northern_square", 2, 450, 50 );
        createObject();
        bgPanel[1].add(bgLabel[1]);

        createBackground(2, "Background_images/church2.jpg");
        createArrow(2, "northern_square", 1, 50, 50);
        createArrow(2, "home", 2, 450, 50 );
        bgPanel[2].add(bgLabel[2]);

        createBackground(3, "Background_images/northern_square.jpg");
        createArrow(3, "church", 1, 50, 50);
        bgPanel[3].add(bgLabel[3]);
    }

    public void update(){
        // use the reference to engine to display the updated state
    }

    public void setMovementCallback(Consumer<String> movementCallback) {
        this.movementCallback = movementCallback;
    }


    public JTextArea getMessageArea() {
        return messageArea;
    }

    public void setMessageArea(JTextArea messageArea) {
        this.messageArea = messageArea;
    }

    public JPanel[] getBgPanel() {
        return bgPanel;
    }
}