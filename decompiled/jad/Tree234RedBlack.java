// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tree234RedBlack.java


public class Tree234RedBlack
{

    protected Tree234RedBlack(int item, boolean redNode)
    {
        newNode = false;
        deleteNode = false;
        dataItem = item;
        this.redNode = redNode;
    }

    public Tree234RedBlack(int item, Tree234RedBlack leftChild, Tree234RedBlack rightChild, boolean redNode)
    {
        newNode = false;
        deleteNode = false;
        dataItem = item;
        this.redNode = redNode;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        positionInserted = POSITION++;
        newNode = true;
    }

    public int getDataItem()
    {
        return dataItem;
    }

    public int getPositionInserted()
    {
        return positionInserted;
    }

    public Tree234RedBlack getLeftChild()
    {
        return leftChild;
    }

    public Tree234RedBlack getRightChild()
    {
        return rightChild;
    }

    protected static void swapValue(Tree234RedBlack one, Tree234RedBlack two)
    {
        int tempDataItem = one.dataItem;
        one.dataItem = two.dataItem;
        two.dataItem = tempDataItem;
        int tempPositionInserted = one.positionInserted;
        one.positionInserted = two.positionInserted;
        two.positionInserted = tempPositionInserted;
    }

    public void rotR(Tree234Tree algorithm)
    {
        if(leftChild != null)
        {
            swapValue(this, leftChild);
            Tree234RedBlack x = leftChild;
            leftChild = leftChild.leftChild;
            x.leftChild = x.rightChild;
            x.rightChild = rightChild;
            rightChild = x;
        }
    }

    public void rotL(Tree234Tree algorithm)
    {
        if(rightChild != null)
        {
            swapValue(this, rightChild);
            Tree234RedBlack x = rightChild;
            rightChild = rightChild.rightChild;
            x.rightChild = x.leftChild;
            x.leftChild = leftChild;
            leftChild = x;
        }
    }

    public void insert(int item, Tree234Tree algorithm)
    {
        algorithm.setCodePosition("3");
        ptr = parent = grandParent = greatGrandParent = this;
        algorithm.setCodePosition("3.1.1");
        for(; ptr != null; comparePtr = -1)
        {
            if(ptr != null && ptr.rightChild != null && ptr.leftChild != null && ptr.rightChild.redNode && ptr.leftChild.redNode)
            {
                splitPtr = ptr.positionInserted;
                splitPtr2 = ptr.leftChild.positionInserted;
                splitPtr3 = ptr.rightChild.positionInserted;
            }
            algorithm.setCodePosition("3.2");
            if(!algorithm.isEnabled())
                return;
            algorithm.setCodePosition("3.2.1");
            if(ptr.rightChild != null && ptr.leftChild != null && ptr.rightChild.redNode && ptr.leftChild.redNode)
            {
                ptr.deleteNode = true;
                deletePtr = ptr.positionInserted;
                algorithm.update234 = false;
                greatGrandParent.split(grandParent, parent, ptr, algorithm);
                algorithm.setCodePosition("3.2.1.1");
            }
            splitPtr = -1;
            splitPtr2 = -1;
            splitPtr3 = -1;
            greatGrandParent = grandParent;
            grandParent = parent;
            parent = ptr;
            if((ptr.leftChild == null || !ptr.leftChild.redNode) && (ptr.rightChild == null || !ptr.rightChild.redNode))
            {
                algorithm.setCodePosition("3.2.2.1");
                algorithm.setCodePosition("3.2.2.1.1");
                if(item < ptr.dataItem)
                {
                    ptr = ptr.leftChild;
                    algorithm.setCodePosition("3.2.2.1.1.1");
                } else
                {
                    algorithm.setCodePosition("3.2.2.1.2");
                    ptr = ptr.rightChild;
                    algorithm.setCodePosition("3.2.2.1.2.1");
                }
                continue;
            }
            if(ptr.leftChild != null && ptr.leftChild.redNode && (ptr.rightChild == null || !ptr.rightChild.redNode))
            {
                algorithm.setCodePosition("3.2.2.1");
                algorithm.setCodePosition("3.2.2.2");
                if(item < ptr.dataItem && item < ptr.leftChild.dataItem)
                {
                    comparePtr = ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    ptr = ptr.leftChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.leftChild;
                    algorithm.setCodePosition("3.2.2.2.1.1");
                    continue;
                }
                if(item < ptr.dataItem)
                {
                    comparePtr = ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    comparePtr = ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.2");
                    ptr = ptr.leftChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.rightChild;
                    algorithm.setCodePosition("3.2.2.2.2.1");
                } else
                {
                    comparePtr = ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    comparePtr = ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.2");
                    algorithm.setCodePosition("3.2.2.2.3");
                    ptr = ptr.rightChild;
                    algorithm.setCodePosition("3.2.2.2.3.1");
                }
                continue;
            }
            if((ptr.leftChild == null || !ptr.leftChild.redNode) && ptr.rightChild != null && ptr.rightChild.redNode)
            {
                algorithm.setCodePosition("3.2.2.1");
                algorithm.setCodePosition("3.2.2.2");
                if(item < ptr.dataItem)
                {
                    comparePtr = ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    ptr = ptr.leftChild;
                    algorithm.setCodePosition("3.2.2.2.1.1");
                    continue;
                }
                if(item > ptr.dataItem && item < ptr.rightChild.dataItem)
                {
                    comparePtr = ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    comparePtr = ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.2");
                    ptr = ptr.rightChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.leftChild;
                    algorithm.setCodePosition("3.2.2.2.2.1");
                } else
                {
                    comparePtr = ptr.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.1");
                    comparePtr = ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("3.2.2.2.2");
                    algorithm.setCodePosition("3.2.2.2.3");
                    ptr = ptr.rightChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.rightChild;
                    algorithm.setCodePosition("3.2.2.2.3.1");
                }
                continue;
            }
            if(ptr.leftChild == null || !ptr.leftChild.redNode || ptr.rightChild == null || !ptr.rightChild.redNode)
                continue;
            algorithm.setCodePosition("3.2.2.1");
            algorithm.setCodePosition("3.2.2.2");
            algorithm.setCodePosition("3.2.2.3");
            if(item < ptr.dataItem && item < ptr.leftChild.dataItem)
            {
                comparePtr = ptr.leftChild.positionInserted;
                algorithm.setCodePosition("3.2.2.3.1");
                ptr = ptr.leftChild;
                grandParent = parent;
                parent = ptr;
                ptr = ptr.leftChild;
                algorithm.setCodePosition("3.2.2.3.1.1");
                continue;
            }
            if(item < ptr.dataItem && item > ptr.leftChild.dataItem)
            {
                comparePtr = ptr.leftChild.positionInserted;
                algorithm.setCodePosition("3.2.2.3.1");
                comparePtr = ptr.positionInserted;
                algorithm.setCodePosition("3.2.2.3.2");
                ptr = ptr.leftChild;
                grandParent = parent;
                parent = ptr;
                ptr = ptr.rightChild;
                algorithm.setCodePosition("3.2.2.3.2.1");
                continue;
            }
            if(item > ptr.dataItem && item < ptr.rightChild.dataItem)
            {
                comparePtr = ptr.leftChild.positionInserted;
                algorithm.setCodePosition("3.2.2.3.1");
                comparePtr = ptr.positionInserted;
                algorithm.setCodePosition("3.2.2.3.2");
                comparePtr = ptr.rightChild.positionInserted;
                algorithm.setCodePosition("3.2.2.3.3");
                ptr = ptr.rightChild;
                grandParent = parent;
                parent = ptr;
                ptr = ptr.leftChild;
                algorithm.setCodePosition("3.2.2.3.3.1");
            } else
            {
                comparePtr = ptr.leftChild.positionInserted;
                algorithm.setCodePosition("3.2.2.3.1");
                comparePtr = ptr.positionInserted;
                algorithm.setCodePosition("3.2.2.3.2");
                comparePtr = ptr.rightChild.positionInserted;
                algorithm.setCodePosition("3.2.2.3.3");
                algorithm.setCodePosition("3.2.2.3.4");
                ptr = ptr.rightChild;
                grandParent = parent;
                parent = ptr;
                ptr = ptr.rightChild;
                algorithm.setCodePosition("3.2.2.3.4.1");
            }
        }

        algorithm.setCodePosition("3.2");
        algorithm.setCodePosition("3.2.0.1");
        if(!parent.redNode && (parent.leftChild == null || !parent.leftChild.redNode) && (parent.rightChild == null || !parent.rightChild.redNode))
        {
            algorithm.setCodePosition("3.3.1");
            if(item < parent.dataItem)
            {
                algorithm.setCodePosition("3.3.1.1");
                if(!algorithm.isEnabled())
                    return;
                ptr = parent.leftChild = new Tree234RedBlack(item, null, null, true);
                algorithm.setCodePosition("3.3.1.1.1");
            } else
            {
                algorithm.setCodePosition("3.3.1.1");
                algorithm.setCodePosition("3.3.1.2");
                if(!algorithm.isEnabled())
                    return;
                ptr = parent.rightChild = new Tree234RedBlack(item, null, null, true);
                algorithm.setCodePosition("3.3.1.2.1");
            }
        } else
        {
            algorithm.setCodePosition("3.3.1");
            algorithm.setCodePosition("3.3.2");
            if(!parent.redNode)
            {
                if(parent.leftChild == null)
                {
                    algorithm.setCodePosition("3.3.2.1");
                    if(!algorithm.isEnabled())
                        return;
                    ptr = parent.leftChild = new Tree234RedBlack(item, null, null, true);
                    algorithm.setCodePosition("3.3.2.1.1");
                } else
                if(parent.rightChild == null)
                {
                    algorithm.setCodePosition("3.3.2.1");
                    algorithm.setCodePosition("3.3.2.2");
                    algorithm.setCodePosition("3.3.2.3");
                    if(!algorithm.isEnabled())
                        return;
                    ptr = parent.rightChild = new Tree234RedBlack(item, null, null, true);
                    algorithm.setCodePosition("3.3.2.3.1");
                }
            } else
            if(grandParent.leftChild != null && grandParent.leftChild.redNode)
            {
                if(item < parent.dataItem)
                {
                    algorithm.setCodePosition("3.3.2.1");
                    if(!algorithm.isEnabled())
                        return;
                    ptr = parent.leftChild = new Tree234RedBlack(item, null, null, true);
                    algorithm.setCodePosition("3.3.2.1.1");
                } else
                {
                    algorithm.setCodePosition("3.3.2.1");
                    algorithm.setCodePosition("3.3.2.2");
                    if(!algorithm.isEnabled())
                        return;
                    ptr = parent.rightChild = new Tree234RedBlack(item, null, null, true);
                    algorithm.setCodePosition("3.3.2.2.1");
                }
            } else
            if(item < parent.dataItem)
            {
                algorithm.setCodePosition("3.3.2.1");
                algorithm.setCodePosition("3.3.2.2");
                if(!algorithm.isEnabled())
                    return;
                ptr = parent.leftChild = new Tree234RedBlack(item, null, null, true);
                algorithm.setCodePosition("3.3.2.2.1");
            } else
            {
                algorithm.setCodePosition("3.3.2.1");
                algorithm.setCodePosition("3.3.2.2");
                algorithm.setCodePosition("3.3.2.3");
                if(!algorithm.isEnabled())
                    return;
                ptr = parent.rightChild = new Tree234RedBlack(item, null, null, true);
                algorithm.setCodePosition("3.3.2.3.1");
            }
        }
        balance(grandParent, parent, ptr, "3.4", algorithm);
        if(!algorithm.isBackMode)
            algorithm.repaint();
        redNode = false;
        algorithm.setCodePosition("3.5");
    }

    public Tree234RedBlack copy()
    {
        Tree234RedBlack redBlack = new Tree234RedBlack(dataItem, redNode);
        redBlack.newNode = newNode;
        redBlack.positionInserted = positionInserted;
        if(leftChild != null)
            redBlack.leftChild = leftChild.copy();
        if(rightChild != null)
            redBlack.rightChild = rightChild.copy();
        return redBlack;
    }

    public void split(Tree234RedBlack grandParent, Tree234RedBlack parent, Tree234RedBlack ptr, Tree234Tree algorithm)
    {
        ptr.rightChild.redNode = false;
        ptr.leftChild.redNode = false;
        ptr.redNode = true;
        algorithm.update234 = true;
        balance(grandParent, parent, ptr, "3.2.1.4", algorithm);
        if(this.ptr.redNode)
        {
            this.ptr = this.parent;
            this.parent = this.grandParent;
        }
    }

    public void balance(Tree234RedBlack grandParent, Tree234RedBlack parent, Tree234RedBlack ptr, String base, Tree234Tree algorithm)
    {
        if(parent.redNode)
            if(parent == grandParent.leftChild)
            {
                if(ptr == parent.rightChild)
                {
                    parent.rotL(algorithm);
                    Tree234RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    grandParent.rotR(algorithm);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                } else
                {
                    grandParent.rotR(algorithm);
                    Tree234RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.ptr = temp;
                }
            } else
            if(parent == grandParent.rightChild)
                if(ptr == parent.leftChild)
                {
                    parent.rotR(algorithm);
                    Tree234RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    grandParent.rotL(algorithm);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                } else
                {
                    grandParent.rotL(algorithm);
                    Tree234RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.parent = temp;
                }
    }

    public void resetMarkers()
    {
        ptr = parent = grandParent = greatGrandParent = null;
    }

    public boolean search(int item, Tree234Tree algorithm)
    {
        algorithm.setCodePosition("4");
        parent = ptr = this;
        algorithm.setCodePosition("4.1.1");
        while(ptr != null) 
        {
            algorithm.setCodePosition("4.2");
            if(item == ptr.dataItem)
            {
                algorithm.setCodePosition("4.2.1");
                return true;
            }
            if(ptr.leftChild != null && ptr.leftChild.redNode && item == ptr.leftChild.dataItem)
            {
                algorithm.setCodePosition("4.2.1");
                return true;
            }
            if(ptr.rightChild != null && ptr.rightChild.redNode && item == ptr.rightChild.dataItem)
            {
                algorithm.setCodePosition("4.2.1");
                return true;
            }
            algorithm.setCodePosition("4.2.2");
            parent = ptr;
            algorithm.setCodePosition("4.2.2.1");
            if((ptr.leftChild == null || !ptr.leftChild.redNode) && (ptr.rightChild == null || !ptr.rightChild.redNode))
            {
                algorithm.setCodePosition("4.2.2.1.1");
                algorithm.setCodePosition("4.2.2.1.1.1");
                if(item < ptr.dataItem)
                {
                    ptr = ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.1.1.1");
                } else
                {
                    algorithm.setCodePosition("4.2.2.1.1.2");
                    ptr = ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.1.2.1");
                }
            } else
            if(ptr.leftChild != null && ptr.leftChild.redNode && (ptr.rightChild == null || !ptr.rightChild.redNode))
            {
                algorithm.setCodePosition("4.2.2.1.1");
                algorithm.setCodePosition("4.2.2.1.2");
                if(item < ptr.dataItem && item < ptr.leftChild.dataItem)
                {
                    splitPtr = ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    ptr = ptr.leftChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.2.1.1");
                } else
                if(item < ptr.dataItem)
                {
                    splitPtr = ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    splitPtr2 = ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.2");
                    ptr = ptr.leftChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.2.2.1");
                } else
                {
                    splitPtr = ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    splitPtr2 = ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.2");
                    splitPtr = -1;
                    algorithm.setCodePosition("4.2.2.1.2.3");
                    ptr = ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.2.3.1");
                }
            } else
            if((ptr.leftChild == null || !ptr.leftChild.redNode) && ptr.rightChild != null && ptr.rightChild.redNode)
            {
                algorithm.setCodePosition("4.2.2.1.1");
                algorithm.setCodePosition("4.2.2.1.2");
                if(item < ptr.dataItem)
                {
                    splitPtr = ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    ptr = ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.2.1.1");
                } else
                if(item > ptr.dataItem && item < ptr.rightChild.dataItem)
                {
                    splitPtr = ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    splitPtr2 = ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.2");
                    ptr = ptr.rightChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.2.2.1");
                } else
                {
                    splitPtr = ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.1");
                    splitPtr2 = ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.2.2");
                    splitPtr = -1;
                    algorithm.setCodePosition("4.2.2.1.2.3");
                    ptr = ptr.rightChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.2.3.1");
                }
            } else
            if(ptr.leftChild != null && ptr.leftChild.redNode && ptr.rightChild != null && ptr.rightChild.redNode)
            {
                algorithm.setCodePosition("4.2.2.1.1");
                algorithm.setCodePosition("4.2.2.1.2");
                algorithm.setCodePosition("4.2.2.1.3");
                if(item < ptr.dataItem && item < ptr.leftChild.dataItem)
                {
                    splitPtr = ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.1");
                    ptr = ptr.leftChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.3.1.1");
                } else
                if(item < ptr.dataItem && item > ptr.leftChild.dataItem)
                {
                    splitPtr = ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.1");
                    splitPtr2 = ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.2");
                    ptr = ptr.leftChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.3.2.1");
                } else
                if(item > ptr.dataItem && item < ptr.rightChild.dataItem)
                {
                    splitPtr = ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.1");
                    splitPtr2 = ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.2");
                    splitPtr = ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.3");
                    ptr = ptr.rightChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.leftChild;
                    algorithm.setCodePosition("4.2.2.1.3.3.1");
                } else
                {
                    splitPtr = ptr.leftChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.1");
                    splitPtr2 = ptr.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.2");
                    splitPtr = ptr.rightChild.positionInserted;
                    algorithm.setCodePosition("4.2.2.1.3.3");
                    splitPtr2 = -1;
                    algorithm.setCodePosition("4.2.2.1.3.4");
                    ptr = ptr.rightChild;
                    grandParent = parent;
                    parent = ptr;
                    ptr = ptr.rightChild;
                    algorithm.setCodePosition("4.2.2.1.3.4.1");
                }
            }
            splitPtr = -1;
            splitPtr2 = -1;
            splitPtr3 = -1;
        }
        algorithm.setCodePosition("4.2");
        algorithm.setCodePosition("4.2.3");
        algorithm.setCodePosition("4.3");
        return false;
    }

    public static int POSITION = 0;
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
    public static int deletePtr = -1;
    public static int comparePtr = -1;
    public static int splitPtr = -1;
    public static int splitPtr2 = -1;
    public static int splitPtr3 = -1;

}
