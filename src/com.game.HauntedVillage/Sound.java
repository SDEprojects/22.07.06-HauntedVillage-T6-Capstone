package com.game.HauntedVillage;


import com.apps.util.Console;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Sound {

    static Boolean SFX_On = true;

    public static void runMusic(){
        File file = new File("22.07.06-HauntedVillage/resources/music.wav");
        try {
            Scanner scanner = new Scanner(System.in);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            FloatControl gainMusicControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainMusicControl.setValue(20f * (float) Math.log10(1)); // set volume to 50% to start

            String response = "";

            while (!response.equals("C")) {
                if (SFX_On == true) {
                    System.out.println("Music Options: C = Continue with music, V = Change Volume, Q = Continue without music, S = Turn Off");
                    System.out.println("Enter your choice:");
                } else {
                    System.out.println("Music Options: C = Continue with music, V = Change Volume, Q = Continue without music, S = Turn On");
                    System.out.println("Enter your choice:");
                }

                response = scanner.next();
                response = response.toUpperCase();

                switch(response){
                    case ("C"):
                    break;
                    case ("V"):
                        System.out.println(gainMusicControl.getValue());
                        System.out.println("The current volume is " + Math.pow(10f, gainMusicControl.getValue()/20f) +"/2.0" );
                        System.out.println("What would you like to set the volume to? ");
                        Scanner volScanner = new Scanner(System.in);
                        float volumeEntry = 0;
                        volumeEntry = Float.parseFloat(volScanner.next());
                        if (volumeEntry <= 100 && volumeEntry >=0){
                            gainMusicControl.setValue(20f * (float) Math.log10(2*(volumeEntry/100)));
                        } else
                            System.out.println("Not valid entry. Please enter a value between 0 and 100.");
                        break;
                    case ("Q"):
                        clip.close();
                        response = "C";
                        break;
                    case ("S"):
                        SFX_On = false;
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

    public static void runFX(){
        File file = new File("22.07.06-HauntedVillage/resources/inventorySFX.wav");
        if (SFX_On == true){
            try {
                Scanner scanner = new Scanner(System.in);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                FloatControl gainFXControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainFXControl.setValue(20f * (float) Math.log10(1)); // set volume to 50% to start

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

}