import java.util.*;
import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class SplayTreeCanvas extends AlgorithmCanvas
{
    private int tweenSpeed;
    private SplayTreeCodeStack codeStack;
    private SplayTreeNode head;
    private ElementArray insertData;
    private ElementArray searchData;
    private SplayTreeNode currentNode;
    private boolean isShowTween;
    
    public SplayTreeCanvas() {
        super();
        this.isShowTween = true;
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
        if (this.currentNode != null) {
            final String splayPointer = Messages.getString("SplayTreeCanvas.0");
            this.drawLine(g, new Point(g.getFontMetrics().stringWidth(splayPointer), top), this.currentNode.getPosition(), splayPointer);
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
            final SplayTree splayTree = (SplayTree)((SplayTree)e.paramObj);
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
            if (this.isShowTween && multiTweensArray != null) {
                for (int i = 0; i < multiTweensArray.size(); ++i) {
                    final Object element = multiTweensArray.elementAt(i);
                    if (!(element instanceof Hashtable<K, V>)) {
                        if (!(element instanceof MultipleTween)) {
                            throw new RuntimeException("Vector of unexpected type");
                        }
                        this.addTween((MultipleTween)element);
                    }
                    if (this.tweens.getNumberOfTweens() > 0) {
                        this.tweens.run();
                    }
                }
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
        line.setColor(SplayTreeColors.POINTER_COLOR);
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
