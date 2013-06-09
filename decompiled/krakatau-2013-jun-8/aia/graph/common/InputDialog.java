package aia.graph.common;

public class InputDialog extends java.awt.Dialog {
    private int m_value;
    private java.awt.Button m_btnOK;
    private java.awt.Button m_btnCancel;
    private java.awt.Label m_lblText;
    private java.awt.TextField m_txtValue;
    private aia.graph.common.GraphDialogEventHandler m_handler;
    private String m_prompt;
    private String m_title;
    private java.awt.Frame m_parent;
    
    public InputDialog(aia.graph.common.GraphDialogEventHandler a, java.awt.Frame a0, String s, boolean b, String s0, int i)
    {
        super(a0, s, b);
        Object a1 = a;
        this.m_prompt = null;
        this.m_parent = null;
        this.m_value = i;
        this.m_handler = (aia.graph.common.GraphDialogEventHandler)a1;
        this.m_prompt = s0;
        this.m_title = s;
        this.m_parent = a0;
        this.initGui();
    }
    
    private void initGui()
    {
        java.awt.GridBagLayout a = new java.awt.GridBagLayout();
        this.setLayout((java.awt.LayoutManager)a);
        this.setSize(200, 140);
        String s = localization.Messages.getString("InputDialog.0");
        java.awt.Button a0 = new java.awt.Button(s);
        this.m_btnOK = a0;
        String s0 = localization.Messages.getString("InputDialog.1");
        java.awt.Button a1 = new java.awt.Button(s0);
        this.m_btnCancel = a1;
        String s1 = this.m_prompt;
        java.awt.Label a2 = new java.awt.Label(s1);
        this.m_lblText = a2;
        int i = this.m_value;
        String s2 = Integer.toString(i);
        java.awt.TextField a3 = new java.awt.TextField(s2);
        this.m_txtValue = a3;
        java.awt.GridBagConstraints a4 = new java.awt.GridBagConstraints();
        a4.gridwidth = 0;
        a4.fill = 2;
        a4.anchor = 17;
        a4.weightx = 1.0;
        java.awt.Label a5 = this.m_lblText;
        a.setConstraints((java.awt.Component)a5, a4);
        java.awt.Label a6 = this.m_lblText;
        java.awt.Component a7 = this.add((java.awt.Component)a6);
        java.awt.GridBagConstraints a8 = new java.awt.GridBagConstraints();
        a8.gridwidth = 0;
        a8.fill = 2;
        a8.weightx = 1.0;
        java.awt.TextField a9 = this.m_txtValue;
        a.setConstraints((java.awt.Component)a9, a8);
        java.awt.TextField a10 = this.m_txtValue;
        java.awt.Component a11 = this.add((java.awt.Component)a10);
        java.awt.GridBagConstraints a12 = new java.awt.GridBagConstraints();
        a12.gridwidth = 2;
        java.awt.Button a13 = this.m_btnOK;
        a.setConstraints((java.awt.Component)a13, a12);
        java.awt.Button a14 = this.m_btnOK;
        java.awt.Component a15 = this.add((java.awt.Component)a14);
        a12.gridwidth = 2;
        java.awt.Button a16 = this.m_btnCancel;
        a.setConstraints((java.awt.Component)a16, a12);
        java.awt.Button a17 = this.m_btnCancel;
        java.awt.Component a18 = this.add((java.awt.Component)a17);
        java.awt.Button a19 = this.m_btnOK;
        aia.graph.common.InputDialog$1 a20 = new aia.graph.common.InputDialog$1(this);
        a19.addActionListener((java.awt.event.ActionListener)a20);
        java.awt.Button a21 = this.m_btnCancel;
        aia.graph.common.InputDialog$2 a22 = new aia.graph.common.InputDialog$2(this);
        a21.addActionListener((java.awt.event.ActionListener)a22);
        java.awt.TextField a23 = this.m_txtValue;
        aia.graph.common.InputDialog$3 a24 = new aia.graph.common.InputDialog$3(this);
        a23.addKeyListener((java.awt.event.KeyListener)a24);
        this.pack();
        java.awt.Frame a25 = this.m_parent;
        java.awt.Dimension a26 = a25.getSize();
        java.awt.Toolkit a27 = this.getToolkit();
        java.awt.Dimension a28 = a27.getScreenSize();
        int i0 = a28.width;
        java.awt.Dimension a29 = this.getSize();
        int i1 = a29.width;
        int i2 = i0 - i1;
        int i3 = i2 / 2;
        int i4 = a28.height;
        java.awt.Dimension a30 = this.getSize();
        int i5 = a30.height;
        int i6 = i4 - i5;
        int i7 = i6 / 2;
        this.setLocation(i3, i7);
    }
    
    public void btnOK_Clicked()
    {
        Object a = this.m_handler;
        String s = this.m_title;
        java.awt.TextField a0 = this.m_txtValue;
        String s0 = a0.getText();
        ((aia.graph.common.GraphDialogEventHandler)a).processDialogEvent(s, s0);
        this.dispose();
    }
    
    public void btnCancel_Clicked()
    {
        Object a = this.m_handler;
        String s = this.m_title;
        ((aia.graph.common.GraphDialogEventHandler)a).processDialogEvent(s, (String)null);
        this.dispose();
    }
    
    public void txtValue_KeyTyped(java.awt.event.KeyEvent a)
    {
        int i = a.getKeyChar();
        if(i == 10)
        {
            this.btnOK_Clicked();
        }
    }
}