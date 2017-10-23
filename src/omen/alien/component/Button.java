package omen.alien.component;

import java.util.concurrent.Callable;

public class Button {

    char key;
    String label;
    Callable<String> labelFn;
    Runnable action;

    public Button(char _key, Callable<String> _labelFn, Runnable _action) {
        key = _key;
        action = _action;
        labelFn = _labelFn;
    }

    public Button(char _key, String _label, Runnable _action) {
        key = _key;
        label = _label;
        action = _action;
    }

    public Button() {
        key = '-';
        label = "-";
        action = () -> {};
    }

    public String getLabel() {
        String result = "";
        if (label != null) {
            result = label;
        } else if (labelFn != null) {

            try {
                result = labelFn.call();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return result;
    }

    public void setLabel(String _label) {
        label = _label;
    }

    public void trigger() {
        action.run();
    }

}
