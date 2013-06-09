import com.cim.AIA.*;

public class SortedListThread extends AlgorithmThread
{
    public SortedListThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new SortedList(this, data.copy());
    }
}
