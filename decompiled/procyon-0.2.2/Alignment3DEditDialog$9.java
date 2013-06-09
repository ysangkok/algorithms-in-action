import java.awt.event.*;

class Alignment3DEditDialog$9 implements AdjustmentListener {
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        this.val$algorithm.moveX(Alignment3DEditDialog.this.xMove.getValue());
    }
}