package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.layer.Layer;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordMessageWidget extends RecordWidget {

    int h = 100;
    int w = App.stage.w;
    int x = App.stage.centerX(w);
    int y = App.stage.centerY(h);

    Layer tmpLayer;

    public RecordMessageWidget(RecordLayout _parent) {

        parent = _parent;

        init(x, y, w, h);

        tmpLayer = layer.copy();

        onEnable(() -> {
            redrawMessage();
        });

        onSetText(() -> {
            redrawMessage();
        });

        onDraw(() -> {
            layer.init();
            layer.fillFrom(tmpLayer);
            layer.draw();
        });
    }

    void redrawMessage() {
        if (text.length() > 0) {
            tmpLayer.init();
            tmpLayer.canvas.fill(color);
            tmpLayer.canvas.textFont(App.font, 110);
            tmpLayer.canvas.textAlign(Const.CENTER, Const.CENTER);
            tmpLayer.canvas.text(text, tmpLayer.mid_x, tmpLayer.mid_y);
            tmpLayer.canvas.endDraw();
        } else {
            tmpLayer.clear();
        }
    }

}
