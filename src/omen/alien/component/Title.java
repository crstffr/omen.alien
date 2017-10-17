package omen.alien.component;

import omen.alien.*;

public class Title {

    View view;
    int _color = 0;
    String _text = "";
    boolean _changed = true;

    int x = Const.TITLE_VIEW_X;
    int y = Const.TITLE_VIEW_Y;
    int w = Const.TITLE_VIEW_W;
    int h = Const.TITLE_VIEW_H;

    public Title() {
        view = new View(x, y, w, h);
    }

    public Title text(String val) {
        if (!val.equals(_text)) {
            _text = val;
            _changed = true;
        }
        return this;
    }

    public Title color(int val) {
        if (val != _color) {
            _color = val;
            _changed = true;
        }
        return this;
    }

    public Title draw() {
        if (_changed) {
            view.clear();
            view.layer.fill(_color);
            view.layer.textFont(Const.FONT);
            view.layer.textSize(Const.TITLE_FONT_SIZE);
            view.layer.textAlign(Const.LEFT, Const.CENTER);
            view.layer.text(_text, Const.TITLE_TEXT_X, Const.TITLE_TEXT_Y);
            view.draw();
            _changed = false;
        }
        return this;
    }

}
