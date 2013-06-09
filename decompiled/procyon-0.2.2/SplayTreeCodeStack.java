import localization.*;
import java.util.*;
import java.awt.*;

public class SplayTreeCodeStack
{
    private SplayTreeCodeStackData top;
    private Point position;
    private int width;
    private static Hashtable<K, V> codeHashtable;
    private static final String COMMENT = "//";
    public static final int gap = 10;
    private static final String[][][] allCode;
    
    public SplayTreeCodeStack() {
        super();
        this.position = new Point();
        this.top = null;
    }
    
    public void setCode(final String codeSection) {
        this.top.setCode(codeSection);
    }
    
    public void push(final String codeSection) {
        final SplayTreeCodeStackData newItem = new SplayTreeCodeStackData(codeSection);
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
            while (lineNumber < this.top.code.length && (this.top.code[lineNumber].length() == 0 || this.top.code[lineNumber].startsWith("//"))) {
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
            final String stackLabel = Messages.getString("SplayTreeCodeStack.47");
            g.setColor(SplayTreeColors.CODE_STACK_LABEL_COLOR);
            g.drawString(stackLabel, this.position.x - g.getFontMetrics().stringWidth(stackLabel) - 20, screenBottom - (screenBottom - this.position.y - g.getFontMetrics().getHeight()) / 2);
            g.drawLine(this.position.x - 10, this.position.y, this.position.x - 5, this.position.y);
            g.drawLine(this.position.x - 10, this.position.y, this.position.x - 10, screenBottom);
            g.drawLine(this.position.x - 10, screenBottom, this.position.x - 5, screenBottom);
        }
    }
    
    static {
        allCode = new String[][][] { { { Messages.getString("SplayTreeCodeStack.1") }, { Messages.getString("SplayTreeCodeStack.2"), Messages.getString("SplayTreeCodeStack.3"), Messages.getString("SplayTreeCodeStack.4"), Messages.getString("SplayTreeCodeStack.5") } }, { { Messages.getString("SplayTreeCodeStack.6") }, { Messages.getString("SplayTreeCodeStack.7"), Messages.getString("SplayTreeCodeStack.8"), "", Messages.getString("SplayTreeCodeStack.10") } }, { { Messages.getString("SplayTreeCodeStack.11") }, { Messages.getString("SplayTreeCodeStack.12"), Messages.getString("SplayTreeCodeStack.13"), Messages.getString("SplayTreeCodeStack.14"), Messages.getString("SplayTreeCodeStack.15") } }, { { Messages.getString("SplayTreeCodeStack.16") }, { Messages.getString("SplayTreeCodeStack.17"), Messages.getString("SplayTreeCodeStack.18"), Messages.getString("SplayTreeCodeStack.19"), Messages.getString("SplayTreeCodeStack.20") } }, { { Messages.getString("SplayTreeCodeStack.21") }, { Messages.getString("SplayTreeCodeStack.22"), Messages.getString("SplayTreeCodeStack.23"), Messages.getString("SplayTreeCodeStack.24"), Messages.getString("SplayTreeCodeStack.25") } }, { { Messages.getString("SplayTreeCodeStack.26") }, { Messages.getString("SplayTreeCodeStack.27"), Messages.getString("SplayTreeCodeStack.28"), Messages.getString("SplayTreeCodeStack.29"), Messages.getString("SplayTreeCodeStack.30") } }, { { Messages.getString("SplayTreeCodeStack.31") }, { Messages.getString("SplayTreeCodeStack.32"), Messages.getString("SplayTreeCodeStack.33"), "", Messages.getString("SplayTreeCodeStack.35") } }, { { Messages.getString("SplayTreeCodeStack.36") }, { Messages.getString("SplayTreeCodeStack.37"), Messages.getString("SplayTreeCodeStack.38"), Messages.getString("SplayTreeCodeStack.39"), Messages.getString("SplayTreeCodeStack.40") } }, { { Messages.getString("SplayTreeCodeStack.41") }, { Messages.getString("SplayTreeCodeStack.42"), Messages.getString("SplayTreeCodeStack.43"), Messages.getString("SplayTreeCodeStack.44"), Messages.getString("SplayTreeCodeStack.45") } } };
        SplayTreeCodeStack.codeHashtable = new Hashtable();
        for (int i = 0; i < SplayTreeCodeStack.allCode.length; ++i) {
            if (SplayTreeCodeStack.allCode[i].length != 2 && SplayTreeCodeStack.allCode[i][0].length != 1) {
                throw new RuntimeException("SplayTreeCodeStack: Invalid code");
            }
            SplayTreeCodeStack.codeHashtable.put(SplayTreeCodeStack.allCode[i][0][0], SplayTreeCodeStack.allCode[i][1]);
        }
    }
}
