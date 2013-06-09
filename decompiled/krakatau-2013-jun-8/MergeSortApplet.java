public class MergeSortApplet extends com.cim.AIA.AlgorithmApplet {
    final private static long serialVersionUID = 5914517531399113703L;
    final public static String RightPartitionFileName;
    final protected static String IMAGE_DIRECTORY = "images/";
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    final public static int ANIM_WIN_WIDTH = 600;
    final public static int ANIM_WIN_HEIGHT = 765;
    protected com.cim.AIA.IntArray initalData;
    protected boolean debugingMode;
    
    public MergeSortApplet()
    {
        super();
        int[] a = MergeSortAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.IntArray a0 = new com.cim.AIA.IntArray(a);
        this.initalData = a0;
        this.debugingMode = true;
        this.imageDirectory = "images/";
        String s = MergeSortApplet.RightPartitionFileName;
        this.algorithmFileName = s;
        String s0 = MergeSortApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = MergeSortApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
        int i = this.debugingMode?1:0;
        if(i != 0)
        {
            java.awt.Button a1 = new java.awt.Button("Display Positions");
            MergeSortApplet$1 a2 = new MergeSortApplet$1(this);
            a1.addActionListener((java.awt.event.ActionListener)a2);
            java.awt.Component a3 = this.add((java.awt.Component)a1);
        }
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        com.cim.AIA.IntArray a = this.initalData;
        return (com.cim.AIA.Copyable)a;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        MergeSortThread a2 = new MergeSortThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        MergeSortCanvas a = new MergeSortCanvas();
        return a;
    }
    
    public void setLocationAndSize()
    {
        com.cim.AIA.ExplainationWindow a = this.explainationWindow;
        a.setLocation(0, 0);
        com.cim.AIA.ExplainationWindow a0 = this.explainationWindow;
        a0.setSize(250, 735);
        com.cim.AIA.AlgorithmWindow a1 = this.window;
        a1.setLocation(250, 0);
        com.cim.AIA.AlgorithmWindow a2 = this.window;
        a2.setSize(420, 765);
        com.cim.AIA.HelpWindow a3 = this.helpWindow;
        a3.setLocation(250, 600);
        com.cim.AIA.HelpWindow a4 = this.helpWindow;
        a4.setSize(420, 135);
        com.cim.AIA.AnimationWindow a5 = this.animWindow;
        a5.setLocation(670, 0);
        com.cim.AIA.AnimationWindow a6 = this.animWindow;
        a6.setSize(600, 765);
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        MergeSortAnimationWindow a2 = new MergeSortAnimationWindow(a, a0, a1);
        return a2;
    }
    
    static
    {
        String s = localization.Messages.getString("MergeSortApplet.0");
        MergeSortApplet.RightPartitionFileName = s;
        String s0 = localization.Messages.getString("MergeSortApplet.2");
        MergeSortApplet.EXPLANATION_TITLE = s0;
        String s1 = localization.Messages.getString("MergeSortApplet.3");
        MergeSortApplet.EXPLANATION_FILE_NAME = s1;
    }
}