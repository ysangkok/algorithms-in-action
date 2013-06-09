package com.cim.AIA;

public class InsertTween extends Tween
{
    protected Sizeable sizeable;
    protected int steps;
    protected int stepsToGo;
    protected int finalWidth;
    protected double currentWidth;
    
    public InsertTween(final Paintable paintable, final Sizeable sizeable, final int steps) {
        super(paintable);
        this.finalWidth = 0;
        this.currentWidth = 0.0;
        this.sizeable = sizeable;
        this.steps = steps;
        this.stepsToGo = steps;
    }
    
    protected void step() {
        if (this.stepsToGo > 0) {
            if (this.stepsToGo == this.steps) {
                this.setTweening(true);
                this.finalWidth = this.sizeable.getWidth();
                this.currentWidth = 0.0;
                this.sizeable.setWidth((int)this.currentWidth);
            }
            this.sizeable.setWidth((int)(this.currentWidth + (double)this.finalWidth / (double)this.steps));
            this.currentWidth = this.currentWidth + (double)this.finalWidth / (double)this.steps;
            this.stepsToGo = this.stepsToGo - 1;
        }
        else {
            this.setTweening(false);
        }
    }
}
