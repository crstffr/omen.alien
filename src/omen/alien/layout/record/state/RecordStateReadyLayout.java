package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.record.RecordLayout;

import java.util.ArrayList;

public class RecordStateReadyLayout extends RecordStateLayout {

    public RecordStateReadyLayout(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> parent.setHeader(""));
        onEnable(() -> parent.setHeader("READY"));
        buttonRow.addButton(new Button(Const.UI_BUTTON_1, "RECORD", () -> parent.record()));
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

}