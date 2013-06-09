public class RadixTrieCanvas extends com.cim.AIA.AlgorithmCanvas {
    private RadixTrieNode head;
    private RadixTrieNode currentNode;
    private RadixTrieDigitalElementArray insertBitArray;
    private RadixTrieDigitalElementArray searchBitArray;
    private RadixTrieDigitalElementArray compareBitArray;
    private com.cim.AIA.ElementArray insertData;
    private com.cim.AIA.ElementArray searchData;
    private int treeTop;
    private int mostSignificantBit;
    private java.util.Vector insertedData;
    private int insertedDataWidth;
    final private static int Y_GAP = 20;
    
    public RadixTrieCanvas()
    {
        super();
        this.insertedDataWidth = 70;
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        label21: {
            Exception a0 = null;
            label1: {
                RadixTrieNode a1 = null;
                com.cim.AIA.ElementArray a2 = null;
                com.cim.AIA.ElementArray a3 = null;
                RadixTrieDigitalElementArray a4 = null;
                RadixTrieDigitalElementArray a5 = null;
                RadixTrieDigitalElementArray a6 = null;
                RadixTrieNode a7 = null;
                RadixTrieNode a8 = null;
                RadixTrieNode a9 = null;
                java.awt.Rectangle a10 = null;
                int i = 0;
                java.awt.Point a11 = null;
                label0: try
                {
                    a1 = this.head;
                    break label0;
                }
                catch(Exception a12)
                {
                    a0 = a12;
                    break label1;
                }
                label2: {
                    RadixTrieNode a13 = null;
                    if(a1 == null)
                    {
                        break label2;
                    }
                    try
                    {
                        a13 = this.head;
                    }
                    catch(Exception a14)
                    {
                        a0 = a14;
                        break label1;
                    }
                    try
                    {
                        a13.draw(a);
                    }
                    catch(Exception a15)
                    {
                        a0 = a15;
                        break label1;
                    }
                }
                label3: try
                {
                    a2 = this.insertData;
                    break label3;
                }
                catch(Exception a16)
                {
                    a0 = a16;
                    break label1;
                }
                label4: {
                    com.cim.AIA.ElementArray a17 = null;
                    if(a2 == null)
                    {
                        break label4;
                    }
                    try
                    {
                        a17 = this.insertData;
                    }
                    catch(Exception a18)
                    {
                        a0 = a18;
                        break label1;
                    }
                    try
                    {
                        a17.draw(a);
                    }
                    catch(Exception a19)
                    {
                        a0 = a19;
                        break label1;
                    }
                }
                label5: try
                {
                    a3 = this.searchData;
                    break label5;
                }
                catch(Exception a20)
                {
                    a0 = a20;
                    break label1;
                }
                label6: {
                    com.cim.AIA.ElementArray a21 = null;
                    if(a3 == null)
                    {
                        break label6;
                    }
                    try
                    {
                        a21 = this.searchData;
                    }
                    catch(Exception a22)
                    {
                        a0 = a22;
                        break label1;
                    }
                    try
                    {
                        a21.draw(a);
                    }
                    catch(Exception a23)
                    {
                        a0 = a23;
                        break label1;
                    }
                }
                label7: try
                {
                    a4 = this.insertBitArray;
                    break label7;
                }
                catch(Exception a24)
                {
                    a0 = a24;
                    break label1;
                }
                label8: {
                    RadixTrieDigitalElementArray a25 = null;
                    if(a4 == null)
                    {
                        break label8;
                    }
                    try
                    {
                        a25 = this.insertBitArray;
                    }
                    catch(Exception a26)
                    {
                        a0 = a26;
                        break label1;
                    }
                    try
                    {
                        a25.draw(a);
                    }
                    catch(Exception a27)
                    {
                        a0 = a27;
                        break label1;
                    }
                }
                label9: try
                {
                    a5 = this.searchBitArray;
                    break label9;
                }
                catch(Exception a28)
                {
                    a0 = a28;
                    break label1;
                }
                label10: {
                    RadixTrieDigitalElementArray a29 = null;
                    if(a5 == null)
                    {
                        break label10;
                    }
                    try
                    {
                        a29 = this.searchBitArray;
                    }
                    catch(Exception a30)
                    {
                        a0 = a30;
                        break label1;
                    }
                    try
                    {
                        a29.draw(a);
                    }
                    catch(Exception a31)
                    {
                        a0 = a31;
                        break label1;
                    }
                }
                label11: try
                {
                    a6 = this.compareBitArray;
                    break label11;
                }
                catch(Exception a32)
                {
                    a0 = a32;
                    break label1;
                }
                label12: {
                    RadixTrieDigitalElementArray a33 = null;
                    if(a6 == null)
                    {
                        break label12;
                    }
                    try
                    {
                        a33 = this.compareBitArray;
                    }
                    catch(Exception a34)
                    {
                        a0 = a34;
                        break label1;
                    }
                    try
                    {
                        a33.draw(a);
                    }
                    catch(Exception a35)
                    {
                        a0 = a35;
                        break label1;
                    }
                }
                label13: try
                {
                    a7 = this.currentNode;
                    break label13;
                }
                catch(Exception a36)
                {
                    a0 = a36;
                    break label1;
                }
                label14: {
                    RadixTrieNode a37 = null;
                    RadixTrieNode a38 = null;
                    java.awt.Rectangle a39 = null;
                    int i0 = 0;
                    java.awt.Point a40 = null;
                    RadixTrieNode a41 = null;
                    java.awt.Point a42 = null;
                    int i1 = 0;
                    RadixTrieNode a43 = null;
                    int i2 = 0;
                    int i3 = 0;
                    RadixTrieNode a44 = null;
                    int i4 = 0;
                    String s = null;
                    if(a7 == null)
                    {
                        break label14;
                    }
                    try
                    {
                        a37 = this.head;
                    }
                    catch(Exception a45)
                    {
                        a0 = a45;
                        break label1;
                    }
                    int i5 = a37 instanceof RadixTrieNullNode?1:0;
                    if(i5 != 0)
                    {
                        break label14;
                    }
                    try
                    {
                        a38 = this.head;
                    }
                    catch(Exception a46)
                    {
                        a0 = a46;
                        break label1;
                    }
                    try
                    {
                        a39 = a38.getRectangle();
                    }
                    catch(Exception a47)
                    {
                        a0 = a47;
                        break label1;
                    }
                    try
                    {
                        i0 = a39.y;
                    }
                    catch(Exception a48)
                    {
                        a0 = a48;
                        break label1;
                    }
                    try
                    {
                        a40 = new java.awt.Point(40, i0);
                    }
                    catch(Exception a49)
                    {
                        a0 = a49;
                        break label1;
                    }
                    try
                    {
                        a41 = this.currentNode;
                    }
                    catch(Exception a50)
                    {
                        a0 = a50;
                        break label1;
                    }
                    try
                    {
                        a42 = a41.getPosition();
                    }
                    catch(Exception a51)
                    {
                        a0 = a51;
                        break label1;
                    }
                    try
                    {
                        i1 = a42.x;
                    }
                    catch(Exception a52)
                    {
                        a0 = a52;
                        break label1;
                    }
                    try
                    {
                        a43 = this.currentNode;
                    }
                    catch(Exception a53)
                    {
                        a0 = a53;
                        break label1;
                    }
                    try
                    {
                        i2 = a43.getRadius();
                    }
                    catch(Exception a54)
                    {
                        a0 = a54;
                        break label1;
                    }
                    try
                    {
                        int i6 = i2 / 2;
                        int i7 = i1 - i6;
                        a42.x = i7;
                    }
                    catch(Exception a55)
                    {
                        a0 = a55;
                        break label1;
                    }
                    try
                    {
                        i3 = a42.y;
                    }
                    catch(Exception a56)
                    {
                        a0 = a56;
                        break label1;
                    }
                    try
                    {
                        a44 = this.currentNode;
                    }
                    catch(Exception a57)
                    {
                        a0 = a57;
                        break label1;
                    }
                    try
                    {
                        i4 = a44.getRadius();
                    }
                    catch(Exception a58)
                    {
                        a0 = a58;
                        break label1;
                    }
                    try
                    {
                        int i8 = i4 / 2;
                        int i9 = i3 - i8;
                        a42.y = i9;
                    }
                    catch(Exception a59)
                    {
                        a0 = a59;
                        break label1;
                    }
                    try
                    {
                        s = localization.Messages.getString("RadixTrieCanvas.0");
                    }
                    catch(Exception a60)
                    {
                        a0 = a60;
                        break label1;
                    }
                    try
                    {
                        this.drawLine(a, a40, a42, s);
                    }
                    catch(Exception a61)
                    {
                        a0 = a61;
                        break label1;
                    }
                }
                label15: try
                {
                    a8 = this.head;
                    break label15;
                }
                catch(Exception a62)
                {
                    a0 = a62;
                    break label1;
                }
                label16: try
                {
                    a9 = this.head;
                    break label16;
                }
                catch(Exception a63)
                {
                    a0 = a63;
                    break label1;
                }
                label17: try
                {
                    a10 = a9.getRectangle();
                    break label17;
                }
                catch(Exception a64)
                {
                    a0 = a64;
                    break label1;
                }
                label18: try
                {
                    i = a10.y;
                    break label18;
                }
                catch(Exception a65)
                {
                    a0 = a65;
                    break label1;
                }
                label19: try
                {
                    int i10 = i + 20;
                    a11 = new java.awt.Point(5, i10);
                    break label19;
                }
                catch(Exception a66)
                {
                    a0 = a66;
                    break label1;
                }
                label20: try
                {
                    this.drawInsertedData(a, a8, a11);
                    break label20;
                }
                catch(Exception a67)
                {
                    a0 = a67;
                    break label1;
                }
                break label21;
            }
            java.io.PrintStream a68 = System.out;
            a68.println("Caught this exception");
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
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Dimension a1 = this.getParentSize();
            int i4 = a1.width;
            int i5 = i4 / 2;
            Object a2 = a.paramObj;
            RadixTrie dummy = (RadixTrie)a2;
            RadixTrie a3 = (RadixTrie)a2;
            int i6 = a3.getMostSignificantBit();
            this.mostSignificantBit = i6;
            com.cim.AIA.ElementArray a4 = a3.getInsertData();
            this.insertData = a4;
            com.cim.AIA.ElementArray a5 = this.insertData;
            if(a5 == null)
            {
                i = 20;
            }
            else
            {
                com.cim.AIA.ElementArray a6 = this.insertData;
                com.cim.AIA.ElementArray a7 = this.insertData;
                int i7 = a7.getWidth();
                int i8 = i7 / 2;
                int i9 = i5 - i8;
                a6.setLocation(i9, 20);
                com.cim.AIA.ElementArray a8 = this.insertData;
                int i10 = a8.getHeight();
                int i11 = i10 + 20;
                int i12 = 20 + i11;
                i = i12;
            }
            RadixTrieDigitalElementArray a9 = a3.getInsertBitArray();
            this.insertBitArray = a9;
            RadixTrieDigitalElementArray a10 = this.insertBitArray;
            if(a10 == null)
            {
                i0 = i;
            }
            else
            {
                RadixTrieDigitalElementArray a11 = this.insertBitArray;
                String s = localization.Messages.getString("RadixTrieCanvas.2");
                a11.setLabel(s);
                RadixTrieDigitalElementArray a12 = this.insertBitArray;
                RadixTrieDigitalElementArray a13 = this.insertBitArray;
                int i13 = a13.getWidth();
                int i14 = i13 / 2;
                int i15 = i5 - i14;
                java.awt.Point a14 = new java.awt.Point(i15, i);
                a12.setPosition(a14);
                RadixTrieDigitalElementArray a15 = this.insertBitArray;
                int i16 = a15.getHeight();
                int i17 = i16 + 20;
                int i18 = i + i17;
                i0 = i18;
            }
            RadixTrieDigitalElementArray a16 = a3.getCompareBitArray();
            this.compareBitArray = a16;
            RadixTrieDigitalElementArray a17 = this.compareBitArray;
            if(a17 == null)
            {
                i1 = i0;
            }
            else
            {
                RadixTrieDigitalElementArray a18 = this.compareBitArray;
                String s0 = localization.Messages.getString("RadixTrieCanvas.3");
                a18.setLabel(s0);
                RadixTrieDigitalElementArray a19 = this.compareBitArray;
                RadixTrieDigitalElementArray a20 = this.compareBitArray;
                int i19 = a20.getWidth();
                int i20 = i19 / 2;
                int i21 = i5 - i20;
                java.awt.Point a21 = new java.awt.Point(i21, i0);
                a19.setPosition(a21);
                RadixTrieDigitalElementArray a22 = this.compareBitArray;
                int i22 = a22.getHeight();
                int i23 = i22 + 20;
                int i24 = i0 + i23;
                i1 = i24;
            }
            RadixTrieNode a23 = a3.getCurrentNode();
            this.currentNode = a23;
            RadixTrieNode a24 = a3.getHead();
            this.head = a24;
            RadixTrieNode a25 = this.head;
            label1: {
                if(a25 == null)
                {
                    i2 = i1;
                    break label1;
                }
                int i25 = this.treeTop;
                label3: {
                    label2: {
                        if(i25 >= i1)
                        {
                            break label2;
                        }
                        this.treeTop = i1;
                        break label3;
                    }
                    com.cim.AIA.ElementArray a26 = this.insertData;
                    if(a26 == null)
                    {
                        this.treeTop = i1;
                    }
                }
                RadixTrieNode a27 = this.head;
                RadixTrieNode a28 = this.head;
                java.awt.Rectangle a29 = a28.getRectangle();
                int i26 = a29.width;
                int i27 = i26 / 2;
                int i28 = i5 - i27;
                int i29 = this.treeTop;
                java.awt.Point a30 = new java.awt.Point(i28, i29);
                a27.setPosition(a30);
                RadixTrieNode a31 = this.head;
                java.awt.Rectangle a32 = a31.getRectangle();
                int i30 = a32.x;
                int i31 = this.insertedDataWidth;
                if(i30 < i31)
                {
                    RadixTrieNode a33 = this.head;
                    RadixTrieNode a34 = this.head;
                    java.awt.Point a35 = a34.getPosition();
                    int i32 = a35.x;
                    int i33 = i32 - i30;
                    int i34 = this.insertedDataWidth;
                    int i35 = i33 + i34;
                    RadixTrieNode a36 = this.head;
                    java.awt.Point a37 = a36.getPosition();
                    int i36 = a37.y;
                    java.awt.Point a38 = new java.awt.Point(i35, i36);
                    a33.setPosition(a38);
                }
                RadixTrieNode a39 = this.head;
                java.awt.Rectangle a40 = a39.getRectangle();
                int i37 = a40.y;
                RadixTrieNode a41 = this.head;
                java.awt.Rectangle a42 = a41.getRectangle();
                int i38 = a42.height;
                int i39 = i37 + i38;
                int i40 = i39 + 20;
                int i41 = i1 + i40;
                i2 = i41;
            }
            RadixTrieDigitalElementArray a43 = a3.getSearchBitArray();
            this.searchBitArray = a43;
            RadixTrieDigitalElementArray a44 = this.searchBitArray;
            if(a44 == null)
            {
                i3 = i2;
            }
            else
            {
                RadixTrieDigitalElementArray a45 = this.searchBitArray;
                String s1 = localization.Messages.getString("RadixTrieCanvas.4");
                a45.setLabel(s1);
                RadixTrieDigitalElementArray a46 = this.searchBitArray;
                RadixTrieDigitalElementArray a47 = this.searchBitArray;
                int i42 = a47.getWidth();
                int i43 = i42 / 2;
                int i44 = i5 - i43;
                java.awt.Point a48 = new java.awt.Point(i44, i2);
                a46.setPosition(a48);
                RadixTrieDigitalElementArray a49 = this.searchBitArray;
                int i45 = a49.getHeight();
                int i46 = i45 + 20;
                int i47 = i2 + i46;
                i3 = i47;
            }
            com.cim.AIA.ElementArray a50 = a3.getSearchData();
            this.searchData = a50;
            com.cim.AIA.ElementArray a51 = this.searchData;
            if(a51 != null)
            {
                com.cim.AIA.ElementArray a52 = this.searchData;
                com.cim.AIA.ElementArray a53 = this.searchData;
                int i48 = a53.getWidth();
                int i49 = i48 / 2;
                int i50 = i5 - i49;
                a52.setLocation(i50, i3);
                com.cim.AIA.ElementArray a54 = this.searchData;
                int i51 = a54.getHeight();
            }
            java.util.Vector a55 = a3.getInsertedData();
            this.insertedData = a55;
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
        java.awt.Color a4 = RadixTrieColors.POINTER_COLOR;
        a2.setColor(a4);
        a2.draw(a);
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    private void drawInsertedData(java.awt.Graphics a, RadixTrieNode a0, java.awt.Point a1)
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
                RadixTrieExternalNode dummy = (RadixTrieExternalNode)a5;
                RadixTrieExternalNode a6 = (RadixTrieExternalNode)a5;
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
                        java.awt.Color a13 = RadixTrieColors.DEFAULT_BIT_NOT_USEFUL_COLOR;
                        a.setColor(a13);
                    }
                    else
                    {
                        java.awt.Color a14 = RadixTrieColors.DEFAULT_BIT_USEFUL_COLOR;
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