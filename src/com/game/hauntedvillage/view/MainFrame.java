package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private final MainPanel backgroundPanel;
    private final TextPanel textPanel;
    private final TopPanel topPanel;
    private final SplashScreen splashScreen;
    private final InventoryPanel inventoryPanel;
    private final ItemDisplayPanel itemListPanel;
    private final WinPanel winPanel;
    private final LosePanel losePanel;

    public MainFrame(GameManager baseController) {
        textPanel = new TextPanel(baseController);
        splashScreen = new SplashScreen(baseController);
        topPanel = new TopPanel(baseController);
        inventoryPanel = new InventoryPanel(baseController);
        itemListPanel = new ItemDisplayPanel(baseController, inventoryPanel);
        backgroundPanel = new MainPanel(baseController, textPanel, itemListPanel);
        winPanel = new WinPanel(baseController);
        losePanel = new LosePanel(baseController);
        setupFrame();
    }


    public void showGamePanel() {
        splashScreen.setVisible(false);
        backgroundPanel.setVisible(true);
        inventoryPanel.setVisible(true);
        topPanel.setVisible(true);
        textPanel.setVisible(true);

    }

    public void endGamePanels(boolean win){
        backgroundPanel.setVisible(false);
        inventoryPanel.setVisible(false);
        topPanel.setVisible(false);
        textPanel.setVisible(false);

        if(win == true){
            winPanel.setVisible(true);
        }else{
           losePanel.setVisible(true);
        }

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
        itemListPanel.setVisible(false);

        //(6) Win Panel to display if user wins the game
        add(winPanel);
        winPanel.setVisible(false);

        //(7) Lose Panel to display if user loses the game
        add(losePanel);
        losePanel.setVisible(false);

        setJMenuBar(createMenuBar());
        this.setVisible(true);
    }


    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGame = new JMenuItem(("New Game"));
        JMenuItem saving = new JMenuItem("Saving Game");
        JMenuItem loading = new JMenuItem("Loading Game");
        JMenuItem exitItem = new JMenuItem("Exit");

        gameMenu.add(newGame);
        gameMenu.add(saving);
        gameMenu.add(loading);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);

        menuBar.add(gameMenu);
        newGame.addActionListener(e -> {
            int decision = JOptionPane.showConfirmDialog(MainFrame.this, "Play again?", "Confirm Start New Game", JOptionPane.OK_CANCEL_OPTION);
            if (decision == JOptionPane.OK_OPTION) {
                GameManager startNewGame = new GameManager();
            }
        });
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

    public void itemPanelOn() {
        itemListPanel.setVisible(true);
    }

    public void itemPanelOff() {
        itemListPanel.setVisible(false);
    }

    public void updateText(String text) {
        textPanel.setText(text);
    }
}
