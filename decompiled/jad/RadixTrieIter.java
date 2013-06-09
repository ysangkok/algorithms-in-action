// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieIter.java

import com.cim.AIA.*;
import java.io.PrintStream;
import java.util.Random;
import java.util.Vector;
import localization.Messages;

public class RadixTrieIter extends Algorithm
    implements MethodSelectionListener, ControlListener
{

    public RadixTrieIter(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        isBackMode = false;
        savedTree = new RadixTrieIterNullNode(null);
        head = new RadixTrieIterNullNode(null);
        mostSignificantBit = 0;
        currentLevel = -1;
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
            savedTree = new RadixTrieIterNullNode(null);
            searchPos = 0;
            insertPos = 0;
        } else
        if(executingMode == 12)
            searchPos = 0;
        head = savedTree;
        insertedData = new Vector();
        radixTrieToVector(insertedData, head);
        currentLevel = -1;
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
            ((Node)newInsertData.getElement(i)).setBackgroundColor(RadixTrieIterColors.INSERT_ACTIVE_COLOR);
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
            ((Node)newSearchData.getElement(i)).setBackgroundColor(RadixTrieIterColors.SEARCH_ACTIVE_COLOR);
        }

    }

    protected void initialiseMethods(String string, MethodSelectable methodSelectable)
    {
        MethodSelection methodSelection1 = new MethodSelection(BUILD_MODE_LABEL, string, Messages.getString("RadixTrieIter.2"), "3a", getLogger(), getBreakPoint());
        MethodSelection methodSelection2 = new MethodSelection(SEARCH_MODE_LABEL, string, Messages.getString("RadixTrieIter.3"), "5a", getLogger(), getBreakPoint());
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

    private RadixTrieIterDigitalElementArray initialiseBitElementArray(int number)
    {
        return new RadixTrieIterDigitalElementArray(number, mostSignificantBit);
    }

    public RadixTrieIterNode getHead()
    {
        return head;
    }

    public Vector getInsertedData()
    {
        return insertedData;
    }

    public RadixTrieIterDigitalElementArray getInsertBitArray()
    {
        if(executingMode == 11)
            return insertDataBitArray;
        else
            return null;
    }

    public RadixTrieIterDigitalElementArray getSearchBitArray()
    {
        if(executingMode == 12)
            return searchDataBitArray;
        else
            return null;
    }

    public RadixTrieIterDigitalElementArray getFinalCompareBitArray()
    {
        if(executingMode == 12)
            return finalCompareDataBitArray;
        else
            return null;
    }

    public RadixTrieIterDigitalElementArray getCompareBitArray()
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

    public RadixTrieIterNode getCurrentNode()
    {
        unHighlightPath(head);
        highlightPath(pathNode);
        return currentNode;
    }

    public int getMostSignificantBit()
    {
        return mostSignificantBit;
    }

    public int getCurrentLevel()
    {
        return currentLevel;
    }

    private void unHighlightPath(RadixTrieIterNode node)
    {
        if(node != null)
        {
            node.setIsOnPath(false);
            if(node instanceof RadixTrieIterInternalNode)
            {
                unHighlightPath(((RadixTrieIterInternalNode)node).getLeft());
                unHighlightPath(((RadixTrieIterInternalNode)node).getRight());
            }
        }
    }

    private void highlightPath(RadixTrieIterNode node)
    {
        if(node != null)
        {
            node.setIsOnPath(true);
            highlightPath(((RadixTrieIterNode) (node.getParent())));
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

    private void printTrie(RadixTrieIterNode trie)
    {
        if(trie instanceof RadixTrieIterInternalNode)
        {
            printTrie(((RadixTrieIterInternalNode)trie).getLeft());
            printTrie(((RadixTrieIterInternalNode)trie).getRight());
        }
        if(trie instanceof RadixTrieIterExternalNode)
            System.out.println((new StringBuilder()).append(((RadixTrieIterExternalNode)trie).getKey()).append(" ").append(Integer.toBinaryString(((RadixTrieIterExternalNode)trie).getKey())).toString());
    }

    private void radixTrieToVector(Vector vector, RadixTrieIterNode node)
    {
        if(node instanceof RadixTrieIterInternalNode)
        {
            radixTrieToVector(vector, ((RadixTrieIterInternalNode)node).getLeft());
            radixTrieToVector(vector, ((RadixTrieIterInternalNode)node).getRight());
        } else
        if(node instanceof RadixTrieIterExternalNode)
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
                DataItem dataItem = new DataItem(insertData.getElement(i).getObject().toString(), ((Integer)insertData.getElement(i).getObject()).intValue());
                insertDataBitArray = initialiseBitElementArray(dataItem.getKey());
                insertDataBitArray.setColors(RadixTrieIterColors.INSERT_BIT_ACTIVE_COLOR, RadixTrieIterColors.INSERT_BIT_HIGHLIGHT_COLOR);
                try
                {
                    if(enabled)
                    {
                        ((Node)insertData.getElement(i)).setBackgroundColor(RadixTrieIterColors.INSERT_HIGHLIGHT_COLOR);
                        head = insert(head, dataItem, 0, true, i);
                        clearPointers();
                        RadixTrieIterExternalNode.clearNewestNode();
                        RadixTrieIterExternalNode.clearCompareNode();
                        insertedData = new Vector();
                        radixTrieToVector(insertedData, head);
                        insertPos++;
                        ((Node)insertData.getElement(i)).setBackgroundColor(RadixTrieIterColors.INSERT_DONE_COLOR);
                    }
                }
                catch(Exception e)
                {
                    clearPointers();
                    System.out.println((new StringBuilder()).append(e).append("Already inserted exception caught").toString());
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
                DataItem dataItem = new DataItem(searchNode.getObject().toString(), ((Integer)searchNode.getObject()).intValue());
                searchDataBitArray = initialiseBitElementArray(dataItem.getKey());
                searchDataBitArray.setColors(RadixTrieIterColors.SEARCH_BIT_ACTIVE_COLOR, RadixTrieIterColors.SEARCH_BIT_HIGHLIGHT_COLOR);
                if(enabled)
                {
                    searchNode.setBackgroundColor(RadixTrieIterColors.SEARCH_HIGHLIGHT_COLOR);
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
                    RadixTrieIterExternalNode.clearCompareNode();
                    insertedData = new Vector();
                    radixTrieToVector(insertedData, head);
                    searchNode.setBackgroundColor(RadixTrieIterColors.SEARCH_DONE_COLOR);
                    searchPos++;
                }
                searchDataBitArray = null;
                i++;
            } while(true);
        }
    }

    private RadixTrieIterNode insert(RadixTrieIterNode trie, DataItem dataItem, int level, boolean isShowRecursion, int index)
        throws Exception
    {
        currentNode = trie;
        pathNode = currentNode;
        setPosition("3.0");
        if(trie == null)
            throw new RuntimeException("trie should not be null");
        setPosition("3.1.1");
        if(trie instanceof RadixTrieIterNullNode)
        {
            RadixTrieIterExternalNode newNode = new RadixTrieIterExternalNode(dataItem);
            newNode.setIsLinkedIn();
            newNode.setLevel(0);
            pathNode = newNode;
            currentNode = head = newNode;
            setPosition("3.1.1.1");
            return newNode;
        }
        currentLevel = 0;
        insertDataBitArray.unHighlight();
        insertDataBitArray.highlightBit((byte)currentLevel);
        setPosition("3.2.2");
        while(currentNode instanceof RadixTrieIterInternalNode) 
        {
            setPosition("3.3.1");
            setPosition("3.3.1.2.1");
            if(getBit(currentLevel, dataItem.getKey()) == 0)
            {
                currentNode = ((RadixTrieIterInternalNode)currentNode).getLeft();
                pathNode = currentNode;
                setPosition("3.3.1.2.1.1");
            } else
            {
                setPosition("3.3.1.2.2");
                currentNode = ((RadixTrieIterInternalNode)currentNode).getRight();
                pathNode = currentNode;
                setPosition("3.3.1.2.2.1");
            }
            if(currentNode instanceof RadixTrieIterInternalNode)
            {
                currentLevel++;
                insertDataBitArray.unHighlight();
                insertDataBitArray.highlightBit((byte)currentLevel);
                setPosition("3.3.1.4");
            } else
            {
                currentLevel++;
                setPosition("3.3.1.4");
            }
        }
        setPosition("3.3.1");
        setPosition("3.3.2");
        pathNode = currentNode.getParent();
        setPosition("3.4.1");
        if(currentNode instanceof RadixTrieIterNullNode)
        {
            RadixTrieIterExternalNode newNode = new RadixTrieIterExternalNode(dataItem);
            if(getBit(currentLevel - 1, dataItem.getKey()) == 0)
                currentNode.getParent().setLeft(newNode);
            else
                currentNode.getParent().setRight(newNode);
            newNode.setLevel(currentLevel);
            newNode.setIsLinkedIn();
            setPosition("3.4.1.1");
            return trie;
        }
        setPosition("3.4.2");
        int trieItem = ((RadixTrieIterExternalNode)currentNode).getKey();
        if(trieItem == dataItem.getKey())
        {
            setPosition("3.4.2.1");
            setPosition("3.4.2.2");
            insertDataBitArray.addMarker(new StringMarker(Messages.getString("RadixTrieIter.4")));
            compareDataBitArray = null;
            setPosition("3.4.2.2.1");
            insertDataBitArray.removeAllMarkers();
            return trie;
        }
        setPosition("3.4.2.1");
        compareDataBitArray = initialiseBitElementArray(trieItem);
        compareDataBitArray.setColors(RadixTrieIterColors.COMPARE_BIT_ACTIVE_COLOR, RadixTrieIterColors.COMPARE_BIT_HIGHLIGHT_COLOR);
        RadixTrieIterExternalNode.setCompareNode((RadixTrieIterExternalNode)currentNode);
        RadixTrieIterInternalNode returnNode = new RadixTrieIterInternalNode();
        RadixTrieIterExternalNode trieNode = (RadixTrieIterExternalNode)currentNode;
        returnNode.setIsLinkedIn();
        if(getBit(currentLevel - 1, dataItem.getKey()) == 0)
        {
            if(currentNode.getParent() != null)
                currentNode.getParent().setLeft(returnNode);
            else
                trie = head = returnNode;
        } else
        if(currentNode.getParent() != null)
            currentNode.getParent().setRight(returnNode);
        else
            trie = head = returnNode;
        RadixTrieIterInternalNode ptr = returnNode;
        currentNode = ptr;
        compareDataBitArray.unHighlight();
        insertDataBitArray.unHighlight();
        compareDataBitArray.highlightBit((byte)currentLevel);
        insertDataBitArray.highlightBit((byte)currentLevel);
        setPosition("3.5.1");
        while(getBit(currentLevel, trieItem) == getBit(currentLevel, dataItem.getKey())) 
        {
            setPosition("3.5.2");
            if(getBit(currentLevel, dataItem.getKey()) == 0)
            {
                setPosition("3.5.2.2");
                currentLevel++;
                compareDataBitArray.unHighlight();
                insertDataBitArray.unHighlight();
                compareDataBitArray.highlightBit((byte)currentLevel);
                insertDataBitArray.highlightBit((byte)currentLevel);
                setPosition("3.5.2.2.1");
                RadixTrieIterInternalNode newInternal = new RadixTrieIterInternalNode();
                pathNode = newInternal;
                ptr.setLeft(newInternal);
                ptr = (RadixTrieIterInternalNode)ptr.getLeft();
                newInternal.setIsLinkedIn();
                currentNode = ptr;
                setPosition("3.5.2.2.2");
            } else
            {
                setPosition("3.5.2.2");
                setPosition("3.5.2.3");
                currentLevel++;
                compareDataBitArray.unHighlight();
                insertDataBitArray.unHighlight();
                compareDataBitArray.highlightBit((byte)currentLevel);
                insertDataBitArray.highlightBit((byte)currentLevel);
                setPosition("3.5.2.3.1");
                RadixTrieIterInternalNode newInternal = new RadixTrieIterInternalNode();
                pathNode = newInternal;
                ptr.setRight(newInternal);
                ptr = (RadixTrieIterInternalNode)ptr.getRight();
                newInternal.setIsLinkedIn();
                currentNode = ptr;
                setPosition("3.5.2.3.2");
            }
        }
        setPosition("3.5.2");
        setPosition("3.5.3");
        if(getBit(currentLevel, dataItem.getKey()) == 0)
        {
            setPosition("3.4.2.1.2.1");
            setPosition("3.4.2.1.2.3");
            ptr.setRight(trieNode);
            trieNode.setLevel(currentLevel + 1);
            insertedData = new Vector();
            radixTrieToVector(insertedData, head);
            setPosition("3.4.2.1.2.4");
            setPosition("3.4.2.1.3.1");
            RadixTrieIterExternalNode newExternal = new RadixTrieIterExternalNode(dataItem);
            newExternal.setLevel(currentLevel + 1);
            newExternal.setIsLinkedIn();
            ptr.setLeft(newExternal);
            insertedData = new Vector();
            radixTrieToVector(insertedData, head);
            setPosition("3.4.2.1.3.2");
            compareDataBitArray.unHighlight();
            insertDataBitArray.unHighlight();
        } else
        {
            setPosition("3.4.2.1.2.1");
            ptr.setLeft(trieNode);
            trieNode.setLevel(currentLevel + 1);
            insertedData = new Vector();
            radixTrieToVector(insertedData, head);
            setPosition("3.4.2.1.2.2");
            setPosition("3.4.2.1.3.1");
            setPosition("3.4.2.1.3.3");
            RadixTrieIterExternalNode newExternal = new RadixTrieIterExternalNode(dataItem);
            newExternal.setLevel(currentLevel + 1);
            newExternal.setIsLinkedIn();
            ptr.setRight(newExternal);
            insertedData = new Vector();
            radixTrieToVector(insertedData, head);
            setPosition("3.4.2.1.3.4");
            compareDataBitArray.unHighlight();
            insertDataBitArray.unHighlight();
        }
        setPosition("3.4.2.1.4");
        return trie;
    }

    private boolean search(RadixTrieIterNode trie, DataItem dataItem, int level)
    {
        setPosition("5");
        currentLevel = 0;
        searchDataBitArray.unHighlight();
        searchDataBitArray.highlightBit((byte)currentLevel);
        currentNode = trie;
        pathNode = currentNode;
        setPosition("5.1.1");
        while(currentNode instanceof RadixTrieIterInternalNode) 
        {
            setPosition("5.1.2");
            if(getBit(currentLevel, dataItem.getKey()) == 0)
            {
                setPosition("5.1.2.1.1");
                currentNode = ((RadixTrieIterInternalNode)currentNode).getLeft();
                pathNode = currentNode;
                setPosition("5.1.2.1.1.1");
            } else
            {
                setPosition("5.1.2.1.1");
                setPosition("5.1.2.1.2");
                currentNode = ((RadixTrieIterInternalNode)currentNode).getRight();
                pathNode = currentNode;
                setPosition("5.1.2.1.2.1");
            }
            currentLevel++;
            searchDataBitArray.unHighlight();
            searchDataBitArray.highlightBit((byte)currentLevel);
            setPosition("5.1.2.2");
        }
        setPosition("5.1.2");
        setPosition("5.1.3");
        if(currentNode instanceof RadixTrieIterExternalNode)
        {
            finalCompareDataBitArray = new RadixTrieIterDigitalElementArray(((RadixTrieIterExternalNode)currentNode).getKey(), mostSignificantBit, false);
            finalCompareDataBitArray.setColors(RadixTrieIterColors.COMPARE_BIT_HIGHLIGHT_COLOR, RadixTrieIterColors.COMPARE_BIT_HIGHLIGHT_COLOR);
        }
        setPosition("5.2");
        if((currentNode instanceof RadixTrieIterExternalNode) && ((RadixTrieIterExternalNode)currentNode).getKey() == dataItem.getKey())
        {
            setPosition("5.2.1");
            searchDataBitArray.unHighlight();
            setPosition("5.2.1.1");
            return true;
        } else
        {
            setPosition("5.2.1");
            setPosition("5.2.2");
            searchDataBitArray.unHighlight();
            setPosition("5.2.2.1");
            return false;
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
        head = new RadixTrieIterNullNode(null);
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
            setPosition(Messages.getString("RadixTrieIter.5"));
    }

    private void clearPointers()
    {
        currentNode = null;
        pathNode = currentNode;
        insertDataBitArray = null;
        searchDataBitArray = null;
        compareDataBitArray = null;
        finalCompareDataBitArray = null;
        insertedData = new Vector();
        radixTrieToVector(insertedData, head);
        currentLevel = -1;
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

    private static final String BUILD_MODE_LABEL = Messages.getString("RadixTrieIter.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("RadixTrieIter.1");
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    public boolean isBackMode;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private RadixTrieIterDigitalElementArray insertDataBitArray;
    private RadixTrieIterDigitalElementArray searchDataBitArray;
    private RadixTrieIterDigitalElementArray compareDataBitArray;
    private RadixTrieIterDigitalElementArray finalCompareDataBitArray;
    private Vector insertedData;
    private RadixTrieIterNode savedTree;
    private int insertPos;
    private int searchPos;
    private RadixTrieIterNode head;
    private ElementArray insertData;
    private ElementArray searchData;
    private ElementArray newSearchData;
    private ElementArray newInsertData;
    int mostSignificantBit;
    private RadixTrieIterNode currentNode;
    private RadixTrieIterNode pathNode;
    private int currentLevel;

}
