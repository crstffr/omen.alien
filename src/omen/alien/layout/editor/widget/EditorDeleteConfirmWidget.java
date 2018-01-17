package omen.alien.layout.editor.widget;

import omen.alien.component.ConfirmDialog;
import omen.alien.component.MajorLayout;
import omen.alien.component.Widget;

public class EditorDeleteConfirmWidget extends Widget {

    public ConfirmDialog confirmDialog;

    public EditorDeleteConfirmWidget(MajorLayout _parent) {

        parent = _parent;

        confirmDialog = new ConfirmDialog();

        onSetColor(() -> {
            confirmDialog.setColor(color);
        });

        onDraw(() -> {
            if (showing) {
                confirmDialog.draw();
            }
        });

    }

}
