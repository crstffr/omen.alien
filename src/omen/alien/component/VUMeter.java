package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.layer.Layer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by crstffr on 11/1/17.
 */
public class VUMeter extends Visualizer {

    Timer timer;
    int channel;
    float curLevel = 0;
    float maxLevel = 0;
    public boolean clipped;
    public boolean clipHold;

    public VUMeter(int _channel, int _x, int _y, int _w, int _h) {
        super(new Layer(_x, _y, _w, _h, Const.RENDERER3D));
        channel = _channel;
        clipHold = false;
        clipped = false;
    }

    void calcLevels(float[] vals) {
        curLevel = 0;
        for (int i = 0; i < vals.length; i++) {
            float val = Math.abs(vals[i]);
            if (val > curLevel) {
                curLevel = val;
            }
            if (val > maxLevel) {
                maxLevel = val;
            }
        }
    }

    @Override
    public void onSampled() {
        switch (channel) {
            case 1:
                if (left != null) {
                    calcLevels(left);
                }
                break;
            case 2:
                if (right != null) {
                    calcLevels(right);
                }
                break;
        }
    }

    public void reset() {
        maxLevel = 0;
        curLevel = 0;
        clipped = false;
        clipHold = false;
    }

    public void draw() {
        if (showing) {

            layer.init();

            int o = 20; // L/R char offset
            int t = 56; // clip text width
            int h = layer.h;
            int w = layer.w - o - t;
            int color = 0;

            layer.canvas.fill(255);
            layer.canvas.textFont(App.font, 22);
            layer.canvas.textAlign(Const.LEFT, Const.CENTER);
            layer.canvas.text(channel == 1 ? "L" : "R", 0, h / 2);

            layer.canvas.fill(32);
            layer.canvas.stroke(128);
            layer.canvas.rect(o, 0, w, h);
            layer.canvas.noStroke();

            if (curLevel > 0.99) {
                color = Const.RED;
                triggerClip();
            } else if (curLevel > 0.92) {
                color = Const.GREEN;
            } else {
                color = 128;
            }

            layer.canvas.fill(color);
            layer.canvas.rect(o, 0, w * curLevel, h);

            layer.canvas.fill(clipped ? Const.RED : 64);
            layer.canvas.textFont(App.font, 22);
            layer.canvas.textAlign(Const.LEFT, Const.CENTER);
            layer.canvas.text("CLIP", w + o + 8, h/2);

            layer.draw();
        }
    }

    void triggerClip() {
        clipped = true;
        if (!clipHold) {
            if (timer != null) {
                timer.cancel();
            }
            timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    clipped = false;
                }
            }, 1500);
        }
    }

}
