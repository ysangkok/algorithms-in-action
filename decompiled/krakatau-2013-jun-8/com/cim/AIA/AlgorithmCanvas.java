package com.cim.AIA;

abstract public class AlgorithmCanvas extends com.cim.common.BasicCanvas implements com.cim.AIA.RepaintListener, com.cim.AIA.Paintable, com.cim.AIA.FinishListener, com.cim.AIA.Questionable, java.awt.event.MouseListener, com.cim.AIA.ColorSelectionListener, com.cim.AIA.FontSelectionListener {
    protected java.util.Vector drawables;
    protected java.util.Vector selectionListeners;
    protected java.util.Vector selectables;
    protected boolean doingAnim;
    protected com.cim.AIA.MultipleTween tweens;
    protected int numberOfTweenSteps;
    protected java.awt.Container parent;
    
    public AlgorithmCanvas()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.drawables = a;
        java.util.Vector a0 = new java.util.Vector();
        this.selectionListeners = a0;
        java.util.Vector a1 = new java.util.Vector();
        this.selectables = a1;
        this.doingAnim = false;
        this.numberOfTweenSteps = 20;
        com.cim.AIA.MultipleTween a2 = new com.cim.AIA.MultipleTween((com.cim.AIA.Paintable)this);
        this.tweens = a2;
        java.awt.Color a3 = java.awt.Color.white;
        this.setBackground(a3);
        this.addMouseListener((java.awt.event.MouseListener)this);
        com.cim.AIA.ConfigurationManager a4 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        a4.addColorSelectionListener((com.cim.AIA.ColorSelectionListener)this);
        com.cim.AIA.ConfigurationManager a5 = com.cim.AIA.ConfigurationManager.getConfigurationManager();
        a5.addFontSelectionListener((com.cim.AIA.FontSelectionListener)this);
    }
    
    public void addDrawable(com.cim.AIA.Drawable a)
    {
        java.util.Vector a0 = this.drawables;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addSelectable(com.cim.AIA.Selectable a)
    {
        java.util.Vector a0 = this.selectables;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addSelectionListener(com.cim.AIA.SelectionListener a)
    {
        java.util.Vector a0 = this.selectionListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addTween(com.cim.AIA.Tween a)
    {
        com.cim.AIA.MultipleTween a0 = this.tweens;
        a0.add(a);
    }
    
    public void cleanUp()
    {
        java.util.Vector a = this.drawables;
        if(a != null)
        {
            java.util.Vector a0 = this.drawables;
            a0.removeAllElements();
        }
        this.drawables = null;
        java.util.Vector a1 = this.selectionListeners;
        if(a1 != null)
        {
            java.util.Vector a2 = this.selectionListeners;
            a2.removeAllElements();
        }
        this.selectionListeners = null;
        java.util.Vector a3 = this.selectables;
        if(a3 != null)
        {
            java.util.Vector a4 = this.selectables;
            a4.removeAllElements();
        }
        this.selectables = null;
        com.cim.AIA.MultipleTween a5 = this.tweens;
        if(a5 != null)
        {
            com.cim.AIA.MultipleTween a6 = this.tweens;
            a6.removeAll();
        }
        this.tweens = null;
        this.parent = null;
    }
    
    public void display(java.awt.Graphics a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.drawables;
            int i0 = a0.size();
            if(i >= i0)
            {
                this.displayAlgorithm(a);
                return;
            }
            else
            {
                java.util.Vector a1 = this.drawables;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.Drawable dummy = (com.cim.AIA.Drawable)a2;
                ((com.cim.AIA.Drawable)a2).draw(a);
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    abstract public void displayAlgorithm(java.awt.Graphics arg);
    
    
    public String getClassName()
    {
        return "AlgorithmCanvas";
    }
    
    protected java.awt.Dimension getParentSize()
    {
        java.awt.Dimension a = null;
        java.awt.Container a0 = this.parent;
        if(a0 == null)
        {
            java.io.PrintStream a1 = System.out;
            a1.println("com.cim.AIA.AlgorithmCanvas Parent is null");
            java.awt.Dimension a2 = this.getSize();
            a = a2;
        }
        else
        {
            java.awt.Container a3 = this.parent;
            java.awt.Dimension a4 = a3.getSize();
            a = a4;
        }
        return a;
    }
    
    abstract protected void handleColorSelection(com.cim.AIA.ColorSelection arg);
    
    
    abstract protected void handleFontSelection(com.cim.AIA.FontSelection arg);
    
    
    public void mouseClicked(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseEntered(java.awt.event.MouseEvent a)
    {
    }
    
    public void mouseExited(java.awt.event.MouseEvent a)
    {
    }
    
    public void mousePressed(java.awt.event.MouseEvent a)
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.selectables;
            int i0 = a0.size();
            if(i >= i0)
            {
                return;
            }
            java.util.Vector a1 = this.selectables;
            Object a2 = a1.elementAt(i);
            com.cim.AIA.Selectable dummy = (com.cim.AIA.Selectable)a2;
            java.awt.Point a3 = a.getPoint();
            Object a4 = ((com.cim.AIA.Selectable)a2).getItemAt(a3);
            if(a4 != null)
            {
                this.repaint();
                this.tellSelectionListeners((com.cim.AIA.Selectable)a4);
            }
            int i1 = i + 1;
            i = i1;
        }
    }
    
    public void mouseReleased(java.awt.event.MouseEvent a)
    {
    }
    
    public void paint()
    {
        this.repaint();
    }
    
    public void processColorSelection(com.cim.AIA.ColorSelection a)
    {
        String s = a.getAtributeName();
        String s0 = com.cim.AIA.ColorSelection.BACKGROUND;
        int i = s.equalsIgnoreCase(s0)?1:0;
        if(i != 0)
        {
            java.awt.Color a0 = a.getColor();
            this.setBackground(a0);
        }
        this.handleColorSelection(a);
    }
    
    public void processFinishEvent(com.cim.AIA.FinishEvent a)
    {
        java.util.Vector a0 = this.drawables;
        a0.removeAllElements();
        this.repaint();
    }
    
    public void processFontSelection(com.cim.AIA.FontSelection a)
    {
        this.handleFontSelection(a);
    }
    
    abstract public void processRepaintEvent(com.cim.AIA.RepaintEvent arg);
    
    
    public void removeAllHighlight()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.selectables;
            int i0 = a.size();
            if(i >= i0)
            {
                this.repaint();
                return;
            }
            else
            {
                java.util.Vector a0 = this.selectables;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.Selectable dummy = (com.cim.AIA.Selectable)a1;
                ((com.cim.AIA.Selectable)a1).unHighlight();
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    protected void removeAllSelectables()
    {
        java.util.Vector a = this.selectables;
        a.removeAllElements();
    }
    
    public void removeDrawable(com.cim.AIA.Drawable a)
    {
        java.util.Vector a0 = this.drawables;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void removeSelectable(com.cim.AIA.Selectable a)
    {
        java.util.Vector a0 = this.selectables;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void removeSelectionListener(com.cim.AIA.SelectionListener a)
    {
        java.util.Vector a0 = this.selectionListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void setParent(java.awt.Container a)
    {
        this.parent = a;
        java.awt.Dimension a0 = a.getSize();
        int i = a0.width;
        java.awt.Dimension a1 = a.getSize();
        int i0 = a1.height;
        this.setSize(i, i0);
    }
    
    public void setSize(int i, int i0)
    {
        java.awt.Container a = this.parent;
        if(a == null)
        {
            ((com.cim.common.BasicCanvas)this).setSize(i, i0);
        }
        else
        {
            java.awt.Container a0 = this.parent;
            java.awt.Dimension a1 = a0.getSize();
            int i1 = a1.width;
            int i2 = Math.max(i1, i);
            java.awt.Container a2 = this.parent;
            java.awt.Dimension a3 = a2.getSize();
            int i3 = a3.height;
            int i4 = Math.max(i3, i0);
            ((com.cim.common.BasicCanvas)this).setSize(i2, i4);
        }
    }
    
    public void setTweenSpeed(int i)
    {
        int i0 = this.numberOfTweenSteps;
        if(i >= i0)
        {
            com.cim.AIA.MultipleTween a = this.tweens;
            int i1 = this.numberOfTweenSteps;
            int i2 = i / i1;
            a.setSleep(i2);
        }
        else
        {
            com.cim.AIA.MultipleTween a0 = this.tweens;
            a0.setSleep(1);
        }
    }
    
    protected void tellSelectionListeners(com.cim.AIA.Selectable a)
    {
        Object a0 = a;
        com.cim.AIA.SelectionEvent a1 = new com.cim.AIA.SelectionEvent((Object)this, a0);
        java.util.Vector a2 = this.selectionListeners;
        int i = a2.size();
        if(i >= 1)
        {
            ((com.cim.AIA.Selectable)a0).highlight();
        }
        int i0 = 0;
        while(true)
        {
            java.util.Vector a3 = this.selectionListeners;
            int i1 = a3.size();
            if(i0 >= i1)
            {
                return;
            }
            else
            {
                java.util.Vector a4 = this.selectionListeners;
                Object a5 = a4.elementAt(i0);
                com.cim.AIA.SelectionListener dummy = (com.cim.AIA.SelectionListener)a5;
                ((com.cim.AIA.SelectionListener)a5).processSelectionEvent(a1);
                int i2 = i0 + 1;
                i0 = i2;
            }
        }
    }
}