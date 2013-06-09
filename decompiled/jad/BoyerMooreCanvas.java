// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BoyerMooreCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class BoyerMooreCanvas extends AlgorithmCanvas
{

    public BoyerMooreCanvas()
    {
        yBuffer = 10;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(theBoyerMoore == null)
            return;
        if(normalFont != null)
            g.setFont(normalFont);
        if(textColor != null)
            g.setColor(textColor);
        if(theString1 != null)
        {
            String label1 = Messages.getString("BoyerMooreCanvas.0");
            g.drawString(label1, 100 - g.getFontMetrics().stringWidth(label1), yBuffer + 40);
            theString1.draw(g);
        }
        if(theString2 != null)
        {
            String label2 = Messages.getString("BoyerMooreCanvas.1");
            g.drawString(label2, 100 - g.getFontMetrics().stringWidth(label2), yBuffer + 60);
            theString2.draw(g);
        }
        if(testBoyerMooreString1 != null)
        {
            String label3 = Messages.getString("BoyerMooreCanvas.2");
            g.drawString(label3, 100 - g.getFontMetrics().stringWidth(label3), yBuffer + 90);
            testBoyerMooreString1.draw(g);
        }
        if(testBoyerMooreString2 != null)
        {
            String label4 = Messages.getString("BoyerMooreCanvas.3");
            g.drawString(label4, 100 - g.getFontMetrics().stringWidth(label4), yBuffer + 110);
            testBoyerMooreString2.draw(g);
        }
        if(theNextTable != null)
        {
            String label5 = Messages.getString("BoyerMooreCanvas.4");
            g.drawString(label5, 100 - g.getFontMetrics().stringWidth(label5), yBuffer + 140);
            theNextTable.draw(g);
        }
        if(theSkipTable != null && theNextTable != null)
        {
            String label6 = Messages.getString("BoyerMooreCanvas.5");
            g.drawString(label6, 100 + theNextTable.getWidth() + 30, yBuffer + 140);
            theSkipTable.draw(g);
        } else
        if(theSkipTable != null)
        {
            String label7 = Messages.getString("BoyerMooreCanvas.6");
            g.drawString(label7, 100 - g.getFontMetrics().stringWidth(label7), yBuffer + 140);
            theSkipTable.draw(g);
        }
        if(skipTableN != null)
        {
            String label8 = (new StringBuilder()).append(Messages.getString("BoyerMooreCanvas.7")).append(skipTableS).append("]").toString();
            g.drawString(label8, 100, skipTableN.getPosition().y + skipTableN.getHeight());
            skipTableN.draw(g);
        }
        if(patternN != null)
        {
            String label9 = Messages.getString("BoyerMooreCanvas.9");
            g.drawString(label9, 100, patternN.getPosition().y + patternN.getHeight());
            patternN.draw(g);
        }
        if(skipN != null)
        {
            String label10 = Messages.getString("BoyerMooreCanvas.10");
            g.drawString(label10, 100, yBuffer + 105);
            skipN.draw(g);
        }
        if(mi1N != null)
        {
            String label11 = Messages.getString("BoyerMooreCanvas.11");
            g.drawString(label11, 100, yBuffer + 105);
            mi1N.draw(g);
        }
        if(BoyerMooreResult == 0)
        {
            Color saveColor = g.getColor();
            g.setColor(Color.red);
            g.drawString(Messages.getString("BoyerMooreCanvas.12"), 100, 90);
            g.setColor(saveColor);
        }
        if(BoyerMooreResult == 1)
        {
            Color saveColor = g.getColor();
            g.setColor(Color.green);
            g.drawString(Messages.getString("BoyerMooreCanvas.13"), 100, 90);
            g.setColor(saveColor);
        }
    }

    protected void handleColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj == null)
            return;
        drawables.removeAllElements();
        theBoyerMoore = (BoyerMoore)(BoyerMoore)e.paramObj;
        theString1 = theBoyerMoore.getBoyerMooreString1();
        if(theString1 != null)
        {
            theString1.setPosition(100, yBuffer + 40);
            windowWidth = 100 + theString1.getWidth();
        }
        theString2 = theBoyerMoore.getBoyerMooreString2();
        if(theString2 != null)
        {
            theString2.setPosition(100, yBuffer + 60);
            int temp1 = 100 + theString2.getWidth();
            if(temp1 > windowWidth)
                windowWidth = temp1;
        }
        theNextTable = theBoyerMoore.getNextTable();
        if(theNextTable != null)
        {
            theNextTable.setPosition(100, yBuffer + 140);
            windowHeight = yBuffer + 160 + theNextTable.getHeight();
        }
        theSkipTable = theBoyerMoore.getSkipTable();
        if(theSkipTable != null && theNextTable != null)
        {
            theSkipTable.setPosition(100 + theNextTable.getWidth() + 30, yBuffer + 140);
            windowHeight = yBuffer + 160 + theSkipTable.getHeight();
        } else
        if(theSkipTable != null)
        {
            theSkipTable.setPosition(100, yBuffer + 140);
            windowHeight = yBuffer + 160 + theSkipTable.getHeight();
        }
        testBoyerMooreString1 = theBoyerMoore.getTestBoyerMooreString1();
        if(testBoyerMooreString1 != null)
            testBoyerMooreString1.setPosition(100, yBuffer + 90);
        testBoyerMooreString2 = theBoyerMoore.getTestBoyerMooreString2();
        if(testBoyerMooreString2 != null)
            testBoyerMooreString2.setPosition(100, yBuffer + 110);
        skipTableN = theBoyerMoore.getSkipTableN();
        if(skipTableN != null)
        {
            skipTableN.setPosition(new Point(220, windowHeight));
            windowHeight = windowHeight + skipTableN.getHeight();
            skipTableS = theBoyerMoore.getSkipTableS();
        }
        patternN = theBoyerMoore.getPatternN();
        if(patternN != null)
        {
            patternN.setPosition(new Point(220, windowHeight));
            windowHeight = windowHeight + patternN.getHeight();
        }
        skipN = theBoyerMoore.getSkipN();
        if(skipN != null)
            skipN.setPosition(new Point(140, yBuffer + 90));
        mi1N = theBoyerMoore.getMi1N();
        if(mi1N != null)
            mi1N.setPosition(new Point(240, yBuffer + 90));
        BoyerMooreResult = theBoyerMoore.getBoyerMooreResult();
        addTween(theBoyerMoore.generateTweens(this, null, numberOfTweenSteps));
        if(tweens.getNumberOfTweens() > 0)
            tweens.run();
        theBoyerMoore.removeAllAnimationRequests();
        setSize(windowWidth, windowHeight);
        repaint();
    }

    private static final long serialVersionUID = 0xe238a74474f62579L;
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
}
