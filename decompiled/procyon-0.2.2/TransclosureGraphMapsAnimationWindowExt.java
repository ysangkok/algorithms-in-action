import localization.*;
import java.awt.*;
import java.applet.*;
import aia.graph.common.*;
import com.cim.AIA.*;

public class TransclosureGraphMapsAnimationWindowExt extends AnimationWindow
{
    protected static final String FRAME_TITLE;
    protected TransclosureGraphMapsThread graphMapsThread;
    protected boolean m_matrixShowing;
    protected HideMatrixButton m_hideMatrixButton;
    
    public String getAlgorithmName() {
        return "Transitive Closure";
    }
    
    public TransclosureGraphMapsAnimationWindowExt(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.graphMapsThread = null;
        this.m_matrixShowing = true;
        this.m_hideMatrixButton = null;
        this.frameTitle = TransclosureGraphMapsAnimationWindowExt.FRAME_TITLE;
        this.m_hideMatrixButton = new HideMatrixButton(this, thread);
        this.addControlButton(this.m_hideMatrixButton);
        this.addControlListener(new ControlListener() {
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
                    TransclosureGraphMapsAnimationWindowExt.this.hideMatrix();
                }
            }
        });
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        this.graphMapsThread = (TransclosureGraphMapsThread)thread;
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseHelpMenuItem();
    }
    
    protected void hideMatrix() {
        this.m_matrixShowing = !this.m_matrixShowing;
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        graphMaps.setMatrixShowing(this.m_matrixShowing);
        if (this.m_matrixShowing) {
            this.m_hideMatrixButton.setLabel(Messages.getString("TransclosureGraphMapsAnimationWindowExt.1"));
        }
        else {
            this.m_hideMatrixButton.setLabel(Messages.getString("TransclosureGraphMapsAnimationWindowExt.2"));
        }
    }
    
    static {
        FRAME_TITLE = Messages.getString("TransclosureGraphMapsAnimationWindowExt.0");
    }
}
