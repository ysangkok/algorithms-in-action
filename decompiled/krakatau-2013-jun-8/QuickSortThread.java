public class QuickSortThread extends com.cim.AIA.AlgorithmThread {
    public QuickSortThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public synchronized void backMode()
    {
        int i = com.cim.AIA.Logger.DEBUG?1:0;
        if(i != 0)
        {
            java.io.PrintStream a = System.out;
            a.println("in backMode fn (QuickSortThread)");
        }
        com.cim.AIA.Algorithm a0 = this.algorithm;
        QuickSort dummy = (QuickSort)a0;
        QuickSort a1 = (QuickSort)a0;
        a1.resetRandomSeed();
        ((com.cim.AIA.AlgorithmThread)this).backMode();
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        QuickSort a2 = new QuickSort((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
    
    public com.cim.AIA.AlgorithmWindow getWindow()
    {
        com.cim.AIA.AlgorithmWindow a = ((com.cim.AIA.AlgorithmThread)this).getAlgorithmWindow();
        return a;
    }
    
    public void resetSeed()
    {
        com.cim.AIA.Algorithm a = this.algorithm;
        QuickSort dummy = (QuickSort)a;
        QuickSort a0 = (QuickSort)a;
        a0.resetRandomSeed();
    }
}