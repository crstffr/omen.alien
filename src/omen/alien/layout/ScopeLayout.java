package omen.alien.layout;

import omen.alien.App;
import omen.alien.Const;
import omen.alien.component.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ScopeLayout extends MajorLayout {

    Waveform waveform;

    public ScopeLayout() {
        super();
        color = Const.GREEN;

        setupButtons();
        setupWaveform();

        onDraw(() -> waveform.draw());
        onEnable(() -> waveform.enable());
        onDisable(() -> waveform.disable().clear());
    }

    /**
     *
     */
    void setupButtons() {
        buttonRow.addButton(new Button(Const.UI_BUTTON_1, () -> {
            return waveform.locked ? "LOCKED" : "UNLOCKED";
        }, () -> {
            waveform.toggleLock();
        }));
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
        buttonRow.addButton(); // blank
    }

    /**
     *
     */
    void setupWaveform() {
        waveform = new Waveform(stage.view.createSubView());
        App.audioInput.addListener(waveform);
        waveform.setColor(color);
    }

}
