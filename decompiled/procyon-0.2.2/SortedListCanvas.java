import localization.*;
import java.awt.*;
import com.cim.AIA.*;

public class SortedListCanvas extends AlgorithmCanvas
{
    private SList linkedList;
    private ElementArray insertElementArray;
    private ElementArray searchElementArray;
    private Node ptr;
    private Node tailptr;
    private Node highlightedNode;
    private static final Color POINTER_COLOR;
    private static final Color TEXT_COLOR;
    private static final String SEARCH_LABEL;
    private static final String PTR_LABEL;
    private static final String TAILPTR_LABEL;
    private static final int GAP = 30;
    private static final int ELEMENT_WIDTH = 20;
    
    public void displayAlgorithm(final Graphics g) {
        if (this.insertElementArray != null) {
            this.insertElementArray.draw(g);
        }
        if (this.searchElementArray != null) {
            this.searchElementArray.draw(g);
            g.drawString(SortedListCanvas.SEARCH_LABEL, this.getParentSize().width / 2 - g.getFontMetrics().stringWidth(SortedListCanvas.SEARCH_LABEL) / 2, this.searchElementArray.getLocation().y);
        }
        if (this.linkedList != null) {
            this.draw(g);
            if (this.ptr != null) {
                final Line ptrLine = new Line(this.ptr.getX() + this.ptr.getWidth() + 10, this.ptr.getY() + 40, this.ptr.getX() + this.ptr.getWidth(), this.ptr.getY() + this.ptr.getHeight());
                ptrLine.setLabel(SortedListCanvas.PTR_LABEL);
                ptrLine.setDistanceFromStartForArrowHead(-3);
                ptrLine.setDistanceFromStartForLabel(-1);
                ptrLine.setXLabelOffset(-g.getFontMetrics().stringWidth(SortedListCanvas.PTR_LABEL) / 2);
                ptrLine.setYLabelOffset(g.getFontMetrics().getHeight());
                ptrLine.showArrowHead(true);
                ptrLine.setColor(SortedListCanvas.POINTER_COLOR);
                ptrLine.draw(g);
            }
            if (this.tailptr != null) {
                final Line tailptrLine = new Line(this.tailptr.getX() - 10, this.tailptr.getY() + 40, this.tailptr.getX(), this.tailptr.getY() + this.tailptr.getHeight());
                tailptrLine.setLabel(SortedListCanvas.TAILPTR_LABEL);
                tailptrLine.setDistanceFromStartForArrowHead(-3);
                tailptrLine.setDistanceFromStartForLabel(-1);
                tailptrLine.setXLabelOffset(-g.getFontMetrics().stringWidth(SortedListCanvas.TAILPTR_LABEL) / 2);
                tailptrLine.setYLabelOffset(g.getFontMetrics().getHeight());
                tailptrLine.showArrowHead(true);
                tailptrLine.setColor(SortedListCanvas.POINTER_COLOR);
                tailptrLine.draw(g);
            }
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        this.removeAllSelectables();
        if (e.paramObj != null) {
            int windowTop = 30;
            final int windowCenterX = this.getParentSize().width / 2;
            final SortedList sortedList = (SortedList)((SortedList)e.paramObj);
            this.linkedList = sortedList.getHead();
            if (sortedList.isBuildMode()) {
                this.insertElementArray = sortedList.getInsertElementArray();
            }
            else {
                this.insertElementArray = null;
            }
            if (sortedList.isSearchMode()) {
                this.addSelectionListener(SearchSelection.getInstance());
                this.searchElementArray = sortedList.getSearchElementArray();
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
            if (this.linkedList != null) {
                windowWidth = this.getParentSize().width;
                final Point point = new Point(30, windowTop);
                this.linkedList.node.setPosition(point);
                final Point nextpoint = new Point(point.x + this.linkedList.node.getWidth(), point.y);
                this.linkedList.nextNode.setPosition(nextpoint);
                this.ptr = sortedList.getPtrNode();
                this.tailptr = sortedList.getTailPtrNode();
                windowTop = windowTop + this.linkedList.getHeight();
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
        }
        this.repaint();
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    public void draw(final Graphics g) {
        SList newList = this.linkedList;
        int counter = 0;
        final Point point = new Point(this.linkedList.node.getPosition());
        while (newList != null) {
            System.out.println(Messages.getString("SortedListCanvas.5") + String.valueOf(counter));
            final Node currentNode = newList.node;
            final Node currentNextNode = newList.nextNode;
            currentNode.setPosition(point);
            currentNode.draw(g);
            point.move(point.x + currentNode.getWidth(), point.y);
            currentNextNode.setPosition(point);
            currentNextNode.draw(g);
            if (newList.next != null) {
                newList.nextNodeLine = new Line(currentNextNode.getX() + currentNextNode.getWidth() / 2, currentNextNode.getY() + currentNextNode.getHeight() / 2, currentNextNode.getX() + 40, currentNextNode.getY() + currentNextNode.getHeight() / 2);
                newList.nextNodeLine.setDistanceFromStartForArrowHead(-3);
                newList.nextNodeLine.showArrowHead(true);
                newList.nextNodeLine.setColor(SortedListCanvas.TEXT_COLOR);
                newList.nextNodeLine.draw(g);
            }
            newList = newList.next;
            ++counter;
            point.move(point.x + 40, point.y);
            if (newList == null) {
                System.out.println(Messages.getString("SortedListCanvas.4"));
                return;
            }
            System.out.println("newList.next NOT null");
        }
    }
    
    static {
        POINTER_COLOR = Color.gray;
        TEXT_COLOR = Color.black;
        SEARCH_LABEL = Messages.getString("SortedListCanvas.0");
        PTR_LABEL = Messages.getString("SortedListCanvas.1");
        TAILPTR_LABEL = Messages.getString("SortedListCanvas.2");
    }
}
