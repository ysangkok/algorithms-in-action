import localization.*;
import java.awt.*;
import java.util.*;
import com.cim.AIA.*;

public class PatriciaTree extends Algorithm implements MethodSelectionListener, ControlListener
{
    protected static final int BUILD_MODE = 11;
    protected static final int SEARCH_MODE = 12;
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
    private static Boolean isHighlightLoop;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private int executingMode;
    private int nextMode;
    private int mostSignificantBit;
    private ElementArray insertData;
    private ElementArray newInsertData;
    private ElementArray searchData;
    private DigitalElementArray dataItemBitArray;
    private DigitalElementArray compareDataBitArray;
    private Node compKeyNode;
    private Node keyFoundNode;
    private PatriciaNode head;
    private PatriciaNode lastNode;
    private PatriciaNode currentSearchTree;
    private Path currentPath;
    private PatriciaNode currentInsertTree;
    private Integer differentiatingBit;
    private Integer currentBit;
    private Boolean isFollowedUpLink;
    private Vector<Node[]> allEndNodes;
    
    public PatriciaTree(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.executingMode = 11;
        this.nextMode = this.executingMode;
        this.mostSignificantBit = 0;
        this.insertData = new ElementArray(0, 0);
        this.insertData.setColumGap(0);
        this.insertData.setElementWidth(20);
        for (int i = 0; i < ((int[])((int[])data)).length; ++i) {
            this.insertData.add(new Node(new Integer(((int[])((int[])data))[i]), i), i);
            ((Node)this.insertData.getElement(i)).setBackgroundColor(PatriciaTreeColors.INSERT_ACTIVE_COLOR);
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
            ((Node)this.searchData.getElement(j)).setBackgroundColor(PatriciaTreeColors.SEARCH_ACTIVE_COLOR);
        }
        this.head = new PatriciaNode();
        this.head.setLeft(this.head);
        this.head.setRight(this.head);
    }
    
    public HierarchyTree buildHierarchyTree(final PatriciaNode current, final Color currentColor) {
        current.getBody().setRingColor(PatriciaTreeColors.DEFAULT_RING_COLOR);
        current.getBody().setTextColor(PatriciaTreeColors.DEFAULT_TEXT_COLOR);
        final HierarchyTree hierarchyTree = new HierarchyTree();
        hierarchyTree.getLine().showArrowHead(false);
        hierarchyTree.add(current.getBody());
        hierarchyTree.add(current.getDataItem().getNode());
        current.setHierarchyTree(hierarchyTree);
        HierarchyTree temp;
        Node tempNode;
        if (current.getLeft().getBit() > current.getBit()) {
            hierarchyTree.addChild(this.buildHierarchyTree(current.getLeft(), currentColor));
        }
        else if (current.getRight().getBit() > current.getBit()) {
            temp = new HierarchyTree();
            tempNode = new Node("", 0);
            tempNode.setIsVisible(false);
            temp.add(tempNode);
            hierarchyTree.addChild(temp);
        }
        else {
            temp = new HierarchyTree();
            hierarchyTree.addChild(temp);
        }
        if (current.getRight().getBit() > current.getBit()) {
            hierarchyTree.addChild(this.buildHierarchyTree(current.getRight(), currentColor));
        }
        else if (current.getLeft().getBit() > current.getBit()) {
            temp = new HierarchyTree();
            tempNode = new Node("", 0);
            tempNode.setIsVisible(false);
            temp.add(tempNode);
            hierarchyTree.addChild(temp);
        }
        else {
            temp = new HierarchyTree();
            hierarchyTree.addChild(temp);
        }
        return hierarchyTree;
    }
    
    protected void changeData(final Object data) {
        this.newInsertData = new ElementArray(0, 0);
        this.newInsertData.setColumGap(0);
        this.newInsertData.setElementWidth(20);
        for (int i = 0; i < ((int[])((int[])data)).length; ++i) {
            this.newInsertData.add(new Node(new Integer(((int[])((int[])data))[i]), i), i);
            ((Node)this.newInsertData.getElement(i)).setBackgroundColor(PatriciaTreeColors.INSERT_ACTIVE_COLOR);
        }
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
    
    protected void clearState() {
        this.head = new PatriciaNode();
        this.head.setLeft(this.head);
        this.head.setRight(this.head);
    }
    
    private void colorHierarchyTree(final HierarchyTree tree, final Path path) {
        PatriciaTree.isHighlightLoop = null;
        if (tree == null) {
            return;
        }
        tree.getLine().showAsThick(true);
        tree.setParentLineColor(PatriciaTreeColors.SEARCH_PATH_COLOR);
        if (tree.getFirstElement() != null) {
            tree.getFirstElement().setRingColor(PatriciaTreeColors.SEARCH_PATH_COLOR);
            tree.getFirstElement().setTextColor(PatriciaTreeColors.SEARCH_PATH_COLOR);
        }
        if (path == null) {
            return;
        }
        HierarchyTree subtree;
        if (path.isLeft) {
            subtree = tree.getLeftChild();
        }
        else {
            subtree = tree.getRightChild();
        }
        this.colorHierarchyTree(subtree, path.next);
        if (subtree != null && subtree.getNumberOfChildren() == 0) {
            PatriciaTree.isHighlightLoop = new Boolean(path.isLeft);
        }
    }
    
    public void controlBack(final ControlEvent e) {
        if (this.executingMode == 11) {
            this.clearState();
        }
    }
    
    public void controlOther(final ControlEvent e) {
    }
    
    public void controlPause(final ControlEvent e) {
    }
    
    public void controlReset(final ControlEvent e) {
    }
    
    public void controlRestart(final ControlEvent e) {
    }
    
    public void controlRun(final ControlEvent e) {
    }
    
    public void controlStep(final ControlEvent e) {
    }
    
    public void findAllEndNodes(final PatriciaNode tree) {
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
    
    protected Vector<Object> generateQuestions() {
        return null;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        return null;
    }
    
    public Vector<Node[]> getAllEndNodes() {
        if (this.allEndNodes == null) {
            this.allEndNodes = new Vector();
        }
        this.allEndNodes.removeAllElements();
        this.findAllEndNodes(this.head);
        return this.allEndNodes;
    }
    
    private int getBit(final int number, final byte bit) {
        return (number >> this.mostSignificantBit - bit) % 2;
    }
    
    public DigitalElementArray getCompareBitArray() {
        return this.compareDataBitArray;
    }
    
    public Node getCompKeyNode() {
        return this.compKeyNode;
    }
    
    public Integer getCurrentBit() {
        return this.currentBit;
    }
    
    public DigitalElementArray getDataItemBitArray() {
        return this.dataItemBitArray;
    }
    
    public Integer getDifferentiatingBit() {
        return this.differentiatingBit;
    }
    
    public PatriciaNode getHeadNode() {
        return this.head;
    }
    
    public HierarchyTree getHierarchyTree() {
        final HierarchyTree hierarchyTree = this.buildHierarchyTree(this.head, PatriciaTreeColors.DEFAULT_TREE_COLOR);
        this.colorHierarchyTree(hierarchyTree, this.currentPath);
        return hierarchyTree;
    }
    
    public Vector<PatriciaTreeInserted> getInsertedItemsArray() {
        final Vector<PatriciaTreeInserted> sortedList = new Vector();
        if (this.head.getLeft() != this.head) {
            this.printNodes("", this.head.getLeft(), sortedList);
        }
        for (int i = 0; i < sortedList.size(); ++i) {
            System.out.println(((PatriciaTreeInserted)sortedList.elementAt(i)).getPattern());
        }
        return sortedList;
    }
    
    public ElementArray getInsertElementArray() {
        if (this.executingMode == 11) {
            return this.insertData;
        }
        return null;
    }
    
    public PatriciaNode getInsertPositionNode() {
        return this.currentInsertTree;
    }
    
    public Boolean getIsFollowedUpLink() {
        return this.isFollowedUpLink;
    }
    
    public Boolean getIsHighlightLoop() {
        return PatriciaTree.isHighlightLoop;
    }
    
    public Node getKeyFoundNode() {
        return this.keyFoundNode;
    }
    
    public Node getLastNode() {
        if (this.lastNode != null) {
            return this.lastNode.getBody();
        }
        return null;
    }
    
    public PatriciaNode getNewNode() {
        return PatriciaNode.getNewNode();
    }
    
    public ElementArray getSearchElementArray() {
        if (this.executingMode == 12) {
            return this.searchData;
        }
        return null;
    }
    
    public Node getSearchPositionNode() {
        if (this.currentSearchTree != null) {
            return this.currentSearchTree.getBody();
        }
        return null;
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    private DigitalElementArray initialiseBitElementArray(final int number) {
        final DigitalElementArray bitArray = new DigitalElementArray(number, this.mostSignificantBit);
        return bitArray;
    }
    
    protected void initialiseCanvases(final String dataDir, final MultiAlgorithmWindow multiAlgorithmWindow) {
        multiAlgorithmWindow.addCodeCanvas(dataDir, Messages.getString("PatriciaTree.44"), "3.1", "5.1hide");
        multiAlgorithmWindow.addCodeCanvas(dataDir, Messages.getString("PatriciaTree.22"), "3.2", "3.2hide");
        multiAlgorithmWindow.addCodeCanvas(dataDir, Messages.getString("PatriciaTree.44"), "5.1s", "5.1shide");
        multiAlgorithmWindow.setSize(new Dimension(350, 200), Messages.getString("PatriciaTree.44"));
        multiAlgorithmWindow.setLocation(new Point(235, 420), Messages.getString("PatriciaTree.44"));
        multiAlgorithmWindow.setLocation(new Point(235, 280), Messages.getString("PatriciaTree.22"));
        multiAlgorithmWindow.setSize(new Dimension(350, 430), Messages.getString("PatriciaTree.22"));
    }
    
    protected void initialiseMethods(final String dataDir, final MethodSelectable methodSelectable) {
        final MethodSelection build = new MethodSelection(PatriciaTree.BUILD_MODE_LABEL, dataDir, Messages.getString("PatriciaTree.2"), "3", this.getLogger(), this.getBreakPoint());
        final MethodSelection search = new MethodSelection(PatriciaTree.SEARCH_MODE_LABEL, dataDir, Messages.getString("PatriciaTree.4"), "5", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(build, true);
        methodSelectable.addMethodSelection(search, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    private void insert(final PatriciaNode root, final PatriciaTreeDataItem dataItem) {
        this.setPosition("3");
        this.currentPath = new Path(true);
        this.dataItemBitArray = this.initialiseBitElementArray(dataItem.getKey());
        this.dataItemBitArray.setColors(PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeColors.INSERT_BIT_HIGHLIGHT_COLOR);
        this.setPosition("3.1.1");
        final int compKey = this.searchKey(root.getLeft(), dataItem.getKey(), -1).getKey();
        this.setPosition("5.1hide");
        this.currentPath = null;
        if (this.currentSearchTree != null) {
            this.compKeyNode = this.currentSearchTree.getDataItem().getNode();
            this.compKeyNode.highlight();
        }
        this.currentSearchTree = null;
        this.setPosition("3.1.2.1");
        if (compKey == dataItem.getKey()) {
            this.clearPointers();
            return;
        }
        this.compareDataBitArray = this.initialiseBitElementArray(compKey);
        this.compareDataBitArray.setColors(PatriciaTreeColors.DATA_ITEM_COLOR, PatriciaTreeColors.DATA_ITEM_HIGHLIGHT_COLOR);
        this.setPosition("3.1.2.2");
        byte bit = 0;
        this.dataItemBitArray.highlightBit(bit);
        this.compareDataBitArray.highlightBit(bit);
        this.setPosition("3.1.2.3");
        while (this.getBit(compKey, bit) == this.getBit(dataItem.getKey(), bit)) {
            bit = (byte)(bit + 1);
            this.dataItemBitArray.unHighlight();
            this.dataItemBitArray.highlightBit(bit);
            this.compareDataBitArray.unHighlight();
            this.compareDataBitArray.highlightBit(bit);
            this.setPosition("3.1.2.3.1", this.isExpanded("3.1.2.3"));
        }
        this.compareDataBitArray.highlightNumber(bit, PatriciaTreeColors.DIFFERENTIATING_COLOR);
        this.differentiatingBit = new Integer(bit);
        if (!this.isExpanded("3.1")) {
            this.dataItemBitArray.unHighlight();
            this.compareDataBitArray = null;
            this.compKeyNode.unHighlight();
            this.compKeyNode = null;
        }
        this.setPosition("3.1.2.4");
        if (!this.enabled) {
            this.clearPointers();
            return;
        }
        this.dataItemBitArray.unHighlight();
        this.compareDataBitArray = null;
        if (this.compKeyNode != null) {
            this.compKeyNode.unHighlight();
            this.compKeyNode = null;
        }
        this.currentPath = new Path(true);
        if (this.isExpanded("3.2")) {
            this.setPosition("3.2.1");
            root.setLeft(this.insertKey(root.getLeft(), dataItem, bit, root, true));
        }
        else {
            root.setLeft(this.insertKey(root.getLeft(), dataItem, bit, root, true));
            this.currentPath = null;
            this.currentInsertTree = null;
            this.setPosition("3.2.1");
        }
        this.setPosition("3.4.1.3b");
        this.setPosition("3.2hide");
        this.dataItemBitArray = null;
        this.currentPath = null;
        this.differentiatingBit = null;
        this.currentInsertTree = null;
    }
    
    private PatriciaNode insertKey(final PatriciaNode tree, final PatriciaTreeDataItem dataItem, final byte diffBit, final PatriciaNode parent, final boolean isShowRecursion) {
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
            this.dataItemBitArray.setColors(PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeColors.INSERT_BIT_HIGHLIGHT_COLOR);
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
                    final PatriciaNode unkownLeft = this.insertKey(tree.getLeft(), dataItem, diffBit, tree, true);
                    tree.setLeftIsKnown(true);
                    tree.setLeft(unkownLeft);
                }
                else {
                    final PatriciaNode unkownLeft = this.insertKey(tree.getLeft(), dataItem, diffBit, tree, false);
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
                    final PatriciaNode unkownRight = this.insertKey(tree.getRight(), dataItem, diffBit, tree, true);
                    tree.setRightIsKnown(true);
                    tree.setRight(unkownRight);
                }
                else {
                    final PatriciaNode unkownRight = this.insertKey(tree.getRight(), dataItem, diffBit, tree, false);
                    tree.setRightIsKnown(true);
                    tree.setRight(unkownRight);
                }
            }
            this.currentInsertTree = tree;
            this.setPosition("3.4.1.2.1.4", isShowRecursion);
            this.currentBit = null;
            return tree;
        }
        final PatriciaNode newNode = new PatriciaNode(dataItem, diffBit);
        this.setPosition("3.4.1.1.1.1", isShowRecursion);
        this.dataItemBitArray = this.initialiseBitElementArray(dataItem.getKey());
        this.dataItemBitArray.setColors(PatriciaTreeColors.INSERT_BIT_ACTIVE_COLOR, PatriciaTreeColors.INSERT_BIT_HIGHLIGHT_COLOR);
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
    
    private boolean isExpanded(final String key) {
        return ((PatriciaTreeThread)this.algorithmThread).getWindow().isExpanded(key);
    }
    
    public void printNodes(final String head, final PatriciaNode current, final Vector<PatriciaTreeInserted> sortedList) {
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
            System.out.println(Messages.getString("PatriciaTree.26") + leftHead + Messages.getString("PatriciaTree.27") + current.getLeft().getDataItem().getKey());
            sortedList.addElement(new PatriciaTreeInserted(leftHead, current.getLeft().getDataItem().getKey()));
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
            System.out.println(Messages.getString("PatriciaTree.32") + rightHead + Messages.getString("PatriciaTree.33") + current.getRight().getDataItem().getKey());
            sortedList.addElement(new PatriciaTreeInserted(rightHead, current.getRight().getDataItem().getKey()));
        }
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (PatriciaTree.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            System.out.println(Messages.getString("PatriciaTree.19"));
            this.nextMode = 11;
        }
        else if (PatriciaTree.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            System.out.println(Messages.getString("PatriciaTree.20"));
            this.nextMode = 12;
        }
    }
    
    public void reInitialise(final Object data) {
        this.mostSignificantBit = 0;
        this.insertData = this.newInsertData;
        for (int i = 0; i < this.insertData.getSize(); ++i) {
            final String binaryString = Integer.toBinaryString(((Integer)this.insertData.getElement(i).getObject()).intValue());
            if (this.mostSignificantBit < binaryString.length()) {
                this.mostSignificantBit = binaryString.length();
            }
        }
        this.mostSignificantBit = this.mostSignificantBit - 1;
    }
    
    protected void removeAllAnimationRequests() {
    }
    
    protected void removeAllQuestions() {
    }
    
    protected void run() {
        switch (this.executingMode) {
            case 11: {
                for (int i = 0; i < this.insertData.getSize(); ++i) {
                    final PatriciaTreeDataItem dataItem = new PatriciaTreeDataItem(new Node(this.insertData.getElement(i).getObject(), i), ((Integer)this.insertData.getElement(i).getObject()).intValue());
                    ((Node)this.insertData.getElement(i)).setBackgroundColor(PatriciaTreeColors.INSERT_HIGHLIGHT_COLOR);
                    this.insert(this.head, dataItem);
                    PatriciaNode.clearNewNode();
                    ((Node)this.insertData.getElement(i)).setBackgroundColor(PatriciaTreeColors.INSERT_DONE_COLOR);
                }
                break;
            }
            case 12: {
                for (int i = 0; i < this.searchData.getSize(); ++i) {
                    final PatriciaTreeDataItem dataItem = new PatriciaTreeDataItem(new Node(this.searchData.getElement(i).getObject(), i), ((Integer)this.searchData.getElement(i).getObject()).intValue());
                    ((Node)this.searchData.getElement(i)).setBackgroundColor(PatriciaTreeColors.SEARCH_HIGHLIGHT_COLOR);
                    this.search(this.head, dataItem);
                    ((Node)this.searchData.getElement(i)).setBackgroundColor(PatriciaTreeColors.SEARCH_DONE_COLOR);
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
    
    private PatriciaTreeDataItem search(final PatriciaNode tree, final PatriciaTreeDataItem dataItem) {
        this.dataItemBitArray = this.initialiseBitElementArray(dataItem.getKey());
        this.dataItemBitArray.setColors(PatriciaTreeColors.SEARCH_BIT_ACTIVE_COLOR, PatriciaTreeColors.SEARCH_BIT_HIGHLIGHT_COLOR);
        this.currentPath = new Path(true);
        final PatriciaTreeDataItem retrieved = this.searchKey(tree.getLeft(), dataItem.getKey(), -1);
        this.currentPath = null;
        this.currentSearchTree = null;
        this.keyFoundNode = retrieved.getNode();
        this.dataItemBitArray = null;
        this.keyFoundNode = null;
        if (dataItem.getKey() == retrieved.getKey()) {
            return retrieved;
        }
        return null;
    }
    
    private PatriciaTreeDataItem searchKey(final PatriciaNode tree, final int key, final byte bit) {
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
    
    private void setPosition(final String key, final boolean isPause) {
        if (isPause) {
            this.setPosition(key);
        }
        else {
            this.setPosition("This key doesn't exist");
        }
    }
    
    protected void storeState(final boolean forceState) {
    }
    
    static {
        BUILD_MODE_LABEL = Messages.getString("PatriciaTree.0");
        SEARCH_MODE_LABEL = Messages.getString("PatriciaTree.1");
    }
}
