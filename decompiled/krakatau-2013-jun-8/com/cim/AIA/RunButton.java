package com.cim.AIA;

public class RunButton extends com.cim.AIA.ControlButton {
    final private static long serialVersionUID = 2639777629215754015L;
    
    public RunButton(String s, String s0, com.cim.AIA.Controlable a, com.cim.AIA.AlgorithmThread a0)
    {
        super(s, s0, a, a0);
    }
    
    protected void activate()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        int i = a.canDoRun()?1:0;
        if(i != 0)
        {
            Object a0 = this.controlable;
            com.cim.AIA.ControlEvent a1 = this.getControlEvent();
            ((com.cim.AIA.Controlable)a0).informControlListeners(a1);
            this.processThread();
        }
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        int i = a.getType();
        label1: {
            label0: {
                if(i != 123128)
                {
                    break label0;
                }
                this.setEnabled(true);
                break label1;
            }
            int i0 = a.getType();
            if(i0 == 123129)
            {
                this.setEnabled(false);
            }
        }
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(false);
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    protected com.cim.AIA.ControlEvent getControlEvent()
    {
        com.cim.AIA.ControlEvent a = new com.cim.AIA.ControlEvent((Object)this, 123127);
        return a;
    }
    
    protected void processThread()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        String s = a.getPosition();
        com.cim.AIA.RunLog a0 = new com.cim.AIA.RunLog((byte)4, (byte)17, s);
        com.cim.AIA.Logger a1 = this.getLogger();
        if(a1 != null)
        {
            com.cim.AIA.Logger a2 = this.getLogger();
            a2.addLog((com.cim.AIA.Log)a0);
        }
        com.cim.AIA.AlgorithmThread a3 = this.thread;
        a3.runMode();
    }
}