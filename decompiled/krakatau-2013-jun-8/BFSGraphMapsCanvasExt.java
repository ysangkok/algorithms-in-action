public class BFSGraphMapsCanvasExt extends aia.graph.common.GraphMapsCanvasExt implements aia.graph.common.GraphDialogEventHandler {
    final private static long serialVersionUID = 8004499584041845061L;
    
    BFSGraphMapsCanvasExt()
    {
        super();
        com.cim.AIA.MultipleTween a = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        this.tweens = a;
        aia.graph.common.DragSelectionListener a0 = new aia.graph.common.DragSelectionListener((aia.graph.common.GraphMapsCanvasExt)this);
        this.addSelectionListener((com.cim.AIA.SelectionListener)a0);
    }
}