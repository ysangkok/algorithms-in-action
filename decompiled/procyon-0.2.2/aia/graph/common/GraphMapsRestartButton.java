package aia.graph.common;

import localization.*;
import com.cim.AIA.*;

public class GraphMapsRestartButton extends ControlButton
{
    public static final int RESTART_TYPE = 2342;
    
    public GraphMapsRestartButton(final Controlable controlable, final AlgorithmThread thread) {
        super(Messages.getString("GraphMapsRestartButton.0"), Messages.getString("GraphMapsRestartButton.1"), controlable, thread);
    }
    
    protected ControlEvent getControlEvent() {
        return new ControlEvent(this, 2342);
    }
    
    protected void activate() {
        this.controlable.informControlListeners(this.getControlEvent());
        this.controlable.informControlListeners(new ControlEvent(this, 123128));
        this.processThread();
    }
    
    protected void processThread() {
    }
    
    public void controlStep(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlBack(final ControlEvent e) {
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
    
    public void controlOther(final ControlEvent e) {
        this.setEnabled(true);
    }
}
