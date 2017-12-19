import ddf.minim.AudioRecorder;
import ddf.minim.spi.AudioRecordingStream;
import ddf.minim.ugens.FilePlayer;
import processing.core.PApplet;

import ddf.minim.Minim;
import ddf.minim.AudioInput;
import ddf.minim.AudioOutput;

import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.AudioSystem;

/**
 * Created by crstffr on 10/25/17.
 */
public class AudioTest extends PApplet {

    Timer timer = new Timer();

    Minim minim;
    AudioInput input;
    AudioOutput output;
    AudioRecorder recorder;

    public static void main(String args[]) {
        PApplet.main("AudioTest");
    }

    @Override
    public void settings() {
        size(100, 100);
    }

    @Override
    public void setup() {

        minim = new Minim(this);
        //minim.debugOn();

        Mixer.Info[] info = AudioSystem.getMixerInfo();
        for(int i = 0; i < info.length; i++) {
            if (i == 1) {
                Mixer mixer = AudioSystem.getMixer(info[i]);
                minim.setInputMixer(mixer);
                minim.setOutputMixer(mixer);
            }

            System.out.println(i + ": " + info[i].toString());

        }

        input = minim.getLineIn(2, 2048, 96000, 16);
        output = minim.getLineOut(2, 2048, 96000, 16);

        recorder = minim.createRecorder(input, "test.wav", false);
        recorder.beginRecord();


        /*
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("FrameRate: " + frameRate);
            }
        }, 0, 1000);
        */

    }

    @Override
    public void keyPressed() {
        if (key == 's') {
            recorder.endRecord();
            FilePlayer player = new FilePlayer(recorder.save());
            player.patch(output);
            player.play();
        }
        if (key == 'q') {
            exit();
        }
    }

    @Override
    public void draw() {

    }

}
