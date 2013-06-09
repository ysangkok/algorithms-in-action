import java.awt.event.*;

class Alignment3DEditDialog$3 implements AdjustmentListener {
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        this.val$algorithm.rotateX(Alignment3DEditDialog.this.xScroll.getValue());
    }
}