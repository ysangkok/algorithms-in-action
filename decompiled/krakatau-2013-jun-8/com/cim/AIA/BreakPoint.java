package com.cim.AIA;

class BreakPoint {
    protected com.cim.AIA.AlgorithmThread algorithmThread;
    protected com.cim.AIA.AlgorithmWindow algorithmWindow;
    protected com.cim.AIA.AnimationWindow animationWindow;
    protected java.util.Vector breakPointLines;
    public boolean inMutex;
    private boolean inAdd;
    private int saveIndex;
    private boolean hasBreaked;
    public boolean enabled;
    
    public BreakPoint()
    {
        super();
        java.util.Vector a = new java.util.Vector();
        this.breakPointLines = a;
        this.enabled = false;
    }
    
    public BreakPoint(com.cim.AIA.AlgorithmThread a, com.cim.AIA.AlgorithmWindow a0, com.cim.AIA.AnimationWindow a1)
    {
        super();
        java.util.Vector a2 = new java.util.Vector();
        this.breakPointLines = a2;
        this.enabled = false;
        this.initialise(a, a0, a1);
    }
    
    public void add()
    {
        int i = this.enabled?1:0;
        if(i != 0)
        {
            com.cim.AIA.AnimationWindow a = this.animationWindow;
            a.setEnabled(false);
            com.cim.AIA.AlgorithmWindow a0 = this.algorithmWindow;
            a0.processStartMutex();
            this.inMutex = true;
            this.inAdd = true;
        }
    }
    
    private boolean add(com.cim.AIA.AlgorithmLine a)
    {
        int i = 0;
        int i0 = this.enabled?1:0;
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            int i1 = this.find(a);
            if(i1 == -1)
            {
                java.util.Vector a0 = this.breakPointLines;
                com.cim.AIA.BreakPointLine a1 = new com.cim.AIA.BreakPointLine(a);
                a0.addElement((Object)a1);
                i = 1;
            }
            else
            {
                i = 0;
            }
        }
        return i != 0;
    }
    
    public void breakPointReceived()
    {
        int i = this.enabled?1:0;
        if(i != 0)
        {
            com.cim.AIA.AnimationWindow a = this.animationWindow;
            com.cim.AIA.AlgorithmThread a0 = this.algorithmThread;
            com.cim.AIA.ControlEvent a1 = new com.cim.AIA.ControlEvent((Object)a0, 123125);
            a.informControlListeners(a1);
            com.cim.AIA.AlgorithmWindow a2 = this.algorithmWindow;
            a2.processStopRun();
        }
    }
    
    public void cleanUp()
    {
        this.algorithmThread = null;
        this.algorithmWindow = null;
        this.animationWindow = null;
        java.util.Vector a = this.breakPointLines;
        if(a != null)
        {
            java.util.Vector a0 = this.breakPointLines;
            a0.removeAllElements();
        }
        this.breakPointLines = null;
    }
    
    public void del()
    {
        int i = this.enabled?1:0;
        if(i != 0)
        {
            com.cim.AIA.AnimationWindow a = this.animationWindow;
            a.setEnabled(false);
            com.cim.AIA.AlgorithmWindow a0 = this.algorithmWindow;
            a0.processStartMutex();
            this.inMutex = true;
            this.inAdd = false;
        }
    }
    
    private boolean del(com.cim.AIA.AlgorithmLine a)
    {
        int i = 0;
        int i0 = this.enabled?1:0;
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            int i1 = this.find(a);
            if(i1 != -1)
            {
                java.util.Vector a0 = this.breakPointLines;
                a0.removeElementAt(i1);
                i = 1;
            }
            else
            {
                i = 0;
            }
        }
        return i != 0;
    }
    
    private int find(com.cim.AIA.AlgorithmLine a)
    {
        int i = 0;
        int i0 = this.enabled?1:0;
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                i = -1;
                break label1;
            }
            int i1 = 0;
            while(true)
            {
                java.util.Vector a0 = this.breakPointLines;
                int i2 = a0.size();
                label2: {
                    if(i1 < i2)
                    {
                        break label2;
                    }
                    i = -1;
                    break;
                }
                java.util.Vector a1 = this.breakPointLines;
                Object a2 = a1.elementAt(i1);
                com.cim.AIA.BreakPointLine dummy = (com.cim.AIA.BreakPointLine)a2;
                com.cim.AIA.BreakPointLine a3 = (com.cim.AIA.BreakPointLine)a2;
                com.cim.AIA.AlgorithmLine a4 = a3.getAlgorithmLine();
                if(a4 != a)
                {
                    int i3 = i1 + 1;
                    i1 = i3;
                    continue;
                }
                i = i1;
                break;
            }
        }
        return i;
    }
    
    public com.cim.AIA.BreakPointLine getLastSearched()
    {
        com.cim.AIA.BreakPointLine a = null;
        int i = this.enabled?1:0;
        if(i != 0)
        {
            java.util.Vector a0 = this.breakPointLines;
            int i0 = this.saveIndex;
            Object a1 = a0.elementAt(i0);
            com.cim.AIA.BreakPointLine dummy = (com.cim.AIA.BreakPointLine)a1;
            com.cim.AIA.BreakPointLine a2 = (com.cim.AIA.BreakPointLine)a1;
            a = a2;
        }
        else
        {
            a = null;
        }
        return a;
    }
    
    public boolean hasBreaked()
    {
        int i = 0;
        int i0 = this.enabled?1:0;
        if(i0 != 0)
        {
            int i1 = this.hasBreaked?1:0;
            this.hasBreaked = false;
            i = i1;
        }
        else
        {
            i = 0;
        }
        return i != 0;
    }
    
    public void highlightBreakPoints()
    {
        int i = this.enabled?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            int i0 = 0;
            while(true)
            {
                java.util.Vector a = this.breakPointLines;
                if(a == null)
                {
                    break;
                }
                java.util.Vector a0 = this.breakPointLines;
                int i1 = a0.size();
                if(i0 >= i1)
                {
                    break;
                }
                else
                {
                    java.util.Vector a1 = this.breakPointLines;
                    Object a2 = a1.elementAt(i0);
                    com.cim.AIA.BreakPointLine dummy = (com.cim.AIA.BreakPointLine)a2;
                    com.cim.AIA.BreakPointLine a3 = (com.cim.AIA.BreakPointLine)a2;
                    com.cim.AIA.AlgorithmLine a4 = a3.getAlgorithmLine();
                    a4.setHighlightLevel(3);
                    int i2 = i0 + 1;
                    i0 = i2;
                }
            }
        }
    }
    
    public void initialise(com.cim.AIA.AlgorithmThread a, com.cim.AIA.AlgorithmWindow a0, com.cim.AIA.AnimationWindow a1)
    {
        this.algorithmThread = a;
        this.algorithmWindow = a0;
        this.animationWindow = a1;
        this.reInit();
    }
    
    public boolean isKeyBreaked(String s)
    {
        int i = 0;
        int i0 = this.enabled?1:0;
        label1: {
            label0: {
                if(i0 != 0)
                {
                    break label0;
                }
                i = 0;
                break label1;
            }
            int i1 = 0;
            while(true)
            {
                java.util.Vector a = this.breakPointLines;
                label3: {
                    label2: {
                        if(a == null)
                        {
                            break label2;
                        }
                        java.util.Vector a0 = this.breakPointLines;
                        int i2 = a0.size();
                        if(i1 < i2)
                        {
                            break label3;
                        }
                    }
                    i = 0;
                    break;
                }
                java.util.Vector a1 = this.breakPointLines;
                Object a2 = a1.elementAt(i1);
                com.cim.AIA.BreakPointLine dummy = (com.cim.AIA.BreakPointLine)a2;
                com.cim.AIA.BreakPointLine a3 = (com.cim.AIA.BreakPointLine)a2;
                com.cim.AIA.AlgorithmLine a4 = a3.getAlgorithmLine();
                String s0 = a4.getKey();
                int i3 = s0.equals((Object)s)?1:0;
                if(i3 == 0)
                {
                    int i4 = i1 + 1;
                    i1 = i4;
                    continue;
                }
                this.saveIndex = i1;
                i = 1;
                break;
            }
        }
        return i != 0;
    }
    
    public void processBreakPoint(String s)
    {
        int i = this.enabled?1:0;
        if(i != 0)
        {
            this.hasBreaked = true;
        }
    }
    
    public void processMutex(com.cim.AIA.AlgorithmLine a)
    {
        int i = this.enabled?1:0;
        label1: {
            label0: {
                if(i != 0)
                {
                    break label0;
                }
                break label1;
            }
            label2: {
                if(a != null)
                {
                    break label2;
                }
                this.release();
                break label1;
            }
            int i0 = this.inAdd?1:0;
            label4: {
                int i1 = 0;
                label3: {
                    if(i0 != 0)
                    {
                        break label3;
                    }
                    int i2 = this.del(a)?1:0;
                    if(i2 == 0)
                    {
                        break label4;
                    }
                    String s = a.getKey();
                    String s0 = a.getLabel();
                    com.cim.AIA.Log a0 = new com.cim.AIA.Log((byte)5, (byte)8, s, s0);
                    com.cim.AIA.AlgorithmThread a1 = this.algorithmThread;
                    com.cim.AIA.Logger a2 = a1.getLogger();
                    if(a2 == null)
                    {
                        break label4;
                    }
                    else
                    {
                        com.cim.AIA.AlgorithmThread a3 = this.algorithmThread;
                        com.cim.AIA.Logger a4 = a3.getLogger();
                        a4.addLog(a0);
                        break label4;
                    }
                }
                int i3 = this.add(a)?1:0;
                if(i3 == 0)
                {
                    break label4;
                }
                String s1 = a.getKey();
                com.cim.AIA.AlgorithmThread a5 = this.algorithmThread;
                com.cim.AIA.AlgorithmWindow a6 = a5.getAlgorithmWindow();
                com.cim.AIA.LadderTree a7 = a6.getLadderTree(s1);
                if(a7 == null)
                {
                    i1 = 0;
                }
                else
                {
                    int i4 = a7.countHiddenLines();
                    i1 = i4;
                }
                String s2 = a.getLabel();
                com.cim.AIA.StepLog a8 = new com.cim.AIA.StepLog((byte)5, (byte)7, s1, i1, s2);
                com.cim.AIA.AlgorithmThread a9 = this.algorithmThread;
                com.cim.AIA.Logger a10 = a9.getLogger();
                if(a10 != null)
                {
                    com.cim.AIA.AlgorithmThread a11 = this.algorithmThread;
                    com.cim.AIA.Logger a12 = a11.getLogger();
                    a12.addLog((com.cim.AIA.Log)a8);
                }
                int i5 = com.cim.AIA.Logger.DEBUG?1:0;
                label5: {
                    if(i5 == 0)
                    {
                        break label5;
                    }
                    java.io.PrintStream a13 = System.err;
                    StringBuilder a14 = new StringBuilder();
                    StringBuilder a15 = a14.append("pos= ");
                    StringBuilder a16 = a15.append(s1);
                    String s3 = a16.toString();
                    a13.println(s3);
                    java.io.PrintStream a17 = System.err;
                    StringBuilder a18 = new StringBuilder();
                    StringBuilder a19 = a18.append("line= ");
                    String s4 = a.getLabel();
                    StringBuilder a20 = a19.append(s4);
                    String s5 = a20.toString();
                    a17.println(s5);
                    java.io.PrintStream a21 = System.err;
                    StringBuilder a22 = new StringBuilder();
                    StringBuilder a23 = a22.append("algorithmThread.getAlgorithmWindow()= ");
                    com.cim.AIA.AlgorithmThread a24 = this.algorithmThread;
                    com.cim.AIA.AlgorithmWindow a25 = a24.getAlgorithmWindow();
                    StringBuilder a26 = a23.append((Object)a25);
                    String s6 = a26.toString();
                    a21.println(s6);
                    java.io.PrintStream a27 = System.err;
                    StringBuilder a28 = new StringBuilder();
                    StringBuilder a29 = a28.append(".getLadderTree(pos)= ");
                    com.cim.AIA.AlgorithmThread a30 = this.algorithmThread;
                    com.cim.AIA.AlgorithmWindow a31 = a30.getAlgorithmWindow();
                    com.cim.AIA.LadderTree a32 = a31.getLadderTree(s1);
                    StringBuilder a33 = a29.append((Object)a32);
                    String s7 = a33.toString();
                    a27.println(s7);
                    int i6 = 0;
                    while(true)
                    {
                        java.util.Vector a34 = this.breakPointLines;
                        int i7 = a34.size();
                        if(i6 >= i7)
                        {
                            break;
                        }
                        else
                        {
                            java.io.PrintStream a35 = System.err;
                            StringBuilder a36 = new StringBuilder();
                            StringBuilder a37 = a36.append("breakpoint ");
                            StringBuilder a38 = a37.append(i6);
                            StringBuilder a39 = a38.append(": ");
                            java.util.Vector a40 = this.breakPointLines;
                            Object a41 = a40.elementAt(i6);
                            com.cim.AIA.BreakPointLine dummy = (com.cim.AIA.BreakPointLine)a41;
                            com.cim.AIA.BreakPointLine a42 = (com.cim.AIA.BreakPointLine)a41;
                            String s8 = a42.getKey();
                            StringBuilder a43 = a39.append(s8);
                            String s9 = a43.toString();
                            a35.println(s9);
                            int i8 = i6 + 1;
                            i6 = i8;
                        }
                    }
                }
            }
            this.release();
        }
    }
    
    public void reInit()
    {
        java.util.Vector a = new java.util.Vector();
        this.breakPointLines = a;
        this.inMutex = false;
        this.inAdd = false;
        this.saveIndex = -1;
        this.hasBreaked = false;
    }
    
    private void release()
    {
        int i = this.enabled?1:0;
        if(i != 0)
        {
            com.cim.AIA.AnimationWindow a = this.animationWindow;
            a.setEnabled(true);
            com.cim.AIA.AlgorithmWindow a0 = this.algorithmWindow;
            a0.processEndMutex();
            this.inMutex = false;
        }
    }
}