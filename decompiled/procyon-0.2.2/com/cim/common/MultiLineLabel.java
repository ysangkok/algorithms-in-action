package com.cim.common;

import java.util.*;
import java.applet.*;
import java.awt.*;

public class MultiLineLabel extends BasicCanvas
{
    private static final long serialVersionUID = -1696824653160308291L;
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    protected String[] lines;
    protected int num_lines;
    protected int margin_width;
    protected int margin_height;
    protected int line_height;
    protected int line_ascent;
    protected int[] line_widths;
    protected int max_width;
    protected int alignment;
    protected Container label_parent;
    Color background_color;
    
    public MultiLineLabel(final String label) {
        this(label, 10, 10, 0);
    }
    
    public MultiLineLabel(final String label, final Applet parent) {
        this(label, 10, 10, 0);
        this.label_parent = parent;
    }
    
    public MultiLineLabel(final String label, final Frame parent) {
        this(label, 10, 10, 0);
        this.label_parent = parent;
    }
    
    public MultiLineLabel(final String label, final int alignment) {
        this(label, 10, 10, alignment);
    }
    
    public MultiLineLabel(final String label, final int margin_width, final int margin_height) {
        this(label, margin_width, margin_height, 0);
    }
    
    public MultiLineLabel(final String label, final int margin_width, final int margin_height, final int alignment) {
        super();
        this.alignment = 0;
        this.setForeground(Color.black);
        this.newLabel(label);
        this.margin_width = margin_width;
        this.margin_height = margin_height;
        this.alignment = alignment;
    }
    
    public void addNotify() {
        super.addNotify();
        this.measure();
    }
    
    public void display(final Graphics g) {
        final Color fg = this.getForeground();
        final Dimension s = this.getSize();
        g.setColor(this.getBackground());
        g.fillRect(0, 0, s.width, s.height);
        g.setColor(fg);
        final Dimension d = this.getSize();
        for (int y = this.line_ascent + (d.height - this.num_lines * this.line_height) / 2, i = 0; i < this.num_lines; ++i, y = y + this.line_height) {
            int x = 0;
            switch (this.alignment) {
                case 0: {
                    x = 0;
                    break;
                }
                case 2: {
                    x = d.width - this.margin_width - this.line_widths[i];
                    break;
                }
                default: {
                    x = (d.width - this.line_widths[i]) / 2;
                    break;
                }
            }
            g.drawString(this.lines[i], x, y);
        }
    }
    
    protected String get_label() {
        return this.lines[0];
    }
    
    public int getAlignment() {
        return this.alignment;
    }
    
    protected String[] getLabel() {
        return this.lines;
    }
    
    public int getMarginHeight() {
        return this.margin_height;
    }
    
    public int getMarginWidth() {
        return this.margin_width;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(this.max_width, this.num_lines * this.line_height);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(this.max_width + 2 * this.margin_width, this.num_lines * this.line_height + 2 * this.margin_height);
    }
    
    protected void measure() {
        final FontMetrics fm = this.getFontMetrics(this.getFont());
        if (fm == null) {
            return;
        }
        this.line_height = fm.getHeight();
        this.line_ascent = fm.getAscent();
        this.max_width = 0;
        for (int i = 0; i < this.num_lines; ++i) {
            this.line_widths[i] = fm.stringWidth(this.lines[i]);
            if (this.line_widths[i] > this.max_width) {
                this.max_width = this.line_widths[i];
            }
        }
    }
    
    public void newLabel(final String label) {
        final StringTokenizer t = new StringTokenizer(label, "\n");
        this.num_lines = t.countTokens();
        this.lines = new String[this.num_lines];
        this.line_widths = new int[this.num_lines];
        for (int i = 0; i < this.num_lines; ++i) {
            this.lines[i] = t.nextToken();
        }
    }
    
    public void repaint() {
        if (this.background_color != null) {
            this.setBackground(this.background_color);
        }
    }
    
    public void setAlignment(final int a) {
        this.alignment = a;
        this.repaint();
    }
    
    public void setFont(final Font f) {
        super.setFont(f);
        this.measure();
        this.repaint();
    }
    
    public void setForeground(final Color c) {
        super.setForeground(c);
        this.repaint();
    }
    
    public void setLabel(final String label) {
        this.newLabel(label);
        this.measure();
        this.repaint();
    }
    
    public void setMarginHeight(final int mh) {
        this.margin_height = mh;
        this.repaint();
    }
    
    public void setMarginWidth(final int mw) {
        this.margin_width = mw;
        this.repaint();
    }
    
    public void setText(final String l) {
        this.setLabel(l);
    }
}
