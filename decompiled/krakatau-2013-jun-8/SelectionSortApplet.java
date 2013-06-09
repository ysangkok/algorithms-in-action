public class SelectionSortApplet extends com.cim.AIA.AlgorithmApplet {
    final private static long serialVersionUID = -6057409737717709991L;
    final public static String IMAGE_DIRECTORY = "images/";
    final protected static String ALGORITHM_FILE_NAME;
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    
    public SelectionSortApplet()
    {
        super();
        this.imageDirectory = "images/";
        String s = SelectionSortApplet.ALGORITHM_FILE_NAME;
        this.algorithmFileName = s;
        String s0 = SelectionSortApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = SelectionSortApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        SelectionSortCanvas a = new SelectionSortCanvas();
        return a;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        SelectionSortThread a2 = new SelectionSortThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        SelectionSortAnimationWindow a2 = new SelectionSortAnimationWindow(a, a0, a1);
        return a2;
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        int[] a = SelectionSortAnimationWindow.DEFAULT_DATA;
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
        String s = localization.Messages.getString("SelectionSortApplet.1");
        SelectionSortApplet.ALGORITHM_FILE_NAME = s;
        String s0 = localization.Messages.getString("SelectionSortApplet.0");
        SelectionSortApplet.EXPLANATION_TITLE = s0;
        String s1 = localization.Messages.getString("SelectionSortApplet.2");
        SelectionSortApplet.EXPLANATION_FILE_NAME = s1;
    }
}