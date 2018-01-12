package omen.alien;

import ddf.minim.*;
import omen.alien.audio.AudioDriver;
import processing.core.*;
import omen.alien.clients.*;
import omen.alien.component.*;
import omen.alien.component.layer.*;
import omen.alien.util.FileCounter;

public class App {

    // Processing Instances
    public static PFont font;
    public static PApplet inst;
    public static AudioDriver audio;

    // Components
    public static StageLayer stage;

    // State
    public static Router router;
    public static UserInput userInput;
    public static FileCounter fileCounter;

    // Daemon Clients
    public static RecordingClient recordingClient;
    public static WaveformClient waveformClient;
    public static DatabaseClient databaseClient;


}
