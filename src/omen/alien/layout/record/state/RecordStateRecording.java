package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.record.RecordLayout;

public class RecordStateRecording extends RecordState {

    public RecordStateRecording(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> parent.setHeader(""));
        onEnable(() -> parent.setHeader("RECORDING"));
        buttonRow.addButton(new Button(Const.UI_BUTTON_1, "SAVE", () -> parent.save()));
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(new Button(Const.UI_BUTTON_4, "DISCARD", () -> parent.reset()));
    }

}
