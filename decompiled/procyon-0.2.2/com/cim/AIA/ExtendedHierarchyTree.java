package com.cim.AIA;

import java.awt.*;

public class ExtendedHierarchyTree extends HierarchyTree
{
    private static final long serialVersionUID = -2606698898546134220L;
    
    public ExtendedHierarchyTree() {
        super();
        this.borderColor = Color.white;
    }
    
    public ExtendedHierarchyTree(final Node node) {
        super(node);
    }
    
    public ExtendedHierarchyTree(final Tree tree) {
        super(tree);
    }
    
    public ExtendedHierarchyTree(final Tree tree, final Node node) {
        super(tree, node);
    }
    
    public void setBorderColor(final Color color) {
        this.borderColor = color;
    }
}
