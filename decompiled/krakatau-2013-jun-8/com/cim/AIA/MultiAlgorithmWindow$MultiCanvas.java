package com.cim.AIA;

public class MultiAlgorithmWindow$MultiCanvas extends java.awt.Frame {
    final private static long serialVersionUID = -8625342235649276241L;
    com.cim.AIA.CodeCanvas canvas;
    boolean isVisible;
    String triggerKey;
    String unHighlight;
    final com.cim.AIA.MultiAlgorithmWindow this$0;
    
    MultiAlgorithmWindow$MultiCanvas(com.cim.AIA.MultiAlgorithmWindow a, String s, String s0, String s1, String s2, String s3, com.cim.AIA.ExplainationListener a0, com.cim.AIA.HelpListener a1)
    {
        this.this$0 = a;
        Object a2 = a0;
        Object a3 = a1;
        StringBuilder a4 = new StringBuilder();
        StringBuilder a5 = a4.append("Code Canvas");
        StringBuilder a6 = a5.append(s);
        String s4 = a6.toString();
        super(s4);
        this.isVisible = false;
        com.cim.AIA.AlgorithmApplet a7 = a.algorithmApplet;
        com.cim.AIA.AlgorithmApplet a8 = a.algorithmApplet;
        java.net.URL a9 = a8.getCodeBase();
        String s5 = a9.toString();
        StringBuilder a10 = new StringBuilder();
        com.cim.AIA.AlgorithmApplet a11 = a.algorithmApplet;
        java.net.URL a12 = a11.getCodeBase();
        String s6 = a12.toString();
        StringBuilder a13 = a10.append(s6);
        String s7 = com.cim.AIA.MultiAlgorithmWindow.access$000();
        StringBuilder a14 = a13.append(s7);
        String s8 = a14.toString();
        com.cim.AIA.CodeCanvas a15 = new com.cim.AIA.CodeCanvas((java.applet.Applet)a7, s5, s1, s8);
        this.canvas = a15;
        java.awt.ScrollPane a16 = new java.awt.ScrollPane(0);
        com.cim.AIA.CodeCanvas a17 = this.canvas;
        a17.setParent((java.awt.Container)a16);
        com.cim.AIA.CodeCanvas a18 = this.canvas;
        java.awt.Component a19 = a16.add((java.awt.Component)a18);
        com.cim.AIA.CodeCanvas a20 = this.canvas;
        com.cim.AIA.Logger a21 = a.getLogger();
        com.cim.AIA.BreakPoint a22 = a.getBreakPoint();
        com.cim.AIA.LadderTree a23 = com.cim.AIA.CodeCanvas.getLadderTreeFromFile(s0, s1, a21, a22);
        a20.setLadderTree(a23);
        com.cim.AIA.CodeCanvas a24 = this.canvas;
        Object a25 = com.cim.AIA.MultiAlgorithmWindow.access$100(a);
        a24.addMouseListener((java.awt.event.MouseListener)a25);
        com.cim.AIA.CodeCanvas a26 = this.canvas;
        a26.addExplainationListener((com.cim.AIA.ExplainationListener)a2);
        com.cim.AIA.CodeCanvas a27 = this.canvas;
        a27.addHelpListener((com.cim.AIA.HelpListener)a3);
        this.triggerKey = s2;
        this.unHighlight = s3;
        java.awt.Component a28 = this.add((java.awt.Component)a16);
        java.awt.Dimension a29 = new java.awt.Dimension(350, 400);
        this.setSize(a29);
        java.awt.Point a30 = new java.awt.Point(200, 200);
        this.setLocation(a30);
        this.setVisible(false);
        Object a31 = com.cim.AIA.MultiAlgorithmWindow.access$200(a);
        this.addWindowListener((java.awt.event.WindowListener)a31);
    }
}