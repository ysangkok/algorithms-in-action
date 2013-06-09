import com.cim.AIA.*;

public class MSTKGraphMapsThread extends AlgorithmThread
{
    public MSTKGraphMapsThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new MSTKGraphMaps(this, data.copy());
    }
}
