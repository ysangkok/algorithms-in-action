package com.cim.AIA;

public class InsertTween extends com.cim.AIA.Tween {
    protected com.cim.AIA.Sizeable sizeable;
    protected int steps;
    protected int stepsToGo;
    protected int finalWidth;
    protected double currentWidth;
    
    public InsertTween(com.cim.AIA.Paintable a, com.cim.AIA.Sizeable a0, int i)
    {
        super(a);
        Object a1 = a0;
        this.finalWidth = 0;
        this.currentWidth = 0.0;
        this.sizeable = (com.cim.AIA.Sizeable)a1;
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
                Object a = this.sizeable;
                int i2 = ((com.cim.AIA.Sizeable)a).getWidth();
                this.finalWidth = i2;
                this.currentWidth = 0.0;
                Object a0 = this.sizeable;
                double d = this.currentWidth;
                int i3 = (int)d;
                ((com.cim.AIA.Sizeable)a0).setWidth(i3);
            }
            Object a1 = this.sizeable;
            double d0 = this.currentWidth;
            int i4 = this.finalWidth;
            double d1 = (double)i4;
            int i5 = this.steps;
            double d2 = (double)i5;
            double d3 = d1 / d2;
            double d4 = d0 + d3;
            int i6 = (int)d4;
            ((com.cim.AIA.Sizeable)a1).setWidth(i6);
            double d5 = this.currentWidth;
            int i7 = this.finalWidth;
            double d6 = (double)i7;
            int i8 = this.steps;
            double d7 = (double)i8;
            double d8 = d6 / d7;
            double d9 = d5 + d8;
            this.currentWidth = d9;
            int i9 = this.stepsToGo;
            int i10 = i9 - 1;
            this.stepsToGo = i10;
        }
    }
}