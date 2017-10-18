package omen.alien.layout;

import omen.alien.Const;
import omen.alien.component.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ScopeLayout extends Layout {

    /**
     * Whether to psudo-lock the oscilloscope using zero-crossing
     */
    boolean locked = false;

    /**
     *
     */
    public void onEnable() {
        // stage.view.fillWith(Const.GREEN);
    }

    /**
     *
     */
    public void onDisable() {

    }

    /**
     *
     */
    public void toggleLock() {
        locked = !locked;
        changed = true;
    }

    /**
     *
     * @param key what key was pressed
     */
    public void keyPressed(char key) {
        switch (key) {
            case 'a':
                toggleLock();
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
        labels.add((locked) ? "LOCKED" : "UNLOCKED");
        labels.add("-");
        labels.add("-");
        labels.add("-");
        return labels;
    }

}
