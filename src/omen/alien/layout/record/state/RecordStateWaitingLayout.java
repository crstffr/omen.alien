package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.record.RecordLayout;

public class RecordStateWaitingLayout extends RecordStateLayout {

    public RecordStateWaitingLayout(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> parent.setHeader(""));
        onEnable(() -> parent.setHeader("WAIT"));
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

}