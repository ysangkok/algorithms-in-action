// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RedBlackTree.java

import com.cim.AIA.*;
import com.cim.common.MessageDialog;
import java.awt.Color;
import java.util.Vector;
import localization.Messages;

public class RedBlackTree extends Algorithm
    implements MethodSelectionListener
{

    public RedBlackTree(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        animThread = new RotateAnimation();
        noHTreeUpdate = false;
        removeSaveLine = false;
        position = -1;
        executingMode = 11;
        nextMode = executingMode;
        canStoreState = false;
        lineMoveRequests = new Vector();
        insertRequests = new Vector();
        deleteRequests = new Vector();
        deleter = new Vector();
        animationNode = null;
        growNode = null;
        update234 = true;
        findNodePtr = null;
        findNodeParent = null;
        theThread = algorithmThread;
        this.data = (int[])(int[])data;
        baseTree = null;
        basePosition = 0;
        animThread.start();
        initialise();
    }

    protected void balanceHierarchyTree(HierarchyTree hierarchyTree)
    {
        if(hierarchyTree == null)
            return;
        Node bNode = hierarchyTree.getNodeAt(1);
        for(int v = 0; v < deleter.size(); v++)
        {
            Node cNode = (Node)deleter.elementAt(v);
            if(cNode == bNode && (hierarchyTree.getParent() != null || hierarchyTree.numberOfElements() > 1))
            {
                insertRequests.addElement(bNode);
                deleter.removeElementAt(v);
                animationNode = new Node(cNode.getObject(), cNode.getIdentifier());
                animationNode.setBackgroundColor(Color.green);
                continue;
            }
            if(cNode == bNode)
            {
                deleter.removeElementAt(v);
                insertRequests.addElement(bNode);
                animationNode = new Node(cNode.getObject(), cNode.getIdentifier());
                animationNode.setBackgroundColor(Color.green);
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

    public void buildTuteTree()
    {
        int DEFAULT_DATA[] = {
            75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 
            50, 30, 45, 35, 40
        };
        int data = DEFAULT_DATA[0];
        tuteTree = new RedBlack(data, null, null, false);
        for(int i = 1; i < DEFAULT_DATA.length; i++)
            tuteTree.insert(DEFAULT_DATA[i]);

    }

    protected void changeData(Object data)
    {
        this.data = (int[])(int[])data;
        position = -1;
        save();
    }

    protected void clearState()
    {
        baseTree = null;
        basePosition = 0;
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

    public void drawAllParentLines(RedBlack tree)
    {
        if(tree == null)
        {
            return;
        } else
        {
            tree.setDrawParentLine(true);
            drawAllParentLines(tree.leftChild);
            drawAllParentLines(tree.rightChild);
            return;
        }
    }

    protected RedBlack findNode(RedBlack root, HierarchyTree hTree, Node searchNode)
    {
        if(root == null || hTree == null)
            return null;
        Node testNode = hTree.getNodeAt(1);
        if(testNode == searchNode)
        {
            findNodePtr = root;
            return root;
        }
        HierarchyTree leftChild = hTree.getLeftChild();
        HierarchyTree rightChild = hTree.getRightChild();
        RedBlack a = findNode(root.leftChild, leftChild, searchNode);
        RedBlack b = findNode(root.rightChild, rightChild, searchNode);
        if(a != null)
            findNodeParent = root;
        if(b != null)
            findNodeParent = root;
        return null;
    }

    protected Vector generateQuestions()
    {
        return null;
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        MultipleTween tweens = new MultipleTween(paintable);
        for(int i = 0; i < insertRequests.size(); i++)
            tweens.add(new InsertTween(null, (Sizeable)insertRequests.elementAt(i), numberOfSteps));

        for(int i = 0; i < deleteRequests.size(); i++)
            tweens.add(new DeleteTween(null, (Node)(Node)deleteRequests.elementAt(i), numberOfSteps));

        if(insertRequests.size() > 0 || deleteRequests.size() > 0)
            return tweens;
        else
            return null;
    }

    public Node getAnimationNode()
    {
        return animationNode;
    }

    public Thread getAnimThread()
    {
        return animThread;
    }

    public HierarchyTree getBalancedHierarchyTree(int nodeWidth)
    {
        HierarchyTree hierarchyTree = getHierarchyTree(null, tree, tree, 0, nodeWidth, false, true);
        balanceHierarchyTree(hierarchyTree);
        colorTree(hierarchyTree, Color.green);
        return hierarchyTree;
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

    public boolean getEnabled()
    {
        return enabled;
    }

    public HierarchyTree getGrandParentNode()
    {
        return grandParentNode;
    }

    public HierarchyTree getGreatGrandParentNode()
    {
        return greatGrandParentNode;
    }

    public Node getGrowNode()
    {
        return growNode;
    }

    protected HierarchyTree getHierarchyTree(HierarchyTree parent, RedBlack tree, RedBlack root, int level, int nodeWidth, boolean adjustMarkers, boolean animate)
    {
        if(tree == null)
            return null;
        Node node = null;
        if(!animate)
            node = new RoundNode(new Integer(tree.getDataItem()), tree.getPositionInserted());
        else
            node = new Node(new Integer(tree.getDataItem()), tree.getPositionInserted());
        node.setWidth(nodeWidth);
        if(tree.newNode & animate)
            insertRequests.addElement(node);
        if((tree.getPositionInserted() == RedBlack.deletePtr) & animate)
            deleter.addElement(node);
        if(tree.redNode)
            node.setHighlightColor(Color.red);
        else
            node.setHighlightColor(Color.gray);
        node.setBackgroundColor(redBlackTreeNodeColor);
        HierarchyTree hierarchyTree = new HierarchyTree(parent, node);
        if(!animate)
            hierarchyTree.setDrawBorder(false);
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

    public HierarchyTree getHierarchyTree(int nodeWidth)
    {
        return getHierarchyTree(null, tree, tree, 0, nodeWidth, true, false);
    }

    public Vector getLineMoveRequests()
    {
        return lineMoveRequests;
    }

    public HierarchyTree getParentNode()
    {
        return parentNode;
    }

    public HierarchyTree getPtrNode()
    {
        return ptrNode;
    }

    public boolean getRemoveSaveLine()
    {
        return removeSaveLine;
    }

    protected HierarchyTree getTuteTree(HierarchyTree parent, RedBlack tree, RedBlack root, int level, int nodeWidth)
    {
        if(tree == null)
            return null;
        Node node = new Node(new Integer(tree.getDataItem()), tree.getPositionInserted());
        node.setWidth(nodeWidth);
        node.setBackgroundColor(tuteNodeColor);
        if(tree.getPositionInserted() == RedBlack.ptrP)
        {
            node.setBackgroundColor(tuteSelectColor);
            node.addMarker(new StringMarker(Messages.getString("RedBlackTree.3")));
        }
        if(tree.getPositionInserted() == RedBlack.ptrC)
        {
            node.setBackgroundColor(tuteSelectColor);
            node.addMarker(new StringMarker(Messages.getString("RedBlackTree.2")));
        }
        HierarchyTree hierarchyTree = new HierarchyTree(parent, node);
        if(!tree.getDrawParentLine())
            hierarchyTree.setDrawParentLine(false);
        if(tree.leftChild == null && tree.rightChild != null)
        {
            Node fakeLeftNode = new Node(new Integer(0), -1);
            fakeLeftNode.setWidth(nodeWidth / 4);
            fakeLeftNode.setIsVisible(false);
            HierarchyTree fakeLeft = new HierarchyTree(hierarchyTree, fakeLeftNode);
            hierarchyTree.addChild(fakeLeft);
        } else
        if(tree.leftChild != null)
            hierarchyTree.addChild(getTuteTree(hierarchyTree, tree.leftChild, root, level + 1, nodeWidth));
        if(tree.rightChild == null && tree.leftChild != null)
        {
            Node fakeRightNode = new Node(new Integer(0), -1);
            fakeRightNode.setWidth(nodeWidth / 4);
            fakeRightNode.setIsVisible(false);
            HierarchyTree fakeRight = new HierarchyTree(hierarchyTree, fakeRightNode);
            hierarchyTree.addChild(fakeRight);
        } else
        if(tree.rightChild != null)
            hierarchyTree.addChild(getTuteTree(hierarchyTree, tree.rightChild, root, level + 1, nodeWidth));
        if(tree.rightChild == null && tree.leftChild == null)
        {
            Node fakeLeftNode = new Node(new Integer(0), -1);
            Node fakeRightNode = new Node(new Integer(0), -1);
            fakeLeftNode.setWidth(nodeWidth / 4);
            fakeRightNode.setWidth(nodeWidth / 4);
            fakeLeftNode.setIsVisible(false);
            fakeRightNode.setIsVisible(false);
            HierarchyTree fakeLeft = new HierarchyTree(hierarchyTree, fakeLeftNode);
            hierarchyTree.addChild(fakeLeft);
            HierarchyTree fakeRight = new HierarchyTree(hierarchyTree, fakeRightNode);
            hierarchyTree.addChild(fakeRight);
        }
        if(parent != null)
            if(tree.getHighLightParent())
                hierarchyTree.setParentLineColor(tuteHighLightColor);
            else
                hierarchyTree.setParentLineColor(Color.black);
        return hierarchyTree;
    }

    public HierarchyTree getTuteTree(int nodeWidth)
    {
        if(tuteHTree == null || !noHTreeUpdate)
            tuteHTree = getTuteTree(null, tuteTree, tuteTree, 0, nodeWidth);
        return tuteHTree;
    }

    protected boolean hasQuestions()
    {
        return false;
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
        lineMoveRequests = new Vector();
        deleter = new Vector();
        parentNode = grandParentNode = greatGrandParentNode = ptrNode = null;
        position = -1;
        RedBlack.POSITION = basePosition;
    }

    protected void initialiseMethods(String dataDir, MethodSelectable methodSelectable)
    {
        MethodSelection build = new MethodSelection(BUILD_MODE_LABEL, dataDir, Messages.getString("RedBlackTree.91"), "2.2a", getLogger(), getBreakPoint());
        MethodSelection search = new MethodSelection(SEARCH_MODE_LABEL, dataDir, Messages.getString("RedBlackTree.92"), "4", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(build, true);
        methodSelectable.addMethodSelection(search, false);
        methodSelectable.addMethodSelectionListener(this);
    }

    public void insert(int data)
    {
        if(tree == null)
        {
            setPosition("3");
            setPosition("3.1");
            setPosition("3.1.1");
            if(!enabled)
                return;
            tree = new RedBlack(data, null, null, false);
            setPosition("3.3");
            setPosition("3.3.4");
            setPosition("3.4");
            setPosition("3.4.4");
            setPosition("3.5");
        } else
        {
            tree.insert(data, this);
        }
        resetMarkers();
    }

    public void leftRotateRequest()
    {
        if(tuteTree == null)
            return;
        if(!enabled)
            return;
        doRightRotate = false;
        noHTreeUpdate = true;
        if(messageDialog != null)
            messageDialog.dispose();
        messageDialog = new MessageDialog(Messages.getString("RedBlackTree.10"), false, false);
        messageDialog.setTitle(Messages.getString("RedBlackTree.11"));
        messageDialog.setVisible(true);
        NodeSelection nodeSelection = NodeSelection.getInstance(this);
        NodeSelection.setEnabled(true);
    }

    public void processMethodSelectionEvent(MethodSelectionEvent e)
    {
        if(BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0)
            nextMode = 11;
        else
        if(SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0)
            nextMode = 12;
    }

    public void processNodeSelection()
    {
        if(messageDialog != null)
        {
            messageDialog.setVisible(false);
            messageDialog.dispose();
        }
        Node selectedNode = NodeSelection.getNode();
        findNodeParent = null;
        findNodePtr = null;
        findNode(tuteTree, tuteHTree, selectedNode);
        NodeSelection.setEnabled(false);
        noHTreeUpdate = false;
        RedBlack h = findNodePtr;
        if(!doRightRotate)
        {
            if(h.rightChild == null)
            {
                MessageDialog msg = new MessageDialog(Messages.getString("RedBlackTree.4"), true, true);
                msg.setTitle(Messages.getString("RedBlackTree.5"));
                msg.setVisible(true);
                noHTreeUpdate = false;
                return;
            }
            h.setHighLightParent(true);
            h.rightChild.setHighLightParent(true);
            if(h.rightChild.leftChild != null)
                h.rightChild.leftChild.setHighLightParent(true);
            animThread.initialise((RedBlackTreeThread)theThread, this, lineMoveRequests, tuteTree, findNodePtr, findNodeParent);
            animThread.rotateLeft();
            animThread.setEnabled(true);
        }
        if(doRightRotate)
        {
            if(h.leftChild == null)
            {
                MessageDialog msg = new MessageDialog(Messages.getString("RedBlackTree.6"), true, true);
                msg.setTitle(Messages.getString("RedBlackTree.7"));
                msg.setVisible(true);
                noHTreeUpdate = false;
                return;
            }
            h.setHighLightParent(true);
            h.leftChild.setHighLightParent(true);
            if(h.leftChild.rightChild != null)
                h.leftChild.rightChild.setHighLightParent(true);
            animThread.initialise((RedBlackTreeThread)theThread, this, lineMoveRequests, tuteTree, findNodePtr, findNodeParent);
            animThread.rotateRight();
            animThread.setEnabled(true);
        }
    }

    public void reInitialise(Object data)
    {
        this.data = (int[])(int[])data;
        initialise();
    }

    protected void removeAllAnimationRequests()
    {
        insertRequests = new Vector();
        deleteRequests = new Vector();
        removeRedBlackTreeDeletes(tree);
        removeRedBlackTreeInserts(tree);
        growNode = null;
        animationNode = null;
    }

    protected void removeAllQuestions()
    {
    }

    protected void removeRedBlackTreeDeletes(RedBlack tree)
    {
        if(tree != null)
        {
            tree.deleteNode = false;
            RedBlack.deletePtr = -1;
            if(tree.getLeftChild() != null)
                removeRedBlackTreeDeletes(tree.getLeftChild());
            if(tree.getRightChild() != null)
                removeRedBlackTreeDeletes(tree.getRightChild());
        }
    }

    protected void removeRedBlackTreeInserts(RedBlack tree)
    {
        if(tree != null)
        {
            tree.newNode = false;
            if(tree.getLeftChild() != null)
                removeRedBlackTreeInserts(tree.getLeftChild());
            if(tree.getRightChild() != null)
                removeRedBlackTreeInserts(tree.getRightChild());
        }
    }

    public void resetAllHighLightParent(RedBlack tree)
    {
        if(tree == null)
        {
            return;
        } else
        {
            tree.setHighLightParent(false);
            resetAllHighLightParent(tree.leftChild);
            resetAllHighLightParent(tree.rightChild);
            return;
        }
    }

    protected void resetMarkers()
    {
        parentNode = grandParentNode = greatGrandParentNode = ptrNode = null;
        if(tree != null)
            tree.resetMarkers();
    }

    public void rightRotateRequest()
    {
        if(tuteTree == null)
            return;
        if(!enabled)
            return;
        doRightRotate = true;
        noHTreeUpdate = true;
        if(messageDialog != null)
            messageDialog.dispose();
        messageDialog = new MessageDialog(Messages.getString("RedBlackTree.8"), false, false);
        messageDialog.setTitle(Messages.getString("RedBlackTree.9"));
        messageDialog.setVisible(true);
        NodeSelection nodeSelection = NodeSelection.getInstance(this);
        NodeSelection.setEnabled(true);
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

    private void save()
    {
        if(tree != null)
            baseTree = tree.copy();
        else
            baseTree = null;
        basePosition = RedBlack.POSITION;
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

    public void setAnimDuration(int speed)
    {
        animThread.setDuration(speed);
    }

    public void setCodePosition(String key)
    {
        setPosition(key);
    }

    public void setRemoveSaveLine(boolean state)
    {
        removeSaveLine = state;
    }

    public void setTuteTree(RedBlack tTree)
    {
        tuteTree = tTree;
    }

    protected void storeState(boolean forceStore)
    {
        if(canStoreState || forceStore)
            save();
    }

    public void updateTuteTree()
    {
        noHTreeUpdate = false;
        if(tree != null)
            tuteTree = tree.copy();
        else
            tuteTree = null;
    }

    protected static final Color tuteNodeColor;
    protected static final Color redBlackTreeNodeColor;
    protected static final Color tuteHighLightColor;
    protected static final Color tuteSelectColor;
    protected static int POS = 0;
    protected static final int BUILD_MODE = 11;
    protected static final int SEARCH_MODE = 12;
    protected static final String BUILD_MODE_LABEL = Messages.getString("RedBlackTree.0");
    protected static final String SEARCH_MODE_LABEL = Messages.getString("RedBlackTree.1");
    protected RotateAnimation animThread;
    protected RedBlack tuteTree;
    protected HierarchyTree tuteHTree;
    protected boolean noHTreeUpdate;
    protected boolean removeSaveLine;
    protected int data[];
    protected boolean searchResults[];
    protected int position;
    protected int executingMode;
    protected int nextMode;
    protected RedBlack tree;
    protected RedBlack baseTree;
    protected int basePosition;
    protected boolean canStoreState;
    protected Vector lineMoveRequests;
    protected Vector insertRequests;
    protected Vector deleteRequests;
    protected Vector deleter;
    protected Node animationNode;
    protected Node growNode;
    protected HierarchyTree parentNode;
    protected HierarchyTree grandParentNode;
    protected HierarchyTree greatGrandParentNode;
    protected HierarchyTree ptrNode;
    private MessageDialog messageDialog;
    private AlgorithmThread theThread;
    public boolean update234;
    protected RedBlack findNodePtr;
    protected RedBlack findNodeParent;
    private boolean doRightRotate;

    static 
    {
        tuteNodeColor = Color.lightGray;
        redBlackTreeNodeColor = Color.lightGray;
        tuteHighLightColor = Color.red;
        tuteSelectColor = Color.yellow;
    }
}
