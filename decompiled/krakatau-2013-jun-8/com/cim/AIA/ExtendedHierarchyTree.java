package com.cim.AIA;

public class ExtendedHierarchyTree extends com.cim.AIA.HierarchyTree {
    final private static long serialVersionUID = -2606698898546134220L;
    
    public ExtendedHierarchyTree()
    {
        super();
        java.awt.Color a = java.awt.Color.white;
        this.borderColor = a;
    }
    
    public ExtendedHierarchyTree(com.cim.AIA.Node a)
    {
        super(a);
    }
    
    public ExtendedHierarchyTree(com.cim.AIA.Tree a)
    {
        super(a);
    }
    
    public ExtendedHierarchyTree(com.cim.AIA.Tree a, com.cim.AIA.Node a0)
    {
        super(a, a0);
    }
    
    public void setBorderColor(java.awt.Color a)
    {
        this.borderColor = a;
    }
}