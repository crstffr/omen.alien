package omen.alien.layout.record.state;

import omen.alien.component.UserInput;
import omen.alien.layout.record.RecordLayout;

public class RecordStateRename extends RecordState {

    UserInput userInput = new UserInput();

    public RecordStateRename(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> parent.setHeader(""));
        onEnable(() -> parent.setHeader("RENAME"));
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

}
