import com.cim.AIA.*;

public class TransclosureGraphMapsThread extends AlgorithmThread
{
    public TransclosureGraphMapsThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new TransclosureGraphMaps(this, data.copy());
    }
    
    public Algorithm getGraphMapsAlgorithm() {
        return this.getAlgorithm();
    }
}
