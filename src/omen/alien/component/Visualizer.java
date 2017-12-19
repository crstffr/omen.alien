package omen.alien.component;

import ddf.minim.AudioInput;
import ddf.minim.AudioListener;
import omen.alien.component.layer.Layer;

/**
 * Created by crstffr on 10/22/17.
 */
public class Visualizer implements AudioListener {

    public int color;
    public Layer layer;
    public float[] left;
    public float[] right;

    public boolean showing;
    public boolean capturing;
    public AudioInput attachedTo;

    public Visualizer(Layer _layer) {
        layer = _layer;
        showing = false;
        capturing = false;
    }

    public void onSampled() {}

    public void samples(float[] _left) {
        samples(_left, null);
    }

    public void samples(float[] _left, float[] _right) {
        if (capturing) {
            left = _left;
            right = _right;
            onSampled();
        }
    }

    public Visualizer attachToInput(AudioInput _input) {
        detachInput();
        _input.addListener(this);
        attachedTo = _input;
        return this;
    }

    public Visualizer detachInput() {
        if (attachedTo != null) {
            attachedTo.removeListener(this);
            attachedTo = null;
        }
        return this;
    }

    public Visualizer setColor(int _color) {
        color = _color;
        return this;
    }

    public Visualizer startCapture() {
        capturing = true;
        return this;
    }

    public Visualizer stopCapture() {
        capturing = false;
        return this;
    }

    public Visualizer show() {
        showing = true;
        return this;
    }

    public Visualizer hide() {
        showing = false;
        return this;
    }

    public Visualizer clear() {
        layer.clear();
        return this;
    }

    public synchronized void draw() {}

}
