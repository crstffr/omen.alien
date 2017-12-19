package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.layer.StageLayer;

public class Waveform extends Visualizer {

    public int trigger = 2;
    float weight = (float)2;
    public boolean locked = false;
    public boolean falling = false;

    public Waveform() {
        super(new StageLayer(Const.RENDERER3D));
    }

    public boolean toggleLock() {
        locked = !locked;
        return locked;
    }

    public boolean toggleFalling() {
        falling = !falling;
        return falling;
    }

    public void decreaseTrigger() {
        if (trigger > 2) {
            trigger = trigger - 2;
        }
    }

    public void increaseTrigger() {
        if (trigger < 64) {
            trigger = trigger + 2;
        }
    }

    public synchronized void draw() {

        if (showing) {

            if (left == null) {
                return;
            }

            float[] set;
            if (attachedTo.left.level() < attachedTo.right.level()) {
                set = right;
            } else {
                set = left;
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

            for (int i = 0; i < set.length - 1 && i < layer.w + zc; i++) {

                currSamp = set[i];
                nextSamp = set[i + trigger];

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
