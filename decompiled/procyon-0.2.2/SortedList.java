import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class SortedList extends Algorithm implements MethodSelectionListener, ControlListener
{
    private int[] insertData;
    private int[] newInsertData;
    private int[] searchData;
    private int[] newSearchData;
    private Color[] insertDataColor;
    private Color[] searchDataColor;
    private ElementArray insertArray;
    private ElementArray searchArray;
    private boolean isBackMode;
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private static final int DELETE_MODE = 13;
    private static final Color TEXT_COLOR;
    private static final Color INSERT_HIGHLIGHT_COLOR;
    private static final Color INSERT_ACTIVE_COLOR;
    private static final Color INSERT_DONE_COLOR;
    private static final Color SEARCH_HIGHLIGHT_COLOR;
    private static final Color SEARCH_ACTIVE_COLOR;
    private static final Color SEARCH_DONE_COLOR;
    private static final Color LIST_ACTIVE_COLOR;
    private static final Color LIST_NULL_COLOR;
    private static final Color LIST_DONE_COLOR;
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
    private static final String DELETE_MODE_LABEL;
    private Node ptrNode;
    private Node tailptrNode;
    private SList head;
    private int executingMode;
    private int nextMode;
    
    public SortedList(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.isBackMode = false;
        this.executingMode = 11;
        this.nextMode = this.executingMode;
        final int[] array = (int[])((int[])data);
        this.insertData = array;
        this.newInsertData = array;
        final Random random = new Random();
        this.searchData = new int[this.insertData.length];
        for (int i = 0; i < this.insertData.length; ++i) {
            if (i % 2 == 0) {
                this.searchData[i] = Math.abs(random.nextInt()) % 98 + 1;
            }
            else {
                this.searchData[i] = this.insertData[i];
            }
        }
        this.newSearchData = this.searchData;
        this.head = null;
        this.initialise();
    }
    
    public void reInitialise(final Object data) {
        this.insertData = this.newInsertData;
        this.searchData = this.newSearchData;
        this.insertDataColor = new Color[this.insertData.length];
        for (int i = 0; i < this.insertData.length; ++i) {
            this.insertDataColor[i] = SortedList.INSERT_ACTIVE_COLOR;
        }
        this.insertArray = new ElementArray(0, 0);
        this.insertArray.setColumGap(0);
        this.insertArray.setElementWidth(20);
        for (int i = 0; i < this.insertData.length; ++i) {
            final Integer nodeValue = new Integer(this.insertData[i]);
            final Node node = new Node(nodeValue, i);
            node.setBackgroundColor(this.insertDataColor[i]);
            node.setHighlightColor(SortedList.INSERT_HIGHLIGHT_COLOR);
            node.setTextColor(SortedList.TEXT_COLOR);
            this.insertArray.setValue(i, node);
        }
        this.searchDataColor = new Color[this.searchData.length];
        for (int i = 0; i < this.searchData.length; ++i) {
            this.searchDataColor[i] = SortedList.SEARCH_ACTIVE_COLOR;
        }
        this.searchArray = new ElementArray(0, 0);
        this.searchArray.setColumGap(0);
        this.searchArray.setElementWidth(20);
        for (int i = 0; i < this.searchData.length; ++i) {
            final Node node2 = new Node(new Integer(this.searchData[i]), i);
            node2.setBackgroundColor(this.searchDataColor[i]);
            node2.setHighlightColor(SortedList.SEARCH_HIGHLIGHT_COLOR);
            node2.setTextColor(SortedList.TEXT_COLOR);
            this.searchArray.setValue(i, node2);
        }
        this.head = null;
        this.initialise();
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        return null;
    }
    
    protected Vector<E> generateQuestions() {
        return null;
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    protected void removeAllQuestions() {
    }
    
    protected void removeAllAnimationRequests() {
    }
    
    private void initialise() {
        final Object o = null;
        this.tailptrNode = o;
        this.ptrNode = o;
    }
    
    protected void initialiseMethods(final String string, final MethodSelectable methodSelectable) {
        final MethodSelection methodSelection1 = new MethodSelection(SortedList.BUILD_MODE_LABEL, string, Messages.getString("SortedList.3"), "3.1", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (SortedList.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
    }
    
    public ElementArray getInsertElementArray() {
        if (this.insertArray != null) {
            for (int i = 0; i < this.insertArray.getSize(); ++i) {
                ((Node)this.insertArray.getElement(i)).setBackgroundColor(this.insertDataColor[i]);
                ((Node)this.insertArray.getElement(i)).setHighlightColor(SortedList.INSERT_HIGHLIGHT_COLOR);
                ((Node)this.insertArray.getElement(i)).setTextColor(SortedList.TEXT_COLOR);
            }
        }
        return this.insertArray;
    }
    
    public ElementArray getSearchElementArray() {
        if (this.searchArray != null) {
            for (int i = 0; i < this.searchArray.getSize(); ++i) {
                ((Node)this.searchArray.getElement(i)).setBackgroundColor(this.searchDataColor[i]);
                ((Node)this.searchArray.getElement(i)).setHighlightColor(SortedList.SEARCH_HIGHLIGHT_COLOR);
                ((Node)this.searchArray.getElement(i)).setTextColor(SortedList.TEXT_COLOR);
            }
        }
        return this.searchArray;
    }
    
    public SList getHead() {
        return this.head;
    }
    
    public Node getPtrNode() {
        return this.ptrNode;
    }
    
    public Node getTailPtrNode() {
        return this.tailptrNode;
    }
    
    public boolean isBuildMode() {
        return this.executingMode == 11;
    }
    
    public boolean isSearchMode() {
        return this.executingMode == 12;
    }
    
    protected void run() {
        this.setPosition("1");
        if (this.executingMode == 11) {
            this.insert();
        }
        else if (this.executingMode == 12) {
            this.setPosition("5");
            this.search();
        }
    }
    
    protected void insert() {
        this.setPosition("1");
        this.setPosition("2");
        if (this.insertData.length > 0) {
            this.setPosition("2.1");
            this.insertDataColor[0] = SortedList.INSERT_HIGHLIGHT_COLOR;
            final Integer newData = new Integer(-1);
            this.head = new SList(newData, -1);
            this.head.node.setBackgroundColor(SortedList.LIST_NULL_COLOR);
            this.head.node.setMarkersBelowValue(false);
            this.head.node.removeAllMarkers();
            this.head.node.showObject(false);
            this.head.nextNode.setBackgroundColor(SortedList.LIST_NULL_COLOR);
            this.head.next = null;
            for (int i = 0; i < this.insertData.length; ++i) {
                this.insertItem(i);
                this.insertDataColor[i] = SortedList.INSERT_DONE_COLOR;
            }
        }
    }
    
    protected void search() {
    }
    
    protected void insertItem(final int index) {
        this.insertDataColor[index] = SortedList.INSERT_HIGHLIGHT_COLOR;
        SList ptr;
        SList tailptr = ptr = this.head;
        final Node node = this.head.node;
        this.tailptrNode = node;
        this.ptrNode = node;
        this.setPosition("3.1.1.1");
        final Integer currentInsertData = new Integer(this.insertData[index]);
        while (ptr != null && (Integer)ptr.data < currentInsertData) {
            this.setPosition("3.1.2.1");
            tailptr = ptr;
            this.tailptrNode = this.ptrNode;
            this.setPosition("3.1.2.2.1");
            ptr = ptr.next;
            if (ptr != null) {
                this.ptrNode = ptr.node;
            }
            else {
                this.ptrNode = tailptr.nextNode;
            }
            this.setPosition("3.1.2.2.2");
        }
        this.setPosition("3.1.2.3");
        final Integer newData = new Integer(this.insertData[index]);
        final SList newElement = new SList(newData, index);
        newElement.node.setBackgroundColor(SortedList.LIST_ACTIVE_COLOR);
        newElement.nextNode.setBackgroundColor(SortedList.LIST_NULL_COLOR);
        newElement.next = null;
        this.setPosition("3.1.3.1");
        newElement.next = ptr;
        this.setPosition("3.1.3.2");
        tailptr.next = newElement;
        this.setPosition("3.1.3.3");
        final Object o = null;
        this.tailptrNode = o;
        this.ptrNode = o;
        newElement.node.setBackgroundColor(SortedList.LIST_DONE_COLOR);
    }
    
    public void controlStep(final ControlEvent e) {
        this.isBackMode = false;
    }
    
    public void controlBack(final ControlEvent e) {
        this.isBackMode = true;
    }
    
    public void controlPause(final ControlEvent e) {
        this.isBackMode = false;
    }
    
    public void controlReset(final ControlEvent e) {
        this.isBackMode = false;
    }
    
    public void controlRestart(final ControlEvent e) {
        this.isBackMode = false;
    }
    
    public void controlRun(final ControlEvent e) {
        this.isBackMode = false;
    }
    
    public void controlOther(final ControlEvent e) {
        this.isBackMode = false;
    }
    
    static {
        TEXT_COLOR = Color.black;
        INSERT_HIGHLIGHT_COLOR = new Color(0, 210, 0);
        INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
        INSERT_DONE_COLOR = new Color(96, 127, 96);
        SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
        SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
        SEARCH_DONE_COLOR = new Color(0, 127, 127);
        LIST_ACTIVE_COLOR = Color.red;
        LIST_NULL_COLOR = SortedList.INSERT_DONE_COLOR;
        LIST_DONE_COLOR = SortedList.INSERT_HIGHLIGHT_COLOR;
        BUILD_MODE_LABEL = Messages.getString("SortedList.0");
        SEARCH_MODE_LABEL = Messages.getString("SortedList.1");
        DELETE_MODE_LABEL = Messages.getString("SortedList.2");
    }
}
