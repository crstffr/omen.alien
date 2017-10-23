package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.record.RecordLayout;

import java.util.ArrayList;

public class RecordStatePausedLayout extends RecordStateLayout {

    public RecordStatePausedLayout(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> parent.setHeader(""));
        onEnable(() -> parent.setHeader("PAUSED"));
        buttonRow.addButton(new Button(Const.UI_BUTTON_1, "RESUME", () -> parent.resume()));
        buttonRow.addButton(new Button(Const.UI_BUTTON_2, "SAVE", () -> parent.save()));
        buttonRow.addButton(new Button(Const.UI_BUTTON_3, "PLAY", () -> parent.play()));
        buttonRow.addButton(new Button(Const.UI_BUTTON_4, "RESET", () -> parent.reset()));
    }

}
