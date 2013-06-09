public class SortedListCanvas extends com.cim.AIA.AlgorithmCanvas {
    private SList linkedList;
    private com.cim.AIA.ElementArray insertElementArray;
    private com.cim.AIA.ElementArray searchElementArray;
    private com.cim.AIA.Node ptr;
    private com.cim.AIA.Node tailptr;
    private com.cim.AIA.Node highlightedNode;
    final private static java.awt.Color POINTER_COLOR;
    final private static java.awt.Color TEXT_COLOR;
    final private static String SEARCH_LABEL;
    final private static String PTR_LABEL;
    final private static String TAILPTR_LABEL;
    final private static int GAP = 30;
    final private static int ELEMENT_WIDTH = 20;
    
    public SortedListCanvas()
    {
        super();
    }
    
    public void displayAlgorithm(java.awt.Graphics a)
    {
        com.cim.AIA.ElementArray a0 = this.insertElementArray;
        if(a0 != null)
        {
            com.cim.AIA.ElementArray a1 = this.insertElementArray;
            a1.draw(a);
        }
        com.cim.AIA.ElementArray a2 = this.searchElementArray;
        if(a2 != null)
        {
            com.cim.AIA.ElementArray a3 = this.searchElementArray;
            a3.draw(a);
            String s = SortedListCanvas.SEARCH_LABEL;
            java.awt.Dimension a4 = this.getParentSize();
            int i = a4.width;
            int i0 = i / 2;
            java.awt.FontMetrics a5 = a.getFontMetrics();
            String s0 = SortedListCanvas.SEARCH_LABEL;
            int i1 = a5.stringWidth(s0);
            int i2 = i1 / 2;
            int i3 = i0 - i2;
            com.cim.AIA.ElementArray a6 = this.searchElementArray;
            java.awt.Point a7 = a6.getLocation();
            int i4 = a7.y;
            a.drawString(s, i3, i4);
        }
        SList a8 = this.linkedList;
        label0: {
            if(a8 == null)
            {
                break label0;
            }
            this.draw(a);
            com.cim.AIA.Node a9 = this.ptr;
            if(a9 != null)
            {
                com.cim.AIA.Node a10 = this.ptr;
                int i5 = a10.getX();
                com.cim.AIA.Node a11 = this.ptr;
                int i6 = a11.getWidth();
                int i7 = i5 + i6;
                int i8 = i7 + 10;
                com.cim.AIA.Node a12 = this.ptr;
                int i9 = a12.getY();
                int i10 = i9 + 40;
                com.cim.AIA.Node a13 = this.ptr;
                int i11 = a13.getX();
                com.cim.AIA.Node a14 = this.ptr;
                int i12 = a14.getWidth();
                int i13 = i11 + i12;
                com.cim.AIA.Node a15 = this.ptr;
                int i14 = a15.getY();
                com.cim.AIA.Node a16 = this.ptr;
                int i15 = a16.getHeight();
                int i16 = i14 + i15;
                com.cim.AIA.Line a17 = new com.cim.AIA.Line(i8, i10, i13, i16);
                String s1 = SortedListCanvas.PTR_LABEL;
                a17.setLabel(s1);
                a17.setDistanceFromStartForArrowHead(-3);
                a17.setDistanceFromStartForLabel(-1);
                java.awt.FontMetrics a18 = a.getFontMetrics();
                String s2 = SortedListCanvas.PTR_LABEL;
                int i17 = a18.stringWidth(s2);
                int i18 = 0 - i17;
                int i19 = i18 / 2;
                a17.setXLabelOffset(i19);
                java.awt.FontMetrics a19 = a.getFontMetrics();
                int i20 = a19.getHeight();
                a17.setYLabelOffset(i20);
                a17.showArrowHead(true);
                java.awt.Color a20 = SortedListCanvas.POINTER_COLOR;
                a17.setColor(a20);
                a17.draw(a);
            }
            com.cim.AIA.Node a21 = this.tailptr;
            if(a21 != null)
            {
                com.cim.AIA.Node a22 = this.tailptr;
                int i21 = a22.getX();
                int i22 = i21 - 10;
                com.cim.AIA.Node a23 = this.tailptr;
                int i23 = a23.getY();
                int i24 = i23 + 40;
                com.cim.AIA.Node a24 = this.tailptr;
                int i25 = a24.getX();
                com.cim.AIA.Node a25 = this.tailptr;
                int i26 = a25.getY();
                com.cim.AIA.Node a26 = this.tailptr;
                int i27 = a26.getHeight();
                int i28 = i26 + i27;
                com.cim.AIA.Line a27 = new com.cim.AIA.Line(i22, i24, i25, i28);
                String s3 = SortedListCanvas.TAILPTR_LABEL;
                a27.setLabel(s3);
                a27.setDistanceFromStartForArrowHead(-3);
                a27.setDistanceFromStartForLabel(-1);
                java.awt.FontMetrics a28 = a.getFontMetrics();
                String s4 = SortedListCanvas.TAILPTR_LABEL;
                int i29 = a28.stringWidth(s4);
                int i30 = 0 - i29;
                int i31 = i30 / 2;
                a27.setXLabelOffset(i31);
                java.awt.FontMetrics a29 = a.getFontMetrics();
                int i32 = a29.getHeight();
                a27.setYLabelOffset(i32);
                a27.showArrowHead(true);
                java.awt.Color a30 = SortedListCanvas.POINTER_COLOR;
                a27.setColor(a30);
                a27.draw(a);
            }
        }
    }
    
    public void processRepaintEvent(com.cim.AIA.RepaintEvent a)
    {
        this.removeAllSelectables();
        Object a0 = a.paramObj;
        label0: {
            int i = 0;
            int i0 = 0;
            if(a0 == null)
            {
                break label0;
            }
            java.awt.Dimension a1 = this.getParentSize();
            int i1 = a1.width;
            int i2 = i1 / 2;
            Object a2 = a.paramObj;
            SortedList dummy = (SortedList)a2;
            SortedList a3 = (SortedList)a2;
            SList a4 = a3.getHead();
            this.linkedList = a4;
            int i3 = a3.isBuildMode()?1:0;
            if(i3 == 0)
            {
                this.insertElementArray = null;
            }
            else
            {
                com.cim.AIA.ElementArray a5 = a3.getInsertElementArray();
                this.insertElementArray = a5;
            }
            int i4 = a3.isSearchMode()?1:0;
            if(i4 == 0)
            {
                this.searchElementArray = null;
            }
            else
            {
                com.cim.AIA.SearchSelection a6 = com.cim.AIA.SearchSelection.getInstance();
                this.addSelectionListener((com.cim.AIA.SelectionListener)a6);
                com.cim.AIA.ElementArray a7 = a3.getSearchElementArray();
                this.searchElementArray = a7;
            }
            com.cim.AIA.ElementArray a8 = this.insertElementArray;
            label1: {
                if(a8 == null)
                {
                    i = 30;
                    break label1;
                }
                com.cim.AIA.ElementArray a9 = this.insertElementArray;
                a9.setElementWidth(20);
                com.cim.AIA.ElementArray a10 = this.insertElementArray;
                a10.setAllHaveSameWidth(true);
                java.awt.Dimension a11 = this.getParentSize();
                int i5 = a11.width;
                com.cim.AIA.ElementArray a12 = this.insertElementArray;
                int i6 = a12.getWidth();
                if(i6 >= i5)
                {
                    com.cim.AIA.ElementArray a13 = this.insertElementArray;
                    a13.setLocation(0, 30);
                }
                else
                {
                    com.cim.AIA.ElementArray a14 = this.insertElementArray;
                    com.cim.AIA.ElementArray a15 = this.insertElementArray;
                    int i7 = a15.getWidth();
                    int i8 = i7 / 2;
                    int i9 = i2 - i8;
                    a14.setLocation(i9, 30);
                }
                com.cim.AIA.ElementArray a16 = this.insertElementArray;
                int i10 = a16.getHeight();
                int i11 = i10 + 30;
                int i12 = 30 + i11;
                i = i12;
            }
            SList a17 = this.linkedList;
            if(a17 == null)
            {
                i0 = i;
            }
            else
            {
                java.awt.Dimension a18 = this.getParentSize();
                int i13 = a18.width;
                java.awt.Point a19 = new java.awt.Point(30, i);
                SList a20 = this.linkedList;
                com.cim.AIA.Node a21 = a20.node;
                a21.setPosition(a19);
                int i14 = a19.x;
                SList a22 = this.linkedList;
                com.cim.AIA.Node a23 = a22.node;
                int i15 = a23.getWidth();
                int i16 = i14 + i15;
                int i17 = a19.y;
                java.awt.Point a24 = new java.awt.Point(i16, i17);
                SList a25 = this.linkedList;
                com.cim.AIA.Node a26 = a25.nextNode;
                a26.setPosition(a24);
                com.cim.AIA.Node a27 = a3.getPtrNode();
                this.ptr = a27;
                com.cim.AIA.Node a28 = a3.getTailPtrNode();
                this.tailptr = a28;
                SList a29 = this.linkedList;
                int i18 = a29.getHeight();
                int i19 = i + i18;
                i0 = i19;
            }
            com.cim.AIA.ElementArray a30 = this.searchElementArray;
            if(a30 == null)
            {
                break label0;
            }
            com.cim.AIA.ElementArray a31 = this.searchElementArray;
            a31.setElementWidth(20);
            com.cim.AIA.ElementArray a32 = this.searchElementArray;
            a32.setAllHaveSameWidth(true);
            java.awt.Dimension a33 = this.getParentSize();
            int i20 = a33.width;
            com.cim.AIA.ElementArray a34 = this.searchElementArray;
            int i21 = a34.getWidth();
            if(i21 >= i20)
            {
                com.cim.AIA.ElementArray a35 = this.searchElementArray;
                a35.setLocation(0, i0);
            }
            else
            {
                com.cim.AIA.ElementArray a36 = this.searchElementArray;
                com.cim.AIA.ElementArray a37 = this.searchElementArray;
                int i22 = a37.getWidth();
                int i23 = i22 / 2;
                int i24 = i2 - i23;
                a36.setLocation(i24, i0);
            }
            com.cim.AIA.ElementArray a38 = this.searchElementArray;
            int i25 = a38.getHeight();
            com.cim.AIA.ElementArray a39 = this.searchElementArray;
            this.addSelectable((com.cim.AIA.Selectable)a39);
        }
        this.repaint();
    }
    
    protected void handleColorSelection(com.cim.AIA.ColorSelection a)
    {
    }
    
    protected void handleFontSelection(com.cim.AIA.FontSelection a)
    {
    }
    
    public void draw(java.awt.Graphics a)
    {
        SList a0 = this.linkedList;
        SList a1 = this.linkedList;
        com.cim.AIA.Node a2 = a1.node;
        java.awt.Point a3 = a2.getPosition();
        java.awt.Point a4 = new java.awt.Point(a3);
        SList a5 = a0;
        int i = 0;
        while(true)
        {
            label1: {
                label0: {
                    if(a5 != null)
                    {
                        break label0;
                    }
                    break label1;
                }
                java.io.PrintStream a6 = System.out;
                StringBuilder a7 = new StringBuilder();
                String s = localization.Messages.getString("SortedListCanvas.5");
                StringBuilder a8 = a7.append(s);
                String s0 = String.valueOf(i);
                StringBuilder a9 = a8.append(s0);
                String s1 = a9.toString();
                a6.println(s1);
                com.cim.AIA.Node a10 = a5.node;
                com.cim.AIA.Node a11 = a5.nextNode;
                a10.setPosition(a4);
                a10.draw(a);
                int i0 = a4.x;
                int i1 = a10.getWidth();
                int i2 = i0 + i1;
                int i3 = a4.y;
                a4.move(i2, i3);
                a11.setPosition(a4);
                a11.draw(a);
                SList a12 = a5.next;
                if(a12 != null)
                {
                    int i4 = a11.getX();
                    int i5 = a11.getWidth();
                    int i6 = i5 / 2;
                    int i7 = i4 + i6;
                    int i8 = a11.getY();
                    int i9 = a11.getHeight();
                    int i10 = i9 / 2;
                    int i11 = i8 + i10;
                    int i12 = a11.getX();
                    int i13 = i12 + 40;
                    int i14 = a11.getY();
                    int i15 = a11.getHeight();
                    int i16 = i15 / 2;
                    int i17 = i14 + i16;
                    com.cim.AIA.Line a13 = new com.cim.AIA.Line(i7, i11, i13, i17);
                    a5.nextNodeLine = a13;
                    com.cim.AIA.Line a14 = a5.nextNodeLine;
                    a14.setDistanceFromStartForArrowHead(-3);
                    com.cim.AIA.Line a15 = a5.nextNodeLine;
                    a15.showArrowHead(true);
                    com.cim.AIA.Line a16 = a5.nextNodeLine;
                    java.awt.Color a17 = SortedListCanvas.TEXT_COLOR;
                    a16.setColor(a17);
                    com.cim.AIA.Line a18 = a5.nextNodeLine;
                    a18.draw(a);
                }
                SList a19 = a5.next;
                int i18 = i + 1;
                int i19 = a4.x;
                int i20 = i19 + 40;
                int i21 = a4.y;
                a4.move(i20, i21);
                if(a19 != null)
                {
                    java.io.PrintStream a20 = System.out;
                    a20.println("newList.next NOT null");
                    a5 = a19;
                    i = i18;
                    continue;
                }
                java.io.PrintStream a21 = System.out;
                String s2 = localization.Messages.getString("SortedListCanvas.4");
                a21.println(s2);
            }
            return;
        }
    }
    
    static
    {
        java.awt.Color a = java.awt.Color.gray;
        SortedListCanvas.POINTER_COLOR = a;
        java.awt.Color a0 = java.awt.Color.black;
        SortedListCanvas.TEXT_COLOR = a0;
        String s = localization.Messages.getString("SortedListCanvas.0");
        SortedListCanvas.SEARCH_LABEL = s;
        String s0 = localization.Messages.getString("SortedListCanvas.1");
        SortedListCanvas.PTR_LABEL = s0;
        String s1 = localization.Messages.getString("SortedListCanvas.2");
        SortedListCanvas.TAILPTR_LABEL = s1;
    }
}