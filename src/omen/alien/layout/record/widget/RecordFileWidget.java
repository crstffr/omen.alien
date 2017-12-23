package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.UserInput;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

public class RecordFileWidget extends RecordWidget {

    int h = 26;
    int w = App.stage.w;
    int x = App.stage.centerX(w);
    int y = App.stage.centerY(h);

    String tmpName = "";
    public UserInput input = new UserInput();

    public RecordFileWidget(RecordLayout _parent) {

        parent = _parent;
        init(x, y, w, h);

        onEnable(() -> {
            setText(getFilename());
        });

        onReset(() -> {
            setText(getFilename());
        });

        onDraw(() -> {
            layer.init();
            layer.canvas.fill(color);
            layer.canvas.textFont(App.font, 28);
            layer.canvas.textAlign(Const.CENTER, Const.CENTER);
            layer.canvas.text(text, layer.mid_x, layer.mid_y);
            layer.draw();
        });

        // Handlers for Renaming

        input.onChange(() -> {
            tmpName = input.value;
            setText(tmpName);
        });

        input.onEnter(() -> {
            if (text.isEmpty()) {
                setText(getFilename());
            }
            input.stopCapture();
            parent.renameDone();
        });

        input.onEscape(() -> {
            setText(getFilename());
            input.stopCapture();
            parent.renameDone();
            tmpName = "";
        });

    }

    public String getFilename() {
        return String.format("%06d", App.fileCounter.getIndex());
    }

    public void save() {
        App.fileCounter.increment();
    }

    public void destroy() {

    }

}
