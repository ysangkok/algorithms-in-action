package com.cim.AIA;

import java.util.*;
import java.awt.*;

class ElementArrayCanvas extends AlgorithmCanvas implements SelectionListener
{
    private static final long serialVersionUID = -3891669761144149335L;
    protected ElementArray elementArray;
    protected int position;
    protected Node selectedNode;
    protected boolean doingTweens;
    protected TextField label;
    
    public ElementArrayCanvas(final TextField displayLabel) {
        super();
        this.elementArray = new ElementArray(10, 30);
        this.position = 0;
        this.selectedNode = null;
        this.doingTweens = false;
        this.tweens.setSleep(1);
        this.addSelectable(this.elementArray);
        this.addSelectionListener(this);
        this.elementArray.setColumGap(1);
        this.elementArray.setElementWidth(20);
        this.elementArray.setAllHaveSameWidth(false);
        this.label = displayLabel;
    }
    
    public void add(final Object object, final int pos, final boolean tween) {
        final Node node = new Node(object, pos);
        int tempPos = pos + 1;
        if (tempPos > this.elementArray.getSize() || this.elementArray.getSize() == 0) {
            tempPos = this.elementArray.getSize();
        }
        this.elementArray.setValue(tempPos, node);
        if (this.selectedNode == null) {
            if (this.elementArray.getSize() == 1) {
                this.position = 0;
            }
            else {
                this.position = this.position + 1;
            }
        }
        if (tween) {
            this.tweens.add(new InsertTween(this, node, this.numberOfTweenSteps));
        }
    }
    
    public void additem(final Object object, final int pos, final boolean append) {
        if (append == true) {
            this.add(object, pos, true);
        }
        else {
            this.add(object, pos - 1, true);
        }
        if (this.selectedNode != null && append == true) {
            this.increment();
            this.repaint();
        }
        if (this.selectedNode == null && !append) {
            this.decriment();
            this.repaint();
        }
        if (this.selectedNode != null && !append) {
            this.selectedNode.unHighlight();
            this.repaint();
        }
    }
    
    public void decriment() {
        if (this.position - 1 < 0) {
            this.position = this.elementArray.getSize() - 1;
        }
        else {
            this.position = this.position - 1;
        }
        if (this.selectedNode != null) {
            this.selectedNode.unHighlight();
        }
        this.selectedNode = (Node)((Node)this.elementArray.getElement(this.position));
        this.selectedNode.highlight();
    }
    
    public void displayAlgorithm(final Graphics g) {
        if (!this.doingTweens && this.elementArray != null) {
            this.setSize(this.elementArray.getWidth() + 50, this.getSize().height);
        }
        if (this.elementArray != null) {
            this.elementArray.setLocation(this.getSize().width / 2 - this.elementArray.getWidth() / 2, 20);
            this.elementArray.draw(g);
        }
        if (this.position != -1) {
            final Node posNode = (Node)((Node)this.elementArray.getElement(this.position));
            if (posNode != null) {
                final int x = posNode.getPosition().x + posNode.getWidth() / 2;
                final Line line = new Line(x, 0, x, 20);
                line.showArrowHead(true);
                line.setDistanceFromStartForArrowHead(-3);
                line.draw(g);
            }
        }
        if (!this.doingTweens && this.tweens.getNumberOfTweens() > 0) {
            this.doingTweens = true;
            this.tweens.run();
            UserSelectionDataDialog.this.initialiseComponents();
            this.doingTweens = false;
        }
    }
    
    public int getCurrentPosition() {
        return this.position;
    }
    
    public int getNumberOfElements() {
        return this.elementArray.getSize();
    }
    
    public Vector<Object> getObjects() {
        final Vector<Object> temp = new Vector();
        for (int i = 0; i < this.elementArray.getSize(); ++i) {
            temp.addElement(((Node)((Node)this.elementArray.getElement(i))).getObject());
        }
        return temp;
    }
    
    protected void handleColorSelection(final ColorSelection colorSelection) {
    }
    
    protected void handleFontSelection(final FontSelection fontSelection) {
    }
    
    public boolean haveSelected() {
        return this.selectedNode != null;
    }
    
    public void increment() {
        if (this.position + 1 >= this.elementArray.getSize()) {
            this.position = 0;
        }
        else {
            this.position = this.position + 1;
        }
        if (this.selectedNode != null) {
            this.selectedNode.unHighlight();
        }
        this.selectedNode = (Node)((Node)this.elementArray.getElement(this.position));
        this.selectedNode.highlight();
    }
    
    public void modify(final Object object, final int pos) {
        final Node node = (Node)((Node)this.elementArray.getElement(pos));
        if (node != null) {
            node.setObject(object);
            this.increment();
        }
    }
    
    public void paint() {
        this.paint(this.getGraphics());
    }
    
    public void processRepaintEvent(final RepaintEvent e) {
        this.repaint();
    }
    
    public void processSelectionEvent(final SelectionEvent e) {
        if (e.paramObj != null) {
            if (this.selectedNode == null || this.selectedNode != (Node)((Node)e.paramObj)) {
                if (this.selectedNode != null) {
                    this.selectedNode.unHighlight();
                }
                this.selectedNode = (Node)((Node)e.paramObj);
                for (int i = 0; i < this.elementArray.getSize(); ++i) {
                    if (this.selectedNode == (Node)((Node)this.elementArray.getElement(i))) {
                        this.position = i;
                    }
                }
            }
            this.label.requestFocus();
        }
    }
    
    public void remove(final int pos) {
        final Node node = (Node)((Node)this.elementArray.getElement(pos));
        if (node != null) {
            if (node == this.selectedNode) {
                this.selectedNode = null;
            }
            node.setBackgroundColor(Color.gray);
            this.tweens.add(new DeleteFromTween(this, node, this.elementArray, this.numberOfTweenSteps));
            if (this.position >= this.elementArray.getSize() - 1) {
                this.position = this.position - 1;
            }
        }
    }
}
