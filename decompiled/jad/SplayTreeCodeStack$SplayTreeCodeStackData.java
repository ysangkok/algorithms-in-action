// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeCodeStack.java

import java.awt.*;
import java.util.Hashtable;

private class setCode
{

    protected void setCode(String codeSection)
    {
        code = (String[])(String[])SplayTreeCodeStack.access$000().get(codeSection);
        if(code == null)
        {
            throw new RuntimeException((new StringBuilder()).append("SplayTreeCodeStack: key: `").append(codeSection).append("' not found").toString());
        } else
        {
            currentLineNumber = 0;
            return;
        }
    }

    protected void draw(Graphics g)
    {
        int screenBottom = position.y;
        int fontHeight = g.getFontMetrics().getHeight();
        if(nextOnStack != null)
        {
            nextOnStack.setPosition(new Point(position.x, screenBottom));
            nextOnStack.draw(g);
            screenBottom = nextOnStack.codeTop - 10;
            position.y = screenBottom;
        }
        for(int i = code.length - 1; i >= 0; i--)
        {
            if(g.getFontMetrics().stringWidth(code[i]) > codeBlockWidth)
                codeBlockWidth = g.getFontMetrics().stringWidth(code[i]);
            if(i == currentLineNumber)
            {
                if(this == SplayTreeCodeStack.access$100(SplayTreeCodeStack.this))
                    g.setColor(SplayTreeColors.CODE_STACK_ACTIVE_COLOR);
                else
                    g.setColor(SplayTreeColors.CODE_STACK_SUSPENDED_COLOR);
                g.fillRect(position.x, screenBottom - fontHeight, codeBlockWidth, fontHeight);
            }
            if(code[i].startsWith("//"))
                g.setColor(SplayTreeColors.CODE_STACK_COMMENT_COLOR);
            else
                g.setColor(SplayTreeColors.CODE_STACK_CODE_COLOR);
            g.drawString(code[i], position.x, screenBottom);
            screenBottom -= fontHeight;
        }

        codeTop = screenBottom;
        g.setColor(SplayTreeColors.CODE_STACK_BOX_COLOR);
        g.drawRect(position.x, screenBottom, codeBlockWidth, position.y - screenBottom);
    }

    protected void setPosition(Point position)
    {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    protected int getMaxWidth()
    {
        if(nextOnStack != null)
        {
            int maxWidth = nextOnStack.getMaxWidth();
            if(codeBlockWidth > maxWidth)
                return codeBlockWidth;
            else
                return maxWidth;
        } else
        {
            return codeBlockWidth;
        }
    }

    protected int currentLineNumber;
    protected codeBlockWidth nextOnStack;
    protected String code[];
    protected Point position;
    protected int codeTop;
    protected int codeBlockWidth;
    final SplayTreeCodeStack this$0;

    protected (String codeSection)
    {
        this$0 = SplayTreeCodeStack.this;
        super();
        position = new Point();
        setCode(codeSection);
    }
}
