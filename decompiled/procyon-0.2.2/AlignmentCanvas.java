import java.awt.*;
import com.cim.AIA.*;

public class AlignmentCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = 2132280409718461071L;
    protected AlignmentMinimum theMinimum;
    protected ElementArray string1EA;
    protected ElementArray string2EA;
    protected AlignmentKey theKey;
    protected AlignmentMatrix3D the3D;
    protected AlignmentMatrix tempMatrix;
    protected AlignmentMatrix aMatrix;
    protected AlignmentMatrix bMatrix;
    protected AlignmentMatrix cMatrix;
    protected int xBuffer;
    protected int yBuffer;
    protected int spacingBetweenArrays;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected Font normalFont;
    protected Color textColor;
    protected Alignment theAlignment;
    
    public AlignmentCanvas() {
        super();
        this.xBuffer = 100;
        this.yBuffer = 10;
        this.spacingBetweenArrays = 100;
        this.xTextBuffer = 10;
        this.yTextBuffer = 5;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.theAlignment == null) {
            return;
        }
        if (this.normalFont != null) {
            g.setFont(this.normalFont);
        }
        if (this.textColor != null) {
            g.setColor(this.textColor);
        }
        if (this.tempMatrix != null) {
            this.tempMatrix.draw(g);
        }
        if (this.aMatrix != null) {
            this.aMatrix.draw(g);
        }
        if (this.bMatrix != null) {
            this.bMatrix.draw(g);
        }
        if (this.cMatrix != null) {
            this.cMatrix.draw(g);
        }
        if (this.string1EA != null && this.string2EA != null) {
            this.string1EA.draw(g);
            this.string2EA.draw(g);
        }
        if (this.theMinimum != null) {
            this.theMinimum.draw(g);
        }
        if (this.theKey != null) {
            this.theKey.draw(g);
        }
        if (this.the3D != null) {
            this.the3D.draw(g);
        }
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR)) {
            this.textColor = colorSelection.getColor();
        }
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj == null) {
            return;
        }
        int windowWidth = this.getParentSize().width;
        int windowTop = 30;
        int maxWidth = 0;
        int matrixWidth = 0;
        final int X_GAP = 20;
        final int Y_GAP = 20;
        this.drawables.removeAllElements();
        this.removeAllSelectables();
        this.theAlignment = (Alignment)((Alignment)e.paramObj);
        this.tempMatrix = this.theAlignment.getMatrix();
        this.aMatrix = this.theAlignment.getAMatrix();
        this.bMatrix = this.theAlignment.getBMatrix();
        this.cMatrix = this.theAlignment.getCMatrix();
        this.string1EA = this.theAlignment.getString1EA();
        this.string2EA = this.theAlignment.getString2EA();
        this.theMinimum = this.theAlignment.getMinimum();
        this.theKey = this.theAlignment.getKey();
        this.the3D = this.theAlignment.get3D();
        if (this.tempMatrix != null) {
            maxWidth = this.tempMatrix.getWidth() + X_GAP;
        }
        if (this.aMatrix != null) {
            maxWidth = X_GAP + (this.aMatrix.getWidth() + X_GAP) * 3;
        }
        if (this.string1EA != null && this.string2EA != null) {
            if (this.string1EA.getWidth() > maxWidth) {
                maxWidth = this.string1EA.getWidth();
            }
            if (this.string2EA.getWidth() > maxWidth) {
                maxWidth = this.string2EA.getWidth();
            }
        }
        if (windowWidth < maxWidth) {
            windowWidth = maxWidth;
        }
        if (this.tempMatrix != null) {
            this.tempMatrix.setLocation((windowWidth - this.tempMatrix.getWidth()) / 2, windowTop);
            windowTop = windowTop + this.tempMatrix.getHeight();
            matrixWidth = this.tempMatrix.getWidth();
        }
        if (this.aMatrix != null) {
            this.aMatrix.setLocation(X_GAP, windowTop);
            matrixWidth = this.aMatrix.getWidth();
        }
        if (this.bMatrix != null) {
            this.bMatrix.setLocation(X_GAP + matrixWidth + X_GAP, windowTop);
            matrixWidth = this.bMatrix.getWidth();
        }
        if (this.cMatrix != null) {
            this.cMatrix.setLocation(X_GAP + (matrixWidth + X_GAP) * 2, windowTop);
            windowTop = windowTop + this.cMatrix.getHeight();
            matrixWidth = this.cMatrix.getWidth();
        }
        if (this.string1EA != null && this.string2EA != null) {
            windowTop = windowTop + Y_GAP;
            maxWidth = this.string1EA.getWidth();
            if (this.string2EA.getWidth() > maxWidth) {
                maxWidth = this.string2EA.getWidth();
            }
            this.string1EA.setLocation((windowWidth - maxWidth) / 2, windowTop);
            windowTop = windowTop + this.string1EA.getHeight();
            windowTop = windowTop + Y_GAP / 2;
            this.string2EA.setLocation((windowWidth - maxWidth) / 2, windowTop);
            windowTop = windowTop + this.string2EA.getHeight();
        }
        if (this.theMinimum != null) {
            windowTop = windowTop + Y_GAP;
            this.theMinimum.setLocation((windowWidth - matrixWidth) / 2, windowTop);
            windowTop = windowTop + this.theMinimum.getHeight();
        }
        if (this.theKey != null) {
            windowTop = windowTop + Y_GAP;
            this.theKey.setLocation((windowWidth - this.theKey.getWidth()) / 2, windowTop);
            if (this.the3D != null) {
                this.the3D.setLocation(20, windowTop);
                if (this.the3D.getHeight() == 0) {
                    windowTop += 300;
                }
                else {
                    windowTop = windowTop + this.the3D.getHeight();
                }
            }
            else {
                windowTop = windowTop + this.theKey.getHeight();
            }
        }
        this.setSize(windowWidth, windowTop + Y_GAP);
        if (this.tempMatrix != null) {
            this.addSelectable(this.tempMatrix);
        }
        if (this.aMatrix != null) {
            this.addSelectable(this.aMatrix);
        }
        if (this.bMatrix != null) {
            this.addSelectable(this.bMatrix);
        }
        if (this.cMatrix != null) {
            this.addSelectable(this.cMatrix);
        }
        this.repaint();
    }
}
