import com.cim.AIA.*;
import java.util.*;

public class RotateAnimation extends Thread
{
    private static final int SPEEDFACTOR = 3;
    private RedBlackTree algorithm;
    public Vector<LineMoveRequest> lineMoveRequests;
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
    
    public RotateAnimation() {
        super();
        this.highLightedLine = 0;
        this.threadEnabled = false;
        this.doRightRotate = false;
        this.runSleepDuration = 3000;
    }
    
    private void doRotateLeft() {
        this.algorithm.setRemoveSaveLine(false);
        this.highLightedLine = 1;
        this.paintAndWaitShort();
        RedBlack.ptrP = this.h.getPositionInserted();
        this.paintAndWait();
        this.highLightedLine = 3;
        this.paintAndWaitShort();
        RedBlack.ptrC = this.h.rightChild.getPositionInserted();
        this.paintAndWait();
        this.highLightedLine = 4;
        this.paintAndWaitShort();
        if (this.h.rightChild.leftChild != null) {
            this.lineMoveRequests.addElement(new LineMoveRequest(this.h.rightChild.positionInserted, this.h.rightChild.leftChild.positionInserted));
        }
        else {
            this.lineMoveRequests.addElement(new LineMoveRequest(this.h.rightChild.positionInserted, -50));
        }
        this.h.rightChild.setDrawParentLine(false);
        this.paintAndWait();
        this.lineMoveRequests.removeAllElements();
        this.highLightedLine = 5;
        this.paintAndWaitShort();
        if (this.h.rightChild.leftChild != null) {
            this.lineMoveRequests.addElement(new LineMoveRequest(this.h.rightChild.leftChild.positionInserted, this.h.positionInserted));
            this.h.rightChild.leftChild.setDrawParentLine(false);
        }
        else {
            this.lineMoveRequests.addElement(new LineMoveRequest(-50, this.h.positionInserted, this.h.rightChild.positionInserted));
        }
        this.paintAndWait();
        this.lineMoveRequests.removeAllElements();
        this.highLightedLine = 6;
        this.paintAndWaitShort();
        if (this.findNodeParent != null) {
            this.lineMoveRequests.addElement(new LineMoveRequest(this.h.positionInserted, this.h.rightChild.positionInserted));
            this.h.setDrawParentLine(false);
        }
        this.paintAndWait();
        this.lineMoveRequests.removeAllElements();
        this.algorithm.setRemoveSaveLine(true);
        this.algorithm.drawAllParentLines(this.tuteTree);
        this.x = this.h.rightChild;
        this.h.rightChild = this.x.leftChild;
        this.x.leftChild = this.h;
        if (this.findNodeParent == null) {
            this.tuteTree = this.x;
        }
        else if (this.findNodeParent.leftChild != null && this.findNodeParent.leftChild == this.h) {
            this.findNodeParent.leftChild = this.x;
        }
        else if (this.findNodeParent.rightChild != null && this.findNodeParent.rightChild == this.h) {
            this.findNodeParent.rightChild = this.x;
        }
        this.algorithm.setTuteTree(this.tuteTree);
        this.paintAndWait();
        RedBlack.ptrP = -1;
        RedBlack.ptrC = -1;
        this.algorithm.resetAllHighLightParent(this.tuteTree);
        this.paintAndWait(10);
    }
    
    private void doRotateRight() {
        this.algorithm.setRemoveSaveLine(false);
        this.highLightedLine = 8;
        this.paintAndWaitShort();
        RedBlack.ptrP = this.h.getPositionInserted();
        this.paintAndWait();
        this.highLightedLine = 10;
        this.paintAndWaitShort();
        RedBlack.ptrC = this.h.leftChild.getPositionInserted();
        this.paintAndWait();
        this.highLightedLine = 11;
        this.paintAndWaitShort();
        if (this.h.leftChild.rightChild != null) {
            this.lineMoveRequests.addElement(new LineMoveRequest(this.h.leftChild.positionInserted, this.h.leftChild.rightChild.positionInserted));
        }
        else {
            this.lineMoveRequests.addElement(new LineMoveRequest(this.h.leftChild.positionInserted, -51));
        }
        this.h.leftChild.setDrawParentLine(false);
        this.paintAndWait();
        this.lineMoveRequests.removeAllElements();
        this.highLightedLine = 12;
        this.paintAndWaitShort();
        if (this.h.leftChild.rightChild != null) {
            this.lineMoveRequests.addElement(new LineMoveRequest(this.h.leftChild.rightChild.positionInserted, this.h.positionInserted));
            this.h.leftChild.rightChild.setDrawParentLine(false);
        }
        else {
            this.lineMoveRequests.addElement(new LineMoveRequest(-51, this.h.positionInserted, this.h.leftChild.positionInserted));
        }
        this.paintAndWait();
        this.lineMoveRequests.removeAllElements();
        this.highLightedLine = 13;
        this.paintAndWaitShort();
        if (this.findNodeParent != null) {
            this.lineMoveRequests.addElement(new LineMoveRequest(this.h.positionInserted, this.h.leftChild.positionInserted));
            this.h.setDrawParentLine(false);
        }
        this.paintAndWait();
        this.lineMoveRequests.removeAllElements();
        this.algorithm.setRemoveSaveLine(true);
        this.algorithm.drawAllParentLines(this.tuteTree);
        this.x = this.h.leftChild;
        this.h.leftChild = this.x.rightChild;
        this.x.rightChild = this.h;
        if (this.findNodeParent == null) {
            this.tuteTree = this.x;
        }
        else if (this.findNodeParent.leftChild != null && this.findNodeParent.leftChild == this.h) {
            this.findNodeParent.leftChild = this.x;
        }
        else if (this.findNodeParent.rightChild != null && this.findNodeParent.rightChild == this.h) {
            this.findNodeParent.rightChild = this.x;
        }
        this.algorithm.setTuteTree(this.tuteTree);
        this.paintAndWait();
        RedBlack.ptrP = -1;
        RedBlack.ptrC = -1;
        this.algorithm.resetAllHighLightParent(this.tuteTree);
        this.paintAndWait(10);
    }
    
    public int getHighLight() {
        return this.highLightedLine;
    }
    
    public void initialise(final RedBlackTreeThread theThread, final RedBlackTree algorithm, final Vector<LineMoveRequest> lineMoveRequests, final RedBlack tuteTree, final RedBlack findNodePtr, final RedBlack findNodeParent) {
        this.theThread = theThread;
        this.algorithm = algorithm;
        this.lineMoveRequests = lineMoveRequests;
        this.tuteTree = tuteTree;
        this.findNodePtr = findNodePtr;
        this.findNodeParent = findNodeParent;
    }
    
    public void paintAndWait() {
        this.theThread.setDisplayMode(true);
        while (AlgorithmThread.displayMode) {
            try {
                sleep((long)this.runSleepDuration);
                continue;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public void paintAndWait(final int i) {
        this.theThread.setDisplayMode(true);
        while (AlgorithmThread.displayMode) {
            try {
                sleep((long)i);
                continue;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public void paintAndWaitShort() {
        this.theThread.setDisplayMode(true);
        while (AlgorithmThread.displayMode) {
            try {
                sleep((long)(this.runSleepDuration / 3));
                continue;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    public void rotateLeft() {
        this.doRightRotate = false;
    }
    
    public void rotateRight() {
        this.doRightRotate = true;
    }
    
    public void run() {
        while (true) {
            if (this.threadEnabled) {
                this.h = this.findNodePtr;
                if (this.doRightRotate) {
                    this.doRotateRight();
                }
                else {
                    this.doRotateLeft();
                }
                this.setEnabled(false);
            }
            else {
                try {
                    sleep(10L);
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public synchronized void setDuration(final int duration) {
        if (duration > 0) {
            this.runSleepDuration = duration;
        }
        else {
            this.runSleepDuration = 1;
        }
    }
    
    public void setEnabled(final boolean state) {
        this.threadEnabled = state;
    }
}
