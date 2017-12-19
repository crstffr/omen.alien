package omen.alien.layout.scope;

import ddf.minim.AudioInput;
import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.*;

/**
 * Created by crstffr on 10/28/17.
 */
public class ScopeLayout extends MajorLayout {

    Waveform waveform;
    AudioInput input;

    public ScopeLayout() {
        super();
        color = Const.GREEN;
        waveform = new Waveform();
        waveform.setColor(color);
        input = App.audio.input;

        setupButtons();

        onEnable(() -> {
            waveform.attachToInput(input);
            waveform.startCapture().show();
        });

        onDisable(() -> {
            waveform.stopCapture().hide();
            waveform.detachInput().clear();
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
            int m = waveform.trigger;
            return waveform.locked ? (waveform.falling ? "RISE +" + m : "FALL +" + m) : "-";
        }, () -> {
            if (waveform.locked) {
                waveform.toggleFalling();
            }
        }));
        buttonRow.addButton(new Button(Const.UI_BUTTON_3, () -> {
            return waveform.locked ? "\\/" : "-";
        }, () -> {
            if (waveform.locked) {
                waveform.decreaseTrigger();
            }
        }));
        buttonRow.addButton(new Button(Const.UI_BUTTON_4, () -> {
            return waveform.locked ? "/\\" : "-";
        }, () -> {
            if (waveform.locked) {
                waveform.increaseTrigger();
            }
        }));
    }
}
