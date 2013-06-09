package com.cim.AIA;

abstract public class Algorithm {
    protected com.cim.AIA.AlgorithmThread algorithmThread;
    protected boolean debugMode;
    protected boolean enabled;
    private boolean started;
    
    public Algorithm(com.cim.AIA.AlgorithmThread a, Object a0)
    {
        super();
        this.debugMode = true;
        this.enabled = true;
        this.started = false;
        this.algorithmThread = a;
    }
    
    protected void askQuestionsWithoutSetPosition()
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        label1: {
            label0: {
                if(a != null)
                {
                    break label0;
                }
                java.io.PrintStream a0 = System.out;
                a0.println("com.cim.AIA.Algorithm.askQuestionWithoutSetPosition: algorithmThread is null");
                break label1;
            }
            int i = this.hasQuestions()?1:0;
            if(i == 0)
            {
                break label1;
            }
            com.cim.AIA.AlgorithmThread a1 = this.algorithmThread;
            int i0 = a1.getHandleQuestions()?1:0;
            if(i0 != 0)
            {
                com.cim.AIA.AlgorithmThread a2 = this.algorithmThread;
                com.cim.AIA.AlgorithmThread a3 = this.algorithmThread;
                String s = a3.getPosition();
                a2.paintAndWait(s, false);
            }
        }
    }
    
    protected void changeData(Object a)
    {
        this.reInitialise(a);
    }
    
    public void cleanUp()
    {
        this.algorithmThread = null;
    }
    
    protected void clearState()
    {
    }
    
    protected void displayMessage(String s)
    {
        int i = this.debugMode?1:0;
        if(i != 0)
        {
            java.io.PrintStream a = System.out;
            a.println(s);
        }
    }
    
    abstract protected java.util.Vector generateQuestions();
    
    
    abstract public com.cim.AIA.MultipleTween generateTweens(com.cim.AIA.Paintable arg, Object arg0, int arg1);
    
    
    public com.cim.AIA.BreakPoint getBreakPoint()
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        com.cim.AIA.BreakPoint a0 = a.getBreakPoint();
        return a0;
    }
    
    public com.cim.AIA.Logger getLogger()
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        com.cim.AIA.Logger a0 = a.getLogger();
        return a0;
    }
    
    abstract protected boolean hasQuestions();
    
    
    public boolean haveStarted()
    {
        int i = this.started?1:0;
        return i != 0;
    }
    
    public boolean isEnabled()
    {
        int i = this.enabled?1:0;
        return i != 0;
    }
    
    abstract public void reInitialise(Object arg);
    
    
    abstract protected void removeAllAnimationRequests();
    
    
    abstract protected void removeAllQuestions();
    
    
    public void repaint()
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        if(a == null)
        {
            java.io.PrintStream a0 = System.out;
            a0.println("com.cim.AIA.Algorithm.repaint: algorithmThread is null");
        }
        else
        {
            com.cim.AIA.AlgorithmThread a1 = this.algorithmThread;
            a1.informRepaintListeners();
        }
    }
    
    abstract protected void run();
    
    
    protected void run(boolean b)
    {
        this.run();
    }
    
    public void runAlgorithm(boolean b)
    {
        int i = this.enabled?1:0;
        int i0 = b?1:0;
        if(i != 0)
        {
            this.started = true;
            this.run(i0 != 0);
        }
    }
    
    protected void setEnabled(boolean b)
    {
        this.enabled = b;
    }
    
    protected void setPosition(String s)
    {
        com.cim.AIA.AlgorithmThread a = this.algorithmThread;
        if(a == null)
        {
            java.io.PrintStream a0 = System.out;
            a0.println("com.cim.AIA.Algorithm.setPosition: algorithmThread is null");
        }
        else
        {
            com.cim.AIA.AlgorithmThread a1 = this.algorithmThread;
            a1.paintAndWait(s);
        }
    }
    
    protected void storeState(boolean b)
    {
    }
}