package omen.alien.layout.scope;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.*;
import processing.core.PGraphics;

/**
 * Created by crstffr on 10/28/17.
 */
public class ScopeLayout extends MajorLayout {

    Waveform waveform;

    public ScopeLayout() {
        super();
        color = Const.GREEN;

        waveform = new Waveform();
        waveform.setColor(color);

        setupButtons();

        onEnable(() -> {
            waveform.enable();
            waveform.attachToInput(App.audioInput);
        });

        onDisable(() -> {
            waveform.disable();
            waveform.removeFromInput(App.audioInput);
        });

        onDraw(() -> {
            waveform.draw();
        });

    }

    void setupButtons() {
        buttonRow.addButton(new Button(Const.UI_BUTTON_1, () -> {
            return waveform.locked ? "LOCKED" : "UNLOCKED";
        }, () -> {
            waveform.toggleLock();
        }));
        buttonRow.addButton(new Button(Const.UI_BUTTON_2, () -> {
            return waveform.locked ? (waveform.falling ? "FALLING" : "RISING") : "-";
        }, () -> {
            if (waveform.locked) {
                waveform.toggleFalling();
            }
        }));
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }
}
