import com.cim.AIA.*;
import java.awt.event.*;

class AlignmentAnimationWindow$VariationMethod$1 implements ItemListener {
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
        final Log log = new Log(4, 21, null, this.val$name);
        if (AlignmentAnimationWindow.this.getLogger() != null) {
            AlignmentAnimationWindow.this.getLogger().addLog(log);
        }
    }
}