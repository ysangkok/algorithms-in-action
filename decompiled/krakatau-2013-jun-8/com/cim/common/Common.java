package com.cim.common;

public class Common {
    public static boolean DEBUG;
    public static int DEBUG_LEVEL;
    final public static int MENUBAR_HEIGHT = 0;
    final public static int CONTROL_PANEL_HEIGHT = 30;
    final public static int CONTROL_PANEL_LEFT_MARGIN = 5;
    final public static int CONTROL_PANEL_TOP_MARGIN = 5;
    final public static int CONTROL_PANEL_HGAP = 5;
    final public static int CONTROL_PANEL_VGAP = 2;
    final public static java.awt.Color CONTROL_PANEL_BGCOLOR;
    final public static java.awt.Dimension CONTROL_PANEL_BUTTON_DIMENSION;
    final public static java.awt.Dimension CONTROL_PANEL_LABEL_DIMENSION;
    final public static java.awt.Dimension CONTROL_PANEL_SPEEDBAR_DIMENSION;
    final public static java.awt.Font FT_BUTTON;
    final public static java.awt.Font FT_LABEL;
    final public static int MAX_SPEED = 1000;
    private static com.cim.common.Common instance;
    
    public static void debug(String s)
    {
        com.cim.common.Common.debug(s, 5);
    }
    
    public static void debug(String s, int i)
    {
        int i0 = com.cim.common.Common.DEBUG?1:0;
        label0: {
            if(i0 == 0)
            {
                break label0;
            }
            int i1 = com.cim.common.Common.DEBUG_LEVEL;
            if(i < i1)
            {
                com.cim.common.Common.message(s);
            }
        }
    }
    
    public static void exitError()
    {
        System.exit(1);
    }
    
    public static void exitOk()
    {
        System.exit(0);
    }
    
    public static com.cim.common.Common getInstance()
    {
        com.cim.common.Common a = com.cim.common.Common.instance;
        if(a == null)
        {
            com.cim.common.Common a0 = new com.cim.common.Common();
            com.cim.common.Common.instance = a0;
        }
        com.cim.common.Common a1 = com.cim.common.Common.instance;
        return a1;
    }
    
    public static void message(String s)
    {
        java.io.PrintStream a = System.out;
        a.println(s);
    }
    
    protected Common()
    {
        super();
    }
    
    public java.awt.Image getImage(String s)
    {
        java.awt.Image a = null;
        java.awt.Canvas a0 = new java.awt.Canvas();
        java.awt.MediaTracker a1 = new java.awt.MediaTracker((java.awt.Component)a0);
        java.awt.Toolkit a2 = java.awt.Toolkit.getDefaultToolkit();
        StringBuilder a3 = new StringBuilder();
        StringBuilder a4 = a3.append("FILE TO BE LOADED ");
        StringBuilder a5 = a4.append(s);
        String s0 = a5.toString();
        com.cim.common.Common.debug(s0, 5);
        label10: {
            java.awt.Image a6 = null;
            label15: {
                Exception a7 = null;
                label1: {
                    Class a8 = null;
                    StringBuilder a9 = null;
                    StringBuilder a10 = null;
                    StringBuilder a11 = null;
                    String s1 = null;
                    java.io.InputStream a12 = null;
                    int i = 0;
                    byte[] a13 = null;
                    java.awt.Image a14 = null;
                    label0: try
                    {
                        a8 = ((Object)this).getClass();
                        break label0;
                    }
                    catch(Exception a15)
                    {
                        a7 = a15;
                        break label1;
                    }
                    label2: try
                    {
                        a9 = new StringBuilder();
                        break label2;
                    }
                    catch(Exception a16)
                    {
                        a7 = a16;
                        break label1;
                    }
                    label3: try
                    {
                        a10 = a9.append("/");
                        break label3;
                    }
                    catch(Exception a17)
                    {
                        a7 = a17;
                        break label1;
                    }
                    label4: try
                    {
                        a11 = a10.append(s);
                        break label4;
                    }
                    catch(Exception a18)
                    {
                        a7 = a18;
                        break label1;
                    }
                    label5: try
                    {
                        s1 = a11.toString();
                        break label5;
                    }
                    catch(Exception a19)
                    {
                        a7 = a19;
                        break label1;
                    }
                    label6: try
                    {
                        a12 = a8.getResourceAsStream(s1);
                        break label6;
                    }
                    catch(Exception a20)
                    {
                        a7 = a20;
                        break label1;
                    }
                    label7: {
                        java.io.PrintStream a21 = null;
                        if(a12 != null)
                        {
                            break label7;
                        }
                        label8: try
                        {
                            a21 = System.out;
                            break label8;
                        }
                        catch(Exception a22)
                        {
                            a7 = a22;
                            break label1;
                        }
                        label9: try
                        {
                            a21.println("getResourceAsStream returned null!");
                            break label9;
                        }
                        catch(Exception a23)
                        {
                            a7 = a23;
                            break label1;
                        }
                        a = null;
                        break label10;
                    }
                    label11: try
                    {
                        i = a12.available();
                        break label11;
                    }
                    catch(Exception a24)
                    {
                        a7 = a24;
                        break label1;
                    }
                    label12: try
                    {
                        a13 = new byte[i];
                        break label12;
                    }
                    catch(NegativeArraySizeException a25)
                    {
                        a7 = a25;
                        break label1;
                    }
                    label13: try
                    {
                        int i0 = a12.read(a13);
                        break label13;
                    }
                    catch(Exception a26)
                    {
                        a7 = a26;
                        break label1;
                    }
                    label14: try
                    {
                        a14 = a2.createImage(a13);
                        break label14;
                    }
                    catch(Exception a27)
                    {
                        a7 = a27;
                        break label1;
                    }
                    a6 = a14;
                    break label15;
                }
                java.io.PrintStream a28 = System.out;
                a28.println("Exception while getting image??");
                a7.printStackTrace();
                a6 = null;
            }
            a1.addImage(a6, 1);
            {
                label17: {
                    InterruptedException a29 = null;
                    label16: {
                        int i1 = 0;
                        java.io.PrintStream a30 = null;
                        try
                        {
                            a1.waitForID(1);
                        }
                        catch(InterruptedException a31)
                        {
                            a29 = a31;
                            break label16;
                        }
                        try
                        {
                            i1 = a1.isErrorAny()?1:0;
                        }
                        catch(InterruptedException a32)
                        {
                            a29 = a32;
                            break label16;
                        }
                        if(i1 == 0)
                        {
                            break label17;
                        }
                        try
                        {
                            a30 = System.out;
                        }
                        catch(InterruptedException a33)
                        {
                            a29 = a33;
                            break label16;
                        }
                        try
                        {
                            a30.println("Could not load all the images..");
                            break label17;
                        }
                        catch(InterruptedException a34)
                        {
                            a29 = a34;
                        }
                    }
                    a29.printStackTrace();
                }
            }
            a = a6;
        }
        return a;
    }
    
    public java.awt.Image getImage(java.net.URL a)
    {
        String s = a.toString();
        java.awt.Image a0 = this.getImage(s);
        return a0;
    }
    
    static
    {
        com.cim.common.Common.DEBUG = false;
        com.cim.common.Common.DEBUG_LEVEL = 2;
        java.awt.Color a = java.awt.Color.lightGray;
        com.cim.common.Common.CONTROL_PANEL_BGCOLOR = a;
        java.awt.Dimension a0 = new java.awt.Dimension(50, 20);
        com.cim.common.Common.CONTROL_PANEL_BUTTON_DIMENSION = a0;
        java.awt.Dimension a1 = new java.awt.Dimension(25, 10);
        com.cim.common.Common.CONTROL_PANEL_LABEL_DIMENSION = a1;
        java.awt.Dimension a2 = new java.awt.Dimension(150, 10);
        com.cim.common.Common.CONTROL_PANEL_SPEEDBAR_DIMENSION = a2;
        java.awt.Font a3 = new java.awt.Font("Helvetica", 3, 11);
        com.cim.common.Common.FT_BUTTON = a3;
        java.awt.Font a4 = new java.awt.Font("Helvetica", 0, 10);
        com.cim.common.Common.FT_LABEL = a4;
        com.cim.common.Common.instance = null;
    }
}