package omen.alien.layout.record.widget;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.UserInput;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.RecordWidget;

import java.io.File;

public class RecordFileWidget extends RecordWidget {

    int h = 26;
    int w = App.stage.view.w;
    int x = App.stage.view.centerX(w);
    int y = App.stage.view.centerY(h) - 50;

    String tmpname = "";
    String filename = "";
    String filepath = "";
    UserInput userInput = new UserInput();

    public RecordFileWidget(RecordLayout _parent) {

        parent = _parent;

        init(x, y, w, h);

        onEnable(() -> {
            buildTempFilepath();
        });

        onDisable(() -> {
            stopRenaming();
        });

        onDraw(() -> {
            view.layer.fill(color);
            view.layer.textFont(App.font, 28);
            view.layer.textAlign(Const.CENTER, Const.CENTER);
            view.layer.text(text, view.mid_x, view.mid_y);
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

    public void startRenaming() {
        userInput.startCapture();
    }

    public void stopRenaming() {
        userInput.stopCapture();
        parent.renameDone();
    }

    public String buildTempFilename() {
        filename = String.format("%06d", App.fileCounter.getIndex()) + ".wav";
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
        App.fileCounter.increment();
    }

    public void destroy() {
        File f = new File(filepath);
        if (f.exists()) {
            f.delete();
        }
    }

}
