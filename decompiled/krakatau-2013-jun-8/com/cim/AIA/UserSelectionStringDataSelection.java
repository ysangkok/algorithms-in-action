package com.cim.AIA;

public class UserSelectionStringDataSelection extends com.cim.AIA.DataSelection {
    final private static long serialVersionUID = -8110746691025590588L;
    final public static int ALLOW_TEXT = 1;
    final public static int ALLOW_NUMBERS = 2;
    final public static int ALLOW_OTHERS = 4;
    final public static int ALLOW_SPACE = 32;
    final public static int TOLOWER = 8;
    final public static int TOUPPER = 16;
    protected String items;
    protected int maxLength;
    protected int minLength;
    protected int param;
    protected String label1;
    protected String label2;
    protected String title;
    
    public UserSelectionStringDataSelection(String s, boolean b, com.cim.AIA.AnimationWindow a, String s0, int i, int i0)
    {
        super(s, b, a);
        this.title = s0;
        String s1 = this.items;
        this.items = s1;
        this.minLength = i;
        this.maxLength = i0;
        this.label1 = null;
        this.label2 = null;
        this.param = 39;
    }
    
    public UserSelectionStringDataSelection(String s, boolean b, com.cim.AIA.AnimationWindow a, String s0, int i, int i0, String s1, String s2)
    {
        super(s, b, a);
        this.title = s0;
        String s3 = this.items;
        this.items = s3;
        this.minLength = i;
        this.maxLength = i0;
        this.label1 = s1;
        this.label2 = s2;
        this.param = 39;
    }
    
    public UserSelectionStringDataSelection(String s, boolean b, com.cim.AIA.AnimationWindow a, String s0, int i, int i0, String s1, String s2, int i1)
    {
        super(s, b, a);
        this.title = s0;
        String s3 = this.items;
        this.items = s3;
        this.minLength = i;
        this.maxLength = i0;
        this.label1 = s1;
        this.label2 = s2;
        this.param = i1;
    }
    
    public com.cim.AIA.Copyable getData()
    {
        com.cim.AIA.UserSelectionStringDataDialog a = null;
        com.cim.AIA.AnimationWindow a0 = this.animationWindow;
        Object a1 = a0.getCurrentData();
        String[] dummy = (String[])a1;
        String[] a2 = (String[])a1;
        String s = this.label1;
        label2: {
            label1: {
                label0: {
                    if(s == null)
                    {
                        break label0;
                    }
                    String s0 = this.label2;
                    if(s0 != null)
                    {
                        break label1;
                    }
                }
                String s1 = this.title;
                int i = this.minLength;
                int i0 = this.maxLength;
                int i1 = this.param;
                com.cim.AIA.UserSelectionStringDataDialog a3 = new com.cim.AIA.UserSelectionStringDataDialog(s1, a2, i, i0, i1);
                a = a3;
                break label2;
            }
            String s2 = this.title;
            int i2 = this.minLength;
            int i3 = this.maxLength;
            String s3 = this.label1;
            String s4 = this.label2;
            int i4 = this.param;
            com.cim.AIA.UserSelectionStringDataDialog a4 = new com.cim.AIA.UserSelectionStringDataDialog(s2, a2, i2, i3, s3, s4, i4);
            a = a4;
        }
        a.setVisible(true);
        String[] a5 = a.getData();
        com.cim.AIA.StringArray a6 = new com.cim.AIA.StringArray(a5);
        return (com.cim.AIA.Copyable)a6;
    }
}