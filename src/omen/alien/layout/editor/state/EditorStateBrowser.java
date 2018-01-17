package omen.alien.layout.editor.state;

import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.layout.editor.EditorLayout;

import java.util.ArrayList;

public class EditorStateBrowser extends EditorState {

    Integer sortVal = 1;
    Boolean sortAsc = false;
    ArrayList<String> sortOptions = new ArrayList<>();


    public EditorStateBrowser(EditorLayout _parent) {
        super(_parent);

        sortOptions.add("ALPHA");
        sortOptions.add("DATE");
        sortOptions.add("LENGTH");

        buttonRow.addButton(new Button(Const.UI_BUTTON_1, "LOAD", () -> {
            // nothing right now
        }));

        buttonRow.addButton(new Button(Const.UI_BUTTON_2, "RENAME", () -> {
            // nothing right now
        }));

        buttonRow.addButton(new Button(Const.UI_BUTTON_3, () -> {
            return sortOptions.get(sortVal);
        }, () -> {
            sortVal = (sortVal < sortOptions.size() - 1) ? sortVal + 1 : 0;
            updateSort();
        }));

        buttonRow.addButton(new Button(Const.UI_BUTTON_4, () -> {
            return sortAsc ? "ASC" : "DESC";
        }, () -> {
            sortAsc = !sortAsc;
            updateSort();
        }));

    }

    public void updateSort() {
        parent.fileBrowserSortBy(sortOptions.get(sortVal), sortAsc);
    }

    public void customKeyHandler(char key, int keyCode) {
        switch (keyCode) {
            case Const.UP:
                parent.fileBrowserSelectPrev();
                break;
            case Const.DOWN:
                parent.fileBrowserSelectNext();
                break;
        }
    }

}
