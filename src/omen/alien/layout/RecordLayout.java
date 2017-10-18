package omen.alien.layout;

import omen.alien.component.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RecordLayout extends Layout {

    /**
     * Record mode layout
     *
     * @param _title
     * @param _buttonRow
     */
    public RecordLayout(Title _title, ButtonRow _buttonRow) {
        super(_title, _buttonRow);
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
     * @param key what key was pressed
     */
    public void keyPressed(char key) {
        switch (key) {
            case 'a':

                break;
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getButtonLabels() {
        ArrayList<String> labels = new ArrayList<>();
        labels.add("STOP");
        labels.add("RETAKE");
        labels.add("-");
        labels.add("-");
        return labels;
    }

}
