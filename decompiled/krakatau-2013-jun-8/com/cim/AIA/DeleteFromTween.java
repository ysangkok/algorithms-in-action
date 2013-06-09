package com.cim.AIA;

public class DeleteFromTween extends com.cim.AIA.Tween {
    protected com.cim.AIA.Sizeable sizeable;
    protected int steps;
    protected int stepsToGo;
    protected int finalWidth;
    protected double currentWidth;
    protected com.cim.AIA.ObjectContainer objectContainer;
    protected boolean sizeableInContainer;
    protected int positionToAddSizeableTo;
    
    public DeleteFromTween(com.cim.AIA.Paintable a, com.cim.AIA.Sizeable a0, com.cim.AIA.ObjectContainer a1, int i)
    {
        this(a, a0, a1, 0, i);
        this.sizeableInContainer = true;
    }
    
    public DeleteFromTween(com.cim.AIA.Paintable a, com.cim.AIA.Sizeable a0, com.cim.AIA.ObjectContainer a1, int i, int i0)
    {
        super(a);
        Object a2 = a0;
        Object a3 = a1;
        this.finalWidth = 0;
        this.currentWidth = 0.0;
        this.sizeable = (com.cim.AIA.Sizeable)a2;
        this.steps = i0;
        this.objectContainer = (com.cim.AIA.ObjectContainer)a3;
        this.stepsToGo = i0;
        int i1 = this.positionToAddSizeableTo;
        this.positionToAddSizeableTo = i1;
        this.sizeableInContainer = false;
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
            label2: {
                if(i0 != i1)
                {
                    break label2;
                }
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
                int i4 = this.sizeableInContainer?1:0;
                if(i4 == 0)
                {
                    Object a1 = this.objectContainer;
                    Object a2 = this.sizeable;
                    int i5 = this.positionToAddSizeableTo;
                    ((com.cim.AIA.ObjectContainer)a1).add(a2, i5);
                }
            }
            Object a3 = this.sizeable;
            double d1 = this.currentWidth;
            double d2 = this.currentWidth;
            int i6 = this.steps;
            double d3 = (double)i6;
            double d4 = d2 / d3;
            double d5 = d1 - d4;
            int i7 = (int)d5;
            ((com.cim.AIA.Sizeable)a3).setWidth(i7);
            double d6 = this.currentWidth;
            double d7 = this.currentWidth;
            int i8 = this.steps;
            double d8 = (double)i8;
            double d9 = d7 / d8;
            double d10 = d6 - d9;
            this.currentWidth = d10;
            int i9 = this.stepsToGo;
            int i10 = i9 - 1;
            this.stepsToGo = i10;
            int i11 = this.stepsToGo;
            if(i11 == 0)
            {
                Object a4 = this.objectContainer;
                Object a5 = this.sizeable;
                ((com.cim.AIA.ObjectContainer)a4).remove(a5);
            }
        }
    }
}