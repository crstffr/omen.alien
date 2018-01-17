package omen.alien.layout.editor.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.Widget;
import omen.alien.component.layer.Layer;
import omen.alien.component.MajorLayout;

public class EditorMessageWidget extends Widget {

    int h = 100;
    int w = App.stage.w;
    int x = App.stage.centerX(w);
    int y = App.stage.centerY(h);

    int fontSize = 110;
    Layer tmpLayer;

    public EditorMessageWidget(MajorLayout _parent) {

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

    public void setSize(int size) {
        fontSize = size;
    }

    void redrawMessage() {
        if (text.length() > 0) {
            tmpLayer.init();
            tmpLayer.canvas.fill(color);
            tmpLayer.canvas.textFont(App.font, fontSize);
            tmpLayer.canvas.textAlign(Const.CENTER, Const.CENTER);
            tmpLayer.canvas.text(text, tmpLayer.mid_x, tmpLayer.mid_y);
            tmpLayer.canvas.endDraw();
        } else {
            tmpLayer.clear();
        }
    }

}
