package omen.alien.layout.record.state;

import omen.alien.layout.record.RecordLayout;

public class RecordStateWaiting extends RecordState {

    public RecordStateWaiting(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> {
            parent.setHeader("");
            parent.setMessage("");
        });
        onEnable(() -> {
            parent.setHeader("");
            parent.setMessage("GET READY");
        });
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

}