import java.awt.event.*;
import com.cim.AIA.*;
import java.awt.*;

public class VariationMethod extends CheckboxMenuItem
{
    private static final long serialVersionUID = -3574523095354355926L;
    protected LadderTree ladderTree;
    protected Alignment alignment;
    protected int variationMethod;
    protected AlignmentAnimationWindow animWindow;
    protected AlgorithmWindow algoWindow;
    protected String expName;
    
    public VariationMethod(final String name, final boolean state, final Alignment aAlignment, final String dataDir, final int aVariationMethod, final String filename, final String expFileName, final AlignmentAnimationWindow alignmentAnimationWindow, final AlgorithmWindow algorithmWindow) {
        super(name, state);
        this.alignment = aAlignment;
        this.variationMethod = aVariationMethod;
        this.animWindow = alignmentAnimationWindow;
        this.algoWindow = algorithmWindow;
        this.expName = expFileName;
        this.ladderTree = CodeCanvas.getLadderTreeFromFile(dataDir, filename, this$0.getLogger(), this$0.getBreakPoint());
        this.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent e) {
                VariationMethod.this.animWindow.resetVariationButtons();
                Copyable temp;
                if (Alignment.runningMode == 1 && (AlignmentAnimationWindow.this.ds1.getState() || AlignmentAnimationWindow.this.ds2.getState())) {
                    if (Alignment.currentVariation == 1) {
                        AlignmentAnimationWindow.this.ds1.setState(false);
                        temp = AlignmentAnimationWindow.this.ds2.getData();
                        AlignmentAnimationWindow.this.ds2.setState(true);
                        AlignmentAnimationWindow.this.setCurrentData(temp, true, true);
                    }
                    else {
                        AlignmentAnimationWindow.this.ds2.setState(false);
                        temp = AlignmentAnimationWindow.this.ds1.getData();
                        AlignmentAnimationWindow.this.ds1.setState(true);
                        AlignmentAnimationWindow.this.setCurrentData(temp, true, true);
                    }
                }
                if (Alignment.runningMode == 2 && (AlignmentAnimationWindow.this.ds3.getState() || AlignmentAnimationWindow.this.ds4.getState())) {
                    if (Alignment.currentVariation == 1) {
                        AlignmentAnimationWindow.this.ds3.setState(false);
                        temp = AlignmentAnimationWindow.this.ds4.getData();
                        AlignmentAnimationWindow.this.ds4.setState(true);
                        AlignmentAnimationWindow.this.setCurrentData(temp, true, true);
                    }
                    else {
                        AlignmentAnimationWindow.this.ds4.setState(false);
                        temp = AlignmentAnimationWindow.this.ds3.getData();
                        AlignmentAnimationWindow.this.ds3.setState(true);
                        AlignmentAnimationWindow.this.setCurrentData(temp, true, true);
                    }
                }
                VariationMethod.this.setState(true);
                AlignmentApplet.expWin.initialiseMainData(AlignmentApplet.codeBaseString, VariationMethod.this.expName);
                VariationMethod.this.alignment.setVariation(VariationMethod.this.variationMethod);
                AlignmentAnimationWindow.this.resetThread(true, true, false, false);
                VariationMethod.this.algoWindow.setLadderTree(VariationMethod.this.ladderTree);
                final Log log = new Log(4, 21, null, name);
                if (AlignmentAnimationWindow.this.getLogger() != null) {
                    AlignmentAnimationWindow.this.getLogger().addLog(log);
                }
            }
        });
    }
}
