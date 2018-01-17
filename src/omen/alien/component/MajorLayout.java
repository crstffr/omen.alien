package omen.alien.component;

import omen.alien.Const;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MajorLayout extends Layout {

    Layout currentStateObj;
    String currentStateStr;

    public LinkedHashMap<String, Layout> states = new LinkedHashMap<>();
    public LinkedHashMap<String, Widget> widgets = new LinkedHashMap<>();

    public int color = Const.WHITE;
    public ButtonRow buttonRow = new ButtonRow();

    public MajorLayout() {

        onEnable(() -> {
            buttonRow.setColor(color);
            widgets.forEach((String key, Widget widget) -> widget.enable());
            if (currentStateObj != null) { currentStateObj.enable(); }
        });

        onDisable(() -> {
            widgets.forEach((String key, Widget widget) -> widget.disable());
            if (currentStateObj != null) { currentStateObj.disable(); }
        });

        onDraw(() -> {
            buttonRow.draw();
            widgets.forEach((String key, Widget widget) -> widget.draw());
            if (currentStateObj != null) { currentStateObj.draw(); }
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

    public void customKeyHandler(char key, int keyCode) {
        // meant to be provided by consumer
    }

    public void keyPressed(char key, int keyCode) {
        if (isEnabled) {
            if (currentStateObj != null) {
                currentStateObj.keyPressed(key, keyCode);
            }
            if (buttonRow.buttons.size() == Const.UI_BUTTONS.length) {
                buttonRow.buttons.forEach((Button button) -> {
                    if (button.key == key) {
                        button.trigger();
                    }
                });
            }
            customKeyHandler(key, keyCode);
        }
    }

}
