public class RedBlack
{
    public static int POSITION;
    public static int deletePtr;
    public static int ptrP;
    public static int ptrC;
    protected int dataItem;
    protected RedBlack leftChild;
    protected RedBlack rightChild;
    protected boolean redNode;
    protected int positionInserted;
    protected boolean highLightParent;
    protected RedBlack ptr;
    protected RedBlack parent;
    protected RedBlack grandParent;
    protected RedBlack greatGrandParent;
    protected boolean newNode;
    protected boolean deleteNode;
    protected boolean drawParentLine;
    
    protected static void swapValue(final RedBlack one, final RedBlack two) {
        final int tempDataItem = one.dataItem;
        one.dataItem = two.dataItem;
        two.dataItem = tempDataItem;
        final int tempPositionInserted = one.positionInserted;
        one.positionInserted = two.positionInserted;
        two.positionInserted = tempPositionInserted;
    }
    
    protected RedBlack(final int item, final boolean redNode) {
        super();
        this.highLightParent = false;
        this.newNode = false;
        this.deleteNode = false;
        this.drawParentLine = true;
        this.dataItem = item;
        this.redNode = redNode;
    }
    
    public RedBlack(final int item, final RedBlack leftChild, final RedBlack rightChild, final boolean redNode) {
        super();
        this.highLightParent = false;
        this.newNode = false;
        this.deleteNode = false;
        this.drawParentLine = true;
        this.dataItem = item;
        this.redNode = redNode;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.positionInserted = RedBlack.POSITION++;
        this.newNode = true;
    }
    
    public void balance(final RedBlack grandParent, final RedBlack parent, final RedBlack ptr) {
        if (parent.redNode) {
            if (parent == grandParent.leftChild) {
                if (ptr == parent.rightChild) {
                    parent.rotL(null);
                    RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    grandParent.rotR(null);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                }
                else {
                    grandParent.rotR(null);
                    final RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.ptr = temp;
                }
            }
            else if (parent == grandParent.rightChild) {
                if (ptr == parent.leftChild) {
                    parent.rotR(null);
                    RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    grandParent.rotL(null);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                }
                else {
                    grandParent.rotL(null);
                    final RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.parent = temp;
                }
            }
        }
    }
    
    public void balance(final RedBlack grandParent, final RedBlack parent, final RedBlack ptr, final String base, final RedBlackTree algorithm) {
        if (parent.redNode) {
            algorithm.setCodePosition(base + ".1");
            if (parent == grandParent.leftChild) {
                if (ptr == parent.rightChild) {
                    parent.rotL(algorithm);
                    RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    algorithm.setCodePosition(base + ".2");
                    grandParent.rotR(algorithm);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                    algorithm.setCodePosition(base + ".3");
                }
                else {
                    grandParent.rotR(algorithm);
                    final RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.ptr = temp;
                    algorithm.setCodePosition(base + ".3");
                }
            }
            else if (parent == grandParent.rightChild) {
                if (ptr == parent.leftChild) {
                    parent.rotR(algorithm);
                    RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    algorithm.setCodePosition(base + ".2");
                    grandParent.rotL(algorithm);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                    algorithm.setCodePosition(base + ".3");
                }
                else {
                    grandParent.rotL(algorithm);
                    final RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.parent = temp;
                    algorithm.setCodePosition(base + ".3");
                }
            }
        }
        algorithm.setCodePosition(base + ".4");
    }
    
    public RedBlack copy() {
        final RedBlack redBlack = new RedBlack(this.dataItem, this.redNode);
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
    
    public int getDataItem() {
        return this.dataItem;
    }
    
    public boolean getDrawParentLine() {
        return this.drawParentLine;
    }
    
    public boolean getHighLightParent() {
        return this.highLightParent;
    }
    
    public RedBlack getLeftChild() {
        return this.leftChild;
    }
    
    public int getPositionInserted() {
        return this.positionInserted;
    }
    
    public RedBlack getRightChild() {
        return this.rightChild;
    }
    
    public void insert(final int item) {
        this.greatGrandParent = this;
        this.grandParent = this;
        this.parent = this;
        this.ptr = this;
        while (this.ptr != null) {
            if (this.ptr.rightChild != null && this.ptr.leftChild != null && this.ptr.rightChild.redNode == true && this.ptr.leftChild.redNode == true) {
                this.ptr.deleteNode = true;
                RedBlack.deletePtr = this.ptr.positionInserted;
                this.greatGrandParent.split(this.grandParent, this.parent, this.ptr);
            }
            this.greatGrandParent = this.grandParent;
            this.grandParent = this.parent;
            this.parent = this.ptr;
            if (item < this.ptr.dataItem) {
                this.ptr = this.ptr.leftChild;
            }
            else {
                this.ptr = this.ptr.rightChild;
            }
        }
        if (item < this.parent.dataItem) {
            final RedBlack parent = this.parent;
            final RedBlack redBlack = new RedBlack(item, null, null, true);
            parent.leftChild = redBlack;
            this.ptr = redBlack;
        }
        else {
            final RedBlack parent2 = this.parent;
            final RedBlack redBlack2 = new RedBlack(item, null, null, true);
            parent2.rightChild = redBlack2;
            this.ptr = redBlack2;
        }
        this.balance(this.grandParent, this.parent, this.ptr);
        this.redNode = false;
    }
    
    public void insert(final int item, final RedBlackTree algorithm) {
        algorithm.setCodePosition("3");
        this.greatGrandParent = this;
        this.grandParent = this;
        this.parent = this;
        this.ptr = this;
        algorithm.setCodePosition("3.1.1");
        while (this.ptr != null) {
            algorithm.setCodePosition("3.2");
            if (!algorithm.isEnabled()) {
                return;
            }
            if (this.ptr.rightChild != null && this.ptr.leftChild != null && this.ptr.rightChild.redNode == true && this.ptr.leftChild.redNode == true) {
                this.ptr.deleteNode = true;
                RedBlack.deletePtr = this.ptr.positionInserted;
                algorithm.update234 = false;
                this.greatGrandParent.split(this.grandParent, this.parent, this.ptr, algorithm);
            }
            this.greatGrandParent = this.grandParent;
            this.grandParent = this.parent;
            algorithm.setCodePosition("3.2.2.1");
            this.parent = this.ptr;
            algorithm.setCodePosition("3.2.2.2");
            if (item < this.ptr.dataItem) {
                this.ptr = this.ptr.leftChild;
                algorithm.setCodePosition("3.2.2.3");
            }
            else {
                this.ptr = this.ptr.rightChild;
                algorithm.setCodePosition("3.2.2.4");
            }
        }
        algorithm.setCodePosition("3.2");
        algorithm.setCodePosition("3.2.0.1");
        algorithm.setCodePosition("3.3.1");
        if (item < this.parent.dataItem) {
            if (!algorithm.isEnabled()) {
                return;
            }
            final RedBlack parent = this.parent;
            final RedBlack redBlack = new RedBlack(item, null, null, true);
            parent.leftChild = redBlack;
            this.ptr = redBlack;
            algorithm.setCodePosition("3.3.2");
        }
        else {
            algorithm.setCodePosition("3.3.3");
            if (!algorithm.isEnabled()) {
                return;
            }
            final RedBlack parent2 = this.parent;
            final RedBlack redBlack2 = new RedBlack(item, null, null, true);
            parent2.rightChild = redBlack2;
            this.ptr = redBlack2;
            algorithm.setCodePosition("3.3.4");
        }
        this.balance(this.grandParent, this.parent, this.ptr, "3.4", algorithm);
        this.redNode = false;
        algorithm.setCodePosition("3.5");
    }
    
    public void resetMarkers() {
        final Object o = null;
        this.greatGrandParent = o;
        this.grandParent = o;
        this.parent = o;
        this.ptr = o;
    }
    
    public void rotL(final RedBlackTree algorithm) {
        if (this.rightChild != null) {
            swapValue(this, this.rightChild);
            final RedBlack x = this.rightChild;
            this.rightChild = this.rightChild.rightChild;
            x.rightChild = x.leftChild;
            x.leftChild = this.leftChild;
            this.leftChild = x;
        }
    }
    
    public void rotR(final RedBlackTree algorithm) {
        if (this.leftChild != null) {
            swapValue(this, this.leftChild);
            final RedBlack x = this.leftChild;
            this.leftChild = this.leftChild.leftChild;
            x.leftChild = x.rightChild;
            x.rightChild = this.rightChild;
            this.rightChild = x;
        }
    }
    
    public boolean search(final int item, final RedBlackTree algorithm) {
        algorithm.setCodePosition("4");
        algorithm.setCodePosition("4.1");
        this.ptr = this;
        this.parent = this;
        algorithm.setCodePosition("4.1.1");
        while (this.ptr != null) {
            algorithm.setCodePosition("4.2");
            if (item == this.ptr.dataItem) {
                algorithm.setCodePosition("4.2.1");
                return true;
            }
            algorithm.setCodePosition("4.2.2");
            this.parent = this.ptr;
            algorithm.setCodePosition("4.2.2.1");
            if (item < this.ptr.dataItem) {
                algorithm.setCodePosition("4.2.2.2");
                this.ptr = this.ptr.leftChild;
                algorithm.setCodePosition("4.2.2.3");
            }
            else {
                this.ptr = this.ptr.rightChild;
                algorithm.setCodePosition("4.2.2.4");
            }
        }
        algorithm.setCodePosition("4.2.3");
        algorithm.setCodePosition("4.3");
        return false;
    }
    
    public void setDrawParentLine(final boolean state) {
        this.drawParentLine = state;
    }
    
    public void setHighLightParent(final boolean state) {
        this.highLightParent = state;
    }
    
    public void split(final RedBlack grandParent, final RedBlack parent, final RedBlack ptr) {
        ptr.rightChild.redNode = false;
        ptr.leftChild.redNode = false;
        ptr.redNode = true;
        this.balance(grandParent, parent, ptr);
    }
    
    public void split(final RedBlack grandParent, final RedBlack parent, final RedBlack ptr, final RedBlackTree algorithm) {
        ptr.rightChild.redNode = false;
        ptr.leftChild.redNode = false;
        algorithm.setCodePosition("3.2.1.1");
        ptr.redNode = true;
        algorithm.setCodePosition("3.2.1.3");
        algorithm.update234 = true;
        this.balance(grandParent, parent, ptr, "3.2.1.4", algorithm);
    }
    
    static {
        RedBlack.POSITION = 0;
        RedBlack.deletePtr = -1;
        RedBlack.ptrP = -1;
        RedBlack.ptrC = -1;
    }
}
