package com.cim.AIA;

abstract public class Tween implements Runnable, com.cim.AIA.Finishable {
    protected java.util.Vector finishListeners;
    protected boolean tweening;
    protected Thread thread;
    protected int sleepDuration;
    protected com.cim.AIA.Paintable paintable;
    
    public Tween(com.cim.AIA.Paintable a)
    {
        super();
        Object a0 = a;
        java.util.Vector a1 = new java.util.Vector();
        this.finishListeners = a1;
        this.tweening = false;
        Thread a2 = new Thread();
        this.thread = a2;
        this.sleepDuration = 50;
        this.paintable = (com.cim.AIA.Paintable)a0;
    }
    
    public void addFinishListener(com.cim.AIA.FinishListener a)
    {
        java.util.Vector a0 = this.finishListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    protected void execute()
    {
        while(true)
        {
            int i = this.isTweening()?1:0;
            if(i == 0)
            {
                this.updateView();
                return;
            }
            this.step();
            this.updateView();
            label0: {
                int i0 = 0;
                try
                {
                    i0 = this.sleepDuration;
                }
                catch(InterruptedException ignoredException)
                {
                    break label0;
                }
                try
                {
                    long j = (long)i0;
                    Thread.sleep(j);
                }
                catch(InterruptedException ignoredException0)
                {
                }
            }
        }
    }
    
    protected void finish()
    {
        com.cim.AIA.FinishEvent a = new com.cim.AIA.FinishEvent((Object)this);
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.finishListeners;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a1 = this.finishListeners;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.FinishListener dummy = (com.cim.AIA.FinishListener)a2;
                ((com.cim.AIA.FinishListener)a2).processFinishEvent(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public int getSleepDuration()
    {
        int i = this.sleepDuration;
        return i;
    }
    
    public boolean isTweening()
    {
        int i = this.tweening?1:0;
        return i != 0;
    }
    
    public void removeFinishListener(com.cim.AIA.FinishListener a)
    {
        java.util.Vector a0 = this.finishListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void run()
    {
        this.setTweening(true);
        this.execute();
        this.setTweening(false);
        this.finish();
    }
    
    public void setSleep(int i)
    {
        this.sleepDuration = i;
    }
    
    protected void setTweening(boolean b)
    {
        this.tweening = b;
    }
    
    abstract protected void step();
    
    
    protected synchronized void updateView()
    {
        Object a = this.paintable;
        if(a == null)
        {
            java.io.PrintStream a0 = System.out;
            a0.println("com.cim.AIA.Tween.java Can't paint tween as paintable is null");
        }
        int i = this.isTweening()?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            Object a1 = this.paintable;
            if(a1 != null)
            {
                Object a2 = this.paintable;
                ((com.cim.AIA.Paintable)a2).paint();
            }
        }
    }
}