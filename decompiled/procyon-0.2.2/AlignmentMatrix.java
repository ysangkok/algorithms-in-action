import java.awt.*;
import com.cim.AIA.*;

public class AlignmentMatrix implements Drawable, Selectable
{
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
    protected Node[] xNodes;
    protected Node[] yNodes;
    protected String matrixName;
    protected int xIndexHighlight;
    protected int yIndexHighlight;
    protected Color nodeBackgroundColor;
    protected Color nodeHighlightColor;
    protected Color labelColor;
    protected Color highlightColor;
    protected Color selectColor;
    protected AlignmentNode[][] saveMatrix;
    protected AlignmentNode[][] theMatrix;
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
    
    public AlignmentMatrix(final int xSize, final int ySize) {
        this(xSize, ySize, null, null);
    }
    
    public AlignmentMatrix(final int xSize, final int ySize, final String xLabel, final String yLabel) {
        super();
        this.DEFAULT_BUFFER_HEIGHT = 10;
        this.DEFAULT_X_GAP = 5;
        this.DEFAULT_Y_GAP = 5;
        this.DEFAULT_ARROW_X = 10;
        this.DEFAULT_ARROW_Y = 10;
        this.DEFAULT_SHIFT_X = 1;
        this.DEFAULT_SHIFT_Y = 1;
        this.DEFAULT_NAMECOLOR = Color.black;
        this.DEFAULT_HIGHLIGHTCOLOR = Color.red;
        this.DEFAULT_SELECTCOLOR = Color.blue;
        this.DEFAULT_LABELCOLOR = new Color(0, 200, 0);
        this.MATRIX_X_GAP = 5;
        this.matrixName = new String();
        this.location = new Point();
        this.sequenceNumber = 1;
        this.theMatrix = new AlignmentNode[xSize][ySize];
        this.saveMatrix = new AlignmentNode[xSize][ySize];
        this.xNodes = new Node[xSize];
        this.yNodes = new Node[ySize];
        this.xSize = xSize;
        this.ySize = ySize;
        this.currentMax = 0;
        this.currentMaxX = -1;
        this.currentMaxY = -1;
        this.setShiftX(1);
        this.setShiftY(1);
        this.setHighlightColor(this.DEFAULT_HIGHLIGHTCOLOR);
        this.setLabelColor(this.DEFAULT_LABELCOLOR);
        this.setNodeBackgroundColor(Alignment.DEFAULT_NODE_COLOR);
        this.setNodeHighlightColor(Alignment.DEFAULT_HIGHLIGHT_COLOR);
        this.setXLabel(xLabel);
        this.setYLabel(yLabel);
        this.setArrowHeight(10);
        this.setArrowWidth(10);
        this.setXGap(5);
        this.setYGap(5);
        this.setLocation(0, 0);
        this.setDrawNull(true);
        this.setDrawIndex(true);
        this.setXIndexHighlight(-1);
        this.setYIndexHighlight(-1);
        this.isHighlight = false;
        this.isSelected = false;
        this.selectColor = this.DEFAULT_SELECTCOLOR;
        this.drawName = false;
        this.drawInternalBox = false;
    }
    
    public void add(final AlignmentNode new1, final int position) {
        this.add(new1, position % this.xSize, position / this.xSize);
    }
    
    public void add(final AlignmentNode new1, final int xPos, final int yPos) {
        final Object obj1 = new1.getObject();
        int tmp;
        if (obj1 instanceof Integer) {
            tmp = ((Integer)new1.getObject()).intValue();
        }
        else if (((String)obj1).equals("-Inf")) {
            tmp = -5000;
        }
        else {
            tmp = 5000;
        }
        if (this.currentMaxX == -1 || this.currentMaxY == -1 || this.currentMax <= tmp) {
            this.currentMax = tmp;
            this.currentMaxX = xPos;
            this.currentMaxY = yPos;
        }
        this.theMatrix[xPos][yPos] = new1;
    }
    
    public void add(final int new1, final int position) {
        AlignmentNode tempNode;
        if (new1 == -5000) {
            tempNode = new AlignmentNode(new String("-Inf"), position);
        }
        else if (new1 == 5000) {
            tempNode = new AlignmentNode(new String("Inf"), position);
        }
        else {
            tempNode = new AlignmentNode(new Integer(new1), position);
        }
        tempNode.setBackgroundColor(this.nodeBackgroundColor);
        tempNode.setHighlightColor(this.nodeHighlightColor);
        this.add(tempNode, position);
    }
    
    public void add(final int new1, final int xPos, final int yPos) {
        this.add(new1, this.xSize * yPos + xPos);
    }
    
    public void draw(final Graphics g) {
        this.saveMatrix();
        if (this.drawNull) {
            for (int j = 0; j < this.ySize; ++j) {
                int i = 0;
                while (i < this.xSize) {
                    if (this.theMatrix[i][j] == null) {
                        final AlignmentNode tempNode = new AlignmentNode("", 0);
                        tempNode.setBackgroundColor(this.nodeBackgroundColor);
                        tempNode.setHighlightColor(this.nodeHighlightColor);
                        if (this.isHighlight) {
                            tempNode.highlight();
                        }
                        this.theMatrix[i][j] = tempNode;
                    }
                    ++i;
                }
            }
        }
        this.setAllArrows();
        this.positionXLabel();
        this.positionYLabel();
        this.positionMatrix();
        if (this.isSelected) {
            g.setColor(this.selectColor);
            g.drawRect(this.location.x, this.location.y, this.getWidth(), this.getHeight());
            g.drawRect(this.location.x - 1, this.location.y - 1, this.getWidth() + 2, this.getHeight() + 2);
        }
        this.drawXLabel(g);
        this.drawYLabel(g);
        this.drawMatrix(g);
        if (this.drawIndex) {
            this.drawXIndex(g);
            this.drawYIndex(g);
        }
        if (this.drawName) {
            this.drawMatrixName(g);
        }
        if (this.drawInternalBox) {
            this.drawInternalBox(g);
        }
        this.restoreMatrix();
    }
    
    public void draw(final Graphics g, final Point pnt) {
        this.setLocation(pnt);
        this.draw(g);
    }
    
    protected void drawInternalBox(final Graphics g) {
        boolean drawXLine;
        boolean drawYLine = drawXLine = true;
        if (this.internalBoxX >= this.xSize || this.internalBoxY >= this.ySize) {
            return;
        }
        final int x2 = this.theMatrix[this.internalBoxX][this.internalBoxY].getPosition().x + this.theMatrix[this.internalBoxX][this.internalBoxY].getRealWidth();
        final int y2 = this.theMatrix[this.internalBoxX][this.internalBoxY].getPosition().y + this.theMatrix[this.internalBoxX][this.internalBoxY].getRealHeight() - this.theMatrix[this.internalBoxX][this.internalBoxY].getBufferHeight();
        int tempX;
        if (this.internalBoxX - this.internalBoxWidth < 0) {
            drawYLine = false;
            tempX = 0;
        }
        else {
            tempX = this.internalBoxX - this.internalBoxWidth;
        }
        int tempY;
        if (this.internalBoxY - this.internalBoxHeight < 0) {
            drawXLine = false;
            tempY = 0;
        }
        else {
            tempY = this.internalBoxY - this.internalBoxHeight;
        }
        final int x1 = this.theMatrix[tempX][tempY].getPosition().x + this.theMatrix[tempX][tempY].getArrowWidth();
        final int y1 = this.theMatrix[tempX][tempY].getPosition().y + this.theMatrix[tempX][tempY].getArrowHeight();
        g.drawLine(x2, y1, x2, y2);
        g.drawLine(x2, y2, x1, y2);
        if (drawYLine) {
            g.drawLine(x1, y2, x1, y1);
        }
        if (drawXLine) {
            g.drawLine(x1, y1, x2, y1);
        }
    }
    
    protected void drawMatrix(final Graphics g) {
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                final AlignmentNode tempNode = this.theMatrix[i][j];
                if (tempNode != null) {
                    tempNode.draw(g);
                }
                ++i;
            }
        }
    }
    
    protected void drawMatrixName(final Graphics g) {
        g.setColor(this.DEFAULT_NAMECOLOR);
        final int xPos = this.location.x + 5;
        final int yPos = this.location.y + g.getFontMetrics().getAscent();
        g.drawString(this.matrixName, xPos, yPos);
    }
    
    protected void drawXIndex(final Graphics g) {
        int xPos = this.location.x + this.nodeWidth + this.xGap + (this.aNodeWidth - this.nodeWidth) + this.nodeWidth / 2;
        final int yPos = this.location.y + g.getFontMetrics().getAscent() / 2 + this.nodeHeight + this.yGap;
        for (int i = 0; i < this.xSize; ++i) {
            if (i != this.xIndexHighlight) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(this.highlightColor);
            }
            g.drawString("" + i, xPos - g.getFontMetrics().stringWidth("" + i) / 2, yPos);
            xPos = xPos + this.aNodeWidth;
        }
    }
    
    protected void drawXLabel(final Graphics g) {
        for (int i = 0; i < this.xSize; ++i) {
            this.xNodes[i].draw(g);
        }
    }
    
    protected void drawYIndex(final Graphics g) {
        final int xPos = this.location.x + this.nodeWidth + this.xGap;
        int yPos = this.location.y + this.nodeHeight + this.yGap + (this.aNodeHeight - this.nodeHeight) + this.nodeHeight / 2 + g.getFontMetrics().getAscent() / 2;
        for (int i = 0; i < this.ySize; ++i) {
            if (i != this.yIndexHighlight) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(this.highlightColor);
            }
            g.drawString("" + i, xPos - g.getFontMetrics().stringWidth("" + i) / 2, yPos);
            yPos = yPos + this.aNodeHeight;
        }
    }
    
    protected void drawYLabel(final Graphics g) {
        for (int i = 0; i < this.ySize; ++i) {
            this.yNodes[i].draw(g);
        }
    }
    
    public boolean equals(final Selectable selectable) {
        if (!(selectable instanceof AlignmentMatrix)) {
            return false;
        }
        final AlignmentMatrix testS = (AlignmentMatrix)selectable;
        if (testS.getIdentifier() != this.getIdentifier()) {
            return false;
        }
        if (testS.xSize != this.xSize) {
            return false;
        }
        if (testS.ySize != this.ySize) {
            return false;
        }
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                final AlignmentNode one = this.get(i, j);
                final AlignmentNode two = testS.get(i, j);
                if (one != null && two != null) {
                    if (!one.equals(two)) {
                        return false;
                    }
                }
                else if (one != null || two != null) {
                    return false;
                }
                ++i;
            }
        }
        return true;
    }
    
    public AlignmentNode get(final int position) {
        return this.theMatrix[position % this.xSize][position / this.xSize];
    }
    
    public AlignmentNode get(final int xPos, final int yPos) {
        return this.theMatrix[xPos][yPos];
    }
    
    public int getArrowHeight() {
        return this.arrowHeight;
    }
    
    public int getArrowWidth() {
        return this.arrowWidth;
    }
    
    public boolean getDrawIndex() {
        return this.drawIndex;
    }
    
    public boolean getDrawNull() {
        return this.drawNull;
    }
    
    public int getHeight() {
        final AlignmentNode testNode = new AlignmentNode("", 0);
        if (this.isGapMode) {
            testNode.setBufferHeight(10);
        }
        final Node testNode2 = new Node("", 0);
        return testNode2.getHeight() + this.yGap + this.ySize * testNode.getRealHeight();
    }
    
    public Color getHighlightColor() {
        return this.highlightColor;
    }
    
    public int getIdentifier() {
        return this.sequenceNumber;
    }
    
    public boolean getIsGapMode() {
        return this.isGapMode;
    }
    
    public Selectable getItemAt(final Point point) {
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                if (this.theMatrix[i][j] != null && this.theMatrix[i][j].getItemAt(point) != null) {
                    return this.theMatrix[i][j];
                }
                ++i;
            }
        }
        return null;
    }
    
    public Point getLocation() {
        return this.location;
    }
    
    public int getMax() {
        return this.currentMax;
    }
    
    public int getMaxX() {
        return this.currentMaxX;
    }
    
    public int getMaxY() {
        return this.currentMaxY;
    }
    
    public int getShiftX() {
        return this.shiftX;
    }
    
    public int getShiftY() {
        return this.shiftY;
    }
    
    public int getWidth() {
        final AlignmentNode testNode = new AlignmentNode("", 0);
        final Node testNode2 = new Node("", 0);
        return testNode2.getWidth() + this.xGap + this.xSize * testNode.getRealWidth();
    }
    
    public int getXGap() {
        return this.xGap;
    }
    
    public String getXLabel() {
        return this.xLabel;
    }
    
    public int getXSize() {
        return this.xSize;
    }
    
    public int getYGap() {
        return this.yGap;
    }
    
    public String getYLabel() {
        return this.yLabel;
    }
    
    public int getYSize() {
        return this.ySize;
    }
    
    public void highlight() {
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                if (this.theMatrix[i][j] != null) {
                    this.theMatrix[i][j].highlight();
                }
                ++i;
            }
        }
        this.isHighlight = true;
    }
    
    protected void positionMatrix() {
        int xPos = this.location.x + this.nodeWidth + (this.aNodeWidth - this.nodeWidth - this.arrowWidth) + this.xGap;
        int yPos = this.location.y + this.nodeHeight + (this.aNodeHeight - this.nodeHeight - this.arrowHeight) + this.yGap;
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                final AlignmentNode tempNode = this.theMatrix[i][j];
                if (tempNode != null) {
                    tempNode.setX(xPos);
                    tempNode.setY(yPos);
                }
                xPos = xPos + this.aNodeWidth;
                ++i;
            }
            xPos = this.location.x + this.nodeWidth + (this.aNodeWidth - this.nodeWidth - this.arrowWidth) + this.xGap;
            yPos = yPos + this.aNodeHeight;
        }
    }
    
    protected void positionXLabel() {
        final int yPos = this.location.y;
        int xPos = this.location.x + this.aNodeWidth + this.xGap;
        for (int i = 0; i < this.xSize; ++i) {
            this.xNodes[i].setPosition(new Point(xPos, yPos));
            xPos = xPos + this.aNodeWidth;
        }
    }
    
    protected void positionYLabel() {
        final int xPos = this.location.x;
        int yPos = this.location.y + this.aNodeHeight + this.yGap;
        for (int i = 0; i < this.ySize; ++i) {
            this.yNodes[i].setPosition(new Point(xPos, yPos));
            yPos = yPos + this.aNodeHeight;
        }
    }
    
    public void printMatrix() {
        System.out.println("X Label = " + this.xLabel);
        System.out.println("Y Label = " + this.yLabel);
        System.out.println("X Size = " + this.xSize);
        System.out.println("Y Size = " + this.ySize);
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                final AlignmentNode temp = this.get(i, j);
                if (temp == null) {
                    System.out.print("   X ");
                }
                else {
                    if (temp.getTraceUp()) {
                        System.out.print("|");
                    }
                    else {
                        System.out.print(" ");
                    }
                    if (temp.getTraceLeft()) {
                        System.out.print("-");
                    }
                    else {
                        System.out.print(" ");
                    }
                    if (temp.getTraceDiag()) {
                        System.out.print("\\");
                    }
                    else {
                        System.out.print(" ");
                    }
                    System.out.print("" + this.valueAt(i, j) + " ");
                }
                ++i;
            }
            System.out.println();
        }
    }
    
    protected void restoreMatrix() {
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                this.theMatrix[i][j] = this.saveMatrix[i][j];
                ++i;
            }
        }
    }
    
    protected void saveMatrix() {
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                this.saveMatrix[i][j] = this.theMatrix[i][j];
                ++i;
            }
        }
    }
    
    protected void setAllArrows() {
        final Node testNode = new Node("", 0);
        this.nodeWidth = testNode.getWidth();
        this.nodeHeight = testNode.getHeight();
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                final AlignmentNode temp = this.theMatrix[i][j];
                if (temp != null) {
                    temp.setArrowWidth(this.arrowWidth);
                    temp.setArrowHeight(this.arrowHeight);
                    if (this.isGapMode) {
                        temp.setHaveChoice(true);
                        temp.setBufferHeight(10);
                    }
                }
                ++i;
            }
        }
        final AlignmentNode tempANode = this.theMatrix[0][0];
        this.aNodeHeight = tempANode.getRealHeight();
        this.aNodeWidth = tempANode.getRealWidth();
    }
    
    public void setAllColor(final Color color, final Color color2) {
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                if (this.theMatrix[i][j] != null) {
                    this.theMatrix[i][j].setBackgroundColor(color);
                    this.theMatrix[i][j].setArrowColor(color2);
                }
                ++i;
            }
        }
    }
    
    public void setArrowHeight(final int x) {
        this.arrowHeight = x;
    }
    
    public void setArrowWidth(final int y) {
        this.arrowWidth = y;
    }
    
    public void setDrawIndex(final boolean state) {
        this.drawIndex = state;
    }
    
    public void setDrawInternalBox(final boolean state) {
        this.drawInternalBox = state;
    }
    
    public void setDrawName(final boolean state) {
        this.drawName = state;
    }
    
    public void setDrawNull(final boolean state) {
        this.drawNull = state;
    }
    
    public void setHighlightColor(final Color c) {
        this.highlightColor = c;
    }
    
    public void setInternalBoxCoords(final int x, final int y) {
        this.internalBoxX = x;
        this.internalBoxY = y;
    }
    
    public void setInternalBoxHeight(final int h) {
        this.internalBoxHeight = h;
    }
    
    public void setInternalBoxSize(final int w, final int h) {
        this.internalBoxWidth = w;
        this.internalBoxHeight = h;
    }
    
    public void setInternalBoxWidth(final int w) {
        this.internalBoxWidth = w;
    }
    
    public void setInternalBoxX(final int x) {
        this.internalBoxX = x;
    }
    
    public void setInternalBoxY(final int y) {
        this.internalBoxY = y;
    }
    
    public void setIsGapMode(final boolean state) {
        this.isGapMode = state;
    }
    
    public void setIsSelected(final boolean state) {
        this.isSelected = state;
    }
    
    public void setLabelColor(final Color c) {
        this.labelColor = c;
    }
    
    public void setLocation(final int xPos, final int yPos) {
        this.location.x = xPos;
        this.location.y = yPos;
    }
    
    public void setLocation(final Point newPnt) {
        this.location = newPnt;
    }
    
    public void setMatrixName(final String name) {
        this.matrixName = new String(name);
    }
    
    public void setNodeBackgroundColor(final Color c) {
        this.nodeBackgroundColor = c;
    }
    
    public void setNodeHighlightColor(final Color c) {
        this.nodeHighlightColor = c;
    }
    
    public void setSequence(final int x) {
        this.sequenceNumber = x;
    }
    
    public void setShiftX(final int x) {
        this.shiftX = x;
    }
    
    public void setShiftY(final int y) {
        this.shiftY = y;
    }
    
    public void setXGap(final int x) {
        this.xGap = x;
    }
    
    public void setXIndexHighlight(final int x) {
        this.xIndexHighlight = x;
    }
    
    public void setXLabel(final String label) {
        this.xLabel = label;
        for (int i = 0; i < this.shiftX; ++i) {
            this.xNodes[i] = new Node("", 0);
            this.xNodes[i].setBackgroundColor(this.labelColor);
        }
        for (int i = this.shiftX; i < this.xSize; ++i) {
            Node tempN;
            if (i - this.shiftX < label.length()) {
                tempN = new Node("" + label.charAt(i - this.shiftX), 0);
            }
            else {
                tempN = new Node("", 0);
            }
            tempN.setBackgroundColor(this.labelColor);
            this.xNodes[i] = tempN;
        }
    }
    
    public void setYGap(final int y) {
        this.yGap = y;
    }
    
    public void setYIndexHighlight(final int y) {
        this.yIndexHighlight = y;
    }
    
    public void setYLabel(final String label) {
        this.yLabel = label;
        for (int i = 0; i < this.shiftY; ++i) {
            this.yNodes[i] = new Node("", 0);
            this.yNodes[i].setBackgroundColor(this.labelColor);
        }
        for (int i = this.shiftY; i < this.ySize; ++i) {
            Node tempN;
            if (i - this.shiftY < label.length()) {
                tempN = new Node("" + label.charAt(i - this.shiftY), 0);
            }
            else {
                tempN = new Node("", 0);
            }
            tempN.setBackgroundColor(this.labelColor);
            this.yNodes[i] = tempN;
        }
    }
    
    public void unHighlight() {
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                if (this.theMatrix[i][j] != null) {
                    this.theMatrix[i][j].unHighlight();
                }
                ++i;
            }
        }
        this.isHighlight = false;
    }
    
    public void unHighlightTrace() {
        for (int j = 0; j < this.ySize; ++j) {
            int i = 0;
            while (i < this.xSize) {
                if (this.theMatrix[i][j] != null) {
                    this.theMatrix[i][j].unHighlightLeft();
                    this.theMatrix[i][j].unHighlightUp();
                    this.theMatrix[i][j].unHighlightDiag();
                    this.theMatrix[i][j].unHighlightA();
                    this.theMatrix[i][j].unHighlightB();
                    this.theMatrix[i][j].unHighlightC();
                    this.theMatrix[i][j].setIsDisabled(false);
                }
                ++i;
            }
        }
    }
    
    public int valueAt(final int position) {
        final AlignmentNode temp = this.get(position);
        if (temp == null) {
            return 0;
        }
        final Object obj1 = temp.getObject();
        if (obj1 instanceof Integer) {
            return ((Integer)temp.getObject()).intValue();
        }
        if (((String)obj1).equals("-Inf")) {
            return -5000;
        }
        return 5000;
    }
    
    public int valueAt(final int xPos, final int yPos) {
        final AlignmentNode temp = this.get(xPos, yPos);
        if (temp == null) {
            return 0;
        }
        final Object obj1 = temp.getObject();
        if (obj1 instanceof Integer) {
            return ((Integer)temp.getObject()).intValue();
        }
        if (((String)obj1).equals("-Inf")) {
            return -5000;
        }
        return 5000;
    }
}
