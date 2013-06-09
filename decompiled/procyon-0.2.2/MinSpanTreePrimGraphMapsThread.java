import com.cim.AIA.*;

public class MinSpanTreePrimGraphMapsThread extends AlgorithmThread
{
    public MinSpanTreePrimGraphMapsThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new MinSpanTreePrimGraphMaps(this, data.copy());
    }
}
