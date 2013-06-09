import com.cim.AIA.*;

public class MoveRequest
{
    Node source;
    Node dest;
    
    public MoveRequest(final Node source, final Node dest) {
        super();
        this.source = source;
        this.dest = dest;
    }
    
    public Node getDest() {
        return this.dest;
    }
    
    public Node getSource() {
        return this.source;
    }
}
