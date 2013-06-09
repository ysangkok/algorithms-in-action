public class SplayTreeIterThread extends com.cim.AIA.AlgorithmThread {
    public SplayTreeIterThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        SplayTreeIter a2 = new SplayTreeIter((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
    
    public com.cim.AIA.AlgorithmWindow getAlgorithmWindow()
    {
        com.cim.AIA.AlgorithmWindow a = ((com.cim.AIA.AlgorithmThread)this).getAlgorithmWindow();
        return a;
    }
    
    public com.cim.AIA.Algorithm getAlgorithm()
    {
        com.cim.AIA.Algorithm a = ((com.cim.AIA.AlgorithmThread)this).getAlgorithm();
        return a;
    }
    
    public int getRunSleepDuration()
    {
        int i = this.runSleepDuration;
        return i;
    }
}