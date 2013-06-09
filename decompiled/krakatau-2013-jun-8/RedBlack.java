public class RedBlack {
    public static int POSITION;
    public static int deletePtr;
    public static int ptrP;
    public static int ptrC;
    protected int dataItem;
    protected RedBlack leftChild;
    protected RedBlack rightChild;
    protected boolean redNode;
    protected int positionInserted;
    protected boolean highLightParent;
    protected RedBlack ptr;
    protected RedBlack parent;
    protected RedBlack grandParent;
    protected RedBlack greatGrandParent;
    protected boolean newNode;
    protected boolean deleteNode;
    protected boolean drawParentLine;
    
    protected static void swapValue(RedBlack a, RedBlack a0)
    {
        int i = a.dataItem;
        int i0 = a0.dataItem;
        a.dataItem = i0;
        a0.dataItem = i;
        int i1 = a.positionInserted;
        int i2 = a0.positionInserted;
        a.positionInserted = i2;
        a0.positionInserted = i1;
    }
    
    protected RedBlack(int i, boolean b)
    {
        super();
        int i0 = b?1:0;
        this.highLightParent = false;
        this.newNode = false;
        this.deleteNode = false;
        this.drawParentLine = true;
        this.dataItem = i;
        this.redNode = i0 != 0;
    }
    
    public RedBlack(int i, RedBlack a, RedBlack a0, boolean b)
    {
        super();
        int i0 = b?1:0;
        this.highLightParent = false;
        this.newNode = false;
        this.deleteNode = false;
        this.drawParentLine = true;
        this.dataItem = i;
        this.redNode = i0 != 0;
        this.leftChild = a;
        this.rightChild = a0;
        int i1 = RedBlack.POSITION;
        int i2 = i1 + 1;
        RedBlack.POSITION = i2;
        this.positionInserted = i1;
        this.newNode = true;
    }
    
    public void balance(RedBlack a, RedBlack a0, RedBlack a1)
    {
        int i = a0.redNode?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            RedBlack a2 = a.leftChild;
            label1: {
                if(a0 != a2)
                {
                    break label1;
                }
                RedBlack a3 = a0.rightChild;
                if(a1 != a3)
                {
                    a.rotR((RedBlackTree)null);
                    RedBlack a4 = this.grandParent;
                    RedBlack a5 = this.parent;
                    this.grandParent = a5;
                    this.ptr = a4;
                    break label0;
                }
                else
                {
                    a0.rotL((RedBlackTree)null);
                    RedBlack a6 = this.parent;
                    RedBlack a7 = this.ptr;
                    this.parent = a7;
                    this.ptr = a6;
                    a.rotR((RedBlackTree)null);
                    RedBlack a8 = this.grandParent;
                    RedBlack a9 = this.ptr;
                    this.grandParent = a9;
                    this.ptr = a8;
                    break label0;
                }
            }
            RedBlack a10 = a.rightChild;
            if(a0 != a10)
            {
                break label0;
            }
            RedBlack a11 = a0.leftChild;
            if(a1 != a11)
            {
                a.rotL((RedBlackTree)null);
                RedBlack a12 = this.grandParent;
                RedBlack a13 = this.parent;
                this.grandParent = a13;
                this.parent = a12;
            }
            else
            {
                a0.rotR((RedBlackTree)null);
                RedBlack a14 = this.parent;
                RedBlack a15 = this.ptr;
                this.parent = a15;
                this.ptr = a14;
                a.rotL((RedBlackTree)null);
                RedBlack a16 = this.grandParent;
                RedBlack a17 = this.ptr;
                this.grandParent = a17;
                this.ptr = a16;
            }
        }
    }
    
    public void balance(RedBlack a, RedBlack a0, RedBlack a1, String s, RedBlackTree a2)
    {
        int i = a0.redNode?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            StringBuilder a3 = new StringBuilder();
            StringBuilder a4 = a3.append(s);
            StringBuilder a5 = a4.append(".1");
            String s0 = a5.toString();
            a2.setCodePosition(s0);
            RedBlack a6 = a.leftChild;
            label1: {
                if(a0 != a6)
                {
                    break label1;
                }
                RedBlack a7 = a0.rightChild;
                if(a1 != a7)
                {
                    a.rotR(a2);
                    RedBlack a8 = this.grandParent;
                    RedBlack a9 = this.parent;
                    this.grandParent = a9;
                    this.ptr = a8;
                    StringBuilder a10 = new StringBuilder();
                    StringBuilder a11 = a10.append(s);
                    StringBuilder a12 = a11.append(".3");
                    String s1 = a12.toString();
                    a2.setCodePosition(s1);
                    break label0;
                }
                else
                {
                    a0.rotL(a2);
                    RedBlack a13 = this.parent;
                    RedBlack a14 = this.ptr;
                    this.parent = a14;
                    this.ptr = a13;
                    StringBuilder a15 = new StringBuilder();
                    StringBuilder a16 = a15.append(s);
                    StringBuilder a17 = a16.append(".2");
                    String s2 = a17.toString();
                    a2.setCodePosition(s2);
                    a.rotR(a2);
                    RedBlack a18 = this.grandParent;
                    RedBlack a19 = this.ptr;
                    this.grandParent = a19;
                    this.ptr = a18;
                    StringBuilder a20 = new StringBuilder();
                    StringBuilder a21 = a20.append(s);
                    StringBuilder a22 = a21.append(".3");
                    String s3 = a22.toString();
                    a2.setCodePosition(s3);
                    break label0;
                }
            }
            RedBlack a23 = a.rightChild;
            if(a0 != a23)
            {
                break label0;
            }
            RedBlack a24 = a0.leftChild;
            if(a1 != a24)
            {
                a.rotL(a2);
                RedBlack a25 = this.grandParent;
                RedBlack a26 = this.parent;
                this.grandParent = a26;
                this.parent = a25;
                StringBuilder a27 = new StringBuilder();
                StringBuilder a28 = a27.append(s);
                StringBuilder a29 = a28.append(".3");
                String s4 = a29.toString();
                a2.setCodePosition(s4);
            }
            else
            {
                a0.rotR(a2);
                RedBlack a30 = this.parent;
                RedBlack a31 = this.ptr;
                this.parent = a31;
                this.ptr = a30;
                StringBuilder a32 = new StringBuilder();
                StringBuilder a33 = a32.append(s);
                StringBuilder a34 = a33.append(".2");
                String s5 = a34.toString();
                a2.setCodePosition(s5);
                a.rotL(a2);
                RedBlack a35 = this.grandParent;
                RedBlack a36 = this.ptr;
                this.grandParent = a36;
                this.ptr = a35;
                StringBuilder a37 = new StringBuilder();
                StringBuilder a38 = a37.append(s);
                StringBuilder a39 = a38.append(".3");
                String s6 = a39.toString();
                a2.setCodePosition(s6);
            }
        }
        StringBuilder a40 = new StringBuilder();
        StringBuilder a41 = a40.append(s);
        StringBuilder a42 = a41.append(".4");
        String s7 = a42.toString();
        a2.setCodePosition(s7);
    }
    
    public RedBlack copy()
    {
        int i = this.dataItem;
        int i0 = this.redNode?1:0;
        RedBlack a = new RedBlack(i, i0 != 0);
        int i1 = this.newNode?1:0;
        a.newNode = i1 != 0;
        int i2 = this.positionInserted;
        a.positionInserted = i2;
        RedBlack a0 = this.leftChild;
        if(a0 != null)
        {
            RedBlack a1 = this.leftChild;
            RedBlack a2 = a1.copy();
            a.leftChild = a2;
        }
        RedBlack a3 = this.rightChild;
        if(a3 != null)
        {
            RedBlack a4 = this.rightChild;
            RedBlack a5 = a4.copy();
            a.rightChild = a5;
        }
        return a;
    }
    
    public int getDataItem()
    {
        int i = this.dataItem;
        return i;
    }
    
    public boolean getDrawParentLine()
    {
        int i = this.drawParentLine?1:0;
        return i != 0;
    }
    
    public boolean getHighLightParent()
    {
        int i = this.highLightParent?1:0;
        return i != 0;
    }
    
    public RedBlack getLeftChild()
    {
        RedBlack a = this.leftChild;
        return a;
    }
    
    public int getPositionInserted()
    {
        int i = this.positionInserted;
        return i;
    }
    
    public RedBlack getRightChild()
    {
        RedBlack a = this.rightChild;
        return a;
    }
    
    public void insert(int i)
    {
        this.greatGrandParent = this;
        this.grandParent = this;
        this.parent = this;
        this.ptr = this;
        while(true)
        {
            RedBlack a = this.ptr;
            if(a == null)
            {
                break;
            }
            RedBlack a0 = this.ptr;
            RedBlack a1 = a0.rightChild;
            label0: {
                if(a1 == null)
                {
                    break label0;
                }
                RedBlack a2 = this.ptr;
                RedBlack a3 = a2.leftChild;
                if(a3 == null)
                {
                    break label0;
                }
                RedBlack a4 = this.ptr;
                RedBlack a5 = a4.rightChild;
                int i0 = a5.redNode?1:0;
                if(i0 != 1)
                {
                    break label0;
                }
                RedBlack a6 = this.ptr;
                RedBlack a7 = a6.leftChild;
                int i1 = a7.redNode?1:0;
                if(i1 == 1)
                {
                    RedBlack a8 = this.ptr;
                    a8.deleteNode = true;
                    RedBlack a9 = this.ptr;
                    int i2 = a9.positionInserted;
                    RedBlack.deletePtr = i2;
                    RedBlack a10 = this.greatGrandParent;
                    RedBlack a11 = this.grandParent;
                    RedBlack a12 = this.parent;
                    RedBlack a13 = this.ptr;
                    a10.split(a11, a12, a13);
                }
            }
            RedBlack a14 = this.grandParent;
            this.greatGrandParent = a14;
            RedBlack a15 = this.parent;
            this.grandParent = a15;
            RedBlack a16 = this.ptr;
            this.parent = a16;
            RedBlack a17 = this.ptr;
            int i3 = a17.dataItem;
            if(i >= i3)
            {
                RedBlack a18 = this.ptr;
                RedBlack a19 = a18.rightChild;
                this.ptr = a19;
            }
            else
            {
                RedBlack a20 = this.ptr;
                RedBlack a21 = a20.leftChild;
                this.ptr = a21;
            }
        }
        RedBlack a22 = this.parent;
        int i4 = a22.dataItem;
        if(i >= i4)
        {
            RedBlack a23 = this.parent;
            RedBlack a24 = new RedBlack(i, (RedBlack)null, (RedBlack)null, true);
            a23.rightChild = a24;
            this.ptr = a24;
        }
        else
        {
            RedBlack a25 = this.parent;
            RedBlack a26 = new RedBlack(i, (RedBlack)null, (RedBlack)null, true);
            a25.leftChild = a26;
            this.ptr = a26;
        }
        RedBlack a27 = this.grandParent;
        RedBlack a28 = this.parent;
        RedBlack a29 = this.ptr;
        this.balance(a27, a28, a29);
        this.redNode = false;
    }
    
    public void insert(int i, RedBlackTree a)
    {
        a.setCodePosition("3");
        this.greatGrandParent = this;
        this.grandParent = this;
        this.parent = this;
        this.ptr = this;
        a.setCodePosition("3.1.1");
        label2: {
            label0: {
                while(true)
                {
                    RedBlack a0 = this.ptr;
                    if(a0 == null)
                    {
                        break label0;
                    }
                    a.setCodePosition("3.2");
                    int i0 = a.isEnabled()?1:0;
                    if(i0 == 0)
                    {
                        break;
                    }
                    RedBlack a1 = this.ptr;
                    RedBlack a2 = a1.rightChild;
                    label1: {
                        if(a2 == null)
                        {
                            break label1;
                        }
                        RedBlack a3 = this.ptr;
                        RedBlack a4 = a3.leftChild;
                        if(a4 == null)
                        {
                            break label1;
                        }
                        RedBlack a5 = this.ptr;
                        RedBlack a6 = a5.rightChild;
                        int i1 = a6.redNode?1:0;
                        if(i1 != 1)
                        {
                            break label1;
                        }
                        RedBlack a7 = this.ptr;
                        RedBlack a8 = a7.leftChild;
                        int i2 = a8.redNode?1:0;
                        if(i2 == 1)
                        {
                            RedBlack a9 = this.ptr;
                            a9.deleteNode = true;
                            RedBlack a10 = this.ptr;
                            int i3 = a10.positionInserted;
                            RedBlack.deletePtr = i3;
                            a.update234 = false;
                            RedBlack a11 = this.greatGrandParent;
                            RedBlack a12 = this.grandParent;
                            RedBlack a13 = this.parent;
                            RedBlack a14 = this.ptr;
                            a11.split(a12, a13, a14, a);
                        }
                    }
                    RedBlack a15 = this.grandParent;
                    this.greatGrandParent = a15;
                    RedBlack a16 = this.parent;
                    this.grandParent = a16;
                    a.setCodePosition("3.2.2.1");
                    RedBlack a17 = this.ptr;
                    this.parent = a17;
                    a.setCodePosition("3.2.2.2");
                    RedBlack a18 = this.ptr;
                    int i4 = a18.dataItem;
                    if(i >= i4)
                    {
                        RedBlack a19 = this.ptr;
                        RedBlack a20 = a19.rightChild;
                        this.ptr = a20;
                        a.setCodePosition("3.2.2.4");
                        continue;
                    }
                    else
                    {
                        RedBlack a21 = this.ptr;
                        RedBlack a22 = a21.leftChild;
                        this.ptr = a22;
                        a.setCodePosition("3.2.2.3");
                        continue;
                    }
                }
                break label2;
            }
            a.setCodePosition("3.2");
            a.setCodePosition("3.2.0.1");
            a.setCodePosition("3.3.1");
            RedBlack a23 = this.parent;
            int i5 = a23.dataItem;
            label5: {
                label3: {
                    if(i < i5)
                    {
                        break label3;
                    }
                    a.setCodePosition("3.3.3");
                    int i6 = a.isEnabled()?1:0;
                    label4: {
                        if(i6 != 0)
                        {
                            break label4;
                        }
                        break label2;
                    }
                    RedBlack a24 = this.parent;
                    RedBlack a25 = new RedBlack(i, (RedBlack)null, (RedBlack)null, true);
                    a24.rightChild = a25;
                    this.ptr = a25;
                    a.setCodePosition("3.3.4");
                    break label5;
                }
                int i7 = a.isEnabled()?1:0;
                label6: {
                    if(i7 != 0)
                    {
                        break label6;
                    }
                    break label2;
                }
                RedBlack a26 = this.parent;
                RedBlack a27 = new RedBlack(i, (RedBlack)null, (RedBlack)null, true);
                a26.leftChild = a27;
                this.ptr = a27;
                a.setCodePosition("3.3.2");
            }
            RedBlack a28 = this.grandParent;
            RedBlack a29 = this.parent;
            RedBlack a30 = this.ptr;
            this.balance(a28, a29, a30, "3.4", a);
            this.redNode = false;
            a.setCodePosition("3.5");
        }
    }
    
    public void resetMarkers()
    {
        this.greatGrandParent = null;
        this.grandParent = null;
        this.parent = null;
        this.ptr = null;
    }
    
    public void rotL(RedBlackTree a)
    {
        RedBlack a0 = this.rightChild;
        if(a0 != null)
        {
            RedBlack a1 = this.rightChild;
            RedBlack.swapValue(this, a1);
            RedBlack a2 = this.rightChild;
            RedBlack a3 = this.rightChild;
            RedBlack a4 = a3.rightChild;
            this.rightChild = a4;
            RedBlack a5 = a2.leftChild;
            a2.rightChild = a5;
            RedBlack a6 = this.leftChild;
            a2.leftChild = a6;
            this.leftChild = a2;
        }
    }
    
    public void rotR(RedBlackTree a)
    {
        RedBlack a0 = this.leftChild;
        if(a0 != null)
        {
            RedBlack a1 = this.leftChild;
            RedBlack.swapValue(this, a1);
            RedBlack a2 = this.leftChild;
            RedBlack a3 = this.leftChild;
            RedBlack a4 = a3.leftChild;
            this.leftChild = a4;
            RedBlack a5 = a2.rightChild;
            a2.leftChild = a5;
            RedBlack a6 = this.rightChild;
            a2.rightChild = a6;
            this.rightChild = a2;
        }
    }
    
    public boolean search(int i, RedBlackTree a)
    {
        a.setCodePosition("4");
        a.setCodePosition("4.1");
        this.ptr = this;
        this.parent = this;
        a.setCodePosition("4.1.1");
        while(true)
        {
            RedBlack a0 = this.ptr;
            label2: {
                int i0 = 0;
                label1: {
                    label0: {
                        if(a0 != null)
                        {
                            break label0;
                        }
                        a.setCodePosition("4.2.3");
                        a.setCodePosition("4.3");
                        i0 = 0;
                        break label1;
                    }
                    a.setCodePosition("4.2");
                    RedBlack a1 = this.ptr;
                    int i1 = a1.dataItem;
                    if(i != i1)
                    {
                        break label2;
                    }
                    a.setCodePosition("4.2.1");
                    i0 = 1;
                }
                return i0 != 0;
            }
            a.setCodePosition("4.2.2");
            RedBlack a2 = this.ptr;
            this.parent = a2;
            a.setCodePosition("4.2.2.1");
            RedBlack a3 = this.ptr;
            int i2 = a3.dataItem;
            if(i >= i2)
            {
                RedBlack a4 = this.ptr;
                RedBlack a5 = a4.rightChild;
                this.ptr = a5;
                a.setCodePosition("4.2.2.4");
            }
            else
            {
                a.setCodePosition("4.2.2.2");
                RedBlack a6 = this.ptr;
                RedBlack a7 = a6.leftChild;
                this.ptr = a7;
                a.setCodePosition("4.2.2.3");
            }
        }
    }
    
    public void setDrawParentLine(boolean b)
    {
        this.drawParentLine = b;
    }
    
    public void setHighLightParent(boolean b)
    {
        this.highLightParent = b;
    }
    
    public void split(RedBlack a, RedBlack a0, RedBlack a1)
    {
        RedBlack a2 = a1.rightChild;
        a2.redNode = false;
        RedBlack a3 = a1.leftChild;
        a3.redNode = false;
        a1.redNode = true;
        this.balance(a, a0, a1);
    }
    
    public void split(RedBlack a, RedBlack a0, RedBlack a1, RedBlackTree a2)
    {
        RedBlack a3 = a1.rightChild;
        a3.redNode = false;
        RedBlack a4 = a1.leftChild;
        a4.redNode = false;
        a2.setCodePosition("3.2.1.1");
        a1.redNode = true;
        a2.setCodePosition("3.2.1.3");
        a2.update234 = true;
        this.balance(a, a0, a1, "3.2.1.4", a2);
    }
    
    static
    {
        RedBlack.POSITION = 0;
        RedBlack.deletePtr = -1;
        RedBlack.ptrP = -1;
        RedBlack.ptrC = -1;
    }
}