import com.cim.AIA.*;

class MinSpanTreePrimGraphMapsAnimationWindowExt$1 implements ControlListener {
    public void controlBack(final ControlEvent e) {
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
        if (e.getType() == 2350) {
            MinSpanTreePrimGraphMapsAnimationWindowExt.this.hideMatrix();
        }
        else if (e.getType() == 2351) {
            MinSpanTreePrimGraphMapsAnimationWindowExt.this.hideStructure();
        }
    }
}