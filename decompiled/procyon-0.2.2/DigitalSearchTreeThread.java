import com.cim.AIA.*;

public class DigitalSearchTreeThread extends AlgorithmThread
{
    public DigitalSearchTreeThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new DigitalSearchTree(this, data.copy());
    }
    
    public AlgorithmWindow getWindow() {
        return super.getAlgorithmWindow();
    }
    
    public Algorithm getAlgorithm() {
        return super.getAlgorithm();
    }
}
