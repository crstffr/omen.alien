package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.layer.Layer;

public class FPS {

    Layer layer;
    int x = Const.FPS_X;
    int y = Const.FPS_Y;
    int w = Const.FPS_W;
    int h = Const.FPS_H;

    public FPS() {
        layer = new Layer(x, y, w, h, Const.RENDERER2D);
    }

    public void draw() {
        layer.init();
        layer.canvas.stroke(255);
        layer.canvas.textFont(App.font, 18);
        layer.canvas.textAlign(Const.RIGHT, Const.TOP);
        layer.canvas.text(Math.round(App.inst.frameRate), w, 0);
        layer.draw();
    }

}
