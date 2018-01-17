package omen.alien.layout.editor.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.component.ConfirmDialog;
import omen.alien.definition.SampleCollectionItem;
import omen.alien.layout.editor.EditorLayout;
import omen.alien.layout.editor.widget.EditorDeleteConfirmWidget;

public class EditorStateDelete extends EditorState {

    ConfirmDialog dialog;
    SampleCollectionItem item;
    EditorDeleteConfirmWidget confirmWidget;

    public EditorStateDelete(EditorLayout _parent) {
        super(_parent);

        confirmWidget = new EditorDeleteConfirmWidget(this);
        confirmWidget.setColor(parent.color);

        dialog = confirmWidget.confirmDialog;
        dialog.setHeaderText("Confirm Delete?");

        onDisable(() -> {
            confirmWidget.hide();
        });

        onDraw(() -> {
            confirmWidget.draw();
        });

        confirmWidget.confirmDialog.onCancel(() -> {
            parent.setState("browser");
            item = null;
        });

        confirmWidget.confirmDialog.onConfirm(() -> {
            parent.actuallyDeleteSample(item);
            parent.setState("browser");
            item = null;
        });

        buttonRow.addButton(new Button(Const.UI_BUTTON_1, "CONFIRM", () -> {
            dialog.confirm();
        }));

        buttonRow.addButton();
        buttonRow.addButton();

        buttonRow.addButton(new Button(Const.UI_BUTTON_4, "CANCEL", () -> {
            dialog.cancel();
        }));

    }

    public void openConfirmDialog(SampleCollectionItem _item) {
        item = _item;
        dialog.setBodyText("Are you sure you want to delete:\n\n\n" + _item.name);
        confirmWidget.show();
    }

    public void customKeyHandler(char key, int code) {
        switch (key) {
            case Const.ESC_KEY:
                dialog.cancel();
                break;
            case Const.ENTER:
            case Const.RETURN:
                dialog.confirm();
                break;
        }
    }

}
