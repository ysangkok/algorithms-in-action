package com.cim.AIA;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import com.cim.common.*;

public abstract class AlgorithmCanvas extends BasicCanvas implements RepaintListener, Paintable, FinishListener, Questionable, MouseListener, ColorSelectionListener, FontSelectionListener
{
    protected Vector<Drawable> drawables;
    protected Vector<SelectionListener> selectionListeners;
    protected Vector<Selectable> selectables;
    protected boolean doingAnim;
    protected MultipleTween tweens;
    protected int numberOfTweenSteps;
    protected Container parent;
    
    public AlgorithmCanvas() {
        super();
        this.drawables = new Vector();
        this.selectionListeners = new Vector();
        this.selectables = new Vector();
        this.doingAnim = false;
        this.numberOfTweenSteps = 20;
        this.tweens = new MultipleTween(this);
        this.setBackground(Color.white);
        this.addMouseListener(this);
        ConfigurationManager.getConfigurationManager().addColorSelectionListener(this);
        ConfigurationManager.getConfigurationManager().addFontSelectionListener(this);
    }
    
    public void addDrawable(final Drawable drawable) {
        this.drawables.addElement(drawable);
    }
    
    public void addSelectable(final Selectable selectable) {
        this.selectables.addElement(selectable);
    }
    
    public void addSelectionListener(final SelectionListener selectionListener) {
        this.selectionListeners.addElement(selectionListener);
    }
    
    public void addTween(final Tween tween) {
        this.tweens.add(tween);
    }
    
    public void cleanUp() {
        if (this.drawables != null) {
            this.drawables.removeAllElements();
        }
        this.drawables = null;
        if (this.selectionListeners != null) {
            this.selectionListeners.removeAllElements();
        }
        this.selectionListeners = null;
        if (this.selectables != null) {
            this.selectables.removeAllElements();
        }
        this.selectables = null;
        if (this.tweens != null) {
            this.tweens.removeAll();
        }
        this.tweens = null;
        this.parent = null;
    }
    
    public void display(final Graphics g) {
        for (int i = 0; i < this.drawables.size(); ++i) {
            ((Drawable)this.drawables.elementAt(i)).draw(g);
        }
        this.displayAlgorithm(g);
    }
    
    public abstract void displayAlgorithm(final Graphics p0);
    
    public String getClassName() {
        return "AlgorithmCanvas";
    }
    
    protected Dimension getParentSize() {
        if (this.parent != null) {
            return this.parent.getSize();
        }
        System.out.println("com.cim.AIA.AlgorithmCanvas Parent is null");
        return this.getSize();
    }
    
    protected abstract void handleColorSelection(final ColorSelection p0);
    
    protected abstract void handleFontSelection(final FontSelection p0);
    
    public void mouseClicked(final MouseEvent e) {
    }
    
    public void mouseEntered(final MouseEvent e) {
    }
    
    public void mouseExited(final MouseEvent e) {
    }
    
    public void mousePressed(final MouseEvent e) {
        for (int i = 0; i < this.selectables.size(); ++i) {
            final Selectable selected = ((Selectable)this.selectables.elementAt(i)).getItemAt(e.getPoint());
            if (selected != null) {
                this.repaint();
                this.tellSelectionListeners(selected);
            }
        }
    }
    
    public void mouseReleased(final MouseEvent e) {
    }
    
    public void paint() {
        this.repaint();
    }
    
    public void processColorSelection(final ColorSelection colorSelection) {
        final String atribute = colorSelection.getAtributeName();
        if (atribute.equalsIgnoreCase(ColorSelection.BACKGROUND)) {
            this.setBackground(colorSelection.getColor());
        }
        this.handleColorSelection(colorSelection);
    }
    
    public void processFinishEvent(final FinishEvent e) {
        this.drawables.removeAllElements();
        this.repaint();
    }
    
    public void processFontSelection(final FontSelection fontSelection) {
        this.handleFontSelection(fontSelection);
    }
    
    public abstract void processRepaintEvent(final RepaintEvent p0);
    
    public void removeAllHighlight() {
        for (int i = 0; i < this.selectables.size(); ++i) {
            ((Selectable)this.selectables.elementAt(i)).unHighlight();
        }
        this.repaint();
    }
    
    protected void removeAllSelectables() {
        this.selectables.removeAllElements();
    }
    
    public void removeDrawable(final Drawable drawable) {
        this.drawables.removeElement(drawable);
    }
    
    public void removeSelectable(final Selectable selectable) {
        this.selectables.removeElement(selectable);
    }
    
    public void removeSelectionListener(final SelectionListener selectionListener) {
        this.selectionListeners.removeElement(selectionListener);
    }
    
    public void setParent(final Container parent) {
        this.parent = parent;
        this.setSize(parent.getSize().width, parent.getSize().height);
    }
    
    public void setSize(final int width, final int height) {
        if (this.parent != null) {
            super.setSize(Math.max(this.parent.getSize().width, width), Math.max(this.parent.getSize().height, height));
        }
        else {
            super.setSize(width, height);
        }
    }
    
    public void setTweenSpeed(final int sleepDuration) {
        if (sleepDuration < this.numberOfTweenSteps) {
            this.tweens.setSleep(1);
        }
        else {
            this.tweens.setSleep(sleepDuration / this.numberOfTweenSteps);
        }
    }
    
    protected void tellSelectionListeners(final Selectable selected) {
        final SelectionEvent selectionEvent = new SelectionEvent(this, selected);
        if (this.selectionListeners.size() >= 1) {
            selected.highlight();
        }
        for (int i = 0; i < this.selectionListeners.size(); ++i) {
            ((SelectionListener)this.selectionListeners.elementAt(i)).processSelectionEvent(selectionEvent);
        }
    }
}
