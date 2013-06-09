import com.cim.AIA.*;
import java.awt.event.*;

class QuickSortAnimationWindow$1 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        String cmd = e.paramString();
        final int i1 = cmd.indexOf("cmd=") + 4;
        final int i2 = cmd.indexOf(",", i1);
        cmd = cmd.substring(i1, i2);
        final Log l1 = new Log(4, 21, null, cmd);
        if (QuickSortAnimationWindow.this.getLogger() != null) {
            QuickSortAnimationWindow.this.getLogger().addLog(l1);
        }
        if (Logger.DEBUG) {
            System.err.println("Partition method action listener called.");
            System.err.println("paramString=" + e.paramString());
            System.err.println("cmd=" + cmd);
        }
    }
}