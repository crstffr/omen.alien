package omen.alien.component;

import java.util.ArrayList;
import java.util.Arrays;

public class Layout {

    public Title title;
    public Stage stage;
    public ButtonRow buttonRow;

    public boolean changed = false;
    public boolean enabled = false;

    /**
     *
     * @param _title
     * @param _buttonRow
     */
    public Layout(Title _title, Stage _stage, ButtonRow _buttonRow) {
        title = _title;
        stage = _stage;
        buttonRow = _buttonRow;
    }

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
        onDisable();
        return this;
    }

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
        if (enabled && changed) {
            title.setText(getTitle()).draw();
            buttonRow.setLabels(getButtonLabels()).draw();
            changed = false;
        }
    }

    /**
     *
     */
    public void clear() {
        title.clear();
        buttonRow.clear();
    }

}
