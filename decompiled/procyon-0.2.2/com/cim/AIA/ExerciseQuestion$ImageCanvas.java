package com.cim.AIA;

import java.awt.*;

class ImageCanvas extends Canvas
{
    private static final long serialVersionUID = -4017393637683774381L;
    protected Image image;
    protected boolean gotImage;
    
    public ImageCanvas() {
        super();
        this.gotImage = false;
    }
    
    public void paint(final Graphics g) {
        if (this.image != null && this.gotImage) {
            g.drawImage(this.image, 0, 0, this.getSize().width, this.getSize().height, null);
        }
    }
    
    public void repaint() {
        this.paint(this.getGraphics());
    }
    
    public void setImage(final Image image) {
        this.image = image;
        this.gotImage = true;
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
