package com.cim.AIA;

public class Tree implements java.io.Serializable, Cloneable {
    final private static long serialVersionUID = 4740451588380896171L;
    protected com.cim.AIA.Tree parentTree;
    protected java.util.Vector children;
    protected Object object;
    protected int seqIndex;
    private int depthFromRoot;
    
    public Tree(Object a)
    {
        this((com.cim.AIA.Tree)null, a);
    }
    
    public Tree(com.cim.AIA.Tree a)
    {
        super();
        java.util.Vector a0 = new java.util.Vector();
        this.children = a0;
        this.seqIndex = 1;
        this.depthFromRoot = 0;
        this.parentTree = a;
        if(a != null)
        {
            int i = a.depthFromRoot;
            int i0 = i + 1;
            this.depthFromRoot = i0;
        }
        else
        {
            this.depthFromRoot = 0;
        }
    }
    
    public Tree(com.cim.AIA.Tree a, Object a0)
    {
        this(a);
        this.object = a0;
    }
    
    public void addChild(com.cim.AIA.Tree a)
    {
        a.setParent(this);
        int i = this.depthFromRoot;
        int i0 = i + 1;
        a.setDepth(i0);
        java.util.Vector a0 = this.children;
        a0.addElement((Object)a);
    }
    
    public void addChild(com.cim.AIA.Tree a, int i)
    {
        a.seqIndex = i;
        this.addChild(a);
    }
    
    public void addChildren(java.util.Vector a)
    {
        int i = 0;
        while(true)
        {
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                Object a0 = a.elementAt(i);
                com.cim.AIA.Tree dummy = (com.cim.AIA.Tree)a0;
                com.cim.AIA.Tree a1 = (com.cim.AIA.Tree)a0;
                this.addChild(a1);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    private java.util.Vector compileElementList(java.util.Vector a, com.cim.AIA.Tree a0)
    {
        java.util.Vector a1 = null;
        a.addElement((Object)a0);
        java.util.Vector a2 = a0.getChildren();
        label2: {
            label1: {
                label0: {
                    if(a2 == null)
                    {
                        break label0;
                    }
                    int i = a2.size();
                    if(i != 0)
                    {
                        break label1;
                    }
                }
                a1 = null;
                break label2;
            }
            int i0 = 0;
            while(true)
            {
                int i1 = a2.size();
                if(i0 >= i1)
                {
                    break;
                }
                else
                {
                    Object a3 = a2.elementAt(i0);
                    com.cim.AIA.Tree dummy = (com.cim.AIA.Tree)a3;
                    com.cim.AIA.Tree a4 = (com.cim.AIA.Tree)a3;
                    java.util.Vector a5 = this.compileElementList(a, a4);
                    int i2 = i0 + 1;
                    i0 = i2;
                }
            }
            a1 = a;
        }
        return a1;
    }
    
    public int countLeaves()
    {
        int i = 1;
        int i0 = 0;
        while(true)
        {
            java.util.Vector a = this.children;
            int i1 = a.size();
            if(i0 >= i1)
            {
                return i;
            }
            else
            {
                com.cim.AIA.Tree a0 = this.getChild(i0);
                int i2 = a0.countLeaves();
                int i3 = i + i2;
                int i4 = i0 + 1;
                i = i3;
                i0 = i4;
            }
        }
    }
    
    public java.util.Vector elementList()
    {
        com.cim.AIA.Tree a = this;
        while(true)
        {
            com.cim.AIA.Tree a0 = a.parentTree;
            if(a0 != null)
            {
                com.cim.AIA.Tree a1 = a.parentTree;
                a = a1;
            }
            else
            {
                java.util.Vector a2 = new java.util.Vector();
                java.util.Vector a3 = this.compileElementList(a2, a);
                return a3;
            }
        }
    }
    
    public com.cim.AIA.Tree getChild(int i)
    {
        com.cim.AIA.Tree a = null;
        java.util.Vector a0 = this.children;
        int i0 = a0.size();
        label2: {
            label1: {
                label0: {
                    if(i >= i0)
                    {
                        break label0;
                    }
                    if(i >= 0)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            java.util.Vector a1 = this.children;
            Object a2 = a1.elementAt(i);
            com.cim.AIA.Tree dummy = (com.cim.AIA.Tree)a2;
            com.cim.AIA.Tree a3 = (com.cim.AIA.Tree)a2;
            a = a3;
        }
        return a;
    }
    
    public java.util.Vector getChildren()
    {
        java.util.Vector a = this.children;
        return a;
    }
    
    public int getDepth()
    {
        int i = this.depthFromRoot;
        return i;
    }
    
    public String getDisplayString()
    {
        Object a = this.object;
        String s = a.toString();
        return s;
    }
    
    public int getNumberOfChildren()
    {
        java.util.Vector a = this.children;
        int i = a.size();
        return i;
    }
    
    public com.cim.AIA.Tree getParent()
    {
        com.cim.AIA.Tree a = this.parentTree;
        return a;
    }
    
    public void insertChildAt(com.cim.AIA.Tree a, int i)
    {
        a.setParent(this);
        int i0 = this.depthFromRoot;
        int i1 = i0 + 1;
        a.setDepth(i1);
        java.util.Vector a0 = this.children;
        a0.insertElementAt((Object)a, i);
    }
    
    public void removeChild(com.cim.AIA.Tree a)
    {
        java.util.Vector a0 = this.children;
        int i = a0.removeElement((Object)a)?1:0;
    }
    
    public void setDepth(int i)
    {
        this.depthFromRoot = i;
    }
    
    public void setParent(com.cim.AIA.Tree a)
    {
        this.parentTree = a;
    }
}