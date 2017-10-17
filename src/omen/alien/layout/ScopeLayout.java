package omen.alien.layout;

import omen.alien.component.*;

public class ScopeLayout extends Layout {

    Title title;
    ButtonRow buttonRow;

    boolean locked = true;
    boolean changed = false;
    boolean enabled = false;

    public ScopeLayout(Title _title, ButtonRow _buttonRow) {
        super(_title, _buttonRow);
        title = _title;
        buttonRow = _buttonRow;
        setTitle("SCOPE");
    }

    public ScopeLayout enable() {
        enabled = true;
        changed = true;
        title.text("SCOPE").draw();
        buttonRow.draw();
        return this;
    }

    public ScopeLayout disable() {
        enabled = false;
        return this;
    }


    public ScopeLayout toggleLock() {
        locked = !locked;
        changed = true;
        return this;
    }

}
