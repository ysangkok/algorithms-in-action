package aia.graph.common;

import localization.*;
import com.cim.AIA.*;

public class HideStructureButton extends ControlButton
{
    public static final int HIDE_STRUCTURE_TYPE = 2351;
    
    public HideStructureButton(final Controlable controlable, final AlgorithmThread thread) {
        super(Messages.getString("HideStructureButton.0"), Messages.getString("HideStructureButton.1"), controlable, thread);
    }
    
    protected ControlEvent getControlEvent() {
        return new ControlEvent(this, 2351);
    }
    
    protected void activate() {
        this.controlable.informControlListeners(this.getControlEvent());
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
