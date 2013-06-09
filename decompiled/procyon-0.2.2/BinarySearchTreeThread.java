import com.cim.AIA.*;

public class BinarySearchTreeThread extends AlgorithmThread
{
    public BinarySearchTreeThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new BinarySearchTree(this, data.copy());
    }
    
    public Algorithm getAlgorithm() {
        return super.getAlgorithm();
    }
}
