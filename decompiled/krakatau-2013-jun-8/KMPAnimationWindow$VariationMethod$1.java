class KMPAnimationWindow$VariationMethod$1 implements java.awt.event.ItemListener {
    final KMPAnimationWindow val$this$0;
    final KMPAnimationWindow$VariationMethod this$1;
    
    KMPAnimationWindow$VariationMethod$1(KMPAnimationWindow$VariationMethod a, KMPAnimationWindow a0)
    {
        this.this$1 = a;
        this.val$this$0 = a0;
        super();
    }
    
    public void itemStateChanged(java.awt.event.ItemEvent a)
    {
        KMPAnimationWindow$VariationMethod a0 = this.this$1;
        KMPAnimationWindow a1 = a0.animWindow;
        a1.resetVariationButtons();
        KMPAnimationWindow$VariationMethod a2 = this.this$1;
        a2.setState(true);
        KMPAnimationWindow$VariationMethod a3 = this.this$1;
        KMP a4 = a3.kmp;
        KMPAnimationWindow$VariationMethod a5 = this.this$1;
        int i = a5.variationMethod;
        a4.setVariation(i);
        KMPAnimationWindow$VariationMethod a6 = this.this$1;
        KMPAnimationWindow a7 = a6.this$0;
        a7.resetThread(true, true, false, false);
        KMPAnimationWindow$VariationMethod a8 = this.this$1;
        com.cim.AIA.AlgorithmWindow a9 = a8.algoWindow;
        KMPAnimationWindow$VariationMethod a10 = this.this$1;
        com.cim.AIA.LadderTree a11 = a10.ladderTree;
        a9.setLadderTree(a11);
    }
}