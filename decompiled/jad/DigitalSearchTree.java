// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigitalSearchTree.java

import com.cim.AIA.*;
import java.awt.Color;
import java.util.Random;
import java.util.Vector;
import localization.Messages;

public class DigitalSearchTree extends Algorithm
    implements MethodSelectionListener, ControlListener
{
    private class DigitalTree
    {

        DigitalTree leftChild;
        DigitalTree rightChild;
        int data;
        Node node;
        Node leftNode;
        Node rightNode;
        final DigitalSearchTree this$0;

        DigitalTree(int data, int index)
        {
            this$0 = DigitalSearchTree.this;
            super();
            leftChild = rightChild = null;
            this.data = data;
            node = new Node(new Integer(data), index);
            node.setBackgroundColor(DigitalSearchTree.TREE_HIGHLIGHT_COLOR);
            node.setTextColor(DigitalSearchTree.TEXT_COLOR);
            node.setMarkersBelowValue(false);
            node.setAdditionalMarkerSpacing(-2);
            byte shrinkFactor = 2;
            node.setRingColor(DigitalSearchTree.PATH_COLOR);
            leftNode = new Node("", 0);
            leftNode.setBackgroundColor(DigitalSearchTree.TREE_NULL_COLOR);
            leftNode.setTextColor(DigitalSearchTree.TEXT_COLOR);
            leftNode.setWidth(node.getWidth() / shrinkFactor);
            leftNode.setHeight(node.getHeight() / shrinkFactor);
            leftNode.setIsVisible(true);
            rightNode = new Node("", 0);
            rightNode.setBackgroundColor(DigitalSearchTree.TREE_NULL_COLOR);
            rightNode.setTextColor(DigitalSearchTree.TEXT_COLOR);
            rightNode.setWidth(node.getWidth() / shrinkFactor);
            rightNode.setHeight(node.getHeight() / shrinkFactor);
            rightNode.setIsVisible(true);
        }
    }


    public DigitalSearchTree(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        mostSignificantBit = 0;
        isBackMode = false;
        canStoreState = false;
        savedTree = null;
        executingMode = 11;
        nextMode = executingMode;
        newInsertData = insertData = (int[])(int[])data;
        Random random = new Random();
        searchData = new int[insertData.length];
        for(int i = 0; i < insertData.length; i++)
            if(i % 2 == 0)
                searchData[i] = Math.abs(random.nextInt()) % 98 + 1;
            else
                searchData[i] = insertData[i];

        newSearchData = searchData;
        root = null;
        initialise();
    }

    protected void setEnabled(boolean state)
    {
        enabled = state;
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

    private ExtendedHierarchyTree makeHierarchyTree(ExtendedHierarchyTree parent, DigitalTree digitalSearchTree)
    {
        if(digitalSearchTree != null)
        {
            ExtendedHierarchyTree hierarchyTree = new ExtendedHierarchyTree(parent, digitalSearchTree.node);
            if(digitalSearchTree.node == ptrNode)
                ptrTree = hierarchyTree;
            if(digitalSearchTree.leftChild != null)
            {
                hierarchyTree.addChild(makeHierarchyTree(hierarchyTree, digitalSearchTree.leftChild));
            } else
            {
                ExtendedHierarchyTree temp = new ExtendedHierarchyTree(hierarchyTree, digitalSearchTree.leftNode);
                hierarchyTree.addChild(temp);
                if(digitalSearchTree.leftNode == ptrNode)
                    ptrTree = temp;
            }
            if(digitalSearchTree.rightChild != null)
            {
                hierarchyTree.addChild(makeHierarchyTree(hierarchyTree, digitalSearchTree.rightChild));
            } else
            {
                ExtendedHierarchyTree temp = new ExtendedHierarchyTree(hierarchyTree, digitalSearchTree.rightNode);
                hierarchyTree.addChild(temp);
                if(digitalSearchTree.rightNode == ptrNode)
                    ptrTree = temp;
            }
            return hierarchyTree;
        } else
        {
            return null;
        }
    }

    private ExtendedHierarchyTree buildHierarchyTree(DigitalTree digitalSearchTree, int i)
    {
        ExtendedHierarchyTree hierarchyTree = null;
        if(digitalSearchTree != null)
        {
            hierarchyTree = new ExtendedHierarchyTree();
            hierarchyTree.add(digitalSearchTree.node);
            hierarchyTree.add(digitalSearchTree.node);
            hierarchyTree.setBorderColor(Color.white);
            if(digitalSearchTree.node == ptrNode)
                ptrTree = hierarchyTree;
            if(digitalSearchTree.leftChild != null)
            {
                hierarchyTree.addChild(buildHierarchyTree(digitalSearchTree.leftChild, 0));
            } else
            {
                ExtendedHierarchyTree temp = new ExtendedHierarchyTree(digitalSearchTree.leftNode);
                temp = new ExtendedHierarchyTree();
                temp.add(digitalSearchTree.leftNode);
                hierarchyTree.addChild(temp);
                if(digitalSearchTree.leftNode == ptrNode)
                    ptrTree = temp;
            }
            if(digitalSearchTree.rightChild != null)
            {
                hierarchyTree.addChild(buildHierarchyTree(digitalSearchTree.rightChild, 0));
            } else
            {
                ExtendedHierarchyTree temp = new ExtendedHierarchyTree(digitalSearchTree.rightNode);
                temp = new ExtendedHierarchyTree();
                temp.add(digitalSearchTree.rightNode);
                hierarchyTree.addChild(temp);
                if(digitalSearchTree.rightNode == ptrNode)
                    ptrTree = temp;
                hierarchyTree.addChild(temp);
            }
        }
        return hierarchyTree;
    }

    public MultipleTween generateTweens(Paintable paintable, Object object, int i)
    {
        return null;
    }

    public ExtendedHierarchyTree getHierarchyTree()
    {
        ptrTree = null;
        ExtendedHierarchyTree tree = buildHierarchyTree(root, 0);
        ExtendedHierarchyTree test = new ExtendedHierarchyTree();
        test = makeHierarchyTree(test, root);
        colorHierarchyTree(ptrTree);
        return test;
    }

    public ElementArray getInsertElementArray()
    {
        if(insertArray != null)
        {
            for(int i = 0; i < insertArray.getSize(); i++)
            {
                ((Node)insertArray.getElement(i)).setBackgroundColor(insertDataColor[i]);
                ((Node)insertArray.getElement(i)).setHighlightColor(INSERT_HIGHLIGHT_COLOR);
                ((Node)insertArray.getElement(i)).setTextColor(TEXT_COLOR);
            }

        }
        return insertArray;
    }

    public ElementArray getSearchElementArray()
    {
        if(searchArray != null)
        {
            for(int i = 0; i < searchArray.getSize(); i++)
            {
                ((Node)searchArray.getElement(i)).setBackgroundColor(searchDataColor[i]);
                ((Node)searchArray.getElement(i)).setHighlightColor(SEARCH_HIGHLIGHT_COLOR);
                ((Node)searchArray.getElement(i)).setTextColor(TEXT_COLOR);
            }

        }
        return searchArray;
    }

    public ElementArray getBitElementArray()
    {
        if(bitArray != null)
        {
            for(int i = 0; i < bitArray.getSize(); i++)
            {
                ((Node)bitArray.getElement(i)).setBackgroundColor(bitDataColor[bitArray.getSize() - i - 1]);
                ((Node)bitArray.getElement(i)).setTextColor(TEXT_COLOR);
            }

        }
        return bitArray;
    }

    public Node getParentNode()
    {
        return parentNode;
    }

    public Node getPtrNode()
    {
        return ptrNode;
    }

    public Node getBitNode()
    {
        return null;
    }

    public Node getHighlightedNode()
    {
        Node highlighted = null;
        if(insertArray != null && executingMode == 11)
        {
            for(int i = 0; i < insertArray.getSize(); i++)
                if(((Node)insertArray.getElement(i)).getBackgroundColor() == INSERT_HIGHLIGHT_COLOR)
                    highlighted = (Node)insertArray.getElement(i);

        } else
        if(searchArray != null && executingMode == 12)
            highlighted = searchNode;
        return highlighted;
    }

    public boolean isBuildMode()
    {
        return executingMode == 11;
    }

    public boolean isSearchMode()
    {
        return executingMode == 12;
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    protected Vector generateQuestions()
    {
        return null;
    }

    private void initialise()
    {
        ptrNode = parentNode = null;
        resetTreeColor(root);
    }

    protected void initialiseMethods(String string, MethodSelectable methodSelectable)
    {
        MethodSelection methodSelection1 = new MethodSelection(BUILD_MODE_LABEL, string, Messages.getString("DigitalSearchTree.7"), "3.1", getLogger(), getBreakPoint());
        MethodSelection methodSelection2 = new MethodSelection(SEARCH_MODE_LABEL, string, Messages.getString("DigitalSearchTree.8"), "5", getLogger(), getBreakPoint());
        MethodSelection methodSelection3 = new MethodSelection(DELETE_MODE_LABEL, string, Messages.getString("DigitalSearchTree.9"), "7", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
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

    protected void storeState(boolean forceState)
    {
        if(canStoreState || forceState)
            save();
    }

    private void save()
    {
        savedTree = root;
    }

    protected void clearState()
    {
    }

    public void reInitialise(Object data)
    {
        insertData = newInsertData;
        if(!isBackMode)
            searchData = newSearchData;
        if(isBackMode && executingMode == 12)
            searchNode.removeAllMarkers();
        mostSignificantBit = 0;
        for(int i = 0; i < insertData.length; i++)
        {
            String binaryString = Integer.toBinaryString(insertData[i]);
            if(mostSignificantBit < binaryString.length())
                mostSignificantBit = binaryString.length();
        }

        mostSignificantBit--;
        if(executingMode == 11)
            savedTree = null;
        root = savedTree;
        insertPos = 0;
        insertDataColor = new Color[insertData.length];
        for(int i = 0; i < insertData.length; i++)
            insertDataColor[i] = INSERT_ACTIVE_COLOR;

        insertArray = new ElementArray(0, 0);
        insertArray.setColumGap(0);
        insertArray.setElementWidth(20);
        for(int i = 0; i < insertData.length; i++)
        {
            Integer nodeValue = new Integer(insertData[i]);
            Node node = new Node(nodeValue, i);
            node.setBackgroundColor(insertDataColor[i]);
            node.setHighlightColor(INSERT_HIGHLIGHT_COLOR);
            node.setTextColor(TEXT_COLOR);
            insertArray.setValue(i, node);
        }

        if(!isBackMode)
        {
            searchDataColor = new Color[searchData.length];
            for(int i = 0; i < searchData.length; i++)
                searchDataColor[i] = SEARCH_ACTIVE_COLOR;

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

        }
        initialise();
    }

    protected void changeData(Object data)
    {
        newInsertData = (int[])(int[])data;
        Random random = new Random();
        newSearchData = new int[insertData.length];
        for(int i = 0; i < insertData.length; i++)
            if(i % 2 == 0)
                newSearchData[i] = Math.abs(random.nextInt()) % 98 + 1;
            else
                newSearchData[i] = insertData[i];

        save();
        initialise();
    }

    protected void removeAllAnimationRequests()
    {
    }

    protected void removeAllQuestions()
    {
    }

    protected void makeQuestion(int j)
    {
    }

    protected void run(boolean canChangeMode)
    {
        if(canChangeMode)
            executingMode = nextMode;
        run();
    }

    protected void run()
    {
        if(!enabled)
            return;
        if(executingMode == 11)
            insert();
        else
        if(executingMode == 12)
        {
            setPosition("5.0");
            while(enabled) 
                search();
        }
    }

    protected void search()
    {
        if(root != null)
        {
            int currentBit = mostSignificantBit;
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
            bitDataColor = new Color[mostSignificantBit + 1];
            bitArray = new ElementArray(0, 0);
            bitArray.setColumGap(0);
            bitArray.setElementWidth(20);
            for(int j = mostSignificantBit; j >= 0; j--)
            {
                bitDataColor[j] = BIT_SEARCH_ACTIVE_COLOR;
                Node node = new Node(new Integer(bitValue(searchValue, j)), j);
                node.setBackgroundColor(bitDataColor[j]);
                node.setHighlightColor(BIT_SEARCH_HIGHLIGHT_COLOR);
                node.setTextColor(TEXT_COLOR);
                bitArray.setValue(mostSignificantBit - j, node);
            }

            DigitalTree ptr = root;
            ptrNode = root.node;
            setPosition("5.1.1");
            while(ptr != null) 
            {
                DigitalTree parent = ptr;
                bitDataColor[currentBit] = BIT_SEARCH_HIGHLIGHT_COLOR;
                bitNode = (Node)bitArray.getElement(mostSignificantBit - currentBit);
                setPosition("5.2.1");
                if(ptr.data == searchValue)
                {
                    resetTreeColor(root);
                    if(!enabled)
                    {
                        searchDataColor[searchNode.getIdentifier()] = SEARCH_ACTIVE_COLOR;
                        bitArray = null;
                        return;
                    } else
                    {
                        searchNode.addMarker(new StringMarker("F"));
                        searchNode.addMarker(new StringMarker("o"));
                        searchNode.addMarker(new StringMarker("u"));
                        searchNode.addMarker(new StringMarker("n"));
                        searchNode.addMarker(new StringMarker("d"));
                        searchDataColor[searchNode.getIdentifier()] = SEARCH_DONE_COLOR;
                        setPosition("5.2.1.1");
                        bitArray = null;
                        return;
                    }
                }
                setPosition("5.2.2");
                setPosition("5.2.2.1.1");
                if(bitValue(searchValue, currentBit) == 0)
                {
                    ptrNode = parent.leftNode;
                    ptr = ptr.leftChild;
                    setPosition("5.2.2.1.1.1");
                } else
                {
                    setPosition("5.2.2.1.2");
                    ptrNode = parent.rightNode;
                    ptr = parent.rightChild;
                    setPosition("5.2.2.1.2.1");
                }
                bitDataColor[currentBit] = BIT_SEARCH_ACTIVE_COLOR;
                currentBit--;
            }
            bitDataColor[currentBit + 1] = BIT_SEARCH_HIGHLIGHT_COLOR;
            resetTreeColor(root);
            if(!enabled)
            {
                searchDataColor[searchNode.getIdentifier()] = SEARCH_ACTIVE_COLOR;
                if(!isBackMode)
                    searchNode = null;
                bitArray = null;
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
            setPosition("5.3");
        }
        if(!isBackMode)
            searchNode = null;
        bitArray = null;
    }

    protected void insert()
    {
        setPosition("1");
        if(insertData.length > 0)
        {
            if(insertPos == 0)
            {
                insertDataColor[0] = INSERT_HIGHLIGHT_COLOR;
                root = new DigitalTree(insertData[0], 0);
                insertDataColor[0] = INSERT_DONE_COLOR;
                root.node.setBackgroundColor(TREE_ACTIVE_COLOR);
                root.node.setMarkersBelowValue(false);
                root.node.removeAllMarkers();
                insertPos++;
            }
            setPosition("2.0");
            setPosition("2.1");
            setPosition("2.2");
            for(int i = insertPos; i < insertData.length; i++)
                insertItem(i);

        }
    }

    protected void insertItem(int index)
    {
        int currentBit = mostSignificantBit + 1;
        setPosition("3");
        bitDataColor = new Color[mostSignificantBit + 1];
        bitArray = new ElementArray(0, 0);
        bitArray.setColumGap(0);
        bitArray.setElementWidth(20);
        for(int j = mostSignificantBit; j >= 0; j--)
        {
            bitDataColor[j] = BIT_INSERT_ACTIVE_COLOR;
            Node node = new Node(new Integer(bitValue(insertData[index], j)), j);
            node.setBackgroundColor(bitDataColor[j]);
            node.setHighlightColor(BIT_INSERT_HIGHLIGHT_COLOR);
            node.setTextColor(TEXT_COLOR);
            bitArray.setValue(mostSignificantBit - j, node);
        }

        insertDataColor[index] = INSERT_HIGHLIGHT_COLOR;
        DigitalTree parent;
        DigitalTree ptr = parent = root;
        ptrNode = root.node;
        setPosition("3.1.0.1.1");
        while(ptr != null) 
        {
            setPosition("3.1.1.1");
            parent = ptr;
            parentNode = ptrNode;
            setPosition("3.1.1.2.1");
            setPosition("3.1.1.2.10.1");
            if(insertData[index] == ptr.data)
            {
                ptrLabel = INSERTION_LABEL;
                setPosition("3.1.1.2.10.1.1");
                setPosition("3.1.1.2.10.1.2");
                insertDataColor[index] = INSERT_ACTIVE_COLOR;
                return;
            }
            if(--currentBit < mostSignificantBit)
                bitDataColor[currentBit + 1] = BIT_INSERT_ACTIVE_COLOR;
            if(!isExpanded("3.1.1"))
                bitDataColor[currentBit] = BIT_INSERT_ACTIVE_COLOR;
            else
                bitDataColor[currentBit] = BIT_INSERT_HIGHLIGHT_COLOR;
            bitNode = (Node)bitArray.getElement(mostSignificantBit - currentBit);
            setPosition("3.1.1.2.4");
            if(bitValue(insertData[index], currentBit) == 0)
            {
                ptrNode = parent.leftNode;
                ptr = ptr.leftChild;
                setPosition("3.1.1.2.2");
            } else
            {
                ptrNode = parent.rightNode;
                ptr = parent.rightChild;
                setPosition("3.1.1.2.3");
            }
        }
        setPosition("3.1.1.1");
        ptrLabel = INSERTION_LABEL;
        ElementArray tempArray = bitArray;
        Node tempNode = parentNode;
        if(!isExpanded("3.1.2"))
            parentNode = null;
        setPosition("3.1.1.3");
        parentNode = tempNode;
        bitArray = tempArray;
        if(!enabled)
        {
            ptrLabel = PTR_LABEL;
            ptrNode = null;
            bitArray = null;
            return;
        }
        bitNode = (Node)bitArray.getElement(mostSignificantBit - currentBit);
        if(!isExpanded("3.1.2"))
        {
            parentNode = null;
            bitDataColor[currentBit] = BIT_INSERT_ACTIVE_COLOR;
        } else
        {
            bitDataColor[currentBit] = BIT_INSERT_HIGHLIGHT_COLOR;
        }
        setPosition("3.1.2.1");
        if(bitValue(insertData[index], currentBit) == 0)
        {
            parent.leftNode = new Node(new Integer(insertData[index]), 0);
            parent.leftNode.setBackgroundColor(TREE_HIGHLIGHT_COLOR);
            setPosition("3.1.2.1.1");
            parent.leftChild = new DigitalTree(insertData[index], index);
            parent.leftChild.node.addMarker(new StringMarker("0 "));
            parent.leftNode = parent.leftChild.node;
        } else
        {
            setPosition("3.1.2.2");
            parent.rightNode = new Node(new Integer(insertData[index]), 0);
            parent.rightNode.setBackgroundColor(TREE_HIGHLIGHT_COLOR);
            setPosition("3.1.2.2.1");
            parent.rightChild = new DigitalTree(insertData[index], index);
            parent.rightChild.node.addMarker(new StringMarker(" 1"));
            parent.rightNode = parent.rightChild.node;
        }
        ptrLabel = PTR_LABEL;
        ptrNode = null;
        parentNode = null;
        bitNode = null;
        bitArray = null;
        resetTreeColor(root);
        insertDataColor[index] = INSERT_DONE_COLOR;
        insertPos++;
    }

    public boolean isExpanded(String key)
    {
        return ((DigitalSearchTreeThread)algorithmThread).getWindow().isExpanded(key);
    }

    private int bitValue(int number, int bitNumber)
    {
        return (number >> bitNumber) % 2;
    }

    private void resetTreeColor(DigitalTree tree)
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

    private int insertData[];
    private int newInsertData[];
    private int insertPos;
    private int searchData[];
    private int newSearchData[];
    private int mostSignificantBit;
    private Color bitDataColor[];
    private Color insertDataColor[];
    private Color searchDataColor[];
    private ElementArray bitArray;
    private ElementArray searchArray;
    private ElementArray insertArray;
    private boolean isBackMode;
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private boolean canStoreState;
    private static final Color TEXT_COLOR;
    private static final Color INSERT_HIGHLIGHT_COLOR;
    private static final Color INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
    private static final Color INSERT_DONE_COLOR = new Color(96, 127, 96);
    private static final Color BIT_INSERT_HIGHLIGHT_COLOR = new Color(200, 255, 200);
    private static final Color BIT_INSERT_ACTIVE_COLOR;
    private static final Color SEARCH_HIGHLIGHT_COLOR;
    private static final Color SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
    private static final Color SEARCH_DONE_COLOR = new Color(0, 127, 127);
    private static final Color BIT_SEARCH_HIGHLIGHT_COLOR = new Color(255, 255, 200);
    private static final Color BIT_SEARCH_ACTIVE_COLOR;
    private static final Color TREE_HIGHLIGHT_COLOR = new Color(255, 255, 0);
    private static final Color TREE_ACTIVE_COLOR = new Color(191, 191, 64);
    private static final Color TREE_DONE_COLOR = new Color(191, 191, 0);
    private static final Color TREE_RING_COLOR;
    private static final Color TREE_NULL_COLOR;
    private static final Color PATH_COLOR;
    private Node parentNode;
    private Node ptrNode;
    private Node bitNode;
    private Node searchNode;
    private ExtendedHierarchyTree ptrTree;
    private static final String BUILD_MODE_LABEL = Messages.getString("DigitalSearchTree.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("DigitalSearchTree.1");
    private static final String DELETE_MODE_LABEL = Messages.getString("DigitalSearchTree.2");
    protected static final String PARENT_LABEL = Messages.getString("DigitalSearchTree.3");
    protected static final String BIT_LABEL = Messages.getString("DigitalSearchTree.4");
    protected static final String PTR_LABEL = Messages.getString("DigitalSearchTree.5");
    protected static final String INSERTION_LABEL = Messages.getString("DigitalSearchTree.6");
    protected static String ptrLabel = PTR_LABEL;
    private int executingMode;
    private int nextMode;
    private DigitalTree root;
    private DigitalTree savedTree;

    static 
    {
        TEXT_COLOR = Color.black;
        INSERT_HIGHLIGHT_COLOR = new Color(0, 210, 0);
        BIT_INSERT_ACTIVE_COLOR = INSERT_HIGHLIGHT_COLOR;
        SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
        BIT_SEARCH_ACTIVE_COLOR = SEARCH_HIGHLIGHT_COLOR;
        TREE_RING_COLOR = Color.black;
        TREE_NULL_COLOR = Color.blue;
        PATH_COLOR = Color.red;
    }




}
