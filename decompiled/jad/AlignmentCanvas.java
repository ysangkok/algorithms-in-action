// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;

public class AlignmentCanvas extends AlgorithmCanvas
{

    public AlignmentCanvas()
    {
        xBuffer = 100;
        yBuffer = 10;
        spacingBetweenArrays = 100;
        xTextBuffer = 10;
        yTextBuffer = 5;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(theAlignment == null)
            return;
        if(normalFont != null)
            g.setFont(normalFont);
        if(textColor != null)
            g.setColor(textColor);
        if(tempMatrix != null)
            tempMatrix.draw(g);
        if(aMatrix != null)
            aMatrix.draw(g);
        if(bMatrix != null)
            bMatrix.draw(g);
        if(cMatrix != null)
            cMatrix.draw(g);
        if(string1EA != null && string2EA != null)
        {
            string1EA.draw(g);
            string2EA.draw(g);
        }
        if(theMinimum != null)
            theMinimum.draw(g);
        if(theKey != null)
            theKey.draw(g);
        if(the3D != null)
            the3D.draw(g);
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
        int windowWidth = getParentSize().width;
        int windowTop = 30;
        int maxWidth = 0;
        int matrixWidth = 0;
        int X_GAP = 20;
        int Y_GAP = 20;
        drawables.removeAllElements();
        removeAllSelectables();
        theAlignment = (Alignment)(Alignment)e.paramObj;
        tempMatrix = theAlignment.getMatrix();
        aMatrix = theAlignment.getAMatrix();
        bMatrix = theAlignment.getBMatrix();
        cMatrix = theAlignment.getCMatrix();
        string1EA = theAlignment.getString1EA();
        string2EA = theAlignment.getString2EA();
        theMinimum = theAlignment.getMinimum();
        theKey = theAlignment.getKey();
        the3D = theAlignment.get3D();
        if(tempMatrix != null)
            maxWidth = tempMatrix.getWidth() + X_GAP;
        if(aMatrix != null)
            maxWidth = X_GAP + (aMatrix.getWidth() + X_GAP) * 3;
        if(string1EA != null && string2EA != null)
        {
            if(string1EA.getWidth() > maxWidth)
                maxWidth = string1EA.getWidth();
            if(string2EA.getWidth() > maxWidth)
                maxWidth = string2EA.getWidth();
        }
        if(windowWidth < maxWidth)
            windowWidth = maxWidth;
        if(tempMatrix != null)
        {
            tempMatrix.setLocation((windowWidth - tempMatrix.getWidth()) / 2, windowTop);
            windowTop += tempMatrix.getHeight();
            matrixWidth = tempMatrix.getWidth();
        }
        if(aMatrix != null)
        {
            aMatrix.setLocation(X_GAP, windowTop);
            matrixWidth = aMatrix.getWidth();
        }
        if(bMatrix != null)
        {
            bMatrix.setLocation(X_GAP + matrixWidth + X_GAP, windowTop);
            matrixWidth = bMatrix.getWidth();
        }
        if(cMatrix != null)
        {
            cMatrix.setLocation(X_GAP + (matrixWidth + X_GAP) * 2, windowTop);
            windowTop += cMatrix.getHeight();
            matrixWidth = cMatrix.getWidth();
        }
        if(string1EA != null && string2EA != null)
        {
            windowTop += Y_GAP;
            maxWidth = string1EA.getWidth();
            if(string2EA.getWidth() > maxWidth)
                maxWidth = string2EA.getWidth();
            string1EA.setLocation((windowWidth - maxWidth) / 2, windowTop);
            windowTop += string1EA.getHeight();
            windowTop += Y_GAP / 2;
            string2EA.setLocation((windowWidth - maxWidth) / 2, windowTop);
            windowTop += string2EA.getHeight();
        }
        if(theMinimum != null)
        {
            windowTop += Y_GAP;
            theMinimum.setLocation((windowWidth - matrixWidth) / 2, windowTop);
            windowTop += theMinimum.getHeight();
        }
        if(theKey != null)
        {
            windowTop += Y_GAP;
            theKey.setLocation((windowWidth - theKey.getWidth()) / 2, windowTop);
            if(the3D != null)
            {
                the3D.setLocation(20, windowTop);
                if(the3D.getHeight() == 0)
                    windowTop += 300;
                else
                    windowTop += the3D.getHeight();
            } else
            {
                windowTop += theKey.getHeight();
            }
        }
        setSize(windowWidth, windowTop + Y_GAP);
        if(tempMatrix != null)
            addSelectable(tempMatrix);
        if(aMatrix != null)
            addSelectable(aMatrix);
        if(bMatrix != null)
            addSelectable(bMatrix);
        if(cMatrix != null)
            addSelectable(cMatrix);
        repaint();
    }

    private static final long serialVersionUID = 0x1d9761bc3ecb128fL;
    protected AlignmentMinimum theMinimum;
    protected ElementArray string1EA;
    protected ElementArray string2EA;
    protected AlignmentKey theKey;
    protected AlignmentMatrix3D the3D;
    protected AlignmentMatrix tempMatrix;
    protected AlignmentMatrix aMatrix;
    protected AlignmentMatrix bMatrix;
    protected AlignmentMatrix cMatrix;
    protected int xBuffer;
    protected int yBuffer;
    protected int spacingBetweenArrays;
    protected int xTextBuffer;
    protected int yTextBuffer;
    protected Font normalFont;
    protected Color textColor;
    protected Alignment theAlignment;
}
