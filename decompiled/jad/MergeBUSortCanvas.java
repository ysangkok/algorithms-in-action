// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MergeBUSortCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;

public class MergeBUSortCanvas extends AlgorithmCanvas
{

    public MergeBUSortCanvas()
    {
        textColor = Color.black;
        xBuffer = 60;
        yBuffer = 80;
        gapBetweenArrayAndMergeBUSort = 120;
        spacingBetweenInformationAndMergeBUSort = 10;
        boxWidth = 20;
        activeYBuffer = 5;
        iWidth = 0;
        jWidth = 0;
        iPoint = null;
        jPoint = null;
        pValue = -1;
        pValueReady = false;
        activeLeft = null;
        activeRight = null;
        tweening = false;
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

    public void displayAlgorithm(Graphics g)
    {
        if(mergeBUSort != null)
        {
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Graphics clippedG = g.create(0, 0, 600, 765);
            mergeBUSort.drawTree(new Point(xBuffer, yBuffer), clippedG);
        }
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            mergeBUSort = (MergeBUSort)(MergeBUSort)e.paramObj;
            removeAllSelectables();
            mergeBUSort.removeAllAnimationRequests();
            tweening = true;
            tweening = false;
        }
        repaint();
    }

    protected Color textColor;
    protected int xBuffer;
    protected int yBuffer;
    protected int gapBetweenArrayAndMergeBUSort;
    protected int spacingBetweenInformationAndMergeBUSort;
    protected int boxWidth;
    protected int activeYBuffer;
    protected MergeBUSort mergeBUSort;
    protected MyElementArray elementArray;
    protected Vector childElements;
    protected int iWidth;
    protected int jWidth;
    protected Point iPoint;
    protected Point jPoint;
    protected int pValue;
    protected boolean pValueReady;
    protected Point activeLeft;
    protected Point activeRight;
    protected Point oldIPoint;
    protected Point oldJPoint;
    protected boolean tweening;
}
