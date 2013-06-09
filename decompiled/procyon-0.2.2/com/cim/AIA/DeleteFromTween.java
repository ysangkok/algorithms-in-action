package com.cim.AIA;

public class DeleteFromTween extends Tween
{
    protected Sizeable sizeable;
    protected int steps;
    protected int stepsToGo;
    protected int finalWidth;
    protected double currentWidth;
    protected ObjectContainer objectContainer;
    protected boolean sizeableInContainer;
    protected int positionToAddSizeableTo;
    
    public DeleteFromTween(final Paintable paintable, final Sizeable sizeable, final ObjectContainer objectContainer, final int steps) {
        this(paintable, sizeable, objectContainer, 0, steps);
        this.sizeableInContainer = true;
    }
    
    public DeleteFromTween(final Paintable paintable, final Sizeable sizeable, final ObjectContainer objectContainer, final int positionToAddSizableTo, final int steps) {
        super(paintable);
        this.finalWidth = 0;
        this.currentWidth = 0.0;
        this.sizeable = sizeable;
        this.steps = steps;
        this.objectContainer = objectContainer;
        this.stepsToGo = steps;
        this.positionToAddSizeableTo = this.positionToAddSizeableTo;
        this.sizeableInContainer = false;
    }
    
    protected void step() {
        if (this.stepsToGo > 0) {
            if (this.stepsToGo == this.steps) {
                this.setTweening(true);
                this.finalWidth = 0;
                this.currentWidth = (double)this.sizeable.getWidth();
                this.sizeable.setWidth((int)this.currentWidth);
                if (!this.sizeableInContainer) {
                    this.objectContainer.add(this.sizeable, this.positionToAddSizeableTo);
                }
            }
            this.sizeable.setWidth((int)(this.currentWidth - this.currentWidth / (double)this.steps));
            this.currentWidth = this.currentWidth - this.currentWidth / (double)this.steps;
            this.stepsToGo = this.stepsToGo - 1;
            if (this.stepsToGo == 0) {
                this.objectContainer.remove(this.sizeable);
            }
        }
        else {
            this.setTweening(false);
        }
    }
}
