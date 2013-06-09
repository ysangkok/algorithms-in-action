package com.cim.AIA;

import java.util.*;

public class MultipleTween extends Tween
{
    protected Vector<Tween> tweens;
    
    public MultipleTween(final Paintable paintable) {
        super(paintable);
        this.tweens = new Vector();
    }
    
    public void add(final Tween tween) {
        this.tweens.addElement(tween);
    }
    
    public int getNumberOfTweens() {
        return this.tweens.size();
    }
    
    public boolean isTweening() {
        boolean stillTweening = false;
        for (int i = 0; i < this.tweens.size(); ++i) {
            final Tween tween = (Tween)this.tweens.elementAt(i);
            if (tween.isTweening()) {
                stillTweening = true;
            }
            else {
                this.tweens.removeElement(tween);
            }
        }
        return stillTweening;
    }
    
    public void removeAll() {
        this.tweens.removeAllElements();
    }
    
    protected void setTweening(final boolean tweening) {
        this.tweening = tweening;
        for (int i = 0; i < this.tweens.size(); ++i) {
            final Tween tween = (Tween)this.tweens.elementAt(i);
            tween.setTweening(tweening);
        }
    }
    
    protected void step() {
        for (int i = 0; i < this.tweens.size(); ++i) {
            final Tween tween = (Tween)this.tweens.elementAt(i);
            tween.step();
        }
    }
}
