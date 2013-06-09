import java.awt.event.*;

class AlignmentCostDialog$1 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        AlignmentCostDialog.this.cancelWasPressed = false;
        if (AlignmentCostDialog.this.checkLength()) {
            AlignmentCostDialog.this.setVisible(false);
        }
    }
}