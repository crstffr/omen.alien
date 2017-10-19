package omen.alien.layout;

import ddf.minim.*;

import omen.alien.*;
import omen.alien.util.*;
import omen.alien.component.*;

import java.io.File;
import java.util.ArrayList;

public class RecordLayout extends Layout {

    AudioRecorder recorder;
    TimeCounter timeCounter;

    boolean clipped = false;
    int clipThreshold = 98;

    String filename = "";
    String filepath = "";
    String tmpname = "";
    String state = "";

    View headerV;
    View timerV;
    View nameV;
    View sizeV;

    public RecordLayout() {

        timeCounter = new TimeCounter();

        int headerH = 36;
        int headerW = 236;
        int headerX = App.stage.view.centerX(headerW);
        int headerY = App.stage.view.centerY(headerH) - 100;

        int nameH = 26;
        int nameW = App.stage.view.w;
        int nameX = App.stage.view.centerX(nameW);
        int nameY = App.stage.view.centerY(nameH) - 50;

        int timerH = 26;
        int timerW = 320;
        int timerX = App.stage.view.centerX(timerW);
        int timerY = App.stage.view.centerY(timerH) + 50;

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
        }
        App.ampliform.clear();
        App.waveform.clear();
        headerV.clear();
        timerV.clear();
        nameV.clear();
        destroy();
    }

    public void beforeFrame() {
        if (is("recording")) {
            checkClipping();
        }
        App.waveform.draw();
        App.ampliform.draw();
        timeCounter.run();
        drawFilename();
        drawHeader();
        drawTimer();
    }

    void newRecording() {
        clipped = false;
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
        clipped = false;
        recorder.endRecord();
        App.ampliform.disable().clear();
        destroy();
        newRecording();
    }

    void rename() {
        App.userInput = true;
        setState("rename");
    }

    void renameCancel() {
        tmpname = "";
        setState("saved");
        App.userInput = false;
    }

    void renameSubmit() {
        File f = new File(Const.SAMPLE_USER_PATH + filename);
        f.renameTo(new File(Const.SAMPLE_USER_PATH + tmpname + ".wav"));
        filename = tmpname + ".wav";
        App.userInput = false;
        setState("saved");
        tmpname = "";
    }

    void checkClipping() {
        if (App.ampliform.getMaxLevel() >= clipThreshold) {
            clipped = true;
            changed = true;
        }
    }

    void edit() {}
    void play() {}

    void destroy() {
        File f = new File(filepath);
        if (f.exists()) {
            f.delete();
        }
    }

    void drawHeader() {

        String text = (clipped) ? "CLIPPED" : state.toUpperCase();

        headerV.layer.fill(Const.RED);
        headerV.layer.textFont(App.font, 48);
        headerV.layer.textAlign(Const.CENTER, Const.CENTER);
        headerV.layer.text(text, headerV.mid_x, headerV.mid_y);
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

        String name = is("rename") ? tmpname + ".wav" : filename;
        nameV.layer.text(name, nameV.mid_x, nameV.mid_y);

        nameV.draw();
    }

    /**
     *
     * @param key what key was pressed
     */
    public void keyPressed(char key) {

        if (App.userInput) {
            switch (key) {
                case Const.ESC_KEY:
                    renameCancel();
                    break;
                case Const.RETURN:
                case Const.ENTER:
                    renameSubmit();
                    break;
                case Const.BACKSPACE:
                    tmpname = tmpname.replaceFirst(".$","");
                    break;
                default:
                    tmpname += key;
                    break;
            }
        }

        if (!App.userInput) {
            switch (key) {
                case 'a':
                    buttonA();
                    break;
                case 's':
                    buttonB();
                    break;
                case 'd':
                    buttonC();
                    break;
                case 'f':
                    buttonD();
                    break;
            }
        }

        changed = true;
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

            case "rename":
                labels.add("-");
                labels.add("-");
                labels.add("-");
                labels.add("-");
                break;
        }

        return labels;
    }

    /**
     *
     * @return String
     */
    public String getTitle() {
        return "";
    }

}
