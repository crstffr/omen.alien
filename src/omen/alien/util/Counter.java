package omen.alien.util;

import omen.alien.App;

public class Counter {

    boolean run = false;

    int start = 0;
    int total = 0;
    int last = 0;
    int now = 0;

    public void start() {
        run = true;
        start = App.inst.millis();
    }

    public void stop() {
        run = false;
    }

    public void run() {
        if (run) {
            now = App.inst.millis();
        }
    }

    public void reset() {
        run = false;
        start = 0;
        total = 0;
        last = 0;
        now = 0;
    }

    public String toString() {

        int time = now - start;

        int H = 3600000;
        int M = H / 60;
        int S = M / 60;
        int F = S / 100;

        int modH = time % H;
        int modM = modH % M;
        int modS = modM % S;
        int modF = modS % F;

        String h = String.format("%02d", time / H);
        String m = String.format("%02d", modH / M);
        String s = String.format("%02d", modM / S);
        String f = String.format("%02d", modS / F);

        return h + " : " + m + " : " + s + " : " + f;
    }
}
