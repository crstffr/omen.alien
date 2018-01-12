package omen.alien.layout.record.state;

import omen.alien.Const;
import omen.alien.component.MajorLayout;
import omen.alien.layout.record.RecordLayout;


public class RecordState extends MajorLayout {

    RecordLayout parent;

    public RecordState(RecordLayout _parent) {
        parent = _parent;
        color = Const.RED;
    }

}
