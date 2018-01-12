package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.Widget;
import omen.alien.component.layer.Layer;
import omen.alien.layout.record.RecordLayout;

public class RecordHeaderWidget extends Widget {

    int h = 50;
    int w = App.stage.w;
    int x = App.stage.centerX(w);
    int y = App.stage.centerY(h) - 100;

    Layer tmpLayer;

    public RecordHeaderWidget(RecordLayout _parent) {

        parent = _parent;

        init(x, y, w, h);

        tmpLayer = layer.copy();

        onEnable(() -> {
            redrawHeader();
        });

        onSetText(() -> {
            redrawHeader();
        });

        onDraw(() -> {
            layer.init();
            layer.fillFrom(tmpLayer);
            layer.draw();
        });
    }

    void redrawHeader() {
        if (text.length() > 0) {
            tmpLayer.init();
            tmpLayer.canvas.fill(color);
            tmpLayer.canvas.textFont(App.font, 60);
            tmpLayer.canvas.textAlign(Const.CENTER, Const.CENTER);
            tmpLayer.canvas.text(text, tmpLayer.mid_x, tmpLayer.mid_y);
            tmpLayer.canvas.endDraw();
        } else {
            tmpLayer.clear();
        }
    }
}
