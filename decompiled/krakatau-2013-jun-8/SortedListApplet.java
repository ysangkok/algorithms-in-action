public class SortedListApplet extends com.cim.AIA.AlgorithmApplet {
    final protected static String IMAGE_DIRECTORY = "images/";
    final protected static String ALGORITHM_FILE_NAME;
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    protected static com.cim.AIA.AlgorithmWindow lastWindow;
    
    public SortedListApplet()
    {
        super();
        this.imageDirectory = "images/";
        String s = SortedListApplet.ALGORITHM_FILE_NAME;
        this.algorithmFileName = s;
        String s0 = SortedListApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = SortedListApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
        com.cim.AIA.AlgorithmWindow a = this.window;
        SortedListApplet.lastWindow = a;
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        int[] a = SortedListAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.IntArray a0 = new com.cim.AIA.IntArray(a);
        return (com.cim.AIA.Copyable)a0;
    }
    
    protected void otherInitialisation()
    {
        com.cim.AIA.Algorithm a = this.algorithm;
        SortedList dummy = (SortedList)a;
        SortedList a0 = (SortedList)a;
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
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        com.cim.AIA.SplitAlgorithmWindow dummy = (com.cim.AIA.SplitAlgorithmWindow)a0;
        com.cim.AIA.SplitAlgorithmWindow a2 = (com.cim.AIA.SplitAlgorithmWindow)a0;
        SortedListThread a3 = new SortedListThread((com.cim.AIA.Copyable)a1, (com.cim.AIA.AlgorithmWindow)a2);
        return a3;
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        SortedListCanvas a = new SortedListCanvas();
        return a;
    }
    
    public com.cim.AIA.AlgorithmWindow getAlgorithmWindow()
    {
        com.cim.AIA.AlgorithmWindow a = SortedListApplet.lastWindow;
        return a;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        SortedListAnimationWindow a2 = new SortedListAnimationWindow(a, a0, a1);
        return a2;
    }
    
    protected com.cim.AIA.AlgorithmWindow getAlgorithmWindow(String s, com.cim.AIA.CodeCanvas a)
    {
        com.cim.AIA.SplitAlgorithmWindow a0 = new com.cim.AIA.SplitAlgorithmWindow(s, a);
        return a0;
    }
    
    static
    {
        String s = localization.Messages.getString("SortedListApplet.1");
        SortedListApplet.ALGORITHM_FILE_NAME = s;
        String s0 = localization.Messages.getString("SortedListApplet.2");
        SortedListApplet.EXPLANATION_TITLE = s0;
        String s1 = localization.Messages.getString("SortedListApplet.3");
        SortedListApplet.EXPLANATION_FILE_NAME = s1;
        SortedListApplet.lastWindow = null;
    }
}