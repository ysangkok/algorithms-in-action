package com.cim.AIA;

import java.util.*;

class BreakPoint
{
    protected AlgorithmThread algorithmThread;
    protected AlgorithmWindow algorithmWindow;
    protected AnimationWindow animationWindow;
    protected Vector<BreakPointLine> breakPointLines;
    public boolean inMutex;
    private boolean inAdd;
    private int saveIndex;
    private boolean hasBreaked;
    public boolean enabled;
    
    public BreakPoint() {
        super();
        this.breakPointLines = new Vector();
        this.enabled = false;
    }
    
    public BreakPoint(final AlgorithmThread thread, final AlgorithmWindow algWin, final AnimationWindow animWin) {
        super();
        this.breakPointLines = new Vector();
        this.enabled = false;
        this.initialise(thread, algWin, animWin);
    }
    
    public void add() {
        if (!this.enabled) {
            return;
        }
        this.animationWindow.setEnabled(false);
        this.algorithmWindow.processStartMutex();
        this.inMutex = true;
        this.inAdd = true;
    }
    
    private boolean add(final AlgorithmLine algLine) {
        if (!this.enabled) {
            return false;
        }
        if (this.find(algLine) != -1) {
            return false;
        }
        this.breakPointLines.addElement(new BreakPointLine(algLine));
        return true;
    }
    
    public void breakPointReceived() {
        if (!this.enabled) {
            return;
        }
        this.animationWindow.informControlListeners(new ControlEvent(this.algorithmThread, 123125));
        this.algorithmWindow.processStopRun();
    }
    
    public void cleanUp() {
        this.algorithmThread = null;
        this.algorithmWindow = null;
        this.animationWindow = null;
        if (this.breakPointLines != null) {
            this.breakPointLines.removeAllElements();
        }
        this.breakPointLines = null;
    }
    
    public void del() {
        if (!this.enabled) {
            return;
        }
        this.animationWindow.setEnabled(false);
        this.algorithmWindow.processStartMutex();
        this.inMutex = true;
        this.inAdd = false;
    }
    
    private boolean del(final AlgorithmLine algLine) {
        if (!this.enabled) {
            return false;
        }
        final int temp = this.find(algLine);
        if (temp == -1) {
            return false;
        }
        this.breakPointLines.removeElementAt(temp);
        return true;
    }
    
    private int find(final AlgorithmLine alg) {
        if (!this.enabled) {
            return -1;
        }
        for (int i = 0; i < this.breakPointLines.size(); ++i) {
            if (((BreakPointLine)this.breakPointLines.elementAt(i)).getAlgorithmLine() == alg) {
                return i;
            }
        }
        return -1;
    }
    
    public BreakPointLine getLastSearched() {
        if (!this.enabled) {
            return null;
        }
        return (BreakPointLine)this.breakPointLines.elementAt(this.saveIndex);
    }
    
    public boolean hasBreaked() {
        if (!this.enabled) {
            return false;
        }
        final boolean temp = this.hasBreaked;
        this.hasBreaked = false;
        return temp;
    }
    
    public void highlightBreakPoints() {
        if (!this.enabled) {
            return;
        }
        for (int i = 0; this.breakPointLines != null && i < this.breakPointLines.size(); ++i) {
            final AlgorithmLine temp = ((BreakPointLine)this.breakPointLines.elementAt(i)).getAlgorithmLine();
            temp.setHighlightLevel(3);
        }
    }
    
    public void initialise(final AlgorithmThread thread, final AlgorithmWindow algWin, final AnimationWindow animWin) {
        this.algorithmThread = thread;
        this.algorithmWindow = algWin;
        this.animationWindow = animWin;
        this.reInit();
    }
    
    public boolean isKeyBreaked(final String key) {
        if (!this.enabled) {
            return false;
        }
        for (int i = 0; this.breakPointLines != null && i < this.breakPointLines.size(); ++i) {
            final AlgorithmLine temp = ((BreakPointLine)this.breakPointLines.elementAt(i)).getAlgorithmLine();
            if (temp.getKey().equals(key)) {
                this.saveIndex = i;
                return true;
            }
        }
        return false;
    }
    
    public void processBreakPoint(final String key) {
        if (!this.enabled) {
            return;
        }
        this.hasBreaked = true;
    }
    
    public void processMutex(final AlgorithmLine algLine) {
        if (!this.enabled) {
            return;
        }
        if (algLine == null) {
            this.release();
            return;
        }
        if (this.inAdd) {
            if (this.add(algLine)) {
                final String pos = algLine.getKey();
                int nlines = 0;
                final LadderTree laddertree = this.algorithmThread.getAlgorithmWindow().getLadderTree(pos);
                if (laddertree != null) {
                    nlines = laddertree.countHiddenLines();
                }
                final Log l1 = new StepLog(5, 7, pos, nlines, algLine.getLabel());
                if (this.algorithmThread.getLogger() != null) {
                    this.algorithmThread.getLogger().addLog(l1);
                }
                if (Logger.DEBUG) {
                    System.err.println("pos= " + pos);
                    System.err.println("line= " + algLine.getLabel());
                    System.err.println("algorithmThread.getAlgorithmWindow()= " + this.algorithmThread.getAlgorithmWindow());
                    System.err.println(".getLadderTree(pos)= " + this.algorithmThread.getAlgorithmWindow().getLadderTree(pos));
                    for (int i = 0; i < this.breakPointLines.size(); ++i) {
                        System.err.println("breakpoint " + i + ": " + ((BreakPointLine)this.breakPointLines.elementAt(i)).getKey());
                    }
                }
            }
        }
        else if (this.del(algLine)) {
            final Log l = new Log(5, 8, algLine.getKey(), algLine.getLabel());
            if (this.algorithmThread.getLogger() != null) {
                this.algorithmThread.getLogger().addLog(l);
            }
        }
        this.release();
    }
    
    public void reInit() {
        this.breakPointLines = new Vector();
        this.inMutex = false;
        this.inAdd = false;
        this.saveIndex = -1;
        this.hasBreaked = false;
    }
    
    private void release() {
        if (!this.enabled) {
            return;
        }
        this.animationWindow.setEnabled(true);
        this.algorithmWindow.processEndMutex();
        this.inMutex = false;
    }
}
