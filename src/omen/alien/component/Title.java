package omen.alien.component;

import omen.alien.*;

public class Title {

    int color = 0;
    String text = "";
    public View view;

    int x = Const.TITLE_VIEW_X;
    int y = Const.TITLE_VIEW_Y;
    int w = Const.TITLE_VIEW_W;
    int h = Const.TITLE_VIEW_H;

    public Title() {
        view = new View(x, y, w, h);
    }

    public Title setText(String val) {
        if (!val.equals(text)) {
            text = val;
        }
        return this;
    }

    public Title setColor(int val) {
        if (val != color) {
            color = val;
        }
        return this;
    }

    public void clear() {
        view.clear();
    }

    public void draw() {
        view.clear();
        view.layer.fill(color);
        view.layer.textFont(App.font);
        view.layer.textSize(Const.TITLE_FONT_SIZE);
        view.layer.textAlign(Const.LEFT, Const.CENTER);
        view.layer.text(text, Const.TITLE_TEXT_X, Const.TITLE_TEXT_Y);
        view.draw();
    }

}
