package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.layer.ChildLayer;
import omen.alien.component.layer.Layer;
import omen.alien.component.layer.StageLayer;
import omen.alien.definition.SampleCollectionItem;
import omen.alien.object.File;

import java.util.LinkedHashMap;

public class FileBrowser {

    Layer layer;
    int color = 255;
    boolean ready = false;

    LinkedHashMap<String, FileBrowserRow> rows = new LinkedHashMap<>();

    public FileBrowser(int _x, int _y, int _w, int _h) {
        layer = new Layer(_x, _y, _w, _h, Const.RENDERER2D);
    }

    public void setColor(int _color) {
        if (_color != color) {
            color = _color;
        }
    }

    public void addItem(String id, SampleCollectionItem item) {
        FileBrowserRow row = new FileBrowserRow(layer, rows.size(), item);
        row.setColor(color);
        rows.put(id, row);
    }

    public void addComplete() {
        ready = true;
    }

    public void clear() {
        rows.forEach((String id, FileBrowserRow row) -> {
            row.clear();
        });
        rows.clear();
        ready = false;
        System.out.println("clear rows");
        System.out.println("row size " + rows.size());
    }

    public void draw() {
        if (ready) {
            rows.forEach((String id, FileBrowserRow row) -> {
                row.draw();
            });
        }
    }

}
