package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.Ampliform;
import omen.alien.component.Waveform;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordWaveformWidget extends RecordWidget {

    int x = 0;
    int y = 0;
    int w = App.stage.view.w;
    int h = App.stage.view.h;

    public Waveform waveform;

    public RecordWaveformWidget(RecordLayout _parent) {

        parent = _parent;
        init(x, y, w, h);
        waveform = new Waveform(view);

        onSetColor(() -> {
            waveform.setColor(color);
        });

        onEnable(() -> {
            waveform.enable();
        });

        onDisable(() -> {
            waveform.disable();
        });

        onReset(() -> {
            waveform.disable().clear();
        });

        onClear(() -> {
            waveform.clear();
        });

        onDraw(() -> {
            waveform.draw();
        });

    }

}
