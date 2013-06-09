package com.cim.AIA;

public class DeleteNodeFromHierarchyTreeTween extends com.cim.AIA.DeleteTween {
    protected com.cim.AIA.Node node;
    protected com.cim.AIA.HierarchyTree hierarchyTree;
    protected int pos;
    
    public DeleteNodeFromHierarchyTreeTween(com.cim.AIA.Paintable a, com.cim.AIA.Node a0, int i, com.cim.AIA.HierarchyTree a1, int i0)
    {
        super(a, (com.cim.AIA.Sizeable)a0, i0);
        this.pos = 0;
        this.node = a0;
        int i1 = this.pos;
        this.pos = i1;
        this.hierarchyTree = a1;
    }
    
    protected void step()
    {
        int i = this.stepsToGo;
        label1: {
            label0: {
                if(i > 0)
                {
                    break label0;
                }
                this.setTweening(false);
                break label1;
            }
            int i0 = this.stepsToGo;
            int i1 = this.steps;
            if(i0 == i1)
            {
                this.setTweening(true);
                this.finalWidth = 0;
                Object a = this.sizeable;
                int i2 = ((com.cim.AIA.Sizeable)a).getWidth();
                double d = (double)i2;
                this.currentWidth = d;
                com.cim.AIA.HierarchyTree a0 = this.hierarchyTree;
                com.cim.AIA.Node a1 = this.node;
                int i3 = this.pos;
                a0.add(a1, i3);
                com.cim.AIA.Node a2 = this.node;
                java.awt.Color a3 = java.awt.Color.gray;
                a2.setBackgroundColor(a3);
                Object a4 = this.sizeable;
                double d0 = this.currentWidth;
                int i4 = (int)d0;
                ((com.cim.AIA.Sizeable)a4).setWidth(i4);
            }
            Object a5 = this.sizeable;
            double d1 = this.currentWidth;
            double d2 = this.currentWidth;
            int i5 = this.steps;
            double d3 = (double)i5;
            double d4 = d2 / d3;
            double d5 = d1 - d4;
            int i6 = (int)d5;
            ((com.cim.AIA.Sizeable)a5).setWidth(i6);
            double d6 = this.currentWidth;
            double d7 = this.currentWidth;
            int i7 = this.steps;
            double d8 = (double)i7;
            double d9 = d7 / d8;
            double d10 = d6 - d9;
            this.currentWidth = d10;
            int i8 = this.stepsToGo;
            int i9 = i8 - 1;
            this.stepsToGo = i9;
            int i10 = this.stepsToGo;
            if(i10 == 0)
            {
                com.cim.AIA.HierarchyTree a6 = this.hierarchyTree;
                com.cim.AIA.Node a7 = this.node;
                a6.remove(a7);
            }
        }
    }
}