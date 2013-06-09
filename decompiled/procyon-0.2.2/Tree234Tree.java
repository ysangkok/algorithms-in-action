import localization.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class Tree234Tree extends Algorithm implements MethodSelectionListener, ControlListener
{
    protected static int POS;
    private final Color T234_NODE_COLOR;
    private final Color T234_COMPARE_COLOR;
    private final Color T234_SPLIT_COLOR;
    private final Color RB_NODE_COLOR;
    private final Color PATH_COLOR;
    public boolean isBackMode;
    protected static final int BUILD_MODE = 11;
    protected static final int SEARCH_MODE = 12;
    protected int[] data;
    protected boolean[] searchResults;
    protected int position;
    protected int executingMode;
    protected int nextMode;
    protected Tree234RedBlack tree;
    protected Tree234RedBlack baseTree;
    protected int basePosition;
    protected boolean canStoreState;
    protected Vector<E> insertRequests;
    protected Vector<E> deleteRequests;
    protected Vector<E> deleter;
    protected Vector<E> spliter;
    protected Vector<E> comparer;
    protected Node animationNode;
    protected Node growNode;
    protected HierarchyTree parentNode;
    protected HierarchyTree grandParentNode;
    protected HierarchyTree greatGrandParentNode;
    protected HierarchyTree ptrNode;
    protected HierarchyTree pathNode;
    protected static final String BUILD_MODE_LABEL;
    protected static final String SEARCH_MODE_LABEL;
    public boolean update234;
    
    public Tree234Tree(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.T234_NODE_COLOR = Color.lightGray;
        this.T234_COMPARE_COLOR = Color.red;
        this.T234_SPLIT_COLOR = Color.green;
        this.RB_NODE_COLOR = Color.lightGray;
        this.PATH_COLOR = Color.green;
        this.isBackMode = false;
        this.position = -1;
        this.executingMode = 11;
        this.nextMode = this.executingMode;
        this.canStoreState = false;
        this.insertRequests = new Vector();
        this.deleteRequests = new Vector();
        this.deleter = new Vector();
        this.spliter = new Vector();
        this.comparer = new Vector();
        this.animationNode = null;
        this.growNode = null;
        this.update234 = true;
        this.data = (int[])((int[])data);
        this.baseTree = null;
        this.basePosition = 0;
        this.initialise();
    }
    
    protected void initialiseMethods(final String dataDir, final MethodSelectable methodSelectable) {
        final MethodSelection build = new MethodSelection(Tree234Tree.BUILD_MODE_LABEL, dataDir, Messages.getString("Tree234Tree.2"), "2.2a", this.getLogger(), this.getBreakPoint());
        final MethodSelection search = new MethodSelection(Tree234Tree.SEARCH_MODE_LABEL, dataDir, Messages.getString("Tree234Tree.4"), "4", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(build, true);
        methodSelectable.addMethodSelection(search, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (Tree234Tree.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
        else if (Tree234Tree.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 12;
        }
    }
    
    public HierarchyTree getPtrNode() {
        return this.ptrNode;
    }
    
    public HierarchyTree getParentNode() {
        return this.parentNode;
    }
    
    public HierarchyTree getGrandParentNode() {
        return this.grandParentNode;
    }
    
    public HierarchyTree getGreatGrandParentNode() {
        return this.greatGrandParentNode;
    }
    
    public Node getAnimationNode() {
        return this.animationNode;
    }
    
    public Node getGrowNode() {
        return this.growNode;
    }
    
    public HierarchyTree getHierarchyTree(final int nodeWidth) {
        final HierarchyTree h1 = this.getHierarchyTree(null, this.tree, this.tree, 0, nodeWidth, true, false);
        return h1;
    }
    
    protected HierarchyTree getHierarchyTree(final HierarchyTree parent, final Tree234RedBlack tree, final Tree234RedBlack root, final int level, final int nodeWidth, final boolean adjustMarkers, final boolean animate) {
        if (tree == null) {
            return null;
        }
        Node node;
        if (!animate) {
            node = new RoundNode(new Integer(tree.getDataItem()), tree.getPositionInserted());
        }
        else {
            node = new Node(new Integer(tree.getDataItem()), tree.getPositionInserted());
        }
        node.setWidth(nodeWidth);
        if (tree.newNode & animate) {
            this.insertRequests.addElement(node);
        }
        if ((((tree.getPositionInserted() != Tree234RedBlack.deletePtr) ? false : true) & animate) != false) {
            this.deleter.addElement(node);
        }
        if ((((tree.getPositionInserted() != Tree234RedBlack.comparePtr) ? false : true) & animate) != false) {
            this.comparer.addElement(node);
        }
        if ((((tree.getPositionInserted() != Tree234RedBlack.splitPtr) ? false : true) & animate) != false) {
            this.spliter.addElement(node);
        }
        if ((((tree.getPositionInserted() != Tree234RedBlack.splitPtr2) ? false : true) & animate) != false) {
            this.spliter.addElement(node);
        }
        if ((((tree.getPositionInserted() != Tree234RedBlack.splitPtr3) ? false : true) & animate) != false) {
            this.spliter.addElement(node);
        }
        if (tree.redNode) {
            node.setHighlightColor(Color.red);
        }
        else {
            node.setHighlightColor(Color.gray);
        }
        node.setBackgroundColor(this.RB_NODE_COLOR);
        final HierarchyTree hierarchyTree = new HierarchyTree(parent, node);
        if (!animate) {
            hierarchyTree.setDrawBorder(false);
        }
        if (root.ptr != null) {
            if (root.ptr == tree) {
                this.pathNode = hierarchyTree;
            }
        }
        else if (root.parent == tree) {
            this.pathNode = hierarchyTree;
        }
        if (adjustMarkers) {
            if (root.ptr != null && root.ptr == tree) {
                this.ptrNode = hierarchyTree;
            }
            if (root.parent != null && root.parent == tree) {
                this.parentNode = hierarchyTree;
            }
            if (root.grandParent != null && root.grandParent == tree) {
                this.grandParentNode = hierarchyTree;
            }
            if (root.greatGrandParent != null && root.greatGrandParent == tree) {
                this.greatGrandParentNode = hierarchyTree;
            }
        }
        if (tree.leftChild == null && tree.rightChild != null) {
            final Node fakeLeftNode = new Node(new Integer(0), -1);
            fakeLeftNode.setWidth(nodeWidth);
            fakeLeftNode.setIsVisible(false);
            final HierarchyTree fakeLeft = new HierarchyTree(hierarchyTree, fakeLeftNode);
            hierarchyTree.addChild(fakeLeft);
        }
        else if (tree.leftChild != null) {
            hierarchyTree.addChild(this.getHierarchyTree(hierarchyTree, tree.leftChild, root, level + 1, nodeWidth, adjustMarkers, animate));
        }
        if (tree.rightChild == null && tree.leftChild != null) {
            final Node fakeRightNode = new Node(new Integer(0), -1);
            fakeRightNode.setWidth(nodeWidth);
            fakeRightNode.setIsVisible(false);
            final HierarchyTree fakeRight = new HierarchyTree(hierarchyTree, fakeRightNode);
            hierarchyTree.addChild(fakeRight);
        }
        else if (tree.rightChild != null) {
            hierarchyTree.addChild(this.getHierarchyTree(hierarchyTree, tree.rightChild, root, level + 1, nodeWidth, adjustMarkers, animate));
        }
        if (parent != null) {
            if (tree.redNode) {
                hierarchyTree.setParentLineColor(Color.red);
                final Line pLine = hierarchyTree.getLine();
                pLine.showAsThick(true);
                pLine.setLineThickness(6);
            }
            else {
                hierarchyTree.setParentLineColor(Color.black);
            }
        }
        return hierarchyTree;
    }
    
    public HierarchyTree getBalancedHierarchyTree(final int nodeWidth) {
        final HierarchyTree hierarchyTree = this.getHierarchyTree(null, this.tree, this.tree, 0, nodeWidth, false, true);
        this.colorHierarchyTree(this.pathNode);
        this.colorTree(hierarchyTree, this.T234_NODE_COLOR);
        this.balanceHierarchyTree(hierarchyTree);
        return hierarchyTree;
    }
    
    private void colorHierarchyTree(final HierarchyTree subTree) {
        if (subTree != null) {
            subTree.getLine().showAsThick(true);
            subTree.setParentLineColor(this.PATH_COLOR);
            this.colorHierarchyTree((HierarchyTree)subTree.getParent());
        }
    }
    
    public void colorTree(final HierarchyTree h, final Color c) {
        if (h == null) {
            return;
        }
        final Vector<E> nodes = h.getNodes();
        for (int v = 0; v < nodes.size(); ++v) {
            final Node node = (Node)nodes.elementAt(v);
            node.setBackgroundColor(c);
        }
        for (int i = h.getNumberOfChildren() - 1; i >= 0; --i) {
            final HierarchyTree child = (HierarchyTree)h.getChild(i);
            this.colorTree(child, c);
        }
    }
    
    protected void balanceHierarchyTree(final HierarchyTree hierarchyTree) {
        if (hierarchyTree == null) {
            return;
        }
        final Node bNode = hierarchyTree.getNodeAt(1);
        Node cNode;
        for (int v = 0; v < this.comparer.size(); ++v) {
            cNode = (Node)this.comparer.elementAt(v);
            if (cNode == bNode) {
                this.comparer.removeElementAt(v);
                cNode.setTextColor(this.T234_COMPARE_COLOR);
                cNode.setRingColor(this.T234_COMPARE_COLOR);
            }
        }
        for (int v = 0; v < this.spliter.size(); ++v) {
            cNode = (Node)this.spliter.elementAt(v);
            if (cNode == bNode) {
                this.spliter.removeElementAt(v);
                cNode.setBackgroundColor(this.T234_SPLIT_COLOR);
            }
        }
        for (int v = 0; v < this.deleter.size(); ++v) {
            cNode = (Node)this.deleter.elementAt(v);
            if (cNode == bNode && (hierarchyTree.getParent() != null || hierarchyTree.numberOfElements() > 1)) {
                this.insertRequests.addElement(bNode);
                this.deleter.removeElementAt(v);
                this.animationNode = new Node(cNode.getObject(), cNode.getIdentifier());
                this.animationNode.setBackgroundColor(this.T234_SPLIT_COLOR);
            }
            else if (cNode == bNode) {
                this.deleter.removeElementAt(v);
                this.insertRequests.addElement(bNode);
                this.animationNode = new Node(cNode.getObject(), cNode.getIdentifier());
                this.animationNode.setBackgroundColor(this.T234_SPLIT_COLOR);
            }
        }
        for (int i = hierarchyTree.getNumberOfChildren() - 1; i >= 0; --i) {
            final HierarchyTree child = (HierarchyTree)hierarchyTree.getChild(i);
            this.balanceHierarchyTree(child);
        }
        final Vector<E> children = hierarchyTree.getChildren();
        for (int j = children.size() - 1; j >= 0; --j) {
            final HierarchyTree child2 = (HierarchyTree)children.elementAt(j);
            boolean shouldBalance = true;
            final Vector<E> nodes = child2.getNodes();
            int v2 = 0;
            while (v2 < nodes.size()) {
                final Node node = (Node)nodes.elementAt(v2);
                if (node.getHighlightColor() != Color.red) {
                    shouldBalance = false;
                    break;
                }
                ++v2;
            }
            if (shouldBalance) {
                if (j == 0) {
                    int k = nodes.size() - 1;
                    while (k >= 0) {
                        final Node aNode = (Node)nodes.elementAt(k);
                        if (aNode.getIsVisible()) {
                            hierarchyTree.add(aNode, 0);
                        }
                        final Vector<E> childTrees = child2.getChildren();
                        int a = childTrees.size() - 1;
                        while (a >= 0) {
                            final HierarchyTree childTree = (HierarchyTree)childTrees.elementAt(a);
                            hierarchyTree.insertChildAt(childTree, 0);
                            --a;
                        }
                        --k;
                    }
                }
                else {
                    int k = 0;
                    while (k < nodes.size()) {
                        final Node aNode = (Node)nodes.elementAt(k);
                        if (aNode.getIsVisible()) {
                            hierarchyTree.add(aNode);
                        }
                        final Vector<E> childTrees = child2.getChildren();
                        int a = 0;
                        while (a < childTrees.size()) {
                            final HierarchyTree childTree = (HierarchyTree)childTrees.elementAt(a);
                            hierarchyTree.addChild(childTree);
                            ++a;
                        }
                        ++k;
                    }
                }
                hierarchyTree.removeChild(child2);
            }
        }
    }
    
    protected void initialise() {
        if (this.executingMode == 11 || this.nextMode == 11) {
            if (this.baseTree != null) {
                this.tree = this.baseTree.copy();
            }
            else {
                this.tree = null;
            }
        }
        this.insertRequests = new Vector();
        this.deleteRequests = new Vector();
        this.deleter = new Vector();
        this.spliter = new Vector();
        this.comparer = new Vector();
        final Object o = null;
        this.ptrNode = o;
        this.greatGrandParentNode = o;
        this.grandParentNode = o;
        this.parentNode = o;
        this.position = -1;
        Tree234RedBlack.POSITION = this.basePosition;
    }
    
    public void reInitialise(final Object data) {
        this.data = (int[])((int[])data);
        this.initialise();
    }
    
    protected void changeData(final Object data) {
        this.data = (int[])((int[])data);
        this.position = -1;
        this.save();
    }
    
    protected void storeState(final boolean forceStore) {
        if (this.canStoreState || forceStore) {
            this.save();
        }
    }
    
    private void save() {
        if (this.tree != null) {
            this.baseTree = this.tree.copy();
        }
        else {
            this.baseTree = null;
        }
        this.basePosition = Tree234RedBlack.POSITION;
    }
    
    protected void clearState() {
        this.baseTree = null;
        this.basePosition = 0;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        final MultipleTween tweens = new MultipleTween(paintable);
        for (int i = 0; i < this.insertRequests.size(); ++i) {
            tweens.add(new InsertTween(null, (Node)((Node)this.insertRequests.elementAt(i)), numberOfSteps));
        }
        for (int i = 0; i < this.deleteRequests.size(); ++i) {
            tweens.add(new DeleteTween(null, (Node)((Node)this.deleteRequests.elementAt(i)), numberOfSteps));
        }
        if (this.insertRequests.size() > 0 || this.deleteRequests.size() > 0) {
            return tweens;
        }
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
        this.insertRequests = new Vector();
        this.deleteRequests = new Vector();
        this.removeTree234TreeDeletes(this.tree);
        this.removeTree234TreeInserts(this.tree);
        this.growNode = null;
        this.animationNode = null;
    }
    
    protected void removeTree234TreeInserts(final Tree234RedBlack tree) {
        if (tree != null) {
            tree.newNode = false;
            if (tree.getLeftChild() != null) {
                this.removeTree234TreeInserts(tree.getLeftChild());
            }
            if (tree.getRightChild() != null) {
                this.removeTree234TreeInserts(tree.getRightChild());
            }
        }
    }
    
    protected void removeTree234TreeDeletes(final Tree234RedBlack tree) {
        if (tree != null) {
            tree.deleteNode = false;
            Tree234RedBlack.deletePtr = -1;
            if (tree.getLeftChild() != null) {
                this.removeTree234TreeDeletes(tree.getLeftChild());
            }
            if (tree.getRightChild() != null) {
                this.removeTree234TreeDeletes(tree.getRightChild());
            }
        }
    }
    
    protected void run() {
        switch (this.executingMode) {
            case 11: {
                this.buildTree();
                break;
            }
            case 12: {
                this.search();
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
    
    public void setCodePosition(final String key) {
        this.setPosition(key);
    }
    
    public void search() {
        this.searchResults = new boolean[this.data.length];
        for (int i = 0; i < this.searchResults.length; ++i) {
            this.searchResults[i] = false;
        }
        for (int i = 0; i < this.data.length; ++i) {
            if (!this.enabled) {
                return;
            }
            this.position = i;
            if (this.tree != null) {
                this.searchResults[i] = this.tree.search(this.data[i], this);
                this.resetMarkers();
            }
            else {
                this.searchResults[i] = false;
            }
        }
        this.position = -1;
    }
    
    public boolean getEnabled() {
        return this.enabled;
    }
    
    protected void resetMarkers() {
        final Object o = null;
        this.ptrNode = o;
        this.greatGrandParentNode = o;
        this.grandParentNode = o;
        this.parentNode = o;
        if (this.tree != null) {
            this.tree.resetMarkers();
        }
    }
    
    public ElementArray getDataElementArray() {
        final ElementArray elementArray = new ElementArray(0, 0);
        for (int i = 0; i < this.data.length; ++i) {
            final Node tempNode = new Node("" + this.data[i], i);
            if (i == this.position) {
                tempNode.highlight();
            }
            if (this.executingMode == 12 && i < this.searchResults.length && i < this.position) {
                String tmpString = "F";
                if (this.searchResults[i]) {
                    tmpString = "T";
                }
                final StringMarker stringMarker = new StringMarker(tmpString);
                tempNode.addMarker(stringMarker);
            }
            elementArray.setValue(i, tempNode);
        }
        return elementArray;
    }
    
    public void insert(final int data) {
        if (this.tree == null) {
            this.setPosition("3");
            this.setPosition("3.1.1");
            this.setPosition("3.2.0.1");
            if (!this.enabled) {
                return;
            }
            this.tree = new Tree234RedBlack(data, null, null, false);
            this.setPosition("3.3");
            this.setPosition("3.5");
        }
        else {
            this.tree.insert(data, this);
        }
        this.resetMarkers();
    }
    
    public void buildTree() {
        this.canStoreState = false;
        this.setPosition("2");
        this.setPosition("2.1");
        for (int i = this.basePosition; i < this.data.length; ++i) {
            if (!this.enabled) {
                return;
            }
            this.position = i;
            this.setPosition("2.2");
            this.insert(this.data[i]);
        }
        this.position = -1;
        this.setPosition("2.3");
        if (!this.enabled) {
            return;
        }
        this.canStoreState = true;
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
        Tree234Tree.POS = 0;
        BUILD_MODE_LABEL = Messages.getString("Tree234Tree.0");
        SEARCH_MODE_LABEL = Messages.getString("Tree234Tree.1");
    }
}
