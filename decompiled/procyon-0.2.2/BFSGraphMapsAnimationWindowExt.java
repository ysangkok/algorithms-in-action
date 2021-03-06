import java.awt.*;
import localization.*;
import java.applet.*;
import aia.graph.common.*;
import com.cim.AIA.*;

public class BFSGraphMapsAnimationWindowExt extends AnimationWindow
{
    private static final long serialVersionUID = -9039246186300035755L;
    protected static final String FRAME_TITLE;
    protected BFSGraphMapsThread graphMapsThread;
    protected boolean m_matrixShowing;
    protected boolean m_structureShowing;
    protected HideMatrixButton m_hideMatrixButton;
    protected HideStructureButton m_hideStructureButton;
    protected String HIDE_MATRIX_LABEL;
    protected String SHOW_MATRIX_LABEL;
    protected String HIDE_STRUCTURE_LABEL;
    protected String SHOW_STRUCTURE_LABEL;
    
    public String getAlgorithmName() {
        return "Depth First Search";
    }
    
    public BFSGraphMapsAnimationWindowExt(final AlgorithmCanvas canvas, final AlgorithmThread thread, final Applet applet) {
        super(canvas, thread, applet);
        this.graphMapsThread = null;
        this.m_matrixShowing = false;
        this.m_structureShowing = false;
        this.m_hideMatrixButton = null;
        this.m_hideStructureButton = null;
        this.HIDE_MATRIX_LABEL = Messages.getString("GraphMapsAnimationWindowExt.1");
        this.SHOW_MATRIX_LABEL = Messages.getString("GraphMapsAnimationWindowExt.2");
        this.HIDE_STRUCTURE_LABEL = Messages.getString("GraphMapsAnimationWindowExt.3");
        this.SHOW_STRUCTURE_LABEL = Messages.getString("GraphMapsAnimationWindowExt.4");
        this.frameTitle = BFSGraphMapsAnimationWindowExt.FRAME_TITLE;
        this.m_hideMatrixButton = new HideMatrixButton(this, thread);
        this.m_hideMatrixButton.setLabel(this.SHOW_MATRIX_LABEL);
        this.addControlButton(this.m_hideMatrixButton);
        this.m_hideStructureButton = new HideStructureButton(this, thread);
        this.m_hideStructureButton.setLabel(this.SHOW_STRUCTURE_LABEL);
        this.addControlButton(this.m_hideStructureButton);
        this.addControlListener(new ControlListener() {
            public void controlBack(final ControlEvent e) {
            }
            
            public void controlOther(final ControlEvent e) {
                if (e.getType() == 2350) {
                    BFSGraphMapsAnimationWindowExt.this.hideMatrix();
                }
                else if (e.getType() == 2351) {
                    BFSGraphMapsAnimationWindowExt.this.hideStructure();
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
        });
        this.graphMapsThread = (BFSGraphMapsThread)thread;
    }
    
    protected void hideMatrix() {
        this.m_matrixShowing = !this.m_matrixShowing;
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        graphMaps.setMatrixShowing(this.m_matrixShowing);
        if (this.m_matrixShowing) {
            this.m_hideMatrixButton.setLabel(this.HIDE_MATRIX_LABEL);
        }
        else {
            this.m_hideMatrixButton.setLabel(this.SHOW_MATRIX_LABEL);
        }
    }
    
    protected void hideStructure() {
        this.m_structureShowing = !this.m_structureShowing;
        final GraphMaps graphMaps = (GraphMaps)this.getAlgorithm();
        graphMaps.setStructureShowing(this.m_structureShowing);
        if (this.m_structureShowing) {
            this.m_hideStructureButton.setLabel(this.HIDE_STRUCTURE_LABEL);
        }
        else {
            this.m_hideStructureButton.setLabel(this.SHOW_STRUCTURE_LABEL);
        }
    }
    
    protected void initialiseMenuBar() {
        this.menuBar = new MenuBar();
        this.setMenuBar(this.menuBar);
        this.initialiseHelpMenuItem();
    }
    
    static {
        FRAME_TITLE = Messages.getString("GraphMapsAnimationWindowExt.0");
    }
}
