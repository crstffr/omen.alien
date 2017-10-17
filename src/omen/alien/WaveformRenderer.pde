class WaveformRenderer implements AudioListener
{
  private float[] left;
  private float[] prev;
  
  WaveformRenderer()
  {
    left = null;
    prev = null;
  }
  
  public synchronized void samples(float[] samp)
  {
    left = samp;
  }
  
  public synchronized void samples(float[] sampL, float[] sampR)
  {
    left = sampL;
  }
  
  synchronized void draw() {
    
    float Li0;
    float Li1;
    int crossing = 0;
    
    if (left == null) { return; }
    
    
    
    
    noFill();
    strokeWeight(2);
    stroke(PRIMARY);  
        
    for(int i = 0; i < left.length - 1 && i < width + crossing; i++) {
    
      Li0 = left[i];
      Li1 = left[i+1];
            
      int multiplier = height / 2;
      
      if (SCOPE_LOCKED) {
       
        if (crossing == 0 && Li0 < 0 && Li1 > 0) {
          crossing = i;
        }
        
        if (crossing != 0) {
          line(i - crossing, height/2 + Li0 * multiplier, i + 1 - crossing, height/2 + Li1 * multiplier);
        }
        
      } else {
          line(i, height/2 + Li0 * multiplier, i + 1, height/2 + Li1 * multiplier);
      }
      
      prev = left;
         
   }
    
    
    
    /*
    if ( left != null && right != null ) {
      
      noFill();
      stroke(PRIMARY);
      beginShape();
      for ( int i = 0; i < left.length; i++ ) {
        vertex(i, height/4 + left[i]*50);
      }
      endShape();
      beginShape();
      for ( int i = 0; i < right.length; i++ ) {
        vertex(i, 3*(height/4) + right[i]*50);
      }
      endShape();
      
    } else if ( left != null ) {
      
      noFill();
      stroke(PRIMARY);
      beginShape();
      for ( int i = 0; i < left.length; i++ )
      {
        vertex(i, height/2 + left[i]*250);
      }
      endShape();
    }
    */
  }
}