public class PatriciaTreeApplet extends com.cim.AIA.AlgorithmApplet {
    final private static long serialVersionUID = -3797081612876957752L;
    final protected static String IMAGE_DIRECTORY = "images/";
    final protected static String ALGORITHM_FILE_NAME;
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    
    public PatriciaTreeApplet()
    {
        super();
        this.imageDirectory = "images/";
        String s = PatriciaTreeApplet.ALGORITHM_FILE_NAME;
        this.algorithmFileName = s;
        String s0 = PatriciaTreeApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = PatriciaTreeApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        PatriciaTreeCanvas a = new PatriciaTreeCanvas();
        return a;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        PatriciaTreeThread a2 = new PatriciaTreeThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    protected com.cim.AIA.AlgorithmWindow getAlgorithmWindow(String s, com.cim.AIA.CodeCanvas a)
    {
        com.cim.AIA.MultiAlgorithmWindow a0 = new com.cim.AIA.MultiAlgorithmWindow((com.cim.AIA.AlgorithmApplet)this, s, a);
        return a0;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        PatriciaTreeAnimationWindow a2 = new PatriciaTreeAnimationWindow(a, a0, a1);
        return a2;
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        int[] a = PatriciaTreeAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.IntArray a0 = new com.cim.AIA.IntArray(a);
        return (com.cim.AIA.Copyable)a0;
    }
    
    protected void otherInitialisation()
    {
        com.cim.AIA.Algorithm a = this.algorithm;
        PatriciaTree dummy = (PatriciaTree)a;
        PatriciaTree a0 = (PatriciaTree)a;
        java.net.URL a1 = this.getCodeBase();
        String s = a1.toString();
        com.cim.AIA.AlgorithmWindow a2 = this.window;
        com.cim.AIA.MultiAlgorithmWindow dummy0 = (com.cim.AIA.MultiAlgorithmWindow)a2;
        com.cim.AIA.MultiAlgorithmWindow a3 = (com.cim.AIA.MultiAlgorithmWindow)a2;
        a0.initialiseMethods(s, (com.cim.AIA.MethodSelectable)a3);
        com.cim.AIA.Algorithm a4 = this.algorithm;
        PatriciaTree dummy1 = (PatriciaTree)a4;
        PatriciaTree a5 = (PatriciaTree)a4;
        java.net.URL a6 = this.getCodeBase();
        String s0 = a6.toString();
        com.cim.AIA.AlgorithmWindow a7 = this.window;
        com.cim.AIA.MultiAlgorithmWindow dummy2 = (com.cim.AIA.MultiAlgorithmWindow)a7;
        com.cim.AIA.MultiAlgorithmWindow a8 = (com.cim.AIA.MultiAlgorithmWindow)a7;
        a5.initialiseCanvases(s0, a8);
    }
    
    public void setLocationAndSize()
    {
        com.cim.AIA.ExplainationWindow a = this.explainationWindow;
        a.setLocation(0, 0);
        com.cim.AIA.ExplainationWindow a0 = this.explainationWindow;
        a0.setSize(235, 735);
        com.cim.AIA.AlgorithmWindow a1 = this.window;
        a1.setLocation(235, 0);
        com.cim.AIA.AlgorithmWindow a2 = this.window;
        a2.setSize(350, 735);
        com.cim.AIA.HelpWindow a3 = this.helpWindow;
        a3.setLocation(235, 0);
        com.cim.AIA.HelpWindow a4 = this.helpWindow;
        a4.setSize(350, 135);
        com.cim.AIA.AnimationWindow a5 = this.animWindow;
        a5.setLocation(585, 0);
        com.cim.AIA.AnimationWindow a6 = this.animWindow;
        a6.setSize(440, 720);
    }
    
    static
    {
        String s = localization.Messages.getString("PatriciaTreeApplet.0");
        PatriciaTreeApplet.ALGORITHM_FILE_NAME = s;
        String s0 = localization.Messages.getString("PatriciaTreeApplet.2");
        PatriciaTreeApplet.EXPLANATION_TITLE = s0;
        String s1 = localization.Messages.getString("PatriciaTreeApplet.3");
        PatriciaTreeApplet.EXPLANATION_FILE_NAME = s1;
    }
}