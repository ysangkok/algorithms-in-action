public class StraightRadixSortThread extends com.cim.AIA.AlgorithmThread {
    public StraightRadixSortThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        StraightRadixSort a2 = new StraightRadixSort((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
}