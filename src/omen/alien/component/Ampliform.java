package omen.alien.component;

import omen.alien.Const;
import omen.alien.component.layer.Layer;
import omen.alien.component.layer.StageLayer;
import omen.alien.App;
import processing.core.PConstants;

import java.util.ArrayList;

public class Ampliform extends Visualizer {

    float trigger;
    float centerLine;
    ArrayList<Float> values;

    public Ampliform(int _x, int _y, int _w, int _h) {
        super(new Layer(_x, _y, _w, _h, Const.RENDERER3D));
        values = new ArrayList<>();
        centerLine = layer.mid_y;
        trigger = (float) (layer.h / 1.28);
    }

    public void onSampled() {
        captureSample();
    }

    void captureSample() {
        values.add(attachedTo.mix.level());
    }

    public Visualizer clear() {
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

                if (size < 200) {
                    layer.canvas.beginShape();
                } else {
                    layer.canvas.beginShape(PConstants.LINES);
                }

                for (int i = 0; i < size; i++) {

                    float val = values.get(i) * trigger;

                    float x1 = i * space;
                    float x2 = (i + 1) * space;
                    float y1 = centerLine - val;
                    float y2 = centerLine + val;

                    if (Math.abs(y2 - y1) < 1) {
                        y1 -= 0.5;
                        y2 += 0.5;
                    }

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
