import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class SkipListCanvas extends AlgorithmCanvas
{
    private static SkipNode skipNode;
    private SkipNode newElement;
    private ElementArray insertElementArray;
    private ElementArray searchElementArray;
    private static int no_of_levels;
    private int ilevel;
    private int jlevel;
    private int currentNewNodeLevel;
    private int maxLevel;
    private boolean drawLevel;
    private boolean drawDiff;
    private boolean drawFinal;
    private boolean drawI;
    private boolean drawJ;
    private boolean drawElement;
    private boolean runTween;
    private boolean dataCondition;
    private boolean displayDataCondition;
    private static int test;
    private Random random;
    private Node ptr;
    private Node tailptr;
    private Node highlightedNode;
    private Node dataConditionNode;
    private Node nextDataNode;
    private Node dataItemNode;
    private static final Color YES_COLOR;
    private static final Color NO_COLOR;
    private static final Color POINTER_COLOR;
    private static final Color TEXT_COLOR;
    private static final String SEARCH_LABEL;
    private static final String PTR_LABEL;
    private static final String TAILPTR_LABEL;
    private static final String SAVEPTR_LABEL;
    private static final String I_PTR;
    private static final String J_PTR;
    private static final String DATA_COND_LABEL;
    private static final String DATA_LABEL;
    private static final int GAP = 30;
    private static final int ELEMENT_WIDTH = 20;
    private static final int boxWidth = 20;
    private Point boxPoint;
    private Point levelPoint;
    private Point dataConditionPoint;
    private Line dataLine;
    
    public SkipListCanvas() {
        super();
        this.drawDiff = false;
        this.drawFinal = false;
        this.drawI = false;
        this.drawJ = false;
        this.drawElement = false;
        this.runTween = false;
        this.dataCondition = false;
        this.displayDataCondition = false;
        this.random = new Random();
        this.boxPoint = new Point(0, 0);
        this.dataLine = null;
    }
    
    public synchronized void displayAlgorithm(final Graphics g) {
        if (this.displayDataCondition) {
            if (this.dataConditionNode != null) {
                this.dataConditionNode.draw(g);
            }
            if (this.nextDataNode != null) {
                this.nextDataNode.draw(g);
            }
            if (this.dataItemNode != null) {
                this.dataItemNode.draw(g);
            }
            g.drawString(SkipListCanvas.DATA_COND_LABEL, this.dataConditionNode.getX() - g.getFontMetrics().stringWidth(SkipListCanvas.DATA_COND_LABEL), this.dataConditionNode.getY() + 3 * this.dataConditionNode.getHeight() / 4);
        }
        if (this.dataLine != null) {
            this.dataLine.draw(g);
        }
        if (this.newElement != null && this.drawElement) {
            for (int i = 0; i < this.newElement.nodes.length; ++i) {
                this.newElement.nodes[i].draw(g);
            }
        }
        if (this.insertElementArray != null) {
            this.insertElementArray.draw(g);
        }
        if (this.searchElementArray != null) {
            this.searchElementArray.draw(g);
            g.drawString(SkipListCanvas.SEARCH_LABEL, this.getParentSize().width / 2 - g.getFontMetrics().stringWidth(SkipListCanvas.SEARCH_LABEL) / 2, this.searchElementArray.getLocation().y);
        }
        if (SkipListCanvas.skipNode != null) {
            final String levelString = Messages.getString("SkipListCanvas.8") + String.valueOf(SkipListCanvas.no_of_levels);
            g.drawString(levelString, this.levelPoint.x, this.levelPoint.y);
            this.draw(g, SkipListCanvas.skipNode);
            this.draw(g);
            for (int j = 2; j <= SkipListCanvas.no_of_levels; ++j) {
                this.draw(g, j);
            }
            if (this.ptr != null) {
                final Line ptrLine = new Line(this.ptr.getX() + this.ptr.getWidth() + 10, this.ptr.getY() + 40, this.ptr.getX() + this.ptr.getWidth(), this.ptr.getY() + this.ptr.getHeight());
                ptrLine.setLabel(SkipListCanvas.PTR_LABEL);
                ptrLine.setDistanceFromStartForArrowHead(-3);
                ptrLine.setDistanceFromStartForLabel(-1);
                ptrLine.setXLabelOffset(-g.getFontMetrics().stringWidth(SkipListCanvas.PTR_LABEL) / 2);
                ptrLine.setYLabelOffset(g.getFontMetrics().getHeight());
                ptrLine.showArrowHead(true);
                ptrLine.setColor(SkipListCanvas.POINTER_COLOR);
                ptrLine.draw(g);
            }
            if (this.tailptr != null) {
                final Line tailptrLine = new Line(this.tailptr.getX() - 10, this.tailptr.getY() + 40, this.tailptr.getX(), this.tailptr.getY() + this.tailptr.getHeight());
                tailptrLine.setLabel(SkipListCanvas.TAILPTR_LABEL);
                tailptrLine.setDistanceFromStartForArrowHead(-3);
                tailptrLine.setDistanceFromStartForLabel(-1);
                tailptrLine.setXLabelOffset(-g.getFontMetrics().stringWidth(SkipListCanvas.TAILPTR_LABEL) / 2);
                tailptrLine.setYLabelOffset(g.getFontMetrics().getHeight());
                tailptrLine.showArrowHead(true);
                tailptrLine.setColor(SkipListCanvas.POINTER_COLOR);
                tailptrLine.draw(g);
            }
            Point point1;
            Point point2;
            if (this.drawJ) {
                point1 = new Point(SkipListCanvas.skipNode.nodes[0].getX() - 15, SkipListCanvas.skipNode.nodes[0].getY() - this.jlevel * SkipListCanvas.skipNode.nodes[0].getHeight() - SkipListCanvas.skipNode.nodes[0].getHeight() / 2);
                point2 = new Point(SkipListCanvas.skipNode.nodes[0].getX(), SkipListCanvas.skipNode.nodes[0].getY() - this.jlevel * SkipListCanvas.skipNode.nodes[0].getHeight() - SkipListCanvas.skipNode.nodes[0].getHeight() / 2);
                final Line jLine = new Line(point1, point2);
                jLine.showArrowHead(true);
                jLine.setDistanceFromStartForArrowHead(-3);
                jLine.setLabel(SkipListCanvas.J_PTR);
                jLine.setDistanceFromStartForLabel(-1);
                jLine.setXLabelOffset(-1 * this.getGraphics().getFontMetrics().stringWidth(SkipListCanvas.J_PTR));
                jLine.setYLabelOffset(1 * this.getGraphics().getFontMetrics().getHeight());
                jLine.setColor(SkipListCanvas.POINTER_COLOR);
                jLine.draw(g);
            }
            if (this.drawI) {
                point1 = new Point(SkipListCanvas.skipNode.nodes[0].getX() - 15, SkipListCanvas.skipNode.nodes[0].getY() - this.ilevel * SkipListCanvas.skipNode.nodes[0].getHeight() - SkipListCanvas.skipNode.nodes[0].getHeight() / 2);
                point2 = new Point(SkipListCanvas.skipNode.nodes[0].getX(), SkipListCanvas.skipNode.nodes[0].getY() - this.ilevel * SkipListCanvas.skipNode.nodes[0].getHeight() - SkipListCanvas.skipNode.nodes[0].getHeight() / 2);
                final Line iLine = new Line(point1, point2);
                iLine.showArrowHead(true);
                iLine.setDistanceFromStartForArrowHead(-3);
                iLine.setLabel(SkipListCanvas.I_PTR);
                iLine.setDistanceFromStartForLabel(-1);
                iLine.setXLabelOffset(-1 * this.getGraphics().getFontMetrics().stringWidth(SkipListCanvas.I_PTR));
                iLine.setYLabelOffset(1 * this.getGraphics().getFontMetrics().getHeight());
                iLine.setColor(SkipListCanvas.POINTER_COLOR);
                iLine.draw(g);
            }
            g.drawRect(this.boxPoint.x, this.boxPoint.y, 20, 20);
            final String newNodeLevel = Messages.getString("SkipListCanvas.9");
            g.drawString(newNodeLevel, this.boxPoint.x - g.getFontMetrics().stringWidth(newNodeLevel), this.boxPoint.y + 15);
        }
        if (this.drawDiff) {
            g.setColor(SkipListCanvas.TEXT_COLOR);
            final int windowCenterX = this.getParentSize().width / 2;
            SkipListCanvas.test = this.random.nextInt();
            while (SkipListCanvas.test > this.maxLevel) {
                SkipListCanvas.test = this.random.nextInt();
            }
            final String string = String.valueOf(SkipListCanvas.test);
            final String printString = string.substring(string.length() - 1, string.length());
            g.drawString(printString, this.boxPoint.x + 10 - g.getFontMetrics().stringWidth(printString) / 2, this.boxPoint.y + 20 - 5);
        }
        if (this.drawFinal) {
            g.drawString(String.valueOf(this.currentNewNodeLevel), this.boxPoint.x + 10 - g.getFontMetrics().stringWidth(String.valueOf(this.currentNewNodeLevel)) / 2, this.boxPoint.y + 20 - 5);
        }
    }
    
    protected MultipleTween generateTween(final SkipNode toNode) {
        final MultipleTween multipleTweens = new MultipleTween(this);
        for (int i = 0; i < this.newElement.nodes.length; ++i) {
            this.newElement.nodes[i].setIsVisible(false);
            final MoveTween fromTween = new MoveTween(null, this.newElement.nodes[i], toNode.nodes[i].getPosition(), this.newElement.nodes[i].getPosition(), true, this.numberOfTweenSteps);
            multipleTweens.add(fromTween);
            final MoveTween toTween = new MoveTween(null, toNode.nodes[i], this.newElement.nodes[i].getPosition(), toNode.nodes[i].getPosition(), true, this.numberOfTweenSteps);
            multipleTweens.add(toTween);
        }
        return multipleTweens;
    }
    
    protected Line initialiseLine(final String label, final Node node) {
        final Point from = new Point(node.getX() + node.getWidth() / 2, node.getY() + 60);
        final Point to = new Point(node.getX() + node.getWidth() / 2, node.getY() + node.getHeight());
        final Line line = new Line(from, to);
        line.setLabel(label);
        line.setDistanceFromStartForArrowHead(-3);
        line.setDistanceFromStartForLabel(-1);
        line.setXLabelOffset(-this.getGraphics().getFontMetrics().stringWidth(label) / 2);
        line.setYLabelOffset(this.getGraphics().getFontMetrics().getHeight());
        line.showArrowHead(true);
        line.setColor(SkipListCanvas.POINTER_COLOR);
        return line;
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        this.removeAllSelectables();
        if (e.paramObj != null) {
            int windowTop = 30;
            final int windowCenterX = this.getParentSize().width / 2;
            final SkipList skipList = (SkipList)((SkipList)e.paramObj);
            SkipListCanvas.skipNode = skipList.getHead();
            SkipListCanvas.no_of_levels = skipList.getLevels();
            if (skipList.isBuildMode()) {
                this.insertElementArray = skipList.getInsertElementArray();
            }
            else {
                this.insertElementArray = null;
            }
            if (skipList.isSearchMode()) {
                this.addSelectionListener(SearchSelection.getInstance());
                this.searchElementArray = skipList.getSearchElementArray();
            }
            else {
                this.searchElementArray = null;
            }
            int windowWidth;
            if (this.insertElementArray != null) {
                this.insertElementArray.setElementWidth(20);
                this.insertElementArray.setAllHaveSameWidth(true);
                windowWidth = this.getParentSize().width;
                if (this.insertElementArray.getWidth() < windowWidth) {
                    this.insertElementArray.setLocation(windowCenterX - this.insertElementArray.getWidth() / 2, windowTop);
                }
                else {
                    this.insertElementArray.setLocation(0, windowTop);
                }
                windowTop = windowTop + (this.insertElementArray.getHeight() + 30);
            }
            if (SkipListCanvas.skipNode != null) {
                windowWidth = this.getParentSize().width;
                this.levelPoint = new Point(30, windowTop);
                this.dataConditionPoint = new Point(3 * this.getParentSize().width / 4, windowTop);
                final Point point = new Point(60, windowTop + SkipListCanvas.skipNode.getHeight());
                SkipListCanvas.skipNode.nodes[0].setPosition(point);
                for (int m = 1; m < SkipListCanvas.skipNode.nodes.length; ++m) {
                    SkipListCanvas.skipNode.nodes[m].setPosition(new Point(SkipListCanvas.skipNode.nodes[0].getX(), SkipListCanvas.skipNode.nodes[0].getY() - m * SkipListCanvas.skipNode.nodes[0].getHeight()));
                }
                SkipNode pointerNode = SkipListCanvas.skipNode;
                int posCounter = 40;
                while (pointerNode.next[0] != null) {
                    pointerNode = pointerNode.next[0];
                    pointerNode.nodes[0].setPosition(new Point(point.x + posCounter, point.y));
                    pointerNode.nodes[1].setPosition(new Point(point.x + posCounter, point.y - pointerNode.nodes[0].getHeight()));
                    posCounter += 40;
                }
                for (int i = 1; i < SkipListCanvas.skipNode.next.length; ++i) {
                    pointerNode = SkipListCanvas.skipNode;
                    while (pointerNode.next[i] != null) {
                        pointerNode = pointerNode.next[i];
                        pointerNode.nodes[i + 1].setPosition(new Point(pointerNode.nodes[0].getX(), pointerNode.nodes[0].getY() - (i + 1) * pointerNode.nodes[0].getHeight()));
                    }
                }
                this.ptr = skipList.getPtrNode();
                this.tailptr = skipList.getTailPtrNode();
                this.drawLevel = skipList.doDrawBoxLevel();
                this.drawFinal = skipList.doDrawBoxFinalLevel();
                this.dataCondition = skipList.getDataCondition();
                this.displayDataCondition = skipList.doDisplayDataCondition();
                this.drawI = skipList.doDrawIPointer();
                this.ilevel = skipList.getIPointer();
                this.drawJ = skipList.doDrawJPointer();
                this.jlevel = skipList.getJPointer();
                this.currentNewNodeLevel = skipList.getNewNodeLevel();
                this.maxLevel = skipList.getMaxLevel();
                this.drawElement = skipList.doDrawNewElement();
                this.runTween = skipList.doRunTween();
                this.boxPoint.setLocation(130, windowTop + SkipListCanvas.skipNode.getHeight() + 80);
                windowTop = windowTop + (SkipListCanvas.skipNode.getHeight() + 110);
                if (skipList.getDataNode() != null) {
                    this.dataLine = this.initialiseLine(SkipListCanvas.DATA_LABEL, skipList.getDataNode());
                }
                else {
                    this.dataLine = null;
                }
                String string;
                if (skipList.getDataItem() != null) {
                    string = String.valueOf(skipList.getDataItem());
                    this.dataItemNode = new Node(string, 0);
                    this.dataItemNode.setBackgroundColor(this.getBackground());
                    this.dataItemNode.setPosition(new Point(this.dataConditionPoint.x - this.getGraphics().getFontMetrics().stringWidth(SkipListCanvas.DATA_COND_LABEL) / 4, this.dataConditionPoint.y));
                }
                if (skipList.getNextData() != null) {
                    string = String.valueOf(skipList.getNextData());
                    this.nextDataNode = new Node(string, 0);
                    this.nextDataNode.setWidth(20);
                    this.nextDataNode.setBackgroundColor(this.getBackground());
                    this.nextDataNode.setPosition(new Point(this.dataConditionPoint.x - 3 * (this.getGraphics().getFontMetrics().stringWidth(SkipListCanvas.DATA_COND_LABEL) / 4), this.dataConditionPoint.y));
                }
                if (skipList.getNextNull()) {
                    this.dataConditionNode = new Node(Messages.getString("SkipListCanvas.10"), 0);
                    this.dataConditionNode.setWidth(this.getGraphics().getFontMetrics().stringWidth(Messages.getString("SkipListCanvas.11")));
                    this.dataConditionNode.setPosition(new Point(this.dataConditionPoint.x, this.dataConditionPoint.y - this.dataConditionNode.getHeight()));
                    this.nextDataNode = new Node(Messages.getString("SkipListCanvas.12"), 0);
                    this.nextDataNode.setWidth(this.getGraphics().getFontMetrics().stringWidth(Messages.getString("SkipListCanvas.13")));
                    this.nextDataNode.setBackgroundColor(this.getBackground());
                    this.nextDataNode.setPosition(new Point(this.dataConditionPoint.x - 3 * (this.getGraphics().getFontMetrics().stringWidth(SkipListCanvas.DATA_COND_LABEL) / 4), this.dataConditionPoint.y));
                }
                else if (this.dataCondition) {
                    this.dataConditionNode = new Node(Messages.getString("SkipListCanvas.14"), 0);
                    this.dataConditionNode.setWidth(20);
                    this.dataConditionNode.setBackgroundColor(SkipListCanvas.YES_COLOR);
                    this.dataConditionNode.setPosition(new Point(this.dataConditionPoint.x, this.dataConditionPoint.y - this.dataConditionNode.getHeight()));
                }
                else {
                    this.dataConditionNode = new Node(Messages.getString("SkipListCanvas.15"), 0);
                    this.dataConditionNode.setWidth(20);
                    this.dataConditionNode.setBackgroundColor(SkipListCanvas.NO_COLOR);
                    this.dataConditionNode.setPosition(new Point(this.dataConditionPoint.x, this.dataConditionPoint.y - this.dataConditionNode.getHeight()));
                }
                if (this.drawElement) {
                    this.newElement = skipList.getNewElement();
                    this.newElement.nodes[0].setPosition(new Point(this.boxPoint.x + 30, this.boxPoint.y + this.newElement.getHeight() - 30));
                    for (int p = 1; p < this.newElement.nodes.length; ++p) {
                        final Point prev_point = this.newElement.nodes[p - 1].getPosition();
                        this.newElement.nodes[p].setPosition(new Point(prev_point.x, prev_point.y - this.newElement.nodes[p - 1].getHeight()));
                    }
                    windowTop = this.newElement.nodes[0].getY() + 10;
                }
                if (skipList.doRunTween()) {
                    final SkipNode finalNewElement = skipList.getFinalNewElement();
                    this.addTween(this.generateTween(finalNewElement));
                }
                if (this.tweens.getNumberOfTweens() > 0) {
                    this.tweens.run();
                }
            }
            if (this.searchElementArray != null) {
                this.searchElementArray.setElementWidth(20);
                this.searchElementArray.setAllHaveSameWidth(true);
                windowWidth = this.getParentSize().width;
                if (this.searchElementArray.getWidth() < windowWidth) {
                    this.searchElementArray.setLocation(windowCenterX - this.searchElementArray.getWidth() / 2, windowTop);
                }
                else {
                    this.searchElementArray.setLocation(0, windowTop);
                }
                windowTop = windowTop + (this.searchElementArray.getHeight() + 30);
                this.addSelectable(this.searchElementArray);
            }
            if (this.drawLevel) {
                for (int j = 0; j < 10; ++j) {
                    this.drawDiff = true;
                    this.repaint();
                    try {
                        Thread.sleep(60L);
                    }
                    catch (InterruptedException f) {
                        System.out.println(f);
                    }
                }
                this.drawDiff = false;
                this.drawFinal = true;
            }
        }
        this.repaint();
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    public void draw(final Graphics g, final SkipNode head) {
        final Point point = new Point(head.nodes[0].getPosition());
        head.nodes[0].draw(g);
        for (int i = 1; i < head.nodes.length; ++i) {
            head.nodes[i].draw(g);
            if (head.next[i - 1] != null) {
                head.nextNodesLines[i].setStartPosition(new Point(head.nodes[i].getX() + head.nodes[i].getWidth() / 2, head.nodes[i].getY() + head.nodes[i].getHeight() / 2));
                head.nextNodesLines[i].setEndPosition(new Point(head.next[i - 1].nodes[0].getX(), head.next[i - 1].nodes[0].getY() - i * head.nodes[i].getHeight() + head.nodes[i].getHeight() / 2));
                head.nextNodesLines[i].setDistanceFromStartForArrowHead(-3);
                head.nextNodesLines[i].showArrowHead(true);
                if (head.nextNodesLines[i].getColor() != Color.red && head.nextNodesLines[i].getColor() != Color.white) {
                    head.nextNodesLines[i].setColor(SkipListCanvas.TEXT_COLOR);
                }
                if (head.nextNodesLines[i].getColor() == Color.white) {
                    head.nextNodesLines[i].setColor(this.getBackground());
                }
                head.nextNodesLines[i].draw(g);
            }
        }
    }
    
    public void draw(final Graphics g) {
        SkipNode newSkipNode = SkipListCanvas.skipNode.next[0];
        int counter = 0;
        final Point point = new Point(SkipListCanvas.skipNode.nodes[0].getX() + 40, SkipListCanvas.skipNode.nodes[0].getY());
        while (newSkipNode != null) {
            newSkipNode.nodes[0].draw(g);
            point.move(point.x, point.y - newSkipNode.nodes[0].getHeight());
            newSkipNode.nodes[1].draw(g);
            if (newSkipNode.next[0] != null) {
                newSkipNode.nextNodesLines[1].setStartPosition(new Point(point.x + newSkipNode.nodes[0].getWidth() / 2, point.y + newSkipNode.nodes[0].getHeight() / 2));
                newSkipNode.nextNodesLines[1].setEndPosition(new Point(point.x + 40, point.y + newSkipNode.nodes[0].getHeight() / 2));
                newSkipNode.nextNodesLines[1].setDistanceFromStartForArrowHead(-3);
                newSkipNode.nextNodesLines[1].showArrowHead(true);
                if (newSkipNode.nextNodesLines[1].getColor() != Color.red && newSkipNode.nextNodesLines[1].getColor() != Color.white) {
                    newSkipNode.nextNodesLines[1].setColor(SkipListCanvas.TEXT_COLOR);
                }
                if (newSkipNode.nextNodesLines[1].getColor() == Color.white) {
                    newSkipNode.nextNodesLines[1].setColor(this.getBackground());
                }
                newSkipNode.nextNodesLines[1].draw(g);
            }
            newSkipNode = newSkipNode.next[0];
            ++counter;
            point.move(point.x + 40, SkipListCanvas.skipNode.nodes[0].getY());
            if (newSkipNode == null) {}
        }
    }
    
    public void draw(final Graphics g, final int atLevel) {
        SkipNode newSkipNode = SkipListCanvas.skipNode.next[atLevel - 1];
        Point point = new Point(0, 0);
        if (newSkipNode != null) {
            point = new Point(newSkipNode.nodes[0].getX(), SkipListCanvas.skipNode.nodes[0].getY() - atLevel * SkipListCanvas.skipNode.nodes[0].getHeight());
        }
        while (newSkipNode != null) {
            newSkipNode.nodes[atLevel].draw(g);
            if (newSkipNode.next[atLevel - 1] != null) {
                newSkipNode.nextNodesLines[atLevel].setStartPosition(new Point(newSkipNode.nodes[atLevel].getX() + newSkipNode.nodes[atLevel].getWidth() / 2, newSkipNode.nodes[atLevel].getY() + newSkipNode.nodes[atLevel].getHeight() / 2));
                newSkipNode.nextNodesLines[atLevel].setEndPosition(new Point(newSkipNode.next[atLevel - 1].nodes[0].getX(), newSkipNode.next[atLevel - 1].nodes[0].getY() - atLevel * newSkipNode.nodes[atLevel].getHeight() + newSkipNode.nodes[atLevel].getHeight() / 2));
                newSkipNode.nextNodesLines[atLevel].setDistanceFromStartForArrowHead(-3);
                newSkipNode.nextNodesLines[atLevel].showArrowHead(true);
                if (newSkipNode.nextNodesLines[atLevel].getColor() != Color.red && newSkipNode.nextNodesLines[atLevel].getColor() != Color.white) {
                    newSkipNode.nextNodesLines[atLevel].setColor(SkipListCanvas.TEXT_COLOR);
                }
                if (newSkipNode.nextNodesLines[atLevel].getColor() == Color.white) {
                    newSkipNode.nextNodesLines[atLevel].setColor(this.getBackground());
                }
                newSkipNode.nextNodesLines[atLevel].draw(g);
            }
            newSkipNode = newSkipNode.next[atLevel - 1];
            if (newSkipNode != null) {
                point.move(newSkipNode.nodes[0].getX(), newSkipNode.nodes[0].getY() - atLevel * newSkipNode.nodes[0].getHeight());
            }
        }
    }
    
    static {
        YES_COLOR = Color.red;
        NO_COLOR = Color.magenta;
        POINTER_COLOR = Color.gray;
        TEXT_COLOR = Color.black;
        SEARCH_LABEL = Messages.getString("SkipListCanvas.0");
        PTR_LABEL = Messages.getString("SkipListCanvas.1");
        TAILPTR_LABEL = Messages.getString("SkipListCanvas.2");
        SAVEPTR_LABEL = Messages.getString("SkipListCanvas.3");
        I_PTR = Messages.getString("SkipListCanvas.4");
        J_PTR = Messages.getString("SkipListCanvas.5");
        DATA_COND_LABEL = Messages.getString("SkipListCanvas.6");
        DATA_LABEL = Messages.getString("SkipListCanvas.7");
    }
}
