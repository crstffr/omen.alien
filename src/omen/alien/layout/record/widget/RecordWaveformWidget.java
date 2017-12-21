package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;
import processing.core.PApplet;
import processing.core.PImage;

public class RecordWaveformWidget extends RecordWidget {

    PImage img = null;
    public int x = App.stage.x;
    public int y = App.stage.y;
    public int w = App.stage.w;
    public int h = App.stage.h;

    public RecordWaveformWidget(RecordLayout _parent) {

        parent = _parent;
        init(x, y, w, h);

        onClear(() -> {
            img = null;
        });

        onDraw(() -> {
            if (img != null) {
                layer.init();
                layer.canvas.tint(255, 32);
                layer.canvas.image(img, x, y);
                layer.draw();
            }
        });
    }

    public void load(String path) {
        Double val = 0.1;
        img = App.inst.loadImage(path);
        img.filter(PApplet.THRESHOLD, val.floatValue());
    }

}
