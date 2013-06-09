import com.cim.common.*;
import localization.*;
import java.awt.event.*;

class RedBlackTreeAnimationWindow$4 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        MessageDialog msg;
        if (RedBlackTreeAnimationWindow.access$000(RedBlackTreeAnimationWindow.this)) {
            msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.14"), true, true);
            msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.15"));
            msg.setVisible(true);
            return;
        }
        if (RedBlackTreeAnimationWindow.access$100(RedBlackTreeAnimationWindow.this)) {
            this.val$redBlackTree.leftRotateRequest();
            return;
        }
        msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.16"), true, true);
        msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.17"));
        msg.setVisible(true);
    }
}