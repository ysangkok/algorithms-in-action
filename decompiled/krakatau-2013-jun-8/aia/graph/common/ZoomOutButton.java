package aia.graph.common;

public class ZoomOutButton extends com.cim.AIA.ControlButton {
    final private static long serialVersionUID = 3545601559441173565L;
    final public static int ZOOM_OUT_TYPE = 2341;
    
    public ZoomOutButton(com.cim.AIA.Controlable a, com.cim.AIA.AlgorithmThread a0)
    {
        String s = localization.Messages.getString("ZoomOutButton.0");
        Object a1 = a;
        String s0 = localization.Messages.getString("ZoomOutButton.1");
        super(s, s0, (com.cim.AIA.Controlable)a1, a0);
    }
    
    protected void activate()
    {
        Object a = this.controlable;
        com.cim.AIA.ControlEvent a0 = this.getControlEvent();
        ((com.cim.AIA.Controlable)a).informControlListeners(a0);
        this.processThread();
    }
    
    public void controlBack(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    public void controlOther(com.cim.AIA.ControlEvent a)
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
    
    public void controlStep(com.cim.AIA.ControlEvent a)
    {
        this.setEnabled(true);
    }
    
    protected com.cim.AIA.ControlEvent getControlEvent()
    {
        com.cim.AIA.ControlEvent a = new com.cim.AIA.ControlEvent((Object)this, 2341);
        return a;
    }
    
    protected void processThread()
    {
    }
}