import java.awt.event.*;

class KMPAnimationWindow$VariationMethod$1 implements ItemListener {
    public void itemStateChanged(final ItemEvent e) {
        VariationMethod.this.animWindow.resetVariationButtons();
        VariationMethod.this.setState(true);
        VariationMethod.this.kmp.setVariation(VariationMethod.this.variationMethod);
        KMPAnimationWindow.this.resetThread(true, true, false, false);
        VariationMethod.this.algoWindow.setLadderTree(VariationMethod.this.ladderTree);
    }
}