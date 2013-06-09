// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SkipList.java

import com.cim.AIA.*;
import java.awt.Color;
import java.util.Random;
import java.util.Vector;
import localization.Messages;

public class SkipList extends Algorithm
    implements MethodSelectionListener, ControlListener
{

    public SkipList(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        isBackMode = false;
        drawBoxLevel = false;
        drawBoxFinalLevel = false;
        drawIPointer = false;
        drawJPointer = false;
        drawNewElement = false;
        runTween = false;
        dataCondition = false;
        displayDataCondition = false;
        nextNull = false;
        dataNode = null;
        savedSkipList = null;
        prob = 0.6F;
        randomLevel = new Random();
        searchNodeVector = new Vector();
        previousModeIsBack = false;
        maxLevel = (int)Math.round(Math.log(31D) / Math.log(1.0F / prob)) - 1;
        executingMode = 11;
        nextMode = executingMode;
        newInsertData = insertData = (int[])(int[])data;
        Random random = new Random();
        searchData = new int[insertData.length];
        for(int i = 0; i < insertData.length; i++)
            if(i % 2 == 0)
                searchData[i] = insertData[i] + 2;
            else
                searchData[i] = insertData[i];

        newSearchData = searchData;
        head = null;
        initialise();
    }

    public void reInitialise(Object data)
    {
        insertData = newInsertData;
        searchData = newSearchData;
        if(executingMode == 11)
            savedSkipList = null;
        head = savedSkipList;
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

        initialise();
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

    protected void changeData(Object data)
    {
        newInsertData = (int[])(int[])data;
        Random random = new Random();
        newSearchData = new int[insertData.length];
        for(int i = 0; i < insertData.length; i++)
            if(i % 2 == 0)
                newSearchData[i] = insertData[i] + 2;
            else
                newSearchData[i] = insertData[i];

        save();
        initialise();
    }

    private void initialise()
    {
        ptrNode = tailptrNode = null;
    }

    protected void initialiseMethods(String string, MethodSelectable methodSelectable)
    {
        MethodSelection methodSelection1 = new MethodSelection(BUILD_MODE_LABEL, string, Messages.getString("SkipList.3"), "3.1", getLogger(), getBreakPoint());
        MethodSelection methodSelection2 = new MethodSelection(SEARCH_MODE_LABEL, string, Messages.getString("SkipList.5"), "5", getLogger(), getBreakPoint());
        MethodSelection methodSelection3 = new MethodSelection(DELETE_MODE_LABEL, string, Messages.getString("SkipList.7"), "7", getLogger(), getBreakPoint());
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

    public SkipNode getHead()
    {
        return head;
    }

    public Node getPtrNode()
    {
        return ptrNode;
    }

    public Node getTailPtrNode()
    {
        return tailptrNode;
    }

    public SkipNode getNewElement()
    {
        return newElem;
    }

    public SkipNode getFinalNewElement()
    {
        return newElement;
    }

    public Node getDataNode()
    {
        return dataNode;
    }

    public Integer getDataItem()
    {
        return dataItem;
    }

    public Integer getNextData()
    {
        return nextData;
    }

    public boolean getNextNull()
    {
        return nextNull;
    }

    public int getLevels()
    {
        return levels;
    }

    public int getIPointer()
    {
        return iPointer;
    }

    public int getJPointer()
    {
        return jPointer;
    }

    public int getMaxLevel()
    {
        return maxLevel;
    }

    public int getNewNodeLevel()
    {
        return newNodeLevel;
    }

    public boolean getDataCondition()
    {
        return dataCondition;
    }

    public boolean doDisplayDataCondition()
    {
        return displayDataCondition;
    }

    public boolean doDrawBoxLevel()
    {
        return drawBoxLevel;
    }

    public boolean doDrawBoxFinalLevel()
    {
        return drawBoxFinalLevel;
    }

    public boolean doDrawIPointer()
    {
        return drawIPointer;
    }

    public boolean doDrawJPointer()
    {
        return drawJPointer;
    }

    public boolean doDrawNewElement()
    {
        return drawNewElement;
    }

    public boolean doRunTween()
    {
        return runTween;
    }

    public boolean isBuildMode()
    {
        return executingMode == 11;
    }

    public boolean isSearchMode()
    {
        return executingMode == 12;
    }

    protected int calculateLevels()
    {
        int lev;
        for(lev = 0; randomLevel.nextFloat() <= prob; lev++);
        if(lev < maxLevel)
            lev++;
        if(lev > maxLevel)
            return lev % maxLevel;
        else
            return lev;
    }

    protected void storeState(boolean forceState)
    {
        if(forceState)
            save();
    }

    private void save()
    {
        savedSkipList = head;
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
        if(needToResetSeed)
        {
            randomLevel.setSeed(0x5397fb1L);
            needToResetSeed = false;
        }
        if(executingMode == 11)
            insert();
        else
        if(executingMode == 12)
        {
            setPosition("5");
            searchCnt = 0;
            backSearchCnt = 0;
            while(enabled) 
                search();
        }
    }

    protected void search()
    {
        if(head != null)
        {
            if(!enabled)
                return;
            if(!isBackMode)
            {
                if(previousModeIsBack)
                {
                    searchCnt = 0;
                    previousModeIsBack = false;
                }
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
                searchNodeVector.insertElementAt(searchNode, searchCnt);
                SearchSelection.setEnabled(false);
                searchCnt++;
            } else
            {
                searchNode = (Node)searchNodeVector.elementAt(backSearchCnt);
                backSearchCnt++;
            }
            if(!enabled)
                return;
            searchDataColor[searchNode.getIdentifier()] = SEARCH_HIGHLIGHT_COLOR;
            int searchValue = Integer.valueOf(searchNode.getDisplayString()).intValue();
            setPosition("5.0");
            SkipNode ptr = head;
            ptrNode = head.nodes[0];
            dataNode = null;
            setPosition("5.1.1");
            for(int i = levels - 1; i >= 0; i--)
            {
                drawIPointer = true;
                iPointer = i;
                setPosition("5.1.2.1.1");
                ptr.nodes[i + 1].setBackgroundColor(Color.red);
                if(ptr.next[i] != null)
                {
                    nextNull = false;
                    dataNode = ptr.next[i].nodes[0];
                    nextData = (Integer)ptr.next[i].data;
                    dataItem = new Integer(searchValue);
                    if(((Integer)ptr.next[i].data).intValue() < searchValue)
                        dataCondition = true;
                    else
                        dataCondition = false;
                } else
                {
                    nextData = null;
                    dataItem = new Integer(searchValue);
                    nextNull = true;
                }
                displayDataCondition = true;
                setPosition("5.1.2.2.1.1");
                for(; ptr.next[i] != null && ((Integer)ptr.next[i].data).intValue() < searchValue; setPosition("5.1.2.2.1.1"))
                {
                    nextNull = false;
                    dataNode = ptr.next[i].nodes[0];
                    nextData = (Integer)ptr.next[i].data;
                    dataItem = new Integer(searchValue);
                    if(((Integer)ptr.next[i].data).intValue() < searchValue)
                        dataCondition = true;
                    else
                        dataCondition = false;
                    ptr.nextNodesLines[i + 1].setColor(Color.red);
                    ptrNode = ptr.next[i].nodes[0];
                    ptr = ptr.next[i];
                    ptr.nodes[i + 1].setBackgroundColor(Color.red);
                    setPosition("5.1.2.2.1.2.1");
                    if(ptr.next[i] != null)
                    {
                        nextNull = false;
                        dataNode = ptr.next[i].nodes[0];
                        nextData = (Integer)ptr.next[i].data;
                        dataItem = new Integer(searchValue);
                        if(((Integer)ptr.next[i].data).intValue() < searchValue)
                            dataCondition = true;
                        else
                            dataCondition = false;
                    } else
                    {
                        nextNull = true;
                        nextData = null;
                        dataItem = new Integer(searchValue);
                    }
                }

                nextNull = false;
                displayDataCondition = false;
            }

            drawIPointer = false;
            setPosition("5.1.2.3");
            setPosition("5.1.3.1");
            if(ptr.next[0] != null && ((Integer)ptr.next[0].data).intValue() == searchValue)
                if(!enabled)
                {
                    searchDataColor[searchNode.getIdentifier()] = SEARCH_ACTIVE_COLOR;
                    recolorSkipList();
                    return;
                } else
                {
                    ptr.nextNodesLines[1].setColor(Color.red);
                    searchNode.addMarker(new StringMarker("F"));
                    searchNode.addMarker(new StringMarker("o"));
                    searchNode.addMarker(new StringMarker("u"));
                    searchNode.addMarker(new StringMarker("n"));
                    searchNode.addMarker(new StringMarker("d"));
                    searchDataColor[searchNode.getIdentifier()] = SEARCH_DONE_COLOR;
                    setPosition("5.1.3.1.1");
                    recolorSkipList();
                    return;
                }
            setPosition("5.1.3.2");
            if(!enabled)
            {
                searchDataColor[searchNode.getIdentifier()] = SEARCH_ACTIVE_COLOR;
                recolorSkipList();
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
            setPosition("5.1.3.2.1");
            dataNode = null;
            recolorSkipList();
        }
        dataNode = null;
    }

    protected void insert()
    {
        if(isBackMode)
            levels = 1;
        if(insertData.length > 0)
        {
            if(insertPos == 0)
            {
                insertDataColor[0] = INSERT_HIGHLIGHT_COLOR;
                Integer newData = new Integer(-1);
                head = new SkipNode(newData, -1, maxLevel);
                for(int j = 0; j < head.nodes.length; j++)
                {
                    head.nodes[j].setBackgroundColor(INSERT_DONE_COLOR);
                    head.nodes[j].setMarkersBelowValue(false);
                    head.nodes[j].removeAllMarkers();
                    head.nodes[j].showObject(false);
                }

                setPosition("2.1");
            }
            if(!enabled)
                return;
            setPosition("3");
            for(int i = insertPos; i < insertData.length; i++)
            {
                if(!enabled)
                    return;
                insertDataColor[i] = Color.red;
                setPosition("3.1");
                insertItem(i);
                insertPos++;
                insertDataColor[i] = INSERT_DONE_COLOR;
                setPosition("3");
            }

        }
    }

    protected void insertItem(int index)
    {
        setPosition("3.1.0");
        SkipNode ptr = head;
        last = new SkipNode[maxLevel];
        ptrNode = head.nodes[0];
        setPosition("3.1.1.1");
        for(int i = levels - 1; i >= 0; i--)
        {
            drawIPointer = true;
            iPointer = i;
            setPosition("3.1.2.1.1");
            ptr.nodes[i + 1].setBackgroundColor(Color.red);
            if(ptr.next[i] != null)
            {
                nextNull = false;
                dataNode = ptr.next[i].nodes[0];
                nextData = (Integer)ptr.next[i].data;
                dataItem = new Integer(insertData[index]);
                if(((Integer)ptr.next[i].data).intValue() < insertData[index])
                    dataCondition = true;
                else
                    dataCondition = false;
            } else
            {
                nextData = null;
                dataItem = new Integer(insertData[index]);
                nextNull = true;
            }
            displayDataCondition = true;
            setPosition("3.1.2.2.1.1");
            for(; ptr.next[i] != null && ((Integer)ptr.next[i].data).intValue() < insertData[index]; setPosition("3.1.2.2.1.1"))
            {
                nextNull = false;
                dataNode = ptr.next[i].nodes[0];
                nextData = (Integer)ptr.next[i].data;
                dataItem = new Integer(insertData[index]);
                if(((Integer)ptr.next[i].data).intValue() < insertData[index])
                    dataCondition = true;
                else
                    dataCondition = false;
                ptr.nextNodesLines[i + 1].setColor(Color.red);
                ptrNode = ptr.next[i].nodes[0];
                ptr = ptr.next[i];
                ptr.nodes[i + 1].setBackgroundColor(Color.red);
                setPosition("3.1.2.2.1.2.1");
                if(ptr.next[i] != null)
                {
                    nextNull = false;
                    dataNode = ptr.next[i].nodes[0];
                    nextData = (Integer)ptr.next[i].data;
                    dataItem = new Integer(insertData[index]);
                    if(((Integer)ptr.next[i].data).intValue() < insertData[index])
                        dataCondition = true;
                    else
                        dataCondition = false;
                } else
                {
                    nextNull = true;
                    nextData = null;
                    dataItem = new Integer(insertData[index]);
                }
            }

            nextNull = false;
            dataNode = null;
            displayDataCondition = false;
            last[i] = ptr;
            last[i].nodes[i + 1].setBackgroundColor(Color.red);
            last[i].nodes[i + 1].highlight();
            setPosition("3.1.2.2.1.4");
        }

        drawIPointer = false;
        setPosition("3.1.2.3");
        newNodeLevel = calculateLevels();
        if(newNodeLevel > levels)
            if(index != 0)
            {
                newNodeLevel = ++levels;
                last[newNodeLevel - 1] = head;
                last[newNodeLevel - 1].nodes[newNodeLevel].setBackgroundColor(Color.red);
                last[newNodeLevel - 1].nodes[newNodeLevel].highlight();
            } else
            if(index == 0)
                newNodeLevel = levels;
        drawBoxLevel = true;
        setPosition("3.1.3.1.1");
        drawBoxLevel = false;
        drawBoxFinalLevel = true;
        Integer newData = new Integer(insertData[index]);
        newElement = new SkipNode(newData, index, newNodeLevel);
        newElement.nodes[0].setBackgroundColor(LIST_ACTIVE_COLOR);
        for(int j = 1; j < newElement.nodes.length; j++)
            newElement.nodes[j].setBackgroundColor(LIST_NULL_COLOR);

        newElem = new SkipNode(newData, index, newNodeLevel);
        newElem.nodes[0].setBackgroundColor(LIST_ACTIVE_COLOR);
        for(int q = 1; q < newElem.nodes.length; q++)
            newElem.nodes[q].setBackgroundColor(Color.white);

        drawNewElement = true;
        setPosition("3.1.3.2.1");
        if(!enabled)
        {
            ptrNode = null;
            drawBoxFinalLevel = false;
            drawNewElement = false;
            recolorSkipList();
            return;
        }
        newElement.nodes[0].setIsVisible(false);
        for(int k = 0; k < newNodeLevel; k++)
        {
            newElement.next[k] = last[k].next[k];
            newElement.nodes[k + 1].setIsVisible(false);
            newElement.nextNodesLines[k + 1].setColor(Color.white);
            newElement.nextNodesLines[k + 1].setArrowHeadWidth(0);
            last[k].next[k] = newElement;
            last[k].nextNodesLines[k + 1].setColor(Color.white);
            last[k].nextNodesLines[k + 1].setArrowHeadWidth(0);
        }

        if(!enabled)
        {
            ptrNode = null;
            drawBoxFinalLevel = false;
            drawNewElement = false;
            recolorSkipList();
            return;
        }
        for(int k = 0; k < newNodeLevel; k++)
        {
            drawJPointer = true;
            jPointer = k;
            setPosition("3.1.3.3.1");
            if(k == 0)
            {
                newElement.nodes[0].setIsVisible(true);
                for(int m = 0; m < newNodeLevel; m++)
                {
                    newElement.nodes[m + 1].setBackgroundColor(Color.white);
                    newElement.nodes[m + 1].setIsVisible(true);
                }

            }
            newElement.nodes[k + 1].setBackgroundColor(Color.red);
            newElement.nextNodesLines[k + 1].setColor(Color.red);
            newElement.nextNodesLines[k + 1].setArrowHeadWidth(5);
            newElement.nextNodesLines[k + 1].showAsDotted(false);
            last[k].nextNodesLines[k + 1].setColor(Color.red);
            last[k].nextNodesLines[k + 1].setArrowHeadWidth(5);
            last[k].nextNodesLines[k + 1].showAsDotted(false);
            if(k == 0)
                runTween = true;
            else
                runTween = false;
            setPosition("3.1.3.3.2.1");
            if(runTween)
                runTween = false;
        }

        drawJPointer = false;
        drawBoxFinalLevel = false;
        drawNewElement = false;
        if(!enabled)
        {
            ptrNode = null;
            drawBoxFinalLevel = false;
            drawNewElement = false;
            recolorSkipList();
            return;
        }
        recolorSkipList();
        newElement.nodes[0].setBackgroundColor(LIST_DONE_COLOR);
        setPosition("3.1.3.3.3");
        if(!enabled)
        {
            ptrNode = null;
            drawBoxFinalLevel = false;
            drawNewElement = false;
            recolorSkipList();
            return;
        } else
        {
            ptrNode = null;
            return;
        }
    }

    protected void recolorSkipList()
    {
        for(int i = 0; i < newNodeLevel; i++)
            last[i].nodes[i + 1].unHighlight();

        for(SkipNode recolor = head.next[0]; recolor != null; recolor = recolor.next[0])
        {
            for(int p = 1; p < recolor.nodes.length; p++)
            {
                recolor.nodes[p].setBackgroundColor(LIST_NULL_COLOR);
                recolor.nextNodesLines[p].setColor(TEXT_COLOR);
            }

        }

        for(int p = 1; p < head.nodes.length; p++)
        {
            head.nodes[p].setBackgroundColor(INSERT_DONE_COLOR);
            head.nextNodesLines[p].setColor(TEXT_COLOR);
        }

    }

    public void controlStep(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlBack(ControlEvent e)
    {
        isBackMode = true;
        needToResetSeed = true;
    }

    public void controlPause(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlReset(ControlEvent e)
    {
        isBackMode = false;
        needToResetSeed = true;
    }

    public void controlRestart(ControlEvent e)
    {
        isBackMode = false;
        needToResetSeed = true;
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
    private Color insertDataColor[];
    private Color searchDataColor[];
    private ElementArray insertArray;
    private ElementArray searchArray;
    private boolean isBackMode;
    private boolean drawBoxLevel;
    private boolean drawBoxFinalLevel;
    private boolean drawIPointer;
    private boolean drawJPointer;
    private boolean drawNewElement;
    private boolean runTween;
    private boolean dataCondition;
    private boolean displayDataCondition;
    private boolean nextNull;
    private int iPointer;
    private int jPointer;
    private int newNodeLevel;
    private Integer dataItem;
    private Integer nextData;
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private static final int DELETE_MODE = 13;
    private static final Color TEXT_COLOR;
    private static final Color INSERT_HIGHLIGHT_COLOR;
    private static final Color INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
    private static final Color INSERT_DONE_COLOR = new Color(96, 127, 96);
    private static final Color SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
    private static final Color SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
    private static final Color SEARCH_DONE_COLOR = new Color(0, 127, 127);
    private static final Color LIST_ACTIVE_COLOR;
    private static final Color LIST_NULL_COLOR;
    private static final Color LIST_DONE_COLOR;
    private static final Color NODE_HIGHLIGHT_COLOR;
    private static final Color NODE_BORDER_COLOR;
    private static final String BUILD_MODE_LABEL = Messages.getString("SkipList.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("SkipList.1");
    private static final String DELETE_MODE_LABEL = Messages.getString("SkipList.2");
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 31;
    private Node ptrNode;
    private Node tailptrNode;
    private Node searchNode;
    private static SkipNode head;
    private SkipNode newElem;
    private SkipNode newElement;
    private SkipNode last[];
    private Node finalNode;
    private Node dataNode;
    private int executingMode;
    private int nextMode;
    private SkipNode savedSkipList;
    private float prob;
    private int maxLevel;
    private static int levels = 1;
    private Random randomLevel;
    private static boolean needToResetSeed = false;
    private static final long aSeed = 0x5397fb1L;
    protected Vector searchNodeVector;
    protected int searchCnt;
    protected int backSearchCnt;
    private boolean previousModeIsBack;

    static 
    {
        TEXT_COLOR = Color.black;
        INSERT_HIGHLIGHT_COLOR = new Color(0, 210, 0);
        LIST_ACTIVE_COLOR = Color.pink;
        LIST_NULL_COLOR = Color.orange;
        LIST_DONE_COLOR = INSERT_HIGHLIGHT_COLOR;
        NODE_HIGHLIGHT_COLOR = Color.green;
        NODE_BORDER_COLOR = Color.black;
    }
}
