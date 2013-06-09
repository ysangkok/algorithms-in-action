package com.cim.AIA;

public class DataItem extends com.cim.AIA.Element {
    private int key;
    private String label;
    private int height;
    private int width;
    java.awt.Point position;
    boolean highlight;
    
    public DataItem(String s, int i)
    {
        super(2, (Object)s);
        this.highlight = false;
        this.key = i;
    }
    
    public boolean equals(com.cim.AIA.Selectable a)
    {
        int i = 0;
        label1: {
            Object a0 = null;
            label0: {
                int i0 = a instanceof com.cim.AIA.DataItem?1:0;
                a0 = a;
                if(i0 != 0)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            String s = this.label;
            com.cim.AIA.DataItem dummy = (com.cim.AIA.DataItem)a0;
            com.cim.AIA.DataItem a1 = (com.cim.AIA.DataItem)a0;
            String s0 = a1.label;
            int i1 = s.compareTo(s0);
            i = i1 != 0?0:1;
        }
        return i != 0;
    }
    
    public void highlight()
    {
        this.highlight = true;
    }
    
    public void unHighlight()
    {
        this.highlight = false;
    }
    
    public void setPosition(java.awt.Point a)
    {
        this.position = a;
    }
    
    public java.awt.Point getPosition()
    {
        java.awt.Point a = this.position;
        return a;
    }
    
    public int getHeight()
    {
        int i = this.height;
        return i;
    }
    
    public void setHeight(int i)
    {
        this.height = i;
    }
    
    public int getWidth()
    {
        int i = this.width;
        return i;
    }
    
    public void setWidth(int i)
    {
        this.width = i;
    }
    
    public int getKey()
    {
        int i = this.key;
        return i;
    }
    
    public com.cim.AIA.Node getNode()
    {
        com.cim.AIA.Node a = new com.cim.AIA.Node((Object)"", 0);
        return a;
    }
    
    public void setX(int i)
    {
        java.awt.Point a = this.position;
        a.x = i;
    }
    
    public void setY(int i)
    {
        java.awt.Point a = this.position;
        a.y = i;
    }
    
    public int getX()
    {
        java.awt.Point a = this.position;
        int i = a.x;
        return i;
    }
    
    public int getY()
    {
        java.awt.Point a = this.position;
        int i = a.y;
        return i;
    }
    
    public void shiftEntire(int i, int i0)
    {
        java.awt.Point a = this.position;
        int i1 = a.x;
        int i2 = i1 + i;
        a.x = i2;
        java.awt.Point a0 = this.position;
        int i3 = a0.y;
        int i4 = i3 + i0;
        a0.y = i4;
    }
    
    public com.cim.AIA.Selectable getItemAt(java.awt.Point a)
    {
        java.awt.Point a0 = this.position;
        int i = a0.x;
        java.awt.Point a1 = this.position;
        int i0 = a1.y;
        int i1 = this.getWidth();
        int i2 = this.getHeight();
        java.awt.Rectangle a2 = new java.awt.Rectangle(i, i0, i1, i2);
        int i3 = a2.contains(a)?1:0;
        com.cim.AIA.DataItem a3 = i3 == 0?null:this;
        return (com.cim.AIA.Selectable)a3;
    }
    
    public void draw(java.awt.Graphics a, java.awt.Point a0)
    {
    }
    
    public void draw(java.awt.Graphics a)
    {
    }
}