package omen.alien.layout.editor;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.Widget;
import omen.alien.component.MajorLayout;
import omen.alien.definition.WsMessage;
import omen.alien.definition.SampleCollectionItem;
import omen.alien.layout.editor.state.EditorStateEmpty;
import omen.alien.layout.editor.state.EditorStateLoaded;
import omen.alien.layout.editor.state.EditorStateLoading;
import omen.alien.layout.editor.widget.*;

public class EditorLayout extends MajorLayout {

    public EditorLayout() {

        super();
        color = Const.YELLOW;

        setupWidgets();
        setupStates();

        onEnable(() -> {
            setState("empty");
            loadFileBrowser();
        });

        onDisable(() -> {
            clearFileBrowser();
        });

        onDraw(() -> {

        });

    }

    void setupStates() {
        states.put("empty", new EditorStateEmpty(this));
        states.put("loaded", new EditorStateLoaded(this));
        states.put("loading", new EditorStateLoading(this));
        setState("empty");
    }

    void setupWidgets() {
        widgets.put("message", new EditorMessageWidget(this));
        widgets.put("fileBrowser", new EditorFileBrowserWidget(this));
        widgets.forEach((String key, Widget widget) -> {
            widget.setColor(color).show();
        });
    }

    public void loadFileBrowser() {
        App.databaseClient.fetchAllSamples(() -> {
            WsMessage msg = App.databaseClient.getResult();
            ((EditorFileBrowserWidget) widgets.get("fileBrowser")).populate(msg.sampleCollection);
        });
    }

    public void clearFileBrowser() {
        ((EditorFileBrowserWidget) widgets.get("fileBrowser")).clear();
    }

    public void loadSample(String _id) {
        setState("loading");
    }

    public void executeEdit() {

    }

}
