package omen.alien.audio;

import com.sun.org.apache.xpath.internal.operations.Bool;
import omen.alien.App;
import omen.alien.clients.RecordingClient;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Function;

/**
 * Created by crstffr on 11/1/17.
 */
public class Recorder {

    RecordingClient client = App.recordingClient;

    Integer sampleRate = 44100; // changes to this do nothing as of yet.
    Integer channels = 2;

    public Recorder() {}

    public void start() {
        client.record(channels, sampleRate);
    }

    public void stop() {
        client.stop();
    }

    public void save(String filepath) {
        client.save(filepath);
    }

    public void discard() {
        client.discard();
    }

    public void onStart(Runnable fn) {
        client.onRecordingStarted(fn);
    }

    public void onStop(Runnable fn) {
        client.onRecordingStopped(fn);
    }

    public void onSave(Runnable fn) {
        client.onRecordingSaved(fn);
    }
}