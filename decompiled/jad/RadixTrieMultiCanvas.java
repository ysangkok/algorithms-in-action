// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieMultiCanvas.java

import com.cim.AIA.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.Vector;
import localization.Messages;

public class RadixTrieMultiCanvas extends AlgorithmCanvas
{

    public RadixTrieMultiCanvas()
    {
        insertedDataWidth = 70;
    }

    public void displayAlgorithm(Graphics g)
    {
        try
        {
            if(head != null)
                head.draw(g);
            if(levelNode != null)
            {
                int windowBottom = getParentSize().height - 30;
                g.drawString(Messages.getString("RadixTrieMultiCanvas.0"), 20, windowBottom);
                levelNode.setPosition(new Point(g.getFontMetrics().stringWidth(Messages.getString("RadixTrieMultiCanvas.1")) + 20, windowBottom - 15));
                levelNode.draw(g);
            }
            if(insertData != null)
                insertData.draw(g);
            if(searchData != null)
                searchData.draw(g);
            if(insertBitArray != null)
                insertBitArray.draw(g);
            if(searchBitArray != null)
                searchBitArray.draw(g);
            if(finalCompareBitArray != null)
                finalCompareBitArray.draw(g);
            if(compareBitArray != null)
                compareBitArray.draw(g);
            if(currentNode != null && !(head instanceof RadixTrieMultiNullNode))
            {
                Point start = new Point(40, head.getRectangle().y);
                Point end = currentNode.getPosition();
                end.x -= currentNode.getRadius() / 2;
                end.y -= currentNode.getRadius() / 2;
                drawLine(g, start, end, "Current Trie");
            }
            drawInsertedData(g, head, new Point(5, head.getRectangle().y + 20));
        }
        catch(Exception e)
        {
            System.out.println("Caught this exception");
            e.printStackTrace();
        }
    }

    public void processRepaintEvent(RepaintEvent e)
    {
        if(e.paramObj != null)
        {
            int windowTop = 20;
            int windowBottom = getParentSize().height - 30;
            int windowCenterX = getParentSize().width / 2;
            RadixTrieMulti radixTrieAlgorithm = (RadixTrieMulti)(RadixTrieMulti)e.paramObj;
            mostSignificantBit = radixTrieAlgorithm.getMostSignificantBit();
            currentLevel = radixTrieAlgorithm.getCurrentLevel();
            if(currentLevel >= 0)
                levelNode = new Node(new Integer(currentLevel / RadixTrieMulti.NO_OF_BITS), 0);
            else
                levelNode = null;
            insertData = radixTrieAlgorithm.getInsertData();
            if(insertData != null)
            {
                insertData.setLocation(windowCenterX - insertData.getWidth() / 2, windowTop);
                windowTop += insertData.getHeight() + 20;
            }
            insertBitArray = radixTrieAlgorithm.getInsertBitArray();
            if(insertBitArray != null)
            {
                insertBitArray.setLabel(Messages.getString("RadixTrieMultiCanvas.4"));
                insertBitArray.setPosition(new Point(windowCenterX - insertBitArray.getWidth() / 2, windowTop));
                windowTop += insertBitArray.getHeight() + 20;
            }
            compareBitArray = radixTrieAlgorithm.getCompareBitArray();
            if(compareBitArray != null)
            {
                compareBitArray.setLabel(Messages.getString("RadixTrieMultiCanvas.5"));
                compareBitArray.setPosition(new Point(windowCenterX - compareBitArray.getWidth() / 2, windowTop));
                windowTop += compareBitArray.getHeight() + 20;
            }
            currentNode = radixTrieAlgorithm.getCurrentNode();
            head = radixTrieAlgorithm.getHead();
            if(head != null)
            {
                if(treeTop < windowTop)
                    treeTop = windowTop;
                else
                if(insertData == null)
                    treeTop = windowTop;
                head.setPosition(new Point(windowCenterX - head.getRectangle().width / 2, treeTop));
                int leftPoint = head.getRectangle().x;
                if(leftPoint < insertedDataWidth)
                    head.setPosition(new Point((head.getPosition().x - leftPoint) + insertedDataWidth, head.getPosition().y));
                windowTop += head.getRectangle().y + head.getRectangle().height + 20;
            }
            searchBitArray = radixTrieAlgorithm.getSearchBitArray();
            if(searchBitArray != null)
            {
                searchBitArray.setLabel(Messages.getString("RadixTrieMultiCanvas.6"));
                searchBitArray.setPosition(new Point(windowCenterX - searchBitArray.getWidth() / 2, windowTop));
                windowTop += searchBitArray.getHeight() + 20;
            }
            finalCompareBitArray = radixTrieAlgorithm.getFinalCompareBitArray();
            if(finalCompareBitArray != null)
            {
                finalCompareBitArray.setLabel(Messages.getString("RadixTrieMultiCanvas.7"));
                finalCompareBitArray.setPosition(new Point(windowCenterX - finalCompareBitArray.getWidth() / 2, windowTop));
                windowTop += finalCompareBitArray.getHeight() + 20;
            }
            searchData = radixTrieAlgorithm.getSearchData();
            if(searchData != null)
            {
                searchData.setLocation(windowCenterX - searchData.getWidth() / 2, windowTop);
                windowTop += searchData.getHeight() + 20;
            }
            insertedData = radixTrieAlgorithm.getInsertedData();
            repaint();
        }
    }

    private void drawLine(Graphics g, Point start, Point end, String label)
    {
        Line line = new Line(start.x, start.y, end.x, end.y);
        line.setLabel(label);
        line.setDistanceFromStartForArrowHead(-3);
        line.setDistanceFromStartForLabel(-1);
        line.setXLabelOffset(-g.getFontMetrics().stringWidth(label) / 2);
        line.showArrowHead(true);
        line.setColor(RadixTrieMultiColors.POINTER_COLOR);
        line.draw(g);
    }

    protected void handleColorSelection(ColorSelection colorselection)
    {
    }

    protected void handleFontSelection(FontSelection fontselection)
    {
    }

    private void drawInsertedData(Graphics g, RadixTrieMultiNode node, Point pos)
    {
        if(insertedData != null)
        {
            for(int i = 0; i < insertedData.size(); i++)
            {
                RadixTrieMultiExternalNode radixNode = (RadixTrieMultiExternalNode)insertedData.elementAt(i);
                Integer data = new Integer(radixNode.getKey());
                Node newNode = new Node(data, 0);
                newNode.setPosition(pos);
                newNode.draw(g);
                int x = pos.x + newNode.getWidth();
                for(int j = 0; j <= mostSignificantBit; j++)
                {
                    String string = (new StringBuilder()).append("").append(getBit(j, data.intValue())).toString();
                    if(j < radixNode.getLevel())
                        g.setColor(RadixTrieMultiColors.DEFAULT_BIT_USEFUL_COLOR);
                    else
                        g.setColor(RadixTrieMultiColors.DEFAULT_BIT_NOT_USEFUL_COLOR);
                    g.drawString(string, x, pos.y + newNode.getHeight() / 2 + g.getFontMetrics().getHeight() / 2);
                    x += g.getFontMetrics().stringWidth(string);
                }

                g.setColor(Color.black);
                pos.y += newNode.getHeight();
            }

        }
    }

    private byte getBit(int bitNumber, int number)
    {
        return (byte)((number >> mostSignificantBit - bitNumber) % 2);
    }

    private RadixTrieMultiNode head;
    private RadixTrieMultiNode currentNode;
    private RadixTrieMultiDigitalElementArray insertBitArray;
    private RadixTrieMultiDigitalElementArray searchBitArray;
    private RadixTrieMultiDigitalElementArray compareBitArray;
    private RadixTrieMultiDigitalElementArray finalCompareBitArray;
    private ElementArray insertData;
    private ElementArray searchData;
    private Node levelNode;
    private int treeTop;
    private int mostSignificantBit;
    private int currentLevel;
    private Vector insertedData;
    private int insertedDataWidth;
    private static final int Y_GAP = 20;
    private static final int X_GAP = 20;
}
