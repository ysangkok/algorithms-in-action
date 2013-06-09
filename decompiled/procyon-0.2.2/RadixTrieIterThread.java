import com.cim.AIA.*;

public class RadixTrieIterThread extends AlgorithmThread
{
    public RadixTrieIterThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new RadixTrieIter(this, data.copy());
    }
    
    public Algorithm getAlgorithm() {
        return super.getAlgorithm();
    }
}
