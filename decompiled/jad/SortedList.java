// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SortedList.java

import com.cim.AIA.*;
import java.awt.Color;
import java.util.Random;
import java.util.Vector;
import localization.Messages;

public class SortedList extends Algorithm
    implements MethodSelectionListener, ControlListener
{

    public SortedList(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        isBackMode = false;
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
        head = null;
        initialise();
    }

    public void reInitialise(Object data)
    {
        insertData = newInsertData;
        searchData = newSearchData;
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

        head = null;
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

    private void initialise()
    {
        ptrNode = tailptrNode = null;
    }

    protected void initialiseMethods(String string, MethodSelectable methodSelectable)
    {
        MethodSelection methodSelection1 = new MethodSelection(BUILD_MODE_LABEL, string, Messages.getString("SortedList.3"), "3.1", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelectionListener(this);
    }

    public void processMethodSelectionEvent(MethodSelectionEvent e)
    {
        if(BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0)
            nextMode = 11;
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

    public SList getHead()
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

    public boolean isBuildMode()
    {
        return executingMode == 11;
    }

    public boolean isSearchMode()
    {
        return executingMode == 12;
    }

    protected void run()
    {
        setPosition("1");
        if(executingMode == 11)
            insert();
        else
        if(executingMode == 12)
        {
            setPosition("5");
            search();
        }
    }

    protected void insert()
    {
        setPosition("1");
        setPosition("2");
        if(insertData.length > 0)
        {
            setPosition("2.1");
            insertDataColor[0] = INSERT_HIGHLIGHT_COLOR;
            Integer newData = new Integer(-1);
            head = new SList(newData, -1);
            head.node.setBackgroundColor(LIST_NULL_COLOR);
            head.node.setMarkersBelowValue(false);
            head.node.removeAllMarkers();
            head.node.showObject(false);
            head.nextNode.setBackgroundColor(LIST_NULL_COLOR);
            head.next = null;
            for(int i = 0; i < insertData.length; i++)
            {
                insertItem(i);
                insertDataColor[i] = INSERT_DONE_COLOR;
            }

        }
    }

    protected void search()
    {
    }

    protected void insertItem(int index)
    {
        insertDataColor[index] = INSERT_HIGHLIGHT_COLOR;
        SList tailptr;
        SList ptr = tailptr = head;
        ptrNode = tailptrNode = head.node;
        setPosition("3.1.1.1");
        for(Integer currentInsertData = new Integer(insertData[index]); ptr != null && ((Integer)ptr.data).intValue() < currentInsertData.intValue(); setPosition("3.1.2.2.2"))
        {
            setPosition("3.1.2.1");
            tailptr = ptr;
            tailptrNode = ptrNode;
            setPosition("3.1.2.2.1");
            ptr = ptr.next;
            if(ptr != null)
                ptrNode = ptr.node;
            else
                ptrNode = tailptr.nextNode;
        }

        setPosition("3.1.2.3");
        Integer newData = new Integer(insertData[index]);
        SList newElement = new SList(newData, index);
        newElement.node.setBackgroundColor(LIST_ACTIVE_COLOR);
        newElement.nextNode.setBackgroundColor(LIST_NULL_COLOR);
        newElement.next = null;
        setPosition("3.1.3.1");
        newElement.next = ptr;
        setPosition("3.1.3.2");
        tailptr.next = newElement;
        setPosition("3.1.3.3");
        ptrNode = tailptrNode = null;
        newElement.node.setBackgroundColor(LIST_DONE_COLOR);
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
    private int searchData[];
    private int newSearchData[];
    private Color insertDataColor[];
    private Color searchDataColor[];
    private ElementArray insertArray;
    private ElementArray searchArray;
    private boolean isBackMode;
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private static final int DELETE_MODE = 13;
    private static final Color TEXT_COLOR;
    private static final Color INSERT_HIGHLIGHT_COLOR;
    private static final Color INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
    private static final Color INSERT_DONE_COLOR;
    private static final Color SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
    private static final Color SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
    private static final Color SEARCH_DONE_COLOR = new Color(0, 127, 127);
    private static final Color LIST_ACTIVE_COLOR;
    private static final Color LIST_NULL_COLOR;
    private static final Color LIST_DONE_COLOR;
    private static final String BUILD_MODE_LABEL = Messages.getString("SortedList.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("SortedList.1");
    private static final String DELETE_MODE_LABEL = Messages.getString("SortedList.2");
    private Node ptrNode;
    private Node tailptrNode;
    private SList head;
    private int executingMode;
    private int nextMode;

    static 
    {
        TEXT_COLOR = Color.black;
        INSERT_HIGHLIGHT_COLOR = new Color(0, 210, 0);
        INSERT_DONE_COLOR = new Color(96, 127, 96);
        LIST_ACTIVE_COLOR = Color.red;
        LIST_NULL_COLOR = INSERT_DONE_COLOR;
        LIST_DONE_COLOR = INSERT_HIGHLIGHT_COLOR;
    }
}
