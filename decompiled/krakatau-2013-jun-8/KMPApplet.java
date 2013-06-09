public class KMPApplet extends com.cim.AIA.AlgorithmApplet {
    final private static long serialVersionUID = 8471561699476367630L;
    final protected static String IMAGE_DIRECTORY = "images/";
    final protected static String ALGORITHM_FILE_NAME;
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    public static KMPCanvas theKMPCanvas;
    public static String FIRST_FILE_NAME;
    public static String SECOND_FILE_NAME;
    protected boolean debug;
    protected com.cim.AIA.StringArray initialData;
    
    public KMPApplet()
    {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        String s = KMPApplet.FIRST_FILE_NAME;
        this.algorithmFileName = s;
        String s0 = KMPApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = KMPApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
        int i = this.debug?1:0;
        if(i != 0)
        {
            String s2 = localization.Messages.getString("KMPApplet.1");
            java.awt.Button a = new java.awt.Button(s2);
            KMPApplet$1 a0 = new KMPApplet$1(this);
            a.addActionListener((java.awt.event.ActionListener)a0);
            java.awt.Component a1 = this.add((java.awt.Component)a);
        }
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        KMPCanvas a = new KMPCanvas();
        KMPApplet.theKMPCanvas = a;
        KMPCanvas a0 = KMPApplet.theKMPCanvas;
        return a0;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        KMPThread a2 = new KMPThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        KMPAnimationWindow a2 = new KMPAnimationWindow(a, a0, a1);
        return a2;
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        String[] a = KMPAnimationWindow.DEFAULT_DATA_1;
        com.cim.AIA.StringArray a0 = new com.cim.AIA.StringArray(a);
        this.initialData = a0;
        com.cim.AIA.StringArray a1 = this.initialData;
        return (com.cim.AIA.Copyable)a1;
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
        String s = localization.Messages.getString("KMPApplet.5");
        KMPApplet.ALGORITHM_FILE_NAME = s;
        String s0 = localization.Messages.getString("KMPApplet.0");
        KMPApplet.EXPLANATION_TITLE = s0;
        String s1 = localization.Messages.getString("KMPApplet.4");
        KMPApplet.EXPLANATION_FILE_NAME = s1;
        String s2 = localization.Messages.getString("KMPApplet.3");
        KMPApplet.FIRST_FILE_NAME = s2;
        String s3 = localization.Messages.getString("KMPApplet.2");
        KMPApplet.SECOND_FILE_NAME = s3;
    }
}