package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class TextPanel extends JTextArea{

    private JTextArea messageArea;
    private GameManager baseController;
    private FontStyle font = new FontStyle();


    public TextPanel(GameManager baseController){
        messageArea=new JTextArea();
        setBounds(50,700,600,200);
        setMargin(new Insets(20,15,10,15));
        setBackground(Color.white);
        setLineWrap(true);
        setEditable(false);
        setWrapStyleWord(true);
        setForeground(Color.RED);

        Border innerBorder=BorderFactory.createTitledBorder("Description");
        Border outerBorder=BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
        setText(baseController.getEngine().location().getCurrentRoom().getDescription());
        setFont((font.getfont(Font.TRUETYPE_FONT,15,"ReadingFile/Blood.ttf")));
    }


}
