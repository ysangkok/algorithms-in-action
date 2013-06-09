import com.cim.AIA.*;

class MinSpanTreePrimGraphMapsAnimationWindow$1 implements ControlListener {
    public void controlBack(final ControlEvent e) {
        MinSpanTreePrimGraphMapsAnimationWindow.this.setBackMode();
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
            MinSpanTreePrimGraphMapsAnimationWindow.this.zoom(true);
        }
        else if (e.getType() == 2341) {
            MinSpanTreePrimGraphMapsAnimationWindow.this.zoom(false);
        }
        else if (e.getType() == 2342) {
            MinSpanTreePrimGraphMapsAnimationWindow.this.restartAlgorithm();
        }
    }
}