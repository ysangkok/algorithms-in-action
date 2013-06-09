public class DFSGraphMapsApplet extends com.cim.AIA.AlgorithmApplet {
    final protected static String IMAGE_DIRECTORY = "images/";
    final protected static String ALGORITHM_FILE_NAME;
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    protected boolean debug;
    
    public DFSGraphMapsApplet()
    {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        String s = DFSGraphMapsApplet.ALGORITHM_FILE_NAME;
        this.algorithmFileName = s;
        String s0 = DFSGraphMapsApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = DFSGraphMapsApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
        int i = this.debug?1:0;
        if(i != 0)
        {
            String s2 = localization.Messages.getString("DFSGraphMapsApplet.4");
            java.awt.Button a = new java.awt.Button(s2);
            DFSGraphMapsApplet$1 a0 = new DFSGraphMapsApplet$1(this);
            a.addActionListener((java.awt.event.ActionListener)a0);
            java.awt.Component a1 = this.add((java.awt.Component)a);
        }
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        int[] a = DFSGraphMapsAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.IntArray a0 = new com.cim.AIA.IntArray(a);
        return (com.cim.AIA.Copyable)a0;
    }
    
    public void setLocationAndSize()
    {
        com.cim.AIA.ExplainationWindow a = this.explainationWindow;
        a.setLocation(0, 0);
        com.cim.AIA.ExplainationWindow a0 = this.explainationWindow;
        a0.setSize(235, 735);
        com.cim.AIA.AlgorithmWindow a1 = this.window;
        a1.setLocation(235, 135);
        com.cim.AIA.AlgorithmWindow a2 = this.window;
        a2.setSize(350, 600);
        com.cim.AIA.HelpWindow a3 = this.helpWindow;
        a3.setLocation(235, 0);
        com.cim.AIA.HelpWindow a4 = this.helpWindow;
        a4.setSize(350, 135);
        com.cim.AIA.AnimationWindow a5 = this.animWindow;
        a5.setLocation(585, 0);
        com.cim.AIA.AnimationWindow a6 = this.animWindow;
        a6.setSize(440, 367);
    }
    
    protected void otherInitialisation()
    {
        com.cim.AIA.Algorithm a = this.algorithm;
        DFSGraphMaps dummy = (DFSGraphMaps)a;
        DFSGraphMaps a0 = (DFSGraphMaps)a;
        java.net.URL a1 = this.getCodeBase();
        String s = a1.toString();
        com.cim.AIA.AlgorithmWindow a2 = this.window;
        com.cim.AIA.SplitAlgorithmWindow dummy0 = (com.cim.AIA.SplitAlgorithmWindow)a2;
        com.cim.AIA.SplitAlgorithmWindow a3 = (com.cim.AIA.SplitAlgorithmWindow)a2;
        a0.initialiseMethods(s, (com.cim.AIA.MethodSelectable)a3);
    }
    
    public com.cim.AIA.AlgorithmWindow getAlgorithmWindow(String s, com.cim.AIA.CodeCanvas a)
    {
        com.cim.AIA.SplitAlgorithmWindow a0 = new com.cim.AIA.SplitAlgorithmWindow(s, a);
        return a0;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        DFSGraphMapsThread a2 = new DFSGraphMapsThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        DFSGraphMapsCanvas a = new DFSGraphMapsCanvas();
        return a;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        DFSGraphMapsAnimationWindow a2 = new DFSGraphMapsAnimationWindow(a, a0, a1);
        return a2;
    }
    
    static
    {
        String s = localization.Messages.getString("DFSGraphMapsApplet.1");
        DFSGraphMapsApplet.ALGORITHM_FILE_NAME = s;
        String s0 = localization.Messages.getString("DFSGraphMapsApplet.2");
        DFSGraphMapsApplet.EXPLANATION_TITLE = s0;
        String s1 = localization.Messages.getString("DFSGraphMapsApplet.3");
        DFSGraphMapsApplet.EXPLANATION_FILE_NAME = s1;
    }
}