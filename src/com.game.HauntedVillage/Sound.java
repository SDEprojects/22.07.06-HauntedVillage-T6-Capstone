package com.game.HauntedVillage;


import com.apps.util.Console;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Sound {

    private static Boolean SFX_On = true;
    private static Boolean musicOn = true;
    private static double musicLevel = 1;
    private static double soundFXLevel = 1;
    static String answer = "";
    private static String musicOnOrOff;
    private static String sfxOnOrOff;

    static File musicFile = new File("resources/music.wav");
    static File soundFXFile = new File("resources/inventorySFX.wav");

    public static void musicPlayer(File file) {
        if(getMusicOn()){
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                FloatControl gainMusicControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainMusicControl.setValue(20f * (float) Math.log10(getMusicLevel())); // set volume to 50% to start

            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void soundFXPlayer(File file) {
        if(getSFX_On()){
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                FloatControl gainMusicControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainMusicControl.setValue(20f * (float) Math.log10(getSoundFXLevel())); // set volume to 50% to start

            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void musicMenu() {
        Scanner scanner = new Scanner(System.in);

        while (!answer.equals("C")) {
            if (getSFX_On()) {
                System.out.println("Music Options: C = Continue with music, V = Change Volume, Q = Continue without music, S = Turn Off Sound Effects");
                System.out.println("Enter your choice:");
            } else {
                System.out.println("Music Options: C = Continue with music, V = Change Volume, Q = Continue without music, S = Turn On Sound Effects");
                System.out.println("Enter your choice:");
            }
            answer = scanner.nextLine();
            answer= answer.toUpperCase();
        }
        switch(answer){
            case ("C"):
                break;
            case ("V"):
                System.out.println("What would you like to set the music volume to? (0 to 100) ");
                Scanner volScanner = new Scanner(System.in);
                double volumeEntry = 0;
                double SFXEntry = 0;
                volumeEntry = Float.parseFloat(volScanner.next());
                if (volumeEntry <= 100 && volumeEntry >=0){
                    setMusicLevel(volumeEntry);
                } else {
                    System.out.println("Not valid entry. Please enter a value between 0 and 100.");
                }
                SFXEntry = Float.parseFloat(volScanner.next());
                System.out.println("What would you like to set the sound effects volume to? (0 to 100)");
                if (SFXEntry <= 100 && SFXEntry >=0){
                    setSoundFXLevel(SFXEntry);
                } else {
                    System.out.println("Not valid entry. Please enter a value between 0 and 100.");
                }
                break;
            case ("Q"):
                setMusicOn(false);
                answer = "C";
                break;
            case ("S"):
                setSFX_On(false);
                break;
            default:
                System.out.println("not a valid response");
        }
        System.out.println("left music menu...");
        Console.pause(1000);

    }

    public static void runMusic(){
        File file = new File("resources/music.wav");
        try {
            Scanner scanner = new Scanner(System.in);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            clip.loop(10);
            FloatControl gainMusicControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainMusicControl.setValue(20f * (float) Math.log10(getMusicLevel())); // set volume to 50% to start

            String response = "";

            while (!response.equals("C")) {
                Art.showArt("music_note");
                System.out.println("Music Options: C = Continue with current settings, V = Change Volume, Q = "+ musicStatus() +", S = "+sfxStatus());
                System.out.println("Enter your choice:");


                response = scanner.next();
                response = response.toUpperCase();

                switch(response){
                    case ("C"):
                    break;
                    case ("V"):
                        System.out.println("The current music volume is " + (float) (Math.pow(10f, gainMusicControl.getValue()/20f))*50 +"/100" );
                        System.out.println("What would you like to set the volume to? ");
                        Scanner volScanner = new Scanner(System.in);
                        float volumeEntry = 0;
                        volumeEntry = Float.parseFloat(volScanner.next());
                        if (volumeEntry <= 100 && volumeEntry >=0){
                            setMusicLevel(volumeEntry);
                            gainMusicControl.setValue(20f * (float) Math.log10(2*(getMusicLevel()/100)));
                        } else {
                            System.out.println("Not valid entry. Please enter a value between 0 and 100.");
                        }
                        System.out.println("The current SFX volume is " + getSoundFXLevel()*50 +"/100" );
                        System.out.println("What would you like to set the SFX volume to? ");
                        Scanner SFXScanner = new Scanner(System.in);
                        float SFXEntry = 0;
                        SFXEntry = Float.parseFloat(SFXScanner.next());
                        if (SFXEntry <= 100 && SFXEntry >=0){
                            setSoundFXLevel(SFXEntry);
                            gainMusicControl.setValue(20f * (float) Math.log10(2*(getSoundFXLevel()/100)));
                        } else {
                            System.out.println("Not valid entry. Please enter a value between 0 and 100.");
                        }
                        break;
                    case ("Q"):
                        if (getMusicOn()){
                            clip.stop();
                            setMusicOn(false);
                        } else{
                         setMusicOn(true);
                         clip.start();
                        }
                        break;
                    case ("S"):
                        if (getSFX_On()){
                            setSFX_On(false);
                        } else {
                            setSFX_On(true);
                        }
                        break;
                    default:
                        System.out.println("not a valid response");
                }
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
        File file = new File("resources/inventorySFX.wav");
        if (getSFX_On()){
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                FloatControl gainFXControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainFXControl.setValue(20f * (float) Math.log10(getSoundFXLevel())); // set volume to 50% to start

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

    public static String musicStatus(){
        if (getMusicOn()){
            musicOnOrOff = "Toggle music OFF";
        } else {
            musicOnOrOff = "Toggle music ON";
        }
        return musicOnOrOff;
    }

    public static String sfxStatus(){
        if(getSFX_On()) {
            sfxOnOrOff = "Toggle SFX OFF";
        } else {
            sfxOnOrOff = "Toggle SFX ON";
        }
        return sfxOnOrOff;
    }

    // ACCESSOR METHODS
    public static double getMusicLevel() {
        return musicLevel;
    }

    public static void setMusicLevel(double musicLevel) {
        Sound.musicLevel = musicLevel;
    }

    public static double getSoundFXLevel() {
        return soundFXLevel;
    }

    public static void setSoundFXLevel(double soundFXLevel) {
        Sound.soundFXLevel = soundFXLevel;
    }

    public static Boolean getMusicOn() {
        return musicOn;
    }

    public static void setMusicOn(Boolean musicOn) {
        Sound.musicOn = musicOn;
    }

    public static Boolean getSFX_On() {
        return SFX_On;
    }

    public static void setSFX_On(Boolean SFX_On) {
        Sound.SFX_On = SFX_On;
    }
}