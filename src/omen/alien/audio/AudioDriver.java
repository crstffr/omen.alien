package omen.alien.audio;

import ddf.minim.AudioInput;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import java.util.HashMap;


public class AudioDriver {

    public Minim minim;
    public AudioInput input;
    public AudioOutput output;

    public AudioDriver(Object _this) {

        minim = new Minim(_this);
        Mixer mixer = getMixer();

        if (mixer != null) {
            minim.setInputMixer(mixer);
            minim.setOutputMixer(mixer);
        }

    }

    public AudioInput getInput() {
        return (input == null) ? connectInput() : input;
    }

    public AudioInput connectInput() {
        disconnectInput();
        input = minim.getLineIn(2, 2048, 44100, 16);
        return input;
    }

    public void disconnectInput() {
        if (input != null) {
            input.close();
            input = null;
        }
    }

    public AudioOutput getOutput() {
        return (output == null) ? connectOutput() : output;
    }

    public AudioOutput connectOutput() {
        disconnectOutput();
        output = minim.getLineOut(2, 512, 44100, 16);
        return output;
    }

    public void disconnectOutput() {
        if (output != null) {
            output.close();
            output = null;
        }
    }

    Mixer getMixer() {
        Mixer result = null;
        Mixer.Info[] info = AudioSystem.getMixerInfo();
        for (int i = 0; i < info.length; i++) {
            String details = info[i].toString();
            if (details.substring(0, 4).equals("jack")) {
                result = AudioSystem.getMixer(info[i]);
            }
        }
        return result;
    }

}
