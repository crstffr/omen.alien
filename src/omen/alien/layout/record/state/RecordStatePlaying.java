package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.record.RecordLayout;

public class RecordStatePlaying extends RecordState {

    public RecordStatePlaying(RecordLayout _parent) {
        super(_parent);
        onDisable(() -> parent.setHeader(""));
        onEnable(() -> parent.setHeader("PLAYING"));
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(new Button(Const.UI_BUTTON_3, "PLAY", () -> parent.play()));
        buttonRow.addButton(new Button(Const.UI_BUTTON_4, "STOP", () -> parent.stopPlaying()));
    }

}
