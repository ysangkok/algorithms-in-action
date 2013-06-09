import java.awt.event.*;

class Alignment3DEditDialog$5 implements AdjustmentListener {
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        this.val$algorithm.rotateZ(Alignment3DEditDialog.this.zScroll.getValue());
    }
}