import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class MergeBUSortCanvas extends AlgorithmCanvas
{
    protected Color textColor;
    protected int xBuffer;
    protected int yBuffer;
    protected int gapBetweenArrayAndMergeBUSort;
    protected int spacingBetweenInformationAndMergeBUSort;
    protected int boxWidth;
    protected int activeYBuffer;
    protected MergeBUSort mergeBUSort;
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
    
    public MergeBUSortCanvas() {
        super();
        this.textColor = Color.black;
        this.xBuffer = 60;
        this.yBuffer = 80;
        this.gapBetweenArrayAndMergeBUSort = 120;
        this.spacingBetweenInformationAndMergeBUSort = 10;
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
        if (this.mergeBUSort != null) {
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            final Graphics clippedG = g.create(0, 0, 600, 765);
            this.mergeBUSort.drawTree(new Point(this.xBuffer, this.yBuffer), clippedG);
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            this.mergeBUSort = (MergeBUSort)((MergeBUSort)e.paramObj);
            this.removeAllSelectables();
            this.mergeBUSort.removeAllAnimationRequests();
            this.tweening = true;
            this.tweening = false;
        }
        this.repaint();
    }
}
