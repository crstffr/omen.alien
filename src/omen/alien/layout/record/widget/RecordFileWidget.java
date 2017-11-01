package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.UserInput;
import omen.alien.component.layer.Layer;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;
import omen.alien.util.FileCounter;

import java.io.File;

public class RecordFileWidget extends RecordWidget {

    int h = 26;
    int w = App.stage.w;
    int x = App.stage.centerX(w);
    int y = App.stage.centerY(h) - 50;

    Layer tmpLayer;
    String tmpname = "";
    String filename = "";
    String filepath = "";
    UserInput userInput = new UserInput();
    FileCounter fileCounter = new FileCounter();

    public RecordFileWidget(RecordLayout _parent) {

        parent = _parent;

        init(x, y, w, h);

        tmpLayer = layer.copy();

        onEnable(() -> {
            buildTempFilepath();
        });

        onSetColor(() -> {
            redrawFilename();
        });

        onSetText(() -> {
            redrawFilename();
        });

        onDisable(() -> {
            // stopRenaming();
        });

        onDraw(() -> {
            layer.init();
            layer.fillFrom(tmpLayer);
            layer.draw();
        });

        userInput.onEnter(() -> {
            saveFilename();
            stopRenaming();
        });

        userInput.onEscape(() -> {
            resetFilename();
            stopRenaming();
        });

        userInput.onChange(() -> {
            setFilename(userInput.value);
        });

    }

    void redrawFilename() {
        tmpLayer.init();
        tmpLayer.canvas.fill(color);
        tmpLayer.canvas.textFont(App.font, 28);
        tmpLayer.canvas.textAlign(Const.CENTER, Const.CENTER);
        tmpLayer.canvas.text(text, tmpLayer.mid_x, tmpLayer.mid_y);
        tmpLayer.canvas.endDraw();
    }

    public void startRenaming() {
        userInput.startCapture();
    }

    public void stopRenaming() {
        userInput.stopCapture();
        parent.renameDone();
    }

    public String buildTempFilename() {
        filename = String.format("%06d", fileCounter.getIndex()) + ".wav";
        setText(filename);
        return filename;
    }

    public String buildTempFilepath() {
        filepath = Const.SAMPLE_TEMP_PATH + buildTempFilename();
        return filepath;
    }

    public void setFilename(String _tmpname) {
        tmpname = _tmpname;
        setText(_tmpname + ".wav");
    }

    public void saveFilename() {
        rename(tmpname + ".wav");
    }

    public void resetFilename() {
        tmpname = "";
        setText(filename);
    }

    public void rename(String _filename) {
        File f = new File(filepath);
        String newpath = Const.SAMPLE_USER_PATH + _filename;
        f.renameTo(new File(newpath));
        filename = _filename;
        filepath = newpath;
    }

    public void save() {
        rename(filename);
        fileCounter.increment();
    }

    public void destroy() {
        File f = new File(filepath);
        if (f.exists()) {
            f.delete();
        }
    }

}
