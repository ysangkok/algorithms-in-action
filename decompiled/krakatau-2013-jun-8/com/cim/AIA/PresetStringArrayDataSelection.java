package com.cim.AIA;

public class PresetStringArrayDataSelection extends com.cim.AIA.DataSelection {
    final private static long serialVersionUID = 3546261853244134347L;
    protected String[] data;
    
    public PresetStringArrayDataSelection(String s, boolean b, com.cim.AIA.AnimationWindow a, String[] a0)
    {
        super(s, b, a);
        this.data = a0;
    }
    
    public com.cim.AIA.Copyable getData()
    {
        String[] a = this.data;
        com.cim.AIA.StringArray a0 = new com.cim.AIA.StringArray(a);
        return (com.cim.AIA.Copyable)a0;
    }
}