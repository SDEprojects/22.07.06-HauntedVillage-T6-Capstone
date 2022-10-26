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
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            String response = "";

            while (!response.equals("C")) {
                System.out.println("Music Options: C = Continue with music, V = Change Volume, Q = Continue without music");
                System.out.println("Enter your choice:");

                response = scanner.next();
                response = response.toUpperCase();

                switch(response){
                    case ("C"):
                    break;
                    case ("V"):
                        System.out.println(gainControl.getValue());
                        System.out.println("The current volume is " + Math.pow(10f, gainControl.getValue()/20f) +"/2.0" );
                        System.out.println("What would you like to set the volume to? ");
                        Scanner volScanner = new Scanner(System.in);
                        float volumeEntry = 0;
                        volumeEntry = Float.parseFloat(volScanner.next());
                        if (volumeEntry <= 100 && volumeEntry >=0){
                            gainControl.setValue(20f * (float) Math.log10(2*(volumeEntry/100)));
                        } else
                            System.out.println("Not valid entry. Please enter a value between 0 and 100.");
                        break;
                    case ("Q"):
                        clip.close();
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

    public static void setVolume(){

    }

}