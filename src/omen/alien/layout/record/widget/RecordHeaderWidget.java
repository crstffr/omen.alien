package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.View;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordHeaderWidget extends RecordWidget {

    int h = 36;
    int w = 236;
    int x = App.stage.view.centerX(w);
    int y = App.stage.view.centerY(h) - 100;

    public RecordHeaderWidget(RecordLayout _parent) {

        parent = _parent;

        init(x, y, w, h);

        onDraw(() -> {
            if (text.length() > 0) {
                view.layer.fill(color);
                view.layer.textFont(App.font, 48);
                view.layer.textAlign(Const.CENTER, Const.CENTER);
                view.layer.text(text, view.mid_x, view.mid_y);
            } else {
                view.clear();
            }
        });
    }
}
