package com.cim.AIA;

import java.util.*;

public abstract class Tween implements Runnable, Finishable
{
    protected Vector<FinishListener> finishListeners;
    protected boolean tweening;
    protected Thread thread;
    protected int sleepDuration;
    protected Paintable paintable;
    
    public Tween(final Paintable paintable) {
        super();
        this.finishListeners = new Vector();
        this.tweening = false;
        this.thread = new Thread();
        this.sleepDuration = 50;
        this.paintable = paintable;
    }
    
    public void addFinishListener(final FinishListener finishListener) {
        this.finishListeners.addElement(finishListener);
    }
    
    protected void execute() {
        while (this.isTweening()) {
            this.step();
            this.updateView();
            try {
                Thread.sleep((long)this.sleepDuration);
                continue;
            }
            catch (InterruptedException ex) {
                continue;
            }
            break;
        }
        this.updateView();
    }
    
    protected void finish() {
        final FinishEvent e = new FinishEvent(this);
        for (int i = 0; i < this.finishListeners.size(); ++i) {
            ((FinishListener)this.finishListeners.elementAt(i)).processFinishEvent(e);
        }
    }
    
    public int getSleepDuration() {
        return this.sleepDuration;
    }
    
    public boolean isTweening() {
        return this.tweening;
    }
    
    public void removeFinishListener(final FinishListener finishListener) {
        this.finishListeners.removeElement(finishListener);
    }
    
    public void run() {
        this.setTweening(true);
        this.execute();
        this.setTweening(false);
        this.finish();
    }
    
    public void setSleep(final int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }
    
    protected void setTweening(final boolean tweening) {
        this.tweening = tweening;
    }
    
    protected abstract void step();
    
    protected synchronized void updateView() {
        if (this.paintable == null) {
            System.out.println("com.cim.AIA.Tween.java Can't paint tween as paintable is null");
        }
        if (this.isTweening() && this.paintable != null) {
            this.paintable.paint();
        }
    }
}
