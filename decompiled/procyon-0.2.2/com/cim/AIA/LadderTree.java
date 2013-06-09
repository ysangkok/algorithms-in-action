package com.cim.AIA;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class LadderTree extends Tree
{
    private static final long serialVersionUID = -9204623185530382869L;
    public static final String VISIBLE = "visible";
    public static final String HIDDEN = "hidden";
    protected static LadderTreeImplementor lImplementor;
    protected static final int EXPECTED_NUMBER_OF_LOC = 40;
    protected Point pos;
    protected boolean expanded;
    protected boolean expandAsOwn;
    protected boolean alwaysExpanded;
    protected String currentPosition;
    protected boolean visible;
    protected Hashtable<String, LadderTree> ladderTreeHashtable;
    protected Logger logger;
    protected BreakPoint breakpoint;
    
    public LadderTree(final LadderTree parent, final AlgorithmLine algLine, final Logger logger, final BreakPoint breakpoint) {
        super(parent, algLine);
        this.pos = new Point();
        this.expanded = false;
        this.expandAsOwn = false;
        this.alwaysExpanded = false;
        this.currentPosition = "";
        this.visible = false;
        this.ladderTreeHashtable = null;
        if (parent != null) {
            parent.children.addElement(this);
            LadderTree temp = this;
            while (temp.getParent() != null) {
                temp = (LadderTree)temp.getParent();
            }
            this.ladderTreeHashtable = temp.ladderTreeHashtable;
        }
        else {
            this.ladderTreeHashtable = new Hashtable(40);
        }
        this.ladderTreeHashtable.put(algLine.getKey(), this);
        this.logger = logger;
        this.breakpoint = breakpoint;
    }
    
    public boolean containsKey(final String key) {
        return this.getLadderTree(key) != null;
    }
    
    public int countHiddenLines() {
        int nlines = 1;
        if (!this.isExpanded()) {
            for (int i = 0; i < this.children.size(); ++i) {
                nlines = nlines + this.getChild(i).countLeaves();
            }
        }
        if (Logger.DEBUG) {
            System.err.println("isExpanded(): " + this.isExpanded());
            System.err.println("expanded: " + this.expanded);
            System.err.println("alwaysExpanded: " + this.alwaysExpanded);
            System.err.println("Hidden lines: " + nlines);
        }
        return nlines;
    }
    
    public void display(final Graphics g, final CodeCanvas codeCanvas) {
        this.display(g, codeCanvas, true);
    }
    
    public void display(final Graphics g, final CodeCanvas codeCanvas, final boolean drawBits) {
        this.visible = true;
        if (this.alwaysExpanded) {
            codeCanvas.drawAlgorithmLine(g, this.getAlgorithmLine(), this.pos, true, false, drawBits);
        }
        else {
            codeCanvas.drawAlgorithmLine(g, this.getAlgorithmLine(), this.pos, this.expanded, this.isExpandable(), drawBits);
        }
        if (this.isExpanded()) {
            for (int i = 0; i < this.children.size(); ++i) {
                final LadderTree ladderTree = (LadderTree)this.children.elementAt(i);
                if (this.getAlgorithmLine().getIsMinMax()) {
                    ladderTree.display(g, codeCanvas, false);
                }
                else {
                    ladderTree.display(g, codeCanvas);
                }
            }
        }
    }
    
    public synchronized void displayTree(final Graphics g, final CodeCanvas c) {
        this.removeAllVisibleMarkers();
        LadderTree.lImplementor.layout(this, c, this.getDepth());
        if (this.breakpoint != null) {
            this.breakpoint.highlightBreakPoints();
        }
        this.highlight(this.currentPosition, 1);
        g.setColor(c.getBackgroundColor());
        g.fillRect(0, 0, c.getSize().width, c.getSize().height);
        c.setSize(LadderTree.lImplementor.getMaxX(), LadderTree.lImplementor.getMaxY());
        LadderTree.lImplementor.paintTree(g, this, c);
    }
    
    synchronized void expandEntireLadderTree(final boolean expand) {
        LadderTree root = this;
        while (root.getParent() != null) {
            root = (LadderTree)root.getParent();
        }
        root.expandLadderTree(expand);
    }
    
    synchronized void expandLadderTree(final boolean expand) {
        if (this.isExpandable() && !this.expandAsOwn) {
            this.setExpand(expand);
        }
        for (int i = 0; i < this.children.size(); ++i) {
            final LadderTree ladderTree = (LadderTree)this.children.elementAt(i);
            ladderTree.expandLadderTree(expand);
        }
    }
    
    protected void explain(final ExplainationListener explainationListener, final String title, final String explaination) {
        final ExplainationEvent e = new ExplainationEvent(this, title, explaination);
        explainationListener.processExplainationEvent(e);
    }
    
    protected void findParentBreakPoint(final String posId) {
        final LadderTree temp2;
        LadderTree temp = temp2 = (LadderTree)this.ladderTreeHashtable.get(posId);
        if (temp == null) {
            System.out.println("com.cim.AIA.findParentBreakPoint: LadderTree does not contain key '" + posId + "'.");
            return;
        }
        while (temp.getParent() != null) {
            temp = (LadderTree)temp.getParent();
            final AlgorithmLine tempAlg = temp.getAlgorithmLine();
            if (this.breakpoint != null && this.breakpoint.isKeyBreaked(tempAlg.getKey())) {
                final BreakPointLine theLine = this.breakpoint.getLastSearched();
                if (!theLine.isChildSet()) {
                    this.breakpoint.processBreakPoint(theLine.getAlgorithmLine().getKey());
                    theLine.setChild(temp2.getAlgorithmLine());
                }
                else if (theLine.getChild() == temp2.getAlgorithmLine()) {
                    this.breakpoint.processBreakPoint(theLine.getAlgorithmLine().getKey());
                }
            }
        }
    }
    
    public AlgorithmLine getAlgorithmLine() {
        return (AlgorithmLine)this.object;
    }
    
    public LadderTree getLadderTree(final String key) {
        return (LadderTree)this.ladderTreeHashtable.get(key);
    }
    
    public boolean getVisible() {
        return this.visible;
    }
    
    public synchronized String getWhatHighlighted(final String key) {
        LadderTree temp = (LadderTree)this.ladderTreeHashtable.get(key);
        if (temp == null) {
            System.out.println("com.cim.AIA.LadderTree.getWhatHighlighted: Ladder Tree does not contain key '" + key + "'.");
            return null;
        }
        while (!temp.visible && temp.getParent() != null) {
            temp = (LadderTree)temp.getParent();
        }
        return temp.getAlgorithmLine().getKey();
    }
    
    public int getXPos() {
        return this.pos.x;
    }
    
    public int getYPos() {
        return this.pos.y;
    }
    
    protected void help(final HelpListener helpListener, final String topic, final String instructions) {
        final HelpEvent e = new HelpEvent(this, topic, instructions);
        helpListener.processHelpEvent(e);
    }
    
    public synchronized void highlight(final String key, final int level) {
        this.removeAllBackgroundHighlight(level);
        if (this.currentPosition != null && this.currentPosition != "") {
            LadderTree temp = (LadderTree)this.ladderTreeHashtable.get(key);
            if (temp == null) {
                System.out.println("com.cim.AIA.highlight: Ladder Tree does not contain key '" + key + "'.");
                return;
            }
            while (!temp.visible && temp.getParent() != null) {
                temp = (LadderTree)temp.getParent();
            }
            temp.getAlgorithmLine().setHighlightLevel(level);
        }
    }
    
    public boolean isExpandable() {
        return this.alwaysExpanded || this.children.size() > 0;
    }
    
    public boolean isExpandable(final String key) {
        final LadderTree temp = (LadderTree)this.ladderTreeHashtable.get(key);
        return temp != null && temp.isExpandable();
    }
    
    public boolean isExpanded() {
        return this.isExpandable() && (this.alwaysExpanded || this.expanded);
    }
    
    public boolean isExpanded(final String key) {
        final LadderTree temp = (LadderTree)this.ladderTreeHashtable.get(key);
        return temp != null && temp.isExpanded();
    }
    
    public boolean isVisible() {
        return this.visible;
    }
    
    public synchronized boolean isVisible(final String key) {
        final LadderTree temp = (LadderTree)this.ladderTreeHashtable.get(key);
        return temp != null && temp.isVisible();
    }
    
    synchronized LadderTree processMouseEvent(final MouseEvent e, final CodeCanvas c, final boolean selected) {
        Rectangle rect;
        if (this.getAlgorithmLine().getIsMinMax()) {
            if (this.getAlgorithmLine().getIsLabelMinMax()) {
                final Point tmpPnt = this.getAlgorithmLine().getLocation();
                rect = new Rectangle(tmpPnt.x, tmpPnt.y, c.getWidthOf(this.getAlgorithmLine()), c.getHeightForAnAlgorithmLine());
            }
            else {
                rect = new Rectangle(this.pos.x + c.getMinMaxXGap() * 2 + c.getMinMaxLineLength() * 2 + c.getWidthOf(this.getAlgorithmLine().getMinMaxLabel()), this.pos.y, c.getWidthOf(this.getAlgorithmLine()), c.getHeightForAnAlgorithmLine());
            }
        }
        else {
            rect = new Rectangle(this.pos.x, this.pos.y, c.getWidthOf(this.getAlgorithmLine()), c.getHeightForAnAlgorithmLine());
        }
        if (rect.contains(e.getPoint())) {
            if (selected && this.breakpoint.inMutex) {
                this.breakpoint.processMutex(this.getAlgorithmLine());
                this.getAlgorithmLine().setHighlightLevel(0);
            }
            else if (selected) {
                this.removeAllBackgroundHighlight(2);
                this.getAlgorithmLine().setHighlightLevel(2);
            }
            LadderTree root = this;
            if (selected) {
                final int nlines = this.countLeaves();
                final Log l1 = new StepLog(6, 10, this.getAlgorithmLine().getKey(), nlines, this.getAlgorithmLine().getLabel());
                if (this.logger != null) {
                    this.logger.addLog(l1);
                }
                this.explain(c, this.getAlgorithmLine().getLabel(), this.getAlgorithmLine().getExplaination());
            }
            else {
                this.help(c, CodeCanvas.LINE_HELP_TITLE, CodeCanvas.LINE_INSTRUCTIONS);
            }
            while (root.getParent() != null && (!root.expandAsOwn || !root.isExpanded())) {
                root = (LadderTree)root.getParent();
            }
            return root;
        }
        if (this.isExpandable()) {
            if (this.expanded) {
                rect = new Rectangle(this.pos.x - c.getExpandedImageSize().width - c.getImageBuffer(), this.pos.y, c.getExpandedImageSize().width, c.getExpandedImageSize().height);
            }
            else {
                rect = new Rectangle(this.pos.x - c.getCollapsedImageSize().width - c.getImageBuffer(), this.pos.y, c.getCollapsedImageSize().width, c.getCollapsedImageSize().height);
            }
            if (rect.contains(e.getPoint())) {
                if (selected) {
                    if (this.expanded) {
                        final int nlines2 = this.countLeaves();
                        final Log l = new StepLog(5, 11, this.getAlgorithmLine().getKey(), nlines2, this.getAlgorithmLine().getLabel());
                        if (this.logger != null) {
                            this.logger.addLog(l);
                        }
                    }
                    else {
                        final int nlines2 = this.countLeaves();
                        final Log l = new StepLog(5, 12, this.getAlgorithmLine().getKey(), nlines2, this.getAlgorithmLine().getLabel());
                        if (this.logger != null) {
                            this.logger.addLog(l);
                        }
                    }
                    this.toggleExpand();
                }
                if (!this.alwaysExpanded) {
                    if (this.expanded) {
                        this.help(c, CodeCanvas.CLOSE_FOLDER_HELP_TITLE, CodeCanvas.CLOSE_FOLDER_INSTRUCTIONS);
                    }
                    else {
                        this.help(c, CodeCanvas.OPEN_FOLDER_HELP_TITLE, CodeCanvas.OPEN_FOLDER_INSTRUCTIONS);
                    }
                }
                LadderTree root2 = this;
                while (root2.getParent() != null && (!root2.expandAsOwn || !root2.isExpanded())) {
                    root2 = (LadderTree)root2.getParent();
                }
                return root2;
            }
        }
        if (this.isExpanded()) {
            for (int i = 0; i < this.children.size(); ++i) {
                final LadderTree ladderTree = (LadderTree)this.children.elementAt(i);
                final LadderTree temp = ladderTree.processMouseEvent(e, c, selected);
                if (temp != null) {
                    return temp;
                }
            }
        }
        return null;
    }
    
    public synchronized void removeAllBackgroundHighlight(final int level) {
        LadderTree root = this;
        while (root.getParent() != null) {
            root = (LadderTree)root.getParent();
        }
        root.removeBackgroundHighlight(level);
    }
    
    protected synchronized void removeAllVisibleMarkers() {
        this.visible = false;
        for (int i = 0; i < this.children.size(); ++i) {
            final LadderTree ladderTree = (LadderTree)this.children.elementAt(i);
            ladderTree.removeAllVisibleMarkers();
        }
    }
    
    public synchronized void removeBackgroundHighlight(final int level) {
        if (this.getAlgorithmLine().getHighlightLevel() == level) {
            this.getAlgorithmLine().setHighlightLevel(0);
        }
        for (int i = 0; i < this.children.size(); ++i) {
            final LadderTree ladderTree = (LadderTree)this.children.elementAt(i);
            ladderTree.removeBackgroundHighlight(level);
        }
    }
    
    public void setAlwaysExpanded(final boolean alwaysExpanded) {
        this.alwaysExpanded = alwaysExpanded;
    }
    
    public void setExpand(final boolean state) {
        if (state == this.expanded) {
            return;
        }
        this.expanded = state;
        final Vector<E> triggers = this.getAlgorithmLine().getTriggers();
        for (int i = 0; i < triggers.size(); ++i) {
            final String key = (String)triggers.elementAt(i);
            final LadderTree triggerTree = this.getLadderTree(key);
            if (triggerTree != null) {
                if (this.expanded) {
                    triggerTree.getAlgorithmLine().increaseTriggerCount();
                }
                else {
                    triggerTree.getAlgorithmLine().decreaseTriggerCount();
                }
            }
        }
    }
    
    public void setExpandAsOwn(final boolean expandAsOwn) {
        this.expandAsOwn = expandAsOwn;
    }
    
    protected void setPosition(final String positionId) {
        if (positionId != "") {
            final String vis = this.isVisible(positionId) ? "visible" : "hidden";
            final Log l1 = new Log(3, 13, positionId, vis);
            if (this.logger != null) {
                this.logger.addLog(l1);
            }
            this.findParentBreakPoint(positionId);
        }
        this.currentPosition = positionId;
    }
    
    public void setVisible(final boolean visible) {
        this.visible = visible;
    }
    
    public void setXPos(final int xPos) {
        this.pos.x = xPos;
    }
    
    public void setYPos(final int yPos) {
        this.pos.y = yPos;
    }
    
    public void toggleExpand() {
        if (!this.alwaysExpanded && this.isExpandable()) {
            this.setExpand(!this.expanded);
        }
    }
    
    static {
        LadderTree.lImplementor = new LadderTreeImplementor();
    }
}
