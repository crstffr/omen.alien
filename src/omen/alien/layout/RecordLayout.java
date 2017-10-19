package omen.alien.layout;

import ddf.minim.*;

import omen.alien.*;
import omen.alien.util.*;
import omen.alien.component.*;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;

public class RecordLayout extends Layout {

    AudioRecorder recorder;
    TimeCounter timeCounter;

    String filename = "";
    String filepath = "";
    String state = "";

    View headerV;
    View timerV;
    View nameV;
    View sizeV;

    public RecordLayout() {

        timeCounter = new TimeCounter();

        int headerW = 236;
        int headerH = 36;
        int headerX = App.stage.view.centerX(headerW);
        int headerY = App.stage.view.centerY(headerH) - 75;

        int nameW = 320;
        int nameH = 26;
        int nameX = App.stage.view.centerX(nameW);
        int nameY = App.stage.view.centerY(nameH) - 25;

        int timerW = 320;
        int timerH = 26;
        int timerX = App.stage.view.centerX(timerW);
        int timerY = App.stage.view.centerY(timerH) + 25;

        headerV = App.stage.view.createSubView(headerX, headerY, headerW, headerH);
        timerV = App.stage.view.createSubView(timerX, timerY, timerW, timerH);
        nameV = App.stage.view.createSubView(nameX, nameY, nameW, nameH);

        setState("ready");
    }

    boolean is(String what) {
        return state.equals(what);
    }

    void setState(String _state) {
        state = _state;
    }

    /**
     *
     */
    public void onEnable() {
        App.title.setColor(Const.RED).draw();
        App.buttonRow.setColor(Const.RED).draw();
        App.ampliform.setColor(Const.TRANSGRAY).draw();
        App.waveform.setColor(Const.TRANSRED).draw();
        newRecording();
        drawFilename();
        drawHeader();
        drawTimer();
    }

    public void onDisable() {
        if (is("recording")) {
            recorder.endRecord();
            timeCounter.reset();
            recorder = null;
            destroy();
        }
        App.ampliform.clear();
        App.waveform.clear();
        headerV.clear();
        timerV.clear();
        nameV.clear();
    }

    public void beforeFrame() {
        App.waveform.draw();
        App.ampliform.draw();
        timeCounter.run();
        drawFilename();
        drawHeader();
        drawTimer();
    }

    void newRecording() {
        recorder = null;
        timeCounter.reset();
        App.ampliform.disable().clear();
        filename = String.format("%06d", App.fileCounter.getIndex()) + ".wav";
        filepath = Const.SAMPLE_TEMP_PATH + filename;
        recorder = App.minim.createRecorder(App.audioInput, filepath);
        setState("ready");
    }

    void record() {
        timeCounter.start();
        recorder.beginRecord();
        App.ampliform.enable();
        setState("recording");
    }

    void pause() {
        timeCounter.stop();
        recorder.endRecord();
        App.ampliform.disable();
        setState("paused");
    }

    void resume() {
        timeCounter.start();
        recorder.beginRecord();
        App.ampliform.enable();
        setState("recording");
    }

    void save() {
        recorder.save();
        timeCounter.stop();
        App.ampliform.disable();
        File f = new File(filepath);
        f.renameTo(new File(Const.SAMPLE_USER_PATH + filename));
        App.fileCounter.increment();
        setState("saved");
    }

    void reset() {
        recorder.endRecord();
        App.ampliform.disable().clear();
        destroy();
        newRecording();
    }

    void rename() {}
    void edit() {}
    void play() {}

    void destroy() {
        File f = new File(filepath);
        if (f.exists()) {
            f.delete();
        }
    }

    void drawHeader() {
        headerV.layer.fill(Const.RED);
        headerV.layer.textFont(App.font, 48);
        headerV.layer.textAlign(Const.CENTER, Const.CENTER);
        headerV.layer.text(state.toUpperCase(), headerV.mid_x, headerV.mid_y);
        headerV.draw();
    }

    void drawTimer() {
        timerV.fillWith(Const.TRANSPARENT);
        timerV.layer.fill(Const.WHITE);
        timerV.layer.textFont(App.font, 34);
        timerV.layer.textAlign(Const.CENTER, Const.CENTER);
        timerV.layer.text(timeCounter.toString(), timerV.mid_x, timerV.mid_y);
        timerV.draw();
    }

    void drawFilename() {
        nameV.layer.fill(Const.RED);
        nameV.layer.textFont(App.font, 28);
        nameV.layer.textAlign(Const.CENTER, Const.CENTER);
        nameV.layer.text(filename, nameV.mid_x, nameV.mid_y);
        nameV.draw();
    }

    /**
     *
     * @param key what key was pressed
     */
    public void keyPressed(char key) {
        switch (key) {
            case 'a': buttonA(); break;
            case 's': buttonB(); break;
            case 'd': buttonC(); break;
            case 'f': buttonD(); break;
        }
        changed = true;
    }

    /**
     *
     * @return String
     */
    public String getTitle() {
        return "";
    }

    void buttonA() {
        switch(state) {
            case "ready":
                record();
                break;
            case "recording":
                pause();
                break;
            case "paused":
                resume();
                break;
            case "saved":
                newRecording();
                break;
        }
    }

    void buttonB() {
        switch(state) {
            case "ready":
                break;
            case "recording":
                save();
                break;
            case "paused":
                save();
                break;
            case "saved":
                rename();
                break;
        }
    }

    void buttonC() {
        switch(state) {
            case "ready":
                break;
            case "recording":
                break;
            case "paused":
                break;
            case "saved":
                edit();
                break;
        }
    }

    void buttonD() {
        switch(state) {
            case "ready":
                break;
            case "recording":
                reset();
                break;
            case "paused":
                reset();
                break;
            case "saved":
                play();
                break;
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getButtonLabels() {
        ArrayList<String> labels = new ArrayList<>();

        switch(state) {
            case "ready":
                labels.add("RECORD");
                labels.add("-");
                labels.add("-");
                labels.add("-");
                break;

            case "recording":
                labels.add("PAUSE");
                labels.add("SAVE");
                labels.add("-");
                labels.add("RESET");
                break;

            case "paused":
                labels.add("RESUME");
                labels.add("SAVE");
                labels.add("PLAY");
                labels.add("RESET");
                break;

            case "saved":
                labels.add("NEW");
                labels.add("RENAME");
                labels.add("PLAY");
                labels.add("EDIT");
                break;
        }

        return labels;
    }

}
