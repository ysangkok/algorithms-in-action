import com.cim.AIA.*;

public class SkipListThread extends AlgorithmThread
{
    public SkipListThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new SkipList(this, data.copy());
    }
    
    public Algorithm getAlgorithm() {
        return super.getAlgorithm();
    }
}
