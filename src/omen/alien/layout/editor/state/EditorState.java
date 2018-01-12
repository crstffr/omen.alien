package omen.alien.layout.editor.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.component.MajorLayout;
import omen.alien.layout.editor.EditorLayout;

public class EditorState extends MajorLayout {

    EditorLayout parent;

    public EditorState(EditorLayout _parent) {
        parent = _parent;
        color = Const.YELLOW;
    }
}
