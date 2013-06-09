public class MinSpanTreePrimGraphMapsThread extends com.cim.AIA.AlgorithmThread {
    public MinSpanTreePrimGraphMapsThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        MinSpanTreePrimGraphMaps a2 = new MinSpanTreePrimGraphMaps((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
}