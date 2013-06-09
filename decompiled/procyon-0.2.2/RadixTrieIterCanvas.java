import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class RadixTrieIterCanvas extends AlgorithmCanvas
{
    private RadixTrieIterNode head;
    private RadixTrieIterNode currentNode;
    private RadixTrieIterDigitalElementArray insertBitArray;
    private RadixTrieIterDigitalElementArray searchBitArray;
    private RadixTrieIterDigitalElementArray compareBitArray;
    private RadixTrieIterDigitalElementArray finalCompareBitArray;
    private ElementArray insertData;
    private ElementArray searchData;
    private Node levelNode;
    private int treeTop;
    private int mostSignificantBit;
    private int currentLevel;
    private Vector<E> insertedData;
    private int insertedDataWidth;
    private static final int Y_GAP = 20;
    private static final int X_GAP = 20;
    
    public RadixTrieIterCanvas() {
        super();
        this.insertedDataWidth = 70;
    }
    
    public void displayAlgorithm(final Graphics g) {
        try {
            if (this.head != null) {
                this.head.draw(g);
            }
            if (this.levelNode != null) {
                final int windowBottom = this.getParentSize().height - 30;
                g.drawString(Messages.getString("RadixTrieIterCanvas.0"), 20, windowBottom);
                this.levelNode.setPosition(new Point(g.getFontMetrics().stringWidth(Messages.getString("RadixTrieIterCanvas.1")) + 20, windowBottom - 15));
                this.levelNode.draw(g);
            }
            if (this.insertData != null) {
                this.insertData.draw(g);
            }
            if (this.searchData != null) {
                this.searchData.draw(g);
            }
            if (this.insertBitArray != null) {
                this.insertBitArray.draw(g);
            }
            if (this.searchBitArray != null) {
                this.searchBitArray.draw(g);
            }
            if (this.finalCompareBitArray != null) {
                this.finalCompareBitArray.draw(g);
            }
            if (this.compareBitArray != null) {
                this.compareBitArray.draw(g);
            }
            if (this.currentNode != null && !(this.head instanceof RadixTrieIterNullNode)) {
                final Point start = new Point(40, this.head.getRectangle().y);
                final Point position;
                final Point end = position = this.currentNode.getPosition();
                position.x = position.x - this.currentNode.getRadius() / 2;
                final Point point = end;
                point.y = point.y - this.currentNode.getRadius() / 2;
                this.drawLine(g, start, end, Messages.getString("RadixTrieIterCanvas.2"));
            }
            this.drawInsertedData(g, this.head, new Point(5, this.head.getRectangle().y + 20));
        }
        catch (Exception e) {
            System.out.println("Caught this exception");
            e.printStackTrace();
        }
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        if (e.paramObj != null) {
            int windowTop = 20;
            final int windowBottom = this.getParentSize().height - 30;
            final int windowCenterX = this.getParentSize().width / 2;
            final RadixTrieIter radixTrieAlgorithm = (RadixTrieIter)((RadixTrieIter)e.paramObj);
            this.mostSignificantBit = radixTrieAlgorithm.getMostSignificantBit();
            this.currentLevel = radixTrieAlgorithm.getCurrentLevel();
            if (this.currentLevel >= 0) {
                this.levelNode = new Node(new Integer(this.currentLevel), 0);
            }
            else {
                this.levelNode = null;
            }
            this.insertData = radixTrieAlgorithm.getInsertData();
            if (this.insertData != null) {
                this.insertData.setLocation(windowCenterX - this.insertData.getWidth() / 2, windowTop);
                windowTop = windowTop + (this.insertData.getHeight() + 20);
            }
            this.insertBitArray = radixTrieAlgorithm.getInsertBitArray();
            if (this.insertBitArray != null) {
                this.insertBitArray.setLabel(Messages.getString("RadixTrieIterCanvas.4"));
                this.insertBitArray.setPosition(new Point(windowCenterX - this.insertBitArray.getWidth() / 2, windowTop));
                windowTop = windowTop + (this.insertBitArray.getHeight() + 20);
            }
            this.compareBitArray = radixTrieAlgorithm.getCompareBitArray();
            if (this.compareBitArray != null) {
                this.compareBitArray.setLabel(Messages.getString("RadixTrieIterCanvas.5"));
                this.compareBitArray.setPosition(new Point(windowCenterX - this.compareBitArray.getWidth() / 2, windowTop));
                windowTop = windowTop + (this.compareBitArray.getHeight() + 20);
            }
            this.currentNode = radixTrieAlgorithm.getCurrentNode();
            this.head = radixTrieAlgorithm.getHead();
            if (this.head != null) {
                if (this.treeTop < windowTop) {
                    this.treeTop = windowTop;
                }
                else if (this.insertData == null) {
                    this.treeTop = windowTop;
                }
                this.head.setPosition(new Point(windowCenterX - this.head.getRectangle().width / 2, this.treeTop));
                final int leftPoint = this.head.getRectangle().x;
                if (leftPoint < this.insertedDataWidth) {
                    this.head.setPosition(new Point(this.head.getPosition().x - leftPoint + this.insertedDataWidth, this.head.getPosition().y));
                }
                windowTop = windowTop + (this.head.getRectangle().y + this.head.getRectangle().height + 20);
            }
            this.searchBitArray = radixTrieAlgorithm.getSearchBitArray();
            if (this.searchBitArray != null) {
                this.searchBitArray.setLabel(Messages.getString("RadixTrieIterCanvas.6"));
                this.searchBitArray.setPosition(new Point(windowCenterX - this.searchBitArray.getWidth() / 2, windowTop));
                windowTop = windowTop + (this.searchBitArray.getHeight() + 20);
            }
            this.finalCompareBitArray = radixTrieAlgorithm.getFinalCompareBitArray();
            if (this.finalCompareBitArray != null) {
                this.finalCompareBitArray.setLabel(Messages.getString("RadixTrieIterCanvas.7"));
                this.finalCompareBitArray.setPosition(new Point(windowCenterX - this.finalCompareBitArray.getWidth() / 2, windowTop));
                windowTop = windowTop + (this.finalCompareBitArray.getHeight() + 20);
            }
            this.searchData = radixTrieAlgorithm.getSearchData();
            if (this.searchData != null) {
                this.searchData.setLocation(windowCenterX - this.searchData.getWidth() / 2, windowTop);
                windowTop = windowTop + (this.searchData.getHeight() + 20);
            }
            this.insertedData = radixTrieAlgorithm.getInsertedData();
            this.repaint();
        }
    }
    
    private void drawLine(final Graphics g, final Point start, final Point end, final String label) {
        final Line line = new Line(start.x, start.y, end.x, end.y);
        line.setLabel(label);
        line.setDistanceFromStartForArrowHead(-3);
        line.setDistanceFromStartForLabel(-1);
        line.setXLabelOffset(-g.getFontMetrics().stringWidth(label) / 2);
        line.showArrowHead(true);
        line.setColor(RadixTrieIterColors.POINTER_COLOR);
        line.draw(g);
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    private void drawInsertedData(final Graphics g, final RadixTrieIterNode node, final Point pos) {
        if (this.insertedData != null) {
            for (int i = 0; i < this.insertedData.size(); ++i) {
                final RadixTrieIterExternalNode radixNode = (RadixTrieIterExternalNode)this.insertedData.elementAt(i);
                final Integer data = new Integer(radixNode.getKey());
                final Node newNode = new Node(data, 0);
                newNode.setPosition(pos);
                newNode.draw(g);
                int x = pos.x + newNode.getWidth();
                int j = 0;
                while (j <= this.mostSignificantBit) {
                    final String string = "" + this.getBit(j, data.intValue());
                    if (j < radixNode.getLevel()) {
                        g.setColor(RadixTrieIterColors.DEFAULT_BIT_USEFUL_COLOR);
                    }
                    else {
                        g.setColor(RadixTrieIterColors.DEFAULT_BIT_NOT_USEFUL_COLOR);
                    }
                    g.drawString(string, x, pos.y + newNode.getHeight() / 2 + g.getFontMetrics().getHeight() / 2);
                    x = x + g.getFontMetrics().stringWidth(string);
                    ++j;
                }
                g.setColor(Color.black);
                pos.y = pos.y + newNode.getHeight();
            }
        }
    }
    
    private byte getBit(final int bitNumber, final int number) {
        return (byte)((number >> this.mostSignificantBit - bitNumber) % 2);
    }
}
