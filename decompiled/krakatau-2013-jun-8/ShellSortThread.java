public class ShellSortThread extends com.cim.AIA.AlgorithmThread {
    public ShellSortThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        ShellSort a2 = new ShellSort((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
    
    public com.cim.AIA.AlgorithmWindow getWindow()
    {
        com.cim.AIA.AlgorithmWindow a = ((com.cim.AIA.AlgorithmThread)this).getAlgorithmWindow();
        return a;
    }
}