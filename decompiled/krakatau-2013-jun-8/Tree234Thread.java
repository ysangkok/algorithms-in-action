public class Tree234Thread extends com.cim.AIA.AlgorithmThread {
    public Tree234Thread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super(a, a0);
    }
    
    public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable a)
    {
        Object a0 = a;
        Object a1 = ((com.cim.AIA.Copyable)a0).copy();
        Tree234Tree a2 = new Tree234Tree((com.cim.AIA.AlgorithmThread)this, a1);
        return a2;
    }
    
    public com.cim.AIA.Algorithm getAlgorithm()
    {
        com.cim.AIA.Algorithm a = ((com.cim.AIA.AlgorithmThread)this).getAlgorithm();
        return a;
    }
}