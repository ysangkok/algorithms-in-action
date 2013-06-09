import com.cim.AIA.*;

public class DistributionCountingThread extends AlgorithmThread
{
    public DistributionCountingThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new DistributionCounting(this, data.copy());
    }
}
