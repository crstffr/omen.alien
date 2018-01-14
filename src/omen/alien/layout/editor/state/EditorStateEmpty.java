package omen.alien.layout.editor.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.editor.EditorLayout;

public class EditorStateEmpty extends EditorState {

    public EditorStateEmpty(EditorLayout _parent) {
        super(_parent);

        buttonRow.addButton(new Button(Const.UI_BUTTON_1, "LOAD", () -> {
            // nothing right now
        }));

        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

}
