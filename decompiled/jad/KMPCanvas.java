// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KMPCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class KMPCanvas extends AlgorithmCanvas
{

    public KMPCanvas()
    {
        xBuffer = 100;
        yBuffer = 10;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(theKMP == null)
            return;
        if(normalFont != null)
            g.setFont(normalFont);
        if(textColor != null)
            g.setColor(textColor);
        if(theString1 != null)
        {
            String label1 = Messages.getString("KMPCanvas.0");
            g.drawString(label1, xBuffer - g.getFontMetrics().stringWidth(label1), yBuffer + 30);
            theString1.draw(g);
        }
        if(theString2 != null)
        {
            String label2 = Messages.getString("KMPCanvas.1");
            g.drawString(label2, xBuffer - g.getFontMetrics().stringWidth(label2), yBuffer + 50);
            theString2.draw(g);
        }
        if(testKMPString1 != null)
        {
            String label3 = Messages.getString("KMPCanvas.2");
            g.drawString(label3, xBuffer - g.getFontMetrics().stringWidth(label3), yBuffer + 90);
            testKMPString1.draw(g);
        }
        if(testKMPString2 != null)
        {
            String label4 = Messages.getString("KMPCanvas.3");
            g.drawString(label4, xBuffer - g.getFontMetrics().stringWidth(label4), yBuffer + 110);
            testKMPString2.draw(g);
        }
        if(theNextTable != null)
        {
            String label5 = Messages.getString("KMPCanvas.4");
            g.drawString(label5, xBuffer - g.getFontMetrics().stringWidth(label5), theNextTable.getY());
            theNextTable.draw(g);
        }
        if(jPointer != null)
        {
            String label6 = "j = ";
            g.drawString(label6, xBuffer - g.getFontMetrics().stringWidth(label6), jPointer.getY() + g.getFontMetrics().getHeight());
            jPointer.draw(g);
        }
        if(iPointer != null)
        {
            String label7 = "i = ";
            g.drawString(label7, xBuffer - g.getFontMetrics().stringWidth(label7), iPointer.getY() + g.getFontMetrics().getHeight());
            iPointer.draw(g);
        }
        if(animNode != null)
            animNode.draw(g);
        if(kmpResult == 0)
        {
            Color saveColor = g.getColor();
            g.setColor(Color.red);
            g.drawString(Messages.getString("KMPCanvas.7"), xBuffer, 90);
            g.setColor(saveColor);
        }
        if(kmpResult == 1)
        {
            Color saveColor = g.getColor();
            g.setColor(Color.green);
            g.drawString(Messages.getString("KMPCanvas.8"), xBuffer, 90);
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
        theKMP = (KMP)(KMP)e.paramObj;
        theString1 = theKMP.getKMPString1();
        if(theString1 != null)
            theString1.setPosition(xBuffer, yBuffer + 30);
        theString2 = theKMP.getKMPString2();
        if(theString2 != null)
            theString2.setPosition(xBuffer, yBuffer + 50);
        testKMPString1 = theKMP.getTestKMPString1();
        if(testKMPString1 != null)
            testKMPString1.setPosition(xBuffer, yBuffer + 90);
        testKMPString2 = theKMP.getTestKMPString2();
        if(testKMPString2 != null)
            testKMPString2.setPosition(xBuffer, yBuffer + 110);
        kmpResult = theKMP.getKMPResult();
        jPointer = theKMP.getJPointer();
        if(jPointer != null)
            if(testKMPString2 == null)
                jPointer.setPosition(new Point(xBuffer, yBuffer + 90));
            else
                jPointer.setPosition(new Point(xBuffer, yBuffer + 160));
        iPointer = theKMP.getIPointer();
        if(iPointer != null)
            if(testKMPString2 == null)
                iPointer.setPosition(new Point(xBuffer, yBuffer + 110));
            else
                iPointer.setPosition(new Point(xBuffer, yBuffer + 130));
        theNextTable = theKMP.getNextTable();
        if(theNextTable != null)
            if(testKMPString2 == null || iPointer == null)
            {
                theNextTable.setPosition(xBuffer, yBuffer + 140);
                windowHeight = yBuffer + 160 + theNextTable.getHeight();
            } else
            {
                theNextTable.setPosition(xBuffer, yBuffer + 200);
            }
        animNode = theKMP.getAnimNode();
        addTween(theKMP.generateTweens(this, null, numberOfTweenSteps));
        if(tweens.getNumberOfTweens() > 0)
            tweens.run();
        theKMP.removeAllAnimationRequests();
        repaint();
    }

    private static final long serialVersionUID = 0x6e454deee078257L;
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
}
