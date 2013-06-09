import com.cim.AIA.*;

public class HeapSortThread extends AlgorithmThread
{
    public HeapSortThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new HeapSort(this, data.copy());
    }
}
