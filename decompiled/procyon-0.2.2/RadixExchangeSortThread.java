import com.cim.AIA.*;

public class RadixExchangeSortThread extends AlgorithmThread
{
    public RadixExchangeSortThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new RadixExchangeSort(this, data.copy());
    }
}
