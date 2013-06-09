public class RadixExchangeSortApplet extends com.cim.AIA.AlgorithmApplet {
    final private static long serialVersionUID = -1277810669627720205L;
    final protected static String IMAGE_DIRECTORY = "images/";
    final protected static String ALGORITHM_FILE_NAME;
    final protected static String EXPLANATION_TITLE;
    final protected static String EXPLANATION_FILE_NAME;
    protected com.cim.AIA.IntArray initalData;
    protected boolean debugingMode;
    
    public RadixExchangeSortApplet()
    {
        super();
        int[] a = RadixExchangeSortAnimationWindow.DEFAULT_DATA;
        com.cim.AIA.IntArray a0 = new com.cim.AIA.IntArray(a);
        this.initalData = a0;
        this.debugingMode = false;
        this.imageDirectory = "images/";
        String s = RadixExchangeSortApplet.ALGORITHM_FILE_NAME;
        this.algorithmFileName = s;
        String s0 = RadixExchangeSortApplet.EXPLANATION_TITLE;
        this.explanationTitle = s0;
        String s1 = RadixExchangeSortApplet.EXPLANATION_FILE_NAME;
        this.explanationFileName = s1;
        int i = this.debugingMode?1:0;
        if(i != 0)
        {
            String s2 = localization.Messages.getString("RadixExchangeSortApplet.4");
            java.awt.Button a1 = new java.awt.Button(s2);
            RadixExchangeSortApplet$1 a2 = new RadixExchangeSortApplet$1(this);
            a1.addActionListener((java.awt.event.ActionListener)a2);
            java.awt.Component a3 = this.add((java.awt.Component)a1);
        }
    }
    
    public com.cim.AIA.AlgorithmCanvas getAlgorithmCanvas()
    {
        RadixExchangeSortCanvas a = new RadixExchangeSortCanvas();
        return a;
    }
    
    public com.cim.AIA.AlgorithmThread getAlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        Object a1 = a;
        RadixExchangeSortThread a2 = new RadixExchangeSortThread((com.cim.AIA.Copyable)a1, a0);
        return a2;
    }
    
    public com.cim.AIA.AnimationWindow getAnimationWindow(com.cim.AIA.AlgorithmCanvas a, com.cim.AIA.AlgorithmThread a0, java.applet.Applet a1)
    {
        RadixExchangeSortAnimationWindow a2 = new RadixExchangeSortAnimationWindow(a, a0, a1);
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
        String s = localization.Messages.getString("RadixExchangeSortApplet.0");
        RadixExchangeSortApplet.ALGORITHM_FILE_NAME = s;
        String s0 = localization.Messages.getString("RadixExchangeSortApplet.2");
        RadixExchangeSortApplet.EXPLANATION_TITLE = s0;
        String s1 = localization.Messages.getString("RadixExchangeSortApplet.1");
        RadixExchangeSortApplet.EXPLANATION_FILE_NAME = s1;
    }
}