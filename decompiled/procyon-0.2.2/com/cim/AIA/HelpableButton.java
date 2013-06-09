package com.cim.AIA;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class HelpableButton extends Button implements Helpable
{
    private static final long serialVersionUID = -1701437300541814144L;
    protected Vector<HelpListener> helpListeners;
    protected String title;
    protected String instructions;
    
    public HelpableButton(final String name, final String helpInstructions) {
        super(name);
        this.helpListeners = new Vector();
        this.title = "";
        this.instructions = "";
        this.title = name;
        this.instructions = helpInstructions;
        this.addMouseListener(new MouseListener() {
            public void mouseClicked(final MouseEvent e) {
            }
            
            public void mouseEntered(final MouseEvent e) {
                HelpableButton.this.help(new HelpEvent(this, HelpableButton.this.title, HelpableButton.this.instructions));
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
    
    protected void help(final HelpEvent e) {
        for (int i = 0; i < this.helpListeners.size(); ++i) {
            ((HelpListener)this.helpListeners.elementAt(i)).processHelpEvent(e);
        }
    }
    
    public void removeAllHelpListeners() {
        this.helpListeners.removeAllElements();
    }
    
    public void removeHelpListener(final HelpListener helpListener) {
        this.helpListeners.removeElement(helpListener);
    }
}
