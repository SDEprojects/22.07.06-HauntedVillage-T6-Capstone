package com.game.hauntedVillage.app;


import com.game.hauntedVillage.controller.GameManager;

public class Main {
    public static void main(String[] args) {
       // Engine.getPlayGame();
       GameManager app=new GameManager();
       app.start();
    }
}