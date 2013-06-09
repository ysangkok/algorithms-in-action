public class TransclosureGraphMapsAnimationWindowExt extends com.cim.AIA.AnimationWindow {
    final protected static String FRAME_TITLE;
    protected TransclosureGraphMapsThread graphMapsThread;
    protected boolean m_matrixShowing;
    protected aia.graph.common.HideMatrixButton m_hideMatrixButton;
    
    public String getAlgorithmName()
    {
        return "Transitive Closure";
    }
    
    public TransclosureGraphMapsAnimationWindowExt(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.graphMapsThread = null;
        this.m_matrixShowing = true;
        this.m_hideMatrixButton = null;
        String s = TransclosureGraphMapsAnimationWindowExt.FRAME_TITLE;
        this.frameTitle = s;
        aia.graph.common.HideMatrixButton a2 = new aia.graph.common.HideMatrixButton((com.cim.AIA.Controlable)this, a0);
        this.m_hideMatrixButton = a2;
        aia.graph.common.HideMatrixButton a3 = this.m_hideMatrixButton;
        this.addControlButton((com.cim.AIA.ControlButton)a3);
        TransclosureGraphMapsAnimationWindowExt$1 a4 = new TransclosureGraphMapsAnimationWindowExt$1(this);
        this.addControlListener((com.cim.AIA.ControlListener)a4);
        com.cim.AIA.Algorithm a5 = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a5;
        TransclosureGraphMapsThread dummy0 = (TransclosureGraphMapsThread)a0;
        TransclosureGraphMapsThread a6 = (TransclosureGraphMapsThread)a0;
        this.graphMapsThread = a6;
    }
    
    protected void initialiseMenuBar()
    {
        java.awt.MenuBar a = new java.awt.MenuBar();
        this.menuBar = a;
        java.awt.MenuBar a0 = this.menuBar;
        this.setMenuBar(a0);
        this.initialiseHelpMenuItem();
    }
    
    protected void hideMatrix()
    {
        int i = this.m_matrixShowing?1:0;
        int i0 = i != 0?0:1;
        this.m_matrixShowing = i0 != 0;
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        int i1 = this.m_matrixShowing?1:0;
        a0.setMatrixShowing(i1 != 0);
        int i2 = this.m_matrixShowing?1:0;
        if(i2 == 0)
        {
            aia.graph.common.HideMatrixButton a1 = this.m_hideMatrixButton;
            String s = localization.Messages.getString("TransclosureGraphMapsAnimationWindowExt.2");
            a1.setLabel(s);
        }
        else
        {
            aia.graph.common.HideMatrixButton a2 = this.m_hideMatrixButton;
            String s0 = localization.Messages.getString("TransclosureGraphMapsAnimationWindowExt.1");
            a2.setLabel(s0);
        }
    }
    
    static
    {
        String s = localization.Messages.getString("TransclosureGraphMapsAnimationWindowExt.0");
        TransclosureGraphMapsAnimationWindowExt.FRAME_TITLE = s;
    }
}