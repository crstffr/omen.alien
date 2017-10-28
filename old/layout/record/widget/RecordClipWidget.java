package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.Ampliform;
import omen.alien.component.Waveform;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordClipWidget extends RecordWidget {

    int x = 0;
    int y = 0;
    int w = App.stage.view.w;
    int h = App.stage.view.h;

    boolean showing = false;

    public RecordClipWidget(RecordLayout _parent) {

        parent = _parent;
        init(x, y, w, h);

        onEnable(() -> {
            hide();
        });

        onDisable(() -> {
            hide();
        });

        onReset(() -> {
            hide();
        });

        onClear(() -> {
            hide();
        });

        onDraw(() -> {
            if (showing) {
                // view1.fillWith(Const.TRANSRED_DARK);
            }
        });
    }

    public void show() {
        showing = true;
    }

    public void hide() {
        showing = false;
    }

}
