import java.awt.*;
import com.cim.AIA.*;

public class AlignmentNode extends Node
{
    public static final Color DEFAULT_ARROW_COLOR;
    public static final Color DEFAULT_ARROW_HIGHLIGHT;
    public static final Color DEFAULT_ARRAY_HIGHLIGHT;
    public static final Color DEFAULT_ARRAY_ACTIVE;
    public static final Color DEFAULT_ARRAY_INACTIVE;
    public static final Color DEFAULT_ARRAY_DISABLE;
    private static Font originalFont;
    private static Font newFont;
    protected boolean traceUp;
    protected boolean highlightUp;
    protected boolean traceLeft;
    protected boolean highlightLeft;
    protected boolean traceDiag;
    protected boolean highlightDiag;
    protected boolean drawTraceBacks;
    protected boolean haveChoice;
    protected boolean traceA;
    protected boolean highlightA;
    protected boolean traceB;
    protected boolean highlightB;
    protected boolean traceC;
    protected boolean highlightC;
    protected Color arrayHighlightColor;
    protected Color arrayActiveColor;
    protected Color arrayInactiveColor;
    protected Color arrowHighlightColor;
    protected Color arrowColor;
    protected Color arrayDisabledColor;
    protected int bufferHeight;
    protected int bufferWidth;
    protected int arrowHeight;
    protected int arrowWidth;
    protected boolean isDisabled;
    protected Color saveColor;
    protected int fontSizeDelta;
    private int AWidth;
    private int BWidth;
    
    public AlignmentNode(final Object value, final int sequenceNumber) {
        this(value, sequenceNumber, 10, 10);
    }
    
    public AlignmentNode(final Object value, final int sequenceNumber, final int aWidth, final int aHeight) {
        super(value, sequenceNumber);
        this.AWidth = 0;
        this.BWidth = 0;
        this.traceUp = false;
        this.highlightUp = false;
        this.traceLeft = false;
        this.highlightLeft = false;
        this.traceDiag = false;
        this.highlightDiag = false;
        this.traceA = false;
        this.highlightA = false;
        this.traceB = false;
        this.highlightB = false;
        this.traceC = false;
        this.highlightC = false;
        this.arrowHeight = aHeight;
        this.arrowWidth = aWidth;
        this.drawTraceBacks = true;
        this.arrowHighlightColor = AlignmentNode.DEFAULT_ARROW_HIGHLIGHT;
        this.arrowColor = AlignmentNode.DEFAULT_ARROW_COLOR;
        this.arrayHighlightColor = AlignmentNode.DEFAULT_ARRAY_HIGHLIGHT;
        this.arrayActiveColor = AlignmentNode.DEFAULT_ARRAY_ACTIVE;
        this.arrayInactiveColor = AlignmentNode.DEFAULT_ARRAY_INACTIVE;
        this.arrayDisabledColor = AlignmentNode.DEFAULT_ARRAY_DISABLE;
        this.isDisabled = false;
        this.bufferHeight = 0;
        this.bufferWidth = 0;
        this.haveChoice = false;
        this.fontSizeDelta = 4;
        this.saveColor = this.backgroundColor;
    }
    
    public void draw(final Graphics g) {
        final int tempWidth = this.arrowWidth;
        final int tempHeight = this.arrowHeight;
        this.arrowWidth = 0;
        this.arrowHeight = 0;
        final Point pos = this.pos;
        pos.x = pos.x + tempWidth;
        final Point pos2 = this.pos;
        pos2.y = pos2.y + tempHeight;
        if (!this.isVisible) {
            return;
        }
        final float[] hsb = Color.RGBtoHSB(this.backgroundColor.getRed(), this.backgroundColor.getGreen(), this.backgroundColor.getBlue(), null);
        if ((double)hsb[2] < 0.65) {
            this.setTextColor(Color.white);
        }
        else {
            this.setTextColor(Color.black);
        }
        super.draw(g);
        final int midX = this.pos.x + this.getWidth() / 2;
        final int midY = this.pos.y + this.getHeight() / 2;
        if (AlignmentNode.originalFont == null) {
            AlignmentNode.originalFont = new Font(g.getFont().getName(), g.getFont().getStyle(), g.getFont().getSize());
        }
        if (AlignmentNode.newFont == null) {
            AlignmentNode.newFont = new Font(g.getFont().getName(), g.getFont().getStyle(), g.getFont().getSize() - this.fontSizeDelta);
        }
        g.setFont(AlignmentNode.newFont);
        if (this.AWidth == 0) {
            this.AWidth = g.getFontMetrics().stringWidth("A");
        }
        if (this.BWidth == 0) {
            this.BWidth = g.getFontMetrics().stringWidth("B");
        }
        if (this.haveChoice) {
            if (this.highlightA) {
                g.setColor(this.arrayHighlightColor);
            }
            else if (this.traceA) {
                g.setColor(this.arrayActiveColor);
            }
            else {
                g.setColor(this.arrayInactiveColor);
            }
            g.drawString("A", this.pos.x + this.AWidth / 2, this.pos.y);
            if (this.highlightB) {
                g.setColor(this.arrayHighlightColor);
            }
            else if (this.traceB) {
                g.setColor(this.arrayActiveColor);
            }
            else {
                g.setColor(this.arrayInactiveColor);
            }
            g.drawString("B", this.pos.x + this.AWidth / 2 + this.AWidth, this.pos.y);
            if (this.highlightC) {
                g.setColor(this.arrayHighlightColor);
            }
            else if (this.traceC) {
                g.setColor(this.arrayActiveColor);
            }
            else {
                g.setColor(this.arrayInactiveColor);
            }
            g.drawString("C", this.pos.x + this.AWidth / 2 + this.AWidth + this.BWidth, this.pos.y);
        }
        g.setColor(Color.black);
        g.setFont(AlignmentNode.originalFont);
        if (this.drawTraceBacks) {
            if (this.traceUp) {
                final Line tUp = new Line(midX, this.pos.y - this.bufferHeight, midX, this.pos.y - tempHeight - this.bufferHeight);
                if (this.highlightUp) {
                    tUp.setColor(this.arrowHighlightColor);
                }
                else {
                    tUp.setColor(this.arrowColor);
                }
                tUp.showArrowHead(true);
                tUp.setDistanceFromStartForArrowHead(-3);
                tUp.draw(g);
            }
            if (this.traceLeft) {
                final Line tLeft = new Line(this.pos.x - this.bufferWidth, midY, this.pos.x - tempWidth - this.bufferWidth, midY);
                if (this.highlightLeft) {
                    tLeft.setColor(this.arrowHighlightColor);
                }
                else {
                    tLeft.setColor(this.arrowColor);
                }
                tLeft.showArrowHead(true);
                tLeft.setDistanceFromStartForArrowHead(-3);
                tLeft.draw(g);
            }
            if (this.traceDiag) {
                final Line tDiag = new Line(this.pos.x, this.pos.y, this.pos.x - tempWidth - this.bufferWidth, this.pos.y - tempHeight - this.bufferHeight);
                if (this.highlightDiag) {
                    tDiag.setColor(this.arrowHighlightColor);
                }
                else {
                    tDiag.setColor(this.arrowColor);
                }
                tDiag.showArrowHead(true);
                tDiag.setDistanceFromStartForArrowHead(-3);
                tDiag.draw(g);
            }
        }
        final Point pos3 = this.pos;
        pos3.x = pos3.x - tempWidth;
        final Point pos4 = this.pos;
        pos4.y = pos4.y - tempHeight;
        this.arrowWidth = tempWidth;
        this.arrowHeight = tempHeight;
    }
    
    public Color getArrayActiveColor() {
        return this.arrayActiveColor;
    }
    
    public Color getArrayHighlightColor() {
        return this.arrayHighlightColor;
    }
    
    public Color getArrayInactiveColor() {
        return this.arrayInactiveColor;
    }
    
    public Color getArrowColor() {
        return this.arrowColor;
    }
    
    public int getArrowHeight() {
        return this.arrowHeight;
    }
    
    public Color getArrowHighlightColor() {
        return this.arrowHighlightColor;
    }
    
    public int getArrowWidth() {
        return this.arrowWidth;
    }
    
    public int getBufferHeight() {
        return this.bufferHeight;
    }
    
    public int getBufferWidth() {
        return this.bufferWidth;
    }
    
    public boolean getDrawTraceBacks() {
        return this.drawTraceBacks;
    }
    
    public int getHeight() {
        return super.getHeight() + this.arrowHeight;
    }
    
    public int getRealHeight() {
        return super.getHeight() + this.arrowHeight + this.bufferHeight;
    }
    
    public int getRealWidth() {
        return super.getWidth() + this.arrowWidth + this.bufferWidth;
    }
    
    public boolean getTraceA() {
        return this.traceA;
    }
    
    public boolean getTraceB() {
        return this.traceB;
    }
    
    public boolean getTraceC() {
        return this.traceC;
    }
    
    public boolean getTraceDiag() {
        return this.traceDiag;
    }
    
    public boolean getTraceLeft() {
        return this.traceLeft;
    }
    
    public boolean getTraceUp() {
        return this.traceUp;
    }
    
    public int getWidth() {
        return super.getWidth() + this.arrowWidth;
    }
    
    public void highlightA() {
        this.highlightA = true;
    }
    
    public void highlightB() {
        this.highlightB = true;
    }
    
    public void highlightC() {
        this.highlightC = true;
    }
    
    public void highlightDiag() {
        this.highlightDiag = true;
    }
    
    public void highlightLeft() {
        this.highlightLeft = true;
    }
    
    public void highlightUp() {
        this.highlightUp = true;
    }
    
    public void setArrayActiveColor(final Color c) {
        this.arrayActiveColor = c;
    }
    
    public void setArrayHighlightColor(final Color c) {
        this.arrayHighlightColor = c;
    }
    
    public void setArrayInactiveColor(final Color c) {
        this.arrayInactiveColor = c;
    }
    
    public void setArrowColor(final Color c) {
        this.arrowColor = c;
    }
    
    public void setArrowHeight(final int x) {
        this.arrowHeight = x;
    }
    
    public void setArrowHighlightColor(final Color c) {
        this.arrowHighlightColor = c;
    }
    
    public void setArrowWidth(final int x) {
        this.arrowWidth = x;
    }
    
    public void setBackgroundColor(final Color color) {
        super.setBackgroundColor(color);
    }
    
    public void setBufferHeight(final int x) {
        this.bufferHeight = x;
    }
    
    public void setBufferWidth(final int x) {
        this.bufferWidth = x;
    }
    
    public void setDrawTraceBacks(final boolean state) {
        this.drawTraceBacks = state;
    }
    
    public void setFontSizeDelta(final int c) {
        this.fontSizeDelta = c;
    }
    
    public void setHaveChoice(final boolean state) {
        this.haveChoice = state;
    }
    
    public void setIsDisabled(final boolean state) {
        this.isDisabled = state;
        if (state == true) {
            this.saveColor = this.backgroundColor;
            this.setBackgroundColor(this.arrayDisabledColor);
        }
        else {
            this.setBackgroundColor(this.saveColor);
        }
    }
    
    public void setTraceA(final boolean state) {
        this.traceA = state;
    }
    
    public void setTraceB(final boolean state) {
        this.traceB = state;
    }
    
    public void setTraceC(final boolean state) {
        this.traceC = state;
    }
    
    public void setTraceDiag(final boolean state) {
        this.traceDiag = state;
    }
    
    public void setTraceLeft(final boolean state) {
        this.traceLeft = state;
    }
    
    public void setTraceUp(final boolean state) {
        this.traceUp = state;
    }
    
    public void unHighlightA() {
        this.highlightA = false;
    }
    
    public void unHighlightB() {
        this.highlightB = false;
    }
    
    public void unHighlightC() {
        this.highlightC = false;
    }
    
    public void unHighlightDiag() {
        this.highlightDiag = false;
    }
    
    public void unHighlightLeft() {
        this.highlightLeft = false;
    }
    
    public void unHighlightUp() {
        this.highlightUp = false;
    }
    
    static {
        DEFAULT_ARROW_COLOR = Color.black;
        DEFAULT_ARROW_HIGHLIGHT = Color.red;
        DEFAULT_ARRAY_HIGHLIGHT = Color.red;
        DEFAULT_ARRAY_ACTIVE = Color.black;
        DEFAULT_ARRAY_INACTIVE = Color.lightGray;
        DEFAULT_ARRAY_DISABLE = Color.gray;
        AlignmentNode.originalFont = null;
        AlignmentNode.newFont = null;
    }
}
