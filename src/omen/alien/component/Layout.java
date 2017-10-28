package omen.alien.component;

import java.util.ArrayList;

public class Layout {

    public boolean enabled = false;

    ArrayList<Runnable> onDrawHandlers;
    ArrayList<Runnable> onEnableHandlers;
    ArrayList<Runnable> onDisableHandlers;
    ArrayList<Runnable> onEveryFrameHandlers;

    public Layout() {
        onDrawHandlers = new ArrayList<>();
        onEnableHandlers = new ArrayList<>();
        onDisableHandlers = new ArrayList<>();
        onEveryFrameHandlers = new ArrayList<>();
    }

    /**
     *
     * @return Layout
     */
    public Layout enable() {
        enabled = true;
        enabled();
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
        enabled = false;
        disabled();
        return this;
    }

    void disabled() {
        for (Runnable fn : onDisableHandlers) fn.run();
    }

    public void onDisable(Runnable fn) {
        onDisableHandlers.add(fn);
    }

    /**
     * Keypress handler for user interaction.
     * Meant to be overridden by extending class.
     *
     * @param key what key was pressed
     */
    public void keyPressed(char key) {}

    public void onDraw(Runnable fn) { onDrawHandlers.add(fn); }

    /**
     * If the layout is enabled and there are changes
     * to draw, then push each of the layout views.
     */
    public synchronized void draw() {
        for (Runnable fn : onDrawHandlers) fn.run();
    }

}
