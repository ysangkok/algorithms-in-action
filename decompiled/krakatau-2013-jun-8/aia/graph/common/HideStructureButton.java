package aia.graph.common;

public class HideStructureButton extends com.cim.AIA.ControlButton {
    final public static int HIDE_STRUCTURE_TYPE = 2351;
    
    public HideStructureButton(com.cim.AIA.Controlable a, com.cim.AIA.AlgorithmThread a0)
    {
        String s = localization.Messages.getString("HideStructureButton.0");
        Object a1 = a;
        String s0 = localization.Messages.getString("HideStructureButton.1");
        super(s, s0, (com.cim.AIA.Controlable)a1, a0);
    }
    
    protected com.cim.AIA.ControlEvent getControlEvent()
    {
        com.cim.AIA.ControlEvent a = new com.cim.AIA.ControlEvent((Object)this, 2351);
        return a;
    }
    
    protected void activate()
    {
        Object a = this.controlable;
        com.cim.AIA.ControlEvent a0 = this.getControlEvent();
        ((com.cim.AIA.Controlable)a).informControlListeners(a0);
        this.processThread();
    }
    
    protected void processThread()
    {
    }
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
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
        this.setEnabled(true);
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
}