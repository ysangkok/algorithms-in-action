// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PatriciaTree.java

import com.cim.AIA.*;
import java.awt.*;
import java.io.PrintStream;
import java.util.Random;
import java.util.Vector;
import localization.Messages;

public class PatriciaTree extends Algorithm
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
        final PatriciaTree this$0;

        Path(boolean isLeft)
        {
            this$0 = PatriciaTree.this;
            super();
            this.isLeft = isLeft;
        }
    }


    public PatriciaTree(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        executingMode = 11;
        nextMode = executingMode;
        mostSignificantBit = 0;
        insertData = new ElementArray(0, 0);
        insertData.setColumGap(0);
        insertData.setElementWidth(20);
        for(int i = 0; i < ((int[])(int[])data).length; i++)
        {
            insertData.add(new Node(new Integer(((int[])(int[])data)[i]), i), i);
            ((Node)insertData.getElement(i)).setBackgroundColor(PatriciaTreeColors.INSERT_ACTIVE_COLOR);
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
            ((Node)searchData.getElement(i)).setBackgroundColor(PatriciaTreeColors.SEARCH_ACTIVE_COLOR);
        }

        head = new PatriciaNode();
        head.setLeft(head);
        head.setRight(head);
    }

    public HierarchyTree buildHierarchyTree(PatriciaNode current, Color currentColor)
    {
        current.getBody().setRingColor(PatriciaTreeColors.DEFAULT_RING_COLOR);
        current.getBody().setTextColor(PatriciaTreeColors.DEFAULT_TEXT_COLOR);
        HierarchyTree hierarchyTree = new HierarchyTree();
        hierarchyTree.getLine().showArrowHead(false);
        hierarchyTree.add(current.getBody());
        hierarchyTree.add(current.getDataItem().getNode());
        current.setHierarchyTree(hierarchyTree);
        if(current.getLeft().getBit() > current.getBit())
            hierarchyTree.addChild(buildHierarchyTree(current.getLeft(), currentColor));
        else
        if(current.getRight().getBit() > current.getBit())
        {
            HierarchyTree temp = new HierarchyTree();
            Node tempNode = new Node("", 0);
            tempNode.setIsVisible(false);
            temp.add(tempNode);
            hierarchyTree.addChild(temp);
        } else
        {
            HierarchyTree temp = new HierarchyTree();
            hierarchyTree.addChild(temp);
        }
        if(current.getRight().getBit() > current.getBit())
            hierarchyTree.addChild(buildHierarchyTree(current.getRight(), currentColor));
        else
        if(current.getLeft().getBit() > current.getBit())
        {
            HierarchyTree temp = new HierarchyTree();
            Node tempNode = new Node("", 0);
            tempNode.setIsVisible(false);
            temp.add(tempNode);
            hierarchyTree.addChild(temp);
        } else
        {
            HierarchyTree temp = new HierarchyTree();
            hierarchyTree.addChild(temp);
        }
        return hierarchyTree;
    }

    protected void changeData(Object data)
    {
        newInsertData = new ElementArray(0, 0);
        newInsertData.setColumGap(0);
        newInsertData.setElementWidth(20);
        for(int i = 0; i < ((int[])(int[])data).length; i++)
        {
            newInsertData.add(new Node(new Integer(((int[])(int[])data)[i]), i), i);
            ((Node)newInsertData.getElement(i)).setBackgroundColor(PatriciaTreeColors.INSERT_ACTIVE_COLOR);
        }

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

    protected void clearState()
    {
        head = new PatriciaNode();
        head.setLeft(head);
        head.setRight(head);
    }

    private void colorHierarchyTree(HierarchyTree tree, Path path)
    {
        isHighlightLoop = null;
        if(tree == null)
            return;
        tree.getLine().showAsThick(true);
        tree.setParentLineColor(PatriciaTreeColors.SEARCH_PATH_COLOR);
        if(tree.getFirstElement() != null)
        {
            tree.getFirstElement().setRingColor(PatriciaTreeColors.SEARCH_PATH_COLOR);
            tree.getFirstElement().setTextColor(PatriciaTreeColors.SEARCH_PATH_COLOR);
        }
        if(path == null)
            return;
        HierarchyTree subtree;
        if(path.isLeft)
            subtree = tree.getLeftChild();
        else
            subtree = tree.getRightChild();
        colorHierarchyTree(subtree, path.next);
        if(subtree != null && subtree.getNumberOfChildren() == 0)
            isHighlightLoop = new Boolean(path.isLeft);
    }

    public void controlBack(ControlEvent e)
    {
        if(executingMode == 11)
            clearState();
    }

    public void controlOther(ControlEvent controlevent)
    {
    }

    public void controlPause(ControlEvent controlevent)
    {
    }

    public void controlReset(ControlEvent controlevent)
    {
    }

    public void controlRestart(ControlEvent controlevent)
    {
    }

    public void controlRun(ControlEvent controlevent)
    {
    }

    public void controlStep(ControlEvent controlevent)
    {
    }

    public void findAllEndNodes(PatriciaNode tree)
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

    protected Vector generateQuestions()
    {
        return null;
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        return null;
    }

    public Vector getAllEndNodes()
    {
        if(allEndNodes == null)
            allEndNodes = new Vector();
        allEndNodes.removeAllElements();
        findAllEndNodes(head);
        return allEndNodes;
    }

    private int getBit(int number, byte bit)
    {
        return (number >> mostSignificantBit - bit) % 2;
    }

    public DigitalElementArray getCompareBitArray()
    {
        return compareDataBitArray;
    }

    public Node getCompKeyNode()
    {
        return compKeyNode;
    }

    public Integer getCurrentBit()
    {
        return currentBit;
    }

    public DigitalElementArray getDataItemBitArray()
    {
        return dataItemBitArray;
    }

    public Integer getDifferentiatingBit()
    {
        return differentiatingBit;
    }

    public PatriciaNode getHeadNode()
    {
        return head;
    }

    public HierarchyTree getHierarchyTree()
    {
        HierarchyTree hierarchyTree = buildHierarchyTree(head, PatriciaTreeColors.DEFAULT_TREE_COLOR);
        colorHierarchyTree(hierarchyTree, currentPath);
        return hierarchyTree;
    }

    public Vector getInsertedItemsArray()
    {
        Vector sortedList = new Vector();
        if(head.getLeft() != head)
            printNodes("", head.getLeft(), sortedList);
        for(int i = 0; i < sortedList.size(); i++)
            System.out.println(((PatriciaTreeInserted)sortedList.elementAt(i)).getPattern());

        return sortedList;
    }

    public ElementArray getInsertElementArray()
    {
        if(executingMode == 11)
            return insertData;
        else
            return null;
    }

    public PatriciaNode getInsertPositionNode()
    {
        return currentInsertTree;
    }

    public Boolean getIsFollowedUpLink()
    {
        return isFollowedUpLink;
    }

    public Boolean getIsHighlightLoop()
    {
        return isHighlightLoop;
    }

    public Node getKeyFoundNode()
    {
        return keyFoundNode;
    }

    public Node getLastNode()
    {
        if(lastNode != null)
            return lastNode.getBody();
        else
            return null;
    }

    public PatriciaNode getNewNode()
    {
        return PatriciaNode.getNewNode();
    }

    public ElementArray getSearchElementArray()
    {
        if(executingMode == 12)
            return searchData;
        else
            return null;
    }

    public Node getSearchPositionNode()
    {
        if(currentSearchTree != null)
            return currentSearchTree.getBody();
        else
            return null;
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    private DigitalElementArray initialiseBitElementArray(int number)
    {
        DigitalElementArray bitArray = new DigitalElementArray(number, mostSignificantBit);
        return bitArray;
    }

    protected void initialiseCanvases(String dataDir, MultiAlgorithmWindow multiAlgorithmWindow)
    {
        multiAlgorithmWindow.addCodeCanvas(dataDir, Messages.getString("PatriciaTree.44"), "3.1", "5.1hide");
        multiAlgorithmWindow.addCodeCanvas(dataDir, Messages.getString("PatriciaTree.22"), "3.2", "3.2hide");
        multiAlgorithmWindow.addCodeCanvas(dataDir, Messages.getString("PatriciaTree.44"), "5.1s", "5.1shide");
        multiAlgorithmWindow.setSize(new Dimension(350, 200), Messages.getString("PatriciaTree.44"));
        multiAlgorithmWindow.setLocation(new Point(235, 420), Messages.getString("PatriciaTree.44"));
        multiAlgorithmWindow.setLocation(new Point(235, 280), Messages.getString("PatriciaTree.22"));
        multiAlgorithmWindow.setSize(new Dimension(350, 430), Messages.getString("PatriciaTree.22"));
    }

    protected void initialiseMethods(String dataDir, MethodSelectable methodSelectable)
    {
        MethodSelection build = new MethodSelection(BUILD_MODE_LABEL, dataDir, Messages.getString("PatriciaTree.2"), "3", getLogger(), getBreakPoint());
        MethodSelection search = new MethodSelection(SEARCH_MODE_LABEL, dataDir, Messages.getString("PatriciaTree.4"), "5", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(build, true);
        methodSelectable.addMethodSelection(search, false);
        methodSelectable.addMethodSelectionListener(this);
    }

    private void insert(PatriciaNode root, PatriciaTreeDataItem dataItem)
    {
        setPosition("3");
        currentPath = new Path(true);
        dataItemBitArray = initialiseBitElementArray(dataItem.getKey());
        dataItemBitArray.setColors(PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeColors.INSERT_BIT_HIGHLIGHT_COLOR);
        setPosition("3.1.1");
        int compKey = searchKey(root.getLeft(), dataItem.getKey(), (byte)-1).getKey();
        setPosition("5.1hide");
        currentPath = null;
        if(currentSearchTree != null)
        {
            compKeyNode = currentSearchTree.getDataItem().getNode();
            compKeyNode.highlight();
        }
        currentSearchTree = null;
        setPosition("3.1.2.1");
        if(compKey == dataItem.getKey())
        {
            clearPointers();
            return;
        }
        compareDataBitArray = initialiseBitElementArray(compKey);
        compareDataBitArray.setColors(PatriciaTreeColors.DATA_ITEM_COLOR, PatriciaTreeColors.DATA_ITEM_HIGHLIGHT_COLOR);
        setPosition("3.1.2.2");
        byte bit = 0;
        dataItemBitArray.highlightBit(bit);
        compareDataBitArray.highlightBit(bit);
        setPosition("3.1.2.3");
        for(; getBit(compKey, bit) == getBit(dataItem.getKey(), bit); setPosition("3.1.2.3.1", isExpanded("3.1.2.3")))
        {
            bit++;
            dataItemBitArray.unHighlight();
            dataItemBitArray.highlightBit(bit);
            compareDataBitArray.unHighlight();
            compareDataBitArray.highlightBit(bit);
        }

        compareDataBitArray.highlightNumber(bit, PatriciaTreeColors.DIFFERENTIATING_COLOR);
        differentiatingBit = new Integer(bit);
        if(!isExpanded("3.1"))
        {
            dataItemBitArray.unHighlight();
            compareDataBitArray = null;
            compKeyNode.unHighlight();
            compKeyNode = null;
        }
        setPosition("3.1.2.4");
        if(!enabled)
        {
            clearPointers();
            return;
        }
        dataItemBitArray.unHighlight();
        compareDataBitArray = null;
        if(compKeyNode != null)
        {
            compKeyNode.unHighlight();
            compKeyNode = null;
        }
        currentPath = new Path(true);
        if(isExpanded("3.2"))
        {
            setPosition("3.2.1");
            root.setLeft(insertKey(root.getLeft(), dataItem, bit, root, true));
        } else
        {
            root.setLeft(insertKey(root.getLeft(), dataItem, bit, root, true));
            currentPath = null;
            currentInsertTree = null;
            setPosition("3.2.1");
        }
        setPosition("3.4.1.3b");
        setPosition("3.2hide");
        dataItemBitArray = null;
        currentPath = null;
        differentiatingBit = null;
        currentInsertTree = null;
    }

    private PatriciaNode insertKey(PatriciaNode tree, PatriciaTreeDataItem dataItem, byte diffBit, PatriciaNode parent, boolean isShowRecursion)
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
            PatriciaNode newNode = new PatriciaNode(dataItem, diffBit);
            setPosition("3.4.1.1.1.1", isShowRecursion);
            dataItemBitArray = initialiseBitElementArray(dataItem.getKey());
            dataItemBitArray.setColors(PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeColors.INSERT_BIT_HIGHLIGHT_COLOR);
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
        dataItemBitArray.setColors(PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeColors.INSERT_BIT_HIGHLIGHT_COLOR);
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
                PatriciaNode unkownLeft = insertKey(tree.getLeft(), dataItem, diffBit, tree, true);
                tree.setLeftIsKnown(true);
                tree.setLeft(unkownLeft);
            } else
            {
                PatriciaNode unkownLeft = insertKey(tree.getLeft(), dataItem, diffBit, tree, false);
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
                PatriciaNode unkownRight = insertKey(tree.getRight(), dataItem, diffBit, tree, true);
                tree.setRightIsKnown(true);
                tree.setRight(unkownRight);
            } else
            {
                PatriciaNode unkownRight = insertKey(tree.getRight(), dataItem, diffBit, tree, false);
                tree.setRightIsKnown(true);
                tree.setRight(unkownRight);
            }
        }
        currentInsertTree = tree;
        setPosition("3.4.1.2.1.4", isShowRecursion);
        currentBit = null;
        return tree;
    }

    private boolean isExpanded(String key)
    {
        return ((PatriciaTreeThread)algorithmThread).getWindow().isExpanded(key);
    }

    public void printNodes(String head, PatriciaNode current, Vector sortedList)
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

            System.out.println((new StringBuilder()).append(Messages.getString("PatriciaTree.26")).append(leftHead).append(Messages.getString("PatriciaTree.27")).append(current.getLeft().getDataItem().getKey()).toString());
            sortedList.addElement(new PatriciaTreeInserted(leftHead, current.getLeft().getDataItem().getKey()));
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

            System.out.println((new StringBuilder()).append(Messages.getString("PatriciaTree.32")).append(rightHead).append(Messages.getString("PatriciaTree.33")).append(current.getRight().getDataItem().getKey()).toString());
            sortedList.addElement(new PatriciaTreeInserted(rightHead, current.getRight().getDataItem().getKey()));
        }
    }

    public void processMethodSelectionEvent(MethodSelectionEvent e)
    {
        if(BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0)
        {
            System.out.println(Messages.getString("PatriciaTree.19"));
            nextMode = 11;
        } else
        if(SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0)
        {
            System.out.println(Messages.getString("PatriciaTree.20"));
            nextMode = 12;
        }
    }

    public void reInitialise(Object data)
    {
        mostSignificantBit = 0;
        insertData = newInsertData;
        for(int i = 0; i < insertData.getSize(); i++)
        {
            String binaryString = Integer.toBinaryString(((Integer)insertData.getElement(i).getObject()).intValue());
            if(mostSignificantBit < binaryString.length())
                mostSignificantBit = binaryString.length();
        }

        mostSignificantBit--;
    }

    protected void removeAllAnimationRequests()
    {
    }

    protected void removeAllQuestions()
    {
    }

    protected void run()
    {
        switch(executingMode)
        {
        default:
            break;

        case 11: // '\013'
            for(int i = 0; i < insertData.getSize(); i++)
            {
                PatriciaTreeDataItem dataItem = new PatriciaTreeDataItem(new Node(insertData.getElement(i).getObject(), i), ((Integer)insertData.getElement(i).getObject()).intValue());
                ((Node)insertData.getElement(i)).setBackgroundColor(PatriciaTreeColors.INSERT_HIGHLIGHT_COLOR);
                insert(head, dataItem);
                PatriciaNode.clearNewNode();
                ((Node)insertData.getElement(i)).setBackgroundColor(PatriciaTreeColors.INSERT_DONE_COLOR);
            }

            break;

        case 12: // '\f'
            for(int i = 0; i < searchData.getSize(); i++)
            {
                PatriciaTreeDataItem dataItem = new PatriciaTreeDataItem(new Node(searchData.getElement(i).getObject(), i), ((Integer)searchData.getElement(i).getObject()).intValue());
                ((Node)searchData.getElement(i)).setBackgroundColor(PatriciaTreeColors.SEARCH_HIGHLIGHT_COLOR);
                search(head, dataItem);
                ((Node)searchData.getElement(i)).setBackgroundColor(PatriciaTreeColors.SEARCH_DONE_COLOR);
            }

            break;
        }
    }

    protected void run(boolean canChangeMode)
    {
        if(canChangeMode)
            executingMode = nextMode;
        run();
    }

    private PatriciaTreeDataItem search(PatriciaNode tree, PatriciaTreeDataItem dataItem)
    {
        dataItemBitArray = initialiseBitElementArray(dataItem.getKey());
        dataItemBitArray.setColors(PatriciaTreeColors.SEARCH_BIT_ACTIVE_COLOR, PatriciaTreeColors.SEARCH_BIT_HIGHLIGHT_COLOR);
        currentPath = new Path(true);
        PatriciaTreeDataItem retrieved = searchKey(tree.getLeft(), dataItem.getKey(), (byte)-1);
        currentPath = null;
        currentSearchTree = null;
        keyFoundNode = retrieved.getNode();
        dataItemBitArray = null;
        keyFoundNode = null;
        if(dataItem.getKey() == retrieved.getKey())
            return retrieved;
        else
            return null;
    }

    private PatriciaTreeDataItem searchKey(PatriciaNode tree, int key, byte bit)
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

    private void setPosition(String key, boolean isPause)
    {
        if(isPause)
            setPosition(key);
        else
            setPosition("This key doesn't exist");
    }

    protected void storeState(boolean flag)
    {
    }

    protected static final int BUILD_MODE = 11;
    protected static final int SEARCH_MODE = 12;
    private static final String BUILD_MODE_LABEL = Messages.getString("PatriciaTree.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("PatriciaTree.1");
    private static Boolean isHighlightLoop;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private int executingMode;
    private int nextMode;
    private int mostSignificantBit;
    private ElementArray insertData;
    private ElementArray newInsertData;
    private ElementArray searchData;
    private DigitalElementArray dataItemBitArray;
    private DigitalElementArray compareDataBitArray;
    private Node compKeyNode;
    private Node keyFoundNode;
    private PatriciaNode head;
    private PatriciaNode lastNode;
    private PatriciaNode currentSearchTree;
    private Path currentPath;
    private PatriciaNode currentInsertTree;
    private Integer differentiatingBit;
    private Integer currentBit;
    private Boolean isFollowedUpLink;
    private Vector allEndNodes;

}
