package com.game.hauntedVillage.viewer;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
class FontStyle {

    public Font getfont(int style,int size, String fileName){
        InputStream is=this.getClass().getClassLoader().getResourceAsStream(fileName);
        Font action=null;
        try{
            action=Font.createFont(Font.TRUETYPE_FONT,is);
        }catch (FontFormatException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
        Font newFront=action.deriveFont(style,size);
        return newFront;
    }

}