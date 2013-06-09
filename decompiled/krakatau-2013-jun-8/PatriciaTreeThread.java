public class PatriciaTreeThread extends com.cim.AIA.AlgorithmThread {
    public PatriciaTreeThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        PatriciaTree a2 = new PatriciaTree((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
    
    public com.cim.AIA.Algorithm getAlgorithm()
    {
        com.cim.AIA.Algorithm a = ((com.cim.AIA.AlgorithmThread)this).getAlgorithm();
        return a;
    }
    
    public com.cim.AIA.AlgorithmWindow getWindow()
    {
        com.cim.AIA.AlgorithmWindow a = this.algorithmWindow;
        return a;
    }
}