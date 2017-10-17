
class ScopeView implements AudioListener
{
  
  View view;
  
  ScopeView(int x, int y, int h, int w)
  {
    this.view = new View(x, y, h, w);
  }
  
  public synchronized void samples(float[] samp)
  {
    
  }
  
  public synchronized void samples(float[] sampL, float[] sampR)
  {
    
  }
  
  synchronized void draw() {
    
    
         
  }
  
}