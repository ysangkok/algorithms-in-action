import java.awt.event.*;

class Alignment3DEditDialog$4 implements AdjustmentListener {
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        this.val$algorithm.rotateY(Alignment3DEditDialog.this.yScroll.getValue());
    }
}