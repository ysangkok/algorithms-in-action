import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class KMPCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = 496615175370539607L;
    protected KMPString theString1;
    protected KMPString theString2;
    protected KMPString testKMPString1;
    protected KMPString testKMPString2;
    protected KMPNextTable theNextTable;
    protected Node jPointer;
    protected Node iPointer;
    protected Node animNode;
    protected int kmpResult;
    protected int xBuffer;
    protected int yBuffer;
    protected int windowHeight;
    protected Font normalFont;
    protected Color textColor;
    protected KMP theKMP;
    
    public KMPCanvas() {
        super();
        this.xBuffer = 100;
        this.yBuffer = 10;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.theKMP == null) {
            return;
        }
        if (this.normalFont != null) {
            g.setFont(this.normalFont);
        }
        if (this.textColor != null) {
            g.setColor(this.textColor);
        }
        if (this.theString1 != null) {
            final String label1 = Messages.getString("KMPCanvas.0");
            g.drawString(label1, this.xBuffer - g.getFontMetrics().stringWidth(label1), this.yBuffer + 30);
            this.theString1.draw(g);
        }
        if (this.theString2 != null) {
            final String label2 = Messages.getString("KMPCanvas.1");
            g.drawString(label2, this.xBuffer - g.getFontMetrics().stringWidth(label2), this.yBuffer + 50);
            this.theString2.draw(g);
        }
        if (this.testKMPString1 != null) {
            final String label3 = Messages.getString("KMPCanvas.2");
            g.drawString(label3, this.xBuffer - g.getFontMetrics().stringWidth(label3), this.yBuffer + 90);
            this.testKMPString1.draw(g);
        }
        if (this.testKMPString2 != null) {
            final String label4 = Messages.getString("KMPCanvas.3");
            g.drawString(label4, this.xBuffer - g.getFontMetrics().stringWidth(label4), this.yBuffer + 110);
            this.testKMPString2.draw(g);
        }
        if (this.theNextTable != null) {
            final String label5 = Messages.getString("KMPCanvas.4");
            g.drawString(label5, this.xBuffer - g.getFontMetrics().stringWidth(label5), this.theNextTable.getY());
            this.theNextTable.draw(g);
        }
        if (this.jPointer != null) {
            final String label6 = "j = ";
            g.drawString(label6, this.xBuffer - g.getFontMetrics().stringWidth(label6), this.jPointer.getY() + g.getFontMetrics().getHeight());
            this.jPointer.draw(g);
        }
        if (this.iPointer != null) {
            final String label7 = "i = ";
            g.drawString(label7, this.xBuffer - g.getFontMetrics().stringWidth(label7), this.iPointer.getY() + g.getFontMetrics().getHeight());
            this.iPointer.draw(g);
        }
        if (this.animNode != null) {
            this.animNode.draw(g);
        }
        Color saveColor;
        if (this.kmpResult == 0) {
            saveColor = g.getColor();
            g.setColor(Color.red);
            g.drawString(Messages.getString("KMPCanvas.7"), this.xBuffer, 90);
            g.setColor(saveColor);
        }
        if (this.kmpResult == 1) {
            saveColor = g.getColor();
            g.setColor(Color.green);
            g.drawString(Messages.getString("KMPCanvas.8"), this.xBuffer, 90);
            g.setColor(saveColor);
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
        this.drawables.removeAllElements();
        this.theKMP = (KMP)((KMP)e.paramObj);
        this.theString1 = this.theKMP.getKMPString1();
        if (this.theString1 != null) {
            this.theString1.setPosition(this.xBuffer, this.yBuffer + 30);
        }
        this.theString2 = this.theKMP.getKMPString2();
        if (this.theString2 != null) {
            this.theString2.setPosition(this.xBuffer, this.yBuffer + 50);
        }
        this.testKMPString1 = this.theKMP.getTestKMPString1();
        if (this.testKMPString1 != null) {
            this.testKMPString1.setPosition(this.xBuffer, this.yBuffer + 90);
        }
        this.testKMPString2 = this.theKMP.getTestKMPString2();
        if (this.testKMPString2 != null) {
            this.testKMPString2.setPosition(this.xBuffer, this.yBuffer + 110);
        }
        this.kmpResult = this.theKMP.getKMPResult();
        this.jPointer = this.theKMP.getJPointer();
        if (this.jPointer != null) {
            if (this.testKMPString2 == null) {
                this.jPointer.setPosition(new Point(this.xBuffer, this.yBuffer + 90));
            }
            else {
                this.jPointer.setPosition(new Point(this.xBuffer, this.yBuffer + 160));
            }
        }
        this.iPointer = this.theKMP.getIPointer();
        if (this.iPointer != null) {
            if (this.testKMPString2 == null) {
                this.iPointer.setPosition(new Point(this.xBuffer, this.yBuffer + 110));
            }
            else {
                this.iPointer.setPosition(new Point(this.xBuffer, this.yBuffer + 130));
            }
        }
        this.theNextTable = this.theKMP.getNextTable();
        if (this.theNextTable != null) {
            if (this.testKMPString2 == null || this.iPointer == null) {
                this.theNextTable.setPosition(this.xBuffer, this.yBuffer + 140);
                this.windowHeight = this.yBuffer + 160 + this.theNextTable.getHeight();
            }
            else {
                this.theNextTable.setPosition(this.xBuffer, this.yBuffer + 200);
            }
        }
        this.animNode = this.theKMP.getAnimNode();
        this.addTween(this.theKMP.generateTweens(this, null, this.numberOfTweenSteps));
        if (this.tweens.getNumberOfTweens() > 0) {
            this.tweens.run();
        }
        this.theKMP.removeAllAnimationRequests();
        this.repaint();
    }
}
