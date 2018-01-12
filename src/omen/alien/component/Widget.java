package omen.alien.component;

import omen.alien.App;
import omen.alien.component.layer.Layer;
import omen.alien.component.layer.ChildLayer;

import java.util.ArrayList;

public class Widget {

    public Layer layer;
    public int color = 0;
    public String text = "";
    public Layout parent;
    public boolean enabled = false;
    public boolean showing = false;

    ArrayList<Runnable> onShowHandlers = new ArrayList<>();
    ArrayList<Runnable> onHideHandlers = new ArrayList<>();
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

    public Widget onSetColor(Runnable fn) {
        onSetColorHandlers.add(fn);
        return this;
    }

    public Widget setColor(int _color) {
        color = _color;
        for (Runnable fn : onSetColorHandlers) fn.run();
        return this;
    }

    public Widget onSetText(Runnable fn) {
        onSetTextHandlers.add(fn);
        return this;
    }

    public Widget setText(String _text) {
        text = _text;
        for (Runnable fn : onSetTextHandlers) fn.run();
        return this;
    }

    public Widget onClear(Runnable fn) {
        onClearHandlers.add(fn);
        return this;
    }

    public Widget clear() {
        for (Runnable fn : onClearHandlers) fn.run();
        return this;
    }

    public Widget onShow(Runnable fn) {
        onShowHandlers.add(fn);
        return this;
    }

    final public Widget show() {
        showing = true;
        for (Runnable fn : onShowHandlers) fn.run();
        return this;
    }

    public Widget onHide(Runnable fn) {
        onHideHandlers.add(fn);
        return this;
    }

    final public Widget hide() {
        showing = false;
        for (Runnable fn : onHideHandlers) fn.run();
        return this;
    }

    public Widget onDraw(Runnable fn) {
        onDrawHandlers.add(fn);
        return this;
    }

    final public Widget draw() {
        if (showing) {
            for (Runnable fn : onDrawHandlers) fn.run();
        }
        return this;
    }

    public Widget onEnable(Runnable fn) {
        onEnableHandlers.add(fn);
        return this;
    }

    public Widget enable() {
        if (!enabled) {
            for (Runnable fn : onEnableHandlers) fn.run();
            enabled = true;
        }
        return this;
    }

    public Widget onDisable(Runnable fn) {
        onDisableHandlers.add(fn);
        return this;
    }

    public Widget disable() {
        if (enabled) {
            for (Runnable fn : onDisableHandlers) fn.run();
            enabled = false;
        }
        return this;
    }

    public Widget onReset(Runnable fn) {
        onResetHandlers.add(fn);
        return this;
    }

    public Widget reset() {
        for (Runnable fn : onResetHandlers) fn.run();
        return this;
    }

}
