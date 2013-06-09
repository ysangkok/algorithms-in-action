// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigitalSearchTree.java

import com.cim.AIA.Node;

private class rightNode
{

    rightNode leftChild;
    rightNode rightChild;
    int data;
    Node node;
    Node leftNode;
    Node rightNode;
    final DigitalSearchTree this$0;

    (int data, int index)
    {
        this$0 = DigitalSearchTree.this;
        super();
        leftChild = rightChild = null;
        this.data = data;
        node = new Node(new Integer(data), index);
        node.setBackgroundColor(DigitalSearchTree.access$000());
        node.setTextColor(DigitalSearchTree.access$100());
        node.setMarkersBelowValue(false);
        node.setAdditionalMarkerSpacing(-2);
        byte shrinkFactor = 2;
        node.setRingColor(DigitalSearchTree.access$200());
        leftNode = new Node("", 0);
        leftNode.setBackgroundColor(DigitalSearchTree.access$300());
        leftNode.setTextColor(DigitalSearchTree.access$100());
        leftNode.setWidth(node.getWidth() / shrinkFactor);
        leftNode.setHeight(node.getHeight() / shrinkFactor);
        leftNode.setIsVisible(true);
        rightNode = new Node("", 0);
        rightNode.setBackgroundColor(DigitalSearchTree.access$300());
        rightNode.setTextColor(DigitalSearchTree.access$100());
        rightNode.setWidth(node.getWidth() / shrinkFactor);
        rightNode.setHeight(node.getHeight() / shrinkFactor);
        rightNode.setIsVisible(true);
    }
}
