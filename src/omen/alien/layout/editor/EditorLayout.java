package omen.alien.layout.editor;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.MajorLayout;
import omen.alien.definition.SampleCollectionItem;
import omen.alien.layout.editor.state.*;

public class EditorLayout extends MajorLayout {

    public EditorLayout() {

        super();
        color = Const.YELLOW;

        setupStates();

        onEnable(() -> {
            setState("browser");
        });

    }

    void setupStates() {
        states.put("browser", new EditorStateBrowser(this));
        states.put("loading", new EditorStateLoading(this));
        states.put("loaded", new EditorStateLoaded(this));
        states.put("delete", new EditorStateDelete(this));
        states.put("empty", new EditorStateBrowserEmpty(this));
        setState("browser");
    }

    public void refreshBrowser() {
        ((EditorStateBrowser) states.get("browser")).refresh();
    }

    public void confirmDeleteSample(SampleCollectionItem item) {
        setState("delete");
        ((EditorStateDelete) states.get("delete")).openConfirmDialog(item);
    }

    public void actuallyDeleteSample(SampleCollectionItem item, Runnable cb) {
        App.databaseClient.deleteSample(item.id, () -> {
            ((EditorStateBrowser) states.get("browser")).removeSelectedItem();
            cb.run();
        });
    }

    public void loadSampleById(String id) {
        setState("loading");
    }

    public void executeEdit() {

    }

}
