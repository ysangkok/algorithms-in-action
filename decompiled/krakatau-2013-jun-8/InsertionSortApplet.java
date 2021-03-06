public class InsertionSortApplet extends com.cim.AIA.AlgorithmApplet {
    final private static long serialVersionUID = 8666293375487043708L;
    final public static String IMAGE_DIRECTORY = "images/";
    final protected static String ALGORITHM_FILE_NAME;
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    
    public InsertionSortApplet()
    {
        super();
        this.imageDirectory = "images/";
        String s = InsertionSortApplet.ALGORITHM_FILE_NAME;
        this.algorithmFileName = s;
        String s0 = InsertionSortApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = InsertionSortApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        InsertionSortCanvas a = new InsertionSortCanvas();
        return a;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        InsertionSortThread a2 = new InsertionSortThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        InsertionSortAnimationWindow a2 = new InsertionSortAnimationWindow(a, a0, a1);
        return a2;
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        int[] a = InsertionSortAnimationWindow.DEFAULT_DATA;
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
        a6.setSize(440, 720);
    }
    
    static
    {
        String s = localization.Messages.getString("InsertionSortApplet.0");
        InsertionSortApplet.ALGORITHM_FILE_NAME = s;
        String s0 = localization.Messages.getString("InsertionSortApplet.2");
        InsertionSortApplet.EXPLANATION_TITLE = s0;
        String s1 = localization.Messages.getString("InsertionSortApplet.1");
        InsertionSortApplet.EXPLANATION_FILE_NAME = s1;
    }
}