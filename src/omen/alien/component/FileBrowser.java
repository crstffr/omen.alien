package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.layer.ChildLayer;
import omen.alien.component.layer.Layer;
import omen.alien.component.layer.StageLayer;
import omen.alien.definition.SampleCollectionItem;
import omen.alien.object.File;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class FileBrowser {

    Layer layer;
    int offset = 0;
    int maxnum = 19;
    int selected = 0;
    int color = 255;
    boolean ready = false;

    ArrayList<SampleCollectionItem> items = new ArrayList<>();
    ArrayList<FileBrowserRow> rows = new ArrayList<>();

    public FileBrowser(int _x, int _y, int _w, int _h) {
        layer = new Layer(_x, _y, _w, _h, Const.RENDERER2D);
    }

    public void setColor(int _color) {
        if (_color != color) {
            color = _color;
        }
    }

    public void markReady() {
        renderRows();
        if (items.size() > 0) {
            selectItem(0);
        }
    }

    public void addItem(SampleCollectionItem item) {
        items.add(item);
    }

    public void scrollUp() {
        setOffset(offset - 1);
    }

    public void scrollDown() {
        setOffset(offset + 1);
    }

    public void setOffset(int _offset) {
        if (offset != _offset) {
            if (_offset >= 0 && _offset <= items.size() - maxnum) {
                offset = _offset;
                clearRows();
                renderRows();
            }
        }
    }

    public void selectPrev() {
        if (selected > 0) {
            selectItem(selected - 1);
            if (selected < offset) {
                scrollUp();
            }
        }
    }

    public void selectNext() {
        if (selected < items.size() - 1) {
            selectItem(selected + 1);
            if (selected >= offset + maxnum) {
                scrollDown();
            }
        }
    }

    public void selectItem(int index) {
        items.get(selected).selected = false;
        items.get(index).selected = true;
        selected = index;
    }

    public void clear() {
        clearItems();
        clearRows();
    }

    public void clearRows() {
        rows.forEach((FileBrowserRow row) -> {
            row.clear();
        });
        rows.clear();
        ready = false;
    }

    public void clearItems() {
        items.clear();
    }

    public void renderRows() {
        for (int i = offset; i < offset + maxnum; i++) {
            if (i < items.size()) {
                SampleCollectionItem item = items.get(i);
                FileBrowserRow row = new FileBrowserRow(layer, rows.size(), item);
                row.setColor(color);
                rows.add(row);
            }
        }
        ready = true;
    }

    public void draw() {
        if (ready) {
            rows.forEach((FileBrowserRow row) -> {
                row.draw();
            });
        }
    }

}
