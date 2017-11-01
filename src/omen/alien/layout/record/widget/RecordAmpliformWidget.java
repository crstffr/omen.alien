package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.component.Ampliform;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordAmpliformWidget extends RecordWidget {

    public int y = 0;
    public int w = App.stage.w;
    public int h = App.stage.h;
    public int x = App.stage.mid_x;

    public Ampliform ampliform;
    public boolean clipped = false;
    public int clipThreshold = 98;

    public RecordAmpliformWidget(RecordLayout _parent) {

        parent = _parent;

        init(x, y, w, h);
        ampliform = new Ampliform();

        onSetColor(() -> {
            ampliform.setColor(color);
        });

        onEnable(() -> {
            ampliform.attachToInput(App.audioInput).show();
        });

        onDisable(() -> {
            ampliform.detachInput().stopCapture().hide().clear();
        });

        onReset(() -> {
            ampliform.stopCapture().clear();
            clipped = false;
        });

        onClear(() -> {
            ampliform.clear();
        });

        onDraw(() -> {
            ampliform.draw();
            if (ampliform.getMaxLevel() > clipThreshold) {
                clipped = true;
            }
        });
    }

    public void start() {
        ampliform.startCapture();
    }

    public void stop() {
        ampliform.stopCapture();
    }

}
