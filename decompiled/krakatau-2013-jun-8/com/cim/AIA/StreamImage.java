package com.cim.AIA;

public class StreamImage {
    public StreamImage()
    {
        super();
    }
    
    public static java.awt.Image getImage(java.io.InputStream a)
    {
        java.awt.Image a0 = null;
        label1: {
            java.awt.Image a1 = null;
            label0: {
                if(a != null)
                {
                    break label0;
                }
                a0 = null;
                break label1;
            }
            byte[] a2 = new byte[2000];
            label3: {
                label2: {
                    java.awt.Toolkit a3 = null;
                    try
                    {
                        int i = a.read(a2);
                    }
                    catch(java.io.IOException ignoredException)
                    {
                        break label2;
                    }
                    try
                    {
                        a3 = java.awt.Toolkit.getDefaultToolkit();
                    }
                    catch(java.io.IOException ignoredException0)
                    {
                        break label2;
                    }
                    try
                    {
                        a1 = a3.createImage(a2);
                        break label3;
                    }
                    catch(java.io.IOException ignoredException1)
                    {
                    }
                }
                a0 = null;
                break label1;
            }
            a0 = a1;
        }
        return a0;
    }
}