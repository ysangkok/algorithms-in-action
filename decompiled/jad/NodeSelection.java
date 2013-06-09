// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NodeSelection.java

import com.cim.AIA.*;
import com.cim.common.MessageDialog;
import java.awt.Point;

public class NodeSelection
    implements SelectionListener
{

    protected static void displayMessage(String label, boolean modalMode)
    {
        Point messageDialogPoint = new Point(40, 40);
        boolean okButton = false;
        if(modalMode)
            okButton = true;
        messageDialogPoint = null;
        if(messageDialog != null)
        {
            Point temp = messageDialog.getLocation();
            if(temp.x != 0 && temp.y != 0)
                messageDialogPoint = new Point(temp.x, temp.y);
            messageDialog.setVisible(false);
            messageDialog.dispose();
        }
        messageDialog = new MessageDialog(label, modalMode, okButton);
        if(messageDialogPoint != null)
            messageDialog.setLocation(messageDialogPoint);
        messageDialog.setTitle("Hello");
        messageDialog.setVisible(true);
    }

    public static Integer getData()
    {
        return selectedData;
    }

    public static boolean getEnabled()
    {
        return searchEnabled;
    }

    public static NodeSelection getInstance(RedBlackTree r)
    {
        if(instance == null)
            instance = new NodeSelection(r);
        return instance;
    }

    public static Node getNode()
    {
        return selectedNode;
    }

    public static void setEnabled(boolean enabled)
    {
        if(enabled)
        {
            selectedNode = null;
            selectedData = null;
        }
        searchEnabled = enabled;
    }

    private NodeSelection(RedBlackTree r)
    {
        redBlackTree = null;
        redBlackTree = r;
    }

    public void processSelectionEvent(SelectionEvent e)
    {
        if(searchEnabled)
        {
            selectedData = Integer.valueOf(((Node)e.paramObj).getDisplayString());
            selectedNode = (Node)e.paramObj;
            redBlackTree.processNodeSelection();
        } else
        {
            ((Node)e.paramObj).unHighlight();
        }
    }

    protected static Integer selectedData = null;
    protected static Node selectedNode = null;
    private static boolean searchEnabled = false;
    private static NodeSelection instance = null;
    private static MessageDialog messageDialog;
    protected RedBlackTree redBlackTree;

}
