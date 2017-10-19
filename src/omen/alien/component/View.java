package omen.alien.component;

import java.util.ArrayList;

import omen.alien.App;
import omen.alien.Const;
import processing.core.PGraphics;

public class View {

    public PGraphics layer;
    ArrayList<View> views;

    public int x;
    public int y;
    public int h;
    public int w;

    public int mid_x;
    public int mid_y;

    public View(int _x, int _y, int _w, int _h) {


        layer = App.inst.createGraphics(_w, _h, Const.JAVA2D);
        views = new ArrayList<>();
        position(_x, _y, _w, _h);

        layer.beginDraw();
        layer.noStroke();
        layer.noFill();
        layer.smooth();
        layer.clear();

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

    public void draw() {
        layer.endDraw();
        App.inst.image(layer, x, y, w, h);
        layer.beginDraw();
        layer.clear();
    }

    public void fillWith(int color) {
        layer.fill(color);
        layer.rect(0, 0, w, h);
        draw();
    }

    public void clear() {
        layer.clear();
        fillWith(0x00000000);
        // clear out subviews as well.
        for (View view : views) { view.clear(); }
    }

    public View createSubView(int _x, int _y) {
        return createSubView(_x, _y, w, h);
    }

    public View createSubView(int _x, int _y, int _w, int _h) {
        View view = new View(x + _x, y + _y, _w, _h);
        views.add(view);
        return view;
    }

}