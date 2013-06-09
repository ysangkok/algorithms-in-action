import com.cim.AIA.*;
import aia.graph.common.*;

public class BFSGraphMapsCanvas extends GraphMapsCanvas implements GraphDialogEventHandler
{
    private static final long serialVersionUID = 8224743444398403802L;
    
    BFSGraphMapsCanvas() {
        super();
        this.tweens = new MultipleTween(this);
    }
}
