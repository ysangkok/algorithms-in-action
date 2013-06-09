import localization.*;
import java.util.*;
import java.awt.*;

public class SplayTreeIterCodeStack
{
    private SplayTreeIterCodeStackData top;
    private Point position;
    private int width;
    private static Hashtable<K, V> codeHashtable;
    private static final String COMMENT;
    public static final int gap = 10;
    private static final String[][][] allCode;
    private static final String[] searchCode;
    private static final String[] zigZigSearchCode;
    private static final String[] zigZagSearchCode;
    private static final String[] insertCode;
    private static final String[] terminalCode;
    private static final String[] zigZigCode;
    private static final String[] zigZagCode;
    
    public SplayTreeIterCodeStack() {
        super();
        this.position = new Point();
        this.top = null;
    }
    
    public void setCode(final String codeSection) {
        this.top.setCode(codeSection);
    }
    
    public void push(final String codeSection) {
        final SplayTreeIterCodeStackData newItem = new SplayTreeIterCodeStackData(codeSection);
        newItem.nextOnStack = this.top;
        this.top = newItem;
    }
    
    public boolean isEmpty() {
        return this.top == null;
    }
    
    public void pop() {
        this.top = this.top.nextOnStack;
    }
    
    private void setCurrentLineNumber(final int currentLineNumber) {
        if (this.top != null) {
            this.top.currentLineNumber = currentLineNumber;
        }
    }
    
    public void stepCodeLine() {
        if (this.top != null) {
            int lineNumber = this.top.currentLineNumber;
            ++lineNumber;
            while (lineNumber < this.top.code.length && (this.top.code[lineNumber].length() == 0 || this.top.code[lineNumber].startsWith(SplayTreeIterCodeStack.COMMENT))) {
                ++lineNumber;
            }
            if (lineNumber == this.top.code.length) {
                this.top.currentLineNumber = lineNumber - 1;
            }
            else {
                this.top.currentLineNumber = lineNumber;
            }
        }
    }
    
    public void setPosition(final Point position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }
    
    public int getWidth() {
        if (this.top != null && this.width < this.top.getMaxWidth()) {
            this.width = this.top.getMaxWidth();
        }
        return this.width;
    }
    
    public void draw(final Graphics g) {
        int screenBottom = this.position.y;
        if (this.top != null) {
            this.top.setPosition(this.position);
            this.top.draw(g);
            if (this.top != null) {
                screenBottom = this.top.codeTop;
            }
        }
        if (this.top != null) {
            final String stackLabel = Messages.getString("SplayTreeIterCodeStack.70");
            g.setColor(SplayTreeIterColors.CODE_STACK_LABEL_COLOR);
            g.drawString(stackLabel, this.position.x - g.getFontMetrics().stringWidth(stackLabel) - 20, screenBottom - (screenBottom - this.position.y - g.getFontMetrics().getHeight()) / 2);
            g.drawLine(this.position.x - 10, this.position.y, this.position.x - 5, this.position.y);
            g.drawLine(this.position.x - 10, this.position.y, this.position.x - 10, screenBottom);
            g.drawLine(this.position.x - 10, screenBottom, this.position.x - 5, screenBottom);
        }
    }
    
    static {
        COMMENT = Messages.getString("SplayTreeIterCodeStack.0");
        allCode = new String[][][] { { { Messages.getString("SplayTreeIterCodeStack.1") }, { Messages.getString("SplayTreeIterCodeStack.2"), Messages.getString("SplayTreeIterCodeStack.3"), Messages.getString("SplayTreeIterCodeStack.4"), Messages.getString("SplayTreeIterCodeStack.5") } }, { { Messages.getString("SplayTreeIterCodeStack.6") }, { Messages.getString("SplayTreeIterCodeStack.7"), Messages.getString("SplayTreeIterCodeStack.8"), Messages.getString("SplayTreeIterCodeStack.9"), Messages.getString("SplayTreeIterCodeStack.10") } }, { { Messages.getString("SplayTreeIterCodeStack.11") }, { Messages.getString("SplayTreeIterCodeStack.12"), Messages.getString("SplayTreeIterCodeStack.13"), Messages.getString("SplayTreeIterCodeStack.14"), Messages.getString("SplayTreeIterCodeStack.15") } }, { { Messages.getString("SplayTreeIterCodeStack.16") }, { Messages.getString("SplayTreeIterCodeStack.17"), Messages.getString("SplayTreeIterCodeStack.18"), Messages.getString("SplayTreeIterCodeStack.19"), Messages.getString("SplayTreeIterCodeStack.20") } }, { { Messages.getString("SplayTreeIterCodeStack.21") }, { Messages.getString("SplayTreeIterCodeStack.22"), Messages.getString("SplayTreeIterCodeStack.23"), Messages.getString("SplayTreeIterCodeStack.24"), Messages.getString("SplayTreeIterCodeStack.25") } }, { { Messages.getString("SplayTreeIterCodeStack.26") }, { Messages.getString("SplayTreeIterCodeStack.27"), Messages.getString("SplayTreeIterCodeStack.28"), Messages.getString("SplayTreeIterCodeStack.29"), Messages.getString("SplayTreeIterCodeStack.30") } }, { { Messages.getString("SplayTreeIterCodeStack.31") }, { Messages.getString("SplayTreeIterCodeStack.32"), Messages.getString("SplayTreeIterCodeStack.33"), Messages.getString("SplayTreeIterCodeStack.34"), Messages.getString("SplayTreeIterCodeStack.35") } }, { { Messages.getString("SplayTreeIterCodeStack.36") }, { Messages.getString("SplayTreeIterCodeStack.37"), Messages.getString("SplayTreeIterCodeStack.38"), Messages.getString("SplayTreeIterCodeStack.39"), Messages.getString("SplayTreeIterCodeStack.40") } }, { { Messages.getString("SplayTreeIterCodeStack.41") }, { Messages.getString("SplayTreeIterCodeStack.42"), Messages.getString("SplayTreeIterCodeStack.43"), Messages.getString("SplayTreeIterCodeStack.44"), Messages.getString("SplayTreeIterCodeStack.45") } } };
        searchCode = new String[] { Messages.getString("SplayTreeIterCodeStack.46"), Messages.getString("SplayTreeIterCodeStack.47") };
        zigZigSearchCode = new String[] { Messages.getString("SplayTreeIterCodeStack.48"), Messages.getString("SplayTreeIterCodeStack.49"), Messages.getString("SplayTreeIterCodeStack.50"), Messages.getString("SplayTreeIterCodeStack.51") };
        zigZagSearchCode = new String[] { Messages.getString("SplayTreeIterCodeStack.52"), Messages.getString("SplayTreeIterCodeStack.53"), Messages.getString("SplayTreeIterCodeStack.54"), Messages.getString("SplayTreeIterCodeStack.55") };
        insertCode = new String[] { Messages.getString("SplayTreeIterCodeStack.56"), Messages.getString("SplayTreeIterCodeStack.57") };
        terminalCode = new String[] { Messages.getString("SplayTreeIterCodeStack.58"), Messages.getString("SplayTreeIterCodeStack.59"), Messages.getString("SplayTreeIterCodeStack.60") };
        zigZigCode = new String[] { Messages.getString("SplayTreeIterCodeStack.61"), Messages.getString("SplayTreeIterCodeStack.62"), Messages.getString("SplayTreeIterCodeStack.63"), Messages.getString("SplayTreeIterCodeStack.64") };
        zigZagCode = new String[] { Messages.getString("SplayTreeIterCodeStack.65"), Messages.getString("SplayTreeIterCodeStack.66"), Messages.getString("SplayTreeIterCodeStack.67"), Messages.getString("SplayTreeIterCodeStack.68") };
        SplayTreeIterCodeStack.codeHashtable = new Hashtable();
        for (int i = 0; i < SplayTreeIterCodeStack.allCode.length; ++i) {
            if (SplayTreeIterCodeStack.allCode[i].length != 2 && SplayTreeIterCodeStack.allCode[i][0].length != 1) {
                throw new RuntimeException(Messages.getString("SplayTreeIterCodeStack.69"));
            }
            SplayTreeIterCodeStack.codeHashtable.put(SplayTreeIterCodeStack.allCode[i][0][0], SplayTreeIterCodeStack.allCode[i][1]);
        }
    }
}
