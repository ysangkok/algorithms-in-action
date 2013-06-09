package com.cim.AIA;

class BreakPointLine
{
    protected AlgorithmLine algorithmLine;
    protected AlgorithmLine childTag;
    
    public BreakPointLine(final AlgorithmLine algLine) {
        super();
        this.algorithmLine = algLine;
        this.childTag = null;
    }
    
    public AlgorithmLine getAlgorithmLine() {
        return this.algorithmLine;
    }
    
    public AlgorithmLine getChild() {
        return this.childTag;
    }
    
    public String getKey() {
        return this.algorithmLine.getKey();
    }
    
    public boolean isChildSet() {
        return this.childTag != null;
    }
    
    public void setChild(final AlgorithmLine child) {
        this.childTag = child;
    }
}
