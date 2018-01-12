package omen.alien.component;

import java.util.ArrayList;

public class Layout {

    public boolean isEnabled = false;

    ArrayList<Runnable> onDrawHandlers;
    ArrayList<Runnable> onClearHandlers;
    ArrayList<Runnable> onEnableHandlers;
    ArrayList<Runnable> onDisableHandlers;
    ArrayList<Runnable> onEveryFrameHandlers;

    public Layout() {
        onDrawHandlers = new ArrayList<>();
        onClearHandlers = new ArrayList<>();
        onEnableHandlers = new ArrayList<>();
        onDisableHandlers = new ArrayList<>();
        onEveryFrameHandlers = new ArrayList<>();
    }

    /**
     * Keypress handler for user interaction.
     * Meant to be overridden by extending class.
     *
     * @param key what key was pressed
     */
    public void keyPressed(char key) {}

    /**
     *
     * @return Layout
     */
    public Layout enable() {
        if (!isEnabled) {
            isEnabled = true;
            enabled();
        }
        return this;
    }

    void enabled() {
        for (Runnable fn : onEnableHandlers) fn.run();
    }

    public void onEnable(Runnable fn) {
        onEnableHandlers.add(fn);
    }

    /**
     *
     * @return Layout
     */
    public Layout disable() {
        if (isEnabled) {
            isEnabled = false;
            disabled();
        }
        return this;
    }

    void disabled() {
        for (Runnable fn : onDisableHandlers) fn.run();
    }

    public void onDisable(Runnable fn) {
        onDisableHandlers.add(fn);
    }

    /**
     *
     * @return
     */
    public Layout clear() {
        for (Runnable fn : onDrawHandlers) fn.run();
        return this;
    }

    public void onClear(Runnable fn) {
        onClearHandlers.add(fn);
    }

    /**
     *
     * @param fn
     */
    public void onDraw(Runnable fn) {
        onDrawHandlers.add(fn);
    }

    /**
     * If the layout is showing and there are changes
     * to draw, then push each of the layout views.
     */
    public void draw() {
        for (Runnable fn : onDrawHandlers) fn.run();
    }

}
