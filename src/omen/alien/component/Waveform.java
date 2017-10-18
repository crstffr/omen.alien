package omen.alien.component;

import ddf.minim.AudioListener;
import omen.alien.App;
import omen.alien.Const;
import omen.alien.View;

public class Waveform implements AudioListener {

    View view;
    int color;
    private float[] left;
    private float[] right;
    public boolean locked = false;

    public Waveform() {
        left = null;
        right = null;
        color = Const.WHITE;
        view = App.stage.view.createSubView(0,0);
    }

    public synchronized void samples(float[] _left) {
        left = _left;
    }

    public synchronized void samples(float[] _left, float[] _right) {
        left = _left;
        right = _right;
    }

    public void toggleLock() {
        locked = !locked;
    }

    public Waveform setColor(int _color) {
        color = _color;
        return this;
    }

    public void clear() {
        view.clear();
    }

    public synchronized void draw() {

        view.clear();

        float Li0;
        float Li1;
        int zc = 0;

        if (left == null) { return; }

        view.layer.noFill();
        view.layer.stroke(color);
        view.layer.strokeWeight(2);

        for(int i = 0; i < left.length - 1 && i < view.w + zc; i++) {

            Li0 = left[i];
            Li1 = left[i+1];

            float multiplier = view.h / 2;

            if (zc == 0 && Li0 < 0 && Li1 > 0) {
                zc = i;
            }

            float x1 = (locked) ? i - zc : i;
            float y1 = view.h/2 + Li0 * multiplier;
            float x2 = (locked) ? i + 1 - zc : i + 1;
            float y2 = view.h/2 + Li1 * multiplier;

            if (!locked || (locked && zc != 0)) {
                view.layer.line(x1, y1, x2, y2);
            }

        }

        view.layer.noStroke();
        view.draw();
    }
}
