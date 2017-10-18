package omen.alien.component;

import java.util.ArrayList;
import java.util.Arrays;
import omen.alien.App;

public class Layout {

    public boolean changed = false;
    public boolean enabled = false;

    /**
     *
     * @return Layout
     */
    public Layout enable() {
        enabled = true;
        changed = true;
        draw();
        onEnable();
        return this;
    }

    /**
     *
     * @return Layout
     */
    public Layout disable() {
        enabled = false;
        clear();
        onDisable();
        return this;
    }


    public void beforeDraw() {}
    public void afterDraw() {}

    public void beforeFrame() {}
    public void afterFrame() {}

    public void onEnable() {}
    public void onDisable() {}

    /**
     * Keypress handler for user interaction.
     * Meant to be overridden by extending class.
     *
     * @param key what key was pressed
     */
    public void keyPressed(char key) {}

    /**
     * Return a String to be rendered as the title.
     * Meant to be overridden by extending class.
     *
     * @return String
     */
    public String getTitle() { return "-"; }

    /**
     * Return an ArrayList of button labels.
     * Meant to be overridden by extending class.
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getButtonLabels() {
        return new ArrayList<>(Arrays.asList("-", "-", "-", "-"));
    }

    /**
     * If the layout is enabled and there are changes
     * to render, then draw each of the layout views.
     */
    public void draw() {
        beforeFrame();
        if (enabled && changed) {
            beforeDraw();
            App.title.setText(getTitle()).draw();
            App.buttonRow.setLabels(getButtonLabels()).draw();
            App.stage.draw();
            changed = false;
            afterDraw();
        }
        afterFrame();
    }

    /**
     *
     */
    public void clear() {
        App.title.clear();
        App.stage.clear();
        App.buttonRow.clear();
    }

}
