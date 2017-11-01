package omen.alien.layout.record;

import ddf.minim.*;

import omen.alien.*;
import omen.alien.layout.record.state.*;
import omen.alien.layout.record.widget.*;
import omen.alien.component.*;

import java.util.HashMap;
import java.util.ArrayList;

public class RecordLayout extends MajorLayout {

    Layout stateObj;
    String stateStr;

    AudioRecorder recorder;
    HashMap<String, Layout> states;
    ArrayList<RecordWidget> widgets;

    String filename = "";
    String filepath = "";
    String tmpname = "";

    RecordClipWidget clipWidget;
    RecordFileWidget fileWidget;
    RecordTimerWidget timerWidget;
    RecordHeaderWidget headerWidget;
    RecordWaveformWidget waveformWidget;
    RecordAmpliformWidget ampliformWidget;

    public RecordLayout() {
        super();
        color = Const.RED;
        states = new HashMap<>();
        widgets = new ArrayList<>();

        setupWidgets();
        setupStateLayouts();
        startNewRecording();

        onEnable(() -> {
            stateObj.enable();
            //waveformWidget.show();
            startNewRecording();
            for (RecordWidget widget : widgets) {
                widget.enable().draw();
            }
        });

        onDisable(() -> {
            stateObj.disable();
            timerWidget.reset();
            //waveformWidget.reset();
            ampliformWidget.reset();
            if (is("recording")) {
                recorder.endRecord();
                recorder = null;
            }
            for (RecordWidget widget : widgets) {
                widget.disable().clear();
            }
            fileWidget.destroy();
        });

        onDraw(() -> {
            stateObj.draw();
            timerWidget.run();
            for (RecordWidget widget : widgets) widget.draw();
            /*
            if (ampliformWidget.clipped) {
                headerWidget.setText("CLIPPED");
                clipWidget.show();
            }
            */
        });
    }

    void setupWidgets() {
        ampliformWidget = new RecordAmpliformWidget(this);
        //waveformWidget = new RecordWaveformWidget(this);
        headerWidget = new RecordHeaderWidget(this);
        timerWidget = new RecordTimerWidget(this);
        fileWidget = new RecordFileWidget(this);
        //clipWidget = new RecordClipWidget(this);

        widgets.add(ampliformWidget);
        //widgets.add(waveformWidget);
        //widgets.add(clipWidget);
        widgets.add(timerWidget);
        widgets.add(fileWidget);
        widgets.add(headerWidget);

        ampliformWidget.setColor(Const.RED);
        //waveformWidget.setColor(Const.MIDRED);
        headerWidget.setColor(Const.WHITE);
        timerWidget.setColor(Const.WHITE);
        //clipWidget.setColor(Const.WHITE);
        fileWidget.setColor(Const.WHITE);
    }

    void setupStateLayouts() {
        states.put("ready", new RecordStateReadyLayout(this));
        states.put("saved", new RecordStateSavedLayout(this));
        states.put("paused", new RecordStatePausedLayout(this));
        states.put("rename", new RecordStateRenameLayout(this));
        states.put("recording", new RecordStateRecordingLayout(this));
    }

    public void keyPressed(char key) {
        stateObj.keyPressed(key);
    }

    boolean is(String what) {
        return stateStr.equals(what);
    }

    Layout getState() {
        return states.get(stateStr);
    }

    void setState(String _state) {
        stateStr = _state;
        stateObj = getState();
        headerWidget.clear();
        for (int i = 0; i < states.size(); i++) {
            Layout s = states.get(i);
            if (s != null && !s.equals(_state)) {
                s.disable();
            }
        }
        stateObj.enable();
    }

    public void setHeader(String _text) {
        headerWidget.setText(_text);
    }

    public void startNewRecording() {
        recorder = null;
        recorder = App.minim.createRecorder(App.audioInput, fileWidget.buildTempFilepath(), false);
        ampliformWidget.reset();
        timerWidget.reset();
        //clipWidget.reset();
        setState("ready");
    }

    public void record() {
        recorder.beginRecord();
        ampliformWidget.start();
        timerWidget.start();
        setState("recording");
    }

    public void pause() {
        ampliformWidget.stop();
        timerWidget.stop();
        recorder.endRecord();
        setState("paused");
    }

    public void resume() {
        timerWidget.start();
        recorder.beginRecord();
        ampliformWidget.start();
        setState("recording");
    }

    public void save() {
        if (recorder.isRecording()) {
            recorder.endRecord();
        }
        /*
        try {

        } catch (Exception e) {}
        */
        recorder.save();
        fileWidget.save();
        timerWidget.stop();
        ampliformWidget.stop();
        setState("saved");
    }

    public void reset() {
        recorder.endRecord();
        fileWidget.destroy();
        startNewRecording();
    }

    public void rename() {
        fileWidget.startRenaming();
        setState("rename");
    }

    public void renameDone() {
        setState("saved");
    }

    public void open() {}

    public void play() {}

}
