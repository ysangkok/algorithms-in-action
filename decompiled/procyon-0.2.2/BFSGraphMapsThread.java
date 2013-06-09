import com.cim.AIA.*;

public class BFSGraphMapsThread extends AlgorithmThread
{
    public BFSGraphMapsThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new BFSGraphMaps(this, data.copy());
    }
}
