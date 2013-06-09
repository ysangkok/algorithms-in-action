import com.cim.AIA.*;

public class SelectionSortThread extends AlgorithmThread
{
    public SelectionSortThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new SelectionSort(this, data.copy());
    }
}
