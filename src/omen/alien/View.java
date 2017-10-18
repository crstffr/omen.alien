package omen.alien;

import processing.core.*;

public class View {

    public PGraphics layer;
    PApplet process;

    int x;
    int y;
    int h;
    int w;

    public View(int _x, int _y, int _w, int _h) {

        process = App.inst;

        x = _x;
        y = _y;
        w = _w;
        h = _h;

        layer = process.createGraphics(w, h, Const.JAVA2D);
        layer.beginDraw();
        layer.noStroke();
        layer.noFill();
        layer.smooth();

    }

    public void draw() {
        layer.endDraw();
        process.image(layer, x, y, w, h);
        layer.beginDraw();
    }

    public void fillWith(int color) {
        layer.fill(color);
        layer.rect(0, 0, w, h);
        layer.endDraw();
        process.image(layer, x, y, w, h);
        layer.beginDraw();
    }

    public void clear() {
        layer.clear();
        layer.fill(0x00000000);
        layer.rect(0, 0, w, h);
        layer.endDraw();
        process.image(layer, x, y, w, h);
        layer.beginDraw();
    }

}