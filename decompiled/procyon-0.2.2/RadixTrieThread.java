import com.cim.AIA.*;

public class RadixTrieThread extends AlgorithmThread
{
    public RadixTrieThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new RadixTrie(this, data.copy());
    }
    
    public Algorithm getAlgorithm() {
        return super.getAlgorithm();
    }
}
