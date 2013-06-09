import com.cim.common.*;
import localization.*;
import java.awt.event.*;

class RedBlackTreeAnimationWindow$5 implements ActionListener {
    public void actionPerformed(final ActionEvent e) {
        MessageDialog msg;
        if (RedBlackTreeAnimationWindow.access$000(RedBlackTreeAnimationWindow.this)) {
            msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.18"), true, true);
            msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.19"));
            msg.setVisible(true);
            return;
        }
        if (RedBlackTreeAnimationWindow.access$100(RedBlackTreeAnimationWindow.this)) {
            this.val$redBlackTree.rightRotateRequest();
            return;
        }
        msg = new MessageDialog(Messages.getString("RedBlackTreeAnimationWindow.20"), true, true);
        msg.setTitle(Messages.getString("RedBlackTreeAnimationWindow.21"));
        msg.setVisible(true);
    }
}