import java.awt.*;
import localization.*;
import java.util.*;
import com.cim.AIA.*;

public class SplayTree extends Algorithm implements MethodSelectionListener, ControlListener
{
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private SplayTreeNode currentNode;
    private SplayTreeNode switchPath;
    private Vector<Vector<MoveMoveable>> tweenVectorVector;
    private boolean isTweenFast;
    private boolean isBackMode;
    private SplayTreeCodeStack codeStack;
    private Boolean wasFound;
    private SplayTreeNode head;
    private SplayTreeNode savedTree;
    private SplayTreeNode startingTree;
    private ElementArray insertData;
    private ElementArray newInsertData;
    private ElementArray searchData;
    private ElementArray newSearchData;
    private int insertPos;
    private int lastInsertPos;
    
    public SplayTree(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.isBackMode = false;
        this.head = new SplayTreeNullNode();
        this.savedTree = new SplayTreeNullNode();
        this.executingMode = 11;
        this.nextMode = this.executingMode;
        this.changeData(data);
        this.insertData = this.newInsertData;
        this.searchData = this.newSearchData;
        this.codeStack = new SplayTreeCodeStack();
    }
    
    public void reInitialise(final Object data) {
        this.tweenVectorVector = null;
        if (!this.isBackMode) {
            this.changeData(data);
            this.insertData = this.newInsertData;
            this.searchData = this.newSearchData;
        }
        this.resetColors();
        if (this.executingMode == 11 && !this.isBackMode) {
            this.savedTree = new SplayTreeNullNode();
            this.insertPos = 0;
        }
        if (this.executingMode == 12 && !this.isBackMode) {
            this.head = this.savedTree.getCopy();
        }
        if (this.isBackMode) {
            if (this.executingMode == 11) {
                this.head = this.startingTree.getCopy();
            }
            else if (this.executingMode == 12) {
                this.head = this.savedTree.getCopy();
            }
            this.insertPos = this.lastInsertPos;
        }
    }
    
    protected void resetColors() {
        int i;
        if (this.insertData != null) {
            for (i = this.insertPos; i < this.insertData.getSize(); ++i) {
                ((Node)this.insertData.getElement(i)).setBackgroundColor(SplayTreeColors.INSERT_ACTIVE_COLOR);
            }
        }
        if (this.searchData != null) {
            for (i = 0; i < this.searchData.getSize(); ++i) {
                ((Node)this.searchData.getElement(i)).setBackgroundColor(SplayTreeColors.SEARCH_ACTIVE_COLOR);
                ((Node)this.searchData.getElement(i)).removeAllMarkers();
            }
        }
    }
    
    protected void changeData(final Object data) {
        this.tweenVectorVector = null;
        final Random random = new Random();
        if (this.executingMode == 11 || this.nextMode == 11) {
            this.newInsertData = new ElementArray(0, 0);
            this.newInsertData.setColumGap(0);
            this.newInsertData.setElementWidth(20);
            for (int i = 0; i < ((int[])((int[])data)).length; ++i) {
                this.newInsertData.add(new Node(new Integer(((int[])((int[])data))[i]), i), i);
                ((Node)this.newInsertData.getElement(i)).setBackgroundColor(SplayTreeColors.INSERT_ACTIVE_COLOR);
            }
        }
        this.newSearchData = new ElementArray(0, 0);
        this.newSearchData.setColumGap(0);
        this.newSearchData.setElementWidth(20);
        for (int i = 0; i < ((int[])((int[])data)).length; ++i) {
            if (i % 2 == 0) {
                this.newSearchData.add(new Node(new Integer(Math.abs(random.nextInt()) % 98 + 1), i), i);
            }
            else {
                this.newSearchData.add(new Node(new Integer(((int[])((int[])data))[i]), i), i);
            }
            ((Node)this.newSearchData.getElement(i)).setBackgroundColor(SplayTreeColors.SEARCH_ACTIVE_COLOR);
        }
    }
    
    private void save() {
        System.out.println("Saving tree");
        this.resetColors();
        if (this.executingMode == 11) {
            this.savedTree = this.head.getCopy();
        }
        else if (this.executingMode == 12) {
            this.head = this.savedTree.getCopy();
        }
    }
    
    protected void storeState(final boolean forceState) {
        this.save();
    }
    
    protected void clearState() {
        this.head = new SplayTreeNullNode();
    }
    
    protected void initialiseMethods(final String string, final MethodSelectable methodSelectable) {
        final MethodSelection methodSelection1 = new MethodSelection(SplayTree.BUILD_MODE_LABEL, string, Messages.getString("SplayTree.2"), "3a", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection2 = new MethodSelection(SplayTree.SEARCH_MODE_LABEL, string, Messages.getString("SplayTree.3"), "5a", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (SplayTree.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
        else if (SplayTree.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 12;
        }
        if (this.nextMode == 11) {
            this.head = this.savedTree;
        }
        if (this.nextMode == 12 && this.savedTree != null) {
            this.head = this.savedTree.getCopy();
        }
    }
    
    public MultipleTween generateTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        return null;
    }
    
    public SplayTreeCodeStack getCodeStack() {
        return this.codeStack;
    }
    
    public Vector<Object> getTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        final Vector<Object> multiTweenArray = new Vector();
        if (this.tweenVectorVector != null) {
            for (int j = 0; j < this.tweenVectorVector.size(); ++j) {
                final Vector<E> tweenVector = (Vector<E>)this.tweenVectorVector.elementAt(j);
                if (tweenVector != null) {
                    final MultipleTween multiTween = new MultipleTween(paintable);
                    int i = 0;
                    if (tweenVector.elementAt(i) instanceof Hashtable<K, V>) {
                        multiTweenArray.addElement((Hashtable<K, V>)tweenVector.elementAt(i));
                        ++i;
                    }
                    while (i < tweenVector.size()) {
                        multiTween.add(new MoveTween(paintable, ((MoveMoveable)tweenVector.elementAt(i)).moveable, ((MoveMoveable)tweenVector.elementAt(i)).from, ((MoveMoveable)tweenVector.elementAt(i)).to, ((MoveMoveable)tweenVector.elementAt(i)).isEntire, numberOfSteps));
                        ++i;
                    }
                    if (multiTween.getNumberOfTweens() > 0) {
                        multiTweenArray.addElement(multiTween);
                    }
                }
            }
        }
        this.tweenVectorVector = null;
        if (multiTweenArray.size() > 0) {
            return multiTweenArray;
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
    
    public SplayTreeNode getHead() {
        this.unHighlightPath(this.head);
        this.highlightSwitchPath(this.switchPath);
        this.highlightPath(this.currentNode);
        return this.head;
    }
    
    public void highlightSwitchPath(final SplayTreeNode node) {
        if (node != null) {
            node.setIsOnSwitchPath(true);
            this.highlightSwitchPath(node.getParent());
        }
    }
    
    public void highlightPath(final SplayTreeNode node) {
        if (node != null) {
            node.setIsOnPath(true);
            this.highlightPath(node.getParent());
        }
    }
    
    private void unHighlightPath(final SplayTreeNode node) {
        if (node != null) {
            node.setIsOnPath(false);
            node.setIsOnSwitchPath(false);
            if (node instanceof SplayTreeDataNode) {
                this.unHighlightPath(((SplayTreeDataNode)node).getLeft());
                this.unHighlightPath(((SplayTreeDataNode)node).getRight());
            }
        }
    }
    
    public SplayTreeNode getCurrentNode() {
        return this.currentNode;
    }
    
    protected void run(final boolean canChangeMode) {
        if (canChangeMode) {
            this.executingMode = this.nextMode;
            SplayTreeDataNode.clearNodeColors();
        }
        this.run();
    }
    
    protected void run() {
        if (!this.isBackMode) {
            this.lastInsertPos = this.insertPos;
            if (this.savedTree != null) {
                this.startingTree = this.savedTree.getCopy();
            }
            else {
                this.startingTree = new SplayTreeNullNode();
            }
        }
        switch (this.executingMode) {
            case 11: {
                for (int i = this.insertPos; i < this.insertData.getSize(); ++i) {
                    this.setPosition("2a");
                    final SplayTreeDataItem dataItem = new SplayTreeDataItem(this.insertData.getElement(i).getObject().toString(), ((Integer)this.insertData.getElement(i).getObject()).intValue());
                    if (this.enabled) {
                        ((Node)this.insertData.getElement(i)).setBackgroundColor(SplayTreeColors.INSERT_HIGHLIGHT_COLOR);
                        this.currentNode = this.head;
                        this.savedTree = this.head.getCopy();
                        this.head = this.insert(this.head, dataItem, null);
                        this.currentNode = null;
                        ((Node)this.insertData.getElement(i)).setBackgroundColor(SplayTreeColors.INSERT_DONE_COLOR);
                        if (this.enabled) {
                            this.insertPos = this.insertPos + 1;
                        }
                        else {
                            this.head = this.savedTree.getCopy();
                        }
                    }
                    this.clearPointers();
                }
                break;
            }
            case 12: {
                for (int i = 0; i < this.searchData.getSize(); ++i) {
                    final Node searchNode = (Node)this.searchData.getElement(i);
                    final SplayTreeDataItem dataItem = new SplayTreeDataItem(searchNode.getObject().toString(), ((Integer)searchNode.getObject()).intValue());
                    if (this.enabled) {
                        searchNode.setBackgroundColor(SplayTreeColors.SEARCH_HIGHLIGHT_COLOR);
                        this.wasFound = null;
                        this.currentNode = this.head;
                        this.head = this.search(this.head, dataItem, null);
                        this.currentNode = null;
                        if (this.wasFound != null) {
                            if (this.wasFound.booleanValue()) {
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
                        }
                        searchNode.setBackgroundColor(SplayTreeColors.SEARCH_DONE_COLOR);
                    }
                    this.clearPointers();
                }
                break;
            }
        }
    }
    
    private SplayTreeDataNode rotateRight(final SplayTreeNode parent) {
        if (((SplayTreeDataNode)parent).getLeft() instanceof SplayTreeNullNode) {
            return (SplayTreeDataNode)parent;
        }
        if (this.tweenVectorVector == null) {
            this.tweenVectorVector = new Vector();
        }
        Vector<MoveMoveable> tweenVector = new Vector();
        final SplayTreeDataNode grandParent = parent.getParent();
        final SplayTreeDataNode child = (SplayTreeDataNode)((SplayTreeDataNode)parent).getLeft();
        if (grandParent != null) {
            if (grandParent.getLeft() == parent) {
                tweenVector.addElement(new MoveMoveable(grandParent.getLeftLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            }
            else {
                tweenVector.addElement(new MoveMoveable(grandParent.getRightLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            }
        }
        tweenVector.addElement(new MoveMoveable(((SplayTreeDataNode)parent).getLeftLink(), new Point(((SplayTreeDataNode)parent).getLeft().getPosition()), new Point(child.getRight().getPosition()), false));
        tweenVector.addElement(new MoveMoveable(child.getRightLink(), new Point(child.getRight().getPosition()), new Point(parent.getPosition()), false));
        this.tweenVectorVector.addElement(tweenVector);
        final boolean temp = this.isTweenFast;
        this.animate("$This key doesn't exist###");
        this.isTweenFast = temp;
        if (this.tweenVectorVector == null) {
            this.tweenVectorVector = new Vector();
        }
        final Vector<E> vector;
        tweenVector = (vector = new Vector());
        vector.addElement(new MoveMoveable(parent, new Point(parent.getPosition()), new Point(parent.getPosition().x, ((SplayTreeDataNode)parent).getLeft().getPosition().y), false));
        final SplayTreeNode parentRight = ((SplayTreeDataNode)parent).getRight();
        tweenVector.addElement(new MoveMoveable(parentRight, new Point(parentRight.getPosition()), parentRight.getPosition(), true));
        tweenVector.addElement(new MoveMoveable(child.getLeft(), new Point(child.getLeft().getPosition()), new Point(child.getLeft().getPosition().x, ((SplayTreeDataNode)parent).getLeft().getPosition().y), true));
        tweenVector.addElement(new MoveMoveable(child, new Point(child.getPosition()), new Point(child.getPosition().x, parent.getPosition().y), false));
        this.tweenVectorVector.addElement(tweenVector);
        ((SplayTreeDataNode)parent).setLeft(child.getRight());
        child.setRight(parent);
        child.setParent(grandParent);
        return child;
    }
    
    private SplayTreeDataNode rotateLeft(final SplayTreeNode parent) {
        if (((SplayTreeDataNode)parent).getRight() instanceof SplayTreeNullNode) {
            return (SplayTreeDataNode)parent;
        }
        if (this.tweenVectorVector == null) {
            this.tweenVectorVector = new Vector();
        }
        Vector<MoveMoveable> tweenVector = new Vector();
        final SplayTreeDataNode grandParent = parent.getParent();
        final SplayTreeDataNode child = (SplayTreeDataNode)((SplayTreeDataNode)parent).getRight();
        if (grandParent != null) {
            if (grandParent.getLeft() == parent) {
                tweenVector.addElement(new MoveMoveable(grandParent.getLeftLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            }
            else {
                tweenVector.addElement(new MoveMoveable(grandParent.getRightLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            }
        }
        tweenVector.addElement(new MoveMoveable(((SplayTreeDataNode)parent).getRightLink(), new Point(((SplayTreeDataNode)parent).getRight().getPosition()), new Point(child.getLeft().getPosition()), false));
        tweenVector.addElement(new MoveMoveable(child.getLeftLink(), new Point(child.getLeft().getPosition()), new Point(parent.getPosition()), false));
        this.tweenVectorVector.addElement(tweenVector);
        final boolean temp = this.isTweenFast;
        this.animate("$This key doesn't exist###");
        this.isTweenFast = temp;
        if (this.tweenVectorVector == null) {
            this.tweenVectorVector = new Vector();
        }
        final Vector<E> vector;
        tweenVector = (vector = new Vector());
        vector.addElement(new MoveMoveable(parent, new Point(parent.getPosition()), new Point(parent.getPosition().x, ((SplayTreeDataNode)parent).getRight().getPosition().y), false));
        final SplayTreeNode parentLeft = ((SplayTreeDataNode)parent).getLeft();
        tweenVector.addElement(new MoveMoveable(parentLeft, new Point(parentLeft.getPosition()), parentLeft.getPosition(), true));
        tweenVector.addElement(new MoveMoveable(child.getRight(), new Point(child.getRight().getPosition()), new Point(child.getRight().getPosition().x, ((SplayTreeDataNode)parent).getRight().getPosition().y), true));
        tweenVector.addElement(new MoveMoveable(child, new Point(child.getPosition()), new Point(child.getPosition().x, parent.getPosition().y), false));
        this.tweenVectorVector.addElement(tweenVector);
        ((SplayTreeDataNode)parent).setRight(child.getLeft());
        child.setLeft(parent);
        child.setParent(grandParent);
        return child;
    }
    
    private SplayTreeNode insert(SplayTreeNode node, final SplayTreeDataItem dataItem, final Boolean isSetLeft) {
        this.switchPath = null;
        this.currentNode = node;
        this.codeStack.push("insertCode");
        this.setPosition("3");
        if (node == null) {
            throw new RuntimeException("insert: Node should never be null");
        }
        final SplayTreeDataNode parent = node.getParent();
        this.setPosition("3.1");
        if (node instanceof SplayTreeNullNode) {
            node = new SplayTreeDataNode(dataItem);
            this.linkEarly(parent, node, isSetLeft);
            this.currentNode = node;
            this.codeStack.stepCodeLine();
            this.setPosition("3.1.1");
            this.switchPath = null;
            this.codeStack.pop();
            return node;
        }
        SplayTreeDataNode tree = (SplayTreeDataNode)node;
        if (dataItem.getKey() < tree.getKey()) {
            this.switchPath = tree.getLeft();
        }
        else {
            this.switchPath = tree.getRight();
        }
        if (this.switchPath instanceof SplayTreeDataNode) {
            if (dataItem.getKey() < ((SplayTreeDataNode)this.switchPath).getKey()) {
                this.switchPath = ((SplayTreeDataNode)this.switchPath).getLeft();
            }
            else {
                this.switchPath = ((SplayTreeDataNode)this.switchPath).getRight();
            }
        }
        this.setPosition("3.2");
        if ((tree.getLeft() instanceof SplayTreeNullNode && dataItem.getKey() < tree.getKey()) || (tree.getRight() instanceof SplayTreeNullNode && dataItem.getKey() >= tree.getKey())) {
            this.codeStack.setCode("terminalCode");
            this.setPosition("TERMINAL");
            if (dataItem.getKey() < tree.getKey()) {
                this.setPosition("TERMINAL.3.1", "TERMINAL.1", false);
                this.setPosition("TERMINAL.2.1");
                tree.setLeft(new SplayTreeDataNode(dataItem));
                if (!this.isBackMode) {
                    super.repaint();
                }
                this.currentNode = tree;
                this.codeStack.stepCodeLine();
                this.setPosition("TERMINAL.3.2", "TERMINAL.1", false);
                this.setPosition("TERMINAL.2.2");
                this.isTweenFast = !this.isExpanded("TERMINAL");
                tree = this.rotateRight(tree);
                this.currentNode = tree;
                this.linkEarly(parent, tree, isSetLeft);
                this.animate("$This key doesn't exist###");
                this.codeStack.stepCodeLine();
                this.setPosition("TERMINAL.3.3", "TERMINAL.1", false);
                this.setPosition("TERMINAL.2.3");
                this.algorithmSleep();
                this.codeStack.pop();
                this.switchPath = null;
                return tree;
            }
            this.setPosition("TERMINAL.3");
            this.setPosition("TERMINAL.3.1");
            tree.setRight(new SplayTreeDataNode(dataItem));
            if (!this.isBackMode) {
                super.repaint();
            }
            this.currentNode = tree;
            this.codeStack.stepCodeLine();
            this.setPosition("TERMINAL.3.2");
            this.isTweenFast = !this.isExpanded("TERMINAL");
            tree = this.rotateLeft(tree);
            this.currentNode = tree;
            this.linkEarly(parent, tree, isSetLeft);
            this.animate("$This key doesn't exist###");
            this.codeStack.stepCodeLine();
            this.setPosition("TERMINAL.3.3");
            this.algorithmSleep();
            this.codeStack.pop();
            this.switchPath = null;
            return tree;
        }
        else if ((dataItem.getKey() < tree.getKey() && dataItem.getKey() < ((SplayTreeDataNode)tree.getLeft()).getKey()) || (dataItem.getKey() >= tree.getKey() && dataItem.getKey() >= ((SplayTreeDataNode)tree.getRight()).getKey())) {
            this.codeStack.setCode("zigZigCode");
            this.setPosition("ZIGZIG");
            this.setPosition("ZIGZIG.2");
            if (dataItem.getKey() < tree.getKey()) {
                final SplayTreeDataNode leftChild = (SplayTreeDataNode)tree.getLeft();
                this.setPosition("ZIGZIG.3.1", "ZIGZIG.1", false);
                this.setPosition("ZIGZIG.2.1");
                leftChild.setLeft(this.insert(leftChild.getLeft(), dataItem, new Boolean(true)));
                this.currentNode = tree;
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZIG.3.2", "ZIGZIG.1", false);
                this.setPosition("ZIGZIG.2.2");
                this.isTweenFast = !this.isExpanded("ZIGZIG");
                tree = this.rotateRight(tree);
                this.currentNode = tree;
                this.linkEarly(parent, tree, isSetLeft);
                this.animate("$This key doesn't exist###");
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZIG.3.3", "ZIGZIG.1", false);
                this.setPosition("ZIGZIG.2.3");
                this.isTweenFast = !this.isExpanded("ZIGZIG");
                tree = this.rotateRight(tree);
                this.currentNode = tree;
                this.linkEarly(parent, tree, isSetLeft);
                this.animate("$This key doesn't exist###");
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZIG.3.4", "ZIGZIG.1", false);
                this.setPosition("ZIGZIG.2.4");
                this.algorithmSleep();
                this.codeStack.pop();
                this.switchPath = null;
                return tree;
            }
            this.setPosition("ZIGZIG.3");
            final SplayTreeDataNode rightChild = (SplayTreeDataNode)tree.getRight();
            this.setPosition("ZIGZIG.3.1");
            rightChild.setRight(this.insert(rightChild.getRight(), dataItem, new Boolean(false)));
            this.currentNode = tree;
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZIG.3.2");
            this.isTweenFast = !this.isExpanded("ZIGZIG");
            tree = this.rotateLeft(tree);
            this.currentNode = tree;
            this.linkEarly(parent, tree, isSetLeft);
            this.animate("$This key doesn't exist###");
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZIG.3.3");
            this.isTweenFast = !this.isExpanded("ZIGZIG");
            tree = this.rotateLeft(tree);
            this.currentNode = tree;
            this.linkEarly(parent, tree, isSetLeft);
            this.animate("$This key doesn't exist###");
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZIG.3.4");
            this.algorithmSleep();
            this.codeStack.pop();
            this.switchPath = null;
            return tree;
        }
        else {
            if ((dataItem.getKey() >= tree.getKey() || dataItem.getKey() < ((SplayTreeDataNode)tree.getLeft()).getKey()) && (dataItem.getKey() < tree.getKey() || dataItem.getKey() >= ((SplayTreeDataNode)tree.getRight()).getKey())) {
                throw new RuntimeException("This case should be unreachable");
            }
            this.codeStack.setCode("zigZagCode");
            this.setPosition("ZIGZAG");
            this.setPosition("ZIGZAG.2");
            if (dataItem.getKey() < tree.getKey()) {
                final SplayTreeDataNode leftChild2 = (SplayTreeDataNode)tree.getLeft();
                this.setPosition("ZIGZAG.3.1", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.1");
                leftChild2.setRight(this.insert(leftChild2.getRight(), dataItem, new Boolean(false)));
                this.currentNode = tree;
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZAG.3.2", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.2");
                this.isTweenFast = !this.isExpanded("ZIGZAG");
                tree.setLeft(this.rotateLeft(leftChild2));
                this.animate("$This key doesn't exist###");
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZAG.3.3", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.3");
                this.isTweenFast = !this.isExpanded("ZIGZAG");
                tree = this.rotateRight(tree);
                this.currentNode = tree;
                this.linkEarly(parent, tree, isSetLeft);
                this.animate("$This key doesn't exist###");
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZAG.3.4", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.4");
                this.algorithmSleep();
                this.codeStack.pop();
                this.switchPath = null;
                return tree;
            }
            this.setPosition("ZIGZAG.3");
            final SplayTreeDataNode rightChild2 = (SplayTreeDataNode)tree.getRight();
            this.setPosition("ZIGZAG.3.1");
            rightChild2.setLeft(this.insert(rightChild2.getLeft(), dataItem, new Boolean(true)));
            this.currentNode = tree;
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZAG.3.2");
            this.isTweenFast = !this.isExpanded("ZIGZAG");
            tree.setRight(this.rotateRight(rightChild2));
            this.animate("$This key doesn't exist###");
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZAG.3.3");
            this.isTweenFast = !this.isExpanded("ZIGZAG");
            tree = this.rotateLeft(tree);
            this.currentNode = tree;
            this.linkEarly(parent, tree, isSetLeft);
            this.animate("$This key doesn't exist###");
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZAG.3.4");
            this.algorithmSleep();
            this.codeStack.pop();
            this.switchPath = null;
            return tree;
        }
    }
    
    private SplayTreeNode search(final SplayTreeNode node, final SplayTreeDataItem dataItem, final Boolean isSetLeft) {
        SplayTreeDataNode.clearNodeColors();
        this.currentNode = node;
        this.codeStack.push("searchCode");
        this.setPosition("5");
        if (node == null) {
            throw new RuntimeException("insert: Node should never be null");
        }
        final SplayTreeDataNode parent = node.getParent();
        this.setPosition("5.1");
        if (node instanceof SplayTreeNullNode) {
            this.wasFound = new Boolean(false);
            this.setPosition("5.1.1");
            this.codeStack.stepCodeLine();
            this.setPosition("5.1.2");
            this.switchPath = null;
            this.algorithmSleep();
            this.codeStack.pop();
            return node;
        }
        SplayTreeDataNode tree = (SplayTreeDataNode)node;
        this.setPosition("Found.1");
        if (tree.getKey() == dataItem.getKey()) {
            this.codeStack.setCode("found");
            this.wasFound = new Boolean(true);
            tree.setIsFound();
            this.codeStack.stepCodeLine();
            this.setPosition("Found.1.1");
            this.codeStack.stepCodeLine();
            this.setPosition("Found.1.2");
            this.switchPath = null;
            this.algorithmSleep();
            this.codeStack.pop();
            return tree;
        }
        this.setPosition("Found.2");
        if (tree.getLeft() instanceof SplayTreeDataNode && dataItem.getKey() == ((SplayTreeDataNode)tree.getLeft()).getKey()) {
            this.codeStack.setCode("found rotate");
            this.codeStack.stepCodeLine();
            this.setPosition("Found.2.1");
            this.wasFound = new Boolean(true);
            ((SplayTreeDataNode)tree.getLeft()).setIsFound();
            this.codeStack.stepCodeLine();
            this.setPosition("Found.2.2");
            this.isTweenFast = !this.isExpanded("Success");
            tree = this.rotateRight(tree);
            this.linkEarly(parent, tree, isSetLeft);
            this.animate("$This key doesn't exist###");
            this.currentNode = tree;
            this.switchPath = null;
            this.codeStack.stepCodeLine();
            this.setPosition("Found.2.3");
            this.algorithmSleep();
            this.codeStack.pop();
            return tree;
        }
        if (tree.getRight() instanceof SplayTreeDataNode && dataItem.getKey() == ((SplayTreeDataNode)tree.getRight()).getKey()) {
            this.codeStack.setCode("found rotate");
            this.codeStack.stepCodeLine();
            this.setPosition("Found.2.1");
            this.wasFound = new Boolean(true);
            ((SplayTreeDataNode)tree.getRight()).setIsFound();
            this.codeStack.stepCodeLine();
            this.setPosition("Found.2.2");
            this.isTweenFast = !this.isExpanded("Success");
            tree = this.rotateLeft(tree);
            this.linkEarly(parent, tree, isSetLeft);
            this.animate("$This key doesn't exist###");
            this.currentNode = tree;
            this.switchPath = null;
            this.codeStack.stepCodeLine();
            this.setPosition("Found.2.3");
            this.algorithmSleep();
            this.codeStack.pop();
            return tree;
        }
        if (dataItem.getKey() < tree.getKey()) {
            this.switchPath = tree.getLeft();
        }
        else {
            this.switchPath = tree.getRight();
        }
        if (this.switchPath instanceof SplayTreeDataNode) {
            if (dataItem.getKey() < ((SplayTreeDataNode)this.switchPath).getKey()) {
                this.switchPath = ((SplayTreeDataNode)this.switchPath).getLeft();
            }
            else {
                this.switchPath = ((SplayTreeDataNode)this.switchPath).getRight();
            }
        }
        this.setPosition("5.2");
        if ((tree.getLeft() instanceof SplayTreeNullNode && dataItem.getKey() < tree.getKey()) || (tree.getRight() instanceof SplayTreeNullNode && dataItem.getKey() > tree.getKey())) {
            this.setPosition("TERMINAL");
            this.wasFound = new Boolean(false);
            this.setPosition("TERMINAL.1");
            this.setPosition("TERMINAL.2");
            this.switchPath = null;
            this.algorithmSleep();
            this.codeStack.pop();
            return tree;
        }
        if ((dataItem.getKey() < tree.getKey() && dataItem.getKey() < ((SplayTreeDataNode)tree.getLeft()).getKey()) || (dataItem.getKey() >= tree.getKey() && dataItem.getKey() >= ((SplayTreeDataNode)tree.getRight()).getKey())) {
            this.codeStack.setCode("zigZigSearchCode");
            this.setPosition("ZIGZIG");
            this.setPosition("ZIGZIG.2");
            if (dataItem.getKey() < tree.getKey()) {
                final SplayTreeDataNode leftChild = (SplayTreeDataNode)tree.getLeft();
                this.setPosition("ZIGZIG.3.1", "ZIGZIG.1", false);
                this.setPosition("ZIGZIG.2.1");
                leftChild.setLeft(this.search(leftChild.getLeft(), dataItem, new Boolean(true)));
                this.currentNode = tree;
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZIG.3.2", "ZIGZIG.1", false);
                this.setPosition("ZIGZIG.2.2");
                this.isTweenFast = !this.isExpanded("ZIGZIG");
                tree = this.rotateRight(tree);
                this.currentNode = tree;
                this.linkEarly(parent, tree, isSetLeft);
                this.animate("$This key doesn't exist###");
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZIG.3.3", "ZIGZIG.1", false);
                this.setPosition("ZIGZIG.2.3");
                this.isTweenFast = !this.isExpanded("ZIGZIG");
                tree = this.rotateRight(tree);
                this.currentNode = tree;
                this.linkEarly(parent, tree, isSetLeft);
                this.animate("$This key doesn't exist###");
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZIG.3.4", "ZIGZIG.1", false);
                this.setPosition("ZIGZIG.2.4");
                this.algorithmSleep();
                this.codeStack.pop();
                return tree;
            }
            this.setPosition("ZIGZIG.3");
            final SplayTreeDataNode rightChild = (SplayTreeDataNode)tree.getRight();
            this.setPosition("ZIGZIG.3.1");
            rightChild.setRight(this.search(rightChild.getRight(), dataItem, new Boolean(false)));
            this.currentNode = tree;
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZIG.3.2");
            this.isTweenFast = !this.isExpanded("ZIGZIG");
            tree = this.rotateLeft(tree);
            this.currentNode = tree;
            this.linkEarly(parent, tree, isSetLeft);
            this.animate("$This key doesn't exist###");
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZIG.3.3");
            this.isTweenFast = !this.isExpanded("ZIGZIG");
            tree = this.rotateLeft(tree);
            this.currentNode = tree;
            this.linkEarly(parent, tree, isSetLeft);
            this.animate("$This key doesn't exist###");
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZIG.3.4");
            this.algorithmSleep();
            this.codeStack.pop();
            return tree;
        }
        else {
            if ((dataItem.getKey() >= tree.getKey() || dataItem.getKey() < ((SplayTreeDataNode)tree.getLeft()).getKey()) && (dataItem.getKey() < tree.getKey() || dataItem.getKey() >= ((SplayTreeDataNode)tree.getRight()).getKey())) {
                throw new RuntimeException("This case should be unreachable");
            }
            this.codeStack.setCode("zigZagSearchCode");
            this.setPosition("ZIGZAG");
            this.setPosition("ZIGZAG.2");
            if (dataItem.getKey() < tree.getKey()) {
                final SplayTreeDataNode leftChild2 = (SplayTreeDataNode)tree.getLeft();
                this.setPosition("ZIGZAG.3.1", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.1");
                leftChild2.setRight(this.search(leftChild2.getRight(), dataItem, new Boolean(false)));
                this.currentNode = tree;
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZAG.3.2", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.2");
                this.isTweenFast = !this.isExpanded("ZIGZAG");
                tree.setLeft(this.rotateLeft(leftChild2));
                this.animate("$This key doesn't exist###");
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZAG.3.3", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.3");
                this.isTweenFast = !this.isExpanded("ZIGZAG");
                tree = this.rotateRight(tree);
                this.currentNode = tree;
                this.linkEarly(parent, tree, isSetLeft);
                this.animate("$This key doesn't exist###");
                this.codeStack.stepCodeLine();
                this.setPosition("ZIGZAG.3.4", "ZIGZAG.1", false);
                this.setPosition("ZIGZAG.2.4");
                this.algorithmSleep();
                this.codeStack.pop();
                return tree;
            }
            this.setPosition("ZIGZAG.3");
            final SplayTreeDataNode rightChild2 = (SplayTreeDataNode)tree.getRight();
            this.setPosition("ZIGZAG.3.1");
            rightChild2.setLeft(this.search(rightChild2.getLeft(), dataItem, new Boolean(true)));
            this.currentNode = tree;
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZAG.3.2");
            this.isTweenFast = !this.isExpanded("ZIGZAG");
            tree.setRight(this.rotateRight(rightChild2));
            this.animate("$This key doesn't exist###");
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZAG.3.3");
            this.isTweenFast = !this.isExpanded("ZIGZAG");
            tree = this.rotateLeft(tree);
            this.currentNode = tree;
            this.linkEarly(parent, tree, isSetLeft);
            this.animate("$This key doesn't exist###");
            this.codeStack.stepCodeLine();
            this.setPosition("ZIGZAG.3.4");
            this.algorithmSleep();
            this.codeStack.pop();
            return tree;
        }
    }
    
    private void linkEarly(final SplayTreeDataNode parent, final SplayTreeNode node, final Boolean isSetLeft) {
        if (isSetLeft != null) {
            if (isSetLeft.booleanValue()) {
                parent.setLeft(node);
            }
            else {
                parent.setRight(node);
            }
        }
        else {
            this.head = node;
        }
    }
    
    private void printTree(final SplayTreeNode tree) {
        if (tree instanceof SplayTreeDataNode) {
            this.printTree(((SplayTreeDataNode)tree).getLeft());
            System.out.println(((SplayTreeDataNode)tree).getKey());
            this.printTree(((SplayTreeDataNode)tree).getRight());
        }
    }
    
    private void setPosition(final String key, final String condition, final boolean isSetOnExpanded) {
        if (this.isExpanded(condition) == isSetOnExpanded) {
            this.setPosition(key);
        }
        else {
            this.setPosition("This key does not exist");
        }
    }
    
    private boolean isExpanded(final String key) {
        return ((SplayTreeThread)this.algorithmThread).getAlgorithmWindow().isExpanded(key);
    }
    
    public boolean isTweenFast() {
        return this.isTweenFast;
    }
    
    private void animate(final String key) {
        this.setPosition(key);
        if (!this.isBackMode) {
            super.repaint();
        }
        else {
            this.tweenVectorVector = null;
        }
        this.isTweenFast = false;
    }
    
    private void clearPointers() {
        this.currentNode = null;
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
    
    private void algorithmSleep() {
        if (!this.isBackMode) {
            super.repaint();
            try {
                final AlgorithmThread algorithmThread = this.algorithmThread;
                AlgorithmThread.sleep((long)(((SplayTreeThread)this.algorithmThread).getRunSleepDuration() / 3));
            }
            catch (Exception ex) {}
        }
    }
    
    static {
        BUILD_MODE_LABEL = Messages.getString("SplayTree.0");
        SEARCH_MODE_LABEL = Messages.getString("SplayTree.1");
    }
}
