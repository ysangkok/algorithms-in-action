import java.awt.event.*;

class RedBlackTreeAnimationWindow$8 implements AdjustmentListener {
    public void adjustmentValueChanged(final AdjustmentEvent e) {
        this.val$redBlackTree.setAnimDuration(RedBlackTreeAnimationWindow.access$200(RedBlackTreeAnimationWindow.this) - RedBlackTreeAnimationWindow.this.speedBar.getValue());
        RedBlackTreeAnimationWindow.this.tuteCanvas.setTweenSpeed(RedBlackTreeAnimationWindow.access$200(RedBlackTreeAnimationWindow.this) - RedBlackTreeAnimationWindow.this.speedBar.getValue());
    }
}