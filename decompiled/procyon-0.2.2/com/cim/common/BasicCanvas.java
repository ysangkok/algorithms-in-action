package com.cim.common;

import java.awt.*;

public abstract class BasicCanvas extends Canvas
{
    public Dimension offDimension;
    public Image offImage;
    public Graphics offGraphics;
    
    public BasicCanvas() {
        super();
        this.offDimension = new Dimension(0, 0);
    }
    
    public BasicCanvas(final int hsize, final int vsize) {
        super();
        this.setSize(hsize, vsize);
        this.offDimension = new Dimension(hsize, vsize);
    }
    
    public abstract void display(final Graphics p0);
    
    public void overlayOffscreen() {
        if (this.offImage != null) {
            this.getGraphics().drawImage(this.offImage, 0, 0, this);
        }
        else {
            System.out.println("OverLay Off: off Image  null, can't draw");
        }
    }
    
    public void paint(final Graphics g) {
        this.update(g);
    }
    
    public void prepareDoubleBuffering(final int hsize, final int vsize, final Graphics g) {
        Common.debug("GOT here..", 5);
        if (this.offGraphics == null || hsize != this.offDimension.width || vsize != this.offDimension.height) {
            this.offDimension = new Dimension(hsize, vsize);
            if (hsize != 0 && vsize != 0) {
                this.offImage = this.createImage(hsize, vsize);
            }
            else {
                System.out.println("Prepare Double Buff: Ivalid Size Can create Image");
            }
            if (this.offImage != null) {
                this.offGraphics = this.offImage.getGraphics();
            }
            else {
                System.out.println("Prepare Double Buff: Off Image null, can't get Graphics");
            }
        }
        if (this.offGraphics != null) {
            this.offGraphics.clearRect(0, 0, hsize, vsize);
            this.offGraphics.setFont(g.getFont());
            Common.debug("NEW F SIZE " + g.getFont().getSize(), 5);
        }
        else {
            System.out.println("Prepare Double Buff: Off Graphics null, can't clear or set font");
        }
    }
    
    public void update(final Graphics g) {
        final Dimension d = this.getSize();
        if (g != null) {
            this.prepareDoubleBuffering(d.width, d.height, g);
        }
        else {
            System.out.println("Update: g null, can't prepare!");
        }
        if (this.offGraphics != null) {
            this.display(this.offGraphics);
        }
        else {
            System.out.println("Update: off Graphics  null, can't display");
        }
        if (this.offImage != null) {
            g.drawImage(this.offImage, 0, 0, this);
        }
        else {
            System.out.println("Update: off Image  null, can't draw");
        }
    }
}
