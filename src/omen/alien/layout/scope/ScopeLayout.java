package omen.alien.layout.scope;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.*;

public class ScopeLayout extends MajorLayout {

    Waveform waveform;

    public ScopeLayout() {
        super();
        color = Const.GREEN;
        waveform = new Waveform();
        waveform.setColor(color);

        setupButtons();

        onEnable(() -> {
            App.audio.connectInput();
            waveform.attachToInput(App.audio.getInput());
            waveform.startCapture().show();
        });

        onDisable(() -> {
            waveform.stopCapture().clear().hide();
            waveform.detachInput();
        });

        onClear(() -> {
            waveform.clear();
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
            return waveform.locked ? (waveform.falling ? "RISE" : "FALL") : "-";
        }, () -> {
            if (waveform.locked) {
                waveform.toggleFalling();
            }
        }));

        buttonRow.addButton(); // blank

        buttonRow.addButton(new Button(Const.UI_BUTTON_4, () -> {
            return (waveform.channel == 1) ? "LEFT" : "RIGHT";
        }, () -> {
            waveform.toggleChannel();
        }));

    }
}
