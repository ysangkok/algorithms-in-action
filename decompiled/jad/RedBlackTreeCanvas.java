// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RedBlackTreeCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;
import localization.Messages;

public class RedBlackTreeCanvas extends AlgorithmCanvas
{

    public RedBlackTreeCanvas()
    {
        saveLine = new Vector();
        animateLine = new Vector();
        nodeWidth = 20;
        xTextPadding = 30;
        yBuffer = 30;
        xBuffer = 30;
        showAs234Only = false;
        showTute = false;
        showCode = false;
        highLightedLine = 0;
    }

    public void animate234()
    {
        Graphics g = getGraphics();
        Hashtable currentPositions = getPositions(balancedHierarchyTree);
        if(oldPositions != null)
        {
            if(growNode != null)
            {
                Point currentPoint = (Point)oldPositions.get(new Integer(growNode.getIdentifier()));
                growNode.setPosition(currentPoint);
                addTween(new DeleteTween(null, growNode, numberOfTweenSteps));
            }
            if(animationNode != null)
            {
                Point oldPoint = (Point)oldPositions.get(new Integer(animationNode.getIdentifier()));
                Point currentPoint = (Point)currentPositions.get(new Integer(animationNode.getIdentifier()));
                if(oldPoint != null && currentPoint != null && (oldPoint.x != currentPoint.x || oldPoint.y != currentPoint.y))
                    addTween(new MoveTween(null, animationNode, oldPoint, currentPoint, false, numberOfTweenSteps));
            }
        }
        if(oldPositions != null)
        {
            MultipleTween moveTweens = getMoveTweens(balancedHierarchyTree, oldPositions, currentPositions);
            if(moveTweens.getNumberOfTweens() > 0)
                addTween(moveTweens);
        }
        oldPositions = currentPositions;
        MultipleTween multiTweens = redBlackTree.generateTweens(this, balancedHierarchyTree, numberOfTweenSteps);
        if(multiTweens != null)
            addTween(multiTweens);
        if(tweens.getNumberOfTweens() > 0)
            tweens.run();
    }

    public void animateRB()
    {
        Graphics g = getGraphics();
        if(g != null)
            initaliseLines(g, 2 * yBuffer + elementArray.getHeight());
        Hashtable currentPositions = getPositions(hierarchyTree);
        if(oldPositions != null)
        {
            MultipleTween moveTweens = getMoveTweens(hierarchyTree, oldPositions, currentPositions);
            if(moveTweens.getNumberOfTweens() > 0)
                addTween(moveTweens);
        }
        oldPositions = currentPositions;
        if(tweens.getNumberOfTweens() > 0)
            tweens.run();
    }

    public void animateTute()
    {
        Graphics g = getGraphics();
        Hashtable currentPositions = getPositions(tuteTree);
        if(oldPositions != null)
        {
            MultipleTween moveTweens2 = getLineTweens(tuteTree, oldPositions, currentPositions);
            if(moveTweens2.getNumberOfTweens() > 0)
                addTween(moveTweens2);
            MultipleTween moveTweens = getMoveTweens(tuteTree, oldPositions, currentPositions);
            if(moveTweens.getNumberOfTweens() > 0)
                addTween(moveTweens);
        }
        if(tweens.getNumberOfTweens() > 0)
            tweens.run();
        oldPositions = currentPositions;
    }

    public void display234(Graphics g)
    {
        if(growNode != null)
            growNode.draw(g);
        if(balancedHierarchyTree != null)
            balancedHierarchyTree.draw(g);
        if(animationNode != null)
            animationNode.draw(g);
    }

    public void displayAlgorithm(Graphics g)
    {
        if(redBlackTree != null)
        {
            if(!showAs234Only && !showTute && !showCode)
                displayRB(g);
            if(showAs234Only && !showTute)
                if(redBlackTree.update234)
                    display234(g);
                else
                    g.drawString(Messages.getString("RedBlackTreeCanvas.22"), 20, 20);
            if(!showAs234Only && showTute)
                displayTute(g);
            if(showCode)
                displayCode(g);
        }
    }

    public void displayCode(Graphics g)
    {
        int XPos = 30;
        int YPos = 30;
        int Y_INC = 15;
        int lineCounter = 1;
        for(int i = 0; i < rotateLeft.length; i++)
        {
            String tempStr = rotateLeft[i];
            if(tempStr.equals("}"))
                XPos -= 30;
            if(lineCounter == highLightedLine)
            {
                g.setColor(CodeCanvas.DEFAULT_HIGHLIGHT_COLOR);
                g.fillRect(XPos, YPos - Y_INC, g.getFontMetrics().stringWidth(tempStr), g.getFontMetrics().getHeight());
                g.setColor(Color.black);
            }
            g.drawString(tempStr, XPos, YPos);
            if(tempStr.equals("{"))
                XPos += 30;
            YPos += Y_INC;
            lineCounter++;
        }

        YPos += Y_INC;
        for(int i = 0; i < rotateRight.length; i++)
        {
            String tempStr = rotateRight[i];
            if(tempStr.equals("}"))
                XPos -= 30;
            if(lineCounter == highLightedLine)
            {
                g.setColor(CodeCanvas.DEFAULT_HIGHLIGHT_COLOR);
                g.fillRect(XPos, YPos - Y_INC, g.getFontMetrics().stringWidth(tempStr), g.getFontMetrics().getHeight());
                g.setColor(Color.black);
            }
            g.drawString(tempStr, XPos, YPos);
            if(tempStr.equals("{"))
                XPos += 30;
            YPos += Y_INC;
            lineCounter++;
        }

    }

    public void displayRB(Graphics g)
    {
        if(ptrLine != null)
            ptrLine.draw(g);
        if(parentLine != null)
            parentLine.draw(g);
        if(grandParentLine != null)
            grandParentLine.draw(g);
        if(elementArray != null)
            elementArray.draw(g);
        if(hierarchyTree != null)
            hierarchyTree.draw(g);
    }

    public void displayTute(Graphics g)
    {
        if(tuteTree != null)
            tuteTree.draw(g);
        for(int i = 0; i < animateLine.size(); i++)
        {
            Line aLine = (Line)animateLine.elementAt(i);
            aLine.draw(g);
        }

    }

    protected HierarchyTree findTree(HierarchyTree root, int id)
    {
        if(root == null)
            return null;
        Node node = root.getNodeAt(1);
        if(node.getIdentifier() == id)
            return root;
        HierarchyTree a = findTree(root.getLeftChild(), id);
        if(a != null)
            return a;
        HierarchyTree b = findTree(root.getRightChild(), id);
        if(b != null)
            return b;
        else
            return null;
    }

    protected MultipleTween getLineTweens(HierarchyTree hTree, Hashtable oldPositions, Hashtable currentPositions)
    {
        MultipleTween multipleTweens = new MultipleTween(this);
        animateLine.removeAllElements();
        if(redBlackTree.getRemoveSaveLine())
            saveLine.removeAllElements();
        for(int i = 0; i < saveLine.size(); i++)
            animateLine.addElement(saveLine.elementAt(i));

        getLineTweens(hTree, oldPositions, currentPositions, multipleTweens);
        return multipleTweens;
    }

    protected void getLineTweens(HierarchyTree hTree, Hashtable oldPositions, Hashtable currentPositions, MultipleTween multipleTweens)
    {
        if(hTree != null)
        {
            for(int i = 0; i < lineMoveRequests.size(); i++)
            {
                LineMoveRequest lineMoveRequest = (LineMoveRequest)lineMoveRequests.elementAt(i);
                int toLineId = lineMoveRequest.getToPositionInsertedId();
                int fromLineId = lineMoveRequest.getFromPositionInsertedId();
                int parentLineId = lineMoveRequest.getParentPositionInsertedId();
                if(toLineId < 0)
                {
                    Point fromPoint = (Point)oldPositions.get(new Integer(fromLineId));
                    HierarchyTree theTree = findTree(hTree, fromLineId);
                    Line tempLine = theTree.getLine().copy();
                    Node node = theTree.getNodeAt(1);
                    Point from = new Point(tempLine.getIntX2(), tempLine.getIntY2());
                    Point to;
                    if(toLineId == -50)
                        to = new Point(tempLine.getIntX2() - node.getWidth(), tempLine.getIntY2() + 35);
                    else
                        to = new Point(tempLine.getIntX2() + node.getWidth(), tempLine.getIntY2() + 35);
                    Line tempLine2 = theTree.getLine().copy();
                    tempLine2.setEndPosition(to);
                    MoveTween moveTween = new MoveTween(null, tempLine, from, to, false, numberOfTweenSteps);
                    multipleTweens.add(moveTween);
                    animateLine.addElement(tempLine);
                    saveLine.addElement(tempLine2);
                    continue;
                }
                if(fromLineId < 0)
                {
                    Point toPoint = (Point)oldPositions.get(new Integer(toLineId));
                    HierarchyTree theTree = findTree(hTree, parentLineId);
                    Line tempLine;
                    Line tempLine2;
                    if(fromLineId == -50)
                    {
                        tempLine = theTree.getLeftChild().getLine().copy();
                        tempLine2 = theTree.getLeftChild().getLine().copy();
                    } else
                    {
                        tempLine = theTree.getRightChild().getLine().copy();
                        tempLine2 = theTree.getRightChild().getLine().copy();
                    }
                    Node node = theTree.getNodeAt(1);
                    Line parentLine = theTree.getLine().copy();
                    Point from;
                    if(fromLineId == -50)
                        from = new Point(parentLine.getIntX2() - node.getWidth(), parentLine.getIntY2() + 40);
                    else
                        from = new Point(parentLine.getIntX2() + node.getWidth(), parentLine.getIntY2() + 40);
                    Point to = new Point(toPoint.x + node.getWidth() / 2, toPoint.y);
                    tempLine.setEndPosition(from);
                    tempLine2.setEndPosition(to);
                    tempLine.setColor(RedBlackTree.tuteHighLightColor);
                    tempLine2.setColor(RedBlackTree.tuteHighLightColor);
                    MoveTween moveTween = new MoveTween(null, tempLine, from, to, false, numberOfTweenSteps);
                    multipleTweens.add(moveTween);
                    animateLine.addElement(tempLine);
                    saveLine.addElement(tempLine2);
                } else
                {
                    Point fromPoint = (Point)oldPositions.get(new Integer(lineMoveRequest.getFromPositionInsertedId()));
                    Point toPoint = (Point)oldPositions.get(new Integer(lineMoveRequest.getToPositionInsertedId()));
                    HierarchyTree theTree = findTree(hTree, lineMoveRequest.getFromPositionInsertedId());
                    Line tempLine = theTree.getLine().copy();
                    Node node = theTree.getNodeAt(1);
                    Point from = new Point(tempLine.getIntX2(), tempLine.getIntY2());
                    Point to = new Point(toPoint.x + node.getWidth() / 2, toPoint.y);
                    Line tempLine2 = theTree.getLine().copy();
                    tempLine2.setEndPosition(to);
                    MoveTween moveTween = new MoveTween(null, tempLine, from, to, false, numberOfTweenSteps);
                    multipleTweens.add(moveTween);
                    animateLine.addElement(tempLine);
                    saveLine.addElement(tempLine2);
                }
            }

        }
    }

    protected MultipleTween getMoveTweens(HierarchyTree hTree, Hashtable oldPositions, Hashtable currentPositions)
    {
        MultipleTween multipleTweens = new MultipleTween(this);
        getMoveTweens(hTree, oldPositions, currentPositions, multipleTweens);
        return multipleTweens;
    }

    protected void getMoveTweens(HierarchyTree hTree, Hashtable oldPositions, Hashtable currentPositions, MultipleTween multipleTweens)
    {
        if(hTree != null)
        {
            Node node = hTree.getNodeAt(1);
            Node tNode = hTree.getNodeAt(2);
            if(node != null && tNode == null)
            {
                Point oldPoint = (Point)oldPositions.get(new Integer(node.getIdentifier()));
                Point currentPoint = (Point)currentPositions.get(new Integer(node.getIdentifier()));
                if(oldPoint != null && currentPoint != null && (oldPoint.x != currentPoint.x || oldPoint.y != currentPoint.y))
                    multipleTweens.add(new MoveTween(null, hTree, oldPoint, currentPoint, false, numberOfTweenSteps));
            }
            Vector children = hTree.getChildren();
            for(int i = 0; i < children.size(); i++)
            {
                HierarchyTree childHTree = (HierarchyTree)(HierarchyTree)children.elementAt(i);
                getMoveTweens(childHTree, oldPositions, currentPositions, multipleTweens);
            }

        }
    }

    protected Hashtable getPositions(HierarchyTree hTree)
    {
        Hashtable positions = new Hashtable();
        getPositions(hTree, positions);
        return positions;
    }

    protected void getPositions(HierarchyTree hTree, Hashtable positions)
    {
        if(hTree != null)
        {
            Vector nodes = hTree.getNodes();
            for(int v = 0; v < nodes.size(); v++)
            {
                Node node = (Node)nodes.elementAt(v);
                if(node != null && node.getIsVisible())
                {
                    int w = node.getWidth();
                    positions.put(new Integer(node.getIdentifier()), new Point(hTree.getX() + v * w, hTree.getY()));
                }
            }

            Vector children = hTree.getChildren();
            for(int i = 0; i < children.size(); i++)
            {
                HierarchyTree childHTree = (HierarchyTree)(HierarchyTree)children.elementAt(i);
                getPositions(childHTree, positions);
            }

        }
    }

    protected void handleColorSelection(ColorSelection colorselection)
    {
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    protected void initaliseLines(Graphics g, int yPoint)
    {
        int xPosition = getSize().width / 2 - (g.getFontMetrics().stringWidth((new StringBuilder()).append(PTR_LABEL).append(PARENT_LABEL).append(GRAND_PARENT_LABEL).append(GREAT_GRAND_PARENT_LABEL).toString()) + 3 * xTextPadding) / 2;
        ptrLine = parentLine = grandParentLine = greatGrandParentLine = null;
        ptrLine = initialiseLine(g, new Point(xPosition, yPoint), redBlackTree.getPtrNode(), PTR_LABEL);
        xPosition += g.getFontMetrics().stringWidth(PTR_LABEL) + xTextPadding;
        parentLine = initialiseLine(g, new Point(xPosition, yPoint), redBlackTree.getParentNode(), PARENT_LABEL);
        xPosition += g.getFontMetrics().stringWidth(PARENT_LABEL) + xTextPadding;
        grandParentLine = initialiseLine(g, new Point(xPosition, yPoint), redBlackTree.getGrandParentNode(), GRAND_PARENT_LABEL);
        xPosition += g.getFontMetrics().stringWidth(GRAND_PARENT_LABEL) + xTextPadding;
    }

    protected Line initialiseLine(Graphics g, Point from, HierarchyTree tree, String label)
    {
        if(tree != null)
        {
            Line line = new Line(from.x, from.y, tree.getPosition().x + nodeWidth / 2, tree.getPosition().y);
            line.showArrowHead(true);
            line.setLabel(label);
            line.setDistanceFromStartForArrowHead(-3);
            line.setDistanceFromStartForLabel(-1);
            line.setXLabelOffset(-1 * (g.getFontMetrics().stringWidth(label) / 2));
            line.setColor(Color.gray);
            return line;
        } else
        {
            return null;
        }
    }

    public void process234()
    {
        balancedHierarchyTree = redBlackTree.getBalancedHierarchyTree(nodeWidth);
        int width = 0;
        int height = 0;
        if(balancedHierarchyTree != null)
        {
            Rectangle rectangle = balancedHierarchyTree.getRectangle();
            width = rectangle.width;
            height = rectangle.height;
        }
        width += 2 * xBuffer;
        height += 2 * yBuffer;
        setSize(width, height);
        if(balancedHierarchyTree != null)
            balancedHierarchyTree.plantTree(getSize().width / 2 - nodeWidth / 2, yBuffer);
    }

    public void processCode()
    {
        highLightedLine = ((RotateAnimation)redBlackTree.getAnimThread()).getHighLight();
    }

    public void processRB()
    {
        elementArray = redBlackTree.getDataElementArray();
        elementArray.setAllHaveSameWidth(true);
        elementArray.setElementWidth(nodeWidth);
        elementArray.setColumGap(0);
        hierarchyTree = redBlackTree.getHierarchyTree(nodeWidth);
        if(hierarchyTree != null)
            hierarchyTree.plantTree(getSize().width / 2 - nodeWidth / 2, 3 * yBuffer + elementArray.getHeight());
        int width = 0;
        int height = 0;
        if(hierarchyTree != null)
        {
            Rectangle rectangle = hierarchyTree.getRectangle();
            width = Math.max(elementArray.getWidth(), rectangle.width);
            height = 3 * yBuffer + rectangle.height;
        } else
        {
            width = elementArray.getWidth();
            height = yBuffer + elementArray.getHeight();
        }
        width += 2 * xBuffer;
        height += 2 * yBuffer;
        setSize(width, height);
        elementArray.setLocation(getSize().width / 2 - elementArray.getWidth() / 2, yBuffer);
        if(hierarchyTree != null)
            hierarchyTree.plantTree(getSize().width / 2 - nodeWidth / 2, 3 * yBuffer + elementArray.getHeight());
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        removeAllSelectables();
        if(e.paramObj != null)
        {
            redBlackTree = (RedBlackTree)(RedBlackTree)e.paramObj;
            removeSelectionListener(NodeSelection.getInstance(redBlackTree));
            addSelectionListener(NodeSelection.getInstance(redBlackTree));
            if(!showAs234Only && !showTute && !showCode)
            {
                processRB();
                animateRB();
            }
            if(showAs234Only && !showTute)
                process234();
            if(!showAs234Only && showTute)
            {
                processTute();
                animateTute();
            }
            if(showCode)
                processCode();
            redBlackTree.removeAllAnimationRequests();
            repaint();
        }
    }

    public void processTute()
    {
        tuteTree = redBlackTree.getTuteTree(nodeWidth);
        lineMoveRequests = redBlackTree.getLineMoveRequests();
        if(tuteTree != null)
            tuteTree.plantTree(getSize().width / 2 - nodeWidth / 2, yBuffer);
        int width = 0;
        int height = 0;
        if(tuteTree != null)
        {
            Rectangle rectangle = tuteTree.getRectangle();
            width = rectangle.width;
            height = rectangle.height;
        }
        width += 2 * xBuffer;
        height += 2 * yBuffer;
        setSize(width, height);
        if(tuteTree != null)
            tuteTree.plantTree(getSize().width / 2 - nodeWidth / 2, yBuffer);
        if(tuteTree != null)
            addSelectable(tuteTree);
    }

    public void setShowAs234Only(boolean state)
    {
        showAs234Only = state;
    }

    public void setShowCode(boolean state)
    {
        showCode = state;
    }

    public void setShowTute(boolean state)
    {
        showTute = state;
    }

    private static final long serialVersionUID = 0xc894a0a8d1bfbe13L;
    protected static final String PTR_LABEL = Messages.getString("RedBlackTreeCanvas.0");
    protected static final String PARENT_LABEL = Messages.getString("RedBlackTreeCanvas.1");
    protected static final String GRAND_PARENT_LABEL = Messages.getString("RedBlackTreeCanvas.2");
    protected static final String GREAT_GRAND_PARENT_LABEL = Messages.getString("RedBlackTreeCanvas.3");
    protected static final String rotateRight[] = {
        Messages.getString("RedBlackTreeCanvas.4"), "{", Messages.getString("RedBlackTreeCanvas.6"), Messages.getString("RedBlackTreeCanvas.7"), Messages.getString("RedBlackTreeCanvas.8"), Messages.getString("RedBlackTreeCanvas.9"), "}"
    };
    protected static final String rotateLeft[] = {
        Messages.getString("RedBlackTreeCanvas.11"), "{", Messages.getString("RedBlackTreeCanvas.13"), Messages.getString("RedBlackTreeCanvas.14"), Messages.getString("RedBlackTreeCanvas.15"), Messages.getString("RedBlackTreeCanvas.16"), "}"
    };
    protected RedBlackTree redBlackTree;
    protected HierarchyTree hierarchyTree;
    protected HierarchyTree tuteTree;
    protected HierarchyTree balancedHierarchyTree;
    protected Vector saveLine;
    protected Vector animateLine;
    protected Vector lineMoveRequests;
    protected Node animationNode;
    protected Node growNode;
    protected ElementArray elementArray;
    protected Hashtable oldPositions;
    protected int nodeWidth;
    protected int xTextPadding;
    protected int yBuffer;
    protected int xBuffer;
    protected Line ptrLine;
    protected Line parentLine;
    protected Line grandParentLine;
    protected Line greatGrandParentLine;
    protected boolean showAs234Only;
    protected boolean showTute;
    protected boolean showCode;
    protected int highLightedLine;

}
