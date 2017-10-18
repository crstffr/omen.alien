package omen.alien.component;

import omen.alien.*;
import java.util.*;

public class ButtonRow {

    int color = 0;
    public View view;
    ArrayList<String> labels;

    int x = Const.BUTTON_VIEW_X;
    int y = Const.BUTTON_VIEW_Y;
    int w = Const.BUTTON_VIEW_W;
    int h = Const.BUTTON_VIEW_H;

    public ButtonRow() {
        view = new View(x, y, w, h);
        labels = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
    }

    public ButtonRow setLabels(ArrayList<String> vals) {
        if (!vals.equals(labels)) {
            labels = vals;
        }
        return this;
    }

    public ButtonRow setColor(int val) {
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
        drawDividers();
        drawLabels();
        view.draw();
    }

    void drawDividers() {
        int num = labels.size();
        int gap = Const.WIDTH / num;

        view.layer.noFill();
        view.layer.stroke(color, (float)128);

        // Top border of button row
        view.layer.line(0, 0, w, 0);

        // Individual button dividers
        for(int i = 1; i < num; i++) {
            int ix = gap * i;
            view.layer.line(ix, 0, ix, h);
        }
    }

    void drawLabels() {
        int num = labels.size();
        int gap = Const.WIDTH / num;
        int offset = gap / 2;

        view.layer.noStroke();
        view.layer.fill(color);
        view.layer.textFont(App.font);
        view.layer.textSize(Const.BUTTON_FONT_SIZE);
        view.layer.textAlign(Const.CENTER, Const.CENTER);

        for(int i = 0; i < num; i++) {
            String label = labels.get(i);
            view.layer.text(label, offset + gap * i, Const.BUTTON_TEXT_Y);
        }
    }

}
