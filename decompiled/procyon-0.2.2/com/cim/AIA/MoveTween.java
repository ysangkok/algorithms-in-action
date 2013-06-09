package com.cim.AIA;

import java.awt.*;

public class MoveTween extends Tween
{
    protected Moveable moveable;
    protected Point from;
    protected Point to;
    protected boolean doEntire;
    protected int steps;
    protected int stepsToGo;
    
    public MoveTween(final Paintable paintable, final Moveable moveable, final Point from, final Point to, final boolean doEntire, final int steps) {
        super(paintable);
        this.doEntire = true;
        this.moveable = moveable;
        this.from = new Point(from.x, from.y);
        this.to = new Point(to.x, to.y);
        this.doEntire = doEntire;
        this.steps = steps;
        this.stepsToGo = steps;
    }
    
    protected void step() {
        if (this.stepsToGo > 0) {
            if (this.stepsToGo == this.steps) {
                this.setTweening(true);
                this.moveable.setX(this.from.x);
                this.moveable.setY(this.from.y);
            }
            final float xDifference = (float)Math.round((float)(this.to.x - this.moveable.getX()));
            final float yDifference = (float)Math.round((float)(this.to.y - this.moveable.getY()));
            if (this.doEntire) {
                this.moveable.shiftEntire(Math.round(xDifference / (float)this.stepsToGo), Math.round(yDifference / (float)this.stepsToGo));
            }
            else {
                this.moveable.setX(this.moveable.getX() + Math.round(xDifference / (float)this.stepsToGo));
                this.moveable.setY(this.moveable.getY() + Math.round(yDifference / (float)this.stepsToGo));
            }
            this.stepsToGo = this.stepsToGo - 1;
        }
        else {
            this.setTweening(false);
        }
    }
}
