import ddf.minim.Minim;
import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.*;
import omen.alien.component.layer.StageLayer;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.scope.ScopeLayout;
import processing.core.PApplet;

import java.util.LinkedHashMap;

public class OmenAlien extends PApplet {

    FPS fps;
    LinkedHashMap<String, Layout> layouts;

    public static void main(String args[]) {
        PApplet.main("OmenAlien");
    }

    @Override
    public void settings() {
        if (displayWidth > 800) {
            size(800, 480, Const.RENDERER);
        } else {
            //size(700, 380, Const.RENDERER);
            fullScreen(Const.RENDERER);
        }
        App.inst = this;
    }

    @Override
    public void setup() {

        noCursor();
        frameRate(100);
        background(Const.BACKGROUND);

        layouts = new LinkedHashMap<>();

        App.minim = new Minim(this);
        App.font = loadFont(Const.FONT_FILE);
        App.stage = new StageLayer(Const.RENDERER);
        App.audioInput = App.minim.getLineIn(2, 2048);

        fps = new FPS();
        layouts.put("scope", new ScopeLayout());
        layouts.put("record", new RecordLayout());

        switchLayout("scope");
        //switchLayout("record");

    }

    Layout currentLayout() {
        return layouts.get(App.layout);
    }

    public void switchLayout(String layout) {
        for(String key : layouts.keySet()) {
            if (key.equals(layout)) {
                App.layout = layout;
            } else {
                layouts.get(key).disable();
            }
        }
        currentLayout().enable();
    }

    @Override
    public void keyPressed() {

        if (key == Const.ESC) {
            // override esc so it doesn't quit out.
            key = Const.ESC_KEY;
        }

        if (App.userInput != null) {
            App.userInput.keyPress(key);
            return;
        }

        if (key == 'q') {
            exit();
        }

        switch(key) {
            case '1':
                switchLayout("scope");
                break;
            case '2':
                switchLayout("record");
                break;
        }

        currentLayout().keyPressed(key);

    }

    public void draw() {
        background(Const.BACKGROUND);
        currentLayout().draw();
        fps.draw();
    }

}
