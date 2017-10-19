package omen.alien;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PConstants;

public class Const implements PConstants {

    public static PFont FONT;

    final public static String ROOT_PATH = System.getProperty("user.dir") + "/";
    final public static String VARS_PATH = ROOT_PATH + "vars/";
    final public static String SAMPLE_PATH = ROOT_PATH + "samples/";
    final public static String SAMPLE_TEMP_PATH = SAMPLE_PATH + "tmp/";
    final public static String SAMPLE_USER_PATH = SAMPLE_PATH + "user/";

    final public static int WIDTH = App.inst.width;
    final public static int HEIGHT = App.inst.height;

    final public static int RED = 0xFFD20000;
    final public static int BLACK = 0xFF000000;
    final public static int WHITE = 0xFFFFFFFF;
    final public static int GREEN = 0xFF00FF00;
    final public static int TRANSRED = 0x88D20000;
    final public static int TRANSGRAY = 0x88888888;
    final public static int TRANSBLACK = 0x88000000;
    final public static int TRANSWHITE = 0x88FFFFFF;
    final public static int TRANSPARENT = 0x00FFFFFF;
    final public static int BACKGROUND = BLACK;
    final public static int PRIMARY = GREEN;

    final public static int BODY_PADDING = 0;
    final public static int BODY_X = BODY_PADDING;
    final public static int BODY_Y = BODY_PADDING;
    final public static int BODY_W = WIDTH - (BODY_PADDING * 2);
    final public static int BODY_H = HEIGHT - (BODY_PADDING * 2);

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

    final public static int RECORD_BOX_X = STAGE_VIEW_W / 4;
    final public static int RECORD_BOX_Y = (STAGE_VIEW_H / 4) - 20;
    final public static int RECORD_BOX_W = STAGE_VIEW_W / 2;
    final public static int RECORD_BOX_H = STAGE_VIEW_H / 2;
    final public static int RECORD_BOX_R = 10; // corner radius

}