import localization.*;
import java.util.*;
import com.cim.common.*;
import com.cim.AIA.*;

public class RadixTrieMulti extends Algorithm implements MethodSelectionListener, ControlListener
{
    public static int NO_OF_NODES;
    public static int NO_OF_BITS;
    public static RadioMenu mnuLink;
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
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
    private Vector<E> insertedData;
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
    
    public RadixTrieMulti(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.isBackMode = false;
        this.savedTree = new RadixTrieMultiNullNode(null);
        this.head = new RadixTrieMultiNullNode(null);
        this.mostSignificantBit = 0;
        this.currentLevel = -1;
        this.executingMode = 11;
        this.nextMode = this.executingMode;
        this.changeData(data);
        this.insertData = this.newInsertData;
        this.searchData = this.newSearchData;
    }
    
    public void reInitialise(final Object data) {
        this.changeData(data);
        this.mostSignificantBit = 0;
        if (this.executingMode == 11 || this.nextMode == 11 || this.insertData == null) {
            this.insertData = this.newInsertData;
        }
        this.searchData = this.newSearchData;
        String binaryString;
        for (int i = 0; i < this.insertData.getSize(); ++i) {
            binaryString = Integer.toBinaryString(((Integer)this.insertData.getElement(i).getObject()).intValue());
            if (this.mostSignificantBit < binaryString.length()) {
                this.mostSignificantBit = binaryString.length();
            }
        }
        for (int i = 0; i < this.searchData.getSize(); ++i) {
            binaryString = Integer.toBinaryString(((Integer)this.searchData.getElement(i).getObject()).intValue());
            if (this.mostSignificantBit < binaryString.length()) {
                this.mostSignificantBit = binaryString.length();
            }
        }
        int tempC = this.mostSignificantBit / RadixTrieMulti.NO_OF_BITS;
        if (this.mostSignificantBit % RadixTrieMulti.NO_OF_BITS > 0) {
            ++tempC;
        }
        this.mostSignificantBit = tempC * RadixTrieMulti.NO_OF_BITS;
        this.mostSignificantBit = this.mostSignificantBit - 1;
        if (this.executingMode == 11 || this.nextMode == 11) {
            this.savedTree = new RadixTrieMultiNullNode(null);
            this.insertPos = 0;
            this.searchPos = 0;
        }
        else if (this.executingMode == 12 || this.nextMode == 12) {
            this.searchPos = 0;
        }
        this.head = this.savedTree;
        this.insertedData = new Vector();
        this.radixTrieToVector(this.insertedData, this.head);
        this.currentLevel = -1;
    }
    
    protected void changeData(final Object data) {
        final Random random = new Random();
        this.newInsertData = new ElementArray(0, 0);
        this.newInsertData.setColumGap(0);
        this.newInsertData.setElementWidth(20);
        this.newSearchData = new ElementArray(0, 0);
        this.newSearchData.setColumGap(0);
        this.newSearchData.setElementWidth(20);
        for (int i = 0; i < ((int[])((int[])data)).length; ++i) {
            this.newInsertData.add(new Node(new Integer(((int[])((int[])data))[i]), i), i);
            ((Node)this.newInsertData.getElement(i)).setBackgroundColor(RadixTrieMultiColors.INSERT_ACTIVE_COLOR);
            if (!this.isBackMode) {
                if (i % 2 == 0) {
                    this.newSearchData.add(new Node(new Integer(Math.abs(random.nextInt()) % 98 + 1), i), i);
                }
                else {
                    this.newSearchData.add(new Node(new Integer(((int[])((int[])data))[i]), i), i);
                }
            }
            else {
                final Integer orgInteger = (Integer)this.searchData.getElement(i).getObject();
                final int orgValue = orgInteger.intValue();
                this.newSearchData.add(new Node(new Integer(orgValue), i), i);
            }
            ((Node)this.newSearchData.getElement(i)).setBackgroundColor(RadixTrieMultiColors.SEARCH_ACTIVE_COLOR);
        }
    }
    
    protected void initialiseMethods(final String string, final MethodSelectable methodSelectable) {
        final MethodSelection methodSelection1 = new MethodSelection(RadixTrieMulti.BUILD_MODE_LABEL, string, Messages.getString("RadixTrieMulti.2"), "3a", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection2 = new MethodSelection(RadixTrieMulti.SEARCH_MODE_LABEL, string, Messages.getString("RadixTrieMulti.3"), "5a", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (RadixTrieMulti.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
            RadixTrieMulti.mnuLink.setEnabled(true);
        }
        else if (RadixTrieMulti.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 12;
            RadixTrieMulti.mnuLink.setEnabled(false);
        }
    }
    
    private RadixTrieMultiDigitalElementArray initialiseBitElementArray(final int number) {
        return new RadixTrieMultiDigitalElementArray(number, this.mostSignificantBit);
    }
    
    public RadixTrieMultiNode getHead() {
        return this.head;
    }
    
    public Vector<E> getInsertedData() {
        return this.insertedData;
    }
    
    public RadixTrieMultiDigitalElementArray getInsertBitArray() {
        if (this.executingMode == 11) {
            return this.insertDataBitArray;
        }
        return null;
    }
    
    public RadixTrieMultiDigitalElementArray getSearchBitArray() {
        if (this.executingMode == 12) {
            return this.searchDataBitArray;
        }
        return null;
    }
    
    public RadixTrieMultiDigitalElementArray getFinalCompareBitArray() {
        if (this.executingMode == 12) {
            return this.finalCompareDataBitArray;
        }
        return null;
    }
    
    public RadixTrieMultiDigitalElementArray getCompareBitArray() {
        return this.compareDataBitArray;
    }
    
    public ElementArray getInsertData() {
        if (this.executingMode == 11) {
            return this.insertData;
        }
        return null;
    }
    
    public ElementArray getSearchData() {
        if (this.executingMode == 12) {
            return this.searchData;
        }
        return null;
    }
    
    public RadixTrieMultiNode getCurrentNode() {
        this.unHighlightPath(this.head);
        this.highlightPath(this.pathNode);
        return this.currentNode;
    }
    
    public int getMostSignificantBit() {
        return this.mostSignificantBit;
    }
    
    public int getCurrentLevel() {
        return this.currentLevel;
    }
    
    private void unHighlightPath(final RadixTrieMultiNode node) {
        if (node != null) {
            node.setIsOnPath(false);
            if (node instanceof RadixTrieMultiInternalNode) {
                for (int i = 0; i < RadixTrieMulti.NO_OF_NODES; ++i) {
                    this.unHighlightPath(((RadixTrieMultiInternalNode)node).getChild(i));
                }
            }
        }
    }
    
    private void highlightPath(final RadixTrieMultiNode node) {
        if (node != null) {
            node.setIsOnPath(true);
            this.highlightPath(node.getParent());
        }
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
    
    private void printTrie(final RadixTrieMultiNode trie) {
        if (trie instanceof RadixTrieMultiInternalNode) {
            for (int i = 0; i < RadixTrieMulti.NO_OF_NODES; ++i) {
                this.printTrie(((RadixTrieMultiInternalNode)trie).getChild(i));
            }
        }
        if (trie instanceof RadixTrieMultiExternalNode) {
            System.out.println(((RadixTrieMultiExternalNode)trie).getKey() + " " + Integer.toBinaryString(((RadixTrieMultiExternalNode)trie).getKey()));
        }
    }
    
    private void radixTrieToVector(final Vector<E> vector, final RadixTrieMultiNode node) {
        if (node instanceof RadixTrieMultiInternalNode) {
            for (int i = 0; i < RadixTrieMulti.NO_OF_NODES; ++i) {
                this.radixTrieToVector(vector, ((RadixTrieMultiInternalNode)node).getChild(i));
            }
        }
        else if (node instanceof RadixTrieMultiExternalNode) {
            vector.addElement(node);
        }
    }
    
    protected void run(final boolean canChangeMode) {
        if (canChangeMode) {
            this.executingMode = this.nextMode;
        }
        this.run();
    }
    
    protected void run() {
        switch (this.executingMode) {
            case 11: {
                this.insertedData = new Vector();
                this.radixTrieToVector(this.insertedData, this.head);
                for (int i = this.insertPos; i < this.insertData.getSize(); ++i) {
                    final DataItem dataItem = new DataItem(this.insertData.getElement(i).getObject().toString(), ((Integer)this.insertData.getElement(i).getObject()).intValue());
                    this.insertDataBitArray = this.initialiseBitElementArray(dataItem.getKey());
                    this.insertDataBitArray.setColors(RadixTrieMultiColors.INSERT_BIT_ACTIVE_COLOR, RadixTrieMultiColors.INSERT_BIT_HIGHLIGHT_COLOR);
                    try {
                        if (this.enabled) {
                            ((Node)this.insertData.getElement(i)).setBackgroundColor(RadixTrieMultiColors.INSERT_HIGHLIGHT_COLOR);
                            this.head = this.insert(this.head, dataItem, 0, true, i);
                            this.clearPointers();
                            RadixTrieMultiExternalNode.clearNewestNode();
                            RadixTrieMultiExternalNode.clearCompareNode();
                            this.insertedData = new Vector();
                            this.radixTrieToVector(this.insertedData, this.head);
                            this.insertPos = this.insertPos + 1;
                            ((Node)this.insertData.getElement(i)).setBackgroundColor(RadixTrieMultiColors.INSERT_DONE_COLOR);
                        }
                    }
                    catch (Exception e) {
                        this.clearPointers();
                        System.out.println(e + "Already inserted exception caught");
                    }
                    this.insertDataBitArray = null;
                }
                break;
            }
            case 12: {
                for (int i = this.searchPos; i < this.searchData.getSize(); ++i) {
                    final Node searchNode = (Node)this.searchData.getElement(i);
                    final DataItem dataItem = new DataItem(searchNode.getObject().toString(), ((Integer)searchNode.getObject()).intValue());
                    this.searchDataBitArray = this.initialiseBitElementArray(dataItem.getKey());
                    this.searchDataBitArray.setColors(RadixTrieMultiColors.SEARCH_BIT_ACTIVE_COLOR, RadixTrieMultiColors.SEARCH_BIT_HIGHLIGHT_COLOR);
                    if (this.enabled) {
                        searchNode.setBackgroundColor(RadixTrieMultiColors.SEARCH_HIGHLIGHT_COLOR);
                        if (this.search(this.head, dataItem, 0)) {
                            searchNode.addMarker(new StringMarker("F"));
                            searchNode.addMarker(new StringMarker("o"));
                            searchNode.addMarker(new StringMarker("u"));
                            searchNode.addMarker(new StringMarker("n"));
                            searchNode.addMarker(new StringMarker("d"));
                        }
                        else {
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
                        this.clearPointers();
                        RadixTrieMultiExternalNode.clearCompareNode();
                        this.insertedData = new Vector();
                        this.radixTrieToVector(this.insertedData, this.head);
                        searchNode.setBackgroundColor(RadixTrieMultiColors.SEARCH_DONE_COLOR);
                        this.searchPos = this.searchPos + 1;
                    }
                    this.searchDataBitArray = null;
                }
                break;
            }
        }
    }
    
    private RadixTrieMultiNode insert(RadixTrieMultiNode trie, final DataItem dataItem, final int level, final boolean isShowRecursion, final int index) throws Exception {
        this.currentNode = trie;
        this.pathNode = this.currentNode;
        this.setPosition("3.0");
        if (trie == null) {
            throw new RuntimeException("trie should not be null");
        }
        this.setPosition("3.1.1");
        RadixTrieMultiExternalNode newNode;
        if (trie instanceof RadixTrieMultiNullNode) {
            newNode = new RadixTrieMultiExternalNode(dataItem);
            newNode.setIsLinkedIn();
            newNode.setLevel(0);
            this.pathNode = newNode;
            final RadixTrieMultiExternalNode radixTrieMultiExternalNode = newNode;
            this.head = radixTrieMultiExternalNode;
            this.currentNode = radixTrieMultiExternalNode;
            this.setPosition("3.1.1.1");
            return newNode;
        }
        this.currentLevel = 0;
        this.insertDataBitArray.unHighlight();
        int i;
        for (i = 0; i < RadixTrieMulti.NO_OF_BITS; ++i) {
            this.insertDataBitArray.highlightBit((byte)(this.currentLevel + i));
        }
        this.setPosition("3.2.2");
        int branch;
        while (this.currentNode instanceof RadixTrieMultiInternalNode) {
            this.setPosition("3.3.1");
            branch = this.getBranch(this.currentLevel, dataItem.getKey());
            this.setPosition("3.3.1.2.1");
            this.currentNode = ((RadixTrieMultiInternalNode)this.currentNode).getChild(branch);
            this.pathNode = this.currentNode;
            this.setPosition("3.3.1.2.2");
            if (this.currentNode instanceof RadixTrieMultiInternalNode) {
                this.currentLevel = this.currentLevel + RadixTrieMulti.NO_OF_BITS;
                this.insertDataBitArray.unHighlight();
                for (i = 0; i < RadixTrieMulti.NO_OF_BITS; ++i) {
                    this.insertDataBitArray.highlightBit((byte)(this.currentLevel + i));
                }
                this.setPosition("3.3.1.3");
            }
            else {
                this.currentLevel = this.currentLevel + RadixTrieMulti.NO_OF_BITS;
                this.setPosition("3.3.1.3");
            }
        }
        this.setPosition("3.3.1");
        this.setPosition("3.3.2");
        this.pathNode = this.currentNode.getParent();
        if (this.currentNode instanceof RadixTrieMultiNullNode) {
            this.setPosition("3.4.1");
            newNode = new RadixTrieMultiExternalNode(dataItem);
            branch = this.getBranch(this.currentLevel - RadixTrieMulti.NO_OF_BITS, dataItem.getKey());
            this.currentNode.getParent().setChild(newNode, branch);
            newNode.setLevel(this.currentLevel);
            newNode.setIsLinkedIn();
            this.setPosition("3.4.1.1");
            return trie;
        }
        this.setPosition("3.4.1");
        this.setPosition("3.4.2");
        final int trieItem = ((RadixTrieMultiExternalNode)this.currentNode).getKey();
        if (trieItem == dataItem.getKey()) {
            this.setPosition("3.4.2.1");
            this.setPosition("3.4.2.2");
            this.insertDataBitArray.addMarker(new StringMarker(Messages.getString("RadixTrieMulti.4")));
            this.compareDataBitArray = null;
            this.setPosition("3.4.2.2.1");
            this.insertDataBitArray.removeAllMarkers();
            return trie;
        }
        this.setPosition("3.4.2.1");
        this.compareDataBitArray = this.initialiseBitElementArray(trieItem);
        this.compareDataBitArray.setColors(RadixTrieMultiColors.COMPARE_BIT_ACTIVE_COLOR, RadixTrieMultiColors.COMPARE_BIT_HIGHLIGHT_COLOR);
        RadixTrieMultiExternalNode.setCompareNode((RadixTrieMultiExternalNode)this.currentNode);
        final RadixTrieMultiInternalNode returnNode = new RadixTrieMultiInternalNode();
        returnNode.parent = null;
        final RadixTrieMultiExternalNode trieNode = (RadixTrieMultiExternalNode)this.currentNode;
        branch = this.getBranch(this.currentLevel - RadixTrieMulti.NO_OF_BITS, dataItem.getKey());
        if (this.currentNode.getParent() != null) {
            this.currentNode.getParent().setChild(returnNode, branch);
        }
        else {
            final RadixTrieMultiInternalNode head = returnNode;
            this.head = head;
            trie = head;
        }
        returnNode.setIsLinkedIn();
        RadixTrieMultiInternalNode ptr = returnNode;
        this.currentNode = ptr;
        this.compareDataBitArray.unHighlight();
        this.insertDataBitArray.unHighlight();
        int j;
        for (j = 0; j < RadixTrieMulti.NO_OF_BITS; ++j) {
            this.compareDataBitArray.highlightBit((byte)(this.currentLevel + j));
            this.insertDataBitArray.highlightBit((byte)(this.currentLevel + j));
        }
        this.setPosition("3.5.1");
        while (this.getBranch(this.currentLevel, trieItem) == this.getBranch(this.currentLevel, dataItem.getKey())) {
            this.setPosition("3.5.2");
            branch = this.getBranch(this.currentLevel, dataItem.getKey());
            final RadixTrieMultiInternalNode newInternal = new RadixTrieMultiInternalNode();
            this.pathNode = newInternal;
            ptr.setChild(newInternal, branch);
            ptr = (RadixTrieMultiInternalNode)ptr.getChild(branch);
            newInternal.setIsLinkedIn();
            this.currentNode = ptr;
            this.setPosition("3.5.2.1.1");
            this.currentLevel = this.currentLevel + RadixTrieMulti.NO_OF_BITS;
            this.compareDataBitArray.unHighlight();
            this.insertDataBitArray.unHighlight();
            for (j = 0; j < RadixTrieMulti.NO_OF_BITS; ++j) {
                this.compareDataBitArray.highlightBit((byte)(this.currentLevel + j));
                this.insertDataBitArray.highlightBit((byte)(this.currentLevel + j));
            }
            this.setPosition("3.5.2.1.2");
        }
        this.setPosition("3.5.2");
        this.setPosition("3.5.3");
        branch = this.getBranch(this.currentLevel, trieItem);
        this.setPosition("3.4.2.1.2.1");
        ptr.setChild(trieNode, branch);
        trieNode.setLevel(this.currentLevel + RadixTrieMulti.NO_OF_BITS);
        this.insertedData = new Vector();
        this.radixTrieToVector(this.insertedData, this.head);
        this.setPosition("3.4.2.1.2.2");
        branch = this.getBranch(this.currentLevel, dataItem.getKey());
        this.setPosition("3.4.2.1.3.1");
        final RadixTrieMultiExternalNode newExternal = new RadixTrieMultiExternalNode(dataItem);
        newExternal.setLevel(this.currentLevel + RadixTrieMulti.NO_OF_BITS);
        newExternal.setIsLinkedIn();
        ptr.setChild(newExternal, branch);
        this.insertedData = new Vector();
        this.radixTrieToVector(this.insertedData, this.head);
        this.setPosition("3.4.2.1.3.2");
        this.compareDataBitArray.unHighlight();
        this.insertDataBitArray.unHighlight();
        this.setPosition("3.4.2.1.4");
        return trie;
    }
    
    private boolean search(final RadixTrieMultiNode trie, final DataItem dataItem, final int level) {
        this.setPosition("5");
        this.currentLevel = 0;
        this.searchDataBitArray.unHighlight();
        int i;
        for (i = 0; i < RadixTrieMulti.NO_OF_BITS; ++i) {
            this.searchDataBitArray.highlightBit((byte)(this.currentLevel + i));
        }
        this.currentNode = trie;
        this.pathNode = this.currentNode;
        this.setPosition("5.1.1");
        while (this.currentNode instanceof RadixTrieMultiInternalNode) {
            this.setPosition("5.1.2");
            final int branch = this.getBranch(this.currentLevel, dataItem.getKey());
            this.setPosition("5.1.2.1.1");
            this.currentNode = ((RadixTrieMultiInternalNode)this.currentNode).getChild(branch);
            this.pathNode = this.currentNode;
            this.setPosition("5.1.2.1.2");
            this.currentLevel = this.currentLevel + RadixTrieMulti.NO_OF_BITS;
            this.searchDataBitArray.unHighlight();
            for (i = 0; i < RadixTrieMulti.NO_OF_BITS; ++i) {
                this.searchDataBitArray.highlightBit((byte)(this.currentLevel + i));
            }
            this.setPosition("5.1.2.2");
        }
        this.setPosition("5.1.2");
        this.setPosition("5.1.3");
        if (this.currentNode instanceof RadixTrieMultiExternalNode) {
            this.finalCompareDataBitArray = new RadixTrieMultiDigitalElementArray(((RadixTrieMultiExternalNode)this.currentNode).getKey(), this.mostSignificantBit, false);
            this.finalCompareDataBitArray.setColors(RadixTrieMultiColors.COMPARE_BIT_HIGHLIGHT_COLOR, RadixTrieMultiColors.COMPARE_BIT_HIGHLIGHT_COLOR);
        }
        if (this.currentNode instanceof RadixTrieMultiExternalNode && ((RadixTrieMultiExternalNode)this.currentNode).getKey() == dataItem.getKey()) {
            this.setPosition("5.2.1");
            this.searchDataBitArray.unHighlight();
            this.setPosition("5.2.1.1");
            return true;
        }
        this.setPosition("5.2.1");
        this.setPosition("5.2.2");
        this.searchDataBitArray.unHighlight();
        this.setPosition("5.2.2.1");
        return false;
    }
    
    private void save() {
        this.savedTree = this.head;
    }
    
    protected void storeState(final boolean forceState) {
        this.save();
    }
    
    protected void clearState() {
        this.head = new RadixTrieMultiNullNode(null);
    }
    
    public int getBranch(final int bitNumber, final int number) {
        return (number >> this.mostSignificantBit - bitNumber - (RadixTrieMulti.NO_OF_BITS - 1)) % RadixTrieMulti.NO_OF_NODES;
    }
    
    private void setPosition(final String string, final boolean isPause) {
        if (isPause) {
            this.setPosition(string);
        }
        else {
            this.setPosition(Messages.getString("RadixTrieMulti.5"));
        }
    }
    
    private void clearPointers() {
        this.currentNode = null;
        this.pathNode = this.currentNode;
        this.insertDataBitArray = null;
        this.searchDataBitArray = null;
        this.compareDataBitArray = null;
        this.finalCompareDataBitArray = null;
        this.insertedData = new Vector();
        this.radixTrieToVector(this.insertedData, this.head);
        this.currentLevel = -1;
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
        RadixTrieMulti.NO_OF_NODES = 4;
        RadixTrieMulti.NO_OF_BITS = 2;
        RadixTrieMulti.mnuLink = null;
        BUILD_MODE_LABEL = Messages.getString("RadixTrieMulti.0");
        SEARCH_MODE_LABEL = Messages.getString("RadixTrieMulti.1");
    }
}
