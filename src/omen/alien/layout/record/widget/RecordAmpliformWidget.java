package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.Ampliform;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordAmpliformWidget extends RecordWidget {

    public int y = 0;
    public int w = App.stage.view.w;
    public int h = App.stage.view.h;
    public int x = App.stage.view.mid_x;

    public Ampliform ampliform;
    public boolean clipped = false;
    public int clipThreshold = 98;

    public RecordAmpliformWidget(RecordLayout _parent) {

        parent = _parent;

        init(x, y, w, h);
        ampliform = new Ampliform(view);

        onSetColor(() -> {
            ampliform.setColor(color);
        });

        onDisable(() -> {
            ampliform.disable();
        });

        onReset(() -> {
            ampliform.disable().clear();
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
        ampliform.enable();
    }

    public void stop() {
        ampliform.disable();
    }

}
