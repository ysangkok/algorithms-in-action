import com.cim.AIA.*;

public class AlignmentThread extends AlgorithmThread
{
    public AlignmentThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new Alignment(this, data.copy());
    }
    
    public AlgorithmWindow getWindow() {
        return super.getAlgorithmWindow();
    }
}
