// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NewBackButton.java

import com.cim.AIA.*;

public class NewBackButton extends BackButton
{

    public NewBackButton(String name, String explain, AnimationWindow animationWindow, AlgorithmThread algorithmThread)
    {
        super(name, explain, animationWindow, algorithmThread);
        switchOffed = false;
    }

    public void controlBack(ControlEvent e)
    {
        if(switchOffed)
        {
            return;
        } else
        {
            super.controlBack(e);
            return;
        }
    }

    public void controlOther(ControlEvent e)
    {
        if(switchOffed)
        {
            return;
        } else
        {
            super.controlOther(e);
            return;
        }
    }

    public void controlPause(ControlEvent e)
    {
        if(switchOffed)
        {
            return;
        } else
        {
            super.controlPause(e);
            return;
        }
    }

    public void controlReset(ControlEvent e)
    {
        if(switchOffed)
        {
            return;
        } else
        {
            super.controlReset(e);
            return;
        }
    }

    public void controlRestart(ControlEvent e)
    {
        if(switchOffed)
        {
            return;
        } else
        {
            super.controlRestart(e);
            return;
        }
    }

    public void controlRun(ControlEvent e)
    {
        if(switchOffed)
        {
            return;
        } else
        {
            super.controlRun(e);
            return;
        }
    }

    public void controlStep(ControlEvent e)
    {
        if(switchOffed)
        {
            return;
        } else
        {
            super.controlStep(e);
            return;
        }
    }

    protected ControlEvent getControlEvent()
    {
        return super.getControlEvent();
    }

    public boolean getSwitch()
    {
        return switchOffed;
    }

    protected void processThread()
    {
        if(switchOffed)
        {
            return;
        } else
        {
            super.processThread();
            return;
        }
    }

    public void switchOff(boolean state)
    {
        switchOffed = state;
    }

    private static final long serialVersionUID = 0x8e8bb9255b2b26a8L;
    protected boolean switchOffed;
}
