package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.record.RecordLayout;

public class RecordStateSaved extends RecordState {

    public RecordStateSaved(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> parent.setHeader(""));
        onEnable(() -> parent.setHeader("SAVED"));
        buttonRow.addButton(new Button(Const.UI_BUTTON_1, "NEW", () -> parent.startNewRecording()));
        buttonRow.addButton(new Button(Const.UI_BUTTON_2, "RENAME", () -> parent.rename()));
        buttonRow.addButton(new Button(Const.UI_BUTTON_3, "PREVIEW", () -> parent.play()));
        buttonRow.addButton(new Button(Const.UI_BUTTON_4, "EDIT", () -> parent.edit()));
    }

    public void customKeyHandler(char key, int code) {
        switch(code) {
            case 32: // spacebar
                parent.play();
                break;
        }
    }

}