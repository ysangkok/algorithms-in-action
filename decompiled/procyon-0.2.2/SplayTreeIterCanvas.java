import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class SplayTreeIterCanvas extends AlgorithmCanvas
{
    private int tweenSpeed;
    protected Hashtable<SplayTreeIterNode, Point> oldPositions;
    private static final int XGAP = 10;
    private static final int YGAP = 5;
    private SplayTreeIterCodeStack codeStack;
    private SplayTreeIterNode head;
    private ElementArray insertData;
    private ElementArray searchData;
    private SplayTreeIterNode currentNode;
    private SplayTreeIterNode parentNode;
    private boolean isShowTween;
    private boolean noMoveTween;
    private int currentLevel;
    
    public SplayTreeIterCanvas() {
        super();
        this.isShowTween = true;
    }
    
    protected Hashtable<SplayTreeIterNode, Point> getPositions(final SplayTreeIterNode head) {
        if (head instanceof SplayTreeIterNullNode) {
            return null;
        }
        final Hashtable<SplayTreeIterNode, Point> positions = new Hashtable();
        this.getPositions(head, positions);
        return positions;
    }
    
    protected void getPositions(final SplayTreeIterNode head, final Hashtable<SplayTreeIterNode, Point> positions) {
        positions.put(head, new Point(head.getPosition()));
        if (!(head instanceof SplayTreeIterNullNode)) {
            this.getPositions(((SplayTreeIterDataNode)head).getLeft(), positions);
            this.getPositions(((SplayTreeIterDataNode)head).getRight(), positions);
        }
    }
    
    protected MultipleTween getMoveTweens(final SplayTreeIterNode head, final Hashtable<SplayTreeIterNode, Point> oldPositions, final Hashtable<SplayTreeIterNode, Point> currentPositions) {
        final MultipleTween multipleTweens = new MultipleTween(this);
        this.getMoveTweens(head, oldPositions, currentPositions, multipleTweens);
        return multipleTweens;
    }
    
    protected void getMoveTweens(final SplayTreeIterNode head, final Hashtable<SplayTreeIterNode, Point> oldPositions, final Hashtable<SplayTreeIterNode, Point> currentPositions, final MultipleTween multipleTweens) {
        final Point oldPoint = (Point)oldPositions.get(head);
        final Point currentPoint = (Point)currentPositions.get(head);
        if (oldPoint != null && currentPoint != null && (oldPoint.x != currentPoint.x || oldPoint.y != currentPoint.y)) {
            multipleTweens.add(new MoveTween(null, head, oldPoint, currentPoint, false, this.numberOfTweenSteps));
        }
        if (!(head instanceof SplayTreeIterNullNode)) {
            this.getMoveTweens(((SplayTreeIterDataNode)head).getLeft(), oldPositions, currentPositions, multipleTweens);
            this.getMoveTweens(((SplayTreeIterDataNode)head).getRight(), oldPositions, currentPositions, multipleTweens);
        }
    }
    
    public void displayAlgorithm(final Graphics g) {
        int top;
        final int gap = top = 15;
        if (this.insertData != null) {
            this.insertData.draw(g);
            top = this.insertData.getLocation().y + this.insertData.getHeight() + gap;
        }
        if (this.head != null) {
            this.head.draw(g);
        }
        if (this.searchData != null) {
            this.searchData.draw(g);
        }
        top = top + 5;
        if (this.parentNode != null) {
            final String parentPointer = Messages.getString("SplayTreeIterCanvas.0");
            this.drawLine(g, new Point(g.getFontMetrics().stringWidth(parentPointer) / 2 + 10, top), this.parentNode.getPosition(), parentPointer);
        }
        top = top + g.getFontMetrics().getHeight() + 5;
        if (this.currentNode != null) {
            final String splayPointer = Messages.getString("SplayTreeIterCanvas.1");
            this.drawLine(g, new Point(g.getFontMetrics().stringWidth(splayPointer) / 2 + 10, top), this.currentNode.getPosition(), splayPointer);
        }
        top = top + g.getFontMetrics().getHeight() + 5;
        if (this.currentLevel != -1) {
            g.drawString(Messages.getString("SplayTreeIterCanvas.2"), 10, top);
            final Node node;
            final Node newNode = node = new Node(new Integer(this.currentLevel), -1);
            node.setPosition(new Point(10 + g.getFontMetrics().stringWidth(Messages.getString("SplayTreeIterCanvas.3")), top - g.getFontMetrics().getHeight()));
            newNode.draw(g);
        }
        if (this.codeStack != null) {
            this.codeStack.draw(g);
        }
    }
    
    public void paint() {
        this.repaint();
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            final int windowCenterX = this.getParentSize().width / 2;
            int windowTop;
            final int gapWidth = windowTop = 25;
            final SplayTreeIter splayTree = (SplayTreeIter)((SplayTreeIter)e.paramObj);
            this.insertData = splayTree.getInsertData();
            if (this.insertData != null) {
                this.insertData.setLocation(windowCenterX - this.insertData.getWidth() / 2, windowTop);
                windowTop = windowTop + (this.insertData.getHeight() + gapWidth);
            }
            this.head = splayTree.getHead();
            if (this.head != null) {
                this.head.setUpperLeft(new Point(20, windowTop));
                this.head.setUpperLeft(new Point(windowCenterX - this.head.getRectangle().width / 2, windowTop));
                windowTop = windowTop + (this.head.getRectangle().height + gapWidth);
            }
            this.searchData = splayTree.getSearchData();
            if (this.searchData != null) {
                this.searchData.setLocation(windowCenterX - this.searchData.getWidth() / 2, windowTop);
                windowTop = windowTop + (this.searchData.getHeight() + gapWidth);
            }
            this.currentNode = splayTree.getCurrentNode();
            this.parentNode = splayTree.getParentNode();
            this.currentLevel = splayTree.getCurrentLevel();
            this.codeStack = splayTree.getCodeStack();
            if (this.codeStack != null) {
                this.codeStack.setPosition(new Point(this.getParentSize().width - this.codeStack.getWidth(), this.getParentSize().height - gapWidth));
            }
            this.repaint();
            final Vector<E> multiTweensArray = splayTree.getTweens(this, null, this.numberOfTweenSteps);
            if (splayTree.isTweenFast()) {
                super.setTweenSpeed(this.tweenSpeed / 3);
            }
            else {
                super.setTweenSpeed(this.tweenSpeed);
            }
            this.noMoveTween = splayTree.getNoMoveTween();
            final Hashtable<SplayTreeIterNode, Point> currentPositions = this.getPositions(this.head);
            if (this.oldPositions != null && !this.noMoveTween) {
                final MultipleTween moveTweens = this.getMoveTweens(this.head, this.oldPositions, currentPositions);
                if (moveTweens.getNumberOfTweens() > 0) {
                    this.addTween(moveTweens);
                }
            }
            this.oldPositions = currentPositions;
            if (this.isShowTween && multiTweensArray != null) {
                for (int i = 0; i < multiTweensArray.size(); ++i) {
                    final Object element = multiTweensArray.elementAt(i);
                    if (!(element instanceof Hashtable<K, V>)) {
                        if (!(element instanceof MultipleTween)) {
                            throw new RuntimeException(Messages.getString("SplayTreeIterCanvas.4"));
                        }
                        this.addTween((MultipleTween)element);
                    }
                }
            }
            if (this.tweens.getNumberOfTweens() > 0) {
                this.tweens.run();
            }
        }
    }
    
    private void drawLine(final Graphics g, final Point start, final Point end, final String label) {
        final Line line = new Line(start.x, start.y, end.x, end.y);
        line.setLabel(label);
        line.setDistanceFromStartForArrowHead(-3);
        line.setDistanceFromStartForLabel(-1);
        line.setXLabelOffset(-g.getFontMetrics().stringWidth(label) / 2);
        line.showArrowHead(true);
        line.setColor(SplayTreeIterColors.POINTER_COLOR);
        line.draw(g);
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    public void setTweenSpeed(final int speed) {
        this.tweenSpeed = speed;
        super.setTweenSpeed(speed);
    }
    
    public void setIsShowTween(final boolean isShowTween) {
        this.isShowTween = isShowTween;
    }
}
