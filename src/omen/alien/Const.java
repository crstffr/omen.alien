package omen.alien;

import processing.core.PFont;
import processing.core.PApplet;
import processing.core.PConstants;

public class Const implements PConstants {

    public static PFont FONT;
    public static PApplet APP;

    final public static int WIDTH = 800;
    final public static int HEIGHT = 480;

    final public static int RED = 0xFFD20000;
    final public static int BLACK = 0xFF000000;
    final public static int GREEN = 0xFF00FF00;
    final public static int PRIMARY = GREEN;
    final public static int BACKGROUND = BLACK;

    final public static int BODY_PADDING = 10;

    final public static int TITLE_FONT_SIZE = 24;
    final public static int TITLE_VIEW_X = BODY_PADDING;
    final public static int TITLE_VIEW_Y = BODY_PADDING;
    final public static int TITLE_VIEW_W = WIDTH / 4;
    final public static int TITLE_VIEW_H = TITLE_FONT_SIZE + 2;
    final public static int TITLE_TEXT_Y = TITLE_FONT_SIZE / 2;
    final public static int TITLE_TEXT_X = 0;


    final public static int BUTTON_FONT_SIZE = 24;
    final public static int BUTTON_VIEW_X = BODY_PADDING;
    final public static int BUTTON_VIEW_H = BUTTON_FONT_SIZE * 2;
    final public static int BUTTON_VIEW_Y = HEIGHT - BODY_PADDING - BUTTON_VIEW_H;
    final public static int BUTTON_VIEW_W = WIDTH - (BODY_PADDING * 2);
    final public static int BUTTON_TEXT_Y = BUTTON_VIEW_H / 2;

    final public static int STAGE_VIEW_X = BODY_PADDING;
    final public static int STAGE_VIEW_Y = TITLE_VIEW_Y + TITLE_VIEW_H + 1;
    final public static int STAGE_VIEW_W = 0;
    final public static int STAGE_VIEW_H = 0;



}