package omen.alien.layout.editor.state;
import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.editor.EditorLayout;

import java.util.ArrayList;

public class EditorStateLoaded extends EditorState {

    int mode = 0;
    boolean valid = false;
    ArrayList<String> modes = new ArrayList<>();

    public EditorStateLoaded(EditorLayout _parent) {
        super(_parent);

        modes.add("crop");
        modes.add("trim");
        modes.add("insert");
        modes.add("normal");
        modes.add("quiet");

        onDisable(() -> {

        });

        onEnable(() -> {

        });

        buttonRow.addButton(new Button(Const.UI_BUTTON_1, () -> {
            return modes.get(mode).toUpperCase();
        }, () -> {
            mode = (mode == modes.size() - 1) ? 0 : mode + 1;
        }));

        buttonRow.addButton(); // blank

        buttonRow.addButton(); // blank

        buttonRow.addButton(new Button(Const.UI_BUTTON_4, () -> {
            return (valid) ? "GO" : "-";
        }, () -> { parent.executeEdit(); }));
    }

}
