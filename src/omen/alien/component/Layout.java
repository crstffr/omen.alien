package omen.alien.component;

public class Layout {

    Title title;
    ButtonRow buttonRow;

    boolean changed = false;
    boolean enabled = false;

    public Layout(Title _title, ButtonRow _buttonRow) {
        title = _title;
        buttonRow = _buttonRow;
    }

    public Layout enable() {
        enabled = true;
        changed = true;
        return this;
    }

    public Layout disable() {
        enabled = false;
        return this;
    }

    public Layout setTitle(String val) {
        title.text(val).draw();
        return this;
    }

    public Layout setButtons() {
        return this;
    }

    public Layout draw() {
        if (changed) {
            title.draw();
            buttonRow.draw();
        }
        return this;
    }

}
