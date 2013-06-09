// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TransclosureGraphMapsCanvasExt.java

import aia.graph.common.*;
import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;

public class TransclosureGraphMapsCanvasExt extends GraphMapsCanvasExt
    implements GraphDialogEventHandler
{

    TransclosureGraphMapsCanvasExt()
    {
        commons = null;
        MATRIX_X = 50;
        MATRIX_Y = 50;
        vecNodes = null;
        tweens = new MultipleTween(this);
        addSelectionListener(new DragSelectionListener(this));
    }

    public Color getTextColor()
    {
        return textColor;
    }

    protected void handleColorSelection(ColorSelection colorSelection)
    {
        String atribute = colorSelection.getAtributeName();
        if(atribute.equalsIgnoreCase(ColorSelection.TEXT_COLOR))
            textColor = colorSelection.getColor();
    }

    protected void handleFontSelection(FontSelection fontSelection)
    {
        String atribute = fontSelection.getAtributeName();
        if(atribute.equalsIgnoreCase("Normal Font"))
            normalFont = fontSelection.getFont();
    }

    public void processDialogEvent(String p_sourceDialog, String value)
    {
        commons.processDialogEvent(p_sourceDialog, value);
    }

    public void editMatrix(GraphMapsNode p_Node)
    {
        commons.editMatrix(p_Node);
    }

    public void displayAlgorithm(Graphics g)
    {
        if(normalFont != null)
            g.setFont(normalFont);
        if(textColor != null)
            g.setColor(textColor);
        if(commons == null)
        {
            return;
        } else
        {
            commons.drawAdjacencyMatrix(g);
            drawLabel(g);
            return;
        }
    }

    private void drawLabel(Graphics g)
    {
        if(commons.getMatrix() == null || graphMaps == null)
            return;
        if(!graphMaps.getMatrixShowing())
            return;
        Font oldFont = g.getFont();
        g.setFont(new Font("Arial", 1, 8));
        FontMetrics fm = g.getFontMetrics();
        g.setColor(Color.black);
        int x = graphMaps.getX();
        int y = graphMaps.getY();
        int j = graphMaps.getJ();
        if(x == graphMaps.getNumberOfNodes() && y == graphMaps.getNumberOfNodes())
        {
            g.drawString("x  y", MATRIX_X + commons.getMatrixWidth() + 5, MATRIX_Y + 20 * x + (20 + fm.getHeight()) / 2);
        } else
        {
            g.drawString("x", MATRIX_X + commons.getMatrixWidth() + 5, MATRIX_Y + 20 * x + (20 + fm.getHeight()) / 2);
            g.drawString("y", MATRIX_X + 20 * y + (20 - fm.stringWidth("y")) / 2, MATRIX_Y + commons.getMatrixHeight() + fm.getHeight());
        }
        if(graphMaps.isInSecondLoop())
        {
            if(j != y)
            {
                g.drawString("j", MATRIX_X + 20 * j + (20 - fm.stringWidth("j")) / 2, MATRIX_Y + commons.getMatrixHeight() + fm.getHeight());
            } else
            {
                g.drawString("y", MATRIX_X + 20 * j + (20 - fm.stringWidth("y")) / 2, MATRIX_Y + commons.getMatrixHeight() + fm.getHeight());
                g.drawString("j", MATRIX_X + 20 * j + (20 - fm.stringWidth("j")) / 2, MATRIX_Y + commons.getMatrixHeight() + 2 * fm.getHeight());
            }
            if(x != y)
                g.drawString("y", MATRIX_X + commons.getMatrixWidth() + 5, MATRIX_Y + 20 * y + (20 + fm.getHeight()) / 2);
            else
                g.drawString("x  y", MATRIX_X + commons.getMatrixWidth() + 5, MATRIX_Y + 20 * y + (20 + fm.getHeight()) / 2);
        }
        g.setFont(oldFont);
    }

    public void drawMatrixAssociatedElements(Graphics g1, ElementArray elementarray, int i)
    {
    }

    public void setStructureNodeAppearance(GraphMapsNode graphmapsnode, GraphMapsNode graphmapsnode1)
    {
    }

    public void drawStructureAssociatedElements(Graphics g1, GraphMapsNode graphmapsnode, GraphMapsNode graphmapsnode1)
    {
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            drawables.removeAllElements();
            graphMaps = (TransclosureGraphMaps)(TransclosureGraphMaps)e.paramObj;
            if(commons == null)
                commons = new TransclosureGraphCanvasCommonExt(this, graphMaps);
            removeAllSelectables();
            commons.setMatrix(graphMaps.getAdjacencyMatrix(), MATRIX_X, MATRIX_Y);
            commons.adjustCanvasSize();
            repaint();
        }
    }

    protected TransclosureGraphMaps graphMaps;
    protected TransclosureGraphCanvasCommonExt commons;
    protected int MATRIX_X;
    protected int MATRIX_Y;
    protected Font normalFont;
    protected Color textColor;
    public static final int NODE_WIDTH = 20;
    public static final int NODE_HEIGHT = 20;
    protected Vector vecNodes;
}
