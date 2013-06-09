public class HeapSortApplet extends com.cim.AIA.AlgorithmApplet {
    final private static long serialVersionUID = -6131463170900705273L;
    final protected static String FILE_NAME;
    final protected static String IMAGE_DIRECTORY = "images/";
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    protected boolean debugingMode;
    
    public HeapSortApplet()
    {
        super();
        this.debugingMode = false;
        this.imageDirectory = "images/";
        String s = HeapSortApplet.FILE_NAME;
        this.algorithmFileName = s;
        String s0 = HeapSortApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = HeapSortApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
        int i = this.debugingMode?1:0;
        if(i != 0)
        {
            String s2 = localization.Messages.getString("HeapSortApplet.0");
            java.awt.Button a = new java.awt.Button(s2);
            HeapSortApplet$1 a0 = new HeapSortApplet$1(this);
            a.addActionListener((java.awt.event.ActionListener)a0);
            java.awt.Component a1 = this.add((java.awt.Component)a);
        }
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        HeapSortCanvas a = new HeapSortCanvas();
        return a;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        HeapSortThread a2 = new HeapSortThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        HeapSortAnimationWindow a2 = new HeapSortAnimationWindow(a, a0, (java.applet.Applet)this);
        return a2;
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        com.cim.AIA.RandomIntArrayDataSelection a = new com.cim.AIA.RandomIntArrayDataSelection("", false, (com.cim.AIA.AnimationWindow)null, 3, 20, 1, 99);
        Object a0 = a.getData();
        return (com.cim.AIA.Copyable)a0;
    }
    
    protected void setLocationAndSize()
    {
        com.cim.AIA.ExplainationWindow a = this.explainationWindow;
        a.setLocation(0, 0);
        com.cim.AIA.ExplainationWindow a0 = this.explainationWindow;
        a0.setSize(250, 735);
        com.cim.AIA.AlgorithmWindow a1 = this.window;
        a1.setLocation(250, 0);
        com.cim.AIA.AlgorithmWindow a2 = this.window;
        a2.setSize(385, 735);
        com.cim.AIA.HelpWindow a3 = this.helpWindow;
        a3.setLocation(635, 0);
        com.cim.AIA.HelpWindow a4 = this.helpWindow;
        a4.setSize(390, 150);
        com.cim.AIA.AnimationWindow a5 = this.animWindow;
        a5.setLocation(635, 150);
        com.cim.AIA.AnimationWindow a6 = this.animWindow;
        a6.setSize(390, 565);
    }
    
    static
    {
        String s = localization.Messages.getString("HeapSortApplet.3");
        HeapSortApplet.FILE_NAME = s;
        String s0 = localization.Messages.getString("HeapSortApplet.1");
        HeapSortApplet.EXPLANATION_TITLE = s0;
        String s1 = localization.Messages.getString("HeapSortApplet.2");
        HeapSortApplet.EXPLANATION_FILE_NAME = s1;
    }
}