public class RadixTrieMultiCanvas extends com.cim.AIA.AlgorithmCanvas {
    private RadixTrieMultiNode head;
    private RadixTrieMultiNode currentNode;
    private RadixTrieMultiDigitalElementArray insertBitArray;
    private RadixTrieMultiDigitalElementArray searchBitArray;
    private RadixTrieMultiDigitalElementArray compareBitArray;
    private RadixTrieMultiDigitalElementArray finalCompareBitArray;
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
    
    public RadixTrieMultiCanvas()
    {
        super();
        this.insertedDataWidth = 70;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        label25: {
            Exception a0 = null;
            label1: {
                RadixTrieMultiNode a1 = null;
                com.cim.AIA.Node a2 = null;
                com.cim.AIA.ElementArray a3 = null;
                com.cim.AIA.ElementArray a4 = null;
                RadixTrieMultiDigitalElementArray a5 = null;
                RadixTrieMultiDigitalElementArray a6 = null;
                RadixTrieMultiDigitalElementArray a7 = null;
                RadixTrieMultiDigitalElementArray a8 = null;
                RadixTrieMultiNode a9 = null;
                RadixTrieMultiNode a10 = null;
                RadixTrieMultiNode a11 = null;
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
                    RadixTrieMultiNode a15 = null;
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
                        s = localization.Messages.getString("RadixTrieMultiCanvas.0");
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
                        s0 = localization.Messages.getString("RadixTrieMultiCanvas.1");
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
                    RadixTrieMultiDigitalElementArray a45 = null;
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
                    RadixTrieMultiDigitalElementArray a49 = null;
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
                    RadixTrieMultiDigitalElementArray a53 = null;
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
                    RadixTrieMultiDigitalElementArray a57 = null;
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
                    RadixTrieMultiNode a61 = null;
                    RadixTrieMultiNode a62 = null;
                    java.awt.Rectangle a63 = null;
                    int i5 = 0;
                    java.awt.Point a64 = null;
                    RadixTrieMultiNode a65 = null;
                    java.awt.Point a66 = null;
                    int i6 = 0;
                    RadixTrieMultiNode a67 = null;
                    int i7 = 0;
                    int i8 = 0;
                    RadixTrieMultiNode a68 = null;
                    int i9 = 0;
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
                    int i10 = a61 instanceof RadixTrieMultiNullNode?1:0;
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
                        this.drawLine(a, a64, a66, "Current Trie");
                    }
                    catch(Exception a84)
                    {
                        a0 = a84;
                        break label1;
                    }
                }
                label19: try
                {
                    a10 = this.head;
                    break label19;
                }
                catch(Exception a85)
                {
                    a0 = a85;
                    break label1;
                }
                label20: try
                {
                    a11 = this.head;
                    break label20;
                }
                catch(Exception a86)
                {
                    a0 = a86;
                    break label1;
                }
                label21: try
                {
                    a12 = a11.getRectangle();
                    break label21;
                }
                catch(Exception a87)
                {
                    a0 = a87;
                    break label1;
                }
                label22: try
                {
                    i = a12.y;
                    break label22;
                }
                catch(Exception a88)
                {
                    a0 = a88;
                    break label1;
                }
                label23: try
                {
                    int i15 = i + 20;
                    a13 = new java.awt.Point(5, i15);
                    break label23;
                }
                catch(Exception a89)
                {
                    a0 = a89;
                    break label1;
                }
                label24: try
                {
                    this.drawInsertedData(a, a10, a13);
                    break label24;
                }
                catch(Exception a90)
                {
                    a0 = a90;
                    break label1;
                }
                break label25;
            }
            java.io.PrintStream a91 = System.out;
            a91.println("Caught this exception");
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
            RadixTrieMulti dummy = (RadixTrieMulti)a3;
            RadixTrieMulti a4 = (RadixTrieMulti)a3;
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
                int i12 = RadixTrieMulti.NO_OF_BITS;
                int i13 = i11 / i12;
                Integer a5 = new Integer(i13);
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
                int i14 = a10.getWidth();
                int i15 = i14 / 2;
                int i16 = i7 - i15;
                a9.setLocation(i16, 20);
                com.cim.AIA.ElementArray a11 = this.insertData;
                int i17 = a11.getHeight();
                int i18 = i17 + 20;
                int i19 = 20 + i18;
                i = i19;
            }
            RadixTrieMultiDigitalElementArray a12 = a4.getInsertBitArray();
            this.insertBitArray = a12;
            RadixTrieMultiDigitalElementArray a13 = this.insertBitArray;
            if(a13 == null)
            {
                i0 = i;
            }
            else
            {
                RadixTrieMultiDigitalElementArray a14 = this.insertBitArray;
                String s = localization.Messages.getString("RadixTrieMultiCanvas.4");
                a14.setLabel(s);
                RadixTrieMultiDigitalElementArray a15 = this.insertBitArray;
                RadixTrieMultiDigitalElementArray a16 = this.insertBitArray;
                int i20 = a16.getWidth();
                int i21 = i20 / 2;
                int i22 = i7 - i21;
                java.awt.Point a17 = new java.awt.Point(i22, i);
                a15.setPosition(a17);
                RadixTrieMultiDigitalElementArray a18 = this.insertBitArray;
                int i23 = a18.getHeight();
                int i24 = i23 + 20;
                int i25 = i + i24;
                i0 = i25;
            }
            RadixTrieMultiDigitalElementArray a19 = a4.getCompareBitArray();
            this.compareBitArray = a19;
            RadixTrieMultiDigitalElementArray a20 = this.compareBitArray;
            if(a20 == null)
            {
                i1 = i0;
            }
            else
            {
                RadixTrieMultiDigitalElementArray a21 = this.compareBitArray;
                String s0 = localization.Messages.getString("RadixTrieMultiCanvas.5");
                a21.setLabel(s0);
                RadixTrieMultiDigitalElementArray a22 = this.compareBitArray;
                RadixTrieMultiDigitalElementArray a23 = this.compareBitArray;
                int i26 = a23.getWidth();
                int i27 = i26 / 2;
                int i28 = i7 - i27;
                java.awt.Point a24 = new java.awt.Point(i28, i0);
                a22.setPosition(a24);
                RadixTrieMultiDigitalElementArray a25 = this.compareBitArray;
                int i29 = a25.getHeight();
                int i30 = i29 + 20;
                int i31 = i0 + i30;
                i1 = i31;
            }
            RadixTrieMultiNode a26 = a4.getCurrentNode();
            this.currentNode = a26;
            RadixTrieMultiNode a27 = a4.getHead();
            this.head = a27;
            RadixTrieMultiNode a28 = this.head;
            label1: {
                if(a28 == null)
                {
                    i2 = i1;
                    break label1;
                }
                int i32 = this.treeTop;
                label3: {
                    label2: {
                        if(i32 >= i1)
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
                RadixTrieMultiNode a30 = this.head;
                RadixTrieMultiNode a31 = this.head;
                java.awt.Rectangle a32 = a31.getRectangle();
                int i33 = a32.width;
                int i34 = i33 / 2;
                int i35 = i7 - i34;
                int i36 = this.treeTop;
                java.awt.Point a33 = new java.awt.Point(i35, i36);
                a30.setPosition(a33);
                RadixTrieMultiNode a34 = this.head;
                java.awt.Rectangle a35 = a34.getRectangle();
                int i37 = a35.x;
                int i38 = this.insertedDataWidth;
                if(i37 < i38)
                {
                    RadixTrieMultiNode a36 = this.head;
                    RadixTrieMultiNode a37 = this.head;
                    java.awt.Point a38 = a37.getPosition();
                    int i39 = a38.x;
                    int i40 = i39 - i37;
                    int i41 = this.insertedDataWidth;
                    int i42 = i40 + i41;
                    RadixTrieMultiNode a39 = this.head;
                    java.awt.Point a40 = a39.getPosition();
                    int i43 = a40.y;
                    java.awt.Point a41 = new java.awt.Point(i42, i43);
                    a36.setPosition(a41);
                }
                RadixTrieMultiNode a42 = this.head;
                java.awt.Rectangle a43 = a42.getRectangle();
                int i44 = a43.y;
                RadixTrieMultiNode a44 = this.head;
                java.awt.Rectangle a45 = a44.getRectangle();
                int i45 = a45.height;
                int i46 = i44 + i45;
                int i47 = i46 + 20;
                int i48 = i1 + i47;
                i2 = i48;
            }
            RadixTrieMultiDigitalElementArray a46 = a4.getSearchBitArray();
            this.searchBitArray = a46;
            RadixTrieMultiDigitalElementArray a47 = this.searchBitArray;
            if(a47 == null)
            {
                i3 = i2;
            }
            else
            {
                RadixTrieMultiDigitalElementArray a48 = this.searchBitArray;
                String s1 = localization.Messages.getString("RadixTrieMultiCanvas.6");
                a48.setLabel(s1);
                RadixTrieMultiDigitalElementArray a49 = this.searchBitArray;
                RadixTrieMultiDigitalElementArray a50 = this.searchBitArray;
                int i49 = a50.getWidth();
                int i50 = i49 / 2;
                int i51 = i7 - i50;
                java.awt.Point a51 = new java.awt.Point(i51, i2);
                a49.setPosition(a51);
                RadixTrieMultiDigitalElementArray a52 = this.searchBitArray;
                int i52 = a52.getHeight();
                int i53 = i52 + 20;
                int i54 = i2 + i53;
                i3 = i54;
            }
            RadixTrieMultiDigitalElementArray a53 = a4.getFinalCompareBitArray();
            this.finalCompareBitArray = a53;
            RadixTrieMultiDigitalElementArray a54 = this.finalCompareBitArray;
            if(a54 == null)
            {
                i4 = i3;
            }
            else
            {
                RadixTrieMultiDigitalElementArray a55 = this.finalCompareBitArray;
                String s2 = localization.Messages.getString("RadixTrieMultiCanvas.7");
                a55.setLabel(s2);
                RadixTrieMultiDigitalElementArray a56 = this.finalCompareBitArray;
                RadixTrieMultiDigitalElementArray a57 = this.finalCompareBitArray;
                int i55 = a57.getWidth();
                int i56 = i55 / 2;
                int i57 = i7 - i56;
                java.awt.Point a58 = new java.awt.Point(i57, i3);
                a56.setPosition(a58);
                RadixTrieMultiDigitalElementArray a59 = this.finalCompareBitArray;
                int i58 = a59.getHeight();
                int i59 = i58 + 20;
                int i60 = i3 + i59;
                i4 = i60;
            }
            com.cim.AIA.ElementArray a60 = a4.getSearchData();
            this.searchData = a60;
            com.cim.AIA.ElementArray a61 = this.searchData;
            if(a61 != null)
            {
                com.cim.AIA.ElementArray a62 = this.searchData;
                com.cim.AIA.ElementArray a63 = this.searchData;
                int i61 = a63.getWidth();
                int i62 = i61 / 2;
                int i63 = i7 - i62;
                a62.setLocation(i63, i4);
                com.cim.AIA.ElementArray a64 = this.searchData;
                int i64 = a64.getHeight();
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
        java.awt.Color a4 = RadixTrieMultiColors.POINTER_COLOR;
        a2.setColor(a4);
        a2.draw(a);
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    private void drawInsertedData(java.awt.Graphics a, RadixTrieMultiNode a0, java.awt.Point a1)
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
                RadixTrieMultiExternalNode dummy = (RadixTrieMultiExternalNode)a5;
                RadixTrieMultiExternalNode a6 = (RadixTrieMultiExternalNode)a5;
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
                        java.awt.Color a13 = RadixTrieMultiColors.DEFAULT_BIT_NOT_USEFUL_COLOR;
                        a.setColor(a13);
                    }
                    else
                    {
                        java.awt.Color a14 = RadixTrieMultiColors.DEFAULT_BIT_USEFUL_COLOR;
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