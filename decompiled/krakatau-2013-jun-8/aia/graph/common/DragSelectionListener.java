package aia.graph.common;

public class DragSelectionListener implements com.cim.AIA.SelectionListener {
    protected aia.graph.common.GraphMapsCanvasExt m_parent;
    
    public DragSelectionListener(aia.graph.common.GraphMapsCanvasExt a)
    {
        super();
        this.m_parent = a;
    }
    
    public void processSelectionEvent(com.cim.AIA.SelectionEvent a)
    {
        Object a0 = a.paramObj;
        aia.graph.common.GraphMapsNode dummy = (aia.graph.common.GraphMapsNode)a0;
        aia.graph.common.GraphMapsNode a1 = (aia.graph.common.GraphMapsNode)a0;
        int i = a1.getNodeType();
        if(i == 2)
        {
            aia.graph.common.GraphMapsCanvasExt a2 = this.m_parent;
            a2.editMatrix(a1);
        }
    }
}