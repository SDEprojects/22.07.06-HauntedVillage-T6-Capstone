package com.game.hauntedvillage.utility;

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
    private boolean mute = false;

    private void setFile(URL url) {
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
    }

    //volume up
    public void volumeUp() {
        currentVolume += 3.0f;
        if (currentVolume > 4.0f) {
            currentVolume = 4.0f;
        }
        floatControl.setValue(currentVolume);
    }

    //volume down
    public void volumeDown() {
        currentVolume -= 3.0f;
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
            mute = true;
        } else {
            currentVolume = preVolume;
            floatControl.setValue(currentVolume);
            mute = false;
        }
    }
}

