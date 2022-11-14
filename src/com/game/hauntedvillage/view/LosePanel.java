package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;
import com.game.hauntedvillage.utility.FontStyle;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class LosePanel extends JPanel{

    private final GameManager baseController;

    public LosePanel(GameManager baseController) {
        this.baseController = baseController;
        JLabel imageLabel = new JLabel("You Lost");
        imageLabel.setBounds(0, 0, 900, 900);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
        imageLabel.setVerticalTextPosition(JLabel.CENTER);
        FontStyle font = new FontStyle();
        Font textFont = (font.getfont(Font.TRUETYPE_FONT, 56, "ReadingFile/Blood.ttf"));
        imageLabel.setFont(textFont);
        imageLabel.setForeground(Color.red);
        ImageIcon backGroundImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("BackgroundImages/church.jpg")));
        imageLabel.setIcon(backGroundImage);
        add(imageLabel);
        setBounds(50, 50, 900, 900);
        setBackground(Color.black);
        setLayout(null);
    }
}