package omen.alien.component;

import omen.alien.Const;
import omen.alien.View;
import java.util.*;

public class ButtonRow {

    View view;
    int _color = 0;
    String[] _text;
    boolean _changed = true;

    int x = Const.BUTTON_VIEW_X;
    int y = Const.BUTTON_VIEW_Y;
    int w = Const.BUTTON_VIEW_W;
    int h = Const.BUTTON_VIEW_H;

    ArrayList<String> _labels = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));

    public ButtonRow() {
        view = new View(x, y, w, h);
    }

    public ButtonRow labels(ArrayList<String> vals) {
        if (!vals.equals(_labels)) {
            _labels = vals;
            _changed = true;
        }
        return this;
    }

    public ButtonRow color(int val) {
        if (val != _color) {
            _color = val;
            _changed = true;
        }
        return this;
    }

    public ButtonRow draw() {
        if (_changed) {
            view.clear();
            drawDividers();
            drawLabels();
            view.draw();
            _changed = false;
        }
        return this;
    }

    void drawDividers() {
        int num = _labels.size();
        int gap = Const.WIDTH / num;

        view.layer.noFill();
        view.layer.stroke(_color);

        for(int i = 1; i < num; i++) {
            int ix = gap * i;
            view.layer.line(ix, 0, ix, h);
        }
    }

    void drawLabels() {
        int num = _labels.size();
        int gap = Const.WIDTH / num;
        int offset = gap / 2;

        view.layer.noStroke();
        view.layer.fill(_color);
        view.layer.textFont(Const.FONT);
        view.layer.textSize(Const.BUTTON_FONT_SIZE);
        view.layer.textAlign(Const.CENTER, Const.CENTER);

        for(int i = 0; i < num; i++) {
            String label = _labels.get(i);
            view.layer.text(label, offset + gap * i, Const.BUTTON_TEXT_Y);
        }
    }

}
