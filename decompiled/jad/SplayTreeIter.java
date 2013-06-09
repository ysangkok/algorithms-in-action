// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SplayTreeIter.java

import com.cim.AIA.*;
import java.awt.Point;
import java.io.PrintStream;
import java.util.*;
import localization.Messages;

public class SplayTreeIter extends Algorithm
    implements MethodSelectionListener, ControlListener
{
    private class MoveMoveable
    {

        private Point from;
        private Point to;
        private Moveable moveable;
        private boolean isEntire;
        final SplayTreeIter this$0;





        protected MoveMoveable(Moveable moveable, Point from, Point to, boolean isEntire)
        {
            this$0 = SplayTreeIter.this;
            super();
            this.moveable = moveable;
            this.from = from;
            this.to = to;
            this.isEntire = isEntire;
        }
    }


    public SplayTreeIter(AlgorithmThread algorithmThread, Object data)
    {
        super(algorithmThread, data);
        isBackMode = false;
        currentLevel = -1;
        noMoveTween = false;
        drawSwitch = false;
        drawParentSwitch = false;
        drawPointers = true;
        head = new SplayTreeIterNullNode();
        savedTree = new SplayTreeIterNullNode();
        executingMode = 11;
        nextMode = executingMode;
        changeData(data);
        insertData = newInsertData;
        searchData = newSearchData;
        codeStack = new SplayTreeIterCodeStack();
    }

    public void reInitialise(Object data)
    {
        noMoveTween = true;
        drawPointers = true;
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
            savedTree = new SplayTreeIterNullNode();
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
                ((Node)insertData.getElement(i)).setBackgroundColor(SplayTreeIterColors.INSERT_ACTIVE_COLOR);

        }
        if(searchData != null)
        {
            for(int i = 0; i < searchData.getSize(); i++)
            {
                ((Node)searchData.getElement(i)).setBackgroundColor(SplayTreeIterColors.SEARCH_ACTIVE_COLOR);
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
                ((Node)newInsertData.getElement(i)).setBackgroundColor(SplayTreeIterColors.INSERT_ACTIVE_COLOR);
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
            ((Node)newSearchData.getElement(i)).setBackgroundColor(SplayTreeIterColors.SEARCH_ACTIVE_COLOR);
        }

    }

    private void save()
    {
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
        head = new SplayTreeIterNullNode();
    }

    protected void initialiseMethods(String string, MethodSelectable methodSelectable)
    {
        MethodSelection methodSelection1 = new MethodSelection(BUILD_MODE_LABEL, string, Messages.getString("SplayTreeIter.2"), "3a", getLogger(), getBreakPoint());
        MethodSelection methodSelection2 = new MethodSelection(SEARCH_MODE_LABEL, string, Messages.getString("SplayTreeIter.4"), "5a", getLogger(), getBreakPoint());
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

    public SplayTreeIterCodeStack getCodeStack()
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
        if(isBackMode)
            return null;
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

    public SplayTreeIterNode getHead()
    {
        unHighlightPath(head);
        highlightSwitchPath(switchPath);
        highlightPath(currentNode);
        if(drawSwitch && currentNode != null)
        {
            currentNode.setIsOnPath(false);
            currentNode.setIsOnSwitchPath(true);
        }
        if(drawParentSwitch && parentNode != null)
        {
            parentNode.setIsOnPath(false);
            parentNode.setIsOnSwitchPath(true);
        }
        return head;
    }

    public void highlightSwitchPath(SplayTreeIterNode node)
    {
        if(node != null)
        {
            node.setIsOnSwitchPath(true);
            highlightSwitchPath(((SplayTreeIterNode) (node.getParent())));
        }
    }

    public void highlightPath(SplayTreeIterNode node)
    {
        if(node != null)
        {
            node.setIsOnPath(true);
            highlightPath(((SplayTreeIterNode) (node.getParent())));
        }
    }

    private void unHighlightPath(SplayTreeIterNode node)
    {
        if(node != null)
        {
            node.setIsOnPath(false);
            node.setIsOnSwitchPath(false);
            if(node instanceof SplayTreeIterDataNode)
            {
                unHighlightPath(((SplayTreeIterDataNode)node).getLeft());
                unHighlightPath(((SplayTreeIterDataNode)node).getRight());
            }
        }
    }

    public SplayTreeIterNode getCurrentNode()
    {
        if(!drawPointers)
            return null;
        else
            return currentNode;
    }

    public SplayTreeIterNode getParentNode()
    {
        if(!drawPointers)
            return null;
        else
            return parentNode;
    }

    public int getCurrentLevel()
    {
        return currentLevel;
    }

    public boolean getNoMoveTween()
    {
        return noMoveTween;
    }

    protected void run(boolean canChangeMode)
    {
        if(canChangeMode)
        {
            executingMode = nextMode;
            SplayTreeIterDataNode.clearNodeColors();
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
                startingTree = new SplayTreeIterNullNode();
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
                SplayTreeIterDataItem dataItem = new SplayTreeIterDataItem(insertData.getElement(i).getObject().toString(), ((Integer)insertData.getElement(i).getObject()).intValue());
                if(enabled)
                {
                    ((Node)insertData.getElement(i)).setBackgroundColor(SplayTreeIterColors.INSERT_HIGHLIGHT_COLOR);
                    currentNode = head;
                    savedTree = head.getCopy();
                    head = insert(head, dataItem, null);
                    currentLevel = -1;
                    parentNode = null;
                    currentNode = null;
                    ((Node)insertData.getElement(i)).setBackgroundColor(SplayTreeIterColors.INSERT_DONE_COLOR);
                    if(enabled)
                        insertPos++;
                    else
                        head = savedTree.getCopy();
                }
                clearPointers();
            }

            break;

        case 12: // '\f'
            if(head instanceof SplayTreeIterNullNode)
                break;
            int i = 0;
            do
            {
                if(i >= searchData.getSize())
                    break label0;
                Node searchNode = (Node)searchData.getElement(i);
                SplayTreeIterDataItem dataItem = new SplayTreeIterDataItem(searchNode.getObject().toString(), ((Integer)searchNode.getObject()).intValue());
                if(enabled)
                {
                    searchNode.setBackgroundColor(SplayTreeIterColors.SEARCH_HIGHLIGHT_COLOR);
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
                    setPosition("5.4");
                    searchNode.setBackgroundColor(SplayTreeIterColors.SEARCH_DONE_COLOR);
                }
                clearPointers();
                i++;
            } while(true);
        }
    }

    private SplayTreeIterDataNode rotateRight(SplayTreeIterNode parent, boolean animate)
    {
        if(((SplayTreeIterDataNode)parent).getLeft() instanceof SplayTreeIterNullNode)
            return (SplayTreeIterDataNode)parent;
        if(tweenVectorVector == null)
            tweenVectorVector = new Vector();
        Vector tweenVector = new Vector();
        SplayTreeIterDataNode grandParent = parent.getParent();
        SplayTreeIterDataNode child = (SplayTreeIterDataNode)((SplayTreeIterDataNode)parent).getLeft();
        if(grandParent != null)
            if(grandParent.getLeft() == parent)
                tweenVector.addElement(new MoveMoveable(grandParent.getLeftLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            else
                tweenVector.addElement(new MoveMoveable(grandParent.getRightLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
        tweenVector.addElement(new MoveMoveable(((SplayTreeIterDataNode)parent).getLeftLink(), new Point(((SplayTreeIterDataNode)parent).getLeft().getPosition()), new Point(child.getRight().getPosition()), false));
        tweenVector.addElement(new MoveMoveable(child.getRightLink(), new Point(child.getRight().getPosition()), new Point(parent.getPosition()), false));
        tweenVectorVector.addElement(tweenVector);
        if(!animate)
            tweenVectorVector = new Vector();
        boolean temp = isTweenFast;
        animate("$This key doesn't exist###");
        isTweenFast = temp;
        if(tweenVectorVector == null)
            tweenVectorVector = new Vector();
        tweenVector = new Vector();
        tweenVector.addElement(new MoveMoveable(parent, new Point(parent.getPosition()), new Point(parent.getPosition().x, ((SplayTreeIterDataNode)parent).getLeft().getPosition().y), false));
        SplayTreeIterNode parentRight = ((SplayTreeIterDataNode)parent).getRight();
        tweenVector.addElement(new MoveMoveable(parentRight, new Point(parentRight.getPosition()), parentRight.getPosition(), true));
        tweenVector.addElement(new MoveMoveable(child.getLeft(), new Point(child.getLeft().getPosition()), new Point(child.getLeft().getPosition().x, ((SplayTreeIterDataNode)parent).getLeft().getPosition().y), true));
        tweenVector.addElement(new MoveMoveable(child, new Point(child.getPosition()), new Point(child.getPosition().x, parent.getPosition().y), false));
        tweenVectorVector.addElement(tweenVector);
        if(!animate)
            tweenVectorVector = new Vector();
        ((SplayTreeIterDataNode)parent).setLeft(child.getRight());
        child.setRight(parent);
        child.setParent(grandParent);
        if(grandParent != null)
            if(grandParent.getLeft() == parent)
                grandParent.setLeft(child);
            else
            if(grandParent.getRight() == parent)
                grandParent.setRight(child);
        return child;
    }

    private SplayTreeIterDataNode rotateLeft(SplayTreeIterNode parent, boolean animate)
    {
        if(((SplayTreeIterDataNode)parent).getRight() instanceof SplayTreeIterNullNode)
            return (SplayTreeIterDataNode)parent;
        if(tweenVectorVector == null)
            tweenVectorVector = new Vector();
        Vector tweenVector = new Vector();
        SplayTreeIterDataNode grandParent = parent.getParent();
        SplayTreeIterDataNode child = (SplayTreeIterDataNode)((SplayTreeIterDataNode)parent).getRight();
        if(grandParent != null)
            if(grandParent.getLeft() == parent)
                tweenVector.addElement(new MoveMoveable(grandParent.getLeftLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
            else
                tweenVector.addElement(new MoveMoveable(grandParent.getRightLink(), new Point(parent.getPosition()), new Point(child.getPosition()), false));
        tweenVector.addElement(new MoveMoveable(((SplayTreeIterDataNode)parent).getRightLink(), new Point(((SplayTreeIterDataNode)parent).getRight().getPosition()), new Point(child.getLeft().getPosition()), false));
        tweenVector.addElement(new MoveMoveable(child.getLeftLink(), new Point(child.getLeft().getPosition()), new Point(parent.getPosition()), false));
        tweenVectorVector.addElement(tweenVector);
        if(!animate)
            tweenVectorVector = new Vector();
        boolean temp = isTweenFast;
        animate("$This key doesn't exist###");
        isTweenFast = temp;
        if(tweenVectorVector == null)
            tweenVectorVector = new Vector();
        tweenVector = new Vector();
        tweenVector.addElement(new MoveMoveable(parent, new Point(parent.getPosition()), new Point(parent.getPosition().x, ((SplayTreeIterDataNode)parent).getRight().getPosition().y), false));
        SplayTreeIterNode parentLeft = ((SplayTreeIterDataNode)parent).getLeft();
        tweenVector.addElement(new MoveMoveable(parentLeft, new Point(parentLeft.getPosition()), parentLeft.getPosition(), true));
        tweenVector.addElement(new MoveMoveable(child.getRight(), new Point(child.getRight().getPosition()), new Point(child.getRight().getPosition().x, ((SplayTreeIterDataNode)parent).getRight().getPosition().y), true));
        tweenVector.addElement(new MoveMoveable(child, new Point(child.getPosition()), new Point(child.getPosition().x, parent.getPosition().y), false));
        tweenVectorVector.addElement(tweenVector);
        if(!animate)
            tweenVectorVector = new Vector();
        ((SplayTreeIterDataNode)parent).setRight(child.getLeft());
        child.setLeft(parent);
        child.setParent(grandParent);
        if(grandParent != null)
            if(grandParent.getLeft() == parent)
                grandParent.setLeft(child);
            else
            if(grandParent.getRight() == parent)
                grandParent.setRight(child);
        return child;
    }

    private SplayTreeIterNode insert(SplayTreeIterNode node, SplayTreeIterDataItem dataItem, Boolean isSetLeft)
    {
        switchPath = null;
        setPosition("3");
        if(node == null)
            throw new RuntimeException("insert: Node should never be null");
        SplayTreeIterDataNode parent = node.getParent();
        setPosition("3.1");
        if(node instanceof SplayTreeIterNullNode)
        {
            node = new SplayTreeIterDataNode(dataItem);
            linkEarly(parent, node, isSetLeft);
            currentNode = node;
            setPosition("3.1.1");
            switchPath = null;
            return node;
        }
        currentNode = node;
        setPosition("3.2.1.1");
        noMoveTween = false;
        while(!(currentNode instanceof SplayTreeIterNullNode)) 
        {
            setPosition("3.2.2.1");
            parentNode = currentNode;
            setPosition("3.2.2.1.1");
            setPosition("3.2.2.1.2");
            if(dataItem.getKey() < ((SplayTreeIterDataNode)currentNode).getKey())
            {
                currentNode = ((SplayTreeIterDataNode)(SplayTreeIterDataNode)currentNode).getLeft();
                setPosition("3.2.2.1.3");
            } else
            {
                setPosition("3.2.2.1.4");
                currentNode = ((SplayTreeIterDataNode)(SplayTreeIterDataNode)currentNode).getRight();
                setPosition("3.2.2.1.5");
            }
        }
        setPosition("3.2.2.1");
        setPosition("3.2.2.2");
        noMoveTween = true;
        setPosition("3.2.3.1");
        setPosition("3.2.3.2");
        SplayTreeIterDataNode newNode = new SplayTreeIterDataNode(dataItem);
        if(dataItem.getKey() < ((SplayTreeIterDataNode)parentNode).getKey())
        {
            ((SplayTreeIterDataNode)parentNode).setLeft(newNode);
            setPosition("3.2.3.3");
        } else
        {
            setPosition("3.2.3.4");
            ((SplayTreeIterDataNode)parentNode).setRight(newNode);
            setPosition("3.2.3.5");
        }
        currentNode = newNode;
        setPosition("3.2.3.6");
        noMoveTween = false;
        do
        {
            if(currentNode == head || parentNode == head)
                break;
            drawSwitch = true;
            drawParentSwitch = true;
            if(!isExpanded("3.3.1") || !isExpanded("3.3"))
                drawPointers = false;
            else
                drawPointers = true;
            setPosition("3.3.1.1");
            if(parentNode.getParent().getLeft() == parentNode && ((SplayTreeIterDataNode)parentNode).getLeft() == currentNode)
            {
                setPosition("3.3.1.1.1");
                if(!isExpanded("3.3.1") || !isExpanded("3.3.1.1.1") || !isExpanded("3.3"))
                    parentNode = rotateRight(parentNode.getParent(), false);
                else
                    parentNode = rotateRight(parentNode.getParent(), true);
                if(parentNode.getParent() == null)
                    head = parentNode;
                setPosition("3.3.1.1.2");
                if(!isExpanded("3.3.1") || !isExpanded("3.3.1.1.1") || !isExpanded("3.3"))
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                if(parentNode == null)
                    head = currentNode;
                setPosition("3.3.1.1.3");
            } else
            if(parentNode.getParent().getRight() == parentNode && ((SplayTreeIterDataNode)parentNode).getRight() == currentNode)
            {
                setPosition("3.3.1.1.1");
                if(!isExpanded("3.3.1") || !isExpanded("3.3.1.1.1") || !isExpanded("3.3"))
                    parentNode = rotateLeft(parentNode.getParent(), false);
                else
                    parentNode = rotateLeft(parentNode.getParent(), true);
                if(parentNode.getParent() == null)
                    head = parentNode;
                setPosition("3.3.1.1.2");
                if(!isExpanded("3.3.1") || !isExpanded("3.3.1.1.1") || !isExpanded("3.3"))
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                if(parentNode == null)
                    head = currentNode;
                setPosition("3.3.1.1.3");
            } else
            if(parentNode.getParent().getLeft() == parentNode && ((SplayTreeIterDataNode)parentNode).getRight() == currentNode)
            {
                setPosition("3.3.1.1.1");
                setPosition("3.3.1.1.4");
                if(!isExpanded("3.3.1") || !isExpanded("3.3.1.1.4") || !isExpanded("3.3"))
                    currentNode = rotateLeft((SplayTreeIterDataNode)(SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateLeft((SplayTreeIterDataNode)(SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                setPosition("3.3.1.1.5");
                if(!isExpanded("3.3.1") || !isExpanded("3.3.1.1.4") || !isExpanded("3.3"))
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                if(parentNode == null)
                    head = currentNode;
                setPosition("3.3.1.1.6");
            } else
            if(parentNode.getParent().getRight() == parentNode && ((SplayTreeIterDataNode)parentNode).getLeft() == currentNode)
            {
                setPosition("3.3.1.1.1");
                setPosition("3.3.1.1.4");
                if(!isExpanded("3.3.1") || !isExpanded("3.3.1.1.4") || !isExpanded("3.3"))
                    currentNode = rotateRight((SplayTreeIterDataNode)(SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateRight((SplayTreeIterDataNode)(SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                setPosition("3.3.1.1.5");
                if(!isExpanded("3.3.1") || !isExpanded("3.3.1.1.4") || !isExpanded("3.3"))
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                if(parentNode == null)
                    head = currentNode;
                setPosition("3.3.1.1.6");
            }
        } while(true);
        drawPointers = true;
        drawSwitch = false;
        drawParentSwitch = false;
        setPosition("3.3.1.1");
        setPosition("3.3.1.2");
        drawSwitch = true;
        setPosition("3.3.2.1");
        if(currentNode != head)
        {
            if(!isExpanded("3.3"))
                drawPointers = false;
            else
                drawPointers = true;
            if(currentNode == ((SplayTreeIterDataNode)parentNode).getRight())
            {
                currentNode = parentNode;
                if(!isExpanded("3.3"))
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                head = currentNode;
            } else
            {
                currentNode = parentNode;
                if(!isExpanded("3.3"))
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                head = currentNode;
            }
            setPosition("3.3.2.1.1");
        }
        drawPointers = true;
        drawSwitch = false;
        setPosition("3.3.2.2");
        setPosition("3.END");
        return currentNode;
    }

    private SplayTreeIterNode search(SplayTreeIterNode node, SplayTreeIterDataItem dataItem, Boolean isSetLeft)
    {
        SplayTreeIterDataNode.clearNodeColors();
        currentNode = node;
        setPosition("5.1.1");
        boolean returnState = false;
        setPosition("5.1.2");
        noMoveTween = false;
        if(node == null)
            throw new RuntimeException("insert: Node should never be null");
        do
        {
            if(currentNode instanceof SplayTreeIterNullNode)
                break;
            setPosition("5.2.1");
            setPosition("5.2.1.2");
            if(dataItem.getKey() == ((SplayTreeIterDataNode)currentNode).getKey())
            {
                returnState = true;
                setPosition("5.2.1.2.1");
                setPosition("5.2.1.2.2");
                break;
            }
            parentNode = currentNode;
            setPosition("5.2.1.1");
            setPosition("5.2.1.3");
            if(dataItem.getKey() < ((SplayTreeIterDataNode)currentNode).getKey())
            {
                currentNode = ((SplayTreeIterDataNode)(SplayTreeIterDataNode)currentNode).getLeft();
                setPosition("5.2.1.3.1");
            } else
            {
                setPosition("5.2.1.4");
                currentNode = ((SplayTreeIterDataNode)(SplayTreeIterDataNode)currentNode).getRight();
                setPosition("5.2.1.4.1");
            }
        } while(true);
        setPosition("5.2.1");
        setPosition("5.2.2");
        setPosition("5.2.3.1");
        if(currentNode instanceof SplayTreeIterNullNode)
        {
            currentNode = parentNode;
            setPosition("5.2.3.2");
            parentNode = parentNode.getParent();
            setPosition("5.2.3.3");
        }
        setPosition("5.2.4");
        do
        {
            if(currentNode == head || parentNode == head)
                break;
            drawSwitch = true;
            drawParentSwitch = true;
            setPosition("5.3.1.1");
            if(parentNode.getParent().getLeft() == parentNode && ((SplayTreeIterDataNode)parentNode).getLeft() == currentNode)
            {
                setPosition("5.3.1.1.1");
                if(!isExpanded("5.3.1") || !isExpanded("5.3.1.1.1") || !isExpanded("5.3"))
                    parentNode = rotateRight(parentNode.getParent(), false);
                else
                    parentNode = rotateRight(parentNode.getParent(), true);
                if(parentNode.getParent() == null)
                    head = parentNode;
                setPosition("5.3.1.1.2");
                if(!isExpanded("5.3.1") || !isExpanded("5.3.1.1.1") || !isExpanded("5.3"))
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                if(parentNode == null)
                    head = currentNode;
                setPosition("5.3.1.1.3");
            } else
            if(parentNode.getParent().getRight() == parentNode && ((SplayTreeIterDataNode)parentNode).getRight() == currentNode)
            {
                setPosition("5.3.1.1.1");
                if(!isExpanded("5.3.1") || !isExpanded("5.3.1.1.1") || !isExpanded("5.3"))
                    parentNode = rotateLeft(parentNode.getParent(), false);
                else
                    parentNode = rotateLeft(parentNode.getParent(), true);
                if(parentNode.getParent() == null)
                    head = parentNode;
                setPosition("5.3.1.1.2");
                if(!isExpanded("5.3.1") || !isExpanded("5.3.1.1.1") || !isExpanded("5.3"))
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                if(parentNode == null)
                    head = currentNode;
                setPosition("5.3.1.1.3");
            } else
            if(parentNode.getParent().getLeft() == parentNode && ((SplayTreeIterDataNode)parentNode).getRight() == currentNode)
            {
                setPosition("5.3.1.1.1");
                setPosition("5.3.1.1.4");
                if(!isExpanded("5.3.1") || !isExpanded("5.3.1.1.4") || !isExpanded("5.3"))
                    currentNode = rotateLeft((SplayTreeIterDataNode)(SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateLeft((SplayTreeIterDataNode)(SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                setPosition("5.3.1.1.5");
                if(!isExpanded("5.3.1") || !isExpanded("5.3.1.1.4") || !isExpanded("5.3"))
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                if(parentNode == null)
                    head = currentNode;
                setPosition("5.3.1.1.6");
            } else
            if(parentNode.getParent().getRight() == parentNode && ((SplayTreeIterDataNode)parentNode).getLeft() == currentNode)
            {
                setPosition("5.3.1.1.1");
                setPosition("5.3.1.1.4");
                if(!isExpanded("5.3.1") || !isExpanded("5.3.1.1.4") || !isExpanded("5.3"))
                    currentNode = rotateRight((SplayTreeIterDataNode)(SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateRight((SplayTreeIterDataNode)(SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                setPosition("5.3.1.1.5");
                if(!isExpanded("5.3.1") || !isExpanded("5.3.1.1.4") || !isExpanded("5.3"))
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                if(parentNode == null)
                    head = currentNode;
                setPosition("5.3.1.1.6");
            }
        } while(true);
        drawSwitch = false;
        drawParentSwitch = false;
        setPosition("5.3.1.1");
        setPosition("5.3.1.2");
        drawSwitch = true;
        setPosition("5.3.2.1");
        if(currentNode != head)
        {
            if(currentNode == ((SplayTreeIterDataNode)parentNode).getRight())
            {
                currentNode = parentNode;
                if(!isExpanded("5.3"))
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateLeft((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                head = currentNode;
            } else
            {
                currentNode = parentNode;
                if(!isExpanded("5.3"))
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, false);
                else
                    currentNode = rotateRight((SplayTreeIterDataNode)parentNode, true);
                parentNode = currentNode.getParent();
                head = currentNode;
            }
            setPosition("5.3.2.1.1");
        }
        drawSwitch = false;
        setPosition("5.3.2.2");
        wasFound = new Boolean(returnState);
        return currentNode;
    }

    private void linkEarly(SplayTreeIterDataNode parent, SplayTreeIterNode node, Boolean isSetLeft)
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

    private void printTree(SplayTreeIterNode tree)
    {
        if(tree instanceof SplayTreeIterDataNode)
        {
            printTree(((SplayTreeIterDataNode)tree).getLeft());
            System.out.println(((SplayTreeIterDataNode)tree).getKey());
            printTree(((SplayTreeIterDataNode)tree).getRight());
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
        return ((SplayTreeIterThread)algorithmThread).getAlgorithmWindow().isExpanded(key);
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
        parentNode = null;
        currentLevel = -1;
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
                AlgorithmThread.sleep(((SplayTreeIterThread)algorithmThread).getRunSleepDuration() / 3);
            }
            catch(Exception e) { }
        }
    }

    private static final String BUILD_MODE_LABEL = Messages.getString("SplayTreeIter.0");
    private static final String SEARCH_MODE_LABEL = Messages.getString("SplayTreeIter.1");
    private static final int BUILD_MODE = 11;
    private static final int SEARCH_MODE = 12;
    private int nextMode;
    private int executingMode;
    private static final int DEFAULT_ELEMENT_WIDTH = 20;
    private SplayTreeIterNode currentNode;
    private SplayTreeIterNode parentNode;
    private SplayTreeIterNode switchPath;
    private Vector tweenVectorVector;
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

}
