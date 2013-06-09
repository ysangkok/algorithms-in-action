package com.cim.AIA;

public class UserSelectionDataDialog extends java.awt.Dialog implements com.cim.AIA.ExitListener, java.awt.event.KeyListener, java.awt.event.MouseMotionListener {
    final private static long serialVersionUID = 3553395302039363907L;
    final public static String DEFAULT_TITLE;
    final public static int DEFAULT_WIDTH = 400;
    final public static int DEFAULT_HEIGHT = 300;
    protected com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas elementArrayCanvas;
    protected com.cim.AIA.UserSelectionDataDialog$KeyPadPanel keyPad;
    protected java.awt.TextField displayLabel;
    protected java.awt.Button add;
    protected java.awt.Button delete;
    protected java.awt.Button modify;
    protected java.awt.Button ok;
    protected java.awt.Button cancel;
    protected boolean cancelWasPressed;
    protected int minNumberOfElements;
    protected int maxNumberOfElements;
    protected int maximumValue;
    protected int minimumValue;
    protected java.awt.Frame frame;
    
    public UserSelectionDataDialog(java.awt.Frame a, String s, int[] a0, int i, int i0, int i1, int i2, int i3, int i4)
    {
        super(a, s, true);
        String s0 = localization.Messages.getString("UserSelectionDataDialog.0");
        java.awt.Button a1 = new java.awt.Button(s0);
        this.add = a1;
        String s1 = localization.Messages.getString("UserSelectionDataDialog.1");
        java.awt.Button a2 = new java.awt.Button(s1);
        this.delete = a2;
        String s2 = localization.Messages.getString("UserSelectionDataDialog.2");
        java.awt.Button a3 = new java.awt.Button(s2);
        this.modify = a3;
        String s3 = localization.Messages.getString("UserSelectionDataDialog.3");
        java.awt.Button a4 = new java.awt.Button(s3);
        this.ok = a4;
        String s4 = localization.Messages.getString("UserSelectionDataDialog.4");
        java.awt.Button a5 = new java.awt.Button(s4);
        this.cancel = a5;
        this.cancelWasPressed = false;
        this.minNumberOfElements = 0;
        this.maxNumberOfElements = 0;
        this.maximumValue = 0;
        this.minimumValue = 0;
        this.addKeyListener((java.awt.event.KeyListener)this);
        this.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        this.frame = a;
        this.minimumValue = i;
        this.maximumValue = i0;
        this.minNumberOfElements = i1;
        this.maxNumberOfElements = i2;
        java.awt.ScrollPane a6 = new java.awt.ScrollPane(0);
        int i5 = i4 / 4;
        a6.setSize(i3, i5);
        StringBuilder a7 = new StringBuilder();
        String s5 = localization.Messages.getString("UserSelectionDataDialog.6");
        StringBuilder a8 = a7.append(s5);
        int i6 = this.maximumValue;
        StringBuilder a9 = a8.append(i6);
        String s6 = a9.toString();
        int i7 = s6.length();
        int i8 = i7 + 3;
        String s7 = localization.Messages.getString("UserSelectionDataDialog.7");
        java.awt.TextField a10 = new java.awt.TextField(s7, i8);
        this.displayLabel = a10;
        java.awt.TextField a11 = this.displayLabel;
        a11.setEditable(true);
        java.awt.TextField a12 = this.displayLabel;
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a13 = new com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas(this, a12);
        this.elementArrayCanvas = a13;
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a14 = this.elementArrayCanvas;
        a14.setParent((java.awt.Container)a6);
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a15 = this.elementArrayCanvas;
        int i9 = i4 / 4;
        int i10 = i9 - 20;
        a15.setSize(i3, i10);
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a16 = this.elementArrayCanvas;
        a16.addMouseMotionListener((java.awt.event.MouseMotionListener)this);
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            int i11 = 0;
            while(true)
            {
                int i12 = a0.length;
                if(i11 >= i12)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a17 = this.elementArrayCanvas;
                    int i13 = a0[i11];
                    Integer a18 = new Integer(i13);
                    a17.additem((Object)a18, i11, true);
                    int i14 = i11 + 1;
                    i11 = i14;
                }
            }
        }
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a19 = this.elementArrayCanvas;
        a19.increment();
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a20 = this.elementArrayCanvas;
        a20.repaint();
        java.awt.TextField a21 = this.displayLabel;
        int i15 = this.minimumValue;
        int i16 = this.maximumValue;
        com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a22 = new com.cim.AIA.UserSelectionDataDialog$KeyPadPanel(this, a21, i15, i16);
        this.keyPad = a22;
        java.awt.GridBagLayout a23 = new java.awt.GridBagLayout();
        java.awt.GridBagConstraints a24 = new java.awt.GridBagConstraints();
        this.setLayout((java.awt.LayoutManager)a23);
        a24.ipadx = 2;
        a24.ipady = 2;
        a24.fill = 1;
        a24.anchor = 10;
        a24.gridwidth = 0;
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a25 = this.elementArrayCanvas;
        java.awt.Component a26 = a6.add((java.awt.Component)a25);
        a23.setConstraints((java.awt.Component)a6, a24);
        java.awt.Component a27 = this.add((java.awt.Component)a6);
        this.initialiseComponents();
        java.awt.Button a28 = this.add;
        com.cim.AIA.UserSelectionDataDialog$1 a29 = new com.cim.AIA.UserSelectionDataDialog$1(this);
        a28.addActionListener((java.awt.event.ActionListener)a29);
        java.awt.Button a30 = this.delete;
        com.cim.AIA.UserSelectionDataDialog$2 a31 = new com.cim.AIA.UserSelectionDataDialog$2(this);
        a30.addActionListener((java.awt.event.ActionListener)a31);
        java.awt.Button a32 = this.modify;
        com.cim.AIA.UserSelectionDataDialog$3 a33 = new com.cim.AIA.UserSelectionDataDialog$3(this);
        a32.addActionListener((java.awt.event.ActionListener)a33);
        java.awt.Button a34 = this.ok;
        com.cim.AIA.UserSelectionDataDialog$4 a35 = new com.cim.AIA.UserSelectionDataDialog$4(this);
        a34.addActionListener((java.awt.event.ActionListener)a35);
        java.awt.Button a36 = this.cancel;
        com.cim.AIA.UserSelectionDataDialog$5 a37 = new com.cim.AIA.UserSelectionDataDialog$5(this);
        a36.addActionListener((java.awt.event.ActionListener)a37);
        java.awt.TextField a38 = this.displayLabel;
        com.cim.AIA.UserSelectionDataDialog$6 a39 = new com.cim.AIA.UserSelectionDataDialog$6(this);
        a38.addKeyListener((java.awt.event.KeyListener)a39);
        java.awt.TextField a40 = this.displayLabel;
        com.cim.AIA.UserSelectionDataDialog$7 a41 = new com.cim.AIA.UserSelectionDataDialog$7(this);
        a40.addTextListener((java.awt.event.TextListener)a41);
        java.awt.Panel a42 = new java.awt.Panel();
        java.awt.BorderLayout a43 = new java.awt.BorderLayout();
        a42.setLayout((java.awt.LayoutManager)a43);
        java.awt.Panel a44 = new java.awt.Panel();
        java.awt.TextField a45 = this.displayLabel;
        java.awt.Component a46 = a44.add((java.awt.Component)a45);
        String s8 = localization.Messages.getString("UserSelectionDataDialog.8");
        java.awt.Label a47 = new java.awt.Label(s8);
        a24.gridwidth = 1;
        java.awt.Insets a48 = new java.awt.Insets(0, 20, 0, 0);
        a24.insets = a48;
        a23.setConstraints((java.awt.Component)a47, a24);
        java.awt.Component a49 = this.add((java.awt.Component)a47);
        java.awt.Panel a50 = new java.awt.Panel();
        a24.weightx = 4.0;
        a24.gridwidth = 1;
        java.awt.Insets a51 = new java.awt.Insets(0, 0, 0, 0);
        a24.insets = a51;
        a23.setConstraints((java.awt.Component)a50, a24);
        java.awt.Component a52 = this.add((java.awt.Component)a50);
        String s9 = localization.Messages.getString("UserSelectionDataDialog.9");
        java.awt.Label a53 = new java.awt.Label(s9);
        a24.gridwidth = 0;
        a23.setConstraints((java.awt.Component)a53, a24);
        java.awt.Component a54 = this.add((java.awt.Component)a53);
        String s10 = localization.Messages.getString("UserSelectionDataDialog.10");
        java.awt.TextArea a55 = new java.awt.TextArea(s10, 5, 30, 3);
        a55.setEditable(false);
        String s11 = localization.Messages.getString("UserSelectionDataDialog.11");
        a55.append(s11);
        String s12 = localization.Messages.getString("UserSelectionDataDialog.12");
        a55.append(s12);
        String s13 = localization.Messages.getString("UserSelectionDataDialog.13");
        a55.append(s13);
        String s14 = localization.Messages.getString("UserSelectionDataDialog.14");
        a55.append(s14);
        a24.gridwidth = 1;
        a23.setConstraints((java.awt.Component)a44, a24);
        java.awt.Component a56 = this.add((java.awt.Component)a44);
        java.awt.Panel a57 = new java.awt.Panel();
        a24.weightx = 4.0;
        a24.gridwidth = 1;
        a23.setConstraints((java.awt.Component)a57, a24);
        java.awt.Component a58 = this.add((java.awt.Component)a57);
        a24.gridwidth = 0;
        java.awt.Insets a59 = new java.awt.Insets(0, 0, 10, 20);
        a24.insets = a59;
        a23.setConstraints((java.awt.Component)a55, a24);
        java.awt.Component a60 = this.add((java.awt.Component)a55);
        java.awt.Panel a61 = new java.awt.Panel();
        java.awt.Button a62 = this.modify;
        java.awt.Component a63 = a61.add((java.awt.Component)a62);
        java.awt.Button a64 = this.add;
        java.awt.Component a65 = a61.add((java.awt.Component)a64);
        java.awt.Button a66 = this.delete;
        java.awt.Component a67 = a61.add((java.awt.Component)a66);
        java.awt.Panel a68 = new java.awt.Panel();
        java.awt.Button a69 = this.ok;
        java.awt.Component a70 = a68.add((java.awt.Component)a69);
        java.awt.Button a71 = this.cancel;
        java.awt.Component a72 = a68.add((java.awt.Component)a71);
        java.awt.Insets a73 = new java.awt.Insets(0, 5, 0, 0);
        a24.insets = a73;
        a24.gridwidth = 0;
        com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a74 = this.keyPad;
        a23.setConstraints((java.awt.Component)a74, a24);
        com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a75 = this.keyPad;
        java.awt.Component a76 = this.add((java.awt.Component)a75);
        a24.gridwidth = 1;
        java.awt.Insets a77 = new java.awt.Insets(0, 40, 0, 0);
        a24.insets = a77;
        a23.setConstraints((java.awt.Component)a61, a24);
        java.awt.Component a78 = this.add((java.awt.Component)a61);
        java.awt.Insets a79 = new java.awt.Insets(0, 0, 0, 0);
        a24.insets = a79;
        a24.gridwidth = 0;
        a23.setConstraints((java.awt.Component)a68, a24);
        java.awt.Component a80 = this.add((java.awt.Component)a68);
        this.setSize(i3, i4);
        this.setResizable(false);
        this.pack();
        java.awt.Toolkit a81 = this.getToolkit();
        java.awt.Dimension a82 = a81.getScreenSize();
        int i17 = a82.width;
        java.awt.Dimension a83 = this.getSize();
        int i18 = a83.width;
        int i19 = i17 - i18;
        int i20 = i19 / 2;
        int i21 = a82.height;
        java.awt.Dimension a84 = this.getSize();
        int i22 = a84.height;
        int i23 = i21 - i22;
        int i24 = i23 / 2;
        this.setLocation(i20, i24);
        java.awt.TextField a85 = this.displayLabel;
        a85.requestFocus();
    }
    
    public UserSelectionDataDialog(int i, int i0)
    {
        String s = com.cim.AIA.UserSelectionDataDialog.DEFAULT_TITLE;
        this(s, i, i0, 400, 300);
    }
    
    public UserSelectionDataDialog(String s, int i, int i0, int i1, int i2)
    {
        java.awt.Frame a = new java.awt.Frame();
        this(a, s, (int[])null, 0, i, 0, i0, i1, i2);
        java.awt.Frame a0 = this.frame;
        a0.setSize(i1, i2);
    }
    
    public UserSelectionDataDialog(String s, int[] a, int i, int i0, int i1, int i2)
    {
        java.awt.Frame a0 = new java.awt.Frame();
        this(a0, s, a, i, i0, i1, i2, 400, 300);
    }
    
    protected void addInput(boolean b, boolean b0)
    {
        int i = this.valid()?1:0;
        int i0 = b?1:0;
        int i1 = b0?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a = this.elementArrayCanvas;
            int i2 = a.getNumberOfElements();
            int i3 = this.maxNumberOfElements;
            if(i2 < i3)
            {
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a0 = this.elementArrayCanvas;
                com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a1 = this.keyPad;
                int i4 = a1.getCurrentValue();
                Integer a2 = new Integer(i4);
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a3 = this.elementArrayCanvas;
                int i5 = a3.getCurrentPosition();
                a0.additem((Object)a2, i5, i1 != 0);
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a4 = this.elementArrayCanvas;
                a4.repaint();
                com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a5 = this.keyPad;
                a5.setCurrentValue(0, 0);
                this.initialiseComponents();
            }
        }
        if(i0 != 0)
        {
            java.awt.TextField a6 = this.displayLabel;
            a6.requestFocus();
        }
    }
    
    public boolean cancelPressed()
    {
        int i = this.cancelWasPressed?1:0;
        return i != 0;
    }
    
    protected void deleteInput(boolean b)
    {
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a = this.elementArrayCanvas;
        int i = b?1:0;
        int i0 = a.getNumberOfElements();
        int i1 = this.minNumberOfElements;
        label0: {
            if(i0 <= i1)
            {
                break label0;
            }
            com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a0 = this.elementArrayCanvas;
            int i2 = a0.getNumberOfElements();
            if(i2 > 0)
            {
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a1 = this.elementArrayCanvas;
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a2 = this.elementArrayCanvas;
                int i3 = a2.getCurrentPosition();
                a1.remove(i3);
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a3 = this.elementArrayCanvas;
                a3.repaint();
                this.initialiseComponents();
            }
        }
        if(i != 0)
        {
            java.awt.TextField a4 = this.displayLabel;
            a4.requestFocus();
        }
    }
    
    public int[] getData()
    {
        int[] a = null;
        int i = this.cancelPressed()?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                int[] a0 = new int[0];
                a = a0;
                break label1;
            }
            com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a1 = this.elementArrayCanvas;
            java.util.Vector a2 = a1.getObjects();
            int i0 = a2.size();
            int[] a3 = new int[i0];
            int i1 = 0;
            while(true)
            {
                int i2 = a2.size();
                if(i1 >= i2)
                {
                    break;
                }
                else
                {
                    Object a4 = a2.elementAt(i1);
                    Integer dummy = (Integer)a4;
                    Integer a5 = (Integer)a4;
                    int i3 = a5.intValue();
                    a3[i1] = i3;
                    int i4 = i1 + 1;
                    i1 = i4;
                }
            }
            a = a3;
        }
        return a;
    }
    
    protected boolean handleAKeyEvent(java.awt.event.KeyEvent a, boolean b)
    {
        int i = 0;
        int i0 = a.getKeyCode();
        int i1 = b?1:0;
        label1: {
            label0: {
                if(i0 != 38)
                {
                    break label0;
                }
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a0 = this.elementArrayCanvas;
                a0.decriment();
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a1 = this.elementArrayCanvas;
                a1.repaint();
                a.consume();
                i = 1;
                break label1;
            }
            label2: {
                if(i0 != 40)
                {
                    break label2;
                }
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a2 = this.elementArrayCanvas;
                a2.increment();
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a3 = this.elementArrayCanvas;
                a3.repaint();
                a.consume();
                i = 1;
                break label1;
            }
            label3: {
                if(i0 != 65)
                {
                    break label3;
                }
                this.addInput(i1 != 0, true);
                a.consume();
                i = 1;
                break label1;
            }
            label4: {
                if(i0 != 68)
                {
                    break label4;
                }
                this.deleteInput(i1 != 0);
                a.consume();
                i = 1;
                break label1;
            }
            label5: {
                if(i0 != 77)
                {
                    break label5;
                }
                this.modifyInput(i1 != 0);
                a.consume();
                i = 1;
                break label1;
            }
            if(i0 != 73)
            {
                i = 0;
            }
            else
            {
                this.addInput(i1 != 0, false);
                a.consume();
                i = 1;
            }
        }
        return i != 0;
    }
    
    protected void initialiseComponents()
    {
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a = this.elementArrayCanvas;
        int i = a.getNumberOfElements();
        int i0 = this.maxNumberOfElements;
        label1: {
            label0: {
                if(i >= i0)
                {
                    break label0;
                }
                int i1 = this.valid()?1:0;
                if(i1 == 0)
                {
                    break label0;
                }
                java.awt.Button a0 = this.add;
                a0.setEnabled(true);
                break label1;
            }
            java.awt.Button a1 = this.add;
            a1.setEnabled(false);
        }
        java.awt.TextField a2 = this.displayLabel;
        a2.setEnabled(true);
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a3 = this.elementArrayCanvas;
        int i2 = a3.getNumberOfElements();
        int i3 = this.minNumberOfElements;
        label4: {
            label3: {
                label2: {
                    if(i2 <= i3)
                    {
                        break label2;
                    }
                    com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a4 = this.elementArrayCanvas;
                    int i4 = a4.getNumberOfElements();
                    if(i4 > 0)
                    {
                        break label3;
                    }
                }
                java.awt.Button a5 = this.delete;
                a5.setEnabled(false);
                break label4;
            }
            java.awt.Button a6 = this.delete;
            a6.setEnabled(true);
        }
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a7 = this.elementArrayCanvas;
        int i5 = a7.getNumberOfElements();
        label7: {
            label6: {
                label5: {
                    if(i5 <= 0)
                    {
                        break label5;
                    }
                    int i6 = this.valid()?1:0;
                    if(i6 != 0)
                    {
                        break label6;
                    }
                }
                java.awt.Button a8 = this.modify;
                a8.setEnabled(false);
                break label7;
            }
            java.awt.Button a9 = this.modify;
            a9.setEnabled(true);
        }
        com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a10 = this.elementArrayCanvas;
        int i7 = a10.getNumberOfElements();
        int i8 = this.minNumberOfElements;
        if(i7 >= i8)
        {
            java.awt.Button a11 = this.ok;
            a11.setEnabled(true);
        }
        else
        {
            java.awt.Button a12 = this.ok;
            a12.setEnabled(false);
        }
        java.awt.Button a13 = this.cancel;
        a13.setEnabled(true);
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
    
    protected void modifyInput(boolean b)
    {
        int i = this.valid()?1:0;
        int i0 = b?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a = this.elementArrayCanvas;
            int i1 = a.getNumberOfElements();
            if(i1 > 0)
            {
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a0 = this.elementArrayCanvas;
                com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a1 = this.keyPad;
                int i2 = a1.getCurrentValue();
                Integer a2 = new Integer(i2);
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a3 = this.elementArrayCanvas;
                int i3 = a3.getCurrentPosition();
                a0.modify((Object)a2, i3);
                com.cim.AIA.UserSelectionDataDialog$ElementArrayCanvas a4 = this.elementArrayCanvas;
                a4.repaint();
                com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a5 = this.keyPad;
                a5.setCurrentValue(0, 0);
                this.initialiseComponents();
            }
        }
        if(i0 != 0)
        {
            java.awt.TextField a6 = this.displayLabel;
            a6.requestFocus();
        }
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
    
    protected boolean valid()
    {
        int i = 0;
        com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a = this.keyPad;
        int i0 = a.isValid()?1:0;
        label1: {
            int i1 = 0;
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            com.cim.AIA.UserSelectionDataDialog$KeyPadPanel a0 = this.keyPad;
            int i2 = a0.getCurrentValue();
            int i3 = this.minimumValue;
            label4: {
                label3: {
                    label2: {
                        if(i2 < i3)
                        {
                            break label2;
                        }
                        int i4 = this.maximumValue;
                        if(i2 <= i4)
                        {
                            break label3;
                        }
                    }
                    i1 = 0;
                    break label4;
                }
                i1 = 1;
            }
            i = i1;
        }
        return i != 0;
    }
    
    static
    {
        String s = localization.Messages.getString("UserSelectionDataDialog.5");
        com.cim.AIA.UserSelectionDataDialog.DEFAULT_TITLE = s;
    }
}