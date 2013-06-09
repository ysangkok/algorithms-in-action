public class SelectionSortThread extends com.cim.AIA.AlgorithmThread {
    public SelectionSortThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        SelectionSort a2 = new SelectionSort((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
}