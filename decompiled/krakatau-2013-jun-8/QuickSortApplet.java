public class QuickSortApplet extends com.cim.AIA.AlgorithmApplet {
    final private static long serialVersionUID = -4679757344986568093L;
    final public static String RightPartitionFileName;
    final public static String RandomPartitionFileName;
    final public static String MiddleOf3RandomPartitionFileName;
    final public static String MiddleOf3PartitionFileName;
    final protected static String IMAGE_DIRECTORY = "images/";
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    protected com.cim.AIA.IntArray initalData;
    protected boolean debugingMode;
    
    public QuickSortApplet()
    {
        super();
        int[] a = QuickSortAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.IntArray a0 = new com.cim.AIA.IntArray(a);
        this.initalData = a0;
        this.debugingMode = false;
        this.imageDirectory = "images/";
        String s = QuickSortApplet.RightPartitionFileName;
        this.algorithmFileName = s;
        String s0 = QuickSortApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = QuickSortApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
        int i = this.debugingMode?1:0;
        if(i != 0)
        {
            String s2 = localization.Messages.getString("QuickSortApplet.1");
            java.awt.Button a1 = new java.awt.Button(s2);
            QuickSortApplet$1 a2 = new QuickSortApplet$1(this);
            a1.addActionListener((java.awt.event.ActionListener)a2);
            java.awt.Component a3 = this.add((java.awt.Component)a1);
        }
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        QuickSortCanvas a = new QuickSortCanvas();
        return a;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        QuickSortThread a2 = new QuickSortThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        QuickSortAnimationWindow a2 = new QuickSortAnimationWindow(a, a0, a1);
        return a2;
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        com.cim.AIA.IntArray a = this.initalData;
        return (com.cim.AIA.Copyable)a;
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
        a2.setSize(420, 600);
        com.cim.AIA.HelpWindow a3 = this.helpWindow;
        a3.setLocation(250, 600);
        com.cim.AIA.HelpWindow a4 = this.helpWindow;
        a4.setSize(420, 135);
        com.cim.AIA.AnimationWindow a5 = this.animWindow;
        a5.setLocation(670, 0);
        com.cim.AIA.AnimationWindow a6 = this.animWindow;
        a6.setSize(355, 720);
    }
    
    static
    {
        String s = localization.Messages.getString("QuickSortApplet.6");
        QuickSortApplet.RightPartitionFileName = s;
        String s0 = localization.Messages.getString("QuickSortApplet.5");
        QuickSortApplet.RandomPartitionFileName = s0;
        String s1 = localization.Messages.getString("QuickSortApplet.4");
        QuickSortApplet.MiddleOf3RandomPartitionFileName = s1;
        String s2 = localization.Messages.getString("QuickSortApplet.3");
        QuickSortApplet.MiddleOf3PartitionFileName = s2;
        String s3 = localization.Messages.getString("QuickSortApplet.0");
        QuickSortApplet.EXPLANATION_TITLE = s3;
        String s4 = localization.Messages.getString("QuickSortApplet.2");
        QuickSortApplet.EXPLANATION_FILE_NAME = s4;
    }
}