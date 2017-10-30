package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.View;
import omen.alien.component.layer.BaseLayer;
import omen.alien.component.layer.ChildLayer;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordHeaderWidget extends RecordWidget {

    int h = 36;
    int w = 236;
    int x = App.stage.centerX(w);
    int y = App.stage.centerY(h) - 100;

    BaseLayer tmpLayer;

    public RecordHeaderWidget(RecordLayout _parent) {

        parent = _parent;
        tmpLayer = new ChildLayer(App.stage, x, y, w, h);

        init(x, y, w, h);

        onEnable(() -> {
            redrawHeader();
        });

        onSetText(() -> {
            redrawHeader();
        });

        onDraw(() -> {
            layer.init();
            layer.copy(tmpLayer);
            layer.draw();
        });
    }

    void redrawHeader() {
        if (text.length() > 0) {
            tmpLayer.init();
            tmpLayer.canvas.fill(color);
            tmpLayer.canvas.textFont(App.font, 48);
            tmpLayer.canvas.textAlign(Const.CENTER, Const.CENTER);
            tmpLayer.canvas.text(text, tmpLayer.mid_x, tmpLayer.mid_y);
            tmpLayer.canvas.endDraw();
        } else {
            tmpLayer.clear();
        }
    }
}
