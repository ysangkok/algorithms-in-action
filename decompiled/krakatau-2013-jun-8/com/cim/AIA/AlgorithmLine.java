package com.cim.AIA;

public class AlgorithmLine {
    final protected static String NO_EXPLAINATION = "";
    protected static boolean DEBUG_MODE;
    protected java.awt.Point location;
    protected boolean isMinMax;
    protected boolean isLabelMinMax;
    protected boolean isFirstMinMax;
    protected boolean isLastMinMax;
    protected com.cim.AIA.AlgorithmLine firstInstance;
    protected com.cim.AIA.AlgorithmLine labelInstance;
    protected String minmaxLabel;
    protected String label;
    protected String key;
    protected String explaination;
    protected String alternativeLabel;
    protected java.util.Vector triggers;
    protected int triggerCount;
    protected java.util.Vector lastLineKeys;
    protected int highlight;
    
    public static void setDebugMode(boolean b)
    {
        com.cim.AIA.AlgorithmLine.DEBUG_MODE = b;
    }
    
    public AlgorithmLine(String s)
    {
        super();
        java.awt.Point a = new java.awt.Point();
        this.location = a;
        this.isMinMax = false;
        this.isLabelMinMax = false;
        this.isFirstMinMax = false;
        this.isLastMinMax = false;
        this.firstInstance = null;
        this.labelInstance = null;
        this.label = "";
        this.key = "";
        this.explaination = "";
        this.alternativeLabel = "";
        java.util.Vector a0 = new java.util.Vector();
        this.triggers = a0;
        this.triggerCount = 0;
        java.util.Vector a1 = new java.util.Vector();
        this.lastLineKeys = a1;
        this.highlight = 0;
        java.util.StringTokenizer a2 = new java.util.StringTokenizer(s, "#");
        String s0 = a2.nextToken();
        java.util.StringTokenizer a3 = new java.util.StringTokenizer(s0, "|");
        String s1 = a3.nextToken();
        this.label = s1;
        int i = a3.hasMoreTokens()?1:0;
        if(i != 0)
        {
            String s2 = a3.nextToken();
            this.alternativeLabel = s2;
        }
        int i0 = a2.hasMoreTokens()?1:0;
        label0: {
            if(i0 == 0)
            {
                break label0;
            }
            String s3 = a2.nextToken();
            java.util.StringTokenizer a4 = new java.util.StringTokenizer(s3, "|");
            String s4 = a4.nextToken();
            this.key = s4;
            String s5 = this.key;
            String s6 = this.strip(s5);
            this.key = s6;
            while(true)
            {
                int i1 = a4.hasMoreTokens()?1:0;
                if(i1 == 0)
                {
                    break;
                }
                else
                {
                    String s7 = a4.nextToken();
                    String s8 = this.strip(s7);
                    this.addTrigger(s8);
                }
            }
        }
        int i2 = a2.hasMoreTokens()?1:0;
        label1: {
            if(i2 == 0)
            {
                break label1;
            }
            String s9 = a2.nextToken();
            java.util.StringTokenizer a5 = new java.util.StringTokenizer(s9, "|");
            while(true)
            {
                int i3 = a5.countTokens();
                if(i3 <= 1)
                {
                    break;
                }
                else
                {
                    String s10 = a5.nextToken();
                    String s11 = this.strip(s10);
                    java.util.Vector a6 = this.lastLineKeys;
                    a6.addElement((Object)s11);
                }
            }
            String s12 = a5.nextToken();
            this.explaination = s12;
        }
        while(true)
        {
            int i4 = a2.hasMoreTokens()?1:0;
            if(i4 == 0)
            {
                return;
            }
            else
            {
                StringBuilder a7 = new StringBuilder();
                String s13 = this.explaination;
                StringBuilder a8 = a7.append(s13);
                StringBuilder a9 = a8.append("\n\n");
                String s14 = a2.nextToken();
                StringBuilder a10 = a9.append(s14);
                String s15 = a10.toString();
                this.explaination = s15;
            }
        }
    }
    
    public AlgorithmLine(String s, String s0, String s1)
    {
        super();
        java.awt.Point a = new java.awt.Point();
        this.location = a;
        this.isMinMax = false;
        this.isLabelMinMax = false;
        this.isFirstMinMax = false;
        this.isLastMinMax = false;
        this.firstInstance = null;
        this.labelInstance = null;
        this.label = "";
        this.key = "";
        this.explaination = "";
        this.alternativeLabel = "";
        java.util.Vector a0 = new java.util.Vector();
        this.triggers = a0;
        this.triggerCount = 0;
        java.util.Vector a1 = new java.util.Vector();
        this.lastLineKeys = a1;
        this.highlight = 0;
        this.label = s;
        String s2 = this.strip(s0);
        this.key = s2;
        this.explaination = s1;
    }
    
    public AlgorithmLine(String s, String s0, String s1, String s2)
    {
        this(s, s1, s2);
        this.alternativeLabel = s0;
    }
    
    public void addLastLineKey(String s)
    {
        java.util.Vector a = this.lastLineKeys;
        a.addElement((Object)s);
    }
    
    public void addTrigger(String s)
    {
        java.util.Vector a = this.triggers;
        a.addElement((Object)s);
    }
    
    public void decreaseTriggerCount()
    {
        int i = this.triggerCount;
        if(i >= 1)
        {
            int i0 = this.triggerCount;
            int i1 = i0 - 1;
            this.triggerCount = i1;
        }
    }
    
    public String getExplaination()
    {
        String s = null;
        String s0 = this.explaination;
        if(s0 != "")
        {
            String s1 = this.explaination;
            s = s1;
        }
        else
        {
            s = "";
        }
        return s;
    }
    
    public com.cim.AIA.AlgorithmLine getFirstInstance()
    {
        com.cim.AIA.AlgorithmLine a = this.firstInstance;
        return a;
    }
    
    public int getHighlightLevel()
    {
        int i = this.highlight;
        return i;
    }
    
    public boolean getIsFirstMinMax()
    {
        int i = this.isFirstMinMax?1:0;
        return i != 0;
    }
    
    public boolean getIsLabelMinMax()
    {
        int i = this.isLabelMinMax?1:0;
        return i != 0;
    }
    
    public boolean getIsLastMinMax()
    {
        int i = this.isLastMinMax?1:0;
        return i != 0;
    }
    
    public boolean getIsMinMax()
    {
        int i = this.isMinMax?1:0;
        return i != 0;
    }
    
    public String getKey()
    {
        String s = this.key;
        return s;
    }
    
    public String getLabel()
    {
        String s = null;
        String s0 = this.alternativeLabel;
        label1: {
            label0: {
                if(s0 != "")
                {
                    break label0;
                }
                int i = com.cim.AIA.AlgorithmLine.DEBUG_MODE?1:0;
                if(i == 0)
                {
                    String s1 = this.label;
                    s = s1;
                    break label1;
                }
                else
                {
                    StringBuilder a = new StringBuilder();
                    String s2 = this.label;
                    StringBuilder a0 = a.append(s2);
                    StringBuilder a1 = a0.append(" ");
                    String s3 = this.key;
                    StringBuilder a2 = a1.append(s3);
                    String s4 = a2.toString();
                    s = s4;
                    break label1;
                }
            }
            int i0 = this.triggerCount;
            label2: {
                if(i0 == 0)
                {
                    break label2;
                }
                int i1 = com.cim.AIA.AlgorithmLine.DEBUG_MODE?1:0;
                if(i1 == 0)
                {
                    String s5 = this.alternativeLabel;
                    s = s5;
                    break label1;
                }
                else
                {
                    StringBuilder a3 = new StringBuilder();
                    String s6 = this.alternativeLabel;
                    StringBuilder a4 = a3.append(s6);
                    StringBuilder a5 = a4.append(" ");
                    String s7 = this.key;
                    StringBuilder a6 = a5.append(s7);
                    String s8 = a6.toString();
                    s = s8;
                    break label1;
                }
            }
            int i2 = com.cim.AIA.AlgorithmLine.DEBUG_MODE?1:0;
            if(i2 == 0)
            {
                String s9 = this.label;
                s = s9;
            }
            else
            {
                StringBuilder a7 = new StringBuilder();
                String s10 = this.label;
                StringBuilder a8 = a7.append(s10);
                StringBuilder a9 = a8.append(" ");
                String s11 = this.key;
                StringBuilder a10 = a9.append(s11);
                String s12 = a10.toString();
                s = s12;
            }
        }
        return s;
    }
    
    public com.cim.AIA.AlgorithmLine getLabelInstance()
    {
        com.cim.AIA.AlgorithmLine a = this.labelInstance;
        return a;
    }
    
    public java.awt.Point getLocation()
    {
        java.awt.Point a = this.location;
        return a;
    }
    
    public String getMinMaxLabel()
    {
        String s = this.minmaxLabel;
        return s;
    }
    
    public java.util.Vector getTriggers()
    {
        java.util.Vector a = this.triggers;
        return a;
    }
    
    public void increaseTriggerCount()
    {
        int i = this.triggerCount;
        int i0 = i + 1;
        this.triggerCount = i0;
    }
    
    public boolean isComment()
    {
        String s = this.label;
        int i = s.indexOf("//");
        int i0 = i != 0?0:1;
        return i0 != 0;
    }
    
    public boolean isHighlighted()
    {
        int i = this.highlight;
        int i0 = i != 0?1:0;
        return i0 != 0;
    }
    
    public boolean isLastLine(String s)
    {
        int i = 0;
        while(true)
        {
            int i0 = 0;
            java.util.Vector a = this.lastLineKeys;
            int i1 = a.size();
            label1: {
                label0: {
                    if(i < i1)
                    {
                        break label0;
                    }
                    i0 = 0;
                    break label1;
                }
                java.util.Vector a0 = this.lastLineKeys;
                Object a1 = a0.elementAt(i);
                String dummy = (String)a1;
                String s0 = (String)a1;
                int i2 = s0.equalsIgnoreCase(s)?1:0;
                if(i2 == 0)
                {
                    int i3 = i + 1;
                    i = i3;
                    continue;
                }
                i0 = 1;
            }
            return i0 != 0;
        }
    }
    
    public void setFirstInstance(com.cim.AIA.AlgorithmLine a)
    {
        this.firstInstance = a;
    }
    
    public void setHighlightLevel(int i)
    {
        this.highlight = i;
    }
    
    public void setIsFirstMinMax(boolean b)
    {
        this.isFirstMinMax = b;
    }
    
    public void setIsLabelMinMax(boolean b)
    {
        this.isLabelMinMax = b;
    }
    
    public void setIsLastMinMax(boolean b)
    {
        this.isLastMinMax = b;
    }
    
    public void setIsMinMax(boolean b)
    {
        this.isMinMax = b;
    }
    
    public void setLabelInstance(com.cim.AIA.AlgorithmLine a)
    {
        this.labelInstance = a;
    }
    
    public void setLocation(java.awt.Point a)
    {
        this.location = a;
    }
    
    public void setMinMaxLabel(String s)
    {
        this.minmaxLabel = s;
    }
    
    protected String strip(String s)
    {
        String s0 = s.trim();
        java.util.StringTokenizer a = new java.util.StringTokenizer(s, " ");
        String s1 = "";
        while(true)
        {
            int i = a.hasMoreTokens()?1:0;
            if(i == 0)
            {
                return s1;
            }
            else
            {
                StringBuilder a0 = new StringBuilder();
                StringBuilder a1 = a0.append(s1);
                String s2 = a.nextToken();
                StringBuilder a2 = a1.append(s2);
                String s3 = a2.toString();
                s1 = s3;
            }
        }
    }
    
    static
    {
        com.cim.AIA.AlgorithmLine.DEBUG_MODE = false;
    }
}