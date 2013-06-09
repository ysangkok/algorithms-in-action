public class DFSGraphMapsAnimationWindowExt extends com.cim.AIA.AnimationWindow {
    final protected static String FRAME_TITLE;
    protected DFSGraphMapsThread graphMapsThread;
    protected boolean m_matrixShowing;
    protected boolean m_structureShowing;
    protected aia.graph.common.HideMatrixButton m_hideMatrixButton;
    protected aia.graph.common.HideStructureButton m_hideStructureButton;
    protected String SHOW_MATRIX_LABEL;
    protected String HIDE_MATRIX_LABEL;
    protected String SHOW_STRUCTURE_LABEL;
    protected String HIDE_STRUCTURE_LABEL;
    
    public String getAlgorithmName()
    {
        return "Depth First Search";
    }
    
    public DFSGraphMapsAnimationWindowExt(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        super(a, a0, a1);
        this.graphMapsThread = null;
        this.m_matrixShowing = false;
        this.m_structureShowing = false;
        this.m_hideMatrixButton = null;
        this.m_hideStructureButton = null;
        String s = localization.Messages.getString("DFSGraphMapsAnimationWindowExt.1");
        this.SHOW_MATRIX_LABEL = s;
        String s0 = localization.Messages.getString("DFSGraphMapsAnimationWindowExt.2");
        this.HIDE_MATRIX_LABEL = s0;
        String s1 = localization.Messages.getString("DFSGraphMapsAnimationWindowExt.3");
        this.SHOW_STRUCTURE_LABEL = s1;
        String s2 = localization.Messages.getString("DFSGraphMapsAnimationWindowExt.4");
        this.HIDE_STRUCTURE_LABEL = s2;
        String s3 = DFSGraphMapsAnimationWindowExt.FRAME_TITLE;
        this.frameTitle = s3;
        aia.graph.common.HideMatrixButton a2 = new aia.graph.common.HideMatrixButton((com.cim.AIA.Controlable)this, a0);
        this.m_hideMatrixButton = a2;
        aia.graph.common.HideMatrixButton a3 = this.m_hideMatrixButton;
        String s4 = this.SHOW_MATRIX_LABEL;
        a3.setLabel(s4);
        aia.graph.common.HideMatrixButton a4 = this.m_hideMatrixButton;
        this.addControlButton((com.cim.AIA.ControlButton)a4);
        aia.graph.common.HideStructureButton a5 = new aia.graph.common.HideStructureButton((com.cim.AIA.Controlable)this, a0);
        this.m_hideStructureButton = a5;
        aia.graph.common.HideStructureButton a6 = this.m_hideStructureButton;
        String s5 = this.SHOW_STRUCTURE_LABEL;
        a6.setLabel(s5);
        aia.graph.common.HideStructureButton a7 = this.m_hideStructureButton;
        this.addControlButton((com.cim.AIA.ControlButton)a7);
        DFSGraphMapsAnimationWindowExt$1 a8 = new DFSGraphMapsAnimationWindowExt$1(this);
        this.addControlListener((com.cim.AIA.ControlListener)a8);
        com.cim.AIA.Algorithm a9 = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a9;
        DFSGraphMapsThread dummy0 = (DFSGraphMapsThread)a0;
        DFSGraphMapsThread a10 = (DFSGraphMapsThread)a0;
        this.graphMapsThread = a10;
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
            String s = this.SHOW_MATRIX_LABEL;
            a1.setLabel(s);
        }
        else
        {
            aia.graph.common.HideMatrixButton a2 = this.m_hideMatrixButton;
            String s0 = this.HIDE_MATRIX_LABEL;
            a2.setLabel(s0);
        }
    }
    
    protected void hideStructure()
    {
        int i = this.m_structureShowing?1:0;
        int i0 = i != 0?0:1;
        this.m_structureShowing = i0 != 0;
        com.cim.AIA.Algorithm a = this.getAlgorithm();
        aia.graph.common.GraphMaps dummy = (aia.graph.common.GraphMaps)a;
        aia.graph.common.GraphMaps a0 = (aia.graph.common.GraphMaps)a;
        int i1 = this.m_structureShowing?1:0;
        a0.setStructureShowing(i1 != 0);
        int i2 = this.m_structureShowing?1:0;
        if(i2 == 0)
        {
            aia.graph.common.HideStructureButton a1 = this.m_hideStructureButton;
            String s = this.SHOW_STRUCTURE_LABEL;
            a1.setLabel(s);
        }
        else
        {
            aia.graph.common.HideStructureButton a2 = this.m_hideStructureButton;
            String s0 = this.HIDE_STRUCTURE_LABEL;
            a2.setLabel(s0);
        }
    }
    
    static
    {
        String s = localization.Messages.getString("DFSGraphMapsAnimationWindowExt.0");
        DFSGraphMapsAnimationWindowExt.FRAME_TITLE = s;
    }
}