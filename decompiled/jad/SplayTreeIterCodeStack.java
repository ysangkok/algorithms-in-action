// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeIterCodeStack.java

import java.awt.*;
import java.util.Hashtable;
import localization.Messages;

public class SplayTreeIterCodeStack
{
    private class SplayTreeIterCodeStackData
    {

        protected void setCode(String codeSection)
        {
            code = (String[])(String[])SplayTreeIterCodeStack.codeHashtable.get(codeSection);
            if(code == null)
            {
                throw new RuntimeException((new StringBuilder()).append(Messages.getString("SplayTreeIterCodeStack.71")).append(codeSection).append(Messages.getString("SplayTreeIterCodeStack.72")).toString());
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
                        g.setColor(SplayTreeIterColors.CODE_STACK_ACTIVE_COLOR);
                    else
                        g.setColor(SplayTreeIterColors.CODE_STACK_SUSPENDED_COLOR);
                    g.fillRect(position.x, screenBottom - fontHeight, codeBlockWidth, fontHeight);
                }
                if(code[i].startsWith(SplayTreeIterCodeStack.COMMENT))
                    g.setColor(SplayTreeIterColors.CODE_STACK_COMMENT_COLOR);
                else
                    g.setColor(SplayTreeIterColors.CODE_STACK_CODE_COLOR);
                g.drawString(code[i], position.x, screenBottom);
                screenBottom -= fontHeight;
            }

            codeTop = screenBottom;
            g.setColor(SplayTreeIterColors.CODE_STACK_BOX_COLOR);
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
        protected SplayTreeIterCodeStackData nextOnStack;
        protected String code[];
        protected Point position;
        protected int codeTop;
        protected int codeBlockWidth;
        final SplayTreeIterCodeStack this$0;

        protected SplayTreeIterCodeStackData(String codeSection)
        {
            this$0 = SplayTreeIterCodeStack.this;
            super();
            position = new Point();
            setCode(codeSection);
        }
    }


    public SplayTreeIterCodeStack()
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
        SplayTreeIterCodeStackData newItem = new SplayTreeIterCodeStackData(codeSection);
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
            for(lineNumber++; lineNumber < top.code.length && (top.code[lineNumber].length() == 0 || top.code[lineNumber].startsWith(COMMENT)); lineNumber++);
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
            String stackLabel = Messages.getString("SplayTreeIterCodeStack.70");
            g.setColor(SplayTreeIterColors.CODE_STACK_LABEL_COLOR);
            g.drawString(stackLabel, position.x - g.getFontMetrics().stringWidth(stackLabel) - 20, screenBottom - (screenBottom - position.y - g.getFontMetrics().getHeight()) / 2);
            g.drawLine(position.x - 10, position.y, position.x - 5, position.y);
            g.drawLine(position.x - 10, position.y, position.x - 10, screenBottom);
            g.drawLine(position.x - 10, screenBottom, position.x - 5, screenBottom);
        }
    }

    private SplayTreeIterCodeStackData top;
    private Point position;
    private int width;
    private static Hashtable codeHashtable;
    private static final String COMMENT = Messages.getString("SplayTreeIterCodeStack.0");
    public static final int gap = 10;
    private static final String allCode[][][] = {
        {
            {
                Messages.getString("SplayTreeIterCodeStack.1")
            }, {
                Messages.getString("SplayTreeIterCodeStack.2"), Messages.getString("SplayTreeIterCodeStack.3"), Messages.getString("SplayTreeIterCodeStack.4"), Messages.getString("SplayTreeIterCodeStack.5")
            }
        }, {
            {
                Messages.getString("SplayTreeIterCodeStack.6")
            }, {
                Messages.getString("SplayTreeIterCodeStack.7"), Messages.getString("SplayTreeIterCodeStack.8"), Messages.getString("SplayTreeIterCodeStack.9"), Messages.getString("SplayTreeIterCodeStack.10")
            }
        }, {
            {
                Messages.getString("SplayTreeIterCodeStack.11")
            }, {
                Messages.getString("SplayTreeIterCodeStack.12"), Messages.getString("SplayTreeIterCodeStack.13"), Messages.getString("SplayTreeIterCodeStack.14"), Messages.getString("SplayTreeIterCodeStack.15")
            }
        }, {
            {
                Messages.getString("SplayTreeIterCodeStack.16")
            }, {
                Messages.getString("SplayTreeIterCodeStack.17"), Messages.getString("SplayTreeIterCodeStack.18"), Messages.getString("SplayTreeIterCodeStack.19"), Messages.getString("SplayTreeIterCodeStack.20")
            }
        }, {
            {
                Messages.getString("SplayTreeIterCodeStack.21")
            }, {
                Messages.getString("SplayTreeIterCodeStack.22"), Messages.getString("SplayTreeIterCodeStack.23"), Messages.getString("SplayTreeIterCodeStack.24"), Messages.getString("SplayTreeIterCodeStack.25")
            }
        }, {
            {
                Messages.getString("SplayTreeIterCodeStack.26")
            }, {
                Messages.getString("SplayTreeIterCodeStack.27"), Messages.getString("SplayTreeIterCodeStack.28"), Messages.getString("SplayTreeIterCodeStack.29"), Messages.getString("SplayTreeIterCodeStack.30")
            }
        }, {
            {
                Messages.getString("SplayTreeIterCodeStack.31")
            }, {
                Messages.getString("SplayTreeIterCodeStack.32"), Messages.getString("SplayTreeIterCodeStack.33"), Messages.getString("SplayTreeIterCodeStack.34"), Messages.getString("SplayTreeIterCodeStack.35")
            }
        }, {
            {
                Messages.getString("SplayTreeIterCodeStack.36")
            }, {
                Messages.getString("SplayTreeIterCodeStack.37"), Messages.getString("SplayTreeIterCodeStack.38"), Messages.getString("SplayTreeIterCodeStack.39"), Messages.getString("SplayTreeIterCodeStack.40")
            }
        }, {
            {
                Messages.getString("SplayTreeIterCodeStack.41")
            }, {
                Messages.getString("SplayTreeIterCodeStack.42"), Messages.getString("SplayTreeIterCodeStack.43"), Messages.getString("SplayTreeIterCodeStack.44"), Messages.getString("SplayTreeIterCodeStack.45")
            }
        }
    };
    private static final String searchCode[] = {
        Messages.getString("SplayTreeIterCodeStack.46"), Messages.getString("SplayTreeIterCodeStack.47")
    };
    private static final String zigZigSearchCode[] = {
        Messages.getString("SplayTreeIterCodeStack.48"), Messages.getString("SplayTreeIterCodeStack.49"), Messages.getString("SplayTreeIterCodeStack.50"), Messages.getString("SplayTreeIterCodeStack.51")
    };
    private static final String zigZagSearchCode[] = {
        Messages.getString("SplayTreeIterCodeStack.52"), Messages.getString("SplayTreeIterCodeStack.53"), Messages.getString("SplayTreeIterCodeStack.54"), Messages.getString("SplayTreeIterCodeStack.55")
    };
    private static final String insertCode[] = {
        Messages.getString("SplayTreeIterCodeStack.56"), Messages.getString("SplayTreeIterCodeStack.57")
    };
    private static final String terminalCode[] = {
        Messages.getString("SplayTreeIterCodeStack.58"), Messages.getString("SplayTreeIterCodeStack.59"), Messages.getString("SplayTreeIterCodeStack.60")
    };
    private static final String zigZigCode[] = {
        Messages.getString("SplayTreeIterCodeStack.61"), Messages.getString("SplayTreeIterCodeStack.62"), Messages.getString("SplayTreeIterCodeStack.63"), Messages.getString("SplayTreeIterCodeStack.64")
    };
    private static final String zigZagCode[] = {
        Messages.getString("SplayTreeIterCodeStack.65"), Messages.getString("SplayTreeIterCodeStack.66"), Messages.getString("SplayTreeIterCodeStack.67"), Messages.getString("SplayTreeIterCodeStack.68")
    };

    static 
    {
        codeHashtable = new Hashtable();
        for(int i = 0; i < allCode.length; i++)
        {
            if(allCode[i].length != 2 && allCode[i][0].length != 1)
                throw new RuntimeException(Messages.getString("SplayTreeIterCodeStack.69"));
            codeHashtable.put(allCode[i][0][0], allCode[i][1]);
        }

    }



}
