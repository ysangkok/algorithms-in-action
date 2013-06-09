package com.cim.AIA;

public class NonRadioCheckboxGroup {
    protected java.util.Vector checkboxes;
    
    public NonRadioCheckboxGroup()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.checkboxes = a;
    }
    
    public void add(java.awt.Checkbox a)
    {
        java.util.Vector a0 = this.checkboxes;
        a0.addElement((Object)a);
    }
    
    public java.awt.Checkbox[] getSelected()
    {
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        while(true)
        {
            int i2 = 0;
            int i3 = 0;
            java.util.Vector a = this.checkboxes;
            int i4 = i0;
            int i5 = a.size();
            i = i4;
            int i6 = i;
            if(i1 >= i5)
            {
                break;
            }
            else
            {
                i2 = i6;
            }
            java.util.Vector a0 = this.checkboxes;
            int i7 = i2;
            Object a1 = a0.elementAt(i1);
            int i8 = i7;
            java.awt.Checkbox dummy = (java.awt.Checkbox)a1;
            java.awt.Checkbox a2 = (java.awt.Checkbox)a1;
            int i9 = i8;
            int i10 = a2.getState()?1:0;
            int i11 = i9;
            int i12 = i11;
            if(i10 == 0)
            {
                i3 = i12;
            }
            else
            {
                int i13 = i11 + 1;
                i3 = i13;
            }
            int i14 = i1 + 1;
            i0 = i3;
            i1 = i14;
        }
        java.awt.Checkbox[] a3 = new java.awt.Checkbox[i];
        int i15 = 0;
        int i16 = 0;
        while(true)
        {
            int i17 = 0;
            int i18 = 0;
            java.util.Vector a4 = this.checkboxes;
            int i19 = i15;
            int i20 = a4.size();
            int i21 = i19;
            int i22 = i21;
            if(i16 >= i20)
            {
                return a3;
            }
            else
            {
                i17 = i22;
            }
            java.util.Vector a5 = this.checkboxes;
            int i23 = i17;
            Object a6 = a5.elementAt(i16);
            int i24 = i23;
            java.awt.Checkbox dummy0 = (java.awt.Checkbox)a6;
            java.awt.Checkbox a7 = (java.awt.Checkbox)a6;
            int i25 = i24;
            int i26 = a7.getState()?1:0;
            int i27 = i25;
            int i28 = i27;
            if(i26 == 0)
            {
                i18 = i28;
            }
            else
            {
                java.util.Vector a8 = this.checkboxes;
                Object a9 = a8.elementAt(i16);
                java.awt.Checkbox dummy1 = (java.awt.Checkbox)a9;
                java.awt.Checkbox a10 = (java.awt.Checkbox)a9;
                a3[i27] = a10;
                int i29 = i27 + 1;
                i18 = i29;
            }
            int i30 = i16 + 1;
            i15 = i18;
            i16 = i30;
        }
    }
}