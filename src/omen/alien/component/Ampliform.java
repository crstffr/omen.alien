package omen.alien.component;

import omen.alien.Const;
import omen.alien.component.layer.StageLayer;
import processing.core.PApplet;
import omen.alien.App;

import java.util.ArrayList;

public class Ampliform extends Visualizer {

    int i = 0;
    float maxLevel = 0;
    ArrayList<Float> values;

    public Ampliform() {
        super(new StageLayer(Const.P3D));
        values = new ArrayList<>();
    }

    public void onSampled() {
        captureMax();
        captureSample();
    }

    void captureSample() {

        float lerpN = 0; // how many extra values to fill out the graph?

        float currValue = App.audioInput.mix.level();

        if (values.size() > 0) {
            float prevValue = values.get(values.size() - 1);
            for (float i = 1; i <= lerpN; i++) {
                float fauxValue = PApplet.lerp(prevValue, currValue, i / lerpN);
                values.add(fauxValue);
            }
        }

        values.add(currValue);

        if (values.size() > layer.w) {
            for (int i = 0; i <= lerpN; i++) {
                values.remove(0);
            }
        }

        int x = layer.mid_x - (values.size() / 2);

        layer.position(x, layer.y);

    }

    void captureMax() {
        float[] mixValues = App.audioInput.mix.toArray();
        for (int i = 0; i < mixValues.length; i++) {
            float currLevel = Math.abs(mixValues[i]) * 100;
            if (currLevel > maxLevel) {
                maxLevel = currLevel;
            }
        }
    }

    public float getMaxLevel() {
        return maxLevel;
    }

    public Visualizer clear() {
        maxLevel = 0;
        layer.clear();
        values.clear();
        layer.position(layer.mid_x, 0);
        return this;
    }

    public synchronized void draw() {

        layer.init();
        layer.canvas.stroke(color);
        layer.canvas.strokeWeight(1);
        layer.canvas.background(0x00010101);

        float trigger = layer.h / (float) 1.28;
        float centerLine = layer.mid_y;

        int i = 0;

        for (Float value : values) {

            float val = value * trigger;
            float x1 = i;
            float y1 = centerLine - val;
            float x2 = i;
            float y2 = centerLine + val;

            layer.canvas.line(x1, y1, x2, y2);

            i++;
        }

        layer.canvas.noStroke();
        layer.canvas.noFill();
        layer.draw();

    }

}
