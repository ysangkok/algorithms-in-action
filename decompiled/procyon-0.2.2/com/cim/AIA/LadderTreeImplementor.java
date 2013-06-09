package com.cim.AIA;

import java.awt.*;

public class LadderTreeImplementor
{
    protected int rowCount;
    protected boolean isInitialised;
    protected int maxX;
    protected int maxY;
    protected int xBuffer;
    protected int yBuffer;
    
    public LadderTreeImplementor() {
        super();
        this.rowCount = 0;
        this.isInitialised = false;
        this.maxX = 0;
        this.maxY = 0;
    }
    
    public int getMaxX() {
        return this.maxX + this.xBuffer;
    }
    
    public int getMaxY() {
        return this.maxY + this.yBuffer;
    }
    
    public synchronized void layout(final LadderTree tree, final CodeCanvas c) {
        this.layout(tree, c, 0);
    }
    
    public synchronized void layout(final LadderTree tree, final CodeCanvas c, final int depthAdjustment) {
        if (tree.getDepth() - depthAdjustment <= 0) {
            this.isInitialised = false;
        }
        if (!this.isInitialised) {
            this.rowCount = 0;
            this.isInitialised = true;
            this.xBuffer = c.getXBuffer();
            this.yBuffer = c.getYBuffer();
            this.maxX = 0;
            this.maxY = 0;
        }
        if (!tree.getAlgorithmLine().getIsLabelMinMax()) {
            this.rowCount = this.rowCount + 1;
        }
        tree.setXPos((tree.getDepth() - depthAdjustment) * this.xBuffer + this.xBuffer);
        tree.setYPos(this.rowCount * this.yBuffer);
        if (tree.getAlgorithmLine().getIsMinMax()) {
            if (tree.getXPos() + c.getWidthOf(tree.getAlgorithmLine()) + c.getWidthOf(tree.getAlgorithmLine().getMinMaxLabel()) + c.getMinMaxLineLength() * 2 + c.getMinMaxXGap() * 2 > this.maxX) {
                this.maxX = tree.getXPos() + c.getWidthOf(tree.getAlgorithmLine()) + c.getWidthOf(tree.getAlgorithmLine().getMinMaxLabel()) + c.getMinMaxLineLength() * 2 + c.getMinMaxXGap() * 2;
            }
        }
        else if (tree.getXPos() + c.getWidthOf(tree.getAlgorithmLine()) > this.maxX) {
            this.maxX = tree.getXPos() + c.getWidthOf(tree.getAlgorithmLine());
        }
        if (tree.getYPos() + this.yBuffer > this.maxY) {
            this.maxY = tree.getYPos() + this.yBuffer;
        }
        tree.setVisible(true);
        if (tree.isExpanded()) {
            for (int i = 0; i < tree.getNumberOfChildren(); ++i) {
                final LadderTree t = (LadderTree)tree.getChild(i);
                this.layout(t, c, depthAdjustment);
            }
        }
    }
    
    public synchronized void paintTree(final Graphics g, final LadderTree t, final CodeCanvas c) {
        t.display(g, c);
    }
}
