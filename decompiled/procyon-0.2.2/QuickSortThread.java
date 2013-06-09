import com.cim.AIA.*;

public class QuickSortThread extends AlgorithmThread
{
    public QuickSortThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public synchronized void backMode() {
        if (Logger.DEBUG) {
            System.out.println("in backMode fn (QuickSortThread)");
        }
        ((QuickSort)this.algorithm).resetRandomSeed();
        super.backMode();
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new QuickSort(this, data.copy());
    }
    
    public AlgorithmWindow getWindow() {
        return super.getAlgorithmWindow();
    }
    
    public void resetSeed() {
        ((QuickSort)this.algorithm).resetRandomSeed();
    }
}
