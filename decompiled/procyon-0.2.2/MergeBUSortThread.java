import com.cim.AIA.*;

public class MergeBUSortThread extends AlgorithmThread
{
    public MergeBUSortThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new MergeBUSort(this, data.copy());
    }
    
    public AlgorithmWindow getWindow() {
        return super.getAlgorithmWindow();
    }
    
    public synchronized void backMode() {
        ((MergeBUSort)this.algorithm).resetRandomSeed();
        super.backMode();
    }
    
    public void resetSeed() {
        ((MergeBUSort)this.algorithm).resetRandomSeed();
    }
}
