package com.game.hauntedVillage.model;

import com.game.hauntedVillage.viewer.GameManager;

public class ScreenChanger {
    private GameManager game;
    public ScreenChanger(GameManager game){
        this.game = game;
    }

    public void showScreen1(){
        game.getGui().getBgPanel()[1].setVisible(true);
        game.getGui().getBgPanel()[2].setVisible(false);
        game.getGui().getBgPanel()[3].setVisible(false);
    }
    public void showScreen2(){
        game.getGui().getBgPanel()[1].setVisible(false);
        game.getGui().getBgPanel()[2].setVisible(true);
        game.getGui().getBgPanel()[3].setVisible(false);
    }
    public void showScreen3(){
        game.getGui().getBgPanel()[1].setVisible(false);
        game.getGui().getBgPanel()[2].setVisible(false);
        game.getGui().getBgPanel()[3].setVisible(true);
    }
}