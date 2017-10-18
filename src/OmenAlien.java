import processing.core.*;
import java.util.LinkedHashMap;

import omen.alien.component.*;
import omen.alien.layout.*;
import omen.alien.*;

public class OmenAlien extends PApplet {

    LinkedHashMap<String, Layout> layouts = new LinkedHashMap<>();
    public static void main(String args[]) {
        PApplet.main("OmenAlien");
    }

    @Override
    public void settings() {
        size(800, 480, PConstants.JAVA2D);
        App.inst = this;
    }

    @Override
    public void setup() {

        this.frameRate(60);
        this.background(Const.BLACK);

        App.title = new Title();
        App.stage = new Stage();
        App.buttonRow = new ButtonRow();
        App.font = this.loadFont("AnonymousPro-Bold-48.vlw");

        App.title.setColor(Const.PRIMARY);
        App.buttonRow.setColor(Const.PRIMARY);

        layouts.put("scope", new ScopeLayout());
        layouts.put("record", new RecordLayout());

        switchLayout("scope");
    }

    @Override
    public void keyPressed() {
        switch (this.key) {
            case '1':
                switchLayout("scope");
                break;
            case '2':
                switchLayout("record");
                break;
            case '3':
                // switchLayout("sample");
                break;
            case '4':
                // switchLayout("files");
                break;
        }

        layouts.get(App.layout).keyPressed(this.key);
    }

    public void switchLayout(String layout) {
        for(String key : layouts.keySet()) {
            if (key.equals(layout)) {
                App.layout = layout;
            } else {
                layouts.get(key).disable();
            }
        }
        layouts.get(App.layout).enable();
    }

    @Override
    public void draw() {
        layouts.get(App.layout).draw();
    }
}
