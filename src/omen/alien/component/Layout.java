package omen.alien.component;

import java.util.ArrayList;
import java.util.Arrays;

public class Layout {

    Title title;
    String titleText;
    ButtonRow buttonRow;

    public boolean changed = false;
    public boolean enabled = false;

    /**
     *
     * @param _title
     * @param _buttonRow
     */
    public Layout(Title _title, ButtonRow _buttonRow) {
        title = _title;
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
        return this;
    }

    /**
     *
     * @return Layout
     */
    public Layout disable() {
        enabled = false;
        return this;
    }

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
            title.text(getTitle()).draw();
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
