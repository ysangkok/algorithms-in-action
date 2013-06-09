import com.cim.AIA.*;

public class RedBlackTreeThread extends AlgorithmThread
{
    public RedBlackTreeThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super(data, algorithmWindow);
    }
    
    public Algorithm createAlgorithm(final Copyable data) {
        return new RedBlackTree(this, data.copy());
    }
    
    public void paintAndWait() {
        this.repaint();
        this.finish();
    }
}
