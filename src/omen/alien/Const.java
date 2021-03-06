package omen.alien;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PConstants;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Const implements PConstants {

    final public static String RENDERER2D = JAVA2D;
    final public static String RENDERER3D = JAVA2D;
    final public static String FONT_FILE = "AnonymousPro-Bold-110.vlw";
    final public static String ROOT_DIR = System.getProperty("user.dir") + "/";

    final public static String VARS_PATH = ROOT_DIR + "vars/";
    final public static String SAMPLE_PATH = ROOT_DIR + "samples/";
    final public static String SAMPLE_TEMP_PATH = SAMPLE_PATH + "tmp/";
    final public static String SAMPLE_USER_PATH = SAMPLE_PATH + "user/";

    final public static String USER_DIR = ROOT_DIR + "user/";
    final public static String USER_DATA_DIR = USER_DIR + "data/";
    final public static String USER_AUDIO_DIR = USER_DIR + "audio/";
    final public static String FILE_COUNTER = USER_DIR + ".filecount";

    final public static int WS_RECORDING_PORT = 8901;
    final public static int WS_PLAYBACK_PORT = 8902;
    final public static int WS_WAVEFORM_PORT = 8903;
    final public static int WS_GPIO_PORT = 8904;
    final public static int WS_DATA_PORT = 8905;

    final public static int ESC_KEY = 10000;
    final public static int WIDTH = App.inst.width;
    final public static int HEIGHT = App.inst.height;
    final public static int SAMPLE_RATE = 48000; // don't change

    final public static char UI_BUTTON_1 = 'a';
    final public static char UI_BUTTON_2 = 's';
    final public static char UI_BUTTON_3 = 'd';
    final public static char UI_BUTTON_4 = 'f';
    final public static char[] UI_BUTTONS = {UI_BUTTON_1, UI_BUTTON_2, UI_BUTTON_3, UI_BUTTON_4};

    final public static int RED = 0xFFD20000;
    final public static int BLACK = 0xFF000000;
    final public static int WHITE = 0xFFFFFFFF;
    final public static int GREEN = 0xFF00FF00;
    final public static int YELLOW = 0xFFFFD000;
    final public static int MIDRED = 0xFF6F0000;
    final public static int MIDGRAY = 0xFF484848;
    final public static int TRANSRED_DARK = 0xAAD20000;
    final public static int TRANSPARENT = 0x00FFFFFF;
    final public static int DIALOG_BACKGROUND = 0xAA000000;
    final public static int BACKGROUND = BLACK;
    final public static int PRIMARY = GREEN;

    final public static int BODY_PADDING = 0;
    final public static int BODY_X = BODY_PADDING;
    final public static int BODY_Y = BODY_PADDING;
    final public static int BODY_W = WIDTH - (BODY_PADDING * 2);
    final public static int BODY_H = HEIGHT - (BODY_PADDING * 2);

    final public static int FPS_Y = 0;
    final public static int FPS_H = 20;
    final public static int FPS_W = 40;
    final public static int FPS_X = WIDTH - FPS_W;

    final public static int TITLE_FONT_SIZE = 28;
    final public static int TITLE_VIEW_X = BODY_X;
    final public static int TITLE_VIEW_Y = BODY_Y;
    final public static int TITLE_VIEW_W = WIDTH / 4;
    final public static int TITLE_VIEW_H = TITLE_FONT_SIZE + 2;
    final public static int TITLE_TEXT_Y = TITLE_FONT_SIZE / 2;
    final public static int TITLE_TEXT_X = 0;

    final public static int BUTTON_FONT_SIZE = 32;
    final public static int BUTTON_VIEW_X = BODY_X;
    final public static int BUTTON_VIEW_H = BUTTON_FONT_SIZE * 2;
    final public static int BUTTON_VIEW_Y = HEIGHT - BODY_PADDING - BUTTON_VIEW_H;
    final public static int BUTTON_VIEW_W = BODY_W;
    final public static int BUTTON_TEXT_Y = (BUTTON_VIEW_H / 2) + 3;

    final public static int STAGE_VIEW_X = BODY_PADDING;
    final public static int STAGE_VIEW_Y = BODY_PADDING;
    final public static int STAGE_VIEW_W = BODY_W;
    final public static int STAGE_VIEW_H = BODY_H - BUTTON_VIEW_H - 1;

    final public static int CONFIRM_DIALOG_W = (int) Math.round((float) STAGE_VIEW_W * 0.6);
    final public static int CONFIRM_DIALOG_H = (int) Math.round((float) STAGE_VIEW_H * 0.6);;
    final public static int CONFIRM_DIALOG_X = (int) (STAGE_VIEW_W / 2) - (CONFIRM_DIALOG_W / 2);
    final public static int CONFIRM_DIALOG_Y = (int) (STAGE_VIEW_H / 2) - (CONFIRM_DIALOG_H / 2);

}