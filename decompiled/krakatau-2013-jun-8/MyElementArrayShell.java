public class MyElementArrayShell implements com.cim.AIA.Drawable, com.cim.AIA.Selectable, com.cim.AIA.ObjectContainer {
    final public static int DEFAULT_COLUM_WIDTH = 10;
    final public static int DEFAULT_COLUM_GAP = 10;
    protected int columGap;
    protected int sequenceNumber;
    protected int elementWidth;
    protected boolean allHaveSameWidth;
    protected java.util.Vector elements;
    protected java.awt.Point location;
    
    public MyElementArrayShell(int i, int i0)
    {
        super();
        this.columGap = 10;
        this.sequenceNumber = 1;
        this.elementWidth = 10;
        this.allHaveSameWidth = true;
        java.awt.Point a = new java.awt.Point(i, i0);
        this.location = a;
        java.util.Vector a0 = new java.util.Vector();
        this.elements = a0;
    }
    
    public void add(Object a, int i)
    {
        int i0 = a instanceof com.cim.AIA.Element?1:0;
        if(i0 != 0)
        {
            com.cim.AIA.Element dummy = (com.cim.AIA.Element)a;
            com.cim.AIA.Element a0 = (com.cim.AIA.Element)a;
            this.setValue(i, a0);
        }
    }
    
    public void draw(java.awt.Graphics a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.elements;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            int i1 = this.isEmpty(i)?1:0;
            if(i1 == 0)
            {
                java.util.Vector a1 = this.elements;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.Element dummy = (com.cim.AIA.Element)a2;
                com.cim.AIA.Element a3 = (com.cim.AIA.Element)a2;
                a3.draw(a);
            }
            int i2 = i + 1;
            i = i2;
        }
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
        int i = a0.x;
        int i0 = a0.y;
        this.setLocation(i, i0);
        this.draw(a);
    }
    
    public boolean equals(com.cim.AIA.Selectable a)
    {
        int i = 0;
        label1: {
            Object a0 = null;
            label0: {
                int i0 = a instanceof MyElementArrayShell?1:0;
                a0 = a;
                if(i0 != 0)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            MyElementArrayShell dummy = (MyElementArrayShell)a0;
            MyElementArrayShell a1 = (MyElementArrayShell)a0;
            int i1 = a1.getIdentifier();
            int i2 = this.getIdentifier();
            label2: {
                if(i1 == i2)
                {
                    break label2;
                }
                i = 0;
                break label1;
            }
            java.util.Vector a2 = this.elements;
            int i3 = a2.size();
            java.util.Vector a3 = a1.elements;
            int i4 = a3.size();
            label3: {
                if(i3 == i4)
                {
                    break label3;
                }
                i = 0;
                break label1;
            }
            int i5 = 0;
            while(true)
            {
                java.util.Vector a4 = this.elements;
                int i6 = a4.size();
                label4: {
                    if(i5 < i6)
                    {
                        break label4;
                    }
                    i = 1;
                    break;
                }
                java.util.Vector a5 = this.elements;
                Object a6 = a5.elementAt(i5);
                com.cim.AIA.Element dummy0 = (com.cim.AIA.Element)a6;
                com.cim.AIA.Element a7 = (com.cim.AIA.Element)a6;
                java.util.Vector a8 = a1.elements;
                Object a9 = a8.elementAt(i5);
                com.cim.AIA.Element dummy1 = (com.cim.AIA.Element)a9;
                com.cim.AIA.Element a10 = (com.cim.AIA.Element)a9;
                label7: {
                    label6: {
                        label5: {
                            if(a7 == null)
                            {
                                break label5;
                            }
                            if(a10 == null)
                            {
                                break label5;
                            }
                            int i7 = a7.equals((com.cim.AIA.Selectable)a10)?1:0;
                            if(i7 != 0)
                            {
                                break label6;
                            }
                            else
                            {
                                break label7;
                            }
                        }
                        label9: {
                            label8: {
                                if(a7 != null)
                                {
                                    break label8;
                                }
                                if(a10 == null)
                                {
                                    break label9;
                                }
                            }
                            i = 0;
                            break;
                        }
                    }
                    int i8 = i5 + 1;
                    i5 = i8;
                    continue;
                }
                i = 0;
                break;
            }
        }
        return i != 0;
    }
    
    public int getColumGap()
    {
        int i = this.columGap;
        return i;
    }
    
    public com.cim.AIA.Element getElement(int i)
    {
        com.cim.AIA.Element a = null;
        label2: {
            label1: {
                label0: {
                    if(i < 0)
                    {
                        break label0;
                    }
                    java.util.Vector a0 = this.elements;
                    int i0 = a0.size();
                    if(i < i0)
                    {
                        break label1;
                    }
                }
                a = null;
                break label2;
            }
            java.util.Vector a1 = this.elements;
            Object a2 = a1.elementAt(i);
            com.cim.AIA.Element dummy = (com.cim.AIA.Element)a2;
            com.cim.AIA.Element a3 = (com.cim.AIA.Element)a2;
            a = a3;
        }
        return a;
    }
    
    public int getElementWidth()
    {
        int i = this.elementWidth;
        return i;
    }
    
    public int getHeight()
    {
        int i = 0;
        int i0 = 0;
        while(true)
        {
            int i1 = 0;
            int i2 = 0;
            java.util.Vector a = this.elements;
            int i3 = i;
            int i4 = a.size();
            int i5 = i3;
            int i6 = i5;
            if(i0 >= i4)
            {
                return i5;
            }
            else
            {
                i1 = i6;
            }
            java.util.Vector a0 = this.elements;
            int i7 = i1;
            Object a1 = a0.elementAt(i0);
            int i8 = i7;
            com.cim.AIA.Element dummy = (com.cim.AIA.Element)a1;
            com.cim.AIA.Element a2 = (com.cim.AIA.Element)a1;
            int i9 = i8;
            label0: {
                int i10 = 0;
                int i11 = i9;
                int i12 = i9;
                if(a2 == null)
                {
                    i2 = i12;
                    break label0;
                }
                else
                {
                    i10 = i11;
                }
                int i13 = a2.getHeight();
                int i14 = i10;
                int i15 = i14;
                if(i13 <= i14)
                {
                    i2 = i15;
                }
                else
                {
                    int i16 = a2.getHeight();
                    i2 = i16;
                }
            }
            int i17 = i0 + 1;
            i = i2;
            i0 = i17;
        }
    }
    
    public int getIdentifier()
    {
        int i = this.sequenceNumber;
        return i;
    }
    
    public com.cim.AIA.Selectable getItemAt(java.awt.Point a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.elements;
            int i0 = a0.size();
            label2: {
                com.cim.AIA.Element a1 = null;
                label1: {
                    label0: {
                        if(i < i0)
                        {
                            break label0;
                        }
                        a1 = null;
                        break label1;
                    }
                    java.util.Vector a2 = this.elements;
                    Object a3 = a2.elementAt(i);
                    com.cim.AIA.Element dummy = (com.cim.AIA.Element)a3;
                    com.cim.AIA.Element a4 = (com.cim.AIA.Element)a3;
                    if(a4 == null)
                    {
                        break label2;
                    }
                    Object a5 = a4.getItemAt(a);
                    if(a5 == null)
                    {
                        break label2;
                    }
                    a1 = a4;
                }
                return (com.cim.AIA.Selectable)a1;
            }
            int i1 = i + 1;
            i = i1;
        }
    }
    
    public java.awt.Point getLocation()
    {
        java.awt.Point a = this.location;
        return a;
    }
    
    public int getSize()
    {
        java.util.Vector a = this.elements;
        int i = a.size();
        return i;
    }
    
    public int getWidth()
    {
        int i = 0;
        int i0 = this.allHaveSameWidth?1:0;
        label1: {
            label0: {
                if(i0 == 0)
                {
                    break label0;
                }
                java.util.Vector a = this.elements;
                int i1 = a.size();
                int i2 = this.elementWidth;
                int i3 = i1 * i2;
                java.util.Vector a0 = this.elements;
                int i4 = a0.size();
                int i5 = i4 - 1;
                int i6 = this.columGap;
                int i7 = i5 * i6;
                int i8 = i3 + i7;
                i = i8;
                break label1;
            }
            int i9 = 0;
            int i10 = 0;
            while(true)
            {
                java.util.Vector a1 = this.elements;
                int i11 = a1.size();
                if(i10 >= i11)
                {
                    break;
                }
                else
                {
                    java.util.Vector a2 = this.elements;
                    Object a3 = a2.elementAt(i10);
                    com.cim.AIA.Element dummy = (com.cim.AIA.Element)a3;
                    com.cim.AIA.Element a4 = (com.cim.AIA.Element)a3;
                    int i12 = a4.getWidth();
                    int i13 = i9 + i12;
                    int i14 = i10 + 1;
                    i9 = i13;
                    i10 = i14;
                }
            }
            java.util.Vector a5 = this.elements;
            int i15 = a5.size();
            int i16 = i15 - 1;
            int i17 = this.columGap;
            int i18 = i16 * i17;
            int i19 = i9 + i18;
            i = i19;
        }
        return i;
    }
    
    public void highlight()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.elements;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.elements;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.Element dummy = (com.cim.AIA.Element)a1;
                com.cim.AIA.Element a2 = (com.cim.AIA.Element)a1;
                a2.highlight();
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public boolean isEmpty(int i)
    {
        java.util.Vector a = this.elements;
        Object a0 = a.elementAt(i);
        int i0 = a0 != null?0:1;
        return i0 != 0;
    }
    
    public void remove(Object a)
    {
        int i = a instanceof com.cim.AIA.Element?1:0;
        if(i != 0)
        {
            com.cim.AIA.Element dummy = (com.cim.AIA.Element)a;
            com.cim.AIA.Element a0 = (com.cim.AIA.Element)a;
            this.removeElement(a0);
        }
    }
    
    public void removeElement(com.cim.AIA.Element a)
    {
        java.util.Vector a0 = this.elements;
        int i = a0.contains((Object)a)?1:0;
        if(i != 0)
        {
            java.util.Vector a1 = this.elements;
            int i0 = a1.removeElement((Object)a)?1:0;
        }
    }
    
    public void removeElement(int i)
    {
        label0: {
            if(i < 0)
            {
                break label0;
            }
            java.util.Vector a = this.elements;
            int i0 = a.size();
            if(i < i0)
            {
                java.util.Vector a0 = this.elements;
                a0.removeElementAt(i);
            }
        }
    }
    
    public void setAllHaveSameWidth(boolean b)
    {
        this.allHaveSameWidth = b;
        int i = b?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            int i0 = 0;
            while(true)
            {
                java.util.Vector a = this.elements;
                int i1 = a.size();
                if(i0 >= i1)
                {
                    break;
                }
                int i2 = this.isEmpty(i0)?1:0;
                if(i2 == 0)
                {
                    java.util.Vector a0 = this.elements;
                    Object a1 = a0.elementAt(i0);
                    com.cim.AIA.Element dummy = (com.cim.AIA.Element)a1;
                    com.cim.AIA.Element a2 = (com.cim.AIA.Element)a1;
                    int i3 = this.elementWidth;
                    a2.setWidth(i3);
                }
                int i4 = i0 + 1;
                i0 = i4;
            }
        }
    }
    
    public void setColumGap(int i)
    {
        this.columGap = i;
    }
    
    public void setElementWidth(int i)
    {
        this.elementWidth = i;
        int i0 = 0;
        while(true)
        {
            java.util.Vector a = this.elements;
            int i1 = a.size();
            if(i0 >= i1)
            {
                return;
            }
            int i2 = this.isEmpty(i0)?1:0;
            if(i2 == 0)
            {
                java.util.Vector a0 = this.elements;
                Object a1 = a0.elementAt(i0);
                com.cim.AIA.Element dummy = (com.cim.AIA.Element)a1;
                com.cim.AIA.Element a2 = (com.cim.AIA.Element)a1;
                a2.setWidth(i);
            }
            int i3 = i0 + 1;
            i0 = i3;
        }
    }
    
    public void setLocation(int i, int i0)
    {
        java.awt.Point a = this.location;
        a.x = i;
        java.awt.Point a0 = this.location;
        a0.y = i0;
        int i1 = this.allHaveSameWidth?1:0;
        label1: {
            label0: {
                if(i1 != 0)
                {
                    break label0;
                }
                int i2 = 0;
                int i3 = 0;
                while(true)
                {
                    int i4 = 0;
                    int i5 = 0;
                    java.util.Vector a1 = this.elements;
                    int i6 = i2;
                    int i7 = a1.size();
                    int i8 = i6;
                    int i9 = i8;
                    if(i3 >= i7)
                    {
                        break label1;
                    }
                    else
                    {
                        i4 = i9;
                    }
                    java.util.Vector a2 = this.elements;
                    int i10 = i4;
                    Object a3 = a2.elementAt(i3);
                    int i11 = i10;
                    com.cim.AIA.Element dummy = (com.cim.AIA.Element)a3;
                    com.cim.AIA.Element a4 = (com.cim.AIA.Element)a3;
                    int i12 = i11;
                    int i13 = i12;
                    if(a4 == null)
                    {
                        i5 = i13;
                    }
                    else
                    {
                        java.awt.Point a5 = this.location;
                        int i14 = a5.x;
                        int i15 = this.columGap;
                        int i16 = i3 * i15;
                        int i17 = i14 + i16;
                        int i18 = i17 + i12;
                        java.awt.Point a6 = this.location;
                        int i19 = a6.y;
                        java.awt.Point a7 = new java.awt.Point(i18, i19);
                        a4.setPosition(a7);
                        int i20 = a4.getWidth();
                        int i21 = i12 + i20;
                        i5 = i21;
                    }
                    int i22 = i3 + 1;
                    i2 = i5;
                    i3 = i22;
                }
            }
            int i23 = 0;
            while(true)
            {
                java.util.Vector a8 = this.elements;
                int i24 = a8.size();
                if(i23 >= i24)
                {
                    break;
                }
                java.util.Vector a9 = this.elements;
                Object a10 = a9.elementAt(i23);
                com.cim.AIA.Element dummy0 = (com.cim.AIA.Element)a10;
                com.cim.AIA.Element a11 = (com.cim.AIA.Element)a10;
                if(a11 != null)
                {
                    java.awt.Point a12 = this.location;
                    int i25 = a12.x;
                    int i26 = this.columGap;
                    int i27 = this.elementWidth;
                    int i28 = i26 + i27;
                    int i29 = i23 * i28;
                    int i30 = i25 + i29;
                    java.awt.Point a13 = this.location;
                    int i31 = a13.y;
                    java.awt.Point a14 = new java.awt.Point(i30, i31);
                    a11.setPosition(a14);
                }
                int i32 = i23 + 1;
                i23 = i32;
            }
        }
    }
    
    public void setSequence(int i)
    {
        this.sequenceNumber = i;
    }
    
    public void setValue(int i, com.cim.AIA.Element a)
    {
        label0: {
            if(i < 0)
            {
                break label0;
            }
            java.util.Vector a0 = this.elements;
            a0.insertElementAt((Object)a, i);
            java.awt.Point a1 = this.location;
            int i0 = a1.x;
            int i1 = this.columGap;
            int i2 = this.elementWidth;
            int i3 = i1 + i2;
            int i4 = i * i3;
            int i5 = i0 + i4;
            java.awt.Point a2 = this.location;
            int i6 = a2.y;
            java.awt.Point a3 = new java.awt.Point(i5, i6);
            a.setPosition(a3);
            int i7 = this.allHaveSameWidth?1:0;
            if(i7 != 0)
            {
                int i8 = this.elementWidth;
                a.setWidth(i8);
            }
        }
    }
    
    public void unHighlight()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.elements;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.elements;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.Element dummy = (com.cim.AIA.Element)a1;
                com.cim.AIA.Element a2 = (com.cim.AIA.Element)a1;
                a2.unHighlight();
                int i1 = i + 1;
                i = i1;
            }
        }
    }
}