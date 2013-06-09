import java.awt.*;

private class SplayTreeCodeStackData
{
    protected int currentLineNumber;
    protected SplayTreeCodeStackData nextOnStack;
    protected String[] code;
    protected Point position;
    protected int codeTop;
    protected int codeBlockWidth;
    
    protected SplayTreeCodeStackData(final String codeSection) {
        super();
        this.position = new Point();
        this.setCode(codeSection);
    }
    
    protected void setCode(final String codeSection) {
        this.code = (String[])((String[])SplayTreeCodeStack.access$000().get(codeSection));
        if (this.code == null) {
            throw new RuntimeException("SplayTreeCodeStack: key: `" + codeSection + "' not found");
        }
        this.currentLineNumber = 0;
    }
    
    protected void draw(final Graphics g) {
        int screenBottom = this.position.y;
        final int fontHeight = g.getFontMetrics().getHeight();
        if (this.nextOnStack != null) {
            this.nextOnStack.setPosition(new Point(this.position.x, screenBottom));
            this.nextOnStack.draw(g);
            screenBottom = this.nextOnStack.codeTop - 10;
            this.position.y = screenBottom;
        }
        for (int i = this.code.length - 1; i >= 0; --i) {
            if (g.getFontMetrics().stringWidth(this.code[i]) > this.codeBlockWidth) {
                this.codeBlockWidth = g.getFontMetrics().stringWidth(this.code[i]);
            }
            if (i == this.currentLineNumber) {
                if (this == SplayTreeCodeStack.access$100(SplayTreeCodeStack.this)) {
                    g.setColor(SplayTreeColors.CODE_STACK_ACTIVE_COLOR);
                }
                else {
                    g.setColor(SplayTreeColors.CODE_STACK_SUSPENDED_COLOR);
                }
                g.fillRect(this.position.x, screenBottom - fontHeight, this.codeBlockWidth, fontHeight);
            }
            if (this.code[i].startsWith("//")) {
                g.setColor(SplayTreeColors.CODE_STACK_COMMENT_COLOR);
            }
            else {
                g.setColor(SplayTreeColors.CODE_STACK_CODE_COLOR);
            }
            g.drawString(this.code[i], this.position.x, screenBottom);
            screenBottom = screenBottom - fontHeight;
        }
        this.codeTop = screenBottom;
        g.setColor(SplayTreeColors.CODE_STACK_BOX_COLOR);
        g.drawRect(this.position.x, screenBottom, this.codeBlockWidth, this.position.y - screenBottom);
    }
    
    protected void setPosition(final Point position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }
    
    protected int getMaxWidth() {
        if (this.nextOnStack == null) {
            return this.codeBlockWidth;
        }
        final int maxWidth = this.nextOnStack.getMaxWidth();
        if (this.codeBlockWidth > maxWidth) {
            return this.codeBlockWidth;
        }
        return maxWidth;
    }
}
