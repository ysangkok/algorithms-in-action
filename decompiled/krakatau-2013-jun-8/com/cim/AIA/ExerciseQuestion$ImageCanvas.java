package com.cim.AIA;

class ExerciseQuestion$ImageCanvas extends java.awt.Canvas {
    final private static long serialVersionUID = -4017393637683774381L;
    protected java.awt.Image image;
    protected boolean gotImage;
    final com.cim.AIA.ExerciseQuestion this$0;
    
    public ExerciseQuestion$ImageCanvas(com.cim.AIA.ExerciseQuestion a)
    {
        this.this$0 = a;
        super();
        this.gotImage = false;
    }
    
    public void paint(java.awt.Graphics a)
    {
        java.awt.Image a0 = this.image;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            int i = this.gotImage?1:0;
            if(i != 0)
            {
                java.awt.Image a1 = this.image;
                java.awt.Dimension a2 = this.getSize();
                int i0 = a2.width;
                java.awt.Dimension a3 = this.getSize();
                int i1 = a3.height;
                int i2 = a.drawImage(a1, 0, 0, i0, i1, (java.awt.image.ImageObserver)null)?1:0;
            }
        }
    }
    
    public void repaint()
    {
        java.awt.Graphics a = this.getGraphics();
        this.paint(a);
    }
    
    public void setImage(java.awt.Image a)
    {
        this.image = a;
        this.gotImage = true;
    }
    
    public void update(java.awt.Graphics a)
    {
        this.paint(a);
    }
}