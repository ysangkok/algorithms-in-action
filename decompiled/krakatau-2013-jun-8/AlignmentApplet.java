public class AlignmentApplet extends com.cim.AIA.AlgorithmApplet {
    final private static long serialVersionUID = 5436130069584190965L;
    final protected static String IMAGE_DIRECTORY = "images/";
    final public static String GLOBAL_DIST_NOGAP_NAME;
    final public static String GLOBAL_DIST_GAP_NAME;
    final public static String GLOBAL_SIM_NOGAP_NAME;
    final public static String GLOBAL_SIM_GAP_NAME;
    final public static String LOCAL_SIM_NOGAP_NAME;
    final public static String LOCAL_SIM_GAP_NAME;
    public static String FIRST_FILE_NAME;
    public static String SECOND_FILE_NAME;
    public static AlignmentCanvas theAlignmentCanvas;
    final protected static String EXPLANATION_TITLE = " Introduction to Alignment";
    final protected static String EXPLANATION_DIST_NOGAP;
    final protected static String EXPLANATION_DIST_GAP;
    final protected static String EXPLANATION_SIM_NOGAP;
    final protected static String EXPLANATION_SIM_GAP;
    final protected static String EXPLANATION_SIMLT_NOGAP;
    public static com.cim.AIA.ExplainationWindow expWin;
    public static String FIRST_EXPLANATION_NAME;
    public static String SECOND_EXPLANATION_NAME;
    public static String codeBaseString;
    final protected static String EXPLANATION_FILE_NAME;
    protected boolean debug;
    protected com.cim.AIA.StringArray initialData;
    
    public AlignmentApplet()
    {
        super();
        this.debug = false;
        this.imageDirectory = "images/";
        this.explanationTitle = " Introduction to Alignment";
        String s = AlignmentApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s;
        int i = this.debug?1:0;
        if(i != 0)
        {
            java.awt.Button a = new java.awt.Button("Display Positions");
            AlignmentApplet$1 a0 = new AlignmentApplet$1(this);
            a.addActionListener((java.awt.event.ActionListener)a0);
            java.awt.Component a1 = this.add((java.awt.Component)a);
        }
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        AlignmentCanvas a = new AlignmentCanvas();
        AlignmentApplet.theAlignmentCanvas = a;
        AlignmentCanvas a0 = AlignmentApplet.theAlignmentCanvas;
        return a0;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        AlignmentThread a2 = new AlignmentThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        AlignmentAnimationWindow a2 = new AlignmentAnimationWindow(a, a0, a1);
        return a2;
    }
    
    public com.cim.AIA.Copyable getInitialData()
    {
        com.cim.AIA.StringArray a = this.initialData;
        return (com.cim.AIA.Copyable)a;
    }
    
    protected void otherInitialisation()
    {
        com.cim.AIA.ExplainationWindow a = this.explainationWindow;
        AlignmentApplet.expWin = a;
        java.net.URL a0 = this.getCodeBase();
        String s = a0.toString();
        AlignmentApplet.codeBaseString = s;
    }
    
    public void parseParameter(String s)
    {
        Alignment.setRunningMode(s);
        int i = Alignment.runningMode;
        if(i == 1)
        {
            String s0 = AlignmentApplet.GLOBAL_DIST_NOGAP_NAME;
            AlignmentApplet.FIRST_FILE_NAME = s0;
            String s1 = AlignmentApplet.GLOBAL_DIST_GAP_NAME;
            AlignmentApplet.SECOND_FILE_NAME = s1;
            String s2 = AlignmentApplet.EXPLANATION_DIST_NOGAP;
            AlignmentApplet.FIRST_EXPLANATION_NAME = s2;
            String s3 = AlignmentApplet.EXPLANATION_DIST_GAP;
            AlignmentApplet.SECOND_EXPLANATION_NAME = s3;
            String[] a = AlignmentAnimationWindow.GLOBAL_DIST_NOGAP_DATA;
            com.cim.AIA.StringArray a0 = new com.cim.AIA.StringArray(a);
            this.initialData = a0;
        }
        int i0 = Alignment.runningMode;
        if(i0 == 2)
        {
            String s4 = AlignmentApplet.GLOBAL_SIM_NOGAP_NAME;
            AlignmentApplet.FIRST_FILE_NAME = s4;
            String s5 = AlignmentApplet.GLOBAL_SIM_GAP_NAME;
            AlignmentApplet.SECOND_FILE_NAME = s5;
            String s6 = AlignmentApplet.EXPLANATION_SIM_NOGAP;
            AlignmentApplet.FIRST_EXPLANATION_NAME = s6;
            String s7 = AlignmentApplet.EXPLANATION_SIM_GAP;
            AlignmentApplet.SECOND_EXPLANATION_NAME = s7;
            String[] a1 = AlignmentAnimationWindow.GLOBAL_SIM_NOGAP_DATA;
            com.cim.AIA.StringArray a2 = new com.cim.AIA.StringArray(a1);
            this.initialData = a2;
        }
        int i1 = Alignment.runningMode;
        if(i1 == 3)
        {
            String s8 = AlignmentApplet.LOCAL_SIM_NOGAP_NAME;
            AlignmentApplet.FIRST_FILE_NAME = s8;
            String s9 = AlignmentApplet.LOCAL_SIM_GAP_NAME;
            AlignmentApplet.SECOND_FILE_NAME = s9;
            String s10 = AlignmentApplet.EXPLANATION_SIMLT_NOGAP;
            AlignmentApplet.FIRST_EXPLANATION_NAME = s10;
            String[] a3 = AlignmentAnimationWindow.LOCAL_SIM_NOGAP_DATA;
            com.cim.AIA.StringArray a4 = new com.cim.AIA.StringArray(a3);
            this.initialData = a4;
        }
        String s11 = AlignmentApplet.FIRST_FILE_NAME;
        this.algorithmFileName = s11;
        String s12 = AlignmentApplet.FIRST_EXPLANATION_NAME;
        this.explanationFileName = s12;
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
        String s = localization.Messages.getString("AlignmentApplet.1");
        AlignmentApplet.GLOBAL_DIST_NOGAP_NAME = s;
        String s0 = localization.Messages.getString("AlignmentApplet.2");
        AlignmentApplet.GLOBAL_DIST_GAP_NAME = s0;
        String s1 = localization.Messages.getString("AlignmentApplet.3");
        AlignmentApplet.GLOBAL_SIM_NOGAP_NAME = s1;
        String s2 = localization.Messages.getString("AlignmentApplet.4");
        AlignmentApplet.GLOBAL_SIM_GAP_NAME = s2;
        String s3 = localization.Messages.getString("AlignmentApplet.5");
        AlignmentApplet.LOCAL_SIM_NOGAP_NAME = s3;
        String s4 = localization.Messages.getString("AlignmentApplet.6");
        AlignmentApplet.LOCAL_SIM_GAP_NAME = s4;
        AlignmentApplet.FIRST_FILE_NAME = "";
        AlignmentApplet.SECOND_FILE_NAME = "";
        String s5 = localization.Messages.getString("AlignmentApplet.10");
        AlignmentApplet.EXPLANATION_DIST_NOGAP = s5;
        String s6 = localization.Messages.getString("AlignmentApplet.11");
        AlignmentApplet.EXPLANATION_DIST_GAP = s6;
        String s7 = localization.Messages.getString("AlignmentApplet.12");
        AlignmentApplet.EXPLANATION_SIM_NOGAP = s7;
        String s8 = localization.Messages.getString("AlignmentApplet.13");
        AlignmentApplet.EXPLANATION_SIM_GAP = s8;
        String s9 = localization.Messages.getString("AlignmentApplet.14");
        AlignmentApplet.EXPLANATION_SIMLT_NOGAP = s9;
        AlignmentApplet.FIRST_EXPLANATION_NAME = "";
        AlignmentApplet.SECOND_EXPLANATION_NAME = "";
        String s10 = localization.Messages.getString("AlignmentApplet.17");
        AlignmentApplet.EXPLANATION_FILE_NAME = s10;
    }
}