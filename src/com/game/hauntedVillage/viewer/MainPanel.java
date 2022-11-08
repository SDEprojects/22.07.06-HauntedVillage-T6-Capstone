package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private GameManager baseController;
    private ScreenChanger changeScreen;
    private final ArrayList<JPanel> bgPanel = new ArrayList<>();
    private final ArrayList<JLabel> bgLabel = new ArrayList<>();
    int locationOrder = 0;
//    String roomName=baseController.getEngine().movement();

    public MainPanel(GameManager baseController) {
        this.baseController = baseController;
        generateScene();
        changeScreen = new ScreenChanger(getBgPanel());
    }

    public void createBackground(String picName) {
        JPanel backGround = new JPanel();
        backGround.setBounds(50, 50, 900, 500);
        backGround.setLayout(null);
        backGround.setBackground(null);
        bgPanel.add(backGround);
        add(bgPanel.get(locationOrder));

        JLabel backGroundLable = new JLabel();
        backGroundLable.setBounds(0, 0, 900, 500);
        backGroundLable.setLayout(null);
        bgLabel.add(backGroundLable);

        ImageIcon bgImage = new ImageIcon(getClass().getClassLoader().getResource(picName));
        bgLabel.get(locationOrder).setIcon(bgImage);
        bgPanel.get(locationOrder).add(bgLabel.get(locationOrder));
    }

    public void createArrow(String direction, int arrowNum, int posX, int posY) {
        ImageIcon arrow1 = new ImageIcon(getClass().getClassLoader().getResource("cottage.png"));
        JButton[] arrow = new JButton[4];
        arrow[arrowNum] = new JButton();
        arrow[arrowNum].setBounds(posX, posY, 200, 200);
        arrow[arrowNum].setBackground(null);
        arrow[arrowNum].setIcon(arrow1);
        arrow[arrowNum].addActionListener(new arrowListener());
        arrow[arrowNum].setActionCommand(direction);
        bgPanel.get(locationOrder).add(arrow[arrowNum]);
    }

    public ArrayList<JPanel> generateScene() {
        createBackground("Background_images/home.jpg");
        createArrow("church", 1, 50, 50);
        createArrow("northern_square", 2, 450, 50);
//        createObject();
        bgPanel.get(locationOrder).add(bgLabel.get(locationOrder));
        locationOrder++;


        createBackground("Background_images/church2.jpg");
        createArrow("northern_square", 1, 50, 50);
        createArrow("home", 2, 450, 50);
        bgPanel.get(locationOrder).add(bgLabel.get(locationOrder));
        locationOrder++;

        createBackground("Background_images/northern_square.jpg");
        createArrow("church", 1, 50, 50);
        bgPanel.get(locationOrder).add(bgLabel.get(locationOrder));
        locationOrder++;
        return bgPanel;
    }

    public ArrayList<JPanel> getBgPanel() {
        return bgPanel;
    }

    class arrowListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            System.out.println(command);
            if (command.equals("church")) {
                changeScreen.showScreen2();
            } else if (command.equals("northern_square")) {
                changeScreen.showScreen3();
            } else if (command.equals("home")) {
                changeScreen.showScreen1();
            } else {
                System.out.println("you need more arrows");
            }
        }
    }
}