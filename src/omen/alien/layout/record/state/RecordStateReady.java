package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.record.widget.RecordHeaderWidget;

public class RecordStateReady extends RecordState {

    RecordHeaderWidget headerWidget;

    public RecordStateReady(RecordLayout _parent) {
        super(_parent);

        headerWidget = (RecordHeaderWidget) parent.widgets.get("header");
        onEnable(() -> headerWidget.setText("READY"));
        onDisable(() -> headerWidget.setText(""));

        buttonRow.addButton(new Button(Const.UI_BUTTON_1, "RECORD", () -> parent.record()));
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

}
