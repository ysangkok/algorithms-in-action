public class MergeSortThread extends com.cim.AIA.AlgorithmThread {
    public MergeSortThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        MergeSort a2 = new MergeSort((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
    
    public com.cim.AIA.AlgorithmWindow getWindow()
    {
        com.cim.AIA.AlgorithmWindow a = ((com.cim.AIA.AlgorithmThread)this).getAlgorithmWindow();
        return a;
    }
    
    public synchronized void backMode()
    {
        com.cim.AIA.Algorithm a = this.algorithm;
        MergeSort dummy = (MergeSort)a;
        MergeSort a0 = (MergeSort)a;
        a0.resetRandomSeed();
        ((com.cim.AIA.AlgorithmThread)this).backMode();
    }
    
    public void resetSeed()
    {
        com.cim.AIA.Algorithm a = this.algorithm;
        MergeSort dummy = (MergeSort)a;
        MergeSort a0 = (MergeSort)a;
        a0.resetRandomSeed();
    }
}