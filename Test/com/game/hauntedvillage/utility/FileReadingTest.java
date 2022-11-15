package com.game.hauntedvillage.utility;

import org.junit.Test;

public class FileReadingTest {

    @Test(expected = IllegalArgumentException.class)
    public void getFileFromResourceAsStreamWithInvalidFilePath() {
        FileReading.getFileFromResourceAsStreamFortxt("abc");
    }
}