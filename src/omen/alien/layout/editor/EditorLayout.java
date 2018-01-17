package omen.alien.layout.editor;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.Widget;
import omen.alien.component.MajorLayout;
import omen.alien.definition.WsMessage;
import omen.alien.layout.editor.state.EditorStateBrowser;
import omen.alien.layout.editor.state.EditorStateLoaded;
import omen.alien.layout.editor.state.EditorStateLoading;
import omen.alien.layout.editor.widget.*;

public class EditorLayout extends MajorLayout {

    EditorMessageWidget messageWidget;
    EditorFileBrowserWidget fileBrowserWidget;

    public EditorLayout() {

        super();
        color = Const.YELLOW;

        setupWidgets();
        setupStates();

        onEnable(() -> {
            setState("browser");
            loadFileBrowser();
        });

        onDisable(() -> {
            clearFileBrowser();
        });

        onDraw(() -> {

        });

    }

    void setupStates() {
        states.put("browser", new EditorStateBrowser(this));
        states.put("loaded", new EditorStateLoaded(this));
        states.put("loading", new EditorStateLoading(this));
        setState("browser");
    }

    void setupWidgets() {
        messageWidget = new EditorMessageWidget(this);
        fileBrowserWidget = new EditorFileBrowserWidget(this);

        widgets.put("message", messageWidget);
        widgets.put("fileBrowser", fileBrowserWidget);

        widgets.forEach((String key, Widget widget) -> {
            widget.setColor(color).show();
        });
    }

    public void loadFileBrowser() {
        App.databaseClient.fetchAllSamples(() -> {
            WsMessage msg = App.databaseClient.getResult();
            fileBrowserWidget.populate(msg.sampleCollection);
        });
    }

    public void fileBrowserScrollUp() {
        fileBrowserWidget.scrollUp();
    }

    public void fileBrowserScrollDown() {
        fileBrowserWidget.scrollDown();
    }

    public void fileBrowserSelectPrev() {
        fileBrowserWidget.selectPrev();
    }

    public void fileBrowserSelectNext() {
        fileBrowserWidget.selectNext();
    }

    public void fileBrowserSortBy(String field, Boolean asc) {
        fileBrowserWidget.sortBy(field, asc);
    }

    public void clearFileBrowser() {
        fileBrowserWidget.clear();
    }

    public void loadSample(String _id) {
        setState("loading");
    }

    public void executeEdit() {

    }

}
