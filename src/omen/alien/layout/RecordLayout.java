package omen.alien.layout;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.View;
import omen.alien.component.*;
import omen.alien.util.Counter;
import processing.core.PGraphics;

import java.util.ArrayList;

public class RecordLayout extends Layout {

    Counter counter;
    View header;
    View timer;

    int headerX;
    int headerY;
    int headerW;
    int headerH;
    int timerX;
    int timerY;
    int timerW;
    int timerH;

    boolean recording = false;

    public RecordLayout() {

        counter = new Counter();

        headerX = App.stage.view.x;
        headerY = App.stage.view.h / 5;
        headerW = App.stage.view.w;
        headerH = headerY;

        header = new View(headerX, headerY, headerW, headerH);

        timerX = headerX;
        timerY = headerY * 2;
        timerW = headerW;
        timerH = headerY;

        timer = new View(timerX, timerY, timerW, timerH);
    }

    /**
     *
     */
    public void onEnable() {
        counter.reset();
        drawHeader();
        drawTimer();
        start();
    }

    public void onDisable() {
        counter.stop();
        header.clear();
        timer.clear();
    }

    public void onFrame() {
        if (recording) {
            counter.run();
            drawHeader();
            drawTimer();
        }
    }

    void start() {
        recording = true;
        counter.start();
    }

    void stop() {
        recording = false;
        counter.stop();
    }

    void reset() {
        counter.reset();
    }

    void drawTimer() {
        String time = counter.toString();
        int x = timerW / 2;
        int y = timerH / 2;
        timer.clear();
        timer.layer.fill(Const.WHITE);
        timer.layer.textFont(App.font);
        timer.layer.textSize(34);
        timer.layer.textAlign(Const.CENTER, Const.CENTER);
        timer.layer.text(time, x, y);
        timer.draw();
    }

    public void drawHeader() {
        int x = headerW / 2;
        int y = headerH / 2;
        header.layer.fill(Const.RED);
        header.layer.textFont(App.font);
        header.layer.textSize(48);
        header.layer.textAlign(Const.CENTER, Const.CENTER);
        header.layer.text("RECORDING", x, y);
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
        return "";
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
