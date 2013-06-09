// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tree234Canvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;
import localization.Messages;

public class Tree234Canvas extends AlgorithmCanvas
{

    public Tree234Canvas()
    {
        nodeWidth = 20;
        xTextPadding = 30;
        yBuffer = 30;
        xBuffer = 30;
        showAs234Only = true;
    }

    public void setShowAs234Only(boolean state)
    {
        showAs234Only = state;
    }

    public void display234(Graphics g)
    {
        if(elementArray != null)
            elementArray.draw(g);
        if(growNode != null)
            growNode.draw(g);
        if(balancedHierarchyTree != null)
            balancedHierarchyTree.draw(g);
        if(animationNode != null)
            animationNode.draw(g);
    }

    public void displayRB(Graphics g)
    {
        if(ptrLine != null)
            ptrLine.draw(g);
        if(parentLine != null)
            parentLine.draw(g);
        if(grandParentLine != null)
            grandParentLine.draw(g);
        if(hierarchyTree != null)
            hierarchyTree.draw(g);
    }

    public void displayAlgorithm(Graphics g)
    {
        if(redBlackTree != null)
            if(!showAs234Only)
                displayRB(g);
            else
            if(redBlackTree.update234)
                display234(g);
            else
                g.drawString(Messages.getString("Tree234Canvas.4"), 20, 20);
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
                Point oldPoint = (Point)(Point)oldPositions.get(new Integer(node.getIdentifier()));
                Point currentPoint = (Point)(Point)currentPositions.get(new Integer(node.getIdentifier()));
                if(oldPoint != null && currentPoint != null && (oldPoint.x != currentPoint.x || oldPoint.y != currentPoint.y))
                    multipleTweens.add(new MoveTween(null, hTree, oldPoint, currentPoint, false, numberOfTweenSteps / 3));
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

    protected void initaliseLines(Graphics g, int yPoint)
    {
        int xPosition = getSize().width / 2 - (g.getFontMetrics().stringWidth((new StringBuilder()).append("Ptr").append(PARENT_LABEL).append(GRAND_PARENT_LABEL).append(GREAT_GRAND_PARENT_LABEL).toString()) + 3 * xTextPadding) / 2;
        ptrLine = parentLine = grandParentLine = greatGrandParentLine = null;
        ptrLine = initialiseLine(g, new Point(xPosition, yPoint), redBlackTree.getPtrNode(), "Ptr");
        xPosition += g.getFontMetrics().stringWidth("Ptr") + xTextPadding;
        parentLine = initialiseLine(g, new Point(xPosition, yPoint), redBlackTree.getParentNode(), PARENT_LABEL);
        xPosition += g.getFontMetrics().stringWidth(PARENT_LABEL) + xTextPadding;
        grandParentLine = initialiseLine(g, new Point(xPosition, yPoint), redBlackTree.getGrandParentNode(), GRAND_PARENT_LABEL);
        xPosition += g.getFontMetrics().stringWidth(GRAND_PARENT_LABEL) + xTextPadding;
    }

    public void processRB()
    {
        hierarchyTree = redBlackTree.getHierarchyTree(nodeWidth);
        if(hierarchyTree != null)
            hierarchyTree.plantTree(getSize().width / 2 - nodeWidth / 2, yBuffer);
        int width = 0;
        int height = 0;
        if(hierarchyTree != null)
        {
            Rectangle rectangle = hierarchyTree.getRectangle();
            width = rectangle.width;
            height = rectangle.height;
        }
        width += 2 * xBuffer;
        height += 2 * yBuffer;
        setSize(width, height);
        if(hierarchyTree != null)
            hierarchyTree.plantTree(getSize().width / 2 - nodeWidth / 2, 2 * yBuffer);
    }

    public void animateRB()
    {
        Graphics g = getGraphics();
        if(g != null)
            initaliseLines(g, yBuffer);
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

    public void process234()
    {
        elementArray = redBlackTree.getDataElementArray();
        elementArray.setAllHaveSameWidth(true);
        elementArray.setElementWidth(nodeWidth);
        elementArray.setColumGap(0);
        balancedHierarchyTree = redBlackTree.getBalancedHierarchyTree(nodeWidth);
        animationNode = redBlackTree.getAnimationNode();
        growNode = redBlackTree.getGrowNode();
        int width = 0;
        int height = 0;
        if(balancedHierarchyTree != null)
        {
            Rectangle rectangle = balancedHierarchyTree.getRectangle();
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
        if(balancedHierarchyTree != null)
            balancedHierarchyTree.plantTree(getSize().width / 2 - nodeWidth / 2, 3 * yBuffer + elementArray.getHeight());
    }

    public void animate234()
    {
        Graphics g = getGraphics();
        Hashtable currentPositions = getPositions(balancedHierarchyTree);
        if(oldPositions != null)
        {
            if(growNode != null)
            {
                Point currentPoint = (Point)(Point)oldPositions.get(new Integer(growNode.getIdentifier()));
                growNode.setPosition(currentPoint);
                addTween(new DeleteTween(null, growNode, numberOfTweenSteps / 3));
            }
            if(animationNode != null)
            {
                Point oldPoint = (Point)(Point)oldPositions.get(new Integer(animationNode.getIdentifier()));
                Point currentPoint = (Point)(Point)currentPositions.get(new Integer(animationNode.getIdentifier()));
                if(oldPoint != null && currentPoint != null && (oldPoint.x != currentPoint.x || oldPoint.y != currentPoint.y))
                    addTween(new MoveTween(null, animationNode, oldPoint, currentPoint, false, numberOfTweenSteps / 3));
            }
        }
        if(oldPositions != null)
        {
            MultipleTween moveTweens = getMoveTweens(balancedHierarchyTree, oldPositions, currentPositions);
            if(moveTweens.getNumberOfTweens() > 0)
                addTween(moveTweens);
        }
        oldPositions = currentPositions;
        MultipleTween multiTweens = redBlackTree.generateTweens(this, balancedHierarchyTree, numberOfTweenSteps / 3);
        if(multiTweens != null)
            addTween(multiTweens);
        if(tweens.getNumberOfTweens() > 0)
            tweens.run();
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            redBlackTree = (Tree234Tree)(Tree234Tree)e.paramObj;
            if(!showAs234Only)
            {
                processRB();
            } else
            {
                process234();
                animate234();
            }
            redBlackTree.removeAllAnimationRequests();
            repaint();
        }
    }

    protected void handleColorSelection(ColorSelection colorselection)
    {
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    protected Tree234Tree redBlackTree;
    protected HierarchyTree hierarchyTree;
    protected HierarchyTree balancedHierarchyTree;
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
    private final int SPEED_UP_FACTOR = 3;
    protected static final String PTR_LABEL = "Ptr";
    protected static final String PARENT_LABEL = Messages.getString("Tree234Canvas.1");
    protected static final String GRAND_PARENT_LABEL = Messages.getString("Tree234Canvas.2");
    protected static final String GREAT_GRAND_PARENT_LABEL = Messages.getString("Tree234Canvas.3");

}
