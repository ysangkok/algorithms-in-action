package com.cim.AIA;

import java.awt.*;

public class ProgressBar extends Canvas
{
    private static final long serialVersionUID = 3328266732264135126L;
    protected static final int HEIGHT = 20;
    protected static final int GAP = 3;
    protected int max;
    protected int step;
    protected int mark;
    protected Image offscreen;
    
    public ProgressBar(final int max, final int step) {
        super();
        this.setBackground(Color.white);
        this.max = max;
        this.step = step;
    }
    
    public void draw(final Graphics g) {
        final int w = this.getSize().width;
        final int h = this.getSize().height;
        final int blocks = this.max / this.step;
        final int gaps = blocks - 1;
        final int block_w = (w - gaps * 3) / blocks;
        g.setColor(Color.black);
        int x = (w - blocks * block_w - gaps * 3) / 2;
        x = ((x < 0) ? 0 : x);
        for (int i = 0; i < this.max; ++i) {
            if (i < this.mark) {
                g.setColor(new Color(10, 87, 159));
                g.fill3DRect(x, 0, block_w, h, true);
            }
            else {
                g.setColor(Color.black);
                g.fillRect(x, 0, block_w, h);
            }
            x = x + (block_w + 3);
        }
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(20, 20);
    }
    
    public void paint(final Graphics g) {
        if (this.offscreen != null) {
            g.drawImage(this.offscreen, 0, 0, this);
        }
        else {
            this.draw(g);
        }
    }
    
    public void setProgressMark(final int mark) {
        this.mark = mark;
    }
    
    public void update(final Graphics g) {
        this.offscreen = this.createImage(this.getSize().width, this.getSize().height);
        final Graphics og = this.offscreen.getGraphics();
        this.draw(og);
        this.paint(g);
    }
}
