import localization.*;
import java.awt.*;
import com.cim.common.*;
import com.cim.AIA.*;

public class DeleteSelection implements SelectionListener
{
    protected static Integer selectedData;
    protected static Node selectedNode;
    private static boolean searchEnabled;
    private static DeleteSelection instance;
    private static MessageDialog messageDialog;
    
    protected static void displayMessage(final String label, final boolean modalMode) {
        Point messageDialogPoint = new Point(40, 40);
        boolean okButton = false;
        if (modalMode) {
            okButton = true;
        }
        messageDialogPoint = null;
        if (DeleteSelection.messageDialog != null) {
            final Point temp = DeleteSelection.messageDialog.getLocation();
            if (temp.x != 0 && temp.y != 0) {
                messageDialogPoint = new Point(temp.x, temp.y);
            }
            DeleteSelection.messageDialog.setVisible(false);
            DeleteSelection.messageDialog.dispose();
        }
        DeleteSelection.messageDialog = new MessageDialog(label, modalMode, okButton);
        if (messageDialogPoint != null) {
            DeleteSelection.messageDialog.setLocation(messageDialogPoint);
        }
        DeleteSelection.messageDialog.setTitle("Hello");
        DeleteSelection.messageDialog.setVisible(true);
    }
    
    public static Integer getData() {
        return DeleteSelection.selectedData;
    }
    
    public static boolean getEnabled() {
        return DeleteSelection.searchEnabled;
    }
    
    public static DeleteSelection getInstance() {
        if (DeleteSelection.instance == null) {
            DeleteSelection.instance = new DeleteSelection();
        }
        return DeleteSelection.instance;
    }
    
    public static Node getNode() {
        return DeleteSelection.selectedNode;
    }
    
    public static void setEnabled(final boolean enabled) {
        if (enabled) {
            DeleteSelection.selectedNode = null;
            DeleteSelection.selectedData = null;
            final String message = Messages.getString("DeleteSelection.0");
            DeleteSelection.messageDialog = new MessageDialog(message, false, false);
            DeleteSelection.messageDialog.setTitle(Messages.getString("DeleteSelection.1"));
            DeleteSelection.messageDialog.setVisible(true);
        }
        else if (DeleteSelection.messageDialog != null) {
            DeleteSelection.messageDialog.setVisible(false);
            DeleteSelection.messageDialog.dispose();
        }
        DeleteSelection.searchEnabled = enabled;
    }
    
    public void processSelectionEvent(final SelectionEvent e) {
        if (DeleteSelection.searchEnabled) {
            DeleteSelection.selectedData = Integer.valueOf(((Node)e.paramObj).getDisplayString());
            DeleteSelection.selectedNode = (Node)e.paramObj;
        }
        else {
            ((Node)e.paramObj).unHighlight();
        }
    }
    
    static {
        DeleteSelection.selectedData = null;
        DeleteSelection.selectedNode = null;
        DeleteSelection.searchEnabled = false;
        DeleteSelection.instance = null;
    }
}
