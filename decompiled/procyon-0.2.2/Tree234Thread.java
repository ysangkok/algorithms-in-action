import com.cim.AIA.*;

public class Tree234Thread extends AlgorithmThread
{
    public Tree234Thread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new Tree234Tree(this, data.copy());
    }
    
    public Algorithm getAlgorithm() {
        return super.getAlgorithm();
    }
}
