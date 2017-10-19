package omen.alien.component;

import ddf.minim.AudioListener;
import omen.alien.App;
import processing.core.PApplet;

import java.util.ArrayList;

public class Ampliform implements AudioListener{

    View view;
    int color;
    boolean enabled;
    private float[] left;
    private float[] right;
    ArrayList<Float> values;

    public Ampliform() {
        left = null;
        right = null;
        enabled = false;
        values = new ArrayList<>();
        view = App.stage.view.createSubView(App.stage.view.mid_x, 0);
    }

    public Ampliform enable() {
        enabled = true;
        return this;
    }

    public Ampliform disable() {
        enabled = false;
        return this;
    }

    public synchronized void samples(float[] _left) {
        left = _left;
        captureSample();
    }

    public synchronized void samples(float[] _left, float[] _right) {
        left = _left;
        right = _right;
        captureSample();
    }

    void captureSample() {
        if (enabled) {

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
    }

    public Ampliform setColor(int _color) {
        color = _color;
        return this;
    }

    public Ampliform clear() {
        view.clear();
        values.clear();
        view.position(App.stage.view.mid_x, 0);
        return this;
    }

    public synchronized void draw() {

        view.layer.stroke(color);
        view.layer.strokeWeight(1);

        float multiplier = view.h;
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
        view.draw();

    }

}
