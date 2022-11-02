package com.game.HauntedVillage;

import java.io.InputStream;

class IntroStroy {
    //prints game background information before game
    void presentInfo() {
//        Art.showArt("house"); //don't need it for GUI
        InputStream logo = FileReading.getFileFromResourceAsStreamFortxt("info.txt");
        FileReading.printInputStream(logo);
    }
}
