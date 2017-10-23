package omen.alien.component;

import omen.alien.App;
import omen.alien.Const;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInput {

    public String value = "";
    Pattern pattern = Pattern.compile("[a-zA-Z_0-9 -]");
    ArrayList<Runnable> onEnterHandlers = new ArrayList<>();
    ArrayList<Runnable> onEscapeHandlers = new ArrayList<>();
    ArrayList<Runnable> onChangeHandlers = new ArrayList<>();

    public void startCapture() {
        App.userInput = this;
    }

    public void stopCapture() {
        App.userInput = null;
    }

    public void setPattern(String _pattern) {
        pattern = Pattern.compile(_pattern);
    }

    public void keyPress(char key) {
        switch (key) {
            case Const.ESC_KEY:
                escape();
                reset();
                break;
            case Const.ENTER:
            case Const.RETURN:
                enter();
                reset();
                break;
            case Const.BACKSPACE:
                value = value.replaceFirst(".$","");
                change();
                break;
            default:
                if (pattern.matcher(Character.toString(key)).matches()) {
                    value += key;
                    change();
                }
                break;
        }
    }

    void reset() {
        value = "";
    }

    void change() {
        for (Runnable fn : onChangeHandlers) fn.run();
    }

    void escape() {
        for (Runnable fn : onEscapeHandlers) fn.run();
    }

    void enter() {
        for (Runnable fn : onEnterHandlers) fn.run();
    }

    public void onChange(Runnable fn) {
        onChangeHandlers.add(fn);
    }

    public void onEscape(Runnable fn) {
        onEscapeHandlers.add(fn);
    }

    public void onEnter(Runnable fn) {
        onEnterHandlers.add(fn);
    }

}
