// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTree.java

import com.cim.AIA.*;
import java.awt.Point;
import java.io.PrintStream;
import java.util.*;
import localization.Messages;

public class SplayTree extends Algorithm
    implements MethodSelectionListener, ControlListener
{
    private class MoveMoveable
    {

        private Point from;
        private Point to;
        private Moveable moveable;
        private boolean isEntire;
        final SplayTree this$0;





        protected MoveMoveable(Moveable moveable, Point from, Point to, boolean isEntire)
        {
            this$0 = SplayTree.this;
            super();
            this.moveable = moveable;
            this.from = from;
            this.to = to;
            this.isEntire = isEntire;
        }
    }


    public SplayTree(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        isBackMode = false;
        head = new SplayTreeNullNode();
        savedTree = new SplayTreeNullNode();
        executingMode = 11;
        nextMode = executingMode;
        changeData(data);
        insertData = newInsertData;
        searchData = newSearchData;
        codeStack = new SplayTreeCodeStack();
    }

    public void reInitialise(Object data)
    {
        tweenVectorVector = null;
        if(!isBackMode)
        {
            changeData(data);
            insertData = newInsertData;
            searchData = newSearchData;
        }
        resetColors();
        if(executingMode == 11 && !isBackMode)
        {
            savedTree = new SplayTreeNullNode();
            insertPos = 0;
        }
        if(executingMode == 12 && !isBackMode)
            head = savedTree.getCopy();
        if(isBackMode)
        {
            if(executingMode == 11)
                head = startingTree.getCopy();
            else
            if(executingMode == 12)
                head = savedTree.getCopy();
            insertPos = lastInsertPos;
        }
    }

    protected void resetColors()
    {
        if(insertData != null)
        {
            for(int i = insertPos; i < insertData.getSize(); i++)
                ((Node)insertData.getElement(i)).setBackgroundColor(SplayTreeColors.INSERT_ACTIVE_COLOR);

        }
        if(searchData != null)
        {
            for(int i = 0; i < searchData.getSize(); i++)
            {
                ((Node)searchData.getElement(i)).setBackgroundColor(SplayTreeColors.SEARCH_ACTIVE_COLOR);
                ((Node)searchData.getElement(i)).removeAllMarkers();
            }

        }
    }

    protected void changeData(Object data)
    {
        tweenVectorVector = null;
        Random random = new Random();
        if(executingMode == 11 || nextMode == 11)
        {
            newInsertData = new ElementArray(0, 0);
            newInsertData.setColumGap(0);
            newInsertData.setElementWidth(20);
            for(int i = 0; i < ((int[])(int[])data).length; i++)
            {
                newInsertData.add(new Node(new Integer(((int[])(int[])data)[i]), i), i);
                ((Node)newInsertData.getElement(i)).setBackgroundColor(SplayTreeColors.INSERT_ACTIVE_COLOR);
            }

        }
        newSearchData = new ElementArray(0, 0);
        newSearchData.setColumGap(0);
        newSearchData.setElementWidth(20);
        for(int i = 0; i < ((int[])(int[])data).length; i++)
        {
            if(i % 2 == 0)
                newSearchData.add(new Node(new Integer(Math.abs(random.nextInt()) % 98 + 1), i), i);
            else
                newSearchData.add(new Node(new Integer(((int[])(int[])data)[i]), i), i);
            ((Node)newSearchData.getElement(i)).setBackgroundColor(SplayTreeColors.SEARCH_ACTIVE_COLOR);
        }

    }

    private void save()
    {
        System.out.println("Saving tree");
        resetColors();
        if(executingMode == 11)
            savedTree = head.getCopy();
        else
        if(executingMode == 12)
            head = savedTree.getCopy();
    }

    protected void storeState(boolean forceState)
    {
        save();
    }

    protected void clearState()
    {
        head = new SplayTreeNullNode();
    }

    protected void initialiseMethods(String string, MethodSelectable methodSelectable)
    {
        MethodSelection methodSelection1 = new MethodSelection(BUILD_MODE_LABEL, string, Messages.getString("SplayTree.2"), "3a", getLogger(), getBreakPoint());
        MethodSelection methodSelection2 = new MethodSelection(SEARCH_MODE_LABEL, string, Messages.getString("SplayTree.3"), "5a", getLogger(), getBreakPoint());
        methodSelectable.addMethodSelection(methodSelection1, true);
        methodSelectable.addMethodSelection(methodSelection2, false);
        methodSelectable.addMethodSelectionListener(this);
    }

    public void processMethodSelectionEvent(MethodSelectionEvent e)
    {
        if(BUILD_MODE_LABEL.compareTo(e.getMethodName()) == 0)
            nextMode = 11;
        else
        if(SEARCH_MODE_LABEL.compareTo(e.getMethodName()) == 0)
            nextMode = 12;
        if(nextMode == 11)
            head = savedTree;
        if(nextMode == 12 && savedTree != null)
            head = savedTree.getCopy();
    }

    public MultipleTween generateTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        return null;
    }

    public SplayTreeCodeStack getCodeStack()
    {
        return codeStack;
    }

    public Vector getTweens(Paintable paintable, Object parameter, int numberOfSteps)
    {
        Vector multiTweenArray = new Vector();
        if(tweenVectorVector != null)
        {
            for(int j = 0; j < tweenVectorVector.size(); j++)
            {
                Vector tweenVector = (Vector)tweenVectorVector.elementAt(j);
                if(tweenVector == null)
                    continue;
                MultipleTween multiTween = new MultipleTween(paintable);
                int i = 0;
                if(tweenVector.elementAt(i) instanceof Hashtable)
                {
                    multiTweenArray.addElement((Hashtable)tweenVector.elementAt(i));
                    i++;
                }
                for(; i < tweenVector.size(); i++)
                    multiTween.add(new MoveTween(paintable, ((MoveMoveable)tweenVector.elementAt(i)).moveable, ((MoveMoveable)tweenVector.elementAt(i)).from, ((MoveMoveable)tweenVector.elementAt(i)).to, ((MoveMoveable)tweenVector.elementAt(i)).isEntire, numberOfSteps));

                if(multiTween.getNumberOfTweens() > 0)
                    multiTweenArray.addElement(multiTween);
            }

        }
        tweenVectorVector = null;
        if(multiTweenArray.size() > 0)
            return multiTweenArray;
        else
            return null;
    }

    protected Vector generateQuestions()
    {
        return null;
    }

    protected boolean hasQuestions()
    {
        return false;
    }

    protected void removeAllQuestions()
    {
    }

    protected void removeAllAnimationRequests()
    {
    }

    public ElementArray getInsertData()
    {
        if(executingMode == 11)
            return insertData;
        else
            return null;
    }

    public ElementArray getSearchData()
    {
        if(executingMode == 12)
            return searchData;
        else
            return null;
    }

    public SplayTreeNode getHead()
    {
        unHighlightPath(head);
        highlightSwitchPath(switchPath);
        highlightPath(currentNode);
        return head;
    }

    public void highlightSwitchPath(SplayTreeNode node)
    {
        if(node != null)
        {
            node.setIsOnSwitchPath(true);
            highlightSwitchPath(((SplayTreeNode) (node.getParent())));
        }
    }

    public void highlightPath(SplayTreeNode node)
    {
        if(node != null)
        {
            node.setIsOnPath(true);
            highlightPath(((SplayTreeNode) (node.getParent())));
        }
    }

    private void unHighlightPath(SplayTreeNode node)
    {
        if(node != null)
        {
            node.setIsOnPath(false);
            node.setIsOnSwitchPath(false);
            if(node instanceof SplayTreeDataNode)
            {
                unHighlightPath(((SplayTreeDataNode)node).getLeft());
                unHighlightPath(((SplayTreeDataNode)node).getRight());
            }
        }
    }

    public SplayTreeNode getCurrentNode()
    {
        return currentNode;
    }

    protected void run(boolean canChangeMode)
    {
        if(canChangeMode)
        {
            executingMode = nextMode;
            SplayTreeDataNode.clearNodeColors();
        }
        run();
    }

    protected void run()
    {
        if(!isBackMode)
        {
            lastInsertPos = insertPos;
            if(savedTree != null)
                startingTree = savedTree.getCopy();
            else
                startingTree = new SplayTreeNullNode();
        }
label0:
        switch(executingMode)
        {
        default:
            break;

        case 11: // '\013'
            for(int i = insertPos; i < insertData.getSize(); i++)
            {
                setPosition("2a");
                SplayTreeDataItem dataItem = new SplayTreeDataItem(insertData.getElement(i).getObject().toString(), ((Integer)insertData.getElement(i).getObject()).intValue());
                if(enabled)
                {
                    ((Node)insertData.getElement(i)).setBackgroundColor(SplayTreeColors.INSERT_HIGHLIGHT_COLOR);
                    currentNode = head;
                    savedTree = head.getCopy();
                    head = insert(head, dataItem, null);
                    currentNode = null;
                    ((Node)insertData.getElement(i)).setBackgroundColor(SplayTreeColors.INSERT_DONE_COLOR);
                    if(enabled)
                        insertPos++;
                    else
                        head = savedTree.getCopy();
                }
                clearPointers();
            }

            break;

        case 12: // '\f'
            int i = 0;
            do
            {
                if(i >= searchData.getSize())
                    break label0;
                Node searchNode = (Node)searchData.getElement(i);
                SplayTreeDataItem dataItem = new SplayTreeDataItem(searchNode.getObject().toString(), ((Integer)searchNode.getObject()).intValue());
                if(enabled)
                {
                    searchNode.setBackgroundColor(SplayTreeColors.SEARCH_HIGHLIGHT_COLOR);
                    wasFound = null;
                    currentNode = head;
                    head = search(head, dataItem, null);
                    currentNode = null;
                    if(wasFound != null)
                        if(wasFound.booleanValue())
                        {
                            searchNode.addMarker(new StringMarker("F"));
                            searchNode.addMarker(new StringMarker("o"));
                            searchNode.addMarker(new StringMarker("u"));
                            searchNode.addMarker(new StringMarker("n"));
                            searchNode.addMarker(new StringMarker("d"));
                        } else
                        {
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
                    searchNode.setBackgroundColor(SplayTreeColors.SEARCH_DONE_COLOR);
                }
                clearPointers();
                i++;
            } while(true);
        }
    }

    private SplayTreeDataNode rotateRight(SplayTreeNode parent)
    {
        if(((SplayTreeDataNode)parent).getLeft() instanceof SplayTreeNullNode)
            return (SplayTreeDataNode)parent;
        if(tweenVectorVector == null)
            tweenVectorVector = new Vector();
        Vector tweenVector = new Vector();
        SplayTreeDataNode grandParent = parent.getParent();
        SplayTreeDataNode child = (SplayTreeDataNode)((SplayTreeDataNode)parent).getLeft();
        if(grandParent != null)
            if(grandParent.getLeft() == parent)
                tweenVector.addElement(new MoveMoveable(grandParent.getLeftLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            else
                tweenVector.addElement(new MoveMoveable(grandParent.getRightLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
        tweenVector.addElement(new MoveMoveable(((SplayTreeDataNode)parent).getLeftLink(), new Point(((SplayTreeDataNode)parent).getLeft().getPosition()), new Point(child.getRight().getPosition()), false));
        tweenVector.addElement(new MoveMoveable(child.getRightLink(), new Point(child.getRight().getPosition()), new Point(parent.getPosition()), false));
        tweenVectorVector.addElement(tweenVector);
        boolean temp = isTweenFast;
        animate("$This key doesn't exist###");
        isTweenFast = temp;
        if(tweenVectorVector == null)
            tweenVectorVector = new Vector();
        tweenVector = new Vector();
        tweenVector.addElement(new MoveMoveable(parent, new Point(parent.getPosition()), new Point(parent.getPosition().x, ((SplayTreeDataNode)parent).getLeft().getPosition().y), false));
        SplayTreeNode parentRight = ((SplayTreeDataNode)parent).getRight();
        tweenVector.addElement(new MoveMoveable(parentRight, new Point(parentRight.getPosition()), parentRight.getPosition(), true));
        tweenVector.addElement(new MoveMoveable(child.getLeft(), new Point(child.getLeft().getPosition()), new Point(child.getLeft().getPosition().x, ((SplayTreeDataNode)parent).getLeft().getPosition().y), true));
        tweenVector.addElement(new MoveMoveable(child, new Point(child.getPosition()), new Point(child.getPosition().x, parent.getPosition().y), false));
        tweenVectorVector.addElement(tweenVector);
        ((SplayTreeDataNode)parent).setLeft(child.getRight());
        child.setRight(parent);
        child.setParent(grandParent);
        return child;
    }

    private SplayTreeDataNode rotateLeft(SplayTreeNode parent)
    {
        if(((SplayTreeDataNode)parent).getRight() instanceof SplayTreeNullNode)
            return (SplayTreeDataNode)parent;
        if(tweenVectorVector == null)
            tweenVectorVector = new Vector();
        Vector tweenVector = new Vector();
        SplayTreeDataNode grandParent = parent.getParent();
        SplayTreeDataNode child = (SplayTreeDataNode)((SplayTreeDataNode)parent).getRight();
        if(grandParent != null)
            if(grandParent.getLeft() == parent)
                tweenVector.addElement(new MoveMoveable(grandParent.getLeftLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            else
                tweenVector.addElement(new MoveMoveable(grandParent.getRightLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
        tweenVector.addElement(new MoveMoveable(((SplayTreeDataNode)parent).getRightLink(), new Point(((SplayTreeDataNode)parent).getRight().getPosition()), new Point(child.getLeft().getPosition()), false));
        tweenVector.addElement(new MoveMoveable(child.getLeftLink(), new Point(child.getLeft().getPosition()), new Point(parent.getPosition()), false));
        tweenVectorVector.addElement(tweenVector);
        boolean temp = isTweenFast;
        animate("$This key doesn't exist###");
        isTweenFast = temp;
        if(tweenVectorVector == null)
            tweenVectorVector = new Vector();
        tweenVector = new Vector();
        tweenVector.addElement(new MoveMoveable(parent, new Point(parent.getPosition()), new Point(parent.getPosition().x, ((SplayTreeDataNode)parent).getRight().getPosition().y), false));
        SplayTreeNode parentLeft = ((SplayTreeDataNode)parent).getLeft();
        tweenVector.addElement(new MoveMoveable(parentLeft, new Point(parentLeft.getPosition()), parentLeft.getPosition(), true));
        tweenVector.addElement(new MoveMoveable(child.getRight(), new Point(child.getRight().getPosition()), new Point(child.getRight().getPosition().x, ((SplayTreeDataNode)parent).getRight().getPosition().y), true));
        tweenVector.addElement(new MoveMoveable(child, new Point(child.getPosition()), new Point(child.getPosition().x, parent.getPosition().y), false));
        tweenVectorVector.addElement(tweenVector);
        ((SplayTreeDataNode)parent).setRight(child.getLeft());
        child.setLeft(parent);
        child.setParent(grandParent);
        return child;
    }

    private SplayTreeNode insert(SplayTreeNode node, SplayTreeDataItem dataItem, Boolean isSetLeft)
    {
        switchPath = null;
        currentNode = node;
        codeStack.push("insertCode");
        setPosition("3");
        if(node == null)
            throw new RuntimeException("insert: Node should never be null");
        SplayTreeDataNode parent = node.getParent();
        setPosition("3.1");
        if(node instanceof SplayTreeNullNode)
        {
            node = new SplayTreeDataNode(dataItem);
            linkEarly(parent, node, isSetLeft);
            currentNode = node;
            codeStack.stepCodeLine();
            setPosition("3.1.1");
            switchPath = null;
            codeStack.pop();
            return node;
        }
        SplayTreeDataNode tree = (SplayTreeDataNode)node;
        if(dataItem.getKey() < tree.getKey())
            switchPath = tree.getLeft();
        else
            switchPath = tree.getRight();
        if(switchPath instanceof SplayTreeDataNode)
            if(dataItem.getKey() < ((SplayTreeDataNode)switchPath).getKey())
                switchPath = ((SplayTreeDataNode)switchPath).getLeft();
            else
                switchPath = ((SplayTreeDataNode)switchPath).getRight();
        setPosition("3.2");
        if((tree.getLeft() instanceof SplayTreeNullNode) && dataItem.getKey() < tree.getKey() || (tree.getRight() instanceof SplayTreeNullNode) && dataItem.getKey() >= tree.getKey())
        {
            codeStack.setCode("terminalCode");
            setPosition("TERMINAL");
            if(dataItem.getKey() < tree.getKey())
            {
                setPosition("TERMINAL.3.1", "TERMINAL.1", false);
                setPosition("TERMINAL.2.1");
                tree.setLeft(new SplayTreeDataNode(dataItem));
                if(!isBackMode)
                    super.repaint();
                currentNode = tree;
                codeStack.stepCodeLine();
                setPosition("TERMINAL.3.2", "TERMINAL.1", false);
                setPosition("TERMINAL.2.2");
                isTweenFast = !isExpanded("TERMINAL");
                tree = rotateRight(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("TERMINAL.3.3", "TERMINAL.1", false);
                setPosition("TERMINAL.2.3");
                algorithmSleep();
                codeStack.pop();
                switchPath = null;
                return tree;
            }
            setPosition("TERMINAL.3");
            setPosition("TERMINAL.3.1");
            tree.setRight(new SplayTreeDataNode(dataItem));
            if(!isBackMode)
                super.repaint();
            currentNode = tree;
            codeStack.stepCodeLine();
            setPosition("TERMINAL.3.2");
            isTweenFast = !isExpanded("TERMINAL");
            tree = rotateLeft(tree);
            currentNode = tree;
            linkEarly(parent, tree, isSetLeft);
            animate("$This key doesn't exist###");
            codeStack.stepCodeLine();
            setPosition("TERMINAL.3.3");
            algorithmSleep();
            codeStack.pop();
            switchPath = null;
            return tree;
        }
        if(dataItem.getKey() < tree.getKey() && dataItem.getKey() < ((SplayTreeDataNode)tree.getLeft()).getKey() || dataItem.getKey() >= tree.getKey() && dataItem.getKey() >= ((SplayTreeDataNode)tree.getRight()).getKey())
        {
            codeStack.setCode("zigZigCode");
            setPosition("ZIGZIG");
            setPosition("ZIGZIG.2");
            if(dataItem.getKey() < tree.getKey())
            {
                SplayTreeDataNode leftChild = (SplayTreeDataNode)tree.getLeft();
                setPosition("ZIGZIG.3.1", "ZIGZIG.1", false);
                setPosition("ZIGZIG.2.1");
                leftChild.setLeft(insert(leftChild.getLeft(), dataItem, new Boolean(true)));
                currentNode = tree;
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.2", "ZIGZIG.1", false);
                setPosition("ZIGZIG.2.2");
                isTweenFast = !isExpanded("ZIGZIG");
                tree = rotateRight(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.3", "ZIGZIG.1", false);
                setPosition("ZIGZIG.2.3");
                isTweenFast = !isExpanded("ZIGZIG");
                tree = rotateRight(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.4", "ZIGZIG.1", false);
                setPosition("ZIGZIG.2.4");
                algorithmSleep();
                codeStack.pop();
                switchPath = null;
                return tree;
            } else
            {
                setPosition("ZIGZIG.3");
                SplayTreeDataNode rightChild = (SplayTreeDataNode)tree.getRight();
                setPosition("ZIGZIG.3.1");
                rightChild.setRight(insert(rightChild.getRight(), dataItem, new Boolean(false)));
                currentNode = tree;
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.2");
                isTweenFast = !isExpanded("ZIGZIG");
                tree = rotateLeft(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.3");
                isTweenFast = !isExpanded("ZIGZIG");
                tree = rotateLeft(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.4");
                algorithmSleep();
                codeStack.pop();
                switchPath = null;
                return tree;
            }
        }
        if(dataItem.getKey() < tree.getKey() && dataItem.getKey() >= ((SplayTreeDataNode)tree.getLeft()).getKey() || dataItem.getKey() >= tree.getKey() && dataItem.getKey() < ((SplayTreeDataNode)tree.getRight()).getKey())
        {
            codeStack.setCode("zigZagCode");
            setPosition("ZIGZAG");
            setPosition("ZIGZAG.2");
            if(dataItem.getKey() < tree.getKey())
            {
                SplayTreeDataNode leftChild = (SplayTreeDataNode)tree.getLeft();
                setPosition("ZIGZAG.3.1", "ZIGZAG.1", false);
                setPosition("ZIGZAG.2.1");
                leftChild.setRight(insert(leftChild.getRight(), dataItem, new Boolean(false)));
                currentNode = tree;
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.2", "ZIGZAG.1", false);
                setPosition("ZIGZAG.2.2");
                isTweenFast = !isExpanded("ZIGZAG");
                tree.setLeft(rotateLeft(leftChild));
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.3", "ZIGZAG.1", false);
                setPosition("ZIGZAG.2.3");
                isTweenFast = !isExpanded("ZIGZAG");
                tree = rotateRight(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.4", "ZIGZAG.1", false);
                setPosition("ZIGZAG.2.4");
                algorithmSleep();
                codeStack.pop();
                switchPath = null;
                return tree;
            } else
            {
                setPosition("ZIGZAG.3");
                SplayTreeDataNode rightChild = (SplayTreeDataNode)tree.getRight();
                setPosition("ZIGZAG.3.1");
                rightChild.setLeft(insert(rightChild.getLeft(), dataItem, new Boolean(true)));
                currentNode = tree;
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.2");
                isTweenFast = !isExpanded("ZIGZAG");
                tree.setRight(rotateRight(rightChild));
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.3");
                isTweenFast = !isExpanded("ZIGZAG");
                tree = rotateLeft(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.4");
                algorithmSleep();
                codeStack.pop();
                switchPath = null;
                return tree;
            }
        } else
        {
            throw new RuntimeException("This case should be unreachable");
        }
    }

    private SplayTreeNode search(SplayTreeNode node, SplayTreeDataItem dataItem, Boolean isSetLeft)
    {
        SplayTreeDataNode.clearNodeColors();
        currentNode = node;
        codeStack.push("searchCode");
        setPosition("5");
        if(node == null)
            throw new RuntimeException("insert: Node should never be null");
        SplayTreeDataNode parent = node.getParent();
        setPosition("5.1");
        if(node instanceof SplayTreeNullNode)
        {
            wasFound = new Boolean(false);
            setPosition("5.1.1");
            codeStack.stepCodeLine();
            setPosition("5.1.2");
            switchPath = null;
            algorithmSleep();
            codeStack.pop();
            return node;
        }
        SplayTreeDataNode tree = (SplayTreeDataNode)node;
        setPosition("Found.1");
        if(tree.getKey() == dataItem.getKey())
        {
            codeStack.setCode("found");
            wasFound = new Boolean(true);
            tree.setIsFound();
            codeStack.stepCodeLine();
            setPosition("Found.1.1");
            codeStack.stepCodeLine();
            setPosition("Found.1.2");
            switchPath = null;
            algorithmSleep();
            codeStack.pop();
            return tree;
        }
        setPosition("Found.2");
        if((tree.getLeft() instanceof SplayTreeDataNode) && dataItem.getKey() == ((SplayTreeDataNode)tree.getLeft()).getKey())
        {
            codeStack.setCode("found rotate");
            codeStack.stepCodeLine();
            setPosition("Found.2.1");
            wasFound = new Boolean(true);
            ((SplayTreeDataNode)tree.getLeft()).setIsFound();
            codeStack.stepCodeLine();
            setPosition("Found.2.2");
            isTweenFast = !isExpanded("Success");
            tree = rotateRight(tree);
            linkEarly(parent, tree, isSetLeft);
            animate("$This key doesn't exist###");
            currentNode = tree;
            switchPath = null;
            codeStack.stepCodeLine();
            setPosition("Found.2.3");
            algorithmSleep();
            codeStack.pop();
            return tree;
        }
        if((tree.getRight() instanceof SplayTreeDataNode) && dataItem.getKey() == ((SplayTreeDataNode)tree.getRight()).getKey())
        {
            codeStack.setCode("found rotate");
            codeStack.stepCodeLine();
            setPosition("Found.2.1");
            wasFound = new Boolean(true);
            ((SplayTreeDataNode)tree.getRight()).setIsFound();
            codeStack.stepCodeLine();
            setPosition("Found.2.2");
            isTweenFast = !isExpanded("Success");
            tree = rotateLeft(tree);
            linkEarly(parent, tree, isSetLeft);
            animate("$This key doesn't exist###");
            currentNode = tree;
            switchPath = null;
            codeStack.stepCodeLine();
            setPosition("Found.2.3");
            algorithmSleep();
            codeStack.pop();
            return tree;
        }
        if(dataItem.getKey() < tree.getKey())
            switchPath = tree.getLeft();
        else
            switchPath = tree.getRight();
        if(switchPath instanceof SplayTreeDataNode)
            if(dataItem.getKey() < ((SplayTreeDataNode)switchPath).getKey())
                switchPath = ((SplayTreeDataNode)switchPath).getLeft();
            else
                switchPath = ((SplayTreeDataNode)switchPath).getRight();
        setPosition("5.2");
        if((tree.getLeft() instanceof SplayTreeNullNode) && dataItem.getKey() < tree.getKey() || (tree.getRight() instanceof SplayTreeNullNode) && dataItem.getKey() > tree.getKey())
        {
            setPosition("TERMINAL");
            wasFound = new Boolean(false);
            setPosition("TERMINAL.1");
            setPosition("TERMINAL.2");
            switchPath = null;
            algorithmSleep();
            codeStack.pop();
            return tree;
        }
        if(dataItem.getKey() < tree.getKey() && dataItem.getKey() < ((SplayTreeDataNode)tree.getLeft()).getKey() || dataItem.getKey() >= tree.getKey() && dataItem.getKey() >= ((SplayTreeDataNode)tree.getRight()).getKey())
        {
            codeStack.setCode("zigZigSearchCode");
            setPosition("ZIGZIG");
            setPosition("ZIGZIG.2");
            if(dataItem.getKey() < tree.getKey())
            {
                SplayTreeDataNode leftChild = (SplayTreeDataNode)tree.getLeft();
                setPosition("ZIGZIG.3.1", "ZIGZIG.1", false);
                setPosition("ZIGZIG.2.1");
                leftChild.setLeft(search(leftChild.getLeft(), dataItem, new Boolean(true)));
                currentNode = tree;
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.2", "ZIGZIG.1", false);
                setPosition("ZIGZIG.2.2");
                isTweenFast = !isExpanded("ZIGZIG");
                tree = rotateRight(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.3", "ZIGZIG.1", false);
                setPosition("ZIGZIG.2.3");
                isTweenFast = !isExpanded("ZIGZIG");
                tree = rotateRight(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.4", "ZIGZIG.1", false);
                setPosition("ZIGZIG.2.4");
                algorithmSleep();
                codeStack.pop();
                return tree;
            } else
            {
                setPosition("ZIGZIG.3");
                SplayTreeDataNode rightChild = (SplayTreeDataNode)tree.getRight();
                setPosition("ZIGZIG.3.1");
                rightChild.setRight(search(rightChild.getRight(), dataItem, new Boolean(false)));
                currentNode = tree;
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.2");
                isTweenFast = !isExpanded("ZIGZIG");
                tree = rotateLeft(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.3");
                isTweenFast = !isExpanded("ZIGZIG");
                tree = rotateLeft(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZIG.3.4");
                algorithmSleep();
                codeStack.pop();
                return tree;
            }
        }
        if(dataItem.getKey() < tree.getKey() && dataItem.getKey() >= ((SplayTreeDataNode)tree.getLeft()).getKey() || dataItem.getKey() >= tree.getKey() && dataItem.getKey() < ((SplayTreeDataNode)tree.getRight()).getKey())
        {
            codeStack.setCode("zigZagSearchCode");
            setPosition("ZIGZAG");
            setPosition("ZIGZAG.2");
            if(dataItem.getKey() < tree.getKey())
            {
                SplayTreeDataNode leftChild = (SplayTreeDataNode)tree.getLeft();
                setPosition("ZIGZAG.3.1", "ZIGZAG.1", false);
                setPosition("ZIGZAG.2.1");
                leftChild.setRight(search(leftChild.getRight(), dataItem, new Boolean(false)));
                currentNode = tree;
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.2", "ZIGZAG.1", false);
                setPosition("ZIGZAG.2.2");
                isTweenFast = !isExpanded("ZIGZAG");
                tree.setLeft(rotateLeft(leftChild));
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.3", "ZIGZAG.1", false);
                setPosition("ZIGZAG.2.3");
                isTweenFast = !isExpanded("ZIGZAG");
                tree = rotateRight(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.4", "ZIGZAG.1", false);
                setPosition("ZIGZAG.2.4");
                algorithmSleep();
                codeStack.pop();
                return tree;
            } else
            {
                setPosition("ZIGZAG.3");
                SplayTreeDataNode rightChild = (SplayTreeDataNode)tree.getRight();
                setPosition("ZIGZAG.3.1");
                rightChild.setLeft(search(rightChild.getLeft(), dataItem, new Boolean(true)));
                currentNode = tree;
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.2");
                isTweenFast = !isExpanded("ZIGZAG");
                tree.setRight(rotateRight(rightChild));
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.3");
                isTweenFast = !isExpanded("ZIGZAG");
                tree = rotateLeft(tree);
                currentNode = tree;
                linkEarly(parent, tree, isSetLeft);
                animate("$This key doesn't exist###");
                codeStack.stepCodeLine();
                setPosition("ZIGZAG.3.4");
                algorithmSleep();
                codeStack.pop();
                return tree;
            }
        } else
        {
            throw new RuntimeException("This case should be unreachable");
        }
    }

    private void linkEarly(SplayTreeDataNode parent, SplayTreeNode node, Boolean isSetLeft)
    {
        if(isSetLeft != null)
        {
            if(isSetLeft.booleanValue())
                parent.setLeft(node);
            else
                parent.setRight(node);
        } else
        {
            head = node;
        }
    }

    private void printTree(SplayTreeNode tree)
    {
        if(tree instanceof SplayTreeDataNode)
        {
            printTree(((SplayTreeDataNode)tree).getLeft());
            System.out.println(((SplayTreeDataNode)tree).getKey());
            printTree(((SplayTreeDataNode)tree).getRight());
        }
    }

    private void setPosition(String key, String condition, boolean isSetOnExpanded)
    {
        if(isExpanded(condition) == isSetOnExpanded)
            setPosition(key);
        else
            setPosition("This key does not exist");
    }

    private boolean isExpanded(String key)
    {
        return ((SplayTreeThread)algorithmThread).getAlgorithmWindow().isExpanded(key);
    }

    public boolean isTweenFast()
    {
        return isTweenFast;
    }

    private void animate(String key)
    {
        setPosition(key);
        if(!isBackMode)
            super.repaint();
        else
            tweenVectorVector = null;
        isTweenFast = false;
    }

    private void clearPointers()
    {
        currentNode = null;
    }

    public void controlStep(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlBack(ControlEvent e)
    {
        isBackMode = true;
    }

    public void controlPause(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlReset(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlRestart(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlRun(ControlEvent e)
    {
        isBackMode = false;
    }

    public void controlOther(ControlEvent e)
    {
        isBackMode = false;
    }

    private void algorithmSleep()
    {
        if(!isBackMode)
        {
            super.repaint();
            try
            {
                AlgorithmThread _tmp = algorithmThread;
                AlgorithmThread.sleep(((SplayTreeThread)algorithmThread).getRunSleepDuration() / 3);
            }
            catch(Exception e) { }
        }
    }

    private static final String BUILD_MODE_LABEL = Messages.getString("SplayTree.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("SplayTree.1");
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private SplayTreeNode currentNode;
    private SplayTreeNode switchPath;
    private Vector tweenVectorVector;
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

}
