import omen.alien.*;
import omen.alien.component.*;
import omen.alien.layout.ScopeLayout;
import processing.core.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

public class OmenAlien extends PApplet {

    Title title;
    ButtonRow buttonRow;
    ScopeLayout scopeLayout;

    LinkedHashMap<String, Object> layouts = new LinkedHashMap<>();

    public static void main(String args[]) {
        PApplet.main("OmenAlien");
    }

    @Override
    public void settings() {
        size(800, 480, PConstants.JAVA2D);
        Const.APP = this;
    }

    @Override
    public void setup() {

        this.frameRate(60);
        this.background(Const.BLACK);

        Const.FONT = this.loadFont("Krungthep-24-smooth.vlw");

        title = new Title();
        title.color(Const.PRIMARY).text("SCOPERDOPER");

        buttonRow = new ButtonRow();
        buttonRow.color(Const.PRIMARY);

        scopeLayout = new ScopeLayout(title, buttonRow);
        layouts.put("scope", scopeLayout);


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
                switchMode("sample");
                break;
            case '4':
                switchMode("files");
                break;
            case 'a':
                break;
            case 's':
                break;
            case 'd':
                break;
            case 'f':
                break;
        }
    }

    public void switchMode(String mode) {

        Set<String> keys = layouts
    .keySet();

        for(String key : keys) {

            if (key.equals(mode)) {
                layouts
    .get(key);
            }

            System.out.println(key);
        }

        switch (mode) {
            case "scope":
                title.text("SCOPE");
                buttonRow.labels(new ArrayList<>(
                    Arrays.asList("LOCK", "-", "-", "-")
                ));
                break;
            case "record":
                title.text("RECORD");
                break;
            case "sample":
                title.text("SAMPLE");
                break;
            case "files":
                title.text("FILES");
                break;
        }
    }

    @Override
    public void draw() {

        title.draw();
        buttonRow.draw();
    }
}
