// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTreeIterDataItem.java

import com.cim.AIA.Node;

public class PatriciaTreeIterDataItem
{

    PatriciaTreeIterDataItem()
    {
        key = 0;
        node = new Node("0", 0);
        node.setBackgroundColor(PatriciaTreeIterColors.DATA_ITEM_COLOR);
        node.setHighlightColor(PatriciaTreeIterColors.DATA_ITEM_HIGHLIGHT_COLOR);
    }

    PatriciaTreeIterDataItem(Node node, int key)
    {
        this.node = node;
        this.key = key;
        this.node.setWidth(node.getWidth());
        this.node.setMarkersBelowValue(false);
        this.node.setBackgroundColor(PatriciaTreeIterColors.DATA_ITEM_COLOR);
        this.node.setHighlightColor(PatriciaTreeIterColors.DATA_ITEM_HIGHLIGHT_COLOR);
    }

    public int getKey()
    {
        return key;
    }

    public Node getNode()
    {
        return node;
    }

    private Node node;
    private int key;
}
