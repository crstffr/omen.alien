package omen.alien.definition;

import com.google.gson.annotations.Expose;

public class SampleCollectionItem {
    @Expose
    public String id;
    @Expose
    public String name;
    @Expose
    public Float length;
    @Expose
    public Boolean selected;
    @Expose
    public Long created;

    public String getName() {
        return name;
    }
}
