package omen.alien.layout.record.state;

import omen.alien.layout.record.RecordLayout;

public class RecordStateSaving extends RecordState {

    public RecordStateSaving(RecordLayout _parent) {
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