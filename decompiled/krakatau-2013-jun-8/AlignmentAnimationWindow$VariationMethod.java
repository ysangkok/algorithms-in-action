public class AlignmentAnimationWindow$VariationMethod extends java.awt.CheckboxMenuItem {
    final private static long serialVersionUID = -3574523095354355926L;
    protected com.cim.AIA.LadderTree ladderTree;
    protected Alignment alignment;
    protected int variationMethod;
    protected AlignmentAnimationWindow animWindow;
    protected com.cim.AIA.AlgorithmWindow algoWindow;
    protected String expName;
    final AlignmentAnimationWindow this$0;
    
    public AlignmentAnimationWindow$VariationMethod(AlignmentAnimationWindow a, String s, boolean b, Alignment a0, String s0, int i, String s1, String s2, AlignmentAnimationWindow a1, com.cim.AIA.AlgorithmWindow a2)
    {
        this.this$0 = a;
        int i0 = b?1:0;
        super(s, i0 != 0);
        this.alignment = a0;
        this.variationMethod = i;
        this.animWindow = a1;
        this.algoWindow = a2;
        this.expName = s2;
        com.cim.AIA.Logger a3 = a.getLogger();
        com.cim.AIA.BreakPoint a4 = a.getBreakPoint();
        com.cim.AIA.LadderTree a5 = com.cim.AIA.CodeCanvas.getLadderTreeFromFile(s0, s1, a3, a4);
        this.ladderTree = a5;
        AlignmentAnimationWindow$VariationMethod$1 a6 = new AlignmentAnimationWindow$VariationMethod$1(this, a, s);
        this.addItemListener((java.awt.event.ItemListener)a6);
    }
}