package com.game.hauntedvillage.view;

import com.game.hauntedvillage.controller.GameManager;
import com.game.hauntedvillage.utility.FontStyle;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

class WinPanel extends JPanel implements ActionListener {

    private final GameManager baseController;
    private JLabel imageLabel;
    private Timer timer = new Timer(80, this);
    private BufferedImage finalScene;
    float alphaValue = 0f;

    public WinPanel(GameManager baseController) {
        this.baseController = baseController;
        imageLabel = new JLabel("You Won");
        imageLabel.setBounds(0, 0, 900, 900);
        imageLabel.setHorizontalTextPosition(JLabel.CENTER);
        imageLabel.setVerticalTextPosition(JLabel.CENTER);
        imageLabel.setFont(new Font("Libian TC", Font.BOLD, 56));
        imageLabel.setForeground(Color.red);
        loadFinalScreen();
        timer.start();

        ImageIcon backGroundImage = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("BackgroundImages/home.jpg")));
        imageLabel.setIcon(backGroundImage);
        add(imageLabel);
        setBounds(50, 50, 900, 900);
        setBackground(Color.black);
        setLayout(null);


    }

    public void loadFinalScreen(){

        try {
            finalScene = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("BackgroundImages/catEyes.jpeg")));
        }catch(IOException e){
            e.printStackTrace();
        }



    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D graph = (Graphics2D) g;
        graph.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
        graph.drawImage(finalScene, 0, 0, null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        alphaValue += 0.01f;
        if(alphaValue >1){
            alphaValue = 1;
            timer.stop();
        }
        repaint();

    }
}