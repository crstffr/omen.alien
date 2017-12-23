package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.layer.StageLayer;

public class Waveform extends Visualizer {

    public int channel = 1;
    float weight = (float)1;
    public boolean locked = false;
    public boolean falling = false;

    public Waveform() {
        super(new StageLayer(Const.RENDERER3D));
    }

    public void toggleLock() {
        locked = !locked;
    }

    public void toggleFalling() {
        falling = !falling;
    }

    public void toggleChannel() {
        channel = (channel == 1) ? 2 : 1;
    }

    public void draw() {

        float[] set;

        if (showing) {

            if (left == null) {
                return;
            }

            layer.init();

            set = (channel == 1) ? left : right;

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
                nextSamp = set[i + 2];

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
