package aia.graph.common;

import localization.*;
import com.cim.AIA.*;

public class ZoomInButton extends ControlButton
{
    private static final long serialVersionUID = -7883852258326973743L;
    public static final int ZOOM_IN_TYPE = 2340;
    
    public ZoomInButton(final Controlable controlable, final AlgorithmThread thread) {
        super(Messages.getString("ZoomInButton.0"), Messages.getString("ZoomInButton.1"), controlable, thread);
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
        return new ControlEvent(this, 2340);
    }
    
    protected void processThread() {
    }
}
