package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.component.UserInput;
import omen.alien.layout.record.RecordLayout;

import java.util.ArrayList;

public class RecordStateRenameLayout extends RecordStateLayout {

    UserInput userInput = new UserInput();

    public RecordStateRenameLayout(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> parent.setHeader(""));
        onEnable(() -> parent.setHeader("RENAME"));
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

}
