package com.cim.AIA;

class BreakPointLine {
    protected com.cim.AIA.AlgorithmLine algorithmLine;
    protected com.cim.AIA.AlgorithmLine childTag;
    
    public BreakPointLine(com.cim.AIA.AlgorithmLine a)
    {
        super();
        this.algorithmLine = a;
        this.childTag = null;
    }
    
    public com.cim.AIA.AlgorithmLine getAlgorithmLine()
    {
        com.cim.AIA.AlgorithmLine a = this.algorithmLine;
        return a;
    }
    
    public com.cim.AIA.AlgorithmLine getChild()
    {
        com.cim.AIA.AlgorithmLine a = this.childTag;
        return a;
    }
    
    public String getKey()
    {
        com.cim.AIA.AlgorithmLine a = this.algorithmLine;
        String s = a.getKey();
        return s;
    }
    
    public boolean isChildSet()
    {
        com.cim.AIA.AlgorithmLine a = this.childTag;
        int i = a != null?1:0;
        return i != 0;
    }
    
    public void setChild(com.cim.AIA.AlgorithmLine a)
    {
        this.childTag = a;
    }
}