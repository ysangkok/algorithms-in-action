import com.cim.AIA.*;

public class DFSGraphMapsThread extends AlgorithmThread
{
    public DFSGraphMapsThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new DFSGraphMaps(this, data.copy());
    }
}
