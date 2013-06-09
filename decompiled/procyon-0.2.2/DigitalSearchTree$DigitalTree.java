import com.cim.AIA.*;

private class DigitalTree
{
    DigitalTree leftChild;
    DigitalTree rightChild;
    int data;
    Node node;
    Node leftNode;
    Node rightNode;
    
    DigitalTree(final int data, final int index) {
        super();
        final Object o = null;
        this.rightChild = o;
        this.leftChild = o;
        this.data = data;
        this.node = new Node(new Integer(data), index);
        this.node.setBackgroundColor(DigitalSearchTree.access$000());
        this.node.setTextColor(DigitalSearchTree.access$100());
        this.node.setMarkersBelowValue(false);
        this.node.setAdditionalMarkerSpacing(-2);
        final byte shrinkFactor = 2;
        this.node.setRingColor(DigitalSearchTree.access$200());
        this.leftNode = new Node("", 0);
        this.leftNode.setBackgroundColor(DigitalSearchTree.access$300());
        this.leftNode.setTextColor(DigitalSearchTree.access$100());
        this.leftNode.setWidth(this.node.getWidth() / shrinkFactor);
        this.leftNode.setHeight(this.node.getHeight() / shrinkFactor);
        this.leftNode.setIsVisible(true);
        this.rightNode = new Node("", 0);
        this.rightNode.setBackgroundColor(DigitalSearchTree.access$300());
        this.rightNode.setTextColor(DigitalSearchTree.access$100());
        this.rightNode.setWidth(this.node.getWidth() / shrinkFactor);
        this.rightNode.setHeight(this.node.getHeight() / shrinkFactor);
        this.rightNode.setIsVisible(true);
    }
}
