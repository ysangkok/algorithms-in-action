import localization.*;
import java.util.*;
import com.cim.AIA.*;

public class RadixTrieIter extends Algorithm implements MethodSelectionListener, ControlListener
{
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
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
    private Vector<E> insertedData;
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
    
    public RadixTrieIter(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.isBackMode = false;
        this.savedTree = new RadixTrieIterNullNode(null);
        this.head = new RadixTrieIterNullNode(null);
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
        this.mostSignificantBit = this.mostSignificantBit - 1;
        if (this.executingMode == 11) {
            this.savedTree = new RadixTrieIterNullNode(null);
            this.searchPos = 0;
            this.insertPos = 0;
        }
        else if (this.executingMode == 12) {
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
            ((Node)this.newInsertData.getElement(i)).setBackgroundColor(RadixTrieIterColors.INSERT_ACTIVE_COLOR);
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
            ((Node)this.newSearchData.getElement(i)).setBackgroundColor(RadixTrieIterColors.SEARCH_ACTIVE_COLOR);
        }
    }
    
    protected void initialiseMethods(final String string, final MethodSelectable methodSelectable) {
        final MethodSelection methodSelection1 = new MethodSelection(RadixTrieIter.BUILD_MODE_LABEL, string, Messages.getString("RadixTrieIter.2"), "3a", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection2 = new MethodSelection(RadixTrieIter.SEARCH_MODE_LABEL, string, Messages.getString("RadixTrieIter.3"), "5a", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (RadixTrieIter.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
        else if (RadixTrieIter.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 12;
        }
    }
    
    private RadixTrieIterDigitalElementArray initialiseBitElementArray(final int number) {
        return new RadixTrieIterDigitalElementArray(number, this.mostSignificantBit);
    }
    
    public RadixTrieIterNode getHead() {
        return this.head;
    }
    
    public Vector<E> getInsertedData() {
        return this.insertedData;
    }
    
    public RadixTrieIterDigitalElementArray getInsertBitArray() {
        if (this.executingMode == 11) {
            return this.insertDataBitArray;
        }
        return null;
    }
    
    public RadixTrieIterDigitalElementArray getSearchBitArray() {
        if (this.executingMode == 12) {
            return this.searchDataBitArray;
        }
        return null;
    }
    
    public RadixTrieIterDigitalElementArray getFinalCompareBitArray() {
        if (this.executingMode == 12) {
            return this.finalCompareDataBitArray;
        }
        return null;
    }
    
    public RadixTrieIterDigitalElementArray getCompareBitArray() {
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
    
    public RadixTrieIterNode getCurrentNode() {
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
    
    private void unHighlightPath(final RadixTrieIterNode node) {
        if (node != null) {
            node.setIsOnPath(false);
            if (node instanceof RadixTrieIterInternalNode) {
                this.unHighlightPath(((RadixTrieIterInternalNode)node).getLeft());
                this.unHighlightPath(((RadixTrieIterInternalNode)node).getRight());
            }
        }
    }
    
    private void highlightPath(final RadixTrieIterNode node) {
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
    
    private void printTrie(final RadixTrieIterNode trie) {
        if (trie instanceof RadixTrieIterInternalNode) {
            this.printTrie(((RadixTrieIterInternalNode)trie).getLeft());
            this.printTrie(((RadixTrieIterInternalNode)trie).getRight());
        }
        if (trie instanceof RadixTrieIterExternalNode) {
            System.out.println(((RadixTrieIterExternalNode)trie).getKey() + " " + Integer.toBinaryString(((RadixTrieIterExternalNode)trie).getKey()));
        }
    }
    
    private void radixTrieToVector(final Vector<E> vector, final RadixTrieIterNode node) {
        if (node instanceof RadixTrieIterInternalNode) {
            this.radixTrieToVector(vector, ((RadixTrieIterInternalNode)node).getLeft());
            this.radixTrieToVector(vector, ((RadixTrieIterInternalNode)node).getRight());
        }
        else if (node instanceof RadixTrieIterExternalNode) {
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
                    this.insertDataBitArray.setColors(RadixTrieIterColors.INSERT_BIT_ACTIVE_COLOR, RadixTrieIterColors.INSERT_BIT_HIGHLIGHT_COLOR);
                    try {
                        if (this.enabled) {
                            ((Node)this.insertData.getElement(i)).setBackgroundColor(RadixTrieIterColors.INSERT_HIGHLIGHT_COLOR);
                            this.head = this.insert(this.head, dataItem, 0, true, i);
                            this.clearPointers();
                            RadixTrieIterExternalNode.clearNewestNode();
                            RadixTrieIterExternalNode.clearCompareNode();
                            this.insertedData = new Vector();
                            this.radixTrieToVector(this.insertedData, this.head);
                            this.insertPos = this.insertPos + 1;
                            ((Node)this.insertData.getElement(i)).setBackgroundColor(RadixTrieIterColors.INSERT_DONE_COLOR);
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
                    this.searchDataBitArray.setColors(RadixTrieIterColors.SEARCH_BIT_ACTIVE_COLOR, RadixTrieIterColors.SEARCH_BIT_HIGHLIGHT_COLOR);
                    if (this.enabled) {
                        searchNode.setBackgroundColor(RadixTrieIterColors.SEARCH_HIGHLIGHT_COLOR);
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
                        RadixTrieIterExternalNode.clearCompareNode();
                        this.insertedData = new Vector();
                        this.radixTrieToVector(this.insertedData, this.head);
                        searchNode.setBackgroundColor(RadixTrieIterColors.SEARCH_DONE_COLOR);
                        this.searchPos = this.searchPos + 1;
                    }
                    this.searchDataBitArray = null;
                }
                break;
            }
        }
    }
    
    private RadixTrieIterNode insert(RadixTrieIterNode trie, final DataItem dataItem, final int level, final boolean isShowRecursion, final int index) throws Exception {
        this.currentNode = trie;
        this.pathNode = this.currentNode;
        this.setPosition("3.0");
        if (trie == null) {
            throw new RuntimeException("trie should not be null");
        }
        this.setPosition("3.1.1");
        RadixTrieIterExternalNode newNode;
        if (trie instanceof RadixTrieIterNullNode) {
            newNode = new RadixTrieIterExternalNode(dataItem);
            newNode.setIsLinkedIn();
            newNode.setLevel(0);
            this.pathNode = newNode;
            final RadixTrieIterExternalNode radixTrieIterExternalNode = newNode;
            this.head = radixTrieIterExternalNode;
            this.currentNode = radixTrieIterExternalNode;
            this.setPosition("3.1.1.1");
            return newNode;
        }
        this.currentLevel = 0;
        this.insertDataBitArray.unHighlight();
        this.insertDataBitArray.highlightBit((byte)this.currentLevel);
        this.setPosition("3.2.2");
        while (this.currentNode instanceof RadixTrieIterInternalNode) {
            this.setPosition("3.3.1");
            this.setPosition("3.3.1.2.1");
            if (this.getBit(this.currentLevel, dataItem.getKey()) == 0) {
                this.currentNode = ((RadixTrieIterInternalNode)this.currentNode).getLeft();
                this.pathNode = this.currentNode;
                this.setPosition("3.3.1.2.1.1");
            }
            else {
                this.setPosition("3.3.1.2.2");
                this.currentNode = ((RadixTrieIterInternalNode)this.currentNode).getRight();
                this.pathNode = this.currentNode;
                this.setPosition("3.3.1.2.2.1");
            }
            if (this.currentNode instanceof RadixTrieIterInternalNode) {
                this.currentLevel = this.currentLevel + 1;
                this.insertDataBitArray.unHighlight();
                this.insertDataBitArray.highlightBit((byte)this.currentLevel);
                this.setPosition("3.3.1.4");
            }
            else {
                this.currentLevel = this.currentLevel + 1;
                this.setPosition("3.3.1.4");
            }
        }
        this.setPosition("3.3.1");
        this.setPosition("3.3.2");
        this.pathNode = this.currentNode.getParent();
        this.setPosition("3.4.1");
        if (this.currentNode instanceof RadixTrieIterNullNode) {
            newNode = new RadixTrieIterExternalNode(dataItem);
            if (this.getBit(this.currentLevel - 1, dataItem.getKey()) == 0) {
                this.currentNode.getParent().setLeft(newNode);
            }
            else {
                this.currentNode.getParent().setRight(newNode);
            }
            newNode.setLevel(this.currentLevel);
            newNode.setIsLinkedIn();
            this.setPosition("3.4.1.1");
            return trie;
        }
        this.setPosition("3.4.2");
        final int trieItem = ((RadixTrieIterExternalNode)this.currentNode).getKey();
        if (trieItem == dataItem.getKey()) {
            this.setPosition("3.4.2.1");
            this.setPosition("3.4.2.2");
            this.insertDataBitArray.addMarker(new StringMarker(Messages.getString("RadixTrieIter.4")));
            this.compareDataBitArray = null;
            this.setPosition("3.4.2.2.1");
            this.insertDataBitArray.removeAllMarkers();
            return trie;
        }
        this.setPosition("3.4.2.1");
        this.compareDataBitArray = this.initialiseBitElementArray(trieItem);
        this.compareDataBitArray.setColors(RadixTrieIterColors.COMPARE_BIT_ACTIVE_COLOR, RadixTrieIterColors.COMPARE_BIT_HIGHLIGHT_COLOR);
        RadixTrieIterExternalNode.setCompareNode((RadixTrieIterExternalNode)this.currentNode);
        final RadixTrieIterInternalNode returnNode = new RadixTrieIterInternalNode();
        final RadixTrieIterExternalNode trieNode = (RadixTrieIterExternalNode)this.currentNode;
        returnNode.setIsLinkedIn();
        if (this.getBit(this.currentLevel - 1, dataItem.getKey()) == 0) {
            if (this.currentNode.getParent() != null) {
                this.currentNode.getParent().setLeft(returnNode);
            }
            else {
                final RadixTrieIterInternalNode head = returnNode;
                this.head = head;
                trie = head;
            }
        }
        else if (this.currentNode.getParent() != null) {
            this.currentNode.getParent().setRight(returnNode);
        }
        else {
            final RadixTrieIterInternalNode head2 = returnNode;
            this.head = head2;
            trie = head2;
        }
        RadixTrieIterInternalNode ptr = returnNode;
        this.currentNode = ptr;
        this.compareDataBitArray.unHighlight();
        this.insertDataBitArray.unHighlight();
        this.compareDataBitArray.highlightBit((byte)this.currentLevel);
        this.insertDataBitArray.highlightBit((byte)this.currentLevel);
        this.setPosition("3.5.1");
        while (this.getBit(this.currentLevel, trieItem) == this.getBit(this.currentLevel, dataItem.getKey())) {
            this.setPosition("3.5.2");
            if (this.getBit(this.currentLevel, dataItem.getKey()) == 0) {
                this.setPosition("3.5.2.2");
                this.currentLevel = this.currentLevel + 1;
                this.compareDataBitArray.unHighlight();
                this.insertDataBitArray.unHighlight();
                this.compareDataBitArray.highlightBit((byte)this.currentLevel);
                this.insertDataBitArray.highlightBit((byte)this.currentLevel);
                this.setPosition("3.5.2.2.1");
                final RadixTrieIterInternalNode newInternal = new RadixTrieIterInternalNode();
                this.pathNode = newInternal;
                ptr.setLeft(newInternal);
                ptr = (RadixTrieIterInternalNode)ptr.getLeft();
                newInternal.setIsLinkedIn();
                this.currentNode = ptr;
                this.setPosition("3.5.2.2.2");
            }
            else {
                this.setPosition("3.5.2.2");
                this.setPosition("3.5.2.3");
                this.currentLevel = this.currentLevel + 1;
                this.compareDataBitArray.unHighlight();
                this.insertDataBitArray.unHighlight();
                this.compareDataBitArray.highlightBit((byte)this.currentLevel);
                this.insertDataBitArray.highlightBit((byte)this.currentLevel);
                this.setPosition("3.5.2.3.1");
                final RadixTrieIterInternalNode newInternal = new RadixTrieIterInternalNode();
                this.pathNode = newInternal;
                ptr.setRight(newInternal);
                ptr = (RadixTrieIterInternalNode)ptr.getRight();
                newInternal.setIsLinkedIn();
                this.currentNode = ptr;
                this.setPosition("3.5.2.3.2");
            }
        }
        this.setPosition("3.5.2");
        this.setPosition("3.5.3");
        if (this.getBit(this.currentLevel, dataItem.getKey()) == 0) {
            this.setPosition("3.4.2.1.2.1");
            this.setPosition("3.4.2.1.2.3");
            ptr.setRight(trieNode);
            trieNode.setLevel(this.currentLevel + 1);
            this.insertedData = new Vector();
            this.radixTrieToVector(this.insertedData, this.head);
            this.setPosition("3.4.2.1.2.4");
            this.setPosition("3.4.2.1.3.1");
            final RadixTrieIterExternalNode newExternal = new RadixTrieIterExternalNode(dataItem);
            newExternal.setLevel(this.currentLevel + 1);
            newExternal.setIsLinkedIn();
            ptr.setLeft(newExternal);
            this.insertedData = new Vector();
            this.radixTrieToVector(this.insertedData, this.head);
            this.setPosition("3.4.2.1.3.2");
            this.compareDataBitArray.unHighlight();
            this.insertDataBitArray.unHighlight();
        }
        else {
            this.setPosition("3.4.2.1.2.1");
            ptr.setLeft(trieNode);
            trieNode.setLevel(this.currentLevel + 1);
            this.insertedData = new Vector();
            this.radixTrieToVector(this.insertedData, this.head);
            this.setPosition("3.4.2.1.2.2");
            this.setPosition("3.4.2.1.3.1");
            this.setPosition("3.4.2.1.3.3");
            final RadixTrieIterExternalNode newExternal = new RadixTrieIterExternalNode(dataItem);
            newExternal.setLevel(this.currentLevel + 1);
            newExternal.setIsLinkedIn();
            ptr.setRight(newExternal);
            this.insertedData = new Vector();
            this.radixTrieToVector(this.insertedData, this.head);
            this.setPosition("3.4.2.1.3.4");
            this.compareDataBitArray.unHighlight();
            this.insertDataBitArray.unHighlight();
        }
        this.setPosition("3.4.2.1.4");
        return trie;
    }
    
    private boolean search(final RadixTrieIterNode trie, final DataItem dataItem, final int level) {
        this.setPosition("5");
        this.currentLevel = 0;
        this.searchDataBitArray.unHighlight();
        this.searchDataBitArray.highlightBit((byte)this.currentLevel);
        this.currentNode = trie;
        this.pathNode = this.currentNode;
        this.setPosition("5.1.1");
        while (this.currentNode instanceof RadixTrieIterInternalNode) {
            this.setPosition("5.1.2");
            if (this.getBit(this.currentLevel, dataItem.getKey()) == 0) {
                this.setPosition("5.1.2.1.1");
                this.currentNode = ((RadixTrieIterInternalNode)this.currentNode).getLeft();
                this.pathNode = this.currentNode;
                this.setPosition("5.1.2.1.1.1");
            }
            else {
                this.setPosition("5.1.2.1.1");
                this.setPosition("5.1.2.1.2");
                this.currentNode = ((RadixTrieIterInternalNode)this.currentNode).getRight();
                this.pathNode = this.currentNode;
                this.setPosition("5.1.2.1.2.1");
            }
            this.currentLevel = this.currentLevel + 1;
            this.searchDataBitArray.unHighlight();
            this.searchDataBitArray.highlightBit((byte)this.currentLevel);
            this.setPosition("5.1.2.2");
        }
        this.setPosition("5.1.2");
        this.setPosition("5.1.3");
        if (this.currentNode instanceof RadixTrieIterExternalNode) {
            this.finalCompareDataBitArray = new RadixTrieIterDigitalElementArray(((RadixTrieIterExternalNode)this.currentNode).getKey(), this.mostSignificantBit, false);
            this.finalCompareDataBitArray.setColors(RadixTrieIterColors.COMPARE_BIT_HIGHLIGHT_COLOR, RadixTrieIterColors.COMPARE_BIT_HIGHLIGHT_COLOR);
        }
        this.setPosition("5.2");
        if (this.currentNode instanceof RadixTrieIterExternalNode && ((RadixTrieIterExternalNode)this.currentNode).getKey() == dataItem.getKey()) {
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
        this.head = new RadixTrieIterNullNode(null);
    }
    
    private byte getBit(final int bitNumber, final int number) {
        return (byte)((number >> this.mostSignificantBit - bitNumber) % 2);
    }
    
    private void setPosition(final String string, final boolean isPause) {
        if (isPause) {
            this.setPosition(string);
        }
        else {
            this.setPosition(Messages.getString("RadixTrieIter.5"));
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
        BUILD_MODE_LABEL = Messages.getString("RadixTrieIter.0");
        SEARCH_MODE_LABEL = Messages.getString("RadixTrieIter.1");
    }
}
