package omen.alien.audio;

import ddf.minim.AudioInput;
import ddf.minim.AudioOutput;
import ddf.minim.Minim;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import java.util.HashMap;

/**
 * Created by crstffr on 11/12/17.
 */
public class AudioDriver {

    public Minim minim;
    public AudioInput input;

    public AudioDriver(Object _this) {

        minim = new Minim(_this);

        Mixer mixer = getMixer();

        if (mixer != null) {
            minim.setInputMixer(mixer);
            minim.setOutputMixer(mixer);
        }

        connectInput();

    }

    public AudioInput getInput() {
        return input;
    }

    public void connectInput() {
        if (input != null) {
            input.close();
        }
        input = minim.getLineIn(2, 2048, 44100, 16);
    }

    Mixer getMixer() {
        Mixer result = null;
        Mixer.Info[] info = AudioSystem.getMixerInfo();
        for (int i = 0; i < info.length; i++) {
            String details = info[i].toString();
            //System.out.println(details);
            if (details.substring(0, 4).equals("jack")) {
                //System.out.println("Found mixer: #" + i);
                result = AudioSystem.getMixer(info[i]);
            }
        }
        return result;
    }

}
