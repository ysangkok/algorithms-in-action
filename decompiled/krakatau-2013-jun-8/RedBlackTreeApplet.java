public class RedBlackTreeApplet extends com.cim.AIA.AlgorithmApplet {
    final private static long serialVersionUID = -6694171910480087104L;
    final protected static String IMAGE_DIRECTORY = "images/";
    final protected static String ALGORITHM_FILE_NAME;
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    
    public RedBlackTreeApplet()
    {
        super();
        this.imageDirectory = "images/";
        String s = RedBlackTreeApplet.ALGORITHM_FILE_NAME;
        this.algorithmFileName = s;
        String s0 = RedBlackTreeApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = RedBlackTreeApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        RedBlackTreeCanvas a = new RedBlackTreeCanvas();
        return a;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        RedBlackTreeThread a2 = new RedBlackTreeThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    protected com.cim.AIA.AlgorithmWindow getAlgorithmWindow(String s, com.cim.AIA.CodeCanvas a)
    {
        com.cim.AIA.SplitAlgorithmWindow a0 = new com.cim.AIA.SplitAlgorithmWindow(s, a);
        return a0;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        RedBlackTreeAnimationWindow a2 = new RedBlackTreeAnimationWindow(a, a0, a1);
        return a2;
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        int[] a = RedBlackTreeAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.IntArray a0 = new com.cim.AIA.IntArray(a);
        return (com.cim.AIA.Copyable)a0;
    }
    
    protected void otherInitialisation()
    {
        com.cim.AIA.Algorithm a = this.algorithm;
        RedBlackTree dummy = (RedBlackTree)a;
        RedBlackTree a0 = (RedBlackTree)a;
        java.net.URL a1 = this.getCodeBase();
        String s = a1.toString();
        com.cim.AIA.AlgorithmWindow a2 = this.window;
        com.cim.AIA.SplitAlgorithmWindow dummy0 = (com.cim.AIA.SplitAlgorithmWindow)a2;
        com.cim.AIA.SplitAlgorithmWindow a3 = (com.cim.AIA.SplitAlgorithmWindow)a2;
        a0.initialiseMethods(s, (com.cim.AIA.MethodSelectable)a3);
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
        a6.setSize(440, 720);
    }
    
    static
    {
        String s = localization.Messages.getString("RedBlackTreeApplet.2");
        RedBlackTreeApplet.ALGORITHM_FILE_NAME = s;
        String s0 = localization.Messages.getString("RedBlackTreeApplet.0");
        RedBlackTreeApplet.EXPLANATION_TITLE = s0;
        String s1 = localization.Messages.getString("RedBlackTreeApplet.1");
        RedBlackTreeApplet.EXPLANATION_FILE_NAME = s1;
    }
}