package omen.alien.util;

import omen.alien.Const;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileCounter {

    Path path;

    public FileCounter() {
        path = Paths.get(Const.FILE_COUNTER);
        init();
    }

    void init() {
        File f = new File(path.toString());
        if (!f.exists()) {
            try {
                Files.write(path, "1".getBytes());
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public int getIndex() {
        List<String> lines;
        int index = 0;
        try {
            lines = Files.readAllLines(path);
            index = Integer.parseInt(lines.get(0));
        } catch (IOException e) {
            System.out.println(e);
        }
        return index;
    }

    public void increment() {
        int index = getIndex() + 1;
        String str = Integer.toString(index);
        try {
            Files.write(path, str.getBytes());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
