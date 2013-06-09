import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class DigitalSearchTree extends Algorithm implements MethodSelectionListener, ControlListener
{
    private int[] insertData;
    private int[] newInsertData;
    private int insertPos;
    private int[] searchData;
    private int[] newSearchData;
    private int mostSignificantBit;
    private Color[] bitDataColor;
    private Color[] insertDataColor;
    private Color[] searchDataColor;
    private ElementArray bitArray;
    private ElementArray searchArray;
    private ElementArray insertArray;
    private boolean isBackMode;
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private boolean canStoreState;
    private static final Color TEXT_COLOR;
    private static final Color INSERT_HIGHLIGHT_COLOR;
    private static final Color INSERT_ACTIVE_COLOR;
    private static final Color INSERT_DONE_COLOR;
    private static final Color BIT_INSERT_HIGHLIGHT_COLOR;
    private static final Color BIT_INSERT_ACTIVE_COLOR;
    private static final Color SEARCH_HIGHLIGHT_COLOR;
    private static final Color SEARCH_ACTIVE_COLOR;
    private static final Color SEARCH_DONE_COLOR;
    private static final Color BIT_SEARCH_HIGHLIGHT_COLOR;
    private static final Color BIT_SEARCH_ACTIVE_COLOR;
    private static final Color TREE_HIGHLIGHT_COLOR;
    private static final Color TREE_ACTIVE_COLOR;
    private static final Color TREE_DONE_COLOR;
    private static final Color TREE_RING_COLOR;
    private static final Color TREE_NULL_COLOR;
    private static final Color PATH_COLOR;
    private Node parentNode;
    private Node ptrNode;
    private Node bitNode;
    private Node searchNode;
    private ExtendedHierarchyTree ptrTree;
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
    private static final String DELETE_MODE_LABEL;
    protected static final String PARENT_LABEL;
    protected static final String BIT_LABEL;
    protected static final String PTR_LABEL;
    protected static final String INSERTION_LABEL;
    protected static String ptrLabel;
    private int executingMode;
    private int nextMode;
    private DigitalTree root;
    private DigitalTree savedTree;
    
    public DigitalSearchTree(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.mostSignificantBit = 0;
        this.isBackMode = false;
        this.canStoreState = false;
        this.savedTree = null;
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
        this.root = null;
        this.initialise();
    }
    
    protected void setEnabled(final boolean state) {
        this.enabled = state;
    }
    
    private void colorHierarchyTree(final ExtendedHierarchyTree subTree) {
        if (subTree != null) {
            subTree.getLine().showAsThick(true);
            subTree.setParentLineColor(DigitalSearchTree.PATH_COLOR);
            if (subTree.getNodes().size() > 0) {
                ((Node)subTree.getNodes().elementAt(0)).setTextColor(DigitalSearchTree.PATH_COLOR);
                ((Node)subTree.getNodes().elementAt(0)).setRingColor(DigitalSearchTree.PATH_COLOR);
            }
            this.colorHierarchyTree((ExtendedHierarchyTree)subTree.getParent());
        }
    }
    
    private ExtendedHierarchyTree makeHierarchyTree(final ExtendedHierarchyTree parent, final DigitalTree digitalSearchTree) {
        if (digitalSearchTree != null) {
            final ExtendedHierarchyTree hierarchyTree = new ExtendedHierarchyTree(parent, digitalSearchTree.node);
            if (digitalSearchTree.node == this.ptrNode) {
                this.ptrTree = hierarchyTree;
            }
            ExtendedHierarchyTree temp;
            if (digitalSearchTree.leftChild != null) {
                hierarchyTree.addChild(this.makeHierarchyTree(hierarchyTree, digitalSearchTree.leftChild));
            }
            else {
                temp = new ExtendedHierarchyTree(hierarchyTree, digitalSearchTree.leftNode);
                hierarchyTree.addChild(temp);
                if (digitalSearchTree.leftNode == this.ptrNode) {
                    this.ptrTree = temp;
                }
            }
            if (digitalSearchTree.rightChild != null) {
                hierarchyTree.addChild(this.makeHierarchyTree(hierarchyTree, digitalSearchTree.rightChild));
            }
            else {
                temp = new ExtendedHierarchyTree(hierarchyTree, digitalSearchTree.rightNode);
                hierarchyTree.addChild(temp);
                if (digitalSearchTree.rightNode == this.ptrNode) {
                    this.ptrTree = temp;
                }
            }
            return hierarchyTree;
        }
        return null;
    }
    
    private ExtendedHierarchyTree buildHierarchyTree(final DigitalTree digitalSearchTree, final int i) {
        ExtendedHierarchyTree hierarchyTree = null;
        if (digitalSearchTree != null) {
            hierarchyTree = new ExtendedHierarchyTree();
            hierarchyTree.add(digitalSearchTree.node);
            hierarchyTree.add(digitalSearchTree.node);
            hierarchyTree.setBorderColor(Color.white);
            if (digitalSearchTree.node == this.ptrNode) {
                this.ptrTree = hierarchyTree;
            }
            ExtendedHierarchyTree temp;
            if (digitalSearchTree.leftChild != null) {
                hierarchyTree.addChild(this.buildHierarchyTree(digitalSearchTree.leftChild, 0));
            }
            else {
                temp = new ExtendedHierarchyTree(digitalSearchTree.leftNode);
                temp = new ExtendedHierarchyTree();
                temp.add(digitalSearchTree.leftNode);
                hierarchyTree.addChild(temp);
                if (digitalSearchTree.leftNode == this.ptrNode) {
                    this.ptrTree = temp;
                }
            }
            if (digitalSearchTree.rightChild != null) {
                hierarchyTree.addChild(this.buildHierarchyTree(digitalSearchTree.rightChild, 0));
            }
            else {
                temp = new ExtendedHierarchyTree(digitalSearchTree.rightNode);
                temp = new ExtendedHierarchyTree();
                temp.add(digitalSearchTree.rightNode);
                hierarchyTree.addChild(temp);
                if (digitalSearchTree.rightNode == this.ptrNode) {
                    this.ptrTree = temp;
                }
                hierarchyTree.addChild(temp);
            }
        }
        return hierarchyTree;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object object, final int i) {
        return null;
    }
    
    public ExtendedHierarchyTree getHierarchyTree() {
        this.ptrTree = null;
        final ExtendedHierarchyTree tree = this.buildHierarchyTree(this.root, 0);
        ExtendedHierarchyTree test = new ExtendedHierarchyTree();
        test = this.makeHierarchyTree(test, this.root);
        this.colorHierarchyTree(this.ptrTree);
        return test;
    }
    
    public ElementArray getInsertElementArray() {
        if (this.insertArray != null) {
            for (int i = 0; i < this.insertArray.getSize(); ++i) {
                ((Node)this.insertArray.getElement(i)).setBackgroundColor(this.insertDataColor[i]);
                ((Node)this.insertArray.getElement(i)).setHighlightColor(DigitalSearchTree.INSERT_HIGHLIGHT_COLOR);
                ((Node)this.insertArray.getElement(i)).setTextColor(DigitalSearchTree.TEXT_COLOR);
            }
        }
        return this.insertArray;
    }
    
    public ElementArray getSearchElementArray() {
        if (this.searchArray != null) {
            for (int i = 0; i < this.searchArray.getSize(); ++i) {
                ((Node)this.searchArray.getElement(i)).setBackgroundColor(this.searchDataColor[i]);
                ((Node)this.searchArray.getElement(i)).setHighlightColor(DigitalSearchTree.SEARCH_HIGHLIGHT_COLOR);
                ((Node)this.searchArray.getElement(i)).setTextColor(DigitalSearchTree.TEXT_COLOR);
            }
        }
        return this.searchArray;
    }
    
    public ElementArray getBitElementArray() {
        if (this.bitArray != null) {
            for (int i = 0; i < this.bitArray.getSize(); ++i) {
                ((Node)this.bitArray.getElement(i)).setBackgroundColor(this.bitDataColor[this.bitArray.getSize() - i - 1]);
                ((Node)this.bitArray.getElement(i)).setTextColor(DigitalSearchTree.TEXT_COLOR);
            }
        }
        return this.bitArray;
    }
    
    public Node getParentNode() {
        return this.parentNode;
    }
    
    public Node getPtrNode() {
        return this.ptrNode;
    }
    
    public Node getBitNode() {
        return null;
    }
    
    public Node getHighlightedNode() {
        Node highlighted = null;
        if (this.insertArray != null && this.executingMode == 11) {
            for (int i = 0; i < this.insertArray.getSize(); ++i) {
                if (((Node)this.insertArray.getElement(i)).getBackgroundColor() == DigitalSearchTree.INSERT_HIGHLIGHT_COLOR) {
                    highlighted = (Node)this.insertArray.getElement(i);
                }
            }
        }
        else if (this.searchArray != null && this.executingMode == 12) {
            highlighted = this.searchNode;
        }
        return highlighted;
    }
    
    public boolean isBuildMode() {
        return this.executingMode == 11;
    }
    
    public boolean isSearchMode() {
        return this.executingMode == 12;
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    protected Vector<E> generateQuestions() {
        return null;
    }
    
    private void initialise() {
        final Object o = null;
        this.parentNode = o;
        this.ptrNode = o;
        this.resetTreeColor(this.root);
    }
    
    protected void initialiseMethods(final String string, final MethodSelectable methodSelectable) {
        final MethodSelection methodSelection1 = new MethodSelection(DigitalSearchTree.BUILD_MODE_LABEL, string, Messages.getString("DigitalSearchTree.7"), "3.1", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection2 = new MethodSelection(DigitalSearchTree.SEARCH_MODE_LABEL, string, Messages.getString("DigitalSearchTree.8"), "5", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection3 = new MethodSelection(DigitalSearchTree.DELETE_MODE_LABEL, string, Messages.getString("DigitalSearchTree.9"), "7", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (DigitalSearchTree.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
        else if (DigitalSearchTree.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 12;
        }
    }
    
    protected void storeState(final boolean forceState) {
        if (this.canStoreState || forceState) {
            this.save();
        }
    }
    
    private void save() {
        this.savedTree = this.root;
    }
    
    protected void clearState() {
    }
    
    public void reInitialise(final Object data) {
        this.insertData = this.newInsertData;
        if (!this.isBackMode) {
            this.searchData = this.newSearchData;
        }
        if (this.isBackMode && this.executingMode == 12) {
            this.searchNode.removeAllMarkers();
        }
        this.mostSignificantBit = 0;
        for (int i = 0; i < this.insertData.length; ++i) {
            final String binaryString = Integer.toBinaryString(this.insertData[i]);
            if (this.mostSignificantBit < binaryString.length()) {
                this.mostSignificantBit = binaryString.length();
            }
        }
        this.mostSignificantBit = this.mostSignificantBit - 1;
        if (this.executingMode == 11) {
            this.savedTree = null;
        }
        this.root = this.savedTree;
        this.insertPos = 0;
        this.insertDataColor = new Color[this.insertData.length];
        for (int i = 0; i < this.insertData.length; ++i) {
            this.insertDataColor[i] = DigitalSearchTree.INSERT_ACTIVE_COLOR;
        }
        this.insertArray = new ElementArray(0, 0);
        this.insertArray.setColumGap(0);
        this.insertArray.setElementWidth(20);
        for (int i = 0; i < this.insertData.length; ++i) {
            final Integer nodeValue = new Integer(this.insertData[i]);
            final Node node = new Node(nodeValue, i);
            node.setBackgroundColor(this.insertDataColor[i]);
            node.setHighlightColor(DigitalSearchTree.INSERT_HIGHLIGHT_COLOR);
            node.setTextColor(DigitalSearchTree.TEXT_COLOR);
            this.insertArray.setValue(i, node);
        }
        if (!this.isBackMode) {
            this.searchDataColor = new Color[this.searchData.length];
            for (int i = 0; i < this.searchData.length; ++i) {
                this.searchDataColor[i] = DigitalSearchTree.SEARCH_ACTIVE_COLOR;
            }
            this.searchArray = new ElementArray(0, 0);
            this.searchArray.setColumGap(0);
            this.searchArray.setElementWidth(20);
            for (int i = 0; i < this.searchData.length; ++i) {
                final Node node2 = new Node(new Integer(this.searchData[i]), i);
                node2.setBackgroundColor(this.searchDataColor[i]);
                node2.setHighlightColor(DigitalSearchTree.SEARCH_HIGHLIGHT_COLOR);
                node2.setTextColor(DigitalSearchTree.TEXT_COLOR);
                this.searchArray.setValue(i, node2);
            }
        }
        this.initialise();
    }
    
    protected void changeData(final Object data) {
        this.newInsertData = (int[])((int[])data);
        final Random random = new Random();
        this.newSearchData = new int[this.insertData.length];
        for (int i = 0; i < this.insertData.length; ++i) {
            if (i % 2 == 0) {
                this.newSearchData[i] = Math.abs(random.nextInt()) % 98 + 1;
            }
            else {
                this.newSearchData[i] = this.insertData[i];
            }
        }
        this.save();
        this.initialise();
    }
    
    protected void removeAllAnimationRequests() {
    }
    
    protected void removeAllQuestions() {
    }
    
    protected void makeQuestion(final int i) {
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
        if (this.executingMode == 11) {
            this.insert();
        }
        else if (this.executingMode == 12) {
            this.setPosition("5.0");
            while (this.enabled) {
                this.search();
            }
        }
    }
    
    protected void search() {
        if (this.root != null) {
            int currentBit = this.mostSignificantBit;
            if (!this.isBackMode) {
                if (!this.enabled) {
                    return;
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
                SearchSelection.setEnabled(false);
                if (!this.enabled) {
                    return;
                }
            }
            this.searchDataColor[this.searchNode.getIdentifier()] = DigitalSearchTree.SEARCH_HIGHLIGHT_COLOR;
            final int searchValue = Integer.valueOf(this.searchNode.getDisplayString()).intValue();
            this.bitDataColor = new Color[this.mostSignificantBit + 1];
            this.bitArray = new ElementArray(0, 0);
            this.bitArray.setColumGap(0);
            this.bitArray.setElementWidth(20);
            for (int j = this.mostSignificantBit; j >= 0; --j) {
                this.bitDataColor[j] = DigitalSearchTree.BIT_SEARCH_ACTIVE_COLOR;
                final Node node = new Node(new Integer(this.bitValue(searchValue, j)), j);
                node.setBackgroundColor(this.bitDataColor[j]);
                node.setHighlightColor(DigitalSearchTree.BIT_SEARCH_HIGHLIGHT_COLOR);
                node.setTextColor(DigitalSearchTree.TEXT_COLOR);
                this.bitArray.setValue(this.mostSignificantBit - j, node);
            }
            DigitalTree ptr = this.root;
            this.ptrNode = this.root.node;
            this.setPosition("5.1.1");
            while (ptr != null) {
                final DigitalTree parent = ptr;
                this.bitDataColor[currentBit] = DigitalSearchTree.BIT_SEARCH_HIGHLIGHT_COLOR;
                this.bitNode = (Node)this.bitArray.getElement(this.mostSignificantBit - currentBit);
                this.setPosition("5.2.1");
                if (ptr.data == searchValue) {
                    this.resetTreeColor(this.root);
                    if (!this.enabled) {
                        this.searchDataColor[this.searchNode.getIdentifier()] = DigitalSearchTree.SEARCH_ACTIVE_COLOR;
                        this.bitArray = null;
                        return;
                    }
                    this.searchNode.addMarker(new StringMarker("F"));
                    this.searchNode.addMarker(new StringMarker("o"));
                    this.searchNode.addMarker(new StringMarker("u"));
                    this.searchNode.addMarker(new StringMarker("n"));
                    this.searchNode.addMarker(new StringMarker("d"));
                    this.searchDataColor[this.searchNode.getIdentifier()] = DigitalSearchTree.SEARCH_DONE_COLOR;
                    this.setPosition("5.2.1.1");
                    this.bitArray = null;
                    return;
                }
                else {
                    this.setPosition("5.2.2");
                    this.setPosition("5.2.2.1.1");
                    if (this.bitValue(searchValue, currentBit) == 0) {
                        this.ptrNode = parent.leftNode;
                        ptr = ptr.leftChild;
                        this.setPosition("5.2.2.1.1.1");
                    }
                    else {
                        this.setPosition("5.2.2.1.2");
                        this.ptrNode = parent.rightNode;
                        ptr = parent.rightChild;
                        this.setPosition("5.2.2.1.2.1");
                    }
                    this.bitDataColor[currentBit] = DigitalSearchTree.BIT_SEARCH_ACTIVE_COLOR;
                    --currentBit;
                }
            }
            this.bitDataColor[currentBit + 1] = DigitalSearchTree.BIT_SEARCH_HIGHLIGHT_COLOR;
            this.resetTreeColor(this.root);
            if (!this.enabled) {
                this.searchDataColor[this.searchNode.getIdentifier()] = DigitalSearchTree.SEARCH_ACTIVE_COLOR;
                if (!this.isBackMode) {
                    this.searchNode = null;
                }
                this.bitArray = null;
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
            this.searchDataColor[this.searchNode.getIdentifier()] = DigitalSearchTree.SEARCH_DONE_COLOR;
            this.setPosition("5.3");
        }
        if (!this.isBackMode) {
            this.searchNode = null;
        }
        this.bitArray = null;
    }
    
    protected void insert() {
        this.setPosition("1");
        if (this.insertData.length > 0) {
            if (this.insertPos == 0) {
                this.insertDataColor[0] = DigitalSearchTree.INSERT_HIGHLIGHT_COLOR;
                this.root = new DigitalTree(this.insertData[0], 0);
                this.insertDataColor[0] = DigitalSearchTree.INSERT_DONE_COLOR;
                this.root.node.setBackgroundColor(DigitalSearchTree.TREE_ACTIVE_COLOR);
                this.root.node.setMarkersBelowValue(false);
                this.root.node.removeAllMarkers();
                this.insertPos = this.insertPos + 1;
            }
            this.setPosition("2.0");
            this.setPosition("2.1");
            this.setPosition("2.2");
            for (int i = this.insertPos; i < this.insertData.length; ++i) {
                this.insertItem(i);
            }
        }
    }
    
    protected void insertItem(final int index) {
        int currentBit = this.mostSignificantBit + 1;
        this.setPosition("3");
        this.bitDataColor = new Color[this.mostSignificantBit + 1];
        this.bitArray = new ElementArray(0, 0);
        this.bitArray.setColumGap(0);
        this.bitArray.setElementWidth(20);
        for (int j = this.mostSignificantBit; j >= 0; --j) {
            this.bitDataColor[j] = DigitalSearchTree.BIT_INSERT_ACTIVE_COLOR;
            final Node node = new Node(new Integer(this.bitValue(this.insertData[index], j)), j);
            node.setBackgroundColor(this.bitDataColor[j]);
            node.setHighlightColor(DigitalSearchTree.BIT_INSERT_HIGHLIGHT_COLOR);
            node.setTextColor(DigitalSearchTree.TEXT_COLOR);
            this.bitArray.setValue(this.mostSignificantBit - j, node);
        }
        this.insertDataColor[index] = DigitalSearchTree.INSERT_HIGHLIGHT_COLOR;
        DigitalTree ptr;
        DigitalTree parent = ptr = this.root;
        this.ptrNode = this.root.node;
        this.setPosition("3.1.0.1.1");
        while (ptr != null) {
            this.setPosition("3.1.1.1");
            parent = ptr;
            this.parentNode = this.ptrNode;
            this.setPosition("3.1.1.2.1");
            this.setPosition("3.1.1.2.10.1");
            if (this.insertData[index] == ptr.data) {
                DigitalSearchTree.ptrLabel = DigitalSearchTree.INSERTION_LABEL;
                this.setPosition("3.1.1.2.10.1.1");
                this.setPosition("3.1.1.2.10.1.2");
                this.insertDataColor[index] = DigitalSearchTree.INSERT_ACTIVE_COLOR;
                return;
            }
            --currentBit;
            if (currentBit < this.mostSignificantBit) {
                this.bitDataColor[currentBit + 1] = DigitalSearchTree.BIT_INSERT_ACTIVE_COLOR;
            }
            if (!this.isExpanded("3.1.1")) {
                this.bitDataColor[currentBit] = DigitalSearchTree.BIT_INSERT_ACTIVE_COLOR;
            }
            else {
                this.bitDataColor[currentBit] = DigitalSearchTree.BIT_INSERT_HIGHLIGHT_COLOR;
            }
            this.bitNode = (Node)this.bitArray.getElement(this.mostSignificantBit - currentBit);
            this.setPosition("3.1.1.2.4");
            if (this.bitValue(this.insertData[index], currentBit) == 0) {
                this.ptrNode = parent.leftNode;
                ptr = ptr.leftChild;
                this.setPosition("3.1.1.2.2");
            }
            else {
                this.ptrNode = parent.rightNode;
                ptr = parent.rightChild;
                this.setPosition("3.1.1.2.3");
            }
        }
        this.setPosition("3.1.1.1");
        DigitalSearchTree.ptrLabel = DigitalSearchTree.INSERTION_LABEL;
        final ElementArray tempArray = this.bitArray;
        final Node tempNode = this.parentNode;
        if (!this.isExpanded("3.1.2")) {
            this.parentNode = null;
        }
        this.setPosition("3.1.1.3");
        this.parentNode = tempNode;
        this.bitArray = tempArray;
        if (!this.enabled) {
            DigitalSearchTree.ptrLabel = DigitalSearchTree.PTR_LABEL;
            this.ptrNode = null;
            this.bitArray = null;
            return;
        }
        this.bitNode = (Node)this.bitArray.getElement(this.mostSignificantBit - currentBit);
        if (!this.isExpanded("3.1.2")) {
            this.parentNode = null;
            this.bitDataColor[currentBit] = DigitalSearchTree.BIT_INSERT_ACTIVE_COLOR;
        }
        else {
            this.bitDataColor[currentBit] = DigitalSearchTree.BIT_INSERT_HIGHLIGHT_COLOR;
        }
        this.setPosition("3.1.2.1");
        if (this.bitValue(this.insertData[index], currentBit) == 0) {
            parent.leftNode = new Node(new Integer(this.insertData[index]), 0);
            parent.leftNode.setBackgroundColor(DigitalSearchTree.TREE_HIGHLIGHT_COLOR);
            this.setPosition("3.1.2.1.1");
            parent.leftChild = new DigitalTree(this.insertData[index], index);
            parent.leftChild.node.addMarker(new StringMarker("0 "));
            parent.leftNode = parent.leftChild.node;
        }
        else {
            this.setPosition("3.1.2.2");
            parent.rightNode = new Node(new Integer(this.insertData[index]), 0);
            parent.rightNode.setBackgroundColor(DigitalSearchTree.TREE_HIGHLIGHT_COLOR);
            this.setPosition("3.1.2.2.1");
            parent.rightChild = new DigitalTree(this.insertData[index], index);
            parent.rightChild.node.addMarker(new StringMarker(" 1"));
            parent.rightNode = parent.rightChild.node;
        }
        DigitalSearchTree.ptrLabel = DigitalSearchTree.PTR_LABEL;
        this.ptrNode = null;
        this.parentNode = null;
        this.bitNode = null;
        this.bitArray = null;
        this.resetTreeColor(this.root);
        this.insertDataColor[index] = DigitalSearchTree.INSERT_DONE_COLOR;
        this.insertPos = this.insertPos + 1;
    }
    
    public boolean isExpanded(final String key) {
        return ((DigitalSearchTreeThread)this.algorithmThread).getWindow().isExpanded(key);
    }
    
    private int bitValue(final int number, final int bitNumber) {
        return (number >> bitNumber) % 2;
    }
    
    private void resetTreeColor(final DigitalTree tree) {
        if (tree != null) {
            tree.node.setBackgroundColor(DigitalSearchTree.TREE_ACTIVE_COLOR);
            tree.node.setRingColor(DigitalSearchTree.TREE_RING_COLOR);
            tree.node.setTextColor(DigitalSearchTree.TEXT_COLOR);
            this.resetTreeColor(tree.leftChild);
            this.resetTreeColor(tree.rightChild);
        }
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
        BIT_INSERT_HIGHLIGHT_COLOR = new Color(200, 255, 200);
        BIT_INSERT_ACTIVE_COLOR = DigitalSearchTree.INSERT_HIGHLIGHT_COLOR;
        SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
        SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
        SEARCH_DONE_COLOR = new Color(0, 127, 127);
        BIT_SEARCH_HIGHLIGHT_COLOR = new Color(255, 255, 200);
        BIT_SEARCH_ACTIVE_COLOR = DigitalSearchTree.SEARCH_HIGHLIGHT_COLOR;
        TREE_HIGHLIGHT_COLOR = new Color(255, 255, 0);
        TREE_ACTIVE_COLOR = new Color(191, 191, 64);
        TREE_DONE_COLOR = new Color(191, 191, 0);
        TREE_RING_COLOR = Color.black;
        TREE_NULL_COLOR = Color.blue;
        PATH_COLOR = Color.red;
        BUILD_MODE_LABEL = Messages.getString("DigitalSearchTree.0");
        SEARCH_MODE_LABEL = Messages.getString("DigitalSearchTree.1");
        DELETE_MODE_LABEL = Messages.getString("DigitalSearchTree.2");
        PARENT_LABEL = Messages.getString("DigitalSearchTree.3");
        BIT_LABEL = Messages.getString("DigitalSearchTree.4");
        PTR_LABEL = Messages.getString("DigitalSearchTree.5");
        INSERTION_LABEL = Messages.getString("DigitalSearchTree.6");
        DigitalSearchTree.ptrLabel = DigitalSearchTree.PTR_LABEL;
    }
}
