package omen.alien.layout;

import omen.alien.component.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ScopeLayout extends Layout {

    /**
     * Oscilloscope layout
     *
     * @param _title
     * @param _buttonRow
     */
    public ScopeLayout(Title _title, ButtonRow _buttonRow) {
        super(_title, _buttonRow);
    }

    /**
     * Whether to psudo-lock the oscilloscope using zero-crossing
     */
    boolean locked = false;

    /**
     *
     */
    public void toggleLock() {
        locked = !locked;
        changed = true;
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
