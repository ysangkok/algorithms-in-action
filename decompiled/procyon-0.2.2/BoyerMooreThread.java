import com.cim.AIA.*;

public class BoyerMooreThread extends AlgorithmThread
{
    public BoyerMooreThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new BoyerMoore(this, data.copy());
    }
}
