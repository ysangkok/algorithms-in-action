import com.cim.AIA.*;

public class ShellSortThread extends AlgorithmThread
{
    public ShellSortThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new ShellSort(this, data.copy());
    }
    
    public AlgorithmWindow getWindow() {
        return super.getAlgorithmWindow();
    }
}
