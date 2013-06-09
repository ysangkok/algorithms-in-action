import com.cim.AIA.*;

public class StraightRadixSortThread extends AlgorithmThread
{
    public StraightRadixSortThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new StraightRadixSort(this, data.copy());
    }
}
