import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class PatriciaTreeIter extends Algorithm implements MethodSelectionListener, ControlListener
{
    protected static final int BUILD_MODE = 11;
    protected static final int SEARCH_MODE = 12;
    private int executingMode;
    private int nextMode;
    private ExtendedHierarchyTree currentHierarchyTree;
    private int mostSignificantBit;
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
    private ElementArray insertData;
    private ElementArray newInsertData;
    private ElementArray searchData;
    private ElementArray newSearchData;
    private DigitalElementArray dataItemBitArray;
    private DigitalElementArray compareDataBitArray;
    private Node compKeyNode;
    private Node keyFoundNode;
    private Integer searchDataValue;
    private PatriciaTreeIterNode head;
    private int insertPos;
    private int lastInsertPos;
    private boolean isBackMode;
    private PatriciaTreeIterNode savedTree;
    private PatriciaTreeIterNode startingTree;
    private PatriciaTreeIterNode lastNode;
    private PatriciaTreeIterNode currentSearchTree;
    private PatriciaTreeIterNode insertionPoint;
    private Path currentPath;
    private PatriciaTreeIterNode currentInsertTree;
    private Integer differentiatingBit;
    private Integer currentBit;
    private static Boolean isHighlightLoop;
    private Boolean isFollowedUpLink;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private static Color searchTreeColor;
    private static Color postSearchTreeColor;
    private Vector<E> allEndNodes;
    
    public PatriciaTreeIter(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.executingMode = 11;
        this.nextMode = this.executingMode;
        this.currentHierarchyTree = null;
        this.mostSignificantBit = 0;
        this.insertPos = 0;
        this.lastInsertPos = 0;
        this.isBackMode = false;
        this.savedTree = null;
        this.startingTree = null;
        this.savedTree = new PatriciaTreeIterNode();
        this.savedTree.setLeft(this.savedTree);
        this.savedTree.setRight(this.savedTree);
        this.insertData = new ElementArray(0, 0);
        this.insertData.setColumGap(0);
        this.insertData.setElementWidth(20);
        for (int i = 0; i < ((int[])((int[])data)).length; ++i) {
            this.insertData.add(new Node(new Integer(((int[])((int[])data))[i]), i), i);
            ((Node)this.insertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_ACTIVE_COLOR);
        }
        this.newInsertData = this.insertData;
        final Random random = new Random();
        this.searchData = new ElementArray(0, 0);
        this.searchData.setColumGap(0);
        this.searchData.setElementWidth(20);
        for (int j = 0; j < ((int[])((int[])data)).length; ++j) {
            if (j % 2 == 0) {
                this.searchData.add(new Node(new Integer(Math.abs(random.nextInt()) % 98 + 1), j), j);
            }
            else {
                this.searchData.add(new Node(new Integer(((int[])((int[])data))[j]), j), j);
            }
            ((Node)this.searchData.getElement(j)).setBackgroundColor(PatriciaTreeIterColors.SEARCH_ACTIVE_COLOR);
        }
        this.newSearchData = this.searchData;
        this.head = new PatriciaTreeIterNode();
        this.head.setLeft(this.head);
        this.head.setRight(this.head);
    }
    
    protected void initialiseMethods(final String dataDir, final MethodSelectable methodSelectable) {
        final MethodSelection build = new MethodSelection(PatriciaTreeIter.BUILD_MODE_LABEL, dataDir, Messages.getString("PatriciaTreeIter.2"), "3a", this.getLogger(), this.getBreakPoint());
        final MethodSelection search = new MethodSelection(PatriciaTreeIter.SEARCH_MODE_LABEL, dataDir, Messages.getString("PatriciaTreeIter.4"), "5a", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(build, true);
        methodSelectable.addMethodSelection(search, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (PatriciaTreeIter.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
        else if (PatriciaTreeIter.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 12;
        }
        if (this.nextMode == 11) {
            this.head = this.savedTree;
        }
        if (this.nextMode == 12 && this.savedTree != null) {
            this.head = this.savedTree.copy();
        }
    }
    
    protected void storeState(final boolean forceState) {
        if (this.executingMode == 11) {
            this.savedTree = this.head.copy();
        }
        else if (this.executingMode == 12) {
            this.head = this.savedTree.copy();
        }
    }
    
    protected void clearState() {
        this.insertPos = 0;
        this.head = new PatriciaTreeIterNode();
        this.head.setLeft(this.head);
        this.head.setRight(this.head);
    }
    
    public void reInitialise(final Object data) {
        if (this.executingMode == 11 && !this.isBackMode) {
            this.savedTree = new PatriciaTreeIterNode();
            this.savedTree.setLeft(this.savedTree);
            this.savedTree.setRight(this.savedTree);
            this.insertPos = 0;
        }
        if (this.executingMode == 12 && !this.isBackMode) {
            this.head = this.savedTree.copy();
        }
        if (this.isBackMode) {
            if (this.executingMode == 11) {
                this.head = this.startingTree.copy();
            }
            else if (this.executingMode == 12) {
                this.head = this.savedTree.copy();
            }
            this.insertPos = this.lastInsertPos;
        }
        this.mostSignificantBit = 0;
        this.currentSearchTree = null;
        this.insertData = this.newInsertData;
        for (int i = 0; i < this.insertData.getSize(); ++i) {
            ((Node)this.insertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_ACTIVE_COLOR);
        }
        for (int i = 0; i < this.insertData.getSize(); ++i) {
            final String binaryString = Integer.toBinaryString(((Integer)this.insertData.getElement(i).getObject()).intValue());
            if (this.mostSignificantBit < binaryString.length()) {
                this.mostSignificantBit = binaryString.length();
            }
        }
        this.mostSignificantBit = this.mostSignificantBit - 1;
    }
    
    protected void changeData(final Object data) {
        final Random random = new Random();
        this.newInsertData = new ElementArray(0, 0);
        this.newInsertData.setColumGap(0);
        this.newInsertData.setElementWidth(20);
        for (int i = 0; i < ((int[])((int[])data)).length; ++i) {
            this.newInsertData.add(new Node(new Integer(((int[])((int[])data))[i]), i), i);
            ((Node)this.newInsertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_ACTIVE_COLOR);
        }
        this.insertData = this.newInsertData;
        for (int i = 0; i < this.insertPos; ++i) {
            ((Node)this.insertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_DONE_COLOR);
        }
        for (int i = 0; i < this.insertData.getSize(); ++i) {
            final String binaryString = Integer.toBinaryString(((Integer)this.insertData.getElement(i).getObject()).intValue());
            if (this.mostSignificantBit < binaryString.length()) {
                this.mostSignificantBit = binaryString.length();
            }
        }
        this.mostSignificantBit = this.mostSignificantBit - 1;
        this.searchData = new ElementArray(0, 0);
        this.searchData.setColumGap(0);
        this.searchData.setElementWidth(20);
        for (int i = 0; i < ((int[])((int[])data)).length; ++i) {
            if (i % 2 == 0) {
                this.searchData.add(new Node(new Integer(Math.abs(random.nextInt()) % 98 + 1), i), i);
            }
            else {
                this.searchData.add(new Node(new Integer(((int[])((int[])data))[i]), i), i);
            }
            ((Node)this.searchData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.SEARCH_ACTIVE_COLOR);
        }
        this.newSearchData = this.searchData;
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
    
    public Integer getDifferentiatingBit() {
        return this.differentiatingBit;
    }
    
    public Integer getCurrentBit() {
        return this.currentBit;
    }
    
    public ElementArray getInsertElementArray() {
        if (this.executingMode == 11) {
            return this.insertData;
        }
        return null;
    }
    
    public ElementArray getSearchElementArray() {
        if (this.executingMode == 12) {
            return this.searchData;
        }
        return null;
    }
    
    public DigitalElementArray getDataItemBitArray() {
        return this.dataItemBitArray;
    }
    
    public DigitalElementArray getCompareBitArray() {
        return this.compareDataBitArray;
    }
    
    private DigitalElementArray initialiseBitElementArray(final int number) {
        final DigitalElementArray bitArray = new DigitalElementArray(number, this.mostSignificantBit);
        return bitArray;
    }
    
    public Vector<E> getInsertedItemsArray() {
        final Vector<E> sortedList = new Vector();
        if (this.head.getLeft() != this.head) {
            this.printNodes("", this.head.getLeft(), sortedList);
        }
        for (int i = 0; i < sortedList.size(); ++i) {
            System.out.println(((Inserted)sortedList.elementAt(i)).getPattern());
        }
        return sortedList;
    }
    
    public ExtendedHierarchyTree getHierarchyTree() {
        final ExtendedHierarchyTree hierarchyTree = this.buildHierarchyTree(this.head, PatriciaTreeIterColors.DEFAULT_TREE_COLOR);
        this.colorHierarchyTree(hierarchyTree, this.currentPath);
        return hierarchyTree;
    }
    
    public Node getSearchPositionNode() {
        if (this.currentSearchTree != null) {
            return this.currentSearchTree.getBody();
        }
        return null;
    }
    
    public Node getCompKeyNode() {
        return this.compKeyNode;
    }
    
    public Node getKeyFoundNode() {
        return this.keyFoundNode;
    }
    
    public Node getInsertionPoint() {
        if (this.insertionPoint != null) {
            return this.insertionPoint.getBody();
        }
        return null;
    }
    
    public PatriciaTreeIterNode getInsertPositionNode() {
        return this.currentInsertTree;
    }
    
    public PatriciaTreeIterNode getNewNode() {
        return PatriciaTreeIterNode.getNewNode();
    }
    
    public void printNodes(final String head, final PatriciaTreeIterNode current, final Vector<E> sortedList) {
        int i;
        if (current.getLeft().getBit() > current.getBit()) {
            String leftHead = head + "0";
            for (i = current.getBit() + 1; i < current.getLeft().getBit(); ++i) {
                leftHead = leftHead + "*";
            }
            this.printNodes(leftHead, current.getLeft(), sortedList);
        }
        else {
            String leftHead = head + "0";
            for (i = current.getBit() + 1; i <= this.mostSignificantBit; ++i) {
                leftHead = leftHead + "*";
            }
            System.out.println("Pattern: " + leftHead + " Node: " + current.getLeft().getDataItem().getKey());
            sortedList.addElement(new Inserted(leftHead, current.getLeft().getDataItem().getKey()));
        }
        if (current.getRight().getBit() > current.getBit()) {
            String rightHead = head + "1";
            for (i = current.getBit() + 1; i < current.getRight().getBit(); ++i) {
                rightHead = rightHead + "*";
            }
            this.printNodes(rightHead, current.getRight(), sortedList);
        }
        else {
            String rightHead = head + "1";
            for (i = current.getBit() + 1; i <= this.mostSignificantBit; ++i) {
                rightHead = rightHead + "*";
            }
            System.out.println("Pattern: " + rightHead + " Node: " + current.getRight().getDataItem().getKey());
            sortedList.addElement(new Inserted(rightHead, current.getRight().getDataItem().getKey()));
        }
    }
    
    public PatriciaTreeIterNode getHeadNode() {
        return this.head;
    }
    
    public ExtendedHierarchyTree buildHierarchyTree(final PatriciaTreeIterNode current, final Color currentColor) {
        current.getBody().setRingColor(PatriciaTreeIterColors.DEFAULT_RING_COLOR);
        current.getBody().setTextColor(PatriciaTreeIterColors.DEFAULT_TEXT_COLOR);
        final ExtendedHierarchyTree hierarchyTree = new ExtendedHierarchyTree();
        hierarchyTree.setDrawBorder(false);
        hierarchyTree.getLine().showArrowHead(false);
        hierarchyTree.add(current.getBody());
        hierarchyTree.add(current.getDataItem().getNode());
        current.setHierarchyTree(hierarchyTree);
        ExtendedHierarchyTree temp;
        Node tempNode;
        if (current.getLeft().getBit() > current.getBit()) {
            hierarchyTree.addChild(this.buildHierarchyTree(current.getLeft(), currentColor));
        }
        else if (current.getRight().getBit() > current.getBit()) {
            temp = new ExtendedHierarchyTree();
            tempNode = new Node("", 0);
            tempNode.setIsVisible(false);
            temp.add(tempNode);
            hierarchyTree.addChild(temp);
        }
        else {
            temp = new ExtendedHierarchyTree();
            hierarchyTree.addChild(temp);
        }
        if (current.getRight().getBit() > current.getBit()) {
            hierarchyTree.addChild(this.buildHierarchyTree(current.getRight(), currentColor));
        }
        else if (current.getLeft().getBit() > current.getBit()) {
            temp = new ExtendedHierarchyTree();
            tempNode = new Node("", 0);
            tempNode.setIsVisible(false);
            temp.add(tempNode);
            hierarchyTree.addChild(temp);
        }
        else {
            temp = new ExtendedHierarchyTree();
            hierarchyTree.addChild(temp);
        }
        return hierarchyTree;
    }
    
    private void colorHierarchyTree(final ExtendedHierarchyTree tree, final Path path) {
        PatriciaTreeIter.isHighlightLoop = null;
        if (tree == null) {
            return;
        }
        tree.getLine().showAsThick(true);
        tree.setParentLineColor(PatriciaTreeIterColors.SEARCH_PATH_COLOR);
        if (tree.getFirstElement() != null) {
            tree.getFirstElement().setRingColor(PatriciaTreeIterColors.SEARCH_PATH_COLOR);
            tree.getFirstElement().setTextColor(PatriciaTreeIterColors.SEARCH_PATH_COLOR);
        }
        if (path == null) {
            return;
        }
        ExtendedHierarchyTree subtree;
        if (path.isLeft) {
            subtree = (ExtendedHierarchyTree)tree.getLeftChild();
        }
        else {
            subtree = (ExtendedHierarchyTree)tree.getRightChild();
        }
        this.colorHierarchyTree(subtree, path.next);
        if (subtree != null && subtree.getNumberOfChildren() == 0) {
            PatriciaTreeIter.isHighlightLoop = new Boolean(path.isLeft);
        }
    }
    
    protected void run() {
        if (!this.isBackMode) {
            this.lastInsertPos = this.insertPos;
            if (this.savedTree != null) {
                this.startingTree = this.savedTree.copy();
            }
            else {
                this.startingTree = new PatriciaTreeIterNode();
                this.startingTree.setLeft(this.startingTree);
                this.startingTree.setRight(this.startingTree);
            }
        }
        switch (this.executingMode) {
            case 11: {
                for (int i = this.insertPos; i < this.insertData.getSize(); ++i) {
                    final PatriciaTreeIterDataItem dataItem = new PatriciaTreeIterDataItem(new Node((Integer)this.insertData.getElement(i).getObject(), i), ((Integer)this.insertData.getElement(i).getObject()).intValue());
                    if (this.enabled) {
                        ((Node)this.insertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_HIGHLIGHT_COLOR);
                        this.savedTree = this.head.copy();
                        this.insert(this.head, dataItem);
                        if (this.enabled) {
                            this.insertPos = this.insertPos + 1;
                        }
                        else {
                            this.head = this.savedTree.copy();
                        }
                        this.head.clearNewNode();
                        ((Node)this.insertData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.INSERT_DONE_COLOR);
                    }
                }
                break;
            }
            case 12: {
                for (int i = 0; i < this.searchData.getSize(); ++i) {
                    final PatriciaTreeIterDataItem dataItem = new PatriciaTreeIterDataItem(new Node((Integer)this.searchData.getElement(i).getObject(), i), ((Integer)this.searchData.getElement(i).getObject()).intValue());
                    if (this.enabled) {
                        ((Node)this.searchData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.SEARCH_HIGHLIGHT_COLOR);
                        if (this.search(this.head, dataItem)) {
                            ((Node)this.searchData.getElement(i)).addMarker(new StringMarker("F"));
                            this.currentSearchTree.getDataItem().getNode().unHighlight();
                            this.setPosition("5.3.2");
                        }
                        else {
                            ((Node)this.searchData.getElement(i)).addMarker(new StringMarker("N"));
                            this.currentSearchTree.getDataItem().getNode().unHighlight();
                            this.setPosition("5.3.4");
                        }
                        ((Node)this.searchData.getElement(i)).setBackgroundColor(PatriciaTreeIterColors.SEARCH_DONE_COLOR);
                    }
                }
                for (int i = 0; i < this.searchData.getSize(); ++i) {
                    ((Node)this.searchData.getElement(i)).removeAllMarkers();
                }
                break;
            }
        }
    }
    
    protected void run(final boolean canChangeMode) {
        if (canChangeMode) {
            this.executingMode = this.nextMode;
        }
        this.run();
    }
    
    private void insert(final PatriciaTreeIterNode root, final PatriciaTreeIterDataItem dataItem) {
        byte bitPos = -1;
        PatriciaTreeIterNode parent = root;
        this.setPosition("3");
        this.currentPath = new Path(true);
        this.currentSearchTree = root.getLeft();
        this.dataItemBitArray = this.initialiseBitElementArray(dataItem.getKey());
        this.dataItemBitArray.setColors(PatriciaTreeIterColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeIterColors.INSERT_BIT_HIGHLIGHT_COLOR);
        this.isFollowedUpLink = new Boolean(this.currentSearchTree.getBit() <= parent.getBit());
        bitPos = root.getLeft().getBit();
        this.dataItemBitArray.unHighlight();
        if (bitPos >= 0) {
            this.dataItemBitArray.highlightBit(bitPos);
        }
        this.setPosition("3.1.1");
        while (this.currentSearchTree.getBit() > parent.getBit()) {
            this.setPosition("3.2.1.1");
            parent = this.currentSearchTree;
            if (this.getBit(dataItem.getKey(), bitPos) == 0) {
                this.setPosition("3.2.1.1.1.1");
                this.currentPath.add(true);
                this.currentSearchTree = this.currentSearchTree.getLeft();
                this.isFollowedUpLink = new Boolean(this.currentSearchTree.getBit() <= parent.getBit());
                this.setPosition("3.2.1.1.1.2");
            }
            else {
                this.setPosition("3.2.1.1.1.1");
                this.setPosition("3.2.1.1.1.3");
                this.currentPath.add(false);
                this.currentSearchTree = this.currentSearchTree.getRight();
                this.isFollowedUpLink = new Boolean(this.currentSearchTree.getBit() <= parent.getBit());
                this.setPosition("3.2.1.1.1.4");
            }
            bitPos = this.currentSearchTree.getBit();
            this.dataItemBitArray.unHighlight();
            if (bitPos >= 0) {
                this.dataItemBitArray.highlightBit(bitPos);
            }
            this.setPosition("3.2.1.1.2");
        }
        this.setPosition("3.2.1.1");
        if (this.currentSearchTree != null) {
            this.currentSearchTree.getDataItem().getNode().highlight();
        }
        this.setPosition("3.2.1.2");
        this.isFollowedUpLink = null;
        this.setPosition("3.2.1.3");
        if (this.currentSearchTree.getDataItem().getKey() == dataItem.getKey()) {
            this.clearPointers();
            this.setPosition("3.2.1.3.1");
            return;
        }
        this.currentSearchTree.getDataItem().getNode().unHighlight();
        final int compKey = this.currentSearchTree.getDataItem().getKey();
        this.compareDataBitArray = this.initialiseBitElementArray(compKey);
        this.compareDataBitArray.setColors(PatriciaTreeIterColors.DATA_ITEM_COLOR, PatriciaTreeIterColors.DATA_ITEM_HIGHLIGHT_COLOR);
        byte bit = 0;
        this.dataItemBitArray.unHighlight();
        this.dataItemBitArray.highlightBit(bit);
        this.compareDataBitArray.highlightBit(bit);
        this.setPosition("3.2.2.1");
        for (; this.getBit(compKey, bit) == this.getBit(dataItem.getKey(), bit); bit = (byte)(bit + 1), this.dataItemBitArray.unHighlight(), this.dataItemBitArray.highlightBit(bit), this.compareDataBitArray.unHighlight(), this.compareDataBitArray.highlightBit(bit), this.setPosition("3.2.2.2.1")) {
            this.setPosition("3.2.2.2");
        }
        this.compareDataBitArray.highlightNumber(bit, PatriciaTreeIterColors.DIFFERENTIATING_COLOR);
        this.differentiatingBit = new Integer(bit);
        this.setPosition("3.2.2.2");
        this.setPosition("3.2.2.3");
        parent = root;
        this.dataItemBitArray.unHighlight();
        this.compareDataBitArray = null;
        if (this.compKeyNode != null) {
            this.compKeyNode.unHighlight();
            this.compKeyNode = null;
        }
        this.currentPath = new Path(true);
        this.currentSearchTree = root.getLeft();
        this.isFollowedUpLink = new Boolean(this.currentSearchTree.getBit() <= parent.getBit());
        bitPos = this.currentSearchTree.getBit();
        this.dataItemBitArray.unHighlight();
        if (bitPos >= 0) {
            this.dataItemBitArray.highlightBit(bitPos);
        }
        this.setPosition("3.3.1.2");
        while (this.currentSearchTree.getBit() > parent.getBit() && this.currentSearchTree.getBit() < bit) {
            this.setPosition("3.3.2.1");
            parent = this.currentSearchTree;
            if (this.getBit(dataItem.getKey(), bitPos) == 0) {
                this.setPosition("3.3.2.1.2.1");
                this.currentPath.add(true);
                this.currentSearchTree = this.currentSearchTree.getLeft();
                this.isFollowedUpLink = new Boolean(this.currentSearchTree.getBit() <= parent.getBit());
                this.setPosition("3.3.2.1.2.2");
            }
            else {
                this.setPosition("3.3.2.1.2.1");
                this.setPosition("3.3.2.1.2.3");
                this.currentPath.add(false);
                this.currentSearchTree = this.currentSearchTree.getRight();
                this.isFollowedUpLink = new Boolean(this.currentSearchTree.getBit() <= parent.getBit());
                this.setPosition("3.3.2.1.2.4");
            }
            bitPos = this.currentSearchTree.getBit();
            this.dataItemBitArray.unHighlight();
            if (bitPos >= 0) {
                this.dataItemBitArray.highlightBit(bitPos);
            }
            this.setPosition("3.3.2.1.3");
        }
        this.setPosition("3.3.2.1");
        this.insertionPoint = this.currentSearchTree;
        this.insertionPoint.getDataItem().getNode().highlight();
        this.setPosition("3.3.2.2");
        this.isFollowedUpLink = null;
        final PatriciaTreeIterNode newNode = new PatriciaTreeIterNode(dataItem, bit);
        newNode.getDataItem().getNode().highlight();
        this.currentPath = null;
        this.setPosition("3.3.3.1");
        if (this.getBit(dataItem.getKey(), bit) == 0) {
            this.setPosition("3.3.3.3.1");
            newNode.setLeft(newNode);
            newNode.setRight(this.currentSearchTree);
            this.setPosition("3.3.3.3.3");
            this.dataItemBitArray.unHighlight();
        }
        else {
            this.setPosition("3.3.3.3.1");
            this.setPosition("3.3.3.3.4");
            newNode.setLeft(this.currentSearchTree);
            newNode.setRight(newNode);
            this.setPosition("3.3.3.3.6");
            this.dataItemBitArray.unHighlight();
        }
        this.insertionPoint.getDataItem().getNode().unHighlight();
        this.insertionPoint = null;
        if (this.getBit(dataItem.getKey(), parent.getBit()) == 0) {
            parent.setLeft(newNode);
        }
        else {
            parent.setRight(newNode);
        }
        this.setPosition("3.3.3.4");
        newNode.getDataItem().getNode().unHighlight();
        this.dataItemBitArray = null;
        this.currentPath = null;
        this.differentiatingBit = null;
    }
    
    private PatriciaTreeIterNode insertKey(final PatriciaTreeIterNode tree, final PatriciaTreeIterDataItem dataItem, final byte diffBit, final PatriciaTreeIterNode parent, final boolean isShowRecursion) {
        this.setPosition("3.4.1", isShowRecursion);
        this.currentInsertTree = tree;
        this.currentBit = new Integer(tree.getBit());
        this.setPosition("3.4.1.0", isShowRecursion);
        this.differentiatingBit = new Integer(diffBit);
        this.isFollowedUpLink = new Boolean(tree.getBit() >= diffBit || tree.getBit() <= parent.getBit());
        this.setPosition("3.4.1.1", isShowRecursion);
        if (tree.getBit() < diffBit && tree.getBit() > parent.getBit()) {
            this.setPosition("3.4.1.2", isShowRecursion);
            this.isFollowedUpLink = null;
            this.dataItemBitArray = this.initialiseBitElementArray(dataItem.getKey());
            this.dataItemBitArray.setColors(PatriciaTreeIterColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeIterColors.INSERT_BIT_HIGHLIGHT_COLOR);
            this.dataItemBitArray.highlightBit(tree.getBit());
            this.setPosition("3.4.1.2.1.2", isShowRecursion);
            if (this.getBit(dataItem.getKey(), tree.getBit()) == 0) {
                this.dataItemBitArray.unHighlight();
                this.currentPath.add(true);
                this.lastNode = tree;
                tree.setLeftIsKnown(false);
                this.setPosition("3.4.1.2.1.2.1", isShowRecursion);
                this.currentBit = null;
                if (this.isExpanded("3.4.1.2.1")) {
                    final PatriciaTreeIterNode unkownLeft = this.insertKey(tree.getLeft(), dataItem, diffBit, tree, true);
                    tree.setLeftIsKnown(true);
                    tree.setLeft(unkownLeft);
                }
                else {
                    final PatriciaTreeIterNode unkownLeft = this.insertKey(tree.getLeft(), dataItem, diffBit, tree, false);
                    tree.setLeftIsKnown(true);
                    tree.setLeft(unkownLeft);
                }
            }
            else {
                this.dataItemBitArray.unHighlight();
                this.currentPath.add(false);
                this.lastNode = tree;
                tree.setRightIsKnown(false);
                this.setPosition("3.4.1.2.1.3", isShowRecursion);
                this.currentBit = null;
                if (this.isExpanded("3.4.1.2.1")) {
                    final PatriciaTreeIterNode unkownRight = this.insertKey(tree.getRight(), dataItem, diffBit, tree, true);
                    tree.setRightIsKnown(true);
                    tree.setRight(unkownRight);
                }
                else {
                    final PatriciaTreeIterNode unkownRight = this.insertKey(tree.getRight(), dataItem, diffBit, tree, false);
                    tree.setRightIsKnown(true);
                    tree.setRight(unkownRight);
                }
            }
            this.currentInsertTree = tree;
            this.setPosition("3.4.1.2.1.4", isShowRecursion);
            this.currentBit = null;
            return tree;
        }
        final PatriciaTreeIterNode newNode = new PatriciaTreeIterNode(dataItem, diffBit);
        this.setPosition("3.4.1.1.1.1", isShowRecursion);
        this.dataItemBitArray = this.initialiseBitElementArray(dataItem.getKey());
        this.dataItemBitArray.setColors(PatriciaTreeIterColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeIterColors.INSERT_BIT_HIGHLIGHT_COLOR);
        this.dataItemBitArray.highlightBit(diffBit);
        this.setPosition("3.4.1.1.1.2.1", isShowRecursion);
        if (this.getBit(dataItem.getKey(), diffBit) == 0) {
            newNode.setLeft(newNode);
            this.setPosition("3.4.1.1.1.2.1.1", isShowRecursion);
            newNode.setRight(tree);
            this.setPosition("3.4.1.1.1.2.1.2", isShowRecursion);
            this.dataItemBitArray.unHighlight();
        }
        else {
            this.setPosition("3.4.1.1.1.2.2", isShowRecursion);
            newNode.setRight(newNode);
            this.setPosition("3.4.1.1.1.2.2.1", isShowRecursion);
            newNode.setLeft(tree);
            this.setPosition("3.4.1.1.1.2.2.2", isShowRecursion);
            this.dataItemBitArray.unHighlight();
        }
        if (!this.enabled) {
            this.clearPointers();
            return tree;
        }
        this.setPosition("3.4.1.1.1.3", isShowRecursion);
        this.currentBit = null;
        this.isFollowedUpLink = null;
        return newNode;
    }
    
    private void setPosition(final String key, final boolean isPause) {
        if (isPause) {
            this.setPosition(key);
        }
        else {
            this.setPosition("This key doesn't exist");
        }
    }
    
    private boolean search(final PatriciaTreeIterNode tree, final PatriciaTreeIterDataItem dataItem) {
        this.dataItemBitArray = this.initialiseBitElementArray(dataItem.getKey());
        this.dataItemBitArray.setColors(PatriciaTreeIterColors.SEARCH_BIT_ACTIVE_COLOR, PatriciaTreeIterColors.SEARCH_BIT_HIGHLIGHT_COLOR);
        PatriciaTreeIterNode parent = tree;
        byte bitPos = -1;
        this.setPosition("5");
        this.currentPath = new Path(true);
        this.currentSearchTree = tree.getLeft();
        bitPos = tree.getLeft().getBit();
        this.dataItemBitArray.unHighlight();
        if (bitPos >= 0) {
            this.dataItemBitArray.highlightBit(bitPos);
        }
        this.isFollowedUpLink = new Boolean(this.currentSearchTree.getBit() <= parent.getBit());
        this.setPosition("5.1.1");
        while (this.currentSearchTree.getBit() > parent.getBit()) {
            this.setPosition("5.2.1");
            parent = this.currentSearchTree;
            if (this.getBit(dataItem.getKey(), bitPos) == 0) {
                this.setPosition("5.2.1.1");
                this.currentPath.add(true);
                this.currentSearchTree = this.currentSearchTree.getLeft();
                this.isFollowedUpLink = new Boolean(this.currentSearchTree.getBit() <= parent.getBit());
                this.setPosition("5.2.1.2");
            }
            else {
                this.setPosition("5.2.1.1");
                this.setPosition("5.2.1.3");
                this.currentPath.add(false);
                this.currentSearchTree = this.currentSearchTree.getRight();
                this.isFollowedUpLink = new Boolean(this.currentSearchTree.getBit() <= parent.getBit());
                this.setPosition("5.2.1.4");
            }
            bitPos = this.currentSearchTree.getBit();
            this.dataItemBitArray.unHighlight();
            if (bitPos >= 0) {
                this.dataItemBitArray.highlightBit(bitPos);
            }
            this.setPosition("5.2.1.5");
        }
        this.setPosition("5.2.1");
        this.currentSearchTree.getDataItem().getNode().highlight();
        this.setPosition("5.2.2");
        this.isFollowedUpLink = null;
        if (this.currentSearchTree.getDataItem().getKey() == dataItem.getKey()) {
            this.setPosition("5.3.1");
            return true;
        }
        this.setPosition("5.3.1");
        this.setPosition("5.3.3");
        return false;
    }
    
    public Node getLastNode() {
        if (this.currentSearchTree != null) {
            return this.currentSearchTree.getBody();
        }
        return null;
    }
    
    public Boolean getIsHighlightLoop() {
        return PatriciaTreeIter.isHighlightLoop;
    }
    
    public Boolean getIsFollowedUpLink() {
        return this.isFollowedUpLink;
    }
    
    public Vector<E> getAllEndNodes() {
        if (this.allEndNodes == null) {
            this.allEndNodes = new Vector();
        }
        this.allEndNodes.removeAllElements();
        this.findAllEndNodes(this.head);
        return this.allEndNodes;
    }
    
    public void findAllEndNodes(final PatriciaTreeIterNode tree) {
        final Node[] nodes = new Node[] { tree.getBody(), null, null };
        if (tree.getBit() >= tree.getLeft().getBit()) {
            nodes[1] = tree.getLeft().getBody();
        }
        else {
            this.findAllEndNodes(tree.getLeft());
        }
        if (tree.getBit() >= tree.getRight().getBit()) {
            nodes[2] = tree.getRight().getBody();
        }
        else {
            this.findAllEndNodes(tree.getRight());
        }
        if (tree.getBit() >= tree.getLeft().getBit() || tree.getBit() >= tree.getRight().getBit()) {
            this.allEndNodes.addElement(nodes);
        }
    }
    
    private PatriciaTreeIterDataItem searchKey(final PatriciaTreeIterNode tree, final int key, final byte bit) {
        this.currentSearchTree = tree;
        this.dataItemBitArray.unHighlight();
        if (this.currentPath == null) {
            throw new RuntimeException("search path should have been initialised");
        }
        this.isFollowedUpLink = new Boolean(tree.getBit() <= bit);
        this.setPosition("5.1.1");
        if (tree.getBit() <= bit) {
            this.setPosition("5.1.1.1");
            this.isFollowedUpLink = null;
            return tree.getDataItem();
        }
        this.dataItemBitArray.highlightBit(tree.getBit());
        this.setPosition("5.1.2");
        this.isFollowedUpLink = null;
        this.setPosition("5.1.3");
        if (this.getBit(key, tree.getBit()) == 0) {
            this.setPosition("5.1.3.1");
            this.currentPath.add(true);
            this.lastNode = tree;
            return this.searchKey(tree.getLeft(), key, tree.getBit());
        }
        this.setPosition("5.1.4");
        this.currentPath.add(false);
        this.lastNode = tree;
        return this.searchKey(tree.getRight(), key, tree.getBit());
    }
    
    private int getBit(final int number, final byte bit) {
        return (number >> this.mostSignificantBit - bit) % 2;
    }
    
    private boolean isExpanded(final String key) {
        return ((PatriciaTreeIterThread)this.algorithmThread).getWindow().isExpanded(key);
    }
    
    private void clearPointers() {
        this.isFollowedUpLink = null;
        this.currentPath = null;
        this.currentBit = null;
        this.dataItemBitArray = null;
        this.compareDataBitArray = null;
        if (this.compKeyNode != null) {
            this.compKeyNode.unHighlight();
            this.compKeyNode = null;
        }
        this.keyFoundNode = null;
        this.differentiatingBit = null;
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
        BUILD_MODE_LABEL = Messages.getString("PatriciaTreeIter.0");
        SEARCH_MODE_LABEL = Messages.getString("PatriciaTreeIter.1");
        PatriciaTreeIter.searchTreeColor = Color.orange;
        PatriciaTreeIter.postSearchTreeColor = Color.gray;
    }
}
