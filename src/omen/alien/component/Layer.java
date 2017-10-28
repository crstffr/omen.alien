package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 * Created by crstffr on 10/28/17.
 */
public class Layer {

    public PGraphics canvas;

    public int x;
    public int y;
    public int w;
    public int h;
    public int mid_x;
    public int mid_y;

    public Layer(int _x, int _y, int _w, int _h, String _r) {
        canvas = App.inst.createGraphics(_w, _h, _r);
        position(_x, _y, _w, _h);
    }

    public void position(int _x, int _y) {
        position(_x, _y, w, h);
    }

    public void position(int _x, int _y, int _w, int _h) {
        x = _x;
        y = _y;
        w = _w;
        h = _h;
        mid_x = w / 2;
        mid_y = h / 2;
    }

    public int centerX(int _w) {
        return mid_x - (_w / 2);
    }

    public int centerY(int _h) {
        return mid_y - (_h / 2);
    }


    public synchronized void init() {
        canvas.beginDraw();
        canvas.clear();
        canvas.noStroke();
        canvas.noFill();
    }

    public synchronized void draw() {
        canvas.endDraw();
        App.inst.image(canvas, x, y, w, h);
    }

}
