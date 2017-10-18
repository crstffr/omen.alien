package omen.alien.component;

import omen.alien.*;
import processing.core.PGraphics;

public class Stage {

    public View view;

    int x = Const.STAGE_VIEW_X;
    int y = Const.STAGE_VIEW_Y;
    int w = Const.STAGE_VIEW_W;
    int h = Const.STAGE_VIEW_H;

    public Stage() {
        view = new View(x, y, w, h);
    }

    public PGraphics getLayer() {
        return view.layer;
    }

    public void clear() {
        view.clear();
    }

    public void draw() {
        view.clear();
        view.draw();
    }
}
