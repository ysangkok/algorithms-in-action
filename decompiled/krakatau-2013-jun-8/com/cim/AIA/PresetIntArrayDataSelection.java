package com.cim.AIA;

public class PresetIntArrayDataSelection extends com.cim.AIA.DataSelection {
    final private static long serialVersionUID = -7456313318083260168L;
    protected int[] data;
    
    public PresetIntArrayDataSelection(String s, boolean b, com.cim.AIA.AnimationWindow a, int[] a0)
    {
        super(s, b, a);
        this.data = a0;
    }
    
    public com.cim.AIA.Copyable getData()
    {
        int[] a = this.data;
        com.cim.AIA.IntArray a0 = new com.cim.AIA.IntArray(a);
        return (com.cim.AIA.Copyable)a0;
    }
}