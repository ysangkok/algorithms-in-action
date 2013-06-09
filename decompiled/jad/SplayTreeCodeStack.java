// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeCodeStack.java

import java.awt.*;
import java.util.Hashtable;
import localization.Messages;

public class SplayTreeCodeStack
{
    private class SplayTreeCodeStackData
    {

        protected void setCode(String codeSection)
        {
            code = (String[])(String[])SplayTreeCodeStack.codeHashtable.get(codeSection);
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
                    if(this == top)
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
        protected SplayTreeCodeStackData nextOnStack;
        protected String code[];
        protected Point position;
        protected int codeTop;
        protected int codeBlockWidth;
        final SplayTreeCodeStack this$0;

        protected SplayTreeCodeStackData(String codeSection)
        {
            this$0 = SplayTreeCodeStack.this;
            super();
            position = new Point();
            setCode(codeSection);
        }
    }


    public SplayTreeCodeStack()
    {
        position = new Point();
        top = null;
    }

    public void setCode(String codeSection)
    {
        top.setCode(codeSection);
    }

    public void push(String codeSection)
    {
        SplayTreeCodeStackData newItem = new SplayTreeCodeStackData(codeSection);
        newItem.nextOnStack = top;
        top = newItem;
    }

    public boolean isEmpty()
    {
        return top == null;
    }

    public void pop()
    {
        top = top.nextOnStack;
    }

    private void setCurrentLineNumber(int currentLineNumber)
    {
        if(top != null)
            top.currentLineNumber = currentLineNumber;
    }

    public void stepCodeLine()
    {
        if(top != null)
        {
            int lineNumber = top.currentLineNumber;
            for(lineNumber++; lineNumber < top.code.length && (top.code[lineNumber].length() == 0 || top.code[lineNumber].startsWith("//")); lineNumber++);
            if(lineNumber == top.code.length)
                top.currentLineNumber = lineNumber - 1;
            else
                top.currentLineNumber = lineNumber;
        }
    }

    public void setPosition(Point position)
    {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public int getWidth()
    {
        if(top != null && width < top.getMaxWidth())
            width = top.getMaxWidth();
        return width;
    }

    public void draw(Graphics g)
    {
        int screenBottom = position.y;
        if(top != null)
        {
            top.setPosition(position);
            top.draw(g);
            if(top != null)
                screenBottom = top.codeTop;
        }
        if(top != null)
        {
            String stackLabel = Messages.getString("SplayTreeCodeStack.47");
            g.setColor(SplayTreeColors.CODE_STACK_LABEL_COLOR);
            g.drawString(stackLabel, position.x - g.getFontMetrics().stringWidth(stackLabel) - 20, screenBottom - (screenBottom - position.y - g.getFontMetrics().getHeight()) / 2);
            g.drawLine(position.x - 10, position.y, position.x - 5, position.y);
            g.drawLine(position.x - 10, position.y, position.x - 10, screenBottom);
            g.drawLine(position.x - 10, screenBottom, position.x - 5, screenBottom);
        }
    }

    private SplayTreeCodeStackData top;
    private Point position;
    private int width;
    private static Hashtable codeHashtable;
    private static final String COMMENT = "//";
    public static final int gap = 10;
    private static final String allCode[][][] = {
        {
            {
                Messages.getString("SplayTreeCodeStack.1")
            }, {
                Messages.getString("SplayTreeCodeStack.2"), Messages.getString("SplayTreeCodeStack.3"), Messages.getString("SplayTreeCodeStack.4"), Messages.getString("SplayTreeCodeStack.5")
            }
        }, {
            {
                Messages.getString("SplayTreeCodeStack.6")
            }, {
                Messages.getString("SplayTreeCodeStack.7"), Messages.getString("SplayTreeCodeStack.8"), "", Messages.getString("SplayTreeCodeStack.10")
            }
        }, {
            {
                Messages.getString("SplayTreeCodeStack.11")
            }, {
                Messages.getString("SplayTreeCodeStack.12"), Messages.getString("SplayTreeCodeStack.13"), Messages.getString("SplayTreeCodeStack.14"), Messages.getString("SplayTreeCodeStack.15")
            }
        }, {
            {
                Messages.getString("SplayTreeCodeStack.16")
            }, {
                Messages.getString("SplayTreeCodeStack.17"), Messages.getString("SplayTreeCodeStack.18"), Messages.getString("SplayTreeCodeStack.19"), Messages.getString("SplayTreeCodeStack.20")
            }
        }, {
            {
                Messages.getString("SplayTreeCodeStack.21")
            }, {
                Messages.getString("SplayTreeCodeStack.22"), Messages.getString("SplayTreeCodeStack.23"), Messages.getString("SplayTreeCodeStack.24"), Messages.getString("SplayTreeCodeStack.25")
            }
        }, {
            {
                Messages.getString("SplayTreeCodeStack.26")
            }, {
                Messages.getString("SplayTreeCodeStack.27"), Messages.getString("SplayTreeCodeStack.28"), Messages.getString("SplayTreeCodeStack.29"), Messages.getString("SplayTreeCodeStack.30")
            }
        }, {
            {
                Messages.getString("SplayTreeCodeStack.31")
            }, {
                Messages.getString("SplayTreeCodeStack.32"), Messages.getString("SplayTreeCodeStack.33"), "", Messages.getString("SplayTreeCodeStack.35")
            }
        }, {
            {
                Messages.getString("SplayTreeCodeStack.36")
            }, {
                Messages.getString("SplayTreeCodeStack.37"), Messages.getString("SplayTreeCodeStack.38"), Messages.getString("SplayTreeCodeStack.39"), Messages.getString("SplayTreeCodeStack.40")
            }
        }, {
            {
                Messages.getString("SplayTreeCodeStack.41")
            }, {
                Messages.getString("SplayTreeCodeStack.42"), Messages.getString("SplayTreeCodeStack.43"), Messages.getString("SplayTreeCodeStack.44"), Messages.getString("SplayTreeCodeStack.45")
            }
        }
    };

    static 
    {
        codeHashtable = new Hashtable();
        for(int i = 0; i < allCode.length; i++)
        {
            if(allCode[i].length != 2 && allCode[i][0].length != 1)
                throw new RuntimeException("SplayTreeCodeStack: Invalid code");
            codeHashtable.put(allCode[i][0][0], allCode[i][1]);
        }

    }


}
