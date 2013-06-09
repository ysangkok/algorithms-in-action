public class RadixTrieDigitalElementArray implements com.cim.AIA.Drawable, com.cim.AIA.Selectable {
    final private static String BIT_ARRAY_LABEL;
    private com.cim.AIA.ElementArray bitElementArray;
    private int numberOfBits;
    private com.cim.AIA.Node dataNode;
    private int data;
    private String label;
    final private static int GAP = 20;
    boolean isShowBitNumber;
    private com.cim.AIA.StringMarker[] bitNumbers;
    final private static java.awt.Color DEFAULT_TEXT_COLOR;
    final private static java.awt.Color DEFAULT_BACKGROUND_COLOR;
    
    public RadixTrieDigitalElementArray(int i, int i0)
    {
        super();
        this.isShowBitNumber = false;
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
        com.cim.AIA.ElementArray a0 = this.bitElementArray;
        a0.draw(a);
        com.cim.AIA.Node a1 = this.dataNode;
        a1.draw(a);
        com.cim.AIA.ElementArray a2 = this.bitElementArray;
        java.awt.Point a3 = a2.getLocation();
        int i = a3.x;
        com.cim.AIA.ElementArray a4 = this.bitElementArray;
        int i0 = a4.getWidth();
        int i1 = i + i0;
        int i2 = i1 + 10;
        java.awt.FontMetrics a5 = a.getFontMetrics();
        int i3 = a5.stringWidth("=");
        int i4 = i3 / 2;
        int i5 = i2 - i4;
        com.cim.AIA.ElementArray a6 = this.bitElementArray;
        java.awt.Point a7 = a6.getLocation();
        int i6 = a7.y;
        com.cim.AIA.ElementArray a8 = this.bitElementArray;
        int i7 = a8.getHeight();
        int i8 = i7 / 2;
        int i9 = i6 + i8;
        java.awt.FontMetrics a9 = a.getFontMetrics();
        int i10 = a9.getHeight();
        int i11 = i10 / 2;
        int i12 = i9 + i11;
        a.drawString("=", i5, i12);
        String s = this.label;
        if(s != null)
        {
            java.awt.Color a10 = RadixTrieDigitalElementArray.DEFAULT_TEXT_COLOR;
            a.setColor(a10);
            String s0 = this.label;
            java.awt.Point a11 = this.getPosition();
            int i13 = a11.x;
            java.awt.FontMetrics a12 = a.getFontMetrics();
            String s1 = this.label;
            int i14 = a12.stringWidth(s1);
            int i15 = i13 - i14;
            java.awt.Point a13 = this.getPosition();
            int i16 = a13.y;
            com.cim.AIA.ElementArray a14 = this.bitElementArray;
            int i17 = a14.getHeight();
            int i18 = i17 / 2;
            int i19 = i16 + i18;
            java.awt.FontMetrics a15 = a.getFontMetrics();
            int i20 = a15.getHeight();
            int i21 = i20 / 2;
            int i22 = i19 + i21;
            a.drawString(s0, i15, i22);
        }
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
                java.awt.Color a6 = RadixTrieDigitalElementArray.DEFAULT_BACKGROUND_COLOR;
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
        String s = localization.Messages.getString("RadixTrieDigitalElementArray.0");
        RadixTrieDigitalElementArray.BIT_ARRAY_LABEL = s;
        java.awt.Color a = java.awt.Color.black;
        RadixTrieDigitalElementArray.DEFAULT_TEXT_COLOR = a;
        java.awt.Color a0 = java.awt.Color.white;
        RadixTrieDigitalElementArray.DEFAULT_BACKGROUND_COLOR = a0;
    }
}