package omen.alien.component;

import omen.alien.Const;
import omen.alien.component.layer.Layer;

public class MajorLayout extends Layout {

    public int color = Const.WHITE;
    public ButtonRow buttonRow;

    public MajorLayout() {

        buttonRow = new ButtonRow();

        onEnable(() -> {
            buttonRow.setColor(color);
        });

        onClear(() -> {
            buttonRow.clear();
        });

        onDraw(() -> {
            buttonRow.draw();
        });
    }

    public void keyPressed(char key) {
        if (enabled) {
            for (int i = 0; i < Const.UI_BUTTONS.length; i++) {
                if (Const.UI_BUTTONS[i] == key) {
                    buttonRow.buttons.get(i).trigger();
                    break;
                }
            }
        }
    }

}
