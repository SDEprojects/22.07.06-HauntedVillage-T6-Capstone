package com.game.hauntedVillage.utility;

import com.game.hauntedvillage.utility.FileReading;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileReadingTest {

    @Test(expected = IllegalArgumentException.class)
    public void getFileFromResourceAsStreamWithInvalidFilePath() {
        FileReading.getFileFromResourceAsStreamFortxt("abc");
    }
}