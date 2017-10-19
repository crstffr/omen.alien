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

        this.noCursor();
        this.frameRate(60);
        this.background(Const.BLACK);
        App.font = this.loadFont("AnonymousPro-Bold-48.vlw");

        // Components

        App.title = new Title();
        App.stage = new Stage();
        App.waveform = new Waveform();
        App.ampliform = new Ampliform();
        App.buttonRow = new ButtonRow();
        App.fileCounter = new FileCounter();

        App.title.setColor(Const.PRIMARY);
        App.buttonRow.setColor(Const.PRIMARY);

        // Audio

        App.minim = new Minim(this);
        App.audioInput = App.minim.getLineIn(Minim.MONO, 2048, 44100, 16);
        // fft = new FFT(in.bufferSize(), in.sampleRate());
        App.audioRecorder = App.minim.createRecorder(App.audioInput, "tmp.wav", true);
        App.audioInput.addListener(App.ampliform);
        App.audioInput.addListener(App.waveform);

        // Layouts

        layouts.put("scope", new ScopeLayout());
        layouts.put("record", new RecordLayout());

        switchLayout("scope");
    }

    @Override
    public void keyPressed() {
        if (!App.userInput) {
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
