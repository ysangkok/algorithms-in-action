public class RadixTrieIterCanvas extends com.cim.AIA.AlgorithmCanvas {
    private RadixTrieIterNode head;
    private RadixTrieIterNode currentNode;
    private RadixTrieIterDigitalElementArray insertBitArray;
    private RadixTrieIterDigitalElementArray searchBitArray;
    private RadixTrieIterDigitalElementArray compareBitArray;
    private RadixTrieIterDigitalElementArray finalCompareBitArray;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray searchData;
    private com.cim.AIA.Node levelNode;
    private int treeTop;
    private int mostSignificantBit;
    private int currentLevel;
    private java.util.Vector insertedData;
    private int insertedDataWidth;
    final private static int Y_GAP = 20;
    final private static int X_GAP = 20;
    
    public RadixTrieIterCanvas()
    {
        super();
        this.insertedDataWidth = 70;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        label25: {
            Exception a0 = null;
            label1: {
                RadixTrieIterNode a1 = null;
                com.cim.AIA.Node a2 = null;
                com.cim.AIA.ElementArray a3 = null;
                com.cim.AIA.ElementArray a4 = null;
                RadixTrieIterDigitalElementArray a5 = null;
                RadixTrieIterDigitalElementArray a6 = null;
                RadixTrieIterDigitalElementArray a7 = null;
                RadixTrieIterDigitalElementArray a8 = null;
                RadixTrieIterNode a9 = null;
                RadixTrieIterNode a10 = null;
                RadixTrieIterNode a11 = null;
                java.awt.Rectangle a12 = null;
                int i = 0;
                java.awt.Point a13 = null;
                label0: try
                {
                    a1 = this.head;
                    break label0;
                }
                catch(Exception a14)
                {
                    a0 = a14;
                    break label1;
                }
                label2: {
                    RadixTrieIterNode a15 = null;
                    if(a1 == null)
                    {
                        break label2;
                    }
                    try
                    {
                        a15 = this.head;
                    }
                    catch(Exception a16)
                    {
                        a0 = a16;
                        break label1;
                    }
                    try
                    {
                        a15.draw(a);
                    }
                    catch(Exception a17)
                    {
                        a0 = a17;
                        break label1;
                    }
                }
                label3: try
                {
                    a2 = this.levelNode;
                    break label3;
                }
                catch(Exception a18)
                {
                    a0 = a18;
                    break label1;
                }
                label4: {
                    java.awt.Dimension a19 = null;
                    int i0 = 0;
                    int i1 = 0;
                    String s = null;
                    com.cim.AIA.Node a20 = null;
                    java.awt.FontMetrics a21 = null;
                    String s0 = null;
                    int i2 = 0;
                    java.awt.Point a22 = null;
                    com.cim.AIA.Node a23 = null;
                    if(a2 == null)
                    {
                        break label4;
                    }
                    try
                    {
                        a19 = this.getParentSize();
                    }
                    catch(Exception a24)
                    {
                        a0 = a24;
                        break label1;
                    }
                    try
                    {
                        i0 = a19.height;
                    }
                    catch(Exception a25)
                    {
                        a0 = a25;
                        break label1;
                    }
                    try
                    {
                        i1 = i0 - 30;
                        s = localization.Messages.getString("RadixTrieIterCanvas.0");
                    }
                    catch(Exception a26)
                    {
                        a0 = a26;
                        break label1;
                    }
                    try
                    {
                        a.drawString(s, 20, i1);
                    }
                    catch(Exception a27)
                    {
                        a0 = a27;
                        break label1;
                    }
                    try
                    {
                        a20 = this.levelNode;
                    }
                    catch(Exception a28)
                    {
                        a0 = a28;
                        break label1;
                    }
                    try
                    {
                        a21 = a.getFontMetrics();
                    }
                    catch(Exception a29)
                    {
                        a0 = a29;
                        break label1;
                    }
                    try
                    {
                        s0 = localization.Messages.getString("RadixTrieIterCanvas.1");
                    }
                    catch(Exception a30)
                    {
                        a0 = a30;
                        break label1;
                    }
                    try
                    {
                        i2 = a21.stringWidth(s0);
                    }
                    catch(Exception a31)
                    {
                        a0 = a31;
                        break label1;
                    }
                    try
                    {
                        int i3 = i2 + 20;
                        int i4 = i1 - 15;
                        a22 = new java.awt.Point(i3, i4);
                    }
                    catch(Exception a32)
                    {
                        a0 = a32;
                        break label1;
                    }
                    try
                    {
                        a20.setPosition(a22);
                    }
                    catch(Exception a33)
                    {
                        a0 = a33;
                        break label1;
                    }
                    try
                    {
                        a23 = this.levelNode;
                    }
                    catch(Exception a34)
                    {
                        a0 = a34;
                        break label1;
                    }
                    try
                    {
                        a23.draw(a);
                    }
                    catch(Exception a35)
                    {
                        a0 = a35;
                        break label1;
                    }
                }
                label5: try
                {
                    a3 = this.insertData;
                    break label5;
                }
                catch(Exception a36)
                {
                    a0 = a36;
                    break label1;
                }
                label6: {
                    com.cim.AIA.ElementArray a37 = null;
                    if(a3 == null)
                    {
                        break label6;
                    }
                    try
                    {
                        a37 = this.insertData;
                    }
                    catch(Exception a38)
                    {
                        a0 = a38;
                        break label1;
                    }
                    try
                    {
                        a37.draw(a);
                    }
                    catch(Exception a39)
                    {
                        a0 = a39;
                        break label1;
                    }
                }
                label7: try
                {
                    a4 = this.searchData;
                    break label7;
                }
                catch(Exception a40)
                {
                    a0 = a40;
                    break label1;
                }
                label8: {
                    com.cim.AIA.ElementArray a41 = null;
                    if(a4 == null)
                    {
                        break label8;
                    }
                    try
                    {
                        a41 = this.searchData;
                    }
                    catch(Exception a42)
                    {
                        a0 = a42;
                        break label1;
                    }
                    try
                    {
                        a41.draw(a);
                    }
                    catch(Exception a43)
                    {
                        a0 = a43;
                        break label1;
                    }
                }
                label9: try
                {
                    a5 = this.insertBitArray;
                    break label9;
                }
                catch(Exception a44)
                {
                    a0 = a44;
                    break label1;
                }
                label10: {
                    RadixTrieIterDigitalElementArray a45 = null;
                    if(a5 == null)
                    {
                        break label10;
                    }
                    try
                    {
                        a45 = this.insertBitArray;
                    }
                    catch(Exception a46)
                    {
                        a0 = a46;
                        break label1;
                    }
                    try
                    {
                        a45.draw(a);
                    }
                    catch(Exception a47)
                    {
                        a0 = a47;
                        break label1;
                    }
                }
                label11: try
                {
                    a6 = this.searchBitArray;
                    break label11;
                }
                catch(Exception a48)
                {
                    a0 = a48;
                    break label1;
                }
                label12: {
                    RadixTrieIterDigitalElementArray a49 = null;
                    if(a6 == null)
                    {
                        break label12;
                    }
                    try
                    {
                        a49 = this.searchBitArray;
                    }
                    catch(Exception a50)
                    {
                        a0 = a50;
                        break label1;
                    }
                    try
                    {
                        a49.draw(a);
                    }
                    catch(Exception a51)
                    {
                        a0 = a51;
                        break label1;
                    }
                }
                label13: try
                {
                    a7 = this.finalCompareBitArray;
                    break label13;
                }
                catch(Exception a52)
                {
                    a0 = a52;
                    break label1;
                }
                label14: {
                    RadixTrieIterDigitalElementArray a53 = null;
                    if(a7 == null)
                    {
                        break label14;
                    }
                    try
                    {
                        a53 = this.finalCompareBitArray;
                    }
                    catch(Exception a54)
                    {
                        a0 = a54;
                        break label1;
                    }
                    try
                    {
                        a53.draw(a);
                    }
                    catch(Exception a55)
                    {
                        a0 = a55;
                        break label1;
                    }
                }
                label15: try
                {
                    a8 = this.compareBitArray;
                    break label15;
                }
                catch(Exception a56)
                {
                    a0 = a56;
                    break label1;
                }
                label16: {
                    RadixTrieIterDigitalElementArray a57 = null;
                    if(a8 == null)
                    {
                        break label16;
                    }
                    try
                    {
                        a57 = this.compareBitArray;
                    }
                    catch(Exception a58)
                    {
                        a0 = a58;
                        break label1;
                    }
                    try
                    {
                        a57.draw(a);
                    }
                    catch(Exception a59)
                    {
                        a0 = a59;
                        break label1;
                    }
                }
                label17: try
                {
                    a9 = this.currentNode;
                    break label17;
                }
                catch(Exception a60)
                {
                    a0 = a60;
                    break label1;
                }
                label18: {
                    RadixTrieIterNode a61 = null;
                    RadixTrieIterNode a62 = null;
                    java.awt.Rectangle a63 = null;
                    int i5 = 0;
                    java.awt.Point a64 = null;
                    RadixTrieIterNode a65 = null;
                    java.awt.Point a66 = null;
                    int i6 = 0;
                    RadixTrieIterNode a67 = null;
                    int i7 = 0;
                    int i8 = 0;
                    RadixTrieIterNode a68 = null;
                    int i9 = 0;
                    String s1 = null;
                    if(a9 == null)
                    {
                        break label18;
                    }
                    try
                    {
                        a61 = this.head;
                    }
                    catch(Exception a69)
                    {
                        a0 = a69;
                        break label1;
                    }
                    int i10 = a61 instanceof RadixTrieIterNullNode?1:0;
                    if(i10 != 0)
                    {
                        break label18;
                    }
                    try
                    {
                        a62 = this.head;
                    }
                    catch(Exception a70)
                    {
                        a0 = a70;
                        break label1;
                    }
                    try
                    {
                        a63 = a62.getRectangle();
                    }
                    catch(Exception a71)
                    {
                        a0 = a71;
                        break label1;
                    }
                    try
                    {
                        i5 = a63.y;
                    }
                    catch(Exception a72)
                    {
                        a0 = a72;
                        break label1;
                    }
                    try
                    {
                        a64 = new java.awt.Point(40, i5);
                    }
                    catch(Exception a73)
                    {
                        a0 = a73;
                        break label1;
                    }
                    try
                    {
                        a65 = this.currentNode;
                    }
                    catch(Exception a74)
                    {
                        a0 = a74;
                        break label1;
                    }
                    try
                    {
                        a66 = a65.getPosition();
                    }
                    catch(Exception a75)
                    {
                        a0 = a75;
                        break label1;
                    }
                    try
                    {
                        i6 = a66.x;
                    }
                    catch(Exception a76)
                    {
                        a0 = a76;
                        break label1;
                    }
                    try
                    {
                        a67 = this.currentNode;
                    }
                    catch(Exception a77)
                    {
                        a0 = a77;
                        break label1;
                    }
                    try
                    {
                        i7 = a67.getRadius();
                    }
                    catch(Exception a78)
                    {
                        a0 = a78;
                        break label1;
                    }
                    try
                    {
                        int i11 = i7 / 2;
                        int i12 = i6 - i11;
                        a66.x = i12;
                    }
                    catch(Exception a79)
                    {
                        a0 = a79;
                        break label1;
                    }
                    try
                    {
                        i8 = a66.y;
                    }
                    catch(Exception a80)
                    {
                        a0 = a80;
                        break label1;
                    }
                    try
                    {
                        a68 = this.currentNode;
                    }
                    catch(Exception a81)
                    {
                        a0 = a81;
                        break label1;
                    }
                    try
                    {
                        i9 = a68.getRadius();
                    }
                    catch(Exception a82)
                    {
                        a0 = a82;
                        break label1;
                    }
                    try
                    {
                        int i13 = i9 / 2;
                        int i14 = i8 - i13;
                        a66.y = i14;
                    }
                    catch(Exception a83)
                    {
                        a0 = a83;
                        break label1;
                    }
                    try
                    {
                        s1 = localization.Messages.getString("RadixTrieIterCanvas.2");
                    }
                    catch(Exception a84)
                    {
                        a0 = a84;
                        break label1;
                    }
                    try
                    {
                        this.drawLine(a, a64, a66, s1);
                    }
                    catch(Exception a85)
                    {
                        a0 = a85;
                        break label1;
                    }
                }
                label19: try
                {
                    a10 = this.head;
                    break label19;
                }
                catch(Exception a86)
                {
                    a0 = a86;
                    break label1;
                }
                label20: try
                {
                    a11 = this.head;
                    break label20;
                }
                catch(Exception a87)
                {
                    a0 = a87;
                    break label1;
                }
                label21: try
                {
                    a12 = a11.getRectangle();
                    break label21;
                }
                catch(Exception a88)
                {
                    a0 = a88;
                    break label1;
                }
                label22: try
                {
                    i = a12.y;
                    break label22;
                }
                catch(Exception a89)
                {
                    a0 = a89;
                    break label1;
                }
                label23: try
                {
                    int i15 = i + 20;
                    a13 = new java.awt.Point(5, i15);
                    break label23;
                }
                catch(Exception a90)
                {
                    a0 = a90;
                    break label1;
                }
                label24: try
                {
                    this.drawInsertedData(a, a10, a13);
                    break label24;
                }
                catch(Exception a91)
                {
                    a0 = a91;
                    break label1;
                }
                break label25;
            }
            java.io.PrintStream a92 = System.out;
            a92.println("Caught this exception");
            a0.printStackTrace();
        }
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        Object a0 = a.paramObj;
        label0: {
            int i = 0;
            int i0 = 0;
            int i1 = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Dimension a1 = this.getParentSize();
            int i5 = a1.height;
            java.awt.Dimension a2 = this.getParentSize();
            int i6 = a2.width;
            int i7 = i6 / 2;
            Object a3 = a.paramObj;
            RadixTrieIter dummy = (RadixTrieIter)a3;
            RadixTrieIter a4 = (RadixTrieIter)a3;
            int i8 = a4.getMostSignificantBit();
            this.mostSignificantBit = i8;
            int i9 = a4.getCurrentLevel();
            this.currentLevel = i9;
            int i10 = this.currentLevel;
            if(i10 < 0)
            {
                this.levelNode = null;
            }
            else
            {
                int i11 = this.currentLevel;
                Integer a5 = new Integer(i11);
                com.cim.AIA.Node a6 = new com.cim.AIA.Node((Object)a5, 0);
                this.levelNode = a6;
            }
            com.cim.AIA.ElementArray a7 = a4.getInsertData();
            this.insertData = a7;
            com.cim.AIA.ElementArray a8 = this.insertData;
            if(a8 == null)
            {
                i = 20;
            }
            else
            {
                com.cim.AIA.ElementArray a9 = this.insertData;
                com.cim.AIA.ElementArray a10 = this.insertData;
                int i12 = a10.getWidth();
                int i13 = i12 / 2;
                int i14 = i7 - i13;
                a9.setLocation(i14, 20);
                com.cim.AIA.ElementArray a11 = this.insertData;
                int i15 = a11.getHeight();
                int i16 = i15 + 20;
                int i17 = 20 + i16;
                i = i17;
            }
            RadixTrieIterDigitalElementArray a12 = a4.getInsertBitArray();
            this.insertBitArray = a12;
            RadixTrieIterDigitalElementArray a13 = this.insertBitArray;
            if(a13 == null)
            {
                i0 = i;
            }
            else
            {
                RadixTrieIterDigitalElementArray a14 = this.insertBitArray;
                String s = localization.Messages.getString("RadixTrieIterCanvas.4");
                a14.setLabel(s);
                RadixTrieIterDigitalElementArray a15 = this.insertBitArray;
                RadixTrieIterDigitalElementArray a16 = this.insertBitArray;
                int i18 = a16.getWidth();
                int i19 = i18 / 2;
                int i20 = i7 - i19;
                java.awt.Point a17 = new java.awt.Point(i20, i);
                a15.setPosition(a17);
                RadixTrieIterDigitalElementArray a18 = this.insertBitArray;
                int i21 = a18.getHeight();
                int i22 = i21 + 20;
                int i23 = i + i22;
                i0 = i23;
            }
            RadixTrieIterDigitalElementArray a19 = a4.getCompareBitArray();
            this.compareBitArray = a19;
            RadixTrieIterDigitalElementArray a20 = this.compareBitArray;
            if(a20 == null)
            {
                i1 = i0;
            }
            else
            {
                RadixTrieIterDigitalElementArray a21 = this.compareBitArray;
                String s0 = localization.Messages.getString("RadixTrieIterCanvas.5");
                a21.setLabel(s0);
                RadixTrieIterDigitalElementArray a22 = this.compareBitArray;
                RadixTrieIterDigitalElementArray a23 = this.compareBitArray;
                int i24 = a23.getWidth();
                int i25 = i24 / 2;
                int i26 = i7 - i25;
                java.awt.Point a24 = new java.awt.Point(i26, i0);
                a22.setPosition(a24);
                RadixTrieIterDigitalElementArray a25 = this.compareBitArray;
                int i27 = a25.getHeight();
                int i28 = i27 + 20;
                int i29 = i0 + i28;
                i1 = i29;
            }
            RadixTrieIterNode a26 = a4.getCurrentNode();
            this.currentNode = a26;
            RadixTrieIterNode a27 = a4.getHead();
            this.head = a27;
            RadixTrieIterNode a28 = this.head;
            label1: {
                if(a28 == null)
                {
                    i2 = i1;
                    break label1;
                }
                int i30 = this.treeTop;
                label3: {
                    label2: {
                        if(i30 >= i1)
                        {
                            break label2;
                        }
                        this.treeTop = i1;
                        break label3;
                    }
                    com.cim.AIA.ElementArray a29 = this.insertData;
                    if(a29 == null)
                    {
                        this.treeTop = i1;
                    }
                }
                RadixTrieIterNode a30 = this.head;
                RadixTrieIterNode a31 = this.head;
                java.awt.Rectangle a32 = a31.getRectangle();
                int i31 = a32.width;
                int i32 = i31 / 2;
                int i33 = i7 - i32;
                int i34 = this.treeTop;
                java.awt.Point a33 = new java.awt.Point(i33, i34);
                a30.setPosition(a33);
                RadixTrieIterNode a34 = this.head;
                java.awt.Rectangle a35 = a34.getRectangle();
                int i35 = a35.x;
                int i36 = this.insertedDataWidth;
                if(i35 < i36)
                {
                    RadixTrieIterNode a36 = this.head;
                    RadixTrieIterNode a37 = this.head;
                    java.awt.Point a38 = a37.getPosition();
                    int i37 = a38.x;
                    int i38 = i37 - i35;
                    int i39 = this.insertedDataWidth;
                    int i40 = i38 + i39;
                    RadixTrieIterNode a39 = this.head;
                    java.awt.Point a40 = a39.getPosition();
                    int i41 = a40.y;
                    java.awt.Point a41 = new java.awt.Point(i40, i41);
                    a36.setPosition(a41);
                }
                RadixTrieIterNode a42 = this.head;
                java.awt.Rectangle a43 = a42.getRectangle();
                int i42 = a43.y;
                RadixTrieIterNode a44 = this.head;
                java.awt.Rectangle a45 = a44.getRectangle();
                int i43 = a45.height;
                int i44 = i42 + i43;
                int i45 = i44 + 20;
                int i46 = i1 + i45;
                i2 = i46;
            }
            RadixTrieIterDigitalElementArray a46 = a4.getSearchBitArray();
            this.searchBitArray = a46;
            RadixTrieIterDigitalElementArray a47 = this.searchBitArray;
            if(a47 == null)
            {
                i3 = i2;
            }
            else
            {
                RadixTrieIterDigitalElementArray a48 = this.searchBitArray;
                String s1 = localization.Messages.getString("RadixTrieIterCanvas.6");
                a48.setLabel(s1);
                RadixTrieIterDigitalElementArray a49 = this.searchBitArray;
                RadixTrieIterDigitalElementArray a50 = this.searchBitArray;
                int i47 = a50.getWidth();
                int i48 = i47 / 2;
                int i49 = i7 - i48;
                java.awt.Point a51 = new java.awt.Point(i49, i2);
                a49.setPosition(a51);
                RadixTrieIterDigitalElementArray a52 = this.searchBitArray;
                int i50 = a52.getHeight();
                int i51 = i50 + 20;
                int i52 = i2 + i51;
                i3 = i52;
            }
            RadixTrieIterDigitalElementArray a53 = a4.getFinalCompareBitArray();
            this.finalCompareBitArray = a53;
            RadixTrieIterDigitalElementArray a54 = this.finalCompareBitArray;
            if(a54 == null)
            {
                i4 = i3;
            }
            else
            {
                RadixTrieIterDigitalElementArray a55 = this.finalCompareBitArray;
                String s2 = localization.Messages.getString("RadixTrieIterCanvas.7");
                a55.setLabel(s2);
                RadixTrieIterDigitalElementArray a56 = this.finalCompareBitArray;
                RadixTrieIterDigitalElementArray a57 = this.finalCompareBitArray;
                int i53 = a57.getWidth();
                int i54 = i53 / 2;
                int i55 = i7 - i54;
                java.awt.Point a58 = new java.awt.Point(i55, i3);
                a56.setPosition(a58);
                RadixTrieIterDigitalElementArray a59 = this.finalCompareBitArray;
                int i56 = a59.getHeight();
                int i57 = i56 + 20;
                int i58 = i3 + i57;
                i4 = i58;
            }
            com.cim.AIA.ElementArray a60 = a4.getSearchData();
            this.searchData = a60;
            com.cim.AIA.ElementArray a61 = this.searchData;
            if(a61 != null)
            {
                com.cim.AIA.ElementArray a62 = this.searchData;
                com.cim.AIA.ElementArray a63 = this.searchData;
                int i59 = a63.getWidth();
                int i60 = i59 / 2;
                int i61 = i7 - i60;
                a62.setLocation(i61, i4);
                com.cim.AIA.ElementArray a64 = this.searchData;
                int i62 = a64.getHeight();
            }
            java.util.Vector a65 = a4.getInsertedData();
            this.insertedData = a65;
            this.repaint();
        }
    }
    
    private void drawLine(java.awt.Graphics a, java.awt.Point a0, java.awt.Point a1, String s)
    {
        int i = a0.x;
        int i0 = a0.y;
        int i1 = a1.x;
        int i2 = a1.y;
        com.cim.AIA.Line a2 = new com.cim.AIA.Line(i, i0, i1, i2);
        a2.setLabel(s);
        a2.setDistanceFromStartForArrowHead(-3);
        a2.setDistanceFromStartForLabel(-1);
        java.awt.FontMetrics a3 = a.getFontMetrics();
        int i3 = a3.stringWidth(s);
        int i4 = 0 - i3;
        int i5 = i4 / 2;
        a2.setXLabelOffset(i5);
        a2.showArrowHead(true);
        java.awt.Color a4 = RadixTrieIterColors.POINTER_COLOR;
        a2.setColor(a4);
        a2.draw(a);
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    private void drawInsertedData(java.awt.Graphics a, RadixTrieIterNode a0, java.awt.Point a1)
    {
        java.util.Vector a2 = this.insertedData;
        label0: {
            if(a2 == null)
            {
                break label0;
            }
            int i = 0;
            label1: while(true)
            {
                java.util.Vector a3 = this.insertedData;
                int i0 = a3.size();
                if(i >= i0)
                {
                    break;
                }
                java.util.Vector a4 = this.insertedData;
                Object a5 = a4.elementAt(i);
                RadixTrieIterExternalNode dummy = (RadixTrieIterExternalNode)a5;
                RadixTrieIterExternalNode a6 = (RadixTrieIterExternalNode)a5;
                int i1 = a6.getKey();
                Integer a7 = new Integer(i1);
                com.cim.AIA.Node a8 = new com.cim.AIA.Node((Object)a7, 0);
                a8.setPosition(a1);
                a8.draw(a);
                int i2 = a1.x;
                int i3 = a8.getWidth();
                int i4 = i2 + i3;
                int i5 = i4;
                int i6 = 0;
                while(true)
                {
                    int i7 = this.mostSignificantBit;
                    if(i6 > i7)
                    {
                        java.awt.Color a9 = java.awt.Color.black;
                        a.setColor(a9);
                        int i8 = a1.y;
                        int i9 = a8.getHeight();
                        int i10 = i8 + i9;
                        a1.y = i10;
                        int i11 = i + 1;
                        i = i11;
                        continue label1;
                    }
                    StringBuilder a10 = new StringBuilder();
                    StringBuilder a11 = a10.append("");
                    int i12 = a7.intValue();
                    int i13 = this.getBit(i6, i12);
                    StringBuilder a12 = a11.append(i13);
                    String s = a12.toString();
                    int i14 = a6.getLevel();
                    if(i6 >= i14)
                    {
                        java.awt.Color a13 = RadixTrieIterColors.DEFAULT_BIT_NOT_USEFUL_COLOR;
                        a.setColor(a13);
                    }
                    else
                    {
                        java.awt.Color a14 = RadixTrieIterColors.DEFAULT_BIT_USEFUL_COLOR;
                        a.setColor(a14);
                    }
                    int i15 = a1.y;
                    int i16 = a8.getHeight();
                    int i17 = i16 / 2;
                    int i18 = i15 + i17;
                    java.awt.FontMetrics a15 = a.getFontMetrics();
                    int i19 = a15.getHeight();
                    int i20 = i19 / 2;
                    int i21 = i18 + i20;
                    a.drawString(s, i5, i21);
                    java.awt.FontMetrics a16 = a.getFontMetrics();
                    int i22 = a16.stringWidth(s);
                    int i23 = i5 + i22;
                    int i24 = i6 + 1;
                    i5 = i23;
                    i6 = i24;
                }
            }
        }
    }
    
    private byte getBit(int i, int i0)
    {
        int i1 = this.mostSignificantBit;
        int i2 = i1 - i;
        int i3 = i0 >> i2;
        int i4 = i3 % 2;
        int i5 = (byte)i4;
        return (byte)i5;
    }
}