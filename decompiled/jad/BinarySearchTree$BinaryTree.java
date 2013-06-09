// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BinarySearchTree.java

import com.cim.AIA.Node;

private class e
{

    e leftChild;
    e rightChild;
    int data;
    Node node;
    Node leftNode;
    Node rightNode;
    final BinarySearchTree this$0;

    (int data, int index)
    {
        this$0 = BinarySearchTree.this;
        super();
        leftChild = rightChild = null;
        this.data = data;
        node = new Node(new Integer(data), index);
        node.setBackgroundColor(BinarySearchTree.access$000());
        node.setTextColor(BinarySearchTree.access$100());
        node.setRingColor(BinarySearchTree.access$200());
        leftNode = new Node("", 0);
        leftNode.setBackgroundColor(BinarySearchTree.access$300());
        leftNode.setTextColor(BinarySearchTree.access$100());
        leftNode.setWidth(node.getWidth() / 2);
        leftNode.setHeight(node.getHeight() / 2);
        leftNode.setIsVisible(true);
        rightNode = new Node("", 0);
        rightNode.setBackgroundColor(BinarySearchTree.access$300());
        rightNode.setTextColor(BinarySearchTree.access$100());
        rightNode.setWidth(node.getWidth() / 2);
        rightNode.setHeight(node.getHeight() / 2);
        rightNode.setIsVisible(true);
    }
}
