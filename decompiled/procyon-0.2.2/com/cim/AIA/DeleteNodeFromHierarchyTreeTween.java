package com.cim.AIA;

import java.awt.*;

public class DeleteNodeFromHierarchyTreeTween extends DeleteTween
{
    protected Node node;
    protected HierarchyTree hierarchyTree;
    protected int pos;
    
    public DeleteNodeFromHierarchyTreeTween(final Paintable paintable, final Node node, final int position, final HierarchyTree hierarchyTree, final int steps) {
        super(paintable, node, steps);
        this.pos = 0;
        this.node = node;
        this.pos = this.pos;
        this.hierarchyTree = hierarchyTree;
    }
    
    protected void step() {
        if (this.stepsToGo > 0) {
            if (this.stepsToGo == this.steps) {
                this.setTweening(true);
                this.finalWidth = 0;
                this.currentWidth = (double)this.sizeable.getWidth();
                this.hierarchyTree.add(this.node, this.pos);
                this.node.setBackgroundColor(Color.gray);
                this.sizeable.setWidth((int)this.currentWidth);
            }
            this.sizeable.setWidth((int)(this.currentWidth - this.currentWidth / (double)this.steps));
            this.currentWidth = this.currentWidth - this.currentWidth / (double)this.steps;
            this.stepsToGo = this.stepsToGo - 1;
            if (this.stepsToGo == 0) {
                this.hierarchyTree.remove(this.node);
            }
        }
        else {
            this.setTweening(false);
        }
    }
}
