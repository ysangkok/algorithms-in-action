package aia.graph.common;

import com.cim.AIA.*;

public class DragSelectionListener implements SelectionListener
{
    protected GraphMapsCanvasExt m_parent;
    
    public DragSelectionListener(final GraphMapsCanvasExt canvas) {
        super();
        this.m_parent = canvas;
    }
    
    public void processSelectionEvent(final SelectionEvent e) {
        final GraphMapsNode node = (GraphMapsNode)e.paramObj;
        if (node.getNodeType() == 2) {
            this.m_parent.editMatrix(node);
        }
    }
}
