package com.cim.AIA;

import localization.*;
import java.awt.*;
import com.cim.common.*;

public class SearchSelection implements SelectionListener
{
    protected static Integer selectedData;
    protected static Node selectedNode;
    private static boolean searchEnabled;
    private static SearchSelection instance;
    private static MessageDialog messageDialog;
    
    protected static void displayMessage(final String label, final boolean modalMode) {
        Point messageDialogPoint = new Point(40, 40);
        boolean okButton = false;
        if (modalMode) {
            okButton = true;
        }
        messageDialogPoint = null;
        if (SearchSelection.messageDialog != null) {
            final Point temp = SearchSelection.messageDialog.getLocation();
            if (temp.x != 0 && temp.y != 0) {
                messageDialogPoint = new Point(temp.x, temp.y);
            }
            SearchSelection.messageDialog.setVisible(false);
            SearchSelection.messageDialog.dispose();
        }
        SearchSelection.messageDialog = new MessageDialog(label, modalMode, okButton);
        if (messageDialogPoint != null) {
            SearchSelection.messageDialog.setLocation(messageDialogPoint);
        }
        SearchSelection.messageDialog.setTitle("Hello");
        SearchSelection.messageDialog.setVisible(true);
    }
    
    public static Integer getData() {
        return SearchSelection.selectedData;
    }
    
    public static boolean getEnabled() {
        return SearchSelection.searchEnabled;
    }
    
    public static SearchSelection getInstance() {
        if (SearchSelection.instance == null) {
            SearchSelection.instance = new SearchSelection();
        }
        return SearchSelection.instance;
    }
    
    public static Node getNode() {
        return SearchSelection.selectedNode;
    }
    
    public static void setEnabled(final boolean enabled) {
        if (enabled) {
            SearchSelection.selectedNode = null;
            SearchSelection.selectedData = null;
            final String message = Messages.getString("SearchSelection.0");
            SearchSelection.messageDialog = new MessageDialog(message, false, false);
            SearchSelection.messageDialog.setTitle(Messages.getString("SearchSelection.1"));
            SearchSelection.messageDialog.setVisible(true);
        }
        else if (SearchSelection.messageDialog != null) {
            SearchSelection.messageDialog.setVisible(false);
            SearchSelection.messageDialog.dispose();
        }
        SearchSelection.searchEnabled = enabled;
    }
    
    public void processSelectionEvent(final SelectionEvent e) {
        if (SearchSelection.searchEnabled) {
            SearchSelection.selectedData = Integer.valueOf(((Node)e.paramObj).getDisplayString());
            SearchSelection.selectedNode = (Node)e.paramObj;
        }
        else {
            ((Node)e.paramObj).unHighlight();
        }
    }
    
    static {
        SearchSelection.selectedData = null;
        SearchSelection.selectedNode = null;
        SearchSelection.searchEnabled = false;
        SearchSelection.instance = null;
    }
}
