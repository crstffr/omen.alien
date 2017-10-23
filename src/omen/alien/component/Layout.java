package omen.alien.component;

import java.util.ArrayList;
import java.util.Arrays;

public class Layout {

    public boolean enabled = false;

    ArrayList<Runnable> onDrawHandlers = new ArrayList<>();
    ArrayList<Runnable> onEnableHandlers = new ArrayList<>();
    ArrayList<Runnable> onDisableHandlers = new ArrayList<>();
    ArrayList<Runnable> onEveryFrameHandlers = new ArrayList<>();

    public boolean beforeDraw() { return true; }
    public void afterDraw() {}
    public void render() {}
    public void clear() {}

    public boolean beforeEveryFrame() { return true; }
    public void afterEveryFrame() {}

    /**
     *
     * @return Layout
     */
    public Layout enable() {
        enabled = true;
        draw();
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
        clear();
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
     * to render, then draw each of the layout views.
     */
    public void draw() {
        if (beforeEveryFrame()) {
            if (enabled) {
                if (beforeDraw()) {
                    for (Runnable fn : onDrawHandlers) fn.run();
                    afterDraw();
                }
            }
            afterEveryFrame();
        }
    }

}
