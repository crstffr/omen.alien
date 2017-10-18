import omen.alien.*;
import omen.alien.component.*;
import omen.alien.layout.RecordLayout;
import omen.alien.layout.ScopeLayout;
import processing.core.*;

import java.util.LinkedHashMap;

public class OmenAlien extends PApplet {

    Title title;
    String mode;
    Stage stage;
    ButtonRow buttonRow;
    ScopeLayout scopeLayout;
    RecordLayout recordLayout;
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

        App.font = this.loadFont("Krungthep-24-smooth.vlw");

        stage = new Stage();

        title = new Title();
        title.setColor(Const.PRIMARY);

        buttonRow = new ButtonRow();
        buttonRow.setColor(Const.PRIMARY);

        scopeLayout = new ScopeLayout(title, stage, buttonRow);
        layouts.put("scope", scopeLayout);

        recordLayout = new RecordLayout(title, stage, buttonRow);
        layouts.put("record", recordLayout);

        switchMode("scope");
    }

    @Override
    public void keyPressed() {
        switch (this.key) {
            case '1':
                switchMode("scope");
                break;
            case '2':
                switchMode("record");
                break;
            case '3':
                // switchMode("sample");
                break;
            case '4':
                // switchMode("files");
                break;
        }

        layouts.get(mode).keyPressed(this.key);
    }

    public void switchMode(String _mode) {
        for(String key : layouts.keySet()) {
            if (key.equals(_mode)) {
                layouts.get(key).enable();
                mode = _mode;
            } else {
                layouts.get(key).disable();
            }
        }
    }

    @Override
    public void draw() {
        layouts.get(mode).draw();
    }
}
