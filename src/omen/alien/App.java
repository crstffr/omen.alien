package omen.alien;

import ddf.minim.*;
import omen.alien.util.FileCounter;
import processing.core.*;
import omen.alien.component.*;
import omen.alien.component.layer.*;

public class App {

    // Processing Instances
    public static PApplet inst;
    public static PFont font;
    public static Minim minim;
    public static AudioInput audioInput;
    public static AudioRecorder audioRecorder;

    // Components
    public static StageLayer stage;

    // State
    public static String layout = "";
    public static UserInput userInput;

}
