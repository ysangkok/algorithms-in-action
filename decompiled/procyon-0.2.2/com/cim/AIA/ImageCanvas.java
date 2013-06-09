package com.cim.AIA;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

class ImageCanvas extends Canvas implements Helpable
{
    private static final long serialVersionUID = 6090991999894247359L;
    protected Vector<E> helpListeners;
    protected String instructions;
    private Dimension imageSize;
    private Image image;
    
    public ImageCanvas(final Image logoImage, final Dimension logoImageSize) {
        super();
        this.helpListeners = new Vector();
        this.instructions = "Developed by L.Stern, L.Naish and H.Sondergaard at The University of Melbourne. (C) 2001";
        this.image = logoImage;
        if (this.image != null) {
            this.imageSize = new Dimension(this.image.getWidth(this), this.image.getHeight(this));
        }
        else {
            this.imageSize = new Dimension(50, 50);
        }
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent e) {
            }
            
            public void mouseEntered(final MouseEvent e) {
                ImageCanvas.this.help(new HelpEvent(this, "Algorithms in Action", ImageCanvas.this.instructions));
            }
            
            public void mouseExited(final MouseEvent e) {
            }
            
            public void mousePressed(final MouseEvent e) {
            }
            
            public void mouseReleased(final MouseEvent e) {
            }
        });
    }
    
    public void addHelpListener(final HelpListener helpListener) {
        this.helpListeners.addElement(helpListener);
    }
    
    public void cleanUp() {
        if (this.helpListeners != null) {
            this.helpListeners.removeAllElements();
        }
        this.helpListeners = null;
        if (this.image != null) {
            this.image.flush();
        }
        this.image = null;
    }
    
    public Dimension getSize() {
        return this.imageSize;
    }
    
    protected void help(final HelpEvent e) {
        for (int i = 0; i < this.helpListeners.size(); ++i) {
            ((HelpListener)((HelpListener)this.helpListeners.elementAt(i))).processHelpEvent(e);
        }
    }
    
    public void paint(final Graphics g) {
        if (this.image != null) {
            g.drawImage(this.image, 0, 0, this);
        }
    }
    
    public void removeHelpListener(final HelpListener helpListener) {
        this.helpListeners.removeElement(helpListener);
    }
}
