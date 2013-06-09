import com.cim.AIA.*;

class BFSGraphMapsAnimationWindow$1 implements ControlListener {
    public void controlBack(final ControlEvent e) {
        BFSGraphMapsAnimationWindow.this.setBackMode();
    }
    
    public void controlOther(final ControlEvent e) {
        if (e.getType() == 2340) {
            BFSGraphMapsAnimationWindow.this.zoom(true);
        }
        else if (e.getType() == 2341) {
            BFSGraphMapsAnimationWindow.this.zoom(false);
        }
        else if (e.getType() == 2342) {
            BFSGraphMapsAnimationWindow.this.restartAlgorithm();
        }
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
}