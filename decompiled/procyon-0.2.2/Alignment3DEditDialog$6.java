import java.awt.event.*;

class Alignment3DEditDialog$6 implements AdjustmentListener {
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        this.val$algorithm.translateX(Alignment3DEditDialog.this.xTranslate.getValue());
    }
}