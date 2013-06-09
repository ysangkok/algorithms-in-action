import com.cim.AIA.*;

private class BinaryTree
{
    BinaryTree leftChild;
    BinaryTree rightChild;
    int data;
    Node node;
    Node leftNode;
    Node rightNode;
    
    BinaryTree(final int data, final int index) {
        super();
        final Object o = null;
        this.rightChild = o;
        this.leftChild = o;
        this.data = data;
        this.node = new Node(new Integer(data), index);
        this.node.setBackgroundColor(BinarySearchTree.access$000());
        this.node.setTextColor(BinarySearchTree.access$100());
        this.node.setRingColor(BinarySearchTree.access$200());
        this.leftNode = new Node("", 0);
        this.leftNode.setBackgroundColor(BinarySearchTree.access$300());
        this.leftNode.setTextColor(BinarySearchTree.access$100());
        this.leftNode.setWidth(this.node.getWidth() / 2);
        this.leftNode.setHeight(this.node.getHeight() / 2);
        this.leftNode.setIsVisible(true);
        this.rightNode = new Node("", 0);
        this.rightNode.setBackgroundColor(BinarySearchTree.access$300());
        this.rightNode.setTextColor(BinarySearchTree.access$100());
        this.rightNode.setWidth(this.node.getWidth() / 2);
        this.rightNode.setHeight(this.node.getHeight() / 2);
        this.rightNode.setIsVisible(true);
    }
}
