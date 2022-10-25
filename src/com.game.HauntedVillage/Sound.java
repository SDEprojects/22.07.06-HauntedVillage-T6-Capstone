package com.game.HauntedVillage;


import com.apps.util.Console;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Sound {

    public static void runMusic(){
        File file = new File("22.07.06-HauntedVillage/resources/music.wav");
        try {
            Scanner scanner = new Scanner(System.in);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            String response = "";

            while (!response.equals("C")) {
                System.out.println("Music Options: C = Continue with music, Q = Continue without music");
                System.out.println("Enter your choice:");

                response = scanner.next();
                response = response.toUpperCase();

                switch(response){
                    case ("C"):
                    break;
                    case ("Q"): clip.close();
                    response = "C";
                    break;
                    default:
                        System.out.println("not a valid response");
                }
                System.out.println("left music menu...");
                Console.pause(1000);
            }
        } catch (UnsupportedAudioFileException e) {
            System.out.println("runMusic() UnsupportedAudioFileException");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("runMusic() IOException");
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

}