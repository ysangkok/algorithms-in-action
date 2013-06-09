import com.cim.AIA.*;

public class RadixTrieMultiThread extends AlgorithmThread
{
    public RadixTrieMultiThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new RadixTrieMulti(this, data.copy());
    }
    
    public Algorithm getAlgorithm() {
        return super.getAlgorithm();
    }
}
