package com.cim.AIA;

public class MoveTween extends com.cim.AIA.Tween {
    protected com.cim.AIA.Moveable moveable;
    protected java.awt.Point from;
    protected java.awt.Point to;
    protected boolean doEntire;
    protected int steps;
    protected int stepsToGo;
    
    public MoveTween(com.cim.AIA.Paintable a, com.cim.AIA.Moveable a0, java.awt.Point a1, java.awt.Point a2, boolean b, int i)
    {
        super(a);
        Object a3 = a0;
        int i0 = b?1:0;
        this.doEntire = true;
        this.moveable = (com.cim.AIA.Moveable)a3;
        int i1 = a1.x;
        int i2 = a1.y;
        java.awt.Point a4 = new java.awt.Point(i1, i2);
        this.from = a4;
        int i3 = a2.x;
        int i4 = a2.y;
        java.awt.Point a5 = new java.awt.Point(i3, i4);
        this.to = a5;
        this.doEntire = i0 != 0;
        this.steps = i;
        this.stepsToGo = i;
    }
    
    protected void step()
    {
        int i = this.stepsToGo;
        label1: {
            label0: {
                if(i > 0)
                {
                    break label0;
                }
                this.setTweening(false);
                break label1;
            }
            int i0 = this.stepsToGo;
            int i1 = this.steps;
            if(i0 == i1)
            {
                this.setTweening(true);
                Object a = this.moveable;
                java.awt.Point a0 = this.from;
                int i2 = a0.x;
                ((com.cim.AIA.Moveable)a).setX(i2);
                Object a1 = this.moveable;
                java.awt.Point a2 = this.from;
                int i3 = a2.y;
                ((com.cim.AIA.Moveable)a1).setY(i3);
            }
            java.awt.Point a3 = this.to;
            int i4 = a3.x;
            Object a4 = this.moveable;
            int i5 = ((com.cim.AIA.Moveable)a4).getX();
            int i6 = i4 - i5;
            float f = (float)i6;
            int i7 = Math.round(f);
            float f0 = (float)i7;
            java.awt.Point a5 = this.to;
            int i8 = a5.y;
            Object a6 = this.moveable;
            int i9 = ((com.cim.AIA.Moveable)a6).getY();
            int i10 = i8 - i9;
            float f1 = (float)i10;
            int i11 = Math.round(f1);
            float f2 = (float)i11;
            int i12 = this.doEntire?1:0;
            if(i12 == 0)
            {
                Object a7 = this.moveable;
                Object a8 = this.moveable;
                int i13 = ((com.cim.AIA.Moveable)a8).getX();
                int i14 = this.stepsToGo;
                float f3 = (float)i14;
                float f4 = f0 / f3;
                int i15 = Math.round(f4);
                int i16 = i13 + i15;
                ((com.cim.AIA.Moveable)a7).setX(i16);
                Object a9 = this.moveable;
                Object a10 = this.moveable;
                int i17 = ((com.cim.AIA.Moveable)a10).getY();
                int i18 = this.stepsToGo;
                float f5 = (float)i18;
                float f6 = f2 / f5;
                int i19 = Math.round(f6);
                int i20 = i17 + i19;
                ((com.cim.AIA.Moveable)a9).setY(i20);
            }
            else
            {
                Object a11 = this.moveable;
                int i21 = this.stepsToGo;
                float f7 = (float)i21;
                float f8 = f0 / f7;
                int i22 = Math.round(f8);
                int i23 = this.stepsToGo;
                float f9 = (float)i23;
                float f10 = f2 / f9;
                int i24 = Math.round(f10);
                ((com.cim.AIA.Moveable)a11).shiftEntire(i22, i24);
            }
            int i25 = this.stepsToGo;
            int i26 = i25 - 1;
            this.stepsToGo = i26;
        }
    }
}