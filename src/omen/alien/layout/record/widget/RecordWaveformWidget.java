package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.component.Waveform;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordWaveformWidget extends RecordWidget {

    int x = 0;
    int y = 0;
    int w = App.stage.w;
    int h = App.stage.h;

    public Waveform waveform;

    public RecordWaveformWidget(RecordLayout _parent) {

        parent = _parent;
        init(x, y, w, h);
        waveform = new Waveform();

        onSetColor(() -> {
            waveform.setColor(color);
        });

        onEnable(() -> {
            waveform.attachToInput(App.audioInput).show();
        });

        onDisable(() -> {
            waveform.detachInput().hide();
        });

        onReset(() -> {
            waveform.hide();
        });

        onDraw(() -> {
            waveform.draw();
        });

    }

}
