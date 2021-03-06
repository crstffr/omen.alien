package omen.alien.layout.editor.widget;


import omen.alien.App;
import omen.alien.component.FileBrowser;
import omen.alien.component.MajorLayout;
import omen.alien.component.Widget;
import omen.alien.definition.SampleCollectionItem;

import java.util.ArrayList;

public class EditorFileBrowserWidget extends Widget {

    public FileBrowser fileBrowser;

    public int x = App.stage.x;
    public int y = App.stage.y + 5;
    public int w = App.stage.w;
    public int h = App.stage.h;

    public EditorFileBrowserWidget(MajorLayout _parent) {

        parent = _parent;

        init(x, y, w, h);

        fileBrowser = new FileBrowser(x, y, w, h);

        onSetColor(() -> {
            fileBrowser.setColor(color);
        });

    }

}
