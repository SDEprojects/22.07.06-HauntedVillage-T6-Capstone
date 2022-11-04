package com.game.HauntedVillage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI {
    GameManager game;
    JFrame window;
    JTextArea messageArea;
    JPanel [] bgPanel = new JPanel[12];
    JLabel [] bgLabel = new JLabel[12];
    IntroStroy intro = new IntroStroy();
    NpcDialogue npc = new NpcDialogue();

    public GUI( GameManager game){
        this.game = game;

      createGameWindow();
      createBackground();
      createObject();

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
        messageArea.setBackground(Color.WHITE);
        messageArea.setFont(new Font("Comic sans", Font.PLAIN, 25));
        messageArea.setLineWrap(true);
        messageArea.setEditable(false);
        messageArea.setWrapStyleWord(true);

        window.add(messageArea);
    }

    public void createBackground(){
        bgPanel[1] = new JPanel();
        bgPanel[1].setBounds(50,50, 900, 500);
        bgPanel[1].setLayout(null);
        bgPanel[1].setBackground(null);
        window.add(bgPanel[1]);

        bgLabel[1] = new JLabel();
        bgLabel[1].setBounds(0,0, 900, 500);
        bgLabel[1].setLayout(null);

        ImageIcon bgImage = new ImageIcon(getClass().getClassLoader().getResource("evergreen.png"));
        bgLabel[1].setIcon(bgImage);


    }

    public void createObject(){
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(400, 280, 200, 200);

        ImageIcon cottage = new ImageIcon(getClass().getClassLoader().getResource("cottage.png"));
        objectLabel.setIcon(cottage);
        bgPanel[1].add(objectLabel);
        bgPanel[1].add(bgLabel[1]);

        objectLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);

                messageArea.setText(intro.getIntro());


            }
        });
    }


}