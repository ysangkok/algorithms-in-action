import java.awt.event.*;
import com.cim.AIA.*;
import java.awt.*;

public class VariationMethod extends CheckboxMenuItem
{
    private static final long serialVersionUID = 1425958837788488856L;
    protected LadderTree ladderTree;
    protected KMP kmp;
    protected int variationMethod;
    protected KMPAnimationWindow animWindow;
    protected AlgorithmWindow algoWindow;
    
    public VariationMethod(final String name, final boolean state, final KMP aKmp, final String dataDir, final int aVariationMethod, final String filename, final KMPAnimationWindow kmpAnimationWindow, final AlgorithmWindow algorithmWindow) {
        super(name, state);
        this.kmp = aKmp;
        this.variationMethod = aVariationMethod;
        this.animWindow = kmpAnimationWindow;
        this.algoWindow = algorithmWindow;
        this.ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, this$0.getLogger(), this$0.getBreakPoint());
        this.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                VariationMethod.this.animWindow.resetVariationButtons();
                VariationMethod.this.setState(true);
                VariationMethod.this.kmp.setVariation(VariationMethod.this.variationMethod);
                KMPAnimationWindow.this.resetThread(true, true, false, false);
                VariationMethod.this.algoWindow.setLadderTree(VariationMethod.this.ladderTree);
            }
        });
    }
}
