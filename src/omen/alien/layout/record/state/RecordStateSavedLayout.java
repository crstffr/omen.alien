package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.record.RecordLayout;

import java.util.ArrayList;

public class RecordStateSavedLayout extends RecordStateLayout {

    public RecordStateSavedLayout(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> parent.setHeader(""));
        onEnable(() -> parent.setHeader("SAVED"));
        buttonRow.addButton(new Button(Const.UI_BUTTON_1, "NEW", () -> parent.startNewRecording()));
        buttonRow.addButton(new Button(Const.UI_BUTTON_2, "RENAME", () -> parent.rename()));
        buttonRow.addButton(new Button(Const.UI_BUTTON_2, "PREVIEW", () -> parent.play()));
        buttonRow.addButton(new Button(Const.UI_BUTTON_4, "EDIT", () -> parent.open()));
    }

}