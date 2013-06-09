package com.cim.AIA;

public class BackButton extends ControlButton
{
    private static final long serialVersionUID = -2331169966993698750L;
    private static boolean isDisabled;
    
    public static void allDisable(final boolean state) {
        BackButton.isDisabled = state;
    }
    
    public BackButton(final String name, final String explain, final Controlable controlable, final AlgorithmThread algorithmThread) {
        super(name, explain, controlable, algorithmThread);
        if (BackButton.isDisabled) {
            this.setEnabled(false);
        }
    }
    
    public void controlBack(final ControlEvent e) {
        if (!BackButton.isDisabled) {
            this.setEnabled(true);
        }
    }
    
    public void controlOther(final ControlEvent e) {
        if (!BackButton.isDisabled) {
            if (e.getType() == 123128) {
                this.setEnabled(false);
            }
            else if (e.getType() == 123129) {
                this.setEnabled(true);
            }
        }
    }
    
    public void controlPause(final ControlEvent e) {
        if (!BackButton.isDisabled) {
            this.setEnabled(true);
        }
    }
    
    public void controlReset(final ControlEvent e) {
        if (!BackButton.isDisabled) {
            this.setEnabled(false);
        }
    }
    
    public void controlRestart(final ControlEvent e) {
        if (!BackButton.isDisabled) {
            this.setEnabled(false);
        }
    }
    
    public void controlRun(final ControlEvent e) {
        if (!BackButton.isDisabled) {
            this.setEnabled(false);
        }
    }
    
    public void controlStep(final ControlEvent e) {
        if (!BackButton.isDisabled) {
            this.setEnabled(true);
        }
    }
    
    protected ControlEvent getControlEvent() {
        return new ControlEvent(this, 123124);
    }
    
    protected void processThread() {
        this.thread.backMode();
        final String pos = this.thread.getHighlightPos();
        final int nlines = this.thread.calcHiddenLines(pos);
        final Log l1 = new StepLog(4, 6, pos, nlines);
        if (this.getLogger() != null) {
            this.getLogger().addLog(l1);
        }
    }
    
    static {
        BackButton.isDisabled = false;
    }
}
