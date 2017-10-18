package omen.alien.layout;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.View;
import omen.alien.component.*;
import omen.alien.util.Counter;
import processing.core.PGraphics;

import java.util.ArrayList;

public class RecordLayout extends Layout {

    boolean recording = false;
    Counter counter;
    View header;
    View timer;


    public RecordLayout() {

        counter = new Counter();

        int headerW = 236;
        int headerH = 36;
        int headerY = 80;
        int headerX = App.stage.view.centerX(headerW);

        int timerW = 320;
        int timerH = 26;
        int timerY = 130;
        int timerX = App.stage.view.centerX(timerW);

        timer = App.stage.view.createSubView(timerX, timerY, timerW, timerH);
        header = App.stage.view.createSubView(headerX, headerY, headerW, headerH);

    }

    /**
     *
     */
    public void onEnable() {
        App.title.setColor(Const.RED).draw();
        App.waveform.setColor(Const.RED).draw();
        App.buttonRow.setColor(Const.RED).draw();
        counter.reset();
        drawHeader();
        drawTimer();
        start();
    }

    public void onDisable() {
        App.waveform.clear();
        counter.stop();
        header.clear();
        timer.clear();
    }

    public void beforeFrame() {
        if (recording) {
            counter.run();
            App.waveform.draw();
        }
        drawHeader();
        drawTimer();
    }

    void start() {
        recording = true;
        counter.start();
    }

    void stop() {
        App.waveform.clear();
        recording = false;
        counter.stop();
    }

    void reset() {
        counter.reset();
    }

    void drawTimer() {
        timer.fillWith(Const.TRANSPARENT);
        timer.layer.fill(Const.RED);
        timer.layer.textFont(App.font);
        timer.layer.textSize(34);
        timer.layer.textAlign(Const.CENTER, Const.CENTER);
        timer.layer.text(counter.toString(), timer.mid_x, timer.mid_y);
        timer.draw();
    }

    public void drawHeader() {
        header.layer.fill(Const.RED);
        header.layer.textFont(App.font);
        header.layer.textSize(48);
        header.layer.textAlign(Const.CENTER, Const.CENTER);
        header.layer.text("RECORDING", header.mid_x, header.mid_y);
        header.draw();
    }


    /**
     *
     * @param key what key was pressed
     */
    public void keyPressed(char key) {
        switch (key) {
            case 'a':
                stop();
                break;
            case 'f':
                stop();
                start();
                break;
        }
    }

    /**
     *
     * @return String
     */
    public String getTitle() {
        return "RECORD";
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getButtonLabels() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("STOP");
        labels.add("-");
        labels.add("-");
        labels.add("AGAIN");
        return labels;
    }

}
