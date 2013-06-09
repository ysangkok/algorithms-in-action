package aia.graph.common;

import localization.*;
import com.cim.AIA.*;

public class ZoomOutButton extends ControlButton
{
    private static final long serialVersionUID = 3545601559441173565L;
    public static final int ZOOM_OUT_TYPE = 2341;
    
    public ZoomOutButton(final Controlable controlable, final AlgorithmThread thread) {
        super(Messages.getString("ZoomOutButton.0"), Messages.getString("ZoomOutButton.1"), controlable, thread);
    }
    
    protected void activate() {
        this.controlable.informControlListeners(this.getControlEvent());
        this.processThread();
    }
    
    public void controlBack(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlOther(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlPause(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlReset(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlRestart(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlRun(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlStep(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    protected ControlEvent getControlEvent() {
        return new ControlEvent(this, 2341);
    }
    
    protected void processThread() {
    }
}
