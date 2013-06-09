package com.cim.AIA;

public class UserSelectionIntArrayDataSelection extends com.cim.AIA.DataSelection {
    final private static long serialVersionUID = 6336065059735441792L;
    protected int[] items;
    protected int maxValue;
    protected int minValue;
    protected int minimumNumberOfElements;
    protected int maximumNumberOfElements;
    protected String title;
    
    public UserSelectionIntArrayDataSelection(String s, boolean b, com.cim.AIA.AnimationWindow a, String s0, int i, int i0, int i1, int i2)
    {
        super(s, b, a);
        this.title = s0;
        int[] a0 = this.items;
        this.items = a0;
        this.minValue = i;
        this.maxValue = i0;
        this.minimumNumberOfElements = i1;
        this.maximumNumberOfElements = i2;
    }
    
    public com.cim.AIA.Copyable getData()
    {
        com.cim.AIA.AnimationWindow a = this.animationWindow;
        Object a0 = a.getCurrentData();
        int[] dummy = (int[])a0;
        int[] a1 = (int[])a0;
        String s = this.title;
        int i = this.minValue;
        int i0 = this.maxValue;
        int i1 = this.minimumNumberOfElements;
        int i2 = this.maximumNumberOfElements;
        com.cim.AIA.UserSelectionDataDialog a2 = new com.cim.AIA.UserSelectionDataDialog(s, a1, i, i0, i1, i2);
        a2.setVisible(true);
        int[] a3 = a2.getData();
        com.cim.AIA.IntArray a4 = new com.cim.AIA.IntArray(a3);
        return (com.cim.AIA.Copyable)a4;
    }
}