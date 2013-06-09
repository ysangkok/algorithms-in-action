import com.cim.AIA.*;

public class SplayTreeThread extends AlgorithmThread
{
    public SplayTreeThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new SplayTree(this, data.copy());
    }
    
    public AlgorithmWindow getAlgorithmWindow() {
        return super.getAlgorithmWindow();
    }
    
    public Algorithm getAlgorithm() {
        return super.getAlgorithm();
    }
    
    public int getRunSleepDuration() {
        return this.runSleepDuration;
    }
}
