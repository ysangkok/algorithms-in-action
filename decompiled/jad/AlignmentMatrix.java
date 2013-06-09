// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlignmentMatrix.java

import com.cim.AIA.*;
import java.awt.*;
import java.io.PrintStream;

public class AlignmentMatrix
    implements Drawable, Selectable
{

    public AlignmentMatrix(int xSize, int ySize)
    {
        this(xSize, ySize, null, null);
    }

    public AlignmentMatrix(int xSize, int ySize, String xLabel, String yLabel)
    {
        DEFAULT_NAMECOLOR = Color.black;
        DEFAULT_HIGHLIGHTCOLOR = Color.red;
        DEFAULT_SELECTCOLOR = Color.blue;
        DEFAULT_LABELCOLOR = new Color(0, 200, 0);
        matrixName = new String();
        location = new Point();
        sequenceNumber = 1;
        theMatrix = new AlignmentNode[xSize][ySize];
        saveMatrix = new AlignmentNode[xSize][ySize];
        xNodes = new Node[xSize];
        yNodes = new Node[ySize];
        this.xSize = xSize;
        this.ySize = ySize;
        currentMax = 0;
        currentMaxX = -1;
        currentMaxY = -1;
        setShiftX(1);
        setShiftY(1);
        setHighlightColor(DEFAULT_HIGHLIGHTCOLOR);
        setLabelColor(DEFAULT_LABELCOLOR);
        setNodeBackgroundColor(Alignment.DEFAULT_NODE_COLOR);
        setNodeHighlightColor(Alignment.DEFAULT_HIGHLIGHT_COLOR);
        setXLabel(xLabel);
        setYLabel(yLabel);
        setArrowHeight(10);
        setArrowWidth(10);
        setXGap(5);
        setYGap(5);
        setLocation(0, 0);
        setDrawNull(true);
        setDrawIndex(true);
        setXIndexHighlight(-1);
        setYIndexHighlight(-1);
        isHighlight = false;
        isSelected = false;
        selectColor = DEFAULT_SELECTCOLOR;
        drawName = false;
        drawInternalBox = false;
    }

    public void add(AlignmentNode new1, int position)
    {
        add(new1, position % xSize, position / xSize);
    }

    public void add(AlignmentNode new1, int xPos, int yPos)
    {
        Object obj1 = new1.getObject();
        int tmp;
        if(obj1 instanceof Integer)
            tmp = ((Integer)new1.getObject()).intValue();
        else
        if(((String)obj1).equals("-Inf"))
            tmp = -5000;
        else
            tmp = 5000;
        if(currentMaxX == -1 || currentMaxY == -1 || currentMax <= tmp)
        {
            currentMax = tmp;
            currentMaxX = xPos;
            currentMaxY = yPos;
        }
        theMatrix[xPos][yPos] = new1;
    }

    public void add(int new1, int position)
    {
        AlignmentNode tempNode;
        if(new1 == -5000)
            tempNode = new AlignmentNode(new String("-Inf"), position);
        else
        if(new1 == 5000)
            tempNode = new AlignmentNode(new String("Inf"), position);
        else
            tempNode = new AlignmentNode(new Integer(new1), position);
        tempNode.setBackgroundColor(nodeBackgroundColor);
        tempNode.setHighlightColor(nodeHighlightColor);
        add(tempNode, position);
    }

    public void add(int new1, int xPos, int yPos)
    {
        add(new1, xSize * yPos + xPos);
    }

    public void draw(Graphics g)
    {
        saveMatrix();
        if(drawNull)
        {
            for(int j = 0; j < ySize; j++)
            {
                for(int i = 0; i < xSize; i++)
                {
                    AlignmentNode tempNode = theMatrix[i][j];
                    if(tempNode != null)
                        continue;
                    tempNode = new AlignmentNode("", 0);
                    tempNode.setBackgroundColor(nodeBackgroundColor);
                    tempNode.setHighlightColor(nodeHighlightColor);
                    if(isHighlight)
                        tempNode.highlight();
                    theMatrix[i][j] = tempNode;
                }

            }

        }
        setAllArrows();
        positionXLabel();
        positionYLabel();
        positionMatrix();
        if(isSelected)
        {
            g.setColor(selectColor);
            g.drawRect(location.x, location.y, getWidth(), getHeight());
            g.drawRect(location.x - 1, location.y - 1, getWidth() + 2, getHeight() + 2);
        }
        drawXLabel(g);
        drawYLabel(g);
        drawMatrix(g);
        if(drawIndex)
        {
            drawXIndex(g);
            drawYIndex(g);
        }
        if(drawName)
            drawMatrixName(g);
        if(drawInternalBox)
            drawInternalBox(g);
        restoreMatrix();
    }

    public void draw(Graphics g, Point pnt)
    {
        setLocation(pnt);
        draw(g);
    }

    protected void drawInternalBox(Graphics g)
    {
        boolean drawYLine;
        boolean drawXLine = drawYLine = true;
        if(internalBoxX >= xSize || internalBoxY >= ySize)
            return;
        int x2 = theMatrix[internalBoxX][internalBoxY].getPosition().x + theMatrix[internalBoxX][internalBoxY].getRealWidth();
        int y2 = (theMatrix[internalBoxX][internalBoxY].getPosition().y + theMatrix[internalBoxX][internalBoxY].getRealHeight()) - theMatrix[internalBoxX][internalBoxY].getBufferHeight();
        int tempX;
        if(internalBoxX - internalBoxWidth < 0)
        {
            drawYLine = false;
            tempX = 0;
        } else
        {
            tempX = internalBoxX - internalBoxWidth;
        }
        int tempY;
        if(internalBoxY - internalBoxHeight < 0)
        {
            drawXLine = false;
            tempY = 0;
        } else
        {
            tempY = internalBoxY - internalBoxHeight;
        }
        int x1 = theMatrix[tempX][tempY].getPosition().x + theMatrix[tempX][tempY].getArrowWidth();
        int y1 = theMatrix[tempX][tempY].getPosition().y + theMatrix[tempX][tempY].getArrowHeight();
        g.drawLine(x2, y1, x2, y2);
        g.drawLine(x2, y2, x1, y2);
        if(drawYLine)
            g.drawLine(x1, y2, x1, y1);
        if(drawXLine)
            g.drawLine(x1, y1, x2, y1);
    }

    protected void drawMatrix(Graphics g)
    {
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
            {
                AlignmentNode tempNode = theMatrix[i][j];
                if(tempNode != null)
                    tempNode.draw(g);
            }

        }

    }

    protected void drawMatrixName(Graphics g)
    {
        g.setColor(DEFAULT_NAMECOLOR);
        int xPos = location.x + 5;
        int yPos = location.y + g.getFontMetrics().getAscent();
        g.drawString(matrixName, xPos, yPos);
    }

    protected void drawXIndex(Graphics g)
    {
        int xPos = location.x + nodeWidth + xGap + (aNodeWidth - nodeWidth) + nodeWidth / 2;
        int yPos = location.y + g.getFontMetrics().getAscent() / 2 + nodeHeight + yGap;
        for(int i = 0; i < xSize; i++)
        {
            if(i != xIndexHighlight)
                g.setColor(Color.black);
            else
                g.setColor(highlightColor);
            g.drawString((new StringBuilder()).append("").append(i).toString(), xPos - g.getFontMetrics().stringWidth((new StringBuilder()).append("").append(i).toString()) / 2, yPos);
            xPos += aNodeWidth;
        }

    }

    protected void drawXLabel(Graphics g)
    {
        for(int i = 0; i < xSize; i++)
            xNodes[i].draw(g);

    }

    protected void drawYIndex(Graphics g)
    {
        int xPos = location.x + nodeWidth + xGap;
        int yPos = location.y + nodeHeight + yGap + (aNodeHeight - nodeHeight) + nodeHeight / 2 + g.getFontMetrics().getAscent() / 2;
        for(int i = 0; i < ySize; i++)
        {
            if(i != yIndexHighlight)
                g.setColor(Color.black);
            else
                g.setColor(highlightColor);
            g.drawString((new StringBuilder()).append("").append(i).toString(), xPos - g.getFontMetrics().stringWidth((new StringBuilder()).append("").append(i).toString()) / 2, yPos);
            yPos += aNodeHeight;
        }

    }

    protected void drawYLabel(Graphics g)
    {
        for(int i = 0; i < ySize; i++)
            yNodes[i].draw(g);

    }

    public boolean equals(Selectable selectable)
    {
        if(!(selectable instanceof AlignmentMatrix))
            return false;
        AlignmentMatrix testS = (AlignmentMatrix)selectable;
        if(testS.getIdentifier() != getIdentifier())
            return false;
        if(testS.xSize != xSize)
            return false;
        if(testS.ySize != ySize)
            return false;
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
            {
                AlignmentNode one = get(i, j);
                AlignmentNode two = testS.get(i, j);
                if(one != null && two != null)
                {
                    if(!one.equals(two))
                        return false;
                    continue;
                }
                if(one != null || two != null)
                    return false;
            }

        }

        return true;
    }

    public AlignmentNode get(int position)
    {
        return theMatrix[position % xSize][position / xSize];
    }

    public AlignmentNode get(int xPos, int yPos)
    {
        return theMatrix[xPos][yPos];
    }

    public int getArrowHeight()
    {
        return arrowHeight;
    }

    public int getArrowWidth()
    {
        return arrowWidth;
    }

    public boolean getDrawIndex()
    {
        return drawIndex;
    }

    public boolean getDrawNull()
    {
        return drawNull;
    }

    public int getHeight()
    {
        AlignmentNode testNode = new AlignmentNode("", 0);
        if(isGapMode)
            testNode.setBufferHeight(10);
        Node testNode2 = new Node("", 0);
        return testNode2.getHeight() + yGap + ySize * testNode.getRealHeight();
    }

    public Color getHighlightColor()
    {
        return highlightColor;
    }

    public int getIdentifier()
    {
        return sequenceNumber;
    }

    public boolean getIsGapMode()
    {
        return isGapMode;
    }

    public Selectable getItemAt(Point point)
    {
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
                if(theMatrix[i][j] != null && theMatrix[i][j].getItemAt(point) != null)
                    return theMatrix[i][j];

        }

        return null;
    }

    public Point getLocation()
    {
        return location;
    }

    public int getMax()
    {
        return currentMax;
    }

    public int getMaxX()
    {
        return currentMaxX;
    }

    public int getMaxY()
    {
        return currentMaxY;
    }

    public int getShiftX()
    {
        return shiftX;
    }

    public int getShiftY()
    {
        return shiftY;
    }

    public int getWidth()
    {
        AlignmentNode testNode = new AlignmentNode("", 0);
        Node testNode2 = new Node("", 0);
        return testNode2.getWidth() + xGap + xSize * testNode.getRealWidth();
    }

    public int getXGap()
    {
        return xGap;
    }

    public String getXLabel()
    {
        return xLabel;
    }

    public int getXSize()
    {
        return xSize;
    }

    public int getYGap()
    {
        return yGap;
    }

    public String getYLabel()
    {
        return yLabel;
    }

    public int getYSize()
    {
        return ySize;
    }

    public void highlight()
    {
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
                if(theMatrix[i][j] != null)
                    theMatrix[i][j].highlight();

        }

        isHighlight = true;
    }

    protected void positionMatrix()
    {
        int xPos = location.x + nodeWidth + (aNodeWidth - nodeWidth - arrowWidth) + xGap;
        int yPos = location.y + nodeHeight + (aNodeHeight - nodeHeight - arrowHeight) + yGap;
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
            {
                AlignmentNode tempNode = theMatrix[i][j];
                if(tempNode != null)
                {
                    tempNode.setX(xPos);
                    tempNode.setY(yPos);
                }
                xPos += aNodeWidth;
            }

            xPos = location.x + nodeWidth + (aNodeWidth - nodeWidth - arrowWidth) + xGap;
            yPos += aNodeHeight;
        }

    }

    protected void positionXLabel()
    {
        int yPos = location.y;
        int xPos = location.x + aNodeWidth + xGap;
        for(int i = 0; i < xSize; i++)
        {
            xNodes[i].setPosition(new Point(xPos, yPos));
            xPos += aNodeWidth;
        }

    }

    protected void positionYLabel()
    {
        int xPos = location.x;
        int yPos = location.y + aNodeHeight + yGap;
        for(int i = 0; i < ySize; i++)
        {
            yNodes[i].setPosition(new Point(xPos, yPos));
            yPos += aNodeHeight;
        }

    }

    public void printMatrix()
    {
        System.out.println((new StringBuilder()).append("X Label = ").append(xLabel).toString());
        System.out.println((new StringBuilder()).append("Y Label = ").append(yLabel).toString());
        System.out.println((new StringBuilder()).append("X Size = ").append(xSize).toString());
        System.out.println((new StringBuilder()).append("Y Size = ").append(ySize).toString());
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
            {
                AlignmentNode temp = get(i, j);
                if(temp == null)
                {
                    System.out.print("   X ");
                    continue;
                }
                if(temp.getTraceUp())
                    System.out.print("|");
                else
                    System.out.print(" ");
                if(temp.getTraceLeft())
                    System.out.print("-");
                else
                    System.out.print(" ");
                if(temp.getTraceDiag())
                    System.out.print("\\");
                else
                    System.out.print(" ");
                System.out.print((new StringBuilder()).append("").append(valueAt(i, j)).append(" ").toString());
            }

            System.out.println();
        }

    }

    protected void restoreMatrix()
    {
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
                theMatrix[i][j] = saveMatrix[i][j];

        }

    }

    protected void saveMatrix()
    {
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
                saveMatrix[i][j] = theMatrix[i][j];

        }

    }

    protected void setAllArrows()
    {
        Node testNode = new Node("", 0);
        nodeWidth = testNode.getWidth();
        nodeHeight = testNode.getHeight();
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
            {
                AlignmentNode temp = theMatrix[i][j];
                if(temp == null)
                    continue;
                temp.setArrowWidth(arrowWidth);
                temp.setArrowHeight(arrowHeight);
                if(isGapMode)
                {
                    temp.setHaveChoice(true);
                    temp.setBufferHeight(10);
                }
            }

        }

        AlignmentNode tempANode = theMatrix[0][0];
        aNodeHeight = tempANode.getRealHeight();
        aNodeWidth = tempANode.getRealWidth();
    }

    public void setAllColor(Color color, Color color2)
    {
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
                if(theMatrix[i][j] != null)
                {
                    theMatrix[i][j].setBackgroundColor(color);
                    theMatrix[i][j].setArrowColor(color2);
                }

        }

    }

    public void setArrowHeight(int x)
    {
        arrowHeight = x;
    }

    public void setArrowWidth(int y)
    {
        arrowWidth = y;
    }

    public void setDrawIndex(boolean state)
    {
        drawIndex = state;
    }

    public void setDrawInternalBox(boolean state)
    {
        drawInternalBox = state;
    }

    public void setDrawName(boolean state)
    {
        drawName = state;
    }

    public void setDrawNull(boolean state)
    {
        drawNull = state;
    }

    public void setHighlightColor(Color c)
    {
        highlightColor = c;
    }

    public void setInternalBoxCoords(int x, int y)
    {
        internalBoxX = x;
        internalBoxY = y;
    }

    public void setInternalBoxHeight(int h)
    {
        internalBoxHeight = h;
    }

    public void setInternalBoxSize(int w, int h)
    {
        internalBoxWidth = w;
        internalBoxHeight = h;
    }

    public void setInternalBoxWidth(int w)
    {
        internalBoxWidth = w;
    }

    public void setInternalBoxX(int x)
    {
        internalBoxX = x;
    }

    public void setInternalBoxY(int y)
    {
        internalBoxY = y;
    }

    public void setIsGapMode(boolean state)
    {
        isGapMode = state;
    }

    public void setIsSelected(boolean state)
    {
        isSelected = state;
    }

    public void setLabelColor(Color c)
    {
        labelColor = c;
    }

    public void setLocation(int xPos, int yPos)
    {
        location.x = xPos;
        location.y = yPos;
    }

    public void setLocation(Point newPnt)
    {
        location = newPnt;
    }

    public void setMatrixName(String name)
    {
        matrixName = new String(name);
    }

    public void setNodeBackgroundColor(Color c)
    {
        nodeBackgroundColor = c;
    }

    public void setNodeHighlightColor(Color c)
    {
        nodeHighlightColor = c;
    }

    public void setSequence(int x)
    {
        sequenceNumber = x;
    }

    public void setShiftX(int x)
    {
        shiftX = x;
    }

    public void setShiftY(int y)
    {
        shiftY = y;
    }

    public void setXGap(int x)
    {
        xGap = x;
    }

    public void setXIndexHighlight(int x)
    {
        xIndexHighlight = x;
    }

    public void setXLabel(String label)
    {
        xLabel = label;
        for(int i = 0; i < shiftX; i++)
        {
            xNodes[i] = new Node("", 0);
            xNodes[i].setBackgroundColor(labelColor);
        }

        for(int i = shiftX; i < xSize; i++)
        {
            Node tempN;
            if(i - shiftX < label.length())
                tempN = new Node((new StringBuilder()).append("").append(label.charAt(i - shiftX)).toString(), 0);
            else
                tempN = new Node("", 0);
            tempN.setBackgroundColor(labelColor);
            xNodes[i] = tempN;
        }

    }

    public void setYGap(int y)
    {
        yGap = y;
    }

    public void setYIndexHighlight(int y)
    {
        yIndexHighlight = y;
    }

    public void setYLabel(String label)
    {
        yLabel = label;
        for(int i = 0; i < shiftY; i++)
        {
            yNodes[i] = new Node("", 0);
            yNodes[i].setBackgroundColor(labelColor);
        }

        for(int i = shiftY; i < ySize; i++)
        {
            Node tempN;
            if(i - shiftY < label.length())
                tempN = new Node((new StringBuilder()).append("").append(label.charAt(i - shiftY)).toString(), 0);
            else
                tempN = new Node("", 0);
            tempN.setBackgroundColor(labelColor);
            yNodes[i] = tempN;
        }

    }

    public void unHighlight()
    {
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
                if(theMatrix[i][j] != null)
                    theMatrix[i][j].unHighlight();

        }

        isHighlight = false;
    }

    public void unHighlightTrace()
    {
        for(int j = 0; j < ySize; j++)
        {
            for(int i = 0; i < xSize; i++)
                if(theMatrix[i][j] != null)
                {
                    theMatrix[i][j].unHighlightLeft();
                    theMatrix[i][j].unHighlightUp();
                    theMatrix[i][j].unHighlightDiag();
                    theMatrix[i][j].unHighlightA();
                    theMatrix[i][j].unHighlightB();
                    theMatrix[i][j].unHighlightC();
                    theMatrix[i][j].setIsDisabled(false);
                }

        }

    }

    public int valueAt(int position)
    {
        AlignmentNode temp = get(position);
        if(temp != null)
        {
            Object obj1 = temp.getObject();
            if(obj1 instanceof Integer)
                return ((Integer)temp.getObject()).intValue();
            return !((String)obj1).equals("-Inf") ? 5000 : -5000;
        } else
        {
            return 0;
        }
    }

    public int valueAt(int xPos, int yPos)
    {
        AlignmentNode temp = get(xPos, yPos);
        if(temp != null)
        {
            Object obj1 = temp.getObject();
            if(obj1 instanceof Integer)
                return ((Integer)temp.getObject()).intValue();
            return !((String)obj1).equals("-Inf") ? 5000 : -5000;
        } else
        {
            return 0;
        }
    }

    public static final int NEG_INF = -5000;
    public static final int POS_INF = 5000;
    protected final int DEFAULT_BUFFER_HEIGHT = 10;
    protected final int DEFAULT_X_GAP = 5;
    protected final int DEFAULT_Y_GAP = 5;
    protected final int DEFAULT_ARROW_X = 10;
    protected final int DEFAULT_ARROW_Y = 10;
    protected final int DEFAULT_SHIFT_X = 1;
    protected final int DEFAULT_SHIFT_Y = 1;
    protected final Color DEFAULT_NAMECOLOR;
    protected final Color DEFAULT_HIGHLIGHTCOLOR;
    protected final Color DEFAULT_SELECTCOLOR;
    protected final Color DEFAULT_LABELCOLOR;
    protected final int MATRIX_X_GAP = 5;
    protected Node xNodes[];
    protected Node yNodes[];
    protected String matrixName;
    protected int xIndexHighlight;
    protected int yIndexHighlight;
    protected Color nodeBackgroundColor;
    protected Color nodeHighlightColor;
    protected Color labelColor;
    protected Color highlightColor;
    protected Color selectColor;
    protected AlignmentNode saveMatrix[][];
    protected AlignmentNode theMatrix[][];
    protected int nodeHeight;
    protected int nodeWidth;
    protected int aNodeHeight;
    protected int aNodeWidth;
    protected int arrowHeight;
    protected int arrowWidth;
    protected int xSize;
    protected int ySize;
    protected String xLabel;
    protected String yLabel;
    protected int shiftX;
    protected int shiftY;
    protected int xGap;
    protected int yGap;
    protected Point location;
    protected int sequenceNumber;
    protected int internalBoxX;
    protected int internalBoxY;
    protected int internalBoxWidth;
    protected int internalBoxHeight;
    protected int currentMax;
    protected int currentMaxX;
    protected int currentMaxY;
    protected boolean drawNull;
    protected boolean drawIndex;
    protected boolean isHighlight;
    protected boolean isGapMode;
    protected boolean isSelected;
    protected boolean drawName;
    protected boolean drawInternalBox;
}
