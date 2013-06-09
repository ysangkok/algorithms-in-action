public class Alignment3DEditDialog extends java.awt.Dialog implements com.cim.AIA.ExitListener {
    final private static long serialVersionUID = 6536259069078192924L;
    final public static int DEFAULT_WIDTH = 400;
    final public static int DEFAULT_HEIGHT = 300;
    protected java.awt.Button ok;
    protected java.awt.Button dump;
    protected java.awt.Scrollbar xScroll;
    protected java.awt.Scrollbar yScroll;
    protected java.awt.Scrollbar zScroll;
    protected java.awt.Scrollbar xTranslate;
    protected java.awt.Scrollbar yTranslate;
    protected java.awt.Scrollbar zTranslate;
    protected java.awt.Scrollbar xMove;
    
    public Alignment3DEditDialog(java.awt.Frame a, String s, Alignment a0)
    {
        super(a, s, false);
        String s0 = localization.Messages.getString("Alignment3DEditDialog.0");
        java.awt.Button a1 = new java.awt.Button(s0);
        this.ok = a1;
        String s1 = localization.Messages.getString("Alignment3DEditDialog.1");
        java.awt.Button a2 = new java.awt.Button(s1);
        this.dump = a2;
        java.awt.Panel a3 = new java.awt.Panel();
        java.awt.Panel a4 = new java.awt.Panel();
        java.awt.Scrollbar a5 = new java.awt.Scrollbar(0, -65, 1, -90, 90);
        this.xScroll = a5;
        java.awt.Scrollbar a6 = new java.awt.Scrollbar(0, 0, 1, -90, 90);
        this.yScroll = a6;
        java.awt.Scrollbar a7 = new java.awt.Scrollbar(0, 0, 1, -90, 90);
        this.zScroll = a7;
        java.awt.Scrollbar a8 = new java.awt.Scrollbar(0, 0, 1, -100, 100);
        this.xTranslate = a8;
        java.awt.Scrollbar a9 = new java.awt.Scrollbar(0, 0, 1, -100, 100);
        this.yTranslate = a9;
        java.awt.Scrollbar a10 = new java.awt.Scrollbar(0, 0, 1, -100, 100);
        this.zTranslate = a10;
        java.awt.Scrollbar a11 = new java.awt.Scrollbar(0, 50, 1, -100, 200);
        this.xMove = a11;
        java.awt.GridLayout a12 = new java.awt.GridLayout(7, 1);
        a4.setLayout((java.awt.LayoutManager)a12);
        java.awt.Scrollbar a13 = this.xScroll;
        java.awt.Component a14 = a4.add((java.awt.Component)a13);
        java.awt.Scrollbar a15 = this.yScroll;
        java.awt.Component a16 = a4.add((java.awt.Component)a15);
        java.awt.Scrollbar a17 = this.zScroll;
        java.awt.Component a18 = a4.add((java.awt.Component)a17);
        java.awt.Scrollbar a19 = this.xTranslate;
        java.awt.Component a20 = a4.add((java.awt.Component)a19);
        java.awt.Scrollbar a21 = this.yTranslate;
        java.awt.Component a22 = a4.add((java.awt.Component)a21);
        java.awt.Scrollbar a23 = this.zTranslate;
        java.awt.Component a24 = a4.add((java.awt.Component)a23);
        java.awt.Scrollbar a25 = this.xMove;
        java.awt.Component a26 = a4.add((java.awt.Component)a25);
        java.awt.GridLayout a27 = new java.awt.GridLayout(3, 1);
        a3.setLayout((java.awt.LayoutManager)a27);
        java.awt.Component a28 = a3.add((java.awt.Component)a4);
        java.awt.Button a29 = this.dump;
        java.awt.Component a30 = a3.add((java.awt.Component)a29);
        java.awt.Button a31 = this.ok;
        java.awt.Component a32 = a3.add((java.awt.Component)a31);
        java.awt.Component a33 = this.add((java.awt.Component)a3);
        java.awt.Button a34 = this.ok;
        Alignment3DEditDialog$1 a35 = new Alignment3DEditDialog$1(this);
        a34.addActionListener((java.awt.event.ActionListener)a35);
        java.awt.Button a36 = this.dump;
        Alignment3DEditDialog$2 a37 = new Alignment3DEditDialog$2(this);
        a36.addActionListener((java.awt.event.ActionListener)a37);
        java.awt.Scrollbar a38 = this.xScroll;
        Alignment3DEditDialog$3 a39 = new Alignment3DEditDialog$3(this, a0);
        a38.addAdjustmentListener((java.awt.event.AdjustmentListener)a39);
        java.awt.Scrollbar a40 = this.yScroll;
        Alignment3DEditDialog$4 a41 = new Alignment3DEditDialog$4(this, a0);
        a40.addAdjustmentListener((java.awt.event.AdjustmentListener)a41);
        java.awt.Scrollbar a42 = this.zScroll;
        Alignment3DEditDialog$5 a43 = new Alignment3DEditDialog$5(this, a0);
        a42.addAdjustmentListener((java.awt.event.AdjustmentListener)a43);
        java.awt.Scrollbar a44 = this.xTranslate;
        Alignment3DEditDialog$6 a45 = new Alignment3DEditDialog$6(this, a0);
        a44.addAdjustmentListener((java.awt.event.AdjustmentListener)a45);
        java.awt.Scrollbar a46 = this.yTranslate;
        Alignment3DEditDialog$7 a47 = new Alignment3DEditDialog$7(this, a0);
        a46.addAdjustmentListener((java.awt.event.AdjustmentListener)a47);
        java.awt.Scrollbar a48 = this.zTranslate;
        Alignment3DEditDialog$8 a49 = new Alignment3DEditDialog$8(this, a0);
        a48.addAdjustmentListener((java.awt.event.AdjustmentListener)a49);
        java.awt.Scrollbar a50 = this.xMove;
        Alignment3DEditDialog$9 a51 = new Alignment3DEditDialog$9(this, a0);
        a50.addAdjustmentListener((java.awt.event.AdjustmentListener)a51);
        this.setSize(400, 300);
        this.setResizable(false);
    }
    
    public Alignment3DEditDialog(String s, Alignment a)
    {
        java.awt.Frame a0 = new java.awt.Frame();
        this(a0, s, a);
    }
    
    public void processExitEvent(com.cim.AIA.ExitEvent a)
    {
        this.setVisible(false);
        this.removeAll();
        this.dispose();
    }
}