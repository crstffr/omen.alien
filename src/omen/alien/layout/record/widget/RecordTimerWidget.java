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
    int x = App.stage.view.centerX(w);
    int y = App.stage.view.centerY(h) + 50;

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
            view.layer.clear();
            view.layer.fill(color);
            view.layer.textFont(App.font, 28);
            view.layer.textAlign(Const.CENTER, Const.CENTER);
            view.layer.text(timeCounter.toString(), view.mid_x, view.mid_y);
        });

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
