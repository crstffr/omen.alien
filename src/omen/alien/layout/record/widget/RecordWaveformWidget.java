package omen.alien.layout.record.widget;

import ddf.minim.AudioInput;
import omen.alien.App;
import omen.alien.component.Waveform;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordWaveformWidget extends RecordWidget {

    int x = 0;
    int y = 0;
    int w = App.stage.w;
    int h = App.stage.h;

    AudioInput input;
    public Waveform waveform;

    public RecordWaveformWidget(RecordLayout _parent) {

        parent = _parent;

        init(x, y, w, h);
        waveform = new Waveform();

        onSetColor(() -> {
            waveform.setColor(color);
        });

        onEnable(() -> {
            input = App.minim.getLineIn(2, 2048, 96000, 16);
            waveform.attachToInput(input).startCapture().show();
        });

        onDisable(() -> {
            waveform.stopCapture().hide();
            waveform.detachInput().clear();
            input.close();
            input = null;
        });

        onReset(() -> {
            waveform.hide();
        });

        onDraw(() -> {
            waveform.draw();
        });

    }

}
