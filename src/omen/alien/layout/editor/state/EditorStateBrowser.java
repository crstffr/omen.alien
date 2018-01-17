package omen.alien.layout.editor.state;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.Button;
import omen.alien.component.FileBrowser;
import omen.alien.definition.SampleCollectionItem;
import omen.alien.definition.WsMessage;
import omen.alien.layout.editor.EditorLayout;
import omen.alien.layout.editor.widget.EditorFileBrowserWidget;

import java.util.ArrayList;

public class EditorStateBrowser extends EditorState {

    Integer sortVal = 0;
    Boolean sortAsc = false;
    ArrayList<String> sortOptions = new ArrayList<>();

    EditorFileBrowserWidget browserWidget;
    public FileBrowser browser;

    public EditorStateBrowser(EditorLayout _parent) {
        super(_parent);

        sortOptions.add("DATE");
        sortOptions.add("ALPHA");
        sortOptions.add("LENGTH");

        browserWidget = new EditorFileBrowserWidget(this);
        browserWidget.setColor(parent.color);
        browser = browserWidget.fileBrowser;

        onEnable(() -> {
            populate();
        });

        onDisable(() -> {
            browserWidget.hide();
        });

        onDraw(() -> {
            browser.draw();
        });

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

    public void refresh() {
        browser.clearAll();
        loadItems();
    }

    public void removeSelectedItem() {
        browser.removeSelectedItem();
        System.out.println(browser.getItemCount());
        if (browser.getItemCount() == 0) {
            parent.setState("empty");
        }
    }

    public void populate() {
        loadItems(() -> {
            if (browser.getItemCount() > 0) {
                browserWidget.show();
            } else {
                parent.setState("empty");
            }
        });
    }

    public void loadItems() {
        loadItems(() -> {});
    }

    public void loadItems(Runnable cb) {
        if (browser.getItemCount() == 0) {
            App.databaseClient.fetchAllSamples(() -> {
                WsMessage msg = App.databaseClient.getResult();
                msg.sampleCollection.forEach((SampleCollectionItem item) -> {
                    browser.addItem(item);
                });
                browser.sortBy("DATE", false);
                cb.run();
            });
        } else {
            cb.run();
        }
    }



    public void updateSort() {
        browser.sortBy(sortOptions.get(sortVal), sortAsc);
    }

    public void customKeyHandler(char key, int keyCode) {
        switch (keyCode) {
            case Const.UP:
                browser.selectPrev();
                break;
            case Const.DOWN:
                browser.selectNext();
                break;
            case Const.ENTER:
            case Const.RETURN:
                System.out.println("LOAD IT");
                break;
            case Const.DELETE:
                parent.confirmDeleteSample(browser.getSelectedItem());
                break;
        }
    }

}
