package omen.alien.component.layer;

import omen.alien.Const;

/**
 * Created by crstffr on 10/28/17.
 */
public class StageLayer extends Layer {
    public StageLayer() {
        super(Const.STAGE_VIEW_X,
              Const.STAGE_VIEW_Y,
              Const.STAGE_VIEW_W,
              Const.STAGE_VIEW_H,
              Const.RENDERER2D
        );
    }
    public StageLayer(String renderer) {
        super(Const.STAGE_VIEW_X,
              Const.STAGE_VIEW_Y,
              Const.STAGE_VIEW_W,
              Const.STAGE_VIEW_H,
              renderer
        );
    }
}
