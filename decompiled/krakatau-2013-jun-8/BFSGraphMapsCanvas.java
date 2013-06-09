public class BFSGraphMapsCanvas extends aia.graph.common.GraphMapsCanvas implements aia.graph.common.GraphDialogEventHandler {
    final private static long serialVersionUID = 8224743444398403802L;
    
    BFSGraphMapsCanvas()
    {
        super();
        com.cim.AIA.MultipleTween a = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        this.tweens = a;
    }
}