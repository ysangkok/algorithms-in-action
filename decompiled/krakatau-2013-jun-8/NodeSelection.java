public class NodeSelection implements com.cim.AIA.SelectionListener {
    protected static Integer selectedData;
    protected static com.cim.AIA.Node selectedNode;
    private static boolean searchEnabled;
    private static NodeSelection instance;
    private static com.cim.common.MessageDialog messageDialog;
    protected RedBlackTree redBlackTree;
    
    protected static void displayMessage(String s, boolean b)
    {
        java.awt.Point a = null;
        int i = b?1:0;
        java.awt.Point a0 = new java.awt.Point(40, 40);
        int i0 = i == 0?0:1;
        com.cim.common.MessageDialog a1 = NodeSelection.messageDialog;
        label0: {
            java.awt.Point a2 = null;
            java.awt.Point a3 = null;
            if(a1 == null)
            {
                a = a3;
                break label0;
            }
            com.cim.common.MessageDialog a4 = NodeSelection.messageDialog;
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
            com.cim.common.MessageDialog a9 = NodeSelection.messageDialog;
            a9.setVisible(false);
            com.cim.common.MessageDialog a10 = NodeSelection.messageDialog;
            a10.dispose();
            a = a2;
        }
        com.cim.common.MessageDialog a11 = new com.cim.common.MessageDialog(s, i != 0, i0 != 0);
        NodeSelection.messageDialog = a11;
        if(a != null)
        {
            com.cim.common.MessageDialog a12 = NodeSelection.messageDialog;
            a12.setLocation(a);
        }
        com.cim.common.MessageDialog a13 = NodeSelection.messageDialog;
        a13.setTitle("Hello");
        com.cim.common.MessageDialog a14 = NodeSelection.messageDialog;
        a14.setVisible(true);
    }
    
    public static Integer getData()
    {
        Integer a = NodeSelection.selectedData;
        return a;
    }
    
    public static boolean getEnabled()
    {
        int i = NodeSelection.searchEnabled?1:0;
        return i != 0;
    }
    
    public static NodeSelection getInstance(RedBlackTree a)
    {
        NodeSelection a0 = NodeSelection.instance;
        if(a0 == null)
        {
            NodeSelection a1 = new NodeSelection(a);
            NodeSelection.instance = a1;
        }
        NodeSelection a2 = NodeSelection.instance;
        return a2;
    }
    
    public static com.cim.AIA.Node getNode()
    {
        com.cim.AIA.Node a = NodeSelection.selectedNode;
        return a;
    }
    
    public static void setEnabled(boolean b)
    {
        int i = 0;
        int i0 = b?1:0;
        int i1 = b?1:0;
        if(!b)
        {
            i = i1;
        }
        else
        {
            NodeSelection.selectedNode = null;
            NodeSelection.selectedData = null;
            i = i0;
        }
        NodeSelection.searchEnabled = i != 0;
    }
    
    private NodeSelection(RedBlackTree a)
    {
        super();
        this.redBlackTree = null;
        this.redBlackTree = a;
    }
    
    public void processSelectionEvent(com.cim.AIA.SelectionEvent a)
    {
        int i = NodeSelection.searchEnabled?1:0;
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
            NodeSelection.selectedData = a4;
            Object a5 = a.paramObj;
            com.cim.AIA.Node dummy1 = (com.cim.AIA.Node)a5;
            com.cim.AIA.Node a6 = (com.cim.AIA.Node)a5;
            NodeSelection.selectedNode = a6;
            RedBlackTree a7 = this.redBlackTree;
            a7.processNodeSelection();
        }
    }
    
    static
    {
        NodeSelection.selectedData = null;
        NodeSelection.selectedNode = null;
        NodeSelection.searchEnabled = false;
        NodeSelection.instance = null;
    }
}