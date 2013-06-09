package com.cim.AIA;

public class ConfigurationManager {
    private static com.cim.AIA.ConfigurationManager configurationManager;
    protected java.util.Vector fontSelectionListeners;
    protected java.util.Vector colorSelectionListeners;
    protected java.util.Vector fontSelections;
    protected java.util.Vector colorSelections;
    
    public static com.cim.AIA.ConfigurationManager getConfigurationManager()
    {
        com.cim.AIA.ConfigurationManager a = com.cim.AIA.ConfigurationManager.configurationManager;
        if(a == null)
        {
            com.cim.AIA.ConfigurationManager a0 = new com.cim.AIA.ConfigurationManager();
            com.cim.AIA.ConfigurationManager.configurationManager = a0;
        }
        com.cim.AIA.ConfigurationManager a1 = com.cim.AIA.ConfigurationManager.configurationManager;
        return a1;
    }
    
    public static void reset()
    {
        com.cim.AIA.ConfigurationManager.configurationManager = null;
    }
    
    private ConfigurationManager()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.fontSelectionListeners = a;
        java.util.Vector a0 = new java.util.Vector();
        this.colorSelectionListeners = a0;
        java.util.Vector a1 = new java.util.Vector();
        this.fontSelections = a1;
        java.util.Vector a2 = new java.util.Vector();
        this.colorSelections = a2;
    }
    
    public void addColorSelection(com.cim.AIA.ColorSelection a)
    {
        java.util.Vector a0 = this.colorSelections;
        a0.addElement((Object)a);
    }
    
    public void addColorSelectionListener(com.cim.AIA.ColorSelectionListener a)
    {
        java.util.Vector a0 = this.colorSelectionListeners;
        Object a1 = a;
        int i = a0.contains(a1)?1:0;
        if(i == 0)
        {
            java.util.Vector a2 = this.colorSelectionListeners;
            a2.addElement(a1);
        }
    }
    
    public void addFontSelection(com.cim.AIA.FontSelection a)
    {
        java.util.Vector a0 = this.fontSelections;
        a0.addElement((Object)a);
    }
    
    public void addFontSelectionListener(com.cim.AIA.FontSelectionListener a)
    {
        java.util.Vector a0 = this.fontSelectionListeners;
        Object a1 = a;
        int i = a0.contains(a1)?1:0;
        if(i == 0)
        {
            java.util.Vector a2 = this.fontSelectionListeners;
            a2.addElement(a1);
        }
    }
    
    public void askUserForChanges()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.fontSelections;
            int i0 = a.size();
            if(i >= i0)
            {
                break;
            }
            else
            {
                java.util.Vector a0 = this.fontSelections;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.FontSelection dummy = (com.cim.AIA.FontSelection)a1;
                com.cim.AIA.FontSelection a2 = (com.cim.AIA.FontSelection)a1;
                a2.backup();
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = 0;
        while(true)
        {
            java.util.Vector a3 = this.colorSelections;
            int i3 = a3.size();
            if(i2 >= i3)
            {
                java.util.Vector a4 = this.fontSelections;
                java.util.Vector a5 = this.colorSelections;
                com.cim.AIA.ConfigurationManagerDialog a6 = new com.cim.AIA.ConfigurationManagerDialog(a4, a5);
                a6.setVisible(true);
                int i4 = a6.acceptChanges()?1:0;
                this.informAll(i4 != 0);
                a6.dispose();
                return;
            }
            else
            {
                java.util.Vector a7 = this.colorSelections;
                Object a8 = a7.elementAt(i2);
                com.cim.AIA.ColorSelection dummy0 = (com.cim.AIA.ColorSelection)a8;
                com.cim.AIA.ColorSelection a9 = (com.cim.AIA.ColorSelection)a8;
                a9.backup();
                int i5 = i2 + 1;
                i2 = i5;
            }
        }
    }
    
    protected void informAll(boolean b)
    {
        int i = 0;
        int i0 = b?1:0;
        int i1 = 0;
        while(true)
        {
            int i2 = 0;
            int i3 = 0;
            java.util.Vector a = this.fontSelections;
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
            java.util.Vector a0 = this.fontSelections;
            int i7 = i2;
            Object a1 = a0.elementAt(i1);
            int i8 = i7;
            com.cim.AIA.FontSelection dummy = (com.cim.AIA.FontSelection)a1;
            com.cim.AIA.FontSelection a2 = (com.cim.AIA.FontSelection)a1;
            int i9 = i8;
            int i10 = a2.changed()?1:0;
            int i11 = i9;
            label2: {
                int i12 = 0;
                int i13 = 0;
                label1: {
                    int i14 = 0;
                    label0: {
                        int i15 = 0;
                        int i16 = i11;
                        int i17 = i11;
                        if(i10 == 0)
                        {
                            i14 = i17;
                            break label0;
                        }
                        else
                        {
                            i15 = i16;
                        }
                        int i18 = i15;
                        int i19 = i15;
                        if(i15 == 0)
                        {
                            i14 = i19;
                        }
                        else
                        {
                            i12 = i18;
                            break label1;
                        }
                    }
                    a2.restore();
                    i3 = i14;
                    break label2;
                }
                int i20 = i12;
                int i21 = 0;
                while(true)
                {
                    int i22 = 0;
                    int i23 = 0;
                    java.util.Vector a3 = this.fontSelectionListeners;
                    int i24 = i20;
                    int i25 = a3.size();
                    int i26 = i24;
                    int i27 = i26;
                    int i28 = i26;
                    if(i21 >= i25)
                    {
                        i13 = i28;
                        break;
                    }
                    else
                    {
                        i22 = i27;
                    }
                    java.util.Vector a4 = this.fontSelectionListeners;
                    int i29 = i22;
                    Object a5 = a4.elementAt(i21);
                    int i30 = i29;
                    com.cim.AIA.FontSelectionListener dummy0 = (com.cim.AIA.FontSelectionListener)a5;
                    int i31 = i30;
                    int i32 = a2.appliesTo((com.cim.AIA.ConfigurationSelectionListener)a5)?1:0;
                    int i33 = i31;
                    int i34 = i33;
                    int i35 = i33;
                    if(i32 == 0)
                    {
                        i23 = i35;
                    }
                    else
                    {
                        int i36 = i34;
                        ((com.cim.AIA.FontSelectionListener)a5).processFontSelection(a2);
                        i23 = i36;
                    }
                    int i37 = i21 + 1;
                    i20 = i23;
                    i21 = i37;
                }
                a2.accept();
                int i38 = i13;
                i3 = i38;
            }
            int i39 = i1 + 1;
            i0 = i3;
            i1 = i39;
        }
        int i40 = 0;
        while(true)
        {
            java.util.Vector a6 = this.colorSelections;
            int i41 = a6.size();
            if(i40 >= i41)
            {
                break;
            }
            java.util.Vector a7 = this.colorSelections;
            Object a8 = a7.elementAt(i40);
            com.cim.AIA.ColorSelection dummy1 = (com.cim.AIA.ColorSelection)a8;
            com.cim.AIA.ColorSelection a9 = (com.cim.AIA.ColorSelection)a8;
            int i42 = a9.changed()?1:0;
            label3: {
                if(i42 == 0)
                {
                    break label3;
                }
                if(i == 0)
                {
                    break label3;
                }
                int i43 = 0;
                while(true)
                {
                    java.util.Vector a10 = this.colorSelectionListeners;
                    int i44 = a10.size();
                    if(i43 >= i44)
                    {
                        break;
                    }
                    java.util.Vector a11 = this.colorSelectionListeners;
                    Object a12 = a11.elementAt(i43);
                    com.cim.AIA.ColorSelectionListener dummy2 = (com.cim.AIA.ColorSelectionListener)a12;
                    int i45 = a9.appliesTo((com.cim.AIA.ConfigurationSelectionListener)a12)?1:0;
                    if(i45 != 0)
                    {
                        ((com.cim.AIA.ColorSelectionListener)a12).processColorSelection(a9);
                    }
                    int i46 = i43 + 1;
                    i43 = i46;
                }
                a9.accept();
            }
            a9.restore();
            int i47 = i40 + 1;
            i40 = i47;
        }
        label4: {
            if(i == 0)
            {
                break label4;
            }
            int i48 = 0;
            while(true)
            {
                java.util.Vector a13 = this.fontSelectionListeners;
                int i49 = a13.size();
                if(i48 >= i49)
                {
                    break;
                }
                else
                {
                    java.util.Vector a14 = this.fontSelectionListeners;
                    Object a15 = a14.elementAt(i48);
                    com.cim.AIA.FontSelectionListener dummy3 = (com.cim.AIA.FontSelectionListener)a15;
                    ((com.cim.AIA.FontSelectionListener)a15).repaint();
                    int i50 = i48 + 1;
                    i48 = i50;
                }
            }
            int i51 = 0;
            while(true)
            {
                java.util.Vector a16 = this.colorSelectionListeners;
                int i52 = a16.size();
                if(i51 >= i52)
                {
                    break;
                }
                else
                {
                    java.util.Vector a17 = this.colorSelectionListeners;
                    Object a18 = a17.elementAt(i51);
                    com.cim.AIA.ColorSelectionListener dummy4 = (com.cim.AIA.ColorSelectionListener)a18;
                    ((com.cim.AIA.ColorSelectionListener)a18).repaint();
                    int i53 = i51 + 1;
                    i51 = i53;
                }
            }
        }
    }
    
    public void removeColorSelection(com.cim.AIA.ColorSelection a)
    {
        java.util.Vector a0 = this.colorSelections;
        int i = a0.removeElement((Object)a)?1:0;
    }
    
    public void removeColorSelectionListener(com.cim.AIA.ColorSelectionListener a)
    {
        java.util.Vector a0 = this.colorSelectionListeners;
        Object a1 = a;
        int i = a0.contains(a1)?1:0;
        if(i != 0)
        {
            java.util.Vector a2 = this.colorSelectionListeners;
            int i0 = a2.removeElement(a1)?1:0;
        }
    }
    
    public void removeFontSelection(com.cim.AIA.FontSelection a)
    {
        java.util.Vector a0 = this.fontSelections;
        int i = a0.removeElement((Object)a)?1:0;
    }
    
    public void removeFontSelectionListener(com.cim.AIA.FontSelectionListener a)
    {
        java.util.Vector a0 = this.fontSelectionListeners;
        Object a1 = a;
        int i = a0.contains(a1)?1:0;
        if(i != 0)
        {
            java.util.Vector a2 = this.fontSelectionListeners;
            int i0 = a2.removeElement(a1)?1:0;
        }
    }
}