package com.cim.AIA;

public class RestartButton extends ControlButton
{
    private static final long serialVersionUID = 3426245993755386905L;
    protected boolean reInitialiseAlgorithm;
    protected boolean clearAlgorithmState;
    protected boolean storeAlgorithmState;
    protected boolean forceAlgorithmStore;
    
    public RestartButton(final String name, final String explain, final AnimationWindow animationWindow, final AlgorithmThread algorithmThread) {
        super(name, explain, animationWindow, algorithmThread);
        this.reInitialiseAlgorithm = false;
        this.clearAlgorithmState = false;
        this.storeAlgorithmState = true;
        this.forceAlgorithmStore = true;
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
        this.setEnabled(false);
    }
    
    public void controlRun(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    public void controlStep(final ControlEvent e) {
        this.setEnabled(true);
    }
    
    protected ControlEvent getControlEvent() {
        return new ControlEvent(this, 123128);
    }
    
    protected void processThread() {
        final Log l1 = new Log(4, 16, this.thread.getPosition());
        if (this.getLogger() != null) {
            this.getLogger().addLog(l1);
        }
        ((AnimationWindow)((AnimationWindow)this.controlable)).resetThread(this.reInitialiseAlgorithm, this.clearAlgorithmState, this.storeAlgorithmState, this.forceAlgorithmStore);
    }
    
    public void setClearAlgorithmState(final boolean clearAlgorithmState) {
        this.clearAlgorithmState = clearAlgorithmState;
    }
    
    public void setForceAlgorithmStore(final boolean forceAlgorithmStore) {
        this.forceAlgorithmStore = forceAlgorithmStore;
    }
    
    public void setReinitialiseAlgorithm(final boolean reInitialiseAlgorithm) {
        this.reInitialiseAlgorithm = reInitialiseAlgorithm;
    }
    
    public void setStoreAlgorithmState(final boolean storeAlgorithmState) {
        this.storeAlgorithmState = storeAlgorithmState;
    }
}
