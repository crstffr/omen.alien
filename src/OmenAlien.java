import ddf.minim.Minim;
import omen.alien.App;
import omen.alien.Const;
import omen.alien.audio.AudioDriver;
import omen.alien.clients.RecordingClient;
import omen.alien.clients.WaveformClient;
import omen.alien.component.*;
import omen.alien.component.layer.StageLayer;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.scope.ScopeLayout;
import omen.alien.util.FileCounter;
import processing.core.PApplet;

import java.util.LinkedHashMap;

public class OmenAlien extends PApplet {

    FPS fps;
    ScopeLayout scopeLayout;
    RecordLayout recordLayout;

    LinkedHashMap<String, Layout> layouts;

    public static void main(String args[]) {
        PApplet.main("OmenAlien");
    }

    @Override
    public void settings() {
        if (displayWidth > 800) {
            size(800, 480, Const.RENDERER2D);
        } else {
            fullScreen(Const.RENDERER2D);
        }
        App.inst = this;
    }

    @Override
    public void setup() {

        noCursor();
        frameRate(100);
        background(Const.BACKGROUND);

        layouts = new LinkedHashMap<>();

        App.audio = new AudioDriver(this);
        App.font = loadFont(Const.FONT_FILE);
        App.stage = new StageLayer(Const.RENDERER2D);
        App.fileCounter = new FileCounter();

        App.recordingClient = new RecordingClient();
        App.recordingClient.connect();

        App.waveformClient = new WaveformClient();
        App.waveformClient.connect();

        fps = new FPS();
        scopeLayout = new ScopeLayout();
        recordLayout = new RecordLayout();

        layouts.put("scope", scopeLayout);
        layouts.put("record", recordLayout);

        //switchLayout("scope");
        switchLayout("record");

    }

    Layout currentLayout() {
        return layouts.get(App.layout);
    }

    public void switchLayout(String layout) {
        if (App.layout.equals(layout)) {
            return;
        }
        for (String key : layouts.keySet()) {
            if (key.equals(layout)) {
                App.layout = layout;
            } else {
                layouts.get(key).disable();
            }
        }
        App.audio.connectInput();
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

        switch (key) {
            case '1':
                switchLayout("scope");
                break;
            case '2':
                switchLayout("record");
                break;
            case 'w':
                switchLayout("record");
                recordLayout.toggleRecord();
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