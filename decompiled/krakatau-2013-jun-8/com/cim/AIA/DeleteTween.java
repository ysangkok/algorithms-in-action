package com.cim.AIA;

public class DeleteTween extends com.cim.AIA.Tween {
    protected com.cim.AIA.Sizeable sizeable;
    protected int steps;
    protected int stepsToGo;
    protected int finalWidth;
    protected double currentWidth;
    
    public DeleteTween(com.cim.AIA.Paintable a, com.cim.AIA.Sizeable a0, int i)
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
                this.finalWidth = 0;
                Object a = this.sizeable;
                int i2 = ((com.cim.AIA.Sizeable)a).getWidth();
                double d = (double)i2;
                this.currentWidth = d;
                Object a0 = this.sizeable;
                double d0 = this.currentWidth;
                int i3 = (int)d0;
                ((com.cim.AIA.Sizeable)a0).setWidth(i3);
            }
            Object a1 = this.sizeable;
            double d1 = this.currentWidth;
            double d2 = this.currentWidth;
            int i4 = this.steps;
            double d3 = (double)i4;
            double d4 = d2 / d3;
            double d5 = d1 - d4;
            int i5 = (int)d5;
            ((com.cim.AIA.Sizeable)a1).setWidth(i5);
            double d6 = this.currentWidth;
            double d7 = this.currentWidth;
            int i6 = this.steps;
            double d8 = (double)i6;
            double d9 = d7 / d8;
            double d10 = d6 - d9;
            this.currentWidth = d10;
            int i7 = this.stepsToGo;
            int i8 = i7 - 1;
            this.stepsToGo = i8;
        }
    }
}