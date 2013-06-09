package com.cim.AIA;

abstract public class AlgorithmThread extends Thread implements com.cim.AIA.Finishable, com.cim.AIA.Repaintable, com.cim.AIA.ExitListener, com.cim.AIA.FinishListener, com.cim.AIA.ModeListener {
    public static boolean displayMode;
    private boolean threadEndLock;
    private boolean hasEnded;
    protected boolean handleQuestions;
    protected com.cim.AIA.Algorithm algorithm;
    protected java.util.Vector questionsToAsk;
    protected java.util.Vector questionableItems;
    protected int questionPosition;
    protected boolean canDoStepMode;
    protected boolean canDoBackMode;
    protected boolean canDoRunMode;
    protected boolean canDoPauseMode;
    protected boolean wasInStepMode;
    protected boolean wasInRunMode;
    protected com.cim.AIA.AlgorithmWindow algorithmWindow;
    private com.cim.AIA.Copyable originalData;
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
    final protected int sleepDurationWhenFinished;
    final protected int sleepDurationForNextInput;
    protected java.util.Vector finishListeners;
    protected java.util.Vector repaintListeners;
    protected int questionsCount;
    protected com.cim.AIA.Logger logger;
    protected com.cim.AIA.BreakPoint breakpoint;
    
    public AlgorithmThread(com.cim.AIA.Copyable a, com.cim.AIA.AlgorithmWindow a0)
    {
        super();
        Object a1 = a;
        this.threadEndLock = false;
        this.hasEnded = false;
        this.handleQuestions = false;
        java.util.Vector a2 = new java.util.Vector();
        this.questionsToAsk = a2;
        java.util.Vector a3 = new java.util.Vector();
        this.questionableItems = a3;
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
        java.util.Vector a4 = new java.util.Vector();
        this.finishListeners = a4;
        java.util.Vector a5 = new java.util.Vector();
        this.repaintListeners = a5;
        this.questionsCount = 0;
        this.algorithmWindow = a0;
        this.originalData = (com.cim.AIA.Copyable)a1;
        com.cim.AIA.Algorithm a6 = this.createAlgorithm((com.cim.AIA.Copyable)a1);
        this.algorithm = a6;
    }
    
    public void addFinishListener(com.cim.AIA.FinishListener a)
    {
        java.util.Vector a0 = this.finishListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addQuestionable(com.cim.AIA.Questionable a)
    {
        java.util.Vector a0 = this.questionableItems;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    public void addRepaintListener(com.cim.AIA.RepaintListener a)
    {
        java.util.Vector a0 = this.repaintListeners;
        Object a1 = a;
        a0.addElement(a1);
    }
    
    protected void askQuestion(int i)
    {
        label0: {
            if(i < 0)
            {
                break label0;
            }
            java.util.Vector a = this.questionsToAsk;
            int i0 = a.size();
            if(i >= i0)
            {
                break label0;
            }
            java.util.Vector a0 = this.questionsToAsk;
            Object a1 = a0.elementAt(i);
            com.cim.AIA.Question dummy = (com.cim.AIA.Question)a1;
            com.cim.AIA.Question a2 = (com.cim.AIA.Question)a1;
            int i1 = 0;
            while(true)
            {
                java.util.Vector a3 = this.questionableItems;
                int i2 = a3.size();
                if(i1 >= i2)
                {
                    break;
                }
                else
                {
                    java.util.Vector a4 = this.questionableItems;
                    Object a5 = a4.elementAt(i1);
                    com.cim.AIA.Questionable dummy0 = (com.cim.AIA.Questionable)a5;
                    ((com.cim.AIA.Questionable)a5).addSelectionListener((com.cim.AIA.SelectionListener)a2);
                    int i3 = i1 + 1;
                    i1 = i3;
                }
            }
            a2.addFinishListener((com.cim.AIA.FinishListener)this);
            int i4 = this.questionPosition;
            int i5 = i4 + 1;
            this.questionPosition = i5;
            a2.ask();
        }
    }
    
    public synchronized void backMode()
    {
        int i = this.canDoBackMode?1:0;
        if(i != 0)
        {
            this.setBackMode();
        }
    }
    
    public int calcHiddenLines(String s)
    {
        com.cim.AIA.LadderTree a = null;
        int i = 0;
        com.cim.AIA.AlgorithmWindow a0 = this.getAlgorithmWindow();
        label0: {
            com.cim.AIA.LadderTree a1 = null;
            if(a0 == null)
            {
                a = a1;
                break label0;
            }
            com.cim.AIA.LadderTree a2 = null;
            if(s == null)
            {
                a = a2;
            }
            else
            {
                com.cim.AIA.AlgorithmWindow a3 = this.getAlgorithmWindow();
                com.cim.AIA.LadderTree a4 = a3.getLadderTree(s);
                a = a4;
            }
        }
        if(a == null)
        {
            i = 0;
        }
        else
        {
            int i0 = a.countHiddenLines();
            i = i0;
        }
        return i;
    }
    
    public synchronized boolean canDoRun()
    {
        int i = this.canDoRunMode?1:0;
        return i != 0;
    }
    
    public void cleanUp()
    {
        this.algorithm = null;
        this.algorithmWindow = null;
        this.originalData = null;
        this.breakpoint = null;
        java.util.Vector a = this.questionsToAsk;
        if(a != null)
        {
            java.util.Vector a0 = this.questionsToAsk;
            a0.removeAllElements();
        }
        this.questionsToAsk = null;
        java.util.Vector a1 = this.questionableItems;
        if(a1 != null)
        {
            java.util.Vector a2 = this.questionableItems;
            a2.removeAllElements();
        }
        this.questionableItems = null;
        java.util.Vector a3 = this.finishListeners;
        if(a3 != null)
        {
            java.util.Vector a4 = this.finishListeners;
            a4.removeAllElements();
        }
        this.finishListeners = null;
        java.util.Vector a5 = this.repaintListeners;
        if(a5 != null)
        {
            java.util.Vector a6 = this.repaintListeners;
            a6.removeAllElements();
        }
        this.repaintListeners = null;
    }
    
    abstract public com.cim.AIA.Algorithm createAlgorithm(com.cim.AIA.Copyable arg);
    
    
    public synchronized void endThread()
    {
        int i = this.hasEnded?1:0;
        label1: {
            label0: {
                if(i == 0)
                {
                    break label0;
                }
                break label1;
            }
            this.threadEndLock = true;
            this.setReset(false, true, true, false, false);
            while(true)
            {
                int i0 = this.threadEndLock?1:0;
                if(i0 != 1)
                {
                    break;
                }
                try
                {
                    Thread.sleep(10L);
                }
                catch(InterruptedException ignoredException)
                {
                }
            }
            this.hasEnded = true;
        }
    }
    
    protected void finish()
    {
        com.cim.AIA.FinishEvent a = new com.cim.AIA.FinishEvent((Object)this);
        int i = 0;
        while(true)
        {
            java.util.Vector a0 = this.finishListeners;
            int i0 = a0.size();
            if(i >= i0)
            {
                break;
            }
            else
            {
                java.util.Vector a1 = this.finishListeners;
                Object a2 = a1.elementAt(i);
                com.cim.AIA.FinishListener dummy = (com.cim.AIA.FinishListener)a2;
                ((com.cim.AIA.FinishListener)a2).processFinishEvent(a);
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = com.cim.AIA.Logger.DEBUG?1:0;
        if(i2 != 0)
        {
            java.io.PrintStream a3 = System.err;
            a3.println("Algorithm finished");
        }
        com.cim.AIA.Logger a4 = this.getLogger();
        if(a4 != null)
        {
            com.cim.AIA.Logger a5 = this.getLogger();
            com.cim.AIA.Log a6 = new com.cim.AIA.Log((byte)2, (byte)20);
            a5.addLog(a6);
        }
    }
    
    protected com.cim.AIA.Algorithm getAlgorithm()
    {
        com.cim.AIA.Algorithm a = this.algorithm;
        return a;
    }
    
    protected com.cim.AIA.AlgorithmWindow getAlgorithmWindow()
    {
        com.cim.AIA.AlgorithmWindow a = this.algorithmWindow;
        return a;
    }
    
    public com.cim.AIA.BreakPoint getBreakPoint()
    {
        com.cim.AIA.BreakPoint a = this.breakpoint;
        return a;
    }
    
    public boolean getDisplayMode()
    {
        int i = com.cim.AIA.AlgorithmThread.displayMode?1:0;
        return i != 0;
    }
    
    public boolean getHandleQuestions()
    {
        int i = this.handleQuestions?1:0;
        return i != 0;
    }
    
    public String getHighlightPos()
    {
        com.cim.AIA.LadderTree a = null;
        String s = null;
        String s0 = this.getPosition();
        com.cim.AIA.AlgorithmWindow a0 = this.getAlgorithmWindow();
        com.cim.AIA.LadderTree a1 = null;
        if(a0 == null)
        {
            a = a1;
        }
        else
        {
            com.cim.AIA.AlgorithmWindow a2 = this.getAlgorithmWindow();
            com.cim.AIA.LadderTree a3 = a2.getLadderTree(s0);
            a = a3;
        }
        if(a == null)
        {
            s = s0;
        }
        else
        {
            String s1 = a.getWhatHighlighted(s0);
            s = s1;
        }
        return s;
    }
    
    public com.cim.AIA.Logger getLogger()
    {
        com.cim.AIA.Logger a = this.logger;
        return a;
    }
    
    public String getPosition()
    {
        com.cim.AIA.AlgorithmWindow a = this.algorithmWindow;
        String s = a.getPosition();
        return s;
    }
    
    protected void informRepaintListeners()
    {
        int i = this.generatePaintEvents?1:0;
        label0: {
            if(i == 0)
            {
                break label0;
            }
            com.cim.AIA.Algorithm a = this.algorithm;
            com.cim.AIA.RepaintEvent a0 = new com.cim.AIA.RepaintEvent((Object)this, (Object)a);
            int i0 = 0;
            while(true)
            {
                java.util.Vector a1 = this.repaintListeners;
                int i1 = a1.size();
                if(i0 >= i1)
                {
                    break;
                }
                else
                {
                    java.util.Vector a2 = this.repaintListeners;
                    Object a3 = a2.elementAt(i0);
                    com.cim.AIA.RepaintListener dummy = (com.cim.AIA.RepaintListener)a3;
                    ((com.cim.AIA.RepaintListener)a3).processRepaintEvent(a0);
                    int i2 = i0 + 1;
                    i0 = i2;
                }
            }
        }
    }
    
    public void modeNormal(com.cim.AIA.ModeEvent a)
    {
        this.setHandleQuestions(false);
    }
    
    public void modeOther(com.cim.AIA.ModeEvent a)
    {
    }
    
    public void modeQuiz(com.cim.AIA.ModeEvent a)
    {
    }
    
    public void modeSelfTest(com.cim.AIA.ModeEvent a)
    {
        this.setHandleQuestions(true);
    }
    
    private void paintAndWait(int i)
    {
        int i0 = this.doingReset?1:0;
        label2: {
            label1: {
                label0: {
                    if(i0 != 0)
                    {
                        break label0;
                    }
                    int i1 = this.doingBack?1:0;
                    if(i1 == 0)
                    {
                        break label1;
                    }
                }
                break label2;
            }
            this.repaint();
            try
            {
                long j = (long)i;
                com.cim.AIA.AlgorithmThread.sleep(j);
                int i2 = this.stepMode?1:0;
                if(i2 != 0)
                {
                    this.continueExec = false;
                }
                while(true)
                {
                    int i3 = this.continueExec?1:0;
                    if(i3 != 0)
                    {
                        break;
                    }
                    int i4 = com.cim.AIA.AlgorithmThread.displayMode?1:0;
                    if(i4 != 0)
                    {
                        this.repaint();
                        com.cim.AIA.AlgorithmThread.displayMode = false;
                    }
                    com.cim.AIA.AlgorithmThread.sleep(100L);
                    int i5 = this.stepMode?1:0;
                    if(i5 == 0)
                    {
                        continue;
                    }
                    int i6 = this.questionPosition;
                    if(i6 != 0)
                    {
                    }
                    else
                    {
                        this.canDoStepMode = true;
                        this.canDoBackMode = true;
                    }
                }
            }
            catch(InterruptedException a)
            {
                java.io.PrintStream a0 = System.out;
                a0.println((Object)a);
            }
        }
    }
    
    public void paintAndWait(String s)
    {
        this.paintAndWait(s, true);
    }
    
    public void paintAndWait(String s, boolean b)
    {
        int i = 0;
        int i0 = b?1:0;
        int i1 = b?1:0;
        if(!b)
        {
            i = i1;
        }
        else
        {
            int i2 = this.stepsTaken;
            int i3 = i2 + 1;
            this.stepsTaken = i3;
            int i4 = this.stepsJumped;
            int i5 = i4 + 1;
            this.stepsJumped = i5;
            i = i0;
        }
        com.cim.AIA.BreakPoint a = this.breakpoint;
        int i6 = a.isKeyBreaked(s)?1:0;
        label0: {
            if(i6 == 0)
            {
                break label0;
            }
            int i7 = this.doingBack?1:0;
            if(i7 != 0)
            {
                break label0;
            }
            int i8 = this.generatePaintEvents?1:0;
            if(i8 != 0)
            {
                com.cim.AIA.BreakPoint a0 = this.breakpoint;
                a0.processBreakPoint(s);
            }
        }
        int i9 = this.generatePaintEvents?1:0;
        label1: {
            if(i9 == 0)
            {
                break label1;
            }
            int i10 = this.doingBack?1:0;
            if(i10 == 0)
            {
                com.cim.AIA.AlgorithmWindow a1 = this.algorithmWindow;
                a1.setPosition(s);
            }
        }
        com.cim.AIA.BreakPoint a2 = this.breakpoint;
        int i11 = a2.hasBreaked()?1:0;
        label3: {
            int i12 = 0;
            int i13 = 0;
            label2: {
                if(i11 == 0)
                {
                    break label2;
                }
                this.canDoStepMode = false;
                this.canDoBackMode = false;
                this.continueExec = true;
                this.stepMode = true;
                this.runMode = false;
                com.cim.AIA.BreakPoint a3 = this.breakpoint;
                a3.breakPointReceived();
                if(i != 0)
                {
                    int i14 = this.stepsJumped;
                    this.backStepsToJump = i14;
                    this.stepsJumped = 0;
                }
                this.paintAndWait(0);
                break label3;
            }
            com.cim.AIA.AlgorithmWindow a4 = this.algorithmWindow;
            int i15 = a4.isVisible(s)?1:0;
            label6: {
                label5: {
                    label4: {
                        if(i15 == 0)
                        {
                            break label4;
                        }
                        com.cim.AIA.AlgorithmWindow a5 = this.algorithmWindow;
                        int i16 = a5.isExpandable(s)?1:0;
                        if(i16 == 0)
                        {
                            break label5;
                        }
                        com.cim.AIA.AlgorithmWindow a6 = this.algorithmWindow;
                        int i17 = a6.isExpanded(s)?1:0;
                        if(i17 == 0)
                        {
                            break label4;
                        }
                        com.cim.AIA.AlgorithmWindow a7 = this.algorithmWindow;
                        int i18 = a7.isExpandable(s)?1:0;
                        if(i18 != 0)
                        {
                            break label5;
                        }
                    }
                    i12 = 0;
                    break label6;
                }
                i12 = 1;
            }
            com.cim.AIA.Algorithm a8 = this.algorithm;
            int i19 = a8.hasQuestions()?1:0;
            label9: {
                label8: {
                    label7: {
                        if(i19 == 0)
                        {
                            break label7;
                        }
                        int i20 = this.handleQuestions?1:0;
                        if(i20 != 0)
                        {
                            break label8;
                        }
                    }
                    i13 = 0;
                    break label9;
                }
                i13 = 1;
            }
            int i21 = this.generatePaintEvents?1:0;
            label10: {
                if(i21 == 0)
                {
                    break label10;
                }
                label11: {
                    if(i12 != 0)
                    {
                        break label11;
                    }
                    if(i13 != 0)
                    {
                        break label11;
                    }
                    com.cim.AIA.AlgorithmWindow a9 = this.algorithmWindow;
                    int i22 = a9.shouldRepaint(s)?1:0;
                    if(i22 == 0)
                    {
                        break label10;
                    }
                }
                if(i != 0)
                {
                    int i23 = this.stepsJumped;
                    this.backStepsToJump = i23;
                    this.stepsJumped = 0;
                }
                int i24 = this.stepMode?1:0;
                label12: {
                    if(i24 == 0)
                    {
                        break label12;
                    }
                    this.paintAndWait(0);
                    break label10;
                }
                int i25 = this.runMode?1:0;
                if(i25 == 0)
                {
                    break label10;
                }
                String s0 = this.getHighlightPos();
                int i26 = this.calcHiddenLines(s0);
                com.cim.AIA.StepLog a10 = new com.cim.AIA.StepLog((byte)2, (byte)3, s0, i26);
                com.cim.AIA.Logger a11 = this.getLogger();
                if(a11 != null)
                {
                    com.cim.AIA.Logger a12 = this.getLogger();
                    a12.addLog((com.cim.AIA.Log)a10);
                }
                int i27 = this.runSleepDuration;
                this.paintAndWait(i27);
            }
            int i28 = this.doingBack?1:0;
            label13: {
                if(i28 == 0)
                {
                    break label13;
                }
                int i29 = this.stepsTaken;
                int i30 = this.stepsToBeTaken;
                if(i29 < i30)
                {
                    break label13;
                }
                this.stepsToBeTaken = 0;
                this.generatePaintEvents = true;
                com.cim.AIA.AlgorithmWindow a13 = this.algorithmWindow;
                a13.setPosition(s);
                com.cim.AIA.BreakPoint a14 = this.breakpoint;
                if(a14 != null)
                {
                    com.cim.AIA.BreakPoint a15 = this.breakpoint;
                    int i31 = a15.hasBreaked()?1:0;
                }
                this.doingBack = false;
                this.stepMode = true;
                this.runMode = false;
                this.continueExec = false;
                com.cim.AIA.Algorithm a16 = this.algorithm;
                a16.removeAllAnimationRequests();
                com.cim.AIA.Algorithm a17 = this.algorithm;
                a17.removeAllQuestions();
                this.canDoRunMode = true;
                this.canDoBackMode = true;
                this.canDoStepMode = true;
                this.canDoPauseMode = true;
                this.paintAndWait(0);
            }
        }
    }
    
    public synchronized void pauseMode()
    {
        int i = this.canDoPauseMode?1:0;
        if(i != 0)
        {
            this.setPauseMode();
        }
    }
    
    public void processExitEvent(com.cim.AIA.ExitEvent a)
    {
        java.util.Vector a0 = this.questionsToAsk;
        label0: {
            if(a0 == null)
            {
                break label0;
            }
            int i = 0;
            while(true)
            {
                java.util.Vector a1 = this.questionsToAsk;
                int i0 = a1.size();
                if(i >= i0)
                {
                    break;
                }
                else
                {
                    java.util.Vector a2 = this.questionsToAsk;
                    Object a3 = a2.elementAt(i);
                    com.cim.AIA.Question dummy = (com.cim.AIA.Question)a3;
                    com.cim.AIA.Question a4 = (com.cim.AIA.Question)a3;
                    a4.processExitEvent(a);
                    int i1 = i + 1;
                    i = i1;
                }
            }
        }
        com.cim.AIA.Question.closeDialog();
        com.cim.AIA.Algorithm a5 = this.algorithm;
        a5.setEnabled(false);
        this.cleanUp();
    }
    
    public void processFinishEvent(com.cim.AIA.FinishEvent a)
    {
        Object a0 = a.getSource();
        com.cim.AIA.SelectionListener dummy = (com.cim.AIA.SelectionListener)a0;
        com.cim.AIA.SelectionListener dummy0 = (com.cim.AIA.SelectionListener)a0;
        int i = 0;
        while(true)
        {
            java.util.Vector a1 = this.questionableItems;
            int i0 = a1.size();
            if(i >= i0)
            {
                break;
            }
            else
            {
                java.util.Vector a2 = this.questionableItems;
                Object a3 = a2.elementAt(i);
                com.cim.AIA.Questionable dummy1 = (com.cim.AIA.Questionable)a3;
                ((com.cim.AIA.Questionable)a3).removeSelectionListener((com.cim.AIA.SelectionListener)a0);
                int i1 = i + 1;
                i = i1;
            }
        }
        int i2 = this.questionPosition;
        java.util.Vector a4 = this.questionsToAsk;
        int i3 = a4.size();
        label1: {
            label0: {
                if(i2 >= i3)
                {
                    break label0;
                }
                int i4 = this.questionPosition;
                this.askQuestion(i4);
                break label1;
            }
            this.canDoRunMode = true;
            this.canDoBackMode = true;
            this.canDoStepMode = true;
            this.canDoPauseMode = true;
            int i5 = this.doingReset?1:0;
            if(i5 != 0)
            {
                break label1;
            }
            int i6 = this.doingBack?1:0;
            if(i6 != 0)
            {
                break label1;
            }
            int i7 = this.wasInStepMode?1:0;
            label2: {
                if(i7 == 0)
                {
                    break label2;
                }
                this.setStepMode();
                break label1;
            }
            int i8 = this.wasInRunMode?1:0;
            if(i8 != 0)
            {
                this.setRunMode();
            }
        }
    }
    
    protected void removeAllQuestions()
    {
        int i = 0;
        while(true)
        {
            java.util.Vector a = this.questionableItems;
            int i0 = a.size();
            if(i >= i0)
            {
                break;
            }
            java.util.Vector a0 = this.questionableItems;
            Object a1 = a0.elementAt(i);
            com.cim.AIA.Questionable dummy = (com.cim.AIA.Questionable)a1;
            int i1 = 0;
            while(true)
            {
                java.util.Vector a2 = this.questionsToAsk;
                int i2 = a2.size();
                if(i1 >= i2)
                {
                    int i3 = i + 1;
                    i = i3;
                }
                else
                {
                    java.util.Vector a3 = this.questionsToAsk;
                    Object a4 = a3.elementAt(i1);
                    com.cim.AIA.SelectionListener dummy0 = (com.cim.AIA.SelectionListener)a4;
                    com.cim.AIA.SelectionListener dummy1 = (com.cim.AIA.SelectionListener)a4;
                    ((com.cim.AIA.Questionable)a1).removeSelectionListener((com.cim.AIA.SelectionListener)a4);
                    int i4 = i1 + 1;
                    i1 = i4;
                }
            }
        }
        com.cim.AIA.ExitEvent a5 = new com.cim.AIA.ExitEvent((Object)this);
        int i5 = 0;
        while(true)
        {
            java.util.Vector a6 = this.questionsToAsk;
            int i6 = a6.size();
            if(i5 >= i6)
            {
                java.util.Vector a7 = new java.util.Vector();
                this.questionsToAsk = a7;
                return;
            }
            else
            {
                java.util.Vector a8 = this.questionsToAsk;
                Object a9 = a8.elementAt(i5);
                com.cim.AIA.Question dummy2 = (com.cim.AIA.Question)a9;
                com.cim.AIA.Question a10 = (com.cim.AIA.Question)a9;
                a10.processExitEvent(a5);
                int i7 = i5 + 1;
                i5 = i7;
            }
        }
    }
    
    public void removeFinishListener(com.cim.AIA.FinishListener a)
    {
        java.util.Vector a0 = this.finishListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void removeQuestionable(com.cim.AIA.Questionable a)
    {
        java.util.Vector a0 = this.questionableItems;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void removeRepaintListener(com.cim.AIA.RepaintListener a)
    {
        java.util.Vector a0 = this.repaintListeners;
        Object a1 = a;
        int i = a0.removeElement(a1)?1:0;
    }
    
    public void repaint()
    {
        int i = this.generatePaintEvents?1:0;
        {
            label1: {
                label0: {
                    if(i == 0)
                    {
                        break label0;
                    }
                    this.informRepaintListeners();
                    com.cim.AIA.Algorithm a = this.algorithm;
                    a.removeAllAnimationRequests();
                    this.questionPosition = 0;
                    java.util.Vector a0 = new java.util.Vector();
                    this.questionsToAsk = a0;
                    com.cim.AIA.Algorithm a1 = this.algorithm;
                    java.util.Vector a2 = a1.generateQuestions();
                    com.cim.AIA.Algorithm a3 = this.algorithm;
                    a3.removeAllQuestions();
                    int i0 = this.handleQuestions?1:0;
                    if(i0 == 0)
                    {
                        break label1;
                    }
                    if(a2 == null)
                    {
                        break label0;
                    }
                    this.questionsToAsk = a2;
                    java.util.Vector a4 = this.questionsToAsk;
                    int i1 = a4.size();
                    if(i1 >= 1)
                    {
                        int i2 = this.stepMode?1:0;
                        this.wasInStepMode = i2 != 0;
                        int i3 = this.runMode?1:0;
                        this.wasInRunMode = i3 != 0;
                        this.canDoRunMode = false;
                        this.canDoBackMode = true;
                        this.canDoStepMode = false;
                        this.canDoPauseMode = true;
                        this.setPauseMode();
                        int i4 = this.questionPosition;
                        this.askQuestion(i4);
                    }
                }
            }
        }
    }
    
    public synchronized void reset()
    {
        this.setReset(true, true, true, false, false);
    }
    
    public void run()
    {
        while(true)
        {
            int i = this.stepMode?1:0;
            label3: {
                label1: {
                    label0: {
                        if(i != 0)
                        {
                            break label0;
                        }
                        int i0 = this.runMode?1:0;
                        if(i0 == 0)
                        {
                            break label1;
                        }
                    }
                    int i1 = this.doneAlgorithm?1:0;
                    if(i1 != 0)
                    {
                        break label1;
                    }
                    this.haveFinished = false;
                    com.cim.AIA.Algorithm a = this.algorithm;
                    a.removeAllAnimationRequests();
                    com.cim.AIA.Algorithm a0 = this.algorithm;
                    a0.removeAllQuestions();
                    int i2 = this.threadEndLock?1:0;
                    label2: {
                        if(i2 != 1)
                        {
                            break label2;
                        }
                        break label3;
                    }
                    com.cim.AIA.Algorithm a1 = this.algorithm;
                    a1.runAlgorithm(true);
                    this.doneAlgorithm = true;
                    int i3 = this.doingReset?1:0;
                    label4: {
                        if(i3 != 0)
                        {
                            break label4;
                        }
                        int i4 = this.doingBack?1:0;
                        if(i4 == 0)
                        {
                            this.canDoBackMode = true;
                        }
                    }
                    int i5 = this.stepMode?1:0;
                    label5: {
                        if(i5 != 0)
                        {
                            break label5;
                        }
                        int i6 = this.runMode?1:0;
                        if(i6 == 0)
                        {
                            break label1;
                        }
                    }
                    this.repaint();
                    this.finish();
                }
                this.haveFinished = true;
                try
                {
                    com.cim.AIA.AlgorithmThread.sleep(150L);
                }
                catch(InterruptedException a2)
                {
                    java.io.PrintStream a3 = System.out;
                    a3.println((Object)a2);
                }
                int i7 = this.threadEndLock?1:0;
                if(i7 != 1)
                {
                    continue;
                }
            }
            this.threadEndLock = false;
            return;
        }
    }
    
    public synchronized void runMode()
    {
        int i = this.canDoRunMode?1:0;
        if(i != 0)
        {
            this.setRunMode();
        }
    }
    
    private synchronized void setBackMode()
    {
        this.canDoBackMode = false;
        this.canDoStepMode = false;
        this.generatePaintEvents = false;
        int i = this.stepsTaken;
        int i0 = this.backStepsToJump;
        this.setReset(false, true, false, true, false);
        this.generatePaintEvents = true;
        this.doingBack = true;
        int i1 = i - i0;
        this.stepsToBeTaken = i1;
        this.setRunMode();
        com.cim.AIA.AlgorithmWindow a = this.algorithmWindow;
        a.processStopRun();
    }
    
    public void setBreakPoint(com.cim.AIA.BreakPoint a)
    {
        this.breakpoint = a;
    }
    
    public synchronized void setData(com.cim.AIA.Copyable a)
    {
        this.originalData = a;
        this.setReset(true, true, true, false, false);
    }
    
    public synchronized void setData(com.cim.AIA.Copyable a, boolean b, boolean b0, boolean b1, boolean b2)
    {
        this.originalData = a;
        int i = b?1:0;
        int i0 = b0?1:0;
        int i1 = b1?1:0;
        int i2 = b2?1:0;
        this.setReset(true, i != 0, i0 != 0, i1 != 0, i2 != 0);
    }
    
    public void setDisplayMode(boolean b)
    {
        com.cim.AIA.AlgorithmThread.displayMode = b;
    }
    
    public synchronized void setHandleQuestions(boolean b)
    {
        this.handleQuestions = b;
        int i = b?1:0;
        if(i == 0)
        {
            this.removeAllQuestions();
        }
    }
    
    public void setLogger(com.cim.AIA.Logger a)
    {
        this.logger = a;
    }
    
    protected synchronized void setPauseMode()
    {
        this.continueExec = false;
        com.cim.AIA.AlgorithmWindow a = this.algorithmWindow;
        a.processStopRun();
    }
    
    protected synchronized void setReset(boolean b, boolean b0, boolean b1, boolean b2, boolean b3)
    {
        this.doingReset = true;
        int i = b?1:0;
        int i0 = b0?1:0;
        int i1 = b1?1:0;
        int i2 = b2?1:0;
        int i3 = b3?1:0;
        com.cim.AIA.Algorithm a = this.algorithm;
        a.setEnabled(false);
        this.stepMode = false;
        this.runMode = false;
        this.continueExec = true;
        this.generatePaintEvents = false;
        com.cim.AIA.AlgorithmWindow a0 = this.algorithmWindow;
        a0.processStopRun();
        this.removeAllQuestions();
        java.util.Vector a1 = new java.util.Vector();
        this.questionsToAsk = a1;
        this.questionPosition = 0;
        com.cim.AIA.Algorithm a2 = this.algorithm;
        int i4 = a2.haveStarted()?1:0;
        if(i4 == 0)
        {
            com.cim.AIA.Algorithm a3 = this.algorithm;
            a3.runAlgorithm(false);
        }
        try
        {
            while(true)
            {
                int i5 = this.haveFinished?1:0;
                if(i5 != 0)
                {
                    break;
                }
                else
                {
                    this.continueExec = true;
                    com.cim.AIA.AlgorithmThread.sleep(100L);
                }
            }
        }
        catch(InterruptedException a4)
        {
            java.io.PrintStream a5 = System.out;
            a5.println((Object)a4);
        }
        if(i1 != 0)
        {
            com.cim.AIA.Algorithm a6 = this.algorithm;
            a6.clearState();
        }
        if(i2 != 0)
        {
            com.cim.AIA.Algorithm a7 = this.algorithm;
            a7.storeState(i3 != 0);
        }
        Object a8 = this.originalData;
        Object a9 = ((com.cim.AIA.Copyable)a8).copy();
        if(i0 == 0)
        {
            com.cim.AIA.Algorithm a10 = this.algorithm;
            a10.changeData(a9);
        }
        else
        {
            com.cim.AIA.Algorithm a11 = this.algorithm;
            a11.reInitialise(a9);
        }
        com.cim.AIA.Algorithm a12 = this.algorithm;
        a12.setEnabled(true);
        this.stepsTaken = 0;
        com.cim.AIA.Algorithm a13 = this.algorithm;
        a13.removeAllAnimationRequests();
        com.cim.AIA.Algorithm a14 = this.algorithm;
        a14.removeAllQuestions();
        this.generatePaintEvents = true;
        if(i != 0)
        {
            this.canDoRunMode = true;
            this.canDoBackMode = true;
            this.canDoStepMode = true;
            this.canDoPauseMode = true;
            this.repaint();
            com.cim.AIA.AlgorithmWindow a15 = this.algorithmWindow;
            a15.setPosition("");
        }
        this.doneAlgorithm = false;
        this.doingReset = false;
    }
    
    private synchronized void setRunMode()
    {
        this.continueExec = true;
        this.stepMode = false;
        this.runMode = true;
        com.cim.AIA.AlgorithmWindow a = this.algorithmWindow;
        a.processRun();
    }
    
    public synchronized void setRunSleepDuration(int i)
    {
        if(i <= 0)
        {
            this.runSleepDuration = 1;
        }
        else
        {
            this.runSleepDuration = i;
        }
    }
    
    private synchronized void setStepMode()
    {
        this.canDoStepMode = false;
        this.canDoBackMode = false;
        this.continueExec = true;
        this.stepMode = true;
        this.runMode = false;
        com.cim.AIA.AlgorithmWindow a = this.algorithmWindow;
        a.processStopRun();
    }
    
    public synchronized void stepMode()
    {
        int i = this.canDoStepMode?1:0;
        if(i != 0)
        {
            this.setStepMode();
        }
    }
    
    static
    {
        com.cim.AIA.AlgorithmThread.displayMode = false;
    }
}