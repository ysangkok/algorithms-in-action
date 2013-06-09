package com.cim.AIA;

public class BackButton extends com.cim.AIA.ControlButton {
    final private static long serialVersionUID = -2331169966993698750L;
    private static boolean isDisabled;
    
    public static void allDisable(boolean b)
    {
        com.cim.AIA.BackButton.isDisabled = b;
    }
    
    public BackButton(String s, String s0, com.cim.AIA.Controlable a, com.cim.AIA.AlgorithmThread a0)
    {
        super(s, s0, a, a0);
        int i = com.cim.AIA.BackButton.isDisabled?1:0;
        if(i != 0)
        {
            this.setEnabled(false);
        }
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.BackButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(true);
        }
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.BackButton.isDisabled?1:0;
        label0: {
            if(i != 0)
            {
                break label0;
            }
            int i0 = a.getType();
            label1: {
                if(i0 != 123128)
                {
                    break label1;
                }
                this.setEnabled(false);
                break label0;
            }
            int i1 = a.getType();
            if(i1 == 123129)
            {
                this.setEnabled(true);
            }
        }
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.BackButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(true);
        }
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.BackButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(false);
        }
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.BackButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(false);
        }
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.BackButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(false);
        }
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.BackButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(true);
        }
    }
    
    protected com.cim.AIA.ControlEvent getControlEvent()
    {
        com.cim.AIA.ControlEvent a = new com.cim.AIA.ControlEvent((Object)this, 123124);
        return a;
    }
    
    protected void processThread()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        a.backMode();
        com.cim.AIA.AlgorithmThread a0 = this.thread;
        String s = a0.getHighlightPos();
        com.cim.AIA.AlgorithmThread a1 = this.thread;
        int i = a1.calcHiddenLines(s);
        com.cim.AIA.StepLog a2 = new com.cim.AIA.StepLog((byte)4, (byte)6, s, i);
        com.cim.AIA.Logger a3 = this.getLogger();
        if(a3 != null)
        {
            com.cim.AIA.Logger a4 = this.getLogger();
            a4.addLog((com.cim.AIA.Log)a2);
        }
    }
    
    static
    {
        com.cim.AIA.BackButton.isDisabled = false;
    }
}