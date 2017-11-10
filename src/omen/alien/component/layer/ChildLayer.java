package omen.alien.component.layer;

import omen.alien.Const;

/**
 * Created by crstffr on 10/29/17.
 */
public class ChildLayer extends Layer {

    public ChildLayer(Layer _l, int _x, int _y, int _w, int _h) {
        super(_l.x + _x, _l.y + _y, _w, _h, Const.RENDERER2D);
    }

    public ChildLayer(Layer _l, int _x, int _y, int _w, int _h, String _r) {
        super(_l.x + _x, _l.y + _y, _w, _h, _r);
    }

}
