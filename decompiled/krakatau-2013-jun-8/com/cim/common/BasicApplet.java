package com.cim.common;

abstract public class BasicApplet extends java.applet.Applet {
    final public static java.awt.Cursor CURSOR;
    public static boolean runAsApplet;
    public static String[] arguments;
    
    public BasicApplet()
    {
        super();
    }
    
    public java.net.URL getCodeBase()
    {
        java.net.URL a = null;
        int i = com.cim.common.BasicApplet.runAsApplet?1:0;
        if(i == 0)
        {
            a = null;
        }
        else
        {
            java.net.URL a0 = ((java.applet.Applet)this).getCodeBase();
            a = a0;
        }
        return a;
    }
    
    public String getParameter(String s)
    {
        String s0 = null;
        com.cim.common.Common.debug("Got here??", 2);
        int i = com.cim.common.BasicApplet.runAsApplet?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                String s1 = ((java.applet.Applet)this).getParameter(s);
                s0 = s1;
                break label1;
            }
            int i0 = 0;
            while(true)
            {
                String[] a = com.cim.common.BasicApplet.arguments;
                int i1 = a.length;
                label2: {
                    if(i0 < i1)
                    {
                        break label2;
                    }
                    s0 = null;
                    break;
                }
                String[] a0 = com.cim.common.BasicApplet.arguments;
                String s2 = a0[i0];
                java.util.StringTokenizer a1 = new java.util.StringTokenizer(s2, "=");
                int i2 = a1.countTokens();
                label4: {
                    label3: {
                        if(i2 != 2)
                        {
                            break label3;
                        }
                        String s3 = a1.nextToken();
                        int i3 = s3.equals((Object)s)?1:0;
                        if(i3 != 0)
                        {
                            break label4;
                        }
                    }
                    int i4 = i0 + 1;
                    i0 = i4;
                    continue;
                }
                String s4 = a1.nextToken();
                s0 = s4;
                break;
            }
        }
        return s0;
    }
    
    public void init()
    {
        java.awt.Cursor a = com.cim.common.BasicApplet.CURSOR;
        this.setCursor(a);
        java.io.PrintStream a0 = System.out;
        a0.println("In the basic Applet.. Cursor set...");
        java.awt.Color a1 = java.awt.Color.red;
        this.setBackground(a1);
    }
    
    static
    {
        java.awt.Cursor a = new java.awt.Cursor(0);
        com.cim.common.BasicApplet.CURSOR = a;
        com.cim.common.BasicApplet.runAsApplet = true;
    }
}