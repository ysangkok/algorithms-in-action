public class NewBackButton extends com.cim.AIA.BackButton {
    final private static long serialVersionUID = -8175237128466520408L;
    protected boolean switchOffed;
    
    public NewBackButton(String s, String s0, com.cim.AIA.AnimationWindow a, com.cim.AIA.AlgorithmThread a0)
    {
        super(s, s0, (com.cim.AIA.Controlable)a, a0);
        this.switchOffed = false;
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        int i = this.switchOffed?1:0;
        if(i == 0)
        {
            ((com.cim.AIA.BackButton)this).controlBack(a);
        }
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        int i = this.switchOffed?1:0;
        if(i == 0)
        {
            ((com.cim.AIA.BackButton)this).controlOther(a);
        }
    }
    
    public void controlPause(com.cim.AIA.ControlEvent a)
    {
        int i = this.switchOffed?1:0;
        if(i == 0)
        {
            ((com.cim.AIA.BackButton)this).controlPause(a);
        }
    }
    
    public void controlReset(com.cim.AIA.ControlEvent a)
    {
        int i = this.switchOffed?1:0;
        if(i == 0)
        {
            ((com.cim.AIA.BackButton)this).controlReset(a);
        }
    }
    
    public void controlRestart(com.cim.AIA.ControlEvent a)
    {
        int i = this.switchOffed?1:0;
        if(i == 0)
        {
            ((com.cim.AIA.BackButton)this).controlRestart(a);
        }
    }
    
    public void controlRun(com.cim.AIA.ControlEvent a)
    {
        int i = this.switchOffed?1:0;
        if(i == 0)
        {
            ((com.cim.AIA.BackButton)this).controlRun(a);
        }
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        int i = this.switchOffed?1:0;
        if(i == 0)
        {
            ((com.cim.AIA.BackButton)this).controlStep(a);
        }
    }
    
    protected com.cim.AIA.ControlEvent getControlEvent()
    {
        com.cim.AIA.ControlEvent a = ((com.cim.AIA.BackButton)this).getControlEvent();
        return a;
    }
    
    public boolean getSwitch()
    {
        int i = this.switchOffed?1:0;
        return i != 0;
    }
    
    protected void processThread()
    {
        int i = this.switchOffed?1:0;
        if(i == 0)
        {
            ((com.cim.AIA.BackButton)this).processThread();
        }
    }
    
    public void switchOff(boolean b)
    {
        this.switchOffed = b;
    }
}