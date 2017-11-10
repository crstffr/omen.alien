package omen.alien.component;

import ddf.minim.AudioInput;
import ddf.minim.AudioRecorder;
import omen.alien.App;

/**
 * Created by crstffr on 11/1/17.
 */
public class Recorder {

    String filepath;
    AudioInput input;
    AudioRecorder recorder;

    public Recorder(String _filepath) {
        input = App.minim.getLineIn(2, 4096, 96000, 16);
        filepath = _filepath;
    }

    void createRecorder() {
        if (recorder == null) {
            recorder = App.minim.createRecorder(input, filepath, false);
        }
    }

    public void start() {
        createRecorder();
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
