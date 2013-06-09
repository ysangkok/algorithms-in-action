package com.cim.common;

public class RadioMenu extends java.awt.Menu {
    final private static long serialVersionUID = -8903947943034089806L;
    
    public RadioMenu(String s)
    {
        super(s, false);
    }
    
    public RadioMenu(String s, boolean b)
    {
        super(s, b);
    }
    
    public java.awt.MenuItem add(java.awt.MenuItem a)
    {
        java.awt.MenuItem a0 = ((java.awt.Menu)this).add(a);
        return a0;
    }
    
    public void add(String s)
    {
        java.awt.CheckboxMenuItem a = new java.awt.CheckboxMenuItem(s);
        java.awt.MenuItem a0 = this.add((java.awt.MenuItem)a);
    }
    
    public java.awt.MenuItem getSelectedItem()
    {
        java.awt.MenuItem a = this.getSelectedItem(0);
        return a;
    }
    
    public java.awt.MenuItem getSelectedItem(int i)
    {
        int i0 = i;
        while(true)
        {
            int i1 = this.getItemCount();
            label2: {
                java.awt.CheckboxMenuItem a = null;
                label1: {
                    label0: {
                        if(i0 < i1)
                        {
                            break label0;
                        }
                        a = null;
                        break label1;
                    }
                    java.awt.MenuItem a0 = this.getItem(i0);
                    int i2 = a0 instanceof java.awt.CheckboxMenuItem?1:0;
                    if(i2 == 0)
                    {
                        break label2;
                    }
                    java.awt.MenuItem a1 = this.getItem(i0);
                    java.awt.CheckboxMenuItem dummy = (java.awt.CheckboxMenuItem)a1;
                    java.awt.CheckboxMenuItem a2 = (java.awt.CheckboxMenuItem)a1;
                    int i3 = a2.getState()?1:0;
                    if(i3 == 0)
                    {
                        break label2;
                    }
                    a = a2;
                }
                return a;
            }
            int i4 = i0 + 1;
            i0 = i4;
        }
    }
    
    public void selectItem(java.awt.MenuItem a)
    {
        int i = this.getItemCount();
        int i0 = 0;
        int i1 = i;
        int i2 = 0;
        int i3 = 0;
        while(true)
        {
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            int i13 = i0;
            int i14 = i1;
            int i15 = i2;
            if(i3 >= i)
            {
                break;
            }
            else
            {
                i4 = i13;
                i5 = i14;
                i6 = i15;
            }
            java.awt.MenuItem a0 = this.getItem(i3);
            int i16 = i4;
            int i17 = i5;
            int i18 = i6;
            int i19 = i16;
            int i20 = i17;
            int i21 = i16;
            int i22 = i17;
            int i23 = i18;
            if(a != a0)
            {
                i7 = i21;
                i8 = i22;
                i9 = i23;
            }
            else
            {
                int i24 = i19;
                int i25 = i20;
                i7 = i24;
                i8 = i25;
                i9 = 1;
            }
            java.awt.MenuItem a1 = this.getItem(i3);
            int i26 = i7;
            int i27 = i8;
            int i28 = i9;
            String s = a1.getLabel();
            int i29 = i26;
            int i30 = i27;
            int i31 = i28;
            int i32 = s.equals((Object)"-")?1:0;
            int i33 = i29;
            int i34 = i30;
            int i35 = i31;
            label0: {
                int i36 = 0;
                int i37 = 0;
                int i38 = i33;
                int i39 = i34;
                int i40 = i33;
                int i41 = i34;
                int i42 = i35;
                if(i32 == 0)
                {
                    i10 = i40;
                    i11 = i41;
                    i12 = i42;
                    break label0;
                }
                else
                {
                    i36 = i38;
                    i37 = i39;
                }
                int i43 = i36;
                int i44 = i37;
                if(i35 == 0)
                {
                    int i45 = i44;
                    i10 = i3;
                    i11 = i45;
                    i12 = 0;
                }
                else
                {
                    int i46 = i43;
                    i10 = i46;
                    i11 = i3;
                    i12 = 1;
                }
            }
            int i47 = i3 + 1;
            i0 = i10;
            i1 = i11;
            i2 = i12;
            i3 = i47;
        }
        java.awt.MenuItem a2 = this.getSelectedItem();
        java.awt.MenuItem a3 = a;
        int i48 = 0;
        int i49 = i0;
        while(true)
        {
            java.awt.MenuItem a4 = null;
            int i50 = 0;
            java.awt.MenuItem a5 = null;
            int i51 = 0;
            java.awt.MenuItem a6 = null;
            int i52 = 0;
            java.awt.MenuItem a7 = a3;
            int i53 = i48;
            if(i49 >= i1)
            {
                break;
            }
            else
            {
                a4 = a7;
                i50 = i53;
            }
            try
            {
                java.awt.MenuItem a8 = null;
                int i54 = 0;
                a5 = a4;
                i51 = i50;
                java.awt.MenuItem a9 = this.getItem(i49);
                java.awt.MenuItem a10 = a4;
                int i55 = i50;
                a5 = a10;
                i51 = i55;
                java.awt.CheckboxMenuItem dummy = (java.awt.CheckboxMenuItem)a9;
                java.awt.CheckboxMenuItem a11 = (java.awt.CheckboxMenuItem)a9;
                java.awt.MenuItem a12 = a10;
                int i56 = i55;
                java.awt.CheckboxMenuItem a13 = (java.awt.CheckboxMenuItem)a12;
                int i57 = i56;
                java.awt.MenuItem a14 = a12;
                int i58 = i56;
                if(a12 != a11)
                {
                    java.awt.MenuItem a15 = a14;
                    int i59 = i58;
                    a5 = a15;
                    i51 = i59;
                    a11.setState(false);
                    a8 = a15;
                    i54 = i59;
                }
                else
                {
                    java.awt.CheckboxMenuItem a16 = a13;
                    int i60 = i57;
                    a5 = a16;
                    i51 = i60;
                    a11.setState(true);
                    java.awt.CheckboxMenuItem a17 = a16;
                    a8 = a17;
                    i54 = 1;
                }
                a6 = a8;
                i52 = i54;
            }
            catch(ClassCastException ignoredException)
            {
                java.awt.MenuItem a18 = a5;
                int i61 = i51;
                a6 = a18;
                i52 = i61;
            }
            int i62 = i49 + 1;
            a3 = a6;
            i48 = i52;
            i49 = i62;
        }
        label1: {
            if(i48 != 0)
            {
                break label1;
            }
            if(a2 != null)
            {
                java.awt.CheckboxMenuItem dummy0 = (java.awt.CheckboxMenuItem)a2;
                java.awt.CheckboxMenuItem a19 = (java.awt.CheckboxMenuItem)a2;
                a19.setState(true);
            }
        }
    }
}