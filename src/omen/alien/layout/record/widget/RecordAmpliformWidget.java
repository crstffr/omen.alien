package omen.alien.layout.record.widget;

import ddf.minim.AudioInput;
import omen.alien.App;
import omen.alien.component.Ampliform;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordAmpliformWidget extends RecordWidget {

    public int x = 0;
    public int y = App.stage.mid_y;
    public int w = App.stage.w;
    public int h = App.stage.h / 2;

    public Ampliform ampliform;
    AudioInput input = App.audioInput;

    public RecordAmpliformWidget(RecordLayout _parent) {

        parent = _parent;
        init(x, y, w, h);

        ampliform = new Ampliform(x, y, w, h);

        onSetColor(() -> {
            ampliform.setColor(color);
        });

        onEnable(() -> {
            ampliform.attachToInput(input);
        });

        onDisable(() -> {
            ampliform.stopCapture().hide();
            ampliform.detachInput().clear();
        });

        onShow(() -> {
            ampliform.show();
        });

        onHide(() -> {
            ampliform.hide();
        });

        onReset(() -> {
            ampliform.stopCapture().clear().hide();
        });

        onClear(() -> {
            ampliform.clear();
        });

        onDraw(() -> {
            ampliform.draw();
        });
    }

    public void start() {
        ampliform.startCapture();
    }

    public void stop() {
        ampliform.stopCapture();
    }

}
