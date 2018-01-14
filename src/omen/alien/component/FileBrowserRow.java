package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.layer.ChildLayer;
import omen.alien.component.layer.Layer;
import omen.alien.definition.SampleCollectionItem;
import omen.alien.object.File;

import java.math.BigDecimal;

public class FileBrowserRow {

    int x;
    int y;
    int w;
    int h = 20;
    int color = 255;

    SampleCollectionItem item;

    Layer layerRow;
    Layer layerName;
    Layer layerDate;
    Layer layerLength;

    public FileBrowserRow(Layer _parent, int _n, SampleCollectionItem _item) {
        x = _parent.x;
        w = _parent.w;
        y = h * _n;

        item = _item;

        layerRow = new Layer(x, y, w, h, Const.RENDERER2D);
        layerName = new ChildLayer(layerRow, 0, 0, 400, h);
        layerDate = new ChildLayer(layerRow, 400, 0, 200, h);
        layerLength = new ChildLayer(layerRow, 600, 0, 200, h);

        System.out.println(x + " " + y);
    }

    public void setColor(int _color) {
        if (_color != color) {
            color = _color;
        }
    }

    public void clear() {
        layerRow.clear();
        layerName.clear();
        layerDate.clear();
        layerLength.clear();
    }

    public void draw() {
        //layerRow.init();
        layerName.init();
        layerLength.init();

        layerName.canvas.fill(color);
        layerName.canvas.textFont(App.font, 18);
        layerName.canvas.textAlign(Const.LEFT, Const.CENTER);
        layerName.canvas.text(item.name, 10, layerName.mid_y);

        String length = BigDecimal.valueOf(item.length).setScale(2, 1).toString() + "S";

        layerLength.canvas.fill(color);
        layerLength.canvas.textFont(App.font, 18);
        layerLength.canvas.textAlign(Const.LEFT, Const.CENTER);
        layerLength.canvas.text(length, 0, layerLength.mid_y);

        layerName.draw();
        layerLength.draw();

    }


}
