package omen.alien.component;

import processing.core.PApplet;
import omen.alien.App;

import java.util.ArrayList;

public class Ampliform extends Visualizer {

    int i = 0;
    float maxLevel = 0;
    ArrayList<Float> values;

    public Ampliform(View _view) {
        super(_view);
        values = new ArrayList<>();
    }

    public void onSampled() {
        captureMax();
        captureSample();
    }

    void captureSample() {

        float lerpN = 3; // how many extra values to fill out the graph?

        float currValue = App.audioInput.mix.level();

        if (values.size() > 0) {
            float prevValue = values.get(values.size() - 1);
            for (float i = 1; i <= lerpN; i++) {
                float fauxValue = PApplet.lerp(prevValue, currValue, i / lerpN);
                values.add(fauxValue);
            }
        }

        values.add(currValue);

        if (values.size() > view.w) {
            for (int i = 0; i <= lerpN; i++) {
                values.remove(0);
            }
        }

        int x = App.stage.view.mid_x - (values.size() / 2);

        view.position(x, view.y);

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
        view.clear();
        values.clear();
        view.position(App.stage.view.mid_x, 0);
        return this;
    }

    public synchronized void draw() {

        view.layer.noFill();
        view.layer.stroke(color);
        view.layer.strokeWeight(1);
        view.layer.background(0x00010101);

        float multiplier = view.h / (float) 1.28;
        float centerLine = view.mid_y;

        int i = 0;

        for (Float value : values) {

            float val = value * multiplier;
            float x1 = i;
            float y1 = centerLine - val;
            float x2 = i;
            float y2 = centerLine + val;

            view.layer.line(x1, y1, x2, y2);

            i++;
        }

        view.layer.noStroke();
        view.layer.noFill();
        view.draw();

    }
}
