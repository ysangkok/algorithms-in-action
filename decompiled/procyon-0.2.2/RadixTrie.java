import localization.*;
import java.util.*;
import com.cim.AIA.*;

public class RadixTrie extends Algorithm implements MethodSelectionListener, ControlListener
{
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    public boolean isBackMode;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private RadixTrieDigitalElementArray insertDataBitArray;
    private RadixTrieDigitalElementArray searchDataBitArray;
    private RadixTrieDigitalElementArray compareDataBitArray;
    private Vector<E> insertedData;
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
    
    public RadixTrie(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.isBackMode = false;
        this.savedTree = new RadixTrieNullNode(null);
        this.head = new RadixTrieNullNode(null);
        this.mostSignificantBit = 0;
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
        this.mostSignificantBit = this.mostSignificantBit - 1;
        if (this.executingMode == 11) {
            this.savedTree = new RadixTrieNullNode(null);
            this.insertPos = 0;
        }
        else if (this.executingMode == 12) {
            this.searchPos = 0;
        }
        this.head = this.savedTree;
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
            ((Node)this.newInsertData.getElement(i)).setBackgroundColor(RadixTrieColors.INSERT_ACTIVE_COLOR);
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
            ((Node)this.newSearchData.getElement(i)).setBackgroundColor(RadixTrieColors.SEARCH_ACTIVE_COLOR);
        }
    }
    
    protected void initialiseMethods(final String string, final MethodSelectable methodSelectable) {
        final MethodSelection methodSelection1 = new MethodSelection(RadixTrie.BUILD_MODE_LABEL, string, Messages.getString("RadixTrie.2"), "3a", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection2 = new MethodSelection(RadixTrie.SEARCH_MODE_LABEL, string, Messages.getString("RadixTrie.4"), "5a", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (RadixTrie.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
        else if (RadixTrie.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 12;
        }
    }
    
    private RadixTrieDigitalElementArray initialiseBitElementArray(final int number) {
        return new RadixTrieDigitalElementArray(number, this.mostSignificantBit);
    }
    
    public RadixTrieNode getHead() {
        return this.head;
    }
    
    public Vector<E> getInsertedData() {
        return this.insertedData;
    }
    
    public RadixTrieDigitalElementArray getInsertBitArray() {
        if (this.executingMode == 11) {
            return this.insertDataBitArray;
        }
        return null;
    }
    
    public RadixTrieDigitalElementArray getSearchBitArray() {
        if (this.executingMode == 12) {
            return this.searchDataBitArray;
        }
        return null;
    }
    
    public RadixTrieDigitalElementArray getCompareBitArray() {
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
    
    public RadixTrieNode getCurrentNode() {
        this.unHighlightPath(this.head);
        this.highlightPath(this.pathNode);
        return this.currentNode;
    }
    
    public int getMostSignificantBit() {
        return this.mostSignificantBit;
    }
    
    private void unHighlightPath(final RadixTrieNode node) {
        if (node != null) {
            node.setIsOnPath(false);
            if (node instanceof RadixTrieInternalNode) {
                this.unHighlightPath(((RadixTrieInternalNode)node).getLeft());
                this.unHighlightPath(((RadixTrieInternalNode)node).getRight());
            }
        }
    }
    
    private void highlightPath(final RadixTrieNode node) {
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
    
    private void printTrie(final RadixTrieNode trie) {
        if (trie instanceof RadixTrieInternalNode) {
            this.printTrie(((RadixTrieInternalNode)trie).getLeft());
            this.printTrie(((RadixTrieInternalNode)trie).getRight());
        }
        if (trie instanceof RadixTrieExternalNode) {
            System.out.println(((RadixTrieExternalNode)trie).getKey() + " " + Integer.toBinaryString(((RadixTrieExternalNode)trie).getKey()));
        }
    }
    
    private void radixTrieToVector(final Vector<E> vector, final RadixTrieNode node) {
        if (node instanceof RadixTrieInternalNode) {
            this.radixTrieToVector(vector, ((RadixTrieInternalNode)node).getLeft());
            this.radixTrieToVector(vector, ((RadixTrieInternalNode)node).getRight());
        }
        else if (node instanceof RadixTrieExternalNode) {
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
                    final RadixTrieDataItem dataItem = new RadixTrieDataItem(this.insertData.getElement(i).getObject().toString(), ((Integer)this.insertData.getElement(i).getObject()).intValue());
                    this.insertDataBitArray = this.initialiseBitElementArray(dataItem.getKey());
                    this.insertDataBitArray.setColors(RadixTrieColors.INSERT_BIT_ACTIVE_COLOR, RadixTrieColors.INSERT_BIT_HIGHLIGHT_COLOR);
                    try {
                        if (this.enabled) {
                            ((Node)this.insertData.getElement(i)).setBackgroundColor(RadixTrieColors.INSERT_HIGHLIGHT_COLOR);
                            this.head = this.insert(this.head, dataItem, 0, true);
                            this.clearPointers();
                            RadixTrieExternalNode.clearNewestNode();
                            RadixTrieExternalNode.clearCompareNode();
                            this.insertedData = new Vector();
                            this.radixTrieToVector(this.insertedData, this.head);
                            this.insertPos = this.insertPos + 1;
                            ((Node)this.insertData.getElement(i)).setBackgroundColor(RadixTrieColors.INSERT_DONE_COLOR);
                        }
                    }
                    catch (Exception e) {
                        this.clearPointers();
                        System.out.println("Already inserted exception caught");
                    }
                    this.insertDataBitArray = null;
                }
                break;
            }
            case 12: {
                for (int i = this.searchPos; i < this.searchData.getSize(); ++i) {
                    final Node searchNode = (Node)this.searchData.getElement(i);
                    final RadixTrieDataItem dataItem = new RadixTrieDataItem(searchNode.getObject().toString(), ((Integer)searchNode.getObject()).intValue());
                    this.searchDataBitArray = this.initialiseBitElementArray(dataItem.getKey());
                    this.searchDataBitArray.setColors(RadixTrieColors.SEARCH_BIT_ACTIVE_COLOR, RadixTrieColors.SEARCH_BIT_HIGHLIGHT_COLOR);
                    if (this.enabled) {
                        searchNode.setBackgroundColor(RadixTrieColors.SEARCH_HIGHLIGHT_COLOR);
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
                        RadixTrieExternalNode.clearCompareNode();
                        this.insertedData = new Vector();
                        this.radixTrieToVector(this.insertedData, this.head);
                        searchNode.setBackgroundColor(RadixTrieColors.SEARCH_DONE_COLOR);
                        this.searchPos = this.searchPos + 1;
                    }
                    this.searchDataBitArray = null;
                }
                break;
            }
        }
    }
    
    private RadixTrieNode insert(final RadixTrieNode trie, final RadixTrieDataItem dataItem, final int level, final boolean isShowRecursion) throws Exception {
        this.currentNode = trie;
        this.pathNode = this.currentNode;
        this.setPosition("3.1", isShowRecursion);
        if (trie == null) {
            throw new RuntimeException("trie should not be null");
        }
        if (trie instanceof RadixTrieNullNode) {
            this.setPosition("3.1.1", isShowRecursion);
            final RadixTrieExternalNode newNode = new RadixTrieExternalNode(dataItem);
            newNode.setIsLinkedIn();
            newNode.setLevel(level);
            this.pathNode = newNode;
            return newNode;
        }
        this.setPosition("3.2", isShowRecursion);
        if (!(trie instanceof RadixTrieExternalNode)) {
            this.insertDataBitArray.highlightBit((byte)level);
            this.setPosition("3.3", isShowRecursion);
            if (trie instanceof RadixTrieInternalNode) {
                this.setPosition("3.3.1", isShowRecursion);
                this.setPosition("3.3.1.1", isShowRecursion);
                if (this.getBit(level, dataItem.getKey()) == 0) {
                    this.setPosition("3.3.1.1.1", isShowRecursion);
                    this.insertDataBitArray.unHighlight();
                    ((RadixTrieInternalNode)trie).setLeft(this.insert(((RadixTrieInternalNode)trie).getLeft(), dataItem, level + 1, isShowRecursion));
                }
                else {
                    this.setPosition("3.3.1.2", isShowRecursion);
                    this.setPosition("3.3.1.2.1", isShowRecursion);
                    this.insertDataBitArray.unHighlight();
                    ((RadixTrieInternalNode)trie).setRight(this.insert(((RadixTrieInternalNode)trie).getRight(), dataItem, level + 1, isShowRecursion));
                }
            }
            this.insertedData = new Vector();
            this.radixTrieToVector(this.insertedData, this.head);
            this.currentNode = trie;
            this.setPosition("3.4", isShowRecursion);
            return trie;
        }
        this.compareDataBitArray = this.initialiseBitElementArray(((RadixTrieExternalNode)trie).getKey());
        this.compareDataBitArray.setColors(RadixTrieColors.COMPARE_BIT_ACTIVE_COLOR, RadixTrieColors.COMPARE_BIT_HIGHLIGHT_COLOR);
        RadixTrieExternalNode.setCompareNode((RadixTrieExternalNode)trie);
        this.setPosition("3.2.1.1", isShowRecursion);
        if (((RadixTrieExternalNode)trie).getKey() == dataItem.getKey()) {
            this.setPosition("3.2.1.1.1", isShowRecursion);
            this.compareDataBitArray = null;
            throw new Exception("Item already in trie");
        }
        this.setPosition("3.2.2", isShowRecursion);
        this.setPosition("3.2.2.2", isShowRecursion);
        this.setPosition("3.2.2.1", isShowRecursion);
        final RadixTrieInternalNode newNode2 = new RadixTrieInternalNode();
        if (trie.getParent() != null) {
            if (trie.getParent().getLeft() == trie) {
                trie.getParent().setLeft(newNode2);
            }
            if (trie.getParent().getRight() == trie) {
                trie.getParent().setRight(newNode2);
            }
        }
        else {
            if (trie != this.head) {
                throw new RuntimeException("This case should be unreachable");
            }
            this.head = newNode2;
        }
        this.currentNode = newNode2;
        this.pathNode = this.currentNode;
        final RadixTrieNode newInternalNode = this.makeInternalNode(dataItem, (RadixTrieExternalNode)trie, level, true, newNode2);
        this.setPosition("3.2.2.3", isShowRecursion);
        this.compareDataBitArray = null;
        newInternalNode.setIsLinkedIn();
        return newInternalNode;
    }
    
    private RadixTrieNode makeInternalNode(final RadixTrieDataItem dataItem, final RadixTrieExternalNode trie, final int level, final boolean isShowRecursion, final RadixTrieInternalNode newTrie) {
        this.compareDataBitArray.unHighlight();
        this.insertDataBitArray.unHighlight();
        this.compareDataBitArray.highlightBit((byte)level);
        this.insertDataBitArray.highlightBit((byte)level);
        this.setPosition("1111", isShowRecursion);
        this.setPosition("3.2.2.1.1");
        if (this.getBit(level, trie.getKey()) != this.getBit(level, dataItem.getKey())) {
            this.setPosition("3.2.2.1.1.2.1", isShowRecursion);
            RadixTrieExternalNode newExternalNode;
            if (this.getBit(level, trie.getKey()) == 0) {
                newTrie.setLeft(trie);
                trie.setLevel(level + 1);
                this.insertedData = new Vector();
                this.radixTrieToVector(this.insertedData, this.head);
                this.setPosition("3.2.2.1.1.2.1.1.1", isShowRecursion);
                newExternalNode = new RadixTrieExternalNode(dataItem);
                newExternalNode.setLevel(level + 1);
                newTrie.setRight(newExternalNode);
                this.insertedData = new Vector();
                this.radixTrieToVector(this.insertedData, this.head);
                this.setPosition("3.2.2.1.1.2.1.1.2", isShowRecursion);
                this.compareDataBitArray.unHighlight();
                this.insertDataBitArray.unHighlight();
                newExternalNode.setIsLinkedIn();
                this.setPosition("3.2.2.1.3");
                newTrie.setIsLinkedIn();
                this.currentNode = newTrie.getParent();
                return newTrie;
            }
            this.setPosition("3.2.2.1.1.2.2", isShowRecursion);
            if (this.getBit(level, trie.getKey()) == 1) {
                newExternalNode = new RadixTrieExternalNode(dataItem);
                newExternalNode.setLevel(level + 1);
                newExternalNode.setIsLinkedIn();
                newTrie.setLeft(newExternalNode);
                this.insertedData = new Vector();
                this.radixTrieToVector(this.insertedData, this.head);
                this.setPosition("3.2.2.1.1.2.2.1.1", isShowRecursion);
                newTrie.setRight(trie);
                trie.setLevel(level + 1);
                this.insertedData = new Vector();
                this.radixTrieToVector(this.insertedData, this.head);
                this.setPosition("3.2.2.1.1.2.2.1.2", isShowRecursion);
                this.compareDataBitArray.unHighlight();
                this.insertDataBitArray.unHighlight();
                this.setPosition("3.2.2.1.3");
                newTrie.setIsLinkedIn();
                this.currentNode = newTrie.getParent();
                return newTrie;
            }
        }
        else {
            final RadixTrieInternalNode brandNewTrie = new RadixTrieInternalNode();
            this.setPosition("3.2.2.1.2", isShowRecursion);
            this.setPosition("3.2.2.1.2.1.1", isShowRecursion);
            if (this.getBit(level, trie.getKey()) == 0) {
                this.setPosition("3.2.2.1.2.1.1.1.1", isShowRecursion);
                this.currentNode = brandNewTrie;
                this.pathNode = this.currentNode;
                newTrie.setLeft(brandNewTrie);
                newTrie.setLeft(this.makeInternalNode(dataItem, trie, level + 1, isShowRecursion, brandNewTrie));
            }
            if (this.getBit(level, trie.getKey()) == 1) {
                this.setPosition("3.2.2.1.2.1.2", isShowRecursion);
                this.setPosition("3.2.2.1.2.1.2.1.1", isShowRecursion);
                this.currentNode = brandNewTrie;
                this.pathNode = this.currentNode;
                newTrie.setRight(brandNewTrie);
                newTrie.setRight(this.makeInternalNode(dataItem, trie, level + 1, isShowRecursion, brandNewTrie));
            }
        }
        this.compareDataBitArray.unHighlight();
        this.insertDataBitArray.unHighlight();
        this.setPosition("3.2.2.1.3");
        newTrie.setIsLinkedIn();
        this.currentNode = newTrie.getParent();
        return newTrie;
    }
    
    private boolean search(final RadixTrieNode trie, final RadixTrieDataItem dataItem, final int level) {
        this.searchDataBitArray.unHighlight();
        this.searchDataBitArray.highlightBit((byte)level);
        this.currentNode = trie;
        this.pathNode = this.currentNode;
        this.setPosition("5");
        this.setPosition("5.1");
        if (trie instanceof RadixTrieNullNode) {
            return false;
        }
        this.setPosition("5.2");
        if (trie instanceof RadixTrieExternalNode) {
            this.setPosition("5.2.1");
            if (((RadixTrieExternalNode)trie).getKey() == dataItem.getKey()) {
                this.setPosition("5.2.1.1");
                this.searchDataBitArray.unHighlight();
                return true;
            }
            this.setPosition("5.2.2");
            this.searchDataBitArray.unHighlight();
            return false;
        }
        else {
            this.setPosition("5.3");
            if (this.getBit(level, dataItem.getKey()) == 0) {
                this.setPosition("5.3.1");
                return this.search(((RadixTrieInternalNode)trie).getLeft(), dataItem, level + 1);
            }
            this.setPosition("5.4");
            this.setPosition("5.4.1");
            return this.search(((RadixTrieInternalNode)trie).getRight(), dataItem, level + 1);
        }
    }
    
    private void save() {
        this.savedTree = this.head;
    }
    
    protected void storeState(final boolean forceState) {
        this.save();
    }
    
    protected void clearState() {
        this.head = new RadixTrieNullNode(null);
    }
    
    private byte getBit(final int bitNumber, final int number) {
        return (byte)((number >> this.mostSignificantBit - bitNumber) % 2);
    }
    
    private void setPosition(final String string, final boolean isPause) {
        if (isPause) {
            this.setPosition(string);
        }
        else {
            this.setPosition("This key does not exist");
        }
    }
    
    private void clearPointers() {
        this.currentNode = null;
        this.pathNode = this.currentNode;
        this.insertDataBitArray = null;
        this.searchDataBitArray = null;
        this.compareDataBitArray = null;
        this.insertedData = new Vector();
        this.radixTrieToVector(this.insertedData, this.head);
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
        BUILD_MODE_LABEL = Messages.getString("RadixTrie.0");
        SEARCH_MODE_LABEL = Messages.getString("RadixTrie.1");
    }
}
