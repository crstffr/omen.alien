import ddf.minim.Minim;
import omen.alien.App;
import omen.alien.Const;
import omen.alien.Router;
import omen.alien.audio.AudioDriver;
import omen.alien.clients.DatabaseClient;
import omen.alien.clients.RecordingClient;
import omen.alien.clients.WaveformClient;
import omen.alien.component.*;
import omen.alien.component.layer.StageLayer;
import omen.alien.layout.editor.EditorLayout;
import omen.alien.layout.record.RecordLayout;
import omen.alien.layout.scope.ScopeLayout;
import omen.alien.util.FileCounter;
import processing.core.PApplet;

import java.util.LinkedHashMap;

public class OmenAlien extends PApplet {

    FPS fps;

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

        App.router = new Router();
        App.audio = new AudioDriver(this);
        App.font = loadFont(Const.FONT_FILE);
        App.stage = new StageLayer(Const.RENDERER2D);
        App.fileCounter = new FileCounter();

        App.recordingClient = new RecordingClient();
        App.recordingClient.connect();

        App.waveformClient = new WaveformClient();
        App.waveformClient.connect();

        App.databaseClient = new DatabaseClient();
        App.databaseClient.connect();

        fps = new FPS();

        App.router.registerLayout("scope", new ScopeLayout());
        App.router.registerLayout("record", new RecordLayout());
        App.router.registerLayout("editor", new EditorLayout());

        App.router.switchLayout("record");

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
                App.router.switchLayout("scope");
                break;
            case '2':
                App.router.switchLayout("record");
                break;
            case '3':
                App.router.switchLayout("editor");
                break;
            case 'w':
                ((RecordLayout) App.router.switchLayout("record")).toggleRecord();
                break;
        }

        App.router.getCurrentLayout().keyPressed(key);

    }

    public void draw() {
        background(Const.BACKGROUND);
        App.router.getCurrentLayout().draw();
        fps.draw();
    }

}