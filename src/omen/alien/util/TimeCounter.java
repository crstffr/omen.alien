package omen.alien.util;

import omen.alien.App;

public class TimeCounter {

    int now = 0;
    int start = 0;
    int total = 0;
    int current = 0;
    boolean enabled = false;

    public void enable() {
        enabled = true;
        start = App.inst.millis();
    }

    public void disable() {
        enabled = false;
        total += current;
        current = 0;
    }

    public void reset() {
        now = 0;
        start = 0;
        total = 0;
        current = 0;
        enabled = false;
    }

    public void run() {
        if (enabled) {
            now = App.inst.millis();
            current = now - start;
        }
    }

    public int getMillis() {
        return total + current;
    }

    public String toString() {

        int time = total + current;

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
