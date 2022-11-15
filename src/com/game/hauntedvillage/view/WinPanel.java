package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

class WinPanel extends JPanel implements ActionListener {

    private final Timer timer = new Timer(80, this);
    private BufferedImage finalScene;
    float alphaValue = 0f;

    public WinPanel(GameManager baseController) {
        JLabel imageLabel = new JLabel("You Won");
        imageLabel.setBounds(0, 0, 900, 900);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
        imageLabel.setVerticalTextPosition(JLabel.CENTER);
        imageLabel.setFont(new Font("Libian TC", Font.BOLD, 56));
        imageLabel.setForeground(Color.red);

        ImageIcon backGroundImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("BackgroundImages/home.jpg")));
        imageLabel.setIcon(backGroundImage);
        add(imageLabel);
        setBounds(50, 50, 900, 800);
        setBackground(Color.black);
        setLayout(null);
    }

    public void loadFinalScreen() {
        timer.start();
        try {
            finalScene = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("BackgroundImages/end.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graph = (Graphics2D) g;
        graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
        graph.drawImage(finalScene, -60, 160, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        alphaValue += 0.01f;
        if (alphaValue > 1) {
            alphaValue = 1;
            timer.stop();
        }
        repaint();

    }
}