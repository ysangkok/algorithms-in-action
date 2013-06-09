import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class PatriciaTreeIterCanvas extends AlgorithmCanvas
{
    private HierarchyTree PatriciaTree;
    private ElementArray insertElementArray;
    private ElementArray searchElementArray;
    private DigitalElementArray compareDataBitArray;
    private DigitalElementArray dataItemBitArray;
    private Node searchPositionNode;
    private PatriciaTreeIterNode insertPositionNode;
    private Node insertionPoint;
    private PatriciaTreeIterNode growNode;
    private NewNode newNode;
    private Node compKeyNode;
    private Node keyFoundNode;
    private Integer differentiatingBit;
    private Integer currentBit;
    private Boolean isHighlightLoop;
    private Node lastNode;
    private Vector<E> allEndNodes;
    private int windowTop;
    private static int PatriciaTreeYPosition;
    private static final String DATA_BIT_ARRAY_LABEL;
    private static final String COMPARE_BIT_ARRAY_LABEL;
    private static int numberOfTweens;
    private static Node upLinkNode;
    private PatriciaTreeIterNode patriciaNode;
    
    public PatriciaTreeIterCanvas() {
        super();
        this.windowTop = 0;
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (this.PatriciaTree != null) {
            this.PatriciaTree.draw(g);
            this.drawLoops(g);
        }
        if (this.insertElementArray != null) {
            this.insertElementArray.draw(g);
        }
        if (this.searchElementArray != null) {
            this.searchElementArray.draw(g);
        }
        if (this.dataItemBitArray != null) {
            this.dataItemBitArray.draw(g);
        }
        if (this.compareDataBitArray != null) {
            this.compareDataBitArray.setIsShowBitNumber(true);
            this.compareDataBitArray.draw(g);
        }
        Point start;
        if (this.insertionPoint != null) {
            final Point start3;
            start = (start3 = new Point(this.getSize().width / 4, g.getFontMetrics().getHeight() + this.PatriciaTree.getPosition().y));
            this.drawLine(g, start3, new Point(this.insertionPoint.getX(), this.insertionPoint.getY()), Messages.getString("PatriciaTreeIterCanvas.2"));
        }
        if (this.searchPositionNode != null) {}
        if (this.compKeyNode != null) {
            this.compKeyNode.draw(g);
            final Point start4;
            start = (start4 = new Point(this.getSize().width / 4, g.getFontMetrics().getHeight() + this.PatriciaTree.getPosition().y));
            this.drawLine(g, start4, new Point(this.compKeyNode.getX(), this.compKeyNode.getY()), Messages.getString("PatriciaTreeIterCanvas.3"));
        }
        if (this.keyFoundNode != null) {
            this.keyFoundNode.draw(g);
            final Point start5;
            start = (start5 = new Point(3 * this.getSize().width / 4, g.getFontMetrics().getHeight() + this.PatriciaTree.getPosition().y));
            this.drawLine(g, start5, new Point(this.keyFoundNode.getX() + this.keyFoundNode.getWidth(), this.keyFoundNode.getY()), Messages.getString("PatriciaTreeIterCanvas.4"));
        }
        this.patriciaNode.setPosition(new Point(10, this.windowTop));
        this.patriciaNode.setPosition(new Point(10, this.getParentSize().height - this.patriciaNode.getHeight() - 30));
        if (this.patriciaNode != null) {
            this.patriciaNode.draw(g);
            final String diffBitLabel = Messages.getString("PatriciaTreeIterCanvas.5");
            final Point start6;
            Point start2 = start6 = new Point(this.patriciaNode.getBody().getPosition().x - g.getFontMetrics().stringWidth(diffBitLabel) / 2 + this.patriciaNode.getBody().getWidth() / 2, this.patriciaNode.getBody().getPosition().y - g.getFontMetrics().getHeight() / 2);
            this.drawLine(g, start6, new Point(this.patriciaNode.getBody().getPosition().x + this.patriciaNode.getBody().getWidth() / 2, this.patriciaNode.getBody().getPosition().y), diffBitLabel);
            final String keyValueLabel = Messages.getString("PatriciaTreeIterCanvas.6");
            final Point start7;
            start2 = (start7 = new Point(this.patriciaNode.getDataItem().getNode().getPosition().x + g.getFontMetrics().stringWidth(keyValueLabel) / 2 + this.patriciaNode.getDataItem().getNode().getWidth() / 2, this.patriciaNode.getDataItem().getNode().getPosition().y - g.getFontMetrics().getHeight() / 2));
            this.drawLine(g, start7, new Point(this.patriciaNode.getDataItem().getNode().getPosition().x + this.patriciaNode.getDataItem().getNode().getWidth() / 2, this.patriciaNode.getDataItem().getNode().getPosition().y), keyValueLabel);
        }
        if (this.insertPositionNode != null && this.insertPositionNode.getPosition() != null) {
            this.insertPositionNode.getBody().draw(g);
            final Point start8;
            start = (start8 = new Point(this.getSize().width / 6, this.PatriciaTree.getPosition().y));
            this.drawLine(g, start8, new Point(this.insertPositionNode.getBody().getX(), this.insertPositionNode.getBody().getY()), Messages.getString("PatriciaTreeIterCanvas.7"));
            final Point test = new Point(this.insertPositionNode.getPosition().x, this.insertPositionNode.getPosition().y - 10);
            this.drawLine(g, start, test, Messages.getString("PatriciaTreeIterCanvas.8"));
        }
        if (this.differentiatingBit != null) {
            final Node node;
            final Node diffBitNode = node = new Node(this.differentiatingBit, 0);
            node.addMarker(new StringMarker(Messages.getString("PatriciaTreeIterCanvas.9")));
            if (this.PatriciaTree != null) {
                diffBitNode.setPosition(new Point(4 * this.getParentSize().width / 5, this.PatriciaTree.getPosition().y));
            }
            diffBitNode.setBackgroundColor(PatriciaTreeIterColors.DIFFERENTIATING_COLOR);
            diffBitNode.draw(g);
        }
        if (this.currentBit != null) {
            final Node node2;
            final Node currentBitNode = node2 = new Node(this.currentBit, 0);
            node2.addMarker(new StringMarker(Messages.getString("PatriciaTreeIterCanvas.10")));
            if (this.PatriciaTree != null) {
                currentBitNode.setPosition(new Point(4 * this.getParentSize().width / 5, this.PatriciaTree.getPosition().y + 45));
            }
            currentBitNode.setBackgroundColor(PatriciaTreeIterColors.CURRENT_BIT_COLOR);
            currentBitNode.draw(g);
        }
        if (this.newNode != null && !this.newNode.hasBeenTweened) {
            this.newNode.drawNewNode(g);
        }
        if (PatriciaTreeIterCanvas.upLinkNode != null) {
            PatriciaTreeIterCanvas.upLinkNode.draw(g);
            g.drawString(Messages.getString("PatriciaTreeIterCanvas.11"), PatriciaTreeIterCanvas.upLinkNode.getPosition().x - g.getFontMetrics().stringWidth(Messages.getString("PatriciaTreeIterCanvas.12")) / 2 + PatriciaTreeIterCanvas.upLinkNode.getWidth() / 2, PatriciaTreeIterCanvas.upLinkNode.getPosition().y);
        }
    }
    
    protected synchronized MultipleTween getMoveTweens() {
        MultipleTween multipleTweens = null;
        if (this.newNode != null && this.newNode.getHierarchyTree() != null && !this.newNode.hasBeenTweened) {
            multipleTweens = new MultipleTween(this);
            PatriciaTreeIterCanvas.numberOfTweens = PatriciaTreeIterCanvas.numberOfTweens + 1;
            multipleTweens.add(new InsertTween(null, this.growNode.getBody(), this.numberOfTweenSteps));
            multipleTweens.add(new InsertTween(null, this.growNode.getDataItem().getNode(), this.numberOfTweenSteps));
            this.newNode.hasBeenTweened = true;
        }
        return multipleTweens;
    }
    
    private void drawLine(final Graphics g, final Point start, final Point end, final String label) {
        final Line line = new Line(start.x, start.y, end.x, end.y);
        line.setLabel(label);
        line.setDistanceFromStartForArrowHead(-3);
        line.setDistanceFromStartForLabel(-1);
        line.setXLabelOffset(-g.getFontMetrics().stringWidth(label) / 2);
        line.showArrowHead(true);
        line.setColor(PatriciaTreeIterColors.POINTER_COLOR);
        line.draw(g);
    }
    
    private void drawArrowHead(final Graphics g, final Point position, final int angle) {
        final int[] xPoints = new int[4];
        final int[] yPoints = new int[4];
        xPoints[0] = 0;
        yPoints[0] = 0;
        xPoints[1] = -8;
        yPoints[1] = -4;
        xPoints[2] = -4;
        yPoints[2] = 0;
        xPoints[3] = xPoints[1];
        yPoints[3] = -yPoints[1];
        final double radianAngle = (double)angle * 3.141592653589793 / 180.0;
        for (int i = 0; i < xPoints.length; ++i) {
            double newX = (double)xPoints[i] * Math.cos(radianAngle) + (double)yPoints[i] * Math.sin(radianAngle);
            double newY = (double)yPoints[i] * Math.cos(radianAngle) - (double)xPoints[i] * Math.sin(radianAngle);
            newX = (double)Math.round(newX);
            newY = (double)Math.round(newY);
            xPoints[i] = (int)Math.round(newX) + position.x;
            yPoints[i] = (int)Math.round(newY) + position.y;
        }
        g.fillPolygon(xPoints, yPoints, xPoints.length);
    }
    
    public void drawLoops(final Graphics g) {
        if (this.allEndNodes != null) {
            for (int i = 0; i < this.allEndNodes.size(); ++i) {
                final Node[] endNodes = (Node[])((Node[])this.allEndNodes.elementAt(i));
                final int EDGEGAP = 20;
                final int nodeWidth = endNodes[0].getWidth() + 20;
                final int nodeHeight = endNodes[0].getHeight() + 6;
                final Point point;
                final Point origin = point = new Point(endNodes[0].getPosition());
                point.y = point.y + nodeHeight / 2;
                final Point point2 = origin;
                point2.x = point2.x + nodeWidth / 2;
                boolean isThick;
                int width;
                int height;
                int startAngle;
                int arcAngle;
                int x;
                int y;
                Point endPoint;
                if (endNodes[1] != null) {
                    isThick = (this.isHighlightLoop != null && this.lastNode == endNodes[1] && this.isHighlightLoop == true);
                    final Point point3;
                    final Point destinationLeft = point3 = new Point(endNodes[1].getPosition());
                    point3.y = point3.y + nodeHeight / 2;
                    final Point point4 = destinationLeft;
                    point4.x = point4.x + nodeWidth / 2;
                    if (destinationLeft.x <= origin.x) {
                        if (destinationLeft.x == origin.x && destinationLeft.y == origin.y) {
                            width = EDGEGAP;
                            height = nodeHeight;
                            startAngle = 90;
                            arcAngle = 270;
                            x = origin.x - nodeWidth / 2 - width / 2;
                            y = origin.y;
                            this.drawArrowHead(g, new Point(x + width / 2, destinationLeft.y), 15);
                        }
                        else {
                            width = Math.abs(origin.x - destinationLeft.x) * 2 - nodeWidth;
                            height = Math.abs(origin.y - destinationLeft.y) * 2 - nodeHeight;
                            startAngle = 180;
                            arcAngle = 90;
                            x = origin.x - width / 2 - nodeWidth / 2;
                            y = origin.y - height;
                            this.drawArrowHead(g, new Point(destinationLeft.x, destinationLeft.y + nodeHeight / 2), 90);
                        }
                        this.drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                    }
                    else {
                        width = EDGEGAP;
                        height = Math.abs(origin.y - destinationLeft.y) - nodeHeight / 2;
                        startAngle = 180;
                        arcAngle = 90;
                        x = origin.x - width / 2 - nodeWidth / 2;
                        y = origin.y - height;
                        this.drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                        endPoint = new Point(x, y + height / 2 + 1);
                        this.drawArrowHead(g, endPoint, 180 - (int)(Math.atan((double)height * 4.5 / (double)width) * 180.0 / 3.141592653589793));
                        width = Math.abs(endPoint.x - destinationLeft.x) * 2 - nodeWidth;
                        height = Math.abs(endPoint.y - destinationLeft.y) * 2;
                        startAngle = 90;
                        arcAngle = 90;
                        x = endPoint.x;
                        y = endPoint.y - height / 2;
                        this.drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                    }
                }
                if (endNodes[2] != null) {
                    isThick = (this.isHighlightLoop != null && this.lastNode == endNodes[2] && !this.isHighlightLoop);
                    final Point point5;
                    final Point destinationRight = point5 = new Point(endNodes[2].getPosition());
                    point5.y = point5.y + nodeHeight / 2;
                    final Point point6 = destinationRight;
                    point6.x = point6.x + nodeWidth / 2;
                    if (destinationRight.x >= origin.x) {
                        if (destinationRight.x == origin.x && destinationRight.y == origin.y) {
                            width = EDGEGAP;
                            height = nodeHeight;
                            startAngle = 180;
                            arcAngle = 270;
                            x = origin.x + nodeWidth / 2 - width / 2;
                            y = origin.y;
                            this.drawArrowHead(g, new Point(x + width / 2, destinationRight.y), 175);
                        }
                        else {
                            width = Math.abs(origin.x - destinationRight.x) * 2 - nodeWidth;
                            height = Math.abs(origin.y - destinationRight.y) * 2 - nodeHeight;
                            startAngle = 270;
                            arcAngle = 90;
                            x = origin.x - width / 2 + nodeWidth / 2;
                            y = origin.y - height;
                            this.drawArrowHead(g, new Point(destinationRight.x, destinationRight.y + nodeHeight / 2), 90);
                        }
                        this.drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                    }
                    else {
                        width = EDGEGAP;
                        height = Math.abs(origin.y - destinationRight.y) - nodeHeight / 2;
                        startAngle = 270;
                        arcAngle = 90;
                        x = origin.x - width / 2 + nodeWidth / 2;
                        y = origin.y - height;
                        this.drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                        endPoint = new Point(x + width, y + height / 2 + 1);
                        this.drawArrowHead(g, endPoint, (int)(Math.atan((double)height * 4.5 / (double)width) * 180.0 / 3.141592653589793));
                        width = Math.abs(endPoint.x - destinationRight.x) * 2 - nodeWidth;
                        height = Math.abs(endPoint.y - destinationRight.y) * 2;
                        startAngle = 0;
                        arcAngle = 90;
                        x = endPoint.x - width;
                        y = endPoint.y - height / 2;
                        this.drawArc(g, isThick, x, y, width, height, startAngle, arcAngle);
                    }
                }
            }
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            final PatriciaTreeIter patriciaAlgorithm = (PatriciaTreeIter)((PatriciaTreeIter)e.paramObj);
            final int gapWidth = 25;
            this.windowTop = gapWidth;
            final int windowCenterX = this.getParentSize().width / 2;
            this.insertElementArray = patriciaAlgorithm.getInsertElementArray();
            if (this.insertElementArray != null) {
                this.insertElementArray.setLocation(windowCenterX - this.insertElementArray.getWidth() / 2, this.windowTop);
                this.windowTop = this.windowTop + (this.insertElementArray.getHeight() + gapWidth);
            }
            this.searchElementArray = patriciaAlgorithm.getSearchElementArray();
            if (this.searchElementArray != null) {
                this.searchElementArray.setLocation(windowCenterX - this.searchElementArray.getWidth() / 2, this.windowTop);
                this.windowTop = this.windowTop + (this.searchElementArray.getHeight() + gapWidth);
            }
            this.dataItemBitArray = patriciaAlgorithm.getDataItemBitArray();
            if (this.dataItemBitArray != null) {
                this.dataItemBitArray.setPosition(new Point(windowCenterX - this.dataItemBitArray.getWidth() / 2, this.windowTop));
                this.dataItemBitArray.setLabel(PatriciaTreeIterCanvas.DATA_BIT_ARRAY_LABEL);
                this.windowTop = this.windowTop + (this.dataItemBitArray.getHeight() + gapWidth);
            }
            Point upLinkNodePosition;
            if (this.dataItemBitArray != null) {
                upLinkNodePosition = new Point(this.dataItemBitArray.getPosition().x + this.dataItemBitArray.getWidth() + gapWidth * 2, this.dataItemBitArray.getPosition().y);
            }
            else {
                upLinkNodePosition = new Point(350, this.windowTop);
            }
            if (patriciaAlgorithm.getIsFollowedUpLink() != null) {
                if (patriciaAlgorithm.getIsFollowedUpLink().booleanValue()) {
                    PatriciaTreeIterCanvas.upLinkNode = new Node(Messages.getString("PatriciaTreeIterCanvas.13"), 0);
                    PatriciaTreeIterCanvas.upLinkNode.setBackgroundColor(PatriciaTreeIterColors.YES_COLOR);
                    PatriciaTreeIterCanvas.upLinkNode.setPosition(upLinkNodePosition);
                }
                else {
                    PatriciaTreeIterCanvas.upLinkNode = new Node(Messages.getString("PatriciaTreeIterCanvas.14"), 0);
                    PatriciaTreeIterCanvas.upLinkNode.setBackgroundColor(PatriciaTreeIterColors.NO_COLOR);
                    PatriciaTreeIterCanvas.upLinkNode.setPosition(upLinkNodePosition);
                }
            }
            else {
                PatriciaTreeIterCanvas.upLinkNode = new Node(Messages.getString("PatriciaTreeIterCanvas.15"), 0);
                PatriciaTreeIterCanvas.upLinkNode.setBackgroundColor(PatriciaTreeIterColors.DEFAULT_BACKGROUND_COLOR);
                PatriciaTreeIterCanvas.upLinkNode.setPosition(upLinkNodePosition);
            }
            this.compareDataBitArray = patriciaAlgorithm.getCompareBitArray();
            if (this.compareDataBitArray != null) {
                this.compareDataBitArray.setPosition(new Point(windowCenterX - this.compareDataBitArray.getWidth() / 2, this.windowTop));
                this.compareDataBitArray.setLabel(PatriciaTreeIterCanvas.COMPARE_BIT_ARRAY_LABEL);
                this.windowTop = this.windowTop + (this.compareDataBitArray.getHeight() + gapWidth);
            }
            this.differentiatingBit = patriciaAlgorithm.getDifferentiatingBit();
            this.currentBit = patriciaAlgorithm.getCurrentBit();
            this.PatriciaTree = patriciaAlgorithm.getHierarchyTree();
            if (this.PatriciaTree != null) {
                final int windowWidth = this.getParentSize().width;
                if (this.windowTop > PatriciaTreeIterCanvas.PatriciaTreeYPosition) {
                    PatriciaTreeIterCanvas.PatriciaTreeYPosition = this.windowTop;
                }
                this.PatriciaTree.plantTree(windowCenterX, PatriciaTreeIterCanvas.PatriciaTreeYPosition);
                final int rightPoint = this.PatriciaTree.getRectangle().x + this.PatriciaTree.getRectangle().width;
                if (rightPoint > windowWidth) {
                    this.PatriciaTree.plantTree(windowCenterX - (rightPoint - windowWidth), this.PatriciaTree.getPosition().y);
                }
                final int leftPoint = this.PatriciaTree.getRectangle().x;
                if (leftPoint < 0) {
                    this.PatriciaTree.plantTree(this.PatriciaTree.getPosition().x - leftPoint, this.PatriciaTree.getPosition().y);
                }
                this.windowTop = PatriciaTreeIterCanvas.PatriciaTreeYPosition + this.PatriciaTree.getRectangle().height;
            }
            this.insertionPoint = patriciaAlgorithm.getInsertionPoint();
            this.searchPositionNode = patriciaAlgorithm.getSearchPositionNode();
            this.insertPositionNode = patriciaAlgorithm.getInsertPositionNode();
            this.compKeyNode = patriciaAlgorithm.getCompKeyNode();
            this.keyFoundNode = patriciaAlgorithm.getKeyFoundNode();
            final PatriciaTreeIterNode temp = patriciaAlgorithm.getNewNode();
            if (temp != null) {
                this.growNode = temp;
                Point newNodePosition;
                if (this.insertElementArray != null) {
                    newNodePosition = new Point(20, this.insertElementArray.getLocation().y + this.insertElementArray.getHeight() + gapWidth);
                }
                else {
                    newNodePosition = new Point(20, 60);
                }
                this.newNode = new NewNode(temp, newNodePosition);
            }
            else {
                this.newNode = null;
            }
            this.setSize(this.PatriciaTree.getRectangle().width + this.PatriciaTree.getRectangle().x, this.getParentSize().height);
            this.isHighlightLoop = patriciaAlgorithm.getIsHighlightLoop();
            this.lastNode = patriciaAlgorithm.getLastNode();
            this.allEndNodes = patriciaAlgorithm.getAllEndNodes();
            this.patriciaNode = patriciaAlgorithm.getHeadNode();
            this.repaint();
            if (this.newNode != null) {
                final MultipleTween tempTween = this.getMoveTweens();
                if (tempTween != null) {
                    this.addTween(tempTween);
                }
            }
            if (PatriciaTreeIterCanvas.numberOfTweens > 0) {
                PatriciaTreeIterCanvas.numberOfTweens = 0;
                new Thread(this.tweens).start();
            }
            this.repaint();
        }
    }
    
    private void drawArc(final Graphics g, final boolean isThick, final int x, final int y, final int width, final int height, final int startAngle, final int arcAngle) {
        final Color currentColor = g.getColor();
        if (isThick) {
            g.setColor(PatriciaTreeIterColors.SEARCH_PATH_COLOR);
            g.drawArc(x, y, width, height, startAngle, arcAngle);
            g.translate(0, 1);
            g.drawArc(x, y, width, height, startAngle, arcAngle);
            g.translate(1, -1);
            g.drawArc(x, y, width, height, startAngle, arcAngle);
            g.translate(-1, 0);
        }
        else {
            g.setColor(PatriciaTreeIterColors.UPLINK_COLOR);
            g.drawArc(x, y, width, height, startAngle, arcAngle);
        }
        g.setColor(currentColor);
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    static {
        PatriciaTreeIterCanvas.PatriciaTreeYPosition = 0;
        DATA_BIT_ARRAY_LABEL = Messages.getString("PatriciaTreeIterCanvas.0");
        COMPARE_BIT_ARRAY_LABEL = Messages.getString("PatriciaTreeIterCanvas.1");
        PatriciaTreeIterCanvas.numberOfTweens = 0;
    }
}
