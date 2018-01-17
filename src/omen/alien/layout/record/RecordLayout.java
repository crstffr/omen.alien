package omen.alien.layout.record;

import omen.alien.*;
import omen.alien.definition.WsMessage;
import omen.alien.layout.editor.EditorLayout;
import omen.alien.layout.record.state.*;
import omen.alien.layout.record.widget.*;
import omen.alien.component.*;

public class RecordLayout extends MajorLayout {

    String sampleId;
    boolean saving;
    
    // Widgets

    RecordFileWidget fileWidget;
    RecordMeterWidget meterWidget;
    RecordTimerWidget timerWidget;
    RecordHeaderWidget headerWidget;
    RecordMessageWidget messageWidget;
    RecordWaveformWidget waveformWidget;

    public RecordLayout() {
        super();
        color = Const.RED;

        setupWidgets();
        setupStates();
        startNewRecording();

        onEnable(() -> {
            startNewRecording();
        });

        onDisable(() -> {
            timerWidget.reset();
            fileWidget.destroy();
            if (App.recordingClient.busy) {
                App.recordingClient.discard();
            }
        });

        onDraw(() -> {
            timerWidget.run();
        });
    }

    void setupWidgets() {
        waveformWidget = new RecordWaveformWidget(this);
        messageWidget = new RecordMessageWidget(this);
        headerWidget = new RecordHeaderWidget(this);
        timerWidget = new RecordTimerWidget(this);
        meterWidget = new RecordMeterWidget(this);
        fileWidget = new RecordFileWidget(this);

        widgets.put("waveform", waveformWidget);
        widgets.put("message", messageWidget);
        widgets.put("file", fileWidget);
        widgets.put("timer", timerWidget);
        widgets.put("meter", meterWidget);
        widgets.put("header", headerWidget);

        headerWidget.setColor(Const.RED).show();
        messageWidget.setColor(Const.RED);
        fileWidget.setColor(Const.WHITE);
        timerWidget.setColor(Const.WHITE);
    }

    void setupStates() {
        states.put("ready", new RecordStateReady(this));
        states.put("saved", new RecordStateSaved(this));
        states.put("rename", new RecordStateRename(this));
        states.put("saving", new RecordStateSaving(this));
        states.put("waiting", new RecordStateWaiting(this));
        states.put("playing", new RecordStatePlaying(this));
        states.put("recording", new RecordStateRecording(this));
    }

    public void setHeader(String _text) {
        headerWidget.setText(_text);
    }

    public void setMessage(String _text) {
        messageWidget.setText(_text);
    }

    public void startNewRecording() {
        waveformWidget.reset().hide();
        fileWidget.reset().hide();
        meterWidget.reset().show();
        timerWidget.reset().hide();
        messageWidget.reset().hide();
        meterWidget.start();
        sampleId = null;
        setState("ready");
    }

    public void toggleRecord() {
        if (stateIs("recording")) {
            save();
        } else if (!stateIs("waiting")) {
            startNewRecording();
            record();
        }
    }

    public void record() {
        messageWidget.show();
        headerWidget.hide();
        timerWidget.hide();
        meterWidget.hide();
        setState("waiting");
        App.recordingClient.record(2, 44100, () -> {
            timerWidget.show();
            timerWidget.start();
            headerWidget.show();
            meterWidget.holdClip();
            meterWidget.show();
            messageWidget.hide();
            setState("recording");
        });
    }

    public void save() {
        messageWidget.show();
        headerWidget.hide();
        meterWidget.stop();
        meterWidget.hide();
        timerWidget.stop();
        timerWidget.hide();
        setState("saving");
        App.recordingClient.save(fileWidget.text, () -> {
            WsMessage recording = App.recordingClient.getResult();
            sampleId = recording.id;
            App.waveformClient.generateDat(sampleId, () -> {
                Integer zoomLevel = 1;
                App.waveformClient.generatePng(sampleId, zoomLevel, () -> {
                    WsMessage pngResult = App.waveformClient.pngResults.get(sampleId);
                    if (pngResult != null) {
                        waveformWidget.load(pngResult.path);
                        waveformWidget.show();
                    }
                    messageWidget.hide();
                    headerWidget.show();
                    fileWidget.save();
                    fileWidget.show();
                    setState("saved");
                });
            });
        });
    }

    public void reset() {
        App.recordingClient.discard();
        waveformWidget.clear();
        fileWidget.destroy();
        startNewRecording();
    }

    public void rename() {
        fileWidget.input.startCapture();
        setState("rename");
    }

    public void renameDone() {
        App.databaseClient.renameSample(sampleId, fileWidget.text, () -> {});
        setState("saved");
    }

    public void edit() {
        ((EditorLayout) App.router.switchLayout("editor")).loadSample(sampleId);
    }

    public void play() {}

    public void stopPlaying() {}

    public void monitor() {}

}
