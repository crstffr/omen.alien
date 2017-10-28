import ddf.minim.Minim;
import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.*;
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
            size(800, 480, P2D);
        } else {
            fullScreen(P2D);
        }
        App.inst = this;
    }

    @Override
    public void setup() {

        noCursor();
        background(0);
        frameRate(100);

        layouts = new LinkedHashMap<>();

        App.minim = new Minim(this);
        App.font = loadFont(Const.FONT_FILE);
        App.audioInput = App.minim.getLineIn(2, 2048);

        fps = new FPS();
        layouts.put("scope", new ScopeLayout());

        switchLayout("scope");

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

        if (key == 'q') {
            exit();
        }

        currentLayout().keyPressed(key);

    }

    int i = 0;

    public synchronized void draw() {

        background(0);

        fps.draw();
        currentLayout().draw();

        i = (i < width) ? i + 1 : 0;
    }

}
