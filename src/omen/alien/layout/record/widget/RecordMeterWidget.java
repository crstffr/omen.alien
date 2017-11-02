package omen.alien.layout.record.widget;

import ddf.minim.AudioInput;
import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.VUMeter;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

/**
 * Created by crstffr on 11/1/17.
 */
public class RecordMeterWidget extends RecordWidget {

    int p = 15;
    int h = 80;
    int w = (Const.WIDTH / 4) * 3;
    int x = App.stage.centerX(w);
    int y = App.stage.centerY(h) + 100;

    VUMeter meterL;
    VUMeter meterR;
    AudioInput input = App.audioInput;

    public RecordMeterWidget(RecordLayout _parent) {
        parent = _parent;
        init(x, y, w, h);

        int meterH = (h / 2) - p;
        meterL = new VUMeter(1, x, y, w, meterH);
        meterR = new VUMeter(2, x, y + meterH + p, w, meterH);

        onEnable(() -> {
            meterL.attachToInput(input).startCapture().show();
            meterR.attachToInput(input).startCapture().show();
        });

        onDisable(() -> {
            meterL.stopCapture().detachInput().hide();
            meterR.stopCapture().detachInput().hide();
        });

        onReset(() -> {
            meterL.reset();
            meterR.reset();
        });

        onShow(() -> {
            meterL.show();
            meterR.show();
        });

        onHide(() -> {
            meterL.hide();
            meterR.hide();
        });

        onDraw(() -> {
            meterL.draw();
            meterR.draw();
        });
    }

    public void holdClip() {
        meterL.clipHold = true;
        meterR.clipHold = true;
    }

    public void start() {
        meterL.startCapture();
        meterR.startCapture();
    }

    public void stop() {
        meterL.stopCapture();
        meterR.stopCapture();
    }

}
