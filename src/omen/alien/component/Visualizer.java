package omen.alien.component;

import ddf.minim.AudioInput;
import ddf.minim.AudioListener;
import omen.alien.component.layer.BaseLayer;

/**
 * Created by crstffr on 10/22/17.
 */
public class Visualizer implements AudioListener {

    public int color;
    public BaseLayer layer;
    public float[] left;
    public float[] right;
    public boolean enabled;

    public Visualizer(BaseLayer _layer) {
        layer = _layer;
    }

    public void onSampled() {}

    public void samples(float[] _left) {
        samples(_left, null);
    }

    public void samples(float[] _left, float[] _right) {
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
        layer.clear();
        return this;
    }

    public void draw() {}

}
