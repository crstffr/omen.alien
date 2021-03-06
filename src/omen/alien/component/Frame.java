package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import processing.core.PGraphics;

import java.util.ArrayList;

public class Frame {

    PGraphics frame;
    ArrayList<PGraphics> layers;

    public Frame() {
        String r = Const.RENDERER2D;
        int w = App.inst.width;
        int h = App.inst.height;
        frame = App.inst.createGraphics(w, h, r);
    }

    public void init() {
        frame.beginDraw();
        frame.clear();
    }

    public void push(PGraphics _layer) {
        push(_layer, 0, 0);
    }

    public void push(PGraphics _layer, int _x, int _y) {
        App.inst.image(_layer.get(), _x, _y);
    }

    public void draw() {
        frame.endDraw();
        App.inst.image(frame.get(), 0, 0, App.inst.width, App.inst.height);
    }

}
