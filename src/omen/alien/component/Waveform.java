package omen.alien.component;

import omen.alien.Const;
import processing.core.PConstants;

public class Waveform extends Visualizer {

    public boolean locked = false;
    public boolean falling = false;

    float weight = (float)2;

    public Waveform() {
        super(new StageLayer(Const.P3D));
    }

    public boolean toggleLock() {
        locked = !locked;
        return locked;
    }

    public boolean toggleFalling() {
        falling = !falling;
        return falling;
    }

    public synchronized void draw() {

        if (enabled) {

            if (left == null) {
                return;
            }

            layer.init();

            // Sample values
            float currSamp;
            float nextSamp;

            // Zero-cross point
            int zc = 0;

            // Position and scale
            int centerLine = layer.mid_y;
            int sampleMult = layer.mid_y;

            layer.canvas.stroke(color);
            layer.canvas.strokeWeight(weight);
            layer.canvas.beginShape();

            for (int i = 0; i < left.length - 1 && i < layer.w + zc; i++) {

                currSamp = left[i];
                nextSamp = left[i + 1];

                if (falling) {
                    // Falling Trigger
                    if (zc == 0 && currSamp > 0 && nextSamp < 0) {
                        zc = i;
                    }
                } else {
                    // Rising Trigger
                    if (zc == 0 && currSamp < 0 && nextSamp > 0) {
                        zc = i;
                    }
                }

                float x1 = (locked) ? i - zc : i;
                float y1 = centerLine + currSamp * sampleMult;

                if (!locked || (locked && zc != 0)) {
                    layer.canvas.vertex(x1, y1);
                }

            }

            layer.canvas.endShape();
            layer.canvas.noStroke();
            layer.draw();

        }
    }
}
