// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RotateAnimation.java

import com.cim.AIA.AlgorithmThread;
import java.util.Vector;

public class RotateAnimation extends Thread
{

    public RotateAnimation()
    {
        highLightedLine = 0;
        threadEnabled = false;
        doRightRotate = false;
        runSleepDuration = 3000;
    }

    private void doRotateLeft()
    {
        algorithm.setRemoveSaveLine(false);
        highLightedLine = 1;
        paintAndWaitShort();
        RedBlack.ptrP = h.getPositionInserted();
        paintAndWait();
        highLightedLine = 3;
        paintAndWaitShort();
        RedBlack.ptrC = h.rightChild.getPositionInserted();
        paintAndWait();
        highLightedLine = 4;
        paintAndWaitShort();
        if(h.rightChild.leftChild != null)
            lineMoveRequests.addElement(new LineMoveRequest(h.rightChild.positionInserted, h.rightChild.leftChild.positionInserted));
        else
            lineMoveRequests.addElement(new LineMoveRequest(h.rightChild.positionInserted, -50));
        h.rightChild.setDrawParentLine(false);
        paintAndWait();
        lineMoveRequests.removeAllElements();
        highLightedLine = 5;
        paintAndWaitShort();
        if(h.rightChild.leftChild != null)
        {
            lineMoveRequests.addElement(new LineMoveRequest(h.rightChild.leftChild.positionInserted, h.positionInserted));
            h.rightChild.leftChild.setDrawParentLine(false);
        } else
        {
            lineMoveRequests.addElement(new LineMoveRequest(-50, h.positionInserted, h.rightChild.positionInserted));
        }
        paintAndWait();
        lineMoveRequests.removeAllElements();
        highLightedLine = 6;
        paintAndWaitShort();
        if(findNodeParent != null)
        {
            lineMoveRequests.addElement(new LineMoveRequest(h.positionInserted, h.rightChild.positionInserted));
            h.setDrawParentLine(false);
        }
        paintAndWait();
        lineMoveRequests.removeAllElements();
        algorithm.setRemoveSaveLine(true);
        algorithm.drawAllParentLines(tuteTree);
        x = h.rightChild;
        h.rightChild = x.leftChild;
        x.leftChild = h;
        if(findNodeParent == null)
            tuteTree = x;
        else
        if(findNodeParent.leftChild != null && findNodeParent.leftChild == h)
            findNodeParent.leftChild = x;
        else
        if(findNodeParent.rightChild != null && findNodeParent.rightChild == h)
            findNodeParent.rightChild = x;
        algorithm.setTuteTree(tuteTree);
        paintAndWait();
        RedBlack.ptrP = -1;
        RedBlack.ptrC = -1;
        algorithm.resetAllHighLightParent(tuteTree);
        paintAndWait(10);
    }

    private void doRotateRight()
    {
        algorithm.setRemoveSaveLine(false);
        highLightedLine = 8;
        paintAndWaitShort();
        RedBlack.ptrP = h.getPositionInserted();
        paintAndWait();
        highLightedLine = 10;
        paintAndWaitShort();
        RedBlack.ptrC = h.leftChild.getPositionInserted();
        paintAndWait();
        highLightedLine = 11;
        paintAndWaitShort();
        if(h.leftChild.rightChild != null)
            lineMoveRequests.addElement(new LineMoveRequest(h.leftChild.positionInserted, h.leftChild.rightChild.positionInserted));
        else
            lineMoveRequests.addElement(new LineMoveRequest(h.leftChild.positionInserted, -51));
        h.leftChild.setDrawParentLine(false);
        paintAndWait();
        lineMoveRequests.removeAllElements();
        highLightedLine = 12;
        paintAndWaitShort();
        if(h.leftChild.rightChild != null)
        {
            lineMoveRequests.addElement(new LineMoveRequest(h.leftChild.rightChild.positionInserted, h.positionInserted));
            h.leftChild.rightChild.setDrawParentLine(false);
        } else
        {
            lineMoveRequests.addElement(new LineMoveRequest(-51, h.positionInserted, h.leftChild.positionInserted));
        }
        paintAndWait();
        lineMoveRequests.removeAllElements();
        highLightedLine = 13;
        paintAndWaitShort();
        if(findNodeParent != null)
        {
            lineMoveRequests.addElement(new LineMoveRequest(h.positionInserted, h.leftChild.positionInserted));
            h.setDrawParentLine(false);
        }
        paintAndWait();
        lineMoveRequests.removeAllElements();
        algorithm.setRemoveSaveLine(true);
        algorithm.drawAllParentLines(tuteTree);
        x = h.leftChild;
        h.leftChild = x.rightChild;
        x.rightChild = h;
        if(findNodeParent == null)
            tuteTree = x;
        else
        if(findNodeParent.leftChild != null && findNodeParent.leftChild == h)
            findNodeParent.leftChild = x;
        else
        if(findNodeParent.rightChild != null && findNodeParent.rightChild == h)
            findNodeParent.rightChild = x;
        algorithm.setTuteTree(tuteTree);
        paintAndWait();
        RedBlack.ptrP = -1;
        RedBlack.ptrC = -1;
        algorithm.resetAllHighLightParent(tuteTree);
        paintAndWait(10);
    }

    public int getHighLight()
    {
        return highLightedLine;
    }

    public void initialise(RedBlackTreeThread theThread, RedBlackTree algorithm, Vector lineMoveRequests, RedBlack tuteTree, RedBlack findNodePtr, RedBlack findNodeParent)
    {
        this.theThread = theThread;
        this.algorithm = algorithm;
        this.lineMoveRequests = lineMoveRequests;
        this.tuteTree = tuteTree;
        this.findNodePtr = findNodePtr;
        this.findNodeParent = findNodeParent;
    }

    public void paintAndWait()
    {
        theThread.setDisplayMode(true);
        while(AlgorithmThread.displayMode) 
            try
            {
                sleep(runSleepDuration);
            }
            catch(Exception e) { }
    }

    public void paintAndWait(int i)
    {
        theThread.setDisplayMode(true);
        while(AlgorithmThread.displayMode) 
            try
            {
                sleep(i);
            }
            catch(Exception e) { }
    }

    public void paintAndWaitShort()
    {
        theThread.setDisplayMode(true);
        while(AlgorithmThread.displayMode) 
            try
            {
                sleep(runSleepDuration / 3);
            }
            catch(Exception e) { }
    }

    public void rotateLeft()
    {
        doRightRotate = false;
    }

    public void rotateRight()
    {
        doRightRotate = true;
    }

    public void run()
    {
        do
        {
            while(threadEnabled) 
            {
                h = findNodePtr;
                if(doRightRotate)
                    doRotateRight();
                else
                    doRotateLeft();
                setEnabled(false);
            }
            try
            {
                sleep(10L);
            }
            catch(Exception e) { }
        } while(true);
    }

    public synchronized void setDuration(int duration)
    {
        if(duration > 0)
            runSleepDuration = duration;
        else
            runSleepDuration = 1;
    }

    public void setEnabled(boolean state)
    {
        threadEnabled = state;
    }

    private static final int SPEEDFACTOR = 3;
    private RedBlackTree algorithm;
    public Vector lineMoveRequests;
    private int highLightedLine;
    private RedBlack tuteTree;
    private RedBlack findNodePtr;
    private RedBlack findNodeParent;
    private RedBlackTreeThread theThread;
    private boolean threadEnabled;
    private boolean doRightRotate;
    private RedBlack h;
    private RedBlack x;
    private int runSleepDuration;
}
