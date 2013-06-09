public class Tree234RedBlack {
    public static int POSITION;
    protected int dataItem;
    protected Tree234RedBlack leftChild;
    protected Tree234RedBlack rightChild;
    protected boolean redNode;
    protected int positionInserted;
    protected Tree234RedBlack ptr;
    protected Tree234RedBlack parent;
    protected Tree234RedBlack grandParent;
    protected Tree234RedBlack greatGrandParent;
    protected boolean newNode;
    protected boolean deleteNode;
    public static int deletePtr;
    public static int comparePtr;
    public static int splitPtr;
    public static int splitPtr2;
    public static int splitPtr3;
    
    protected Tree234RedBlack(int i, boolean b)
    {
        super();
        int i0 = b?1:0;
        this.newNode = false;
        this.deleteNode = false;
        this.dataItem = i;
        this.redNode = i0 != 0;
    }
    
    public Tree234RedBlack(int i, Tree234RedBlack a, Tree234RedBlack a0, boolean b)
    {
        super();
        int i0 = b?1:0;
        this.newNode = false;
        this.deleteNode = false;
        this.dataItem = i;
        this.redNode = i0 != 0;
        this.leftChild = a;
        this.rightChild = a0;
        int i1 = Tree234RedBlack.POSITION;
        int i2 = i1 + 1;
        Tree234RedBlack.POSITION = i2;
        this.positionInserted = i1;
        this.newNode = true;
    }
    
    public int getDataItem()
    {
        int i = this.dataItem;
        return i;
    }
    
    public int getPositionInserted()
    {
        int i = this.positionInserted;
        return i;
    }
    
    public Tree234RedBlack getLeftChild()
    {
        Tree234RedBlack a = this.leftChild;
        return a;
    }
    
    public Tree234RedBlack getRightChild()
    {
        Tree234RedBlack a = this.rightChild;
        return a;
    }
    
    protected static void swapValue(Tree234RedBlack a, Tree234RedBlack a0)
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
    
    public void rotR(Tree234Tree a)
    {
        Tree234RedBlack a0 = this.leftChild;
        if(a0 != null)
        {
            Tree234RedBlack a1 = this.leftChild;
            Tree234RedBlack.swapValue(this, a1);
            Tree234RedBlack a2 = this.leftChild;
            Tree234RedBlack a3 = this.leftChild;
            Tree234RedBlack a4 = a3.leftChild;
            this.leftChild = a4;
            Tree234RedBlack a5 = a2.rightChild;
            a2.leftChild = a5;
            Tree234RedBlack a6 = this.rightChild;
            a2.rightChild = a6;
            this.rightChild = a2;
        }
    }
    
    public void rotL(Tree234Tree a)
    {
        Tree234RedBlack a0 = this.rightChild;
        if(a0 != null)
        {
            Tree234RedBlack a1 = this.rightChild;
            Tree234RedBlack.swapValue(this, a1);
            Tree234RedBlack a2 = this.rightChild;
            Tree234RedBlack a3 = this.rightChild;
            Tree234RedBlack a4 = a3.rightChild;
            this.rightChild = a4;
            Tree234RedBlack a5 = a2.leftChild;
            a2.rightChild = a5;
            Tree234RedBlack a6 = this.leftChild;
            a2.leftChild = a6;
            this.leftChild = a2;
        }
    }
    
    public void insert(int i, Tree234Tree a)
    {
        a.setCodePosition("3");
        this.greatGrandParent = this;
        this.grandParent = this;
        this.parent = this;
        this.ptr = this;
        a.setCodePosition("3.1.1");
        label18: {
            label0: {
                while(true)
                {
                    Tree234RedBlack a0 = this.ptr;
                    if(a0 == null)
                    {
                        break label0;
                    }
                    Tree234RedBlack a1 = this.ptr;
                    label1: {
                        if(a1 == null)
                        {
                            break label1;
                        }
                        Tree234RedBlack a2 = this.ptr;
                        Tree234RedBlack a3 = a2.rightChild;
                        if(a3 == null)
                        {
                            break label1;
                        }
                        Tree234RedBlack a4 = this.ptr;
                        Tree234RedBlack a5 = a4.leftChild;
                        if(a5 == null)
                        {
                            break label1;
                        }
                        Tree234RedBlack a6 = this.ptr;
                        Tree234RedBlack a7 = a6.rightChild;
                        int i0 = a7.redNode?1:0;
                        if(i0 != 1)
                        {
                            break label1;
                        }
                        Tree234RedBlack a8 = this.ptr;
                        Tree234RedBlack a9 = a8.leftChild;
                        int i1 = a9.redNode?1:0;
                        if(i1 == 1)
                        {
                            Tree234RedBlack a10 = this.ptr;
                            int i2 = a10.positionInserted;
                            Tree234RedBlack.splitPtr = i2;
                            Tree234RedBlack a11 = this.ptr;
                            Tree234RedBlack a12 = a11.leftChild;
                            int i3 = a12.positionInserted;
                            Tree234RedBlack.splitPtr2 = i3;
                            Tree234RedBlack a13 = this.ptr;
                            Tree234RedBlack a14 = a13.rightChild;
                            int i4 = a14.positionInserted;
                            Tree234RedBlack.splitPtr3 = i4;
                        }
                    }
                    a.setCodePosition("3.2");
                    int i5 = a.isEnabled()?1:0;
                    if(i5 == 0)
                    {
                        break;
                    }
                    a.setCodePosition("3.2.1");
                    Tree234RedBlack a15 = this.ptr;
                    Tree234RedBlack a16 = a15.rightChild;
                    label2: {
                        if(a16 == null)
                        {
                            break label2;
                        }
                        Tree234RedBlack a17 = this.ptr;
                        Tree234RedBlack a18 = a17.leftChild;
                        if(a18 == null)
                        {
                            break label2;
                        }
                        Tree234RedBlack a19 = this.ptr;
                        Tree234RedBlack a20 = a19.rightChild;
                        int i6 = a20.redNode?1:0;
                        if(i6 != 1)
                        {
                            break label2;
                        }
                        Tree234RedBlack a21 = this.ptr;
                        Tree234RedBlack a22 = a21.leftChild;
                        int i7 = a22.redNode?1:0;
                        if(i7 == 1)
                        {
                            Tree234RedBlack a23 = this.ptr;
                            a23.deleteNode = true;
                            Tree234RedBlack a24 = this.ptr;
                            int i8 = a24.positionInserted;
                            Tree234RedBlack.deletePtr = i8;
                            a.update234 = false;
                            Tree234RedBlack a25 = this.greatGrandParent;
                            Tree234RedBlack a26 = this.grandParent;
                            Tree234RedBlack a27 = this.parent;
                            Tree234RedBlack a28 = this.ptr;
                            a25.split(a26, a27, a28, a);
                            a.setCodePosition("3.2.1.1");
                        }
                    }
                    Tree234RedBlack.splitPtr = -1;
                    Tree234RedBlack.splitPtr2 = -1;
                    Tree234RedBlack.splitPtr3 = -1;
                    Tree234RedBlack a29 = this.grandParent;
                    this.greatGrandParent = a29;
                    Tree234RedBlack a30 = this.parent;
                    this.grandParent = a30;
                    Tree234RedBlack a31 = this.ptr;
                    this.parent = a31;
                    Tree234RedBlack a32 = this.ptr;
                    Tree234RedBlack a33 = a32.leftChild;
                    label6: {
                        label4: {
                            label3: {
                                if(a33 == null)
                                {
                                    break label3;
                                }
                                Tree234RedBlack a34 = this.ptr;
                                Tree234RedBlack a35 = a34.leftChild;
                                int i9 = a35.redNode?1:0;
                                if(i9 != 0)
                                {
                                    break label4;
                                }
                            }
                            Tree234RedBlack a36 = this.ptr;
                            Tree234RedBlack a37 = a36.rightChild;
                            label5: {
                                if(a37 == null)
                                {
                                    break label5;
                                }
                                Tree234RedBlack a38 = this.ptr;
                                Tree234RedBlack a39 = a38.rightChild;
                                int i10 = a39.redNode?1:0;
                                if(i10 != 0)
                                {
                                    break label4;
                                }
                            }
                            a.setCodePosition("3.2.2.1");
                            a.setCodePosition("3.2.2.1.1");
                            Tree234RedBlack a40 = this.ptr;
                            int i11 = a40.dataItem;
                            if(i >= i11)
                            {
                                a.setCodePosition("3.2.2.1.2");
                                Tree234RedBlack a41 = this.ptr;
                                Tree234RedBlack a42 = a41.rightChild;
                                this.ptr = a42;
                                a.setCodePosition("3.2.2.1.2.1");
                                break label6;
                            }
                            else
                            {
                                Tree234RedBlack a43 = this.ptr;
                                Tree234RedBlack a44 = a43.leftChild;
                                this.ptr = a44;
                                a.setCodePosition("3.2.2.1.1.1");
                                break label6;
                            }
                        }
                        Tree234RedBlack a45 = this.ptr;
                        Tree234RedBlack a46 = a45.leftChild;
                        label7: {
                            if(a46 == null)
                            {
                                break label7;
                            }
                            Tree234RedBlack a47 = this.ptr;
                            Tree234RedBlack a48 = a47.leftChild;
                            int i12 = a48.redNode?1:0;
                            if(i12 == 0)
                            {
                                break label7;
                            }
                            Tree234RedBlack a49 = this.ptr;
                            Tree234RedBlack a50 = a49.rightChild;
                            label8: {
                                if(a50 == null)
                                {
                                    break label8;
                                }
                                Tree234RedBlack a51 = this.ptr;
                                Tree234RedBlack a52 = a51.rightChild;
                                int i13 = a52.redNode?1:0;
                                if(i13 != 0)
                                {
                                    break label7;
                                }
                            }
                            a.setCodePosition("3.2.2.1");
                            a.setCodePosition("3.2.2.2");
                            Tree234RedBlack a53 = this.ptr;
                            int i14 = a53.dataItem;
                            label9: {
                                if(i >= i14)
                                {
                                    break label9;
                                }
                                Tree234RedBlack a54 = this.ptr;
                                Tree234RedBlack a55 = a54.leftChild;
                                int i15 = a55.dataItem;
                                if(i >= i15)
                                {
                                    break label9;
                                }
                                Tree234RedBlack a56 = this.ptr;
                                Tree234RedBlack a57 = a56.leftChild;
                                int i16 = a57.positionInserted;
                                Tree234RedBlack.comparePtr = i16;
                                a.setCodePosition("3.2.2.2.1");
                                Tree234RedBlack a58 = this.ptr;
                                Tree234RedBlack a59 = a58.leftChild;
                                this.ptr = a59;
                                Tree234RedBlack a60 = this.parent;
                                this.grandParent = a60;
                                Tree234RedBlack a61 = this.ptr;
                                this.parent = a61;
                                Tree234RedBlack a62 = this.ptr;
                                Tree234RedBlack a63 = a62.leftChild;
                                this.ptr = a63;
                                a.setCodePosition("3.2.2.2.1.1");
                                break label6;
                            }
                            Tree234RedBlack a64 = this.ptr;
                            int i17 = a64.dataItem;
                            if(i >= i17)
                            {
                                Tree234RedBlack a65 = this.ptr;
                                Tree234RedBlack a66 = a65.leftChild;
                                int i18 = a66.positionInserted;
                                Tree234RedBlack.comparePtr = i18;
                                a.setCodePosition("3.2.2.2.1");
                                Tree234RedBlack a67 = this.ptr;
                                int i19 = a67.positionInserted;
                                Tree234RedBlack.comparePtr = i19;
                                a.setCodePosition("3.2.2.2.2");
                                a.setCodePosition("3.2.2.2.3");
                                Tree234RedBlack a68 = this.ptr;
                                Tree234RedBlack a69 = a68.rightChild;
                                this.ptr = a69;
                                a.setCodePosition("3.2.2.2.3.1");
                                break label6;
                            }
                            else
                            {
                                Tree234RedBlack a70 = this.ptr;
                                Tree234RedBlack a71 = a70.leftChild;
                                int i20 = a71.positionInserted;
                                Tree234RedBlack.comparePtr = i20;
                                a.setCodePosition("3.2.2.2.1");
                                Tree234RedBlack a72 = this.ptr;
                                int i21 = a72.positionInserted;
                                Tree234RedBlack.comparePtr = i21;
                                a.setCodePosition("3.2.2.2.2");
                                Tree234RedBlack a73 = this.ptr;
                                Tree234RedBlack a74 = a73.leftChild;
                                this.ptr = a74;
                                Tree234RedBlack a75 = this.parent;
                                this.grandParent = a75;
                                Tree234RedBlack a76 = this.ptr;
                                this.parent = a76;
                                Tree234RedBlack a77 = this.ptr;
                                Tree234RedBlack a78 = a77.rightChild;
                                this.ptr = a78;
                                a.setCodePosition("3.2.2.2.2.1");
                                break label6;
                            }
                        }
                        Tree234RedBlack a79 = this.ptr;
                        Tree234RedBlack a80 = a79.leftChild;
                        label11: {
                            label10: {
                                if(a80 == null)
                                {
                                    break label10;
                                }
                                Tree234RedBlack a81 = this.ptr;
                                Tree234RedBlack a82 = a81.leftChild;
                                int i22 = a82.redNode?1:0;
                                if(i22 != 0)
                                {
                                    break label11;
                                }
                            }
                            Tree234RedBlack a83 = this.ptr;
                            Tree234RedBlack a84 = a83.rightChild;
                            if(a84 == null)
                            {
                                break label11;
                            }
                            Tree234RedBlack a85 = this.ptr;
                            Tree234RedBlack a86 = a85.rightChild;
                            int i23 = a86.redNode?1:0;
                            if(i23 == 0)
                            {
                                break label11;
                            }
                            a.setCodePosition("3.2.2.1");
                            a.setCodePosition("3.2.2.2");
                            Tree234RedBlack a87 = this.ptr;
                            int i24 = a87.dataItem;
                            label12: {
                                if(i >= i24)
                                {
                                    break label12;
                                }
                                Tree234RedBlack a88 = this.ptr;
                                int i25 = a88.positionInserted;
                                Tree234RedBlack.comparePtr = i25;
                                a.setCodePosition("3.2.2.2.1");
                                Tree234RedBlack a89 = this.ptr;
                                Tree234RedBlack a90 = a89.leftChild;
                                this.ptr = a90;
                                a.setCodePosition("3.2.2.2.1.1");
                                break label6;
                            }
                            Tree234RedBlack a91 = this.ptr;
                            int i26 = a91.dataItem;
                            label13: {
                                if(i <= i26)
                                {
                                    break label13;
                                }
                                Tree234RedBlack a92 = this.ptr;
                                Tree234RedBlack a93 = a92.rightChild;
                                int i27 = a93.dataItem;
                                if(i >= i27)
                                {
                                    break label13;
                                }
                                Tree234RedBlack a94 = this.ptr;
                                int i28 = a94.positionInserted;
                                Tree234RedBlack.comparePtr = i28;
                                a.setCodePosition("3.2.2.2.1");
                                Tree234RedBlack a95 = this.ptr;
                                Tree234RedBlack a96 = a95.rightChild;
                                int i29 = a96.positionInserted;
                                Tree234RedBlack.comparePtr = i29;
                                a.setCodePosition("3.2.2.2.2");
                                Tree234RedBlack a97 = this.ptr;
                                Tree234RedBlack a98 = a97.rightChild;
                                this.ptr = a98;
                                Tree234RedBlack a99 = this.parent;
                                this.grandParent = a99;
                                Tree234RedBlack a100 = this.ptr;
                                this.parent = a100;
                                Tree234RedBlack a101 = this.ptr;
                                Tree234RedBlack a102 = a101.leftChild;
                                this.ptr = a102;
                                a.setCodePosition("3.2.2.2.2.1");
                                break label6;
                            }
                            Tree234RedBlack a103 = this.ptr;
                            int i30 = a103.positionInserted;
                            Tree234RedBlack.comparePtr = i30;
                            a.setCodePosition("3.2.2.2.1");
                            Tree234RedBlack a104 = this.ptr;
                            Tree234RedBlack a105 = a104.rightChild;
                            int i31 = a105.positionInserted;
                            Tree234RedBlack.comparePtr = i31;
                            a.setCodePosition("3.2.2.2.2");
                            a.setCodePosition("3.2.2.2.3");
                            Tree234RedBlack a106 = this.ptr;
                            Tree234RedBlack a107 = a106.rightChild;
                            this.ptr = a107;
                            Tree234RedBlack a108 = this.parent;
                            this.grandParent = a108;
                            Tree234RedBlack a109 = this.ptr;
                            this.parent = a109;
                            Tree234RedBlack a110 = this.ptr;
                            Tree234RedBlack a111 = a110.rightChild;
                            this.ptr = a111;
                            a.setCodePosition("3.2.2.2.3.1");
                            break label6;
                        }
                        Tree234RedBlack a112 = this.ptr;
                        Tree234RedBlack a113 = a112.leftChild;
                        if(a113 == null)
                        {
                            break label6;
                        }
                        Tree234RedBlack a114 = this.ptr;
                        Tree234RedBlack a115 = a114.leftChild;
                        int i32 = a115.redNode?1:0;
                        if(i32 == 0)
                        {
                            break label6;
                        }
                        Tree234RedBlack a116 = this.ptr;
                        Tree234RedBlack a117 = a116.rightChild;
                        if(a117 == null)
                        {
                            break label6;
                        }
                        Tree234RedBlack a118 = this.ptr;
                        Tree234RedBlack a119 = a118.rightChild;
                        int i33 = a119.redNode?1:0;
                        if(i33 == 0)
                        {
                            break label6;
                        }
                        a.setCodePosition("3.2.2.1");
                        a.setCodePosition("3.2.2.2");
                        a.setCodePosition("3.2.2.3");
                        Tree234RedBlack a120 = this.ptr;
                        int i34 = a120.dataItem;
                        label14: {
                            if(i >= i34)
                            {
                                break label14;
                            }
                            Tree234RedBlack a121 = this.ptr;
                            Tree234RedBlack a122 = a121.leftChild;
                            int i35 = a122.dataItem;
                            if(i >= i35)
                            {
                                break label14;
                            }
                            Tree234RedBlack a123 = this.ptr;
                            Tree234RedBlack a124 = a123.leftChild;
                            int i36 = a124.positionInserted;
                            Tree234RedBlack.comparePtr = i36;
                            a.setCodePosition("3.2.2.3.1");
                            Tree234RedBlack a125 = this.ptr;
                            Tree234RedBlack a126 = a125.leftChild;
                            this.ptr = a126;
                            Tree234RedBlack a127 = this.parent;
                            this.grandParent = a127;
                            Tree234RedBlack a128 = this.ptr;
                            this.parent = a128;
                            Tree234RedBlack a129 = this.ptr;
                            Tree234RedBlack a130 = a129.leftChild;
                            this.ptr = a130;
                            a.setCodePosition("3.2.2.3.1.1");
                            break label6;
                        }
                        Tree234RedBlack a131 = this.ptr;
                        int i37 = a131.dataItem;
                        label15: {
                            if(i >= i37)
                            {
                                break label15;
                            }
                            Tree234RedBlack a132 = this.ptr;
                            Tree234RedBlack a133 = a132.leftChild;
                            int i38 = a133.dataItem;
                            if(i <= i38)
                            {
                                break label15;
                            }
                            Tree234RedBlack a134 = this.ptr;
                            Tree234RedBlack a135 = a134.leftChild;
                            int i39 = a135.positionInserted;
                            Tree234RedBlack.comparePtr = i39;
                            a.setCodePosition("3.2.2.3.1");
                            Tree234RedBlack a136 = this.ptr;
                            int i40 = a136.positionInserted;
                            Tree234RedBlack.comparePtr = i40;
                            a.setCodePosition("3.2.2.3.2");
                            Tree234RedBlack a137 = this.ptr;
                            Tree234RedBlack a138 = a137.leftChild;
                            this.ptr = a138;
                            Tree234RedBlack a139 = this.parent;
                            this.grandParent = a139;
                            Tree234RedBlack a140 = this.ptr;
                            this.parent = a140;
                            Tree234RedBlack a141 = this.ptr;
                            Tree234RedBlack a142 = a141.rightChild;
                            this.ptr = a142;
                            a.setCodePosition("3.2.2.3.2.1");
                            break label6;
                        }
                        Tree234RedBlack a143 = this.ptr;
                        int i41 = a143.dataItem;
                        label17: {
                            label16: {
                                if(i <= i41)
                                {
                                    break label16;
                                }
                                Tree234RedBlack a144 = this.ptr;
                                Tree234RedBlack a145 = a144.rightChild;
                                int i42 = a145.dataItem;
                                if(i < i42)
                                {
                                    break label17;
                                }
                            }
                            Tree234RedBlack a146 = this.ptr;
                            Tree234RedBlack a147 = a146.leftChild;
                            int i43 = a147.positionInserted;
                            Tree234RedBlack.comparePtr = i43;
                            a.setCodePosition("3.2.2.3.1");
                            Tree234RedBlack a148 = this.ptr;
                            int i44 = a148.positionInserted;
                            Tree234RedBlack.comparePtr = i44;
                            a.setCodePosition("3.2.2.3.2");
                            Tree234RedBlack a149 = this.ptr;
                            Tree234RedBlack a150 = a149.rightChild;
                            int i45 = a150.positionInserted;
                            Tree234RedBlack.comparePtr = i45;
                            a.setCodePosition("3.2.2.3.3");
                            a.setCodePosition("3.2.2.3.4");
                            Tree234RedBlack a151 = this.ptr;
                            Tree234RedBlack a152 = a151.rightChild;
                            this.ptr = a152;
                            Tree234RedBlack a153 = this.parent;
                            this.grandParent = a153;
                            Tree234RedBlack a154 = this.ptr;
                            this.parent = a154;
                            Tree234RedBlack a155 = this.ptr;
                            Tree234RedBlack a156 = a155.rightChild;
                            this.ptr = a156;
                            a.setCodePosition("3.2.2.3.4.1");
                            break label6;
                        }
                        Tree234RedBlack a157 = this.ptr;
                        Tree234RedBlack a158 = a157.leftChild;
                        int i46 = a158.positionInserted;
                        Tree234RedBlack.comparePtr = i46;
                        a.setCodePosition("3.2.2.3.1");
                        Tree234RedBlack a159 = this.ptr;
                        int i47 = a159.positionInserted;
                        Tree234RedBlack.comparePtr = i47;
                        a.setCodePosition("3.2.2.3.2");
                        Tree234RedBlack a160 = this.ptr;
                        Tree234RedBlack a161 = a160.rightChild;
                        int i48 = a161.positionInserted;
                        Tree234RedBlack.comparePtr = i48;
                        a.setCodePosition("3.2.2.3.3");
                        Tree234RedBlack a162 = this.ptr;
                        Tree234RedBlack a163 = a162.rightChild;
                        this.ptr = a163;
                        Tree234RedBlack a164 = this.parent;
                        this.grandParent = a164;
                        Tree234RedBlack a165 = this.ptr;
                        this.parent = a165;
                        Tree234RedBlack a166 = this.ptr;
                        Tree234RedBlack a167 = a166.leftChild;
                        this.ptr = a167;
                        a.setCodePosition("3.2.2.3.3.1");
                    }
                    Tree234RedBlack.comparePtr = -1;
                    continue;
                }
                break label18;
            }
            a.setCodePosition("3.2");
            a.setCodePosition("3.2.0.1");
            Tree234RedBlack a168 = this.parent;
            int i49 = a168.redNode?1:0;
            label24: {
                label19: {
                    if(i49 != 0)
                    {
                        break label19;
                    }
                    Tree234RedBlack a169 = this.parent;
                    Tree234RedBlack a170 = a169.leftChild;
                    label20: {
                        if(a170 == null)
                        {
                            break label20;
                        }
                        Tree234RedBlack a171 = this.parent;
                        Tree234RedBlack a172 = a171.leftChild;
                        int i50 = a172.redNode?1:0;
                        if(i50 != 0)
                        {
                            break label19;
                        }
                    }
                    Tree234RedBlack a173 = this.parent;
                    Tree234RedBlack a174 = a173.rightChild;
                    label21: {
                        if(a174 == null)
                        {
                            break label21;
                        }
                        Tree234RedBlack a175 = this.parent;
                        Tree234RedBlack a176 = a175.rightChild;
                        int i51 = a176.redNode?1:0;
                        if(i51 != 0)
                        {
                            break label19;
                        }
                    }
                    a.setCodePosition("3.3.1");
                    Tree234RedBlack a177 = this.parent;
                    int i52 = a177.dataItem;
                    label22: {
                        if(i >= i52)
                        {
                            break label22;
                        }
                        a.setCodePosition("3.3.1.1");
                        int i53 = a.isEnabled()?1:0;
                        label23: {
                            if(i53 != 0)
                            {
                                break label23;
                            }
                            break label18;
                        }
                        Tree234RedBlack a178 = this.parent;
                        Tree234RedBlack a179 = new Tree234RedBlack(i, (Tree234RedBlack)null, (Tree234RedBlack)null, true);
                        a178.leftChild = a179;
                        this.ptr = a179;
                        a.setCodePosition("3.3.1.1.1");
                        break label24;
                    }
                    a.setCodePosition("3.3.1.1");
                    a.setCodePosition("3.3.1.2");
                    int i54 = a.isEnabled()?1:0;
                    label25: {
                        if(i54 != 0)
                        {
                            break label25;
                        }
                        break label18;
                    }
                    Tree234RedBlack a180 = this.parent;
                    Tree234RedBlack a181 = new Tree234RedBlack(i, (Tree234RedBlack)null, (Tree234RedBlack)null, true);
                    a180.rightChild = a181;
                    this.ptr = a181;
                    a.setCodePosition("3.3.1.2.1");
                    break label24;
                }
                a.setCodePosition("3.3.1");
                a.setCodePosition("3.3.2");
                Tree234RedBlack a182 = this.parent;
                int i55 = a182.redNode?1:0;
                label26: {
                    if(i55 != 0)
                    {
                        break label26;
                    }
                    Tree234RedBlack a183 = this.parent;
                    Tree234RedBlack a184 = a183.leftChild;
                    label27: {
                        if(a184 != null)
                        {
                            break label27;
                        }
                        a.setCodePosition("3.3.2.1");
                        int i56 = a.isEnabled()?1:0;
                        label28: {
                            if(i56 != 0)
                            {
                                break label28;
                            }
                            break label18;
                        }
                        Tree234RedBlack a185 = this.parent;
                        Tree234RedBlack a186 = new Tree234RedBlack(i, (Tree234RedBlack)null, (Tree234RedBlack)null, true);
                        a185.leftChild = a186;
                        this.ptr = a186;
                        a.setCodePosition("3.3.2.1.1");
                        break label24;
                    }
                    Tree234RedBlack a187 = this.parent;
                    Tree234RedBlack a188 = a187.rightChild;
                    if(a188 != null)
                    {
                        break label24;
                    }
                    a.setCodePosition("3.3.2.1");
                    a.setCodePosition("3.3.2.2");
                    a.setCodePosition("3.3.2.3");
                    int i57 = a.isEnabled()?1:0;
                    label29: {
                        if(i57 != 0)
                        {
                            break label29;
                        }
                        break label18;
                    }
                    Tree234RedBlack a189 = this.parent;
                    Tree234RedBlack a190 = new Tree234RedBlack(i, (Tree234RedBlack)null, (Tree234RedBlack)null, true);
                    a189.rightChild = a190;
                    this.ptr = a190;
                    a.setCodePosition("3.3.2.3.1");
                    break label24;
                }
                Tree234RedBlack a191 = this.grandParent;
                Tree234RedBlack a192 = a191.leftChild;
                label30: {
                    if(a192 == null)
                    {
                        break label30;
                    }
                    Tree234RedBlack a193 = this.grandParent;
                    Tree234RedBlack a194 = a193.leftChild;
                    int i58 = a194.redNode?1:0;
                    if(i58 == 0)
                    {
                        break label30;
                    }
                    Tree234RedBlack a195 = this.parent;
                    int i59 = a195.dataItem;
                    label31: {
                        if(i >= i59)
                        {
                            break label31;
                        }
                        a.setCodePosition("3.3.2.1");
                        int i60 = a.isEnabled()?1:0;
                        label32: {
                            if(i60 != 0)
                            {
                                break label32;
                            }
                            break label18;
                        }
                        Tree234RedBlack a196 = this.parent;
                        Tree234RedBlack a197 = new Tree234RedBlack(i, (Tree234RedBlack)null, (Tree234RedBlack)null, true);
                        a196.leftChild = a197;
                        this.ptr = a197;
                        a.setCodePosition("3.3.2.1.1");
                        break label24;
                    }
                    a.setCodePosition("3.3.2.1");
                    a.setCodePosition("3.3.2.2");
                    int i61 = a.isEnabled()?1:0;
                    label33: {
                        if(i61 != 0)
                        {
                            break label33;
                        }
                        break label18;
                    }
                    Tree234RedBlack a198 = this.parent;
                    Tree234RedBlack a199 = new Tree234RedBlack(i, (Tree234RedBlack)null, (Tree234RedBlack)null, true);
                    a198.rightChild = a199;
                    this.ptr = a199;
                    a.setCodePosition("3.3.2.2.1");
                    break label24;
                }
                Tree234RedBlack a200 = this.parent;
                int i62 = a200.dataItem;
                label34: {
                    if(i < i62)
                    {
                        break label34;
                    }
                    a.setCodePosition("3.3.2.1");
                    a.setCodePosition("3.3.2.2");
                    a.setCodePosition("3.3.2.3");
                    int i63 = a.isEnabled()?1:0;
                    label35: {
                        if(i63 != 0)
                        {
                            break label35;
                        }
                        break label18;
                    }
                    Tree234RedBlack a201 = this.parent;
                    Tree234RedBlack a202 = new Tree234RedBlack(i, (Tree234RedBlack)null, (Tree234RedBlack)null, true);
                    a201.rightChild = a202;
                    this.ptr = a202;
                    a.setCodePosition("3.3.2.3.1");
                    break label24;
                }
                a.setCodePosition("3.3.2.1");
                a.setCodePosition("3.3.2.2");
                int i64 = a.isEnabled()?1:0;
                label36: {
                    if(i64 != 0)
                    {
                        break label36;
                    }
                    break label18;
                }
                Tree234RedBlack a203 = this.parent;
                Tree234RedBlack a204 = new Tree234RedBlack(i, (Tree234RedBlack)null, (Tree234RedBlack)null, true);
                a203.leftChild = a204;
                this.ptr = a204;
                a.setCodePosition("3.3.2.2.1");
            }
            Tree234RedBlack a205 = this.grandParent;
            Tree234RedBlack a206 = this.parent;
            Tree234RedBlack a207 = this.ptr;
            this.balance(a205, a206, a207, "3.4", a);
            int i65 = a.isBackMode?1:0;
            if(i65 == 0)
            {
                a.repaint();
            }
            this.redNode = false;
            a.setCodePosition("3.5");
        }
    }
    
    public Tree234RedBlack copy()
    {
        int i = this.dataItem;
        int i0 = this.redNode?1:0;
        Tree234RedBlack a = new Tree234RedBlack(i, i0 != 0);
        int i1 = this.newNode?1:0;
        a.newNode = i1 != 0;
        int i2 = this.positionInserted;
        a.positionInserted = i2;
        Tree234RedBlack a0 = this.leftChild;
        if(a0 != null)
        {
            Tree234RedBlack a1 = this.leftChild;
            Tree234RedBlack a2 = a1.copy();
            a.leftChild = a2;
        }
        Tree234RedBlack a3 = this.rightChild;
        if(a3 != null)
        {
            Tree234RedBlack a4 = this.rightChild;
            Tree234RedBlack a5 = a4.copy();
            a.rightChild = a5;
        }
        return a;
    }
    
    public void split(Tree234RedBlack a, Tree234RedBlack a0, Tree234RedBlack a1, Tree234Tree a2)
    {
        Tree234RedBlack a3 = a1.rightChild;
        a3.redNode = false;
        Tree234RedBlack a4 = a1.leftChild;
        a4.redNode = false;
        a1.redNode = true;
        a2.update234 = true;
        this.balance(a, a0, a1, "3.2.1.4", a2);
        Tree234RedBlack a5 = this.ptr;
        int i = a5.redNode?1:0;
        if(i != 0)
        {
            Tree234RedBlack a6 = this.parent;
            this.ptr = a6;
            Tree234RedBlack a7 = this.grandParent;
            this.parent = a7;
        }
    }
    
    public void balance(Tree234RedBlack a, Tree234RedBlack a0, Tree234RedBlack a1, String s, Tree234Tree a2)
    {
        int i = a0.redNode?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            Tree234RedBlack a3 = a.leftChild;
            label1: {
                if(a0 != a3)
                {
                    break label1;
                }
                Tree234RedBlack a4 = a0.rightChild;
                if(a1 != a4)
                {
                    a.rotR(a2);
                    Tree234RedBlack a5 = this.grandParent;
                    Tree234RedBlack a6 = this.parent;
                    this.grandParent = a6;
                    this.ptr = a5;
                    break label0;
                }
                else
                {
                    a0.rotL(a2);
                    Tree234RedBlack a7 = this.parent;
                    Tree234RedBlack a8 = this.ptr;
                    this.parent = a8;
                    this.ptr = a7;
                    a.rotR(a2);
                    Tree234RedBlack a9 = this.grandParent;
                    Tree234RedBlack a10 = this.ptr;
                    this.grandParent = a10;
                    this.ptr = a9;
                    break label0;
                }
            }
            Tree234RedBlack a11 = a.rightChild;
            if(a0 != a11)
            {
                break label0;
            }
            Tree234RedBlack a12 = a0.leftChild;
            if(a1 != a12)
            {
                a.rotL(a2);
                Tree234RedBlack a13 = this.grandParent;
                Tree234RedBlack a14 = this.parent;
                this.grandParent = a14;
                this.parent = a13;
            }
            else
            {
                a0.rotR(a2);
                Tree234RedBlack a15 = this.parent;
                Tree234RedBlack a16 = this.ptr;
                this.parent = a16;
                this.ptr = a15;
                a.rotL(a2);
                Tree234RedBlack a17 = this.grandParent;
                Tree234RedBlack a18 = this.ptr;
                this.grandParent = a18;
                this.ptr = a17;
            }
        }
    }
    
    public void resetMarkers()
    {
        this.greatGrandParent = null;
        this.grandParent = null;
        this.parent = null;
        this.ptr = null;
    }
    
    public boolean search(int i, Tree234Tree a)
    {
        a.setCodePosition("4");
        this.ptr = this;
        this.parent = this;
        a.setCodePosition("4.1.1");
        while(true)
        {
            Tree234RedBlack a0 = this.ptr;
            label4: {
                int i0 = 0;
                label1: {
                    label0: {
                        if(a0 != null)
                        {
                            break label0;
                        }
                        a.setCodePosition("4.2");
                        a.setCodePosition("4.2.3");
                        a.setCodePosition("4.3");
                        i0 = 0;
                        break label1;
                    }
                    a.setCodePosition("4.2");
                    Tree234RedBlack a1 = this.ptr;
                    int i1 = a1.dataItem;
                    label2: {
                        if(i != i1)
                        {
                            break label2;
                        }
                        a.setCodePosition("4.2.1");
                        i0 = 1;
                        break label1;
                    }
                    Tree234RedBlack a2 = this.ptr;
                    Tree234RedBlack a3 = a2.leftChild;
                    label3: {
                        if(a3 == null)
                        {
                            break label3;
                        }
                        Tree234RedBlack a4 = this.ptr;
                        Tree234RedBlack a5 = a4.leftChild;
                        int i2 = a5.redNode?1:0;
                        if(i2 == 0)
                        {
                            break label3;
                        }
                        Tree234RedBlack a6 = this.ptr;
                        Tree234RedBlack a7 = a6.leftChild;
                        int i3 = a7.dataItem;
                        if(i != i3)
                        {
                            break label3;
                        }
                        a.setCodePosition("4.2.1");
                        i0 = 1;
                        break label1;
                    }
                    Tree234RedBlack a8 = this.ptr;
                    Tree234RedBlack a9 = a8.rightChild;
                    if(a9 == null)
                    {
                        break label4;
                    }
                    Tree234RedBlack a10 = this.ptr;
                    Tree234RedBlack a11 = a10.rightChild;
                    int i4 = a11.redNode?1:0;
                    if(i4 == 0)
                    {
                        break label4;
                    }
                    Tree234RedBlack a12 = this.ptr;
                    Tree234RedBlack a13 = a12.rightChild;
                    int i5 = a13.dataItem;
                    if(i != i5)
                    {
                        break label4;
                    }
                    a.setCodePosition("4.2.1");
                    i0 = 1;
                }
                return i0 != 0;
            }
            a.setCodePosition("4.2.2");
            Tree234RedBlack a14 = this.ptr;
            this.parent = a14;
            a.setCodePosition("4.2.2.1");
            Tree234RedBlack a15 = this.ptr;
            Tree234RedBlack a16 = a15.leftChild;
            label8: {
                label6: {
                    label5: {
                        if(a16 == null)
                        {
                            break label5;
                        }
                        Tree234RedBlack a17 = this.ptr;
                        Tree234RedBlack a18 = a17.leftChild;
                        int i6 = a18.redNode?1:0;
                        if(i6 != 0)
                        {
                            break label6;
                        }
                    }
                    Tree234RedBlack a19 = this.ptr;
                    Tree234RedBlack a20 = a19.rightChild;
                    label7: {
                        if(a20 == null)
                        {
                            break label7;
                        }
                        Tree234RedBlack a21 = this.ptr;
                        Tree234RedBlack a22 = a21.rightChild;
                        int i7 = a22.redNode?1:0;
                        if(i7 != 0)
                        {
                            break label6;
                        }
                    }
                    a.setCodePosition("4.2.2.1.1");
                    a.setCodePosition("4.2.2.1.1.1");
                    Tree234RedBlack a23 = this.ptr;
                    int i8 = a23.dataItem;
                    if(i >= i8)
                    {
                        a.setCodePosition("4.2.2.1.1.2");
                        Tree234RedBlack a24 = this.ptr;
                        Tree234RedBlack a25 = a24.rightChild;
                        this.ptr = a25;
                        a.setCodePosition("4.2.2.1.1.2.1");
                        break label8;
                    }
                    else
                    {
                        Tree234RedBlack a26 = this.ptr;
                        Tree234RedBlack a27 = a26.leftChild;
                        this.ptr = a27;
                        a.setCodePosition("4.2.2.1.1.1.1");
                        break label8;
                    }
                }
                Tree234RedBlack a28 = this.ptr;
                Tree234RedBlack a29 = a28.leftChild;
                label9: {
                    if(a29 == null)
                    {
                        break label9;
                    }
                    Tree234RedBlack a30 = this.ptr;
                    Tree234RedBlack a31 = a30.leftChild;
                    int i9 = a31.redNode?1:0;
                    if(i9 == 0)
                    {
                        break label9;
                    }
                    Tree234RedBlack a32 = this.ptr;
                    Tree234RedBlack a33 = a32.rightChild;
                    label10: {
                        if(a33 == null)
                        {
                            break label10;
                        }
                        Tree234RedBlack a34 = this.ptr;
                        Tree234RedBlack a35 = a34.rightChild;
                        int i10 = a35.redNode?1:0;
                        if(i10 != 0)
                        {
                            break label9;
                        }
                    }
                    a.setCodePosition("4.2.2.1.1");
                    a.setCodePosition("4.2.2.1.2");
                    Tree234RedBlack a36 = this.ptr;
                    int i11 = a36.dataItem;
                    label11: {
                        if(i >= i11)
                        {
                            break label11;
                        }
                        Tree234RedBlack a37 = this.ptr;
                        Tree234RedBlack a38 = a37.leftChild;
                        int i12 = a38.dataItem;
                        if(i >= i12)
                        {
                            break label11;
                        }
                        Tree234RedBlack a39 = this.ptr;
                        Tree234RedBlack a40 = a39.leftChild;
                        int i13 = a40.positionInserted;
                        Tree234RedBlack.splitPtr = i13;
                        a.setCodePosition("4.2.2.1.2.1");
                        Tree234RedBlack a41 = this.ptr;
                        Tree234RedBlack a42 = a41.leftChild;
                        this.ptr = a42;
                        Tree234RedBlack a43 = this.parent;
                        this.grandParent = a43;
                        Tree234RedBlack a44 = this.ptr;
                        this.parent = a44;
                        Tree234RedBlack a45 = this.ptr;
                        Tree234RedBlack a46 = a45.leftChild;
                        this.ptr = a46;
                        a.setCodePosition("4.2.2.1.2.1.1");
                        break label8;
                    }
                    Tree234RedBlack a47 = this.ptr;
                    int i14 = a47.dataItem;
                    if(i >= i14)
                    {
                        Tree234RedBlack a48 = this.ptr;
                        Tree234RedBlack a49 = a48.leftChild;
                        int i15 = a49.positionInserted;
                        Tree234RedBlack.splitPtr = i15;
                        a.setCodePosition("4.2.2.1.2.1");
                        Tree234RedBlack a50 = this.ptr;
                        int i16 = a50.positionInserted;
                        Tree234RedBlack.splitPtr2 = i16;
                        a.setCodePosition("4.2.2.1.2.2");
                        Tree234RedBlack.splitPtr = -1;
                        a.setCodePosition("4.2.2.1.2.3");
                        Tree234RedBlack a51 = this.ptr;
                        Tree234RedBlack a52 = a51.rightChild;
                        this.ptr = a52;
                        a.setCodePosition("4.2.2.1.2.3.1");
                        break label8;
                    }
                    else
                    {
                        Tree234RedBlack a53 = this.ptr;
                        Tree234RedBlack a54 = a53.leftChild;
                        int i17 = a54.positionInserted;
                        Tree234RedBlack.splitPtr = i17;
                        a.setCodePosition("4.2.2.1.2.1");
                        Tree234RedBlack a55 = this.ptr;
                        int i18 = a55.positionInserted;
                        Tree234RedBlack.splitPtr2 = i18;
                        a.setCodePosition("4.2.2.1.2.2");
                        Tree234RedBlack a56 = this.ptr;
                        Tree234RedBlack a57 = a56.leftChild;
                        this.ptr = a57;
                        Tree234RedBlack a58 = this.parent;
                        this.grandParent = a58;
                        Tree234RedBlack a59 = this.ptr;
                        this.parent = a59;
                        Tree234RedBlack a60 = this.ptr;
                        Tree234RedBlack a61 = a60.rightChild;
                        this.ptr = a61;
                        a.setCodePosition("4.2.2.1.2.2.1");
                        break label8;
                    }
                }
                Tree234RedBlack a62 = this.ptr;
                Tree234RedBlack a63 = a62.leftChild;
                label13: {
                    label12: {
                        if(a63 == null)
                        {
                            break label12;
                        }
                        Tree234RedBlack a64 = this.ptr;
                        Tree234RedBlack a65 = a64.leftChild;
                        int i19 = a65.redNode?1:0;
                        if(i19 != 0)
                        {
                            break label13;
                        }
                    }
                    Tree234RedBlack a66 = this.ptr;
                    Tree234RedBlack a67 = a66.rightChild;
                    if(a67 == null)
                    {
                        break label13;
                    }
                    Tree234RedBlack a68 = this.ptr;
                    Tree234RedBlack a69 = a68.rightChild;
                    int i20 = a69.redNode?1:0;
                    if(i20 == 0)
                    {
                        break label13;
                    }
                    a.setCodePosition("4.2.2.1.1");
                    a.setCodePosition("4.2.2.1.2");
                    Tree234RedBlack a70 = this.ptr;
                    int i21 = a70.dataItem;
                    label14: {
                        if(i >= i21)
                        {
                            break label14;
                        }
                        Tree234RedBlack a71 = this.ptr;
                        int i22 = a71.positionInserted;
                        Tree234RedBlack.splitPtr = i22;
                        a.setCodePosition("4.2.2.1.2.1");
                        Tree234RedBlack a72 = this.ptr;
                        Tree234RedBlack a73 = a72.leftChild;
                        this.ptr = a73;
                        a.setCodePosition("4.2.2.1.2.1.1");
                        break label8;
                    }
                    Tree234RedBlack a74 = this.ptr;
                    int i23 = a74.dataItem;
                    label15: {
                        if(i <= i23)
                        {
                            break label15;
                        }
                        Tree234RedBlack a75 = this.ptr;
                        Tree234RedBlack a76 = a75.rightChild;
                        int i24 = a76.dataItem;
                        if(i >= i24)
                        {
                            break label15;
                        }
                        Tree234RedBlack a77 = this.ptr;
                        int i25 = a77.positionInserted;
                        Tree234RedBlack.splitPtr = i25;
                        a.setCodePosition("4.2.2.1.2.1");
                        Tree234RedBlack a78 = this.ptr;
                        Tree234RedBlack a79 = a78.rightChild;
                        int i26 = a79.positionInserted;
                        Tree234RedBlack.splitPtr2 = i26;
                        a.setCodePosition("4.2.2.1.2.2");
                        Tree234RedBlack a80 = this.ptr;
                        Tree234RedBlack a81 = a80.rightChild;
                        this.ptr = a81;
                        Tree234RedBlack a82 = this.parent;
                        this.grandParent = a82;
                        Tree234RedBlack a83 = this.ptr;
                        this.parent = a83;
                        Tree234RedBlack a84 = this.ptr;
                        Tree234RedBlack a85 = a84.leftChild;
                        this.ptr = a85;
                        a.setCodePosition("4.2.2.1.2.2.1");
                        break label8;
                    }
                    Tree234RedBlack a86 = this.ptr;
                    int i27 = a86.positionInserted;
                    Tree234RedBlack.splitPtr = i27;
                    a.setCodePosition("4.2.2.1.2.1");
                    Tree234RedBlack a87 = this.ptr;
                    Tree234RedBlack a88 = a87.rightChild;
                    int i28 = a88.positionInserted;
                    Tree234RedBlack.splitPtr2 = i28;
                    a.setCodePosition("4.2.2.1.2.2");
                    Tree234RedBlack.splitPtr = -1;
                    a.setCodePosition("4.2.2.1.2.3");
                    Tree234RedBlack a89 = this.ptr;
                    Tree234RedBlack a90 = a89.rightChild;
                    this.ptr = a90;
                    Tree234RedBlack a91 = this.parent;
                    this.grandParent = a91;
                    Tree234RedBlack a92 = this.ptr;
                    this.parent = a92;
                    Tree234RedBlack a93 = this.ptr;
                    Tree234RedBlack a94 = a93.rightChild;
                    this.ptr = a94;
                    a.setCodePosition("4.2.2.1.2.3.1");
                    break label8;
                }
                Tree234RedBlack a95 = this.ptr;
                Tree234RedBlack a96 = a95.leftChild;
                if(a96 == null)
                {
                    break label8;
                }
                Tree234RedBlack a97 = this.ptr;
                Tree234RedBlack a98 = a97.leftChild;
                int i29 = a98.redNode?1:0;
                if(i29 == 0)
                {
                    break label8;
                }
                Tree234RedBlack a99 = this.ptr;
                Tree234RedBlack a100 = a99.rightChild;
                if(a100 == null)
                {
                    break label8;
                }
                Tree234RedBlack a101 = this.ptr;
                Tree234RedBlack a102 = a101.rightChild;
                int i30 = a102.redNode?1:0;
                if(i30 == 0)
                {
                    break label8;
                }
                a.setCodePosition("4.2.2.1.1");
                a.setCodePosition("4.2.2.1.2");
                a.setCodePosition("4.2.2.1.3");
                Tree234RedBlack a103 = this.ptr;
                int i31 = a103.dataItem;
                label16: {
                    if(i >= i31)
                    {
                        break label16;
                    }
                    Tree234RedBlack a104 = this.ptr;
                    Tree234RedBlack a105 = a104.leftChild;
                    int i32 = a105.dataItem;
                    if(i >= i32)
                    {
                        break label16;
                    }
                    Tree234RedBlack a106 = this.ptr;
                    Tree234RedBlack a107 = a106.leftChild;
                    int i33 = a107.positionInserted;
                    Tree234RedBlack.splitPtr = i33;
                    a.setCodePosition("4.2.2.1.3.1");
                    Tree234RedBlack a108 = this.ptr;
                    Tree234RedBlack a109 = a108.leftChild;
                    this.ptr = a109;
                    Tree234RedBlack a110 = this.parent;
                    this.grandParent = a110;
                    Tree234RedBlack a111 = this.ptr;
                    this.parent = a111;
                    Tree234RedBlack a112 = this.ptr;
                    Tree234RedBlack a113 = a112.leftChild;
                    this.ptr = a113;
                    a.setCodePosition("4.2.2.1.3.1.1");
                    break label8;
                }
                Tree234RedBlack a114 = this.ptr;
                int i34 = a114.dataItem;
                label17: {
                    if(i >= i34)
                    {
                        break label17;
                    }
                    Tree234RedBlack a115 = this.ptr;
                    Tree234RedBlack a116 = a115.leftChild;
                    int i35 = a116.dataItem;
                    if(i <= i35)
                    {
                        break label17;
                    }
                    Tree234RedBlack a117 = this.ptr;
                    Tree234RedBlack a118 = a117.leftChild;
                    int i36 = a118.positionInserted;
                    Tree234RedBlack.splitPtr = i36;
                    a.setCodePosition("4.2.2.1.3.1");
                    Tree234RedBlack a119 = this.ptr;
                    int i37 = a119.positionInserted;
                    Tree234RedBlack.splitPtr2 = i37;
                    a.setCodePosition("4.2.2.1.3.2");
                    Tree234RedBlack a120 = this.ptr;
                    Tree234RedBlack a121 = a120.leftChild;
                    this.ptr = a121;
                    Tree234RedBlack a122 = this.parent;
                    this.grandParent = a122;
                    Tree234RedBlack a123 = this.ptr;
                    this.parent = a123;
                    Tree234RedBlack a124 = this.ptr;
                    Tree234RedBlack a125 = a124.rightChild;
                    this.ptr = a125;
                    a.setCodePosition("4.2.2.1.3.2.1");
                    break label8;
                }
                Tree234RedBlack a126 = this.ptr;
                int i38 = a126.dataItem;
                label18: {
                    if(i <= i38)
                    {
                        break label18;
                    }
                    Tree234RedBlack a127 = this.ptr;
                    Tree234RedBlack a128 = a127.rightChild;
                    int i39 = a128.dataItem;
                    if(i >= i39)
                    {
                        break label18;
                    }
                    Tree234RedBlack a129 = this.ptr;
                    Tree234RedBlack a130 = a129.leftChild;
                    int i40 = a130.positionInserted;
                    Tree234RedBlack.splitPtr = i40;
                    a.setCodePosition("4.2.2.1.3.1");
                    Tree234RedBlack a131 = this.ptr;
                    int i41 = a131.positionInserted;
                    Tree234RedBlack.splitPtr2 = i41;
                    a.setCodePosition("4.2.2.1.3.2");
                    Tree234RedBlack a132 = this.ptr;
                    Tree234RedBlack a133 = a132.rightChild;
                    int i42 = a133.positionInserted;
                    Tree234RedBlack.splitPtr = i42;
                    a.setCodePosition("4.2.2.1.3.3");
                    Tree234RedBlack a134 = this.ptr;
                    Tree234RedBlack a135 = a134.rightChild;
                    this.ptr = a135;
                    Tree234RedBlack a136 = this.parent;
                    this.grandParent = a136;
                    Tree234RedBlack a137 = this.ptr;
                    this.parent = a137;
                    Tree234RedBlack a138 = this.ptr;
                    Tree234RedBlack a139 = a138.leftChild;
                    this.ptr = a139;
                    a.setCodePosition("4.2.2.1.3.3.1");
                    break label8;
                }
                Tree234RedBlack a140 = this.ptr;
                Tree234RedBlack a141 = a140.leftChild;
                int i43 = a141.positionInserted;
                Tree234RedBlack.splitPtr = i43;
                a.setCodePosition("4.2.2.1.3.1");
                Tree234RedBlack a142 = this.ptr;
                int i44 = a142.positionInserted;
                Tree234RedBlack.splitPtr2 = i44;
                a.setCodePosition("4.2.2.1.3.2");
                Tree234RedBlack a143 = this.ptr;
                Tree234RedBlack a144 = a143.rightChild;
                int i45 = a144.positionInserted;
                Tree234RedBlack.splitPtr = i45;
                a.setCodePosition("4.2.2.1.3.3");
                Tree234RedBlack.splitPtr2 = -1;
                a.setCodePosition("4.2.2.1.3.4");
                Tree234RedBlack a145 = this.ptr;
                Tree234RedBlack a146 = a145.rightChild;
                this.ptr = a146;
                Tree234RedBlack a147 = this.parent;
                this.grandParent = a147;
                Tree234RedBlack a148 = this.ptr;
                this.parent = a148;
                Tree234RedBlack a149 = this.ptr;
                Tree234RedBlack a150 = a149.rightChild;
                this.ptr = a150;
                a.setCodePosition("4.2.2.1.3.4.1");
            }
            Tree234RedBlack.splitPtr = -1;
            Tree234RedBlack.splitPtr2 = -1;
            Tree234RedBlack.splitPtr3 = -1;
        }
    }
    
    static
    {
        Tree234RedBlack.POSITION = 0;
        Tree234RedBlack.deletePtr = -1;
        Tree234RedBlack.comparePtr = -1;
        Tree234RedBlack.splitPtr = -1;
        Tree234RedBlack.splitPtr2 = -1;
        Tree234RedBlack.splitPtr3 = -1;
    }
}