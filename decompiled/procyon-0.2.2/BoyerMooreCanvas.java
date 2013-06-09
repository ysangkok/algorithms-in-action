import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class BoyerMooreCanvas extends AlgorithmCanvas
{
    private static final long serialVersionUID = -2145781309980138119L;
    public static final int xBuffer = 100;
    protected BoyerMooreString theString1;
    protected BoyerMooreString theString2;
    protected BoyerMooreString testBoyerMooreString1;
    protected BoyerMooreString testBoyerMooreString2;
    protected BoyerMooreNextTable theNextTable;
    protected BoyerMooreSkipTable theSkipTable;
    protected String skipTableS;
    protected Node skipTableN;
    protected Node patternN;
    protected Node mi1N;
    protected Node skipN;
    protected int BoyerMooreResult;
    protected ElementArray dataElementArray;
    protected int yBuffer;
    protected int windowHeight;
    protected int windowWidth;
    protected Font normalFont;
    protected Color textColor;
    protected BoyerMoore theBoyerMoore;
    
    public BoyerMooreCanvas() {
        super();
        this.yBuffer = 10;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.theBoyerMoore == null) {
            return;
        }
        if (this.normalFont != null) {
            g.setFont(this.normalFont);
        }
        if (this.textColor != null) {
            g.setColor(this.textColor);
        }
        if (this.theString1 != null) {
            final String label1 = Messages.getString("BoyerMooreCanvas.0");
            g.drawString(label1, 100 - g.getFontMetrics().stringWidth(label1), this.yBuffer + 40);
            this.theString1.draw(g);
        }
        if (this.theString2 != null) {
            final String label2 = Messages.getString("BoyerMooreCanvas.1");
            g.drawString(label2, 100 - g.getFontMetrics().stringWidth(label2), this.yBuffer + 60);
            this.theString2.draw(g);
        }
        if (this.testBoyerMooreString1 != null) {
            final String label3 = Messages.getString("BoyerMooreCanvas.2");
            g.drawString(label3, 100 - g.getFontMetrics().stringWidth(label3), this.yBuffer + 90);
            this.testBoyerMooreString1.draw(g);
        }
        if (this.testBoyerMooreString2 != null) {
            final String label4 = Messages.getString("BoyerMooreCanvas.3");
            g.drawString(label4, 100 - g.getFontMetrics().stringWidth(label4), this.yBuffer + 110);
            this.testBoyerMooreString2.draw(g);
        }
        if (this.theNextTable != null) {
            final String label5 = Messages.getString("BoyerMooreCanvas.4");
            g.drawString(label5, 100 - g.getFontMetrics().stringWidth(label5), this.yBuffer + 140);
            this.theNextTable.draw(g);
        }
        if (this.theSkipTable != null && this.theNextTable != null) {
            final String label6 = Messages.getString("BoyerMooreCanvas.5");
            g.drawString(label6, 100 + this.theNextTable.getWidth() + 30, this.yBuffer + 140);
            this.theSkipTable.draw(g);
        }
        else if (this.theSkipTable != null) {
            final String label7 = Messages.getString("BoyerMooreCanvas.6");
            g.drawString(label7, 100 - g.getFontMetrics().stringWidth(label7), this.yBuffer + 140);
            this.theSkipTable.draw(g);
        }
        if (this.skipTableN != null) {
            final String label8 = Messages.getString("BoyerMooreCanvas.7") + this.skipTableS + "]";
            g.drawString(label8, 100, this.skipTableN.getPosition().y + this.skipTableN.getHeight());
            this.skipTableN.draw(g);
        }
        if (this.patternN != null) {
            final String label9 = Messages.getString("BoyerMooreCanvas.9");
            g.drawString(label9, 100, this.patternN.getPosition().y + this.patternN.getHeight());
            this.patternN.draw(g);
        }
        if (this.skipN != null) {
            final String label10 = Messages.getString("BoyerMooreCanvas.10");
            g.drawString(label10, 100, this.yBuffer + 105);
            this.skipN.draw(g);
        }
        if (this.mi1N != null) {
            final String label11 = Messages.getString("BoyerMooreCanvas.11");
            g.drawString(label11, 100, this.yBuffer + 105);
            this.mi1N.draw(g);
        }
        Color saveColor;
        if (this.BoyerMooreResult == 0) {
            saveColor = g.getColor();
            g.setColor(Color.red);
            g.drawString(Messages.getString("BoyerMooreCanvas.12"), 100, 90);
            g.setColor(saveColor);
        }
        if (this.BoyerMooreResult == 1) {
            saveColor = g.getColor();
            g.setColor(Color.green);
            g.drawString(Messages.getString("BoyerMooreCanvas.13"), 100, 90);
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
        this.theBoyerMoore = (BoyerMoore)((BoyerMoore)e.paramObj);
        this.theString1 = this.theBoyerMoore.getBoyerMooreString1();
        if (this.theString1 != null) {
            this.theString1.setPosition(100, this.yBuffer + 40);
            this.windowWidth = 100 + this.theString1.getWidth();
        }
        this.theString2 = this.theBoyerMoore.getBoyerMooreString2();
        if (this.theString2 != null) {
            this.theString2.setPosition(100, this.yBuffer + 60);
            final int temp1 = 100 + this.theString2.getWidth();
            if (temp1 > this.windowWidth) {
                this.windowWidth = temp1;
            }
        }
        this.theNextTable = this.theBoyerMoore.getNextTable();
        if (this.theNextTable != null) {
            this.theNextTable.setPosition(100, this.yBuffer + 140);
            this.windowHeight = this.yBuffer + 160 + this.theNextTable.getHeight();
        }
        this.theSkipTable = this.theBoyerMoore.getSkipTable();
        if (this.theSkipTable != null && this.theNextTable != null) {
            this.theSkipTable.setPosition(100 + this.theNextTable.getWidth() + 30, this.yBuffer + 140);
            this.windowHeight = this.yBuffer + 160 + this.theSkipTable.getHeight();
        }
        else if (this.theSkipTable != null) {
            this.theSkipTable.setPosition(100, this.yBuffer + 140);
            this.windowHeight = this.yBuffer + 160 + this.theSkipTable.getHeight();
        }
        this.testBoyerMooreString1 = this.theBoyerMoore.getTestBoyerMooreString1();
        if (this.testBoyerMooreString1 != null) {
            this.testBoyerMooreString1.setPosition(100, this.yBuffer + 90);
        }
        this.testBoyerMooreString2 = this.theBoyerMoore.getTestBoyerMooreString2();
        if (this.testBoyerMooreString2 != null) {
            this.testBoyerMooreString2.setPosition(100, this.yBuffer + 110);
        }
        this.skipTableN = this.theBoyerMoore.getSkipTableN();
        if (this.skipTableN != null) {
            this.skipTableN.setPosition(new Point(220, this.windowHeight));
            this.windowHeight = this.windowHeight + this.skipTableN.getHeight();
            this.skipTableS = this.theBoyerMoore.getSkipTableS();
        }
        this.patternN = this.theBoyerMoore.getPatternN();
        if (this.patternN != null) {
            this.patternN.setPosition(new Point(220, this.windowHeight));
            this.windowHeight = this.windowHeight + this.patternN.getHeight();
        }
        this.skipN = this.theBoyerMoore.getSkipN();
        if (this.skipN != null) {
            this.skipN.setPosition(new Point(140, this.yBuffer + 90));
        }
        this.mi1N = this.theBoyerMoore.getMi1N();
        if (this.mi1N != null) {
            this.mi1N.setPosition(new Point(240, this.yBuffer + 90));
        }
        this.BoyerMooreResult = this.theBoyerMoore.getBoyerMooreResult();
        this.addTween(this.theBoyerMoore.generateTweens(this, null, this.numberOfTweenSteps));
        if (this.tweens.getNumberOfTweens() > 0) {
            this.tweens.run();
        }
        this.theBoyerMoore.removeAllAnimationRequests();
        this.setSize(this.windowWidth, this.windowHeight);
        this.repaint();
    }
}
