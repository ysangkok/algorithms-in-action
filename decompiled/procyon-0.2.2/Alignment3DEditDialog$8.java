import java.awt.event.*;

class Alignment3DEditDialog$8 implements AdjustmentListener {
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        this.val$algorithm.translateZ(Alignment3DEditDialog.this.zTranslate.getValue());
    }
}