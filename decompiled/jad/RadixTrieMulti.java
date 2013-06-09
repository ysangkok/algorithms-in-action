// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RadixTrieMulti.java

import com.cim.AIA.*;
import com.cim.common.RadioMenu;
import java.io.PrintStream;
import java.util.Random;
import java.util.Vector;
import localization.Messages;

public class RadixTrieMulti extends Algorithm
    implements MethodSelectionListener, ControlListener
{

    public RadixTrieMulti(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        isBackMode = false;
        savedTree = new RadixTrieMultiNullNode(null);
        head = new RadixTrieMultiNullNode(null);
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

        int tempC = mostSignificantBit / NO_OF_BITS;
        if(mostSignificantBit % NO_OF_BITS > 0)
            tempC++;
        mostSignificantBit = tempC * NO_OF_BITS;
        mostSignificantBit--;
        if(executingMode == 11 || nextMode == 11)
        {
            savedTree = new RadixTrieMultiNullNode(null);
            insertPos = 0;
            searchPos = 0;
        } else
        if(executingMode == 12 || nextMode == 12)
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
            ((Node)newInsertData.getElement(i)).setBackgroundColor(RadixTrieMultiColors.INSERT_ACTIVE_COLOR);
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
            ((Node)newSearchData.getElement(i)).setBackgroundColor(RadixTrieMultiColors.SEARCH_ACTIVE_COLOR);
        }

    }

    protected void initialiseMethods(String string, MethodSelectable methodSelectable)
    {
        MethodSelection methodSelection1 = new MethodSelection(BUILD_MODE_LABEL, string, Messages.getString("RadixTrieMulti.2"), "3a", getLogger(), getBreakPoint());
        MethodSelection methodSelection2 = new MethodSelection(SEARCH_MODE_LABEL, string, Messages.getString("RadixTrieMulti.3"), "5a", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelectionListener(this);
    }

    public void processMethodSelectionEvent(MethodSelectionEvent e)
    {
        if(BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0)
        {
            nextMode = 11;
            mnuLink.setEnabled(true);
        } else
        if(SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0)
        {
            nextMode = 12;
            mnuLink.setEnabled(false);
        }
    }

    private RadixTrieMultiDigitalElementArray initialiseBitElementArray(int number)
    {
        return new RadixTrieMultiDigitalElementArray(number, mostSignificantBit);
    }

    public RadixTrieMultiNode getHead()
    {
        return head;
    }

    public Vector getInsertedData()
    {
        return insertedData;
    }

    public RadixTrieMultiDigitalElementArray getInsertBitArray()
    {
        if(executingMode == 11)
            return insertDataBitArray;
        else
            return null;
    }

    public RadixTrieMultiDigitalElementArray getSearchBitArray()
    {
        if(executingMode == 12)
            return searchDataBitArray;
        else
            return null;
    }

    public RadixTrieMultiDigitalElementArray getFinalCompareBitArray()
    {
        if(executingMode == 12)
            return finalCompareDataBitArray;
        else
            return null;
    }

    public RadixTrieMultiDigitalElementArray getCompareBitArray()
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

    public RadixTrieMultiNode getCurrentNode()
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

    private void unHighlightPath(RadixTrieMultiNode node)
    {
        if(node != null)
        {
            node.setIsOnPath(false);
            if(node instanceof RadixTrieMultiInternalNode)
            {
                for(int i = 0; i < NO_OF_NODES; i++)
                    unHighlightPath(((RadixTrieMultiInternalNode)node).getChild(i));

            }
        }
    }

    private void highlightPath(RadixTrieMultiNode node)
    {
        if(node != null)
        {
            node.setIsOnPath(true);
            highlightPath(((RadixTrieMultiNode) (node.getParent())));
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

    private void printTrie(RadixTrieMultiNode trie)
    {
        if(trie instanceof RadixTrieMultiInternalNode)
        {
            for(int i = 0; i < NO_OF_NODES; i++)
                printTrie(((RadixTrieMultiInternalNode)trie).getChild(i));

        }
        if(trie instanceof RadixTrieMultiExternalNode)
            System.out.println((new StringBuilder()).append(((RadixTrieMultiExternalNode)trie).getKey()).append(" ").append(Integer.toBinaryString(((RadixTrieMultiExternalNode)trie).getKey())).toString());
    }

    private void radixTrieToVector(Vector vector, RadixTrieMultiNode node)
    {
        if(node instanceof RadixTrieMultiInternalNode)
        {
            for(int i = 0; i < NO_OF_NODES; i++)
                radixTrieToVector(vector, ((RadixTrieMultiInternalNode)node).getChild(i));

        } else
        if(node instanceof RadixTrieMultiExternalNode)
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
                insertDataBitArray.setColors(RadixTrieMultiColors.INSERT_BIT_ACTIVE_COLOR, RadixTrieMultiColors.INSERT_BIT_HIGHLIGHT_COLOR);
                try
                {
                    if(enabled)
                    {
                        ((Node)insertData.getElement(i)).setBackgroundColor(RadixTrieMultiColors.INSERT_HIGHLIGHT_COLOR);
                        head = insert(head, dataItem, 0, true, i);
                        clearPointers();
                        RadixTrieMultiExternalNode.clearNewestNode();
                        RadixTrieMultiExternalNode.clearCompareNode();
                        insertedData = new Vector();
                        radixTrieToVector(insertedData, head);
                        insertPos++;
                        ((Node)insertData.getElement(i)).setBackgroundColor(RadixTrieMultiColors.INSERT_DONE_COLOR);
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
                searchDataBitArray.setColors(RadixTrieMultiColors.SEARCH_BIT_ACTIVE_COLOR, RadixTrieMultiColors.SEARCH_BIT_HIGHLIGHT_COLOR);
                if(enabled)
                {
                    searchNode.setBackgroundColor(RadixTrieMultiColors.SEARCH_HIGHLIGHT_COLOR);
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
                    RadixTrieMultiExternalNode.clearCompareNode();
                    insertedData = new Vector();
                    radixTrieToVector(insertedData, head);
                    searchNode.setBackgroundColor(RadixTrieMultiColors.SEARCH_DONE_COLOR);
                    searchPos++;
                }
                searchDataBitArray = null;
                i++;
            } while(true);
        }
    }

    private RadixTrieMultiNode insert(RadixTrieMultiNode trie, DataItem dataItem, int level, boolean isShowRecursion, int index)
        throws Exception
    {
        currentNode = trie;
        pathNode = currentNode;
        setPosition("3.0");
        if(trie == null)
            throw new RuntimeException("trie should not be null");
        setPosition("3.1.1");
        if(trie instanceof RadixTrieMultiNullNode)
        {
            RadixTrieMultiExternalNode newNode = new RadixTrieMultiExternalNode(dataItem);
            newNode.setIsLinkedIn();
            newNode.setLevel(0);
            pathNode = newNode;
            currentNode = head = newNode;
            setPosition("3.1.1.1");
            return newNode;
        }
        currentLevel = 0;
        insertDataBitArray.unHighlight();
        for(int i = 0; i < NO_OF_BITS; i++)
            insertDataBitArray.highlightBit((byte)(currentLevel + i));

        setPosition("3.2.2");
        int branch;
        while(currentNode instanceof RadixTrieMultiInternalNode) 
        {
            setPosition("3.3.1");
            branch = getBranch(currentLevel, dataItem.getKey());
            setPosition("3.3.1.2.1");
            currentNode = ((RadixTrieMultiInternalNode)currentNode).getChild(branch);
            pathNode = currentNode;
            setPosition("3.3.1.2.2");
            if(currentNode instanceof RadixTrieMultiInternalNode)
            {
                currentLevel = currentLevel + NO_OF_BITS;
                insertDataBitArray.unHighlight();
                for(int i = 0; i < NO_OF_BITS; i++)
                    insertDataBitArray.highlightBit((byte)(currentLevel + i));

                setPosition("3.3.1.3");
            } else
            {
                currentLevel = currentLevel + NO_OF_BITS;
                setPosition("3.3.1.3");
            }
        }
        setPosition("3.3.1");
        setPosition("3.3.2");
        pathNode = currentNode.getParent();
        if(currentNode instanceof RadixTrieMultiNullNode)
        {
            setPosition("3.4.1");
            RadixTrieMultiExternalNode newNode = new RadixTrieMultiExternalNode(dataItem);
            branch = getBranch(currentLevel - NO_OF_BITS, dataItem.getKey());
            currentNode.getParent().setChild(newNode, branch);
            newNode.setLevel(currentLevel);
            newNode.setIsLinkedIn();
            setPosition("3.4.1.1");
            return trie;
        }
        setPosition("3.4.1");
        setPosition("3.4.2");
        int trieItem = ((RadixTrieMultiExternalNode)currentNode).getKey();
        if(trieItem == dataItem.getKey())
        {
            setPosition("3.4.2.1");
            setPosition("3.4.2.2");
            insertDataBitArray.addMarker(new StringMarker(Messages.getString("RadixTrieMulti.4")));
            compareDataBitArray = null;
            setPosition("3.4.2.2.1");
            insertDataBitArray.removeAllMarkers();
            return trie;
        }
        setPosition("3.4.2.1");
        compareDataBitArray = initialiseBitElementArray(trieItem);
        compareDataBitArray.setColors(RadixTrieMultiColors.COMPARE_BIT_ACTIVE_COLOR, RadixTrieMultiColors.COMPARE_BIT_HIGHLIGHT_COLOR);
        RadixTrieMultiExternalNode.setCompareNode((RadixTrieMultiExternalNode)currentNode);
        RadixTrieMultiInternalNode returnNode = new RadixTrieMultiInternalNode();
        returnNode.parent = null;
        RadixTrieMultiExternalNode trieNode = (RadixTrieMultiExternalNode)currentNode;
        branch = getBranch(currentLevel - NO_OF_BITS, dataItem.getKey());
        if(currentNode.getParent() != null)
            currentNode.getParent().setChild(returnNode, branch);
        else
            trie = head = returnNode;
        returnNode.setIsLinkedIn();
        RadixTrieMultiInternalNode ptr = returnNode;
        currentNode = ptr;
        compareDataBitArray.unHighlight();
        insertDataBitArray.unHighlight();
        for(int i = 0; i < NO_OF_BITS; i++)
        {
            compareDataBitArray.highlightBit((byte)(currentLevel + i));
            insertDataBitArray.highlightBit((byte)(currentLevel + i));
        }

        setPosition("3.5.1");
        for(; getBranch(currentLevel, trieItem) == getBranch(currentLevel, dataItem.getKey()); setPosition("3.5.2.1.2"))
        {
            setPosition("3.5.2");
            branch = getBranch(currentLevel, dataItem.getKey());
            RadixTrieMultiInternalNode newInternal = new RadixTrieMultiInternalNode();
            pathNode = newInternal;
            ptr.setChild(newInternal, branch);
            ptr = (RadixTrieMultiInternalNode)ptr.getChild(branch);
            newInternal.setIsLinkedIn();
            currentNode = ptr;
            setPosition("3.5.2.1.1");
            currentLevel = currentLevel + NO_OF_BITS;
            compareDataBitArray.unHighlight();
            insertDataBitArray.unHighlight();
            for(int i = 0; i < NO_OF_BITS; i++)
            {
                compareDataBitArray.highlightBit((byte)(currentLevel + i));
                insertDataBitArray.highlightBit((byte)(currentLevel + i));
            }

        }

        setPosition("3.5.2");
        setPosition("3.5.3");
        branch = getBranch(currentLevel, trieItem);
        setPosition("3.4.2.1.2.1");
        ptr.setChild(trieNode, branch);
        trieNode.setLevel(currentLevel + NO_OF_BITS);
        insertedData = new Vector();
        radixTrieToVector(insertedData, head);
        setPosition("3.4.2.1.2.2");
        branch = getBranch(currentLevel, dataItem.getKey());
        setPosition("3.4.2.1.3.1");
        RadixTrieMultiExternalNode newExternal = new RadixTrieMultiExternalNode(dataItem);
        newExternal.setLevel(currentLevel + NO_OF_BITS);
        newExternal.setIsLinkedIn();
        ptr.setChild(newExternal, branch);
        insertedData = new Vector();
        radixTrieToVector(insertedData, head);
        setPosition("3.4.2.1.3.2");
        compareDataBitArray.unHighlight();
        insertDataBitArray.unHighlight();
        setPosition("3.4.2.1.4");
        return trie;
    }

    private boolean search(RadixTrieMultiNode trie, DataItem dataItem, int level)
    {
        setPosition("5");
        currentLevel = 0;
        searchDataBitArray.unHighlight();
        for(int i = 0; i < NO_OF_BITS; i++)
            searchDataBitArray.highlightBit((byte)(currentLevel + i));

        currentNode = trie;
        pathNode = currentNode;
        setPosition("5.1.1");
        while(currentNode instanceof RadixTrieMultiInternalNode) 
        {
            setPosition("5.1.2");
            int branch = getBranch(currentLevel, dataItem.getKey());
            setPosition("5.1.2.1.1");
            currentNode = ((RadixTrieMultiInternalNode)currentNode).getChild(branch);
            pathNode = currentNode;
            setPosition("5.1.2.1.2");
            currentLevel = currentLevel + NO_OF_BITS;
            searchDataBitArray.unHighlight();
            for(int i = 0; i < NO_OF_BITS; i++)
                searchDataBitArray.highlightBit((byte)(currentLevel + i));

            setPosition("5.1.2.2");
        }
        setPosition("5.1.2");
        setPosition("5.1.3");
        if(currentNode instanceof RadixTrieMultiExternalNode)
        {
            finalCompareDataBitArray = new RadixTrieMultiDigitalElementArray(((RadixTrieMultiExternalNode)currentNode).getKey(), mostSignificantBit, false);
            finalCompareDataBitArray.setColors(RadixTrieMultiColors.COMPARE_BIT_HIGHLIGHT_COLOR, RadixTrieMultiColors.COMPARE_BIT_HIGHLIGHT_COLOR);
        }
        if((currentNode instanceof RadixTrieMultiExternalNode) && ((RadixTrieMultiExternalNode)currentNode).getKey() == dataItem.getKey())
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
        head = new RadixTrieMultiNullNode(null);
    }

    public int getBranch(int bitNumber, int number)
    {
        return (number >> mostSignificantBit - bitNumber - (NO_OF_BITS - 1)) % NO_OF_NODES;
    }

    private void setPosition(String string, boolean isPause)
    {
        if(isPause)
            setPosition(string);
        else
            setPosition(Messages.getString("RadixTrieMulti.5"));
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

    public static int NO_OF_NODES = 4;
    public static int NO_OF_BITS = 2;
    public static RadioMenu mnuLink = null;
    private static final String BUILD_MODE_LABEL = Messages.getString("RadixTrieMulti.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("RadixTrieMulti.1");
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    public boolean isBackMode;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private RadixTrieMultiDigitalElementArray insertDataBitArray;
    private RadixTrieMultiDigitalElementArray searchDataBitArray;
    private RadixTrieMultiDigitalElementArray compareDataBitArray;
    private RadixTrieMultiDigitalElementArray finalCompareDataBitArray;
    private Vector insertedData;
    private RadixTrieMultiNode savedTree;
    private int insertPos;
    private int searchPos;
    private RadixTrieMultiNode head;
    private ElementArray insertData;
    private ElementArray searchData;
    private ElementArray newSearchData;
    private ElementArray newInsertData;
    int mostSignificantBit;
    private RadixTrieMultiNode currentNode;
    private RadixTrieMultiNode pathNode;
    private int currentLevel;

}
