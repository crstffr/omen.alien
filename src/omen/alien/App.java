package omen.alien;

import ddf.minim.*;
import ddf.minim.spi.AudioOut;
import omen.alien.audio.AudioDriver;
import omen.alien.clients.RecordingClient;
import omen.alien.util.FileCounter;
import processing.core.*;
import omen.alien.component.*;
import omen.alien.component.layer.*;

public class App {

    // Processing Instances
    public static PFont font;
    public static PApplet inst;
    public static AudioDriver audio;

    // Components
    public static StageLayer stage;

    // State
    public static String layout = "";
    public static UserInput userInput;
    public static FileCounter fileCounter;

    // Daemon Clients
    public static RecordingClient recordingClient;


}
