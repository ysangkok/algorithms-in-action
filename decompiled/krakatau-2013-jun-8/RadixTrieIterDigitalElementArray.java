public class RadixTrieIterDigitalElementArray implements com.cim.AIA.Drawable, com.cim.AIA.Selectable {
    final private static String BIT_ARRAY_LABEL;
    private com.cim.AIA.ElementArray bitElementArray;
    private int numberOfBits;
    private com.cim.AIA.Node dataNode;
    private int data;
    private String label;
    final private static int GAP = 20;
    boolean isShowBitNumber;
    private boolean isShowDigits;
    private com.cim.AIA.StringMarker[] bitNumbers;
    final private static java.awt.Color DEFAULT_TEXT_COLOR;
    final private static java.awt.Color DEFAULT_BACKGROUND_COLOR;
    
    public RadixTrieIterDigitalElementArray(int i, int i0, boolean b)
    {
        this(i, i0);
        int i1 = b?1:0;
        this.isShowDigits = i1 != 0;
    }
    
    public RadixTrieIterDigitalElementArray(int i, int i0)
    {
        super();
        this.isShowBitNumber = false;
        this.isShowDigits = true;
        this.numberOfBits = i0;
        this.data = i;
        com.cim.AIA.ElementArray a = new com.cim.AIA.ElementArray(0, 0);
        this.bitElementArray = a;
        com.cim.AIA.ElementArray a0 = this.bitElementArray;
        a0.setColumGap(0);
        com.cim.AIA.ElementArray a1 = this.bitElementArray;
        a1.setElementWidth(20);
        com.cim.AIA.ElementArray a2 = this.bitElementArray;
        a2.setAllHaveSameWidth(true);
        int i1 = 0;
        while(true)
        {
            if(i1 > i0)
            {
                Integer a3 = new Integer(i);
                com.cim.AIA.Node a4 = new com.cim.AIA.Node((Object)a3, 0);
                this.dataNode = a4;
                java.awt.Point a5 = new java.awt.Point(0, 0);
                this.setPosition(a5);
                this.isShowDigits = true;
                return;
            }
            else
            {
                int i2 = this.getBit(i1);
                Integer a6 = new Integer(i2);
                com.cim.AIA.Node a7 = new com.cim.AIA.Node((Object)a6, i1);
                com.cim.AIA.ElementArray a8 = this.bitElementArray;
                a8.setValue(i1, (com.cim.AIA.Element)a7);
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
    }
    
    public void setIsShowBitNumber(boolean b)
    {
        int i = 0;
        int i0 = this.isShowBitNumber?1:0;
        int i1 = b?1:0;
        int i2 = b?1:0;
        label1: {
            label0: {
                int i3 = i1 ^ i0;
                if(i3 != 0)
                {
                    break label0;
                }
                if(i2 != 0)
                {
                    i = i2;
                    break label1;
                }
                com.cim.AIA.StringMarker[] a = this.bitNumbers;
                if(a == null)
                {
                    i = 0;
                    break label1;
                }
                int i4 = 0;
                while(true)
                {
                    com.cim.AIA.ElementArray a0 = this.bitElementArray;
                    int i5 = a0.getSize();
                    if(i4 >= i5)
                    {
                        i = 0;
                        break label1;
                    }
                    else
                    {
                        com.cim.AIA.ElementArray a1 = this.bitElementArray;
                        com.cim.AIA.Element a2 = a1.getElement(i4);
                        com.cim.AIA.StringMarker[] a3 = this.bitNumbers;
                        com.cim.AIA.StringMarker a4 = a3[i4];
                        a2.removeMarker(a4);
                        int i6 = i4 + 1;
                        i4 = i6;
                    }
                }
            }
            com.cim.AIA.StringMarker[] a5 = this.bitNumbers;
            label2: {
                if(a5 != null)
                {
                    break label2;
                }
                com.cim.AIA.ElementArray a6 = this.bitElementArray;
                int i7 = a6.getSize();
                com.cim.AIA.StringMarker[] a7 = new com.cim.AIA.StringMarker[i7];
                this.bitNumbers = a7;
                int i8 = 0;
                while(true)
                {
                    com.cim.AIA.ElementArray a8 = this.bitElementArray;
                    int i9 = a8.getSize();
                    if(i8 >= i9)
                    {
                        break;
                    }
                    else
                    {
                        com.cim.AIA.StringMarker[] a9 = this.bitNumbers;
                        StringBuilder a10 = new StringBuilder();
                        StringBuilder a11 = a10.append("");
                        StringBuilder a12 = a11.append(i8);
                        String s = a12.toString();
                        com.cim.AIA.StringMarker a13 = new com.cim.AIA.StringMarker(s);
                        a9[i8] = a13;
                        int i10 = i8 + 1;
                        i8 = i10;
                    }
                }
            }
            int i11 = 0;
            while(true)
            {
                com.cim.AIA.ElementArray a14 = this.bitElementArray;
                int i12 = a14.getSize();
                if(i11 >= i12)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.ElementArray a15 = this.bitElementArray;
                    com.cim.AIA.Element a16 = a15.getElement(i11);
                    com.cim.AIA.Node dummy = (com.cim.AIA.Node)a16;
                    com.cim.AIA.Node a17 = (com.cim.AIA.Node)a16;
                    com.cim.AIA.StringMarker[] a18 = this.bitNumbers;
                    com.cim.AIA.StringMarker a19 = a18[i11];
                    a17.removeMarker(a19);
                    com.cim.AIA.StringMarker[] a20 = this.bitNumbers;
                    com.cim.AIA.StringMarker a21 = a20[i11];
                    a17.addMarker(a21);
                    a17.setMarkersBelowValue(false);
                    int i13 = i11 + 1;
                    i11 = i13;
                }
            }
            i = i2;
        }
        this.isShowBitNumber = i != 0;
    }
    
    private int getBit(int i)
    {
        int i0 = this.data;
        int i1 = this.numberOfBits;
        int i2 = i1 - i;
        int i3 = i0 >> i2;
        int i4 = i3 % 2;
        return i4;
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        this.setPosition(a0);
        this.draw(a);
    }
    
    public void draw(java.awt.Graphics a)
    {
        int i = this.isShowDigits?1:0;
        if(i != 0)
        {
            com.cim.AIA.ElementArray a0 = this.bitElementArray;
            a0.draw(a);
        }
        com.cim.AIA.Node a1 = this.dataNode;
        a1.draw(a);
        com.cim.AIA.ElementArray a2 = this.bitElementArray;
        java.awt.Point a3 = a2.getLocation();
        int i0 = a3.x;
        com.cim.AIA.ElementArray a4 = this.bitElementArray;
        int i1 = a4.getWidth();
        int i2 = i0 + i1;
        int i3 = i2 + 10;
        java.awt.FontMetrics a5 = a.getFontMetrics();
        int i4 = a5.stringWidth("=");
        int i5 = i4 / 2;
        int i6 = i3 - i5;
        com.cim.AIA.ElementArray a6 = this.bitElementArray;
        java.awt.Point a7 = a6.getLocation();
        int i7 = a7.y;
        com.cim.AIA.ElementArray a8 = this.bitElementArray;
        int i8 = a8.getHeight();
        int i9 = i8 / 2;
        int i10 = i7 + i9;
        java.awt.FontMetrics a9 = a.getFontMetrics();
        int i11 = a9.getHeight();
        int i12 = i11 / 2;
        int i13 = i10 + i12;
        a.drawString("=", i6, i13);
        String s = this.label;
        if(s != null)
        {
            java.awt.Color a10 = RadixTrieIterDigitalElementArray.DEFAULT_TEXT_COLOR;
            a.setColor(a10);
            String s0 = this.label;
            java.awt.Point a11 = this.getPosition();
            int i14 = a11.x;
            java.awt.FontMetrics a12 = a.getFontMetrics();
            String s1 = this.label;
            int i15 = a12.stringWidth(s1);
            int i16 = i14 - i15;
            java.awt.Point a13 = this.getPosition();
            int i17 = a13.y;
            com.cim.AIA.ElementArray a14 = this.bitElementArray;
            int i18 = a14.getHeight();
            int i19 = i18 / 2;
            int i20 = i17 + i19;
            java.awt.FontMetrics a15 = a.getFontMetrics();
            int i21 = a15.getHeight();
            int i22 = i21 / 2;
            int i23 = i20 + i22;
            a.drawString(s0, i16, i23);
        }
    }
    
    public void addMarker(com.cim.AIA.StringMarker a)
    {
        com.cim.AIA.Node a0 = this.dataNode;
        a0.addMarker(a);
    }
    
    public void removeAllMarkers()
    {
        com.cim.AIA.Node a = this.dataNode;
        a.removeAllMarkers();
    }
    
    public void setLabel(String s)
    {
        this.label = s;
    }
    
    public void setColors(java.awt.Color a, java.awt.Color a0)
    {
        int i = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a1 = this.bitElementArray;
            int i0 = a1.getSize();
            if(i >= i0)
            {
                com.cim.AIA.Node a2 = this.dataNode;
                a2.setBackgroundColor(a);
                com.cim.AIA.Node a3 = this.dataNode;
                a3.setHighlightColor(a0);
                return;
            }
            else
            {
                com.cim.AIA.ElementArray a4 = this.bitElementArray;
                com.cim.AIA.Element a5 = a4.getElement(i);
                com.cim.AIA.Node dummy = (com.cim.AIA.Node)a5;
                com.cim.AIA.Node a6 = (com.cim.AIA.Node)a5;
                a6.setBackgroundColor(a);
                a6.setHighlightColor(a0);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public void setPosition(java.awt.Point a)
    {
        com.cim.AIA.ElementArray a0 = this.bitElementArray;
        int i = a.x;
        int i0 = a.y;
        a0.setLocation(i, i0);
        com.cim.AIA.Node a1 = this.dataNode;
        int i1 = a.x;
        com.cim.AIA.ElementArray a2 = this.bitElementArray;
        int i2 = a2.getWidth();
        int i3 = i1 + i2;
        int i4 = i3 + 20;
        int i5 = a.y;
        java.awt.Point a3 = new java.awt.Point(i4, i5);
        a1.setPosition(a3);
    }
    
    public java.awt.Point getPosition()
    {
        com.cim.AIA.ElementArray a = this.bitElementArray;
        java.awt.Point a0 = a.getLocation();
        return a0;
    }
    
    public int getWidth()
    {
        com.cim.AIA.ElementArray a = this.bitElementArray;
        int i = a.getWidth();
        int i0 = i + 20;
        com.cim.AIA.Node a0 = this.dataNode;
        int i1 = a0.getWidth();
        int i2 = i0 + i1;
        return i2;
    }
    
    public int getHeight()
    {
        com.cim.AIA.ElementArray a = this.bitElementArray;
        int i = a.getHeight();
        return i;
    }
    
    public boolean equals(com.cim.AIA.Selectable a)
    {
        return false;
    }
    
    public int getIdentifier()
    {
        return 0;
    }
    
    public void highlight()
    {
    }
    
    public void unHighlight()
    {
        int i = 0;
        while(true)
        {
            com.cim.AIA.ElementArray a = this.bitElementArray;
            int i0 = a.getSize();
            if(i >= i0)
            {
                return;
            }
            com.cim.AIA.ElementArray a0 = this.bitElementArray;
            com.cim.AIA.Element a1 = a0.getElement(i);
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a1;
            com.cim.AIA.Node a2 = (com.cim.AIA.Node)a1;
            a2.unHighlight();
            com.cim.AIA.StringMarker[] a3 = this.bitNumbers;
            if(a3 != null)
            {
                com.cim.AIA.StringMarker[] a4 = this.bitNumbers;
                com.cim.AIA.StringMarker a5 = a4[i];
                java.awt.Color a6 = RadixTrieIterDigitalElementArray.DEFAULT_BACKGROUND_COLOR;
                a5.setBackgroundColor(a6);
            }
            int i1 = i + 1;
            i = i1;
        }
    }
    
    public void highlightBit(byte a)
    {
        com.cim.AIA.ElementArray a0 = this.bitElementArray;
        int i = a;
        int i0 = a0.getSize();
        if(i0 > i)
        {
            com.cim.AIA.ElementArray a1 = this.bitElementArray;
            com.cim.AIA.Element a2 = a1.getElement(i);
            com.cim.AIA.Node dummy = (com.cim.AIA.Node)a2;
            com.cim.AIA.Node a3 = (com.cim.AIA.Node)a2;
            a3.highlight();
        }
    }
    
    public void highlightNumber(byte a, java.awt.Color a0)
    {
        com.cim.AIA.StringMarker[] a1 = this.bitNumbers;
        int i = a;
        if(a1 != null)
        {
            com.cim.AIA.StringMarker[] a2 = this.bitNumbers;
            com.cim.AIA.StringMarker a3 = a2[i];
            a3.setBackgroundColor(a0);
        }
    }
    
    public com.cim.AIA.Selectable getItemAt(java.awt.Point a)
    {
        return null;
    }
    
    static
    {
        String s = localization.Messages.getString("RadixTrieIterDigitalElementArray.0");
        RadixTrieIterDigitalElementArray.BIT_ARRAY_LABEL = s;
        java.awt.Color a = java.awt.Color.black;
        RadixTrieIterDigitalElementArray.DEFAULT_TEXT_COLOR = a;
        java.awt.Color a0 = java.awt.Color.white;
        RadixTrieIterDigitalElementArray.DEFAULT_BACKGROUND_COLOR = a0;
    }
}