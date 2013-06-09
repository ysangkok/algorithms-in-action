import com.cim.AIA.*;

public class NewBackButton extends BackButton
{
    private static final long serialVersionUID = -8175237128466520408L;
    protected boolean switchOffed;
    
    public NewBackButton(final String name, final String explain, final AnimationWindow animationWindow, final AlgorithmThread algorithmThread) {
        super(name, explain, animationWindow, algorithmThread);
        this.switchOffed = false;
    }
    
    public void controlBack(final ControlEvent e) {
        if (this.switchOffed) {
            return;
        }
        super.controlBack(e);
    }
    
    public void controlOther(final ControlEvent e) {
        if (this.switchOffed) {
            return;
        }
        super.controlOther(e);
    }
    
    public void controlPause(final ControlEvent e) {
        if (this.switchOffed) {
            return;
        }
        super.controlPause(e);
    }
    
    public void controlReset(final ControlEvent e) {
        if (this.switchOffed) {
            return;
        }
        super.controlReset(e);
    }
    
    public void controlRestart(final ControlEvent e) {
        if (this.switchOffed) {
            return;
        }
        super.controlRestart(e);
    }
    
    public void controlRun(final ControlEvent e) {
        if (this.switchOffed) {
            return;
        }
        super.controlRun(e);
    }
    
    public void controlStep(final ControlEvent e) {
        if (this.switchOffed) {
            return;
        }
        super.controlStep(e);
    }
    
    protected ControlEvent getControlEvent() {
        return super.getControlEvent();
    }
    
    public boolean getSwitch() {
        return this.switchOffed;
    }
    
    protected void processThread() {
        if (this.switchOffed) {
            return;
        }
        super.processThread();
    }
    
    public void switchOff(final boolean state) {
        this.switchOffed = state;
    }
}
