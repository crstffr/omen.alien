package omen.alien.layout.record;

import omen.alien.*;
import omen.alien.layout.record.state.*;
import omen.alien.layout.record.widget.*;
import omen.alien.component.*;

import java.util.HashMap;
import java.util.ArrayList;

public class RecordLayout extends MajorLayout {

    Layout stateObj;
    String stateStr;

    public Recorder recorder;
    HashMap<String, Layout> states;
    ArrayList<RecordWidget> widgets;

    RecordFileWidget fileWidget;
    RecordMeterWidget meterWidget;
    RecordTimerWidget timerWidget;
    RecordHeaderWidget headerWidget;
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
            startNewRecording();
            for (RecordWidget widget : widgets) {
                widget.enable().draw();
            }
        });

        onDisable(() -> {
            stateObj.disable();
            timerWidget.reset();
            if (is("recording")) {
                recorder.destroy();
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
        });
    }

    void setupWidgets() {
        ampliformWidget = new RecordAmpliformWidget(this);
        headerWidget = new RecordHeaderWidget(this);
        timerWidget = new RecordTimerWidget(this);
        meterWidget = new RecordMeterWidget(this);
        fileWidget = new RecordFileWidget(this);

        widgets.add(fileWidget);
        widgets.add(timerWidget);
        widgets.add(meterWidget);
        widgets.add(headerWidget);
        widgets.add(ampliformWidget);

        headerWidget.setColor(Const.RED).show();
        fileWidget.setColor(Const.WHITE).show();
        timerWidget.setColor(Const.WHITE).show();
        ampliformWidget.setColor(Const.YELLOW).hide();
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

    public void setRecordingStyle() {
        headerWidget.setColor(Const.RED).show();
        fileWidget.setColor(Const.WHITE).show();
        timerWidget.setColor(Const.WHITE).show();
        buttonRow.setColor(Const.WHITE).draw();
    }

    public void setDefaultStyle() {
        headerWidget.setColor(Const.RED).show();
        fileWidget.setColor(Const.WHITE).show();
        timerWidget.setColor(Const.WHITE).show();
        buttonRow.setColor(Const.RED).draw();
    }

    public void startNewRecording() {
        recorder = new Recorder(fileWidget.buildTempFilepath());
        ampliformWidget.reset().hide();
        meterWidget.reset().show();
        timerWidget.reset().show();
        setDefaultStyle();
        setState("ready");
    }

    public void record() {
        recorder.start();
        timerWidget.start();
        meterWidget.holdClip();
        ampliformWidget.start();
        setRecordingStyle();
        setState("recording");
    }

    public void pause() {
        timerWidget.stop();
        recorder.pause();
        ampliformWidget.stop();
        setDefaultStyle();
        setState("paused");
    }

    public void resume() {
        recorder.resume();
        timerWidget.start();
        ampliformWidget.start();
        setRecordingStyle();
        setState("recording");
    }

    public void save() {
        recorder.save();
        fileWidget.save();
        timerWidget.stop();
        ampliformWidget.stop();
        ampliformWidget.show();
        setDefaultStyle();
        meterWidget.hide();
        setState("saved");
    }

    public void reset() {
        recorder.destroy();
        fileWidget.destroy();
        startNewRecording();
    }

    public void rename() {
        fileWidget.startRenaming();
        ampliformWidget.hide();
        timerWidget.hide();
        setState("rename");
    }

    public void renameDone() {
        ampliformWidget.show();
        timerWidget.show();
        setState("saved");
    }

    public void open() {}

    public void play() {}

}
