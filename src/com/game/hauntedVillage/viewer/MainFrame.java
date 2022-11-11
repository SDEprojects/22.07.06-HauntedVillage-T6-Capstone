package com.game.hauntedVillage.viewer;

import com.game.hauntedVillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private MainPanel backgroundPanel;
    private TextPanel textPanel;
    private TopPanel topPanel;
    private SplashScreen splashScreen;
    private InventoryPanel inventoryPanel;
    private ItemDisplayPanel itemListPanel;

    public MainFrame(GameManager baseController) {
        backgroundPanel = new MainPanel(baseController, textPanel);
        textPanel = new TextPanel(baseController);
        splashScreen = new SplashScreen(baseController);
        topPanel = new TopPanel(baseController);
        inventoryPanel = new InventoryPanel(baseController);
        itemListPanel=new ItemDisplayPanel(baseController);
        setupFrame();
    }

    public void showGamePanel() {
        splashScreen.setVisible(false);
        backgroundPanel.setVisible(true);
        inventoryPanel.setVisible(true);
        topPanel.setVisible(true);
        textPanel.setVisible(true);
    }

    public void updateText(String text) {
        textPanel.setText(text);
    }

    private void setupFrame() {
        this.setTitle("Haunted Village");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000, 1000);
        getContentPane().setBackground(Color.black);
        add(splashScreen);
        // (1) Main panel for the map and mouse interaction
        add(backgroundPanel);
        backgroundPanel.setVisible(false);
        // (2) left bottom text display panel to display area description and speak content
        add(textPanel);
        textPanel.setVisible(false);
        // (3) right bottom inventory display and interact with item you have
        add(inventoryPanel);
        inventoryPanel.setVisible(false);
        // (4) top area to display the player health status, sound control,map, and help menu
        add(topPanel);
        topPanel.setVisible(false);
        // (5) ItemList Panel for display the item when you search the map
        add(itemListPanel);
        itemListPanel.setVisible(true);

        setJMenuBar(createMenuBar());
        this.setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        JMenuItem saving = new JMenuItem("Saving Game");
        JMenuItem loading = new JMenuItem("Loading Game");
        JMenuItem exitItem = new JMenuItem("Exit");

        gameMenu.add(saving);
        gameMenu.add(loading);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        menuBar.add(gameMenu);

        gameMenu.setMnemonic(KeyEvent.VK_F);

        exitItem.addActionListener(e -> {
            int decision = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure to quit the " +
                    "Game? (make sure you saving the game)", "Confirm Quit Game", JOptionPane.OK_CANCEL_OPTION);
            if (decision == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        });
        return menuBar;
        }
    }
