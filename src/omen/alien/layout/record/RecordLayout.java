package omen.alien.layout.record;

import omen.alien.*;
import omen.alien.clients.RecordingClient;
import omen.alien.clients.WaveformClient;
import omen.alien.interf.WsMessage;
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
    RecordingClient recordingClient;
    WaveformClient waveformClient;

    // Widgets

    RecordFileWidget fileWidget;
    RecordMeterWidget meterWidget;
    RecordTimerWidget timerWidget;
    RecordHeaderWidget headerWidget;
    RecordWaveformWidget waveformWidget;

    // Layouts

    RecordStateReadyLayout readyLayout;
    RecordStateSavedLayout savedLayout;
    RecordStateRenameLayout renameLayout;
    RecordStateSavingLayout savingLayout;
    RecordStateWaitingLayout waitingLayout;
    RecordStatePlayingLayout playingLayout;
    RecordStateRecordingLayout recordingLayout;

    public RecordLayout() {
        super();
        color = Const.RED;
        states = new HashMap<>();
        widgets = new ArrayList<>();

        recordingClient = App.recordingClient;
        waveformClient = App.waveformClient;

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
            if (recordingClient.busy) {
                recordingClient.discard();
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
    }

    void setupWidgets() {
        waveformWidget = new RecordWaveformWidget(this);
        headerWidget = new RecordHeaderWidget(this);
        timerWidget = new RecordTimerWidget(this);
        meterWidget = new RecordMeterWidget(this);
        fileWidget = new RecordFileWidget(this);

        widgets.add(waveformWidget);
        widgets.add(fileWidget);
        widgets.add(timerWidget);
        widgets.add(meterWidget);
        widgets.add(headerWidget);

        headerWidget.setColor(Const.RED).show();
        fileWidget.setColor(Const.WHITE);
        timerWidget.setColor(Const.WHITE);
    }

    void setupStateLayouts() {
        readyLayout = new RecordStateReadyLayout(this);
        savedLayout = new RecordStateSavedLayout(this);
        renameLayout = new RecordStateRenameLayout(this);
        savingLayout = new RecordStateSavingLayout(this);
        waitingLayout = new RecordStateWaitingLayout(this);
        playingLayout = new RecordStatePlayingLayout(this);
        recordingLayout = new RecordStateRecordingLayout(this);

        states.put("ready", readyLayout);
        states.put("saved", savedLayout);
        states.put("rename", renameLayout);
        states.put("saving", savingLayout);
        states.put("waiting", waitingLayout);
        states.put("playing", playingLayout);
        states.put("recording", recordingLayout);
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
        waveformWidget.reset().hide();
        fileWidget.reset().hide();
        meterWidget.reset().show();
        timerWidget.reset().hide();
        setState("ready");
    }

    public void toggleRecord() {
        if (is("waiting")) {
            // do nothing.
        } else if (is("recording")) {
            save();
        } else {
            startNewRecording();
            record();
        }
    }

    public void record() {
        setState("waiting");
        recordingClient.record(2, 44100, () -> {
            timerWidget.show();
            timerWidget.start();
            meterWidget.holdClip();
            setState("recording");
        });
    }

    public void save() {
        meterWidget.hide();
        timerWidget.stop();
        timerWidget.hide();
        setState("saving");
        recordingClient.save(fileWidget.text, () -> {
            WsMessage recording = recordingClient.getResult();
            waveformClient.generateDat(recording.id, () -> {
                Integer zoomLevel = 1;
                waveformClient.generatePng(recording.id, zoomLevel, () -> {
                    WsMessage pngResult = waveformClient.pngResults.get(recording.id);
                    if (pngResult != null) {
                        waveformWidget.load(pngResult.path);
                        waveformWidget.show();
                    }
                    fileWidget.save();
                    fileWidget.show();
                    setState("saved");
                });
            });
        });
    }

    public void reset() {
        recordingClient.discard();
        waveformWidget.clear();
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
