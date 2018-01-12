package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import processing.core.PImage;
import processing.core.PApplet;
import omen.alien.component.Widget;
import omen.alien.component.layer.Layer;
import omen.alien.layout.record.RecordLayout;

public class RecordWaveformWidget extends Widget {

    Layer tmpLayer;

    public int x = App.stage.x;
    public int y = App.stage.y;
    public int w = App.stage.w;
    public int h = App.stage.h;

    public RecordWaveformWidget(RecordLayout _parent) {

        parent = _parent;

        init(x, y, w, h);

        tmpLayer = layer.copy();

        onClear(() -> {
            tmpLayer.clear();
        });

        onDraw(() -> {
            if (tmpLayer != null) {
                layer.init();
                layer.fillFrom(tmpLayer);
                layer.draw();
            }
        });
    }

    public void load(String path) {
        Double val = 0.1; // threshold val
        PImage img = App.inst.loadImage(path);
        img.filter(PApplet.THRESHOLD, val.floatValue());

        tmpLayer.init();
        tmpLayer.canvas.tint(255, 64);
        tmpLayer.canvas.image(img, x, y);
        tmpLayer.canvas.endDraw();
    }

}
