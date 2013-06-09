public class Tree234RedBlack
{
    public static int POSITION;
    protected int dataItem;
    protected Tree234RedBlack leftChild;
    protected Tree234RedBlack rightChild;
    protected boolean redNode;
    protected int positionInserted;
    protected Tree234RedBlack ptr;
    protected Tree234RedBlack parent;
    protected Tree234RedBlack grandParent;
    protected Tree234RedBlack greatGrandParent;
    protected boolean newNode;
    protected boolean deleteNode;
    public static int deletePtr;
    public static int comparePtr;
    public static int splitPtr;
    public static int splitPtr2;
    public static int splitPtr3;
    
    protected Tree234RedBlack(final int item, final boolean redNode) {
        super();
        this.newNode = false;
        this.deleteNode = false;
        this.dataItem = item;
        this.redNode = redNode;
    }
    
    public Tree234RedBlack(final int item, final Tree234RedBlack leftChild, final Tree234RedBlack rightChild, final boolean redNode) {
        super();
        this.newNode = false;
        this.deleteNode = false;
        this.dataItem = item;
        this.redNode = redNode;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.positionInserted = Tree234RedBlack.POSITION++;
        this.newNode = true;
    }
    
    public int getDataItem() {
        return this.dataItem;
    }
    
    public int getPositionInserted() {
        return this.positionInserted;
    }
    
    public Tree234RedBlack getLeftChild() {
        return this.leftChild;
    }
    
    public Tree234RedBlack getRightChild() {
        return this.rightChild;
    }
    
    protected static void swapValue(final Tree234RedBlack one, final Tree234RedBlack two) {
        final int tempDataItem = one.dataItem;
        one.dataItem = two.dataItem;
        two.dataItem = tempDataItem;
        final int tempPositionInserted = one.positionInserted;
        one.positionInserted = two.positionInserted;
        two.positionInserted = tempPositionInserted;
    }
    
    public void rotR(final Tree234Tree algorithm) {
        if (this.leftChild != null) {
            swapValue(this, this.leftChild);
            final Tree234RedBlack x = this.leftChild;
            this.leftChild = this.leftChild.leftChild;
            x.leftChild = x.rightChild;
            x.rightChild = this.rightChild;
            this.rightChild = x;
        }
    }
    
    public void rotL(final Tree234Tree algorithm) {
        if (this.rightChild != null) {
            swapValue(this, this.rightChild);
            final Tree234RedBlack x = this.rightChild;
            this.rightChild = this.rightChild.rightChild;
            x.rightChild = x.leftChild;
            x.leftChild = this.leftChild;
            this.leftChild = x;
        }
    }
    
    public void insert(final int item, final Tree234Tree algorithm) {
        algorithm.setCodePosition("3");
        this.greatGrandParent = this;
        this.grandParent = this;
        this.parent = this;
        this.ptr = this;
        algorithm.setCodePosition("3.1.1");
        while (this.ptr != null) {
            if (this.ptr != null && this.ptr.rightChild != null && this.ptr.leftChild != null && this.ptr.rightChild.redNode == true && this.ptr.leftChild.redNode == true) {
                Tree234RedBlack.splitPtr = this.ptr.positionInserted;
                Tree234RedBlack.splitPtr2 = this.ptr.leftChild.positionInserted;
                Tree234RedBlack.splitPtr3 = this.ptr.rightChild.positionInserted;
            }
            algorithm.setCodePosition("3.2");
            if (!algorithm.isEnabled()) {
                return;
            }
            algorithm.setCodePosition("3.2.1");
            if (this.ptr.rightChild != null && this.ptr.leftChild != null && this.ptr.rightChild.redNode == true && this.ptr.leftChild.redNode == true) {
                this.ptr.deleteNode = true;
                Tree234RedBlack.deletePtr = this.ptr.positionInserted;
                algorithm.update234 = false;
                this.greatGrandParent.split(this.grandParent, this.parent, this.ptr, algorithm);
                algorithm.setCodePosition("3.2.1.1");
            }
            Tree234RedBlack.splitPtr = -1;
            Tree234RedBlack.splitPtr2 = -1;
            Tree234RedBlack.splitPtr3 = -1;
            this.greatGrandParent = this.grandParent;
            this.grandParent = this.parent;
            this.parent = this.ptr;
            if ((this.ptr.leftChild == null || !this.ptr.leftChild.redNode) && (this.ptr.rightChild == null || !this.ptr.rightChild.redNode)) {
                algorithm.setCodePosition("3.2.2.1");
                algorithm.setCodePosition("3.2.2.1.1");
                if (item < this.ptr.dataItem) {
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("3.2.2.1.1.1");
                }
                else {
                    algorithm.setCodePosition("3.2.2.1.2");
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("3.2.2.1.2.1");
                }
            }
            else if (this.ptr.leftChild != null && this.ptr.leftChild.redNode && (this.ptr.rightChild == null || !this.ptr.rightChild.redNode)) {
                algorithm.setCodePosition("3.2.2.1");
                algorithm.setCodePosition("3.2.2.2");
                if (item < this.ptr.dataItem && item < this.ptr.leftChild.dataItem) {
                    Tree234RedBlack.comparePtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    this.ptr = this.ptr.leftChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("3.2.2.2.1.1");
                }
                else if (item < this.ptr.dataItem) {
                    Tree234RedBlack.comparePtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    Tree234RedBlack.comparePtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.2");
                    this.ptr = this.ptr.leftChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("3.2.2.2.2.1");
                }
                else {
                    Tree234RedBlack.comparePtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    Tree234RedBlack.comparePtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.2");
                    algorithm.setCodePosition("3.2.2.2.3");
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("3.2.2.2.3.1");
                }
            }
            else if ((this.ptr.leftChild == null || !this.ptr.leftChild.redNode) && this.ptr.rightChild != null && this.ptr.rightChild.redNode) {
                algorithm.setCodePosition("3.2.2.1");
                algorithm.setCodePosition("3.2.2.2");
                if (item < this.ptr.dataItem) {
                    Tree234RedBlack.comparePtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("3.2.2.2.1.1");
                }
                else if (item > this.ptr.dataItem && item < this.ptr.rightChild.dataItem) {
                    Tree234RedBlack.comparePtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    Tree234RedBlack.comparePtr = this.ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.2");
                    this.ptr = this.ptr.rightChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("3.2.2.2.2.1");
                }
                else {
                    Tree234RedBlack.comparePtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    Tree234RedBlack.comparePtr = this.ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.2");
                    algorithm.setCodePosition("3.2.2.2.3");
                    this.ptr = this.ptr.rightChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("3.2.2.2.3.1");
                }
            }
            else if (this.ptr.leftChild != null && this.ptr.leftChild.redNode && this.ptr.rightChild != null && this.ptr.rightChild.redNode) {
                algorithm.setCodePosition("3.2.2.1");
                algorithm.setCodePosition("3.2.2.2");
                algorithm.setCodePosition("3.2.2.3");
                if (item < this.ptr.dataItem && item < this.ptr.leftChild.dataItem) {
                    Tree234RedBlack.comparePtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.3.1");
                    this.ptr = this.ptr.leftChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("3.2.2.3.1.1");
                }
                else if (item < this.ptr.dataItem && item > this.ptr.leftChild.dataItem) {
                    Tree234RedBlack.comparePtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.3.1");
                    Tree234RedBlack.comparePtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.3.2");
                    this.ptr = this.ptr.leftChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("3.2.2.3.2.1");
                }
                else if (item > this.ptr.dataItem && item < this.ptr.rightChild.dataItem) {
                    Tree234RedBlack.comparePtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.3.1");
                    Tree234RedBlack.comparePtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.3.2");
                    Tree234RedBlack.comparePtr = this.ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.3.3");
                    this.ptr = this.ptr.rightChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("3.2.2.3.3.1");
                }
                else {
                    Tree234RedBlack.comparePtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.3.1");
                    Tree234RedBlack.comparePtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.3.2");
                    Tree234RedBlack.comparePtr = this.ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.3.3");
                    algorithm.setCodePosition("3.2.2.3.4");
                    this.ptr = this.ptr.rightChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("3.2.2.3.4.1");
                }
            }
            Tree234RedBlack.comparePtr = -1;
        }
        algorithm.setCodePosition("3.2");
        algorithm.setCodePosition("3.2.0.1");
        if (!this.parent.redNode && (this.parent.leftChild == null || !this.parent.leftChild.redNode) && (this.parent.rightChild == null || !this.parent.rightChild.redNode)) {
            algorithm.setCodePosition("3.3.1");
            if (item < this.parent.dataItem) {
                algorithm.setCodePosition("3.3.1.1");
                if (!algorithm.isEnabled()) {
                    return;
                }
                final Tree234RedBlack parent = this.parent;
                final Tree234RedBlack tree234RedBlack = new Tree234RedBlack(item, null, null, true);
                parent.leftChild = tree234RedBlack;
                this.ptr = tree234RedBlack;
                algorithm.setCodePosition("3.3.1.1.1");
            }
            else {
                algorithm.setCodePosition("3.3.1.1");
                algorithm.setCodePosition("3.3.1.2");
                if (!algorithm.isEnabled()) {
                    return;
                }
                final Tree234RedBlack parent2 = this.parent;
                final Tree234RedBlack tree234RedBlack2 = new Tree234RedBlack(item, null, null, true);
                parent2.rightChild = tree234RedBlack2;
                this.ptr = tree234RedBlack2;
                algorithm.setCodePosition("3.3.1.2.1");
            }
        }
        else {
            algorithm.setCodePosition("3.3.1");
            algorithm.setCodePosition("3.3.2");
            if (!this.parent.redNode) {
                if (this.parent.leftChild == null) {
                    algorithm.setCodePosition("3.3.2.1");
                    if (!algorithm.isEnabled()) {
                        return;
                    }
                    final Tree234RedBlack parent3 = this.parent;
                    final Tree234RedBlack tree234RedBlack3 = new Tree234RedBlack(item, null, null, true);
                    parent3.leftChild = tree234RedBlack3;
                    this.ptr = tree234RedBlack3;
                    algorithm.setCodePosition("3.3.2.1.1");
                }
                else if (this.parent.rightChild == null) {
                    algorithm.setCodePosition("3.3.2.1");
                    algorithm.setCodePosition("3.3.2.2");
                    algorithm.setCodePosition("3.3.2.3");
                    if (!algorithm.isEnabled()) {
                        return;
                    }
                    final Tree234RedBlack parent4 = this.parent;
                    final Tree234RedBlack tree234RedBlack4 = new Tree234RedBlack(item, null, null, true);
                    parent4.rightChild = tree234RedBlack4;
                    this.ptr = tree234RedBlack4;
                    algorithm.setCodePosition("3.3.2.3.1");
                }
            }
            else if (this.grandParent.leftChild != null && this.grandParent.leftChild.redNode) {
                if (item < this.parent.dataItem) {
                    algorithm.setCodePosition("3.3.2.1");
                    if (!algorithm.isEnabled()) {
                        return;
                    }
                    final Tree234RedBlack parent5 = this.parent;
                    final Tree234RedBlack tree234RedBlack5 = new Tree234RedBlack(item, null, null, true);
                    parent5.leftChild = tree234RedBlack5;
                    this.ptr = tree234RedBlack5;
                    algorithm.setCodePosition("3.3.2.1.1");
                }
                else {
                    algorithm.setCodePosition("3.3.2.1");
                    algorithm.setCodePosition("3.3.2.2");
                    if (!algorithm.isEnabled()) {
                        return;
                    }
                    final Tree234RedBlack parent6 = this.parent;
                    final Tree234RedBlack tree234RedBlack6 = new Tree234RedBlack(item, null, null, true);
                    parent6.rightChild = tree234RedBlack6;
                    this.ptr = tree234RedBlack6;
                    algorithm.setCodePosition("3.3.2.2.1");
                }
            }
            else if (item < this.parent.dataItem) {
                algorithm.setCodePosition("3.3.2.1");
                algorithm.setCodePosition("3.3.2.2");
                if (!algorithm.isEnabled()) {
                    return;
                }
                final Tree234RedBlack parent7 = this.parent;
                final Tree234RedBlack tree234RedBlack7 = new Tree234RedBlack(item, null, null, true);
                parent7.leftChild = tree234RedBlack7;
                this.ptr = tree234RedBlack7;
                algorithm.setCodePosition("3.3.2.2.1");
            }
            else {
                algorithm.setCodePosition("3.3.2.1");
                algorithm.setCodePosition("3.3.2.2");
                algorithm.setCodePosition("3.3.2.3");
                if (!algorithm.isEnabled()) {
                    return;
                }
                final Tree234RedBlack parent8 = this.parent;
                final Tree234RedBlack tree234RedBlack8 = new Tree234RedBlack(item, null, null, true);
                parent8.rightChild = tree234RedBlack8;
                this.ptr = tree234RedBlack8;
                algorithm.setCodePosition("3.3.2.3.1");
            }
        }
        this.balance(this.grandParent, this.parent, this.ptr, "3.4", algorithm);
        if (!algorithm.isBackMode) {
            algorithm.repaint();
        }
        this.redNode = false;
        algorithm.setCodePosition("3.5");
    }
    
    public Tree234RedBlack copy() {
        final Tree234RedBlack redBlack = new Tree234RedBlack(this.dataItem, this.redNode);
        redBlack.newNode = this.newNode;
        redBlack.positionInserted = this.positionInserted;
        if (this.leftChild != null) {
            redBlack.leftChild = this.leftChild.copy();
        }
        if (this.rightChild != null) {
            redBlack.rightChild = this.rightChild.copy();
        }
        return redBlack;
    }
    
    public void split(final Tree234RedBlack grandParent, final Tree234RedBlack parent, final Tree234RedBlack ptr, final Tree234Tree algorithm) {
        ptr.rightChild.redNode = false;
        ptr.leftChild.redNode = false;
        ptr.redNode = true;
        algorithm.update234 = true;
        this.balance(grandParent, parent, ptr, "3.2.1.4", algorithm);
        if (this.ptr.redNode) {
            this.ptr = this.parent;
            this.parent = this.grandParent;
        }
    }
    
    public void balance(final Tree234RedBlack grandParent, final Tree234RedBlack parent, final Tree234RedBlack ptr, final String base, final Tree234Tree algorithm) {
        if (parent.redNode) {
            if (parent == grandParent.leftChild) {
                if (ptr == parent.rightChild) {
                    parent.rotL(algorithm);
                    Tree234RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    grandParent.rotR(algorithm);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                }
                else {
                    grandParent.rotR(algorithm);
                    final Tree234RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.ptr = temp;
                }
            }
            else if (parent == grandParent.rightChild) {
                if (ptr == parent.leftChild) {
                    parent.rotR(algorithm);
                    Tree234RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    grandParent.rotL(algorithm);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                }
                else {
                    grandParent.rotL(algorithm);
                    final Tree234RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.parent = temp;
                }
            }
        }
    }
    
    public void resetMarkers() {
        final Object o = null;
        this.greatGrandParent = o;
        this.grandParent = o;
        this.parent = o;
        this.ptr = o;
    }
    
    public boolean search(final int item, final Tree234Tree algorithm) {
        algorithm.setCodePosition("4");
        this.ptr = this;
        this.parent = this;
        algorithm.setCodePosition("4.1.1");
        while (this.ptr != null) {
            algorithm.setCodePosition("4.2");
            if (item == this.ptr.dataItem) {
                algorithm.setCodePosition("4.2.1");
                return true;
            }
            if (this.ptr.leftChild != null && this.ptr.leftChild.redNode && item == this.ptr.leftChild.dataItem) {
                algorithm.setCodePosition("4.2.1");
                return true;
            }
            if (this.ptr.rightChild != null && this.ptr.rightChild.redNode && item == this.ptr.rightChild.dataItem) {
                algorithm.setCodePosition("4.2.1");
                return true;
            }
            algorithm.setCodePosition("4.2.2");
            this.parent = this.ptr;
            algorithm.setCodePosition("4.2.2.1");
            if ((this.ptr.leftChild == null || !this.ptr.leftChild.redNode) && (this.ptr.rightChild == null || !this.ptr.rightChild.redNode)) {
                algorithm.setCodePosition("4.2.2.1.1");
                algorithm.setCodePosition("4.2.2.1.1.1");
                if (item < this.ptr.dataItem) {
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.1.1.1");
                }
                else {
                    algorithm.setCodePosition("4.2.2.1.1.2");
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.1.2.1");
                }
            }
            else if (this.ptr.leftChild != null && this.ptr.leftChild.redNode && (this.ptr.rightChild == null || !this.ptr.rightChild.redNode)) {
                algorithm.setCodePosition("4.2.2.1.1");
                algorithm.setCodePosition("4.2.2.1.2");
                if (item < this.ptr.dataItem && item < this.ptr.leftChild.dataItem) {
                    Tree234RedBlack.splitPtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    this.ptr = this.ptr.leftChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.2.1.1");
                }
                else if (item < this.ptr.dataItem) {
                    Tree234RedBlack.splitPtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    Tree234RedBlack.splitPtr2 = this.ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.2");
                    this.ptr = this.ptr.leftChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.2.2.1");
                }
                else {
                    Tree234RedBlack.splitPtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    Tree234RedBlack.splitPtr2 = this.ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.2");
                    Tree234RedBlack.splitPtr = -1;
                    algorithm.setCodePosition("4.2.2.1.2.3");
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.2.3.1");
                }
            }
            else if ((this.ptr.leftChild == null || !this.ptr.leftChild.redNode) && this.ptr.rightChild != null && this.ptr.rightChild.redNode) {
                algorithm.setCodePosition("4.2.2.1.1");
                algorithm.setCodePosition("4.2.2.1.2");
                if (item < this.ptr.dataItem) {
                    Tree234RedBlack.splitPtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.2.1.1");
                }
                else if (item > this.ptr.dataItem && item < this.ptr.rightChild.dataItem) {
                    Tree234RedBlack.splitPtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    Tree234RedBlack.splitPtr2 = this.ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.2");
                    this.ptr = this.ptr.rightChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.2.2.1");
                }
                else {
                    Tree234RedBlack.splitPtr = this.ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    Tree234RedBlack.splitPtr2 = this.ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.2");
                    Tree234RedBlack.splitPtr = -1;
                    algorithm.setCodePosition("4.2.2.1.2.3");
                    this.ptr = this.ptr.rightChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.2.3.1");
                }
            }
            else if (this.ptr.leftChild != null && this.ptr.leftChild.redNode && this.ptr.rightChild != null && this.ptr.rightChild.redNode) {
                algorithm.setCodePosition("4.2.2.1.1");
                algorithm.setCodePosition("4.2.2.1.2");
                algorithm.setCodePosition("4.2.2.1.3");
                if (item < this.ptr.dataItem && item < this.ptr.leftChild.dataItem) {
                    Tree234RedBlack.splitPtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.1");
                    this.ptr = this.ptr.leftChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.3.1.1");
                }
                else if (item < this.ptr.dataItem && item > this.ptr.leftChild.dataItem) {
                    Tree234RedBlack.splitPtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.1");
                    Tree234RedBlack.splitPtr2 = this.ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.2");
                    this.ptr = this.ptr.leftChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.3.2.1");
                }
                else if (item > this.ptr.dataItem && item < this.ptr.rightChild.dataItem) {
                    Tree234RedBlack.splitPtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.1");
                    Tree234RedBlack.splitPtr2 = this.ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.2");
                    Tree234RedBlack.splitPtr = this.ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.3");
                    this.ptr = this.ptr.rightChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.3.3.1");
                }
                else {
                    Tree234RedBlack.splitPtr = this.ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.1");
                    Tree234RedBlack.splitPtr2 = this.ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.2");
                    Tree234RedBlack.splitPtr = this.ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.3");
                    Tree234RedBlack.splitPtr2 = -1;
                    algorithm.setCodePosition("4.2.2.1.3.4");
                    this.ptr = this.ptr.rightChild;
                    this.grandParent = this.parent;
                    this.parent = this.ptr;
                    this.ptr = this.ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.3.4.1");
                }
            }
            Tree234RedBlack.splitPtr = -1;
            Tree234RedBlack.splitPtr2 = -1;
            Tree234RedBlack.splitPtr3 = -1;
        }
        algorithm.setCodePosition("4.2");
        algorithm.setCodePosition("4.2.3");
        algorithm.setCodePosition("4.3");
        return false;
    }
    
    static {
        Tree234RedBlack.POSITION = 0;
        Tree234RedBlack.deletePtr = -1;
        Tree234RedBlack.comparePtr = -1;
        Tree234RedBlack.splitPtr = -1;
        Tree234RedBlack.splitPtr2 = -1;
        Tree234RedBlack.splitPtr3 = -1;
    }
}
