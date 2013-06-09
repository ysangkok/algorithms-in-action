package com.cim.AIA;

public class StepButton extends ControlButton
{
    private static final long serialVersionUID = -6736310483679896991L;
    private static boolean isDisabled;
    
    public static void allDisable(final boolean state) {
        StepButton.isDisabled = state;
    }
    
    public StepButton(final String name, final String explain, final Controlable controlable, final AlgorithmThread algorithmThread) {
        super(name, explain, controlable, algorithmThread);
        if (StepButton.isDisabled) {
            this.setEnabled(false);
        }
    }
    
    public void controlBack(final ControlEvent e) {
        if (!StepButton.isDisabled) {
            this.setEnabled(true);
        }
    }
    
    public void controlOther(final ControlEvent e) {
        if (!StepButton.isDisabled) {
            if (e.getType() == 123128) {
                this.setEnabled(true);
            }
            else if (e.getType() == 123129) {
                this.setEnabled(false);
            }
        }
    }
    
    public void controlPause(final ControlEvent e) {
        if (!StepButton.isDisabled) {
            this.setEnabled(true);
        }
    }
    
    public void controlReset(final ControlEvent e) {
        if (!StepButton.isDisabled) {
            this.setEnabled(true);
        }
    }
    
    public void controlRestart(final ControlEvent e) {
        if (!StepButton.isDisabled) {
            this.setEnabled(true);
        }
    }
    
    public void controlRun(final ControlEvent e) {
        if (!StepButton.isDisabled) {
            this.setEnabled(false);
        }
    }
    
    public void controlStep(final ControlEvent e) {
        if (!StepButton.isDisabled) {
            this.setEnabled(true);
        }
    }
    
    protected ControlEvent getControlEvent() {
        return new ControlEvent(this, 123123);
    }
    
    protected void processThread() {
        this.thread.stepMode();
        final String pos = this.thread.getHighlightPos();
        final int nlines = this.thread.calcHiddenLines(pos);
        final Log l1 = new StepLog(4, 19, pos, nlines);
        if (Logger.DEBUG) {
            System.err.println("pos: " + pos);
            System.err.println("nlines: " + nlines);
        }
        if (this.getLogger() != null) {
            this.getLogger().addLog(l1);
        }
    }
    
    static {
        StepButton.isDisabled = false;
    }
}
