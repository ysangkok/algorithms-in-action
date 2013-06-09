import localization.*;
import com.cim.common.*;
import java.util.*;
import java.awt.*;
import com.cim.AIA.*;

public class RedBlackTree extends Algorithm implements MethodSelectionListener
{
    protected static final Color tuteNodeColor;
    protected static final Color redBlackTreeNodeColor;
    protected static final Color tuteHighLightColor;
    protected static final Color tuteSelectColor;
    protected static int POS;
    protected static final int BUILD_MODE = 11;
    protected static final int SEARCH_MODE = 12;
    protected static final String BUILD_MODE_LABEL;
    protected static final String SEARCH_MODE_LABEL;
    protected RotateAnimation animThread;
    protected RedBlack tuteTree;
    protected HierarchyTree tuteHTree;
    protected boolean noHTreeUpdate;
    protected boolean removeSaveLine;
    protected int[] data;
    protected boolean[] searchResults;
    protected int position;
    protected int executingMode;
    protected int nextMode;
    protected RedBlack tree;
    protected RedBlack baseTree;
    protected int basePosition;
    protected boolean canStoreState;
    protected Vector<LineMoveRequest> lineMoveRequests;
    protected Vector<Node> insertRequests;
    protected Vector<Object> deleteRequests;
    protected Vector<Node> deleter;
    protected Node animationNode;
    protected Node growNode;
    protected HierarchyTree parentNode;
    protected HierarchyTree grandParentNode;
    protected HierarchyTree greatGrandParentNode;
    protected HierarchyTree ptrNode;
    private MessageDialog messageDialog;
    private AlgorithmThread theThread;
    public boolean update234;
    protected RedBlack findNodePtr;
    protected RedBlack findNodeParent;
    private boolean doRightRotate;
    
    public RedBlackTree(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.animThread = new RotateAnimation();
        this.noHTreeUpdate = false;
        this.removeSaveLine = false;
        this.position = -1;
        this.executingMode = 11;
        this.nextMode = this.executingMode;
        this.canStoreState = false;
        this.lineMoveRequests = new Vector();
        this.insertRequests = new Vector();
        this.deleteRequests = new Vector();
        this.deleter = new Vector();
        this.animationNode = null;
        this.growNode = null;
        this.update234 = true;
        this.findNodePtr = null;
        this.findNodeParent = null;
        this.theThread = algorithmThread;
        this.data = (int[])((int[])data);
        this.baseTree = null;
        this.basePosition = 0;
        this.animThread.start();
        this.initialise();
    }
    
    protected void balanceHierarchyTree(final HierarchyTree hierarchyTree) {
        if (hierarchyTree == null) {
            return;
        }
        final Node bNode = hierarchyTree.getNodeAt(1);
        for (int v = 0; v < this.deleter.size(); ++v) {
            final Node cNode = (Node)this.deleter.elementAt(v);
            if (cNode == bNode && (hierarchyTree.getParent() != null || hierarchyTree.numberOfElements() > 1)) {
                this.insertRequests.addElement(bNode);
                this.deleter.removeElementAt(v);
                this.animationNode = new Node(cNode.getObject(), cNode.getIdentifier());
                this.animationNode.setBackgroundColor(Color.green);
            }
            else if (cNode == bNode) {
                this.deleter.removeElementAt(v);
                this.insertRequests.addElement(bNode);
                this.animationNode = new Node(cNode.getObject(), cNode.getIdentifier());
                this.animationNode.setBackgroundColor(Color.green);
            }
        }
        for (int i = hierarchyTree.getNumberOfChildren() - 1; i >= 0; --i) {
            final HierarchyTree child = (HierarchyTree)hierarchyTree.getChild(i);
            this.balanceHierarchyTree(child);
        }
        final Vector<?> children = hierarchyTree.getChildren();
        for (int j = children.size() - 1; j >= 0; --j) {
            final HierarchyTree child2 = (HierarchyTree)children.elementAt(j);
            boolean shouldBalance = true;
            final Vector<Node> nodes = child2.getNodes();
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
                        final Vector<?> childTrees = child2.getChildren();
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
                        final Vector<?> childTrees = child2.getChildren();
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
    
    public void buildTuteTree() {
        final int[] DEFAULT_DATA = new int[] { 75, 5, 70, 10, 65, 15, 60, 20, 55, 25, 50, 30, 45, 35, 40 };
        final int data = DEFAULT_DATA[0];
        this.tuteTree = new RedBlack(data, null, null, false);
        for (int i = 1; i < DEFAULT_DATA.length; ++i) {
            this.tuteTree.insert(DEFAULT_DATA[i]);
        }
    }
    
    protected void changeData(final Object data) {
        this.data = (int[])((int[])data);
        this.position = -1;
        this.save();
    }
    
    protected void clearState() {
        this.baseTree = null;
        this.basePosition = 0;
    }
    
    public void colorTree(final HierarchyTree h, final Color c) {
        if (h == null) {
            return;
        }
        final Vector<?> nodes = h.getNodes();
        for (int v = 0; v < nodes.size(); ++v) {
            final Node node = (Node)nodes.elementAt(v);
            node.setBackgroundColor(c);
        }
        for (int i = h.getNumberOfChildren() - 1; i >= 0; --i) {
            final HierarchyTree child = (HierarchyTree)h.getChild(i);
            this.colorTree(child, c);
        }
    }
    
    public void drawAllParentLines(final RedBlack tree) {
        if (tree == null) {
            return;
        }
        tree.setDrawParentLine(true);
        this.drawAllParentLines(tree.leftChild);
        this.drawAllParentLines(tree.rightChild);
    }
    
    protected RedBlack findNode(final RedBlack root, final HierarchyTree hTree, final Node searchNode) {
        if (root == null || hTree == null) {
            return null;
        }
        if (hTree.getNodeAt(1) == searchNode) {
            this.findNodePtr = root;
            return root;
        }
        final HierarchyTree leftChild = hTree.getLeftChild();
        final HierarchyTree rightChild = hTree.getRightChild();
        final RedBlack a = this.findNode(root.leftChild, leftChild, searchNode);
        final RedBlack b = this.findNode(root.rightChild, rightChild, searchNode);
        if (a != null) {
            this.findNodeParent = root;
        }
        if (b != null) {
            this.findNodeParent = root;
        }
        return null;
    }
    
    protected Vector<Object> generateQuestions() {
        return null;
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        final MultipleTween tweens = new MultipleTween(paintable);
        for (int i = 0; i < this.insertRequests.size(); ++i) {
            tweens.add(new InsertTween(null, (Sizeable)this.insertRequests.elementAt(i), numberOfSteps));
        }
        for (int i = 0; i < this.deleteRequests.size(); ++i) {
            tweens.add(new DeleteTween(null, (Node)((Node)this.deleteRequests.elementAt(i)), numberOfSteps));
        }
        if (this.insertRequests.size() > 0 || this.deleteRequests.size() > 0) {
            return tweens;
        }
        return null;
    }
    
    public Node getAnimationNode() {
        return this.animationNode;
    }
    
    public Thread getAnimThread() {
        return this.animThread;
    }
    
    public HierarchyTree getBalancedHierarchyTree(final int nodeWidth) {
        final HierarchyTree hierarchyTree = this.getHierarchyTree(null, this.tree, this.tree, 0, nodeWidth, false, true);
        this.balanceHierarchyTree(hierarchyTree);
        this.colorTree(hierarchyTree, Color.green);
        return hierarchyTree;
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
    
    public boolean getEnabled() {
        return this.enabled;
    }
    
    public HierarchyTree getGrandParentNode() {
        return this.grandParentNode;
    }
    
    public HierarchyTree getGreatGrandParentNode() {
        return this.greatGrandParentNode;
    }
    
    public Node getGrowNode() {
        return this.growNode;
    }
    
    protected HierarchyTree getHierarchyTree(final HierarchyTree parent, final RedBlack tree, final RedBlack root, final int level, final int nodeWidth, final boolean adjustMarkers, final boolean animate) {
        if (tree == null) {
            return null;
        }
        Node node = null;
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
        if ((((tree.getPositionInserted() != RedBlack.deletePtr) ? false : true) & animate) != false) {
            this.deleter.addElement(node);
        }
        if (tree.redNode) {
            node.setHighlightColor(Color.red);
        }
        else {
            node.setHighlightColor(Color.gray);
        }
        node.setBackgroundColor(RedBlackTree.redBlackTreeNodeColor);
        final HierarchyTree hierarchyTree = new HierarchyTree(parent, node);
        if (!animate) {
            hierarchyTree.setDrawBorder(false);
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
    
    public HierarchyTree getHierarchyTree(final int nodeWidth) {
        return this.getHierarchyTree(null, this.tree, this.tree, 0, nodeWidth, true, false);
    }
    
    public Vector<LineMoveRequest> getLineMoveRequests() {
        return this.lineMoveRequests;
    }
    
    public HierarchyTree getParentNode() {
        return this.parentNode;
    }
    
    public HierarchyTree getPtrNode() {
        return this.ptrNode;
    }
    
    public boolean getRemoveSaveLine() {
        return this.removeSaveLine;
    }
    
    protected HierarchyTree getTuteTree(final HierarchyTree parent, final RedBlack tree, final RedBlack root, final int level, final int nodeWidth) {
        if (tree == null) {
            return null;
        }
        final Node node = new Node(new Integer(tree.getDataItem()), tree.getPositionInserted());
        node.setWidth(nodeWidth);
        node.setBackgroundColor(RedBlackTree.tuteNodeColor);
        if (tree.getPositionInserted() == RedBlack.ptrP) {
            node.setBackgroundColor(RedBlackTree.tuteSelectColor);
            node.addMarker(new StringMarker(Messages.getString("RedBlackTree.3")));
        }
        if (tree.getPositionInserted() == RedBlack.ptrC) {
            node.setBackgroundColor(RedBlackTree.tuteSelectColor);
            node.addMarker(new StringMarker(Messages.getString("RedBlackTree.2")));
        }
        final HierarchyTree hierarchyTree = new HierarchyTree(parent, node);
        if (!tree.getDrawParentLine()) {
            hierarchyTree.setDrawParentLine(false);
        }
        if (tree.leftChild == null && tree.rightChild != null) {
            final Node fakeLeftNode = new Node(new Integer(0), -1);
            fakeLeftNode.setWidth(nodeWidth / 4);
            fakeLeftNode.setIsVisible(false);
            final HierarchyTree fakeLeft = new HierarchyTree(hierarchyTree, fakeLeftNode);
            hierarchyTree.addChild(fakeLeft);
        }
        else if (tree.leftChild != null) {
            hierarchyTree.addChild(this.getTuteTree(hierarchyTree, tree.leftChild, root, level + 1, nodeWidth));
        }
        if (tree.rightChild == null && tree.leftChild != null) {
            final Node fakeRightNode = new Node(new Integer(0), -1);
            fakeRightNode.setWidth(nodeWidth / 4);
            fakeRightNode.setIsVisible(false);
            final HierarchyTree fakeRight = new HierarchyTree(hierarchyTree, fakeRightNode);
            hierarchyTree.addChild(fakeRight);
        }
        else if (tree.rightChild != null) {
            hierarchyTree.addChild(this.getTuteTree(hierarchyTree, tree.rightChild, root, level + 1, nodeWidth));
        }
        if (tree.rightChild == null && tree.leftChild == null) {
            final Node fakeLeftNode2 = new Node(new Integer(0), -1);
            final Node fakeRightNode2 = new Node(new Integer(0), -1);
            fakeLeftNode2.setWidth(nodeWidth / 4);
            fakeRightNode2.setWidth(nodeWidth / 4);
            fakeLeftNode2.setIsVisible(false);
            fakeRightNode2.setIsVisible(false);
            final HierarchyTree fakeLeft2 = new HierarchyTree(hierarchyTree, fakeLeftNode2);
            hierarchyTree.addChild(fakeLeft2);
            final HierarchyTree fakeRight2 = new HierarchyTree(hierarchyTree, fakeRightNode2);
            hierarchyTree.addChild(fakeRight2);
        }
        if (parent != null) {
            if (tree.getHighLightParent()) {
                hierarchyTree.setParentLineColor(RedBlackTree.tuteHighLightColor);
            }
            else {
                hierarchyTree.setParentLineColor(Color.black);
            }
        }
        return hierarchyTree;
    }
    
    public HierarchyTree getTuteTree(final int nodeWidth) {
        if (this.tuteHTree == null || !this.noHTreeUpdate) {
            this.tuteHTree = this.getTuteTree(null, this.tuteTree, this.tuteTree, 0, nodeWidth);
        }
        return this.tuteHTree;
    }
    
    protected boolean hasQuestions() {
        return false;
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
        this.lineMoveRequests = new Vector();
        this.deleter = new Vector();
        final Object o = null;
        this.ptrNode = o;
        this.greatGrandParentNode = o;
        this.grandParentNode = o;
        this.parentNode = o;
        this.position = -1;
        RedBlack.POSITION = this.basePosition;
    }
    
    protected void initialiseMethods(final String dataDir, final MethodSelectable methodSelectable) {
        final MethodSelection build = new MethodSelection(RedBlackTree.BUILD_MODE_LABEL, dataDir, Messages.getString("RedBlackTree.91"), "2.2a", this.getLogger(), this.getBreakPoint());
        final MethodSelection search = new MethodSelection(RedBlackTree.SEARCH_MODE_LABEL, dataDir, Messages.getString("RedBlackTree.92"), "4", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(build, true);
        methodSelectable.addMethodSelection(search, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void insert(final int data) {
        if (this.tree == null) {
            this.setPosition("3");
            this.setPosition("3.1");
            this.setPosition("3.1.1");
            if (!this.enabled) {
                return;
            }
            this.tree = new RedBlack(data, null, null, false);
            this.setPosition("3.3");
            this.setPosition("3.3.4");
            this.setPosition("3.4");
            this.setPosition("3.4.4");
            this.setPosition("3.5");
        }
        else {
            this.tree.insert(data, this);
        }
        this.resetMarkers();
    }
    
    public void leftRotateRequest() {
        if (this.tuteTree == null) {
            return;
        }
        if (!this.enabled) {
            return;
        }
        this.doRightRotate = false;
        this.noHTreeUpdate = true;
        if (this.messageDialog != null) {
            this.messageDialog.dispose();
        }
        this.messageDialog = new MessageDialog(Messages.getString("RedBlackTree.10"), false, false);
        this.messageDialog.setTitle(Messages.getString("RedBlackTree.11"));
        this.messageDialog.setVisible(true);
        final NodeSelection nodeSelection = NodeSelection.getInstance(this);
        NodeSelection.setEnabled(true);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (RedBlackTree.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
        else if (RedBlackTree.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 12;
        }
    }
    
    public void processNodeSelection() {
        if (this.messageDialog != null) {
            this.messageDialog.setVisible(false);
            this.messageDialog.dispose();
        }
        final Node selectedNode = NodeSelection.getNode();
        this.findNodeParent = null;
        this.findNodePtr = null;
        this.findNode(this.tuteTree, this.tuteHTree, selectedNode);
        NodeSelection.setEnabled(false);
        this.noHTreeUpdate = false;
        final RedBlack h = this.findNodePtr;
        MessageDialog msg;
        if (!this.doRightRotate) {
            if (h.rightChild == null) {
                msg = new MessageDialog(Messages.getString("RedBlackTree.4"), true, true);
                msg.setTitle(Messages.getString("RedBlackTree.5"));
                msg.setVisible(true);
                this.noHTreeUpdate = false;
                return;
            }
            h.setHighLightParent(true);
            h.rightChild.setHighLightParent(true);
            if (h.rightChild.leftChild != null) {
                h.rightChild.leftChild.setHighLightParent(true);
            }
            this.animThread.initialise((RedBlackTreeThread)this.theThread, this, this.lineMoveRequests, this.tuteTree, this.findNodePtr, this.findNodeParent);
            this.animThread.rotateLeft();
            this.animThread.setEnabled(true);
        }
        if (this.doRightRotate) {
            if (h.leftChild == null) {
                msg = new MessageDialog(Messages.getString("RedBlackTree.6"), true, true);
                msg.setTitle(Messages.getString("RedBlackTree.7"));
                msg.setVisible(true);
                this.noHTreeUpdate = false;
                return;
            }
            h.setHighLightParent(true);
            h.leftChild.setHighLightParent(true);
            if (h.leftChild.rightChild != null) {
                h.leftChild.rightChild.setHighLightParent(true);
            }
            this.animThread.initialise((RedBlackTreeThread)this.theThread, this, this.lineMoveRequests, this.tuteTree, this.findNodePtr, this.findNodeParent);
            this.animThread.rotateRight();
            this.animThread.setEnabled(true);
        }
    }
    
    public void reInitialise(final Object data) {
        this.data = (int[])((int[])data);
        this.initialise();
    }
    
    protected void removeAllAnimationRequests() {
        this.insertRequests = new Vector();
        this.deleteRequests = new Vector();
        this.removeRedBlackTreeDeletes(this.tree);
        this.removeRedBlackTreeInserts(this.tree);
        this.growNode = null;
        this.animationNode = null;
    }
    
    protected void removeAllQuestions() {
    }
    
    protected void removeRedBlackTreeDeletes(final RedBlack tree) {
        if (tree != null) {
            tree.deleteNode = false;
            RedBlack.deletePtr = -1;
            if (tree.getLeftChild() != null) {
                this.removeRedBlackTreeDeletes(tree.getLeftChild());
            }
            if (tree.getRightChild() != null) {
                this.removeRedBlackTreeDeletes(tree.getRightChild());
            }
        }
    }
    
    protected void removeRedBlackTreeInserts(final RedBlack tree) {
        if (tree != null) {
            tree.newNode = false;
            if (tree.getLeftChild() != null) {
                this.removeRedBlackTreeInserts(tree.getLeftChild());
            }
            if (tree.getRightChild() != null) {
                this.removeRedBlackTreeInserts(tree.getRightChild());
            }
        }
    }
    
    public void resetAllHighLightParent(final RedBlack tree) {
        if (tree == null) {
            return;
        }
        tree.setHighLightParent(false);
        this.resetAllHighLightParent(tree.leftChild);
        this.resetAllHighLightParent(tree.rightChild);
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
    
    public void rightRotateRequest() {
        if (this.tuteTree == null) {
            return;
        }
        if (!this.enabled) {
            return;
        }
        this.doRightRotate = true;
        this.noHTreeUpdate = true;
        if (this.messageDialog != null) {
            this.messageDialog.dispose();
        }
        this.messageDialog = new MessageDialog(Messages.getString("RedBlackTree.8"), false, false);
        this.messageDialog.setTitle(Messages.getString("RedBlackTree.9"));
        this.messageDialog.setVisible(true);
        final NodeSelection nodeSelection = NodeSelection.getInstance(this);
        NodeSelection.setEnabled(true);
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
    
    private void save() {
        if (this.tree != null) {
            this.baseTree = this.tree.copy();
        }
        else {
            this.baseTree = null;
        }
        this.basePosition = RedBlack.POSITION;
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
    
    public void setAnimDuration(final int speed) {
        this.animThread.setDuration(speed);
    }
    
    public void setCodePosition(final String key) {
        this.setPosition(key);
    }
    
    public void setRemoveSaveLine(final boolean state) {
        this.removeSaveLine = state;
    }
    
    public void setTuteTree(final RedBlack tTree) {
        this.tuteTree = tTree;
    }
    
    protected void storeState(final boolean forceStore) {
        if (this.canStoreState || forceStore) {
            this.save();
        }
    }
    
    public void updateTuteTree() {
        this.noHTreeUpdate = false;
        if (this.tree != null) {
            this.tuteTree = this.tree.copy();
        }
        else {
            this.tuteTree = null;
        }
    }
    
    static {
        tuteNodeColor = Color.lightGray;
        redBlackTreeNodeColor = Color.lightGray;
        tuteHighLightColor = Color.red;
        tuteSelectColor = Color.yellow;
        RedBlackTree.POS = 0;
        BUILD_MODE_LABEL = Messages.getString("RedBlackTree.0");
        SEARCH_MODE_LABEL = Messages.getString("RedBlackTree.1");
    }
}
