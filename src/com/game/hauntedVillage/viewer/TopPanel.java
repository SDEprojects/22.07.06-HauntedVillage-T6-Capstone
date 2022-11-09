package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TopPanel extends JPanel {

    private JPanel panel;
    private GameManager baseController;
    JLabel heartLabel;

    //generates top panel
    public TopPanel(GameManager baseController) {
        this.baseController = baseController;
       // generatePanel();
        createTopLabel();
    }

    //creates label
    public void createTopLabel() {

        panel = new JPanel();
        setBounds(50, 20, 900, 50);
        setLayout(null);
        setOpaque(true);
        setBackground(Color.PINK);
        panel.add(createIcons());
    }

    //create icons
    public JLabel createIcons() {
        heartLabel = new JLabel();
        heartLabel.setBounds(0,0, 20, 20);
        //ImageIcon heart = new ImageIcon(getClass().getClassLoader().getResource("cottage.png"));
       // heartLabel.setIcon(heart);
        System.out.println("called icon method");
        return heartLabel;
    }

    //compiles icons and label
    public void generatePanel() {
        createTopLabel();
        createIcons();
    }
}
