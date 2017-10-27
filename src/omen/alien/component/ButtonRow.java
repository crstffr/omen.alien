package omen.alien.component;

import omen.alien.*;
import java.util.*;

public class ButtonRow {

    int color = 0;
    public View view;
    ArrayList<Button> buttons;
    HashMap<Character, Button> buttonKeyMap;

    int x = Const.BUTTON_VIEW_X;
    int y = Const.BUTTON_VIEW_Y;
    int w = Const.BUTTON_VIEW_W;
    int h = Const.BUTTON_VIEW_H;

    public ButtonRow() {
        view = new View(x, y, w, h);
        buttons = new ArrayList<>();
        buttonKeyMap = new HashMap<>();
    }

    public void addButton() {
        addButton(new Button());
    }

    public void addButton(Button _button) {
        buttons.add(_button);
        buttonKeyMap.put(_button.key, _button);
    }

    public Button getButton(int _index) {
        return buttons.get(_index);
    }

    public ArrayList getButtons() {
        return buttons;
    }

    public ButtonRow setColor(int _color) {
        if (_color != color) {
            color = _color;
        }
        return this;
    }

    public void keyPress(char key) {
        Button btn = buttonKeyMap.get(key);
        if (btn != null) {
            btn.trigger();
        }
    }

    public void draw() {
        /*
        view.clear();
        view.layer.beginDraw();
        drawDividers();
        drawLabels();
        view.layer.endDraw();
        view.draw();
        */
    }

    public void clear() {
        view.clear();
    }

    void drawDividers() {

        view.layer.noFill();
        view.layer.stroke(color, (float)128);

        // Top border of button row
        view.layer.line(0, 0, w, 0);

        if (buttons.size() > 0) {
            int num = buttons.size();
            int gap = Const.WIDTH / num;

            // Individual button dividers
            for (int i = 1; i < num; i++) {
                int ix = gap * i;
                view.layer.line(ix, 0, ix, h);
            }
        }
    }

    void drawLabels() {
        if (buttons.size() > 0) {
            int num = buttons.size();
            int gap = Const.WIDTH / num;
            int offset = gap / 2;

            view.layer.noStroke();
            view.layer.fill(color);
            view.layer.textFont(App.font);
            view.layer.textSize(Const.BUTTON_FONT_SIZE);
            view.layer.textAlign(Const.CENTER, Const.CENTER);

            for (int i = 0; i < num; i++) {
                String label = buttons.get(i).getLabel();
                view.layer.text(label, offset + gap * i, Const.BUTTON_TEXT_Y);
            }

        }
    }

}
