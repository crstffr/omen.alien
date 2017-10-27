package omen.alien.component;

import ddf.minim.AudioInput;
import ddf.minim.AudioListener;

import java.util.ArrayList;

/**
 * Created by crstffr on 10/22/17.
 */
public class Visualizer implements AudioListener {

    public int color;
    public View view;
    public float[] left;
    public float[] right;
    public boolean enabled;

    public Visualizer(View _view) {
        view = _view;
    }

    public void onSampled() {}

    public synchronized void samples(float[] _left) {
        samples(_left, null);
    }

    public synchronized void samples(float[] _left, float[] _right) {
        if (enabled) {
            left = _left;
            right = _right;
            onSampled();
        }
    }

    public void attachToInput(AudioInput _input) {
        _input.addListener(this);
    }

    public void removeFromInput(AudioInput _input) {
        _input.removeListener(this);
    }

    public Visualizer setColor(int _color) {
        color = _color;
        return this;
    }

    public Visualizer enable() {
        enabled = true;
        return this;
    }

    public Visualizer disable() {
        enabled = false;
        return this;
    }

    public Visualizer clear() {
        view.clear();
        return this;
    }

    public synchronized void draw() {}

}
