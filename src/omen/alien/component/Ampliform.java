package omen.alien.component;

import omen.alien.Const;
import omen.alien.component.layer.StageLayer;
import omen.alien.App;
import processing.core.PConstants;

import java.util.ArrayList;

public class Ampliform extends Visualizer {

    float maxLevel = 0;
    float trigger;
    float centerLine;
    ArrayList<Float> values;

    public Ampliform() {
        super(new StageLayer(Const.P3D));
        values = new ArrayList<>();
        centerLine = layer.mid_y;
        trigger = (float) (layer.h / 1.28);
    }

    public synchronized void onSampled() {
        //captureMax();
        captureSample();
    }

    void captureSample() {
        values.add(App.audioInput.mix.level());
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
        return this;
    }

    public synchronized void draw() {

        if (showing) {

            layer.init();
            layer.canvas.stroke(color);

            int size = values.size();

            if (size > 2) {

                float space = (float) layer.w / (size - 1);

                if (size < 100) {
                    layer.canvas.beginShape();
                } else {
                    layer.canvas.strokeWeight(space / (float) 1.25);
                    layer.canvas.beginShape(PConstants.LINES);
                }

                for (int i = 0; i < size; i++) {

                    float val = values.get(i) * trigger;

                    float x1 = i * space;
                    float x2 = (i + 1) * space;
                    float y1 = centerLine - val;
                    float y2 = centerLine + val;

                    layer.canvas.vertex(x1, y1);
                    layer.canvas.vertex(x1, y2);

                }

                layer.canvas.endShape();

            }

            layer.canvas.noStroke();
            layer.canvas.noFill();
            layer.draw();

        }

    }

}
