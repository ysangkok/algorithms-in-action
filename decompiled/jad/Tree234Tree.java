// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tree234Tree.java

import com.cim.AIA.*;
import java.awt.Color;
import java.util.Vector;
import localization.Messages;

public class Tree234Tree extends Algorithm
    implements MethodSelectionListener, ControlListener
{

    public Tree234Tree(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        T234_NODE_COLOR = Color.lightGray;
        T234_COMPARE_COLOR = Color.red;
        T234_SPLIT_COLOR = Color.green;
        RB_NODE_COLOR = Color.lightGray;
        PATH_COLOR = Color.green;
        isBackMode = false;
        position = -1;
        executingMode = 11;
        nextMode = executingMode;
        canStoreState = false;
        insertRequests = new Vector();
        deleteRequests = new Vector();
        deleter = new Vector();
        spliter = new Vector();
        comparer = new Vector();
        animationNode = null;
        growNode = null;
        update234 = true;
        this.data = (int[])(int[])data;
        baseTree = null;
        basePosition = 0;
        initialise();
    }

    protected void initialiseMethods(String dataDir, MethodSelectable methodSelectable)
    {
        MethodSelection build = new MethodSelection(BUILD_MODE_LABEL, dataDir, Messages.getString("Tree234Tree.2"), "2.2a", getLogger(), getBreakPoint());
        MethodSelection search = new MethodSelection(SEARCH_MODE_LABEL, dataDir, Messages.getString("Tree234Tree.4"), "4", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(build, true);
        methodSelectable.addMethodSelection(search, false);
        methodSelectable.addMethodSelectionListener(this);
    }

    public void processMethodSelectionEvent(MethodSelectionEvent e)
    {
        if(BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0)
            nextMode = 11;
        else
        if(SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0)
            nextMode = 12;
    }

    public HierarchyTree getPtrNode()
    {
        return ptrNode;
    }

    public HierarchyTree getParentNode()
    {
        return parentNode;
    }

    public HierarchyTree getGrandParentNode()
    {
        return grandParentNode;
    }

    public HierarchyTree getGreatGrandParentNode()
    {
        return greatGrandParentNode;
    }

    public Node getAnimationNode()
    {
        return animationNode;
    }

    public Node getGrowNode()
    {
        return growNode;
    }

    public HierarchyTree getHierarchyTree(int nodeWidth)
    {
        HierarchyTree h1 = getHierarchyTree(null, tree, tree, 0, nodeWidth, true, false);
        return h1;
    }

    protected HierarchyTree getHierarchyTree(HierarchyTree parent, Tree234RedBlack tree, Tree234RedBlack root, int level, int nodeWidth, boolean adjustMarkers, boolean animate)
    {
        if(tree == null)
            return null;
        Node node;
        if(!animate)
            node = new RoundNode(new Integer(tree.getDataItem()), tree.getPositionInserted());
        else
            node = new Node(new Integer(tree.getDataItem()), tree.getPositionInserted());
        node.setWidth(nodeWidth);
        if(tree.newNode & animate)
            insertRequests.addElement(node);
        if((tree.getPositionInserted() == Tree234RedBlack.deletePtr) & animate)
            deleter.addElement(node);
        if((tree.getPositionInserted() == Tree234RedBlack.comparePtr) & animate)
            comparer.addElement(node);
        if((tree.getPositionInserted() == Tree234RedBlack.splitPtr) & animate)
            spliter.addElement(node);
        if((tree.getPositionInserted() == Tree234RedBlack.splitPtr2) & animate)
            spliter.addElement(node);
        if((tree.getPositionInserted() == Tree234RedBlack.splitPtr3) & animate)
            spliter.addElement(node);
        if(tree.redNode)
            node.setHighlightColor(Color.red);
        else
            node.setHighlightColor(Color.gray);
        node.setBackgroundColor(RB_NODE_COLOR);
        HierarchyTree hierarchyTree = new HierarchyTree(parent, node);
        if(!animate)
            hierarchyTree.setDrawBorder(false);
        if(root.ptr != null)
        {
            if(root.ptr == tree)
                pathNode = hierarchyTree;
        } else
        if(root.parent == tree)
            pathNode = hierarchyTree;
        if(adjustMarkers)
        {
            if(root.ptr != null && root.ptr == tree)
                ptrNode = hierarchyTree;
            if(root.parent != null && root.parent == tree)
                parentNode = hierarchyTree;
            if(root.grandParent != null && root.grandParent == tree)
                grandParentNode = hierarchyTree;
            if(root.greatGrandParent != null && root.greatGrandParent == tree)
                greatGrandParentNode = hierarchyTree;
        }
        if(tree.leftChild == null && tree.rightChild != null)
        {
            Node fakeLeftNode = new Node(new Integer(0), -1);
            fakeLeftNode.setWidth(nodeWidth);
            fakeLeftNode.setIsVisible(false);
            HierarchyTree fakeLeft = new HierarchyTree(hierarchyTree, fakeLeftNode);
            hierarchyTree.addChild(fakeLeft);
        } else
        if(tree.leftChild != null)
            hierarchyTree.addChild(getHierarchyTree(hierarchyTree, tree.leftChild, root, level + 1, nodeWidth, adjustMarkers, animate));
        if(tree.rightChild == null && tree.leftChild != null)
        {
            Node fakeRightNode = new Node(new Integer(0), -1);
            fakeRightNode.setWidth(nodeWidth);
            fakeRightNode.setIsVisible(false);
            HierarchyTree fakeRight = new HierarchyTree(hierarchyTree, fakeRightNode);
            hierarchyTree.addChild(fakeRight);
        } else
        if(tree.rightChild != null)
            hierarchyTree.addChild(getHierarchyTree(hierarchyTree, tree.rightChild, root, level + 1, nodeWidth, adjustMarkers, animate));
        if(parent != null)
            if(tree.redNode)
            {
                hierarchyTree.setParentLineColor(Color.red);
                Line pLine = hierarchyTree.getLine();
                pLine.showAsThick(true);
                pLine.setLineThickness(6);
            } else
            {
                hierarchyTree.setParentLineColor(Color.black);
            }
        return hierarchyTree;
    }

    public HierarchyTree getBalancedHierarchyTree(int nodeWidth)
    {
        HierarchyTree hierarchyTree = getHierarchyTree(null, tree, tree, 0, nodeWidth, false, true);
        colorHierarchyTree(pathNode);
        colorTree(hierarchyTree, T234_NODE_COLOR);
        balanceHierarchyTree(hierarchyTree);
        return hierarchyTree;
    }

    private void colorHierarchyTree(HierarchyTree subTree)
    {
        if(subTree != null)
        {
            subTree.getLine().showAsThick(true);
            subTree.setParentLineColor(PATH_COLOR);
            colorHierarchyTree((HierarchyTree)subTree.getParent());
        }
    }

    public void colorTree(HierarchyTree h, Color c)
    {
        if(h == null)
            return;
        Vector nodes = h.getNodes();
        for(int v = 0; v < nodes.size(); v++)
        {
            Node node = (Node)nodes.elementAt(v);
            node.setBackgroundColor(c);
        }

        for(int i = h.getNumberOfChildren() - 1; i >= 0; i--)
        {
            HierarchyTree child = (HierarchyTree)h.getChild(i);
            colorTree(child, c);
        }

    }

    protected void balanceHierarchyTree(HierarchyTree hierarchyTree)
    {
        if(hierarchyTree == null)
            return;
        Node bNode = hierarchyTree.getNodeAt(1);
        for(int v = 0; v < comparer.size(); v++)
        {
            Node cNode = (Node)comparer.elementAt(v);
            if(cNode == bNode)
            {
                comparer.removeElementAt(v);
                cNode.setTextColor(T234_COMPARE_COLOR);
                cNode.setRingColor(T234_COMPARE_COLOR);
            }
        }

        for(int v = 0; v < spliter.size(); v++)
        {
            Node cNode = (Node)spliter.elementAt(v);
            if(cNode == bNode)
            {
                spliter.removeElementAt(v);
                cNode.setBackgroundColor(T234_SPLIT_COLOR);
            }
        }

        for(int v = 0; v < deleter.size(); v++)
        {
            Node cNode = (Node)deleter.elementAt(v);
            if(cNode == bNode && (hierarchyTree.getParent() != null || hierarchyTree.numberOfElements() > 1))
            {
                insertRequests.addElement(bNode);
                deleter.removeElementAt(v);
                animationNode = new Node(cNode.getObject(), cNode.getIdentifier());
                animationNode.setBackgroundColor(T234_SPLIT_COLOR);
                continue;
            }
            if(cNode == bNode)
            {
                deleter.removeElementAt(v);
                insertRequests.addElement(bNode);
                animationNode = new Node(cNode.getObject(), cNode.getIdentifier());
                animationNode.setBackgroundColor(T234_SPLIT_COLOR);
            }
        }

        for(int i = hierarchyTree.getNumberOfChildren() - 1; i >= 0; i--)
        {
            HierarchyTree child = (HierarchyTree)hierarchyTree.getChild(i);
            balanceHierarchyTree(child);
        }

        Vector children = hierarchyTree.getChildren();
        for(int i = children.size() - 1; i >= 0; i--)
        {
            HierarchyTree child = (HierarchyTree)children.elementAt(i);
            boolean shouldBalance = true;
            Vector nodes = child.getNodes();
            int v = 0;
            do
            {
                if(v >= nodes.size())
                    break;
                Node node = (Node)nodes.elementAt(v);
                if(node.getHighlightColor() != Color.red)
                {
                    shouldBalance = false;
                    break;
                }
                v++;
            } while(true);
            if(!shouldBalance)
                continue;
            if(i == 0)
            {
                for(int j = nodes.size() - 1; j >= 0; j--)
                {
                    Node aNode = (Node)nodes.elementAt(j);
                    if(aNode.getIsVisible())
                        hierarchyTree.add(aNode, 0);
                    Vector childTrees = child.getChildren();
                    for(int a = childTrees.size() - 1; a >= 0; a--)
                    {
                        HierarchyTree childTree = (HierarchyTree)childTrees.elementAt(a);
                        hierarchyTree.insertChildAt(childTree, 0);
                    }

                }

            } else
            {
                for(int j = 0; j < nodes.size(); j++)
                {
                    Node aNode = (Node)nodes.elementAt(j);
                    if(aNode.getIsVisible())
                        hierarchyTree.add(aNode);
                    Vector childTrees = child.getChildren();
                    for(int a = 0; a < childTrees.size(); a++)
                    {
                        HierarchyTree childTree = (HierarchyTree)childTrees.elementAt(a);
                        hierarchyTree.addChild(childTree);
                    }

                }

            }
            hierarchyTree.removeChild(child);
        }

    }

    protected void initialise()
    {
        if(executingMode == 11 || nextMode == 11)
            if(baseTree != null)
                tree = baseTree.copy();
            else
                tree = null;
        insertRequests = new Vector();
        deleteRequests = new Vector();
        deleter = new Vector();
        spliter = new Vector();
        comparer = new Vector();
        parentNode = grandParentNode = greatGrandParentNode = ptrNode = null;
        position = -1;
        Tree234RedBlack.POSITION = basePosition;
    }

    public void reInitialise(Object data)
    {
        this.data = (int[])(int[])data;
        initialise();
    }

    protected void changeData(Object data)
    {
        this.data = (int[])(int[])data;
        position = -1;
        save();
    }

    protected void storeState(boolean forceStore)
    {
        if(canStoreState || forceStore)
            save();
    }

    private void save()
    {
        if(tree != null)
            baseTree = tree.copy();
        else
            baseTree = null;
        basePosition = Tree234RedBlack.POSITION;
    }

    protected void clearState()
    {
        baseTree = null;
        basePosition = 0;
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        MultipleTween tweens = new MultipleTween(paintable);
        for(int i = 0; i < insertRequests.size(); i++)
            tweens.add(new InsertTween(null, (Node)(Node)insertRequests.elementAt(i), numberOfSteps));

        for(int i = 0; i < deleteRequests.size(); i++)
            tweens.add(new DeleteTween(null, (Node)(Node)deleteRequests.elementAt(i), numberOfSteps));

        if(insertRequests.size() > 0 || deleteRequests.size() > 0)
            return tweens;
        else
            return null;
    }

    protected Vector generateQuestions()
    {
        return null;
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    protected void removeAllQuestions()
    {
    }

    protected void removeAllAnimationRequests()
    {
        insertRequests = new Vector();
        deleteRequests = new Vector();
        removeTree234TreeDeletes(tree);
        removeTree234TreeInserts(tree);
        growNode = null;
        animationNode = null;
    }

    protected void removeTree234TreeInserts(Tree234RedBlack tree)
    {
        if(tree != null)
        {
            tree.newNode = false;
            if(tree.getLeftChild() != null)
                removeTree234TreeInserts(tree.getLeftChild());
            if(tree.getRightChild() != null)
                removeTree234TreeInserts(tree.getRightChild());
        }
    }

    protected void removeTree234TreeDeletes(Tree234RedBlack tree)
    {
        if(tree != null)
        {
            tree.deleteNode = false;
            Tree234RedBlack _tmp = tree;
            Tree234RedBlack.deletePtr = -1;
            if(tree.getLeftChild() != null)
                removeTree234TreeDeletes(tree.getLeftChild());
            if(tree.getRightChild() != null)
                removeTree234TreeDeletes(tree.getRightChild());
        }
    }

    protected void run()
    {
        switch(executingMode)
        {
        case 11: // '\013'
            buildTree();
            break;

        case 12: // '\f'
            search();
            break;
        }
    }

    protected void run(boolean canChangeMode)
    {
        if(canChangeMode)
            executingMode = nextMode;
        run();
    }

    public void setCodePosition(String key)
    {
        setPosition(key);
    }

    public void search()
    {
        searchResults = new boolean[data.length];
        for(int i = 0; i < searchResults.length; i++)
            searchResults[i] = false;

        for(int i = 0; i < data.length; i++)
        {
            if(!enabled)
                return;
            position = i;
            if(tree != null)
            {
                searchResults[i] = tree.search(data[i], this);
                resetMarkers();
            } else
            {
                searchResults[i] = false;
            }
        }

        position = -1;
    }

    public boolean getEnabled()
    {
        return enabled;
    }

    protected void resetMarkers()
    {
        parentNode = grandParentNode = greatGrandParentNode = ptrNode = null;
        if(tree != null)
            tree.resetMarkers();
    }

    public ElementArray getDataElementArray()
    {
        ElementArray elementArray = new ElementArray(0, 0);
        for(int i = 0; i < data.length; i++)
        {
            Node tempNode = new Node((new StringBuilder()).append("").append(data[i]).toString(), i);
            if(i == position)
                tempNode.highlight();
            if(executingMode == 12 && i < searchResults.length && i < position)
            {
                String tmpString = "F";
                if(searchResults[i])
                    tmpString = "T";
                StringMarker stringMarker = new StringMarker(tmpString);
                tempNode.addMarker(stringMarker);
            }
            elementArray.setValue(i, tempNode);
        }

        return elementArray;
    }

    public void insert(int data)
    {
        if(tree == null)
        {
            setPosition("3");
            setPosition("3.1.1");
            setPosition("3.2.0.1");
            if(!enabled)
                return;
            tree = new Tree234RedBlack(data, null, null, false);
            setPosition("3.3");
            setPosition("3.5");
        } else
        {
            tree.insert(data, this);
        }
        resetMarkers();
    }

    public void buildTree()
    {
        canStoreState = false;
        setPosition("2");
        setPosition("2.1");
        for(int i = basePosition; i < data.length; i++)
        {
            if(!enabled)
                return;
            position = i;
            setPosition("2.2");
            insert(data[i]);
        }

        position = -1;
        setPosition("2.3");
        if(!enabled)
        {
            return;
        } else
        {
            canStoreState = true;
            return;
        }
    }

    public void controlStep(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlBack(ControlEvent e)
    {
        isBackMode = true;
    }

    public void controlPause(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlReset(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlRestart(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlRun(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlOther(ControlEvent e)
    {
        isBackMode = false;
    }

    protected static int POS = 0;
    private final Color T234_NODE_COLOR;
    private final Color T234_COMPARE_COLOR;
    private final Color T234_SPLIT_COLOR;
    private final Color RB_NODE_COLOR;
    private final Color PATH_COLOR;
    public boolean isBackMode;
    protected static final int BUILD_MODE = 11;
    protected static final int SEARCH_MODE = 12;
    protected int data[];
    protected boolean searchResults[];
    protected int position;
    protected int executingMode;
    protected int nextMode;
    protected Tree234RedBlack tree;
    protected Tree234RedBlack baseTree;
    protected int basePosition;
    protected boolean canStoreState;
    protected Vector insertRequests;
    protected Vector deleteRequests;
    protected Vector deleter;
    protected Vector spliter;
    protected Vector comparer;
    protected Node animationNode;
    protected Node growNode;
    protected HierarchyTree parentNode;
    protected HierarchyTree grandParentNode;
    protected HierarchyTree greatGrandParentNode;
    protected HierarchyTree ptrNode;
    protected HierarchyTree pathNode;
    protected static final String BUILD_MODE_LABEL = Messages.getString("Tree234Tree.0");
    protected static final String SEARCH_MODE_LABEL = Messages.getString("Tree234Tree.1");
    public boolean update234;

}
