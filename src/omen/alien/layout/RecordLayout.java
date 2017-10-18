package omen.alien.layout;

import omen.alien.Const;
import omen.alien.component.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RecordLayout extends Layout {

    /**
     *
     */
    public void onEnable() {
        // stage.view.fillWith(Const.RED);
    }

    /**
     *
     */
    public void onDisable() {

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
