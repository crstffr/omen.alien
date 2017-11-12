package omen.alien.component;

import ddf.minim.AudioInput;
import ddf.minim.AudioRecorder;
import omen.alien.App;

/**
 * Created by crstffr on 11/1/17.
 */
public class Recorder {

    String filepath;
    AudioRecorder recorder;
    static AudioInput input = App.minim.getLineIn(2, 4096, 96000, 16);;
    
    public Recorder(String _filepath) {
        recorder = App.minim.createRecorder(input, _filepath, false);
    }

    public void start() {
        recorder.beginRecord();
    }

    public void pause() {
        if (recorder != null && recorder.isRecording()) {
            recorder.endRecord();
        }
    }

    public void resume() {
        if (recorder != null && recorder.isRecording() == false) {
            recorder.beginRecord();
        }
    }

    public void save() {
        if (recorder != null) {
            if (recorder.isRecording() == true) {
                recorder.endRecord();
            }
            recorder.save();
            recorder = null;
        }
    }

    public void destroy() {
        if (recorder != null) {
            if (recorder.isRecording() == true) {
                recorder.endRecord();
            }
            recorder = null;
        }
    }

}