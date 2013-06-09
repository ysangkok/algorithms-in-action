import java.awt.event.*;

class Alignment3DEditDialog$7 implements AdjustmentListener {
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        this.val$algorithm.translateY(Alignment3DEditDialog.this.yTranslate.getValue());
    }
}