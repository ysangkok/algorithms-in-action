package com.cim.AIA;

public class UserSelectionStringDataDialog extends java.awt.Dialog implements com.cim.AIA.ExitListener, java.awt.event.KeyListener, java.awt.event.MouseMotionListener {
    final private static long serialVersionUID = -2946624226568940176L;
    final public static String DEFAULT_TITLE;
    final public static String DEFAULT_STRING1;
    final public static String DEFAULT_STRING2;
    final public static int DEFAULT_WIDTH = 400;
    final public static int DEFAULT_HEIGHT = 300;
    protected java.awt.Button ok;
    protected java.awt.Button cancel;
    protected String string1;
    protected String string2;
    protected java.awt.TextField str1;
    protected java.awt.TextField str2;
    protected boolean cancelWasPressed;
    protected int minLength;
    protected int maxLength;
    protected int param;
    protected java.awt.Frame frame;
    
    public UserSelectionStringDataDialog(java.awt.Frame a, String s, String[] a0, int i, int i0, int i1, int i2, String s0, String s1)
    {
        this(a, s, a0, i, i0, i1, i2, s0, s1, 0);
    }
    
    public UserSelectionStringDataDialog(java.awt.Frame a, String s, String[] a0, int i, int i0, int i1, int i2, String s0, String s1, int i3)
    {
        super(a, s, true);
        String s2 = localization.Messages.getString("UserSelectionStringDataDialog.0");
        java.awt.Button a1 = new java.awt.Button(s2);
        this.ok = a1;
        String s3 = localization.Messages.getString("UserSelectionStringDataDialog.1");
        java.awt.Button a2 = new java.awt.Button(s3);
        this.cancel = a2;
        this.cancelWasPressed = false;
        this.minLength = 0;
        this.maxLength = 0;
        this.param = 0;
        this.addKeyListener((java.awt.event.KeyListener)this);
        this.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        this.param = i3;
        this.frame = a;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            label3: {
                label2: {
                    label1: {
                        int i4 = a0.length;
                        if(i4 <= 0)
                        {
                            break label1;
                        }
                        String s4 = a0[0];
                        if(s4 != null)
                        {
                            break label2;
                        }
                    }
                    String s5 = localization.Messages.getString("UserSelectionStringDataDialog.5");
                    this.string1 = s5;
                    break label3;
                }
                String s6 = a0[0];
                this.string1 = s6;
            }
            label5: {
                label4: {
                    int i5 = a0.length;
                    if(i5 <= 1)
                    {
                        break label4;
                    }
                    String s7 = a0[1];
                    if(s7 != null)
                    {
                        break label5;
                    }
                }
                String s8 = localization.Messages.getString("UserSelectionStringDataDialog.6");
                this.string2 = s8;
                break label0;
            }
            String s9 = a0[1];
            this.string2 = s9;
        }
        this.minLength = i;
        this.maxLength = i0;
        java.awt.Color a3 = java.awt.Color.lightGray;
        this.setBackground(a3);
        java.awt.GridBagLayout a4 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a5 = new java.awt.GridBagConstraints();
        this.setLayout((java.awt.LayoutManager)a4);
        String s10 = localization.Messages.getString("UserSelectionStringDataDialog.7");
        java.awt.Label a6 = new java.awt.Label(s10);
        java.awt.Label a7 = new java.awt.Label(s0);
        java.awt.Label a8 = new java.awt.Label(s1);
        String s11 = this.string1;
        java.awt.TextField a9 = new java.awt.TextField(s11, 30);
        this.str1 = a9;
        String s12 = this.string2;
        java.awt.TextField a10 = new java.awt.TextField(s12, 30);
        this.str2 = a10;
        this.buildConstraints(a5, 0, 0, 2, 1, 0, 0);
        a5.anchor = 17;
        java.awt.Insets a11 = new java.awt.Insets(5, 10, 0, 0);
        a5.insets = a11;
        a4.setConstraints((java.awt.Component)a6, a5);
        java.awt.Component a12 = this.add((java.awt.Component)a6);
        this.buildConstraints(a5, 0, 1, 1, 1, 30, 0);
        a5.anchor = 10;
        a5.fill = 10;
        java.awt.Insets a13 = new java.awt.Insets(10, 10, 0, 0);
        a5.insets = a13;
        a4.setConstraints((java.awt.Component)a7, a5);
        java.awt.Component a14 = this.add((java.awt.Component)a7);
        this.buildConstraints(a5, 0, 2, 1, 1, 30, 0);
        a4.setConstraints((java.awt.Component)a8, a5);
        java.awt.Component a15 = this.add((java.awt.Component)a8);
        java.awt.Insets a16 = new java.awt.Insets(10, 10, 0, 10);
        a5.insets = a16;
        this.buildConstraints(a5, 1, 1, 1, 1, 70, 0);
        java.awt.TextField a17 = this.str1;
        a4.setConstraints((java.awt.Component)a17, a5);
        java.awt.TextField a18 = this.str1;
        java.awt.Component a19 = this.add((java.awt.Component)a18);
        this.buildConstraints(a5, 1, 2, 1, 1, 70, 0);
        java.awt.TextField a20 = this.str2;
        a4.setConstraints((java.awt.Component)a20, a5);
        java.awt.TextField a21 = this.str2;
        java.awt.Component a22 = this.add((java.awt.Component)a21);
        java.awt.Panel a23 = new java.awt.Panel();
        java.awt.Button a24 = this.ok;
        java.awt.Component a25 = a23.add((java.awt.Component)a24);
        java.awt.Button a26 = this.cancel;
        java.awt.Component a27 = a23.add((java.awt.Component)a26);
        java.awt.Insets a28 = new java.awt.Insets(10, 10, 5, 10);
        a5.insets = a28;
        this.buildConstraints(a5, 0, 3, 2, 1, 0, 0);
        a4.setConstraints((java.awt.Component)a23, a5);
        java.awt.Component a29 = this.add((java.awt.Component)a23);
        java.awt.Button a30 = this.ok;
        com.cim.AIA.UserSelectionStringDataDialog$1 a31 = new com.cim.AIA.UserSelectionStringDataDialog$1(this);
        a30.addActionListener((java.awt.event.ActionListener)a31);
        java.awt.Button a32 = this.cancel;
        com.cim.AIA.UserSelectionStringDataDialog$2 a33 = new com.cim.AIA.UserSelectionStringDataDialog$2(this);
        a32.addActionListener((java.awt.event.ActionListener)a33);
        this.setSize(i1, i2);
        this.setResizable(false);
        this.pack();
        java.awt.Toolkit a34 = this.getToolkit();
        java.awt.Dimension a35 = a34.getScreenSize();
    }
    
    public UserSelectionStringDataDialog(int i)
    {
        String s = com.cim.AIA.UserSelectionStringDataDialog.DEFAULT_TITLE;
        this(s, i, 400, 300);
    }
    
    public UserSelectionStringDataDialog(String s, int i, int i0, int i1)
    {
        java.awt.Frame a = new java.awt.Frame();
        String s0 = com.cim.AIA.UserSelectionStringDataDialog.DEFAULT_STRING1;
        String s1 = com.cim.AIA.UserSelectionStringDataDialog.DEFAULT_STRING2;
        this(a, s, (String[])null, 0, i, i0, i1, s0, s1);
        java.awt.Frame a0 = this.frame;
        a0.setSize(i0, i1);
    }
    
    public UserSelectionStringDataDialog(String s, String[] a, int i, int i0)
    {
        java.awt.Frame a0 = new java.awt.Frame();
        String s0 = com.cim.AIA.UserSelectionStringDataDialog.DEFAULT_STRING1;
        String s1 = com.cim.AIA.UserSelectionStringDataDialog.DEFAULT_STRING2;
        this(a0, s, a, i, i0, 400, 300, s0, s1);
    }
    
    public UserSelectionStringDataDialog(String s, String[] a, int i, int i0, int i1)
    {
        java.awt.Frame a0 = new java.awt.Frame();
        String s0 = com.cim.AIA.UserSelectionStringDataDialog.DEFAULT_STRING1;
        String s1 = com.cim.AIA.UserSelectionStringDataDialog.DEFAULT_STRING2;
        this(a0, s, a, i, i0, 400, 300, s0, s1, i1);
    }
    
    public UserSelectionStringDataDialog(String s, String[] a, int i, int i0, String s0, String s1)
    {
        java.awt.Frame a0 = new java.awt.Frame();
        this(a0, s, a, i, i0, 400, 300, s0, s1);
    }
    
    public UserSelectionStringDataDialog(String s, String[] a, int i, int i0, String s0, String s1, int i1)
    {
        java.awt.Frame a0 = new java.awt.Frame();
        this(a0, s, a, i, i0, 400, 300, s0, s1, i1);
    }
    
    private void buildConstraints(java.awt.GridBagConstraints a, int i, int i0, int i1, int i2, int i3, int i4)
    {
        a.gridx = i;
        a.gridy = i0;
        a.gridwidth = i1;
        a.gridheight = i2;
        double d = (double)i3;
        a.weightx = d;
        double d0 = (double)i4;
        a.weighty = d0;
    }
    
    public boolean cancelPressed()
    {
        int i = this.cancelWasPressed?1:0;
        return i != 0;
    }
    
    protected boolean checkLength()
    {
        int i = 0;
        java.awt.TextField a = this.str1;
        String s = a.getText();
        int i0 = s.length();
        java.awt.TextField a0 = this.str2;
        String s0 = a0.getText();
        int i1 = s0.length();
        int i2 = this.minLength;
        label1: {
            label0: {
                if(i0 < i2)
                {
                    break label0;
                }
                int i3 = this.maxLength;
                if(i0 > i3)
                {
                    break label0;
                }
                int i4 = this.minLength;
                if(i1 < i4)
                {
                    break label0;
                }
                int i5 = this.maxLength;
                if(i1 > i5)
                {
                    break label0;
                }
                i = 1;
                break label1;
            }
            StringBuilder a1 = new StringBuilder();
            String s1 = localization.Messages.getString("UserSelectionStringDataDialog.24");
            StringBuilder a2 = a1.append(s1);
            int i6 = this.minLength;
            StringBuilder a3 = a2.append(i6);
            String s2 = localization.Messages.getString("UserSelectionStringDataDialog.25");
            StringBuilder a4 = a3.append(s2);
            int i7 = this.maxLength;
            StringBuilder a5 = a4.append(i7);
            String s3 = a5.toString();
            com.cim.common.MessageDialog a6 = new com.cim.common.MessageDialog(s3, true, true);
            String s4 = localization.Messages.getString("UserSelectionStringDataDialog.26");
            a6.setTitle(s4);
            a6.setVisible(true);
            i = 0;
        }
        return i != 0;
    }
    
    protected boolean checkParams()
    {
        int i = 0;
        java.awt.TextField a = this.str1;
        String s = a.getText();
        int i0 = 0;
        label1: {
            while(true)
            {
                int i1 = s.length();
                if(i0 >= i1)
                {
                    break;
                }
                int i2 = s.charAt(i0);
                int i3 = Character.isLetter((char)i2)?1:0;
                label0: {
                    if(i3 == 0)
                    {
                        break label0;
                    }
                    int i4 = this.param;
                    int i5 = i4 & 1;
                    if(i5 == 1)
                    {
                        break label0;
                    }
                    String s0 = localization.Messages.getString("UserSelectionStringDataDialog.8");
                    com.cim.common.MessageDialog a0 = new com.cim.common.MessageDialog(s0, true, true);
                    String s1 = localization.Messages.getString("UserSelectionStringDataDialog.9");
                    a0.setTitle(s1);
                    a0.setVisible(true);
                    i = 0;
                    break label1;
                }
                int i6 = s.charAt(i0);
                int i7 = Character.isDigit((char)i6)?1:0;
                label2: {
                    if(i7 == 0)
                    {
                        break label2;
                    }
                    int i8 = this.param;
                    int i9 = i8 & 2;
                    if(i9 == 2)
                    {
                        break label2;
                    }
                    String s2 = localization.Messages.getString("UserSelectionStringDataDialog.10");
                    com.cim.common.MessageDialog a1 = new com.cim.common.MessageDialog(s2, true, true);
                    String s3 = localization.Messages.getString("UserSelectionStringDataDialog.11");
                    a1.setTitle(s3);
                    a1.setVisible(true);
                    i = 0;
                    break label1;
                }
                int i10 = s.charAt(i0);
                int i11 = Character.isSpaceChar((char)i10)?1:0;
                label3: {
                    if(i11 == 0)
                    {
                        break label3;
                    }
                    int i12 = this.param;
                    int i13 = i12 & 32;
                    if(i13 == 32)
                    {
                        break label3;
                    }
                    String s4 = localization.Messages.getString("UserSelectionStringDataDialog.12");
                    com.cim.common.MessageDialog a2 = new com.cim.common.MessageDialog(s4, true, true);
                    String s5 = localization.Messages.getString("UserSelectionStringDataDialog.13");
                    a2.setTitle(s5);
                    a2.setVisible(true);
                    i = 0;
                    break label1;
                }
                int i14 = s.charAt(i0);
                int i15 = Character.isLetter((char)i14)?1:0;
                label5: {
                    label4: {
                        if(i15 != 0)
                        {
                            break label4;
                        }
                        int i16 = s.charAt(i0);
                        int i17 = Character.isDigit((char)i16)?1:0;
                        if(i17 != 0)
                        {
                            break label4;
                        }
                        int i18 = s.charAt(i0);
                        int i19 = Character.isSpaceChar((char)i18)?1:0;
                        if(i19 != 0)
                        {
                            break label4;
                        }
                        int i20 = this.param;
                        int i21 = i20 & 4;
                        if(i21 != 4)
                        {
                            break label5;
                        }
                    }
                    int i22 = i0 + 1;
                    i0 = i22;
                    continue;
                }
                String s6 = localization.Messages.getString("UserSelectionStringDataDialog.14");
                com.cim.common.MessageDialog a3 = new com.cim.common.MessageDialog(s6, true, true);
                String s7 = localization.Messages.getString("UserSelectionStringDataDialog.15");
                a3.setTitle(s7);
                a3.setVisible(true);
                i = 0;
                break label1;
            }
            java.awt.TextField a4 = this.str2;
            String s8 = a4.getText();
            int i23 = 0;
            while(true)
            {
                int i24 = s8.length();
                if(i23 >= i24)
                {
                    break;
                }
                int i25 = s8.charAt(i23);
                int i26 = Character.isLetter((char)i25)?1:0;
                label6: {
                    if(i26 == 0)
                    {
                        break label6;
                    }
                    int i27 = this.param;
                    int i28 = i27 & 1;
                    if(i28 == 1)
                    {
                        break label6;
                    }
                    String s9 = localization.Messages.getString("UserSelectionStringDataDialog.16");
                    com.cim.common.MessageDialog a5 = new com.cim.common.MessageDialog(s9, true, true);
                    String s10 = localization.Messages.getString("UserSelectionStringDataDialog.17");
                    a5.setTitle(s10);
                    a5.setVisible(true);
                    i = 0;
                    break label1;
                }
                int i29 = s8.charAt(i23);
                int i30 = Character.isDigit((char)i29)?1:0;
                label7: {
                    if(i30 == 0)
                    {
                        break label7;
                    }
                    int i31 = this.param;
                    int i32 = i31 & 2;
                    if(i32 == 2)
                    {
                        break label7;
                    }
                    String s11 = localization.Messages.getString("UserSelectionStringDataDialog.18");
                    com.cim.common.MessageDialog a6 = new com.cim.common.MessageDialog(s11, true, true);
                    String s12 = localization.Messages.getString("UserSelectionStringDataDialog.19");
                    a6.setTitle(s12);
                    a6.setVisible(true);
                    i = 0;
                    break label1;
                }
                int i33 = s8.charAt(i23);
                int i34 = Character.isSpaceChar((char)i33)?1:0;
                label8: {
                    if(i34 == 0)
                    {
                        break label8;
                    }
                    int i35 = this.param;
                    int i36 = i35 & 32;
                    if(i36 == 32)
                    {
                        break label8;
                    }
                    String s13 = localization.Messages.getString("UserSelectionStringDataDialog.20");
                    com.cim.common.MessageDialog a7 = new com.cim.common.MessageDialog(s13, true, true);
                    String s14 = localization.Messages.getString("UserSelectionStringDataDialog.21");
                    a7.setTitle(s14);
                    a7.setVisible(true);
                    i = 0;
                    break label1;
                }
                int i37 = s8.charAt(i23);
                int i38 = Character.isLetter((char)i37)?1:0;
                label10: {
                    label9: {
                        if(i38 != 0)
                        {
                            break label9;
                        }
                        int i39 = s8.charAt(i23);
                        int i40 = Character.isDigit((char)i39)?1:0;
                        if(i40 != 0)
                        {
                            break label9;
                        }
                        int i41 = s8.charAt(i23);
                        int i42 = Character.isSpaceChar((char)i41)?1:0;
                        if(i42 != 0)
                        {
                            break label9;
                        }
                        int i43 = this.param;
                        int i44 = i43 & 4;
                        if(i44 != 4)
                        {
                            break label10;
                        }
                    }
                    int i45 = i23 + 1;
                    i23 = i45;
                    continue;
                }
                String s15 = localization.Messages.getString("UserSelectionStringDataDialog.22");
                com.cim.common.MessageDialog a8 = new com.cim.common.MessageDialog(s15, true, true);
                String s16 = localization.Messages.getString("UserSelectionStringDataDialog.23");
                a8.setTitle(s16);
                a8.setVisible(true);
                i = 0;
                break label1;
            }
            int i46 = this.param;
            int i47 = i46 & 16;
            if(i47 == 16)
            {
                java.awt.TextField a9 = this.str1;
                java.awt.TextField a10 = this.str1;
                String s17 = a10.getText();
                String s18 = s17.toUpperCase();
                a9.setText(s18);
                java.awt.TextField a11 = this.str2;
                java.awt.TextField a12 = this.str2;
                String s19 = a12.getText();
                String s20 = s19.toUpperCase();
                a11.setText(s20);
            }
            int i48 = this.param;
            int i49 = i48 & 8;
            if(i49 == 8)
            {
                java.awt.TextField a13 = this.str1;
                java.awt.TextField a14 = this.str1;
                String s21 = a14.getText();
                String s22 = s21.toLowerCase();
                a13.setText(s22);
                java.awt.TextField a15 = this.str2;
                java.awt.TextField a16 = this.str2;
                String s23 = a16.getText();
                String s24 = s23.toLowerCase();
                a15.setText(s24);
            }
            i = 1;
        }
        return i != 0;
    }
    
    public String[] getData()
    {
        String[] a = null;
        int i = this.cancelPressed()?1:0;
        if(i == 0)
        {
            String[] a0 = new String[2];
            java.awt.TextField a1 = this.str1;
            String s = a1.getText();
            a0[0] = s;
            java.awt.TextField a2 = this.str2;
            String s0 = a2.getText();
            a0[1] = s0;
            a = a0;
        }
        else
        {
            String[] a3 = new String[0];
            a = a3;
        }
        return a;
    }
    
    protected boolean handleAKeyEvent(java.awt.event.KeyEvent a, boolean b)
    {
        int i = a.getKeyCode();
        return false;
    }
    
    protected void initialiseComponents()
    {
    }
    
    public void keyPressed(java.awt.event.KeyEvent a)
    {
        int i = this.handleAKeyEvent(a, true)?1:0;
    }
    
    public void keyReleased(java.awt.event.KeyEvent a)
    {
    }
    
    public void keyTyped(java.awt.event.KeyEvent a)
    {
    }
    
    public void mouseDragged(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseMoved(java.awt.event.MouseEvent a)
    {
    }
    
    public void processExitEvent(com.cim.AIA.ExitEvent a)
    {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
    }
    
    static
    {
        String s = localization.Messages.getString("UserSelectionStringDataDialog.2");
        com.cim.AIA.UserSelectionStringDataDialog.DEFAULT_TITLE = s;
        String s0 = localization.Messages.getString("UserSelectionStringDataDialog.3");
        com.cim.AIA.UserSelectionStringDataDialog.DEFAULT_STRING1 = s0;
        String s1 = localization.Messages.getString("UserSelectionStringDataDialog.4");
        com.cim.AIA.UserSelectionStringDataDialog.DEFAULT_STRING2 = s1;
    }
}