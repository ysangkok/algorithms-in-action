package com.cim.AIA;

public class RunButton extends ControlButton
{
    private static final long serialVersionUID = 2639777629215754015L;
    
    public RunButton(final String name, final String explain, final Controlable controlable, final AlgorithmThread algorithmThread) {
        super(name, explain, controlable, algorithmThread);
    }
    
    protected void activate() {
        if (this.thread.canDoRun()) {
            this.controlable.informControlListeners(this.getControlEvent());
            this.processThread();
        }
    }
    
    public void controlBack(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlOther(final ControlEvent e) {
        if (e.getType() == 123128) {
            this.setEnabled(true);
        }
        else if (e.getType() == 123129) {
            this.setEnabled(false);
        }
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
        this.setEnabled(false);
    }
    
    public void controlStep(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    protected ControlEvent getControlEvent() {
        return new ControlEvent(this, 123127);
    }
    
    protected void processThread() {
        final Log l1 = new RunLog(4, 17, this.thread.getPosition());
        if (this.getLogger() != null) {
            this.getLogger().addLog(l1);
        }
        this.thread.runMode();
    }
}
