import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class MergeSortCanvas extends AlgorithmCanvas
{
    protected Color textColor;
    protected int xBuffer;
    protected int yBuffer;
    protected int gapBetweenArrayAndMergeSort;
    protected int spacingBetweenInformationAndMergeSort;
    protected int boxWidth;
    protected int activeYBuffer;
    protected MergeSort mergeSort;
    protected MyElementArray elementArray;
    protected Vector<E> childElements;
    protected int iWidth;
    protected int jWidth;
    protected Point iPoint;
    protected Point jPoint;
    protected int pValue;
    protected boolean pValueReady;
    protected Point activeLeft;
    protected Point activeRight;
    protected Point oldIPoint;
    protected Point oldJPoint;
    protected boolean tweening;
    
    public MergeSortCanvas() {
        super();
        this.textColor = Color.black;
        this.xBuffer = 60;
        this.yBuffer = 80;
        this.gapBetweenArrayAndMergeSort = 120;
        this.spacingBetweenInformationAndMergeSort = 10;
        this.boxWidth = 20;
        this.activeYBuffer = 5;
        this.iWidth = 0;
        this.jWidth = 0;
        this.iPoint = null;
        this.jPoint = null;
        this.pValue = -1;
        this.pValueReady = false;
        this.activeLeft = null;
        this.activeRight = null;
        this.tweening = false;
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.mergeSort != null) {
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            final Graphics clippedG = g.create(0, 0, 600, 765);
            this.mergeSort.drawTree(new Point(this.xBuffer, this.yBuffer), clippedG);
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.mergeSort = (MergeSort)((MergeSort)e.paramObj);
            this.removeAllSelectables();
            this.mergeSort.removeAllAnimationRequests();
            this.tweening = true;
            this.tweening = false;
        }
        this.repaint();
    }
}
