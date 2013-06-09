package com.cim.AIA;

public class SearchSelection implements com.cim.AIA.SelectionListener {
    protected static Integer selectedData;
    protected static com.cim.AIA.Node selectedNode;
    private static boolean searchEnabled;
    private static com.cim.AIA.SearchSelection instance;
    private static com.cim.common.MessageDialog messageDialog;
    
    protected static void displayMessage(String s, boolean b)
    {
        java.awt.Point a = null;
        int i = b?1:0;
        java.awt.Point a0 = new java.awt.Point(40, 40);
        int i0 = i == 0?0:1;
        com.cim.common.MessageDialog a1 = com.cim.AIA.SearchSelection.messageDialog;
        label0: {
            java.awt.Point a2 = null;
            java.awt.Point a3 = null;
            if(a1 == null)
            {
                a = a3;
                break label0;
            }
            com.cim.common.MessageDialog a4 = com.cim.AIA.SearchSelection.messageDialog;
            java.awt.Point a5 = a4.getLocation();
            int i1 = a5.x;
            label1: {
                java.awt.Point a6 = null;
                if(i1 == 0)
                {
                    a2 = a6;
                    break label1;
                }
                int i2 = a5.y;
                java.awt.Point a7 = null;
                if(i2 == 0)
                {
                    a2 = a7;
                }
                else
                {
                    int i3 = a5.x;
                    int i4 = a5.y;
                    java.awt.Point a8 = new java.awt.Point(i3, i4);
                    a2 = a8;
                }
            }
            com.cim.common.MessageDialog a9 = com.cim.AIA.SearchSelection.messageDialog;
            a9.setVisible(false);
            com.cim.common.MessageDialog a10 = com.cim.AIA.SearchSelection.messageDialog;
            a10.dispose();
            a = a2;
        }
        com.cim.common.MessageDialog a11 = new com.cim.common.MessageDialog(s, i != 0, i0 != 0);
        com.cim.AIA.SearchSelection.messageDialog = a11;
        if(a != null)
        {
            com.cim.common.MessageDialog a12 = com.cim.AIA.SearchSelection.messageDialog;
            a12.setLocation(a);
        }
        com.cim.common.MessageDialog a13 = com.cim.AIA.SearchSelection.messageDialog;
        a13.setTitle("Hello");
        com.cim.common.MessageDialog a14 = com.cim.AIA.SearchSelection.messageDialog;
        a14.setVisible(true);
    }
    
    public static Integer getData()
    {
        Integer a = com.cim.AIA.SearchSelection.selectedData;
        return a;
    }
    
    public static boolean getEnabled()
    {
        int i = com.cim.AIA.SearchSelection.searchEnabled?1:0;
        return i != 0;
    }
    
    public static com.cim.AIA.SearchSelection getInstance()
    {
        com.cim.AIA.SearchSelection a = com.cim.AIA.SearchSelection.instance;
        if(a == null)
        {
            com.cim.AIA.SearchSelection a0 = new com.cim.AIA.SearchSelection();
            com.cim.AIA.SearchSelection.instance = a0;
        }
        com.cim.AIA.SearchSelection a1 = com.cim.AIA.SearchSelection.instance;
        return a1;
    }
    
    public static com.cim.AIA.Node getNode()
    {
        com.cim.AIA.Node a = com.cim.AIA.SearchSelection.selectedNode;
        return a;
    }
    
    public static void setEnabled(boolean b)
    {
        int i = 0;
        label1: {
            label0: {
                int i0 = b?1:0;
                if(!b)
                {
                    break label0;
                }
                com.cim.AIA.SearchSelection.selectedNode = null;
                com.cim.AIA.SearchSelection.selectedData = null;
                String s = localization.Messages.getString("SearchSelection.0");
                com.cim.common.MessageDialog a = new com.cim.common.MessageDialog(s, false, false);
                com.cim.AIA.SearchSelection.messageDialog = a;
                com.cim.common.MessageDialog a0 = com.cim.AIA.SearchSelection.messageDialog;
                String s0 = localization.Messages.getString("SearchSelection.1");
                a0.setTitle(s0);
                com.cim.common.MessageDialog a1 = com.cim.AIA.SearchSelection.messageDialog;
                a1.setVisible(true);
                i = i0;
                break label1;
            }
            com.cim.common.MessageDialog a2 = com.cim.AIA.SearchSelection.messageDialog;
            if(a2 == null)
            {
                i = 0;
            }
            else
            {
                com.cim.common.MessageDialog a3 = com.cim.AIA.SearchSelection.messageDialog;
                a3.setVisible(false);
                com.cim.common.MessageDialog a4 = com.cim.AIA.SearchSelection.messageDialog;
                a4.dispose();
                i = 0;
            }
        }
        com.cim.AIA.SearchSelection.searchEnabled = i != 0;
    }
    
    private SearchSelection()
    {
        super();
    }
    
    public void processSelectionEvent(com.cim.AIA.SelectionEvent a)
    {
        int i = com.cim.AIA.SearchSelection.searchEnabled?1:0;
        if(i == 0)
        {
            Object a0 = a.paramObj;
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a0;
            com.cim.AIA.Node a1 = (com.cim.AIA.Node)a0;
            a1.unHighlight();
        }
        else
        {
            Object a2 = a.paramObj;
            com.cim.AIA.Node dummy0 = (com.cim.AIA.Node)a2;
            com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
            String s = a3.getDisplayString();
            Integer a4 = Integer.valueOf(s);
            com.cim.AIA.SearchSelection.selectedData = a4;
            Object a5 = a.paramObj;
            com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a5;
            com.cim.AIA.Node a6 = (com.cim.AIA.Node)a5;
            com.cim.AIA.SearchSelection.selectedNode = a6;
        }
    }
    
    static
    {
        com.cim.AIA.SearchSelection.selectedData = null;
        com.cim.AIA.SearchSelection.selectedNode = null;
        com.cim.AIA.SearchSelection.searchEnabled = false;
        com.cim.AIA.SearchSelection.instance = null;
    }
}