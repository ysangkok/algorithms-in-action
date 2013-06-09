public class AlignmentCostDialog extends java.awt.Dialog implements com.cim.AIA.ExitListener, java.awt.event.KeyListener, java.awt.event.MouseMotionListener {
    final private static long serialVersionUID = -785833018587654574L;
    final public static String DEFAULT_TITLE;
    final public static String DIST_DEFAULT_STRING1;
    final public static String DIST_DEFAULT_STRING2;
    final public static String DIST_DEFAULT_STRING3;
    final public static String DIST_DEFAULT_STRING4;
    final public static String DIST_DEFAULT_STRING5;
    final public static String SIM_DEFAULT_STRING1;
    final public static String SIM_DEFAULT_STRING2;
    final public static String SIM_DEFAULT_STRING3;
    final public static String SIM_DEFAULT_STRING4;
    final public static String SIM_DEFAULT_STRING5;
    final public static int DEFAULT_WIDTH = 400;
    final public static int DEFAULT_HEIGHT = 300;
    final public static int DEFAULT_VARIATION = 1;
    protected java.awt.Button ok;
    protected java.awt.Button cancel;
    protected java.awt.Checkbox ignore;
    protected String string1;
    protected String string2;
    protected String string3;
    protected String string4;
    protected String string5;
    protected java.awt.TextField str1;
    protected java.awt.TextField str2;
    protected java.awt.TextField str3;
    protected java.awt.TextField str4;
    protected java.awt.TextField str5;
    protected boolean cancelWasPressed;
    protected int minValue;
    protected int maxValue;
    protected java.awt.Frame frame;
    
    public AlignmentCostDialog(java.awt.Frame a, String s, String[] a0, int i, int i0, int i1, int i2, String s0, String s1, String s2, String s3, String s4, int i3)
    {
        String s5 = null;
        String s6 = null;
        String s7 = null;
        String s8 = null;
        String s9 = null;
        super(a, s, true);
        String s10 = localization.Messages.getString("AlignmentCostDialog.0");
        java.awt.Button a1 = new java.awt.Button(s10);
        this.ok = a1;
        String s11 = localization.Messages.getString("AlignmentCostDialog.1");
        java.awt.Button a2 = new java.awt.Button(s11);
        this.cancel = a2;
        String s12 = localization.Messages.getString("AlignmentCostDialog.2");
        java.awt.Checkbox a3 = new java.awt.Checkbox(s12, false);
        this.ignore = a3;
        this.cancelWasPressed = false;
        this.minValue = 0;
        this.maxValue = 0;
        label0: {
            if(s0 != null)
            {
                s5 = s0;
                break label0;
            }
            int i4 = Alignment.runningMode;
            if(i4 != 1)
            {
                String s13 = AlignmentCostDialog.SIM_DEFAULT_STRING1;
                s5 = s13;
            }
            else
            {
                String s14 = AlignmentCostDialog.DIST_DEFAULT_STRING1;
                s5 = s14;
            }
        }
        label1: {
            if(s1 != null)
            {
                s6 = s1;
                break label1;
            }
            int i5 = Alignment.runningMode;
            if(i5 != 1)
            {
                String s15 = AlignmentCostDialog.SIM_DEFAULT_STRING2;
                s6 = s15;
            }
            else
            {
                String s16 = AlignmentCostDialog.DIST_DEFAULT_STRING2;
                s6 = s16;
            }
        }
        label2: {
            if(s2 != null)
            {
                s7 = s2;
                break label2;
            }
            int i6 = Alignment.runningMode;
            if(i6 != 1)
            {
                String s17 = AlignmentCostDialog.SIM_DEFAULT_STRING3;
                s7 = s17;
            }
            else
            {
                String s18 = AlignmentCostDialog.DIST_DEFAULT_STRING3;
                s7 = s18;
            }
        }
        label3: {
            if(s3 != null)
            {
                s8 = s3;
                break label3;
            }
            int i7 = Alignment.runningMode;
            if(i7 != 1)
            {
                String s19 = AlignmentCostDialog.SIM_DEFAULT_STRING4;
                s8 = s19;
            }
            else
            {
                String s20 = AlignmentCostDialog.DIST_DEFAULT_STRING4;
                s8 = s20;
            }
        }
        label4: {
            if(s4 != null)
            {
                s9 = s4;
                break label4;
            }
            int i8 = Alignment.runningMode;
            if(i8 != 1)
            {
                String s21 = AlignmentCostDialog.SIM_DEFAULT_STRING5;
                s9 = s21;
            }
            else
            {
                String s22 = AlignmentCostDialog.DIST_DEFAULT_STRING5;
                s9 = s22;
            }
        }
        this.addKeyListener((java.awt.event.KeyListener)this);
        this.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        this.frame = a;
        label5: {
            if(a0 == null)
            {
                break label5;
            }
            label8: {
                label7: {
                    label6: {
                        int i9 = a0.length;
                        if(i9 <= 0)
                        {
                            break label6;
                        }
                        String s23 = a0[0];
                        if(s23 != null)
                        {
                            break label7;
                        }
                    }
                    this.string1 = "";
                    break label8;
                }
                String s24 = a0[0];
                this.string1 = s24;
            }
            label11: {
                label10: {
                    label9: {
                        int i10 = a0.length;
                        if(i10 <= 1)
                        {
                            break label9;
                        }
                        String s25 = a0[1];
                        if(s25 != null)
                        {
                            break label10;
                        }
                    }
                    this.string2 = "";
                    break label11;
                }
                String s26 = a0[1];
                this.string2 = s26;
            }
            label14: {
                label13: {
                    label12: {
                        int i11 = a0.length;
                        if(i11 <= 2)
                        {
                            break label12;
                        }
                        String s27 = a0[2];
                        if(s27 != null)
                        {
                            break label13;
                        }
                    }
                    this.string3 = "";
                    break label14;
                }
                String s28 = a0[2];
                this.string3 = s28;
            }
            label17: {
                label16: {
                    label15: {
                        int i12 = a0.length;
                        if(i12 <= 3)
                        {
                            break label15;
                        }
                        String s29 = a0[3];
                        if(s29 != null)
                        {
                            break label16;
                        }
                    }
                    this.string4 = "";
                    break label17;
                }
                String s30 = a0[3];
                this.string4 = s30;
            }
            label19: {
                label18: {
                    int i13 = a0.length;
                    if(i13 <= 4)
                    {
                        break label18;
                    }
                    String s31 = a0[4];
                    if(s31 != null)
                    {
                        break label19;
                    }
                }
                this.string5 = "";
                break label5;
            }
            String s32 = a0[4];
            this.string5 = s32;
        }
        this.minValue = i;
        this.maxValue = i0;
        java.awt.Color a4 = java.awt.Color.lightGray;
        this.setBackground(a4);
        java.awt.GridBagLayout a5 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a6 = new java.awt.GridBagConstraints();
        this.setLayout((java.awt.LayoutManager)a5);
        String s33 = localization.Messages.getString("AlignmentCostDialog.19");
        java.awt.Label a7 = new java.awt.Label(s33);
        java.awt.Label a8 = new java.awt.Label(s5);
        java.awt.Label a9 = new java.awt.Label(s6);
        java.awt.Label a10 = new java.awt.Label(s7);
        java.awt.Label a11 = new java.awt.Label(s8);
        java.awt.Label a12 = new java.awt.Label(s9);
        String s34 = this.string1;
        java.awt.TextField a13 = new java.awt.TextField(s34, 10);
        this.str1 = a13;
        String s35 = this.string2;
        java.awt.TextField a14 = new java.awt.TextField(s35, 10);
        this.str2 = a14;
        String s36 = this.string3;
        java.awt.TextField a15 = new java.awt.TextField(s36, 10);
        this.str3 = a15;
        String s37 = this.string4;
        java.awt.TextField a16 = new java.awt.TextField(s37, 10);
        this.str4 = a16;
        String s38 = this.string5;
        java.awt.TextField a17 = new java.awt.TextField(s38, 10);
        this.str5 = a17;
        int i14 = Alignment.runningMode;
        if(i14 == 1)
        {
            java.awt.TextField a18 = this.str1;
            a18.setEnabled(false);
        }
        if(i3 != 1)
        {
            java.awt.TextField a19 = this.str3;
            a19.setEnabled(false);
        }
        else
        {
            java.awt.TextField a20 = this.str4;
            a20.setEnabled(false);
            java.awt.TextField a21 = this.str5;
            a21.setEnabled(false);
        }
        this.buildConstraints(a6, 0, 0, 2, 1, 0, 0);
        a6.anchor = 17;
        java.awt.Insets a22 = new java.awt.Insets(5, 10, 0, 0);
        a6.insets = a22;
        a5.setConstraints((java.awt.Component)a7, a6);
        java.awt.Component a23 = this.add((java.awt.Component)a7);
        this.buildConstraints(a6, 0, 1, 1, 1, 30, 0);
        a6.anchor = 10;
        a6.fill = 10;
        java.awt.Insets a24 = new java.awt.Insets(10, 10, 0, 0);
        a6.insets = a24;
        a5.setConstraints((java.awt.Component)a8, a6);
        java.awt.Component a25 = this.add((java.awt.Component)a8);
        this.buildConstraints(a6, 0, 2, 1, 1, 30, 0);
        a5.setConstraints((java.awt.Component)a9, a6);
        java.awt.Component a26 = this.add((java.awt.Component)a9);
        this.buildConstraints(a6, 0, 3, 1, 1, 30, 0);
        a5.setConstraints((java.awt.Component)a10, a6);
        java.awt.Component a27 = this.add((java.awt.Component)a10);
        this.buildConstraints(a6, 0, 4, 1, 1, 30, 0);
        a5.setConstraints((java.awt.Component)a11, a6);
        java.awt.Component a28 = this.add((java.awt.Component)a11);
        this.buildConstraints(a6, 0, 5, 1, 1, 30, 0);
        a5.setConstraints((java.awt.Component)a12, a6);
        java.awt.Component a29 = this.add((java.awt.Component)a12);
        java.awt.Insets a30 = new java.awt.Insets(10, 10, 0, 10);
        a6.insets = a30;
        this.buildConstraints(a6, 1, 1, 1, 1, 70, 0);
        java.awt.TextField a31 = this.str1;
        a5.setConstraints((java.awt.Component)a31, a6);
        java.awt.TextField a32 = this.str1;
        java.awt.Component a33 = this.add((java.awt.Component)a32);
        this.buildConstraints(a6, 1, 2, 1, 1, 70, 0);
        java.awt.TextField a34 = this.str2;
        a5.setConstraints((java.awt.Component)a34, a6);
        java.awt.TextField a35 = this.str2;
        java.awt.Component a36 = this.add((java.awt.Component)a35);
        this.buildConstraints(a6, 1, 3, 1, 1, 70, 0);
        java.awt.TextField a37 = this.str3;
        a5.setConstraints((java.awt.Component)a37, a6);
        java.awt.TextField a38 = this.str3;
        java.awt.Component a39 = this.add((java.awt.Component)a38);
        this.buildConstraints(a6, 1, 4, 1, 1, 70, 0);
        java.awt.TextField a40 = this.str4;
        a5.setConstraints((java.awt.Component)a40, a6);
        java.awt.TextField a41 = this.str4;
        java.awt.Component a42 = this.add((java.awt.Component)a41);
        this.buildConstraints(a6, 1, 5, 1, 1, 70, 0);
        java.awt.TextField a43 = this.str5;
        a5.setConstraints((java.awt.Component)a43, a6);
        java.awt.TextField a44 = this.str5;
        java.awt.Component a45 = this.add((java.awt.Component)a44);
        java.awt.Panel a46 = new java.awt.Panel();
        java.awt.Button a47 = this.ok;
        java.awt.Component a48 = a46.add((java.awt.Component)a47);
        java.awt.Button a49 = this.cancel;
        java.awt.Component a50 = a46.add((java.awt.Component)a49);
        java.awt.Insets a51 = new java.awt.Insets(10, 10, 5, 10);
        a6.insets = a51;
        this.buildConstraints(a6, 0, 6, 2, 1, 0, 0);
        a5.setConstraints((java.awt.Component)a46, a6);
        java.awt.Component a52 = this.add((java.awt.Component)a46);
        java.awt.Button a53 = this.ok;
        AlignmentCostDialog$1 a54 = new AlignmentCostDialog$1(this);
        a53.addActionListener((java.awt.event.ActionListener)a54);
        java.awt.Button a55 = this.cancel;
        AlignmentCostDialog$2 a56 = new AlignmentCostDialog$2(this);
        a55.addActionListener((java.awt.event.ActionListener)a56);
        this.setSize(i1, i2);
        this.setResizable(false);
        this.pack();
        java.awt.Toolkit a57 = this.getToolkit();
        java.awt.Dimension a58 = a57.getScreenSize();
    }
    
    public AlignmentCostDialog(int i)
    {
        String s = AlignmentCostDialog.DEFAULT_TITLE;
        this(s, i, 400, 300, 1);
    }
    
    public AlignmentCostDialog(String s, int i, int i0, int i1, int i2)
    {
        java.awt.Frame a = new java.awt.Frame();
        this(a, s, (String[])null, 0, i, i0, i1, (String)null, (String)null, (String)null, (String)null, (String)null, i2);
        java.awt.Frame a0 = this.frame;
        a0.setSize(i0, i1);
    }
    
    public AlignmentCostDialog(String s, String[] a, int i, int i0, int i1)
    {
        java.awt.Frame a0 = new java.awt.Frame();
        this(a0, s, a, i, i0, 400, 300, (String)null, (String)null, (String)null, (String)null, (String)null, i1);
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
        label2: {
            label1: {
                label0: {
                    java.awt.TextField a = null;
                    String s = null;
                    java.awt.TextField a0 = null;
                    String s0 = null;
                    java.awt.TextField a1 = null;
                    String s1 = null;
                    java.awt.TextField a2 = null;
                    String s2 = null;
                    java.awt.TextField a3 = null;
                    String s3 = null;
                    try
                    {
                        a = this.str1;
                    }
                    catch(NumberFormatException ignoredException)
                    {
                        break label0;
                    }
                    try
                    {
                        s = a.getText();
                    }
                    catch(NumberFormatException ignoredException0)
                    {
                        break label0;
                    }
                    try
                    {
                        int i0 = Integer.parseInt(s);
                    }
                    catch(NumberFormatException ignoredException1)
                    {
                        break label0;
                    }
                    try
                    {
                        a0 = this.str2;
                    }
                    catch(NumberFormatException ignoredException2)
                    {
                        break label0;
                    }
                    try
                    {
                        s0 = a0.getText();
                    }
                    catch(NumberFormatException ignoredException3)
                    {
                        break label0;
                    }
                    try
                    {
                        int i1 = Integer.parseInt(s0);
                    }
                    catch(NumberFormatException ignoredException4)
                    {
                        break label0;
                    }
                    try
                    {
                        a1 = this.str3;
                    }
                    catch(NumberFormatException ignoredException5)
                    {
                        break label0;
                    }
                    try
                    {
                        s1 = a1.getText();
                    }
                    catch(NumberFormatException ignoredException6)
                    {
                        break label0;
                    }
                    try
                    {
                        int i2 = Integer.parseInt(s1);
                    }
                    catch(NumberFormatException ignoredException7)
                    {
                        break label0;
                    }
                    try
                    {
                        a2 = this.str4;
                    }
                    catch(NumberFormatException ignoredException8)
                    {
                        break label0;
                    }
                    try
                    {
                        s2 = a2.getText();
                    }
                    catch(NumberFormatException ignoredException9)
                    {
                        break label0;
                    }
                    try
                    {
                        int i3 = Integer.parseInt(s2);
                    }
                    catch(NumberFormatException ignoredException10)
                    {
                        break label0;
                    }
                    try
                    {
                        a3 = this.str5;
                    }
                    catch(NumberFormatException ignoredException11)
                    {
                        break label0;
                    }
                    try
                    {
                        s3 = a3.getText();
                    }
                    catch(NumberFormatException ignoredException12)
                    {
                        break label0;
                    }
                    try
                    {
                        int i4 = Integer.parseInt(s3);
                        break label1;
                    }
                    catch(NumberFormatException ignoredException13)
                    {
                    }
                }
                String s4 = localization.Messages.getString("AlignmentCostDialog.20");
                com.cim.common.MessageDialog a4 = new com.cim.common.MessageDialog(s4, true, true);
                String s5 = localization.Messages.getString("AlignmentCostDialog.21");
                a4.setTitle(s5);
                a4.setVisible(true);
                i = 0;
                break label2;
            }
            java.awt.TextField a5 = this.str1;
            String s6 = a5.getText();
            int i5 = Integer.parseInt(s6);
            java.awt.TextField a6 = this.str2;
            String s7 = a6.getText();
            int i6 = Integer.parseInt(s7);
            java.awt.TextField a7 = this.str3;
            String s8 = a7.getText();
            int i7 = Integer.parseInt(s8);
            java.awt.TextField a8 = this.str4;
            String s9 = a8.getText();
            int i8 = Integer.parseInt(s9);
            java.awt.TextField a9 = this.str5;
            String s10 = a9.getText();
            int i9 = Integer.parseInt(s10);
            int i10 = this.minValue;
            label3: {
                if(i5 < i10)
                {
                    break label3;
                }
                int i11 = this.maxValue;
                if(i5 > i11)
                {
                    break label3;
                }
                int i12 = this.minValue;
                if(i6 < i12)
                {
                    break label3;
                }
                int i13 = this.maxValue;
                if(i6 > i13)
                {
                    break label3;
                }
                int i14 = this.minValue;
                if(i7 < i14)
                {
                    break label3;
                }
                int i15 = this.maxValue;
                if(i7 > i15)
                {
                    break label3;
                }
                int i16 = this.minValue;
                if(i8 < i16)
                {
                    break label3;
                }
                int i17 = this.maxValue;
                if(i8 > i17)
                {
                    break label3;
                }
                int i18 = this.minValue;
                if(i9 < i18)
                {
                    break label3;
                }
                int i19 = this.maxValue;
                if(i9 > i19)
                {
                    break label3;
                }
                i = 1;
                break label2;
            }
            StringBuilder a10 = new StringBuilder();
            String s11 = localization.Messages.getString("AlignmentCostDialog.22");
            StringBuilder a11 = a10.append(s11);
            int i20 = this.minValue;
            StringBuilder a12 = a11.append(i20);
            String s12 = localization.Messages.getString("AlignmentCostDialog.23");
            StringBuilder a13 = a12.append(s12);
            int i21 = this.maxValue;
            StringBuilder a14 = a13.append(i21);
            String s13 = a14.toString();
            com.cim.common.MessageDialog a15 = new com.cim.common.MessageDialog(s13, true, true);
            String s14 = localization.Messages.getString("AlignmentCostDialog.24");
            a15.setTitle(s14);
            a15.setVisible(true);
            i = 0;
        }
        return i != 0;
    }
    
    public int[] getData()
    {
        int[] a = null;
        int i = this.cancelPressed()?1:0;
        if(i == 0)
        {
            int[] a0 = new int[5];
            java.awt.TextField a1 = this.str1;
            String s = a1.getText();
            int i0 = Integer.parseInt(s);
            a0[0] = i0;
            java.awt.TextField a2 = this.str2;
            String s0 = a2.getText();
            int i1 = Integer.parseInt(s0);
            a0[1] = i1;
            java.awt.TextField a3 = this.str3;
            String s1 = a3.getText();
            int i2 = Integer.parseInt(s1);
            a0[2] = i2;
            java.awt.TextField a4 = this.str4;
            String s2 = a4.getText();
            int i3 = Integer.parseInt(s2);
            a0[3] = i3;
            java.awt.TextField a5 = this.str5;
            String s3 = a5.getText();
            int i4 = Integer.parseInt(s3);
            a0[4] = i4;
            a = a0;
        }
        else
        {
            int[] a6 = new int[0];
            a = a6;
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
        String s = localization.Messages.getString("AlignmentCostDialog.3");
        AlignmentCostDialog.DEFAULT_TITLE = s;
        String s0 = localization.Messages.getString("AlignmentCostDialog.4");
        AlignmentCostDialog.DIST_DEFAULT_STRING1 = s0;
        String s1 = localization.Messages.getString("AlignmentCostDialog.5");
        AlignmentCostDialog.DIST_DEFAULT_STRING2 = s1;
        String s2 = localization.Messages.getString("AlignmentCostDialog.6");
        AlignmentCostDialog.DIST_DEFAULT_STRING3 = s2;
        String s3 = localization.Messages.getString("AlignmentCostDialog.7");
        AlignmentCostDialog.DIST_DEFAULT_STRING4 = s3;
        String s4 = localization.Messages.getString("AlignmentCostDialog.8");
        AlignmentCostDialog.DIST_DEFAULT_STRING5 = s4;
        String s5 = localization.Messages.getString("AlignmentCostDialog.9");
        AlignmentCostDialog.SIM_DEFAULT_STRING1 = s5;
        String s6 = localization.Messages.getString("AlignmentCostDialog.10");
        AlignmentCostDialog.SIM_DEFAULT_STRING2 = s6;
        String s7 = localization.Messages.getString("AlignmentCostDialog.11");
        AlignmentCostDialog.SIM_DEFAULT_STRING3 = s7;
        String s8 = localization.Messages.getString("AlignmentCostDialog.12");
        AlignmentCostDialog.SIM_DEFAULT_STRING4 = s8;
        String s9 = localization.Messages.getString("AlignmentCostDialog.13");
        AlignmentCostDialog.SIM_DEFAULT_STRING5 = s9;
    }
}