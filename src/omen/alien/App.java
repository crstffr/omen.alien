package omen.alien;

import ddf.minim.*;
import omen.alien.util.FileCounter;
import processing.core.*;
import omen.alien.component.*;

public class App {

    // Processing Instances
    public static PApplet inst;
    public static PFont font;
    public static Minim minim;
    public static AudioInput audioInput;
    public static AudioRecorder audioRecorder;

    // Components
    public static Title title;
    public static Stage stage;
    public static Waveform waveform;
    public static Ampliform ampliform;
    public static ButtonRow buttonRow;
    public static FileCounter fileCounter;

    // State
    public static String layout = "";
    public static boolean userInput = false;

}
