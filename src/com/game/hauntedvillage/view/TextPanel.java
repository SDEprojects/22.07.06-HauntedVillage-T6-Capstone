package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;
import com.game.hauntedvillage.utility.FontStyle;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.*;

class TextPanel extends JTextArea {

    public TextPanel(GameManager baseController) {
        setBounds(50, 628, 600, 240);
        setMargin(new Insets(20, 15, 10, 15));
        setBackground(Color.BLACK);
        setLineWrap(true);
        setEditable(false);
        setWrapStyleWord(true);
        setForeground(Color.RED);
        FontStyle font = new FontStyle();
        Font textFont = (font.getfont(Font.TRUETYPE_FONT, 18, "ReadingFile/Blood.ttf"));
        Border abc = BorderFactory.createDashedBorder(Color.ORANGE, 10, 25);
        TitledBorder titleBorder = new TitledBorder(abc, "Description", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION);
        titleBorder.setTitleColor(Color.RED);
        titleBorder.setTitleFont(new Font("Libian TC", Font.BOLD, 24));
        setBorder(titleBorder);
        setText(baseController.getEngine().getLocation().getCurrentRoom().getDescription());
        setFont(textFont);
    }


}
