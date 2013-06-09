package com.cim.AIA;

public class ResetButton extends ControlButton
{
    private static final long serialVersionUID = 6634795993700422496L;
    
    public ResetButton(final String name, final String explain, final AnimationWindow animationWindow, final AlgorithmThread algorithmThread) {
        super(name, explain, animationWindow, algorithmThread);
    }
    
    public void controlBack(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlOther(final ControlEvent e) {
        if (e.getType() == 123128) {
            this.setEnabled(false);
        }
        else if (e.getType() == 123129) {
            this.setEnabled(true);
        }
    }
    
    public void controlPause(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlReset(final ControlEvent e) {
        this.setEnabled(false);
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
        return new ControlEvent(this, 123126);
    }
    
    protected void processThread() {
        final Log l1 = new Log(4, 15, this.thread.getPosition());
        if (this.getLogger() != null) {
            this.getLogger().addLog(l1);
        }
        ((AnimationWindow)((AnimationWindow)this.controlable)).resetThread();
    }
}
