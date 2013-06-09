import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class BinarySearchTree extends Algorithm implements MethodSelectionListener, ControlListener
{
    private static final byte shrinkFactor = 2;
    private static final Color TEXT_COLOR;
    private static final Color INSERT_HIGHLIGHT_COLOR;
    private static final Color INSERT_ACTIVE_COLOR;
    private static final Color INSERT_DONE_COLOR;
    private static final Color SEARCH_HIGHLIGHT_COLOR;
    private static final Color SEARCH_ACTIVE_COLOR;
    private static final Color SEARCH_DONE_COLOR;
    private static final Color TREE_HIGHLIGHT_COLOR;
    private static final Color TREE_ACTIVE_COLOR;
    private static final Color TREE_RING_COLOR;
    private static final Color TREE_NULL_COLOR;
    private static final Color PATH_COLOR;
    public static NewBackButton backButton;
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private static final int DELETE_MODE = 13;
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
    private static final String DELETE_MODE_LABEL;
    protected static final String PARENT_LABEL;
    protected static final String PTR_LABEL;
    protected static final String DATAITEMPTR_LABEL;
    protected static final String REPLACEMENT_LABEL;
    private int[] insertData;
    private int insertPos;
    private int[] searchData;
    private BinaryTree root;
    private BinaryTree saveRoot;
    private Node searchNode;
    private Node deleteNode;
    private Node saveDeleteNode;
    private Color[] insertDataColor;
    private Color[] searchDataColor;
    private ElementArray searchArray;
    private ElementArray insertArray;
    private Vector<MoveRequest> moveRequests;
    private Vector<NewLinks> newLinks;
    private Node parentNode;
    private Node ptrNode;
    private Node dataItemNode;
    private Node replaceNode;
    private Node nullNode;
    private ExtendedHierarchyTree ptrTree;
    private boolean isBackMode;
    private int executingMode;
    private int nextMode;
    
    public BinarySearchTree(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.moveRequests = new Vector();
        this.newLinks = new Vector();
        this.isBackMode = false;
        this.executingMode = 11;
        this.nextMode = this.executingMode;
        this.insertData = (int[])((int[])data);
        this.searchData = (int[])((int[])data);
        this.root = null;
        this.initialise();
    }
    
    private ExtendedHierarchyTree buildHierarchyTree(final BinaryTree binarySearchTree, final int i) {
        ExtendedHierarchyTree hierarchyTree = null;
        if (binarySearchTree != null) {
            hierarchyTree = new ExtendedHierarchyTree();
            hierarchyTree.add(binarySearchTree.node);
            hierarchyTree.add(binarySearchTree.node);
            hierarchyTree.setBorderColor(Color.white);
            if (binarySearchTree.node == this.ptrNode) {
                this.ptrTree = hierarchyTree;
            }
            ExtendedHierarchyTree temp;
            if (binarySearchTree.leftChild != null) {
                hierarchyTree.addChild(this.buildHierarchyTree(binarySearchTree.leftChild, 0));
            }
            else {
                temp = new ExtendedHierarchyTree(binarySearchTree.leftNode);
                temp = new ExtendedHierarchyTree();
                temp.add(binarySearchTree.leftNode);
                hierarchyTree.addChild(temp);
                if (binarySearchTree.leftNode == this.ptrNode) {
                    this.ptrTree = temp;
                }
            }
            if (binarySearchTree.rightChild != null) {
                hierarchyTree.addChild(this.buildHierarchyTree(binarySearchTree.rightChild, 0));
            }
            else {
                temp = new ExtendedHierarchyTree(binarySearchTree.rightNode);
                temp = new ExtendedHierarchyTree();
                temp.add(binarySearchTree.rightNode);
                hierarchyTree.addChild(temp);
                if (binarySearchTree.rightNode == this.ptrNode) {
                    this.ptrTree = temp;
                }
                hierarchyTree.addChild(temp);
            }
        }
        return hierarchyTree;
    }
    
    protected void changeData(final Object data) {
        if (this.insertData != (int[])((int[])data)) {}
        this.insertData = (int[])((int[])data);
        this.searchData = (int[])((int[])data);
        this.initialise();
    }
    
    protected void clearState() {
        this.saveRoot = null;
    }
    
    private void colorHierarchyTree(final ExtendedHierarchyTree subTree) {
        if (subTree != null) {
            subTree.getLine().showAsThick(true);
            subTree.setParentLineColor(BinarySearchTree.PATH_COLOR);
            if (subTree.getNodes().size() > 0) {
                ((Node)subTree.getNodes().elementAt(0)).setTextColor(BinarySearchTree.PATH_COLOR);
                ((Node)subTree.getNodes().elementAt(0)).setRingColor(BinarySearchTree.PATH_COLOR);
            }
            this.colorHierarchyTree((ExtendedHierarchyTree)subTree.getParent());
        }
    }
    
    public void controlBack(final ControlEvent e) {
        this.isBackMode = true;
    }
    
    public void controlOther(final ControlEvent e) {
        this.isBackMode = false;
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
    
    public void controlStep(final ControlEvent e) {
        this.isBackMode = false;
    }
    
    protected void delete() {
        this.setPosition("4.0");
        if (this.root != null) {
            if (!this.isBackMode) {
                if (!this.enabled) {
                    return;
                }
                DeleteSelection.setEnabled(true);
                while (DeleteSelection.getNode() == null) {
                    if (!this.enabled) {
                        DeleteSelection.setEnabled(false);
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
                this.deleteNode = DeleteSelection.getNode();
                DeleteSelection.setEnabled(false);
                if (!this.enabled) {
                    return;
                }
            }
            final int deleteValue = this.insertData[this.deleteNode.getIdentifier()];
            this.saveRoot = this.saveTree(this.root);
            BinaryTree ptr;
            BinaryTree parent = ptr = this.root;
            this.ptrNode = this.root.node;
            this.setPosition("4.1.1");
            this.parentNode = this.root.node;
            while (ptr.data != deleteValue) {
                this.setPosition("4.2.1");
                parent = ptr;
                this.parentNode = this.ptrNode;
                this.setPosition("4.2.1.1");
                this.setPosition("4.2.1.2");
                if (ptr.data > deleteValue) {
                    this.ptrNode = ptr.leftNode;
                    ptr = ptr.leftChild;
                    this.setPosition("4.2.1.2.1");
                }
                else {
                    this.setPosition("4.2.1.3");
                    this.ptrNode = ptr.rightNode;
                    ptr = ptr.rightChild;
                    this.setPosition("4.2.1.3.1");
                }
            }
            this.setPosition("4.2.1");
            this.setPosition("4.2.2");
            final BinaryTree oldParent = parent;
            this.setPosition("4.2.3");
            final BinaryTree dataItemPtr = parent = ptr;
            this.dataItemNode = this.ptrNode;
            this.parentNode = this.ptrNode;
            this.setPosition("4.2.4");
            if (ptr.rightChild == null) {
                this.ptrNode = ptr.leftNode;
                ptr = ptr.leftChild;
                if (ptr != null) {
                    this.setPosition("4.3.1.3.1");
                    for (; ptr.rightChild != null; ptr = ptr.rightChild, this.setPosition("4.3.1.3.2.2")) {
                        this.setPosition("4.3.1.3.2");
                        parent = ptr;
                        this.parentNode = this.ptrNode;
                        this.setPosition("4.3.1.3.2.1");
                        this.ptrNode = ptr.rightNode;
                    }
                    this.setPosition("4.3.1.3.2");
                    this.setPosition("4.3.1.3.3");
                }
                else {
                    this.setPosition("4.3.1.3.2");
                    this.setPosition("4.3.1.3.3");
                }
                this.replaceNode = this.ptrNode;
                this.setPosition("4.3.1.4.1");
                if (parent != dataItemPtr) {
                    this.newLinks.addElement(new NewLinks(this.parentNode, true, ptr.leftNode));
                    this.setPosition("4.3.1.4.1.1");
                    this.newLinks.addElement(new NewLinks(this.ptrNode, false, dataItemPtr.leftNode));
                    this.setPosition("4.3.1.4.1.2");
                    this.newLinks.addElement(new NewLinks(this.ptrNode, true, dataItemPtr.rightNode));
                    this.setPosition("4.3.1.4.1.3");
                }
                else if (parent != dataItemPtr || ptr != null) {}
                this.dataItemNode = null;
                if (oldParent.data == deleteValue) {
                    this.setPosition("4.3.1.5.1");
                }
                else if (oldParent.leftChild != null && deleteValue == oldParent.leftChild.data) {
                    this.setPosition("4.3.1.5.1");
                    this.newLinks.addElement(new NewLinks(oldParent.node, false, this.ptrNode));
                    this.setPosition("4.3.1.5.2");
                }
                else {
                    this.setPosition("4.3.1.5.1");
                    this.setPosition("4.3.1.5.2");
                    this.newLinks.addElement(new NewLinks(oldParent.node, true, this.ptrNode));
                    this.setPosition("4.3.1.5.3");
                }
                this.newLinks.removeAllElements();
                if (parent != dataItemPtr) {
                    parent.rightChild = ptr.leftChild;
                    parent.rightNode = ptr.leftNode;
                    ptr.leftNode = dataItemPtr.leftNode;
                    ptr.rightNode = dataItemPtr.rightNode;
                    ptr.leftChild = dataItemPtr.leftChild;
                    ptr.rightChild = dataItemPtr.rightChild;
                }
                else if (parent == dataItemPtr && ptr != null) {
                    ptr.rightNode = dataItemPtr.rightNode;
                    ptr.rightChild = dataItemPtr.rightChild;
                }
                if (oldParent.data == deleteValue) {
                    this.root = ptr;
                    if (this.root != null) {
                        this.root.node = this.ptrNode;
                        this.root.node.setMarkersBelowValue(false);
                        this.root.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.47")));
                    }
                    final Object replaceNode = null;
                    this.parentNode = replaceNode;
                    this.ptrNode = replaceNode;
                    this.replaceNode = replaceNode;
                    this.setPosition("4.3.1.5.1.1");
                }
                else if (oldParent.leftChild != null && deleteValue == oldParent.leftChild.data) {
                    oldParent.leftChild = ptr;
                    oldParent.leftNode = this.ptrNode;
                    final Object replaceNode2 = null;
                    this.parentNode = replaceNode2;
                    this.ptrNode = replaceNode2;
                    this.replaceNode = replaceNode2;
                    this.setPosition(Messages.getString("BinarySearchTree.49"));
                }
                else {
                    oldParent.rightChild = ptr;
                    oldParent.rightNode = this.ptrNode;
                    final Object replaceNode3 = null;
                    this.parentNode = replaceNode3;
                    this.ptrNode = replaceNode3;
                    this.replaceNode = replaceNode3;
                    this.setPosition("4.3.1.5.3.1");
                }
            }
            else if (ptr.rightChild != null) {
                this.ptrNode = ptr.rightNode;
                ptr = ptr.rightChild;
                this.setPosition("4.3.2.3.1");
                for (; ptr.leftChild != null; ptr = ptr.leftChild, this.setPosition("4.3.2.3.2.2")) {
                    this.setPosition("4.3.2.3.2");
                    parent = ptr;
                    this.parentNode = this.ptrNode;
                    this.setPosition("4.3.2.3.2.1");
                    this.ptrNode = ptr.leftNode;
                }
                this.setPosition("4.3.2.3.2");
                this.setPosition("4.3.2.3.3");
                this.replaceNode = this.ptrNode;
                this.setPosition("4.3.2.4.1");
                if (parent != dataItemPtr) {
                    final NewLinks n1 = new NewLinks(this.parentNode, false, ptr.rightNode);
                    this.newLinks.addElement(n1);
                    this.setPosition("4.3.2.4.1.1");
                    final NewLinks n2 = new NewLinks(this.ptrNode, false, dataItemPtr.leftNode);
                    this.newLinks.addElement(n2);
                    this.setPosition("4.3.2.4.1.2");
                    final NewLinks n3 = new NewLinks(this.ptrNode, true, dataItemPtr.rightNode);
                    this.newLinks.addElement(n3);
                    this.setPosition("4.3.2.4.1.3");
                }
                else {
                    this.setPosition("4.3.2.4.2");
                    this.newLinks.addElement(new NewLinks(this.ptrNode, false, dataItemPtr.leftNode));
                    this.setPosition("4.3.2.4.2.1");
                }
                this.dataItemNode = null;
                if (oldParent.data == deleteValue) {
                    this.setPosition("4.3.2.5.1");
                }
                else if (oldParent.leftChild != null && deleteValue == oldParent.leftChild.data) {
                    this.setPosition("4.3.2.5.1");
                    this.newLinks.addElement(new NewLinks(oldParent.node, false, this.ptrNode));
                    this.setPosition("4.3.2.5.2");
                }
                else {
                    this.setPosition("4.3.2.5.1");
                    this.setPosition("4.3.2.5.2");
                    this.newLinks.addElement(new NewLinks(oldParent.node, true, this.ptrNode));
                    this.setPosition("4.3.2.5.3");
                }
                this.newLinks.removeAllElements();
                if (parent != dataItemPtr) {
                    parent.leftChild = ptr.rightChild;
                    parent.leftNode = ptr.rightNode;
                    ptr.leftNode = dataItemPtr.leftNode;
                    ptr.rightNode = dataItemPtr.rightNode;
                    ptr.leftChild = dataItemPtr.leftChild;
                    ptr.rightChild = dataItemPtr.rightChild;
                }
                else {
                    ptr.leftNode = dataItemPtr.leftNode;
                    ptr.leftChild = dataItemPtr.leftChild;
                }
                if (oldParent.data == deleteValue) {
                    this.root = ptr;
                    if (this.root != null) {
                        this.root.node = this.ptrNode;
                        this.root.node.setMarkersBelowValue(false);
                        this.root.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.69")));
                    }
                    final Object replaceNode4 = null;
                    this.parentNode = replaceNode4;
                    this.ptrNode = replaceNode4;
                    this.replaceNode = replaceNode4;
                    this.setPosition("4.3.2.5.1.1");
                }
                else if (oldParent.leftChild != null && deleteValue == oldParent.leftChild.data) {
                    oldParent.leftChild = ptr;
                    oldParent.leftNode = this.ptrNode;
                    final Object replaceNode5 = null;
                    this.parentNode = replaceNode5;
                    this.ptrNode = replaceNode5;
                    this.replaceNode = replaceNode5;
                    this.setPosition("4.3.2.5.2.1");
                }
                else {
                    oldParent.rightChild = ptr;
                    oldParent.rightNode = this.ptrNode;
                    final Object replaceNode6 = null;
                    this.parentNode = replaceNode6;
                    this.ptrNode = replaceNode6;
                    this.replaceNode = replaceNode6;
                    this.setPosition("4.3.2.5.3.1");
                }
            }
        }
    }
    
    protected Vector<Object> generateQuestions() {
        return null;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object object, final int numberOfSteps) {
        final MultipleTween tweens = new MultipleTween(paintable);
        for (int i = 0; i < this.moveRequests.size(); ++i) {
            final MoveRequest move = (MoveRequest)this.moveRequests.elementAt(i);
            final Node source = move.getSource();
            final Node dest = move.getDest();
            tweens.add(new MoveTween(paintable, source, source.getPosition(), dest.getPosition(), false, numberOfSteps));
        }
        return tweens;
    }
    
    public Node getDataItemNode() {
        return this.dataItemNode;
    }
    
    public Node getdeleteNode() {
        if (this.deleteNode == null) {
            return null;
        }
        final Node node;
        final Node newNode = node = new Node(new Integer(this.insertData[this.deleteNode.getIdentifier()]), 0);
        node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.7")));
        return newNode;
    }
    
    public ExtendedHierarchyTree getHierarchyTree() {
        this.ptrTree = null;
        ExtendedHierarchyTree test = new ExtendedHierarchyTree();
        test = this.makeHierarchyTree(test, this.root);
        this.colorHierarchyTree(this.ptrTree);
        return test;
    }
    
    public ElementArray getInsertElementArray() {
        for (int i = 0; i < this.insertArray.getSize(); ++i) {
            ((Node)this.insertArray.getElement(i)).setBackgroundColor(this.insertDataColor[i]);
            ((Node)this.insertArray.getElement(i)).setHighlightColor(BinarySearchTree.SEARCH_HIGHLIGHT_COLOR);
            ((Node)this.insertArray.getElement(i)).setTextColor(BinarySearchTree.TEXT_COLOR);
        }
        return this.insertArray;
    }
    
    public Vector<NewLinks> getNewLinks() {
        return this.newLinks;
    }
    
    public Node getParentNode() {
        return this.parentNode;
    }
    
    public Node getPtrNode() {
        return this.ptrNode;
    }
    
    public Node getReplaceNode() {
        return this.replaceNode;
    }
    
    public ElementArray getSearchElementArray() {
        for (int i = 0; i < this.searchArray.getSize(); ++i) {
            ((Node)this.searchArray.getElement(i)).setBackgroundColor(this.searchDataColor[i]);
            ((Node)this.searchArray.getElement(i)).setHighlightColor(BinarySearchTree.SEARCH_HIGHLIGHT_COLOR);
            ((Node)this.searchArray.getElement(i)).setTextColor(BinarySearchTree.TEXT_COLOR);
        }
        return this.searchArray;
    }
    
    protected boolean hasQuestions() {
        return false;
    }
    
    private void initialise() {
        final Object o = null;
        this.parentNode = o;
        this.ptrNode = o;
        if (!this.isBackMode) {
            this.deleteNode = null;
        }
        this.resetTreeColor(this.root);
        this.newLinks.removeAllElements();
        this.nullNode = new Node("", 0);
        this.nullNode.setBackgroundColor(BinarySearchTree.TREE_NULL_COLOR);
        this.nullNode.setTextColor(BinarySearchTree.TEXT_COLOR);
        this.nullNode.setWidth(this.nullNode.getWidth() / 2);
        this.nullNode.setHeight(this.nullNode.getHeight() / 2);
        if (BinarySearchTree.backButton != null) {
            BinarySearchTree.backButton.switchOff(false);
        }
    }
    
    protected void initialiseMethods(final String string, final MethodSelectable methodSelectable) {
        final MethodSelection methodSelection1 = new MethodSelection(BinarySearchTree.BUILD_MODE_LABEL, string, Messages.getString("BinarySearchTree.91"), "2.1s", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection2 = new MethodSelection(BinarySearchTree.SEARCH_MODE_LABEL, string, Messages.getString("BinarySearchTree.92"), "3s", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection3 = new MethodSelection(BinarySearchTree.DELETE_MODE_LABEL, string, Messages.getString("BinarySearchTree.93"), "4s", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelection(methodSelection3, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    protected void insert() {
        if (this.insertData.length > 0) {
            if (this.insertPos == 0) {
                this.insertDataColor[0] = BinarySearchTree.INSERT_HIGHLIGHT_COLOR;
                this.root = new BinaryTree(this.insertData[0], 0);
                this.setPosition("1.1");
                this.insertDataColor[0] = BinarySearchTree.INSERT_DONE_COLOR;
                this.root.node.setBackgroundColor(BinarySearchTree.TREE_ACTIVE_COLOR);
                this.root.node.setMarkersBelowValue(false);
                this.root.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.118")));
                this.insertPos = this.insertPos + 1;
            }
            for (int i = this.insertPos; i < this.insertData.length; ++i) {
                this.insertDataColor[i] = BinarySearchTree.INSERT_HIGHLIGHT_COLOR;
                this.setPosition("2");
                BinaryTree ptr;
                BinaryTree parent = ptr = this.root;
                final Node node = this.root.node;
                this.ptrNode = node;
                this.parentNode = node;
                this.setPosition("2.1.1.1");
                while (ptr != null) {
                    this.setPosition("2.1.2.1");
                    parent = ptr;
                    this.parentNode = this.ptrNode;
                    this.setPosition("2.1.2.1.1");
                    this.setPosition("2.1.2.1.2");
                    if (this.insertData[i] < ptr.data) {
                        this.ptrNode = parent.leftNode;
                        ptr = ptr.leftChild;
                        this.setPosition("2.1.2.1.2.1");
                    }
                    else {
                        this.setPosition("2.1.2.1.3");
                        this.ptrNode = parent.rightNode;
                        ptr = parent.rightChild;
                        this.setPosition("2.1.2.1.3.1");
                    }
                }
                this.setPosition("2.1.2.2");
                this.setPosition("2.1.2.1");
                final Object o = null;
                this.ptrNode = o;
                this.parentNode = o;
                this.setPosition("2.1.3.1");
                if (!this.enabled) {
                    return;
                }
                if (this.insertData[i] < parent.data) {
                    parent.leftChild = new BinaryTree(this.insertData[i], i);
                    parent.leftNode = parent.leftChild.node;
                    this.setPosition("2.1.3.1.1");
                }
                else {
                    this.setPosition("2.1.3.2");
                    parent.rightChild = new BinaryTree(this.insertData[i], i);
                    parent.rightNode = parent.rightChild.node;
                    this.setPosition("2.1.3.2.1");
                }
                this.resetTreeColor(this.root);
                this.insertDataColor[i] = BinarySearchTree.INSERT_DONE_COLOR;
                this.insertPos = this.insertPos + 1;
            }
        }
    }
    
    public boolean isBuildMode() {
        return this.executingMode == 11;
    }
    
    public boolean isDeleteMode() {
        return this.executingMode == 13;
    }
    
    public boolean isSearchMode() {
        return this.executingMode == 12;
    }
    
    private ExtendedHierarchyTree makeHierarchyTree(final ExtendedHierarchyTree parent, final BinaryTree binarySearchTree) {
        if (binarySearchTree != null) {
            final ExtendedHierarchyTree hierarchyTree = new ExtendedHierarchyTree(parent, binarySearchTree.node);
            if (binarySearchTree.node == this.ptrNode) {
                this.ptrTree = hierarchyTree;
            }
            ExtendedHierarchyTree temp;
            if (binarySearchTree.leftChild != null) {
                hierarchyTree.addChild(this.makeHierarchyTree(hierarchyTree, binarySearchTree.leftChild));
            }
            else {
                temp = new ExtendedHierarchyTree(hierarchyTree, binarySearchTree.leftNode);
                hierarchyTree.addChild(temp);
                if (binarySearchTree.leftNode == this.ptrNode) {
                    this.ptrTree = temp;
                }
            }
            if (binarySearchTree.rightChild != null) {
                hierarchyTree.addChild(this.makeHierarchyTree(hierarchyTree, binarySearchTree.rightChild));
            }
            else {
                temp = new ExtendedHierarchyTree(hierarchyTree, binarySearchTree.rightNode);
                hierarchyTree.addChild(temp);
                if (binarySearchTree.rightNode == this.ptrNode) {
                    this.ptrTree = temp;
                }
            }
            return hierarchyTree;
        }
        return null;
    }
    
    protected void makeQuestion(final int i) {
    }
    
    protected void move(final Node source, final Node dest) {
        this.moveRequests.addElement(new MoveRequest(source, dest));
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (BinarySearchTree.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
        if (BinarySearchTree.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 12;
        }
        if (BinarySearchTree.DELETE_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 13;
        }
    }
    
    public void reInitialise(final Object data) {
        this.insertData = (int[])((int[])data);
        this.searchData = (int[])((int[])data);
        if (this.executingMode == 11) {
            this.saveRoot = null;
        }
        this.root = this.saveTree(this.saveRoot);
        this.deleteNode = this.saveDeleteNode;
        if (this.root != null) {
            this.root.node.setMarkersBelowValue(false);
            this.root.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.8")));
        }
        this.insertPos = 0;
        this.insertDataColor = new Color[this.insertData.length];
        this.searchDataColor = new Color[this.insertData.length];
        for (int i = 0; i < this.insertData.length; ++i) {
            this.insertDataColor[i] = BinarySearchTree.INSERT_ACTIVE_COLOR;
        }
        this.insertArray = new ElementArray(0, 0);
        this.insertArray.setColumGap(0);
        this.insertArray.setElementWidth(20);
        Node node;
        for (int i = 0; i < this.insertData.length; ++i) {
            node = new Node(new Integer(this.insertData[i]), i);
            node.setBackgroundColor(this.insertDataColor[i]);
            node.setHighlightColor(BinarySearchTree.INSERT_HIGHLIGHT_COLOR);
            node.setTextColor(BinarySearchTree.TEXT_COLOR);
            this.insertArray.setValue(i, node);
        }
        for (int i = 0; i < this.searchData.length; ++i) {
            this.searchDataColor[i] = BinarySearchTree.SEARCH_ACTIVE_COLOR;
        }
        if (!this.isBackMode) {
            this.searchArray = new ElementArray(0, 0);
            this.searchArray.setColumGap(0);
            this.searchArray.setElementWidth(20);
            for (int i = 0; i < this.searchData.length; ++i) {
                node = new Node(new Integer(this.searchData[i]), i);
                node.setBackgroundColor(this.searchDataColor[i]);
                node.setHighlightColor(BinarySearchTree.SEARCH_HIGHLIGHT_COLOR);
                node.setTextColor(BinarySearchTree.TEXT_COLOR);
                this.searchArray.setValue(i, node);
            }
        }
        else {
            for (int i = 0; i < this.searchData.length; ++i) {
                node = (Node)this.searchArray.getElement(i);
                node.removeAllMarkers();
            }
        }
        this.initialise();
    }
    
    protected void removeAllAnimationRequests() {
        this.moveRequests.removeAllElements();
    }
    
    protected void removeAllQuestions() {
    }
    
    private void resetTreeColor(final BinaryTree tree) {
        if (tree != null) {
            tree.node.setBackgroundColor(BinarySearchTree.TREE_ACTIVE_COLOR);
            tree.node.setRingColor(BinarySearchTree.TREE_RING_COLOR);
            tree.node.setTextColor(BinarySearchTree.TEXT_COLOR);
            this.resetTreeColor(tree.leftChild);
            this.resetTreeColor(tree.rightChild);
        }
    }
    
    protected void run() {
        if (!this.enabled) {
            return;
        }
        this.setPosition("0");
        if (this.executingMode == 11) {
            this.insert();
        }
        else if (this.executingMode == 12) {
            this.search();
        }
        else if (this.executingMode == 13) {
            this.delete();
        }
    }
    
    protected void run(final boolean canChangeMode) {
        if (canChangeMode) {
            this.executingMode = this.nextMode;
        }
        this.run();
    }
    
    protected BinaryTree saveTree(final BinaryTree original) {
        if (original == null) {
            return null;
        }
        final int index = original.node.getIdentifier();
        final int data = original.data;
        final BinaryTree newTree = new BinaryTree(data, index);
        if (this.deleteNode == original.node) {
            this.saveDeleteNode = newTree.node;
        }
        newTree.leftChild = this.saveTree(original.leftChild);
        newTree.rightChild = this.saveTree(original.rightChild);
        if (newTree.leftChild != null) {
            newTree.leftNode = newTree.leftChild.node;
        }
        if (newTree.rightChild != null) {
            newTree.rightNode = newTree.rightChild.node;
        }
        return newTree;
    }
    
    protected void search() {
        this.makeQuestion(13);
        this.setPosition("3.0");
        if (this.root != null) {
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
            this.searchDataColor[this.searchNode.getIdentifier()] = BinarySearchTree.SEARCH_HIGHLIGHT_COLOR;
            final int searchValue = Integer.valueOf(this.searchNode.getDisplayString()).intValue();
            BinaryTree ptr;
            BinaryTree parent = ptr = this.root;
            this.ptrNode = this.root.node;
            this.setPosition("3.1.1");
            while (ptr != null) {
                this.setPosition("3.2");
                this.setPosition("3.2.1");
                if (ptr.data == searchValue) {
                    this.resetTreeColor(this.root);
                    if (!this.enabled) {
                        this.searchDataColor[this.searchNode.getIdentifier()] = BinarySearchTree.SEARCH_ACTIVE_COLOR;
                        return;
                    }
                    this.searchNode.addMarker(new StringMarker("F"));
                    this.searchNode.addMarker(new StringMarker("o"));
                    this.searchNode.addMarker(new StringMarker("u"));
                    this.searchNode.addMarker(new StringMarker("n"));
                    this.searchNode.addMarker(new StringMarker("d"));
                    this.searchDataColor[this.searchNode.getIdentifier()] = BinarySearchTree.SEARCH_DONE_COLOR;
                    this.setPosition("3.2.1.1");
                    return;
                }
                else {
                    parent = ptr;
                    this.setPosition("3.2.2.1.2");
                    if (searchValue < ptr.data) {
                        this.ptrNode = parent.leftNode;
                        ptr = ptr.leftChild;
                        this.setPosition("3.2.2.1.2.1");
                    }
                    else {
                        this.setPosition("3.2.2.1.3");
                        this.ptrNode = parent.rightNode;
                        ptr = parent.rightChild;
                        this.setPosition("3.2.2.1.3.1");
                    }
                }
            }
            this.setPosition("3.2");
            this.resetTreeColor(this.root);
            if (!this.enabled) {
                this.searchDataColor[this.searchNode.getIdentifier()] = BinarySearchTree.SEARCH_ACTIVE_COLOR;
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
            this.searchDataColor[this.searchNode.getIdentifier()] = BinarySearchTree.SEARCH_DONE_COLOR;
            this.setPosition("3.3.3");
        }
    }
    
    protected void setEnabled(final boolean state) {
        this.enabled = state;
    }
    
    protected void storeState(final boolean forceState) {
        if (this.executingMode == 13) {
            this.saveRoot.node.setMarkersBelowValue(false);
            this.saveRoot.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.116")));
            this.deleteNode = this.saveDeleteNode;
        }
        else {
            this.saveRoot = this.saveTree(this.root);
            this.saveRoot.node.setMarkersBelowValue(false);
            this.saveRoot.node.addMarker(new StringMarker(Messages.getString("BinarySearchTree.117")));
            this.deleteNode = this.saveDeleteNode;
        }
    }
    
    static {
        TEXT_COLOR = Color.black;
        INSERT_HIGHLIGHT_COLOR = new Color(0, 255, 0);
        INSERT_ACTIVE_COLOR = new Color(64, 191, 64);
        INSERT_DONE_COLOR = new Color(96, 127, 96);
        SEARCH_HIGHLIGHT_COLOR = new Color(64, 255, 255);
        SEARCH_ACTIVE_COLOR = new Color(64, 191, 191);
        SEARCH_DONE_COLOR = new Color(0, 127, 127);
        TREE_HIGHLIGHT_COLOR = new Color(255, 255, 0);
        TREE_ACTIVE_COLOR = new Color(191, 191, 64);
        TREE_RING_COLOR = Color.black;
        TREE_NULL_COLOR = Color.blue;
        PATH_COLOR = Color.red;
        BUILD_MODE_LABEL = Messages.getString("BinarySearchTree.0");
        SEARCH_MODE_LABEL = Messages.getString("BinarySearchTree.1");
        DELETE_MODE_LABEL = Messages.getString("BinarySearchTree.2");
        PARENT_LABEL = Messages.getString("BinarySearchTree.3");
        PTR_LABEL = Messages.getString("BinarySearchTree.4");
        DATAITEMPTR_LABEL = Messages.getString("BinarySearchTree.5");
        REPLACEMENT_LABEL = Messages.getString("BinarySearchTree.6");
    }
}
