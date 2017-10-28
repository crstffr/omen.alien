package omen.alien.component;

import omen.alien.*;

import java.util.*;

public class ButtonRow {

    int color = 255;
    public Layer layer1;
    public Layer layer2;
    ArrayList<Button> buttons;
    HashMap<Character, Button> buttonKeyMap;

    int x = Const.BUTTON_VIEW_X;
    int y = Const.BUTTON_VIEW_Y;
    int w = Const.BUTTON_VIEW_W;
    int h = Const.BUTTON_VIEW_H;

    public ButtonRow() {
        layer1 = new Layer(x, y, w, h, Const.RENDERER);
        layer2 = new Layer(x, y, w, h, Const.RENDERER);
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

        if (buttons.size() > 0) {

        // Button Dividers

            layer1.init();
            layer1.canvas.stroke(color);

            // Button Row Top Line
            layer1.canvas.line(0, 0, layer1.canvas.width, 0);

            int num = buttons.size();
            int gap = Const.WIDTH / num;
            int offset = gap / 2;

            // Button Dividers
            for (int i = 1; i < num; i++) {
                int ix = gap * i;
                layer1.canvas.line(ix, 0, ix, h);
            }

            layer1.draw();

        // Button Labels

            layer2.init();
            for (int i = 0; i < num; i++) {
                layer2.canvas.fill(color);
                layer2.canvas.textFont(App.font);
                layer2.canvas.textSize(Const.BUTTON_FONT_SIZE);
                layer2.canvas.textAlign(Const.CENTER, Const.CENTER);
                layer2.canvas.text(buttons.get(i).getLabel(), offset + gap * i, Const.BUTTON_TEXT_Y);
            }
            layer2.draw();
        }

    }

}
