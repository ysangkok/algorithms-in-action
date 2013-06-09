package com.cim.AIA;

public class MultipleTween extends com.cim.AIA.Tween {
    protected java.util.Vector tweens;
    
    public MultipleTween(com.cim.AIA.Paintable a)
    {
        super(a);
        java.util.Vector a0 = new java.util.Vector();
        this.tweens = a0;
    }
    
    public void add(com.cim.AIA.Tween a)
    {
        java.util.Vector a0 = this.tweens;
        a0.addElement((Object)a);
    }
    
    public int getNumberOfTweens()
    {
        java.util.Vector a = this.tweens;
        int i = a.size();
        return i;
    }
    
    public boolean isTweening()
    {
        int i = 0;
        int i0 = 0;
        while(true)
        {
            int i1 = 0;
            int i2 = 0;
            java.util.Vector a = this.tweens;
            int i3 = i;
            int i4 = a.size();
            int i5 = i3;
            int i6 = i5;
            if(i0 >= i4)
            {
                return i5 != 0;
            }
            else
            {
                i1 = i6;
            }
            java.util.Vector a0 = this.tweens;
            int i7 = i1;
            Object a1 = a0.elementAt(i0);
            int i8 = i7;
            com.cim.AIA.Tween dummy = (com.cim.AIA.Tween)a1;
            com.cim.AIA.Tween a2 = (com.cim.AIA.Tween)a1;
            int i9 = i8;
            int i10 = a2.isTweening()?1:0;
            int i11 = i9;
            int i12 = i11;
            if(i10 == 0)
            {
                int i13 = i12;
                java.util.Vector a3 = this.tweens;
                int i14 = i13;
                int i15 = a3.removeElement((Object)a2)?1:0;
                int i16 = i14;
                i2 = i16;
            }
            else
            {
                i2 = 1;
            }
            int i17 = i0 + 1;
            i = i2;
            i0 = i17;
        }
    }
    
    public void removeAll()
    {
        java.util.Vector a = this.tweens;
        a.removeAllElements();
    }
    
    protected void setTweening(boolean b)
    {
        this.tweening = b;
        int i = b?1:0;
        int i0 = 0;
        while(true)
        {
            java.util.Vector a = this.tweens;
            int i1 = a.size();
            if(i0 >= i1)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.tweens;
                Object a1 = a0.elementAt(i0);
                com.cim.AIA.Tween dummy = (com.cim.AIA.Tween)a1;
                com.cim.AIA.Tween a2 = (com.cim.AIA.Tween)a1;
                a2.setTweening(i != 0);
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
    }
    
    protected void step()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.tweens;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.tweens;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.Tween dummy = (com.cim.AIA.Tween)a1;
                com.cim.AIA.Tween a2 = (com.cim.AIA.Tween)a1;
                a2.step();
                int i1 = i + 1;
                i = i1;
            }
        }
    }
}