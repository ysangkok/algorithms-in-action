// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrie.java

import com.cim.AIA.*;
import java.io.PrintStream;
import java.util.Random;
import java.util.Vector;
import localization.Messages;

public class RadixTrie extends Algorithm
    implements MethodSelectionListener, ControlListener
{

    public RadixTrie(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        isBackMode = false;
        savedTree = new RadixTrieNullNode(null);
        head = new RadixTrieNullNode(null);
        mostSignificantBit = 0;
        executingMode = 11;
        nextMode = executingMode;
        changeData(data);
        insertData = newInsertData;
        searchData = newSearchData;
    }

    public void reInitialise(Object data)
    {
        changeData(data);
        mostSignificantBit = 0;
        if(executingMode == 11 || nextMode == 11 || insertData == null)
            insertData = newInsertData;
        searchData = newSearchData;
        for(int i = 0; i < insertData.getSize(); i++)
        {
            String binaryString = Integer.toBinaryString(((Integer)insertData.getElement(i).getObject()).intValue());
            if(mostSignificantBit < binaryString.length())
                mostSignificantBit = binaryString.length();
        }

        for(int i = 0; i < searchData.getSize(); i++)
        {
            String binaryString = Integer.toBinaryString(((Integer)searchData.getElement(i).getObject()).intValue());
            if(mostSignificantBit < binaryString.length())
                mostSignificantBit = binaryString.length();
        }

        mostSignificantBit--;
        if(executingMode == 11)
        {
            savedTree = new RadixTrieNullNode(null);
            insertPos = 0;
        } else
        if(executingMode == 12)
            searchPos = 0;
        head = savedTree;
    }

    protected void changeData(Object data)
    {
        Random random = new Random();
        newInsertData = new ElementArray(0, 0);
        newInsertData.setColumGap(0);
        newInsertData.setElementWidth(20);
        newSearchData = new ElementArray(0, 0);
        newSearchData.setColumGap(0);
        newSearchData.setElementWidth(20);
        for(int i = 0; i < ((int[])(int[])data).length; i++)
        {
            newInsertData.add(new Node(new Integer(((int[])(int[])data)[i]), i), i);
            ((Node)newInsertData.getElement(i)).setBackgroundColor(RadixTrieColors.INSERT_ACTIVE_COLOR);
            if(!isBackMode)
            {
                if(i % 2 == 0)
                    newSearchData.add(new Node(new Integer(Math.abs(random.nextInt()) % 98 + 1), i), i);
                else
                    newSearchData.add(new Node(new Integer(((int[])(int[])data)[i]), i), i);
            } else
            {
                Integer orgInteger = (Integer)searchData.getElement(i).getObject();
                int orgValue = orgInteger.intValue();
                newSearchData.add(new Node(new Integer(orgValue), i), i);
            }
            ((Node)newSearchData.getElement(i)).setBackgroundColor(RadixTrieColors.SEARCH_ACTIVE_COLOR);
        }

    }

    protected void initialiseMethods(String string, MethodSelectable methodSelectable)
    {
        MethodSelection methodSelection1 = new MethodSelection(BUILD_MODE_LABEL, string, Messages.getString("RadixTrie.2"), "3a", getLogger(), getBreakPoint());
        MethodSelection methodSelection2 = new MethodSelection(SEARCH_MODE_LABEL, string, Messages.getString("RadixTrie.4"), "5a", getLogger(), getBreakPoint());
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

    private RadixTrieDigitalElementArray initialiseBitElementArray(int number)
    {
        return new RadixTrieDigitalElementArray(number, mostSignificantBit);
    }

    public RadixTrieNode getHead()
    {
        return head;
    }

    public Vector getInsertedData()
    {
        return insertedData;
    }

    public RadixTrieDigitalElementArray getInsertBitArray()
    {
        if(executingMode == 11)
            return insertDataBitArray;
        else
            return null;
    }

    public RadixTrieDigitalElementArray getSearchBitArray()
    {
        if(executingMode == 12)
            return searchDataBitArray;
        else
            return null;
    }

    public RadixTrieDigitalElementArray getCompareBitArray()
    {
        return compareDataBitArray;
    }

    public ElementArray getInsertData()
    {
        if(executingMode == 11)
            return insertData;
        else
            return null;
    }

    public ElementArray getSearchData()
    {
        if(executingMode == 12)
            return searchData;
        else
            return null;
    }

    public RadixTrieNode getCurrentNode()
    {
        unHighlightPath(head);
        highlightPath(pathNode);
        return currentNode;
    }

    public int getMostSignificantBit()
    {
        return mostSignificantBit;
    }

    private void unHighlightPath(RadixTrieNode node)
    {
        if(node != null)
        {
            node.setIsOnPath(false);
            if(node instanceof RadixTrieInternalNode)
            {
                unHighlightPath(((RadixTrieInternalNode)node).getLeft());
                unHighlightPath(((RadixTrieInternalNode)node).getRight());
            }
        }
    }

    private void highlightPath(RadixTrieNode node)
    {
        if(node != null)
        {
            node.setIsOnPath(true);
            highlightPath(((RadixTrieNode) (node.getParent())));
        }
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

    private void printTrie(RadixTrieNode trie)
    {
        if(trie instanceof RadixTrieInternalNode)
        {
            printTrie(((RadixTrieInternalNode)trie).getLeft());
            printTrie(((RadixTrieInternalNode)trie).getRight());
        }
        if(trie instanceof RadixTrieExternalNode)
            System.out.println((new StringBuilder()).append(((RadixTrieExternalNode)trie).getKey()).append(" ").append(Integer.toBinaryString(((RadixTrieExternalNode)trie).getKey())).toString());
    }

    private void radixTrieToVector(Vector vector, RadixTrieNode node)
    {
        if(node instanceof RadixTrieInternalNode)
        {
            radixTrieToVector(vector, ((RadixTrieInternalNode)node).getLeft());
            radixTrieToVector(vector, ((RadixTrieInternalNode)node).getRight());
        } else
        if(node instanceof RadixTrieExternalNode)
            vector.addElement(node);
    }

    protected void run(boolean canChangeMode)
    {
        if(canChangeMode)
            executingMode = nextMode;
        run();
    }

    protected void run()
    {
label0:
        switch(executingMode)
        {
        default:
            break;

        case 11: // '\013'
            insertedData = new Vector();
            radixTrieToVector(insertedData, head);
            for(int i = insertPos; i < insertData.getSize(); i++)
            {
                RadixTrieDataItem dataItem = new RadixTrieDataItem(insertData.getElement(i).getObject().toString(), ((Integer)insertData.getElement(i).getObject()).intValue());
                insertDataBitArray = initialiseBitElementArray(dataItem.getKey());
                insertDataBitArray.setColors(RadixTrieColors.INSERT_BIT_ACTIVE_COLOR, RadixTrieColors.INSERT_BIT_HIGHLIGHT_COLOR);
                try
                {
                    if(enabled)
                    {
                        ((Node)insertData.getElement(i)).setBackgroundColor(RadixTrieColors.INSERT_HIGHLIGHT_COLOR);
                        head = insert(head, dataItem, 0, true);
                        clearPointers();
                        RadixTrieExternalNode.clearNewestNode();
                        RadixTrieExternalNode.clearCompareNode();
                        insertedData = new Vector();
                        radixTrieToVector(insertedData, head);
                        insertPos++;
                        ((Node)insertData.getElement(i)).setBackgroundColor(RadixTrieColors.INSERT_DONE_COLOR);
                    }
                }
                catch(Exception e)
                {
                    clearPointers();
                    System.out.println("Already inserted exception caught");
                }
                insertDataBitArray = null;
            }

            break;

        case 12: // '\f'
            int i = searchPos;
            do
            {
                if(i >= searchData.getSize())
                    break label0;
                Node searchNode = (Node)searchData.getElement(i);
                RadixTrieDataItem dataItem = new RadixTrieDataItem(searchNode.getObject().toString(), ((Integer)searchNode.getObject()).intValue());
                searchDataBitArray = initialiseBitElementArray(dataItem.getKey());
                searchDataBitArray.setColors(RadixTrieColors.SEARCH_BIT_ACTIVE_COLOR, RadixTrieColors.SEARCH_BIT_HIGHLIGHT_COLOR);
                if(enabled)
                {
                    searchNode.setBackgroundColor(RadixTrieColors.SEARCH_HIGHLIGHT_COLOR);
                    if(search(head, dataItem, 0))
                    {
                        searchNode.addMarker(new StringMarker("F"));
                        searchNode.addMarker(new StringMarker("o"));
                        searchNode.addMarker(new StringMarker("u"));
                        searchNode.addMarker(new StringMarker("n"));
                        searchNode.addMarker(new StringMarker("d"));
                    } else
                    {
                        searchNode.addMarker(new StringMarker("N"));
                        searchNode.addMarker(new StringMarker("o"));
                        searchNode.addMarker(new StringMarker("t"));
                        searchNode.addMarker(new StringMarker(" "));
                        searchNode.addMarker(new StringMarker("F"));
                        searchNode.addMarker(new StringMarker("o"));
                        searchNode.addMarker(new StringMarker("u"));
                        searchNode.addMarker(new StringMarker("n"));
                        searchNode.addMarker(new StringMarker("d"));
                    }
                    clearPointers();
                    RadixTrieExternalNode.clearCompareNode();
                    insertedData = new Vector();
                    radixTrieToVector(insertedData, head);
                    searchNode.setBackgroundColor(RadixTrieColors.SEARCH_DONE_COLOR);
                    searchPos++;
                }
                searchDataBitArray = null;
                i++;
            } while(true);
        }
    }

    private RadixTrieNode insert(RadixTrieNode trie, RadixTrieDataItem dataItem, int level, boolean isShowRecursion)
        throws Exception
    {
        currentNode = trie;
        pathNode = currentNode;
        setPosition("3.1", isShowRecursion);
        if(trie == null)
            throw new RuntimeException("trie should not be null");
        if(trie instanceof RadixTrieNullNode)
        {
            setPosition("3.1.1", isShowRecursion);
            RadixTrieExternalNode newNode = new RadixTrieExternalNode(dataItem);
            newNode.setIsLinkedIn();
            newNode.setLevel(level);
            pathNode = newNode;
            return newNode;
        }
        setPosition("3.2", isShowRecursion);
        if(trie instanceof RadixTrieExternalNode)
        {
            compareDataBitArray = initialiseBitElementArray(((RadixTrieExternalNode)trie).getKey());
            compareDataBitArray.setColors(RadixTrieColors.COMPARE_BIT_ACTIVE_COLOR, RadixTrieColors.COMPARE_BIT_HIGHLIGHT_COLOR);
            RadixTrieExternalNode.setCompareNode((RadixTrieExternalNode)trie);
            setPosition("3.2.1.1", isShowRecursion);
            if(((RadixTrieExternalNode)trie).getKey() == dataItem.getKey())
            {
                setPosition("3.2.1.1.1", isShowRecursion);
                compareDataBitArray = null;
                throw new Exception("Item already in trie");
            }
            setPosition("3.2.2", isShowRecursion);
            setPosition("3.2.2.2", isShowRecursion);
            setPosition("3.2.2.1", isShowRecursion);
            RadixTrieInternalNode newNode = new RadixTrieInternalNode();
            if(trie.getParent() != null)
            {
                if(trie.getParent().getLeft() == trie)
                    trie.getParent().setLeft(newNode);
                if(trie.getParent().getRight() == trie)
                    trie.getParent().setRight(newNode);
            } else
            if(trie == head)
                head = newNode;
            else
                throw new RuntimeException("This case should be unreachable");
            currentNode = newNode;
            pathNode = currentNode;
            RadixTrieNode newInternalNode = makeInternalNode(dataItem, (RadixTrieExternalNode)trie, level, true, newNode);
            setPosition("3.2.2.3", isShowRecursion);
            compareDataBitArray = null;
            newInternalNode.setIsLinkedIn();
            return newInternalNode;
        }
        insertDataBitArray.highlightBit((byte)level);
        setPosition("3.3", isShowRecursion);
        if(trie instanceof RadixTrieInternalNode)
        {
            setPosition("3.3.1", isShowRecursion);
            setPosition("3.3.1.1", isShowRecursion);
            if(getBit(level, dataItem.getKey()) == 0)
            {
                setPosition("3.3.1.1.1", isShowRecursion);
                insertDataBitArray.unHighlight();
                ((RadixTrieInternalNode)trie).setLeft(insert(((RadixTrieInternalNode)trie).getLeft(), dataItem, level + 1, isShowRecursion));
            } else
            {
                setPosition("3.3.1.2", isShowRecursion);
                setPosition("3.3.1.2.1", isShowRecursion);
                insertDataBitArray.unHighlight();
                ((RadixTrieInternalNode)trie).setRight(insert(((RadixTrieInternalNode)trie).getRight(), dataItem, level + 1, isShowRecursion));
            }
        }
        insertedData = new Vector();
        radixTrieToVector(insertedData, head);
        currentNode = trie;
        setPosition("3.4", isShowRecursion);
        return trie;
    }

    private RadixTrieNode makeInternalNode(RadixTrieDataItem dataItem, RadixTrieExternalNode trie, int level, boolean isShowRecursion, RadixTrieInternalNode newTrie)
    {
        compareDataBitArray.unHighlight();
        insertDataBitArray.unHighlight();
        compareDataBitArray.highlightBit((byte)level);
        insertDataBitArray.highlightBit((byte)level);
        setPosition("1111", isShowRecursion);
        setPosition("3.2.2.1.1");
        if(getBit(level, trie.getKey()) != getBit(level, dataItem.getKey()))
        {
            setPosition("3.2.2.1.1.2.1", isShowRecursion);
            if(getBit(level, trie.getKey()) == 0)
            {
                newTrie.setLeft(trie);
                trie.setLevel(level + 1);
                insertedData = new Vector();
                radixTrieToVector(insertedData, head);
                setPosition("3.2.2.1.1.2.1.1.1", isShowRecursion);
                RadixTrieExternalNode newExternalNode = new RadixTrieExternalNode(dataItem);
                newExternalNode.setLevel(level + 1);
                newTrie.setRight(newExternalNode);
                insertedData = new Vector();
                radixTrieToVector(insertedData, head);
                setPosition("3.2.2.1.1.2.1.1.2", isShowRecursion);
                compareDataBitArray.unHighlight();
                insertDataBitArray.unHighlight();
                newExternalNode.setIsLinkedIn();
                setPosition("3.2.2.1.3");
                newTrie.setIsLinkedIn();
                currentNode = newTrie.getParent();
                return newTrie;
            }
            setPosition("3.2.2.1.1.2.2", isShowRecursion);
            if(getBit(level, trie.getKey()) == 1)
            {
                RadixTrieExternalNode newExternalNode = new RadixTrieExternalNode(dataItem);
                newExternalNode.setLevel(level + 1);
                newExternalNode.setIsLinkedIn();
                newTrie.setLeft(newExternalNode);
                insertedData = new Vector();
                radixTrieToVector(insertedData, head);
                setPosition("3.2.2.1.1.2.2.1.1", isShowRecursion);
                newTrie.setRight(trie);
                trie.setLevel(level + 1);
                insertedData = new Vector();
                radixTrieToVector(insertedData, head);
                setPosition("3.2.2.1.1.2.2.1.2", isShowRecursion);
                compareDataBitArray.unHighlight();
                insertDataBitArray.unHighlight();
                setPosition("3.2.2.1.3");
                newTrie.setIsLinkedIn();
                currentNode = newTrie.getParent();
                return newTrie;
            }
        } else
        {
            RadixTrieInternalNode brandNewTrie = new RadixTrieInternalNode();
            setPosition("3.2.2.1.2", isShowRecursion);
            setPosition("3.2.2.1.2.1.1", isShowRecursion);
            if(getBit(level, trie.getKey()) == 0)
            {
                setPosition("3.2.2.1.2.1.1.1.1", isShowRecursion);
                currentNode = brandNewTrie;
                pathNode = currentNode;
                newTrie.setLeft(brandNewTrie);
                newTrie.setLeft(makeInternalNode(dataItem, trie, level + 1, isShowRecursion, brandNewTrie));
            }
            if(getBit(level, trie.getKey()) == 1)
            {
                setPosition("3.2.2.1.2.1.2", isShowRecursion);
                setPosition("3.2.2.1.2.1.2.1.1", isShowRecursion);
                currentNode = brandNewTrie;
                pathNode = currentNode;
                newTrie.setRight(brandNewTrie);
                newTrie.setRight(makeInternalNode(dataItem, trie, level + 1, isShowRecursion, brandNewTrie));
            }
        }
        compareDataBitArray.unHighlight();
        insertDataBitArray.unHighlight();
        setPosition("3.2.2.1.3");
        newTrie.setIsLinkedIn();
        currentNode = newTrie.getParent();
        return newTrie;
    }

    private boolean search(RadixTrieNode trie, RadixTrieDataItem dataItem, int level)
    {
        searchDataBitArray.unHighlight();
        searchDataBitArray.highlightBit((byte)level);
        currentNode = trie;
        pathNode = currentNode;
        setPosition("5");
        setPosition("5.1");
        if(trie instanceof RadixTrieNullNode)
            return false;
        setPosition("5.2");
        if(trie instanceof RadixTrieExternalNode)
        {
            setPosition("5.2.1");
            if(((RadixTrieExternalNode)trie).getKey() == dataItem.getKey())
            {
                setPosition("5.2.1.1");
                searchDataBitArray.unHighlight();
                return true;
            } else
            {
                setPosition("5.2.2");
                searchDataBitArray.unHighlight();
                return false;
            }
        }
        setPosition("5.3");
        if(getBit(level, dataItem.getKey()) == 0)
        {
            setPosition("5.3.1");
            return search(((RadixTrieInternalNode)trie).getLeft(), dataItem, level + 1);
        } else
        {
            setPosition("5.4");
            setPosition("5.4.1");
            return search(((RadixTrieInternalNode)trie).getRight(), dataItem, level + 1);
        }
    }

    private void save()
    {
        savedTree = head;
    }

    protected void storeState(boolean forceState)
    {
        save();
    }

    protected void clearState()
    {
        head = new RadixTrieNullNode(null);
    }

    private byte getBit(int bitNumber, int number)
    {
        return (byte)((number >> mostSignificantBit - bitNumber) % 2);
    }

    private void setPosition(String string, boolean isPause)
    {
        if(isPause)
            setPosition(string);
        else
            setPosition("This key does not exist");
    }

    private void clearPointers()
    {
        currentNode = null;
        pathNode = currentNode;
        insertDataBitArray = null;
        searchDataBitArray = null;
        compareDataBitArray = null;
        insertedData = new Vector();
        radixTrieToVector(insertedData, head);
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

    private static final String BUILD_MODE_LABEL = Messages.getString("RadixTrie.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("RadixTrie.1");
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    public boolean isBackMode;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private RadixTrieDigitalElementArray insertDataBitArray;
    private RadixTrieDigitalElementArray searchDataBitArray;
    private RadixTrieDigitalElementArray compareDataBitArray;
    private Vector insertedData;
    private RadixTrieNode savedTree;
    private int insertPos;
    private int searchPos;
    private RadixTrieNode head;
    private ElementArray insertData;
    private ElementArray searchData;
    private ElementArray newSearchData;
    private ElementArray newInsertData;
    int mostSignificantBit;
    private RadixTrieNode currentNode;
    private RadixTrieNode pathNode;

}
