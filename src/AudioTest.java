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

    public static void main(String args[]) {
        PApplet.main("AudioTest");
    }

    @Override
    public void settings() {
        size(100, 100);
    }

    @Override
    public void setup() {

        Mixer.Info[] info = AudioSystem.getMixerInfo();
        for(int i = 0; i < info.length; i++) {
            System.out.println(i + ": " + info[i].toString());
        }

        Minim minim;
        AudioInput input;
        AudioOutput output;
        AudioRecorder recorder;

        minim = new Minim(this);
        minim.debugOn();

        input = minim.getLineIn();
        output = minim.getLineOut();

        recorder = minim.createRecorder(input, "test.wav", false);
        recorder.beginRecord();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                try {

                } catch(Exception e) {
                    System.out.println(">>" + e);
                }

                recorder.endRecord();
                recorder.save();
                exit();
            }
        }, 5000);

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                System.out.println("FrameRate: " + frameRate);
            }
        }, 0, 1000);

    }

    @Override
    public void draw() {

    }

}
