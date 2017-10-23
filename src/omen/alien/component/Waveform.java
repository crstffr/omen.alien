package omen.alien.component;

public class Waveform extends Visualizer {

    public boolean locked = false;

    public Waveform(View _view) {
        super(_view);
    }

    public boolean toggleLock() {
        locked = !locked;
        return locked;
    }

    public synchronized void draw() {

        if (enabled) {

            view.clear();

            // Sample values
            float currSamp;
            float nextSamp;

            // Zero-cross point
            int zc = 0;

            // Position and scale
            int centerLine = view.mid_y;
            int sampleMult = view.mid_y;

            if (left == null) {
                return;
            }

            view.layer.noFill();
            view.layer.stroke(color);
            view.layer.strokeWeight(2);
            view.layer.background(0x00010101);

            for (int i = 0; i < left.length - 1 && i < view.w + zc; i++) {

                currSamp = left[i];
                nextSamp = left[i + 1];

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
}
