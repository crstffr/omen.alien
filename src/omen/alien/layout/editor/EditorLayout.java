package omen.alien.layout.editor;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.component.Layout;
import omen.alien.component.Widget;
import omen.alien.component.MajorLayout;
import omen.alien.layout.editor.state.EditorStateLoaded;
import omen.alien.layout.editor.state.EditorStateLoading;
import omen.alien.layout.editor.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

public class EditorLayout extends MajorLayout {

    public EditorLayout() {
        super();
        color = Const.YELLOW;

        setupWidgets();
        setupStates();

        onEnable(() -> {
            setState("loading");
        });

        onDisable(() -> {

        });

        onDraw(() -> {

        });

    }

    void setupStates() {
        states.put("loaded", new EditorStateLoaded(this));
        states.put("loading", new EditorStateLoading(this));
    }

    void setupWidgets() {
        widgets.put("message", new EditorMessageWidget(this));

        widgets.forEach((String key, Widget widget) -> {
            widget.setColor(color).show();
        });
    }

    public void loadSample(String _id) {

    }

    public void executeEdit() {

    }

}
