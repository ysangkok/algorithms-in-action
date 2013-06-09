package com.cim.AIA;

public class StepButton extends com.cim.AIA.ControlButton {
    final private static long serialVersionUID = -6736310483679896991L;
    private static boolean isDisabled;
    
    public static void allDisable(boolean b)
    {
        com.cim.AIA.StepButton.isDisabled = b;
    }
    
    public StepButton(String s, String s0, com.cim.AIA.Controlable a, com.cim.AIA.AlgorithmThread a0)
    {
        super(s, s0, a, a0);
        int i = com.cim.AIA.StepButton.isDisabled?1:0;
        if(i != 0)
        {
            this.setEnabled(false);
        }
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.StepButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(true);
        }
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.StepButton.isDisabled?1:0;
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
                this.setEnabled(true);
                break label0;
            }
            int i1 = a.getType();
            if(i1 == 123129)
            {
                this.setEnabled(false);
            }
        }
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.StepButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(true);
        }
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.StepButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(true);
        }
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.StepButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(true);
        }
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.StepButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(false);
        }
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        int i = com.cim.AIA.StepButton.isDisabled?1:0;
        if(i == 0)
        {
            this.setEnabled(true);
        }
    }
    
    protected com.cim.AIA.ControlEvent getControlEvent()
    {
        com.cim.AIA.ControlEvent a = new com.cim.AIA.ControlEvent((Object)this, 123123);
        return a;
    }
    
    protected void processThread()
    {
        com.cim.AIA.AlgorithmThread a = this.thread;
        a.stepMode();
        com.cim.AIA.AlgorithmThread a0 = this.thread;
        String s = a0.getHighlightPos();
        com.cim.AIA.AlgorithmThread a1 = this.thread;
        int i = a1.calcHiddenLines(s);
        com.cim.AIA.StepLog a2 = new com.cim.AIA.StepLog((byte)4, (byte)19, s, i);
        int i0 = com.cim.AIA.Logger.DEBUG?1:0;
        if(i0 != 0)
        {
            java.io.PrintStream a3 = System.err;
            StringBuilder a4 = new StringBuilder();
            StringBuilder a5 = a4.append("pos: ");
            StringBuilder a6 = a5.append(s);
            String s0 = a6.toString();
            a3.println(s0);
            java.io.PrintStream a7 = System.err;
            StringBuilder a8 = new StringBuilder();
            StringBuilder a9 = a8.append("nlines: ");
            StringBuilder a10 = a9.append(i);
            String s1 = a10.toString();
            a7.println(s1);
        }
        com.cim.AIA.Logger a11 = this.getLogger();
        if(a11 != null)
        {
            com.cim.AIA.Logger a12 = this.getLogger();
            a12.addLog((com.cim.AIA.Log)a2);
        }
    }
    
    static
    {
        com.cim.AIA.StepButton.isDisabled = false;
    }
}