// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTreeCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.util.Vector;
import localization.Messages;

public class PatriciaTreeCanvas extends AlgorithmCanvas
{
    protected class NewNode
        implements Moveable
    {

        protected void drawNewNode(Graphics g)
        {
            Point bodyLocation = currentPosition;
            body.setPosition(bodyLocation);
            body.draw(g);
            Point itemLocation = new Point();
            itemLocation.x = bodyLocation.x + body.getWidth();
            itemLocation.y = bodyLocation.y;
            dataNode.setPosition(itemLocation);
            dataNode.draw(g);
            if(isDrawLabel)
                g.drawString(Messages.getString("PatriciaTreeCanvas.16"), itemLocation.x - g.getFontMetrics().stringWidth(Messages.getString("PatriciaTreeCanvas.17")) / 2, itemLocation.y);
            int width = body.getWidth();
            int height = body.getHeight();
            if(isSelfLinkLeft != null)
                if(isSelfLinkLeft.booleanValue())
                {
                    int startAngle = 90;
                    int arcAngle = 270;
                    int x = bodyLocation.x - width / 2;
                    int y = bodyLocation.y + height / 2;
                    drawArc(g, false, x, y, width, height, startAngle, arcAngle);
                    drawArrowHead(g, new Point(x + width / 2, y), 15);
                } else
                {
                    Line line = new Line(bodyLocation.x, bodyLocation.y + height, bodyLocation.x - width / 2, bodyLocation.y + height + width / 2);
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                }
            if(isSelfLinkRight != null)
                if(isSelfLinkRight.booleanValue())
                {
                    int startAngle = 180;
                    int arcAngle = 270;
                    int x = (itemLocation.x + dataNode.getWidth()) - width / 2;
                    int y = itemLocation.y + height / 2;
                    drawArrowHead(g, new Point(x + width / 2, y), 175);
                    drawArc(g, false, x, y, width, height, startAngle, arcAngle);
                } else
                {
                    Line line = new Line(itemLocation.x + width, itemLocation.y + height, itemLocation.x + width + width / 2, itemLocation.y + height + width / 2);
                    line.showArrowHead(true);
                    line.setDistanceFromStartForArrowHead(-3);
                    line.draw(g);
                }
        }

        public HierarchyTree getHierarchyTree()
        {
            return patriciaNode.getHierarchyTree();
        }

        public Point getPosition()
        {
            return currentPosition;
        }

        public int getX()
        {
            return currentPosition.x;
        }

        public int getY()
        {
            return currentPosition.y;
        }

        private void init(PatriciaNode patriciaNode, Point position)
        {
            hasBeenTweened = false;
            this.patriciaNode = patriciaNode;
            destinationBody = patriciaNode.getBody();
            destinationDataNode = patriciaNode.getDataItem().getNode();
            currentPosition = new Point(position);
            body = new Node(patriciaNode.getBody().getObject(), 0);
            body.setBackgroundColor(PatriciaTreeColors.DIFFERENTIATING_COLOR);
            dataNode = new Node(patriciaNode.getDataItem().getNode().getObject(), 0);
            dataNode.setBackgroundColor(PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR);
            if(patriciaNode.getLeft() == patriciaNode)
                isSelfLinkLeft = new Boolean(true);
            else
            if(patriciaNode.getLeft() != null)
                isSelfLinkLeft = new Boolean(false);
            if(patriciaNode.getRight() == patriciaNode)
                isSelfLinkRight = new Boolean(true);
            else
            if(patriciaNode.getRight() != null)
                isSelfLinkRight = new Boolean(false);
        }

        public void setNode(PatriciaNode patriciaNode, Point position)
        {
            if(this.patriciaNode != patriciaNode)
                init(patriciaNode, position);
        }

        public void setX(int newX)
        {
            isDrawLabel = false;
            currentPosition.x = newX;
        }

        public void setY(int newY)
        {
            isDrawLabel = false;
            currentPosition.y = newY;
        }

        public void shiftEntire(int x, int y)
        {
            isDrawLabel = false;
            currentPosition.x += x;
            currentPosition.y += x;
        }

        Point currentPosition;
        Node body;
        Node dataNode;
        Boolean isSelfLinkLeft;
        Boolean isSelfLinkRight;
        Node destinationBody;
        Node destinationDataNode;
        PatriciaNode patriciaNode;
        boolean isDrawLabel;
        boolean hasBeenTweened;
        final PatriciaTreeCanvas this$0;

        NewNode(PatriciaNode patriciaNode, Point position)
        {
            this$0 = PatriciaTreeCanvas.this;
            super();
            isDrawLabel = true;
            init(patriciaNode, position);
        }
    }


    public PatriciaTreeCanvas()
    {
        windowTop = 0;
    }

    public void displayAlgorithm(Graphics g)
    {
        if(PatriciaTree != null)
        {
            PatriciaTree.draw(g);
            drawLoops(g);
        }
        if(insertElementArray != null)
            insertElementArray.draw(g);
        if(searchElementArray != null)
            searchElementArray.draw(g);
        if(dataItemBitArray != null)
            dataItemBitArray.draw(g);
        if(compareDataBitArray != null)
        {
            compareDataBitArray.setIsShowBitNumber(true);
            compareDataBitArray.draw(g);
        }
        if(searchPositionNode != null)
        {
            searchPositionNode.draw(g);
            Point start = new Point(getSize().width / 4, g.getFontMetrics().getHeight() + PatriciaTree.getPosition().y);
            drawLine(g, start, new Point(searchPositionNode.getX(), searchPositionNode.getY()), Messages.getString("PatriciaTreeCanvas.2"));
        }
        if(compKeyNode != null)
        {
            compKeyNode.draw(g);
            Point start = new Point(getSize().width / 4, g.getFontMetrics().getHeight() + PatriciaTree.getPosition().y);
            drawLine(g, start, new Point(compKeyNode.getX(), compKeyNode.getY()), Messages.getString("PatriciaTreeCanvas.3"));
        }
        if(keyFoundNode != null)
        {
            keyFoundNode.draw(g);
            Point start = new Point((3 * getSize().width) / 4, g.getFontMetrics().getHeight() + PatriciaTree.getPosition().y);
            drawLine(g, start, new Point(keyFoundNode.getX() + keyFoundNode.getWidth(), keyFoundNode.getY()), Messages.getString("PatriciaTreeCanvas.4"));
        }
        patriciaNode.setPosition(new Point(10, windowTop));
        patriciaNode.setPosition(new Point(10, getParentSize().height - patriciaNode.getHeight() - 30));
        if(patriciaNode != null)
        {
            patriciaNode.draw(g);
            String diffBitLabel = Messages.getString("PatriciaTreeCanvas.5");
            Point start = new Point((patriciaNode.getBody().getPosition().x - g.getFontMetrics().stringWidth(diffBitLabel) / 2) + patriciaNode.getBody().getWidth() / 2, patriciaNode.getBody().getPosition().y - g.getFontMetrics().getHeight() / 2);
            drawLine(g, start, new Point(patriciaNode.getBody().getPosition().x + patriciaNode.getBody().getWidth() / 2, patriciaNode.getBody().getPosition().y), diffBitLabel);
            String keyValueLabel = Messages.getString("PatriciaTreeCanvas.6");
            start = new Point(patriciaNode.getDataItem().getNode().getPosition().x + g.getFontMetrics().stringWidth(keyValueLabel) / 2 + patriciaNode.getDataItem().getNode().getWidth() / 2, patriciaNode.getDataItem().getNode().getPosition().y - g.getFontMetrics().getHeight() / 2);
            drawLine(g, start, new Point(patriciaNode.getDataItem().getNode().getPosition().x + patriciaNode.getDataItem().getNode().getWidth() / 2, patriciaNode.getDataItem().getNode().getPosition().y), keyValueLabel);
        }
        if(insertPositionNode != null && insertPositionNode.getPosition() != null)
        {
            insertPositionNode.getBody().draw(g);
            Point start = new Point(getSize().width / 6, PatriciaTree.getPosition().y);
            drawLine(g, start, new Point(insertPositionNode.getBody().getX(), insertPositionNode.getBody().getY()), Messages.getString("PatriciaTreeCanvas.7"));
            Point test = new Point(insertPositionNode.getPosition().x, insertPositionNode.getPosition().y - 10);
            drawLine(g, start, test, Messages.getString("PatriciaTreeCanvas.8"));
        }
        if(differentiatingBit != null)
        {
            Node diffBitNode = new Node(differentiatingBit, 0);
            diffBitNode.addMarker(new StringMarker(Messages.getString("PatriciaTreeCanvas.9")));
            if(PatriciaTree != null)
                diffBitNode.setPosition(new Point((4 * getParentSize().width) / 5, PatriciaTree.getPosition().y));
            diffBitNode.setBackgroundColor(PatriciaTreeColors.DIFFERENTIATING_COLOR);
            diffBitNode.draw(g);
        }
        if(currentBit != null)
        {
            Node currentBitNode = new Node(currentBit, 0);
            currentBitNode.addMarker(new StringMarker(Messages.getString("PatriciaTreeCanvas.10")));
            if(PatriciaTree != null)
                currentBitNode.setPosition(new Point((4 * getParentSize().width) / 5, PatriciaTree.getPosition().y + 45));
            currentBitNode.setBackgroundColor(PatriciaTreeColors.CURRENT_BIT_COLOR);
            currentBitNode.draw(g);
        }
        if(newNode != null)
            newNode.drawNewNode(g);
        if(upLinkNode != null)
        {
            upLinkNode.draw(g);
            g.drawString(Messages.getString("PatriciaTreeCanvas.11"), (upLinkNode.getPosition().x - g.getFontMetrics().stringWidth(Messages.getString("PatriciaTreeCanvas.12")) / 2) + upLinkNode.getWidth() / 2, upLinkNode.getPosition().y);
        }
    }

    private void drawArc(Graphics g, boolean isThick, int x, int y, int width, int height, int startAngle, 
            int arcAngle)
    {
        java.awt.Color currentColor = g.getColor();
        if(isThick)
        {
            g.setColor(PatriciaTreeColors.SEARCH_PATH_COLOR);
            g.drawArc(x, y, width, height, startAngle, arcAngle);
            g.translate(0, 1);
            g.drawArc(x, y, width, height, startAngle, arcAngle);
            g.translate(1, -1);
            g.drawArc(x, y, width, height, startAngle, arcAngle);
            g.translate(-1, 0);
        } else
        {
            g.setColor(PatriciaTreeColors.UPLINK_COLOR);
            g.drawArc(x, y, width, height, startAngle, arcAngle);
        }
        g.setColor(currentColor);
    }

    private void drawArrowHead(Graphics g, Point position, int angle)
    {
        int xPoints[] = new int[4];
        int yPoints[] = new int[4];
        xPoints[0] = 0;
        yPoints[0] = 0;
        xPoints[1] = -8;
        yPoints[1] = -4;
        xPoints[2] = -4;
        yPoints[2] = 0;
        xPoints[3] = xPoints[1];
        yPoints[3] = -yPoints[1];
        double radianAngle = ((double)angle * 3.1415926535897931D) / 180D;
        for(int i = 0; i < xPoints.length; i++)
        {
            double newX = (double)xPoints[i] * Math.cos(radianAngle) + (double)yPoints[i] * Math.sin(radianAngle);
            double newY = (double)yPoints[i] * Math.cos(radianAngle) - (double)xPoints[i] * Math.sin(radianAngle);
            newX = Math.round(newX);
            newY = Math.round(newY);
            xPoints[i] = (int)Math.round(newX) + position.x;
            yPoints[i] = (int)Math.round(newY) + position.y;
        }

        g.fillPolygon(xPoints, yPoints, xPoints.length);
    }

    private void drawLine(Graphics g, Point start, Point end, String label)
    {
        Line line = new Line(start.x, start.y, end.x, end.y);
        line.setLabel(label);
        line.setDistanceFromStartForArrowHead(-3);
        line.setDistanceFromStartForLabel(-1);
        line.setXLabelOffset(-g.getFontMetrics().stringWidth(label) / 2);
        line.showArrowHead(true);
        line.setColor(PatriciaTreeColors.POINTER_COLOR);
        line.draw(g);
    }

    public void drawLoops(Graphics g)
    {
        if(allEndNodes != null)
        {
            for(int i = 0; i < allEndNodes.size(); i++)
            {
                Node endNodes[] = (Node[])allEndNodes.elementAt(i);
                int EDGEGAP = 20;
                int nodeWidth = endNodes[0].getWidth() + 20;
                int nodeHeight = endNodes[0].getHeight();
                Point origin = new Point(endNodes[0].getPosition());
                origin.y += nodeHeight / 2;
                origin.x += nodeWidth / 2;
                boolean isThick;
                if(endNodes[1] != null)
                {
                    if(isHighlightLoop != null && lastNode == endNodes[0] && isHighlightLoop.booleanValue())
                        isThick = true;
                    else
                        isThick = false;
                    Point destinationLeft = new Point(endNodes[1].getPosition());
                    destinationLeft.y += nodeHeight / 2;
                    destinationLeft.x += nodeWidth / 2;
                    if(destinationLeft.x <= origin.x)
                    {
                        int x;
                        int y;
                        int width;
                        int height;
                        int startAngle;
                        int arcAngle;
                        if(destinationLeft.x == origin.x && destinationLeft.y == origin.y)
                        {
                            width = EDGEGAP;
                            height = nodeHeight;
                            startAngle = 90;
                            arcAngle = 270;
                            x = origin.x - nodeWidth / 2 - width / 2;
                            y = origin.y;
                            drawArrowHead(g, new Point(x + width / 2, destinationLeft.y), 15);
                        } else
                        {
                            width = Math.abs(origin.x - destinationLeft.x) * 2 - nodeWidth;
                            height = Math.abs(origin.y - destinationLeft.y) * 2 - nodeHeight;
                            startAngle = 180;
                            arcAngle = 90;
                            x = origin.x - width / 2 - nodeWidth / 2;
                            y = origin.y - height;
                            drawArrowHead(g, new Point(destinationLeft.x, destinationLeft.y + nodeHeight / 2), 90);
                        }
                        drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                    } else
                    {
                        int width = EDGEGAP;
                        int height = Math.abs(origin.y - destinationLeft.y) - nodeHeight / 2;
                        int startAngle = 180;
                        int arcAngle = 90;
                        int x = origin.x - width / 2 - nodeWidth / 2;
                        int y = origin.y - height;
                        drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                        Point endPoint = new Point(x, y + height / 2 + 1);
                        drawArrowHead(g, endPoint, 180 - (int)((Math.atan(((double)height * 4.5D) / (double)width) * 180D) / 3.1415926535897931D));
                        width = Math.abs(endPoint.x - destinationLeft.x) * 2 - nodeWidth;
                        height = Math.abs(endPoint.y - destinationLeft.y) * 2;
                        startAngle = 90;
                        arcAngle = 90;
                        x = endPoint.x;
                        y = endPoint.y - height / 2;
                        drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                    }
                }
                if(endNodes[2] == null)
                    continue;
                if(isHighlightLoop != null && lastNode == endNodes[0] && !isHighlightLoop.booleanValue())
                    isThick = true;
                else
                    isThick = false;
                Point destinationRight = new Point(endNodes[2].getPosition());
                destinationRight.y += nodeHeight / 2;
                destinationRight.x += nodeWidth / 2;
                if(destinationRight.x >= origin.x)
                {
                    int x;
                    int y;
                    int width;
                    int height;
                    int startAngle;
                    int arcAngle;
                    if(destinationRight.x == origin.x && destinationRight.y == origin.y)
                    {
                        width = EDGEGAP;
                        height = nodeHeight;
                        startAngle = 180;
                        arcAngle = 270;
                        x = (origin.x + nodeWidth / 2) - width / 2;
                        y = origin.y;
                        drawArrowHead(g, new Point(x + width / 2, destinationRight.y), 175);
                    } else
                    {
                        width = Math.abs(origin.x - destinationRight.x) * 2 - nodeWidth;
                        height = Math.abs(origin.y - destinationRight.y) * 2 - nodeHeight;
                        startAngle = 270;
                        arcAngle = 90;
                        x = (origin.x - width / 2) + nodeWidth / 2;
                        y = origin.y - height;
                        drawArrowHead(g, new Point(destinationRight.x, destinationRight.y + nodeHeight / 2), 90);
                    }
                    drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                } else
                {
                    int width = EDGEGAP;
                    int height = Math.abs(origin.y - destinationRight.y) - nodeHeight / 2;
                    int startAngle = 270;
                    int arcAngle = 90;
                    int x = (origin.x - width / 2) + nodeWidth / 2;
                    int y = origin.y - height;
                    drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                    Point endPoint = new Point(x + width, y + height / 2 + 1);
                    drawArrowHead(g, endPoint, (int)((Math.atan(((double)height * 4.5D) / (double)width) * 180D) / 3.1415926535897931D));
                    width = Math.abs(endPoint.x - destinationRight.x) * 2 - nodeWidth;
                    height = Math.abs(endPoint.y - destinationRight.y) * 2;
                    startAngle = 0;
                    arcAngle = 90;
                    x = endPoint.x - width;
                    y = endPoint.y - height / 2;
                    drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                }
            }

        }
    }

    protected synchronized MultipleTween getMoveTweens()
    {
        MultipleTween multipleTweens = null;
        if(newNode != null && newNode.getHierarchyTree() != null && !newNode.hasBeenTweened)
        {
            multipleTweens = new MultipleTween(this);
            numberOfTweens++;
            multipleTweens.add(new MoveTween(null, newNode, newNode.getPosition(), newNode.getHierarchyTree().getPosition(), false, numberOfTweenSteps));
            newNode.hasBeenTweened = true;
            PatriciaNode.clearNewNode();
        }
        return multipleTweens;
    }

    protected void handleColorSelection(ColorSelection colorselection)
    {
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            PatriciaTree patriciaAlgorithm = (PatriciaTree)(PatriciaTree)e.paramObj;
            int gapWidth = 25;
            windowTop = gapWidth;
            int windowCenterX = getParentSize().width / 2;
            insertElementArray = patriciaAlgorithm.getInsertElementArray();
            if(insertElementArray != null)
            {
                insertElementArray.setLocation(windowCenterX - insertElementArray.getWidth() / 2, windowTop);
                windowTop += insertElementArray.getHeight() + gapWidth;
            }
            searchElementArray = patriciaAlgorithm.getSearchElementArray();
            if(searchElementArray != null)
            {
                searchElementArray.setLocation(windowCenterX - searchElementArray.getWidth() / 2, windowTop);
                windowTop += searchElementArray.getHeight() + gapWidth;
            }
            dataItemBitArray = patriciaAlgorithm.getDataItemBitArray();
            if(dataItemBitArray != null)
            {
                dataItemBitArray.setPosition(new Point(windowCenterX - dataItemBitArray.getWidth() / 2, windowTop));
                dataItemBitArray.setLabel(DATA_BIT_ARRAY_LABEL);
                windowTop += dataItemBitArray.getHeight() + gapWidth;
            }
            Point upLinkNodePosition;
            if(dataItemBitArray != null)
                upLinkNodePosition = new Point(dataItemBitArray.getPosition().x + dataItemBitArray.getWidth() + gapWidth * 2, dataItemBitArray.getPosition().y);
            else
                upLinkNodePosition = new Point(350, windowTop);
            if(patriciaAlgorithm.getIsFollowedUpLink() != null)
            {
                if(patriciaAlgorithm.getIsFollowedUpLink().booleanValue())
                {
                    upLinkNode = new Node(Messages.getString("PatriciaTreeCanvas.13"), 0);
                    upLinkNode.setBackgroundColor(PatriciaTreeColors.YES_COLOR);
                    upLinkNode.setPosition(upLinkNodePosition);
                } else
                {
                    upLinkNode = new Node(Messages.getString("PatriciaTreeCanvas.14"), 0);
                    upLinkNode.setBackgroundColor(PatriciaTreeColors.NO_COLOR);
                    upLinkNode.setPosition(upLinkNodePosition);
                }
            } else
            {
                upLinkNode = new Node("?", 0);
                upLinkNode.setBackgroundColor(PatriciaTreeColors.DEFAULT_BACKGROUND_COLOR);
                upLinkNode.setPosition(upLinkNodePosition);
            }
            compareDataBitArray = patriciaAlgorithm.getCompareBitArray();
            if(compareDataBitArray != null)
            {
                compareDataBitArray.setPosition(new Point(windowCenterX - compareDataBitArray.getWidth() / 2, windowTop));
                compareDataBitArray.setLabel(COMPARE_BIT_ARRAY_LABEL);
                windowTop += compareDataBitArray.getHeight() + gapWidth;
            }
            differentiatingBit = patriciaAlgorithm.getDifferentiatingBit();
            currentBit = patriciaAlgorithm.getCurrentBit();
            PatriciaTree = patriciaAlgorithm.getHierarchyTree();
            if(PatriciaTree != null)
            {
                int windowWidth = getParentSize().width;
                if(windowTop > PatriciaTreeYPosition)
                    PatriciaTreeYPosition = windowTop;
                PatriciaTree.plantTree(windowCenterX, PatriciaTreeYPosition);
                int rightPoint = PatriciaTree.getRectangle().x + PatriciaTree.getRectangle().width;
                if(rightPoint > windowWidth)
                    PatriciaTree.plantTree(windowCenterX - (rightPoint - windowWidth), PatriciaTree.getPosition().y);
                int leftPoint = PatriciaTree.getRectangle().x;
                if(leftPoint < 0)
                    PatriciaTree.plantTree(PatriciaTree.getPosition().x - leftPoint, PatriciaTree.getPosition().y);
                windowTop = PatriciaTreeYPosition + PatriciaTree.getRectangle().height;
            }
            searchPositionNode = patriciaAlgorithm.getSearchPositionNode();
            insertPositionNode = patriciaAlgorithm.getInsertPositionNode();
            compKeyNode = patriciaAlgorithm.getCompKeyNode();
            keyFoundNode = patriciaAlgorithm.getKeyFoundNode();
            PatriciaNode temp = patriciaAlgorithm.getNewNode();
            if(temp != null)
            {
                Point newNodePosition;
                if(insertElementArray != null)
                    newNodePosition = new Point(20, insertElementArray.getLocation().y + insertElementArray.getHeight() + gapWidth);
                else
                    newNodePosition = new Point(20, 60);
                newNode = new NewNode(temp, newNodePosition);
            } else
            {
                newNode = null;
            }
            setSize(PatriciaTree.getRectangle().width + PatriciaTree.getRectangle().x, getParentSize().height);
            isHighlightLoop = patriciaAlgorithm.getIsHighlightLoop();
            lastNode = patriciaAlgorithm.getLastNode();
            allEndNodes = patriciaAlgorithm.getAllEndNodes();
            patriciaNode = patriciaAlgorithm.getHeadNode();
            repaint();
            if(newNode != null)
            {
                MultipleTween tempTween = getMoveTweens();
                if(tempTween != null)
                    addTween(tempTween);
            }
            if(numberOfTweens > 0)
            {
                numberOfTweens = 0;
                (new Thread(tweens)).start();
            }
            repaint();
        }
    }

    private static final long serialVersionUID = 0x1ead6314591e2151L;
    private static int PatriciaTreeYPosition = 0;
    private static final String DATA_BIT_ARRAY_LABEL = Messages.getString("PatriciaTreeCanvas.0");
    private static final String COMPARE_BIT_ARRAY_LABEL = Messages.getString("PatriciaTreeCanvas.1");
    private static int numberOfTweens = 0;
    private static Node upLinkNode;
    private HierarchyTree PatriciaTree;
    private ElementArray insertElementArray;
    private ElementArray searchElementArray;
    private DigitalElementArray compareDataBitArray;
    private DigitalElementArray dataItemBitArray;
    private Node searchPositionNode;
    private PatriciaNode insertPositionNode;
    private NewNode newNode;
    private Node compKeyNode;
    private Node keyFoundNode;
    private Integer differentiatingBit;
    private Integer currentBit;
    private Boolean isHighlightLoop;
    private Node lastNode;
    private Vector allEndNodes;
    private int windowTop;
    private PatriciaNode patriciaNode;



}
