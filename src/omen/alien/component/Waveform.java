package omen.alien.component;

import ddf.minim.AudioListener;
import omen.alien.App;
import omen.alien.Const;

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

        // Sample values
        float currSamp;
        float nextSamp;

        // Zero-cross point
        int zc = 0;

        // Position and scale
        int centerLine = view.mid_y;
        int sampleMult = view.mid_y;

        if (left == null) { return; }

        view.layer.strokeWeight(2);
        view.layer.stroke(color);

        for(int i = 0; i < left.length - 1 && i < view.w + zc; i++) {

            currSamp = left[i];
            nextSamp = left[i+1];

            if (zc == 0 && currSamp < 0 && nextSamp > 0) {
                zc = i;
            }

            float x1 = (locked) ? i - zc : i;
            float x2 = (locked) ? i + 1 - zc : i + 1;
            float y1 = centerLine + currSamp * sampleMult;
            float y2 = centerLine + nextSamp * sampleMult;

            if (!locked || (locked && zc != 0)) {
                view.layer.line(x1, y1, x2, y2);
            }

        }

        view.layer.noStroke();
        view.draw();
    }
}
