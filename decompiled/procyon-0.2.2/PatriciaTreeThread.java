import com.cim.AIA.*;

public class PatriciaTreeThread extends AlgorithmThread
{
    public PatriciaTreeThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new PatriciaTree(this, data.copy());
    }
    
    public Algorithm getAlgorithm() {
        return super.getAlgorithm();
    }
    
    public AlgorithmWindow getWindow() {
        return this.algorithmWindow;
    }
}
