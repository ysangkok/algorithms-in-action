public class MoveRequest {
    com.cim.AIA.Node source;
    com.cim.AIA.Node dest;
    
    public MoveRequest(com.cim.AIA.Node a, com.cim.AIA.Node a0)
    {
        super();
        this.source = a;
        this.dest = a0;
    }
    
    public com.cim.AIA.Node getDest()
    {
        com.cim.AIA.Node a = this.dest;
        return a;
    }
    
    public com.cim.AIA.Node getSource()
    {
        com.cim.AIA.Node a = this.source;
        return a;
    }
}