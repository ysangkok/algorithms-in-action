import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class SkipList extends Algorithm implements MethodSelectionListener, ControlListener
{
    private int[] insertData;
    private int[] newInsertData;
    private int insertPos;
    private int[] searchData;
    private int[] newSearchData;
    private Color[] insertDataColor;
    private Color[] searchDataColor;
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
    private static final Color INSERT_ACTIVE_COLOR;
    private static final Color INSERT_DONE_COLOR;
    private static final Color SEARCH_HIGHLIGHT_COLOR;
    private static final Color SEARCH_ACTIVE_COLOR;
    private static final Color SEARCH_DONE_COLOR;
    private static final Color LIST_ACTIVE_COLOR;
    private static final Color LIST_NULL_COLOR;
    private static final Color LIST_DONE_COLOR;
    private static final Color NODE_HIGHLIGHT_COLOR;
    private static final Color NODE_BORDER_COLOR;
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
    private static final String DELETE_MODE_LABEL;
    public static final int MINIMUM_NUMBER_OF_ELEMENTS = 5;
    public static final int MAXIMUM_NUMBER_OF_ELEMENTS = 31;
    private Node ptrNode;
    private Node tailptrNode;
    private Node searchNode;
    private static SkipNode head;
    private SkipNode newElem;
    private SkipNode newElement;
    private SkipNode[] last;
    private Node finalNode;
    private Node dataNode;
    private int executingMode;
    private int nextMode;
    private SkipNode savedSkipList;
    private float prob;
    private int maxLevel;
    private static int levels;
    private Random randomLevel;
    private static boolean needToResetSeed;
    private static final long aSeed = 87654321L;
    protected Vector<E> searchNodeVector;
    protected int searchCnt;
    protected int backSearchCnt;
    private boolean previousModeIsBack;
    
    public SkipList(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.isBackMode = false;
        this.drawBoxLevel = false;
        this.drawBoxFinalLevel = false;
        this.drawIPointer = false;
        this.drawJPointer = false;
        this.drawNewElement = false;
        this.runTween = false;
        this.dataCondition = false;
        this.displayDataCondition = false;
        this.nextNull = false;
        this.dataNode = null;
        this.savedSkipList = null;
        this.prob = 0.6f;
        this.randomLevel = new Random();
        this.searchNodeVector = new Vector();
        this.previousModeIsBack = false;
        this.maxLevel = (int)Math.round(Math.log(31.0) / Math.log((double)(1.0f / this.prob))) - 1;
        this.executingMode = 11;
        this.nextMode = this.executingMode;
        final int[] array = (int[])((int[])data);
        this.insertData = array;
        this.newInsertData = array;
        final Random random = new Random();
        this.searchData = new int[this.insertData.length];
        for (int i = 0; i < this.insertData.length; ++i) {
            if (i % 2 == 0) {
                this.searchData[i] = this.insertData[i] + 2;
            }
            else {
                this.searchData[i] = this.insertData[i];
            }
        }
        this.newSearchData = this.searchData;
        SkipList.head = null;
        this.initialise();
    }
    
    public void reInitialise(final Object data) {
        this.insertData = this.newInsertData;
        this.searchData = this.newSearchData;
        if (this.executingMode == 11) {
            this.savedSkipList = null;
        }
        SkipList.head = this.savedSkipList;
        this.insertPos = 0;
        this.insertDataColor = new Color[this.insertData.length];
        for (int i = 0; i < this.insertData.length; ++i) {
            this.insertDataColor[i] = SkipList.INSERT_ACTIVE_COLOR;
        }
        this.insertArray = new ElementArray(0, 0);
        this.insertArray.setColumGap(0);
        this.insertArray.setElementWidth(20);
        for (int i = 0; i < this.insertData.length; ++i) {
            final Integer nodeValue = new Integer(this.insertData[i]);
            final Node node = new Node(nodeValue, i);
            node.setBackgroundColor(this.insertDataColor[i]);
            node.setHighlightColor(SkipList.INSERT_HIGHLIGHT_COLOR);
            node.setTextColor(SkipList.TEXT_COLOR);
            this.insertArray.setValue(i, node);
        }
        this.searchDataColor = new Color[this.searchData.length];
        for (int i = 0; i < this.searchData.length; ++i) {
            this.searchDataColor[i] = SkipList.SEARCH_ACTIVE_COLOR;
        }
        this.searchArray = new ElementArray(0, 0);
        this.searchArray.setColumGap(0);
        this.searchArray.setElementWidth(20);
        for (int i = 0; i < this.searchData.length; ++i) {
            final Node node2 = new Node(new Integer(this.searchData[i]), i);
            node2.setBackgroundColor(this.searchDataColor[i]);
            node2.setHighlightColor(SkipList.SEARCH_HIGHLIGHT_COLOR);
            node2.setTextColor(SkipList.TEXT_COLOR);
            this.searchArray.setValue(i, node2);
        }
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
    
    protected void changeData(final Object data) {
        this.newInsertData = (int[])((int[])data);
        final Random random = new Random();
        this.newSearchData = new int[this.insertData.length];
        for (int i = 0; i < this.insertData.length; ++i) {
            if (i % 2 == 0) {
                this.newSearchData[i] = this.insertData[i] + 2;
            }
            else {
                this.newSearchData[i] = this.insertData[i];
            }
        }
        this.save();
        this.initialise();
    }
    
    private void initialise() {
        final Object o = null;
        this.tailptrNode = o;
        this.ptrNode = o;
    }
    
    protected void initialiseMethods(final String string, final MethodSelectable methodSelectable) {
        final MethodSelection methodSelection1 = new MethodSelection(SkipList.BUILD_MODE_LABEL, string, Messages.getString("SkipList.3"), "3.1", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection2 = new MethodSelection(SkipList.SEARCH_MODE_LABEL, string, Messages.getString("SkipList.5"), "5", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection3 = new MethodSelection(SkipList.DELETE_MODE_LABEL, string, Messages.getString("SkipList.7"), "7", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (SkipList.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
        else if (SkipList.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 12;
        }
    }
    
    public ElementArray getInsertElementArray() {
        if (this.insertArray != null) {
            for (int i = 0; i < this.insertArray.getSize(); ++i) {
                ((Node)this.insertArray.getElement(i)).setBackgroundColor(this.insertDataColor[i]);
                ((Node)this.insertArray.getElement(i)).setHighlightColor(SkipList.INSERT_HIGHLIGHT_COLOR);
                ((Node)this.insertArray.getElement(i)).setTextColor(SkipList.TEXT_COLOR);
            }
        }
        return this.insertArray;
    }
    
    public ElementArray getSearchElementArray() {
        if (this.searchArray != null) {
            for (int i = 0; i < this.searchArray.getSize(); ++i) {
                ((Node)this.searchArray.getElement(i)).setBackgroundColor(this.searchDataColor[i]);
                ((Node)this.searchArray.getElement(i)).setHighlightColor(SkipList.SEARCH_HIGHLIGHT_COLOR);
                ((Node)this.searchArray.getElement(i)).setTextColor(SkipList.TEXT_COLOR);
            }
        }
        return this.searchArray;
    }
    
    public SkipNode getHead() {
        return SkipList.head;
    }
    
    public Node getPtrNode() {
        return this.ptrNode;
    }
    
    public Node getTailPtrNode() {
        return this.tailptrNode;
    }
    
    public SkipNode getNewElement() {
        return this.newElem;
    }
    
    public SkipNode getFinalNewElement() {
        return this.newElement;
    }
    
    public Node getDataNode() {
        return this.dataNode;
    }
    
    public Integer getDataItem() {
        return this.dataItem;
    }
    
    public Integer getNextData() {
        return this.nextData;
    }
    
    public boolean getNextNull() {
        return this.nextNull;
    }
    
    public int getLevels() {
        return SkipList.levels;
    }
    
    public int getIPointer() {
        return this.iPointer;
    }
    
    public int getJPointer() {
        return this.jPointer;
    }
    
    public int getMaxLevel() {
        return this.maxLevel;
    }
    
    public int getNewNodeLevel() {
        return this.newNodeLevel;
    }
    
    public boolean getDataCondition() {
        return this.dataCondition;
    }
    
    public boolean doDisplayDataCondition() {
        return this.displayDataCondition;
    }
    
    public boolean doDrawBoxLevel() {
        return this.drawBoxLevel;
    }
    
    public boolean doDrawBoxFinalLevel() {
        return this.drawBoxFinalLevel;
    }
    
    public boolean doDrawIPointer() {
        return this.drawIPointer;
    }
    
    public boolean doDrawJPointer() {
        return this.drawJPointer;
    }
    
    public boolean doDrawNewElement() {
        return this.drawNewElement;
    }
    
    public boolean doRunTween() {
        return this.runTween;
    }
    
    public boolean isBuildMode() {
        return this.executingMode == 11;
    }
    
    public boolean isSearchMode() {
        return this.executingMode == 12;
    }
    
    protected int calculateLevels() {
        int lev = 0;
        while (this.randomLevel.nextFloat() <= this.prob) {
            ++lev;
        }
        if (lev < this.maxLevel) {
            ++lev;
        }
        if (lev > this.maxLevel) {
            return lev % this.maxLevel;
        }
        return lev;
    }
    
    protected void storeState(final boolean forceState) {
        if (forceState) {
            this.save();
        }
    }
    
    private void save() {
        this.savedSkipList = SkipList.head;
    }
    
    protected void run(final boolean canChangeMode) {
        if (canChangeMode) {
            this.executingMode = this.nextMode;
        }
        this.run();
    }
    
    protected void run() {
        if (!this.enabled) {
            return;
        }
        if (SkipList.needToResetSeed) {
            this.randomLevel.setSeed(87654321L);
            SkipList.needToResetSeed = false;
        }
        if (this.executingMode == 11) {
            this.insert();
        }
        else if (this.executingMode == 12) {
            this.setPosition("5");
            this.searchCnt = 0;
            this.backSearchCnt = 0;
            while (this.enabled) {
                this.search();
            }
        }
    }
    
    protected void search() {
        if (SkipList.head != null) {
            if (!this.enabled) {
                return;
            }
            if (!this.isBackMode) {
                if (this.previousModeIsBack) {
                    this.searchCnt = 0;
                    this.previousModeIsBack = false;
                }
                SearchSelection.setEnabled(true);
                while (SearchSelection.getNode() == null) {
                    if (!this.enabled) {
                        SearchSelection.setEnabled(false);
                        return;
                    }
                    try {
                        Thread.sleep(1000L);
                        continue;
                    }
                    catch (Exception ex) {
                        continue;
                    }
                    break;
                }
                this.searchNode = SearchSelection.getNode();
                this.searchNodeVector.insertElementAt(this.searchNode, this.searchCnt);
                SearchSelection.setEnabled(false);
                this.searchCnt = this.searchCnt + 1;
            }
            else {
                this.searchNode = (Node)this.searchNodeVector.elementAt(this.backSearchCnt);
                this.backSearchCnt = this.backSearchCnt + 1;
            }
            if (!this.enabled) {
                return;
            }
            this.searchDataColor[this.searchNode.getIdentifier()] = SkipList.SEARCH_HIGHLIGHT_COLOR;
            final int searchValue = Integer.valueOf(this.searchNode.getDisplayString()).intValue();
            this.setPosition("5.0");
            SkipNode ptr = SkipList.head;
            this.ptrNode = SkipList.head.nodes[0];
            this.dataNode = null;
            this.setPosition("5.1.1");
            for (int i = SkipList.levels - 1; i >= 0; --i) {
                this.drawIPointer = true;
                this.iPointer = i;
                this.setPosition("5.1.2.1.1");
                ptr.nodes[i + 1].setBackgroundColor(Color.red);
                if (ptr.next[i] != null) {
                    this.nextNull = false;
                    this.dataNode = ptr.next[i].nodes[0];
                    this.nextData = (Integer)ptr.next[i].data;
                    this.dataItem = new Integer(searchValue);
                    if ((Integer)ptr.next[i].data < searchValue) {
                        this.dataCondition = true;
                    }
                    else {
                        this.dataCondition = false;
                    }
                }
                else {
                    this.nextData = null;
                    this.dataItem = new Integer(searchValue);
                    this.nextNull = true;
                }
                this.displayDataCondition = true;
                this.setPosition("5.1.2.2.1.1");
                while (ptr.next[i] != null && (Integer)ptr.next[i].data < searchValue) {
                    this.nextNull = false;
                    this.dataNode = ptr.next[i].nodes[0];
                    this.nextData = (Integer)ptr.next[i].data;
                    this.dataItem = new Integer(searchValue);
                    if ((Integer)ptr.next[i].data < searchValue) {
                        this.dataCondition = true;
                    }
                    else {
                        this.dataCondition = false;
                    }
                    ptr.nextNodesLines[i + 1].setColor(Color.red);
                    this.ptrNode = ptr.next[i].nodes[0];
                    ptr = ptr.next[i];
                    ptr.nodes[i + 1].setBackgroundColor(Color.red);
                    this.setPosition("5.1.2.2.1.2.1");
                    if (ptr.next[i] != null) {
                        this.nextNull = false;
                        this.dataNode = ptr.next[i].nodes[0];
                        this.nextData = (Integer)ptr.next[i].data;
                        this.dataItem = new Integer(searchValue);
                        if ((Integer)ptr.next[i].data < searchValue) {
                            this.dataCondition = true;
                        }
                        else {
                            this.dataCondition = false;
                        }
                    }
                    else {
                        this.nextNull = true;
                        this.nextData = null;
                        this.dataItem = new Integer(searchValue);
                    }
                    this.setPosition("5.1.2.2.1.1");
                }
                this.nextNull = false;
                this.displayDataCondition = false;
            }
            this.drawIPointer = false;
            this.setPosition("5.1.2.3");
            this.setPosition("5.1.3.1");
            if (ptr.next[0] != null && (Integer)ptr.next[0].data == searchValue) {
                if (!this.enabled) {
                    this.searchDataColor[this.searchNode.getIdentifier()] = SkipList.SEARCH_ACTIVE_COLOR;
                    this.recolorSkipList();
                    return;
                }
                ptr.nextNodesLines[1].setColor(Color.red);
                this.searchNode.addMarker(new StringMarker("F"));
                this.searchNode.addMarker(new StringMarker("o"));
                this.searchNode.addMarker(new StringMarker("u"));
                this.searchNode.addMarker(new StringMarker("n"));
                this.searchNode.addMarker(new StringMarker("d"));
                this.searchDataColor[this.searchNode.getIdentifier()] = SkipList.SEARCH_DONE_COLOR;
                this.setPosition("5.1.3.1.1");
                this.recolorSkipList();
                return;
            }
            else {
                this.setPosition("5.1.3.2");
                if (!this.enabled) {
                    this.searchDataColor[this.searchNode.getIdentifier()] = SkipList.SEARCH_ACTIVE_COLOR;
                    this.recolorSkipList();
                    return;
                }
                this.searchNode.addMarker(new StringMarker("N"));
                this.searchNode.addMarker(new StringMarker("o"));
                this.searchNode.addMarker(new StringMarker("t"));
                this.searchNode.addMarker(new StringMarker(" "));
                this.searchNode.addMarker(new StringMarker("F"));
                this.searchNode.addMarker(new StringMarker("o"));
                this.searchNode.addMarker(new StringMarker("u"));
                this.searchNode.addMarker(new StringMarker("n"));
                this.searchNode.addMarker(new StringMarker("d"));
                this.searchDataColor[this.searchNode.getIdentifier()] = SkipList.SEARCH_DONE_COLOR;
                this.setPosition("5.1.3.2.1");
                this.dataNode = null;
                this.recolorSkipList();
            }
        }
        this.dataNode = null;
    }
    
    protected void insert() {
        if (this.isBackMode) {
            SkipList.levels = 1;
        }
        if (this.insertData.length > 0) {
            if (this.insertPos == 0) {
                this.insertDataColor[0] = SkipList.INSERT_HIGHLIGHT_COLOR;
                final Integer newData = new Integer(-1);
                SkipList.head = new SkipNode(newData, -1, this.maxLevel);
                for (int j = 0; j < SkipList.head.nodes.length; ++j) {
                    SkipList.head.nodes[j].setBackgroundColor(SkipList.INSERT_DONE_COLOR);
                    SkipList.head.nodes[j].setMarkersBelowValue(false);
                    SkipList.head.nodes[j].removeAllMarkers();
                    SkipList.head.nodes[j].showObject(false);
                }
                this.setPosition("2.1");
            }
            if (!this.enabled) {
                return;
            }
            this.setPosition("3");
            for (int i = this.insertPos; i < this.insertData.length; ++i) {
                if (!this.enabled) {
                    return;
                }
                this.insertDataColor[i] = Color.red;
                this.setPosition("3.1");
                this.insertItem(i);
                this.insertPos = this.insertPos + 1;
                this.insertDataColor[i] = SkipList.INSERT_DONE_COLOR;
                this.setPosition("3");
            }
        }
    }
    
    protected void insertItem(final int index) {
        this.setPosition("3.1.0");
        SkipNode ptr = SkipList.head;
        this.last = new SkipNode[this.maxLevel];
        this.ptrNode = SkipList.head.nodes[0];
        this.setPosition("3.1.1.1");
        for (int i = SkipList.levels - 1; i >= 0; --i) {
            this.drawIPointer = true;
            this.iPointer = i;
            this.setPosition("3.1.2.1.1");
            ptr.nodes[i + 1].setBackgroundColor(Color.red);
            if (ptr.next[i] != null) {
                this.nextNull = false;
                this.dataNode = ptr.next[i].nodes[0];
                this.nextData = (Integer)ptr.next[i].data;
                this.dataItem = new Integer(this.insertData[index]);
                if ((Integer)ptr.next[i].data < this.insertData[index]) {
                    this.dataCondition = true;
                }
                else {
                    this.dataCondition = false;
                }
            }
            else {
                this.nextData = null;
                this.dataItem = new Integer(this.insertData[index]);
                this.nextNull = true;
            }
            this.displayDataCondition = true;
            this.setPosition("3.1.2.2.1.1");
            while (ptr.next[i] != null && (Integer)ptr.next[i].data < this.insertData[index]) {
                this.nextNull = false;
                this.dataNode = ptr.next[i].nodes[0];
                this.nextData = (Integer)ptr.next[i].data;
                this.dataItem = new Integer(this.insertData[index]);
                if ((Integer)ptr.next[i].data < this.insertData[index]) {
                    this.dataCondition = true;
                }
                else {
                    this.dataCondition = false;
                }
                ptr.nextNodesLines[i + 1].setColor(Color.red);
                this.ptrNode = ptr.next[i].nodes[0];
                ptr = ptr.next[i];
                ptr.nodes[i + 1].setBackgroundColor(Color.red);
                this.setPosition("3.1.2.2.1.2.1");
                if (ptr.next[i] != null) {
                    this.nextNull = false;
                    this.dataNode = ptr.next[i].nodes[0];
                    this.nextData = (Integer)ptr.next[i].data;
                    this.dataItem = new Integer(this.insertData[index]);
                    if ((Integer)ptr.next[i].data < this.insertData[index]) {
                        this.dataCondition = true;
                    }
                    else {
                        this.dataCondition = false;
                    }
                }
                else {
                    this.nextNull = true;
                    this.nextData = null;
                    this.dataItem = new Integer(this.insertData[index]);
                }
                this.setPosition("3.1.2.2.1.1");
            }
            this.nextNull = false;
            this.dataNode = null;
            this.displayDataCondition = false;
            this.last[i] = ptr;
            this.last[i].nodes[i + 1].setBackgroundColor(Color.red);
            this.last[i].nodes[i + 1].highlight();
            this.setPosition("3.1.2.2.1.4");
        }
        this.drawIPointer = false;
        this.setPosition("3.1.2.3");
        this.newNodeLevel = this.calculateLevels();
        if (this.newNodeLevel > SkipList.levels) {
            if (index != 0) {
                this.newNodeLevel = (SkipList.levels = SkipList.levels + 1);
                this.last[this.newNodeLevel - 1] = SkipList.head;
                this.last[this.newNodeLevel - 1].nodes[this.newNodeLevel].setBackgroundColor(Color.red);
                this.last[this.newNodeLevel - 1].nodes[this.newNodeLevel].highlight();
            }
            else if (index == 0) {
                this.newNodeLevel = SkipList.levels;
            }
        }
        this.drawBoxLevel = true;
        this.setPosition("3.1.3.1.1");
        this.drawBoxLevel = false;
        this.drawBoxFinalLevel = true;
        final Integer newData = new Integer(this.insertData[index]);
        this.newElement = new SkipNode(newData, index, this.newNodeLevel);
        this.newElement.nodes[0].setBackgroundColor(SkipList.LIST_ACTIVE_COLOR);
        for (int j = 1; j < this.newElement.nodes.length; ++j) {
            this.newElement.nodes[j].setBackgroundColor(SkipList.LIST_NULL_COLOR);
        }
        this.newElem = new SkipNode(newData, index, this.newNodeLevel);
        this.newElem.nodes[0].setBackgroundColor(SkipList.LIST_ACTIVE_COLOR);
        for (int q = 1; q < this.newElem.nodes.length; ++q) {
            this.newElem.nodes[q].setBackgroundColor(Color.white);
        }
        this.drawNewElement = true;
        this.setPosition("3.1.3.2.1");
        if (!this.enabled) {
            this.ptrNode = null;
            this.drawBoxFinalLevel = false;
            this.drawNewElement = false;
            this.recolorSkipList();
            return;
        }
        this.newElement.nodes[0].setIsVisible(false);
        for (int k = 0; k < this.newNodeLevel; ++k) {
            this.newElement.next[k] = this.last[k].next[k];
            this.newElement.nodes[k + 1].setIsVisible(false);
            this.newElement.nextNodesLines[k + 1].setColor(Color.white);
            this.newElement.nextNodesLines[k + 1].setArrowHeadWidth(0);
            this.last[k].next[k] = this.newElement;
            this.last[k].nextNodesLines[k + 1].setColor(Color.white);
            this.last[k].nextNodesLines[k + 1].setArrowHeadWidth(0);
        }
        if (!this.enabled) {
            this.ptrNode = null;
            this.drawBoxFinalLevel = false;
            this.drawNewElement = false;
            this.recolorSkipList();
            return;
        }
        for (int k = 0; k < this.newNodeLevel; ++k) {
            this.drawJPointer = true;
            this.jPointer = k;
            this.setPosition("3.1.3.3.1");
            if (k == 0) {
                this.newElement.nodes[0].setIsVisible(true);
                int m = 0;
                while (m < this.newNodeLevel) {
                    this.newElement.nodes[m + 1].setBackgroundColor(Color.white);
                    this.newElement.nodes[m + 1].setIsVisible(true);
                    ++m;
                }
            }
            this.newElement.nodes[k + 1].setBackgroundColor(Color.red);
            this.newElement.nextNodesLines[k + 1].setColor(Color.red);
            this.newElement.nextNodesLines[k + 1].setArrowHeadWidth(5);
            this.newElement.nextNodesLines[k + 1].showAsDotted(false);
            this.last[k].nextNodesLines[k + 1].setColor(Color.red);
            this.last[k].nextNodesLines[k + 1].setArrowHeadWidth(5);
            this.last[k].nextNodesLines[k + 1].showAsDotted(false);
            if (k == 0) {
                this.runTween = true;
            }
            else {
                this.runTween = false;
            }
            this.setPosition("3.1.3.3.2.1");
            if (this.runTween) {
                this.runTween = false;
            }
        }
        this.drawJPointer = false;
        this.drawBoxFinalLevel = false;
        this.drawNewElement = false;
        if (!this.enabled) {
            this.ptrNode = null;
            this.drawBoxFinalLevel = false;
            this.drawNewElement = false;
            this.recolorSkipList();
            return;
        }
        this.recolorSkipList();
        this.newElement.nodes[0].setBackgroundColor(SkipList.LIST_DONE_COLOR);
        this.setPosition("3.1.3.3.3");
        if (!this.enabled) {
            this.ptrNode = null;
            this.drawBoxFinalLevel = false;
            this.drawNewElement = false;
            this.recolorSkipList();
            return;
        }
        this.ptrNode = null;
    }
    
    protected void recolorSkipList() {
        for (int i = 0; i < this.newNodeLevel; ++i) {
            this.last[i].nodes[i + 1].unHighlight();
        }
        for (SkipNode recolor = SkipList.head.next[0]; recolor != null; recolor = recolor.next[0]) {
            int p = 1;
            while (p < recolor.nodes.length) {
                recolor.nodes[p].setBackgroundColor(SkipList.LIST_NULL_COLOR);
                recolor.nextNodesLines[p].setColor(SkipList.TEXT_COLOR);
                ++p;
            }
        }
        for (int p = 1; p < SkipList.head.nodes.length; ++p) {
            SkipList.head.nodes[p].setBackgroundColor(SkipList.INSERT_DONE_COLOR);
            SkipList.head.nextNodesLines[p].setColor(SkipList.TEXT_COLOR);
        }
    }
    
    public void controlStep(final ControlEvent e) {
        this.isBackMode = false;
    }
    
    public void controlBack(final ControlEvent e) {
        this.isBackMode = true;
        SkipList.needToResetSeed = true;
    }
    
    public void controlPause(final ControlEvent e) {
        this.isBackMode = false;
    }
    
    public void controlReset(final ControlEvent e) {
        this.isBackMode = false;
        SkipList.needToResetSeed = true;
    }
    
    public void controlRestart(final ControlEvent e) {
        this.isBackMode = false;
        SkipList.needToResetSeed = true;
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
        LIST_ACTIVE_COLOR = Color.pink;
        LIST_NULL_COLOR = Color.orange;
        LIST_DONE_COLOR = SkipList.INSERT_HIGHLIGHT_COLOR;
        NODE_HIGHLIGHT_COLOR = Color.green;
        NODE_BORDER_COLOR = Color.black;
        BUILD_MODE_LABEL = Messages.getString("SkipList.0");
        SEARCH_MODE_LABEL = Messages.getString("SkipList.1");
        DELETE_MODE_LABEL = Messages.getString("SkipList.2");
        SkipList.levels = 1;
        SkipList.needToResetSeed = false;
    }
}
