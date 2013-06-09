// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieMultiDigitalElementArray.java

import com.cim.AIA.*;
import java.awt.*;
import localization.Messages;

public class RadixTrieMultiDigitalElementArray
    implements Drawable, Selectable
{

    public RadixTrieMultiDigitalElementArray(int data, int numberOfBits, boolean isShow)
    {
        this(data, numberOfBits);
        isShowDigits = isShow;
    }

    public RadixTrieMultiDigitalElementArray(int data, int numberOfBits)
    {
        isShowBitNumber = false;
        isShowDigits = true;
        this.numberOfBits = numberOfBits;
        this.data = data;
        bitElementArray = new ElementArray(0, 0);
        bitElementArray.setColumGap(0);
        bitElementArray.setElementWidth(20);
        bitElementArray.setAllHaveSameWidth(true);
        for(int i = 0; i <= numberOfBits; i++)
        {
            Node node = new Node(new Integer(getBit(i)), i);
            bitElementArray.setValue(i, node);
        }

        dataNode = new Node(new Integer(data), 0);
        setPosition(new Point(0, 0));
        isShowDigits = true;
    }

    public void setIsShowBitNumber(boolean isShowBitNumber)
    {
        if(isShowBitNumber ^ this.isShowBitNumber)
        {
            if(bitNumbers == null)
            {
                bitNumbers = new StringMarker[bitElementArray.getSize()];
                for(int i = 0; i < bitElementArray.getSize(); i++)
                    bitNumbers[i] = new StringMarker((new StringBuilder()).append("").append(i).toString());

            }
            for(int i = 0; i < bitElementArray.getSize(); i++)
            {
                Node tempNode = (Node)bitElementArray.getElement(i);
                tempNode.removeMarker(bitNumbers[i]);
                tempNode.addMarker(bitNumbers[i]);
                tempNode.setMarkersBelowValue(false);
            }

        } else
        if(!isShowBitNumber && bitNumbers != null)
        {
            for(int i = 0; i < bitElementArray.getSize(); i++)
                bitElementArray.getElement(i).removeMarker(bitNumbers[i]);

        }
        this.isShowBitNumber = isShowBitNumber;
    }

    private int getBit(int bit)
    {
        return (data >> numberOfBits - bit) % 2;
    }

    public void draw(Graphics g, Point location)
    {
        setPosition(location);
        draw(g);
    }

    public void draw(Graphics g)
    {
        if(isShowDigits)
            bitElementArray.draw(g);
        dataNode.draw(g);
        g.drawString("=", (bitElementArray.getLocation().x + bitElementArray.getWidth() + 10) - g.getFontMetrics().stringWidth("=") / 2, bitElementArray.getLocation().y + bitElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 2);
        if(label != null)
        {
            g.setColor(DEFAULT_TEXT_COLOR);
            g.drawString(label, getPosition().x - g.getFontMetrics().stringWidth(label), getPosition().y + bitElementArray.getHeight() / 2 + g.getFontMetrics().getHeight() / 2);
        }
    }

    public void addMarker(StringMarker marker)
    {
        dataNode.addMarker(marker);
    }

    public void removeAllMarkers()
    {
        dataNode.removeAllMarkers();
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public void setColors(Color background, Color highlighted)
    {
        for(int i = 0; i < bitElementArray.getSize(); i++)
        {
            Node node = (Node)bitElementArray.getElement(i);
            node.setBackgroundColor(background);
            node.setHighlightColor(highlighted);
        }

        dataNode.setBackgroundColor(background);
        dataNode.setHighlightColor(highlighted);
    }

    public void setPosition(Point position)
    {
        bitElementArray.setLocation(position.x, position.y);
        dataNode.setPosition(new Point(position.x + bitElementArray.getWidth() + 20, position.y));
    }

    public Point getPosition()
    {
        return bitElementArray.getLocation();
    }

    public int getWidth()
    {
        return bitElementArray.getWidth() + 20 + dataNode.getWidth();
    }

    public int getHeight()
    {
        return bitElementArray.getHeight();
    }

    public boolean equals(Selectable selectable)
    {
        return false;
    }

    public int getIdentifier()
    {
        return 0;
    }

    public void highlight()
    {
    }

    public void unHighlight()
    {
        for(int i = 0; i < bitElementArray.getSize(); i++)
        {
            ((Node)bitElementArray.getElement(i)).unHighlight();
            if(bitNumbers != null)
                bitNumbers[i].setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
        }

    }

    public void highlightBit(byte bit)
    {
        if(bitElementArray.getSize() > bit)
            ((Node)bitElementArray.getElement(bit)).highlight();
    }

    public void highlightNumber(byte bit, Color highlightColor)
    {
        if(bitNumbers != null)
            bitNumbers[bit].setBackgroundColor(highlightColor);
    }

    public Selectable getItemAt(Point point)
    {
        return null;
    }

    private static final String BIT_ARRAY_LABEL = Messages.getString("RadixTrieMultiDigitalElementArray.0");
    private ElementArray bitElementArray;
    private int numberOfBits;
    private Node dataNode;
    private int data;
    private String label;
    private static final int GAP = 20;
    boolean isShowBitNumber;
    private boolean isShowDigits;
    private StringMarker bitNumbers[];
    private static final Color DEFAULT_TEXT_COLOR;
    private static final Color DEFAULT_BACKGROUND_COLOR;

    static 
    {
        DEFAULT_TEXT_COLOR = Color.black;
        DEFAULT_BACKGROUND_COLOR = Color.white;
    }
}
