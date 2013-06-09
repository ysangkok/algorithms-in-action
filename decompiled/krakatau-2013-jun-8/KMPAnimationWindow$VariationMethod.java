public class KMPAnimationWindow$VariationMethod extends java.awt.CheckboxMenuItem {
    final private static long serialVersionUID = 1425958837788488856L;
    protected com.cim.AIA.LadderTree ladderTree;
    protected KMP kmp;
    protected int variationMethod;
    protected KMPAnimationWindow animWindow;
    protected com.cim.AIA.AlgorithmWindow algoWindow;
    final KMPAnimationWindow this$0;
    
    public KMPAnimationWindow$VariationMethod(KMPAnimationWindow a, String s, boolean b, KMP a0, String s0, int i, String s1, KMPAnimationWindow a1, com.cim.AIA.AlgorithmWindow a2)
    {
        this.this$0 = a;
        int i0 = b?1:0;
        super(s, i0 != 0);
        this.kmp = a0;
        this.variationMethod = i;
        this.animWindow = a1;
        this.algoWindow = a2;
        com.cim.AIA.Logger a3 = a.getLogger();
        com.cim.AIA.BreakPoint a4 = a.getBreakPoint();
        com.cim.AIA.LadderTree a5 = com.cim.AIA.CodeCanvas.getLadderTreeFromFile(s0, s1, a3, a4);
        this.ladderTree = a5;
        KMPAnimationWindow$VariationMethod$1 a6 = new KMPAnimationWindow$VariationMethod$1(this, a);
        this.addItemListener((java.awt.event.ItemListener)a6);
    }
}