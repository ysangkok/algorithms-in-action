package com.cim.common;

abstract public class BasicCanvas extends java.awt.Canvas {
    public java.awt.Dimension offDimension;
    public java.awt.Image offImage;
    public java.awt.Graphics offGraphics;
    
    public BasicCanvas()
    {
        super();
        java.awt.Dimension a = new java.awt.Dimension(0, 0);
        this.offDimension = a;
    }
    
    public BasicCanvas(int i, int i0)
    {
        super();
        this.setSize(i, i0);
        java.awt.Dimension a = new java.awt.Dimension(i, i0);
        this.offDimension = a;
    }
    
    abstract public void display(java.awt.Graphics arg);
    
    
    public void overlayOffscreen()
    {
        java.awt.Image a = this.offImage;
        if(a == null)
        {
            java.io.PrintStream a0 = System.out;
            a0.println("OverLay Off: off Image  null, can't draw");
        }
        else
        {
            java.awt.Graphics a1 = this.getGraphics();
            java.awt.Image a2 = this.offImage;
            int i = a1.drawImage(a2, 0, 0, (java.awt.image.ImageObserver)this)?1:0;
        }
    }
    
    public void paint(java.awt.Graphics a)
    {
        this.update(a);
    }
    
    public void prepareDoubleBuffering(int i, int i0, java.awt.Graphics a)
    {
        com.cim.common.Common.debug("GOT here..", 5);
        java.awt.Graphics a0 = this.offGraphics;
        label1: {
            label0: {
                if(a0 == null)
                {
                    break label0;
                }
                java.awt.Dimension a1 = this.offDimension;
                int i1 = a1.width;
                if(i != i1)
                {
                    break label0;
                }
                java.awt.Dimension a2 = this.offDimension;
                int i2 = a2.height;
                if(i0 == i2)
                {
                    break label1;
                }
            }
            java.awt.Dimension a3 = new java.awt.Dimension(i, i0);
            this.offDimension = a3;
            label4: {
                label3: {
                    label2: {
                        if(i == 0)
                        {
                            break label2;
                        }
                        if(i0 != 0)
                        {
                            break label3;
                        }
                    }
                    java.io.PrintStream a4 = System.out;
                    a4.println("Prepare Double Buff: Ivalid Size Can create Image");
                    break label4;
                }
                java.awt.Image a5 = this.createImage(i, i0);
                this.offImage = a5;
            }
            java.awt.Image a6 = this.offImage;
            if(a6 == null)
            {
                java.io.PrintStream a7 = System.out;
                a7.println("Prepare Double Buff: Off Image null, can't get Graphics");
            }
            else
            {
                java.awt.Image a8 = this.offImage;
                java.awt.Graphics a9 = a8.getGraphics();
                this.offGraphics = a9;
            }
        }
        java.awt.Graphics a10 = this.offGraphics;
        if(a10 == null)
        {
            java.io.PrintStream a11 = System.out;
            a11.println("Prepare Double Buff: Off Graphics null, can't clear or set font");
        }
        else
        {
            java.awt.Graphics a12 = this.offGraphics;
            a12.clearRect(0, 0, i, i0);
            java.awt.Graphics a13 = this.offGraphics;
            java.awt.Font a14 = a.getFont();
            a13.setFont(a14);
            StringBuilder a15 = new StringBuilder();
            StringBuilder a16 = a15.append("NEW F SIZE ");
            java.awt.Font a17 = a.getFont();
            int i3 = a17.getSize();
            StringBuilder a18 = a16.append(i3);
            String s = a18.toString();
            com.cim.common.Common.debug(s, 5);
        }
    }
    
    public void update(java.awt.Graphics a)
    {
        java.awt.Graphics a0 = null;
        java.awt.Dimension a1 = this.getSize();
        if(a == null)
        {
            java.io.PrintStream a2 = System.out;
            a2.println("Update: g null, can't prepare!");
            a0 = null;
        }
        else
        {
            int i = a1.width;
            int i0 = a1.height;
            this.prepareDoubleBuffering(i, i0, a);
            a0 = a;
        }
        java.awt.Graphics a3 = this.offGraphics;
        if(a3 == null)
        {
            java.io.PrintStream a4 = System.out;
            a4.println("Update: off Graphics  null, can't display");
        }
        else
        {
            java.awt.Graphics a5 = this.offGraphics;
            this.display(a5);
        }
        java.awt.Image a6 = this.offImage;
        if(a6 == null)
        {
            java.io.PrintStream a7 = System.out;
            a7.println("Update: off Image  null, can't draw");
        }
        else
        {
            java.awt.Image a8 = this.offImage;
            int i1 = a0.drawImage(a8, 0, 0, (java.awt.image.ImageObserver)this)?1:0;
        }
    }
}