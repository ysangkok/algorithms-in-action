import java.awt.*;
import com.cim.common.*;
import com.cim.AIA.*;

public class NodeSelection implements SelectionListener
{
    protected static Integer selectedData;
    protected static Node selectedNode;
    private static boolean searchEnabled;
    private static NodeSelection instance;
    private static MessageDialog messageDialog;
    protected RedBlackTree redBlackTree;
    
    protected static void displayMessage(final String label, final boolean modalMode) {
        Point messageDialogPoint = new Point(40, 40);
        boolean okButton = false;
        if (modalMode) {
            okButton = true;
        }
        messageDialogPoint = null;
        if (NodeSelection.messageDialog != null) {
            final Point temp = NodeSelection.messageDialog.getLocation();
            if (temp.x != 0 && temp.y != 0) {
                messageDialogPoint = new Point(temp.x, temp.y);
            }
            NodeSelection.messageDialog.setVisible(false);
            NodeSelection.messageDialog.dispose();
        }
        NodeSelection.messageDialog = new MessageDialog(label, modalMode, okButton);
        if (messageDialogPoint != null) {
            NodeSelection.messageDialog.setLocation(messageDialogPoint);
        }
        NodeSelection.messageDialog.setTitle("Hello");
        NodeSelection.messageDialog.setVisible(true);
    }
    
    public static Integer getData() {
        return NodeSelection.selectedData;
    }
    
    public static boolean getEnabled() {
        return NodeSelection.searchEnabled;
    }
    
    public static NodeSelection getInstance(final RedBlackTree r) {
        if (NodeSelection.instance == null) {
            NodeSelection.instance = new NodeSelection(r);
        }
        return NodeSelection.instance;
    }
    
    public static Node getNode() {
        return NodeSelection.selectedNode;
    }
    
    public static void setEnabled(final boolean enabled) {
        if (enabled) {
            NodeSelection.selectedNode = null;
            NodeSelection.selectedData = null;
        }
        NodeSelection.searchEnabled = enabled;
    }
    
    private NodeSelection(final RedBlackTree r) {
        super();
        this.redBlackTree = null;
        this.redBlackTree = r;
    }
    
    public void processSelectionEvent(final SelectionEvent e) {
        if (NodeSelection.searchEnabled) {
            NodeSelection.selectedData = Integer.valueOf(((Node)e.paramObj).getDisplayString());
            NodeSelection.selectedNode = (Node)e.paramObj;
            this.redBlackTree.processNodeSelection();
        }
        else {
            ((Node)e.paramObj).unHighlight();
        }
    }
    
    static {
        NodeSelection.selectedData = null;
        NodeSelection.selectedNode = null;
        NodeSelection.searchEnabled = false;
        NodeSelection.instance = null;
    }
}
