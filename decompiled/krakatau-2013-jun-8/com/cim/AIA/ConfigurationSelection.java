package com.cim.AIA;

abstract public class ConfigurationSelection {
    final public static String ALL_CLASSES = "Applies To All Classes";
    protected java.util.Vector classNameAppliesTo;
    
    public ConfigurationSelection()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.classNameAppliesTo = a;
    }
    
    public void addClass(com.cim.AIA.ConfigurationSelectionListener a)
    {
        java.util.Vector a0 = this.classNameAppliesTo;
        Object a1 = a;
        String s = ((com.cim.AIA.ConfigurationSelectionListener)a1).getClassName();
        a0.addElement((Object)s);
    }
    
    public boolean appliesTo(com.cim.AIA.ConfigurationSelectionListener a)
    {
        Object a0 = a;
        int i = 0;
        while(true)
        {
            int i0 = 0;
            java.util.Vector a1 = this.classNameAppliesTo;
            Object a2 = a0;
            int i1 = a1.size();
            Object a3 = a2;
            label1: {
                Object a4 = null;
                Object a5 = null;
                label0: {
                    Object a6 = a3;
                    if(i < i1)
                    {
                        a4 = a6;
                        break label0;
                    }
                    i0 = 0;
                    break label1;
                }
                java.util.Vector a7 = this.classNameAppliesTo;
                Object a8 = a4;
                Object a9 = a7.elementAt(i);
                Object a10 = a8;
                String dummy = (String)a9;
                String s = (String)a9;
                Object a11 = a10;
                int i2 = s.equalsIgnoreCase("Applies To All Classes")?1:0;
                Object a12 = a11;
                label2: {
                    Object a13 = a12;
                    if(i2 == 0)
                    {
                        a5 = a13;
                        break label2;
                    }
                    i0 = 1;
                    break label1;
                }
                String s0 = ((com.cim.AIA.ConfigurationSelectionListener)a5).getClassName();
                Object a14 = a5;
                int i3 = s.equalsIgnoreCase(s0)?1:0;
                Object a15 = a14;
                Object a16 = a15;
                if(i3 == 0)
                {
                    Object a17 = a16;
                    int i4 = i + 1;
                    a0 = a17;
                    i = i4;
                    continue;
                }
                i0 = 1;
            }
            return i0 != 0;
        }
    }
    
    public void appliesToAll()
    {
        java.util.Vector a = this.classNameAppliesTo;
        a.addElement((Object)"Applies To All Classes");
    }
    
    abstract public void restoreOriginal();
}