package omen.alien.component;

import omen.alien.Const;

import java.util.ArrayList;
import java.util.HashMap;

public class MajorLayout extends Layout {

    Layout currentStateObj;
    String currentStateStr;

    public HashMap<String, Layout> states = new HashMap<>();
    public HashMap<String, Widget> widgets = new HashMap<>();

    public int color = Const.WHITE;
    public ButtonRow buttonRow = new ButtonRow();

    public MajorLayout() {

        onEnable(() -> {
            buttonRow.setColor(color);
            if (currentStateObj != null) { currentStateObj.enable(); }
            widgets.forEach((String key, Widget widget) -> widget.enable());
        });

        onDisable(() -> {
            if (currentStateObj != null) { currentStateObj.disable(); }
            widgets.forEach((String key, Widget widget) -> widget.disable());
        });

        onDraw(() -> {
            buttonRow.draw();
            if (currentStateObj != null) { currentStateObj.draw(); }
            widgets.forEach((String key, Widget widget) -> widget.draw());
        });

        onClear(() -> {
            if (currentStateObj != null) {
                currentStateObj.clear();
            }
            buttonRow.clear();
        });
    }

    public boolean stateIs(String what) {
        return currentStateStr.equals(what);
    }

    public Layout getStateObj() {
        return states.get(currentStateStr);
    }

    public void setState(String name) {
        currentStateStr = name;
        currentStateObj = getStateObj();
        states.forEach((String key, Layout layout) -> {
            if (layout != null && !key.equals(name)) {
                layout.disable();
            }
        });
        currentStateObj.enable();
    }

    public void keyPressed(char key) {
        if (isEnabled) {
            if (currentStateObj != null) {
                currentStateObj.keyPressed(key);
            } else {
                for (int i = 0; i < Const.UI_BUTTONS.length; i++) {
                    if (Const.UI_BUTTONS[i] == key) {
                        buttonRow.buttons.get(i).trigger();
                        break;
                    }
                }
            }
        }
    }

}
