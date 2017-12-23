package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;
import omen.alien.util.TimeCounter;

public class RecordTimerWidget extends RecordWidget {

    TimeCounter timeCounter;

    int h = 26;
    int w = 320;
    int x = App.stage.centerX(w);
    int y = App.stage.centerY(h) + 115;

    public RecordTimerWidget(RecordLayout _parent) {

        parent = _parent;
        init(x, y, w, h);
        timeCounter = new TimeCounter();

        onDisable(() -> {
            timeCounter.disable();
        });

        onReset(() -> {
            timeCounter.reset();
        });

        onDraw(() -> {
            layer.init();
            layer.canvas.fill(color);
            layer.canvas.textFont(App.font, 28);
            layer.canvas.textAlign(Const.CENTER, Const.CENTER);
            layer.canvas.text(timeCounter.toString(), layer.mid_x, layer.mid_y);
            layer.draw();
        });

    }

    public void set(int millis) {
        timeCounter.set(millis);
    }

    public void start() {
        timeCounter.enable();
    }

    public void stop() {
        timeCounter.disable();
    }

    public void run() {
        timeCounter.run();
    }

}
