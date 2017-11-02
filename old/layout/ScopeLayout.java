package omen.alien.layout;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.*;
import processing.core.PApplet;

public class ScopeLayout extends MajorLayout {

    Waveform waveform;

    public ScopeLayout() {
        super();
        color = Const.GREEN;

        setupButtons();

        /*

        waveform = new Waveform(stage.view1.createSubView());
        waveform.setColor(fgColor);

        setupButtons();

        onDraw(() -> {
            waveform.draw();
        });

        onEnable(() -> {
            waveform.show();
            waveform.attachToInput(App.audioInput);
        });

        onDisable(() -> {
            waveform.hide().clear();
            waveform.detachInput(App.audioInput);
        });
        */

    }

    /**
     *
     */
    void setupButtons() {
        /*
        buttonRow.addButton(new Button(Const.UI_BUTTON_1, () -> {
            return waveform.locked ? "LOCKED" : "UNLOCKED";
        }, () -> {
            waveform.toggleLock();
        }));
        */
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

}
