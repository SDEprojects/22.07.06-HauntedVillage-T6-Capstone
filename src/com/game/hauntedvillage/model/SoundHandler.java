package com.game.hauntedvillage.model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

public class SoundHandler {

    private Clip clip;
    private float preVolume = 0;
    private float currentVolume = 0;
    private FloatControl floatControl;
    boolean mute = false;

    public void setFile(URL url) {
        try {
            AudioInputStream sound = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(sound);
            floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception ignored) {
        }
    }

    //plays sound
    public void playMusic(URL url) {
        setFile(url);
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        //clip.start();
    }

    //volume up
    public void volumeUp() {
        currentVolume += 3.0f;
        System.out.println("current volume: " + currentVolume);
        if (currentVolume > 4.0f) {
            currentVolume = 4.0f;
        }
        floatControl.setValue(currentVolume);
    }

    //volume down
    public void volumeDown() {
        currentVolume -= 3.0f;
        System.out.println("current volume: " + currentVolume);
        if (currentVolume < -30.0f) {
            currentVolume = -30.0f;
        }
        floatControl.setValue(currentVolume);
    }

    //toggle mute/un-mute
    public void toggleMute() {
        if (!mute) {
            preVolume = currentVolume;
            currentVolume = -80.0f;
            floatControl.setValue(currentVolume);
            System.out.println(floatControl);
            mute = true;
        } else {
            currentVolume = preVolume;
            System.out.println("current volume: " + currentVolume);
            floatControl.setValue(currentVolume);
            mute = false;
        }
    }
}

