import java.awt.event.*;

class HeapSortAnimationWindow$3 implements ItemListener {
    public void itemStateChanged(final ItemEvent e) {
        HeapSortAnimationWindow.this.heapSortCanvas.setMarkersEnabled(HeapSortAnimationWindow.this.easyMode);
    }
}