package com.cim.AIA;

import java.awt.*;

public class DigitalElementArray implements Drawable, Selectable
{
    private static final int GAP = 20;
    private static final Color DEFAULT_TEXT_COLOR;
    private static final Color DEFAULT_BACKGROUND_COLOR;
    private ElementArray bitElementArray;
    private int numberOfBits;
    private Node dataNode;
    private int data;
    private String label;
    boolean isShowBitNumber;
    private StringMarker[] bitNumbers;
    
    public DigitalElementArray(final int data, final int numberOfBits) {
        super();
        this.isShowBitNumber = false;
        this.numberOfBits = numberOfBits;
        this.data = data;
        this.bitElementArray = new ElementArray(0, 0);
        this.bitElementArray.setColumGap(0);
        this.bitElementArray.setElementWidth(20);
        this.bitElementArray.setAllHaveSameWidth(true);
        for (int i = 0; i <= numberOfBits; ++i) {
            final Node node = new Node(new Integer(this.getBit(i)), i);
            this.bitElementArray.setValue(i, node);
        }
        this.dataNode = new Node(new Integer(data), 0);
        this.setPosition(new Point(0, 0));
    }
    
    public void draw(final Graphics g) {
        this.bitElementArray.draw(g);
        this.dataNode.draw(g);
        g.drawString("=", this.bitElementArray.getLocation().x + this.bitElementArray.getWidth() + 10 - g.getFontMetrics().stringWidth("=") / 2, this.bitElementArray.getLocation().y + this.bitElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 2);
        if (this.label != null) {
            g.setColor(DigitalElementArray.DEFAULT_TEXT_COLOR);
            g.drawString(this.label, this.getPosition().x - g.getFontMetrics().stringWidth(this.label), this.getPosition().y + this.bitElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 2);
        }
    }
    
    public void draw(final Graphics g, final Point location) {
        this.setPosition(location);
        this.draw(g);
    }
    
    public boolean equals(final Selectable selectable) {
        return false;
    }
    
    private int getBit(final int bit) {
        return (this.data >> this.numberOfBits - bit) % 2;
    }
    
    public int getHeight() {
        return this.bitElementArray.getHeight();
    }
    
    public int getIdentifier() {
        return 0;
    }
    
    public Selectable getItemAt(final Point point) {
        return null;
    }
    
    public Point getPosition() {
        return this.bitElementArray.getLocation();
    }
    
    public int getWidth() {
        return this.bitElementArray.getWidth() + 20 + this.dataNode.getWidth();
    }
    
    public void highlight() {
    }
    
    public void highlightBit(final byte bit) {
        if (this.bitElementArray.getSize() > bit) {
            ((Node)this.bitElementArray.getElement(bit)).highlight();
        }
    }
    
    public void highlightNumber(final byte bit, final Color highlightColor) {
        if (this.bitNumbers != null) {
            this.bitNumbers[bit].setBackgroundColor(highlightColor);
        }
    }
    
    public void setColors(final Color background, final Color highlighted) {
        for (int i = 0; i < this.bitElementArray.getSize(); ++i) {
            final Node node = (Node)this.bitElementArray.getElement(i);
            node.setBackgroundColor(background);
            node.setHighlightColor(highlighted);
        }
        this.dataNode.setBackgroundColor(background);
        this.dataNode.setHighlightColor(highlighted);
    }
    
    public void setIsShowBitNumber(final boolean isShowBitNumber) {
        if (isShowBitNumber ^ this.isShowBitNumber) {
            if (this.bitNumbers == null) {
                this.bitNumbers = new StringMarker[this.bitElementArray.getSize()];
                for (int i = 0; i < this.bitElementArray.getSize(); ++i) {
                    this.bitNumbers[i] = new StringMarker("" + i);
                }
            }
            for (int j = 0; j < this.bitElementArray.getSize(); ++j) {
                final Node tempNode = (Node)this.bitElementArray.getElement(j);
                tempNode.removeMarker(this.bitNumbers[j]);
                tempNode.addMarker(this.bitNumbers[j]);
                tempNode.setMarkersBelowValue(false);
            }
        }
        else if (!isShowBitNumber && this.bitNumbers != null) {
            for (int k = 0; k < this.bitElementArray.getSize(); ++k) {
                this.bitElementArray.getElement(k).removeMarker(this.bitNumbers[k]);
            }
        }
        this.isShowBitNumber = isShowBitNumber;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public void setPosition(final Point position) {
        this.bitElementArray.setLocation(position.x, position.y);
        this.dataNode.setPosition(new Point(position.x + this.bitElementArray.getWidth() + 20, position.y));
    }
    
    public void unHighlight() {
        for (int i = 0; i < this.bitElementArray.getSize(); ++i) {
            ((Node)this.bitElementArray.getElement(i)).unHighlight();
            if (this.bitNumbers != null) {
                this.bitNumbers[i].setBackgroundColor(DigitalElementArray.DEFAULT_BACKGROUND_COLOR);
            }
        }
    }
    
    static {
        DEFAULT_TEXT_COLOR = Color.black;
        DEFAULT_BACKGROUND_COLOR = Color.white;
    }
}
