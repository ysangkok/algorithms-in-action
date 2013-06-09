import com.cim.AIA.*;

class MSTKGraphMapsAnimationWindow$1 implements ControlListener {
    public void controlBack(final ControlEvent e) {
        MSTKGraphMapsAnimationWindow.this.setBackMode();
    }
    
    public void controlPause(final ControlEvent e) {
    }
    
    public void controlReset(final ControlEvent e) {
    }
    
    public void controlRestart(final ControlEvent e) {
    }
    
    public void controlRun(final ControlEvent e) {
    }
    
    public void controlStep(final ControlEvent e) {
    }
    
    public void controlOther(final ControlEvent e) {
        if (e.getType() == 2340) {
            MSTKGraphMapsAnimationWindow.this.zoom(true);
        }
        else if (e.getType() == 2341) {
            MSTKGraphMapsAnimationWindow.this.zoom(false);
        }
        else if (e.getType() == 2342) {
            MSTKGraphMapsAnimationWindow.this.restartAlgorithm();
        }
    }
}