import omen.alien.layout.record.RecordLayout;
import processing.core.*;
import ddf.minim.Minim;
import java.util.*;

import omen.alien.*;
import omen.alien.util.*;
import omen.alien.layout.*;
import omen.alien.component.*;

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

        // Processing

        this.smooth();
        this.noFill();
        this.noCursor();
        this.frameRate(60);
        this.background(Const.BLACK);
        App.font = this.loadFont(Const.FONT_FILE);

        // Components

        App.stage = new Stage();
        App.fileCounter = new FileCounter();

        // Audio
        App.minim = new Minim(this);
        App.audioInput = App.minim.getLineIn(Minim.MONO, 2048, 44100, 16);
        App.audioRecorder = App.minim.createRecorder(App.audioInput, "tmp.wav", true);

        // Layouts
        layouts.put("scope", new ScopeLayout());
        layouts.put("record", new RecordLayout());

        switchLayout("scope");
    }

    @Override
    public void keyPressed() {

        if (key == Const.ESC) {
            // this prevents processing from exiting
            key = Const.ESC_KEY;
        }

        if (App.userInput != null) {

            App.userInput.keyPress(key);

        } else {

            switch (this.key) {
                case 'q':
                    exit();
                    break;
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
