package omen.alien.audio;

import omen.alien.App;
import omen.alien.definition.SampleData;
import omen.alien.definition.SampleInfo;

import java.util.ArrayList;

public class Sample {

    public String id;          // index in db
    public String name;        // user provided name
    public String filepath;    // absolute path to the sample wav file
    public String filename;    // initial auto incremented filename (aka foldername)
    public SampleInfo info;    // object containing all the details of the sample file

    ArrayList<Runnable> readyHandlers = new ArrayList<>();
    boolean ready = false;

    public Sample(String _id) {
        id = _id;
        fetchData(() -> {
            isReady();
        });
    }

    private void isReady() {
        readyHandlers.forEach((Runnable cb) -> {
            cb.run();
        });
        ready = true;
    }

    public void whenReady(Runnable cb) {
        if (ready) {
            cb.run();
        } else {
            readyHandlers.add(cb);
        }
    }

    public void fetchData(Runnable cb) {
        App.databaseClient.getSampleData(id, () -> {
            SampleData data = App.databaseClient.getResult().sampleData;
            filepath = data.filepath;
            filename = data.filename;
            info = data.info;
            name = data.name;
            cb.run();
        });
    }

    public void register() {

    }

}
