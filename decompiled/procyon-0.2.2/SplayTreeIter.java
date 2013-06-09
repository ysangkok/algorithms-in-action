import java.awt.*;
import localization.*;
import java.util.*;
import com.cim.AIA.*;

public class SplayTreeIter extends Algorithm implements MethodSelectionListener, ControlListener
{
    private static final String BUILD_MODE_LABEL;
    private static final String SEARCH_MODE_LABEL;
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private SplayTreeIterNode currentNode;
    private SplayTreeIterNode parentNode;
    private SplayTreeIterNode switchPath;
    private Vector<E> tweenVectorVector;
    private boolean isTweenFast;
    private boolean isBackMode;
    private SplayTreeIterCodeStack codeStack;
    private Boolean wasFound;
    private int currentLevel;
    private boolean noMoveTween;
    private boolean drawSwitch;
    private boolean drawParentSwitch;
    private boolean drawPointers;
    private SplayTreeIterNode head;
    private SplayTreeIterNode savedTree;
    private SplayTreeIterNode startingTree;
    private ElementArray insertData;
    private ElementArray newInsertData;
    private ElementArray searchData;
    private ElementArray newSearchData;
    private int insertPos;
    private int lastInsertPos;
    
    public SplayTreeIter(final AlgorithmThread algorithmThread, final Object data) {
        super(algorithmThread, data);
        this.isBackMode = false;
        this.currentLevel = -1;
        this.noMoveTween = false;
        this.drawSwitch = false;
        this.drawParentSwitch = false;
        this.drawPointers = true;
        this.head = new SplayTreeIterNullNode();
        this.savedTree = new SplayTreeIterNullNode();
        this.executingMode = 11;
        this.nextMode = this.executingMode;
        this.changeData(data);
        this.insertData = this.newInsertData;
        this.searchData = this.newSearchData;
        this.codeStack = new SplayTreeIterCodeStack();
    }
    
    public void reInitialise(final Object data) {
        this.noMoveTween = true;
        this.drawPointers = true;
        this.tweenVectorVector = null;
        if (!this.isBackMode) {
            this.changeData(data);
            this.insertData = this.newInsertData;
            this.searchData = this.newSearchData;
        }
        this.resetColors();
        if (this.executingMode == 11 && !this.isBackMode) {
            this.savedTree = new SplayTreeIterNullNode();
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
                ((Node)this.insertData.getElement(i)).setBackgroundColor(SplayTreeIterColors.INSERT_ACTIVE_COLOR);
            }
        }
        if (this.searchData != null) {
            for (i = 0; i < this.searchData.getSize(); ++i) {
                ((Node)this.searchData.getElement(i)).setBackgroundColor(SplayTreeIterColors.SEARCH_ACTIVE_COLOR);
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
                ((Node)this.newInsertData.getElement(i)).setBackgroundColor(SplayTreeIterColors.INSERT_ACTIVE_COLOR);
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
            ((Node)this.newSearchData.getElement(i)).setBackgroundColor(SplayTreeIterColors.SEARCH_ACTIVE_COLOR);
        }
    }
    
    private void save() {
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
        this.head = new SplayTreeIterNullNode();
    }
    
    protected void initialiseMethods(final String string, final MethodSelectable methodSelectable) {
        final MethodSelection methodSelection1 = new MethodSelection(SplayTreeIter.BUILD_MODE_LABEL, string, Messages.getString("SplayTreeIter.2"), "3a", this.getLogger(), this.getBreakPoint());
        final MethodSelection methodSelection2 = new MethodSelection(SplayTreeIter.SEARCH_MODE_LABEL, string, Messages.getString("SplayTreeIter.4"), "5a", this.getLogger(), this.getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelectionListener(this);
    }
    
    public void processMethodSelectionEvent(final MethodSelectionEvent e) {
        if (SplayTreeIter.BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
            this.nextMode = 11;
        }
        else if (SplayTreeIter.SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0) {
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
    
    public SplayTreeIterCodeStack getCodeStack() {
        return this.codeStack;
    }
    
    public Vector<E> getTweens(final Paintable paintable, final Object parameter, final int numberOfSteps) {
        final Vector<E> multiTweenArray = new Vector();
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
        if (this.isBackMode) {
            return null;
        }
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
    
    public SplayTreeIterNode getHead() {
        this.unHighlightPath(this.head);
        this.highlightSwitchPath(this.switchPath);
        this.highlightPath(this.currentNode);
        if (this.drawSwitch && this.currentNode != null) {
            this.currentNode.setIsOnPath(false);
            this.currentNode.setIsOnSwitchPath(true);
        }
        if (this.drawParentSwitch && this.parentNode != null) {
            this.parentNode.setIsOnPath(false);
            this.parentNode.setIsOnSwitchPath(true);
        }
        return this.head;
    }
    
    public void highlightSwitchPath(final SplayTreeIterNode node) {
        if (node != null) {
            node.setIsOnSwitchPath(true);
            this.highlightSwitchPath(node.getParent());
        }
    }
    
    public void highlightPath(final SplayTreeIterNode node) {
        if (node != null) {
            node.setIsOnPath(true);
            this.highlightPath(node.getParent());
        }
    }
    
    private void unHighlightPath(final SplayTreeIterNode node) {
        if (node != null) {
            node.setIsOnPath(false);
            node.setIsOnSwitchPath(false);
            if (node instanceof SplayTreeIterDataNode) {
                this.unHighlightPath(((SplayTreeIterDataNode)node).getLeft());
                this.unHighlightPath(((SplayTreeIterDataNode)node).getRight());
            }
        }
    }
    
    public SplayTreeIterNode getCurrentNode() {
        if (!this.drawPointers) {
            return null;
        }
        return this.currentNode;
    }
    
    public SplayTreeIterNode getParentNode() {
        if (!this.drawPointers) {
            return null;
        }
        return this.parentNode;
    }
    
    public int getCurrentLevel() {
        return this.currentLevel;
    }
    
    public boolean getNoMoveTween() {
        return this.noMoveTween;
    }
    
    protected void run(final boolean canChangeMode) {
        if (canChangeMode) {
            this.executingMode = this.nextMode;
            SplayTreeIterDataNode.clearNodeColors();
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
                this.startingTree = new SplayTreeIterNullNode();
            }
        }
        switch (this.executingMode) {
            case 11: {
                for (int i = this.insertPos; i < this.insertData.getSize(); ++i) {
                    this.setPosition("2a");
                    final SplayTreeIterDataItem dataItem = new SplayTreeIterDataItem(this.insertData.getElement(i).getObject().toString(), ((Integer)this.insertData.getElement(i).getObject()).intValue());
                    if (this.enabled) {
                        ((Node)this.insertData.getElement(i)).setBackgroundColor(SplayTreeIterColors.INSERT_HIGHLIGHT_COLOR);
                        this.currentNode = this.head;
                        this.savedTree = this.head.getCopy();
                        this.head = this.insert(this.head, dataItem, null);
                        this.currentLevel = -1;
                        this.parentNode = null;
                        this.currentNode = null;
                        ((Node)this.insertData.getElement(i)).setBackgroundColor(SplayTreeIterColors.INSERT_DONE_COLOR);
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
                if (this.head instanceof SplayTreeIterNullNode) {
                    break;
                }
                for (int i = 0; i < this.searchData.getSize(); ++i) {
                    final Node searchNode = (Node)this.searchData.getElement(i);
                    final SplayTreeIterDataItem dataItem = new SplayTreeIterDataItem(searchNode.getObject().toString(), ((Integer)searchNode.getObject()).intValue());
                    if (this.enabled) {
                        searchNode.setBackgroundColor(SplayTreeIterColors.SEARCH_HIGHLIGHT_COLOR);
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
                        this.setPosition("5.4");
                        searchNode.setBackgroundColor(SplayTreeIterColors.SEARCH_DONE_COLOR);
                    }
                    this.clearPointers();
                }
                break;
            }
        }
    }
    
    private SplayTreeIterDataNode rotateRight(final SplayTreeIterNode parent, final boolean animate) {
        if (((SplayTreeIterDataNode)parent).getLeft() instanceof SplayTreeIterNullNode) {
            return (SplayTreeIterDataNode)parent;
        }
        if (this.tweenVectorVector == null) {
            this.tweenVectorVector = new Vector();
        }
        Vector<E> tweenVector = new Vector();
        final SplayTreeIterDataNode grandParent = parent.getParent();
        final SplayTreeIterDataNode child = (SplayTreeIterDataNode)((SplayTreeIterDataNode)parent).getLeft();
        if (grandParent != null) {
            if (grandParent.getLeft() == parent) {
                tweenVector.addElement(new MoveMoveable(grandParent.getLeftLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            }
            else {
                tweenVector.addElement(new MoveMoveable(grandParent.getRightLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            }
        }
        tweenVector.addElement(new MoveMoveable(((SplayTreeIterDataNode)parent).getLeftLink(), new Point(((SplayTreeIterDataNode)parent).getLeft().getPosition()), new Point(child.getRight().getPosition()), false));
        tweenVector.addElement(new MoveMoveable(child.getRightLink(), new Point(child.getRight().getPosition()), new Point(parent.getPosition()), false));
        this.tweenVectorVector.addElement(tweenVector);
        if (!animate) {
            this.tweenVectorVector = new Vector();
        }
        final boolean temp = this.isTweenFast;
        this.animate("$This key doesn't exist###");
        this.isTweenFast = temp;
        if (this.tweenVectorVector == null) {
            this.tweenVectorVector = new Vector();
        }
        final Vector<E> vector;
        tweenVector = (vector = new Vector());
        vector.addElement(new MoveMoveable(parent, new Point(parent.getPosition()), new Point(parent.getPosition().x, ((SplayTreeIterDataNode)parent).getLeft().getPosition().y), false));
        final SplayTreeIterNode parentRight = ((SplayTreeIterDataNode)parent).getRight();
        tweenVector.addElement(new MoveMoveable(parentRight, new Point(parentRight.getPosition()), parentRight.getPosition(), true));
        tweenVector.addElement(new MoveMoveable(child.getLeft(), new Point(child.getLeft().getPosition()), new Point(child.getLeft().getPosition().x, ((SplayTreeIterDataNode)parent).getLeft().getPosition().y), true));
        tweenVector.addElement(new MoveMoveable(child, new Point(child.getPosition()), new Point(child.getPosition().x, parent.getPosition().y), false));
        this.tweenVectorVector.addElement(tweenVector);
        if (!animate) {
            this.tweenVectorVector = new Vector();
        }
        ((SplayTreeIterDataNode)parent).setLeft(child.getRight());
        child.setRight(parent);
        child.setParent(grandParent);
        if (grandParent != null) {
            if (grandParent.getLeft() == parent) {
                grandParent.setLeft(child);
            }
            else if (grandParent.getRight() == parent) {
                grandParent.setRight(child);
            }
        }
        return child;
    }
    
    private SplayTreeIterDataNode rotateLeft(final SplayTreeIterNode parent, final boolean animate) {
        if (((SplayTreeIterDataNode)parent).getRight() instanceof SplayTreeIterNullNode) {
            return (SplayTreeIterDataNode)parent;
        }
        if (this.tweenVectorVector == null) {
            this.tweenVectorVector = new Vector();
        }
        Vector<E> tweenVector = new Vector();
        final SplayTreeIterDataNode grandParent = parent.getParent();
        final SplayTreeIterDataNode child = (SplayTreeIterDataNode)((SplayTreeIterDataNode)parent).getRight();
        if (grandParent != null) {
            if (grandParent.getLeft() == parent) {
                tweenVector.addElement(new MoveMoveable(grandParent.getLeftLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            }
            else {
                tweenVector.addElement(new MoveMoveable(grandParent.getRightLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            }
        }
        tweenVector.addElement(new MoveMoveable(((SplayTreeIterDataNode)parent).getRightLink(), new Point(((SplayTreeIterDataNode)parent).getRight().getPosition()), new Point(child.getLeft().getPosition()), false));
        tweenVector.addElement(new MoveMoveable(child.getLeftLink(), new Point(child.getLeft().getPosition()), new Point(parent.getPosition()), false));
        this.tweenVectorVector.addElement(tweenVector);
        if (!animate) {
            this.tweenVectorVector = new Vector();
        }
        final boolean temp = this.isTweenFast;
        this.animate("$This key doesn't exist###");
        this.isTweenFast = temp;
        if (this.tweenVectorVector == null) {
            this.tweenVectorVector = new Vector();
        }
        final Vector<E> vector;
        tweenVector = (vector = new Vector());
        vector.addElement(new MoveMoveable(parent, new Point(parent.getPosition()), new Point(parent.getPosition().x, ((SplayTreeIterDataNode)parent).getRight().getPosition().y), false));
        final SplayTreeIterNode parentLeft = ((SplayTreeIterDataNode)parent).getLeft();
        tweenVector.addElement(new MoveMoveable(parentLeft, new Point(parentLeft.getPosition()), parentLeft.getPosition(), true));
        tweenVector.addElement(new MoveMoveable(child.getRight(), new Point(child.getRight().getPosition()), new Point(child.getRight().getPosition().x, ((SplayTreeIterDataNode)parent).getRight().getPosition().y), true));
        tweenVector.addElement(new MoveMoveable(child, new Point(child.getPosition()), new Point(child.getPosition().x, parent.getPosition().y), false));
        this.tweenVectorVector.addElement(tweenVector);
        if (!animate) {
            this.tweenVectorVector = new Vector();
        }
        ((SplayTreeIterDataNode)parent).setRight(child.getLeft());
        child.setLeft(parent);
        child.setParent(grandParent);
        if (grandParent != null) {
            if (grandParent.getLeft() == parent) {
                grandParent.setLeft(child);
            }
            else if (grandParent.getRight() == parent) {
                grandParent.setRight(child);
            }
        }
        return child;
    }
    
    private SplayTreeIterNode insert(SplayTreeIterNode node, final SplayTreeIterDataItem dataItem, final Boolean isSetLeft) {
        this.switchPath = null;
        this.setPosition("3");
        if (node == null) {
            throw new RuntimeException("insert: Node should never be null");
        }
        final SplayTreeIterDataNode parent = node.getParent();
        this.setPosition("3.1");
        if (node instanceof SplayTreeIterNullNode) {
            node = new SplayTreeIterDataNode(dataItem);
            this.linkEarly(parent, node, isSetLeft);
            this.currentNode = node;
            this.setPosition("3.1.1");
            this.switchPath = null;
            return node;
        }
        this.currentNode = node;
        this.setPosition("3.2.1.1");
        this.noMoveTween = false;
        while (!(this.currentNode instanceof SplayTreeIterNullNode)) {
            this.setPosition("3.2.2.1");
            this.parentNode = this.currentNode;
            this.setPosition("3.2.2.1.1");
            this.setPosition("3.2.2.1.2");
            if (dataItem.getKey() < ((SplayTreeIterDataNode)this.currentNode).getKey()) {
                this.currentNode = ((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.currentNode)).getLeft();
                this.setPosition("3.2.2.1.3");
            }
            else {
                this.setPosition("3.2.2.1.4");
                this.currentNode = ((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.currentNode)).getRight();
                this.setPosition("3.2.2.1.5");
            }
        }
        this.setPosition("3.2.2.1");
        this.setPosition("3.2.2.2");
        this.noMoveTween = true;
        this.setPosition("3.2.3.1");
        this.setPosition("3.2.3.2");
        final SplayTreeIterDataNode newNode = new SplayTreeIterDataNode(dataItem);
        if (dataItem.getKey() < ((SplayTreeIterDataNode)this.parentNode).getKey()) {
            ((SplayTreeIterDataNode)this.parentNode).setLeft(newNode);
            this.setPosition("3.2.3.3");
        }
        else {
            this.setPosition("3.2.3.4");
            ((SplayTreeIterDataNode)this.parentNode).setRight(newNode);
            this.setPosition("3.2.3.5");
        }
        this.currentNode = newNode;
        this.setPosition("3.2.3.6");
        this.noMoveTween = false;
        while (this.currentNode != this.head && this.parentNode != this.head) {
            this.drawSwitch = true;
            this.drawParentSwitch = true;
            if (!this.isExpanded("3.3.1") || !this.isExpanded("3.3")) {
                this.drawPointers = false;
            }
            else {
                this.drawPointers = true;
            }
            this.setPosition("3.3.1.1");
            if (this.parentNode.getParent().getLeft() == this.parentNode && ((SplayTreeIterDataNode)this.parentNode).getLeft() == this.currentNode) {
                this.setPosition("3.3.1.1.1");
                if (!this.isExpanded("3.3.1") || !this.isExpanded("3.3.1.1.1") || !this.isExpanded("3.3")) {
                    this.parentNode = this.rotateRight(this.parentNode.getParent(), false);
                }
                else {
                    this.parentNode = this.rotateRight(this.parentNode.getParent(), true);
                }
                if (this.parentNode.getParent() == null) {
                    this.head = this.parentNode;
                }
                this.setPosition("3.3.1.1.2");
                if (!this.isExpanded("3.3.1") || !this.isExpanded("3.3.1.1.1") || !this.isExpanded("3.3")) {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                if (this.parentNode == null) {
                    this.head = this.currentNode;
                }
                this.setPosition("3.3.1.1.3");
            }
            else if (this.parentNode.getParent().getRight() == this.parentNode && ((SplayTreeIterDataNode)this.parentNode).getRight() == this.currentNode) {
                this.setPosition("3.3.1.1.1");
                if (!this.isExpanded("3.3.1") || !this.isExpanded("3.3.1.1.1") || !this.isExpanded("3.3")) {
                    this.parentNode = this.rotateLeft(this.parentNode.getParent(), false);
                }
                else {
                    this.parentNode = this.rotateLeft(this.parentNode.getParent(), true);
                }
                if (this.parentNode.getParent() == null) {
                    this.head = this.parentNode;
                }
                this.setPosition("3.3.1.1.2");
                if (!this.isExpanded("3.3.1") || !this.isExpanded("3.3.1.1.1") || !this.isExpanded("3.3")) {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                if (this.parentNode == null) {
                    this.head = this.currentNode;
                }
                this.setPosition("3.3.1.1.3");
            }
            else if (this.parentNode.getParent().getLeft() == this.parentNode && ((SplayTreeIterDataNode)this.parentNode).getRight() == this.currentNode) {
                this.setPosition("3.3.1.1.1");
                this.setPosition("3.3.1.1.4");
                if (!this.isExpanded("3.3.1") || !this.isExpanded("3.3.1.1.4") || !this.isExpanded("3.3")) {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.parentNode), false);
                }
                else {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.parentNode), true);
                }
                this.parentNode = this.currentNode.getParent();
                this.setPosition("3.3.1.1.5");
                if (!this.isExpanded("3.3.1") || !this.isExpanded("3.3.1.1.4") || !this.isExpanded("3.3")) {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                if (this.parentNode == null) {
                    this.head = this.currentNode;
                }
                this.setPosition("3.3.1.1.6");
            }
            else if (this.parentNode.getParent().getRight() == this.parentNode && ((SplayTreeIterDataNode)this.parentNode).getLeft() == this.currentNode) {
                this.setPosition("3.3.1.1.1");
                this.setPosition("3.3.1.1.4");
                if (!this.isExpanded("3.3.1") || !this.isExpanded("3.3.1.1.4") || !this.isExpanded("3.3")) {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.parentNode), false);
                }
                else {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.parentNode), true);
                }
                this.parentNode = this.currentNode.getParent();
                this.setPosition("3.3.1.1.5");
                if (!this.isExpanded("3.3.1") || !this.isExpanded("3.3.1.1.4") || !this.isExpanded("3.3")) {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                if (this.parentNode == null) {
                    this.head = this.currentNode;
                }
                this.setPosition("3.3.1.1.6");
            }
        }
        this.drawPointers = true;
        this.drawSwitch = false;
        this.drawParentSwitch = false;
        this.setPosition("3.3.1.1");
        this.setPosition("3.3.1.2");
        this.drawSwitch = true;
        this.setPosition("3.3.2.1");
        if (this.currentNode != this.head) {
            if (!this.isExpanded("3.3")) {
                this.drawPointers = false;
            }
            else {
                this.drawPointers = true;
            }
            if (this.currentNode == ((SplayTreeIterDataNode)this.parentNode).getRight()) {
                this.currentNode = this.parentNode;
                if (!this.isExpanded("3.3")) {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                this.head = this.currentNode;
            }
            else {
                this.currentNode = this.parentNode;
                if (!this.isExpanded("3.3")) {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                this.head = this.currentNode;
            }
            this.setPosition("3.3.2.1.1");
        }
        this.drawPointers = true;
        this.drawSwitch = false;
        this.setPosition("3.3.2.2");
        this.setPosition("3.END");
        return this.currentNode;
    }
    
    private SplayTreeIterNode search(final SplayTreeIterNode node, final SplayTreeIterDataItem dataItem, final Boolean isSetLeft) {
        SplayTreeIterDataNode.clearNodeColors();
        this.currentNode = node;
        this.setPosition("5.1.1");
        boolean returnState = false;
        this.setPosition("5.1.2");
        this.noMoveTween = false;
        if (node == null) {
            throw new RuntimeException("insert: Node should never be null");
        }
        while (!(this.currentNode instanceof SplayTreeIterNullNode)) {
            this.setPosition("5.2.1");
            this.setPosition("5.2.1.2");
            if (dataItem.getKey() == ((SplayTreeIterDataNode)this.currentNode).getKey()) {
                returnState = true;
                this.setPosition("5.2.1.2.1");
                this.setPosition("5.2.1.2.2");
                break;
            }
            this.parentNode = this.currentNode;
            this.setPosition("5.2.1.1");
            this.setPosition("5.2.1.3");
            if (dataItem.getKey() < ((SplayTreeIterDataNode)this.currentNode).getKey()) {
                this.currentNode = ((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.currentNode)).getLeft();
                this.setPosition("5.2.1.3.1");
            }
            else {
                this.setPosition("5.2.1.4");
                this.currentNode = ((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.currentNode)).getRight();
                this.setPosition("5.2.1.4.1");
            }
        }
        this.setPosition("5.2.1");
        this.setPosition("5.2.2");
        this.setPosition("5.2.3.1");
        if (this.currentNode instanceof SplayTreeIterNullNode) {
            this.currentNode = this.parentNode;
            this.setPosition("5.2.3.2");
            this.parentNode = this.parentNode.getParent();
            this.setPosition("5.2.3.3");
        }
        this.setPosition("5.2.4");
        while (this.currentNode != this.head && this.parentNode != this.head) {
            this.drawSwitch = true;
            this.drawParentSwitch = true;
            this.setPosition("5.3.1.1");
            if (this.parentNode.getParent().getLeft() == this.parentNode && ((SplayTreeIterDataNode)this.parentNode).getLeft() == this.currentNode) {
                this.setPosition("5.3.1.1.1");
                if (!this.isExpanded("5.3.1") || !this.isExpanded("5.3.1.1.1") || !this.isExpanded("5.3")) {
                    this.parentNode = this.rotateRight(this.parentNode.getParent(), false);
                }
                else {
                    this.parentNode = this.rotateRight(this.parentNode.getParent(), true);
                }
                if (this.parentNode.getParent() == null) {
                    this.head = this.parentNode;
                }
                this.setPosition("5.3.1.1.2");
                if (!this.isExpanded("5.3.1") || !this.isExpanded("5.3.1.1.1") || !this.isExpanded("5.3")) {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                if (this.parentNode == null) {
                    this.head = this.currentNode;
                }
                this.setPosition("5.3.1.1.3");
            }
            else if (this.parentNode.getParent().getRight() == this.parentNode && ((SplayTreeIterDataNode)this.parentNode).getRight() == this.currentNode) {
                this.setPosition("5.3.1.1.1");
                if (!this.isExpanded("5.3.1") || !this.isExpanded("5.3.1.1.1") || !this.isExpanded("5.3")) {
                    this.parentNode = this.rotateLeft(this.parentNode.getParent(), false);
                }
                else {
                    this.parentNode = this.rotateLeft(this.parentNode.getParent(), true);
                }
                if (this.parentNode.getParent() == null) {
                    this.head = this.parentNode;
                }
                this.setPosition("5.3.1.1.2");
                if (!this.isExpanded("5.3.1") || !this.isExpanded("5.3.1.1.1") || !this.isExpanded("5.3")) {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                if (this.parentNode == null) {
                    this.head = this.currentNode;
                }
                this.setPosition("5.3.1.1.3");
            }
            else if (this.parentNode.getParent().getLeft() == this.parentNode && ((SplayTreeIterDataNode)this.parentNode).getRight() == this.currentNode) {
                this.setPosition("5.3.1.1.1");
                this.setPosition("5.3.1.1.4");
                if (!this.isExpanded("5.3.1") || !this.isExpanded("5.3.1.1.4") || !this.isExpanded("5.3")) {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.parentNode), false);
                }
                else {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.parentNode), true);
                }
                this.parentNode = this.currentNode.getParent();
                this.setPosition("5.3.1.1.5");
                if (!this.isExpanded("5.3.1") || !this.isExpanded("5.3.1.1.4") || !this.isExpanded("5.3")) {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                if (this.parentNode == null) {
                    this.head = this.currentNode;
                }
                this.setPosition("5.3.1.1.6");
            }
            else if (this.parentNode.getParent().getRight() == this.parentNode && ((SplayTreeIterDataNode)this.parentNode).getLeft() == this.currentNode) {
                this.setPosition("5.3.1.1.1");
                this.setPosition("5.3.1.1.4");
                if (!this.isExpanded("5.3.1") || !this.isExpanded("5.3.1.1.4") || !this.isExpanded("5.3")) {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.parentNode), false);
                }
                else {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)((SplayTreeIterDataNode)this.parentNode), true);
                }
                this.parentNode = this.currentNode.getParent();
                this.setPosition("5.3.1.1.5");
                if (!this.isExpanded("5.3.1") || !this.isExpanded("5.3.1.1.4") || !this.isExpanded("5.3")) {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                if (this.parentNode == null) {
                    this.head = this.currentNode;
                }
                this.setPosition("5.3.1.1.6");
            }
        }
        this.drawSwitch = false;
        this.drawParentSwitch = false;
        this.setPosition("5.3.1.1");
        this.setPosition("5.3.1.2");
        this.drawSwitch = true;
        this.setPosition("5.3.2.1");
        if (this.currentNode != this.head) {
            if (this.currentNode == ((SplayTreeIterDataNode)this.parentNode).getRight()) {
                this.currentNode = this.parentNode;
                if (!this.isExpanded("5.3")) {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateLeft((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                this.head = this.currentNode;
            }
            else {
                this.currentNode = this.parentNode;
                if (!this.isExpanded("5.3")) {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, false);
                }
                else {
                    this.currentNode = this.rotateRight((SplayTreeIterDataNode)this.parentNode, true);
                }
                this.parentNode = this.currentNode.getParent();
                this.head = this.currentNode;
            }
            this.setPosition("5.3.2.1.1");
        }
        this.drawSwitch = false;
        this.setPosition("5.3.2.2");
        this.wasFound = new Boolean(returnState);
        return this.currentNode;
    }
    
    private void linkEarly(final SplayTreeIterDataNode parent, final SplayTreeIterNode node, final Boolean isSetLeft) {
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
    
    private void printTree(final SplayTreeIterNode tree) {
        if (tree instanceof SplayTreeIterDataNode) {
            this.printTree(((SplayTreeIterDataNode)tree).getLeft());
            System.out.println(((SplayTreeIterDataNode)tree).getKey());
            this.printTree(((SplayTreeIterDataNode)tree).getRight());
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
        return ((SplayTreeIterThread)this.algorithmThread).getAlgorithmWindow().isExpanded(key);
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
        this.parentNode = null;
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
    
    private void algorithmSleep() {
        if (!this.isBackMode) {
            super.repaint();
            try {
                final AlgorithmThread algorithmThread = this.algorithmThread;
                AlgorithmThread.sleep((long)(((SplayTreeIterThread)this.algorithmThread).getRunSleepDuration() / 3));
            }
            catch (Exception ex) {}
        }
    }
    
    static {
        BUILD_MODE_LABEL = Messages.getString("SplayTreeIter.0");
        SEARCH_MODE_LABEL = Messages.getString("SplayTreeIter.1");
    }
}
