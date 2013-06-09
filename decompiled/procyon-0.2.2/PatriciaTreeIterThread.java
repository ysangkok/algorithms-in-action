import com.cim.AIA.*;

public class PatriciaTreeIterThread extends AlgorithmThread
{
    public PatriciaTreeIterThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new PatriciaTreeIter(this, data.copy());
    }
    
    public AlgorithmWindow getWindow() {
        return this.algorithmWindow;
    }
    
    public Algorithm getAlgorithm() {
        return super.getAlgorithm();
    }
}
