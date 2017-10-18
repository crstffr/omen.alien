package omen.alien.layout;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ScopeLayout extends Layout {

    /**
     *
     */
    public void onEnable() {
        App.title.setColor(Const.PRIMARY).draw();
        App.buttonRow.setColor(Const.PRIMARY).draw();
        App.waveform.setColor(Const.PRIMARY).draw();
    }

    /**
     *
     */
    public void onDisable() {
        App.waveform.clear();
    }

    /**
     *
     */
    public void afterFrame() {
        App.waveform.draw();
    }

    /**
     *
     * @param key what key was pressed
     */
    public void keyPressed(char key) {
        switch (key) {
            case 'a':
                App.waveform.toggleLock();
                changed = true;
                break;
        }
    }

    /**
     *
     * @return String
     */
    public String getTitle() {
        return "SCOPE";
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getButtonLabels() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add((App.waveform.locked) ? "LOCKED" : "UNLOCKED");
        labels.add("-");
        labels.add("-");
        labels.add("-");
        return labels;
    }

}
