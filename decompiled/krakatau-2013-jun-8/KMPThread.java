public class KMPThread extends com.cim.AIA.AlgorithmThread {
    public KMPThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        KMP a2 = new KMP((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
}