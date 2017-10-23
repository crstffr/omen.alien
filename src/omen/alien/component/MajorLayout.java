package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;

import java.util.ArrayList;
import java.util.Arrays;


public class MajorLayout extends Layout {

    public int color = Const.WHITE;
    public Stage stage = new Stage();
    public ButtonRow buttonRow = new ButtonRow();

    public MajorLayout() {

        onEnable(() -> {
            buttonRow.setColor(color);
        });

        onDisable(() -> {
            stage.clear();
            buttonRow.clear();
        });

        onDraw(() -> {
            stage.draw();
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
