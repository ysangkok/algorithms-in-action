import com.cim.AIA.*;
import aia.graph.common.*;

public class BFSGraphMapsCanvasExt extends GraphMapsCanvasExt implements GraphDialogEventHandler
{
    private static final long serialVersionUID = 8004499584041845061L;
    
    BFSGraphMapsCanvasExt() {
        super();
        this.tweens = new MultipleTween(this);
        this.addSelectionListener(new DragSelectionListener(this));
    }
}
