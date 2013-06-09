package com.cim.AIA;

public class ResetButton extends com.cim.AIA.ControlButton {
    final private static long serialVersionUID = 6634795993700422496L;
    
    public ResetButton(String s, String s0, com.cim.AIA.AnimationWindow a, com.cim.AIA.AlgorithmThread a0)
    {
        super(s, s0, (com.cim.AIA.Controlable)a, a0);
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
                this.setEnabled(false);
                break label1;
            }
            int i0 = a.getType();
            if(i0 == 123129)
            {
                this.setEnabled(true);
            }
        }
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(false);
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    protected com.cim.AIA.ControlEvent getControlEvent()
    {
        com.cim.AIA.ControlEvent a = new com.cim.AIA.ControlEvent((Object)this, 123126);
        return a;
    }
    
    protected void processThread()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        String s = a.getPosition();
        com.cim.AIA.Log a0 = new com.cim.AIA.Log((byte)4, (byte)15, s);
        com.cim.AIA.Logger a1 = this.getLogger();
        if(a1 != null)
        {
            com.cim.AIA.Logger a2 = this.getLogger();
            a2.addLog(a0);
        }
        Object a3 = this.controlable;
        com.cim.AIA.AnimationWindow dummy = (com.cim.AIA.AnimationWindow)a3;
        com.cim.AIA.AnimationWindow a4 = (com.cim.AIA.AnimationWindow)a3;
        a4.resetThread();
    }
}