// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BinarySearchTree.java

import com.cim.AIA.*;
import java.awt.Color;
import java.util.Vector;
import localization.Messages;

public class BinarySearchTree extends Algorithm
    implements MethodSelectionListener, ControlListener
{
    private class BinaryTree
    {

        BinaryTree leftChild;
        BinaryTree rightChild;
        int data;
        Node node;
        Node leftNode;
        Node rightNode;
        final BinarySearchTree this$0;

        BinaryTree(int data, int index)
        {
            this$0 = BinarySearchTree.this;
            super();
            leftChild = rightChild = null;
            this.data = data;
            node = new Node(new Integer(data), index);
            node.setBackgroundColor(BinarySearchTree.TREE_HIGHLIGHT_COLOR);
            node.setTextColor(BinarySearchTree.TEXT_COLOR);
            node.setRingColor(BinarySearchTree.PATH_COLOR);
            leftNode = new Node("", 0);
            leftNode.setBackgroundColor(BinarySearchTree.TREE_NULL_COLOR);
            leftNode.setTextColor(BinarySearchTree.TEXT_COLOR);
            leftNode.setWidth(node.getWidth() / 2);
            leftNode.setHeight(node.getHeight() / 2);
            leftNode.setIsVisible(true);
            rightNode = new Node("", 0);
            rightNode.setBackgroundColor(BinarySearchTree.TREE_NULL_COLOR);
            rightNode.setTextColor(BinarySearchTree.TEXT_COLOR);
            rightNode.setWidth(node.getWidth() / 2);
            rightNode.setHeight(node.getHeight() / 2);
            rightNode.setIsVisible(true);
        }
    }


    public BinarySearchTree(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        moveRequests = new Vector();
        newLinks = new Vector();
        isBackMode = false;
        executingMode = 11;
        nextMode = executingMode;
        insertData = (int[])(int[])data;
        searchData = (int[])(int[])data;
        root = null;
        initialise();
    }

    private ExtendedHierarchyTree buildHierarchyTree(BinaryTree binarySearchTree, int i)
    {
        ExtendedHierarchyTree hierarchyTree = null;
        if(binarySearchTree != null)
        {
            hierarchyTree = new ExtendedHierarchyTree();
            hierarchyTree.add(binarySearchTree.node);
            hierarchyTree.add(binarySearchTree.node);
            hierarchyTree.setBorderColor(Color.white);
            if(binarySearchTree.node == ptrNode)
                ptrTree = hierarchyTree;
            if(binarySearchTree.leftChild != null)
            {
                hierarchyTree.addChild(buildHierarchyTree(binarySearchTree.leftChild, 0));
            } else
            {
                ExtendedHierarchyTree temp = new ExtendedHierarchyTree(binarySearchTree.leftNode);
                temp = new ExtendedHierarchyTree();
                temp.add(binarySearchTree.leftNode);
                hierarchyTree.addChild(temp);
                if(binarySearchTree.leftNode == ptrNode)
                    ptrTree = temp;
            }
            if(binarySearchTree.rightChild != null)
            {
                hierarchyTree.addChild(buildHierarchyTree(binarySearchTree.rightChild, 0));
            } else
            {
                ExtendedHierarchyTree temp = new ExtendedHierarchyTree(binarySearchTree.rightNode);
                temp = new ExtendedHierarchyTree();
                temp.add(binarySearchTree.rightNode);
                hierarchyTree.addChild(temp);
                if(binarySearchTree.rightNode == ptrNode)
                    ptrTree = temp;
                hierarchyTree.addChild(temp);
            }
        }
        return hierarchyTree;
    }

    protected void changeData(Object data)
    {
        if(insertData == (int[])(int[])data);
        insertData = (int[])(int[])data;
        searchData = (int[])(int[])data;
        initialise();
    }

    protected void clearState()
    {
        saveRoot = null;
    }

    private void colorHierarchyTree(ExtendedHierarchyTree subTree)
    {
        if(subTree != null)
        {
            subTree.getLine().showAsThick(true);
            subTree.setParentLineColor(PATH_COLOR);
            if(subTree.getNodes().size() > 0)
            {
                ((Node)subTree.getNodes().elementAt(0)).setTextColor(PATH_COLOR);
                ((Node)subTree.getNodes().elementAt(0)).setRingColor(PATH_COLOR);
            }
            colorHierarchyTree((ExtendedHierarchyTree)subTree.getParent());
        }
    }

    public void controlBack(ControlEvent e)
    {
        isBackMode = true;
    }

    public void controlOther(ControlEvent e)
    {
        isBackMode = false;
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

    public void controlStep(ControlEvent e)
    {
        isBackMode = false;
    }

    protected void delete()
    {
        setPosition("4.0");
        if(root != null)
        {
            if(!isBackMode)
            {
                if(!enabled)
                    return;
                DeleteSelection.setEnabled(true);
                while(DeleteSelection.getNode() == null) 
                {
                    if(!enabled)
                    {
                        DeleteSelection.setEnabled(false);
                        return;
                    }
                    try
                    {
                        Thread.sleep(1000L);
                    }
                    catch(Exception e) { }
                }
                deleteNode = DeleteSelection.getNode();
                DeleteSelection.setEnabled(false);
                if(!enabled)
                    return;
            }
            int deleteValue = insertData[deleteNode.getIdentifier()];
            saveRoot = saveTree(root);
            BinaryTree parent;
            BinaryTree ptr = parent = root;
            ptrNode = root.node;
            setPosition("4.1.1");
            parentNode = root.node;
            while(ptr.data != deleteValue) 
            {
                setPosition("4.2.1");
                parent = ptr;
                parentNode = ptrNode;
                setPosition("4.2.1.1");
                setPosition("4.2.1.2");
                if(ptr.data > deleteValue)
                {
                    ptrNode = ptr.leftNode;
                    ptr = ptr.leftChild;
                    setPosition("4.2.1.2.1");
                } else
                {
                    setPosition("4.2.1.3");
                    ptrNode = ptr.rightNode;
                    ptr = ptr.rightChild;
                    setPosition("4.2.1.3.1");
                }
            }
            setPosition("4.2.1");
            setPosition("4.2.2");
            BinaryTree oldParent = parent;
            setPosition("4.2.3");
            BinaryTree dataItemPtr;
            parent = dataItemPtr = ptr;
            dataItemNode = ptrNode;
            parentNode = ptrNode;
            setPosition("4.2.4");
            if(ptr.rightChild == null)
            {
                ptrNode = ptr.leftNode;
                ptr = ptr.leftChild;
                if(ptr != null)
                {
                    setPosition("4.3.1.3.1");
                    while(ptr.rightChild != null) 
                    {
                        setPosition("4.3.1.3.2");
                        parent = ptr;
                        parentNode = ptrNode;
                        setPosition("4.3.1.3.2.1");
                        ptrNode = ptr.rightNode;
                        ptr = ptr.rightChild;
                        setPosition("4.3.1.3.2.2");
                    }
                    setPosition("4.3.1.3.2");
                    setPosition("4.3.1.3.3");
                } else
                {
                    setPosition("4.3.1.3.2");
                    setPosition("4.3.1.3.3");
                }
                replaceNode = ptrNode;
                setPosition("4.3.1.4.1");
                if(parent != dataItemPtr)
                {
                    newLinks.addElement(new NewLinks(parentNode, true, ptr.leftNode));
                    setPosition("4.3.1.4.1.1");
                    newLinks.addElement(new NewLinks(ptrNode, false, dataItemPtr.leftNode));
                    setPosition("4.3.1.4.1.2");
                    newLinks.addElement(new NewLinks(ptrNode, true, dataItemPtr.rightNode));
                    setPosition("4.3.1.4.1.3");
                } else
                if(parent == dataItemPtr)
                    if(ptr == null);
                dataItemNode = null;
                if(oldParent.data == deleteValue)
                    setPosition("4.3.1.5.1");
                else
                if(oldParent.leftChild != null && deleteValue == oldParent.leftChild.data)
                {
                    setPosition("4.3.1.5.1");
                    newLinks.addElement(new NewLinks(oldParent.node, false, ptrNode));
                    setPosition("4.3.1.5.2");
                } else
                {
                    setPosition("4.3.1.5.1");
                    setPosition("4.3.1.5.2");
                    newLinks.addElement(new NewLinks(oldParent.node, true, ptrNode));
                    setPosition("4.3.1.5.3");
                }
                newLinks.removeAllElements();
                if(parent != dataItemPtr)
                {
                    parent.rightChild = ptr.leftChild;
                    parent.rightNode = ptr.leftNode;
                    ptr.leftNode = dataItemPtr.leftNode;
                    ptr.rightNode = dataItemPtr.rightNode;
                    ptr.leftChild = dataItemPtr.leftChild;
                    ptr.rightChild = dataItemPtr.rightChild;
                } else
                if(parent == dataItemPtr && ptr != null)
                {
                    ptr.rightNode = dataItemPtr.rightNode;
                    ptr.rightChild = dataItemPtr.rightChild;
                }
                if(oldParent.data == deleteValue)
                {
                    root = ptr;
                    if(root != null)
                    {
                        root.node = ptrNode;
                        root.node.setMarkersBelowValue(false);
                        root.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.47")));
                    }
                    replaceNode = ptrNode = parentNode = null;
                    setPosition("4.3.1.5.1.1");
                } else
                if(oldParent.leftChild != null && deleteValue == oldParent.leftChild.data)
                {
                    oldParent.leftChild = ptr;
                    oldParent.leftNode = ptrNode;
                    replaceNode = ptrNode = parentNode = null;
                    setPosition(Messages.getString("BinarySearchTree.49"));
                } else
                {
                    oldParent.rightChild = ptr;
                    oldParent.rightNode = ptrNode;
                    replaceNode = ptrNode = parentNode = null;
                    setPosition("4.3.1.5.3.1");
                }
            } else
            if(ptr.rightChild != null)
            {
                ptrNode = ptr.rightNode;
                ptr = ptr.rightChild;
                setPosition("4.3.2.3.1");
                while(ptr.leftChild != null) 
                {
                    setPosition("4.3.2.3.2");
                    parent = ptr;
                    parentNode = ptrNode;
                    setPosition("4.3.2.3.2.1");
                    ptrNode = ptr.leftNode;
                    ptr = ptr.leftChild;
                    setPosition("4.3.2.3.2.2");
                }
                setPosition("4.3.2.3.2");
                setPosition("4.3.2.3.3");
                replaceNode = ptrNode;
                setPosition("4.3.2.4.1");
                if(parent != dataItemPtr)
                {
                    NewLinks n1 = new NewLinks(parentNode, false, ptr.rightNode);
                    newLinks.addElement(n1);
                    setPosition("4.3.2.4.1.1");
                    NewLinks n2 = new NewLinks(ptrNode, false, dataItemPtr.leftNode);
                    newLinks.addElement(n2);
                    setPosition("4.3.2.4.1.2");
                    NewLinks n3 = new NewLinks(ptrNode, true, dataItemPtr.rightNode);
                    newLinks.addElement(n3);
                    setPosition("4.3.2.4.1.3");
                } else
                {
                    setPosition("4.3.2.4.2");
                    newLinks.addElement(new NewLinks(ptrNode, false, dataItemPtr.leftNode));
                    setPosition("4.3.2.4.2.1");
                }
                dataItemNode = null;
                if(oldParent.data == deleteValue)
                    setPosition("4.3.2.5.1");
                else
                if(oldParent.leftChild != null && deleteValue == oldParent.leftChild.data)
                {
                    setPosition("4.3.2.5.1");
                    newLinks.addElement(new NewLinks(oldParent.node, false, ptrNode));
                    setPosition("4.3.2.5.2");
                } else
                {
                    setPosition("4.3.2.5.1");
                    setPosition("4.3.2.5.2");
                    newLinks.addElement(new NewLinks(oldParent.node, true, ptrNode));
                    setPosition("4.3.2.5.3");
                }
                newLinks.removeAllElements();
                if(parent != dataItemPtr)
                {
                    parent.leftChild = ptr.rightChild;
                    parent.leftNode = ptr.rightNode;
                    ptr.leftNode = dataItemPtr.leftNode;
                    ptr.rightNode = dataItemPtr.rightNode;
                    ptr.leftChild = dataItemPtr.leftChild;
                    ptr.rightChild = dataItemPtr.rightChild;
                } else
                {
                    ptr.leftNode = dataItemPtr.leftNode;
                    ptr.leftChild = dataItemPtr.leftChild;
                }
                if(oldParent.data == deleteValue)
                {
                    root = ptr;
                    if(root != null)
                    {
                        root.node = ptrNode;
                        root.node.setMarkersBelowValue(false);
                        root.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.69")));
                    }
                    replaceNode = ptrNode = parentNode = null;
                    setPosition("4.3.2.5.1.1");
                } else
                if(oldParent.leftChild != null && deleteValue == oldParent.leftChild.data)
                {
                    oldParent.leftChild = ptr;
                    oldParent.leftNode = ptrNode;
                    replaceNode = ptrNode = parentNode = null;
                    setPosition("4.3.2.5.2.1");
                } else
                {
                    oldParent.rightChild = ptr;
                    oldParent.rightNode = ptrNode;
                    replaceNode = ptrNode = parentNode = null;
                    setPosition("4.3.2.5.3.1");
                }
            }
        }
    }

    protected Vector generateQuestions()
    {
        return null;
    }

    public MultipleTween generateTweens(Paintable paintable, Object object, int numberOfSteps)
    {
        MultipleTween tweens = new MultipleTween(paintable);
        for(int i = 0; i < moveRequests.size(); i++)
        {
            MoveRequest move = (MoveRequest)moveRequests.elementAt(i);
            Node source = move.getSource();
            Node dest = move.getDest();
            tweens.add(new MoveTween(paintable, source, source.getPosition(), dest.getPosition(), false, numberOfSteps));
        }

        return tweens;
    }

    public Node getDataItemNode()
    {
        return dataItemNode;
    }

    public Node getdeleteNode()
    {
        if(deleteNode == null)
        {
            return null;
        } else
        {
            Node newNode = new Node(new Integer(insertData[deleteNode.getIdentifier()]), 0);
            newNode.addMarker(new StringMarker(Messages.getString("BinarySearchTree.7")));
            return newNode;
        }
    }

    public ExtendedHierarchyTree getHierarchyTree()
    {
        ptrTree = null;
        ExtendedHierarchyTree test = new ExtendedHierarchyTree();
        test = makeHierarchyTree(test, root);
        colorHierarchyTree(ptrTree);
        return test;
    }

    public ElementArray getInsertElementArray()
    {
        for(int i = 0; i < insertArray.getSize(); i++)
        {
            ((Node)insertArray.getElement(i)).setBackgroundColor(insertDataColor[i]);
            ((Node)insertArray.getElement(i)).setHighlightColor(SEARCH_HIGHLIGHT_COLOR);
            ((Node)insertArray.getElement(i)).setTextColor(TEXT_COLOR);
        }

        return insertArray;
    }

    public Vector getNewLinks()
    {
        return newLinks;
    }

    public Node getParentNode()
    {
        return parentNode;
    }

    public Node getPtrNode()
    {
        return ptrNode;
    }

    public Node getReplaceNode()
    {
        return replaceNode;
    }

    public ElementArray getSearchElementArray()
    {
        for(int i = 0; i < searchArray.getSize(); i++)
        {
            ((Node)searchArray.getElement(i)).setBackgroundColor(searchDataColor[i]);
            ((Node)searchArray.getElement(i)).setHighlightColor(SEARCH_HIGHLIGHT_COLOR);
            ((Node)searchArray.getElement(i)).setTextColor(TEXT_COLOR);
        }

        return searchArray;
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    private void initialise()
    {
        ptrNode = parentNode = null;
        if(!isBackMode)
            deleteNode = null;
        resetTreeColor(root);
        newLinks.removeAllElements();
        nullNode = new Node("", 0);
        nullNode.setBackgroundColor(TREE_NULL_COLOR);
        nullNode.setTextColor(TEXT_COLOR);
        nullNode.setWidth(nullNode.getWidth() / 2);
        nullNode.setHeight(nullNode.getHeight() / 2);
        if(backButton != null)
            backButton.switchOff(false);
    }

    protected void initialiseMethods(String string, MethodSelectable methodSelectable)
    {
        MethodSelection methodSelection1 = new MethodSelection(BUILD_MODE_LABEL, string, Messages.getString("BinarySearchTree.91"), "2.1s", getLogger(), getBreakPoint());
        MethodSelection methodSelection2 = new MethodSelection(SEARCH_MODE_LABEL, string, Messages.getString("BinarySearchTree.92"), "3s", getLogger(), getBreakPoint());
        MethodSelection methodSelection3 = new MethodSelection(DELETE_MODE_LABEL, string, Messages.getString("BinarySearchTree.93"), "4s", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelection(methodSelection3, false);
        methodSelectable.addMethodSelectionListener(this);
    }

    protected void insert()
    {
        if(insertData.length > 0)
        {
            if(insertPos == 0)
            {
                insertDataColor[0] = INSERT_HIGHLIGHT_COLOR;
                root = new BinaryTree(insertData[0], 0);
                setPosition("1.1");
                insertDataColor[0] = INSERT_DONE_COLOR;
                root.node.setBackgroundColor(TREE_ACTIVE_COLOR);
                root.node.setMarkersBelowValue(false);
                root.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.118")));
                insertPos++;
            }
            for(int i = insertPos; i < insertData.length; i++)
            {
                insertDataColor[i] = INSERT_HIGHLIGHT_COLOR;
                setPosition("2");
                BinaryTree parent;
                BinaryTree ptr = parent = root;
                parentNode = ptrNode = root.node;
                setPosition("2.1.1.1");
                while(ptr != null) 
                {
                    setPosition("2.1.2.1");
                    parent = ptr;
                    parentNode = ptrNode;
                    setPosition("2.1.2.1.1");
                    setPosition("2.1.2.1.2");
                    if(insertData[i] < ptr.data)
                    {
                        ptrNode = parent.leftNode;
                        ptr = ptr.leftChild;
                        setPosition("2.1.2.1.2.1");
                    } else
                    {
                        setPosition("2.1.2.1.3");
                        ptrNode = parent.rightNode;
                        ptr = parent.rightChild;
                        setPosition("2.1.2.1.3.1");
                    }
                }
                setPosition("2.1.2.2");
                setPosition("2.1.2.1");
                parentNode = ptrNode = null;
                setPosition("2.1.3.1");
                if(!enabled)
                    return;
                if(insertData[i] < parent.data)
                {
                    parent.leftChild = new BinaryTree(insertData[i], i);
                    parent.leftNode = parent.leftChild.node;
                    setPosition("2.1.3.1.1");
                } else
                {
                    setPosition("2.1.3.2");
                    parent.rightChild = new BinaryTree(insertData[i], i);
                    parent.rightNode = parent.rightChild.node;
                    setPosition("2.1.3.2.1");
                }
                resetTreeColor(root);
                insertDataColor[i] = INSERT_DONE_COLOR;
                insertPos++;
            }

        }
    }

    public boolean isBuildMode()
    {
        return executingMode == 11;
    }

    public boolean isDeleteMode()
    {
        return executingMode == 13;
    }

    public boolean isSearchMode()
    {
        return executingMode == 12;
    }

    private ExtendedHierarchyTree makeHierarchyTree(ExtendedHierarchyTree parent, BinaryTree binarySearchTree)
    {
        if(binarySearchTree != null)
        {
            ExtendedHierarchyTree hierarchyTree = new ExtendedHierarchyTree(parent, binarySearchTree.node);
            if(binarySearchTree.node == ptrNode)
                ptrTree = hierarchyTree;
            if(binarySearchTree.leftChild != null)
            {
                hierarchyTree.addChild(makeHierarchyTree(hierarchyTree, binarySearchTree.leftChild));
            } else
            {
                ExtendedHierarchyTree temp = new ExtendedHierarchyTree(hierarchyTree, binarySearchTree.leftNode);
                hierarchyTree.addChild(temp);
                if(binarySearchTree.leftNode == ptrNode)
                    ptrTree = temp;
            }
            if(binarySearchTree.rightChild != null)
            {
                hierarchyTree.addChild(makeHierarchyTree(hierarchyTree, binarySearchTree.rightChild));
            } else
            {
                ExtendedHierarchyTree temp = new ExtendedHierarchyTree(hierarchyTree, binarySearchTree.rightNode);
                hierarchyTree.addChild(temp);
                if(binarySearchTree.rightNode == ptrNode)
                    ptrTree = temp;
            }
            return hierarchyTree;
        } else
        {
            return null;
        }
    }

    protected void makeQuestion(int j)
    {
    }

    protected void move(Node source, Node dest)
    {
        moveRequests.addElement(new MoveRequest(source, dest));
    }

    public void processMethodSelectionEvent(MethodSelectionEvent e)
    {
        if(BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0)
            nextMode = 11;
        if(SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0)
            nextMode = 12;
        if(DELETE_MODE_LABEL.compareTo(e.getMethodName()) == 0)
            nextMode = 13;
    }

    public void reInitialise(Object data)
    {
        insertData = (int[])(int[])data;
        searchData = (int[])(int[])data;
        if(executingMode == 11)
            saveRoot = null;
        root = saveTree(saveRoot);
        deleteNode = saveDeleteNode;
        if(root != null)
        {
            root.node.setMarkersBelowValue(false);
            root.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.8")));
        }
        insertPos = 0;
        insertDataColor = new Color[insertData.length];
        searchDataColor = new Color[insertData.length];
        for(int i = 0; i < insertData.length; i++)
            insertDataColor[i] = INSERT_ACTIVE_COLOR;

        insertArray = new ElementArray(0, 0);
        insertArray.setColumGap(0);
        insertArray.setElementWidth(20);
        for(int i = 0; i < insertData.length; i++)
        {
            Node node = new Node(new Integer(insertData[i]), i);
            node.setBackgroundColor(insertDataColor[i]);
            node.setHighlightColor(INSERT_HIGHLIGHT_COLOR);
            node.setTextColor(TEXT_COLOR);
            insertArray.setValue(i, node);
        }

        for(int i = 0; i < searchData.length; i++)
            searchDataColor[i] = SEARCH_ACTIVE_COLOR;

        if(!isBackMode)
        {
            searchArray = new ElementArray(0, 0);
            searchArray.setColumGap(0);
            searchArray.setElementWidth(20);
            for(int i = 0; i < searchData.length; i++)
            {
                Node node = new Node(new Integer(searchData[i]), i);
                node.setBackgroundColor(searchDataColor[i]);
                node.setHighlightColor(SEARCH_HIGHLIGHT_COLOR);
                node.setTextColor(TEXT_COLOR);
                searchArray.setValue(i, node);
            }

        } else
        {
            for(int i = 0; i < searchData.length; i++)
            {
                Node node = (Node)searchArray.getElement(i);
                node.removeAllMarkers();
            }

        }
        initialise();
    }

    protected void removeAllAnimationRequests()
    {
        moveRequests.removeAllElements();
    }

    protected void removeAllQuestions()
    {
    }

    private void resetTreeColor(BinaryTree tree)
    {
        if(tree != null)
        {
            tree.node.setBackgroundColor(TREE_ACTIVE_COLOR);
            tree.node.setRingColor(TREE_RING_COLOR);
            tree.node.setTextColor(TEXT_COLOR);
            resetTreeColor(tree.leftChild);
            resetTreeColor(tree.rightChild);
        }
    }

    protected void run()
    {
        if(!enabled)
            return;
        setPosition("0");
        if(executingMode == 11)
            insert();
        else
        if(executingMode == 12)
            search();
        else
        if(executingMode == 13)
            delete();
    }

    protected void run(boolean canChangeMode)
    {
        if(canChangeMode)
            executingMode = nextMode;
        run();
    }

    protected BinaryTree saveTree(BinaryTree original)
    {
        if(original == null)
            return null;
        int index = original.node.getIdentifier();
        int data = original.data;
        BinaryTree newTree = new BinaryTree(data, index);
        if(deleteNode == original.node)
            saveDeleteNode = newTree.node;
        newTree.leftChild = saveTree(original.leftChild);
        newTree.rightChild = saveTree(original.rightChild);
        if(newTree.leftChild != null)
            newTree.leftNode = newTree.leftChild.node;
        if(newTree.rightChild != null)
            newTree.rightNode = newTree.rightChild.node;
        return newTree;
    }

    protected void search()
    {
        makeQuestion(13);
        setPosition("3.0");
        if(root != null)
        {
            if(!isBackMode)
            {
                if(!enabled)
                    return;
                SearchSelection.setEnabled(true);
                while(SearchSelection.getNode() == null) 
                {
                    if(!enabled)
                    {
                        SearchSelection.setEnabled(false);
                        return;
                    }
                    try
                    {
                        Thread.sleep(1000L);
                    }
                    catch(Exception e) { }
                }
                searchNode = SearchSelection.getNode();
                SearchSelection.setEnabled(false);
                if(!enabled)
                    return;
            }
            searchDataColor[searchNode.getIdentifier()] = SEARCH_HIGHLIGHT_COLOR;
            int searchValue = Integer.valueOf(searchNode.getDisplayString()).intValue();
            BinaryTree parent;
            BinaryTree ptr = parent = root;
            ptrNode = root.node;
            setPosition("3.1.1");
            while(ptr != null) 
            {
                setPosition("3.2");
                setPosition("3.2.1");
                if(ptr.data == searchValue)
                {
                    resetTreeColor(root);
                    if(!enabled)
                    {
                        searchDataColor[searchNode.getIdentifier()] = SEARCH_ACTIVE_COLOR;
                        return;
                    } else
                    {
                        searchNode.addMarker(new StringMarker("F"));
                        searchNode.addMarker(new StringMarker("o"));
                        searchNode.addMarker(new StringMarker("u"));
                        searchNode.addMarker(new StringMarker("n"));
                        searchNode.addMarker(new StringMarker("d"));
                        searchDataColor[searchNode.getIdentifier()] = SEARCH_DONE_COLOR;
                        setPosition("3.2.1.1");
                        return;
                    }
                }
                parent = ptr;
                setPosition("3.2.2.1.2");
                if(searchValue < ptr.data)
                {
                    ptrNode = parent.leftNode;
                    ptr = ptr.leftChild;
                    setPosition("3.2.2.1.2.1");
                } else
                {
                    setPosition("3.2.2.1.3");
                    ptrNode = parent.rightNode;
                    ptr = parent.rightChild;
                    setPosition("3.2.2.1.3.1");
                }
            }
            setPosition("3.2");
            resetTreeColor(root);
            if(!enabled)
            {
                searchDataColor[searchNode.getIdentifier()] = SEARCH_ACTIVE_COLOR;
                return;
            }
            searchNode.addMarker(new StringMarker("N"));
            searchNode.addMarker(new StringMarker("o"));
            searchNode.addMarker(new StringMarker("t"));
            searchNode.addMarker(new StringMarker(" "));
            searchNode.addMarker(new StringMarker("F"));
            searchNode.addMarker(new StringMarker("o"));
            searchNode.addMarker(new StringMarker("u"));
            searchNode.addMarker(new StringMarker("n"));
            searchNode.addMarker(new StringMarker("d"));
            searchDataColor[searchNode.getIdentifier()] = SEARCH_DONE_COLOR;
            setPosition("3.3.3");
        }
    }

    protected void setEnabled(boolean state)
    {
        enabled = state;
    }

    protected void storeState(boolean forceState)
    {
        if(executingMode == 13)
        {
            saveRoot.node.setMarkersBelowValue(false);
            saveRoot.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.116")));
            deleteNode = saveDeleteNode;
        } else
        {
            saveRoot = saveTree(root);
            saveRoot.node.setMarkersBelowValue(false);
            saveRoot.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.117")));
            deleteNode = saveDeleteNode;
        }
    }

    private static final byte shrinkFactor = 2;
    private static final Color TEXT_COLOR;
    private static final Color INSERT_HIGHLIGHT_COLOR = new Color(0, 255, 0);
    private static final Color INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
    private static final Color INSERT_DONE_COLOR = new Color(96, 127, 96);
    private static final Color SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
    private static final Color SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
    private static final Color SEARCH_DONE_COLOR = new Color(0, 127, 127);
    private static final Color TREE_HIGHLIGHT_COLOR = new Color(255, 255, 0);
    private static final Color TREE_ACTIVE_COLOR = new Color(191, 191, 64);
    private static final Color TREE_RING_COLOR;
    private static final Color TREE_NULL_COLOR;
    private static final Color PATH_COLOR;
    public static NewBackButton backButton;
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private static final int DELETE_MODE = 13;
    private static final String BUILD_MODE_LABEL = Messages.getString("BinarySearchTree.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("BinarySearchTree.1");
    private static final String DELETE_MODE_LABEL = Messages.getString("BinarySearchTree.2");
    protected static final String PARENT_LABEL = Messages.getString("BinarySearchTree.3");
    protected static final String PTR_LABEL = Messages.getString("BinarySearchTree.4");
    protected static final String DATAITEMPTR_LABEL = Messages.getString("BinarySearchTree.5");
    protected static final String REPLACEMENT_LABEL = Messages.getString("BinarySearchTree.6");
    private int insertData[];
    private int insertPos;
    private int searchData[];
    private BinaryTree root;
    private BinaryTree saveRoot;
    private Node searchNode;
    private Node deleteNode;
    private Node saveDeleteNode;
    private Color insertDataColor[];
    private Color searchDataColor[];
    private ElementArray searchArray;
    private ElementArray insertArray;
    private Vector moveRequests;
    private Vector newLinks;
    private Node parentNode;
    private Node ptrNode;
    private Node dataItemNode;
    private Node replaceNode;
    private Node nullNode;
    private ExtendedHierarchyTree ptrTree;
    private boolean isBackMode;
    private int executingMode;
    private int nextMode;

    static 
    {
        TEXT_COLOR = Color.black;
        TREE_RING_COLOR = Color.black;
        TREE_NULL_COLOR = Color.blue;
        PATH_COLOR = Color.red;
    }




}
