package omen.alien.layout.editor.state;
import omen.alien.layout.editor.EditorLayout;
import omen.alien.layout.editor.widget.EditorMessageWidget;

public class EditorStateLoading extends EditorState {

    EditorMessageWidget msgWidget;

    public EditorStateLoading(EditorLayout _parent) {
        super(_parent);

        msgWidget = (EditorMessageWidget) parent.widgets.get("message");

        onDisable(() -> {
            msgWidget.setText("").hide();
        });

        onEnable(() -> {
            msgWidget.setText("LOADING").show();
        });

        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

}
