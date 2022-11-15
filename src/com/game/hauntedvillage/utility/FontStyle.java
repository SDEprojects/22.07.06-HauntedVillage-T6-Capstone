package com.game.hauntedvillage.utility;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontStyle {

    public Font getfont(int style, int size, String fileName) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
        Font action = null;
        try {
            action = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        return action.deriveFont(style, size);
    }

}