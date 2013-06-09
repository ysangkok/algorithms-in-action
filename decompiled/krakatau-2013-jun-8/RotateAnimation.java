public class RotateAnimation extends Thread {
    final private static int SPEEDFACTOR = 3;
    private RedBlackTree algorithm;
    public java.util.Vector lineMoveRequests;
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
    
    public RotateAnimation()
    {
        super();
        this.highLightedLine = 0;
        this.threadEnabled = false;
        this.doRightRotate = false;
        this.runSleepDuration = 3000;
    }
    
    private void doRotateLeft()
    {
        RedBlackTree a = this.algorithm;
        a.setRemoveSaveLine(false);
        this.highLightedLine = 1;
        this.paintAndWaitShort();
        RedBlack a0 = this.h;
        int i = a0.getPositionInserted();
        RedBlack.ptrP = i;
        this.paintAndWait();
        this.highLightedLine = 3;
        this.paintAndWaitShort();
        RedBlack a1 = this.h;
        RedBlack a2 = a1.rightChild;
        int i0 = a2.getPositionInserted();
        RedBlack.ptrC = i0;
        this.paintAndWait();
        this.highLightedLine = 4;
        this.paintAndWaitShort();
        RedBlack a3 = this.h;
        RedBlack a4 = a3.rightChild;
        RedBlack a5 = a4.leftChild;
        if(a5 == null)
        {
            java.util.Vector a6 = this.lineMoveRequests;
            RedBlack a7 = this.h;
            RedBlack a8 = a7.rightChild;
            int i1 = a8.positionInserted;
            LineMoveRequest a9 = new LineMoveRequest(i1, -50);
            a6.addElement((Object)a9);
        }
        else
        {
            java.util.Vector a10 = this.lineMoveRequests;
            RedBlack a11 = this.h;
            RedBlack a12 = a11.rightChild;
            int i2 = a12.positionInserted;
            RedBlack a13 = this.h;
            RedBlack a14 = a13.rightChild;
            RedBlack a15 = a14.leftChild;
            int i3 = a15.positionInserted;
            LineMoveRequest a16 = new LineMoveRequest(i2, i3);
            a10.addElement((Object)a16);
        }
        RedBlack a17 = this.h;
        RedBlack a18 = a17.rightChild;
        a18.setDrawParentLine(false);
        this.paintAndWait();
        java.util.Vector a19 = this.lineMoveRequests;
        a19.removeAllElements();
        this.highLightedLine = 5;
        this.paintAndWaitShort();
        RedBlack a20 = this.h;
        RedBlack a21 = a20.rightChild;
        RedBlack a22 = a21.leftChild;
        if(a22 == null)
        {
            java.util.Vector a23 = this.lineMoveRequests;
            RedBlack a24 = this.h;
            int i4 = a24.positionInserted;
            RedBlack a25 = this.h;
            RedBlack a26 = a25.rightChild;
            int i5 = a26.positionInserted;
            LineMoveRequest a27 = new LineMoveRequest(-50, i4, i5);
            a23.addElement((Object)a27);
        }
        else
        {
            java.util.Vector a28 = this.lineMoveRequests;
            RedBlack a29 = this.h;
            RedBlack a30 = a29.rightChild;
            RedBlack a31 = a30.leftChild;
            int i6 = a31.positionInserted;
            RedBlack a32 = this.h;
            int i7 = a32.positionInserted;
            LineMoveRequest a33 = new LineMoveRequest(i6, i7);
            a28.addElement((Object)a33);
            RedBlack a34 = this.h;
            RedBlack a35 = a34.rightChild;
            RedBlack a36 = a35.leftChild;
            a36.setDrawParentLine(false);
        }
        this.paintAndWait();
        java.util.Vector a37 = this.lineMoveRequests;
        a37.removeAllElements();
        this.highLightedLine = 6;
        this.paintAndWaitShort();
        RedBlack a38 = this.findNodeParent;
        if(a38 != null)
        {
            java.util.Vector a39 = this.lineMoveRequests;
            RedBlack a40 = this.h;
            int i8 = a40.positionInserted;
            RedBlack a41 = this.h;
            RedBlack a42 = a41.rightChild;
            int i9 = a42.positionInserted;
            LineMoveRequest a43 = new LineMoveRequest(i8, i9);
            a39.addElement((Object)a43);
            RedBlack a44 = this.h;
            a44.setDrawParentLine(false);
        }
        this.paintAndWait();
        java.util.Vector a45 = this.lineMoveRequests;
        a45.removeAllElements();
        RedBlackTree a46 = this.algorithm;
        a46.setRemoveSaveLine(true);
        RedBlackTree a47 = this.algorithm;
        RedBlack a48 = this.tuteTree;
        a47.drawAllParentLines(a48);
        RedBlack a49 = this.h;
        RedBlack a50 = a49.rightChild;
        this.x = a50;
        RedBlack a51 = this.h;
        RedBlack a52 = this.x;
        RedBlack a53 = a52.leftChild;
        a51.rightChild = a53;
        RedBlack a54 = this.x;
        RedBlack a55 = this.h;
        a54.leftChild = a55;
        RedBlack a56 = this.findNodeParent;
        label1: {
            label0: {
                if(a56 != null)
                {
                    break label0;
                }
                RedBlack a57 = this.x;
                this.tuteTree = a57;
                break label1;
            }
            RedBlack a58 = this.findNodeParent;
            RedBlack a59 = a58.leftChild;
            label2: {
                if(a59 == null)
                {
                    break label2;
                }
                RedBlack a60 = this.findNodeParent;
                RedBlack a61 = a60.leftChild;
                RedBlack a62 = this.h;
                if(a61 != a62)
                {
                    break label2;
                }
                RedBlack a63 = this.findNodeParent;
                RedBlack a64 = this.x;
                a63.leftChild = a64;
                break label1;
            }
            RedBlack a65 = this.findNodeParent;
            RedBlack a66 = a65.rightChild;
            if(a66 == null)
            {
                break label1;
            }
            RedBlack a67 = this.findNodeParent;
            RedBlack a68 = a67.rightChild;
            RedBlack a69 = this.h;
            if(a68 == a69)
            {
                RedBlack a70 = this.findNodeParent;
                RedBlack a71 = this.x;
                a70.rightChild = a71;
            }
        }
        RedBlackTree a72 = this.algorithm;
        RedBlack a73 = this.tuteTree;
        a72.setTuteTree(a73);
        this.paintAndWait();
        RedBlack.ptrP = -1;
        RedBlack.ptrC = -1;
        RedBlackTree a74 = this.algorithm;
        RedBlack a75 = this.tuteTree;
        a74.resetAllHighLightParent(a75);
        this.paintAndWait(10);
    }
    
    private void doRotateRight()
    {
        RedBlackTree a = this.algorithm;
        a.setRemoveSaveLine(false);
        this.highLightedLine = 8;
        this.paintAndWaitShort();
        RedBlack a0 = this.h;
        int i = a0.getPositionInserted();
        RedBlack.ptrP = i;
        this.paintAndWait();
        this.highLightedLine = 10;
        this.paintAndWaitShort();
        RedBlack a1 = this.h;
        RedBlack a2 = a1.leftChild;
        int i0 = a2.getPositionInserted();
        RedBlack.ptrC = i0;
        this.paintAndWait();
        this.highLightedLine = 11;
        this.paintAndWaitShort();
        RedBlack a3 = this.h;
        RedBlack a4 = a3.leftChild;
        RedBlack a5 = a4.rightChild;
        if(a5 == null)
        {
            java.util.Vector a6 = this.lineMoveRequests;
            RedBlack a7 = this.h;
            RedBlack a8 = a7.leftChild;
            int i1 = a8.positionInserted;
            LineMoveRequest a9 = new LineMoveRequest(i1, -51);
            a6.addElement((Object)a9);
        }
        else
        {
            java.util.Vector a10 = this.lineMoveRequests;
            RedBlack a11 = this.h;
            RedBlack a12 = a11.leftChild;
            int i2 = a12.positionInserted;
            RedBlack a13 = this.h;
            RedBlack a14 = a13.leftChild;
            RedBlack a15 = a14.rightChild;
            int i3 = a15.positionInserted;
            LineMoveRequest a16 = new LineMoveRequest(i2, i3);
            a10.addElement((Object)a16);
        }
        RedBlack a17 = this.h;
        RedBlack a18 = a17.leftChild;
        a18.setDrawParentLine(false);
        this.paintAndWait();
        java.util.Vector a19 = this.lineMoveRequests;
        a19.removeAllElements();
        this.highLightedLine = 12;
        this.paintAndWaitShort();
        RedBlack a20 = this.h;
        RedBlack a21 = a20.leftChild;
        RedBlack a22 = a21.rightChild;
        if(a22 == null)
        {
            java.util.Vector a23 = this.lineMoveRequests;
            RedBlack a24 = this.h;
            int i4 = a24.positionInserted;
            RedBlack a25 = this.h;
            RedBlack a26 = a25.leftChild;
            int i5 = a26.positionInserted;
            LineMoveRequest a27 = new LineMoveRequest(-51, i4, i5);
            a23.addElement((Object)a27);
        }
        else
        {
            java.util.Vector a28 = this.lineMoveRequests;
            RedBlack a29 = this.h;
            RedBlack a30 = a29.leftChild;
            RedBlack a31 = a30.rightChild;
            int i6 = a31.positionInserted;
            RedBlack a32 = this.h;
            int i7 = a32.positionInserted;
            LineMoveRequest a33 = new LineMoveRequest(i6, i7);
            a28.addElement((Object)a33);
            RedBlack a34 = this.h;
            RedBlack a35 = a34.leftChild;
            RedBlack a36 = a35.rightChild;
            a36.setDrawParentLine(false);
        }
        this.paintAndWait();
        java.util.Vector a37 = this.lineMoveRequests;
        a37.removeAllElements();
        this.highLightedLine = 13;
        this.paintAndWaitShort();
        RedBlack a38 = this.findNodeParent;
        if(a38 != null)
        {
            java.util.Vector a39 = this.lineMoveRequests;
            RedBlack a40 = this.h;
            int i8 = a40.positionInserted;
            RedBlack a41 = this.h;
            RedBlack a42 = a41.leftChild;
            int i9 = a42.positionInserted;
            LineMoveRequest a43 = new LineMoveRequest(i8, i9);
            a39.addElement((Object)a43);
            RedBlack a44 = this.h;
            a44.setDrawParentLine(false);
        }
        this.paintAndWait();
        java.util.Vector a45 = this.lineMoveRequests;
        a45.removeAllElements();
        RedBlackTree a46 = this.algorithm;
        a46.setRemoveSaveLine(true);
        RedBlackTree a47 = this.algorithm;
        RedBlack a48 = this.tuteTree;
        a47.drawAllParentLines(a48);
        RedBlack a49 = this.h;
        RedBlack a50 = a49.leftChild;
        this.x = a50;
        RedBlack a51 = this.h;
        RedBlack a52 = this.x;
        RedBlack a53 = a52.rightChild;
        a51.leftChild = a53;
        RedBlack a54 = this.x;
        RedBlack a55 = this.h;
        a54.rightChild = a55;
        RedBlack a56 = this.findNodeParent;
        label1: {
            label0: {
                if(a56 != null)
                {
                    break label0;
                }
                RedBlack a57 = this.x;
                this.tuteTree = a57;
                break label1;
            }
            RedBlack a58 = this.findNodeParent;
            RedBlack a59 = a58.leftChild;
            label2: {
                if(a59 == null)
                {
                    break label2;
                }
                RedBlack a60 = this.findNodeParent;
                RedBlack a61 = a60.leftChild;
                RedBlack a62 = this.h;
                if(a61 != a62)
                {
                    break label2;
                }
                RedBlack a63 = this.findNodeParent;
                RedBlack a64 = this.x;
                a63.leftChild = a64;
                break label1;
            }
            RedBlack a65 = this.findNodeParent;
            RedBlack a66 = a65.rightChild;
            if(a66 == null)
            {
                break label1;
            }
            RedBlack a67 = this.findNodeParent;
            RedBlack a68 = a67.rightChild;
            RedBlack a69 = this.h;
            if(a68 == a69)
            {
                RedBlack a70 = this.findNodeParent;
                RedBlack a71 = this.x;
                a70.rightChild = a71;
            }
        }
        RedBlackTree a72 = this.algorithm;
        RedBlack a73 = this.tuteTree;
        a72.setTuteTree(a73);
        this.paintAndWait();
        RedBlack.ptrP = -1;
        RedBlack.ptrC = -1;
        RedBlackTree a74 = this.algorithm;
        RedBlack a75 = this.tuteTree;
        a74.resetAllHighLightParent(a75);
        this.paintAndWait(10);
    }
    
    public int getHighLight()
    {
        int i = this.highLightedLine;
        return i;
    }
    
    public void initialise(RedBlackTreeThread a, RedBlackTree a0, java.util.Vector a1, RedBlack a2, RedBlack a3, RedBlack a4)
    {
        this.theThread = a;
        this.algorithm = a0;
        this.lineMoveRequests = a1;
        this.tuteTree = a2;
        this.findNodePtr = a3;
        this.findNodeParent = a4;
    }
    
    public void paintAndWait()
    {
        RedBlackTreeThread a = this.theThread;
        a.setDisplayMode(true);
        while(true)
        {
            int i = com.cim.AIA.AlgorithmThread.displayMode?1:0;
            if(i == 0)
            {
                return;
            }
            label0: {
                int i0 = 0;
                try
                {
                    i0 = this.runSleepDuration;
                }
                catch(Exception ignoredException)
                {
                    break label0;
                }
                try
                {
                    long j = (long)i0;
                    RotateAnimation.sleep(j);
                }
                catch(Exception ignoredException0)
                {
                }
            }
        }
    }
    
    public void paintAndWait(int i)
    {
        RedBlackTreeThread a = this.theThread;
        a.setDisplayMode(true);
        while(true)
        {
            int i0 = com.cim.AIA.AlgorithmThread.displayMode?1:0;
            if(i0 == 0)
            {
                return;
            }
            try
            {
                long j = (long)i;
                RotateAnimation.sleep(j);
            }
            catch(Exception ignoredException)
            {
            }
        }
    }
    
    public void paintAndWaitShort()
    {
        RedBlackTreeThread a = this.theThread;
        a.setDisplayMode(true);
        while(true)
        {
            int i = com.cim.AIA.AlgorithmThread.displayMode?1:0;
            if(i == 0)
            {
                return;
            }
            label0: {
                int i0 = 0;
                try
                {
                    i0 = this.runSleepDuration;
                }
                catch(Exception ignoredException)
                {
                    break label0;
                }
                try
                {
                    int i1 = i0 / 3;
                    long j = (long)i1;
                    RotateAnimation.sleep(j);
                }
                catch(Exception ignoredException0)
                {
                }
            }
        }
    }
    
    public void rotateLeft()
    {
        this.doRightRotate = false;
    }
    
    public void rotateRight()
    {
        this.doRightRotate = true;
    }
    
    public void run()
    {
        while(true)
        {
            int i = this.threadEnabled?1:0;
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                try
                {
                    RotateAnimation.sleep(10L);
                    continue;
                }
                catch(Exception ignoredException)
                {
                    continue;
                }
            }
            RedBlack a = this.findNodePtr;
            this.h = a;
            int i0 = this.doRightRotate?1:0;
            if(i0 == 0)
            {
                this.doRotateLeft();
            }
            else
            {
                this.doRotateRight();
            }
            this.setEnabled(false);
        }
    }
    
    public synchronized void setDuration(int i)
    {
        if(i <= 0)
        {
            this.runSleepDuration = 1;
        }
        else
        {
            this.runSleepDuration = i;
        }
    }
    
    public void setEnabled(boolean b)
    {
        this.threadEnabled = b;
    }
}