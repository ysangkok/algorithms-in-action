// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RedBlack.java


public class RedBlack
{

    protected static void swapValue(RedBlack one, RedBlack two)
    {
        int tempDataItem = one.dataItem;
        one.dataItem = two.dataItem;
        two.dataItem = tempDataItem;
        int tempPositionInserted = one.positionInserted;
        one.positionInserted = two.positionInserted;
        two.positionInserted = tempPositionInserted;
    }

    protected RedBlack(int item, boolean redNode)
    {
        highLightParent = false;
        newNode = false;
        deleteNode = false;
        drawParentLine = true;
        dataItem = item;
        this.redNode = redNode;
    }

    public RedBlack(int item, RedBlack leftChild, RedBlack rightChild, boolean redNode)
    {
        highLightParent = false;
        newNode = false;
        deleteNode = false;
        drawParentLine = true;
        dataItem = item;
        this.redNode = redNode;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        positionInserted = POSITION++;
        newNode = true;
    }

    public void balance(RedBlack grandParent, RedBlack parent, RedBlack ptr)
    {
        if(parent.redNode)
            if(parent == grandParent.leftChild)
            {
                if(ptr == parent.rightChild)
                {
                    parent.rotL(null);
                    RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    grandParent.rotR(null);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                } else
                {
                    grandParent.rotR(null);
                    RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.ptr = temp;
                }
            } else
            if(parent == grandParent.rightChild)
                if(ptr == parent.leftChild)
                {
                    parent.rotR(null);
                    RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    grandParent.rotL(null);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                } else
                {
                    grandParent.rotL(null);
                    RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.parent = temp;
                }
    }

    public void balance(RedBlack grandParent, RedBlack parent, RedBlack ptr, String base, RedBlackTree algorithm)
    {
        if(parent.redNode)
        {
            algorithm.setCodePosition((new StringBuilder()).append(base).append(".1").toString());
            if(parent == grandParent.leftChild)
            {
                if(ptr == parent.rightChild)
                {
                    parent.rotL(algorithm);
                    RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    algorithm.setCodePosition((new StringBuilder()).append(base).append(".2").toString());
                    grandParent.rotR(algorithm);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                    algorithm.setCodePosition((new StringBuilder()).append(base).append(".3").toString());
                } else
                {
                    grandParent.rotR(algorithm);
                    RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.ptr = temp;
                    algorithm.setCodePosition((new StringBuilder()).append(base).append(".3").toString());
                }
            } else
            if(parent == grandParent.rightChild)
                if(ptr == parent.leftChild)
                {
                    parent.rotR(algorithm);
                    RedBlack temp = this.parent;
                    this.parent = this.ptr;
                    this.ptr = temp;
                    algorithm.setCodePosition((new StringBuilder()).append(base).append(".2").toString());
                    grandParent.rotL(algorithm);
                    temp = this.grandParent;
                    this.grandParent = this.ptr;
                    this.ptr = temp;
                    algorithm.setCodePosition((new StringBuilder()).append(base).append(".3").toString());
                } else
                {
                    grandParent.rotL(algorithm);
                    RedBlack temp = this.grandParent;
                    this.grandParent = this.parent;
                    this.parent = temp;
                    algorithm.setCodePosition((new StringBuilder()).append(base).append(".3").toString());
                }
        }
        algorithm.setCodePosition((new StringBuilder()).append(base).append(".4").toString());
    }

    public RedBlack copy()
    {
        RedBlack redBlack = new RedBlack(dataItem, redNode);
        redBlack.newNode = newNode;
        redBlack.positionInserted = positionInserted;
        if(leftChild != null)
            redBlack.leftChild = leftChild.copy();
        if(rightChild != null)
            redBlack.rightChild = rightChild.copy();
        return redBlack;
    }

    public int getDataItem()
    {
        return dataItem;
    }

    public boolean getDrawParentLine()
    {
        return drawParentLine;
    }

    public boolean getHighLightParent()
    {
        return highLightParent;
    }

    public RedBlack getLeftChild()
    {
        return leftChild;
    }

    public int getPositionInserted()
    {
        return positionInserted;
    }

    public RedBlack getRightChild()
    {
        return rightChild;
    }

    public void insert(int item)
    {
        for(ptr = parent = grandParent = greatGrandParent = this; ptr != null;)
        {
            if(ptr.rightChild != null && ptr.leftChild != null && ptr.rightChild.redNode && ptr.leftChild.redNode)
            {
                ptr.deleteNode = true;
                deletePtr = ptr.positionInserted;
                greatGrandParent.split(grandParent, parent, ptr);
            }
            greatGrandParent = grandParent;
            grandParent = parent;
            parent = ptr;
            if(item < ptr.dataItem)
                ptr = ptr.leftChild;
            else
                ptr = ptr.rightChild;
        }

        if(item < parent.dataItem)
            ptr = parent.leftChild = new RedBlack(item, null, null, true);
        else
            ptr = parent.rightChild = new RedBlack(item, null, null, true);
        balance(grandParent, parent, ptr);
        redNode = false;
    }

    public void insert(int item, RedBlackTree algorithm)
    {
        algorithm.setCodePosition("3");
        ptr = parent = grandParent = greatGrandParent = this;
        algorithm.setCodePosition("3.1.1");
        while(ptr != null) 
        {
            algorithm.setCodePosition("3.2");
            if(!algorithm.isEnabled())
                return;
            if(ptr.rightChild != null && ptr.leftChild != null && ptr.rightChild.redNode && ptr.leftChild.redNode)
            {
                ptr.deleteNode = true;
                deletePtr = ptr.positionInserted;
                algorithm.update234 = false;
                greatGrandParent.split(grandParent, parent, ptr, algorithm);
            }
            greatGrandParent = grandParent;
            grandParent = parent;
            algorithm.setCodePosition("3.2.2.1");
            parent = ptr;
            algorithm.setCodePosition("3.2.2.2");
            if(item < ptr.dataItem)
            {
                ptr = ptr.leftChild;
                algorithm.setCodePosition("3.2.2.3");
            } else
            {
                ptr = ptr.rightChild;
                algorithm.setCodePosition("3.2.2.4");
            }
        }
        algorithm.setCodePosition("3.2");
        algorithm.setCodePosition("3.2.0.1");
        algorithm.setCodePosition("3.3.1");
        if(item < parent.dataItem)
        {
            if(!algorithm.isEnabled())
                return;
            ptr = parent.leftChild = new RedBlack(item, null, null, true);
            algorithm.setCodePosition("3.3.2");
        } else
        {
            algorithm.setCodePosition("3.3.3");
            if(!algorithm.isEnabled())
                return;
            ptr = parent.rightChild = new RedBlack(item, null, null, true);
            algorithm.setCodePosition("3.3.4");
        }
        balance(grandParent, parent, ptr, "3.4", algorithm);
        redNode = false;
        algorithm.setCodePosition("3.5");
    }

    public void resetMarkers()
    {
        ptr = parent = grandParent = greatGrandParent = null;
    }

    public void rotL(RedBlackTree algorithm)
    {
        if(rightChild != null)
        {
            swapValue(this, rightChild);
            RedBlack x = rightChild;
            rightChild = rightChild.rightChild;
            x.rightChild = x.leftChild;
            x.leftChild = leftChild;
            leftChild = x;
        }
    }

    public void rotR(RedBlackTree algorithm)
    {
        if(leftChild != null)
        {
            swapValue(this, leftChild);
            RedBlack x = leftChild;
            leftChild = leftChild.leftChild;
            x.leftChild = x.rightChild;
            x.rightChild = rightChild;
            rightChild = x;
        }
    }

    public boolean search(int item, RedBlackTree algorithm)
    {
        algorithm.setCodePosition("4");
        algorithm.setCodePosition("4.1");
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
            algorithm.setCodePosition("4.2.2");
            parent = ptr;
            algorithm.setCodePosition("4.2.2.1");
            if(item < ptr.dataItem)
            {
                algorithm.setCodePosition("4.2.2.2");
                ptr = ptr.leftChild;
                algorithm.setCodePosition("4.2.2.3");
            } else
            {
                ptr = ptr.rightChild;
                algorithm.setCodePosition("4.2.2.4");
            }
        }
        algorithm.setCodePosition("4.2.3");
        algorithm.setCodePosition("4.3");
        return false;
    }

    public void setDrawParentLine(boolean state)
    {
        drawParentLine = state;
    }

    public void setHighLightParent(boolean state)
    {
        highLightParent = state;
    }

    public void split(RedBlack grandParent, RedBlack parent, RedBlack ptr)
    {
        ptr.rightChild.redNode = false;
        ptr.leftChild.redNode = false;
        ptr.redNode = true;
        balance(grandParent, parent, ptr);
    }

    public void split(RedBlack grandParent, RedBlack parent, RedBlack ptr, RedBlackTree algorithm)
    {
        ptr.rightChild.redNode = false;
        ptr.leftChild.redNode = false;
        algorithm.setCodePosition("3.2.1.1");
        ptr.redNode = true;
        algorithm.setCodePosition("3.2.1.3");
        algorithm.update234 = true;
        balance(grandParent, parent, ptr, "3.2.1.4", algorithm);
    }

    public static int POSITION = 0;
    public static int deletePtr = -1;
    public static int ptrP = -1;
    public static int ptrC = -1;
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

}
