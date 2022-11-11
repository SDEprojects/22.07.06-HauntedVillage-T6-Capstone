package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

class TextPanel extends JTextArea{

    private JTextArea messageArea;
    private GameManager baseController;


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
        setFont((getfont(Font.TRUETYPE_FONT,15)));
    }

    public Font getfont(int style,int size){
        InputStream is=this.getClass().getClassLoader().getResourceAsStream("ReadingFile/Blood.ttf");
        Font action=null;
        try{
            action=Font.createFont(Font.TRUETYPE_FONT,is);
        }catch (FontFormatException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        Font newFront=action.deriveFont(style,size);
        return newFront;
    }
}
