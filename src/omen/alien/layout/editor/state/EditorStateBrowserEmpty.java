package omen.alien.layout.editor.state;

import omen.alien.layout.editor.EditorLayout;
import omen.alien.layout.editor.widget.EditorMessageWidget;

public class EditorStateBrowserEmpty extends EditorState {

    EditorMessageWidget messageWidget;

    public EditorStateBrowserEmpty(EditorLayout _parent) {
        super(_parent);

        messageWidget = new EditorMessageWidget(this);
        messageWidget.setText("Sampler is Empty");
        messageWidget.setColor(parent.color);
        messageWidget.setSize(45);

        onEnable(() -> {
            messageWidget.enable();
            messageWidget.show();
        });

        onDisable(() -> {
            messageWidget.hide();
        });

        onDraw(() -> {
            messageWidget.draw();
        });

        buttonRow.addButton();
        buttonRow.addButton();
        buttonRow.addButton();
        buttonRow.addButton();

    }

}
