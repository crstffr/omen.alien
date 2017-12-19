package omen.alien.layout.record;

import omen.alien.*;
import omen.alien.audio.Player;
import omen.alien.audio.Recorder;
import omen.alien.layout.record.state.*;
import omen.alien.layout.record.widget.*;
import omen.alien.component.*;

import java.util.HashMap;
import java.util.ArrayList;

public class RecordLayout extends MajorLayout {

    Layout stateObj;
    String stateStr;
    boolean saving;

    HashMap<String, Layout> states;
    ArrayList<RecordWidget> widgets;

    public Player player;
    public Recorder recorder = new Recorder();

    RecordFileWidget fileWidget;
    RecordMeterWidget meterWidget;
    RecordTimerWidget timerWidget;
    RecordHeaderWidget headerWidget;

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
            for (RecordWidget widget : widgets) {
                widget.enable();
            }
            startNewRecording();
        });

        onDisable(() -> {
            stateObj.disable();
            timerWidget.reset();
            fileWidget.destroy();
            if (recorder != null) {
                recorder.discard();
            }
            for (RecordWidget widget : widgets) {
                widget.disable().clear();
            }
        });

        onDraw(() -> {
            stateObj.draw();
            timerWidget.run();
            for (RecordWidget widget : widgets) widget.draw();
        });

        recorder.onStart(() -> {
            recordStarted();
        });

        recorder.onStop(() -> {
            recordStopped();
        });

        recorder.onSave(() -> {
            recordSaved();
        });
    }

    void setupWidgets() {
        headerWidget = new RecordHeaderWidget(this);
        timerWidget = new RecordTimerWidget(this);
        meterWidget = new RecordMeterWidget(this);
        fileWidget = new RecordFileWidget(this);

        widgets.add(fileWidget);
        widgets.add(timerWidget);
        widgets.add(meterWidget);
        widgets.add(headerWidget);

        headerWidget.setColor(Const.RED).show();
        fileWidget.setColor(Const.WHITE).hide();
        timerWidget.setColor(Const.WHITE).hide();
    }

    void setupStateLayouts() {
        states.put("ready", new RecordStateReadyLayout(this));
        states.put("saved", new RecordStateSavedLayout(this));
        states.put("rename", new RecordStateRenameLayout(this));
        states.put("saving", new RecordStateSavingLayout(this));
        states.put("waiting", new RecordStateWaitingLayout(this));
        states.put("playing", new RecordStatePlayingLayout(this));
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
        fileWidget.reset().hide();
        meterWidget.reset().show();
        timerWidget.reset().hide();
        setState("ready");
    }

    public void toggleRecord() {
        if (is("recording") || is("waiting")) {
            save();
        } else {
            startNewRecording();
            record();
        }
    }

    public void record() {
        recorder.start();
        setState("waiting");
    }

    public void save() {
        recorder.save(fileWidget.text);
        meterWidget.hide();
        timerWidget.hide();
        setState("saving");
    }

    public void recordStarted() {
        timerWidget.show();
        timerWidget.start();
        meterWidget.holdClip();
        setState("recording");
    }

    public void recordStopped() {
        timerWidget.stop();
    }

    public void recordSaved() {
        fileWidget.save();
        fileWidget.show();
        setState("saved");
    }

    public void reset() {
        recorder.discard();
        fileWidget.destroy();
        startNewRecording();
    }

    public void rename() {
        fileWidget.input.startCapture();
        setState("rename");
    }

    public void renameDone() {
        setState("saved");
    }

    public void open() {}

    public void play() {}

    public void stopPlaying() {}

    public void monitor() {}

}
