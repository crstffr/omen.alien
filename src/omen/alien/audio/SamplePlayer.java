package omen.alien.audio;

import java.util.HashMap;
import ddf.minim.ugens.Sampler;
import omen.alien.App;


public class SamplePlayer {

    HashMap<String, Sampler> samplers = new HashMap<>();

    public void registerSample(String trigger, Sample sample) {

        Sampler current = samplers.get(trigger);
        if (current != null) {
            System.out.println("Current sampler exists... Remove it.");
            current.unpatch(App.audio.getOutput());
            samplers.remove(trigger);
        }

        sample.whenReady(() -> {
            System.out.println("Sample ready for registration...");
            Sampler sampler = new Sampler(sample.filepath, 4, App.audio.minim);
            System.out.println(sample.filepath);
            sampler.patch(App.audio.getOutput());
            System.out.println("Sampler patched to output...");
            samplers.put(trigger, sampler);
        });

    }

    public void play(String trigger) {
        System.out.println("Trigger sample: " + trigger);
        Sampler sampler = samplers.get(trigger);
        if (sampler != null) {
            System.out.println("Found sample to trigger... doing it?");
            sampler.trigger();
        }
    }

    public void stop() {
        samplers.forEach((String trigger, Sampler sampler) -> {
            sampler.stop();
        });
    }

}