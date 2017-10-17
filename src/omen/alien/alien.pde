import processing.core.*;
import ddf.minim.analysis.*;
import ddf.minim.*;

PFont            Font;
Minim            minim;
WaveformRenderer waveform;
ScopeView        scopeView;
FFT              fft;
AudioInput       in;




int FR = 60;

int RED = 0xD20000;
int BLACK = 0x000000;
int GREEN = 0x00FF00;
int PRIMARY = GREEN;
int BACKGROUND = BLACK;

int BODY_PADDING = 10;
int BUTTON_ROW_HEIGHT = 50;

int TITLE_FONT_SIZE = 24;


int BUTTON_SIZE = 24;

int TITLE_X = 0;
int TITLE_Y = 0;
int TITLE_W = 0;
int TITLE_H = 0;

int STAGE_X = 1;
int STAGE_Y = 1;
int STAGE_H = 1;
int STAGE_W = 1;


int SCOPE_SAMPLE_RATE = 44100;
boolean SCOPE_LOCKED = true;

// MODES
// 1 = Oscilloscope
// 2 = Recording
// 3 = Sample
// 4 = Files
int MODE = 2;



 
void setup()
{
  frameRate(FR);
  size(800, 480, P3D);
  // fullScreen(P2D);
  background(BLACK);
 
  minim = new Minim(this);
  waveform = new WaveformRenderer();
  
  in = minim.getLineIn(Minim.MONO, 2048, SCOPE_SAMPLE_RATE, 16);
  fft = new FFT(in.bufferSize(), in.sampleRate());
  in.addListener(waveform);
  
  //in.enableMonitoring();
  
  Font = loadFont("Krungthep-24.vlw");
  // Font = loadFont("AvenirNext-Regular-24.vlw");
  
  // noSmooth();
  noCursor();
   
}

void calcStagePos() {
  STAGE_X = 0;  
}

void stop() {
  in.close();
  minim.stop();
}

void keyPressed() {
  switch (key) {
    case '1':
      MODE = 1;
      break;
    case '2':
      MODE = 2;
      break;
    case '3':
      MODE = 3;
      break;
    case '4':
      MODE = 4;
      break;
    case 'a':
      switch(MODE) {
        case 1:
          SCOPE_LOCKED = !SCOPE_LOCKED;
          break;
      }
      break;
  }
}

/**
 * 
 */
void RenderTitle() {
  
  textFont(Font);
  textSize(TITLE_FONT_SIZE);
  textAlign(LEFT, CENTER);
  
  String title = "";
  
  switch (MODE) {
    case 1:
      title = "SCOPE";
      break;
  case 2:
      title = "RECORD";
      break;
  case 3:
      title = "SAMPLE";
      break;
  case 4:
      title = "FILES";
      break; 
  }  
    
  fill(PRIMARY);
  text(title, BODY_PADDING, TITLE_FONT_SIZE / 2 + BODY_PADDING);
}

void RenderButtons() {
  
  String A = "A";
  String B = "B";
  String C = "C";
  String D = "D";
  
  switch (MODE) {
    case 1:
      A = (SCOPE_LOCKED) ? "LOCKED" : "UNLOCKED";
      break;
  case 2:
      A = "CANCEL";
      B = "REDO";
      break;
  case 3:
      
      break;
  case 4:
      
      break; 
  }  
  
  // Dividers
  
  int buttonTop = height - BUTTON_ROW_HEIGHT;
  int buttonBot = height - BODY_PADDING; 
  
  strokeWeight(1);
  stroke(PRIMARY);
  
  line(200, buttonTop, 200, buttonBot);
  line(400, buttonTop, 400, buttonBot);
  line(600, buttonTop, 600, buttonBot);
  
  // Text 
  
  int textY = ((buttonBot - buttonTop) / 2) + buttonTop;
  
  textFont(Font);
  textSize(BUTTON_SIZE);
  textAlign(CENTER, CENTER);
  
  text(A, 100, textY);
  text(B, 300, textY);
  text(C, 500, textY);  
  text(D, 700, textY);
  
}

void ClearScreen() {
  fill(BLACK);
  noStroke();
  rect(0,0,width,height);
}

void RenderStage() {
  
  switch(MODE) {
    case 1:
      waveform.draw();
      break;
  }
  
}
 
/**
 * 
 */
void draw()
{  
    
  ClearScreen();
  RenderTitle();
  RenderButtons();
  RenderStage();
  
  getFrequency();
 
}



void getFrequency() {
  
  stroke(#0000FF);
  
  fft.forward(in.left);
  
  for (int i = 0; i < fft.specSize(); i++)
  {
   // draw the line for frequency band i, scaling it by 4 so we can see it a bit better
    line(i, height, i, height - fft.getBand(i) * 4);
  }
  
  
  
}