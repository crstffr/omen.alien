package omen.alien.layout.record;

import omen.alien.App;
import omen.alien.component.layer.Layer;
import omen.alien.component.layer.ChildLayer;

import java.util.ArrayList;

public class RecordWidget {

    public Layer layer;
    public int color = 0;
    public String text = "";
    public RecordLayout parent;
    public boolean enabled = false;

    ArrayList<Runnable> onDrawHandlers = new ArrayList<>();
    ArrayList<Runnable> onClearHandlers = new ArrayList<>();
    ArrayList<Runnable> onResetHandlers = new ArrayList<>();
    ArrayList<Runnable> onEnableHandlers = new ArrayList<>();
    ArrayList<Runnable> onDisableHandlers = new ArrayList<>();
    ArrayList<Runnable> onSetTextHandlers = new ArrayList<>();
    ArrayList<Runnable> onSetColorHandlers = new ArrayList<>();

    int x;
    int y;
    int w;
    int h;

    public void init(int _x, int _y, int _w, int _h) {
        x = _x;
        y = _y;
        w = _w;
        h = _h;
        layer = new ChildLayer(App.stage, x, y, w, h);
    }

    /**
     *
     * @param fn
     * @return
     */
    public RecordWidget onSetColor(Runnable fn) {
        onSetColorHandlers.add(fn);
        return this;
    }

    public RecordWidget setColor(int _color) {
        color = _color;
        for (Runnable fn : onSetColorHandlers) fn.run();
        return this;
    }

    /**
     *
     * @param fn
     * @return
     */
    public RecordWidget onSetText(Runnable fn) {
        onSetTextHandlers.add(fn);
        return this;
    }

    public RecordWidget setText(String _text) {
        text = _text;
        for (Runnable fn : onSetTextHandlers) fn.run();
        return this;
    }

    /**
     *
     * @param fn
     * @return
     */
    public RecordWidget onClear(Runnable fn) {
        onClearHandlers.add(fn);
        return this;
    }

    public RecordWidget clear() {
        for (Runnable fn : onClearHandlers) fn.run();
        return this;
    }

    /**
     *
     * @param fn
     * @return
     */
    public RecordWidget onDraw(Runnable fn) {
        onDrawHandlers.add(fn);
        return this;
    }

    final public RecordWidget draw() {
        for (Runnable fn : onDrawHandlers) fn.run();
        return this;
    }

    /**
     *
     * @param fn
     * @return
     */
    public RecordWidget onEnable(Runnable fn) {
        onEnableHandlers.add(fn);
        return this;
    }

    public RecordWidget enable() {
        if (enabled == false) {
            for (Runnable fn : onEnableHandlers) fn.run();
            enabled = true;
        }
        return this;
    }

    /**
     *
     * @param fn
     * @return
     */
    public RecordWidget onDisable(Runnable fn) {
        onDisableHandlers.add(fn);
        return this;
    }

    public RecordWidget disable() {
        if (enabled == true) {
            for (Runnable fn : onDisableHandlers) fn.run();
            enabled = false;
        }
        return this;
    }

    /**
     *
     * @param fn
     * @return
     */
    public RecordWidget onReset(Runnable fn) {
        onResetHandlers.add(fn);
        return this;
    }

    public RecordWidget reset() {
        for (Runnable fn : onResetHandlers) fn.run();
        return this;
    }

}
