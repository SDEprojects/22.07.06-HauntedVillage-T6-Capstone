package com.game.hauntedvillage.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class IntroStroy {
    //prints game background information before game
    FileReading file = new FileReading();
    private String introduction;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<IntroStroy> intro = new ArrayList<>();

    public void presentInfo() {
//        Art.showArt("house"); //don't need it for GUI
        InputStream logo = FileReading.getFileFromResourceAsStreamFortxt("ReadingFile/info.txt");
        FileReading.printInputStream(logo);
    }

    IntroStroy dataReader(){
        try {
            String introData = file.dataReader("ReadingFile/info.txt");
            intro = objectMapper.readValue(introData, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return intro.get(0);
    }

    public String getIntro (){
        return dataReader().getIntroduction();
    }

    public String getIntroduction() {

        return introduction;
    }

    @Override
    public String toString(){
        return "Story: introduction=" + getIntroduction();
    }

    public static void main(String[] args) {
        IntroStroy introduction = new IntroStroy();
        //IntroStroy intro = introduction.dataReader();
        System.out.println(introduction.getIntro());;
    }
}
