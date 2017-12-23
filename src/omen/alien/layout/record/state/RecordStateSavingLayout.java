package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.record.RecordLayout;

public class RecordStateSavingLayout extends RecordStateLayout {

    public RecordStateSavingLayout(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> {
            parent.setHeader("");
            parent.setMessage("");
        });
        onEnable(() -> {
            parent.setHeader("");
            parent.setMessage("SAVING");
        });
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

}