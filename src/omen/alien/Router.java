package omen.alien;

import omen.alien.component.Layout;
import java.util.LinkedHashMap;

public class Router {

    String currentLayoutName = "";
    LinkedHashMap<String, Layout> layouts;

    public Router() {
        layouts = new LinkedHashMap<>();
    }

    public void registerLayout(String _name, Layout _layout) {
        layouts.put(_name, _layout);
    }

    public Layout getCurrentLayout() {
        return layouts.get(currentLayoutName);
    }

    public Layout getLayout(String _name) {
        return layouts.get(_name);
    }

    public Layout switchLayout(String _name) {
        if (currentLayoutName.equals(_name)) {
            return getCurrentLayout();
        }
        for (String key : layouts.keySet()) {
            if (key.equals(_name)) {
                currentLayoutName = _name;
            } else {
                layouts.get(key).disable();
            }
        }
        Layout current = getCurrentLayout();
        current.enable();
        return current;
    }



}
