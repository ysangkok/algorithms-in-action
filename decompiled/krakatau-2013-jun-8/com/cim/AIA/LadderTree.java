package com.cim.AIA;

public class LadderTree extends com.cim.AIA.Tree {
    final private static long serialVersionUID = -9204623185530382869L;
    final public static String VISIBLE = "visible";
    final public static String HIDDEN = "hidden";
    protected static com.cim.AIA.LadderTreeImplementor lImplementor;
    final protected static int EXPECTED_NUMBER_OF_LOC = 40;
    protected java.awt.Point pos;
    protected boolean expanded;
    protected boolean expandAsOwn;
    protected boolean alwaysExpanded;
    protected String currentPosition;
    protected boolean visible;
    protected java.util.Hashtable ladderTreeHashtable;
    protected com.cim.AIA.Logger logger;
    protected com.cim.AIA.BreakPoint breakpoint;
    
    public LadderTree(com.cim.AIA.LadderTree a, com.cim.AIA.AlgorithmLine a0, com.cim.AIA.Logger a1, com.cim.AIA.BreakPoint a2)
    {
        super((com.cim.AIA.Tree)a, (Object)a0);
        java.awt.Point a3 = new java.awt.Point();
        this.pos = a3;
        this.expanded = false;
        this.expandAsOwn = false;
        this.alwaysExpanded = false;
        this.currentPosition = "";
        this.visible = false;
        this.ladderTreeHashtable = null;
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                java.util.Hashtable a4 = new java.util.Hashtable(40);
                this.ladderTreeHashtable = a4;
                break label1;
            }
            java.util.Vector a5 = a.children;
            a5.addElement((Object)this);
            com.cim.AIA.LadderTree a6 = this;
            while(true)
            {
                com.cim.AIA.Tree a7 = a6.getParent();
                if(a7 == null)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.Tree a8 = a6.getParent();
                    com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a8;
                    com.cim.AIA.LadderTree a9 = (com.cim.AIA.LadderTree)a8;
                    a6 = a9;
                }
            }
            java.util.Hashtable a10 = a6.ladderTreeHashtable;
            this.ladderTreeHashtable = a10;
        }
        java.util.Hashtable a11 = this.ladderTreeHashtable;
        String s = a0.getKey();
        Object a12 = a11.put((Object)s, (Object)this);
        this.logger = a1;
        this.breakpoint = a2;
    }
    
    public boolean containsKey(String s)
    {
        com.cim.AIA.LadderTree a = this.getLadderTree(s);
        int i = a == null?0:1;
        return i != 0;
    }
    
    public int countHiddenLines()
    {
        int i = 0;
        int i0 = this.isExpanded()?1:0;
        label0: {
            if(i0 != 0)
            {
                i = 1;
                break label0;
            }
            int i1 = 1;
            int i2 = 0;
            while(true)
            {
                java.util.Vector a = this.children;
                int i3 = a.size();
                if(i2 >= i3)
                {
                    i = i1;
                    break;
                }
                else
                {
                    com.cim.AIA.Tree a0 = this.getChild(i2);
                    int i4 = a0.countLeaves();
                    int i5 = i1 + i4;
                    int i6 = i2 + 1;
                    i1 = i5;
                    i2 = i6;
                }
            }
        }
        int i7 = com.cim.AIA.Logger.DEBUG?1:0;
        if(i7 != 0)
        {
            java.io.PrintStream a1 = System.err;
            StringBuilder a2 = new StringBuilder();
            StringBuilder a3 = a2.append("isExpanded(): ");
            int i8 = this.isExpanded()?1:0;
            StringBuilder a4 = a3.append(i8 != 0);
            String s = a4.toString();
            a1.println(s);
            java.io.PrintStream a5 = System.err;
            StringBuilder a6 = new StringBuilder();
            StringBuilder a7 = a6.append("expanded: ");
            int i9 = this.expanded?1:0;
            StringBuilder a8 = a7.append(i9 != 0);
            String s0 = a8.toString();
            a5.println(s0);
            java.io.PrintStream a9 = System.err;
            StringBuilder a10 = new StringBuilder();
            StringBuilder a11 = a10.append("alwaysExpanded: ");
            int i10 = this.alwaysExpanded?1:0;
            StringBuilder a12 = a11.append(i10 != 0);
            String s1 = a12.toString();
            a9.println(s1);
            java.io.PrintStream a13 = System.err;
            StringBuilder a14 = new StringBuilder();
            StringBuilder a15 = a14.append("Hidden lines: ");
            StringBuilder a16 = a15.append(i);
            String s2 = a16.toString();
            a13.println(s2);
        }
        return i;
    }
    
    public void display(java.awt.Graphics a, com.cim.AIA.CodeCanvas a0)
    {
        this.display(a, a0, true);
    }
    
    public void display(java.awt.Graphics a, com.cim.AIA.CodeCanvas a0, boolean b)
    {
        this.visible = true;
        int i = b?1:0;
        int i0 = this.alwaysExpanded?1:0;
        if(i0 == 0)
        {
            com.cim.AIA.AlgorithmLine a1 = this.getAlgorithmLine();
            java.awt.Point a2 = this.pos;
            int i1 = this.expanded?1:0;
            int i2 = this.isExpandable()?1:0;
            a0.drawAlgorithmLine(a, a1, a2, i1 != 0, i2 != 0, i != 0);
        }
        else
        {
            com.cim.AIA.AlgorithmLine a3 = this.getAlgorithmLine();
            java.awt.Point a4 = this.pos;
            a0.drawAlgorithmLine(a, a3, a4, true, false, i != 0);
        }
        int i3 = this.isExpanded()?1:0;
        label0: {
            if(i3 == 0)
            {
                break label0;
            }
            int i4 = 0;
            while(true)
            {
                java.util.Vector a5 = this.children;
                int i5 = a5.size();
                if(i4 >= i5)
                {
                    break;
                }
                java.util.Vector a6 = this.children;
                Object a7 = a6.elementAt(i4);
                com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a7;
                com.cim.AIA.LadderTree a8 = (com.cim.AIA.LadderTree)a7;
                com.cim.AIA.AlgorithmLine a9 = this.getAlgorithmLine();
                int i6 = a9.getIsMinMax()?1:0;
                if(i6 == 0)
                {
                    a8.display(a, a0);
                }
                else
                {
                    a8.display(a, a0, false);
                }
                int i7 = i4 + 1;
                i4 = i7;
            }
        }
    }
    
    public synchronized void displayTree(java.awt.Graphics a, com.cim.AIA.CodeCanvas a0)
    {
        this.removeAllVisibleMarkers();
        com.cim.AIA.LadderTreeImplementor a1 = com.cim.AIA.LadderTree.lImplementor;
        int i = this.getDepth();
        a1.layout(this, a0, i);
        com.cim.AIA.BreakPoint a2 = this.breakpoint;
        if(a2 != null)
        {
            com.cim.AIA.BreakPoint a3 = this.breakpoint;
            a3.highlightBreakPoints();
        }
        String s = this.currentPosition;
        this.highlight(s, 1);
        java.awt.Color a4 = a0.getBackgroundColor();
        a.setColor(a4);
        java.awt.Dimension a5 = a0.getSize();
        int i0 = a5.width;
        java.awt.Dimension a6 = a0.getSize();
        int i1 = a6.height;
        a.fillRect(0, 0, i0, i1);
        com.cim.AIA.LadderTreeImplementor a7 = com.cim.AIA.LadderTree.lImplementor;
        int i2 = a7.getMaxX();
        com.cim.AIA.LadderTreeImplementor a8 = com.cim.AIA.LadderTree.lImplementor;
        int i3 = a8.getMaxY();
        a0.setSize(i2, i3);
        com.cim.AIA.LadderTreeImplementor a9 = com.cim.AIA.LadderTree.lImplementor;
        a9.paintTree(a, this, a0);
    }
    
    synchronized void expandEntireLadderTree(boolean b)
    {
        int i = b?1:0;
        com.cim.AIA.LadderTree a = this;
        while(true)
        {
            com.cim.AIA.Tree a0 = a.getParent();
            int i0 = i;
            int i1 = i0;
            if(a0 == null)
            {
                a.expandLadderTree(i0 != 0);
                return;
            }
            else
            {
                int i2 = i1;
                com.cim.AIA.Tree a1 = a.getParent();
                int i3 = i2;
                com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a1;
                com.cim.AIA.LadderTree a2 = (com.cim.AIA.LadderTree)a1;
                int i4 = i3;
                i = i4;
                a = a2;
            }
        }
    }
    
    synchronized void expandLadderTree(boolean b)
    {
        int i = this.isExpandable()?1:0;
        int i0 = b?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            int i1 = this.expandAsOwn?1:0;
            if(i1 == 0)
            {
                this.setExpand(i0 != 0);
            }
        }
        int i2 = 0;
        while(true)
        {
            java.util.Vector a = this.children;
            int i3 = a.size();
            if(i2 >= i3)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.children;
                Object a1 = a0.elementAt(i2);
                com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a1;
                com.cim.AIA.LadderTree a2 = (com.cim.AIA.LadderTree)a1;
                a2.expandLadderTree(i0 != 0);
                int i4 = i2 + 1;
                i2 = i4;
            }
        }
    }
    
    protected void explain(com.cim.AIA.ExplainationListener a, String s, String s0)
    {
        Object a0 = a;
        com.cim.AIA.ExplainationEvent a1 = new com.cim.AIA.ExplainationEvent((Object)this, s, s0);
        ((com.cim.AIA.ExplainationListener)a0).processExplainationEvent(a1);
    }
    
    protected void findParentBreakPoint(String s)
    {
        java.util.Hashtable a = this.ladderTreeHashtable;
        Object a0 = a.get((Object)s);
        com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a0;
        com.cim.AIA.LadderTree a1 = (com.cim.AIA.LadderTree)a0;
        label1: {
            com.cim.AIA.LadderTree a2 = null;
            label0: {
                if(a1 != null)
                {
                    a2 = a1;
                    break label0;
                }
                java.io.PrintStream a3 = System.out;
                StringBuilder a4 = new StringBuilder();
                StringBuilder a5 = a4.append("com.cim.AIA.findParentBreakPoint: LadderTree does not contain key '");
                StringBuilder a6 = a5.append(s);
                StringBuilder a7 = a6.append("'.");
                String s0 = a7.toString();
                a3.println(s0);
                break label1;
            }
            while(true)
            {
                com.cim.AIA.Tree a8 = a2.getParent();
                if(a8 == null)
                {
                    break;
                }
                com.cim.AIA.Tree a9 = a2.getParent();
                com.cim.AIA.LadderTree dummy0 = (com.cim.AIA.LadderTree)a9;
                com.cim.AIA.LadderTree a10 = (com.cim.AIA.LadderTree)a9;
                com.cim.AIA.AlgorithmLine a11 = a10.getAlgorithmLine();
                com.cim.AIA.BreakPoint a12 = this.breakpoint;
                label2: {
                    if(a12 == null)
                    {
                        break label2;
                    }
                    com.cim.AIA.BreakPoint a13 = this.breakpoint;
                    String s1 = a11.getKey();
                    int i = a13.isKeyBreaked(s1)?1:0;
                    if(i == 0)
                    {
                        break label2;
                    }
                    com.cim.AIA.BreakPoint a14 = this.breakpoint;
                    com.cim.AIA.BreakPointLine a15 = a14.getLastSearched();
                    int i0 = a15.isChildSet()?1:0;
                    label3: {
                        if(i0 != 0)
                        {
                            break label3;
                        }
                        com.cim.AIA.BreakPoint a16 = this.breakpoint;
                        com.cim.AIA.AlgorithmLine a17 = a15.getAlgorithmLine();
                        String s2 = a17.getKey();
                        a16.processBreakPoint(s2);
                        com.cim.AIA.AlgorithmLine a18 = a1.getAlgorithmLine();
                        a15.setChild(a18);
                        break label2;
                    }
                    com.cim.AIA.AlgorithmLine a19 = a15.getChild();
                    com.cim.AIA.AlgorithmLine a20 = a1.getAlgorithmLine();
                    if(a19 == a20)
                    {
                        com.cim.AIA.BreakPoint a21 = this.breakpoint;
                        com.cim.AIA.AlgorithmLine a22 = a15.getAlgorithmLine();
                        String s3 = a22.getKey();
                        a21.processBreakPoint(s3);
                    }
                }
                a2 = a10;
            }
        }
    }
    
    public com.cim.AIA.AlgorithmLine getAlgorithmLine()
    {
        Object a = this.object;
        com.cim.AIA.AlgorithmLine dummy = (com.cim.AIA.AlgorithmLine)a;
        com.cim.AIA.AlgorithmLine a0 = (com.cim.AIA.AlgorithmLine)a;
        return a0;
    }
    
    public com.cim.AIA.LadderTree getLadderTree(String s)
    {
        java.util.Hashtable a = this.ladderTreeHashtable;
        Object a0 = a.get((Object)s);
        com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a0;
        com.cim.AIA.LadderTree a1 = (com.cim.AIA.LadderTree)a0;
        return a1;
    }
    
    public boolean getVisible()
    {
        int i = this.visible?1:0;
        return i != 0;
    }
    
    public synchronized String getWhatHighlighted(String s)
    {
        String s0 = null;
        java.util.Hashtable a = this.ladderTreeHashtable;
        Object a0 = a.get((Object)s);
        com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a0;
        com.cim.AIA.LadderTree a1 = (com.cim.AIA.LadderTree)a0;
        label1: {
            com.cim.AIA.LadderTree a2 = null;
            label0: {
                if(a1 != null)
                {
                    a2 = a1;
                    break label0;
                }
                java.io.PrintStream a3 = System.out;
                StringBuilder a4 = new StringBuilder();
                StringBuilder a5 = a4.append("com.cim.AIA.LadderTree.getWhatHighlighted: Ladder Tree does not contain key '");
                StringBuilder a6 = a5.append(s);
                StringBuilder a7 = a6.append("'.");
                String s1 = a7.toString();
                a3.println(s1);
                s0 = null;
                break label1;
            }
            while(true)
            {
                int i = a2.visible?1:0;
                if(i != 0)
                {
                    break;
                }
                com.cim.AIA.Tree a8 = a2.getParent();
                if(a8 == null)
                {
                    break;
                }
                else
                {
                    com.cim.AIA.Tree a9 = a2.getParent();
                    com.cim.AIA.LadderTree dummy0 = (com.cim.AIA.LadderTree)a9;
                    com.cim.AIA.LadderTree a10 = (com.cim.AIA.LadderTree)a9;
                    a2 = a10;
                }
            }
            com.cim.AIA.AlgorithmLine a11 = a2.getAlgorithmLine();
            String s2 = a11.getKey();
            s0 = s2;
        }
        return s0;
    }
    
    public int getXPos()
    {
        java.awt.Point a = this.pos;
        int i = a.x;
        return i;
    }
    
    public int getYPos()
    {
        java.awt.Point a = this.pos;
        int i = a.y;
        return i;
    }
    
    protected void help(com.cim.AIA.HelpListener a, String s, String s0)
    {
        Object a0 = a;
        com.cim.AIA.HelpEvent a1 = new com.cim.AIA.HelpEvent((Object)this, s, s0);
        ((com.cim.AIA.HelpListener)a0).processHelpEvent(a1);
    }
    
    public synchronized void highlight(String s, int i)
    {
        this.removeAllBackgroundHighlight(i);
        String s0 = this.currentPosition;
        label2: {
            label1: {
                label0: {
                    com.cim.AIA.LadderTree a = null;
                    if(s0 == null)
                    {
                        break label0;
                    }
                    String s1 = this.currentPosition;
                    if(s1 == "")
                    {
                        break label0;
                    }
                    java.util.Hashtable a0 = this.ladderTreeHashtable;
                    Object a1 = a0.get((Object)s);
                    com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a1;
                    com.cim.AIA.LadderTree a2 = (com.cim.AIA.LadderTree)a1;
                    if(a2 != null)
                    {
                        a = a2;
                    }
                    else
                    {
                        break label1;
                    }
                    while(true)
                    {
                        int i0 = a.visible?1:0;
                        if(i0 != 0)
                        {
                            break;
                        }
                        com.cim.AIA.Tree a3 = a.getParent();
                        if(a3 == null)
                        {
                            break;
                        }
                        else
                        {
                            com.cim.AIA.Tree a4 = a.getParent();
                            com.cim.AIA.LadderTree dummy0 = (com.cim.AIA.LadderTree)a4;
                            com.cim.AIA.LadderTree a5 = (com.cim.AIA.LadderTree)a4;
                            a = a5;
                        }
                    }
                    com.cim.AIA.AlgorithmLine a6 = a.getAlgorithmLine();
                    a6.setHighlightLevel(i);
                }
                break label2;
            }
            java.io.PrintStream a7 = System.out;
            StringBuilder a8 = new StringBuilder();
            StringBuilder a9 = a8.append("com.cim.AIA.highlight: Ladder Tree does not contain key '");
            StringBuilder a10 = a9.append(s);
            StringBuilder a11 = a10.append("'.");
            String s2 = a11.toString();
            a7.println(s2);
        }
    }
    
    public boolean isExpandable()
    {
        int i = 0;
        int i0 = this.alwaysExpanded?1:0;
        label1: {
            label0: {
                if(i0 == 0)
                {
                    break label0;
                }
                i = 1;
                break label1;
            }
            java.util.Vector a = this.children;
            int i1 = a.size();
            int i2 = i1 <= 0?0:1;
            i = i2;
        }
        return i != 0;
    }
    
    public boolean isExpandable(String s)
    {
        int i = 0;
        java.util.Hashtable a = this.ladderTreeHashtable;
        Object a0 = a.get((Object)s);
        com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a0;
        com.cim.AIA.LadderTree a1 = (com.cim.AIA.LadderTree)a0;
        if(a1 != null)
        {
            int i0 = a1.isExpandable()?1:0;
            i = i0;
        }
        else
        {
            i = 0;
        }
        return i != 0;
    }
    
    public boolean isExpanded()
    {
        int i = 0;
        int i0 = this.isExpandable()?1:0;
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            int i1 = this.alwaysExpanded?1:0;
            if(i1 == 0)
            {
                int i2 = this.expanded?1:0;
                i = i2;
            }
            else
            {
                i = 1;
            }
        }
        return i != 0;
    }
    
    public boolean isExpanded(String s)
    {
        int i = 0;
        java.util.Hashtable a = this.ladderTreeHashtable;
        Object a0 = a.get((Object)s);
        com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a0;
        com.cim.AIA.LadderTree a1 = (com.cim.AIA.LadderTree)a0;
        if(a1 != null)
        {
            int i0 = a1.isExpanded()?1:0;
            i = i0;
        }
        else
        {
            i = 0;
        }
        return i != 0;
    }
    
    public boolean isVisible()
    {
        int i = this.visible?1:0;
        return i != 0;
    }
    
    public synchronized boolean isVisible(String s)
    {
        int i = 0;
        java.util.Hashtable a = this.ladderTreeHashtable;
        Object a0 = a.get((Object)s);
        com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a0;
        com.cim.AIA.LadderTree a1 = (com.cim.AIA.LadderTree)a0;
        if(a1 != null)
        {
            int i0 = a1.isVisible()?1:0;
            i = i0;
        }
        else
        {
            i = 0;
        }
        return i != 0;
    }
    
    synchronized com.cim.AIA.LadderTree processMouseEvent(java.awt.event.MouseEvent a, com.cim.AIA.CodeCanvas a0, boolean b)
    {
        java.awt.Rectangle a1 = null;
        com.cim.AIA.LadderTree a2 = null;
        com.cim.AIA.AlgorithmLine a3 = this.getAlgorithmLine();
        int i = b?1:0;
        int i0 = a3.getIsMinMax()?1:0;
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                java.awt.Point a4 = this.pos;
                int i1 = a4.x;
                java.awt.Point a5 = this.pos;
                int i2 = a5.y;
                com.cim.AIA.AlgorithmLine a6 = this.getAlgorithmLine();
                int i3 = a0.getWidthOf(a6);
                int i4 = a0.getHeightForAnAlgorithmLine();
                java.awt.Rectangle a7 = new java.awt.Rectangle(i1, i2, i3, i4);
                a1 = a7;
                break label1;
            }
            com.cim.AIA.AlgorithmLine a8 = this.getAlgorithmLine();
            int i5 = a8.getIsLabelMinMax()?1:0;
            if(i5 == 0)
            {
                java.awt.Point a9 = this.pos;
                int i6 = a9.x;
                int i7 = a0.getMinMaxXGap();
                int i8 = i7 * 2;
                int i9 = i6 + i8;
                int i10 = a0.getMinMaxLineLength();
                int i11 = i10 * 2;
                int i12 = i9 + i11;
                com.cim.AIA.AlgorithmLine a10 = this.getAlgorithmLine();
                String s = a10.getMinMaxLabel();
                int i13 = a0.getWidthOf(s);
                int i14 = i12 + i13;
                java.awt.Point a11 = this.pos;
                int i15 = a11.y;
                com.cim.AIA.AlgorithmLine a12 = this.getAlgorithmLine();
                int i16 = a0.getWidthOf(a12);
                int i17 = a0.getHeightForAnAlgorithmLine();
                java.awt.Rectangle a13 = new java.awt.Rectangle(i14, i15, i16, i17);
                a1 = a13;
            }
            else
            {
                com.cim.AIA.AlgorithmLine a14 = this.getAlgorithmLine();
                java.awt.Point a15 = a14.getLocation();
                int i18 = a15.x;
                int i19 = a15.y;
                com.cim.AIA.AlgorithmLine a16 = this.getAlgorithmLine();
                int i20 = a0.getWidthOf(a16);
                int i21 = a0.getHeightForAnAlgorithmLine();
                java.awt.Rectangle a17 = new java.awt.Rectangle(i18, i19, i20, i21);
                a1 = a17;
            }
        }
        java.awt.Point a18 = a.getPoint();
        int i22 = a1.contains(a18)?1:0;
        label8: {
            label2: {
                com.cim.AIA.LadderTree a19 = null;
                if(i22 == 0)
                {
                    break label2;
                }
                label4: {
                    label3: {
                        if(i == 0)
                        {
                            break label3;
                        }
                        com.cim.AIA.BreakPoint a20 = this.breakpoint;
                        int i23 = a20.inMutex?1:0;
                        if(i23 == 0)
                        {
                            break label3;
                        }
                        com.cim.AIA.BreakPoint a21 = this.breakpoint;
                        com.cim.AIA.AlgorithmLine a22 = this.getAlgorithmLine();
                        a21.processMutex(a22);
                        com.cim.AIA.AlgorithmLine a23 = this.getAlgorithmLine();
                        a23.setHighlightLevel(0);
                        break label4;
                    }
                    if(i != 0)
                    {
                        this.removeAllBackgroundHighlight(2);
                        com.cim.AIA.AlgorithmLine a24 = this.getAlgorithmLine();
                        a24.setHighlightLevel(2);
                    }
                }
                label6: {
                    label5: {
                        if(i != 0)
                        {
                            break label5;
                        }
                        String s0 = com.cim.AIA.CodeCanvas.LINE_HELP_TITLE;
                        String s1 = com.cim.AIA.CodeCanvas.LINE_INSTRUCTIONS;
                        this.help((com.cim.AIA.HelpListener)a0, s0, s1);
                        a19 = this;
                        break label6;
                    }
                    int i24 = this.countLeaves();
                    com.cim.AIA.AlgorithmLine a25 = this.getAlgorithmLine();
                    String s2 = a25.getKey();
                    com.cim.AIA.AlgorithmLine a26 = this.getAlgorithmLine();
                    String s3 = a26.getLabel();
                    com.cim.AIA.StepLog a27 = new com.cim.AIA.StepLog((byte)6, (byte)10, s2, i24, s3);
                    com.cim.AIA.Logger a28 = this.logger;
                    if(a28 != null)
                    {
                        com.cim.AIA.Logger a29 = this.logger;
                        a29.addLog((com.cim.AIA.Log)a27);
                    }
                    com.cim.AIA.AlgorithmLine a30 = this.getAlgorithmLine();
                    String s4 = a30.getLabel();
                    com.cim.AIA.AlgorithmLine a31 = this.getAlgorithmLine();
                    String s5 = a31.getExplaination();
                    this.explain((com.cim.AIA.ExplainationListener)a0, s4, s5);
                    a19 = this;
                }
                while(true)
                {
                    com.cim.AIA.Tree a32 = a19.getParent();
                    if(a32 == null)
                    {
                        break;
                    }
                    int i25 = a19.expandAsOwn?1:0;
                    label7: {
                        if(i25 == 0)
                        {
                            break label7;
                        }
                        int i26 = a19.isExpanded()?1:0;
                        if(i26 != 0)
                        {
                            break;
                        }
                    }
                    com.cim.AIA.Tree a33 = a19.getParent();
                    com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a33;
                    com.cim.AIA.LadderTree a34 = (com.cim.AIA.LadderTree)a33;
                    a19 = a34;
                    continue;
                }
                a2 = a19;
                break label8;
            }
            int i27 = this.isExpandable()?1:0;
            label10: {
                com.cim.AIA.LadderTree a35 = null;
                label9: {
                    java.awt.Rectangle a36 = null;
                    if(i27 == 0)
                    {
                        break label9;
                    }
                    int i28 = this.expanded?1:0;
                    if(i28 == 0)
                    {
                        java.awt.Point a37 = this.pos;
                        int i29 = a37.x;
                        java.awt.Dimension a38 = a0.getCollapsedImageSize();
                        int i30 = a38.width;
                        int i31 = i29 - i30;
                        int i32 = a0.getImageBuffer();
                        int i33 = i31 - i32;
                        java.awt.Point a39 = this.pos;
                        int i34 = a39.y;
                        java.awt.Dimension a40 = a0.getCollapsedImageSize();
                        int i35 = a40.width;
                        java.awt.Dimension a41 = a0.getCollapsedImageSize();
                        int i36 = a41.height;
                        java.awt.Rectangle a42 = new java.awt.Rectangle(i33, i34, i35, i36);
                        a36 = a42;
                    }
                    else
                    {
                        java.awt.Point a43 = this.pos;
                        int i37 = a43.x;
                        java.awt.Dimension a44 = a0.getExpandedImageSize();
                        int i38 = a44.width;
                        int i39 = i37 - i38;
                        int i40 = a0.getImageBuffer();
                        int i41 = i39 - i40;
                        java.awt.Point a45 = this.pos;
                        int i42 = a45.y;
                        java.awt.Dimension a46 = a0.getExpandedImageSize();
                        int i43 = a46.width;
                        java.awt.Dimension a47 = a0.getExpandedImageSize();
                        int i44 = a47.height;
                        java.awt.Rectangle a48 = new java.awt.Rectangle(i41, i42, i43, i44);
                        a36 = a48;
                    }
                    java.awt.Point a49 = a.getPoint();
                    int i45 = a36.contains(a49)?1:0;
                    if(i45 != 0)
                    {
                        break label10;
                    }
                }
                int i46 = this.isExpanded()?1:0;
                label12: {
                    label11: {
                        if(i46 == 0)
                        {
                            break label11;
                        }
                        int i47 = 0;
                        while(true)
                        {
                            java.util.Vector a50 = this.children;
                            int i48 = a50.size();
                            if(i47 >= i48)
                            {
                                break;
                            }
                            java.util.Vector a51 = this.children;
                            Object a52 = a51.elementAt(i47);
                            com.cim.AIA.LadderTree dummy0 = (com.cim.AIA.LadderTree)a52;
                            com.cim.AIA.LadderTree a53 = (com.cim.AIA.LadderTree)a52;
                            a35 = a53.processMouseEvent(a, a0, i != 0);
                            if(a35 == null)
                            {
                                int i49 = i47 + 1;
                                i47 = i49;
                            }
                            else
                            {
                                break label12;
                            }
                        }
                    }
                    a2 = null;
                    break label8;
                }
                a2 = a35;
                break label8;
            }
            label13: {
                if(i == 0)
                {
                    break label13;
                }
                int i50 = this.expanded?1:0;
                label15: {
                    label14: {
                        if(i50 != 0)
                        {
                            break label14;
                        }
                        int i51 = this.countLeaves();
                        com.cim.AIA.AlgorithmLine a54 = this.getAlgorithmLine();
                        String s6 = a54.getKey();
                        com.cim.AIA.AlgorithmLine a55 = this.getAlgorithmLine();
                        String s7 = a55.getLabel();
                        com.cim.AIA.StepLog a56 = new com.cim.AIA.StepLog((byte)5, (byte)12, s6, i51, s7);
                        com.cim.AIA.Logger a57 = this.logger;
                        if(a57 == null)
                        {
                            break label15;
                        }
                        else
                        {
                            com.cim.AIA.Logger a58 = this.logger;
                            a58.addLog((com.cim.AIA.Log)a56);
                            break label15;
                        }
                    }
                    int i52 = this.countLeaves();
                    com.cim.AIA.AlgorithmLine a59 = this.getAlgorithmLine();
                    String s8 = a59.getKey();
                    com.cim.AIA.AlgorithmLine a60 = this.getAlgorithmLine();
                    String s9 = a60.getLabel();
                    com.cim.AIA.StepLog a61 = new com.cim.AIA.StepLog((byte)5, (byte)11, s8, i52, s9);
                    com.cim.AIA.Logger a62 = this.logger;
                    if(a62 != null)
                    {
                        com.cim.AIA.Logger a63 = this.logger;
                        a63.addLog((com.cim.AIA.Log)a61);
                    }
                }
                this.toggleExpand();
            }
            int i53 = this.alwaysExpanded?1:0;
            label16: {
                if(i53 != 0)
                {
                    break label16;
                }
                int i54 = this.expanded?1:0;
                if(i54 == 0)
                {
                    String s10 = com.cim.AIA.CodeCanvas.OPEN_FOLDER_HELP_TITLE;
                    String s11 = com.cim.AIA.CodeCanvas.OPEN_FOLDER_INSTRUCTIONS;
                    this.help((com.cim.AIA.HelpListener)a0, s10, s11);
                }
                else
                {
                    String s12 = com.cim.AIA.CodeCanvas.CLOSE_FOLDER_HELP_TITLE;
                    String s13 = com.cim.AIA.CodeCanvas.CLOSE_FOLDER_INSTRUCTIONS;
                    this.help((com.cim.AIA.HelpListener)a0, s12, s13);
                }
            }
            com.cim.AIA.LadderTree a64 = this;
            while(true)
            {
                com.cim.AIA.Tree a65 = a64.getParent();
                if(a65 == null)
                {
                    break;
                }
                int i55 = a64.expandAsOwn?1:0;
                label17: {
                    if(i55 == 0)
                    {
                        break label17;
                    }
                    int i56 = a64.isExpanded()?1:0;
                    if(i56 != 0)
                    {
                        break;
                    }
                }
                com.cim.AIA.Tree a66 = a64.getParent();
                com.cim.AIA.LadderTree dummy1 = (com.cim.AIA.LadderTree)a66;
                com.cim.AIA.LadderTree a67 = (com.cim.AIA.LadderTree)a66;
                a64 = a67;
            }
            a2 = a64;
        }
        return a2;
    }
    
    public synchronized void removeAllBackgroundHighlight(int i)
    {
        com.cim.AIA.LadderTree a = this;
        while(true)
        {
            com.cim.AIA.Tree a0 = a.getParent();
            if(a0 == null)
            {
                a.removeBackgroundHighlight(i);
                return;
            }
            else
            {
                com.cim.AIA.Tree a1 = a.getParent();
                com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a1;
                com.cim.AIA.LadderTree a2 = (com.cim.AIA.LadderTree)a1;
                a = a2;
            }
        }
    }
    
    protected synchronized void removeAllVisibleMarkers()
    {
        this.visible = false;
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.children;
            int i0 = a.size();
            if(i >= i0)
            {
                return;
            }
            else
            {
                java.util.Vector a0 = this.children;
                Object a1 = a0.elementAt(i);
                com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a1;
                com.cim.AIA.LadderTree a2 = (com.cim.AIA.LadderTree)a1;
                a2.removeAllVisibleMarkers();
                int i1 = i + 1;
                i = i1;
            }
        }
    }
    
    public synchronized void removeBackgroundHighlight(int i)
    {
        com.cim.AIA.AlgorithmLine a = this.getAlgorithmLine();
        int i0 = a.getHighlightLevel();
        if(i0 == i)
        {
            com.cim.AIA.AlgorithmLine a0 = this.getAlgorithmLine();
            a0.setHighlightLevel(0);
        }
        int i1 = 0;
        while(true)
        {
            java.util.Vector a1 = this.children;
            int i2 = a1.size();
            if(i1 >= i2)
            {
                return;
            }
            else
            {
                java.util.Vector a2 = this.children;
                Object a3 = a2.elementAt(i1);
                com.cim.AIA.LadderTree dummy = (com.cim.AIA.LadderTree)a3;
                com.cim.AIA.LadderTree a4 = (com.cim.AIA.LadderTree)a3;
                a4.removeBackgroundHighlight(i);
                int i3 = i1 + 1;
                i1 = i3;
            }
        }
    }
    
    public void setAlwaysExpanded(boolean b)
    {
        this.alwaysExpanded = b;
    }
    
    public void setExpand(boolean b)
    {
        int i = this.expanded?1:0;
        int i0 = b?1:0;
        int i1 = b?1:0;
        label1: {
            label0: {
                if(i0 != i)
                {
                    break label0;
                }
                break label1;
            }
            this.expanded = i1 != 0;
            com.cim.AIA.AlgorithmLine a = this.getAlgorithmLine();
            java.util.Vector a0 = a.getTriggers();
            int i2 = 0;
            while(true)
            {
                int i3 = a0.size();
                if(i2 >= i3)
                {
                    break;
                }
                Object a1 = a0.elementAt(i2);
                String dummy = (String)a1;
                String s = (String)a1;
                com.cim.AIA.LadderTree a2 = this.getLadderTree(s);
                label2: {
                    if(a2 == null)
                    {
                        break label2;
                    }
                    int i4 = this.expanded?1:0;
                    if(i4 == 0)
                    {
                        com.cim.AIA.AlgorithmLine a3 = a2.getAlgorithmLine();
                        a3.decreaseTriggerCount();
                    }
                    else
                    {
                        com.cim.AIA.AlgorithmLine a4 = a2.getAlgorithmLine();
                        a4.increaseTriggerCount();
                    }
                }
                int i5 = i2 + 1;
                i2 = i5;
            }
        }
    }
    
    public void setExpandAsOwn(boolean b)
    {
        this.expandAsOwn = b;
    }
    
    protected void setPosition(String s)
    {
        label0: {
            if(s == "")
            {
                break label0;
            }
            int i = this.isVisible(s)?1:0;
            String s0 = i == 0?"hidden":"visible";
            com.cim.AIA.Log a = new com.cim.AIA.Log((byte)3, (byte)13, s, s0);
            com.cim.AIA.Logger a0 = this.logger;
            if(a0 != null)
            {
                com.cim.AIA.Logger a1 = this.logger;
                a1.addLog(a);
            }
            this.findParentBreakPoint(s);
        }
        this.currentPosition = s;
    }
    
    public void setVisible(boolean b)
    {
        this.visible = b;
    }
    
    public void setXPos(int i)
    {
        java.awt.Point a = this.pos;
        a.x = i;
    }
    
    public void setYPos(int i)
    {
        java.awt.Point a = this.pos;
        a.y = i;
    }
    
    public void toggleExpand()
    {
        int i = this.alwaysExpanded?1:0;
        label0: {
            if(i != 0)
            {
                break label0;
            }
            int i0 = this.isExpandable()?1:0;
            if(i0 == 0)
            {
                break label0;
            }
            int i1 = this.expanded?1:0;
            int i2 = i1 != 0?0:1;
            this.setExpand(i2 != 0);
        }
    }
    
    static
    {
        com.cim.AIA.LadderTreeImplementor a = new com.cim.AIA.LadderTreeImplementor();
        com.cim.AIA.LadderTree.lImplementor = a;
    }
}