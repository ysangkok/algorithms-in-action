// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTreeIter.java

import com.cim.AIA.*;
import java.awt.Color;
import java.io.PrintStream;
import java.util.Random;
import java.util.Vector;
import localization.Messages;

public class PatriciaTreeIter extends Algorithm
    implements MethodSelectionListener, ControlListener
{
    protected class Path
    {

        void add(boolean isLeft)
        {
            if(next == null)
                next = new Path(isLeft);
            else
                next.add(isLeft);
        }

        boolean isLeft;
        Path next;
        final PatriciaTreeIter this$0;

        Path(boolean isLeft)
        {
            this$0 = PatriciaTreeIter.this;
            super();
            this.isLeft = isLeft;
        }
    }


    public PatriciaTreeIter(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        executingMode = 11;
        nextMode = executingMode;
        currentHierarchyTree = null;
        mostSignificantBit = 0;
        insertPos = 0;
        lastInsertPos = 0;
        isBackMode = false;
        savedTree = null;
        startingTree = null;
        savedTree = new PatriciaTreeIterNode();
        savedTree.setLeft(savedTree);
        savedTree.setRight(savedTree);
        insertData = new ElementArray(0, 0);
        insertData.setColumGap(0);
        insertData.setElementWidth(20);
        for(int i = 0; i < ((int[])(int[])data).length; i++)
        {
            insertData.add(new Node(new Integer(((int[])(int[])data)[i]), i), i);
            ((Node)insertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_ACTIVE_COLOR);
        }

        newInsertData = insertData;
        Random random = new Random();
        searchData = new ElementArray(0, 0);
        searchData.setColumGap(0);
        searchData.setElementWidth(20);
        for(int i = 0; i < ((int[])(int[])data).length; i++)
        {
            if(i % 2 == 0)
                searchData.add(new Node(new Integer(Math.abs(random.nextInt()) % 98 + 1), i), i);
            else
                searchData.add(new Node(new Integer(((int[])(int[])data)[i]), i), i);
            ((Node)searchData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.SEARCH_ACTIVE_COLOR);
        }

        newSearchData = searchData;
        head = new PatriciaTreeIterNode();
        head.setLeft(head);
        head.setRight(head);
    }

    protected void initialiseMethods(String dataDir, MethodSelectable methodSelectable)
    {
        MethodSelection build = new MethodSelection(BUILD_MODE_LABEL, dataDir, Messages.getString("PatriciaTreeIter.2"), "3a", getLogger(), getBreakPoint());
        MethodSelection search = new MethodSelection(SEARCH_MODE_LABEL, dataDir, Messages.getString("PatriciaTreeIter.4"), "5a", getLogger(), getBreakPoint());
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
        if(nextMode == 11)
            head = savedTree;
        if(nextMode == 12 && savedTree != null)
            head = savedTree.copy();
    }

    protected void storeState(boolean forceState)
    {
        if(executingMode == 11)
            savedTree = head.copy();
        else
        if(executingMode == 12)
            head = savedTree.copy();
    }

    protected void clearState()
    {
        insertPos = 0;
        head = new PatriciaTreeIterNode();
        head.setLeft(head);
        head.setRight(head);
    }

    public void reInitialise(Object data)
    {
        if(executingMode == 11 && !isBackMode)
        {
            savedTree = new PatriciaTreeIterNode();
            savedTree.setLeft(savedTree);
            savedTree.setRight(savedTree);
            insertPos = 0;
        }
        if(executingMode == 12 && !isBackMode)
            head = savedTree.copy();
        if(isBackMode)
        {
            if(executingMode == 11)
                head = startingTree.copy();
            else
            if(executingMode == 12)
                head = savedTree.copy();
            insertPos = lastInsertPos;
        }
        mostSignificantBit = 0;
        currentSearchTree = null;
        insertData = newInsertData;
        for(int i = 0; i < insertData.getSize(); i++)
            ((Node)insertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_ACTIVE_COLOR);

        for(int i = 0; i < insertData.getSize(); i++)
        {
            String binaryString = Integer.toBinaryString(((Integer)insertData.getElement(i).getObject()).intValue());
            if(mostSignificantBit < binaryString.length())
                mostSignificantBit = binaryString.length();
        }

        mostSignificantBit--;
    }

    protected void changeData(Object data)
    {
        Random random = new Random();
        newInsertData = new ElementArray(0, 0);
        newInsertData.setColumGap(0);
        newInsertData.setElementWidth(20);
        for(int i = 0; i < ((int[])(int[])data).length; i++)
        {
            newInsertData.add(new Node(new Integer(((int[])(int[])data)[i]), i), i);
            ((Node)newInsertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_ACTIVE_COLOR);
        }

        insertData = newInsertData;
        for(int i = 0; i < insertPos; i++)
            ((Node)insertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_DONE_COLOR);

        for(int i = 0; i < insertData.getSize(); i++)
        {
            String binaryString = Integer.toBinaryString(((Integer)insertData.getElement(i).getObject()).intValue());
            if(mostSignificantBit < binaryString.length())
                mostSignificantBit = binaryString.length();
        }

        mostSignificantBit--;
        searchData = new ElementArray(0, 0);
        searchData.setColumGap(0);
        searchData.setElementWidth(20);
        for(int i = 0; i < ((int[])(int[])data).length; i++)
        {
            if(i % 2 == 0)
                searchData.add(new Node(new Integer(Math.abs(random.nextInt()) % 98 + 1), i), i);
            else
                searchData.add(new Node(new Integer(((int[])(int[])data)[i]), i), i);
            ((Node)searchData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.SEARCH_ACTIVE_COLOR);
        }

        newSearchData = searchData;
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
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
    }

    public Integer getDifferentiatingBit()
    {
        return differentiatingBit;
    }

    public Integer getCurrentBit()
    {
        return currentBit;
    }

    public ElementArray getInsertElementArray()
    {
        if(executingMode == 11)
            return insertData;
        else
            return null;
    }

    public ElementArray getSearchElementArray()
    {
        if(executingMode == 12)
            return searchData;
        else
            return null;
    }

    public DigitalElementArray getDataItemBitArray()
    {
        return dataItemBitArray;
    }

    public DigitalElementArray getCompareBitArray()
    {
        return compareDataBitArray;
    }

    private DigitalElementArray initialiseBitElementArray(int number)
    {
        DigitalElementArray bitArray = new DigitalElementArray(number, mostSignificantBit);
        return bitArray;
    }

    public Vector getInsertedItemsArray()
    {
        Vector sortedList = new Vector();
        if(head.getLeft() != head)
            printNodes("", head.getLeft(), sortedList);
        for(int i = 0; i < sortedList.size(); i++)
            System.out.println(((Inserted)sortedList.elementAt(i)).getPattern());

        return sortedList;
    }

    public ExtendedHierarchyTree getHierarchyTree()
    {
        ExtendedHierarchyTree hierarchyTree = buildHierarchyTree(head, PatriciaTreeIterColors.DEFAULT_TREE_COLOR);
        colorHierarchyTree(hierarchyTree, currentPath);
        return hierarchyTree;
    }

    public Node getSearchPositionNode()
    {
        if(currentSearchTree != null)
            return currentSearchTree.getBody();
        else
            return null;
    }

    public Node getCompKeyNode()
    {
        return compKeyNode;
    }

    public Node getKeyFoundNode()
    {
        return keyFoundNode;
    }

    public Node getInsertionPoint()
    {
        if(insertionPoint != null)
            return insertionPoint.getBody();
        else
            return null;
    }

    public PatriciaTreeIterNode getInsertPositionNode()
    {
        return currentInsertTree;
    }

    public PatriciaTreeIterNode getNewNode()
    {
        return PatriciaTreeIterNode.getNewNode();
    }

    public void printNodes(String head, PatriciaTreeIterNode current, Vector sortedList)
    {
        if(current.getLeft().getBit() > current.getBit())
        {
            String leftHead = (new StringBuilder()).append(head).append("0").toString();
            for(int i = current.getBit() + 1; i < current.getLeft().getBit(); i++)
                leftHead = (new StringBuilder()).append(leftHead).append("*").toString();

            printNodes(leftHead, current.getLeft(), sortedList);
        } else
        {
            String leftHead = (new StringBuilder()).append(head).append("0").toString();
            for(int i = current.getBit() + 1; i <= mostSignificantBit; i++)
                leftHead = (new StringBuilder()).append(leftHead).append("*").toString();

            System.out.println((new StringBuilder()).append("Pattern: ").append(leftHead).append(" Node: ").append(current.getLeft().getDataItem().getKey()).toString());
            sortedList.addElement(new Inserted(leftHead, current.getLeft().getDataItem().getKey()));
        }
        if(current.getRight().getBit() > current.getBit())
        {
            String rightHead = (new StringBuilder()).append(head).append("1").toString();
            for(int i = current.getBit() + 1; i < current.getRight().getBit(); i++)
                rightHead = (new StringBuilder()).append(rightHead).append("*").toString();

            printNodes(rightHead, current.getRight(), sortedList);
        } else
        {
            String rightHead = (new StringBuilder()).append(head).append("1").toString();
            for(int i = current.getBit() + 1; i <= mostSignificantBit; i++)
                rightHead = (new StringBuilder()).append(rightHead).append("*").toString();

            System.out.println((new StringBuilder()).append("Pattern: ").append(rightHead).append(" Node: ").append(current.getRight().getDataItem().getKey()).toString());
            sortedList.addElement(new Inserted(rightHead, current.getRight().getDataItem().getKey()));
        }
    }

    public PatriciaTreeIterNode getHeadNode()
    {
        return head;
    }

    public ExtendedHierarchyTree buildHierarchyTree(PatriciaTreeIterNode current, Color currentColor)
    {
        current.getBody().setRingColor(PatriciaTreeIterColors.DEFAULT_RING_COLOR);
        current.getBody().setTextColor(PatriciaTreeIterColors.DEFAULT_TEXT_COLOR);
        ExtendedHierarchyTree hierarchyTree = new ExtendedHierarchyTree();
        hierarchyTree.setDrawBorder(false);
        hierarchyTree.getLine().showArrowHead(false);
        hierarchyTree.add(current.getBody());
        hierarchyTree.add(current.getDataItem().getNode());
        current.setHierarchyTree(hierarchyTree);
        if(current.getLeft().getBit() > current.getBit())
            hierarchyTree.addChild(buildHierarchyTree(current.getLeft(), currentColor));
        else
        if(current.getRight().getBit() > current.getBit())
        {
            ExtendedHierarchyTree temp = new ExtendedHierarchyTree();
            Node tempNode = new Node("", 0);
            tempNode.setIsVisible(false);
            temp.add(tempNode);
            hierarchyTree.addChild(temp);
        } else
        {
            ExtendedHierarchyTree temp = new ExtendedHierarchyTree();
            hierarchyTree.addChild(temp);
        }
        if(current.getRight().getBit() > current.getBit())
            hierarchyTree.addChild(buildHierarchyTree(current.getRight(), currentColor));
        else
        if(current.getLeft().getBit() > current.getBit())
        {
            ExtendedHierarchyTree temp = new ExtendedHierarchyTree();
            Node tempNode = new Node("", 0);
            tempNode.setIsVisible(false);
            temp.add(tempNode);
            hierarchyTree.addChild(temp);
        } else
        {
            ExtendedHierarchyTree temp = new ExtendedHierarchyTree();
            hierarchyTree.addChild(temp);
        }
        return hierarchyTree;
    }

    private void colorHierarchyTree(ExtendedHierarchyTree tree, Path path)
    {
        isHighlightLoop = null;
        if(tree == null)
            return;
        tree.getLine().showAsThick(true);
        tree.setParentLineColor(PatriciaTreeIterColors.SEARCH_PATH_COLOR);
        if(tree.getFirstElement() != null)
        {
            tree.getFirstElement().setRingColor(PatriciaTreeIterColors.SEARCH_PATH_COLOR);
            tree.getFirstElement().setTextColor(PatriciaTreeIterColors.SEARCH_PATH_COLOR);
        }
        if(path == null)
            return;
        ExtendedHierarchyTree subtree;
        if(path.isLeft)
            subtree = (ExtendedHierarchyTree)tree.getLeftChild();
        else
            subtree = (ExtendedHierarchyTree)tree.getRightChild();
        colorHierarchyTree(subtree, path.next);
        if(subtree != null && subtree.getNumberOfChildren() == 0)
            isHighlightLoop = new Boolean(path.isLeft);
    }

    protected void run()
    {
        if(!isBackMode)
        {
            lastInsertPos = insertPos;
            if(savedTree != null)
            {
                startingTree = savedTree.copy();
            } else
            {
                startingTree = new PatriciaTreeIterNode();
                startingTree.setLeft(startingTree);
                startingTree.setRight(startingTree);
            }
        }
        switch(executingMode)
        {
        default:
            break;

        case 11: // '\013'
            for(int i = insertPos; i < insertData.getSize(); i++)
            {
                PatriciaTreeIterDataItem dataItem = new PatriciaTreeIterDataItem(new Node((Integer)insertData.getElement(i).getObject(), i), ((Integer)insertData.getElement(i).getObject()).intValue());
                if(enabled)
                {
                    ((Node)insertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_HIGHLIGHT_COLOR);
                    savedTree = head.copy();
                    insert(head, dataItem);
                    if(enabled)
                        insertPos++;
                    else
                        head = savedTree.copy();
                    head.clearNewNode();
                    ((Node)insertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_DONE_COLOR);
                }
            }

            break;

        case 12: // '\f'
            for(int i = 0; i < searchData.getSize(); i++)
            {
                PatriciaTreeIterDataItem dataItem = new PatriciaTreeIterDataItem(new Node((Integer)searchData.getElement(i).getObject(), i), ((Integer)searchData.getElement(i).getObject()).intValue());
                if(!enabled)
                    continue;
                ((Node)searchData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.SEARCH_HIGHLIGHT_COLOR);
                if(search(head, dataItem))
                {
                    ((Node)searchData.getElement(i)).addMarker(new StringMarker("F"));
                    currentSearchTree.getDataItem().getNode().unHighlight();
                    setPosition("5.3.2");
                } else
                {
                    ((Node)searchData.getElement(i)).addMarker(new StringMarker("N"));
                    currentSearchTree.getDataItem().getNode().unHighlight();
                    setPosition("5.3.4");
                }
                ((Node)searchData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.SEARCH_DONE_COLOR);
            }

            for(int i = 0; i < searchData.getSize(); i++)
                ((Node)searchData.getElement(i)).removeAllMarkers();

            break;
        }
    }

    protected void run(boolean canChangeMode)
    {
        if(canChangeMode)
            executingMode = nextMode;
        run();
    }

    private void insert(PatriciaTreeIterNode root, PatriciaTreeIterDataItem dataItem)
    {
        byte bitPos = -1;
        PatriciaTreeIterNode parent = root;
        setPosition("3");
        currentPath = new Path(true);
        currentSearchTree = root.getLeft();
        dataItemBitArray = initialiseBitElementArray(dataItem.getKey());
        dataItemBitArray.setColors(PatriciaTreeIterColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeIterColors.INSERT_BIT_HIGHLIGHT_COLOR);
        isFollowedUpLink = new Boolean(currentSearchTree.getBit() <= parent.getBit());
        bitPos = root.getLeft().getBit();
        dataItemBitArray.unHighlight();
        if(bitPos >= 0)
            dataItemBitArray.highlightBit(bitPos);
        setPosition("3.1.1");
        for(; currentSearchTree.getBit() > parent.getBit(); setPosition("3.2.1.1.2"))
        {
            setPosition("3.2.1.1");
            parent = currentSearchTree;
            if(getBit(dataItem.getKey(), bitPos) == 0)
            {
                setPosition("3.2.1.1.1.1");
                currentPath.add(true);
                currentSearchTree = currentSearchTree.getLeft();
                isFollowedUpLink = new Boolean(currentSearchTree.getBit() <= parent.getBit());
                setPosition("3.2.1.1.1.2");
            } else
            {
                setPosition("3.2.1.1.1.1");
                setPosition("3.2.1.1.1.3");
                currentPath.add(false);
                currentSearchTree = currentSearchTree.getRight();
                isFollowedUpLink = new Boolean(currentSearchTree.getBit() <= parent.getBit());
                setPosition("3.2.1.1.1.4");
            }
            bitPos = currentSearchTree.getBit();
            dataItemBitArray.unHighlight();
            if(bitPos >= 0)
                dataItemBitArray.highlightBit(bitPos);
        }

        setPosition("3.2.1.1");
        if(currentSearchTree != null)
            currentSearchTree.getDataItem().getNode().highlight();
        setPosition("3.2.1.2");
        isFollowedUpLink = null;
        setPosition("3.2.1.3");
        if(currentSearchTree.getDataItem().getKey() == dataItem.getKey())
        {
            clearPointers();
            setPosition("3.2.1.3.1");
            return;
        }
        currentSearchTree.getDataItem().getNode().unHighlight();
        int compKey = currentSearchTree.getDataItem().getKey();
        compareDataBitArray = initialiseBitElementArray(compKey);
        compareDataBitArray.setColors(PatriciaTreeIterColors.DATA_ITEM_COLOR, PatriciaTreeIterColors.DATA_ITEM_HIGHLIGHT_COLOR);
        byte bit = 0;
        dataItemBitArray.unHighlight();
        dataItemBitArray.highlightBit(bit);
        compareDataBitArray.highlightBit(bit);
        setPosition("3.2.2.1");
        for(; getBit(compKey, bit) == getBit(dataItem.getKey(), bit); setPosition("3.2.2.2.1"))
        {
            setPosition("3.2.2.2");
            bit++;
            dataItemBitArray.unHighlight();
            dataItemBitArray.highlightBit(bit);
            compareDataBitArray.unHighlight();
            compareDataBitArray.highlightBit(bit);
        }

        compareDataBitArray.highlightNumber(bit, PatriciaTreeIterColors.DIFFERENTIATING_COLOR);
        differentiatingBit = new Integer(bit);
        setPosition("3.2.2.2");
        setPosition("3.2.2.3");
        parent = root;
        dataItemBitArray.unHighlight();
        compareDataBitArray = null;
        if(compKeyNode != null)
        {
            compKeyNode.unHighlight();
            compKeyNode = null;
        }
        currentPath = new Path(true);
        currentSearchTree = root.getLeft();
        isFollowedUpLink = new Boolean(currentSearchTree.getBit() <= parent.getBit());
        bitPos = currentSearchTree.getBit();
        dataItemBitArray.unHighlight();
        if(bitPos >= 0)
            dataItemBitArray.highlightBit(bitPos);
        setPosition("3.3.1.2");
        for(; currentSearchTree.getBit() > parent.getBit() && currentSearchTree.getBit() < bit; setPosition("3.3.2.1.3"))
        {
            setPosition("3.3.2.1");
            parent = currentSearchTree;
            if(getBit(dataItem.getKey(), bitPos) == 0)
            {
                setPosition("3.3.2.1.2.1");
                currentPath.add(true);
                currentSearchTree = currentSearchTree.getLeft();
                isFollowedUpLink = new Boolean(currentSearchTree.getBit() <= parent.getBit());
                setPosition("3.3.2.1.2.2");
            } else
            {
                setPosition("3.3.2.1.2.1");
                setPosition("3.3.2.1.2.3");
                currentPath.add(false);
                currentSearchTree = currentSearchTree.getRight();
                isFollowedUpLink = new Boolean(currentSearchTree.getBit() <= parent.getBit());
                setPosition("3.3.2.1.2.4");
            }
            bitPos = currentSearchTree.getBit();
            dataItemBitArray.unHighlight();
            if(bitPos >= 0)
                dataItemBitArray.highlightBit(bitPos);
        }

        setPosition("3.3.2.1");
        insertionPoint = currentSearchTree;
        insertionPoint.getDataItem().getNode().highlight();
        setPosition("3.3.2.2");
        isFollowedUpLink = null;
        PatriciaTreeIterNode newNode = new PatriciaTreeIterNode(dataItem, bit);
        newNode.getDataItem().getNode().highlight();
        currentPath = null;
        setPosition("3.3.3.1");
        if(getBit(dataItem.getKey(), bit) == 0)
        {
            setPosition("3.3.3.3.1");
            newNode.setLeft(newNode);
            newNode.setRight(currentSearchTree);
            setPosition("3.3.3.3.3");
            dataItemBitArray.unHighlight();
        } else
        {
            setPosition("3.3.3.3.1");
            setPosition("3.3.3.3.4");
            newNode.setLeft(currentSearchTree);
            newNode.setRight(newNode);
            setPosition("3.3.3.3.6");
            dataItemBitArray.unHighlight();
        }
        insertionPoint.getDataItem().getNode().unHighlight();
        insertionPoint = null;
        if(getBit(dataItem.getKey(), parent.getBit()) == 0)
            parent.setLeft(newNode);
        else
            parent.setRight(newNode);
        setPosition("3.3.3.4");
        newNode.getDataItem().getNode().unHighlight();
        dataItemBitArray = null;
        currentPath = null;
        differentiatingBit = null;
    }

    private PatriciaTreeIterNode insertKey(PatriciaTreeIterNode tree, PatriciaTreeIterDataItem dataItem, byte diffBit, PatriciaTreeIterNode parent, boolean isShowRecursion)
    {
        setPosition("3.4.1", isShowRecursion);
        currentInsertTree = tree;
        currentBit = new Integer(tree.getBit());
        setPosition("3.4.1.0", isShowRecursion);
        differentiatingBit = new Integer(diffBit);
        isFollowedUpLink = new Boolean(tree.getBit() >= diffBit || tree.getBit() <= parent.getBit());
        setPosition("3.4.1.1", isShowRecursion);
        if(tree.getBit() >= diffBit || tree.getBit() <= parent.getBit())
        {
            PatriciaTreeIterNode newNode = new PatriciaTreeIterNode(dataItem, diffBit);
            setPosition("3.4.1.1.1.1", isShowRecursion);
            dataItemBitArray = initialiseBitElementArray(dataItem.getKey());
            dataItemBitArray.setColors(PatriciaTreeIterColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeIterColors.INSERT_BIT_HIGHLIGHT_COLOR);
            dataItemBitArray.highlightBit(diffBit);
            setPosition("3.4.1.1.1.2.1", isShowRecursion);
            if(getBit(dataItem.getKey(), diffBit) == 0)
            {
                newNode.setLeft(newNode);
                setPosition("3.4.1.1.1.2.1.1", isShowRecursion);
                newNode.setRight(tree);
                setPosition("3.4.1.1.1.2.1.2", isShowRecursion);
                dataItemBitArray.unHighlight();
            } else
            {
                setPosition("3.4.1.1.1.2.2", isShowRecursion);
                newNode.setRight(newNode);
                setPosition("3.4.1.1.1.2.2.1", isShowRecursion);
                newNode.setLeft(tree);
                setPosition("3.4.1.1.1.2.2.2", isShowRecursion);
                dataItemBitArray.unHighlight();
            }
            if(!enabled)
            {
                clearPointers();
                return tree;
            } else
            {
                setPosition("3.4.1.1.1.3", isShowRecursion);
                currentBit = null;
                isFollowedUpLink = null;
                return newNode;
            }
        }
        setPosition("3.4.1.2", isShowRecursion);
        isFollowedUpLink = null;
        dataItemBitArray = initialiseBitElementArray(dataItem.getKey());
        dataItemBitArray.setColors(PatriciaTreeIterColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeIterColors.INSERT_BIT_HIGHLIGHT_COLOR);
        dataItemBitArray.highlightBit(tree.getBit());
        setPosition("3.4.1.2.1.2", isShowRecursion);
        if(getBit(dataItem.getKey(), tree.getBit()) == 0)
        {
            dataItemBitArray.unHighlight();
            currentPath.add(true);
            lastNode = tree;
            tree.setLeftIsKnown(false);
            setPosition("3.4.1.2.1.2.1", isShowRecursion);
            currentBit = null;
            if(isExpanded("3.4.1.2.1"))
            {
                PatriciaTreeIterNode unkownLeft = insertKey(tree.getLeft(), dataItem, diffBit, tree, true);
                tree.setLeftIsKnown(true);
                tree.setLeft(unkownLeft);
            } else
            {
                PatriciaTreeIterNode unkownLeft = insertKey(tree.getLeft(), dataItem, diffBit, tree, false);
                tree.setLeftIsKnown(true);
                tree.setLeft(unkownLeft);
            }
        } else
        {
            dataItemBitArray.unHighlight();
            currentPath.add(false);
            lastNode = tree;
            tree.setRightIsKnown(false);
            setPosition("3.4.1.2.1.3", isShowRecursion);
            currentBit = null;
            if(isExpanded("3.4.1.2.1"))
            {
                PatriciaTreeIterNode unkownRight = insertKey(tree.getRight(), dataItem, diffBit, tree, true);
                tree.setRightIsKnown(true);
                tree.setRight(unkownRight);
            } else
            {
                PatriciaTreeIterNode unkownRight = insertKey(tree.getRight(), dataItem, diffBit, tree, false);
                tree.setRightIsKnown(true);
                tree.setRight(unkownRight);
            }
        }
        currentInsertTree = tree;
        setPosition("3.4.1.2.1.4", isShowRecursion);
        currentBit = null;
        return tree;
    }

    private void setPosition(String key, boolean isPause)
    {
        if(isPause)
            setPosition(key);
        else
            setPosition("This key doesn't exist");
    }

    private boolean search(PatriciaTreeIterNode tree, PatriciaTreeIterDataItem dataItem)
    {
        dataItemBitArray = initialiseBitElementArray(dataItem.getKey());
        dataItemBitArray.setColors(PatriciaTreeIterColors.SEARCH_BIT_ACTIVE_COLOR, PatriciaTreeIterColors.SEARCH_BIT_HIGHLIGHT_COLOR);
        PatriciaTreeIterNode parent = tree;
        byte bitPos = -1;
        setPosition("5");
        currentPath = new Path(true);
        currentSearchTree = tree.getLeft();
        bitPos = tree.getLeft().getBit();
        dataItemBitArray.unHighlight();
        if(bitPos >= 0)
            dataItemBitArray.highlightBit(bitPos);
        isFollowedUpLink = new Boolean(currentSearchTree.getBit() <= parent.getBit());
        setPosition("5.1.1");
        for(; currentSearchTree.getBit() > parent.getBit(); setPosition("5.2.1.5"))
        {
            setPosition("5.2.1");
            parent = currentSearchTree;
            if(getBit(dataItem.getKey(), bitPos) == 0)
            {
                setPosition("5.2.1.1");
                currentPath.add(true);
                currentSearchTree = currentSearchTree.getLeft();
                isFollowedUpLink = new Boolean(currentSearchTree.getBit() <= parent.getBit());
                setPosition("5.2.1.2");
            } else
            {
                setPosition("5.2.1.1");
                setPosition("5.2.1.3");
                currentPath.add(false);
                currentSearchTree = currentSearchTree.getRight();
                isFollowedUpLink = new Boolean(currentSearchTree.getBit() <= parent.getBit());
                setPosition("5.2.1.4");
            }
            bitPos = currentSearchTree.getBit();
            dataItemBitArray.unHighlight();
            if(bitPos >= 0)
                dataItemBitArray.highlightBit(bitPos);
        }

        setPosition("5.2.1");
        currentSearchTree.getDataItem().getNode().highlight();
        setPosition("5.2.2");
        isFollowedUpLink = null;
        if(currentSearchTree.getDataItem().getKey() == dataItem.getKey())
        {
            setPosition("5.3.1");
            return true;
        } else
        {
            setPosition("5.3.1");
            setPosition("5.3.3");
            return false;
        }
    }

    public Node getLastNode()
    {
        if(currentSearchTree != null)
            return currentSearchTree.getBody();
        else
            return null;
    }

    public Boolean getIsHighlightLoop()
    {
        return isHighlightLoop;
    }

    public Boolean getIsFollowedUpLink()
    {
        return isFollowedUpLink;
    }

    public Vector getAllEndNodes()
    {
        if(allEndNodes == null)
            allEndNodes = new Vector();
        allEndNodes.removeAllElements();
        findAllEndNodes(head);
        return allEndNodes;
    }

    public void findAllEndNodes(PatriciaTreeIterNode tree)
    {
        Node nodes[] = new Node[3];
        nodes[0] = tree.getBody();
        if(tree.getBit() >= tree.getLeft().getBit())
            nodes[1] = tree.getLeft().getBody();
        else
            findAllEndNodes(tree.getLeft());
        if(tree.getBit() >= tree.getRight().getBit())
            nodes[2] = tree.getRight().getBody();
        else
            findAllEndNodes(tree.getRight());
        if(tree.getBit() >= tree.getLeft().getBit() || tree.getBit() >= tree.getRight().getBit())
            allEndNodes.addElement(nodes);
    }

    private PatriciaTreeIterDataItem searchKey(PatriciaTreeIterNode tree, int key, byte bit)
    {
        currentSearchTree = tree;
        dataItemBitArray.unHighlight();
        if(currentPath == null)
            throw new RuntimeException("search path should have been initialised");
        isFollowedUpLink = new Boolean(tree.getBit() <= bit);
        setPosition("5.1.1");
        if(tree.getBit() <= bit)
        {
            setPosition("5.1.1.1");
            isFollowedUpLink = null;
            return tree.getDataItem();
        }
        dataItemBitArray.highlightBit(tree.getBit());
        setPosition("5.1.2");
        isFollowedUpLink = null;
        setPosition("5.1.3");
        if(getBit(key, tree.getBit()) == 0)
        {
            setPosition("5.1.3.1");
            currentPath.add(true);
            lastNode = tree;
            return searchKey(tree.getLeft(), key, tree.getBit());
        } else
        {
            setPosition("5.1.4");
            currentPath.add(false);
            lastNode = tree;
            return searchKey(tree.getRight(), key, tree.getBit());
        }
    }

    private int getBit(int number, byte bit)
    {
        return (number >> mostSignificantBit - bit) % 2;
    }

    private boolean isExpanded(String key)
    {
        return ((PatriciaTreeIterThread)algorithmThread).getWindow().isExpanded(key);
    }

    private void clearPointers()
    {
        isFollowedUpLink = null;
        currentPath = null;
        currentBit = null;
        dataItemBitArray = null;
        compareDataBitArray = null;
        if(compKeyNode != null)
        {
            compKeyNode.unHighlight();
            compKeyNode = null;
        }
        keyFoundNode = null;
        differentiatingBit = null;
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

    protected static final int BUILD_MODE = 11;
    protected static final int SEARCH_MODE = 12;
    private int executingMode;
    private int nextMode;
    private ExtendedHierarchyTree currentHierarchyTree;
    private int mostSignificantBit;
    private static final String BUILD_MODE_LABEL = Messages.getString("PatriciaTreeIter.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("PatriciaTreeIter.1");
    private ElementArray insertData;
    private ElementArray newInsertData;
    private ElementArray searchData;
    private ElementArray newSearchData;
    private DigitalElementArray dataItemBitArray;
    private DigitalElementArray compareDataBitArray;
    private Node compKeyNode;
    private Node keyFoundNode;
    private Integer searchDataValue;
    private PatriciaTreeIterNode head;
    private int insertPos;
    private int lastInsertPos;
    private boolean isBackMode;
    private PatriciaTreeIterNode savedTree;
    private PatriciaTreeIterNode startingTree;
    private PatriciaTreeIterNode lastNode;
    private PatriciaTreeIterNode currentSearchTree;
    private PatriciaTreeIterNode insertionPoint;
    private Path currentPath;
    private PatriciaTreeIterNode currentInsertTree;
    private Integer differentiatingBit;
    private Integer currentBit;
    private static Boolean isHighlightLoop;
    private Boolean isFollowedUpLink;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private static Color searchTreeColor;
    private static Color postSearchTreeColor;
    private Vector allEndNodes;

    static 
    {
        searchTreeColor = Color.orange;
        postSearchTreeColor = Color.gray;
    }
}
