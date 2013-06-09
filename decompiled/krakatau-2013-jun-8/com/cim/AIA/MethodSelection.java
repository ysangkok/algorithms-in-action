package com.cim.AIA;

public class MethodSelection {
    protected String name;
    protected String key;
    protected com.cim.AIA.LadderTree ladderTree;
    
    public MethodSelection(String s, com.cim.AIA.LadderTree a, String s0)
    {
        super();
        this.name = s;
        this.key = s0;
        this.ladderTree = a;
        int i = a.containsKey(s0)?1:0;
        if(i != 0)
        {
            com.cim.AIA.LadderTree a0 = a.getLadderTree(s0);
            a0.setParent((com.cim.AIA.Tree)null);
        }
    }
    
    public MethodSelection(String s, String s0, String s1, String s2, com.cim.AIA.Logger a, com.cim.AIA.BreakPoint a0)
    {
        super();
        this.name = s;
        this.key = s2;
        com.cim.AIA.LadderTree a1 = com.cim.AIA.CodeCanvas.getLadderTreeFromFile(s0, s1, a, a0);
        this.ladderTree = a1;
        label0: {
            if(s2 == null)
            {
                break label0;
            }
            if(s2 == "")
            {
                break label0;
            }
            com.cim.AIA.LadderTree a2 = this.ladderTree;
            int i = a2.containsKey(s2)?1:0;
            if(i != 0)
            {
                com.cim.AIA.LadderTree a3 = this.ladderTree;
                com.cim.AIA.LadderTree a4 = a3.getLadderTree(s2);
                this.ladderTree = a4;
                com.cim.AIA.LadderTree a5 = this.ladderTree;
                a5.setParent((com.cim.AIA.Tree)null);
            }
        }
    }
    
    public String getKey()
    {
        String s = this.key;
        return s;
    }
    
    public com.cim.AIA.LadderTree getLadderTree()
    {
        com.cim.AIA.LadderTree a = this.ladderTree;
        return a;
    }
    
    public String getName()
    {
        String s = this.name;
        return s;
    }
}