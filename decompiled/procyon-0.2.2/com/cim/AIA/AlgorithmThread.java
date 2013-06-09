package com.cim.AIA;

import java.util.*;

public abstract class AlgorithmThread extends Thread implements Finishable, Repaintable, ExitListener, FinishListener, ModeListener
{
    public static boolean displayMode;
    private boolean threadEndLock;
    private boolean hasEnded;
    protected boolean handleQuestions;
    protected Algorithm algorithm;
    protected Vector<Object> questionsToAsk;
    protected Vector<Questionable> questionableItems;
    protected int questionPosition;
    protected boolean canDoStepMode;
    protected boolean canDoBackMode;
    protected boolean canDoRunMode;
    protected boolean canDoPauseMode;
    protected boolean wasInStepMode;
    protected boolean wasInRunMode;
    protected AlgorithmWindow algorithmWindow;
    private Copyable originalData;
    private boolean doneAlgorithm;
    private boolean haveFinished;
    protected boolean stepMode;
    protected boolean runMode;
    private boolean generatePaintEvents;
    private int stepsTaken;
    private int stepsToBeTaken;
    protected boolean continueExec;
    private boolean doingReset;
    private boolean doingBack;
    private int stepsJumped;
    private int backStepsToJump;
    protected int runSleepDuration;
    protected final int sleepDurationWhenFinished = 150;
    protected final int sleepDurationForNextInput = 100;
    protected Vector<FinishListener> finishListeners;
    protected Vector<RepaintListener> repaintListeners;
    protected int questionsCount;
    protected Logger logger;
    protected BreakPoint breakpoint;
    
    public AlgorithmThread(final Copyable data, final AlgorithmWindow algorithmWindow) {
        super();
        this.threadEndLock = false;
        this.hasEnded = false;
        this.handleQuestions = false;
        this.questionsToAsk = new Vector();
        this.questionableItems = new Vector();
        this.questionPosition = 0;
        this.canDoStepMode = true;
        this.canDoBackMode = true;
        this.canDoRunMode = true;
        this.canDoPauseMode = true;
        this.wasInStepMode = false;
        this.wasInRunMode = false;
        this.doneAlgorithm = false;
        this.haveFinished = false;
        this.stepMode = false;
        this.runMode = false;
        this.generatePaintEvents = true;
        this.stepsTaken = 0;
        this.stepsToBeTaken = 0;
        this.continueExec = false;
        this.doingReset = false;
        this.doingBack = false;
        this.stepsJumped = 0;
        this.backStepsToJump = 0;
        this.runSleepDuration = 750;
        this.sleepDurationWhenFinished = 150;
        this.sleepDurationForNextInput = 100;
        this.finishListeners = new Vector();
        this.repaintListeners = new Vector();
        this.questionsCount = 0;
        this.algorithmWindow = algorithmWindow;
        this.originalData = data;
        this.algorithm = this.createAlgorithm(data);
    }
    
    public void addFinishListener(final FinishListener finishListener) {
        this.finishListeners.addElement(finishListener);
    }
    
    public void addQuestionable(final Questionable questionable) {
        this.questionableItems.addElement(questionable);
    }
    
    public void addRepaintListener(final RepaintListener repaintListener) {
        this.repaintListeners.addElement(repaintListener);
    }
    
    protected void askQuestion(final int questionId) {
        if (questionId >= 0 && questionId < this.questionsToAsk.size()) {
            final Question question = (Question)((Question)this.questionsToAsk.elementAt(questionId));
            for (int i = 0; i < this.questionableItems.size(); ++i) {
                final Questionable questionable = (Questionable)this.questionableItems.elementAt(i);
                questionable.addSelectionListener(question);
            }
            question.addFinishListener(this);
            this.questionPosition = this.questionPosition + 1;
            question.ask();
        }
    }
    
    public synchronized void backMode() {
        if (this.canDoBackMode) {
            this.setBackMode();
        }
    }
    
    public int calcHiddenLines(final String pos) {
        int nlines = 0;
        LadderTree tree = null;
        if (this.getAlgorithmWindow() != null && pos != null) {
            tree = this.getAlgorithmWindow().getLadderTree(pos);
        }
        if (tree != null) {
            nlines = ((tree == null) ? 0 : tree.countHiddenLines());
        }
        return nlines;
    }
    
    public synchronized boolean canDoRun() {
        return this.canDoRunMode;
    }
    
    public void cleanUp() {
        this.algorithm = null;
        this.algorithmWindow = null;
        this.originalData = null;
        this.breakpoint = null;
        if (this.questionsToAsk != null) {
            this.questionsToAsk.removeAllElements();
        }
        this.questionsToAsk = null;
        if (this.questionableItems != null) {
            this.questionableItems.removeAllElements();
        }
        this.questionableItems = null;
        if (this.finishListeners != null) {
            this.finishListeners.removeAllElements();
        }
        this.finishListeners = null;
        if (this.repaintListeners != null) {
            this.repaintListeners.removeAllElements();
        }
        this.repaintListeners = null;
    }
    
    public abstract Algorithm createAlgorithm(final Copyable p0);
    
    public synchronized void endThread() {
        if (this.hasEnded) {
            return;
        }
        this.threadEndLock = true;
        this.setReset(false, true, true, false, false);
        while (this.threadEndLock == true) {
            try {
                Thread.sleep(10L);
                continue;
            }
            catch (InterruptedException ex) {
                continue;
            }
            break;
        }
        this.hasEnded = true;
    }
    
    protected void finish() {
        final FinishEvent e = new FinishEvent(this);
        for (int i = 0; i < this.finishListeners.size(); ++i) {
            final FinishListener finishListener = (FinishListener)this.finishListeners.elementAt(i);
            finishListener.processFinishEvent(e);
        }
        if (Logger.DEBUG) {
            System.err.println("Algorithm finished");
        }
        if (this.getLogger() != null) {
            this.getLogger().addLog(new Log(2, 20));
        }
    }
    
    protected Algorithm getAlgorithm() {
        return this.algorithm;
    }
    
    protected AlgorithmWindow getAlgorithmWindow() {
        return this.algorithmWindow;
    }
    
    public BreakPoint getBreakPoint() {
        return this.breakpoint;
    }
    
    public boolean getDisplayMode() {
        return AlgorithmThread.displayMode;
    }
    
    public boolean getHandleQuestions() {
        return this.handleQuestions;
    }
    
    public String getHighlightPos() {
        String pos = this.getPosition();
        LadderTree tree = null;
        if (this.getAlgorithmWindow() != null) {
            tree = this.getAlgorithmWindow().getLadderTree(pos);
        }
        if (tree != null) {
            pos = tree.getWhatHighlighted(pos);
        }
        return pos;
    }
    
    public Logger getLogger() {
        return this.logger;
    }
    
    public String getPosition() {
        return this.algorithmWindow.getPosition();
    }
    
    protected void informRepaintListeners() {
        if (this.generatePaintEvents) {
            final RepaintEvent e = new RepaintEvent(this, this.algorithm);
            for (int i = 0; i < this.repaintListeners.size(); ++i) {
                final RepaintListener repaintListener = (RepaintListener)this.repaintListeners.elementAt(i);
                repaintListener.processRepaintEvent(e);
            }
        }
    }
    
    public void modeNormal(final ModeEvent e) {
        this.setHandleQuestions(false);
    }
    
    public void modeOther(final ModeEvent e) {
    }
    
    public void modeQuiz(final ModeEvent e) {
    }
    
    public void modeSelfTest(final ModeEvent e) {
        this.setHandleQuestions(true);
    }
    
    private void paintAndWait(final int i) {
        if (this.doingReset || this.doingBack) {
            return;
        }
        this.repaint();
        try {
            sleep((long)i);
            if (this.stepMode) {
                this.continueExec = false;
            }
            while (!this.continueExec) {
                if (AlgorithmThread.displayMode) {
                    this.repaint();
                    AlgorithmThread.displayMode = false;
                }
                sleep(100L);
                if (this.stepMode && this.questionPosition == 0) {
                    this.canDoStepMode = true;
                    this.canDoBackMode = true;
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    
    public void paintAndWait(final String key) {
        this.paintAndWait(key, true);
    }
    
    public void paintAndWait(final String key, final boolean isAStep) {
        if (isAStep) {
            this.stepsTaken = this.stepsTaken + 1;
            this.stepsJumped = this.stepsJumped + 1;
        }
        if (this.breakpoint.isKeyBreaked(key) && !this.doingBack && this.generatePaintEvents) {
            this.breakpoint.processBreakPoint(key);
        }
        if (this.generatePaintEvents && !this.doingBack) {
            this.algorithmWindow.setPosition(key);
        }
        if (this.breakpoint.hasBreaked()) {
            this.canDoStepMode = false;
            this.canDoBackMode = false;
            this.continueExec = true;
            this.stepMode = true;
            this.runMode = false;
            this.breakpoint.breakPointReceived();
            if (isAStep) {
                this.backStepsToJump = this.stepsJumped;
                this.stepsJumped = 0;
            }
            this.paintAndWait(0);
            return;
        }
        final boolean paintAsVisible = this.algorithmWindow.isVisible(key) && (!this.algorithmWindow.isExpandable(key) || (this.algorithmWindow.isExpanded(key) && this.algorithmWindow.isExpandable(key)));
        final boolean doQuestions = this.algorithm.hasQuestions() && this.handleQuestions;
        if (this.generatePaintEvents && (paintAsVisible || doQuestions || this.algorithmWindow.shouldRepaint(key))) {
            if (isAStep) {
                this.backStepsToJump = this.stepsJumped;
                this.stepsJumped = 0;
            }
            if (this.stepMode) {
                this.paintAndWait(0);
            }
            else if (this.runMode) {
                final String pos = this.getHighlightPos();
                final int nlines = this.calcHiddenLines(pos);
                final Log l1 = new StepLog(2, 3, pos, nlines);
                if (this.getLogger() != null) {
                    this.getLogger().addLog(l1);
                }
                this.paintAndWait(this.runSleepDuration);
            }
        }
        if (this.doingBack && this.stepsTaken >= this.stepsToBeTaken) {
            this.stepsToBeTaken = 0;
            this.generatePaintEvents = true;
            this.algorithmWindow.setPosition(key);
            if (this.breakpoint == null || this.breakpoint.hasBreaked()) {}
            this.doingBack = false;
            this.stepMode = true;
            this.runMode = false;
            this.continueExec = false;
            this.algorithm.removeAllAnimationRequests();
            this.algorithm.removeAllQuestions();
            this.canDoRunMode = true;
            this.canDoBackMode = true;
            this.canDoStepMode = true;
            this.canDoPauseMode = true;
            this.paintAndWait(0);
        }
    }
    
    public synchronized void pauseMode() {
        if (this.canDoPauseMode) {
            this.setPauseMode();
        }
    }
    
    public void processExitEvent(final ExitEvent exitEvent) {
        if (this.questionsToAsk != null) {
            for (int i = 0; i < this.questionsToAsk.size(); ++i) {
                ((Question)((Question)this.questionsToAsk.elementAt(i))).processExitEvent(exitEvent);
            }
        }
        Question.closeDialog();
        this.algorithm.setEnabled(false);
        this.cleanUp();
    }
    
    public void processFinishEvent(final FinishEvent e) {
        final SelectionListener selectionListener = (SelectionListener)((SelectionListener)e.getSource());
        for (int i = 0; i < this.questionableItems.size(); ++i) {
            final Questionable questionable = (Questionable)this.questionableItems.elementAt(i);
            questionable.removeSelectionListener(selectionListener);
        }
        if (this.questionPosition >= this.questionsToAsk.size()) {
            this.canDoRunMode = true;
            this.canDoBackMode = true;
            this.canDoStepMode = true;
            this.canDoPauseMode = true;
            if (!this.doingReset && !this.doingBack) {
                if (this.wasInStepMode) {
                    this.setStepMode();
                }
                else if (this.wasInRunMode) {
                    this.setRunMode();
                }
            }
        }
        else {
            this.askQuestion(this.questionPosition);
        }
    }
    
    protected void removeAllQuestions() {
        for (int i = 0; i < this.questionableItems.size(); ++i) {
            final Questionable questionable = (Questionable)this.questionableItems.elementAt(i);
            int v = 0;
            while (v < this.questionsToAsk.size()) {
                final SelectionListener selectionListener = (SelectionListener)((SelectionListener)this.questionsToAsk.elementAt(v));
                questionable.removeSelectionListener(selectionListener);
                ++v;
            }
        }
        final ExitEvent exitEvent = new ExitEvent(this);
        for (int j = 0; j < this.questionsToAsk.size(); ++j) {
            ((Question)((Question)this.questionsToAsk.elementAt(j))).processExitEvent(exitEvent);
        }
        this.questionsToAsk = new Vector();
    }
    
    public void removeFinishListener(final FinishListener finishListener) {
        this.finishListeners.removeElement(finishListener);
    }
    
    public void removeQuestionable(final Questionable questionable) {
        this.questionableItems.removeElement(questionable);
    }
    
    public void removeRepaintListener(final RepaintListener repaintListener) {
        this.repaintListeners.removeElement(repaintListener);
    }
    
    public void repaint() {
        if (this.generatePaintEvents) {
            this.informRepaintListeners();
            this.algorithm.removeAllAnimationRequests();
            this.questionPosition = 0;
            this.questionsToAsk = new Vector();
            final Vector<Object> questions = this.algorithm.generateQuestions();
            this.algorithm.removeAllQuestions();
            if (!this.handleQuestions) {
                return;
            }
            if (questions != null) {
                this.questionsToAsk = questions;
                if (this.questionsToAsk.size() >= 1) {
                    this.wasInStepMode = this.stepMode;
                    this.wasInRunMode = this.runMode;
                    this.canDoRunMode = false;
                    this.canDoBackMode = true;
                    this.canDoStepMode = false;
                    this.canDoPauseMode = true;
                    this.setPauseMode();
                    this.askQuestion(this.questionPosition);
                }
            }
        }
    }
    
    public synchronized void reset() {
        this.setReset(true, true, true, false, false);
    }
    
    public void run() {
        do {
            if ((this.stepMode || this.runMode) && !this.doneAlgorithm) {
                this.haveFinished = false;
                this.algorithm.removeAllAnimationRequests();
                this.algorithm.removeAllQuestions();
                if (this.threadEndLock == true) {
                    break;
                }
                this.algorithm.runAlgorithm(true);
                this.doneAlgorithm = true;
                if (!this.doingReset && !this.doingBack) {
                    this.canDoBackMode = true;
                }
                if (this.stepMode || this.runMode) {
                    this.repaint();
                    this.finish();
                }
            }
            this.haveFinished = true;
            try {
                sleep(150L);
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        while (this.threadEndLock != true);
        this.threadEndLock = false;
    }
    
    public synchronized void runMode() {
        if (this.canDoRunMode) {
            this.setRunMode();
        }
    }
    
    private synchronized void setBackMode() {
        this.canDoBackMode = false;
        this.canDoStepMode = false;
        this.generatePaintEvents = false;
        final int steps = this.stepsTaken;
        final int jumpBack = this.backStepsToJump;
        this.setReset(false, true, false, true, false);
        this.generatePaintEvents = true;
        this.doingBack = true;
        this.stepsToBeTaken = steps - jumpBack;
        this.setRunMode();
        this.algorithmWindow.processStopRun();
    }
    
    public void setBreakPoint(final BreakPoint breakpoint) {
        this.breakpoint = breakpoint;
    }
    
    public synchronized void setData(final Copyable copyable) {
        this.originalData = copyable;
        this.setReset(true, true, true, false, false);
    }
    
    public synchronized void setData(final Copyable copyable, final boolean reInitialiseData, final boolean clearAlgorithmState, final boolean storeAlgorithmState, final boolean forceAlgorithmStore) {
        this.originalData = copyable;
        this.setReset(true, reInitialiseData, clearAlgorithmState, storeAlgorithmState, forceAlgorithmStore);
    }
    
    public void setDisplayMode(final boolean mode) {
        AlgorithmThread.displayMode = mode;
    }
    
    public synchronized void setHandleQuestions(final boolean mode) {
        this.handleQuestions = mode;
        if (!mode) {
            this.removeAllQuestions();
        }
    }
    
    public void setLogger(final Logger logger) {
        this.logger = logger;
    }
    
    protected synchronized void setPauseMode() {
        this.continueExec = false;
        this.algorithmWindow.processStopRun();
    }
    
    protected synchronized void setReset(final boolean paintWhenDone, final boolean reInitialiseAlgorithm, final boolean clearAlgorithmState, final boolean storeAlgorithmState, final boolean forceAlgorithmStore) {
        this.doingReset = true;
        this.algorithm.setEnabled(false);
        this.stepMode = false;
        this.runMode = false;
        this.continueExec = true;
        this.generatePaintEvents = false;
        this.algorithmWindow.processStopRun();
        this.removeAllQuestions();
        this.questionsToAsk = new Vector();
        this.questionPosition = 0;
        Label_0078:
        {
            if (this.algorithm.haveStarted()) {
                break Label_0078;
            }
            this.algorithm.runAlgorithm(false);
            try {
                while (!this.haveFinished) {
                    this.continueExec = true;
                    sleep(100L);
                }
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        if (clearAlgorithmState) {
            this.algorithm.clearState();
        }
        if (storeAlgorithmState) {
            this.algorithm.storeState(forceAlgorithmStore);
        }
        final Object dataCopy = this.originalData.copy();
        if (reInitialiseAlgorithm) {
            this.algorithm.reInitialise(dataCopy);
        }
        else {
            this.algorithm.changeData(dataCopy);
        }
        this.algorithm.setEnabled(true);
        this.stepsTaken = 0;
        this.algorithm.removeAllAnimationRequests();
        this.algorithm.removeAllQuestions();
        this.generatePaintEvents = true;
        if (paintWhenDone) {
            this.canDoRunMode = true;
            this.canDoBackMode = true;
            this.canDoStepMode = true;
            this.canDoPauseMode = true;
            this.repaint();
            this.algorithmWindow.setPosition("");
        }
        this.doneAlgorithm = false;
        this.doingReset = false;
    }
    
    private synchronized void setRunMode() {
        this.continueExec = true;
        this.stepMode = false;
        this.runMode = true;
        this.algorithmWindow.processRun();
    }
    
    public synchronized void setRunSleepDuration(final int duration) {
        if (duration > 0) {
            this.runSleepDuration = duration;
        }
        else {
            this.runSleepDuration = 1;
        }
    }
    
    private synchronized void setStepMode() {
        this.canDoStepMode = false;
        this.canDoBackMode = false;
        this.continueExec = true;
        this.stepMode = true;
        this.runMode = false;
        this.algorithmWindow.processStopRun();
    }
    
    public synchronized void stepMode() {
        if (this.canDoStepMode) {
            this.setStepMode();
        }
    }
    
    static {
        AlgorithmThread.displayMode = false;
    }
}
