// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChangeModeButton.java

import com.cim.AIA.*;

public class ChangeModeButton extends ControlButton
{

    public ChangeModeButton(String name, String explain, AnimationWindow animationWindow, AlgorithmThread algorithmThread)
    {
        super(name, explain, animationWindow, algorithmThread);
        reInitialiseAlgorithm = false;
        clearAlgorithmState = false;
        storeAlgorithmState = true;
        forceAlgorithmStore = true;
    }

    public void controlBack(ControlEvent e)
    {
        setEnabled(true);
    }

    public void controlOther(ControlEvent e)
    {
        if(e.getType() == 0x1e0f8)
            setEnabled(false);
        else
        if(e.getType() == 0x1e0f9)
            setEnabled(true);
    }

    public void controlPause(ControlEvent e)
    {
        setEnabled(true);
    }

    public void controlReset(ControlEvent e)
    {
        setEnabled(false);
    }

    public void controlRestart(ControlEvent e)
    {
        setEnabled(false);
    }

    public void controlRun(ControlEvent e)
    {
        setEnabled(true);
    }

    public void controlStep(ControlEvent e)
    {
        setEnabled(true);
    }

    protected ControlEvent getControlEvent()
    {
        return new ControlEvent(this, 0x1f965);
    }

    protected void processThread()
    {
    }

    public void setClearAlgorithmState(boolean clearAlgorithmState)
    {
        this.clearAlgorithmState = clearAlgorithmState;
    }

    public void setForceAlgorithmStore(boolean forceAlgorithmStore)
    {
        this.forceAlgorithmStore = forceAlgorithmStore;
    }

    public void setReinitialiseAlgorithm(boolean reInitialiseAlgorithm)
    {
        this.reInitialiseAlgorithm = reInitialiseAlgorithm;
    }

    public void setStoreAlgorithmState(boolean storeAlgorithmState)
    {
        this.storeAlgorithmState = storeAlgorithmState;
    }

    private static final long serialVersionUID = 0x36fe5f72cf08afe7L;
    private static final int CHANGE_MODE_EVENT = 0x1f965;
    protected boolean reInitialiseAlgorithm;
    protected boolean clearAlgorithmState;
    protected boolean storeAlgorithmState;
    protected boolean forceAlgorithmStore;
}
