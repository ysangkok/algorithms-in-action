package com.cim.AIA;

import java.util.*;
import java.io.*;

public class Tree implements Serializable, Cloneable
{
    private static final long serialVersionUID = 4740451588380896171L;
    protected Tree parentTree;
    protected Vector<Tree> children;
    protected Object object;
    protected int seqIndex;
    private int depthFromRoot;
    
    public Tree(final Object object) {
        this(null, object);
    }
    
    public Tree(final Tree parentTree) {
        super();
        this.children = new Vector();
        this.seqIndex = 1;
        this.depthFromRoot = 0;
        this.parentTree = parentTree;
        if (parentTree == null) {
            this.depthFromRoot = 0;
        }
        else {
            this.depthFromRoot = parentTree.depthFromRoot + 1;
        }
    }
    
    public Tree(final Tree parentTree, final Object object) {
        this(parentTree);
        this.object = object;
    }
    
    public void addChild(final Tree newChild) {
        newChild.setParent(this);
        newChild.setDepth(this.depthFromRoot + 1);
        this.children.addElement(newChild);
    }
    
    public void addChild(final Tree newChild, final int seqIndex) {
        newChild.seqIndex = seqIndex;
        this.addChild(newChild);
    }
    
    public void addChildren(final Vector<E> childList) {
        for (int i = 0; i < childList.size(); ++i) {
            this.addChild((Tree)childList.elementAt(i));
        }
    }
    
    private Vector<Tree> compileElementList(final Vector<Tree> currentList, final Tree currentTree) {
        currentList.addElement(currentTree);
        final Vector<Tree> childrenList = currentTree.getChildren();
        if (childrenList == null || childrenList.size() == 0) {
            return null;
        }
        for (int i = 0; i < childrenList.size(); ++i) {
            this.compileElementList(currentList, (Tree)childrenList.elementAt(i));
        }
        return currentList;
    }
    
    public int countLeaves() {
        int nlines = 1;
        for (int i = 0; i < this.children.size(); ++i) {
            nlines = nlines + this.getChild(i).countLeaves();
        }
        return nlines;
    }
    
    public Vector<Tree> elementList() {
        Tree root = this;
        while (root.parentTree != null) {
            root = root.parentTree;
        }
        return this.compileElementList(new Vector(), root);
    }
    
    public Tree getChild(final int index) {
        if (index >= this.children.size() || index < 0) {
            return null;
        }
        return (Tree)this.children.elementAt(index);
    }
    
    public Vector<Tree> getChildren() {
        return this.children;
    }
    
    public int getDepth() {
        return this.depthFromRoot;
    }
    
    public String getDisplayString() {
        return this.object.toString();
    }
    
    public int getNumberOfChildren() {
        return this.children.size();
    }
    
    public Tree getParent() {
        return this.parentTree;
    }
    
    public void insertChildAt(final Tree newChild, final int position) {
        newChild.setParent(this);
        newChild.setDepth(this.depthFromRoot + 1);
        this.children.insertElementAt(newChild, position);
    }
    
    public void removeChild(final Tree child) {
        this.children.removeElement(child);
    }
    
    public void setDepth(final int newDepth) {
        this.depthFromRoot = newDepth;
    }
    
    public void setParent(final Tree parentTree) {
        this.parentTree = parentTree;
    }
}
