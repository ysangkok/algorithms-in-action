// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DFSGraphMapsAnimationWindow.java

import com.cim.AIA.ControlEvent;
import com.cim.AIA.ControlListener;

class this._cls0
    implements ControlListener
{

    public void controlBack(ControlEvent e)
    {
        setBackMode();
    }

    public void controlPause(ControlEvent controlevent)
    {
    }

    public void controlReset(ControlEvent controlevent)
    {
    }

    public void controlRestart(ControlEvent controlevent)
    {
    }

    public void controlRun(ControlEvent controlevent)
    {
    }

    public void controlStep(ControlEvent controlevent)
    {
    }

    public void controlOther(ControlEvent e)
    {
        if(e.getType() == 2340)
            zoom(true);
        else
        if(e.getType() == 2341)
            zoom(false);
        else
        if(e.getType() == 2342)
            restartAlgorithm();
    }

    final DFSGraphMapsAnimationWindow this$0;

    ()
    {
        this$0 = DFSGraphMapsAnimationWindow.this;
        super();
    }
}
