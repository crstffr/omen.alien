package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.layer.Layer;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;
import processing.core.PApplet;
import processing.core.PImage;

public class RecordWaveformWidget extends RecordWidget {

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
        tmpLayer.canvas.tint(255, 32);
        tmpLayer.canvas.image(img, x, y);
        tmpLayer.canvas.endDraw();
    }

}
