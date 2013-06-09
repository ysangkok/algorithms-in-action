import com.cim.AIA.*;

public class MergeSortThread extends AlgorithmThread
{
    public MergeSortThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new MergeSort(this, data.copy());
    }
    
    public AlgorithmWindow getWindow() {
        return super.getAlgorithmWindow();
    }
    
    public synchronized void backMode() {
        ((MergeSort)this.algorithm).resetRandomSeed();
        super.backMode();
    }
    
    public void resetSeed() {
        ((MergeSort)this.algorithm).resetRandomSeed();
    }
}
