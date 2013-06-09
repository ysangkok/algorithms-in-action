import com.cim.AIA.*;

public class KMPThread extends AlgorithmThread
{
    public KMPThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new KMP(this, data.copy());
    }
}
