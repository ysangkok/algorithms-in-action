package com.cim.AIA;

public class PauseButton extends ControlButton
{
    private static final long serialVersionUID = 3850562479978195154L;
    
    public PauseButton(final String name, final String explain, final Controlable controlable, final AlgorithmThread algorithmThread) {
        super(name, explain, controlable, algorithmThread);
    }
    
    public void controlBack(final ControlEvent e) {
        this.setEnabled(false);
    }
    
    public void controlOther(final ControlEvent e) {
        if (e.getType() == 123128) {
            this.setEnabled(false);
        }
        else if (e.getType() == 123129) {
            this.setEnabled(false);
        }
    }
    
    public void controlPause(final ControlEvent e) {
        this.setEnabled(false);
    }
    
    public void controlReset(final ControlEvent e) {
        this.setEnabled(false);
    }
    
    public void controlRestart(final ControlEvent e) {
        this.setEnabled(false);
    }
    
    public void controlRun(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlStep(final ControlEvent e) {
        this.setEnabled(false);
    }
    
    protected ControlEvent getControlEvent() {
        return new ControlEvent(this, 123125);
    }
    
    protected void processThread() {
        final Log l1 = new Log(4, 14, this.thread.getPosition());
        if (this.getLogger() != null) {
            this.getLogger().addLog(l1);
        }
        this.thread.pauseMode();
    }
}
