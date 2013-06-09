public class BFSGraphMapsThread extends com.cim.AIA.AlgorithmThread {
    public BFSGraphMapsThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        BFSGraphMaps a2 = new BFSGraphMaps((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
}