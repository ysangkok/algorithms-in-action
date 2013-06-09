package com.cim.AIA;

import java.awt.*;

public class WTFactory extends Thread
{
    protected static final int FIXED_FONT_HEIGHT = 10;
    protected static final int FIXED_FONT_WIDTH = 30;
    protected static final int FIXED_FONT_ASCENT = 3;
    protected int leftBound;
    
    public WTFactory() {
        super();
        this.leftBound = 0;
    }
    
    protected void attachParent(final HierarchyTree t, final int w) {
        final int y = t.border + t.parentDistance;
        final int x2 = (w - t.getWidth()) / 2 - t.border;
        final int x1 = x2 + t.getWidth() + 2 * t.border - w;
        ((HierarchyTree)t.getChild(0)).offset.y = y + t.height;
        ((HierarchyTree)t.getChild(0)).offset.x = x1;
        t.contour.upper_head = new PolyLine(0, t.height, new PolyLine(x1, y, t.contour.upper_head));
        t.contour.lower_head = new PolyLine(0, t.height, new PolyLine(x2, y, t.contour.lower_head));
    }
    
    protected PolyLine bridge(final PolyLine line1, final int x1, final int y1, final PolyLine line2, final int x2, final int y2) {
        final int dy = y2 + line2.dy - y1;
        int dx;
        if (line2.dy == 0) {
            dx = line2.dx;
        }
        else {
            final int s = dy * line2.dx;
            dx = s / line2.dy;
        }
        final PolyLine r = new PolyLine(dx, dy, line2.link);
        line1.link = new PolyLine(x2 + line2.dx - dx - x1, 0, r);
        return r;
    }
    
    protected int join(final HierarchyTree t) {
        HierarchyTree c = (HierarchyTree)t.getChild(0);
        t.contour = c.contour;
        int sum;
        int w = sum = c.getWidth() + 2 * c.border;
        for (int i = 1; i < t.children.size(); ++i) {
            c = (HierarchyTree)t.getChild(i);
            final int d = this.merge(t.contour, c.contour);
            c.offset.x = d + w;
            c.offset.y = 0;
            w = c.getWidth() + 2 * c.border;
            sum = sum + (d + w);
        }
        return sum;
    }
    
    public void layout(final HierarchyTree t) {
        if (t == null) {
            return;
        }
        if (t.children.size() > 0) {
            for (int i = 0; i < t.children.size(); ++i) {
                this.layout((HierarchyTree)t.getChild(i));
            }
            this.attachParent(t, this.join(t));
        }
        else {
            this.layoutLeaf(t);
        }
    }
    
    protected void layoutLeaf(final HierarchyTree t) {
        t.contour.upper_tail = new PolyLine(t.getWidth() + 2 * t.border, 0, null);
        t.contour.upper_head = t.contour.upper_tail;
        t.contour.lower_tail = new PolyLine(0, -t.height - 2 * t.border, null);
        t.contour.lower_head = new PolyLine(t.getWidth() + 2 * t.border, 0, t.contour.lower_tail);
    }
    
    protected int merge(final Polygon c1, final Polygon c2) {
        int total;
        int x;
        int y = x = (total = 0);
        PolyLine upper = c1.lower_head;
        PolyLine lower = c2.upper_head;
        while (lower != null && upper != null) {
            final int d = this.offset(x, y, lower.dx, lower.dy, upper.dx, upper.dy);
            x = x + d;
            total = total + d;
            if (y + lower.dy <= upper.dy) {
                x = x + lower.dx;
                y = y + lower.dy;
                lower = lower.link;
            }
            else {
                x = x - upper.dx;
                y = y - upper.dy;
                upper = upper.link;
            }
        }
        if (lower != null) {
            final PolyLine b = this.bridge(c1.upper_tail, 0, 0, lower, x, y);
            c1.upper_tail = ((b.link == null) ? b : c2.upper_tail);
            c1.lower_tail = c2.lower_tail;
        }
        else {
            final PolyLine b = this.bridge(c2.lower_tail, x, y, upper, 0, 0);
            if (b.link == null) {
                c1.lower_tail = b;
            }
        }
        c1.lower_head = c2.lower_head;
        return total;
    }
    
    protected int offset(final int p1, final int p2, final int a1, final int a2, final int b1, final int b2) {
        if (b2 <= p2 || p2 + a2 <= 0) {
            return 0;
        }
        int d;
        if (b2 * a1 - a2 * b1 > 0) {
            if (p2 < 0) {
                final int s = p2 * a1;
                d = s / a2 - p1;
            }
            else if (p2 > 0) {
                final int s = p2 * b1;
                d = s / b2 - p1;
            }
            else {
                d = -p1;
            }
        }
        else if (b2 < p2 + a2) {
            final int s = (b2 - p2) * a1;
            d = b1 - (p1 + s / a2);
        }
        else if (b2 > p2 + a2) {
            final int s = (a2 + p2) * b1;
            d = s / b2 - (p1 + a1);
        }
        else {
            d = b1 - (p1 + a1);
        }
        if (d > 0) {
            return d;
        }
        return 0;
    }
    
    public void paintTree(final Graphics g, final HierarchyTree t) {
        if (t == null) {
            return;
        }
        t.drawPartial(g);
        for (int i = 0; i < t.children.size(); ++i) {
            final HierarchyTree c = (HierarchyTree)t.getChild(i);
            this.paintTree(g, c);
            final Color oldColor = g.getColor();
            g.setColor(c.getParentLineColor());
            if (c.nodesAreVisible() && c.getDrawParentLine()) {
                c.getLine().draw(g);
            }
        }
    }
    
    public Point plantTree(final HierarchyTree t, final int off_x, final int off_y) {
        this.leftBound = 2147483647;
        return this.plantTree(t, off_x, off_y, new Point(0, 0));
    }
    
    protected Point plantTree(final HierarchyTree t, final int off_x, final int off_y, final Point point) {
        if (t.pos.x > 0 && t.pos.y > 0) {
            t.prevPos = new Point(t.pos);
        }
        else {
            t.prevPos.x = off_x + t.offset.x;
            t.prevPos.y = off_y + t.offset.y;
        }
        t.pos.x = off_x + t.offset.x;
        t.pos.y = off_y + t.offset.y;
        t.calculateLine();
        if (t.pos.x < this.leftBound) {
            this.leftBound = t.pos.x;
        }
        if (t.pos.x + t.getWidth() > point.x) {
            point.x = t.pos.x + t.getWidth();
        }
        if (t.pos.y > point.y) {
            point.y = t.pos.y;
        }
        final HierarchyTree c = (HierarchyTree)t.getChild(0);
        if (c != null) {
            this.plantTree(c, t.pos.x, t.pos.y, point);
            int cur_x = t.pos.x + c.offset.x;
            for (int i = 1; i < t.children.size(); ++i) {
                final HierarchyTree s = (HierarchyTree)t.getChild(i);
                this.plantTree(s, cur_x, t.pos.y + c.offset.y, point);
                cur_x = cur_x + s.offset.x;
            }
        }
        return point;
    }
}
