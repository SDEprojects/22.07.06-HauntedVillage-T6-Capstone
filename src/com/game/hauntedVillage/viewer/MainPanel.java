package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel {
    private final GameManager baseController;
    private final ScreenChanger changeScreen;
    private final ArrayList<JPanel> bgPanel = new ArrayList<>();
    private final ArrayList<JLabel> bgLabel = new ArrayList<>();
    private int locationOrder = 0;
    private String currentLocation;


    public MainPanel(GameManager baseController) {
        this.baseController = baseController;
        currentLocation=baseController.getEngine().location().getCurrent();
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

    public void createArrow(String direction) {
        ImageIcon arrow1 = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/arrowClick_50_east.png"));
        ImageIcon arrow2 = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/arrowClick_50_north.png"));
        ImageIcon arrow3 = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/arrowClick_50_south.png"));
        ImageIcon arrow4 = new ImageIcon(getClass().getClassLoader().getResource("Images_clickTriggers/arrowClick_50_west.png"));
        JButton[] arrow = new JButton[4];
        List<String> directionList=baseController.getEngine().location().directionList(currentLocation);
        for(int i=0;i<directionList.size();i++) {
            if (directionList.contains("north")) {
                arrow[0] = new JButton();
                arrow[0].setBackground(null);
                arrow[0].setBorderPainted(false);
                arrow[0].setIcon(arrow2);
                arrow[0].setBorder(null);
                arrow[0].setBounds(350, -40, 100, 100);
                arrow[0].addActionListener(new arrowListener());
                arrow[0].setActionCommand(direction);
                bgPanel.get(locationOrder).add(arrow[0]);
            }
            else if (directionList.contains("south")) {
                arrow[1] = new JButton();
                arrow[1].setBackground(null);
                arrow[1].setBorderPainted(false);
                arrow[1].setBorder(null);
                arrow[1].setIcon(arrow3);
                arrow[1].setBounds(350, 350, 100, 100);
                arrow[1].addActionListener(new arrowListener());
                arrow[1].setActionCommand(direction);
                bgPanel.get(locationOrder).add(arrow[1]);
            }
            else if (directionList.contains("west")) {
                arrow[2] = new JButton();
                arrow[2].setBackground(null);
                arrow[2].setBorderPainted(false);
                arrow[2].setBorder(null);
                arrow[2].setIcon(arrow4);
                arrow[2].setBounds(-40, 200, 100, 100);
                arrow[2].addActionListener(new arrowListener());
                arrow[2].setActionCommand(direction);
                bgPanel.get(locationOrder).add(arrow[2]);
            }
            else if (directionList.contains("east")) {
                arrow[3] = new JButton();
                arrow[3].setBackground(null);
                arrow[3].setBorderPainted(false);
                arrow[3].setBorder(null);
                arrow[3].setIcon(arrow1);
                arrow[3].setBounds(780, 200, 100, 100);
                arrow[3].addActionListener(new arrowListener());
                arrow[3].setActionCommand(direction);
                bgPanel.get(locationOrder).add(arrow[3]);
            }
        }
    }

    public ArrayList<JPanel> generateScene() {
        createBackground("Background_images/home.jpg");
        createArrow("church");
        createArrow("northern_square");
//        createObject();
        bgPanel.get(locationOrder).add(bgLabel.get(locationOrder));
        locationOrder++;


        createBackground("Background_images/church2.jpg");
        createArrow("northern_square");
        createArrow("home");
        bgPanel.get(locationOrder).add(bgLabel.get(locationOrder));
        locationOrder++;

        createBackground("Background_images/northern_square.jpg");
        createArrow("church");
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